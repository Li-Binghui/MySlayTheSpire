package com.megacrit.cardcrawl.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.cards.green.Strike_Green;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.cards.purple.Defend_Watcher;
import com.megacrit.cardcrawl.cards.purple.Eruption;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.Chimera;
import com.megacrit.cardcrawl.daily.mods.ControlledChaos;
import com.megacrit.cardcrawl.daily.mods.CursedRun;
import com.megacrit.cardcrawl.daily.mods.Draft;
import com.megacrit.cardcrawl.daily.mods.Insanity;
import com.megacrit.cardcrawl.daily.mods.Lethality;
import com.megacrit.cardcrawl.daily.mods.SealedDeck;
import com.megacrit.cardcrawl.daily.mods.Shiny;
import com.megacrit.cardcrawl.daily.mods.Terminal;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.SurroundedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.CursedKey;
import com.megacrit.cardcrawl.relics.DarkstonePeriapt;
import com.megacrit.cardcrawl.relics.DuVuDoll;
import com.megacrit.cardcrawl.relics.Ectoplasm;
import com.megacrit.cardcrawl.relics.FrozenEye;
import com.megacrit.cardcrawl.relics.GoldPlatedCables;
import com.megacrit.cardcrawl.relics.LizardTail;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import com.megacrit.cardcrawl.relics.Necronomicon;
import com.megacrit.cardcrawl.relics.SlaversCollar;
import com.megacrit.cardcrawl.relics.deprecated.DEPRECATED_DarkCore;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.stats.AchievementGrid;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.ui.MultiPageFtue;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.cardManip.CardDisappearEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/characters/AbstractPlayer.class */
public abstract class AbstractPlayer extends AbstractCreature {
    public PlayerClass chosenClass;
    public int gameHandSize;
    public int masterHandSize;
    public int startingMaxHP;
    public EnergyManager energy;
    public String title;
    public int masterMaxOrbs;
    public int maxOrbs;
    private static final float HOVER_ENEMY_WAIT_TIME = 1.0f;
    public Texture img;
    public Texture shoulderImg;
    public Texture shoulder2Img;
    public Texture corpseImg;
    private float arrowScale;
    private float arrowX;
    private float arrowY;
    private static final float ARROW_TARGET_SCALE = 1.2f;
    private static final int TARGET_ARROW_W = 256;
    private static final int SEGMENTS = 20;
    private static final Logger logger = LogManager.getLogger(AbstractPlayer.class.getName());
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Player Tips");
    public static final String[] MSG = tutorialStrings.TEXT;
    public static final String[] LABEL = tutorialStrings.LABEL;
    public static int poisonKillCount = 0;
    public static ArrayList<String> customMods = null;
    private static final Color ARROW_COLOR = new Color(1.0f, 0.2f, 0.3f, 1.0f);
    public static final float HOVER_CARD_Y_POSITION = 210.0f * Settings.scale;
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractPlayer");
    public CardGroup masterDeck = new CardGroup(CardGroup.CardGroupType.MASTER_DECK);
    public CardGroup drawPile = new CardGroup(CardGroup.CardGroupType.DRAW_PILE);
    public CardGroup hand = new CardGroup(CardGroup.CardGroupType.HAND);
    public CardGroup discardPile = new CardGroup(CardGroup.CardGroupType.DISCARD_PILE);
    public CardGroup exhaustPile = new CardGroup(CardGroup.CardGroupType.EXHAUST_PILE);
    public CardGroup limbo = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    public ArrayList<AbstractRelic> relics = new ArrayList<>();
    public ArrayList<AbstractBlight> blights = new ArrayList<>();
    public int potionSlots = 3;
    public ArrayList<AbstractPotion> potions = new ArrayList<>();
    public boolean isEndingTurn = false;
    public boolean viewingRelics = false;
    public boolean inspectMode = false;
    public Hitbox inspectHb = null;
    public int damagedThisCombat = 0;
    public ArrayList<AbstractOrb> orbs = new ArrayList<>();
    public AbstractStance stance = new NeutralStance();
    @Deprecated
    public int cardsPlayedThisTurn = 0;
    private boolean isHoveringCard = false;
    public boolean isHoveringDropZone = false;
    private float hoverStartLine = 0.0f;
    private boolean passedHesitationLine = false;
    public AbstractCard hoveredCard = null;
    public AbstractCard toHover = null;
    public AbstractCard cardInUse = null;
    public boolean isDraggingCard = false;
    private boolean isUsingClickDragControl = false;
    private float clickDragTimer = 0.0f;
    public boolean inSingleTargetMode = false;
    private AbstractMonster hoveredMonster = null;
    public float hoverEnemyWaitTimer = 0.0f;
    public boolean isInKeyboardMode = false;
    private boolean skipMouseModeOnce = false;
    private int keyboardCardIndex = -1;
    private int touchscreenInspectCount = 0;
    private float arrowScaleTimer = 0.0f;
    public boolean endTurnQueued = false;
    private Vector2[] points = new Vector2[20];
    private Vector2 controlPoint = new Vector2();
    private Vector2 arrowTmp = new Vector2();
    private Vector2 startArrowVector = new Vector2();
    private Vector2 endArrowVector = new Vector2();
    private boolean renderCorpse = false;

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/characters/AbstractPlayer$PlayerClass.class */
    public enum PlayerClass {
        IRONCLAD,
        THE_SILENT,
        DEFECT,
        WATCHER
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/characters/AbstractPlayer$RHoverType.class */
    private enum RHoverType {
        RELIC,
        BLIGHT
    }

    public abstract String getPortraitImageName();

    public abstract ArrayList<String> getStartingDeck();

    public abstract ArrayList<String> getStartingRelics();

    public abstract CharSelectInfo getLoadout();

    public abstract String getTitle(PlayerClass playerClass);

    public abstract AbstractCard.CardColor getCardColor();

    public abstract Color getCardRenderColor();

    public abstract String getAchievementKey();

    public abstract ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> arrayList);

    public abstract AbstractCard getStartCardForEvent();

    public abstract Color getCardTrailColor();

    public abstract String getLeaderboardCharacterName();

    public abstract Texture getEnergyImage();

    public abstract int getAscensionMaxHPLoss();

    public abstract BitmapFont getEnergyNumFont();

    public abstract void renderOrb(SpriteBatch spriteBatch, boolean z, float f, float f2);

    public abstract void updateOrb(int i);

    public abstract Prefs getPrefs();

    public abstract void loadPrefs();

    public abstract CharStat getCharStat();

    public abstract int getUnlockedCardCount();

    public abstract int getSeenCardCount();

    public abstract int getCardCount();

    public abstract boolean saveFileExists();

    public abstract String getWinStreakKey();

    public abstract String getLeaderboardWinStreakKey();

    public abstract void renderStatScreen(SpriteBatch spriteBatch, float f, float f2);

    public abstract void doCharSelectScreenSelectEffect();

    public abstract String getCustomModeCharacterButtonSoundKey();

    public abstract Texture getCustomModeCharacterButtonImage();

    public abstract CharacterStrings getCharacterString();

    public abstract String getLocalizedCharacterName();

    public abstract void refreshCharStat();

    public abstract AbstractPlayer newInstance();

    public abstract TextureAtlas.AtlasRegion getOrb();

    public abstract String getSpireHeartText();

    public abstract Color getSlashAttackColor();

    public abstract AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect();

    public abstract String getVampireText();

    public AbstractPlayer(String name, PlayerClass setClass) {
        this.name = name;
        this.title = getTitle(setClass);
        this.drawX = Settings.WIDTH * 0.25f;
        this.drawY = AbstractDungeon.floorY;
        this.chosenClass = setClass;
        this.isPlayer = true;
        initializeStarterRelics(setClass);
        loadPrefs();
        if (AbstractDungeon.ascensionLevel >= 11) {
            this.potionSlots--;
        }
        this.potions.clear();
        for (int i = 0; i < this.potionSlots; i++) {
            this.potions.add(new PotionSlot(i));
        }
        for (int i2 = 0; i2 < this.points.length; i2++) {
            this.points[i2] = new Vector2();
        }
    }

    public String getSaveFilePath() {
        return SaveAndContinue.getPlayerSavePath(this.chosenClass);
    }

    public void dispose() {
        if (this.atlas != null) {
            this.atlas.dispose();
        }
        if (this.img != null) {
            this.img.dispose();
        }
        if (this.shoulderImg != null) {
            this.shoulderImg.dispose();
        }
        if (this.shoulder2Img != null) {
            this.shoulder2Img.dispose();
        }
        if (this.corpseImg != null) {
            this.corpseImg.dispose();
        }
    }

    public void adjustPotionPositions() {
        for (int i = 0; i < this.potions.size() - 1; i++) {
            this.potions.get(i).adjustPosition(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initializeClass(String imgUrl, String shoulder2ImgUrl, String shouldImgUrl, String corpseImgUrl, CharSelectInfo info, float hb_x, float hb_y, float hb_w, float hb_h, EnergyManager energy) {
        if (imgUrl != null) {
            this.img = ImageMaster.loadImage(imgUrl);
        }
        if (this.img != null) {
            this.atlas = null;
        }
        this.shoulderImg = ImageMaster.loadImage(shouldImgUrl);
        this.shoulder2Img = ImageMaster.loadImage(shoulder2ImgUrl);
        this.corpseImg = ImageMaster.loadImage(corpseImgUrl);
        if (Settings.isMobile) {
            hb_w *= 1.17f;
        }
        this.maxHealth = info.maxHp;
        this.startingMaxHP = this.maxHealth;
        this.currentHealth = info.currentHp;
        this.masterMaxOrbs = info.maxOrbs;
        this.energy = energy;
        this.masterHandSize = info.cardDraw;
        this.gameHandSize = this.masterHandSize;
        this.gold = info.gold;
        this.displayGold = this.gold;
        this.hb_h = hb_h * Settings.xScale;
        this.hb_w = hb_w * Settings.scale;
        this.hb_x = hb_x * Settings.scale;
        this.hb_y = hb_y * Settings.scale;
        this.hb = new Hitbox(this.hb_w, this.hb_h);
        this.healthHb = new Hitbox(this.hb.width, 72.0f * Settings.scale);
        refreshHitboxLocation();
    }

    public void initializeStarterDeck() {
        ArrayList<String> cards = getStartingDeck();
        boolean addBaseCards = true;
        if (ModHelper.isModEnabled(Draft.ID) || ModHelper.isModEnabled(Chimera.ID) || ModHelper.isModEnabled(SealedDeck.ID) || ModHelper.isModEnabled(Shiny.ID) || ModHelper.isModEnabled(Insanity.ID)) {
            addBaseCards = false;
        }
        if (ModHelper.isModEnabled(Chimera.ID)) {
            this.masterDeck.addToTop(new Bash());
            this.masterDeck.addToTop(new Survivor());
            this.masterDeck.addToTop(new Zap());
            this.masterDeck.addToTop(new Eruption());
            this.masterDeck.addToTop(new Strike_Red());
            this.masterDeck.addToTop(new Strike_Green());
            this.masterDeck.addToTop(new Strike_Blue());
            this.masterDeck.addToTop(new Defend_Red());
            this.masterDeck.addToTop(new Defend_Green());
            this.masterDeck.addToTop(new Defend_Watcher());
        }
        if (ModHelper.isModEnabled(Insanity.ID)) {
            for (int i = 0; i < 50; i++) {
                this.masterDeck.addToTop(AbstractDungeon.returnRandomCard().makeCopy());
            }
        }
        if (ModHelper.isModEnabled(Shiny.ID)) {
            CardGroup group = AbstractDungeon.getEachRare();
            Iterator<AbstractCard> it = group.group.iterator();
            while (it.hasNext()) {
                AbstractCard c = it.next();
                this.masterDeck.addToTop(c);
            }
        }
        if (addBaseCards) {
            Iterator<String> it2 = cards.iterator();
            while (it2.hasNext()) {
                String s = it2.next();
                this.masterDeck.addToTop(CardLibrary.getCard(this.chosenClass, s).makeCopy());
            }
        }
        Iterator<AbstractCard> it3 = this.masterDeck.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c2 = it3.next();
            UnlockTracker.markCardAsSeen(c2.cardID);
        }
    }

    protected void initializeStarterRelics(PlayerClass chosenClass) {
        ArrayList<String> relics = getStartingRelics();
        if (ModHelper.isModEnabled(CursedRun.ID)) {
            relics.clear();
            relics.add(CursedKey.ID);
            relics.add(DarkstonePeriapt.ID);
            relics.add(DuVuDoll.ID);
        }
        if (ModHelper.isModEnabled(ControlledChaos.ID)) {
            relics.add(FrozenEye.ID);
        }
        int index = 0;
        Iterator<String> it = relics.iterator();
        while (it.hasNext()) {
            String s = it.next();
            RelicLibrary.getRelic(s).makeCopy().instantObtain(this, index, false);
            index++;
        }
        AbstractDungeon.relicsToRemoveOnStart.addAll(relics);
    }

    public void combatUpdate() {
        if (this.cardInUse != null) {
            this.cardInUse.update();
        }
        this.limbo.update();
        this.exhaustPile.update();
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            p.updateParticles();
        }
        Iterator<AbstractOrb> it2 = this.orbs.iterator();
        while (it2.hasNext()) {
            AbstractOrb o = it2.next();
            o.update();
        }
        this.stance.update();
    }

    public void update() {
        updateControllerInput();
        this.hb.update();
        updateHealthBar();
        updatePowers();
        this.healthHb.update();
        updateReticle();
        this.tint.update();
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.EVENT) {
            Iterator<AbstractOrb> it = this.orbs.iterator();
            while (it.hasNext()) {
                AbstractOrb o = it.next();
                o.updateAnimation();
            }
        }
        updateEscapeAnimation();
    }

