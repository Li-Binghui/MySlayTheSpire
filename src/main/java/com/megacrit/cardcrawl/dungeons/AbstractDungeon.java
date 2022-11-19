package com.megacrit.cardcrawl.dungeons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.blights.MimicInfestation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.cards.colorless.SwiftStrike;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.cards.curses.Pride;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.ExceptionHandler;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.credits.CreditsScreen;
import com.megacrit.cardcrawl.daily.mods.BigGameHunter;
import com.megacrit.cardcrawl.daily.mods.Binary;
import com.megacrit.cardcrawl.daily.mods.CertainFuture;
import com.megacrit.cardcrawl.daily.mods.ColorlessCards;
import com.megacrit.cardcrawl.daily.mods.DeadlyEvents;
import com.megacrit.cardcrawl.daily.mods.Diverse;
import com.megacrit.cardcrawl.daily.mods.Draft;
import com.megacrit.cardcrawl.daily.mods.Hoarder;
import com.megacrit.cardcrawl.daily.mods.Insanity;
import com.megacrit.cardcrawl.daily.mods.SealedDeck;
import com.megacrit.cardcrawl.daily.mods.Shiny;
import com.megacrit.cardcrawl.daily.mods.Terminal;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.beyond.MoaiHead;
import com.megacrit.cardcrawl.events.beyond.SecretPortal;
import com.megacrit.cardcrawl.events.city.Beggar;
import com.megacrit.cardcrawl.events.city.Colosseum;
import com.megacrit.cardcrawl.events.city.KnowingSkull;
import com.megacrit.cardcrawl.events.city.TheJoust;
import com.megacrit.cardcrawl.events.exordium.Cleric;
import com.megacrit.cardcrawl.events.exordium.DeadAdventurer;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;
import com.megacrit.cardcrawl.events.shrines.AccursedBlacksmith;
import com.megacrit.cardcrawl.events.shrines.Bonfire;
import com.megacrit.cardcrawl.events.shrines.Designer;
import com.megacrit.cardcrawl.events.shrines.Duplicator;
import com.megacrit.cardcrawl.events.shrines.FaceTrader;
import com.megacrit.cardcrawl.events.shrines.FountainOfCurseRemoval;
import com.megacrit.cardcrawl.events.shrines.Lab;
import com.megacrit.cardcrawl.events.shrines.Nloth;
import com.megacrit.cardcrawl.events.shrines.NoteForYourself;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.events.shrines.WomanInBlue;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.DungeonMap;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapGenerator;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.map.RoomTypeAssigner;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.neow.NeowRoom;
import com.megacrit.cardcrawl.neow.NeowUnlockScreen;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FruitJuice;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.Girya;
import com.megacrit.cardcrawl.relics.JuzuBracelet;
import com.megacrit.cardcrawl.relics.PandorasBox;
import com.megacrit.cardcrawl.relics.PeacePipe;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.relics.RedCirclet;
import com.megacrit.cardcrawl.relics.Shovel;
import com.megacrit.cardcrawl.relics.SmilingMask;
import com.megacrit.cardcrawl.relics.Whetstone;
import com.megacrit.cardcrawl.relics.WingBoots;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.rewards.chests.LargeChest;
import com.megacrit.cardcrawl.rewards.chests.MediumChest;
import com.megacrit.cardcrawl.rewards.chests.SmallChest;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EmptyRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
import com.megacrit.cardcrawl.rooms.VictoryRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.DiscardPileViewScreen;
import com.megacrit.cardcrawl.screens.DrawPileViewScreen;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import com.megacrit.cardcrawl.screens.ExhaustPileViewScreen;
import com.megacrit.cardcrawl.screens.MasterDeckViewScreen;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.screens.options.InputSettingsScreen;
import com.megacrit.cardcrawl.screens.options.SettingsScreen;
import com.megacrit.cardcrawl.screens.select.BossRelicSelectScreen;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.ui.buttons.DynamicBanner;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockCharacterScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/dungeons/AbstractDungeon.class */
public abstract class AbstractDungeon {
    public static String name;
    public static String levelNum;
    public static String id;
    public static AbstractPlayer player;
    protected static float cardUpgradedChance;
    public static AbstractCard transformedCard;
    public static Texture eventBackgroundImg;
    public static Random monsterRng;
    public static Random mapRng;
    public static Random eventRng;
    public static Random merchantRng;
    public static Random cardRng;
    public static Random treasureRng;
    public static Random relicRng;
    public static Random potionRng;
    public static Random monsterHpRng;
    public static Random aiRng;
    public static Random shuffleRng;
    public static Random cardRandomRng;
    public static Random miscRng;
    public static String bossKey;
    public static float colorlessRareChance;
    protected static float shopRoomChance;
    protected static float restRoomChance;
    protected static float eventRoomChance;
    protected static float eliteRoomChance;
    protected static float treasureRoomChance;
    protected static int smallChestChance;
    protected static int mediumChestChance;
    protected static int largeChestChance;
    protected static int commonRelicChance;
    protected static int uncommonRelicChance;
    protected static int rareRelicChance;
    public static AbstractScene scene;
    public static MapRoomNode currMapNode;
    public static ArrayList<ArrayList<MapRoomNode>> map;
    public static boolean leftRoomAvailable;
    public static boolean centerRoomAvailable;
    public static boolean rightRoomAvailable;
    public static final int MAP_HEIGHT = 15;
    public static final int MAP_WIDTH = 7;
    public static final int MAP_DENSITY = 6;
    public static final int FINAL_ACT_MAP_HEIGHT = 3;
    public static DeathScreen deathScreen;
    public static VictoryScreen victoryScreen;
    public static OverlayMenu overlayMenu;
    public static CurrentScreen screen;
    public static CurrentScreen previousScreen;
    public static DynamicBanner dynamicBanner;
    public static boolean isDungeonBeaten;
    public static boolean isFadingIn;
    public static boolean isFadingOut;
    public static boolean waitingOnFadeOut;
    protected static float fadeTimer;
    public static Color fadeColor;
    public static Color sourceFadeColor;
    public static MapRoomNode nextRoom;
    public static final float SCENE_OFFSET_TIME = 1.3f;
    public static boolean ascensionCheck;
    protected static final Logger logger = LogManager.getLogger(AbstractDungeon.class.getName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractDungeon");
    public static final String[] TEXT = uiStrings.TEXT;
    public static int floorNum = 0;
    public static int actNum = 0;
    public static ArrayList<AbstractUnlock> unlocks = new ArrayList<>();
    protected static float shrineChance = 0.25f;
    public static boolean loading_post_combat = false;
    public static boolean is_victory = false;
    public static CardGroup srcColorlessCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup srcCurseCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup srcCommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup srcUncommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup srcRareCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup colorlessCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup curseCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup commonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup uncommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup rareCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static ArrayList<String> commonRelicPool = new ArrayList<>();
    public static ArrayList<String> uncommonRelicPool = new ArrayList<>();
    public static ArrayList<String> rareRelicPool = new ArrayList<>();
    public static ArrayList<String> shopRelicPool = new ArrayList<>();
    public static ArrayList<String> bossRelicPool = new ArrayList<>();
    public static String lastCombatMetricKey = null;
    public static ArrayList<String> monsterList = new ArrayList<>();
    public static ArrayList<String> eliteMonsterList = new ArrayList<>();
    public static ArrayList<String> bossList = new ArrayList<>();
    public static ArrayList<String> eventList = new ArrayList<>();
    public static ArrayList<String> shrineList = new ArrayList<>();
    public static ArrayList<String> specialOneTimeEventList = new ArrayList<>();
    public static GameActionManager actionManager = new GameActionManager();
    public static ArrayList<AbstractGameEffect> topLevelEffects = new ArrayList<>();
    public static ArrayList<AbstractGameEffect> topLevelEffectsQueue = new ArrayList<>();
    public static ArrayList<AbstractGameEffect> effectList = new ArrayList<>();
    public static ArrayList<AbstractGameEffect> effectsQueue = new ArrayList<>();
    public static boolean turnPhaseEffectActive = false;
    public static boolean firstRoomChosen = false;
    public static RenderScene rs = RenderScene.NORMAL;
    public static ArrayList<Integer> pathX = new ArrayList<>();
    public static ArrayList<Integer> pathY = new ArrayList<>();
    public static Color topGradientColor = new Color(1.0f, 1.0f, 1.0f, 0.25f);
    public static Color botGradientColor = new Color(1.0f, 1.0f, 1.0f, 0.25f);
    public static float floorY = 340.0f * Settings.yScale;
    public static TopPanel topPanel = new TopPanel();
    public static CardRewardScreen cardRewardScreen = new CardRewardScreen();
    public static CombatRewardScreen combatRewardScreen = new CombatRewardScreen();
    public static BossRelicSelectScreen bossRelicScreen = new BossRelicSelectScreen();
    public static MasterDeckViewScreen deckViewScreen = new MasterDeckViewScreen();
    public static DiscardPileViewScreen discardPileViewScreen = new DiscardPileViewScreen();
    public static DrawPileViewScreen gameDeckViewScreen = new DrawPileViewScreen();
    public static ExhaustPileViewScreen exhaustPileViewScreen = new ExhaustPileViewScreen();
    public static SettingsScreen settingsScreen = new SettingsScreen();
    public static InputSettingsScreen inputSettingsScreen = new InputSettingsScreen();
    public static DungeonMapScreen dungeonMapScreen = new DungeonMapScreen();
    public static GridCardSelectScreen gridSelectScreen = new GridCardSelectScreen();
    public static HandCardSelectScreen handCardSelectScreen = new HandCardSelectScreen();
    public static ShopScreen shopScreen = new ShopScreen();
    public static CreditsScreen creditsScreen = null;
    public static FtueTip ftue = null;
    public static UnlockCharacterScreen unlockScreen = new UnlockCharacterScreen();
    public static NeowUnlockScreen gUnlockScreen = new NeowUnlockScreen();
    public static boolean isScreenUp = false;
    public static boolean screenSwap = false;
    public static int cardBlizzStartOffset = 5;
    public static int cardBlizzRandomizer = cardBlizzStartOffset;
    public static int cardBlizzGrowth = 1;
    public static int cardBlizzMaxOffset = -40;
    public static float sceneOffsetY = 0.0f;
    public static ArrayList<String> relicsToRemoveOnStart = new ArrayList<>();
    public static int bossCount = 0;
    public static boolean isAscensionMode = false;
    public static int ascensionLevel = 0;
    public static ArrayList<AbstractBlight> blightPool = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger(AbstractDungeon.class.getName());

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen.class */
    public enum CurrentScreen {
        NONE,
        MASTER_DECK_VIEW,
        SETTINGS,
        INPUT_SETTINGS,
        GRID,
        MAP,
        FTUE,
        CHOOSE_ONE,
        HAND_SELECT,
        SHOP,
        COMBAT_REWARD,
        DISCARD_VIEW,
        EXHAUST_VIEW,
        GAME_DECK_VIEW,
        BOSS_REWARD,
        DEATH,
        CARD_REWARD,
        TRANSFORM,
        VICTORY,
        UNLOCK,
        DOOR_UNLOCK,
        CREDITS,
        NO_INTERACT,
        NEOW_UNLOCK
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/dungeons/AbstractDungeon$RenderScene.class */
    public enum RenderScene {
        NORMAL,
        EVENT,
        CAMPFIRE
    }

    protected abstract void initializeLevelSpecificChances();

    protected abstract ArrayList<String> generateExclusions();

    protected abstract void generateMonsters();

    protected abstract void generateWeakEnemies(int i);

    protected abstract void generateStrongEnemies(int i);

    protected abstract void generateElites(int i);

    protected abstract void initializeBoss();

    protected abstract void initializeEventList();

    protected abstract void initializeEventImg();

    protected abstract void initializeShrineList();

    public AbstractDungeon(String name2, String levelId, AbstractPlayer p, ArrayList<String> newSpecialOneTimeEventList) {
        ascensionCheck = UnlockTracker.isAscensionUnlocked(p);
        CardCrawlGame.dungeon = this;
        long startTime = System.currentTimeMillis();
        name = name2;
        id = levelId;
        player = p;
        topPanel.setPlayerName();
        actionManager = new GameActionManager();
        overlayMenu = new OverlayMenu(p);
        dynamicBanner = new DynamicBanner();
        unlocks.clear();
        specialOneTimeEventList = newSpecialOneTimeEventList;
        isFadingIn = false;
        isFadingOut = false;
        waitingOnFadeOut = false;
        fadeTimer = 1.0f;
        isDungeonBeaten = false;
        isScreenUp = false;
        dungeonTransitionSetup();
        generateMonsters();
        initializeBoss();
        setBoss(bossList.get(0));
        initializeEventList();
        initializeEventImg();
        initializeShrineList();
        initializeCardPools();
        if (floorNum == 0) {
            p.initializeStarterDeck();
        }
        initializePotions();
        BlightHelper.initialize();
        if (id.equals(Exordium.ID)) {
            screen = CurrentScreen.NONE;
            isScreenUp = false;
        } else {
            screen = CurrentScreen.MAP;
            isScreenUp = true;
        }
        logger.info("Content generation time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public AbstractDungeon(String name2, AbstractPlayer p, SaveFile saveFile) {
        ascensionCheck = UnlockTracker.isAscensionUnlocked(p);
        id = saveFile.level_name;
        CardCrawlGame.dungeon = this;
        long startTime = System.currentTimeMillis();
        name = name2;
        player = p;
        topPanel.setPlayerName();
        actionManager = new GameActionManager();
        overlayMenu = new OverlayMenu(p);
        dynamicBanner = new DynamicBanner();
        isFadingIn = false;
        isFadingOut = false;
        waitingOnFadeOut = false;
        fadeTimer = 1.0f;
        isDungeonBeaten = false;
        isScreenUp = false;
        firstRoomChosen = true;
        unlocks.clear();
        try {
            loadSave(saveFile);
        } catch (Exception e) {
            logger.info("Exception occurred while loading save!");
            logger.info("Deleting save due to crash!");
            SaveAndContinue.deleteSave(player);
            ExceptionHandler.handleException(e, LOGGER);
            Gdx.app.exit();
        }
        initializeEventImg();
        initializeShrineList();
        initializeCardPools();
        initializePotions();
        BlightHelper.initialize();
        screen = CurrentScreen.NONE;
        isScreenUp = false;
        logger.info("Dungeon load time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private void setBoss(String key) {
        bossKey = key;
        if (!(DungeonMap.boss == null || DungeonMap.bossOutline == null)) {
            DungeonMap.boss.dispose();
            DungeonMap.bossOutline.dispose();
        }
        if (key.equals(MonsterHelper.GUARDIAN_ENC)) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/guardian.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/guardian.png");
        } else if (key.equals("Hexaghost")) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/hexaghost.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/hexaghost.png");
        } else if (key.equals(MonsterHelper.SLIME_BOSS_ENC)) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/slime.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/slime.png");
        } else if (key.equals(MonsterHelper.COLLECTOR_ENC)) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/collector.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/collector.png");
        } else if (key.equals(MonsterHelper.AUTOMATON_ENC)) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/automaton.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/automaton.png");
        } else if (key.equals("Champ")) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/champ.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/champ.png");
        } else if (key.equals(MonsterHelper.AWAKENED_ENC)) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/awakened.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/awakened.png");
        } else if (key.equals(MonsterHelper.TIME_EATER_ENC)) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/timeeater.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/timeeater.png");
        } else if (key.equals("Donu and Deca")) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/donu.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/donu.png");
        } else if (key.equals(MonsterHelper.THE_HEART_ENC)) {
            DungeonMap.boss = ImageMaster.loadImage("images/ui/map/boss/heart.png");
            DungeonMap.bossOutline = ImageMaster.loadImage("images/ui/map/bossOutline/heart.png");
        } else {
            logger.info("WARNING: UNKNOWN BOSS ICON: " + key);
            DungeonMap.boss = null;
        }
        logger.info("[BOSS] " + key);
    }

    public static boolean isPlayerInDungeon() {
        return CardCrawlGame.dungeon != null;
    }

    public static void generateSeeds() {
        logger.info("Generating seeds: " + Settings.seed);
        monsterRng = new Random(Settings.seed);
        eventRng = new Random(Settings.seed);
        merchantRng = new Random(Settings.seed);
        cardRng = new Random(Settings.seed);
        treasureRng = new Random(Settings.seed);
        relicRng = new Random(Settings.seed);
        monsterHpRng = new Random(Settings.seed);
        potionRng = new Random(Settings.seed);
        aiRng = new Random(Settings.seed);
        shuffleRng = new Random(Settings.seed);
        cardRandomRng = new Random(Settings.seed);
        miscRng = new Random(Settings.seed);
    }

    public static void loadSeeds(SaveFile save) {
        if (save.is_daily || save.is_trial) {
            Settings.isDailyRun = save.is_daily;
            Settings.isTrial = save.is_trial;
            Settings.specialSeed = Long.valueOf(save.special_seed);
            if (save.is_daily) {
                ModHelper.setTodaysMods(save.special_seed, player.chosenClass);
            } else {
                ModHelper.setTodaysMods(save.seed, player.chosenClass);
            }
        }
        monsterRng = new Random(Settings.seed, save.monster_seed_count);
        eventRng = new Random(Settings.seed, save.event_seed_count);
        merchantRng = new Random(Settings.seed, save.merchant_seed_count);
        cardRng = new Random(Settings.seed, save.card_seed_count);
        cardBlizzRandomizer = save.card_random_seed_randomizer;
        treasureRng = new Random(Settings.seed, save.treasure_seed_count);
        relicRng = new Random(Settings.seed, save.relic_seed_count);
        potionRng = new Random(Settings.seed, save.potion_seed_count);
        logger.info("Loading seeds: " + Settings.seed);
        logger.info("Monster seed:  " + monsterRng.counter);
        logger.info("Event seed:    " + eventRng.counter);
        logger.info("Merchant seed: " + merchantRng.counter);
        logger.info("Card seed:     " + cardRng.counter);
        logger.info("Treasure seed: " + treasureRng.counter);
        logger.info("Relic seed:    " + relicRng.counter);
        logger.info("Potion seed:   " + potionRng.counter);
    }

    public void populatePathTaken(SaveFile saveFile) {
        MapEdge connectedEdge;
        MapRoomNode node = null;
        if (saveFile.current_room.equals(MonsterRoomBoss.class.getName())) {
            node = new MapRoomNode(-1, 15);
            node.room = new MonsterRoomBoss();
            nextRoom = node;
        } else if (saveFile.current_room.equals(TreasureRoomBoss.class.getName())) {
            node = new MapRoomNode(-1, 15);
            node.room = new TreasureRoomBoss();
            nextRoom = node;
        } else if (saveFile.room_y == 15 && saveFile.room_x == -1) {
            node = new MapRoomNode(-1, 15);
            node.room = new VictoryRoom(VictoryRoom.EventType.HEART);
            nextRoom = node;
        } else if (saveFile.current_room.equals(NeowRoom.class.getName())) {
            nextRoom = null;
        } else {
            nextRoom = map.get(saveFile.room_y).get(saveFile.room_x);
        }
        for (int i = 0; i < pathX.size(); i++) {
            if (pathY.get(i).intValue() == 14) {
                MapRoomNode node2 = map.get(pathY.get(i).intValue()).get(pathX.get(i).intValue());
                Iterator<MapEdge> it = node2.getEdges().iterator();
                while (it.hasNext()) {
                    MapEdge e = it.next();
                    if (e != null) {
                        e.markAsTaken();
                    }
                }
            }
            if (pathY.get(i).intValue() < 15) {
                map.get(pathY.get(i).intValue()).get(pathX.get(i).intValue()).taken = true;
                if (!(node == null || (connectedEdge = node.getEdgeConnectedTo(map.get(pathY.get(i).intValue()).get(pathX.get(i).intValue()))) == null)) {
                    connectedEdge.markAsTaken();
                }
                node = map.get(pathY.get(i).intValue()).get(pathX.get(i).intValue());
            }
        }
        if (isLoadingIntoNeow(saveFile)) {
            logger.info("Loading into Neow");
            currMapNode = new MapRoomNode(0, -1);
            currMapNode.room = new EmptyRoom();
            nextRoom = null;
        } else {
            logger.info("Loading into: " + saveFile.room_x + "," + saveFile.room_y);
            currMapNode = new MapRoomNode(0, -1);
            currMapNode.room = new EmptyRoom();
        }
        nextRoomTransition(saveFile);
        if (isLoadingIntoNeow(saveFile)) {
            if (saveFile.chose_neow_reward) {
                currMapNode.room = new NeowRoom(true);
            } else {
                currMapNode.room = new NeowRoom(false);
            }
        }
        if (!(currMapNode.room instanceof VictoryRoom)) {
            return;
        }
        if (!Settings.isFinalActAvailable || !Settings.hasRubyKey || !Settings.hasEmeraldKey || !Settings.hasSapphireKey) {
            CardCrawlGame.stopClock = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLoadingIntoNeow(SaveFile saveFile) {
        return floorNum == 0 || saveFile.current_room.equals(NeowRoom.class.getName());
    }

    public static AbstractChest getRandomChest() {
        int roll = treasureRng.random(0, 99);
        if (roll < smallChestChance) {
            return new SmallChest();
        }
        if (roll < mediumChestChance + smallChestChance) {
            return new MediumChest();
        }
        return new LargeChest();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void generateMap() {
        long startTime = System.currentTimeMillis();
        ArrayList<AbstractRoom> roomList = new ArrayList<>();
        map = MapGenerator.generateDungeon(15, 7, 6, mapRng);
        int count = 0;
        Iterator<ArrayList<MapRoomNode>> it = map.iterator();
        while (it.hasNext()) {
            ArrayList<MapRoomNode> a = it.next();
            Iterator<MapRoomNode> it2 = a.iterator();
            while (it2.hasNext()) {
                MapRoomNode n = it2.next();
                if (n.hasEdges() && n.y != map.size() - 2) {
                    count++;
                }
            }
        }
        generateRoomTypes(roomList, count);
        RoomTypeAssigner.assignRowAsRoomType(map.get(map.size() - 1), RestRoom.class);
        RoomTypeAssigner.assignRowAsRoomType(map.get(0), MonsterRoom.class);
        if (!Settings.isEndless || !player.hasBlight(MimicInfestation.ID)) {
            RoomTypeAssigner.assignRowAsRoomType(map.get(8), TreasureRoom.class);
        } else {
            RoomTypeAssigner.assignRowAsRoomType(map.get(8), MonsterRoomElite.class);
        }
        map = RoomTypeAssigner.distributeRoomsAcrossMap(mapRng, map, roomList);
        logger.info("Generated the following dungeon map:");
        logger.info(MapGenerator.toString(map, true));
        logger.info("Game Seed: " + Settings.seed);
        logger.info("Map generation time: " + (System.currentTimeMillis() - startTime) + "ms");
        firstRoomChosen = false;
        fadeIn();
        setEmeraldElite();
    }

    protected static void setEmeraldElite() {
        if (Settings.isFinalActAvailable && !Settings.hasEmeraldKey) {
            ArrayList<MapRoomNode> eliteNodes = new ArrayList<>();
            for (int i = 0; i < map.size(); i++) {
                for (int j = 0; j < map.get(i).size(); j++) {
                    if (map.get(i).get(j).room instanceof MonsterRoomElite) {
                        eliteNodes.add(map.get(i).get(j));
                    }
                }
            }
            MapRoomNode chosenNode = eliteNodes.get(mapRng.random(0, eliteNodes.size() - 1));
            chosenNode.hasEmeraldKey = true;
            logger.info("[INFO] Elite nodes identified: " + eliteNodes.size());
            logger.info("[INFO] Emerald Key  placed in: [" + chosenNode.x + "," + chosenNode.y + "]");
        }
    }

    private static void generateRoomTypes(ArrayList<AbstractRoom> roomList, int availableRoomCount) {
        int eliteCount;
        logger.info("Generating Room Types! There are " + availableRoomCount + " rooms:");
        int shopCount = Math.round(availableRoomCount * shopRoomChance);
        logger.info(" SHOP (" + toPercentage(shopRoomChance) + "): " + shopCount);
        int restCount = Math.round(availableRoomCount * restRoomChance);
        logger.info(" REST (" + toPercentage(restRoomChance) + "): " + restCount);
        int treasureCount = Math.round(availableRoomCount * treasureRoomChance);
        logger.info(" TRSRE (" + toPercentage(treasureRoomChance) + "): " + treasureCount);
        if (ModHelper.isModEnabled(BigGameHunter.ID)) {
            eliteCount = Math.round(availableRoomCount * eliteRoomChance * 2.5f);
            logger.info(" ELITE (" + toPercentage(eliteRoomChance) + "): " + eliteCount);
        } else if (ascensionLevel >= 1) {
            eliteCount = Math.round(availableRoomCount * eliteRoomChance * 1.6f);
            logger.info(" ELITE (" + toPercentage(eliteRoomChance) + "): " + eliteCount);
        } else {
            eliteCount = Math.round(availableRoomCount * eliteRoomChance);
            logger.info(" ELITE (" + toPercentage(eliteRoomChance) + "): " + eliteCount);
        }
        int eventCount = Math.round(availableRoomCount * eventRoomChance);
        logger.info(" EVNT (" + toPercentage(eventRoomChance) + "): " + eventCount);
        int monsterCount = ((((availableRoomCount - shopCount) - restCount) - treasureCount) - eliteCount) - eventCount;
        logger.info(" MSTR (" + toPercentage(((((1.0f - shopRoomChance) - restRoomChance) - treasureRoomChance) - eliteRoomChance) - eventRoomChance) + "): " + monsterCount);
        for (int i = 0; i < shopCount; i++) {
            roomList.add(new ShopRoom());
        }
        for (int i2 = 0; i2 < restCount; i2++) {
            roomList.add(new RestRoom());
        }
        for (int i3 = 0; i3 < eliteCount; i3++) {
            roomList.add(new MonsterRoomElite());
        }
        for (int i4 = 0; i4 < eventCount; i4++) {
            roomList.add(new EventRoom());
        }
    }

    private static String toPercentage(float n) {
        return String.format("%.0f", Float.valueOf(n * 100.0f)) + "%";
    }

    private static void firstRoomLogic() {
        initializeFirstRoom();
        leftRoomAvailable = currMapNode.leftNodeAvailable();
        centerRoomAvailable = currMapNode.centerNodeAvailable();
        rightRoomAvailable = currMapNode.rightNodeAvailable();
    }

    private boolean passesDonutCheck(ArrayList<ArrayList<MapRoomNode>> map2) {
        logger.info("CASEY'S DONUT CHECK: ");
        int width = map2.get(0).size();
        int height = map2.size();
        logger.info(" HEIGHT: " + height);
        logger.info(" WIDTH:  " + width);
        int nodeCount = 0;
        boolean[] roomHasNode = new boolean[width];
        for (int i = 0; i < width; i++) {
            roomHasNode[i] = false;
        }
        ArrayList<MapRoomNode> secondToLastRow = map2.get(map2.size() - 2);
        Iterator<MapRoomNode> it = secondToLastRow.iterator();
        while (it.hasNext()) {
            MapRoomNode n = it.next();
            Iterator<MapEdge> it2 = n.getEdges().iterator();
            while (it2.hasNext()) {
                MapEdge e = it2.next();
                roomHasNode[e.dstX] = true;
            }
        }
        for (int i2 = 0; i2 < width - 1; i2++) {
            if (roomHasNode[i2]) {
                nodeCount++;
            }
        }
        if (nodeCount == 1) {
            logger.info(" [SUCCESS] " + nodeCount + " NODE IN LAST ROW");
            int roomCount = 0;
            Iterator<ArrayList<MapRoomNode>> it3 = map2.iterator();
            while (it3.hasNext()) {
                ArrayList<MapRoomNode> rows = it3.next();
                Iterator<MapRoomNode> it4 = rows.iterator();
                while (it4.hasNext()) {
                    MapRoomNode n2 = it4.next();
                    if (n2.room != null) {
                        roomCount++;
                    }
                }
            }
            logger.info(" ROOM COUNT: " + roomCount);
            return true;
        }
        logger.info(" [FAIL] " + nodeCount + " NODES IN LAST ROW");
        return false;
    }

    public static AbstractRoom getCurrRoom() {
        return currMapNode.getRoom();
    }

    public static MapRoomNode getCurrMapNode() {
        return currMapNode;
    }

    public static void setCurrMapNode(MapRoomNode currMapNode2) {
        SoulGroup group = currMapNode.room.souls;
        if (!(currMapNode == null || getCurrRoom() == null)) {
            getCurrRoom().dispose();
        }
        currMapNode = currMapNode2;
        if (currMapNode.room == null) {
            logger.warn("This player loaded into a room that no longer exists (due to a new map gen?)");
            for (int i = 0; i < 5; i++) {
                if (map.get(currMapNode2.y).get(i).room != null) {
                    currMapNode = map.get(currMapNode2.y).get(i);
                    currMapNode.room = map.get(currMapNode2.y).get(i).room;
                    nextRoom.room = map.get(currMapNode2.y).get(i).room;
                    return;
                }
            }
            return;
        }
        currMapNode.room.souls = group;
    }

    public ArrayList<ArrayList<MapRoomNode>> getMap() {
        return map;
    }

    public static AbstractRelic returnRandomRelic(AbstractRelic.RelicTier tier) {
        logger.info("Returning " + tier.name() + " relic");
        return RelicLibrary.getRelic(returnRandomRelicKey(tier)).makeCopy();
    }

    public static AbstractRelic returnRandomScreenlessRelic(AbstractRelic.RelicTier tier) {
        logger.info("Returning " + tier.name() + " relic");
        AbstractRelic makeCopy = RelicLibrary.getRelic(returnRandomRelicKey(tier)).makeCopy();
        while (true) {
            AbstractRelic tmpRelic = makeCopy;
            if (!Objects.equals(tmpRelic.relicId, BottledFlame.ID) && !Objects.equals(tmpRelic.relicId, BottledLightning.ID) && !Objects.equals(tmpRelic.relicId, BottledTornado.ID) && !Objects.equals(tmpRelic.relicId, Whetstone.ID)) {
                return tmpRelic;
            }
            makeCopy = RelicLibrary.getRelic(returnRandomRelicKey(tier)).makeCopy();
        }
    }

    public static AbstractRelic returnRandomNonCampfireRelic(AbstractRelic.RelicTier tier) {
        logger.info("Returning " + tier.name() + " relic");
        AbstractRelic makeCopy = RelicLibrary.getRelic(returnRandomRelicKey(tier)).makeCopy();
        while (true) {
            AbstractRelic tmpRelic = makeCopy;
            if (!Objects.equals(tmpRelic.relicId, PeacePipe.ID) && !Objects.equals(tmpRelic.relicId, Shovel.ID) && !Objects.equals(tmpRelic.relicId, Girya.ID)) {
                return tmpRelic;
            }
            makeCopy = RelicLibrary.getRelic(returnRandomRelicKey(tier)).makeCopy();
        }
    }

    public static AbstractRelic returnRandomRelicEnd(AbstractRelic.RelicTier tier) {
        logger.info("Returning " + tier.name() + " relic");
        return RelicLibrary.getRelic(returnEndRandomRelicKey(tier)).makeCopy();
    }

    public static String returnEndRandomRelicKey(AbstractRelic.RelicTier tier) {
        String retVal = null;
        switch (tier) {
            case COMMON:
                if (!commonRelicPool.isEmpty()) {
                    retVal = commonRelicPool.remove(commonRelicPool.size() - 1);
                    break;
                } else {
                    retVal = returnRandomRelicKey(AbstractRelic.RelicTier.UNCOMMON);
                    break;
                }
            case UNCOMMON:
                if (!uncommonRelicPool.isEmpty()) {
                    retVal = uncommonRelicPool.remove(uncommonRelicPool.size() - 1);
                    break;
                } else {
                    retVal = returnRandomRelicKey(AbstractRelic.RelicTier.RARE);
                    break;
                }
            case RARE:
                if (!rareRelicPool.isEmpty()) {
                    retVal = rareRelicPool.remove(rareRelicPool.size() - 1);
                    break;
                } else {
                    retVal = Circlet.ID;
                    break;
                }
            case SHOP:
                if (!shopRelicPool.isEmpty()) {
                    retVal = shopRelicPool.remove(shopRelicPool.size() - 1);
                    break;
                } else {
                    retVal = returnRandomRelicKey(AbstractRelic.RelicTier.UNCOMMON);
                    break;
                }
            case BOSS:
                if (!bossRelicPool.isEmpty()) {
                    retVal = bossRelicPool.remove(0);
                    break;
                } else {
                    retVal = RedCirclet.ID;
                    break;
                }
            default:
                logger.info("Incorrect relic tier: " + tier.name() + " was called in returnEndRandomRelicKey()");
                break;
        }
        if (!RelicLibrary.getRelic(retVal).canSpawn()) {
            return returnEndRandomRelicKey(tier);
        }
        return retVal;
    }

    public static String returnRandomRelicKey(AbstractRelic.RelicTier tier) {
        String retVal = null;
        switch (tier) {
            case COMMON:
                if (!commonRelicPool.isEmpty()) {
                    retVal = commonRelicPool.remove(0);
                    break;
                } else {
                    retVal = returnRandomRelicKey(AbstractRelic.RelicTier.UNCOMMON);
                    break;
                }
            case UNCOMMON:
                if (!uncommonRelicPool.isEmpty()) {
                    retVal = uncommonRelicPool.remove(0);
                    break;
                } else {
                    retVal = returnRandomRelicKey(AbstractRelic.RelicTier.RARE);
                    break;
                }
            case RARE:
                if (!rareRelicPool.isEmpty()) {
                    retVal = rareRelicPool.remove(0);
                    break;
                } else {
                    retVal = Circlet.ID;
                    break;
                }
            case SHOP:
                if (!shopRelicPool.isEmpty()) {
                    retVal = shopRelicPool.remove(0);
                    break;
                } else {
                    retVal = returnRandomRelicKey(AbstractRelic.RelicTier.UNCOMMON);
                    break;
                }
            case BOSS:
                if (!bossRelicPool.isEmpty()) {
                    retVal = bossRelicPool.remove(0);
                    break;
                } else {
                    retVal = RedCirclet.ID;
                    break;
                }
            default:
                logger.info("Incorrect relic tier: " + tier.name() + " was called in returnRandomRelicKey()");
                break;
        }
        if (!RelicLibrary.getRelic(retVal).canSpawn()) {
            return returnEndRandomRelicKey(tier);
        }
        return retVal;
    }

    public static AbstractRelic.RelicTier returnRandomRelicTier() {
        int roll = relicRng.random(0, 99);
        if (roll < commonRelicChance) {
            return AbstractRelic.RelicTier.COMMON;
        }
        if (roll < commonRelicChance + uncommonRelicChance) {
            return AbstractRelic.RelicTier.UNCOMMON;
        }
        return AbstractRelic.RelicTier.RARE;
    }

    public static AbstractPotion returnTotallyRandomPotion() {
        return PotionHelper.getRandomPotion();
    }

    public static AbstractPotion returnRandomPotion() {
        return returnRandomPotion(false);
    }

    public static AbstractPotion returnRandomPotion(boolean limited) {
        int roll = potionRng.random(0, 99);
        if (roll < PotionHelper.POTION_COMMON_CHANCE) {
            return returnRandomPotion(AbstractPotion.PotionRarity.COMMON, limited);
        }
        if (roll < PotionHelper.POTION_UNCOMMON_CHANCE + PotionHelper.POTION_COMMON_CHANCE) {
            return returnRandomPotion(AbstractPotion.PotionRarity.UNCOMMON, limited);
        }
        return returnRandomPotion(AbstractPotion.PotionRarity.RARE, limited);
    }

    public static AbstractPotion returnRandomPotion(AbstractPotion.PotionRarity rarity, boolean limited) {
        AbstractPotion temp = PotionHelper.getRandomPotion();
        boolean spamCheck = limited;
        while (true) {
            if (temp.rarity == rarity && !spamCheck) {
                return temp;
            }
            spamCheck = limited;
            temp = PotionHelper.getRandomPotion();
            if (temp.ID != FruitJuice.POTION_ID) {
                spamCheck = false;
            }
        }
    }

    public static void transformCard(AbstractCard c) {
        transformCard(c, false);
    }

    public static void transformCard(AbstractCard c, boolean autoUpgrade) {
        transformCard(c, autoUpgrade, new Random());
    }

    public static void transformCard(AbstractCard c, boolean autoUpgrade, Random rng) {
        switch (c.color) {
            case COLORLESS:
                transformedCard = returnTrulyRandomColorlessCardFromAvailable(c, rng).makeCopy();
                break;
            case CURSE:
                transformedCard = CardLibrary.getCurse(c, rng).makeCopy();
                break;
            default:
                transformedCard = returnTrulyRandomCardFromAvailable(c, rng).makeCopy();
                break;
        }
        UnlockTracker.markCardAsSeen(transformedCard.cardID);
        if (autoUpgrade && transformedCard.canUpgrade()) {
            transformedCard.upgrade();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void srcTransformCard(AbstractCard c) {
        logger.info("Transform using SRC pool...");
        switch (c.rarity) {
            case BASIC:
                transformedCard = srcCommonCardPool.getRandomCard(false).makeCopy();
                return;
            case COMMON:
                srcCommonCardPool.removeCard(c.cardID);
                transformedCard = srcCommonCardPool.getRandomCard(false).makeCopy();
                srcCommonCardPool.addToTop(c.makeCopy());
                return;
            case UNCOMMON:
                srcUncommonCardPool.removeCard(c.cardID);
                transformedCard = srcUncommonCardPool.getRandomCard(false).makeCopy();
                srcUncommonCardPool.addToTop(c.makeCopy());
                return;
            case RARE:
                srcRareCardPool.removeCard(c.cardID);
                if (srcRareCardPool.isEmpty()) {
                    transformedCard = srcUncommonCardPool.getRandomCard(false).makeCopy();
                } else {
                    transformedCard = srcRareCardPool.getRandomCard(false).makeCopy();
                }
                srcRareCardPool.addToTop(c.makeCopy());
                return;
            case CURSE:
                if (srcRareCardPool.isEmpty()) {
                    transformedCard = srcUncommonCardPool.getRandomCard(false).makeCopy();
                    break;
                } else {
                    transformedCard = srcRareCardPool.getRandomCard(false).makeCopy();
                    break;
                }
        }
        logger.info("Transform called on a strange card type: " + c.type.name());
        transformedCard = srcCommonCardPool.getRandomCard(false).makeCopy();
    }

    public static CardGroup getEachRare() {
        CardGroup everyRareCard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator<AbstractCard> it = rareCardPool.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            everyRareCard.addToBottom(c.makeCopy());
        }
        return everyRareCard;
    }

    public static AbstractCard returnRandomCard() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        AbstractCard.CardRarity rarity = rollRarity();
        if (rarity.equals(AbstractCard.CardRarity.COMMON)) {
            list.addAll(srcCommonCardPool.group);
        } else if (rarity.equals(AbstractCard.CardRarity.UNCOMMON)) {
            list.addAll(srcUncommonCardPool.group);
        } else {
            list.addAll(srcRareCardPool.group);
        }
        return list.get(cardRandomRng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomCard() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.addAll(srcCommonCardPool.group);
        list.addAll(srcUncommonCardPool.group);
        list.addAll(srcRareCardPool.group);
        return list.get(cardRandomRng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomCardInCombat() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator<AbstractCard> it = srcCommonCardPool.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }
        Iterator<AbstractCard> it2 = srcUncommonCardPool.group.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            if (!c2.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c2);
                UnlockTracker.markCardAsSeen(c2.cardID);
            }
        }
        Iterator<AbstractCard> it3 = srcRareCardPool.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            if (!c3.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c3);
                UnlockTracker.markCardAsSeen(c3.cardID);
            }
        }
        return list.get(cardRandomRng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomCardInCombat(AbstractCard.CardType type) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator<AbstractCard> it = srcCommonCardPool.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c.type == type && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        Iterator<AbstractCard> it2 = srcUncommonCardPool.group.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            if (c2.type == type && !c2.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c2);
            }
        }
        Iterator<AbstractCard> it3 = srcRareCardPool.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            if (c3.type == type && !c3.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c3);
            }
        }
        return list.get(cardRandomRng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomColorlessCardInCombat() {
        return returnTrulyRandomColorlessCardInCombat(cardRandomRng);
    }

    public static AbstractCard returnTrulyRandomColorlessCardInCombat(String prohibitedID) {
        return returnTrulyRandomColorlessCardFromAvailable(prohibitedID, cardRandomRng);
    }

    public static AbstractCard returnTrulyRandomColorlessCardInCombat(Random rng) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator<AbstractCard> it = srcColorlessCardPool.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        return list.get(rng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomColorlessCardFromAvailable(String prohibited, Random rng) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator<AbstractCard> it = srcColorlessCardPool.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c.cardID != prohibited) {
                list.add(c);
            }
        }
        return list.get(rng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomColorlessCardFromAvailable(AbstractCard prohibited, Random rng) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator<AbstractCard> it = srcColorlessCardPool.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (!Objects.equals(c.cardID, prohibited.cardID)) {
                list.add(c);
            }
        }
        return list.get(rng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomCardFromAvailable(AbstractCard prohibited, Random rng) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        switch (prohibited.color) {
            case COLORLESS:
                Iterator<AbstractCard> it = colorlessCardPool.group.iterator();
                while (it.hasNext()) {
                    AbstractCard c = it.next();
                    if (!Objects.equals(c.cardID, prohibited.cardID)) {
                        list.add(c);
                    }
                }
                break;
            case CURSE:
                return CardLibrary.getCurse();
            default:
                Iterator<AbstractCard> it2 = commonCardPool.group.iterator();
                while (it2.hasNext()) {
                    AbstractCard c2 = it2.next();
                    if (!Objects.equals(c2.cardID, prohibited.cardID)) {
                        list.add(c2);
                    }
                }
                Iterator<AbstractCard> it3 = srcUncommonCardPool.group.iterator();
                while (it3.hasNext()) {
                    AbstractCard c3 = it3.next();
                    if (!Objects.equals(c3.cardID, prohibited.cardID)) {
                        list.add(c3);
                    }
                }
                Iterator<AbstractCard> it4 = srcRareCardPool.group.iterator();
                while (it4.hasNext()) {
                    AbstractCard c4 = it4.next();
                    if (!Objects.equals(c4.cardID, prohibited.cardID)) {
                        list.add(c4);
                    }
                }
                break;
        }
        return list.get(rng.random(list.size() - 1)).makeCopy();
    }

    public static AbstractCard returnTrulyRandomCardFromAvailable(AbstractCard prohibited) {
        return returnTrulyRandomCardFromAvailable(prohibited, new Random());
    }

    public static AbstractCard getTransformedCard() {
        AbstractCard retVal = transformedCard;
        transformedCard = null;
        return retVal;
    }

    public void populateFirstStrongEnemy(ArrayList<MonsterInfo> monsters, ArrayList<String> exclusions) {
        String m;
        do {
            m = MonsterInfo.roll(monsters, monsterRng.random());
        } while (exclusions.contains(m));
        monsterList.add(m);
    }

    public void populateMonsterList(ArrayList<MonsterInfo> monsters, int numMonsters, boolean elites) {
        if (elites) {
            int i = 0;
            while (i < numMonsters) {
                if (eliteMonsterList.isEmpty()) {
                    eliteMonsterList.add(MonsterInfo.roll(monsters, monsterRng.random()));
                } else {
                    String toAdd = MonsterInfo.roll(monsters, monsterRng.random());
                    if (!toAdd.equals(eliteMonsterList.get(eliteMonsterList.size() - 1))) {
                        eliteMonsterList.add(toAdd);
                    } else {
                        i--;
                    }
                }
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < numMonsters) {
            if (monsterList.isEmpty()) {
                monsterList.add(MonsterInfo.roll(monsters, monsterRng.random()));
            } else {
                String toAdd2 = MonsterInfo.roll(monsters, monsterRng.random());
                if (toAdd2.equals(monsterList.get(monsterList.size() - 1))) {
                    i2--;
                } else if (monsterList.size() <= 1 || !toAdd2.equals(monsterList.get(monsterList.size() - 2))) {
                    monsterList.add(toAdd2);
                } else {
                    i2--;
                }
            }
            i2++;
        }
    }

    public static AbstractCard returnColorlessCard(AbstractCard.CardRarity rarity) {
        Collections.shuffle(colorlessCardPool.group, new java.util.Random(shuffleRng.randomLong()));
        Iterator<AbstractCard> it = colorlessCardPool.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c.rarity == rarity) {
                return c.makeCopy();
            }
        }
        if (rarity == AbstractCard.CardRarity.RARE) {
            Iterator<AbstractCard> it2 = colorlessCardPool.group.iterator();
            while (it2.hasNext()) {
                AbstractCard c2 = it2.next();
                if (c2.rarity == AbstractCard.CardRarity.UNCOMMON) {
                    return c2.makeCopy();
                }
            }
        }
        return new SwiftStrike();
    }

    public static AbstractCard returnColorlessCard() {
        Collections.shuffle(colorlessCardPool.group);
        Iterator<AbstractCard> it = colorlessCardPool.group.iterator();
        if (!it.hasNext()) {
            return new SwiftStrike();
        }
        AbstractCard c = it.next();
        return c.makeCopy();
    }

    public static AbstractCard returnRandomCurse() {
        AbstractCard c = CardLibrary.getCurse().makeCopy();
        UnlockTracker.markCardAsSeen(c.cardID);
        return c;
    }

    public void initializePotions() {
        PotionHelper.initialize(player.chosenClass);
    }

    public void initializeCardPools() {
        logger.info("INIT CARD POOL");
        long startTime = System.currentTimeMillis();
        commonCardPool.clear();
        uncommonCardPool.clear();
        rareCardPool.clear();
        colorlessCardPool.clear();
        curseCardPool.clear();
        ArrayList<AbstractCard> tmpPool = new ArrayList<>();
        if (ModHelper.isModEnabled(ColorlessCards.ID)) {
            CardLibrary.addColorlessCards(tmpPool);
        }
        if (ModHelper.isModEnabled(Diverse.ID)) {
            CardLibrary.addRedCards(tmpPool);
            CardLibrary.addGreenCards(tmpPool);
            CardLibrary.addBlueCards(tmpPool);
            if (!UnlockTracker.isCharacterLocked("Watcher")) {
                CardLibrary.addPurpleCards(tmpPool);
            }
        } else {
            player.getCardPool(tmpPool);
        }
        addColorlessCards();
        addCurseCards();
        Iterator<AbstractCard> it = tmpPool.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            switch (c.rarity) {
                case COMMON:
                    commonCardPool.addToTop(c);
                    break;
                case UNCOMMON:
                    uncommonCardPool.addToTop(c);
                    break;
                case RARE:
                    rareCardPool.addToTop(c);
                    break;
                case CURSE:
                    curseCardPool.addToTop(c);
                    break;
                default:
                    logger.info("Unspecified rarity: " + c.rarity.name() + " when creating pools! AbstractDungeon: Line 827");
                    break;
            }
        }
        srcColorlessCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        srcCurseCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        srcRareCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        srcUncommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        srcCommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        Iterator<AbstractCard> it2 = colorlessCardPool.group.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            srcColorlessCardPool.addToBottom(c2);
        }
        Iterator<AbstractCard> it3 = curseCardPool.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            srcCurseCardPool.addToBottom(c3);
        }
        Iterator<AbstractCard> it4 = rareCardPool.group.iterator();
        while (it4.hasNext()) {
            AbstractCard c4 = it4.next();
            srcRareCardPool.addToBottom(c4);
        }
        Iterator<AbstractCard> it5 = uncommonCardPool.group.iterator();
        while (it5.hasNext()) {
            AbstractCard c5 = it5.next();
            srcUncommonCardPool.addToBottom(c5);
        }
        Iterator<AbstractCard> it6 = commonCardPool.group.iterator();
        while (it6.hasNext()) {
            AbstractCard c6 = it6.next();
            srcCommonCardPool.addToBottom(c6);
        }
        logger.info("Cardpool load time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private void addColorlessCards() {
        for (Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (!(card.color != AbstractCard.CardColor.COLORLESS || card.rarity == AbstractCard.CardRarity.BASIC || card.rarity == AbstractCard.CardRarity.SPECIAL || card.type == AbstractCard.CardType.STATUS)) {
                colorlessCardPool.addToTop(card);
            }
        }
        logger.info("COLORLESS CARDS: " + colorlessCardPool.size());
    }

    private void addCurseCards() {
        for (Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.type == AbstractCard.CardType.CURSE && !Objects.equals(card.cardID, Necronomicurse.ID) && !Objects.equals(card.cardID, AscendersBane.ID) && !Objects.equals(card.cardID, CurseOfTheBell.ID) && !Objects.equals(card.cardID, Pride.ID)) {
                curseCardPool.addToTop(card);
            }
        }
        logger.info("CURSE CARDS: " + curseCardPool.size());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initializeRelicList() {
        commonRelicPool.clear();
        uncommonRelicPool.clear();
        rareRelicPool.clear();
        shopRelicPool.clear();
        bossRelicPool.clear();
        RelicLibrary.populateRelicPool(commonRelicPool, AbstractRelic.RelicTier.COMMON, player.chosenClass);
        RelicLibrary.populateRelicPool(uncommonRelicPool, AbstractRelic.RelicTier.UNCOMMON, player.chosenClass);
        RelicLibrary.populateRelicPool(rareRelicPool, AbstractRelic.RelicTier.RARE, player.chosenClass);
        RelicLibrary.populateRelicPool(shopRelicPool, AbstractRelic.RelicTier.SHOP, player.chosenClass);
        RelicLibrary.populateRelicPool(bossRelicPool, AbstractRelic.RelicTier.BOSS, player.chosenClass);
        if (floorNum >= 1) {
            Iterator<AbstractRelic> it = player.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                relicsToRemoveOnStart.add(r.relicId);
            }
        }
        Collections.shuffle(commonRelicPool, new java.util.Random(relicRng.randomLong()));
        Collections.shuffle(uncommonRelicPool, new java.util.Random(relicRng.randomLong()));
        Collections.shuffle(rareRelicPool, new java.util.Random(relicRng.randomLong()));
        Collections.shuffle(shopRelicPool, new java.util.Random(relicRng.randomLong()));
        Collections.shuffle(bossRelicPool, new java.util.Random(relicRng.randomLong()));
        if (ModHelper.isModEnabled("Flight") || ModHelper.isModEnabled(CertainFuture.ID)) {
            relicsToRemoveOnStart.add(WingBoots.ID);
        }
        if (ModHelper.isModEnabled(Diverse.ID)) {
            relicsToRemoveOnStart.add(PrismaticShard.ID);
        }
        if (ModHelper.isModEnabled(DeadlyEvents.ID)) {
            relicsToRemoveOnStart.add(JuzuBracelet.ID);
        }
        if (ModHelper.isModEnabled(Hoarder.ID)) {
            relicsToRemoveOnStart.add(SmilingMask.ID);
        }
        if (ModHelper.isModEnabled(Draft.ID) || ModHelper.isModEnabled(SealedDeck.ID) || ModHelper.isModEnabled(Shiny.ID) || ModHelper.isModEnabled(Insanity.ID)) {
            relicsToRemoveOnStart.add(PandorasBox.ID);
        }
        Iterator<String> it2 = relicsToRemoveOnStart.iterator();
        while (it2.hasNext()) {
            String remove = it2.next();
            Iterator<String> s = commonRelicPool.iterator();
            while (true) {
                if (!s.hasNext()) {
                    break;
                }
                String derp = s.next();
                if (derp.equals(remove)) {
                    s.remove();
                    logger.info(derp + " removed.");
                    break;
                }
            }
            Iterator<String> s2 = uncommonRelicPool.iterator();
            while (true) {
                if (!s2.hasNext()) {
                    break;
                }
                String derp2 = s2.next();
                if (derp2.equals(remove)) {
                    s2.remove();
                    logger.info(derp2 + " removed.");
                    break;
                }
            }
            Iterator<String> s3 = rareRelicPool.iterator();
            while (true) {
                if (!s3.hasNext()) {
                    break;
                }
                String derp3 = s3.next();
                if (derp3.equals(remove)) {
                    s3.remove();
                    logger.info(derp3 + " removed.");
                    break;
                }
            }
            Iterator<String> s4 = bossRelicPool.iterator();
            while (true) {
                if (!s4.hasNext()) {
                    break;
                }
                String derp4 = s4.next();
                if (derp4.equals(remove)) {
                    s4.remove();
                    logger.info(derp4 + " removed.");
                    break;
                }
            }
            Iterator<String> s5 = shopRelicPool.iterator();
            while (true) {
                if (s5.hasNext()) {
                    String derp5 = s5.next();
                    if (derp5.equals(remove)) {
                        s5.remove();
                        logger.info(derp5 + " removed.");
                        break;
                    }
                }
            }
        }
        if (Settings.isDebug) {
            logger.info("Relic (Common):");
            Iterator<String> it3 = commonRelicPool.iterator();
            while (it3.hasNext()) {
                String s6 = it3.next();
                logger.info(" " + s6);
            }
            logger.info("Relic (Uncommon):");
            Iterator<String> it4 = uncommonRelicPool.iterator();
            while (it4.hasNext()) {
                String s7 = it4.next();
                logger.info(" " + s7);
            }
            logger.info("Relic (Rare):");
            Iterator<String> it5 = rareRelicPool.iterator();
            while (it5.hasNext()) {
                String s8 = it5.next();
                logger.info(" " + s8);
            }
            logger.info("Relic (Shop):");
            Iterator<String> it6 = shopRelicPool.iterator();
            while (it6.hasNext()) {
                String s9 = it6.next();
                logger.info(" " + s9);
            }
            logger.info("Relic (Boss):");
            Iterator<String> it7 = bossRelicPool.iterator();
            while (it7.hasNext()) {
                String s10 = it7.next();
                logger.info(" " + s10);
            }
        }
    }

    public void initializeSpecialOneTimeEventList() {
        specialOneTimeEventList.clear();
        specialOneTimeEventList.add(AccursedBlacksmith.ID);
        specialOneTimeEventList.add(Bonfire.ID);
        specialOneTimeEventList.add(Designer.ID);
        specialOneTimeEventList.add(Duplicator.ID);
        specialOneTimeEventList.add(FaceTrader.ID);
        specialOneTimeEventList.add(FountainOfCurseRemoval.ID);
        specialOneTimeEventList.add(KnowingSkull.ID);
        specialOneTimeEventList.add(Lab.ID);
        specialOneTimeEventList.add(Nloth.ID);
        if (isNoteForYourselfAvailable()) {
            specialOneTimeEventList.add(NoteForYourself.ID);
        }
        specialOneTimeEventList.add(SecretPortal.ID);
        specialOneTimeEventList.add(TheJoust.ID);
        specialOneTimeEventList.add(WeMeetAgain.ID);
        specialOneTimeEventList.add(WomanInBlue.ID);
    }

    private boolean isNoteForYourselfAvailable() {
        if (Settings.isDailyRun) {
            logger.info("Note For Yourself is disabled due to Daily Run");
            return false;
        } else if (ascensionLevel >= 15) {
            logger.info("Note For Yourself is disabled beyond Ascension 15+");
            return false;
        } else if (ascensionLevel == 0) {
            logger.info("Note For Yourself is enabled due to No Ascension");
            return true;
        } else if (ascensionLevel < player.getPrefs().getInteger(CharStat.ASCENSION_LEVEL)) {
            logger.info("Note For Yourself is enabled as it's less than Highest Unlocked Ascension");
            return true;
        } else {
            logger.info("Note For Yourself is disabled as requirements aren't met");
            return false;
        }
    }

    public static ArrayList<AbstractCard> getColorlessRewardCards() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        int numCards = 3;
        Iterator<AbstractRelic> it = player.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            numCards = r.changeNumberOfCardsInReward(numCards);
        }
        if (ModHelper.isModEnabled(Binary.ID)) {
            numCards--;
        }
        for (int i = 0; i < numCards; i++) {
            AbstractCard.CardRarity rarity = rollRareOrUncommon(colorlessRareChance);
            AbstractCard card = null;
            switch (rarity) {
                case UNCOMMON:
                    card = getColorlessCardFromPool(rarity);
                    break;
                case RARE:
                    card = getColorlessCardFromPool(rarity);
                    cardBlizzRandomizer = cardBlizzStartOffset;
                    break;
                default:
                    logger.info("WTF?");
                    break;
            }
            while (retVal.contains(card)) {
                if (card != null) {
                    logger.info("DUPE: " + card.originalName);
                }
                card = getColorlessCardFromPool(rarity);
            }
            if (card != null) {
                retVal.add(card);
            }
        }
        ArrayList<AbstractCard> retVal2 = new ArrayList<>();
        Iterator<AbstractCard> it2 = retVal.iterator();
        while (it2.hasNext()) {
            AbstractCard c = it2.next();
            retVal2.add(c.makeCopy());
        }
        return retVal2;
    }

    public static ArrayList<AbstractCard> getRewardCards() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        int numCards = 3;
        Iterator<AbstractRelic> it = player.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            numCards = r.changeNumberOfCardsInReward(numCards);
        }
        if (ModHelper.isModEnabled(Binary.ID)) {
            numCards--;
        }
        for (int i = 0; i < numCards; i++) {
            AbstractCard.CardRarity rarity = rollRarity();
            AbstractCard card = null;
            switch (rarity) {
                case COMMON:
                    cardBlizzRandomizer -= cardBlizzGrowth;
                    if (cardBlizzRandomizer <= cardBlizzMaxOffset) {
                        cardBlizzRandomizer = cardBlizzMaxOffset;
                        break;
                    }
                    break;
                case UNCOMMON:
                    break;
                case RARE:
                    cardBlizzRandomizer = cardBlizzStartOffset;
                    break;
                default:
                    logger.info("WTF?");
                    break;
            }
            boolean containsDupe = true;
            while (containsDupe) {
                containsDupe = false;
                if (player.hasRelic(PrismaticShard.ID)) {
                    card = CardLibrary.getAnyColorCard(rarity);
                } else {
                    card = getCard(rarity);
                }
                Iterator<AbstractCard> it2 = retVal.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (it2.next().cardID.equals(card.cardID)) {
                        containsDupe = true;
                    }
                }
            }
            if (card != null) {
                retVal.add(card);
            }
        }
        ArrayList<AbstractCard> retVal2 = new ArrayList<>();
        Iterator<AbstractCard> it3 = retVal.iterator();
        while (it3.hasNext()) {
            retVal2.add(it3.next().makeCopy());
        }
        Iterator<AbstractCard> it4 = retVal2.iterator();
        while (it4.hasNext()) {
            AbstractCard c = it4.next();
            if (c.rarity == AbstractCard.CardRarity.RARE || !cardRng.randomBoolean(cardUpgradedChance) || !c.canUpgrade()) {
                Iterator<AbstractRelic> it5 = player.relics.iterator();
                while (it5.hasNext()) {
                    AbstractRelic r2 = it5.next();
                    r2.onPreviewObtainCard(c);
                }
            } else {
                c.upgrade();
            }
        }
        return retVal2;
    }

    public static AbstractCard getCard(AbstractCard.CardRarity rarity) {
        switch (rarity) {
            case COMMON:
                return commonCardPool.getRandomCard(true);
            case UNCOMMON:
                return uncommonCardPool.getRandomCard(true);
            case RARE:
                return rareCardPool.getRandomCard(true);
            case CURSE:
                return curseCardPool.getRandomCard(true);
            default:
                logger.info("No rarity on getCard in Abstract Dungeon");
                return null;
        }
    }

    public static AbstractCard getCard(AbstractCard.CardRarity rarity, Random rng) {
        switch (rarity) {
            case COMMON:
                return commonCardPool.getRandomCard(rng);
            case UNCOMMON:
                return uncommonCardPool.getRandomCard(rng);
            case RARE:
                return rareCardPool.getRandomCard(rng);
            case CURSE:
                return curseCardPool.getRandomCard(rng);
            default:
                logger.info("No rarity on getCard in Abstract Dungeon");
                return null;
        }
    }

    public static AbstractCard getCardWithoutRng(AbstractCard.CardRarity rarity) {
        switch (rarity) {
            case COMMON:
                return commonCardPool.getRandomCard(false);
            case UNCOMMON:
                return uncommonCardPool.getRandomCard(false);
            case RARE:
                return rareCardPool.getRandomCard(false);
            case CURSE:
                return returnRandomCurse();
            default:
                logger.info("Check getCardWithoutRng");
                return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.megacrit.cardcrawl.cards.AbstractCard getCardFromPool(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity r4, com.megacrit.cardcrawl.cards.AbstractCard.CardType r5, boolean r6) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCardFromPool(com.megacrit.cardcrawl.cards.AbstractCard$CardRarity, com.megacrit.cardcrawl.cards.AbstractCard$CardType, boolean):com.megacrit.cardcrawl.cards.AbstractCard");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.megacrit.cardcrawl.cards.AbstractCard getColorlessCardFromPool(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity r4) {
        /*
            int[] r0 = com.megacrit.cardcrawl.dungeons.AbstractDungeon.AnonymousClass1.$SwitchMap$com$megacrit$cardcrawl$cards$AbstractCard$CardRarity
            r1 = r4
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 3: goto L33;
                case 4: goto L24;
                default: goto L42;
            }
        L24:
            com.megacrit.cardcrawl.cards.CardGroup r0 = com.megacrit.cardcrawl.dungeons.AbstractDungeon.colorlessCardPool
            r1 = 1
            r2 = r4
            com.megacrit.cardcrawl.cards.AbstractCard r0 = r0.getRandomCard(r1, r2)
            r5 = r0
            r0 = r5
            if (r0 == 0) goto L33
            r0 = r5
            return r0
        L33:
            com.megacrit.cardcrawl.cards.CardGroup r0 = com.megacrit.cardcrawl.dungeons.AbstractDungeon.colorlessCardPool
            r1 = 1
            r2 = r4
            com.megacrit.cardcrawl.cards.AbstractCard r0 = r0.getRandomCard(r1, r2)
            r5 = r0
            r0 = r5
            if (r0 == 0) goto L42
            r0 = r5
            return r0
        L42:
            org.apache.logging.log4j.Logger r0 = com.megacrit.cardcrawl.dungeons.AbstractDungeon.logger
            java.lang.String r1 = "ERROR: getColorlessCardFromPool"
            r0.info(r1)
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megacrit.cardcrawl.dungeons.AbstractDungeon.getColorlessCardFromPool(com.megacrit.cardcrawl.cards.AbstractCard$CardRarity):com.megacrit.cardcrawl.cards.AbstractCard");
    }

    public static AbstractCard.CardRarity rollRarity(Random rng) {
        int roll = cardRng.random(99) + cardBlizzRandomizer;
        if (currMapNode == null) {
            return getCardRarityFallback(roll);
        }
        return getCurrRoom().getCardRarity(roll);
    }

    private static AbstractCard.CardRarity getCardRarityFallback(int roll) {
        if (roll < 3) {
            return AbstractCard.CardRarity.RARE;
        }
        if (roll < 40) {
            return AbstractCard.CardRarity.UNCOMMON;
        }
        return AbstractCard.CardRarity.COMMON;
    }

    public static AbstractCard.CardRarity rollRarity() {
        return rollRarity(cardRng);
    }

    public static AbstractCard.CardRarity rollRareOrUncommon(float rareChance) {
        if (cardRng.randomBoolean(rareChance)) {
            return AbstractCard.CardRarity.RARE;
        }
        return AbstractCard.CardRarity.UNCOMMON;
    }

    public static AbstractMonster getRandomMonster() {
        return currMapNode.room.monsters.getRandomMonster(null, true, cardRandomRng);
    }

    public static AbstractMonster getRandomMonster(AbstractMonster except) {
        return currMapNode.room.monsters.getRandomMonster(except, true, cardRandomRng);
    }

    public static void nextRoomTransitionStart() {
        fadeOut();
        waitingOnFadeOut = true;
        overlayMenu.proceedButton.hide();
        if (ModHelper.isModEnabled(Terminal.ID)) {
            player.decreaseMaxHealth(1);
        }
    }

    public static void initializeFirstRoom() {
        fadeIn();
        floorNum++;
        if (currMapNode.room instanceof MonsterRoom) {
            if (!CardCrawlGame.loadingSave) {
                if (SaveHelper.shouldSave()) {
                    SaveHelper.saveIfAppropriate(SaveFile.SaveType.ENTER_ROOM);
                } else {
                    Metrics metrics = new Metrics();
                    metrics.setValues(false, false, null, Metrics.MetricRequestType.NONE);
                    metrics.gatherAllDataAndSave(false, false, null);
                }
            }
            floorNum--;
        }
        scene.nextRoom(currMapNode.room);
    }

    public static void resetPlayer() {
        player.orbs.clear();
        player.animX = 0.0f;
        player.animY = 0.0f;
        player.hideHealthBar();
        player.hand.clear();
        player.powers.clear();
        player.drawPile.clear();
        player.discardPile.clear();
        player.exhaustPile.clear();
        player.limbo.clear();
        player.loseBlock(true);
        player.damagedThisCombat = 0;
        if (!player.stance.ID.equals(NeutralStance.STANCE_ID)) {
            player.stance = new NeutralStance();
            player.onStanceChange(NeutralStance.STANCE_ID);
        }
        GameActionManager.turn = 1;
    }

    public void nextRoomTransition() {
        nextRoomTransition(null);
    }

    public void nextRoomTransition(SaveFile saveFile) {
        AbstractCard tmpCard;
        overlayMenu.proceedButton.setLabel(TEXT[0]);
        combatRewardScreen.clear();
        if (!(nextRoom == null || nextRoom.room == null)) {
            nextRoom.room.rewards.clear();
        }
        if (getCurrRoom() instanceof MonsterRoomElite) {
            if (!eliteMonsterList.isEmpty()) {
                logger.info("Removing elite: " + eliteMonsterList.get(0) + " from monster list.");
                eliteMonsterList.remove(0);
            } else {
                generateElites(10);
            }
        } else if (getCurrRoom() instanceof MonsterRoom) {
            if (!monsterList.isEmpty()) {
                logger.info("Removing monster: " + monsterList.get(0) + " from monster list.");
                monsterList.remove(0);
            } else {
                generateStrongEnemies(12);
            }
        } else if ((getCurrRoom() instanceof EventRoom) && (getCurrRoom().event instanceof NoteForYourself) && (tmpCard = ((NoteForYourself) getCurrRoom().event).saveCard) != null) {
            CardCrawlGame.playerPref.putString("NOTE_CARD", tmpCard.cardID);
            CardCrawlGame.playerPref.putInteger("NOTE_UPGRADE", tmpCard.timesUpgraded);
            CardCrawlGame.playerPref.flush();
        }
        if (RestRoom.lastFireSoundId != 0) {
            CardCrawlGame.sound.fadeOut("REST_FIRE_WET", RestRoom.lastFireSoundId);
        }
        if (!player.stance.ID.equals(NeutralStance.STANCE_ID) && player.stance != null) {
            player.stance.stopIdleSfx();
        }
        gridSelectScreen.upgradePreviewCard = null;
        previousScreen = null;
        dynamicBanner.hide();
        dungeonMapScreen.closeInstantly();
        closeCurrentScreen();
        topPanel.unhoverHitboxes();
        fadeIn();
        player.resetControllerValues();
        effectList.clear();
        Iterator<AbstractGameEffect> i = topLevelEffects.iterator();
        while (i.hasNext()) {
            AbstractGameEffect e = i.next();
            if (!(e instanceof ObtainKeyEffect)) {
                i.remove();
            }
        }
        topLevelEffectsQueue.clear();
        effectsQueue.clear();
        dungeonMapScreen.dismissable = true;
        dungeonMapScreen.map.legend.isLegendHighlighted = false;
        resetPlayer();
        if (!CardCrawlGame.loadingSave) {
            incrementFloorBasedMetrics();
            floorNum++;
            if (!TipTracker.tips.get(TipTracker.INTENT_TIP).booleanValue() && floorNum == 6) {
                TipTracker.neverShowAgain(TipTracker.INTENT_TIP);
            }
            StatsScreen.incrementFloorClimbed();
            SaveHelper.saveIfAppropriate(SaveFile.SaveType.ENTER_ROOM);
        }
        monsterHpRng = new Random(Long.valueOf(Settings.seed.longValue() + floorNum));
        aiRng = new Random(Long.valueOf(Settings.seed.longValue() + floorNum));
        shuffleRng = new Random(Long.valueOf(Settings.seed.longValue() + floorNum));
        cardRandomRng = new Random(Long.valueOf(Settings.seed.longValue() + floorNum));
        miscRng = new Random(Long.valueOf(Settings.seed.longValue() + floorNum));
        boolean isLoadingPostCombatSave = CardCrawlGame.loadingSave && saveFile != null && saveFile.post_combat;
        boolean isLoadingCompletedEvent = false;
        if (nextRoom != null && !isLoadingPostCombatSave) {
            Iterator<AbstractRelic> it = player.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onEnterRoom(nextRoom.room);
            }
        }
        if (!actionManager.actions.isEmpty()) {
            logger.info("[WARNING] Line:1904: Action Manager was NOT clear! Clearing");
            actionManager.clear();
        }
        if (nextRoom != null) {
            String roomMetricKey = nextRoom.room.getMapSymbol();
            if (nextRoom.room instanceof EventRoom) {
                Random eventRngDuplicate = new Random(Settings.seed, eventRng.counter);
                EventHelper.RoomResult roomResult = EventHelper.roll(eventRngDuplicate);
                isLoadingCompletedEvent = isLoadingPostCombatSave && roomResult == EventHelper.RoomResult.EVENT;
                if (!isLoadingCompletedEvent) {
                    eventRng = eventRngDuplicate;
                    nextRoom.room = generateRoom(roomResult);
                }
                roomMetricKey = nextRoom.room.getMapSymbol();
                if ((nextRoom.room instanceof MonsterRoom) || (nextRoom.room instanceof MonsterRoomElite)) {
                    nextRoom.room.combatEvent = true;
                }
                nextRoom.room.setMapSymbol("?");
                nextRoom.room.setMapImg(ImageMaster.MAP_NODE_EVENT, ImageMaster.MAP_NODE_EVENT_OUTLINE);
            }
            if (!isLoadingPostCombatSave) {
                CardCrawlGame.metricData.path_per_floor.add(roomMetricKey);
            }
            setCurrMapNode(nextRoom);
        }
        if (getCurrRoom() != null && !isLoadingPostCombatSave) {
            Iterator<AbstractRelic> it2 = player.relics.iterator();
            while (it2.hasNext()) {
                AbstractRelic r2 = it2.next();
                r2.justEnteredRoom(getCurrRoom());
            }
        }
        if (isLoadingCompletedEvent) {
            getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            String eventKey = (String) saveFile.metric_event_choices.get(saveFile.metric_event_choices.size() - 1).get("event_name");
            ((EventRoom) getCurrRoom()).event = EventHelper.getEvent(eventKey);
        } else {
            if (isAscensionMode) {
                CardCrawlGame.publisherIntegration.setRichPresenceDisplayPlaying(floorNum, ascensionLevel, player.getLocalizedCharacterName());
            } else {
                CardCrawlGame.publisherIntegration.setRichPresenceDisplayPlaying(floorNum, player.getLocalizedCharacterName());
            }
            getCurrRoom().onPlayerEntry();
        }
        if (!(getCurrRoom() instanceof MonsterRoom) || !lastCombatMetricKey.equals(MonsterHelper.SHIELD_SPEAR_ENC)) {
            player.movePosition(Settings.WIDTH * 0.25f, floorY);
            player.flipHorizontal = false;
        } else {
            player.movePosition(Settings.WIDTH / 2.0f, floorY);
        }
        if ((currMapNode.room instanceof MonsterRoom) && !isLoadingPostCombatSave) {
            player.preBattlePrep();
        }
        scene.nextRoom(currMapNode.room);
        if (currMapNode.room instanceof RestRoom) {
            rs = RenderScene.CAMPFIRE;
        } else if (currMapNode.room.event instanceof AbstractImageEvent) {
            rs = RenderScene.EVENT;
        } else {
            rs = RenderScene.NORMAL;
        }
    }

    private void incrementFloorBasedMetrics() {
        if (floorNum != 0) {
            CardCrawlGame.metricData.current_hp_per_floor.add(Integer.valueOf(player.currentHealth));
            CardCrawlGame.metricData.max_hp_per_floor.add(Integer.valueOf(player.maxHealth));
            CardCrawlGame.metricData.gold_per_floor.add(Integer.valueOf(player.gold));
        }
    }

    private AbstractRoom generateRoom(EventHelper.RoomResult roomType) {
        logger.info("GENERATING ROOM: " + roomType.name());
        switch (roomType) {
            case ELITE:
                return new MonsterRoomElite();
            case MONSTER:
                return new MonsterRoom();
            case SHOP:
                return new ShopRoom();
            case TREASURE:
                return new TreasureRoom();
            default:
                return new EventRoom();
        }
    }

    public static MonsterGroup getMonsters() {
        return getCurrRoom().monsters;
    }

    public MonsterGroup getMonsterForRoomCreation() {
        if (monsterList.isEmpty()) {
            generateStrongEnemies(12);
        }
        logger.info("MONSTER: " + monsterList.get(0));
        lastCombatMetricKey = monsterList.get(0);
        return MonsterHelper.getEncounter(monsterList.get(0));
    }

    public MonsterGroup getEliteMonsterForRoomCreation() {
        if (eliteMonsterList.isEmpty()) {
            generateElites(10);
        }
        logger.info("ELITE: " + eliteMonsterList.get(0));
        lastCombatMetricKey = eliteMonsterList.get(0);
        return MonsterHelper.getEncounter(eliteMonsterList.get(0));
    }

    public static AbstractEvent generateEvent(Random rng) {
        if (rng.random(1.0f) >= shrineChance) {
            AbstractEvent retVal = getEvent(rng);
            if (retVal == null) {
                return getShrine(rng);
            }
            return retVal;
        } else if (!shrineList.isEmpty() || !specialOneTimeEventList.isEmpty()) {
            return getShrine(rng);
        } else {
            if (!eventList.isEmpty()) {
                return getEvent(rng);
            }
            logger.info("No events or shrines left");
            return null;
        }
    }

    public static AbstractEvent getShrine(Random rng) {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.addAll(shrineList);
        Iterator<String> it = specialOneTimeEventList.iterator();
        while (it.hasNext()) {
            String e = it.next();
            char c = 65535;
            switch (e.hashCode()) {
                case -2022548400:
                    if (e.equals(Nloth.ID)) {
                        c = 5;
                        break;
                    }
                    break;
                case -1699225493:
                    if (e.equals(FaceTrader.ID)) {
                        c = 3;
                        break;
                    }
                    break;
                case -207216254:
                    if (e.equals(TheJoust.ID)) {
                        c = 6;
                        break;
                    }
                    break;
                case 591082013:
                    if (e.equals(Duplicator.ID)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1051874140:
                    if (e.equals(SecretPortal.ID)) {
                        c = '\b';
                        break;
                    }
                    break;
                case 1088076555:
                    if (e.equals(Designer.ID)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1121505076:
                    if (e.equals(KnowingSkull.ID)) {
                        c = 4;
                        break;
                    }
                    break;
                case 1167999560:
                    if (e.equals(WomanInBlue.ID)) {
                        c = 7;
                        break;
                    }
                    break;
                case 1917982011:
                    if (e.equals(FountainOfCurseRemoval.ID)) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    if (!player.isCursed()) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 1:
                    if (id.equals(TheCity.ID) || id.equals(TheBeyond.ID)) {
                        if (player.gold < 75) {
                            break;
                        } else {
                            tmp.add(e);
                            break;
                        }
                    } else {
                        break;
                    }
                case 2:
                    if (!id.equals(TheCity.ID) && !id.equals(TheBeyond.ID)) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 3:
                    if (!id.equals(TheCity.ID) && !id.equals(Exordium.ID)) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 4:
                    if (id.equals(TheCity.ID) && player.currentHealth > 12) {
                        tmp.add(e);
                        break;
                    }
                    break;
                case 5:
                    if (id.equals(TheCity.ID) || id.equals(TheCity.ID)) {
                        if (player.relics.size() < 2) {
                            break;
                        } else {
                            tmp.add(e);
                            break;
                        }
                    } else {
                        break;
                    }
                case 6:
                    if (id.equals(TheCity.ID) && player.gold >= 50) {
                        tmp.add(e);
                        break;
                    }
                    break;
                case 7:
                    if (player.gold < 50) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case '\b':
                    if (CardCrawlGame.playtime >= 800.0f && id.equals(TheBeyond.ID)) {
                        tmp.add(e);
                        break;
                    }
                    break;
                default:
                    tmp.add(e);
                    break;
            }
        }
        String tmpKey = tmp.get(rng.random(tmp.size() - 1));
        shrineList.remove(tmpKey);
        specialOneTimeEventList.remove(tmpKey);
        logger.info("Removed event: " + tmpKey + " from pool.");
        return EventHelper.getEvent(tmpKey);
    }

    public static AbstractEvent getEvent(Random rng) {
        ArrayList<String> tmp = new ArrayList<>();
        Iterator<String> it = eventList.iterator();
        while (it.hasNext()) {
            String e = it.next();
            char c = 65535;
            switch (e.hashCode()) {
                case -1914822917:
                    if (e.equals(Mushrooms.ID)) {
                        c = 1;
                        break;
                    }
                    break;
                case -308228690:
                    if (e.equals(Colosseum.ID)) {
                        c = 5;
                        break;
                    }
                    break;
                case 236474855:
                    if (e.equals(MoaiHead.ID)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1824060574:
                    if (e.equals(DeadAdventurer.ID)) {
                        c = 0;
                        break;
                    }
                    break;
                case 1962578239:
                    if (e.equals(Cleric.ID)) {
                        c = 3;
                        break;
                    }
                    break;
                case 1985970164:
                    if (e.equals(Beggar.ID)) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    if (floorNum <= 6) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 1:
                    if (floorNum <= 6) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 2:
                    if (!player.hasRelic("Golden Idol") && player.currentHealth / player.maxHealth > 0.5f) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 3:
                    if (player.gold < 35) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 4:
                    if (player.gold < 75) {
                        break;
                    } else {
                        tmp.add(e);
                        break;
                    }
                case 5:
                    if (currMapNode != null && currMapNode.y > map.size() / 2) {
                        tmp.add(e);
                        break;
                    }
                    break;
                default:
                    tmp.add(e);
                    break;
            }
        }
        if (tmp.isEmpty()) {
            return getShrine(rng);
        }
        String tmpKey = tmp.get(rng.random(tmp.size() - 1));
        eventList.remove(tmpKey);
        logger.info("Removed event: " + tmpKey + " from pool.");
        return EventHelper.getEvent(tmpKey);
    }

    public MonsterGroup getBoss() {
        lastCombatMetricKey = bossKey;
        dungeonMapScreen.map.atBoss = true;
        return MonsterHelper.getEncounter(bossKey);
    }

    public void update() {
        if (!CardCrawlGame.stopClock) {
            CardCrawlGame.playtime += Gdx.graphics.getDeltaTime();
        }
        if (CardCrawlGame.screenTimer > 0.0f) {
            InputHelper.justClickedLeft = false;
            CInputActionSet.select.unpress();
        }
        topPanel.update();
        dynamicBanner.update();
        updateFading();
        currMapNode.room.updateObjects();
        if (isScreenUp) {
            topGradientColor.a = MathHelper.fadeLerpSnap(topGradientColor.a, 0.25f);
            botGradientColor.a = MathHelper.fadeLerpSnap(botGradientColor.a, 0.25f);
        } else {
            topGradientColor.a = MathHelper.fadeLerpSnap(topGradientColor.a, 0.1f);
            botGradientColor.a = MathHelper.fadeLerpSnap(botGradientColor.a, 0.1f);
        }
        switch (screen) {
            case NO_INTERACT:
            case NONE:
                dungeonMapScreen.update();
                currMapNode.room.update();
                scene.update();
                currMapNode.room.eventControllerInput();
                break;
            case FTUE:
                ftue.update();
                InputHelper.justClickedRight = false;
                InputHelper.justClickedLeft = false;
                currMapNode.room.update();
                break;
            case MASTER_DECK_VIEW:
                deckViewScreen.update();
                break;
            case GAME_DECK_VIEW:
                gameDeckViewScreen.update();
                break;
            case DISCARD_VIEW:
                discardPileViewScreen.update();
                break;
            case EXHAUST_VIEW:
                exhaustPileViewScreen.update();
                break;
            case SETTINGS:
                settingsScreen.update();
                break;
            case INPUT_SETTINGS:
                inputSettingsScreen.update();
                break;
            case MAP:
                dungeonMapScreen.update();
                break;
            case GRID:
                gridSelectScreen.update();
                if (PeekButton.isPeeking) {
                    currMapNode.room.update();
                    break;
                }
                break;
            case CARD_REWARD:
                cardRewardScreen.update();
                if (PeekButton.isPeeking) {
                    currMapNode.room.update();
                    break;
                }
                break;
            case COMBAT_REWARD:
                combatRewardScreen.update();
                break;
            case BOSS_REWARD:
                bossRelicScreen.update();
                currMapNode.room.update();
                break;
            case HAND_SELECT:
                handCardSelectScreen.update();
                currMapNode.room.update();
                break;
            case SHOP:
                shopScreen.update();
                break;
            case DEATH:
                deathScreen.update();
                break;
            case VICTORY:
                victoryScreen.update();
                break;
            case UNLOCK:
                unlockScreen.update();
                break;
            case NEOW_UNLOCK:
                gUnlockScreen.update();
                break;
            case CREDITS:
                creditsScreen.update();
                break;
            case DOOR_UNLOCK:
                CardCrawlGame.mainMenuScreen.doorUnlockScreen.update();
                break;
            default:
                logger.info("ERROR: UNKNOWN SCREEN TO UPDATE: " + screen.name());
                break;
        }
        turnPhaseEffectActive = false;
        Iterator<AbstractGameEffect> i = topLevelEffects.iterator();
        while (i.hasNext()) {
            AbstractGameEffect e = i.next();
            e.update();
            if (e.isDone) {
                i.remove();
            }
        }
        Iterator<AbstractGameEffect> i2 = effectList.iterator();
        while (i2.hasNext()) {
            AbstractGameEffect e2 = i2.next();
            e2.update();
            if (e2 instanceof PlayerTurnEffect) {
                turnPhaseEffectActive = true;
            }
            if (e2.isDone) {
                i2.remove();
            }
        }
        Iterator<AbstractGameEffect> i3 = effectsQueue.iterator();
        while (i3.hasNext()) {
            AbstractGameEffect e3 = i3.next();
            effectList.add(e3);
            i3.remove();
        }
        Iterator<AbstractGameEffect> i4 = topLevelEffectsQueue.iterator();
        while (i4.hasNext()) {
            AbstractGameEffect e4 = i4.next();
            topLevelEffects.add(e4);
            i4.remove();
        }
        overlayMenu.update();
    }

    public void render(SpriteBatch sb) {
        switch (rs) {
            case NORMAL:
                scene.renderCombatRoomBg(sb);
                break;
            case CAMPFIRE:
                scene.renderCampfireRoom(sb);
                renderLetterboxGradient(sb);
                break;
            case EVENT:
                scene.renderEventRoom(sb);
                break;
        }
        Iterator<AbstractGameEffect> it = effectList.iterator();
        while (it.hasNext()) {
            AbstractGameEffect e = it.next();
            if (e.renderBehind) {
                e.render(sb);
            }
        }
        currMapNode.room.render(sb);
        if (rs == RenderScene.NORMAL) {
            scene.renderCombatRoomFg(sb);
        }
        if (rs != RenderScene.CAMPFIRE) {
            renderLetterboxGradient(sb);
        }
        AbstractRoom room = getCurrRoom();
        if ((room instanceof EventRoom) || (room instanceof NeowRoom) || (room instanceof VictoryRoom)) {
            room.renderEventTexts(sb);
        }
        Iterator<AbstractGameEffect> it2 = effectList.iterator();
        while (it2.hasNext()) {
            AbstractGameEffect e2 = it2.next();
            if (!e2.renderBehind) {
                e2.render(sb);
            }
        }
        overlayMenu.render(sb);
        overlayMenu.renderBlackScreen(sb);
        switch (screen) {
            case NO_INTERACT:
            case FTUE:
                break;
            case NONE:
                dungeonMapScreen.render(sb);
                break;
            case MASTER_DECK_VIEW:
                deckViewScreen.render(sb);
                break;
            case GAME_DECK_VIEW:
                gameDeckViewScreen.render(sb);
                break;
            case DISCARD_VIEW:
                discardPileViewScreen.render(sb);
                break;
            case EXHAUST_VIEW:
                exhaustPileViewScreen.render(sb);
                break;
            case SETTINGS:
                settingsScreen.render(sb);
                break;
            case INPUT_SETTINGS:
                inputSettingsScreen.render(sb);
                break;
            case MAP:
                dungeonMapScreen.render(sb);
                break;
            case GRID:
                gridSelectScreen.render(sb);
                break;
            case CARD_REWARD:
                cardRewardScreen.render(sb);
                break;
            case COMBAT_REWARD:
                combatRewardScreen.render(sb);
                break;
            case BOSS_REWARD:
                bossRelicScreen.render(sb);
                break;
            case HAND_SELECT:
                handCardSelectScreen.render(sb);
                break;
            case SHOP:
                shopScreen.render(sb);
                break;
            case DEATH:
                deathScreen.render(sb);
                break;
            case VICTORY:
                victoryScreen.render(sb);
                break;
            case UNLOCK:
                unlockScreen.render(sb);
                break;
            case NEOW_UNLOCK:
                gUnlockScreen.render(sb);
                break;
            case CREDITS:
                creditsScreen.render(sb);
                break;
            case DOOR_UNLOCK:
                CardCrawlGame.mainMenuScreen.doorUnlockScreen.render(sb);
                break;
            default:
                logger.info("ERROR: UNKNOWN SCREEN TO RENDER: " + screen.name());
                break;
        }
        if (screen != CurrentScreen.UNLOCK) {
            sb.setColor(topGradientColor);
            if (!Settings.hideTopBar) {
                sb.draw(ImageMaster.SCROLL_GRADIENT, 0.0f, Settings.HEIGHT - (128.0f * Settings.scale), Settings.WIDTH, 64.0f * Settings.scale);
            }
            sb.setColor(botGradientColor);
            if (!Settings.hideTopBar) {
                sb.draw(ImageMaster.SCROLL_GRADIENT, 0.0f, 64.0f * Settings.scale, Settings.WIDTH, (-64.0f) * Settings.scale);
            }
        }
        if (screen == CurrentScreen.FTUE) {
            ftue.render(sb);
        }
        overlayMenu.cancelButton.render(sb);
        dynamicBanner.render(sb);
        if (screen != CurrentScreen.UNLOCK) {
            topPanel.render(sb);
        }
        currMapNode.room.renderAboveTopPanel(sb);
        Iterator<AbstractGameEffect> it3 = topLevelEffects.iterator();
        while (it3.hasNext()) {
            AbstractGameEffect e3 = it3.next();
            if (!e3.renderBehind) {
                e3.render(sb);
            }
        }
        sb.setColor(fadeColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
    }

    private void renderLetterboxGradient(SpriteBatch sb) {
    }

    public void updateFading() {
        if (isFadingIn) {
            fadeTimer -= Gdx.graphics.getDeltaTime();
            fadeColor.a = Interpolation.fade.apply(0.0f, 1.0f, fadeTimer / 0.8f);
            if (fadeTimer < 0.0f) {
                isFadingIn = false;
                fadeColor.a = 0.0f;
                fadeTimer = 0.0f;
            }
        } else if (isFadingOut) {
            fadeTimer -= Gdx.graphics.getDeltaTime();
            fadeColor.a = Interpolation.fade.apply(1.0f, 0.0f, fadeTimer / 0.8f);
            if (fadeTimer < 0.0f) {
                fadeTimer = 0.0f;
                isFadingOut = false;
                fadeColor.a = 1.0f;
                if (!isDungeonBeaten) {
                    nextRoomTransition();
                }
            }
        }
    }

    public static void closeCurrentScreen() {
        PeekButton.isPeeking = false;
        if (previousScreen == screen) {
            previousScreen = null;
        }
        switch (screen) {
            case FTUE:
                genericScreenOverlayReset();
                break;
            case MASTER_DECK_VIEW:
                overlayMenu.cancelButton.hide();
                genericScreenOverlayReset();
                Iterator<AbstractCard> it = player.masterDeck.group.iterator();
                while (it.hasNext()) {
                    AbstractCard c = it.next();
                    c.unhover();
                    c.untip();
                }
                break;
            case GAME_DECK_VIEW:
                overlayMenu.cancelButton.hide();
                genericScreenOverlayReset();
                break;
            case DISCARD_VIEW:
                overlayMenu.cancelButton.hide();
                genericScreenOverlayReset();
                Iterator<AbstractCard> it2 = player.discardPile.group.iterator();
                while (it2.hasNext()) {
                    AbstractCard c2 = it2.next();
                    c2.drawScale = 0.12f;
                    c2.targetDrawScale = 0.12f;
                    c2.teleportToDiscardPile();
                    c2.darken(true);
                    c2.unhover();
                }
                break;
            case EXHAUST_VIEW:
                overlayMenu.cancelButton.hide();
                genericScreenOverlayReset();
                break;
            case SETTINGS:
                overlayMenu.cancelButton.hide();
                genericScreenOverlayReset();
                settingsScreen.abandonPopup.hide();
                settingsScreen.exitPopup.hide();
                break;
            case INPUT_SETTINGS:
                overlayMenu.cancelButton.hide();
                genericScreenOverlayReset();
                settingsScreen.abandonPopup.hide();
                settingsScreen.exitPopup.hide();
                break;
            case MAP:
                genericScreenOverlayReset();
                dungeonMapScreen.close();
                if (!firstRoomChosen && nextRoom != null && !dungeonMapScreen.dismissable) {
                    firstRoomChosen = true;
                    firstRoomLogic();
                    break;
                }
                break;
            case GRID:
                genericScreenOverlayReset();
                if (!combatRewardScreen.rewards.isEmpty()) {
                    previousScreen = CurrentScreen.COMBAT_REWARD;
                    break;
                }
                break;
            case CARD_REWARD:
                overlayMenu.cancelButton.hide();
                dynamicBanner.hide();
                genericScreenOverlayReset();
                if (!screenSwap) {
                    cardRewardScreen.onClose();
                    break;
                }
                break;
            case COMBAT_REWARD:
                dynamicBanner.hide();
                genericScreenOverlayReset();
                break;
            case BOSS_REWARD:
                genericScreenOverlayReset();
                dynamicBanner.hide();
                break;
            case HAND_SELECT:
                genericScreenOverlayReset();
                overlayMenu.showCombatPanels();
                break;
            case SHOP:
                CardCrawlGame.sound.play("SHOP_CLOSE");
                genericScreenOverlayReset();
                overlayMenu.cancelButton.hide();
                break;
            case DEATH:
            case VICTORY:
            case UNLOCK:
            case CREDITS:
            case DOOR_UNLOCK:
            default:
                logger.info("UNSPECIFIED CASE: " + screen.name());
                break;
            case NEOW_UNLOCK:
                genericScreenOverlayReset();
                CardCrawlGame.sound.stop("UNLOCK_SCREEN", gUnlockScreen.id);
                break;
            case TRANSFORM:
                CardCrawlGame.sound.play("ATTACK_MAGIC_SLOW_1");
                genericScreenOverlayReset();
                overlayMenu.cancelButton.hide();
                break;
        }
        if (previousScreen == null) {
            screen = CurrentScreen.NONE;
        } else if (screenSwap) {
            screenSwap = false;
        } else {
            screen = previousScreen;
            previousScreen = null;
            if (getCurrRoom().rewardTime) {
                previousScreen = CurrentScreen.COMBAT_REWARD;
            }
            isScreenUp = true;
            openPreviousScreen(screen);
        }
    }

    private static void openPreviousScreen(CurrentScreen s) {
        switch (s) {
            case MASTER_DECK_VIEW:
                deckViewScreen.open();
                return;
            case GAME_DECK_VIEW:
                gameDeckViewScreen.reopen();
                return;
            case DISCARD_VIEW:
                discardPileViewScreen.reopen();
                return;
            case EXHAUST_VIEW:
                exhaustPileViewScreen.reopen();
                return;
            case SETTINGS:
            case INPUT_SETTINGS:
            case UNLOCK:
            default:
                return;
            case MAP:
                if (dungeonMapScreen.dismissable) {
                    overlayMenu.cancelButton.show(DungeonMapScreen.TEXT[1]);
                    return;
                } else {
                    overlayMenu.cancelButton.hide();
                    return;
                }
            case GRID:
                overlayMenu.hideBlackScreen();
                if (gridSelectScreen.isJustForConfirming) {
                    dynamicBanner.appear();
                }
                gridSelectScreen.reopen();
                return;
            case CARD_REWARD:
                cardRewardScreen.reopen();
                if (cardRewardScreen.rItem != null) {
                    previousScreen = CurrentScreen.COMBAT_REWARD;
                    return;
                }
                return;
            case COMBAT_REWARD:
                combatRewardScreen.reopen();
                return;
            case BOSS_REWARD:
                bossRelicScreen.reopen();
                return;
            case HAND_SELECT:
                overlayMenu.hideBlackScreen();
                handCardSelectScreen.reopen();
                return;
            case SHOP:
                shopScreen.open();
                return;
            case DEATH:
                deathScreen.reopen();
                return;
            case VICTORY:
                victoryScreen.reopen();
                return;
            case NEOW_UNLOCK:
                gUnlockScreen.reOpen();
                return;
        }
    }

    private static void genericScreenOverlayReset() {
        if (previousScreen == null) {
            if (player.isDead) {
                previousScreen = CurrentScreen.DEATH;
            } else {
                isScreenUp = false;
                overlayMenu.hideBlackScreen();
            }
        }
        if (getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !player.isDead) {
            overlayMenu.showCombatPanels();
        }
    }

    public static void fadeIn() {
        if (fadeColor.a != 1.0f) {
            logger.info("WARNING: Attempting to fade in even though screen is not black");
        }
        isFadingIn = true;
        if (Settings.FAST_MODE) {
            fadeTimer = 0.001f;
        } else {
            fadeTimer = 0.8f;
        }
    }

    public static void fadeOut() {
        if (fadeTimer == 0.0f) {
            if (fadeColor.a != 0.0f) {
                logger.info("WARNING: Attempting to fade out even though screen is not transparent");
            }
            isFadingOut = true;
            if (Settings.FAST_MODE) {
                fadeTimer = 0.001f;
            } else {
                fadeTimer = 0.8f;
            }
        }
    }

    public static void dungeonTransitionSetup() {
        actNum++;
        if (cardRng.counter > 0 && cardRng.counter < 250) {
            cardRng.setCounter(250);
        } else if (cardRng.counter > 250 && cardRng.counter < 500) {
            cardRng.setCounter(500);
        } else if (cardRng.counter > 500 && cardRng.counter < 750) {
            cardRng.setCounter(750);
        }
        logger.info("CardRng Counter: " + cardRng.counter);
        topPanel.unhoverHitboxes();
        pathX.clear();
        pathY.clear();
        EventHelper.resetProbabilities();
        eventList.clear();
        shrineList.clear();
        monsterList.clear();
        eliteMonsterList.clear();
        bossList.clear();
        AbstractRoom.blizzardPotionMod = 0;
        if (ascensionLevel >= 5) {
            player.heal(MathUtils.round((player.maxHealth - player.currentHealth) * 0.75f), false);
        } else {
            player.heal(player.maxHealth, false);
        }
        if (floorNum > 1) {
            topPanel.panelHealEffect();
        }
        if (floorNum <= 1 && (CardCrawlGame.dungeon instanceof Exordium)) {
            if (ascensionLevel >= 14) {
                player.decreaseMaxHealth(player.getAscensionMaxHPLoss());
            }
            if (ascensionLevel >= 6) {
                player.currentHealth = MathUtils.round(player.maxHealth * 0.9f);
            }
            if (ascensionLevel >= 10) {
                player.masterDeck.addToTop(new AscendersBane());
                UnlockTracker.markCardAsSeen(AscendersBane.ID);
            }
            CardCrawlGame.playtime = 0.0f;
        }
        dungeonMapScreen.map.atBoss = false;
    }

    public static void reset() {
        logger.info("Resetting variables...");
        CardCrawlGame.resetScoreVars();
        ModHelper.setModsFalse();
        floorNum = 0;
        actNum = 0;
        if (!(currMapNode == null || getCurrRoom() == null)) {
            getCurrRoom().dispose();
            if (getCurrRoom().monsters != null) {
                Iterator<AbstractMonster> it = getCurrRoom().monsters.monsters.iterator();
                while (it.hasNext()) {
                    AbstractMonster m = it.next();
                    m.dispose();
                }
            }
        }
        currMapNode = null;
        shrineList.clear();
        relicsToRemoveOnStart.clear();
        previousScreen = null;
        actionManager.clear();
        actionManager.clearNextRoomCombatActions();
        combatRewardScreen.clear();
        cardRewardScreen.reset();
        if (dungeonMapScreen != null) {
            dungeonMapScreen.closeInstantly();
        }
        effectList.clear();
        effectsQueue.clear();
        topLevelEffectsQueue.clear();
        topLevelEffects.clear();
        cardBlizzRandomizer = cardBlizzStartOffset;
        if (player != null) {
            player.relics.clear();
        }
        rs = RenderScene.NORMAL;
        blightPool.clear();
    }

    protected void removeRelicFromPool(ArrayList<String> pool, String name2) {
        Iterator<String> i = pool.iterator();
        while (i.hasNext()) {
            String s = i.next();
            if (s.equals(name2)) {
                i.remove();
                logger.info("Relic" + s + " removed from relic pool.");
            }
        }
    }

    public static void onModifyPower() {
        if (player != null) {
            player.hand.applyPowers();
            if (player.hasPower(FocusPower.POWER_ID)) {
                Iterator<AbstractOrb> it = player.orbs.iterator();
                while (it.hasNext()) {
                    AbstractOrb o = it.next();
                    o.updateDescription();
                }
            }
        }
        if (getCurrRoom().monsters != null) {
            Iterator<AbstractMonster> it2 = getCurrRoom().monsters.monsters.iterator();
            while (it2.hasNext()) {
                AbstractMonster m = it2.next();
                m.applyPowers();
            }
        }
    }

    public void checkForPactAchievement() {
        if (player != null && player.exhaustPile.size() >= 20) {
            UnlockTracker.unlockAchievement(AchievementGrid.THE_PACT_KEY);
        }
    }

    public void loadSave(SaveFile saveFile) {
        floorNum = saveFile.floor_num;
        actNum = saveFile.act_num;
        Settings.seed = Long.valueOf(saveFile.seed);
        loadSeeds(saveFile);
        monsterList = saveFile.monster_list;
        eliteMonsterList = saveFile.elite_monster_list;
        bossList = saveFile.boss_list;
        setBoss(saveFile.boss);
        commonRelicPool = saveFile.common_relics;
        uncommonRelicPool = saveFile.uncommon_relics;
        rareRelicPool = saveFile.rare_relics;
        shopRelicPool = saveFile.shop_relics;
        bossRelicPool = saveFile.boss_relics;
        pathX = saveFile.path_x;
        pathY = saveFile.path_y;
        bossCount = saveFile.spirit_count;
        eventList = saveFile.event_list;
        specialOneTimeEventList = saveFile.one_time_event_list;
        EventHelper.setChances(saveFile.event_chances);
        AbstractRoom.blizzardPotionMod = saveFile.potion_chance;
        ShopScreen.purgeCost = saveFile.purgeCost;
        CardHelper.obtainedCards = saveFile.obtained_cards;
        if (saveFile.daily_mods != null) {
            ModHelper.setMods(saveFile.daily_mods);
        }
    }

    public static AbstractBlight getBlight(String targetID) {
        Iterator<AbstractBlight> it = blightPool.iterator();
        while (it.hasNext()) {
            AbstractBlight b = it.next();
            if (b.blightID.equals(targetID)) {
                return b;
            }
        }
        return null;
    }
}