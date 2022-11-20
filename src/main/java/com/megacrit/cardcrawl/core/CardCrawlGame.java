package com.megacrit.cardcrawl.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.codedisaster.steamworks.SteamUtils;
import com.codedisaster.steamworks.SteamUtilsCallback;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.megacrit.cardcrawl.audio.MusicMaster;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.CharacterManager;
import com.megacrit.cardcrawl.daily.TimeHelper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.AsyncSaver;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.DrawMaster;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.GameTips;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.helpers.SeedHelper;
import com.megacrit.cardcrawl.helpers.ShaderHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.helpers.TrialHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.DevInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.helpers.steamInput.SteamInputHelper;
import com.megacrit.cardcrawl.integrations.DistributorFactory;
import com.megacrit.cardcrawl.integrations.PublisherIntegration;
import com.megacrit.cardcrawl.integrations.SteelSeries;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.metrics.BotDataUploader;
import com.megacrit.cardcrawl.metrics.MetricData;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rewards.RewardSave;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.screens.DoorUnlockScreen;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.screens.SingleRelicViewPopup;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.splash.SplashScreen;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.trials.AbstractTrial;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;
import com.megacrit.cardcrawl.ui.panels.SeedPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import de.robojumper.ststwitch.TwitchConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CardCrawlGame implements ApplicationListener {
    private OrthographicCamera camera;
    public static FitViewport viewport;
    public static PolygonSpriteBatch psb;
    private SpriteBatch sb;
    public static GameCursor cursor;
    public static int popupMX;
    public static int popupMY;
    public static AbstractDungeon dungeon;
    public static MainMenuScreen mainMenuScreen;
    public static SplashScreen splashScreen;
    public static DungeonTransitionScreen dungeonTransitionScreen;
    public static CancelButton cancelButton;
    public static MusicMaster music;
    public static SoundMaster sound;
    public static GameTips tips;
    public static SingleCardViewPopup cardPopup;
    public static SingleRelicViewPopup relicPopup;
    public static String nextDungeon;
    public static boolean loadingSave;
    public static Prefs saveSlotPref;
    public static Prefs playerPref;
    public static String playerName;
    public static String alias;
    public static CharacterManager characterManager;
    public static SkeletonRendererDebug debugRenderer;
    public static Scanner sControllerScanner;
    public static SteamUtils clientUtils;
    public static MetricData metricData;
    public static PublisherIntegration publisherIntegration;
    public static SteelSeries steelSeries;
    public static LocalizedStrings languagePack;
    public static String VERSION_NUM = "[V2.3.2] (10-04-2022)";
    public static String TRUE_VERSION_NUM = "2022-10-04";
    public static boolean isPopupOpen = false;
    public static ScreenShake screenShake = new ScreenShake();
    public static GameMode mode = GameMode.CHAR_SELECT;
    public static boolean startOver = false;
    private static boolean queueCredits = false;
    public static boolean playCreditsBgm = false;
    public static boolean MUTE_IF_BG = false;
    public static AbstractPlayer.PlayerClass chosenCharacter = null;
    public static SaveFile saveFile = null;
    public static int saveSlot = 0;
    public static int monstersSlain = 0;
    public static int elites1Slain = 0;
    public static int elites2Slain = 0;
    public static int elites3Slain = 0;
    public static int elitesModdedSlain = 0;
    public static int champion = 0;
    public static int perfect = 0;
    public static boolean overkill = false;
    public static boolean combo = false;
    public static boolean cheater = false;
    public static int goldGained = 0;
    public static int cardsPurged = 0;
    public static int potionsBought = 0;
    public static int mysteryMachine = 0;
    public static float playtime = 0.0f;
    public static boolean stopClock = false;
    public static AbstractTrial trial = null;
    public static Thread sInputDetectThread = null;
    private static Color screenColor = Color.BLACK.cpy();
    public static float screenTimer = 2.0f;
    public static float screenTime = 2.0f;
    private static boolean fadeIn = true;
    public static boolean displayVersion = true;
    public static String preferenceDir = null;
    private static final Logger logger = LogManager.getLogger(CardCrawlGame.class.getName());
    private FPSLogger fpsLogger = new FPSLogger();
    public boolean prevDebugKeyDown = false;
    SteamInputHelper steamInputHelper = null;
    private boolean displayCursor = true;
    private SteamUtilsCallback clUtilsCallback = new SteamUtilsCallback() { // from class: com.megacrit.cardcrawl.core.CardCrawlGame.1
        @Override // com.codedisaster.steamworks.SteamUtilsCallback
        public void onSteamShutdown() {
            CardCrawlGame.logger.error("Steam client requested to shut down!");
        }
    };

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/core/CardCrawlGame$GameMode.class */
    public enum GameMode {
        CHAR_SELECT,
        GAMEPLAY,
        DUNGEON_TRANSITION,
        SPLASH
    }

    public CardCrawlGame(String prefDir) {
        preferenceDir = prefDir;
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        if (Settings.isAlpha) {
            TRUE_VERSION_NUM += " ALPHA";
            VERSION_NUM += " ALPHA";
        } else if (Settings.isBeta) {
            VERSION_NUM += " BETA";
        }
        try {
            TwitchConfig.createConfig();
            BuildSettings buildSettings = new BuildSettings(Gdx.files.internal(BuildSettings.defaultFilename).reader());
            logger.info("DistributorPlatform=" + buildSettings.getDistributor());
            logger.info("isModded=" + Settings.isModded);
            logger.info("isBeta=" + Settings.isBeta);
            publisherIntegration = DistributorFactory.getEnabledDistributor(buildSettings.getDistributor());
            saveMigration();
            saveSlotPref = SaveHelper.getPrefs("STSSaveSlots");
            saveSlot = saveSlotPref.getInteger("DEFAULT_SLOT", 0);
            playerPref = SaveHelper.getPrefs("STSPlayer");
            playerName = saveSlotPref.getString(SaveHelper.slotName("PROFILE_NAME", saveSlot), "");
            if (playerName.equals("")) {
                playerName = playerPref.getString("name", "");
            }
            alias = playerPref.getString("alias", "");
            if (alias.equals("")) {
                alias = generateRandomAlias();
                playerPref.putString("alias", alias);
                playerPref.flush();
            }
            Settings.initialize(false);
            this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if (Settings.VERT_LETTERBOX_AMT == 0 && Settings.HORIZ_LETTERBOX_AMT == 0) {
                this.camera.position.set(this.camera.viewportWidth / 2.0f, this.camera.viewportHeight / 2.0f, 0.0f);
                this.camera.update();
                viewport = new FitViewport(Settings.WIDTH, Settings.HEIGHT, this.camera);
                viewport.apply();
            } else {
                this.camera.position.set(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, 0.0f);
                this.camera.update();
                viewport = new FitViewport(Settings.WIDTH, Settings.M_H - (Settings.HEIGHT / 2), this.camera);
            }
            languagePack = new LocalizedStrings();
            cardPopup = new SingleCardViewPopup();
            relicPopup = new SingleRelicViewPopup();
            if (Settings.IS_FULLSCREEN) {
                resize(Settings.M_W, Settings.M_H);
            }
            Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("images/blank.png")), 0, 0));
            this.sb = new SpriteBatch();
            psb = new PolygonSpriteBatch();
            music = new MusicMaster();
            sound = new SoundMaster();
            AbstractCreature.initialize();
            AbstractCard.initialize();
            GameDictionary.initialize();
            ImageMaster.initialize();
            AbstractPower.initialize();
            FontHelper.initialize();
            AbstractCard.initializeDynamicFrameWidths();
            UnlockTracker.initialize();
            CardLibrary.initialize();
            RelicLibrary.initialize();
            InputHelper.initialize();
            TipTracker.initialize();
            ModHelper.initialize();
            ShaderHelper.initializeShaders();
            UnlockTracker.retroactiveUnlock();
            CInputHelper.loadSettings();
            clientUtils = new SteamUtils(this.clUtilsCallback);
            this.steamInputHelper = new SteamInputHelper();
            steelSeries = new SteelSeries();
            cursor = new GameCursor();
            metricData = new MetricData();
            cancelButton = new CancelButton();
            tips = new GameTips();
            characterManager = new CharacterManager();
            splashScreen = new SplashScreen();
            mode = GameMode.SPLASH;
        } catch (Exception e) {
            logger.info("Exception occurred in CardCrawlGame create method!");
            ExceptionHandler.handleException(e, logger);
            Gdx.app.exit();
        }
    }

    public static void reloadPrefs() {
        playerPref = SaveHelper.getPrefs("STSPlayer");
        alias = playerPref.getString("alias", "");
        if (alias.equals("")) {
            alias = generateRandomAlias();
            playerPref.putString("alias", alias);
        }
        music.fadeOutBGM();
        mainMenuScreen.fadeOutMusic();
        InputActionSet.prefs = SaveHelper.getPrefs("STSInputSettings");
        InputActionSet.load();
        CInputActionSet.prefs = SaveHelper.getPrefs("STSInputSettings_Controller");
        CInputActionSet.load();
        if (SteamInputHelper.numControllers == 1) {
            SteamInputHelper.initActions(SteamInputHelper.controllerHandles[0]);
        }
        characterManager = new CharacterManager();
        Settings.initialize(true);
        UnlockTracker.initialize();
        CardLibrary.resetForReload();
        CardLibrary.initialize();
        RelicLibrary.resetForReload();
        RelicLibrary.initialize();
        TipTracker.initialize();
        logger.info("TEXTURE COUNT: " + Texture.getNumManagedTextures());
        screenColor.a = 0.0f;
        screenTime = 0.01f;
        screenTimer = 0.01f;
        fadeIn = false;
        startOver = true;
    }

    public void saveMigration() {
        if (!SaveHelper.saveExists()) {
            Preferences p = Gdx.app.getPreferences("STSPlayer");
            if (!p.getString("name", "").equals("") || Gdx.app.getPreferences("STSDataVagabond").getLong(CharStat.PLAYTIME) != 0) {
                logger.info("Migrating Save...");
                migrateHelper("STSPlayer");
                migrateHelper("STSUnlocks");
                migrateHelper("STSUnlockProgress");
                migrateHelper("STSTips");
                migrateHelper("STSSound");
                migrateHelper("STSSeenRelics");
                migrateHelper("STSSeenCards");
                migrateHelper("STSSeenBosses");
                migrateHelper("STSGameplaySettings");
                migrateHelper("STSDataVagabond");
                migrateHelper("STSDataTheSilent");
                migrateHelper("STSAchievements");
                if (MathUtils.randomBoolean(0.5f)) {
                    logger.warn("Save Migration");
                    return;
                }
                return;
            }
            logger.info("New player, no migration.");
            return;
        }
        logger.info("No migration");
    }

    public void migrateHelper(String file) {
        Preferences p = Gdx.app.getPreferences(file);
        Prefs p2 = SaveHelper.getPrefs(file);
        Map<String, ?> map = p.get();
        for (Map.Entry<String, ?> c : map.entrySet()) {
            p2.putString(c.getKey(), p.getString(c.getKey()));
        }
        p2.flush();
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        try {
            TimeHelper.update();
            if (Gdx.graphics.getRawDeltaTime() <= 0.1f) {
                if (!SteamInputHelper.alive) {
                    CInputHelper.initializeIfAble();
                }
                update();
                this.sb.setProjectionMatrix(this.camera.combined);
                psb.setProjectionMatrix(this.camera.combined);
                Gdx.gl.glClear(16384);
                this.sb.begin();
                this.sb.setColor(Color.WHITE);
                switch (mode) {
                    case SPLASH:
                        splashScreen.render(this.sb);
                        break;
                    case CHAR_SELECT:
                        mainMenuScreen.render(this.sb);
                        break;
                    case GAMEPLAY:
                        if (dungeonTransitionScreen == null) {
                            if (dungeon != null) {
                                dungeon.render(this.sb);
                                break;
                            }
                        } else {
                            dungeonTransitionScreen.render(this.sb);
                            break;
                        }
                        break;
                    case DUNGEON_TRANSITION:
                        break;
                    default:
                        logger.info("Unknown Game Mode: " + mode.name());
                        break;
                }
                DrawMaster.draw(this.sb);
                if (cardPopup.isOpen) {
                    cardPopup.render(this.sb);
                }
                if (relicPopup.isOpen) {
                    relicPopup.render(this.sb);
                }
                TipHelper.render(this.sb);
                if (mode != GameMode.SPLASH) {
                    renderBlackFadeScreen(this.sb);
                    if (this.displayCursor) {
                        if (isPopupOpen) {
                            InputHelper.mX = popupMX;
                            InputHelper.mY = popupMY;
                        }
                        cursor.render(this.sb);
                    }
                }
                if (Settings.HORIZ_LETTERBOX_AMT != 0) {
                    this.sb.setColor(Color.BLACK);
                    this.sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, -Settings.HORIZ_LETTERBOX_AMT);
                    this.sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, Settings.HEIGHT, Settings.WIDTH, Settings.HORIZ_LETTERBOX_AMT);
                } else if (Settings.VERT_LETTERBOX_AMT != 0) {
                    this.sb.setColor(Color.BLACK);
                    this.sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, -Settings.VERT_LETTERBOX_AMT, Settings.HEIGHT);
                    this.sb.draw(ImageMaster.WHITE_SQUARE_IMG, Settings.WIDTH, 0.0f, Settings.VERT_LETTERBOX_AMT, Settings.HEIGHT);
                }
                this.sb.end();
            }
        } catch (Exception e) {
            logger.info("Exception occurred in CardCrawlGame render method!");
            ExceptionHandler.handleException(e, logger);
            Gdx.app.exit();
        }
    }

    private void renderBlackFadeScreen(SpriteBatch sb) {
        sb.setColor(screenColor);
        if (screenColor.a < 0.55f && !mainMenuScreen.bg.activated) {
            mainMenuScreen.bg.activated = true;
        }
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
    }

    public void updateFade() {
        if (screenTimer != 0.0f) {
            screenTimer -= Gdx.graphics.getDeltaTime();
            if (screenTimer < 0.0f) {
                screenTimer = 0.0f;
            }
            if (fadeIn) {
                screenColor.a = Interpolation.fade.apply(1.0f, 0.0f, 1.0f - (screenTimer / screenTime));
                return;
            }
            screenColor.a = Interpolation.fade.apply(0.0f, 1.0f, 1.0f - (screenTimer / screenTime));
            if (startOver && screenTimer == 0.0f) {
                if (AbstractDungeon.scene != null) {
                    AbstractDungeon.scene.fadeOutAmbiance();
                }
                long startTime = System.currentTimeMillis();
                AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NONE;
                AbstractDungeon.reset();
                FontHelper.cardTitleFont.getData().setScale(1.0f);
                AbstractRelic.relicPage = 0;
                SeedPanel.textField = "";
                ModHelper.setModsFalse();
                SeedHelper.cachedSeed = null;
                Settings.seed = null;
                Settings.seedSet = false;
                Settings.specialSeed = null;
                Settings.isTrial = false;
                Settings.isDailyRun = false;
                Settings.isEndless = false;
                Settings.isFinalActAvailable = false;
                Settings.hasRubyKey = false;
                Settings.hasEmeraldKey = false;
                Settings.hasSapphireKey = false;
                CustomModeScreen.finalActAvailable = false;
                trial = null;
                logger.info("Dungeon Reset: " + (System.currentTimeMillis() - startTime) + "ms");
                long startTime2 = System.currentTimeMillis();
                ShopScreen.resetPurgeCost();
                tips.initialize();
                metricData.clearData();
                logger.info("Shop Screen Rest, Tips Initialize, Metric Data Clear: " + (System.currentTimeMillis() - startTime2) + "ms");
                long startTime3 = System.currentTimeMillis();
                UnlockTracker.refresh();
                logger.info("Unlock Tracker Refresh:  " + (System.currentTimeMillis() - startTime3) + "ms");
                long startTime4 = System.currentTimeMillis();
                mainMenuScreen = new MainMenuScreen();
                mainMenuScreen.bg.slideDownInstantly();
                saveSlotPref.putFloat(SaveHelper.slotName("COMPLETION", saveSlot), UnlockTracker.getCompletionPercentage());
                saveSlotPref.putLong(SaveHelper.slotName(CharStat.PLAYTIME, saveSlot), UnlockTracker.getTotalPlaytime());
                saveSlotPref.flush();
                logger.info("New Main Menu Screen: " + (System.currentTimeMillis() - startTime4) + "ms");
                long startTime5 = System.currentTimeMillis();
                CardHelper.clear();
                mode = GameMode.CHAR_SELECT;
                nextDungeon = Exordium.ID;
                dungeonTransitionScreen = new DungeonTransitionScreen(Exordium.ID);
                TipTracker.refresh();
                logger.info("[GC] BEFORE: " + String.valueOf(SystemStats.getUsedMemory()));
                System.gc();
                logger.info("[GC] AFTER: " + String.valueOf(SystemStats.getUsedMemory()));
                logger.info("New Transition Screen, Tip Tracker Refresh: " + (System.currentTimeMillis() - startTime5) + "ms");
                System.currentTimeMillis();
                fadeIn(2.0f);
                if (queueCredits) {
                    queueCredits = false;
                    mainMenuScreen.creditsScreen.open(playCreditsBgm);
                    mainMenuScreen.hideMenuButtons();
                }
            }
        }
    }

    public static void fadeIn(float duration) {
        screenColor.a = 1.0f;
        screenTime = duration;
        screenTimer = duration;
        fadeIn = true;
    }

    public static void fadeToBlack(float duration) {
        screenColor.a = 0.0f;
        screenTime = duration;
        screenTimer = duration;
        fadeIn = false;
    }

    public static void startOver() {
        startOver = true;
        fadeToBlack(2.0f);
    }

    public static void startOverButShowCredits() {
        startOver = true;
        queueCredits = true;
        doorUnlockScreenCheck();
        fadeToBlack(2.0f);
    }

    private static void doorUnlockScreenCheck() {
        DoorUnlockScreen.show = false;
        if (!Settings.isStandardRun()) {
            logger.info("[INFO] Non-Standard Run, no check for door.");
            return;
        }
        switch (AbstractDungeon.player.chosenClass) {
            case IRONCLAD:
                if (playerPref.getBoolean(AbstractDungeon.player.chosenClass.name() + "_WIN", false)) {
                    logger.info("[INFO] Ironclad Already Won: No Door.");
                    break;
                } else {
                    logger.info("[INFO] Ironclad Victory: Show Door.");
                    playerPref.putBoolean(AbstractDungeon.player.chosenClass.name() + "_WIN", true);
                    DoorUnlockScreen.show = true;
                    break;
                }
            case THE_SILENT:
                if (playerPref.getBoolean(AbstractDungeon.player.chosenClass.name() + "_WIN", false)) {
                    logger.info("[INFO] Silent Already Won: No Door.");
                    break;
                } else {
                    logger.info("[INFO] Silent Victory: Show Door.");
                    playerPref.putBoolean(AbstractDungeon.player.chosenClass.name() + "_WIN", true);
                    DoorUnlockScreen.show = true;
                    break;
                }
            case DEFECT:
                if (playerPref.getBoolean(AbstractDungeon.player.chosenClass.name() + "_WIN", false)) {
                    logger.info("[INFO] Defect Already Won: No Door.");
                    break;
                } else {
                    logger.info("[INFO] Defect Victory: Show Door.");
                    playerPref.putBoolean(AbstractDungeon.player.chosenClass.name() + "_WIN", true);
                    DoorUnlockScreen.show = true;
                    break;
                }
        }
        if (DoorUnlockScreen.show) {
            playerPref.flush();
        }
    }

    public static void resetScoreVars() {
        monstersSlain = 0;
        elites1Slain = 0;
        elites2Slain = 0;
        elites3Slain = 0;
        if (dungeon != null) {
            AbstractDungeon.bossCount = 0;
        }
        champion = 0;
        perfect = 0;
        overkill = false;
        combo = false;
        goldGained = 0;
        cardsPurged = 0;
        potionsBought = 0;
        mysteryMachine = 0;
        playtime = 0.0f;
        stopClock = false;
    }

    public void update() {
        cursor.update();
        screenShake.update(viewport);
        if (mode != GameMode.SPLASH) {
            updateFade();
        }
        music.update();
        sound.update();
        if (steelSeries.isEnabled.booleanValue()) {
            steelSeries.update();
        }
        if (Settings.isDebug) {
            if (DevInputActionSet.toggleCursor.isJustPressed()) {
                this.displayCursor = !this.displayCursor;
            } else if (DevInputActionSet.toggleVersion.isJustPressed()) {
                displayVersion = !displayVersion;
            }
        }
        if (SteamInputHelper.numControllers == 1) {
            SteamInputHelper.updateFirst();
        } else if (SteamInputHelper.numControllers == 999 && CInputHelper.controllers == null) {
            CInputHelper.initializeIfAble();
        }
        InputHelper.updateFirst();
        if (cardPopup.isOpen) {
            cardPopup.update();
        }
        if (relicPopup.isOpen) {
            relicPopup.update();
        }
        if (isPopupOpen) {
            popupMX = InputHelper.mX;
            popupMY = InputHelper.mY;
            InputHelper.mX = -9999;
            InputHelper.mY = -9999;
        }
        switch (mode) {
            case SPLASH:
                splashScreen.update();
                if (splashScreen.isDone) {
                    mode = GameMode.CHAR_SELECT;
                    splashScreen = null;
                    mainMenuScreen = new MainMenuScreen();
                    break;
                }
                break;
            case CHAR_SELECT:
                mainMenuScreen.update();
                if (mainMenuScreen.fadedOut) {
                    AbstractDungeon.pathX = new ArrayList<>();
                    AbstractDungeon.pathY = new ArrayList<>();
                    if (trial == null && Settings.specialSeed != null) {
                        trial = TrialHelper.getTrialForSeed(SeedHelper.getString(Settings.specialSeed.longValue()));
                    }
                    if (loadingSave) {
                        ModHelper.setModsFalse();
                        AbstractDungeon.player = createCharacter(chosenCharacter);
                        loadPlayerSave(AbstractDungeon.player);
                    } else {
                        Settings.setFinalActAvailability();
                        logger.info("FINAL ACT AVAILABLE: " + Settings.isFinalActAvailable);
                        if (trial == null) {
                            if (Settings.isDailyRun) {
                                AbstractDungeon.ascensionLevel = 0;
                                AbstractDungeon.isAscensionMode = false;
                            }
                            AbstractDungeon.player = createCharacter(chosenCharacter);
                            Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
                            while (it.hasNext()) {
                                AbstractRelic r = it.next();
                                r.updateDescription(AbstractDungeon.player.chosenClass);
                                r.onEquip();
                            }
                            Iterator<AbstractCard> it2 = AbstractDungeon.player.masterDeck.group.iterator();
                            while (it2.hasNext()) {
                                AbstractCard c = it2.next();
                                if (c.rarity != AbstractCard.CardRarity.BASIC) {
                                    CardHelper.obtain(c.cardID, c.rarity, c.color);
                                }
                            }
                        } else {
                            Settings.isTrial = true;
                            Settings.isDailyRun = false;
                            setupTrialMods(trial, chosenCharacter);
                            setupTrialPlayer(trial);
                        }
                    }
                    mode = GameMode.GAMEPLAY;
                    nextDungeon = Exordium.ID;
                    dungeonTransitionScreen = new DungeonTransitionScreen(Exordium.ID);
                    if (!loadingSave) {
                        monstersSlain = 0;
                        elites1Slain = 0;
                        elites2Slain = 0;
                        elites3Slain = 0;
                        break;
                    } else {
                        dungeonTransitionScreen.isComplete = true;
                        break;
                    }
                }
                break;
            case GAMEPLAY:
                if (dungeonTransitionScreen != null) {
                    dungeonTransitionScreen.update();
                    if (dungeonTransitionScreen.isComplete) {
                        dungeonTransitionScreen = null;
                        if (loadingSave) {
                            getDungeon(saveFile.level_name, AbstractDungeon.player, saveFile);
                            loadPostCombat(saveFile);
                            if (!saveFile.post_combat) {
                                loadingSave = false;
                            }
                        } else {
                            getDungeon(nextDungeon, AbstractDungeon.player);
                            if (!nextDungeon.equals(Exordium.ID) || Settings.isShowBuild || !TipTracker.tips.get(TipTracker.NEOW_SKIP).booleanValue()) {
                                AbstractDungeon.dungeonMapScreen.open(true);
                                TipTracker.neverShowAgain(TipTracker.NEOW_SKIP);
                            }
                        }
                    }
                } else if (dungeon != null) {
                    dungeon.update();
                } else {
                    logger.info("Eh-?");
                }
                if (dungeon != null && AbstractDungeon.isDungeonBeaten && AbstractDungeon.fadeColor.a == 1.0f) {
                    dungeon = null;
                    AbstractDungeon.scene.fadeOutAmbiance();
                    dungeonTransitionScreen = new DungeonTransitionScreen(nextDungeon);
                    break;
                }
                break;
            case DUNGEON_TRANSITION:
                break;
            default:
                logger.info("Unknown Game Mode: " + mode.name());
                break;
        }
        updateDebugSwitch();
        InputHelper.updateLast();
        if (CInputHelper.controller != null) {
            CInputHelper.updateLast();
        }
        if (Settings.isInfo) {
            this.fpsLogger.log();
        }
    }

    private void setupTrialMods(AbstractTrial trial2, AbstractPlayer.PlayerClass chosenClass) {
        if (trial2.useRandomDailyMods()) {
            long sourceTime = System.nanoTime();
            Random rng = new Random(Long.valueOf(sourceTime));
            Settings.seed = Long.valueOf(SeedHelper.generateUnoffensiveSeed(rng));
            ModHelper.setTodaysMods(Settings.seed.longValue(), chosenClass);
        } else if (trial2.dailyModIDs() != null) {
            ModHelper.setMods(trial2.dailyModIDs());
            ModHelper.clearNulls();
        }
    }

    private void setupTrialPlayer(AbstractTrial trial2) {
        AbstractDungeon.player = trial2.setupPlayer(createCharacter(chosenCharacter));
        if (!trial2.keepStarterRelic()) {
            AbstractDungeon.player.relics.clear();
        }
        for (String relicID : trial2.extraStartingRelicIDs()) {
            AbstractRelic relic = RelicLibrary.getRelic(relicID);
            relic.instantObtain(AbstractDungeon.player, AbstractDungeon.player.relics.size(), false);
            AbstractDungeon.relicsToRemoveOnStart.add(relic.relicId);
        }
        Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            r.updateDescription(AbstractDungeon.player.chosenClass);
            r.onEquip();
        }
        if (!trial2.keepsStarterCards()) {
            AbstractDungeon.player.masterDeck.clear();
        }
        for (String cardID : trial2.extraStartingCardIDs()) {
            AbstractDungeon.player.masterDeck.addToTop(CardLibrary.getCard(cardID).makeCopy());
        }
    }

    private void loadPostCombat(SaveFile saveFile2) {
        if (saveFile2.post_combat) {
            AbstractDungeon.getCurrRoom().isBattleOver = true;
            AbstractDungeon.overlayMenu.hideCombatPanels();
            AbstractDungeon.loading_post_combat = true;
            AbstractDungeon.getCurrRoom().smoked = saveFile2.smoked;
            AbstractDungeon.getCurrRoom().mugged = saveFile2.mugged;
            if (AbstractDungeon.getCurrRoom().event != null) {
                AbstractDungeon.getCurrRoom().event.postCombatLoad();
            }
            if (AbstractDungeon.getCurrRoom().monsters != null) {
                AbstractDungeon.getCurrRoom().monsters.monsters.clear();
                AbstractDungeon.actionManager.actions.clear();
            }
            if (!saveFile2.smoked) {
                Iterator<RewardSave> it = saveFile2.combat_rewards.iterator();
                while (it.hasNext()) {
                    RewardSave i = it.next();
                    String str = i.type;
                    char c = 65535;
                    switch (str.hashCode()) {
                        case -1929101933:
                            if (str.equals("POTION")) {
                                c = 3;
                                break;
                            }
                            break;
                        case -866293372:
                            if (str.equals("EMERALD_KEY")) {
                                c = 6;
                                break;
                            }
                            break;
                        case -759508872:
                            if (str.equals("STOLEN_GOLD")) {
                                c = 4;
                                break;
                            }
                            break;
                        case -706635454:
                            if (str.equals("SAPPHIRE_KEY")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 2061072:
                            if (str.equals("CARD")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 2193504:
                            if (str.equals("GOLD")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 77859667:
                            if (str.equals("RELIC")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            break;
                        case 1:
                            AbstractDungeon.getCurrRoom().addGoldToRewards(i.amount);
                            break;
                        case 2:
                            AbstractDungeon.getCurrRoom().addRelicToRewards(RelicLibrary.getRelic(i.id).makeCopy());
                            break;
                        case 3:
                            AbstractDungeon.getCurrRoom().addPotionToRewards(PotionHelper.getPotion(i.id));
                            break;
                        case 4:
                            AbstractDungeon.getCurrRoom().addStolenGoldToRewards(i.amount);
                            break;
                        case 5:
                            AbstractDungeon.getCurrRoom().addSapphireKey(AbstractDungeon.getCurrRoom().rewards.get(AbstractDungeon.getCurrRoom().rewards.size() - 1));
                            break;
                        case 6:
                            AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(AbstractDungeon.getCurrRoom().rewards.get(AbstractDungeon.getCurrRoom().rewards.size() - 1), RewardItem.RewardType.EMERALD_KEY));
                            break;
                        default:
                            logger.info("Loading unknown type: " + i.type);
                            break;
                    }
                }
            }
            if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                AbstractDungeon.scene.fadeInAmbiance();
                music.silenceTempBgmInstantly();
                music.silenceBGMInstantly();
                AbstractMonster.playBossStinger();
            } else if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
                AbstractDungeon.scene.fadeInAmbiance();
                music.fadeOutTempBGM();
            }
            saveFile2.post_combat = false;
        }
    }

    private void loadPlayerSave(AbstractPlayer p) {
        saveFile = SaveAndContinue.loadSaveFile(p.chosenClass);
        AbstractDungeon.loading_post_combat = false;
        Settings.seed = Long.valueOf(saveFile.seed);
        Settings.isFinalActAvailable = saveFile.is_final_act_on;
        Settings.hasRubyKey = saveFile.has_ruby_key;
        Settings.hasEmeraldKey = saveFile.has_emerald_key;
        Settings.hasSapphireKey = saveFile.has_sapphire_key;
        Settings.isDailyRun = saveFile.is_daily;
        if (Settings.isDailyRun) {
            Settings.dailyDate = saveFile.daily_date;
        }
        Settings.specialSeed = Long.valueOf(saveFile.special_seed);
        Settings.seedSet = saveFile.seed_set;
        Settings.isTrial = saveFile.is_trial;
        if (Settings.isTrial) {
            ModHelper.setTodaysMods(Settings.seed.longValue(), AbstractDungeon.player.chosenClass);
            AbstractPlayer.customMods = saveFile.custom_mods;
        } else if (Settings.isDailyRun) {
            ModHelper.setTodaysMods(Settings.specialSeed.longValue(), AbstractDungeon.player.chosenClass);
        }
        AbstractPlayer.customMods = saveFile.custom_mods;
        if (AbstractPlayer.customMods == null) {
            AbstractPlayer.customMods = new ArrayList<>();
        }
        p.currentHealth = saveFile.current_health;
        p.maxHealth = saveFile.max_health;
        p.gold = saveFile.gold;
        p.displayGold = p.gold;
        p.masterHandSize = saveFile.hand_size;
        p.potionSlots = saveFile.potion_slots;
        if (p.potionSlots == 0) {
            p.potionSlots = 3;
        }
        p.potions.clear();
        for (int i = 0; i < p.potionSlots; i++) {
            p.potions.add(new PotionSlot(i));
        }
        p.masterMaxOrbs = saveFile.max_orbs;
        p.energy = new EnergyManager(saveFile.red + saveFile.green + saveFile.blue);
        monstersSlain = saveFile.monsters_killed;
        elites1Slain = saveFile.elites1_killed;
        elites2Slain = saveFile.elites2_killed;
        elites3Slain = saveFile.elites3_killed;
        goldGained = saveFile.gold_gained;
        champion = saveFile.champions;
        perfect = saveFile.perfect;
        combo = saveFile.combo;
        overkill = saveFile.overkill;
        mysteryMachine = saveFile.mystery_machine;
        playtime = (float) saveFile.play_time;
        AbstractDungeon.ascensionLevel = saveFile.ascension_level;
        AbstractDungeon.isAscensionMode = saveFile.is_ascension_mode;
        p.masterDeck.clear();
        Iterator<CardSave> it = saveFile.cards.iterator();
        while (it.hasNext()) {
            CardSave s = it.next();
            logger.info(s.id + ", " + s.upgrades);
            p.masterDeck.addToTop(CardLibrary.getCopy(s.id, s.upgrades, s.misc));
        }
        Settings.isEndless = saveFile.is_endless_mode;
        int index = 0;
        p.blights.clear();
        if (saveFile.blights != null) {
            Iterator<String> it2 = saveFile.blights.iterator();
            while (it2.hasNext()) {
                String b = it2.next();
                AbstractBlight blight = BlightHelper.getBlight(b);
                if (blight != null) {
                    int incrementAmount = saveFile.endless_increments.get(index).intValue();
                    for (int i2 = 0; i2 < incrementAmount; i2++) {
                        blight.incrementUp();
                    }
                    blight.setIncrement(incrementAmount);
                    blight.instantObtain(AbstractDungeon.player, index, false);
                }
                index++;
            }
            if (saveFile.blight_counters != null) {
                int index2 = 0;
                Iterator<Integer> it3 = saveFile.blight_counters.iterator();
                while (it3.hasNext()) {
                    Integer i3 = it3.next();
                    p.blights.get(index2).setCounter(i3.intValue());
                    p.blights.get(index2).updateDescription(p.chosenClass);
                    index2++;
                }
            }
        }
        p.relics.clear();
        int index3 = 0;
        Iterator<String> it4 = saveFile.relics.iterator();
        while (it4.hasNext()) {
            String s2 = it4.next();
            AbstractRelic r = RelicLibrary.getRelic(s2).makeCopy();
            r.instantObtain(p, index3, false);
            if (index3 < saveFile.relic_counters.size()) {
                r.setCounter(saveFile.relic_counters.get(index3).intValue());
            }
            r.updateDescription(p.chosenClass);
            index3++;
        }
        int index4 = 0;
        Iterator<String> it5 = saveFile.potions.iterator();
        while (it5.hasNext()) {
            String s3 = it5.next();
            AbstractPotion potion = PotionHelper.getPotion(s3);
            if (potion != null) {
                AbstractDungeon.player.obtainPotion(index4, potion);
            }
            index4++;
        }
        AbstractCard tmpCard = null;
        if (saveFile.bottled_flame != null) {
            Iterator<AbstractCard> it6 = AbstractDungeon.player.masterDeck.group.iterator();
            while (it6.hasNext()) {
                AbstractCard i4 = it6.next();
                if (i4.cardID.equals(saveFile.bottled_flame)) {
                    tmpCard = i4;
                    if (i4.timesUpgraded == saveFile.bottled_flame_upgrade && i4.misc == saveFile.bottled_flame_misc) {
                        break;
                    }
                }
            }
            if (tmpCard != null) {
                tmpCard.inBottleFlame = true;
                ((BottledFlame) AbstractDungeon.player.getRelic(BottledFlame.ID)).card = tmpCard;
                ((BottledFlame) AbstractDungeon.player.getRelic(BottledFlame.ID)).setDescriptionAfterLoading();
            }
        }
        AbstractCard tmpCard2 = null;
        if (saveFile.bottled_lightning != null) {
            Iterator<AbstractCard> it7 = AbstractDungeon.player.masterDeck.group.iterator();
            while (it7.hasNext()) {
                AbstractCard i5 = it7.next();
                if (i5.cardID.equals(saveFile.bottled_lightning)) {
                    tmpCard2 = i5;
                    if (i5.timesUpgraded == saveFile.bottled_lightning_upgrade && i5.misc == saveFile.bottled_lightning_misc) {
                        break;
                    }
                }
            }
            if (tmpCard2 != null) {
                tmpCard2.inBottleLightning = true;
                ((BottledLightning) AbstractDungeon.player.getRelic(BottledLightning.ID)).card = tmpCard2;
                ((BottledLightning) AbstractDungeon.player.getRelic(BottledLightning.ID)).setDescriptionAfterLoading();
            }
        }
        AbstractCard tmpCard3 = null;
        if (saveFile.bottled_tornado != null) {
            Iterator<AbstractCard> it8 = AbstractDungeon.player.masterDeck.group.iterator();
            while (it8.hasNext()) {
                AbstractCard i6 = it8.next();
                if (i6.cardID.equals(saveFile.bottled_tornado)) {
                    tmpCard3 = i6;
                    if (i6.timesUpgraded == saveFile.bottled_tornado_upgrade && i6.misc == saveFile.bottled_tornado_misc) {
                        break;
                    }
                }
            }
            if (tmpCard3 != null) {
                tmpCard3.inBottleTornado = true;
                ((BottledTornado) AbstractDungeon.player.getRelic(BottledTornado.ID)).card = tmpCard3;
                ((BottledTornado) AbstractDungeon.player.getRelic(BottledTornado.ID)).setDescriptionAfterLoading();
            }
        }
        if (saveFile.daily_mods != null && saveFile.daily_mods.size() > 0) {
            ModHelper.setMods(saveFile.daily_mods);
        }
        metricData.clearData();
        metricData.campfire_rested = saveFile.metric_campfire_rested;
        metricData.campfire_upgraded = saveFile.metric_campfire_upgraded;
        metricData.purchased_purges = saveFile.metric_purchased_purges;
        metricData.potions_floor_spawned = saveFile.metric_potions_floor_spawned;
        metricData.current_hp_per_floor = saveFile.metric_current_hp_per_floor;
        metricData.max_hp_per_floor = saveFile.metric_max_hp_per_floor;
        metricData.gold_per_floor = saveFile.metric_gold_per_floor;
        metricData.path_per_floor = saveFile.metric_path_per_floor;
        metricData.path_taken = saveFile.metric_path_taken;
        metricData.items_purchased = saveFile.metric_items_purchased;
        metricData.items_purged = saveFile.metric_items_purged;
        metricData.card_choices = saveFile.metric_card_choices;
        metricData.event_choices = saveFile.metric_event_choices;
        metricData.damage_taken = saveFile.metric_damage_taken;
        metricData.boss_relics = saveFile.metric_boss_relics;
        if (saveFile.metric_potions_obtained != null) {
            metricData.potions_obtained = saveFile.metric_potions_obtained;
        }
        if (saveFile.metric_relics_obtained != null) {
            metricData.relics_obtained = saveFile.metric_relics_obtained;
        }
        if (saveFile.metric_campfire_choices != null) {
            metricData.campfire_choices = saveFile.metric_campfire_choices;
        }
        if (saveFile.metric_item_purchase_floors != null) {
            metricData.item_purchase_floors = saveFile.metric_item_purchase_floors;
        }
        if (saveFile.metric_items_purged_floors != null) {
            metricData.items_purged_floors = saveFile.metric_items_purged_floors;
        }
        if (saveFile.neow_bonus != null) {
            metricData.neowBonus = saveFile.neow_bonus;
        }
        if (saveFile.neow_cost != null) {
            metricData.neowCost = saveFile.neow_cost;
        }
    }

    private static AbstractPlayer createCharacter(AbstractPlayer.PlayerClass selection) {
        AbstractPlayer p = characterManager.recreateCharacter(selection);
        Iterator<AbstractCard> it = p.masterDeck.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            UnlockTracker.markCardAsSeen(c.cardID);
        }
        return p;
    }

    private void updateDebugSwitch() {
        if (Settings.isDev) {
            if (DevInputActionSet.toggleDebug.isJustPressed()) {
                Settings.isDebug = !Settings.isDebug;
            } else if (DevInputActionSet.toggleInfo.isJustPressed()) {
                Settings.isInfo = !Settings.isInfo;
            } else if (Settings.isDebug && DevInputActionSet.uploadData.isJustPressed()) {
                RelicLibrary.uploadRelicData();
                CardLibrary.uploadCardData();
                MonsterHelper.uploadEnemyData();
                PotionHelper.uploadPotionData();
                ModHelper.uploadModData();
                BlightHelper.uploadBlightData();
                BotDataUploader.uploadKeywordData();
            } else if (Settings.isDebug) {
                if (DevInputActionSet.hideTopBar.isJustPressed()) {
                    Settings.hideTopBar = !Settings.hideTopBar;
                } else if (DevInputActionSet.hidePopUps.isJustPressed()) {
                    Settings.hidePopupDetails = !Settings.hidePopupDetails;
                } else if (DevInputActionSet.hideRelics.isJustPressed()) {
                    Settings.hideRelics = !Settings.hideRelics;
                } else if (DevInputActionSet.hideCombatLowUI.isJustPressed()) {
                    Settings.hideLowerElements = !Settings.hideLowerElements;
                } else if (DevInputActionSet.hideCards.isJustPressed()) {
                    Settings.hideCards = !Settings.hideCards;
                } else if (DevInputActionSet.hideEndTurnButton.isJustPressed()) {
                    Settings.hideEndTurn = !Settings.hideEndTurn;
                    if (AbstractDungeon.getMonsters() != null) {
                        Iterator<AbstractMonster> it = AbstractDungeon.getMonsters().monsters.iterator();
                        while (it.hasNext()) {
                            AbstractMonster m = it.next();
                            m.damage(new DamageInfo(AbstractDungeon.player, m.currentHealth, DamageInfo.DamageType.HP_LOSS));
                        }
                    }
                } else if (DevInputActionSet.hideCombatInfo.isJustPressed()) {
                    Settings.hideCombatElements = !Settings.hideCombatElements;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resize(int width, int height) {
    }

    public AbstractDungeon getDungeon(String key, AbstractPlayer p) {
        char c = 65535;
        switch (key.hashCode()) {
            case -1887678253:
                if (key.equals(Exordium.ID)) {
                    c = 0;
                    break;
                }
                break;
            case 313705820:
                if (key.equals(TheCity.ID)) {
                    c = 1;
                    break;
                }
                break;
            case 791401920:
                if (key.equals(TheBeyond.ID)) {
                    c = 2;
                    break;
                }
                break;
            case 884969688:
                if (key.equals(TheEnding.ID)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ArrayList<String> emptyList = new ArrayList<>();
                return new Exordium(p, emptyList);
            case 1:
                return new TheCity(p, AbstractDungeon.specialOneTimeEventList);
            case 2:
                return new TheBeyond(p, AbstractDungeon.specialOneTimeEventList);
            case 3:
                return new TheEnding(p, AbstractDungeon.specialOneTimeEventList);
            default:
                return null;
        }
    }

    public AbstractDungeon getDungeon(String key, AbstractPlayer p, SaveFile saveFile2) {
        char c = 65535;
        switch (key.hashCode()) {
            case -1887678253:
                if (key.equals(Exordium.ID)) {
                    c = 0;
                    break;
                }
                break;
            case 313705820:
                if (key.equals(TheCity.ID)) {
                    c = 1;
                    break;
                }
                break;
            case 791401920:
                if (key.equals(TheBeyond.ID)) {
                    c = 2;
                    break;
                }
                break;
            case 884969688:
                if (key.equals(TheEnding.ID)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new Exordium(p, saveFile2);
            case 1:
                return new TheCity(p, saveFile2);
            case 2:
                return new TheBeyond(p, saveFile2);
            case 3:
                return new TheEnding(p, saveFile2);
            default:
                return null;
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void pause() {
        logger.info("PAUSE()");
        Settings.isControllerMode = false;
        if (MUTE_IF_BG && mainMenuScreen != null) {
            Settings.isBackgrounded = true;
            if (mode == GameMode.CHAR_SELECT) {
                mainMenuScreen.muteAmbienceVolume();
            } else if (AbstractDungeon.scene != null) {
                AbstractDungeon.scene.muteAmbienceVolume();
            }
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resume() {
        logger.info("RESUME()");
        if (MUTE_IF_BG && mainMenuScreen != null) {
            Settings.isBackgrounded = false;
            if (mode == GameMode.CHAR_SELECT) {
                mainMenuScreen.updateAmbienceVolume();
            } else if (AbstractDungeon.scene != null) {
                AbstractDungeon.scene.updateAmbienceVolume();
            }
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        logger.info("Game shutting down...");
        logger.info("Flushing saves to disk...");
        AsyncSaver.shutdownSaveThread();
        if (SteamInputHelper.alive) {
            logger.info("Shutting down controller handler...");
            SteamInputHelper.alive = false;
            SteamInputHelper.controller.shutdown();
            if (clientUtils != null) {
                clientUtils.dispose();
            }
        }
        if (sInputDetectThread != null) {
            logger.info("Steam input detection was running! Shutting down...");
            sInputDetectThread.interrupt();
        }
        logger.info("Shutting down publisher integrations...");
        publisherIntegration.dispose();
        logger.info("Flushing logs to disk. Clean shutdown successful.");
        LogManager.shutdown();
    }

    public static String generateRandomAlias() {
        StringBuilder retVal = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            retVal.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(MathUtils.random(0, "abcdefghijklmnopqrstuvwxyz0123456789".length() - 1)));
        }
        return retVal.toString();
    }

    public static boolean isInARun() {
        return mode == GameMode.GAMEPLAY && AbstractDungeon.player != null && !AbstractDungeon.player.isDead;
    }

    public static Texture getSaveSlotImg() {
        switch (saveSlot) {
            case 0:
                return ImageMaster.PROFILE_A;
            case 1:
                return ImageMaster.PROFILE_B;
            case 2:
                return ImageMaster.PROFILE_C;
            default:
                return ImageMaster.PROFILE_A;
        }
    }
}