    private void updateControllerInput() {
        if (Settings.isControllerMode && !AbstractDungeon.topPanel.selectPotionMode && AbstractDungeon.topPanel.potionUi.isHidden) {
            boolean anyHovered = false;
            boolean orbHovered = false;
            int orbIndex = 0;
            Iterator<AbstractOrb> it = this.orbs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AbstractOrb o = it.next();
                if (o.hb.hovered) {
                    orbHovered = true;
                    break;
                }
                orbIndex++;
            }
            if (orbHovered && (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed())) {
                CInputActionSet.up.unpress();
                CInputActionSet.altUp.unpress();
                this.inspectMode = false;
                this.inspectHb = null;
                this.viewingRelics = true;
                if (!this.blights.isEmpty()) {
                    CInputHelper.setCursor(this.blights.get(0).hb);
                } else {
                    CInputHelper.setCursor(this.relics.get(0).hb);
                }
            } else if (orbHovered && (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed())) {
                int orbIndex2 = orbIndex + 1;
                if (orbIndex2 > this.orbs.size() - 1) {
                    orbIndex2 = 0;
                }
                this.inspectHb = this.orbs.get(orbIndex2).hb;
                Gdx.input.setCursorPosition((int) this.orbs.get(orbIndex2).hb.cX, Settings.HEIGHT - ((int) this.orbs.get(orbIndex2).hb.cY));
            } else if (orbHovered && (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed())) {
                int orbIndex3 = orbIndex - 1;
                if (orbIndex3 < 0) {
                    orbIndex3 = this.orbs.size() - 1;
                }
                this.inspectHb = this.orbs.get(orbIndex3).hb;
                Gdx.input.setCursorPosition((int) this.orbs.get(orbIndex3).hb.cX, Settings.HEIGHT - ((int) this.orbs.get(orbIndex3).hb.cY));
            } else if (!this.inspectMode || (!CInputActionSet.down.isJustPressed() && !CInputActionSet.altDown.isJustPressed())) {
                if (!this.inspectMode && ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp)) {
                    if (Gdx.input.getX() < Settings.WIDTH / 2.0f) {
                        this.inspectHb = this.hb;
                    } else if (!AbstractDungeon.getMonsters().monsters.isEmpty()) {
                        ArrayList<Hitbox> hbs = new ArrayList<>();
                        Iterator<AbstractMonster> it2 = AbstractDungeon.getMonsters().monsters.iterator();
                        while (it2.hasNext()) {
                            AbstractMonster m = it2.next();
                            if (!m.isDying && !m.isEscaping) {
                                hbs.add(m.hb);
                            }
                        }
                        if (!hbs.isEmpty()) {
                            this.inspectHb = hbs.get(0);
                        } else {
                            this.inspectHb = this.hb;
                        }
                    } else {
                        this.inspectHb = this.hb;
                    }
                    CInputHelper.setCursor(this.inspectHb);
                    this.inspectMode = true;
                    releaseCard();
                } else if (this.inspectMode && ((CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                    ArrayList<Hitbox> hbs2 = new ArrayList<>();
                    hbs2.add(this.hb);
                    Iterator<AbstractMonster> it3 = AbstractDungeon.getMonsters().monsters.iterator();
                    while (it3.hasNext()) {
                        AbstractMonster m2 = it3.next();
                        if (!m2.isDying && !m2.isEscaping) {
                            hbs2.add(m2.hb);
                        }
                    }
                    int index = 0;
                    Iterator<Hitbox> it4 = hbs2.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            break;
                        }
                        Hitbox h = it4.next();
                        h.update();
                        if (h.hovered) {
                            anyHovered = true;
                            break;
                        }
                        index++;
                    }
                    if (!anyHovered) {
                        CInputHelper.setCursor(hbs2.get(0));
                        this.inspectHb = hbs2.get(0);
                    } else {
                        int index2 = index + 1;
                        if (index2 > hbs2.size() - 1) {
                            index2 = 0;
                        }
                        CInputHelper.setCursor(hbs2.get(index2));
                        this.inspectHb = hbs2.get(index2);
                    }
                    this.inspectMode = true;
                    releaseCard();
                } else if (this.inspectMode && ((CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                    ArrayList<Hitbox> hbs3 = new ArrayList<>();
                    hbs3.add(this.hb);
                    Iterator<AbstractMonster> it5 = AbstractDungeon.getMonsters().monsters.iterator();
                    while (it5.hasNext()) {
                        AbstractMonster m3 = it5.next();
                        if (!m3.isDying && !m3.isEscaping) {
                            hbs3.add(m3.hb);
                        }
                    }
                    int index3 = 0;
                    Iterator<Hitbox> it6 = hbs3.iterator();
                    while (true) {
                        if (!it6.hasNext()) {
                            break;
                        } else if (it6.next().hovered) {
                            anyHovered = true;
                            break;
                        } else {
                            index3++;
                        }
                    }
                    if (!anyHovered) {
                        CInputHelper.setCursor(hbs3.get(hbs3.size() - 1));
                        this.inspectHb = hbs3.get(hbs3.size() - 1);
                    } else {
                        int index4 = index3 - 1;
                        if (index4 < 0) {
                            index4 = hbs3.size() - 1;
                        }
                        CInputHelper.setCursor(hbs3.get(index4));
                        this.inspectHb = hbs3.get(index4);
                    }
                    this.inspectMode = true;
                    releaseCard();
                } else if (!this.inspectMode) {
                } else {
                    if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                        CInputActionSet.up.unpress();
                        CInputActionSet.altUp.unpress();
                        if (orbHovered || this.orbs.isEmpty()) {
                            this.inspectMode = false;
                            this.inspectHb = null;
                            this.viewingRelics = true;
                            if (!this.blights.isEmpty()) {
                                CInputHelper.setCursor(this.blights.get(0).hb);
                            } else {
                                CInputHelper.setCursor(this.relics.get(0).hb);
                            }
                        } else {
                            CInputHelper.setCursor(this.orbs.get(0).hb);
                            this.inspectHb = this.orbs.get(0).hb;
                        }
                    }
                }
            } else if (orbHovered) {
                this.inspectHb = this.hb;
                CInputHelper.setCursor(this.inspectHb);
            } else {
                this.inspectMode = false;
                this.inspectHb = null;
                if (!this.hand.isEmpty() && !AbstractDungeon.actionManager.turnHasEnded) {
                    this.hoveredCard = this.hand.group.get(0);
                    hoverCardInHand(this.hoveredCard);
                    this.keyboardCardIndex = 0;
                }
            }
        }
    }

    public void updateViewRelicControls() {
        int index = 0;
        boolean anyHovered = false;
        RHoverType type = RHoverType.RELIC;
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AbstractRelic r = it.next();
            if (r.hb.hovered) {
                anyHovered = true;
                break;
            }
            index++;
        }
        if (!anyHovered) {
            index = 0;
            Iterator<AbstractBlight> it2 = this.blights.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                AbstractBlight b = it2.next();
                if (b.hb.hovered) {
                    anyHovered = true;
                    type = RHoverType.BLIGHT;
                    break;
                }
                index++;
            }
        }
        if (!anyHovered) {
            CInputHelper.setCursor(this.relics.get(0).hb);
        } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
            int index2 = index - 1;
            if (type == RHoverType.RELIC) {
                if (index2 < AbstractRelic.relicPage * AbstractRelic.MAX_RELICS_PER_PAGE) {
                    AbstractRelic.relicPage--;
                    if (AbstractRelic.relicPage < 0) {
                        if (this.relics.size() <= AbstractRelic.MAX_RELICS_PER_PAGE) {
                            AbstractRelic.relicPage = 0;
                        } else {
                            AbstractRelic.relicPage = this.relics.size() / AbstractRelic.MAX_RELICS_PER_PAGE;
                            AbstractDungeon.topPanel.adjustRelicHbs();
                        }
                        index2 = this.relics.size() - 1;
                    } else {
                        index2 = ((AbstractRelic.relicPage + 1) * AbstractRelic.MAX_RELICS_PER_PAGE) - 1;
                        AbstractDungeon.topPanel.adjustRelicHbs();
                    }
                }
                CInputHelper.setCursor(this.relics.get(index2).hb);
                return;
            }
            if (index2 < 0) {
                index2 = this.blights.size() - 1;
            }
            CInputHelper.setCursor(this.blights.get(index2).hb);
        } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
            int index3 = index + 1;
            if (type == RHoverType.RELIC) {
                if (index3 > this.relics.size() - 1) {
                    index3 = 0;
                } else if (index3 > ((AbstractRelic.relicPage + 1) * AbstractRelic.MAX_RELICS_PER_PAGE) - 1) {
                    AbstractRelic.relicPage++;
                    AbstractDungeon.topPanel.adjustRelicHbs();
                    index3 = AbstractRelic.relicPage * AbstractRelic.MAX_RELICS_PER_PAGE;
                }
                CInputHelper.setCursor(this.relics.get(index3).hb);
                return;
            }
            if (index3 > this.blights.size() - 1) {
                index3 = 0;
            }
            CInputHelper.setCursor(this.blights.get(index3).hb);
        } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
            CInputActionSet.up.unpress();
            if (type == RHoverType.RELIC) {
                this.viewingRelics = false;
                AbstractDungeon.topPanel.selectPotionMode = true;
                CInputHelper.setCursor(this.potions.get(0).hb);
                return;
            }
            CInputHelper.setCursor(this.relics.get(0).hb);
        } else if (CInputActionSet.cancel.isJustPressed()) {
            this.viewingRelics = false;
            Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
        } else if (!CInputActionSet.down.isJustPressed() && !CInputActionSet.altDown.isJustPressed()) {
        } else {
            if (type != RHoverType.RELIC) {
                CInputActionSet.down.unpress();
                CInputActionSet.altDown.unpress();
                this.viewingRelics = false;
                this.inspectMode = true;
                if (!this.orbs.isEmpty()) {
                    this.inspectHb = this.orbs.get(0).hb;
                } else {
                    this.inspectHb = this.hb;
                }
                CInputHelper.setCursor(this.inspectHb);
            } else if (this.blights.isEmpty()) {
                CInputActionSet.down.unpress();
                CInputActionSet.altDown.unpress();
                this.viewingRelics = false;
                this.inspectMode = true;
                if (!this.orbs.isEmpty()) {
                    this.inspectHb = this.orbs.get(0).hb;
                } else {
                    this.inspectHb = this.hb;
                }
                CInputHelper.setCursor(this.inspectHb);
            } else {
                CInputHelper.setCursor(this.blights.get(0).hb);
            }
        }
    }

    @Override // com.megacrit.cardcrawl.core.AbstractCreature
    public void loseGold(int goldAmount) {
        if (AbstractDungeon.getCurrRoom() instanceof ShopRoom) {
            Iterator<AbstractRelic> it = this.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onSpendGold();
            }
        }
        if (!(AbstractDungeon.getCurrRoom() instanceof ShopRoom) && AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            CardCrawlGame.sound.play("EVENT_PURCHASE");
        }
        if (goldAmount > 0) {
            this.gold -= goldAmount;
            if (this.gold < 0) {
                this.gold = 0;
            }
            Iterator<AbstractRelic> it2 = this.relics.iterator();
            while (it2.hasNext()) {
                AbstractRelic r2 = it2.next();
                r2.onLoseGold();
            }
            return;
        }
        logger.info("NEGATIVE MONEY???");
    }

    @Override // com.megacrit.cardcrawl.core.AbstractCreature
    public void gainGold(int amount) {
        if (hasRelic(Ectoplasm.ID)) {
            getRelic(Ectoplasm.ID).flash();
        } else if (amount <= 0) {
            logger.info("NEGATIVE MONEY???");
        } else {
            CardCrawlGame.goldGained += amount;
            this.gold += amount;
            Iterator<AbstractRelic> it = this.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onGainGold();
            }
        }
    }

    public void playDeathAnimation() {
        this.img = this.corpseImg;
        this.renderCorpse = true;
    }

    public boolean isCursed() {
        boolean cursed = false;
        Iterator<AbstractCard> it = this.masterDeck.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (!(c.type != AbstractCard.CardType.CURSE || c.cardID == Necronomicurse.ID || c.cardID == CurseOfTheBell.ID || c.cardID == AscendersBane.ID)) {
                cursed = true;
            }
        }
        return cursed;
    }

    public void updateInput() {
        if (!this.viewingRelics) {
            if (this.hoverEnemyWaitTimer > 0.0f) {
                this.hoverEnemyWaitTimer -= Gdx.graphics.getDeltaTime();
            }
            if (this.inSingleTargetMode) {
                updateSingleTargetInput();
                return;
            }
            int y = InputHelper.mY;
            boolean hMonster = false;
            Iterator<AbstractMonster> it = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AbstractMonster m = it.next();
                m.hb.update();
                if (m.hb.hovered && !m.isDying && !m.isEscaping && m.currentHealth > 0) {
                    hMonster = true;
                    break;
                }
            }
            boolean tmp = this.isHoveringDropZone;
            if (!Settings.isTouchScreen) {
                this.isHoveringDropZone = ((((float) y) > this.hoverStartLine || ((float) y) > 300.0f * Settings.scale) && ((float) y) < Settings.CARD_DROP_END_Y) || hMonster;
            } else {
                this.isHoveringDropZone = (((float) y) > 350.0f * Settings.scale && ((float) y) < Settings.CARD_DROP_END_Y) || hMonster;
            }
            if (!tmp && this.isHoveringDropZone && this.isDraggingCard) {
                this.hoveredCard.flash(Color.SKY.cpy());
                if (this.hoveredCard.showEvokeValue) {
                    if (this.hoveredCard.showEvokeOrbCount == 0) {
                        Iterator<AbstractOrb> it2 = this.orbs.iterator();
                        while (it2.hasNext()) {
                            AbstractOrb o = it2.next();
                            o.showEvokeValue();
                        }
                    } else {
                        int tmpShowCount = this.hoveredCard.showEvokeOrbCount;
                        int emptyCount = 0;
                        Iterator<AbstractOrb> it3 = this.orbs.iterator();
                        while (it3.hasNext()) {
                            AbstractOrb o2 = it3.next();
                            if (o2 instanceof EmptyOrbSlot) {
                                emptyCount++;
                            }
                        }
                        int tmpShowCount2 = tmpShowCount - emptyCount;
                        if (tmpShowCount2 > 0) {
                            Iterator<AbstractOrb> it4 = this.orbs.iterator();
                            while (it4.hasNext()) {
                                AbstractOrb o3 = it4.next();
                                o3.showEvokeValue();
                                tmpShowCount2--;
                                if (tmpShowCount2 <= 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (this.isDraggingCard && this.isHoveringDropZone && this.hoveredCard != null) {
                this.passedHesitationLine = true;
            }
            if (this.isDraggingCard && y < 50.0f * Settings.scale && this.passedHesitationLine) {
                if (Settings.isTouchScreen) {
                    InputHelper.moveCursorToNeutralPosition();
                }
                releaseCard();
                CardCrawlGame.sound.play("UI_CLICK_1");
            }
            updateFullKeyboardCardSelection();
            if (!this.isInKeyboardMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.topPanel.potionUi.targetMode || AbstractDungeon.isScreenUp) {
                if (this.hoveredCard == null && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NONE && !AbstractDungeon.topPanel.selectPotionMode) {
                    this.isHoveringCard = false;
                    if (this.toHover != null) {
                        this.hoveredCard = this.toHover;
                        this.toHover = null;
                    } else {
                        this.hoveredCard = this.hand.getHoveredCard();
                    }
                    if (this.hoveredCard != null) {
                        this.isHoveringCard = true;
                        this.hoveredCard.current_y = HOVER_CARD_Y_POSITION;
                        this.hoveredCard.target_y = HOVER_CARD_Y_POSITION;
                        this.hoveredCard.setAngle(0.0f, true);
                        this.hand.hoverCardPush(this.hoveredCard);
                    }
                }
            } else if (this.toHover != null) {
                releaseCard();
                this.hoveredCard = this.toHover;
                this.toHover = null;
                this.isHoveringCard = true;
                this.hoveredCard.current_y = HOVER_CARD_Y_POSITION;
                this.hoveredCard.target_y = HOVER_CARD_Y_POSITION;
                this.hoveredCard.setAngle(0.0f, true);
                this.hand.hoverCardPush(this.hoveredCard);
            }
            if (this.hoveredCard != null) {
                this.hoveredCard.drawScale = 1.0f;
                this.hoveredCard.targetDrawScale = 1.0f;
            }
            if (!this.isDraggingCard && hasRelic(Necronomicon.ID)) {
                getRelic(Necronomicon.ID).stopPulse();
            }
            if (!this.endTurnQueued) {
                if (AbstractDungeon.actionManager.turnHasEnded || !clickAndDragCards()) {
                    if (!this.isInKeyboardMode && this.isHoveringCard && this.hoveredCard != null && !this.hoveredCard.isHoveredInHand(1.0f)) {
                        int i = 0;
                        while (true) {
                            if (i < this.hand.group.size()) {
                                if (this.hand.group.get(i) == this.hoveredCard && i != 0 && this.hand.group.get(i - 1).isHoveredInHand(1.0f)) {
                                    this.toHover = this.hand.group.get(i - 1);
                                    break;
                                }
                                i++;
                            } else {
                                break;
                            }
                        }
                        releaseCard();
                    }
                    if (this.hoveredCard != null) {
                        this.hoveredCard.updateHoverLogic();
                    }
                }
            } else if (AbstractDungeon.actionManager.cardQueue.isEmpty() && !AbstractDungeon.actionManager.hasControl) {
                this.endTurnQueued = false;
                this.isEndingTurn = true;
            }
        }
    }

    private void updateSingleTargetInput() {
        if (Settings.isTouchScreen && !this.isUsingClickDragControl && !InputHelper.isMouseDown) {
            Gdx.input.setCursorPosition((int) MathUtils.lerp(Gdx.input.getX(), Settings.WIDTH / 2.0f, Gdx.graphics.getDeltaTime() * 10.0f), (int) MathUtils.lerp(Gdx.input.getY(), Settings.HEIGHT * 1.1f, Gdx.graphics.getDeltaTime() * 4.0f));
        }
        if (!this.isInKeyboardMode) {
            this.hoveredMonster = null;
            Iterator<AbstractMonster> it = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AbstractMonster m = it.next();
                m.hb.update();
                if (m.hb.hovered && !m.isDying && !m.isEscaping && m.currentHealth > 0) {
                    this.hoveredMonster = m;
                    break;
                }
            }
        } else if (InputActionSet.releaseCard.isJustPressed() || CInputActionSet.cancel.isJustPressed()) {
            AbstractCard card = this.hoveredCard;
            this.inSingleTargetMode = false;
            this.hoveredMonster = null;
            hoverCardInHand(card);
        } else {
            updateTargetArrowWithKeyboard(false);
        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() || InputHelper.justClickedRight || InputHelper.mY < 50.0f * Settings.scale || InputHelper.mY < this.hoverStartLine - (400.0f * Settings.scale)) {
            if (Settings.isTouchScreen) {
                InputHelper.moveCursorToNeutralPosition();
            }
            releaseCard();
            CardCrawlGame.sound.play("UI_CLICK_2");
            this.isUsingClickDragControl = false;
            this.inSingleTargetMode = false;
            GameCursor.hidden = false;
            this.hoveredMonster = null;
            return;
        }
        AbstractCard cardFromHotkey = InputHelper.getCardSelectedByHotkey(this.hand);
        if (cardFromHotkey != null && !isCardQueued(cardFromHotkey)) {
            boolean isSameCard = cardFromHotkey == this.hoveredCard;
            releaseCard();
            this.hoveredMonster = null;
            if (isSameCard) {
                GameCursor.hidden = false;
            } else {
                this.hoveredCard = cardFromHotkey;
                this.hoveredCard.setAngle(0.0f, false);
                this.isUsingClickDragControl = true;
                this.isDraggingCard = true;
            }
        }
        if (InputHelper.justClickedLeft || InputActionSet.confirm.isJustPressed() || CInputActionSet.select.isJustPressed()) {
            InputHelper.justClickedLeft = false;
            if (this.hoveredMonster == null) {
                CardCrawlGame.sound.play("UI_CLICK_1");
                return;
            }
            if (this.hoveredCard.canUse(this, this.hoveredMonster)) {
                playCard();
            } else {
                AbstractDungeon.effectList.add(new ThoughtBubble(this.dialogX, this.dialogY, 3.0f, this.hoveredCard.cantUseMessage, true));
                energyTip(this.hoveredCard);
                releaseCard();
            }
            this.isUsingClickDragControl = false;
            this.inSingleTargetMode = false;
            GameCursor.hidden = false;
            this.hoveredMonster = null;
        } else if (!this.isUsingClickDragControl && InputHelper.justReleasedClickLeft && this.hoveredMonster != null) {
            if (this.hoveredCard.canUse(this, this.hoveredMonster)) {
                playCard();
            } else {
                AbstractDungeon.effectList.add(new ThoughtBubble(this.dialogX, this.dialogY, 3.0f, this.hoveredCard.cantUseMessage, true));
                energyTip(this.hoveredCard);
                releaseCard();
            }
            this.inSingleTargetMode = false;
            GameCursor.hidden = false;
            this.hoveredMonster = null;
        }
    }

    private boolean isCardQueued(AbstractCard card) {
        Iterator<CardQueueItem> it = AbstractDungeon.actionManager.cardQueue.iterator();
        while (it.hasNext()) {
            CardQueueItem item = it.next();
            if (item.card == card) {
                return true;
            }
        }
        return false;
    }

    private void energyTip(AbstractCard cardToCheck) {
        int availableEnergy = EnergyPanel.totalCount;
        if (cardToCheck.cost > availableEnergy && !TipTracker.tips.get(TipTracker.ENERGY_USE_TIP).booleanValue()) {
            TipTracker.energyUseCounter++;
            if (TipTracker.energyUseCounter >= 2) {
                AbstractDungeon.ftue = new FtueTip(LABEL[1], MSG[1], 330.0f * Settings.scale, 400.0f * Settings.scale, FtueTip.TipType.ENERGY);
                TipTracker.neverShowAgain(TipTracker.ENERGY_USE_TIP);
            }
        }
    }

    private boolean updateFullKeyboardCardSelection() {
        AbstractCard card;
        if (Settings.isControllerMode || InputActionSet.left.isJustPressed() || InputActionSet.right.isJustPressed() || InputActionSet.confirm.isJustPressed()) {
            this.isInKeyboardMode = true;
            this.skipMouseModeOnce = true;
            GameCursor.hidden = true;
        }
        if (this.isInKeyboardMode && InputHelper.didMoveMouse()) {
            if (this.skipMouseModeOnce) {
                this.skipMouseModeOnce = false;
            } else {
                this.isInKeyboardMode = false;
                GameCursor.hidden = false;
            }
        }
        if (!this.isInKeyboardMode || this.hand.isEmpty() || this.inspectMode) {
            return false;
        }
        if (this.keyboardCardIndex != -2) {
            if (InputActionSet.left.isJustPressed() || CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                this.keyboardCardIndex--;
            } else if (InputActionSet.right.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                this.keyboardCardIndex++;
            }
            this.keyboardCardIndex = (this.keyboardCardIndex + this.hand.size()) % this.hand.size();
            if (AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.topPanel.potionUi.targetMode || (card = this.hand.group.get(this.keyboardCardIndex)) == this.hoveredCard || Math.abs(card.current_x - card.target_x) >= 400.0f * Settings.scale) {
                return false;
            }
            hoverCardInHand(card);
            return true;
        } else if (InputActionSet.left.isJustPressed() || CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
            this.keyboardCardIndex = this.hand.size() - 1;
            return false;
        } else if (!InputActionSet.right.isJustPressed() && !CInputActionSet.right.isJustPressed() && !CInputActionSet.altRight.isJustPressed()) {
            return false;
        } else {
            this.keyboardCardIndex = 0;
            return false;
        }
    }

    private void hoverCardInHand(AbstractCard card) {
        this.toHover = card;
        if (Settings.isControllerMode && AbstractDungeon.actionManager.turnHasEnded) {
            this.toHover = null;
        }
        if (card != null && !this.inspectMode) {
            Gdx.input.setCursorPosition((int) card.hb.cX, (int) (Settings.HEIGHT - HOVER_CARD_Y_POSITION));
        }
    }

    private void updateTargetArrowWithKeyboard(boolean autoTargetFirst) {
        AbstractMonster newTarget;
        int directionIndex = 0;
        if (autoTargetFirst) {
            directionIndex = 0 + 1;
        }
        if (InputActionSet.left.isJustPressed() || CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
            directionIndex--;
        }
        if (InputActionSet.right.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
            directionIndex++;
        }
        if (directionIndex != 0) {
            ArrayList<AbstractMonster> prefiltered = AbstractDungeon.getCurrRoom().monsters.monsters;
            ArrayList<AbstractMonster> sortedMonsters = new ArrayList<>(AbstractDungeon.getCurrRoom().monsters.monsters);
            Iterator<AbstractMonster> it = prefiltered.iterator();
            while (it.hasNext()) {
                AbstractMonster mons = it.next();
                if (mons.isDying) {
                    sortedMonsters.remove(mons);
                }
            }
            sortedMonsters.sort(AbstractMonster.sortByHitbox);
            if (!sortedMonsters.isEmpty()) {
                Iterator<AbstractMonster> it2 = sortedMonsters.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    AbstractMonster m = it2.next();
                    if (m.hb.hovered) {
                        this.hoveredMonster = m;
                        break;
                    }
                }
                if (this.hoveredMonster != null) {
                    int currentTargetIndex = sortedMonsters.indexOf(this.hoveredMonster);
                    int newTargetIndex = currentTargetIndex + directionIndex;
                    newTarget = sortedMonsters.get((newTargetIndex + sortedMonsters.size()) % sortedMonsters.size());
                } else if (directionIndex == 1) {
                    newTarget = sortedMonsters.get(0);
                } else {
                    newTarget = sortedMonsters.get(sortedMonsters.size() - 1);
                }
                if (newTarget != null) {
                    Hitbox target = newTarget.hb;
                    Gdx.input.setCursorPosition((int) target.cX, Settings.HEIGHT - ((int) target.cY));
                    this.hoveredMonster = newTarget;
                    this.isUsingClickDragControl = true;
                    this.isDraggingCard = true;
                }
                if (this.hoveredMonster.halfDead) {
                    this.hoveredMonster = null;
                }
            }
        }
    }

    private void renderCardHotKeyText(SpriteBatch sb) {
        int index = 0;
        Iterator<AbstractCard> it = this.hand.group.iterator();
        while (it.hasNext()) {
            AbstractCard card = it.next();
            if (index < InputActionSet.selectCardActions.length) {
                float width = (AbstractCard.IMG_WIDTH * card.drawScale) / 2.0f;
                float height = (AbstractCard.IMG_HEIGHT * card.drawScale) / 2.0f;
                float topOfCard = card.current_y + height;
                float textSpacing = 50.0f * Settings.scale;
                float textY = topOfCard + textSpacing;
                float sin = (float) Math.sin((card.angle / 180.0f) * 3.141592653589793d);
                float xOffset = sin * width;
                FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, InputActionSet.selectCardActions[index].getKeyString(), card.current_x - xOffset, textY, Settings.CREAM_COLOR);
            }
            index++;
        }
    }

    private boolean clickAndDragCards() {
        boolean simulateRightClickDrop = false;
        AbstractCard cardFromHotkey = InputHelper.getCardSelectedByHotkey(this.hand);
        if (cardFromHotkey != null && !isCardQueued(cardFromHotkey)) {
            if (this.isDraggingCard) {
                simulateRightClickDrop = cardFromHotkey == this.hoveredCard;
                CardCrawlGame.sound.play("UI_CLICK_2");
                releaseCard();
            }
            if (!simulateRightClickDrop) {
                manuallySelectCard(cardFromHotkey);
            }
        }
        if (!CInputActionSet.select.isJustPressed() || this.hoveredCard == null || isCardQueued(this.hoveredCard) || this.isDraggingCard) {
            if (InputHelper.justClickedLeft && this.isHoveringCard && !this.isDraggingCard) {
                this.hoverStartLine = InputHelper.mY + (140.0f * Settings.scale);
                InputHelper.justClickedLeft = false;
                if (this.hoveredCard != null) {
                    CardCrawlGame.sound.play("CARD_OBTAIN");
                    this.isDraggingCard = true;
                    this.passedHesitationLine = false;
                    this.hoveredCard.targetDrawScale = 0.7f;
                    if (!Settings.isTouchScreen || this.touchscreenInspectCount != 0) {
                        return true;
                    }
                    this.hoveredCard.current_y = AbstractCard.IMG_HEIGHT / 2.0f;
                    this.hoveredCard.target_y = AbstractCard.IMG_HEIGHT / 2.0f;
                    Gdx.input.setCursorPosition((int) this.hoveredCard.current_x, (int) (Settings.HEIGHT - (AbstractCard.IMG_HEIGHT / 2.0f)));
                    this.touchscreenInspectCount = 0;
                    return true;
                }
            }
            if (InputHelper.isMouseDown) {
                this.clickDragTimer += Gdx.graphics.getDeltaTime();
            } else {
                this.clickDragTimer = 0.0f;
            }
            if ((!InputHelper.justClickedLeft && !InputActionSet.confirm.isJustPressed() && !CInputActionSet.select.isJustPressed()) || !this.isUsingClickDragControl) {
                if (this.isInKeyboardMode) {
                    if (InputActionSet.releaseCard.isJustPressed() || CInputActionSet.cancel.isJustPressed()) {
                        hoverCardInHand(this.hoveredCard);
                    } else if ((InputActionSet.confirm.isJustPressed() || CInputActionSet.select.isJustPressed()) && this.hoveredCard != null) {
                        manuallySelectCard(this.hoveredCard);
                        if (this.hoveredCard.target == AbstractCard.CardTarget.ENEMY) {
                            updateTargetArrowWithKeyboard(true);
                        } else {
                            Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
                        }
                    }
                }
                if (!this.isDraggingCard || (!InputHelper.isMouseDown && !this.isUsingClickDragControl)) {
                    if (!this.isDraggingCard || !InputHelper.justReleasedClickLeft || Settings.isTouchScreen) {
                        if (!Settings.isTouchScreen || !InputHelper.justReleasedClickLeft || this.hoveredCard == null) {
                            return false;
                        }
                        this.touchscreenInspectCount++;
                        if (this.isHoveringDropZone && this.hoveredCard.hasEnoughEnergy() && this.hoveredCard.canUse(this, null)) {
                            playCard();
                            return true;
                        } else if (this.touchscreenInspectCount <= 1) {
                            return false;
                        } else {
                            AbstractCard newHoveredCard = null;
                            Iterator<AbstractCard> it = this.hand.group.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                AbstractCard c = it.next();
                                c.updateHoverLogic();
                                if (c.hb.hovered && c != this.hoveredCard) {
                                    newHoveredCard = c;
                                    break;
                                }
                            }
                            releaseCard();
                            if (newHoveredCard == null) {
                                InputHelper.moveCursorToNeutralPosition();
                                return false;
                            }
                            newHoveredCard.current_y = AbstractCard.IMG_HEIGHT / 2.0f;
                            newHoveredCard.target_y = AbstractCard.IMG_HEIGHT / 2.0f;
                            newHoveredCard.angle = 0.0f;
                            Gdx.input.setCursorPosition((int) newHoveredCard.current_x, (int) (Settings.HEIGHT - (AbstractCard.IMG_HEIGHT / 2.0f)));
                            this.touchscreenInspectCount = 1;
                            return false;
                        }
                    } else if (this.isHoveringDropZone) {
                        if (this.hoveredCard.target == AbstractCard.CardTarget.ENEMY || this.hoveredCard.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
                            this.inSingleTargetMode = true;
                            this.arrowX = InputHelper.mX;
                            this.arrowY = InputHelper.mY;
                            GameCursor.hidden = true;
                            this.hoveredCard.untip();
                            this.hand.refreshHandLayout();
                            this.hoveredCard.target_y = (AbstractCard.IMG_HEIGHT * 0.75f) / 2.5f;
                            this.hoveredCard.target_x = Settings.WIDTH / 2.0f;
                            this.isDraggingCard = false;
                            return true;
                        } else if (this.hoveredCard.canUse(this, null)) {
                            playCard();
                            return true;
                        } else {
                            AbstractDungeon.effectList.add(new ThoughtBubble(this.dialogX, this.dialogY, 3.0f, this.hoveredCard.cantUseMessage, true));
                            energyTip(this.hoveredCard);
                            releaseCard();
                            return true;
                        }
                    } else if (this.clickDragTimer < 0.4f) {
                        this.isUsingClickDragControl = true;
                        return true;
                    } else if (AbstractDungeon.actionManager.currentAction != null) {
                        return false;
                    } else {
                        releaseCard();
                        CardCrawlGame.sound.play("CARD_OBTAIN");
                        return true;
                    }
                } else if (InputHelper.justClickedRight || simulateRightClickDrop) {
                    CardCrawlGame.sound.play("UI_CLICK_2");
                    releaseCard();
                    return true;
                } else {
                    if (!Settings.isTouchScreen) {
                        this.hoveredCard.target_x = InputHelper.mX;
                        this.hoveredCard.target_y = InputHelper.mY;
                    } else {
                        this.hoveredCard.target_x = InputHelper.mX;
                        this.hoveredCard.target_y = InputHelper.mY + (270.0f * Settings.scale);
                    }
                    if (!this.hoveredCard.hasEnoughEnergy() && this.isHoveringDropZone) {
                        AbstractDungeon.effectList.add(new ThoughtBubble(this.dialogX, this.dialogY, 3.0f, this.hoveredCard.cantUseMessage, true));
                        energyTip(this.hoveredCard);
                        releaseCard();
                        CardCrawlGame.sound.play("CARD_REJECT");
                        return true;
                    } else if (!this.isHoveringDropZone) {
                        return true;
                    } else {
                        if (this.hoveredCard.target != AbstractCard.CardTarget.ENEMY && this.hoveredCard.target != AbstractCard.CardTarget.SELF_AND_ENEMY) {
                            return true;
                        }
                        this.inSingleTargetMode = true;
                        this.arrowX = InputHelper.mX;
                        this.arrowY = InputHelper.mY;
                        GameCursor.hidden = true;
                        this.hoveredCard.untip();
                        this.hand.refreshHandLayout();
                        if (!Settings.isTouchScreen) {
                            this.hoveredCard.target_y = (AbstractCard.IMG_HEIGHT * 0.75f) / 2.5f;
                            this.hoveredCard.target_x = Settings.WIDTH / 2.0f;
                        } else {
                            this.hoveredCard.target_y = 260.0f * Settings.scale;
                            this.hoveredCard.target_x = Settings.WIDTH / 2.0f;
                            this.hoveredCard.targetDrawScale = 1.0f;
                        }
                        this.isDraggingCard = false;
                        return true;
                    }
                }
            } else if (InputHelper.justClickedRight || simulateRightClickDrop) {
                CardCrawlGame.sound.play("UI_CLICK_2");
                releaseCard();
                return true;
            } else {
                InputHelper.justClickedLeft = false;
                if (!this.isHoveringDropZone || !this.hoveredCard.canUse(this, null) || this.hoveredCard.target == AbstractCard.CardTarget.ENEMY || this.hoveredCard.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
                    CardCrawlGame.sound.play("CARD_OBTAIN");
                    releaseCard();
                } else {
                    playCard();
                }
                this.isUsingClickDragControl = false;
                return true;
            }
        } else {
            manuallySelectCard(this.hoveredCard);
            if (this.hoveredCard.target == AbstractCard.CardTarget.ENEMY) {
                updateTargetArrowWithKeyboard(true);
                return true;
            }
            InputHelper.moveCursorToNeutralPosition();
            return true;
        }
    }

    private void manuallySelectCard(AbstractCard card) {
        this.hoveredCard = card;
        this.hoveredCard.setAngle(0.0f, false);
        this.isUsingClickDragControl = true;
        this.isDraggingCard = true;
        this.hoveredCard.flash(Color.SKY.cpy());
        if (!this.hoveredCard.showEvokeValue) {
            return;
        }
        if (this.hoveredCard.showEvokeOrbCount == 0) {
            Iterator<AbstractOrb> it = this.orbs.iterator();
            while (it.hasNext()) {
                AbstractOrb o = it.next();
                o.showEvokeValue();
            }
            return;
        }
        int tmpShowCount = this.hoveredCard.showEvokeOrbCount;
        int emptyCount = 0;
        Iterator<AbstractOrb> it2 = this.orbs.iterator();
        while (it2.hasNext()) {
            AbstractOrb o2 = it2.next();
            if (o2 instanceof EmptyOrbSlot) {
                emptyCount++;
            }
        }
        int tmpShowCount2 = tmpShowCount - emptyCount;
        if (tmpShowCount2 > 0) {
            Iterator<AbstractOrb> it3 = this.orbs.iterator();
            while (it3.hasNext()) {
                AbstractOrb o3 = it3.next();
                o3.showEvokeValue();
                tmpShowCount2--;
                if (tmpShowCount2 <= 0) {
                    return;
                }
            }
        }
    }

    private void playCard() {
        InputHelper.justClickedLeft = false;
        this.hoverEnemyWaitTimer = 1.0f;
        this.hoveredCard.unhover();
        if (!queueContains(this.hoveredCard)) {
            if (this.hoveredCard.target == AbstractCard.CardTarget.ENEMY || this.hoveredCard.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
                if (hasPower(SurroundedPower.POWER_ID)) {
                    this.flipHorizontal = this.hoveredMonster.drawX < this.drawX;
                }
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this.hoveredCard, this.hoveredMonster));
            } else {
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this.hoveredCard, (AbstractMonster) null));
            }
        }
        this.isUsingClickDragControl = false;
        this.hoveredCard = null;
        this.isDraggingCard = false;
    }

    private boolean queueContains(AbstractCard card) {
        Iterator<CardQueueItem> it = AbstractDungeon.actionManager.cardQueue.iterator();
        while (it.hasNext()) {
            CardQueueItem i = it.next();
            if (i.card == card) {
                return true;
            }
        }
        return false;
    }

    public void releaseCard() {
        Iterator<AbstractOrb> it = this.orbs.iterator();
        while (it.hasNext()) {
            AbstractOrb o = it.next();
            o.hideEvokeValues();
        }
        this.passedHesitationLine = false;
        InputHelper.justClickedLeft = false;
        InputHelper.justReleasedClickLeft = false;
        InputHelper.isMouseDown = false;
        this.inSingleTargetMode = false;
        if (!this.isInKeyboardMode) {
            GameCursor.hidden = false;
        }
        this.isUsingClickDragControl = false;
        this.isHoveringDropZone = false;
        this.isDraggingCard = false;
        this.isHoveringCard = false;
        if (this.hoveredCard != null) {
            if (this.hoveredCard.canUse(this, null)) {
                this.hoveredCard.beginGlowing();
            }
            this.hoveredCard.untip();
            this.hoveredCard.hoverTimer = 0.25f;
            this.hoveredCard.unhover();
        }
        this.hoveredCard = null;
        this.hand.refreshHandLayout();
        this.touchscreenInspectCount = 0;
    }

    public void onCardDrawOrDiscard() {
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            p.onDrawOrDiscard();
        }
        Iterator<AbstractRelic> it2 = this.relics.iterator();
        while (it2.hasNext()) {
            AbstractRelic r = it2.next();
            r.onDrawOrDiscard();
        }
        if (hasPower("Corruption")) {
            Iterator<AbstractCard> it3 = this.hand.group.iterator();
            while (it3.hasNext()) {
                AbstractCard c = it3.next();
                if (c.type == AbstractCard.CardType.SKILL && c.costForTurn != 0) {
                    c.modifyCostForCombat(-9);
                }
            }
        }
        this.hand.applyPowers();
        this.hand.glowCheck();
    }

    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            useFastAttackAnimation();
        }
        c.calculateCardDamage(monster);
        if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse) {
            c.energyOnUse = EnergyPanel.totalCount;
        }
        if (c.cost == -1 && c.isInAutoplay) {
            c.freeToPlayOnce = true;
        }
        c.use(this, monster);
        AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, monster));
        if (!c.dontTriggerOnUseCard) {
            this.hand.triggerOnOtherCardPlayed(c);
        }
        this.hand.removeCard(c);
        this.cardInUse = c;
        c.target_x = Settings.WIDTH / 2;
        c.target_y = Settings.HEIGHT / 2;
        if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (!hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL)) {
            this.energy.use(c.costForTurn);
        }
        if (!this.hand.canUseAnyCard() && !this.endTurnQueued) {
            AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
        }
    }

    @Override // com.megacrit.cardcrawl.core.AbstractCreature
    public void damage(DamageInfo info) {
        int damageAmount = info.output;
        boolean hadBlock = true;
        if (this.currentBlock == 0) {
            hadBlock = false;
        }
        if (damageAmount < 0) {
            damageAmount = 0;
        }
        if (damageAmount > 1 && hasPower(IntangiblePlayerPower.POWER_ID)) {
            damageAmount = 1;
        }
        int damageAmount2 = decrementBlock(info, damageAmount);
        if (info.owner == this) {
            Iterator<AbstractRelic> it = this.relics.iterator();
            while (it.hasNext()) {
                damageAmount2 = it.next().onAttackToChangeDamage(info, damageAmount2);
            }
        }
        if (info.owner != null) {
            Iterator<AbstractPower> it2 = info.owner.powers.iterator();
            while (it2.hasNext()) {
                damageAmount2 = it2.next().onAttackToChangeDamage(info, damageAmount2);
            }
        }
        Iterator<AbstractRelic> it3 = this.relics.iterator();
        while (it3.hasNext()) {
            damageAmount2 = it3.next().onAttackedToChangeDamage(info, damageAmount2);
        }
        Iterator<AbstractPower> it4 = this.powers.iterator();
        while (it4.hasNext()) {
            damageAmount2 = it4.next().onAttackedToChangeDamage(info, damageAmount2);
        }
        if (info.owner == this) {
            Iterator<AbstractRelic> it5 = this.relics.iterator();
            while (it5.hasNext()) {
                it5.next().onAttack(info, damageAmount2, this);
            }
        }
        if (info.owner != null) {
            Iterator<AbstractPower> it6 = info.owner.powers.iterator();
            while (it6.hasNext()) {
                it6.next().onAttack(info, damageAmount2, this);
            }
            Iterator<AbstractPower> it7 = this.powers.iterator();
            while (it7.hasNext()) {
                damageAmount2 = it7.next().onAttacked(info, damageAmount2);
            }
            Iterator<AbstractRelic> it8 = this.relics.iterator();
            while (it8.hasNext()) {
                damageAmount2 = it8.next().onAttacked(info, damageAmount2);
            }
        } else {
            logger.info("NO OWNER, DON'T TRIGGER POWERS");
        }
        Iterator<AbstractRelic> it9 = this.relics.iterator();
        while (it9.hasNext()) {
            damageAmount2 = it9.next().onLoseHpLast(damageAmount2);
        }
        this.lastDamageTaken = Math.min(damageAmount2, this.currentHealth);
        if (damageAmount2 > 0) {
            Iterator<AbstractPower> it10 = this.powers.iterator();
            while (it10.hasNext()) {
                damageAmount2 = it10.next().onLoseHp(damageAmount2);
            }
            Iterator<AbstractRelic> it11 = this.relics.iterator();
            while (it11.hasNext()) {
                it11.next().onLoseHp(damageAmount2);
            }
            Iterator<AbstractPower> it12 = this.powers.iterator();
            while (it12.hasNext()) {
                it12.next().wasHPLost(info, damageAmount2);
            }
            Iterator<AbstractRelic> it13 = this.relics.iterator();
            while (it13.hasNext()) {
                it13.next().wasHPLost(damageAmount2);
            }
            if (info.owner != null) {
                Iterator<AbstractPower> it14 = info.owner.powers.iterator();
                while (it14.hasNext()) {
                    it14.next().onInflictDamage(info, damageAmount2, this);
                }
            }
            if (info.owner != this) {
                useStaggerAnimation();
            }
            if (info.type == DamageInfo.DamageType.HP_LOSS) {
                GameActionManager.hpLossThisCombat += damageAmount2;
            }
            GameActionManager.damageReceivedThisTurn += damageAmount2;
            GameActionManager.damageReceivedThisCombat += damageAmount2;
            this.currentHealth -= damageAmount2;
            if (damageAmount2 > 0 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                updateCardsOnDamage();
                this.damagedThisCombat++;
            }
            AbstractDungeon.effectList.add(new StrikeEffect(this, this.hb.cX, this.hb.cY, damageAmount2));
            if (this.currentHealth < 0) {
                this.currentHealth = 0;
            } else if (this.currentHealth < this.maxHealth / 4) {
                AbstractDungeon.topLevelEffects.add(new BorderFlashEffect(new Color(1.0f, 0.1f, 0.05f, 0.0f)));
            }
            healthBarUpdatedEvent();
            if (this.currentHealth <= this.maxHealth / 2.0f && !this.isBloodied) {
                this.isBloodied = true;
                Iterator<AbstractRelic> it15 = this.relics.iterator();
                while (it15.hasNext()) {
                    AbstractRelic r = it15.next();
                    if (r != null) {
                        r.onBloodied();
                    }
                }
            }
            if (this.currentHealth < 1) {
                if (!hasRelic(MarkOfTheBloom.ID)) {
                    if (hasPotion(FairyPotion.POTION_ID)) {
                        Iterator<AbstractPotion> it16 = this.potions.iterator();
                        while (it16.hasNext()) {
                            AbstractPotion p = it16.next();
                            if (p.ID.equals(FairyPotion.POTION_ID)) {
                                p.flash();
                                this.currentHealth = 0;
                                p.use(this);
                                AbstractDungeon.topPanel.destroyPotion(p.slot);
                                return;
                            }
                        }
                    } else if (hasRelic(LizardTail.ID) && ((LizardTail) getRelic(LizardTail.ID)).counter == -1) {
                        this.currentHealth = 0;
                        getRelic(LizardTail.ID).onTrigger();
                        return;
                    }
                }
                this.isDead = true;
                AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
                this.currentHealth = 0;
                if (this.currentBlock > 0) {
                    loseBlock();
                    AbstractDungeon.effectList.add(new HbBlockBrokenEffect((this.hb.cX - (this.hb.width / 2.0f)) + BLOCK_ICON_X, (this.hb.cY - (this.hb.height / 2.0f)) + BLOCK_ICON_Y));
                }
            }
        } else if (this.currentBlock > 0) {
            AbstractDungeon.effectList.add(new BlockedWordEffect(this, this.hb.cX, this.hb.cY, uiStrings.TEXT[0]));
        } else if (hadBlock) {
            AbstractDungeon.effectList.add(new BlockedWordEffect(this, this.hb.cX, this.hb.cY, uiStrings.TEXT[0]));
            AbstractDungeon.effectList.add(new HbBlockBrokenEffect((this.hb.cX - (this.hb.width / 2.0f)) + BLOCK_ICON_X, (this.hb.cY - (this.hb.height / 2.0f)) + BLOCK_ICON_Y));
        } else {
            AbstractDungeon.effectList.add(new StrikeEffect(this, this.hb.cX, this.hb.cY, 0));
        }
    }

    private void updateCardsOnDamage() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            Iterator<AbstractCard> it = this.hand.group.iterator();
            while (it.hasNext()) {
                AbstractCard c = it.next();
                c.tookDamage();
            }
            Iterator<AbstractCard> it2 = this.discardPile.group.iterator();
            while (it2.hasNext()) {
                AbstractCard c2 = it2.next();
                c2.tookDamage();
            }
            Iterator<AbstractCard> it3 = this.drawPile.group.iterator();
            while (it3.hasNext()) {
                AbstractCard c3 = it3.next();
                c3.tookDamage();
            }
        }
    }

    public void updateCardsOnDiscard() {
        Iterator<AbstractCard> it = this.hand.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            c.didDiscard();
        }
        Iterator<AbstractCard> it2 = this.discardPile.group.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            c2.didDiscard();
        }
        Iterator<AbstractCard> it3 = this.drawPile.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            c3.didDiscard();
        }
    }

    @Override // com.megacrit.cardcrawl.core.AbstractCreature
    public void heal(int healAmount) {
        super.heal(healAmount);
        if (this.currentHealth > this.maxHealth / 2.0f && this.isBloodied) {
            this.isBloodied = false;
            Iterator<AbstractRelic> it = this.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onNotBloodied();
            }
        }
    }

    public void gainEnergy(int e) {
        EnergyPanel.addEnergy(e);
        this.hand.glowCheck();
    }

    public void loseEnergy(int e) {
        EnergyPanel.useEnergy(e);
    }

    public void preBattlePrep() {
        if (!TipTracker.tips.get(TipTracker.COMBAT_TIP).booleanValue()) {
            AbstractDungeon.ftue = new MultiPageFtue();
            TipTracker.neverShowAgain(TipTracker.COMBAT_TIP);
        }
        AbstractDungeon.actionManager.clear();
        this.damagedThisCombat = 0;
        this.cardsPlayedThisTurn = 0;
        this.maxOrbs = 0;
        this.orbs.clear();
        increaseMaxOrbSlots(this.masterMaxOrbs, false);
        this.isBloodied = this.currentHealth <= this.maxHealth / 2;
        poisonKillCount = 0;
        GameActionManager.playerHpLastTurn = this.currentHealth;
        this.endTurnQueued = false;
        this.gameHandSize = this.masterHandSize;
        this.isDraggingCard = false;
        this.isHoveringDropZone = false;
        this.hoveredCard = null;
        this.cardInUse = null;
        this.drawPile.initializeDeck(this.masterDeck);
        AbstractDungeon.overlayMenu.endTurnButton.enabled = false;
        this.hand.clear();
        this.discardPile.clear();
        this.exhaustPile.clear();
        if (AbstractDungeon.player.hasRelic(SlaversCollar.ID)) {
            ((SlaversCollar) AbstractDungeon.player.getRelic(SlaversCollar.ID)).beforeEnergyPrep();
        }
        this.energy.prep();
        this.powers.clear();
        this.isEndingTurn = false;
        healthBarUpdatedEvent();
        if (ModHelper.isModEnabled(Lethality.ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
        }
        if (ModHelper.isModEnabled(Terminal.ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new PlatedArmorPower(this, 5), 5));
        }
        AbstractDungeon.getCurrRoom().monsters.usePreBattleAction();
        if (Settings.isFinalActAvailable && AbstractDungeon.getCurrMapNode().hasEmeraldKey) {
            AbstractDungeon.getCurrRoom().applyEmeraldEliteBuff();
        }
        AbstractDungeon.actionManager.addToTop(new WaitAction(1.0f));
        applyPreCombatLogic();
    }

    public ArrayList<String> getRelicNames() {
        ArrayList<String> arr = new ArrayList<>();
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic relic = it.next();
            arr.add(relic.relicId);
        }
        return arr;
    }

    public int getCircletCount() {
        int count = 0;
        int counterSum = 0;
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic relic = it.next();
            if (relic.relicId.equals(Circlet.ID)) {
                count++;
                counterSum += relic.counter;
            }
        }
        if (counterSum > 0) {
            return counterSum;
        }
        return count;
    }

    public void draw(int numCards) {
        for (int i = 0; i < numCards; i++) {
            if (!this.drawPile.isEmpty()) {
                AbstractCard c = this.drawPile.getTopCard();
                c.current_x = CardGroup.DRAW_PILE_X;
                c.current_y = CardGroup.DRAW_PILE_Y;
                c.setAngle(0.0f, true);
                c.lighten(false);
                c.drawScale = 0.12f;
                c.targetDrawScale = 0.75f;
                c.triggerWhenDrawn();
                this.hand.addToHand(c);
                this.drawPile.removeTopCard();
                Iterator<AbstractPower> it = this.powers.iterator();
                while (it.hasNext()) {
                    AbstractPower p = it.next();
                    p.onCardDraw(c);
                }
                Iterator<AbstractRelic> it2 = this.relics.iterator();
                while (it2.hasNext()) {
                    AbstractRelic r = it2.next();
                    r.onCardDraw(c);
                }
            } else {
                logger.info("ERROR: How did this happen? No cards in draw pile?? Player.java");
            }
        }
    }

    public void draw() {
        if (this.hand.size() == 10) {
            createHandIsFullDialog();
            return;
        }
        CardCrawlGame.sound.playAV("CARD_DRAW_8", -0.12f, 0.25f);
        draw(1);
        onCardDrawOrDiscard();
    }

    @Override // com.megacrit.cardcrawl.core.AbstractCreature
    public void render(SpriteBatch sb) {
        this.stance.render(sb);
        if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT || (AbstractDungeon.getCurrRoom() instanceof MonsterRoom)) && !this.isDead) {
            renderHealth(sb);
            if (!this.orbs.isEmpty()) {
                Iterator<AbstractOrb> it = this.orbs.iterator();
                while (it.hasNext()) {
                    AbstractOrb o = it.next();
                    o.render(sb);
                }
            }
        }
        if (!(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {
            if (this.atlas == null || this.renderCorpse) {
                sb.setColor(Color.WHITE);
                sb.draw(this.img, (this.drawX - ((this.img.getWidth() * Settings.scale) / 2.0f)) + this.animX, this.drawY, this.img.getWidth() * Settings.scale, this.img.getHeight() * Settings.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), this.flipHorizontal, this.flipVertical);
            } else {
                renderPlayerImage(sb);
            }
            this.hb.render(sb);
            this.healthHb.render(sb);
            return;
        }
        sb.setColor(Color.WHITE);
        renderShoulderImg(sb);
    }

    public void renderShoulderImg(SpriteBatch sb) {
        if (CampfireUI.hidden) {
            sb.draw(this.shoulder2Img, 0.0f, 0.0f, 1920.0f * Settings.scale, 1136.0f * Settings.scale);
        } else {
            sb.draw(this.shoulderImg, this.animX, 0.0f, 1920.0f * Settings.scale, 1136.0f * Settings.scale);
        }
    }

    public void renderPlayerImage(SpriteBatch sb) {
        if (this.atlas != null) {
            this.state.update(Gdx.graphics.getDeltaTime());
            this.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(this.drawX + this.animX, this.drawY + this.animY);
            this.skeleton.setColor(this.tint.color);
            this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
            sb.end();
            CardCrawlGame.psb.begin();
            sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();
            return;
        }
        sb.setColor(Color.WHITE);
        sb.draw(this.img, (this.drawX - ((this.img.getWidth() * Settings.scale) / 2.0f)) + this.animX, this.drawY, this.img.getWidth() * Settings.scale, this.img.getHeight() * Settings.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), this.flipHorizontal, this.flipVertical);
    }

    public void renderPlayerBattleUi(SpriteBatch sb) {
        if ((this.hb.hovered || this.healthHb.hovered) && !AbstractDungeon.isScreenUp) {
            renderPowerTips(sb);
        }
    }

    @Override // com.megacrit.cardcrawl.core.AbstractCreature
    public void renderPowerTips(SpriteBatch sb) {
        ArrayList<PowerTip> tips = new ArrayList<>();
        if (!this.stance.ID.equals(NeutralStance.STANCE_ID)) {
            tips.add(new PowerTip(this.stance.name, this.stance.description));
        }
        Iterator<AbstractPower> it = this.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (p.region48 != null) {
                tips.add(new PowerTip(p.name, p.description, p.region48));
            } else {
                tips.add(new PowerTip(p.name, p.description, p.img));
            }
        }
        if (tips.isEmpty()) {
            return;
        }
        if (this.hb.cX + (this.hb.width / 2.0f) < TIP_X_THRESHOLD) {
            TipHelper.queuePowerTips(this.hb.cX + (this.hb.width / 2.0f) + TIP_OFFSET_R_X, this.hb.cY + TipHelper.calculateAdditionalOffset(tips, this.hb.cY), tips);
        } else {
            TipHelper.queuePowerTips((this.hb.cX - (this.hb.width / 2.0f)) + TIP_OFFSET_L_X, this.hb.cY + TipHelper.calculateAdditionalOffset(tips, this.hb.cY), tips);
        }
    }

    public void renderHand(SpriteBatch sb) {
        if (Settings.SHOW_CARD_HOTKEYS) {
            renderCardHotKeyText(sb);
        }
        if (this.inspectMode && this.inspectHb != null) {
            renderReticle(sb, this.inspectHb);
        }
        if (this.hoveredCard != null) {
            int aliveMonsters = 0;
            this.hand.renderHand(sb, this.hoveredCard);
            this.hoveredCard.renderHoverShadow(sb);
            if ((this.isDraggingCard || this.inSingleTargetMode) && this.isHoveringDropZone) {
                if (this.isDraggingCard && !this.inSingleTargetMode) {
                    AbstractMonster theMonster = null;
                    Iterator<AbstractMonster> it = AbstractDungeon.getMonsters().monsters.iterator();
                    while (it.hasNext()) {
                        AbstractMonster m = it.next();
                        if (!m.isDying && m.currentHealth > 0) {
                            aliveMonsters++;
                            theMonster = m;
                        }
                    }
                    if (aliveMonsters == 1 && this.hoveredMonster == null) {
                        this.hoveredCard.calculateCardDamage(theMonster);
                        this.hoveredCard.render(sb);
                        this.hoveredCard.applyPowers();
                    } else {
                        this.hoveredCard.render(sb);
                    }
                }
                if (!AbstractDungeon.getCurrRoom().isBattleEnding()) {
                    renderHoverReticle(sb);
                }
            }
            if (this.hoveredMonster != null) {
                this.hoveredCard.calculateCardDamage(this.hoveredMonster);
                this.hoveredCard.render(sb);
                this.hoveredCard.applyPowers();
            } else if (aliveMonsters != 1) {
                this.hoveredCard.render(sb);
            }
        } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
            this.hand.render(sb);
        } else {
            this.hand.renderHand(sb, this.cardInUse);
        }
        if (!(this.cardInUse == null || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || PeekButton.isPeeking)) {
            this.cardInUse.render(sb);
            if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
                AbstractDungeon.effectList.add(new CardDisappearEffect(this.cardInUse.makeCopy(), this.cardInUse.current_x, this.cardInUse.current_y));
                this.cardInUse = null;
            }
        }
        this.limbo.render(sb);
        if (this.inSingleTargetMode && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getCurrRoom().isBattleEnding()) {
            renderTargetingUi(sb);
        }
    }

    private void renderTargetingUi(SpriteBatch sb) {
        this.arrowX = MathHelper.mouseLerpSnap(this.arrowX, InputHelper.mX);
        this.arrowY = MathHelper.mouseLerpSnap(this.arrowY, InputHelper.mY);
        this.controlPoint.x = this.hoveredCard.current_x - ((this.arrowX - this.hoveredCard.current_x) / 4.0f);
        this.controlPoint.y = this.arrowY + ((this.arrowY - this.hoveredCard.current_y) / 2.0f);
        if (this.hoveredMonster == null) {
            this.arrowScale = Settings.scale;
            this.arrowScaleTimer = 0.0f;
            sb.setColor(Color.WHITE);
        } else {
            this.arrowScaleTimer += Gdx.graphics.getDeltaTime();
            if (this.arrowScaleTimer > 1.0f) {
                this.arrowScaleTimer = 1.0f;
            }
            this.arrowScale = Interpolation.elasticOut.apply(Settings.scale, Settings.scale * ARROW_TARGET_SCALE, this.arrowScaleTimer);
            sb.setColor(ARROW_COLOR);
        }
        this.arrowTmp.x = this.controlPoint.x - this.arrowX;
        this.arrowTmp.y = this.controlPoint.y - this.arrowY;
        this.arrowTmp.nor();
        this.startArrowVector.x = this.hoveredCard.current_x;
        this.startArrowVector.y = this.hoveredCard.current_y;
        this.endArrowVector.x = this.arrowX;
        this.endArrowVector.y = this.arrowY;
        drawCurvedLine(sb, this.startArrowVector, this.endArrowVector, this.controlPoint);
        sb.draw(ImageMaster.TARGET_UI_ARROW, this.arrowX - 128.0f, this.arrowY - 128.0f, 128.0f, 128.0f, 256.0f, 256.0f, this.arrowScale, this.arrowScale, this.arrowTmp.angle() + 90.0f, 0, 0, 256, 256, false, false);
    }

    private void drawCurvedLine(SpriteBatch sb, Vector2 start, Vector2 end, Vector2 control) {
        float radius = 7.0f * Settings.scale;
        for (int i = 0; i < this.points.length - 1; i++) {
            this.points[i] = (Vector2) Bezier.quadratic(this.points[i], i / 20.0f, start, control, end, this.arrowTmp);
            radius += 0.4f * Settings.scale;
            if (i != 0) {
                this.arrowTmp.x = this.points[i - 1].x - this.points[i].x;
                this.arrowTmp.y = this.points[i - 1].y - this.points[i].y;
                sb.draw(ImageMaster.TARGET_UI_CIRCLE, this.points[i].x - 64.0f, this.points[i].y - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, radius / 18.0f, radius / 18.0f, this.arrowTmp.nor().angle() + 90.0f, 0, 0, 128, 128, false, false);
            } else {
                this.arrowTmp.x = this.controlPoint.x - this.points[i].x;
                this.arrowTmp.y = this.controlPoint.y - this.points[i].y;
                sb.draw(ImageMaster.TARGET_UI_CIRCLE, this.points[i].x - 64.0f, this.points[i].y - 64.0f, 64.0f, 64.0f, 128.0f, 128.0f, radius / 18.0f, radius / 18.0f, this.arrowTmp.nor().angle() + 270.0f, 0, 0, 128, 128, false, false);
            }
        }
    }

    public void createHandIsFullDialog() {
        AbstractDungeon.effectList.add(new ThoughtBubble(this.dialogX, this.dialogY, 3.0f, MSG[2], true));
    }

    private void renderHoverReticle(SpriteBatch sb) {
        switch (this.hoveredCard.target) {
            case ENEMY:
                if (this.inSingleTargetMode && this.hoveredMonster != null) {
                    this.hoveredMonster.renderReticle(sb);
                    return;
                }
                return;
            case ALL_ENEMY:
                AbstractDungeon.getCurrRoom().monsters.renderReticle(sb);
                return;
            case SELF:
                renderReticle(sb);
                return;
            case SELF_AND_ENEMY:
                renderReticle(sb);
                if (this.inSingleTargetMode && this.hoveredMonster != null) {
                    this.hoveredMonster.renderReticle(sb);
                    return;
                }
                return;
            case ALL:
                renderReticle(sb);
                AbstractDungeon.getCurrRoom().monsters.renderReticle(sb);
                return;
            case NONE:
            default:
                return;
        }
    }

    public void applyPreCombatLogic() {
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r != null) {
                r.atPreBattle();
            }
        }
    }

    public void applyStartOfCombatLogic() {
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r != null) {
                r.atBattleStart();
            }
        }
        Iterator<AbstractBlight> it2 = this.blights.iterator();
        while (it2.hasNext()) {
            AbstractBlight b = it2.next();
            if (b != null) {
                b.atBattleStart();
            }
        }
    }

    public void applyStartOfCombatPreDrawLogic() {
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r != null) {
                r.atBattleStartPreDraw();
            }
        }
    }

    public void applyStartOfTurnRelics() {
        this.stance.atStartOfTurn();
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r != null) {
                r.atTurnStart();
            }
        }
        Iterator<AbstractBlight> it2 = this.blights.iterator();
        while (it2.hasNext()) {
            AbstractBlight b = it2.next();
            if (b != null) {
                b.atTurnStart();
            }
        }
    }

    public void applyStartOfTurnPostDrawRelics() {
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r != null) {
                r.atTurnStartPostDraw();
            }
        }
    }

    public void applyStartOfTurnPreDrawCards() {
        Iterator<AbstractCard> it = this.hand.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c != null) {
                c.atTurnStartPreDraw();
            }
        }
    }

    public void applyStartOfTurnCards() {
        Iterator<AbstractCard> it = this.drawPile.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c != null) {
                c.atTurnStart();
            }
        }
        Iterator<AbstractCard> it2 = this.hand.group.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            if (c2 != null) {
                c2.atTurnStart();
            }
        }
        Iterator<AbstractCard> it3 = this.discardPile.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            if (c3 != null) {
                c3.atTurnStart();
            }
        }
    }

    public void onVictory() {
        if (!this.isDying) {
            Iterator<AbstractRelic> it = this.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onVictory();
            }
            Iterator<AbstractBlight> it2 = this.blights.iterator();
            while (it2.hasNext()) {
                AbstractBlight b = it2.next();
                b.onVictory();
            }
            Iterator<AbstractPower> it3 = this.powers.iterator();
            while (it3.hasNext()) {
                AbstractPower p = it3.next();
                p.onVictory();
            }
        }
        this.damagedThisCombat = 0;
    }

    public boolean hasRelic(String targetID) {
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r.relicId.equals(targetID)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBlight(String targetID) {
        Iterator<AbstractBlight> it = this.blights.iterator();
        while (it.hasNext()) {
            AbstractBlight b = it.next();
            if (b.blightID.equals(targetID)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPotion(String id) {
        Iterator<AbstractPotion> it = this.potions.iterator();
        while (it.hasNext()) {
            AbstractPotion p = it.next();
            if (p.ID.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAnyPotions() {
        Iterator<AbstractPotion> it = this.potions.iterator();
        while (it.hasNext()) {
            AbstractPotion p = it.next();
            if (!(p instanceof PotionSlot)) {
                return true;
            }
        }
        return false;
    }

    public void loseRandomRelics(int amount) {
        if (amount > this.relics.size()) {
            Iterator<AbstractRelic> it = this.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onUnequip();
            }
            this.relics.clear();
            return;
        }
        for (int i = 0; i < amount; i++) {
            int index = MathUtils.random(0, this.relics.size() - 1);
            this.relics.get(index).onUnequip();
            this.relics.remove(index);
        }
        reorganizeRelics();
    }

    public boolean loseRelic(String targetID) {
        if (!hasRelic(targetID)) {
            return false;
        }
        AbstractRelic toRemove = null;
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r.relicId.equals(targetID)) {
                r.onUnequip();
                toRemove = r;
            }
        }
        if (toRemove == null) {
            logger.info("WHY WAS RELIC: " + this.name + " NOT FOUND???");
            return false;
        }
        this.relics.remove(toRemove);
        reorganizeRelics();
        return true;
    }

    public void reorganizeRelics() {
        logger.info("Reorganizing relics");
        ArrayList<AbstractRelic> tmpRelics = new ArrayList<>();
        tmpRelics.addAll(this.relics);
        this.relics.clear();
        for (int i = 0; i < tmpRelics.size(); i++) {
            tmpRelics.get(i).reorganizeObtain(this, i, false, tmpRelics.size());
        }
    }

    public AbstractRelic getRelic(String targetID) {
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r.relicId.equals(targetID)) {
                return r;
            }
        }
        return null;
    }

    public AbstractBlight getBlight(String targetID) {
        Iterator<AbstractBlight> it = this.blights.iterator();
        while (it.hasNext()) {
            AbstractBlight b = it.next();
            if (b.blightID.equals(targetID)) {
                return b;
            }
        }
        return null;
    }

    public void obtainPotion(int slot, AbstractPotion potionToObtain) {
        if (slot <= this.potionSlots) {
            this.potions.set(slot, potionToObtain);
            potionToObtain.setAsObtained(slot);
        }
    }

    public boolean obtainPotion(AbstractPotion potionToObtain) {
        int index = 0;
        Iterator<AbstractPotion> it = this.potions.iterator();
        while (it.hasNext()) {
            AbstractPotion p = it.next();
            if (p instanceof PotionSlot) {
                break;
            }
            index++;
        }
        if (index < this.potionSlots) {
            this.potions.set(index, potionToObtain);
            potionToObtain.setAsObtained(index);
            potionToObtain.flash();
            AbstractPotion.playPotionSound();
            return true;
        }
        logger.info("NOT ENOUGH POTION SLOTS");
        AbstractDungeon.topPanel.flashRed();
        return false;
    }

    public void renderRelics(SpriteBatch sb) {
        for (int i = 0; i < this.relics.size(); i++) {
            if (i / AbstractRelic.MAX_RELICS_PER_PAGE == AbstractRelic.relicPage) {
                this.relics.get(i).renderInTopPanel(sb);
            }
        }
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (r.hb.hovered) {
                r.renderTip(sb);
            }
        }
    }

    public void renderBlights(SpriteBatch sb) {
        Iterator<AbstractBlight> it = this.blights.iterator();
        while (it.hasNext()) {
            it.next().renderInTopPanel(sb);
        }
        Iterator<AbstractBlight> it2 = this.blights.iterator();
        while (it2.hasNext()) {
            AbstractBlight b = it2.next();
            if (b.hb.hovered) {
                b.renderTip(sb);
            }
        }
    }

    public void bottledCardUpgradeCheck(AbstractCard c) {
        if (c.inBottleFlame && hasRelic(BottledFlame.ID)) {
            ((BottledFlame) getRelic(BottledFlame.ID)).setDescriptionAfterLoading();
        }
        if (c.inBottleLightning && hasRelic(BottledLightning.ID)) {
            ((BottledLightning) getRelic(BottledLightning.ID)).setDescriptionAfterLoading();
        }
        if (c.inBottleTornado && hasRelic(BottledTornado.ID)) {
            ((BottledTornado) getRelic(BottledTornado.ID)).setDescriptionAfterLoading();
        }
    }

    public void triggerEvokeAnimation(int slot) {
        if (this.maxOrbs > 0) {
            this.orbs.get(slot).triggerEvokeAnimation();
        }
    }

    public void evokeOrb() {
        if (!(this.orbs.isEmpty() || (this.orbs.get(0) instanceof EmptyOrbSlot))) {
            this.orbs.get(0).onEvoke();
            AbstractOrb orbSlot = new EmptyOrbSlot();
            for (int i = 1; i < this.orbs.size(); i++) {
                Collections.swap(this.orbs, i, i - 1);
            }
            this.orbs.set(this.orbs.size() - 1, orbSlot);
            for (int i2 = 0; i2 < this.orbs.size(); i2++) {
                this.orbs.get(i2).setSlot(i2, this.maxOrbs);
            }
        }
    }

    public void evokeNewestOrb() {
        if (!this.orbs.isEmpty() && !(this.orbs.get(this.orbs.size() - 1) instanceof EmptyOrbSlot)) {
            this.orbs.get(this.orbs.size() - 1).onEvoke();
        }
    }

    public void evokeWithoutLosingOrb() {
        if (!this.orbs.isEmpty() && !(this.orbs.get(0) instanceof EmptyOrbSlot)) {
            this.orbs.get(0).onEvoke();
        }
    }

    public void removeNextOrb() {
        if (!(this.orbs.isEmpty() || (this.orbs.get(0) instanceof EmptyOrbSlot))) {
            AbstractOrb orbSlot = new EmptyOrbSlot(this.orbs.get(0).cX, this.orbs.get(0).cY);
            for (int i = 1; i < this.orbs.size(); i++) {
                Collections.swap(this.orbs, i, i - 1);
            }
            this.orbs.set(this.orbs.size() - 1, orbSlot);
            for (int i2 = 0; i2 < this.orbs.size(); i2++) {
                this.orbs.get(i2).setSlot(i2, this.maxOrbs);
            }
        }
    }

    public boolean hasEmptyOrb() {
        if (this.orbs.isEmpty()) {
            return false;
        }
        Iterator<AbstractOrb> it = this.orbs.iterator();
        while (it.hasNext()) {
            AbstractOrb o = it.next();
            if (o instanceof EmptyOrbSlot) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOrb() {
        if (!this.orbs.isEmpty() && !(this.orbs.get(0) instanceof EmptyOrbSlot)) {
            return true;
        }
        return false;
    }

    public int filledOrbCount() {
        int orbCount = 0;
        Iterator<AbstractOrb> it = this.orbs.iterator();
        while (it.hasNext()) {
            AbstractOrb o = it.next();
            if (!(o instanceof EmptyOrbSlot)) {
                orbCount++;
            }
        }
        return orbCount;
    }

    public void channelOrb(AbstractOrb orbToSet) {
        if (this.maxOrbs <= 0) {
            AbstractDungeon.effectList.add(new ThoughtBubble(this.dialogX, this.dialogY, 3.0f, MSG[4], true));
        } else if (this.maxOrbs > 0) {
            if (hasRelic(DEPRECATED_DarkCore.ID) && !(orbToSet instanceof Dark)) {
                orbToSet = new Dark();
            }
            int index = -1;
            int i = 0;
            while (true) {
                if (i >= this.orbs.size()) {
                    break;
                } else if (this.orbs.get(i) instanceof EmptyOrbSlot) {
                    index = i;
                    break;
                } else {
                    i++;
                }
            }
            if (index != -1) {
                orbToSet.cX = this.orbs.get(index).cX;
                orbToSet.cY = this.orbs.get(index).cY;
                this.orbs.set(index, orbToSet);
                this.orbs.get(index).setSlot(index, this.maxOrbs);
                orbToSet.playChannelSFX();
                Iterator<AbstractPower> it = this.powers.iterator();
                while (it.hasNext()) {
                    AbstractPower p = it.next();
                    p.onChannel(orbToSet);
                }
                AbstractDungeon.actionManager.orbsChanneledThisCombat.add(orbToSet);
                AbstractDungeon.actionManager.orbsChanneledThisTurn.add(orbToSet);
                int plasmaCount = 0;
                Iterator<AbstractOrb> it2 = AbstractDungeon.actionManager.orbsChanneledThisTurn.iterator();
                while (it2.hasNext()) {
                    AbstractOrb o = it2.next();
                    if (o instanceof Plasma) {
                        plasmaCount++;
                    }
                }
                if (plasmaCount == 9) {
                    UnlockTracker.unlockAchievement(AchievementGrid.NEON_KEY);
                }
                orbToSet.applyFocus();
                return;
            }
            AbstractDungeon.actionManager.addToTop(new ChannelAction(orbToSet));
            AbstractDungeon.actionManager.addToTop(new EvokeOrbAction(1));
            AbstractDungeon.actionManager.addToTop(new AnimateOrbAction(1));
        }
    }

    public void increaseMaxOrbSlots(int amount, boolean playSfx) {
        if (this.maxOrbs == 10) {
            AbstractDungeon.effectList.add(new ThoughtBubble(this.dialogX, this.dialogY, 3.0f, MSG[3], true));
            return;
        }
        if (playSfx) {
            CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1f);
        }
        this.maxOrbs += amount;
        for (int i = 0; i < amount; i++) {
            this.orbs.add(new EmptyOrbSlot());
        }
        for (int i2 = 0; i2 < this.orbs.size(); i2++) {
            this.orbs.get(i2).setSlot(i2, this.maxOrbs);
        }
    }

    public void decreaseMaxOrbSlots(int amount) {
        if (this.maxOrbs > 0) {
            this.maxOrbs -= amount;
            if (this.maxOrbs < 0) {
                this.maxOrbs = 0;
            }
            if (!this.orbs.isEmpty()) {
                this.orbs.remove(this.orbs.size() - 1);
            }
            for (int i = 0; i < this.orbs.size(); i++) {
                this.orbs.get(i).setSlot(i, this.maxOrbs);
            }
        }
    }

    public void applyStartOfTurnOrbs() {
        if (!this.orbs.isEmpty()) {
            Iterator<AbstractOrb> it = this.orbs.iterator();
            while (it.hasNext()) {
                AbstractOrb o = it.next();
                o.onStartOfTurn();
            }
            if (hasRelic(GoldPlatedCables.ID) && !(this.orbs.get(0) instanceof EmptyOrbSlot)) {
                this.orbs.get(0).onStartOfTurn();
            }
        }
    }

    private void updateEscapeAnimation() {
        if (this.escapeTimer != 0.0f) {
            this.escapeTimer -= Gdx.graphics.getDeltaTime();
            if (this.flipHorizontal) {
                this.drawX -= (Gdx.graphics.getDeltaTime() * 400.0f) * Settings.scale;
            } else {
                this.drawX += Gdx.graphics.getDeltaTime() * 500.0f * Settings.scale;
            }
        }
        if (this.escapeTimer < 0.0f) {
            AbstractDungeon.getCurrRoom().endBattle();
            this.flipHorizontal = false;
            this.isEscaping = false;
            this.escapeTimer = 0.0f;
        }
    }

    public boolean relicsDoneAnimating() {
        Iterator<AbstractRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            AbstractRelic r = it.next();
            if (!r.isDone) {
                return false;
            }
        }
        return true;
    }

    public void resetControllerValues() {
        if (Settings.isControllerMode) {
            this.toHover = null;
            this.hoveredCard = null;
            this.inspectMode = false;
            this.inspectHb = null;
            this.keyboardCardIndex = -1;
            this.hand.refreshHandLayout();
        }
    }

    public AbstractPotion getRandomPotion() {
        ArrayList<AbstractPotion> list = new ArrayList<>();
        Iterator<AbstractPotion> it = this.potions.iterator();
        while (it.hasNext()) {
            AbstractPotion p = it.next();
            if (!(p instanceof PotionSlot)) {
                list.add(p);
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        Collections.shuffle(list, new Random(AbstractDungeon.miscRng.randomLong()));
        return list.get(0);
    }

    public void removePotion(AbstractPotion potionOption) {
        int slot = this.potions.indexOf(potionOption);
        if (slot >= 0) {
            this.potions.set(slot, new PotionSlot(slot));
        }
    }

    public void movePosition(float x, float y) {
        this.drawX = x;
        this.drawY = y;
        this.dialogX = this.drawX + (0.0f * Settings.scale);
        this.dialogY = this.drawY + (170.0f * Settings.scale);
        this.animX = 0.0f;
        this.animY = 0.0f;
        refreshHitboxLocation();
    }

    public void switchedStance() {
        Iterator<AbstractCard> it = this.hand.group.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            c.switchedStance();
        }
        Iterator<AbstractCard> it2 = this.discardPile.group.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            c2.switchedStance();
        }
        Iterator<AbstractCard> it3 = this.drawPile.group.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            c3.switchedStance();
        }
    }

    public CharacterOption getCharacterSelectOption() {
        return null;
    }

    public void onStanceChange(String id) {
    }
}