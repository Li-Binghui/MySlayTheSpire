package com.megacrit.cardcrawl.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.gikk.twirk.types.TwitchTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.curses.Pride;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.relics.MedicalKit;
import com.megacrit.cardcrawl.relics.Necronomicon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.CardFlashVfx;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.*;


/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/cards/AbstractCard.class */
public abstract class AbstractCard implements Comparable<AbstractCard> {
    public CardType type;
    public int cost;
    public int costForTurn;
    public int price;
    public int chargeCost;
    public boolean isCostModified;
    public boolean isCostModifiedForTurn;
    public boolean retain;
    public boolean selfRetain;
    public boolean dontTriggerOnUseCard;
    public CardRarity rarity;
    public CardColor color;
    public boolean isInnate;
    public boolean isLocked;
    public boolean showEvokeValue;
    public int showEvokeOrbCount;
    public ArrayList<String> keywords;
    private static final int COMMON_CARD_PRICE = 50;
    private static final int UNCOMMON_CARD_PRICE = 75;
    private static final int RARE_CARD_PRICE = 150;
    protected boolean isUsed;
    public boolean upgraded;
    public int timesUpgraded;
    public int misc;
    public int energyOnUse;
    public boolean ignoreEnergyOnUse;
    public boolean isSeen;
    public boolean upgradedCost;
    public boolean upgradedDamage;
    public boolean upgradedBlock;
    public boolean upgradedMagicNumber;
    public UUID uuid;
    public boolean isSelected;
    public boolean exhaust;
    public boolean returnToHand;
    public boolean shuffleBackIntoDrawPile;
    public boolean isEthereal;
    public ArrayList<CardTags> tags;
    public int[] multiDamage;
    protected boolean isMultiDamage;
    public int baseDamage;
    public int baseBlock;
    public int baseMagicNumber;
    public int baseHeal;
    public int baseDraw;
    public int baseDiscard;
    public int damage;
    public int block;
    public int magicNumber;
    public int heal;
    public int draw;
    public int discard;
    public boolean isDamageModified;
    public boolean isBlockModified;
    public boolean isMagicNumberModified;
    protected DamageInfo.DamageType damageType;
    public DamageInfo.DamageType damageTypeForTurn;
    public CardTarget target;
    public boolean purgeOnUse;
    public boolean exhaustOnUseOnce;
    public boolean exhaustOnFire;
    public boolean freeToPlayOnce;
    public boolean isInAutoplay;
    private static TextureAtlas orbAtlas;
    private static TextureAtlas cardAtlas;
    private static TextureAtlas oldCardAtlas;
    public static TextureAtlas.AtlasRegion orb_red;
    public static TextureAtlas.AtlasRegion orb_green;
    public static TextureAtlas.AtlasRegion orb_blue;
    public static TextureAtlas.AtlasRegion orb_purple;
    public static TextureAtlas.AtlasRegion orb_card;
    public static TextureAtlas.AtlasRegion orb_potion;
    public static TextureAtlas.AtlasRegion orb_relic;
    public static TextureAtlas.AtlasRegion orb_special;
    public TextureAtlas.AtlasRegion portrait;
    public TextureAtlas.AtlasRegion jokePortrait;
    public static float typeWidthAttack;
    public static float typeWidthSkill;
    public static float typeWidthPower;
    public static float typeWidthCurse;
    public static float typeWidthStatus;
    public static float typeOffsetAttack;
    public static float typeOffsetSkill;
    public static float typeOffsetPower;
    public static float typeOffsetCurse;
    public static float typeOffsetStatus;
    public AbstractGameEffect flashVfx;
    public String assetUrl;
    public boolean fadingOut;
    public float transparency;
    public float targetTransparency;
    private Color goldColor;
    private Color renderColor;
    private Color textColor;
    private Color typeColor;
    public float targetAngle;
    private static final float NAME_OFFSET_Y = 175.0f;
    private static final float ENERGY_TEXT_OFFSET_X = -132.0f;
    private static final float ENERGY_TEXT_OFFSET_Y = 192.0f;
    private static final int W = 512;
    public float angle;
    private ArrayList<CardGlowBorder> glowList;
    private float glowTimer;
    public boolean isGlowing;
    public static final float SMALL_SCALE = 0.7f;
    public static final int RAW_W = 300;
    public static final int RAW_H = 420;
    public float current_x;
    public float current_y;
    public float target_x;
    public float target_y;
    protected Texture portraitImg;
    private boolean useSmallTitleFont;
    public float drawScale;
    public float targetDrawScale;
    private static final int PORTRAIT_WIDTH = 250;
    private static final int PORTRAIT_HEIGHT = 190;
    private static final float PORTRAIT_OFFSET_Y = 72.0f;
    private static final float LINE_SPACING = 1.45f;
    public boolean isFlipped;
    private boolean darken;
    private float darkTimer;
    private static final float DARKEN_TIME = 0.3f;
    public Hitbox hb;
    public float hoverTimer;
    private boolean renderTip;
    private boolean hovered;
    private float hoverDuration;
    private static final float HOVER_TIP_TIME = 0.2f;
    public AbstractCard cardsToPreview;
    protected static final float CARD_TIP_PAD = 16.0f;
    public float newGlowTimer;
    public String originalName;
    public String name;
    public String rawDescription;
    public String cardID;
    public ArrayList<DescriptionLine> description;
    public String cantUseMessage;
    private static final float TYPE_OFFSET_Y = -1.0f;
    private static final float DESC_OFFSET_Y;
    private static final float DESC_OFFSET_Y2 = -6.0f;
    private static final float DESC_BOX_WIDTH;
    private static final float CN_DESC_BOX_WIDTH;
    private static final float TITLE_BOX_WIDTH;
    private static final float TITLE_BOX_WIDTH_NO_COST;
    private static final float CARD_ENERGY_IMG_WIDTH;
    private static final float MAGIC_NUM_W;
    private static final UIStrings cardRenderStrings;
    public static final String LOCKED_STRING;
    public static final String UNKNOWN_STRING;
    private Color bgColor;
    private Color backColor;
    private Color frameColor;
    private Color frameOutlineColor;
    private Color frameShadowColor;
    private Color imgFrameColor;
    private Color descBoxColor;
    private Color bannerColor;
    private Color tintColor;
    private static final Color ENERGY_COST_RESTRICTED_COLOR;
    private static final Color ENERGY_COST_MODIFIED_COLOR;
    private static final Color FRAME_SHADOW_COLOR;
    private static final Color IMG_FRAME_COLOR_COMMON;
    private static final Color IMG_FRAME_COLOR_UNCOMMON;
    private static final Color IMG_FRAME_COLOR_RARE;
    private static final Color HOVER_IMG_COLOR;
    private static final Color SELECTED_CARD_COLOR;
    private static final Color BANNER_COLOR_COMMON;
    private static final Color BANNER_COLOR_UNCOMMON;
    private static final Color BANNER_COLOR_RARE;
    private static final Color CURSE_BG_COLOR;
    private static final Color CURSE_TYPE_BACK_COLOR;
    private static final Color CURSE_FRAME_COLOR;
    private static final Color CURSE_DESC_BOX_COLOR;
    private static final Color COLORLESS_BG_COLOR;
    private static final Color COLORLESS_TYPE_BACK_COLOR;
    private static final Color COLORLESS_FRAME_COLOR;
    private static final Color COLORLESS_DESC_BOX_COLOR;
    private static final Color RED_BG_COLOR;
    private static final Color RED_TYPE_BACK_COLOR;
    private static final Color RED_FRAME_COLOR;
    private static final Color RED_RARE_OUTLINE_COLOR;
    private static final Color RED_DESC_BOX_COLOR;
    private static final Color GREEN_BG_COLOR;
    private static final Color GREEN_TYPE_BACK_COLOR;
    private static final Color GREEN_FRAME_COLOR;
    private static final Color GREEN_RARE_OUTLINE_COLOR;
    private static final Color GREEN_DESC_BOX_COLOR;
    private static final Color BLUE_BG_COLOR;
    private static final Color BLUE_TYPE_BACK_COLOR;
    private static final Color BLUE_FRAME_COLOR;
    private static final Color BLUE_RARE_OUTLINE_COLOR;
    private static final Color BLUE_DESC_BOX_COLOR;
    protected static final Color BLUE_BORDER_GLOW_COLOR;
    protected static final Color GREEN_BORDER_GLOW_COLOR;
    protected static final Color GOLD_BORDER_GLOW_COLOR;
    public boolean inBottleFlame;
    public boolean inBottleLightning;
    public boolean inBottleTornado;
    public Color glowColor;
    private static final Logger logger = LogManager.getLogger(AbstractCard.class.getName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup");
    public static final String[] TEXT = uiStrings.TEXT;
    public static final float IMG_WIDTH = 300.0f * Settings.scale;
    public static final float IMG_HEIGHT = 420.0f * Settings.scale;
    public static final float IMG_WIDTH_S = (300.0f * Settings.scale) * 0.7f;
    public static final float IMG_HEIGHT_S = (420.0f * Settings.scale) * 0.7f;
    private static final float SHADOW_OFFSET_X = 18.0f * Settings.scale;
    private static final float SHADOW_OFFSET_Y = 14.0f * Settings.scale;
    private static final float HB_W = 300.0f * Settings.scale;
    private static final float HB_H = 420.0f * Settings.scale;
    private static final GlyphLayout gl = new GlyphLayout();
    private static final StringBuilder sbuilder = new StringBuilder();
    private static final StringBuilder sbuilder2 = new StringBuilder();

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/cards/AbstractCard$CardColor.class */
    public enum CardColor {
        RED,
        GREEN,
        BLUE,
        PURPLE,
        COLORLESS,
        CURSE
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/cards/AbstractCard$CardRarity.class */
    public enum CardRarity {
        BASIC,
        SPECIAL,
        COMMON,
        UNCOMMON,
        RARE,
        CURSE
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/cards/AbstractCard$CardTags.class */
    public enum CardTags {
        HEALING,
        STRIKE,
        EMPTY,
        STARTER_DEFEND,
        STARTER_STRIKE
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/cards/AbstractCard$CardTarget.class */
    public enum CardTarget {
        ENEMY,
        ALL_ENEMY,
        SELF,
        NONE,
        SELF_AND_ENEMY,
        ALL
    }

    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/cards/AbstractCard$CardType.class */
    public enum CardType {
        ATTACK,
        SKILL,
        POWER,
        STATUS,
        CURSE
    }

    public abstract void upgrade();

    public abstract void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster);

    public abstract AbstractCard makeCopy();

    static {
        DESC_OFFSET_Y = Settings.BIG_TEXT_MODE ? IMG_HEIGHT * 0.24f : IMG_HEIGHT * 0.255f;
        DESC_BOX_WIDTH = Settings.BIG_TEXT_MODE ? IMG_WIDTH * 0.95f : IMG_WIDTH * 0.79f;
        CN_DESC_BOX_WIDTH = Settings.BIG_TEXT_MODE ? IMG_WIDTH * 0.87f : IMG_WIDTH * 0.72f;
        TITLE_BOX_WIDTH = IMG_WIDTH * 0.6f;
        TITLE_BOX_WIDTH_NO_COST = IMG_WIDTH * 0.7f;
        CARD_ENERGY_IMG_WIDTH = 24.0f * Settings.scale;
        MAGIC_NUM_W = 20.0f * Settings.scale;
        cardRenderStrings = CardCrawlGame.languagePack.getUIString("AbstractCard");
        LOCKED_STRING = cardRenderStrings.TEXT[0];
        UNKNOWN_STRING = cardRenderStrings.TEXT[1];
        ENERGY_COST_RESTRICTED_COLOR = new Color(1.0f, 0.3f, 0.3f, 1.0f);
        ENERGY_COST_MODIFIED_COLOR = new Color(0.4f, 1.0f, 0.4f, 1.0f);
        FRAME_SHADOW_COLOR = new Color(0.0f, 0.0f, 0.0f, 0.25f);
        IMG_FRAME_COLOR_COMMON = CardHelper.getColor(53, 58, 64);
        IMG_FRAME_COLOR_UNCOMMON = CardHelper.getColor(119, 152, 161);
        IMG_FRAME_COLOR_RARE = new Color(0.855f, 0.557f, 0.32f, 1.0f);
        HOVER_IMG_COLOR = new Color(1.0f, 0.815f, 0.314f, 0.8f);
        SELECTED_CARD_COLOR = new Color(0.5f, 0.9f, 0.9f, 1.0f);
        BANNER_COLOR_COMMON = CardHelper.getColor(131, 129, 121);
        BANNER_COLOR_UNCOMMON = CardHelper.getColor(142, 196, 213);
        BANNER_COLOR_RARE = new Color(1.0f, 0.796f, 0.251f, 1.0f);
        CURSE_BG_COLOR = CardHelper.getColor(29, 29, 29);
        CURSE_TYPE_BACK_COLOR = new Color(0.23f, 0.23f, 0.23f, 1.0f);
        CURSE_FRAME_COLOR = CardHelper.getColor(21, 2, 21);
        CURSE_DESC_BOX_COLOR = CardHelper.getColor(52, 58, 64);
        COLORLESS_BG_COLOR = new Color(0.15f, 0.15f, 0.15f, 1.0f);
        COLORLESS_TYPE_BACK_COLOR = new Color(0.23f, 0.23f, 0.23f, 1.0f);
        COLORLESS_FRAME_COLOR = new Color(0.48f, 0.48f, 0.48f, 1.0f);
        COLORLESS_DESC_BOX_COLOR = new Color(0.351f, 0.363f, 0.3745f, 1.0f);
        RED_BG_COLOR = CardHelper.getColor(50, 26, 26);
        RED_TYPE_BACK_COLOR = CardHelper.getColor(91, 43, 32);
        RED_FRAME_COLOR = CardHelper.getColor(121, 12, 28);
        RED_RARE_OUTLINE_COLOR = new Color(1.0f, 0.75f, 0.43f, 1.0f);
        RED_DESC_BOX_COLOR = CardHelper.getColor(53, 58, 64);
        GREEN_BG_COLOR = CardHelper.getColor(19, 45, 40);
        GREEN_TYPE_BACK_COLOR = CardHelper.getColor(32, 91, 43);
        GREEN_FRAME_COLOR = CardHelper.getColor(52, 123, 8);
        GREEN_RARE_OUTLINE_COLOR = new Color(1.0f, 0.75f, 0.43f, 1.0f);
        GREEN_DESC_BOX_COLOR = CardHelper.getColor(53, 58, 64);
        BLUE_BG_COLOR = CardHelper.getColor(19, 45, 40);
        BLUE_TYPE_BACK_COLOR = CardHelper.getColor(32, 91, 43);
        BLUE_FRAME_COLOR = CardHelper.getColor(52, 123, 8);
        BLUE_RARE_OUTLINE_COLOR = new Color(1.0f, 0.75f, 0.43f, 1.0f);
        BLUE_DESC_BOX_COLOR = CardHelper.getColor(53, 58, 64);
        BLUE_BORDER_GLOW_COLOR = new Color(0.2f, 0.9f, 1.0f, 0.25f);
        GREEN_BORDER_GLOW_COLOR = new Color(0.0f, 1.0f, 0.0f, 0.25f);
        GOLD_BORDER_GLOW_COLOR = Color.GOLD.cpy();
    }

    public boolean isStarterStrike() {
        return hasTag(CardTags.STRIKE) && this.rarity.equals(CardRarity.BASIC);
    }

    public boolean isStarterDefend() {
        return hasTag(CardTags.STARTER_DEFEND) && this.rarity.equals(CardRarity.BASIC);
    }

    public AbstractCard(String id, String name, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        this(id, name, imgUrl, cost, rawDescription, type, color, rarity, target, DamageInfo.DamageType.NORMAL);
    }

    public AbstractCard(String id, String name, String deprecatedJokeUrl, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        this(id, name, imgUrl, cost, rawDescription, type, color, rarity, target, DamageInfo.DamageType.NORMAL);
    }

    public AbstractCard(String id, String name, String deprecatedJokeUrl, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, DamageInfo.DamageType dType) {
        this(id, name, imgUrl, cost, rawDescription, type, color, rarity, target, dType);
    }

    public AbstractCard(String id, String name, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, DamageInfo.DamageType dType) {
        this.chargeCost = -1;
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.retain = false;
        this.selfRetain = false;
        this.dontTriggerOnUseCard = false;
        this.isInnate = false;
        this.isLocked = false;
        this.showEvokeValue = false;
        this.showEvokeOrbCount = 0;
        this.keywords = new ArrayList<>();
        this.isUsed = false;
        this.upgraded = false;
        this.timesUpgraded = 0;
        this.misc = 0;
        this.ignoreEnergyOnUse = false;
        this.isSeen = true;
        this.upgradedCost = false;
        this.upgradedDamage = false;
        this.upgradedBlock = false;
        this.upgradedMagicNumber = false;
        this.isSelected = false;
        this.exhaust = false;
        this.returnToHand = false;
        this.shuffleBackIntoDrawPile = false;
        this.isEthereal = false;
        this.tags = new ArrayList<>();
        this.isMultiDamage = false;
        this.baseDamage = -1;
        this.baseBlock = -1;
        this.baseMagicNumber = -1;
        this.baseHeal = -1;
        this.baseDraw = -1;
        this.baseDiscard = -1;
        this.damage = -1;
        this.block = -1;
        this.magicNumber = -1;
        this.heal = -1;
        this.draw = -1;
        this.discard = -1;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.target = CardTarget.ENEMY;
        this.purgeOnUse = false;
        this.exhaustOnUseOnce = false;
        this.exhaustOnFire = false;
        this.freeToPlayOnce = false;
        this.isInAutoplay = false;
        this.fadingOut = false;
        this.transparency = 1.0f;
        this.targetTransparency = 1.0f;
        this.goldColor = Settings.GOLD_COLOR.cpy();
        this.renderColor = Color.WHITE.cpy();
        this.textColor = Settings.CREAM_COLOR.cpy();
        this.typeColor = new Color(0.35f, 0.35f, 0.35f, 0.0f);
        this.targetAngle = 0.0f;
        this.angle = 0.0f;
        this.glowList = new ArrayList<>();
        this.glowTimer = 0.0f;
        this.isGlowing = false;
        this.portraitImg = null;
        this.useSmallTitleFont = false;
        this.drawScale = 0.7f;
        this.targetDrawScale = 0.7f;
        this.isFlipped = false;
        this.darken = false;
        this.darkTimer = 0.0f;
        this.hb = new Hitbox(IMG_WIDTH_S, IMG_HEIGHT_S);
        this.hoverTimer = 0.0f;
        this.renderTip = false;
        this.hovered = false;
        this.hoverDuration = 0.0f;
        this.cardsToPreview = null;
        this.newGlowTimer = 0.0f;
        this.description = new ArrayList<>();
        this.inBottleFlame = false;
        this.inBottleLightning = false;
        this.inBottleTornado = false;
        this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        this.originalName = name;
        this.name = name;
        this.cardID = id;
        this.assetUrl = imgUrl;
        this.portrait = cardAtlas.findRegion(imgUrl);
        this.jokePortrait = oldCardAtlas.findRegion(imgUrl);
        if (this.portrait == null) {
            if (this.jokePortrait != null) {
                this.portrait = this.jokePortrait;
            } else {
                this.portrait = cardAtlas.findRegion("status/beta");
            }
        }
        this.cost = cost;
        this.costForTurn = cost;
        this.rawDescription = rawDescription;
        this.type = type;
        this.color = color;
        this.rarity = rarity;
        this.target = target;
        this.damageType = dType;
        this.damageTypeForTurn = dType;
        createCardImage();
        if (name == null || rawDescription == null) {
            logger.info("Card initialized incorrecty");
        }
        initializeTitle();
        initializeDescription();
        updateTransparency();
        this.uuid = UUID.randomUUID();
    }

    public static void initialize() {
        long startTime = System.currentTimeMillis();
        cardAtlas = new TextureAtlas(Gdx.files.internal("cards/cards.atlas"));
        oldCardAtlas = new TextureAtlas(Gdx.files.internal("oldCards/cards.atlas"));
        orbAtlas = new TextureAtlas(Gdx.files.internal("orbs/orb.atlas"));
        orb_red = orbAtlas.findRegion("red");
        orb_green = orbAtlas.findRegion("green");
        orb_blue = orbAtlas.findRegion("blue");
        orb_purple = orbAtlas.findRegion("purple");
        orb_card = orbAtlas.findRegion("card");
        orb_potion = orbAtlas.findRegion("potion");
        orb_relic = orbAtlas.findRegion("relic");
        orb_special = orbAtlas.findRegion("special");
        logger.info("Card Image load time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void initializeDynamicFrameWidths() {
        float d = 48.0f * Settings.scale;
        gl.setText(FontHelper.cardTypeFont, uiStrings.TEXT[0]);
        typeOffsetAttack = (gl.width - (48.0f * Settings.scale)) / 2.0f;
        typeWidthAttack = (((gl.width / d) - 1.0f) * 2.0f) + 1.0f;
        gl.setText(FontHelper.cardTypeFont, uiStrings.TEXT[1]);
        typeOffsetSkill = (gl.width - (48.0f * Settings.scale)) / 2.0f;
        typeWidthSkill = (((gl.width / d) - 1.0f) * 2.0f) + 1.0f;
        gl.setText(FontHelper.cardTypeFont, uiStrings.TEXT[2]);
        typeOffsetPower = (gl.width - (48.0f * Settings.scale)) / 2.0f;
        typeWidthPower = (((gl.width / d) - 1.0f) * 2.0f) + 1.0f;
        gl.setText(FontHelper.cardTypeFont, uiStrings.TEXT[3]);
        typeOffsetCurse = (gl.width - (48.0f * Settings.scale)) / 2.0f;
        typeWidthCurse = (((gl.width / d) - 1.0f) * 2.0f) + 1.0f;
        gl.setText(FontHelper.cardTypeFont, uiStrings.TEXT[7]);
        typeOffsetStatus = (gl.width - (48.0f * Settings.scale)) / 2.0f;
        typeWidthStatus = (((gl.width / d) - 1.0f) * 2.0f) + 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initializeTitle() {
        FontHelper.cardTitleFont.getData().setScale(1.0f);
        gl.setText(FontHelper.cardTitleFont, this.name, Color.WHITE, 0.0f, 1, false);
        if (this.cost > 0 || this.cost == -1) {
            if (gl.width > TITLE_BOX_WIDTH) {
                this.useSmallTitleFont = true;
            }
        } else if (gl.width > TITLE_BOX_WIDTH_NO_COST) {
            this.useSmallTitleFont = true;
        }
        gl.reset();
    }

    public void initializeDescription() {
        String[] split;
        this.keywords.clear();
        if (Settings.lineBreakViaCharacter) {
            initializeDescriptionCN();
            return;
        }
        this.description.clear();
        int numLines = 1;
        sbuilder.setLength(0);
        float currentWidth = 0.0f;
        for (String word : this.rawDescription.split(" ")) {
            boolean isKeyword = false;
            sbuilder2.setLength(0);
            sbuilder2.append(" ");
            if (word.length() > 0 && word.charAt(word.length() - 1) != ']' && !Character.isLetterOrDigit(word.charAt(word.length() - 1))) {
                sbuilder2.insert(0, word.charAt(word.length() - 1));
                word = word.substring(0, word.length() - 1);
            }
            String keywordTmp = dedupeKeyword(word.toLowerCase());
            if (GameDictionary.keywords.containsKey(keywordTmp)) {
                if (!this.keywords.contains(keywordTmp)) {
                    this.keywords.add(keywordTmp);
                }
                gl.reset();
                gl.setText(FontHelper.cardDescFont_N, sbuilder2);
                float tmp = gl.width;
                gl.setText(FontHelper.cardDescFont_N, word);
                gl.width += tmp;
                isKeyword = true;
            } else if (!word.isEmpty() && word.charAt(0) == '[') {
                gl.reset();
                gl.setText(FontHelper.cardDescFont_N, sbuilder2);
                gl.width += CARD_ENERGY_IMG_WIDTH;
                switch (this.color) {
                    case RED:
                        if (!this.keywords.contains("[R]")) {
                            this.keywords.add("[R]");
                            break;
                        }
                        break;
                    case GREEN:
                        if (!this.keywords.contains("[G]")) {
                            this.keywords.add("[G]");
                            break;
                        }
                        break;
                    case BLUE:
                        if (!this.keywords.contains("[B]")) {
                            this.keywords.add("[B]");
                            break;
                        }
                        break;
                    case PURPLE:
                        if (!this.keywords.contains("[W]")) {
                            this.keywords.add("[W]");
                            break;
                        }
                        break;
                    case COLORLESS:
                        if (word.equals("[W]") && !this.keywords.contains("[W]")) {
                            this.keywords.add("[W]");
                            break;
                        }
                        break;
                    default:
                        logger.info("ERROR: Tried to display an invalid energy type: " + this.color.name());
                        break;
                }
            } else if (word.equals("!D") || word.equals("!B") || word.equals("!M")) {
                gl.setText(FontHelper.cardDescFont_N, word);
            } else if (word.equals("NL")) {
                gl.width = 0.0f;
                word = "";
                this.description.add(new DescriptionLine(sbuilder.toString().trim(), currentWidth));
                currentWidth = 0.0f;
                numLines++;
                sbuilder.setLength(0);
            } else {
                gl.setText(FontHelper.cardDescFont_N, word + ((Object) sbuilder2));
            }
            if (currentWidth + gl.width > DESC_BOX_WIDTH) {
                this.description.add(new DescriptionLine(sbuilder.toString().trim(), currentWidth));
                numLines++;
                sbuilder.setLength(0);
                currentWidth = gl.width;
            } else {
                currentWidth += gl.width;
            }
            if (isKeyword) {
                sbuilder.append('*');
            }
            sbuilder.append(word).append((CharSequence) sbuilder2);
        }
        if (!sbuilder.toString().trim().isEmpty()) {
            this.description.add(new DescriptionLine(sbuilder.toString().trim(), currentWidth));
        }
        if (Settings.isDev && numLines > 5) {
            logger.info("WARNING: Card " + this.name + " has lots of text");
        }
    }

    public void initializeDescriptionCN() {
        char[] charArray;
        this.description.clear();
        int numLines = 1;
        sbuilder.setLength(0);
        float currentWidth = 0.0f;
        for (String word : this.rawDescription.split(" ")) {
            String word2 = word.trim();
            if (Settings.manualLineBreak || Settings.manualAndAutoLineBreak || !word2.contains("NL")) {
                if ((!word2.equals("NL") || sbuilder.length() != 0) && !word2.isEmpty()) {
                    String keywordTmp = dedupeKeyword(word2.toLowerCase());
                    if (GameDictionary.keywords.containsKey(keywordTmp)) {
                        if (!this.keywords.contains(keywordTmp)) {
                            this.keywords.add(keywordTmp);
                        }
                        gl.setText(FontHelper.cardDescFont_N, word2);
                        if (currentWidth + gl.width > CN_DESC_BOX_WIDTH) {
                            numLines++;
                            this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = gl.width;
                            sbuilder.append(" *").append(word2).append(" ");
                        } else {
                            sbuilder.append(" *").append(word2).append(" ");
                            currentWidth += gl.width;
                        }
                    } else if (!word2.isEmpty() && word2.charAt(0) == '[') {
                        switch (this.color) {
                            case RED:
                                if (!this.keywords.contains("[R]")) {
                                    this.keywords.add("[R]");
                                    break;
                                }
                                break;
                            case GREEN:
                                if (!this.keywords.contains("[G]")) {
                                    this.keywords.add("[G]");
                                    break;
                                }
                                break;
                            case BLUE:
                                if (!this.keywords.contains("[B]")) {
                                    this.keywords.add("[B]");
                                    break;
                                }
                                break;
                            case PURPLE:
                                if (!this.keywords.contains("[W]")) {
                                    this.keywords.add("[W]");
                                    break;
                                }
                                break;
                            case COLORLESS:
                                if (!this.keywords.contains("[W]")) {
                                    this.keywords.add("[W]");
                                    break;
                                }
                                break;
                            default:
                                logger.info("ERROR: Tried to display an invalid energy type: " + this.color.name());
                                break;
                        }
                        if (currentWidth + CARD_ENERGY_IMG_WIDTH > CN_DESC_BOX_WIDTH) {
                            numLines++;
                            this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = CARD_ENERGY_IMG_WIDTH;
                            sbuilder.append(" ").append(word2).append(" ");
                        } else {
                            sbuilder.append(" ").append(word2).append(" ");
                            currentWidth += CARD_ENERGY_IMG_WIDTH;
                        }
                    } else if (word2.equals("!D!")) {
                        if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH) {
                            numLines++;
                            this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = MAGIC_NUM_W;
                            sbuilder.append(" D ");
                        } else {
                            sbuilder.append(" D ");
                            currentWidth += MAGIC_NUM_W;
                        }
                    } else if (word2.equals("!B!")) {
                        if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH) {
                            numLines++;
                            this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = MAGIC_NUM_W;
                            sbuilder.append(" ").append(word2).append("! ");
                        } else {
                            sbuilder.append(" ").append(word2).append("! ");
                            currentWidth += MAGIC_NUM_W;
                        }
                    } else if (word2.equals("!M!")) {
                        if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH) {
                            numLines++;
                            this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = MAGIC_NUM_W;
                            sbuilder.append(" ").append(word2).append("! ");
                        } else {
                            sbuilder.append(" ").append(word2).append("! ");
                            currentWidth += MAGIC_NUM_W;
                        }
                    } else if ((Settings.manualLineBreak || Settings.manualAndAutoLineBreak) && word2.equals("NL") && sbuilder.length() != 0) {
                        gl.width = 0.0f;
                        this.description.add(new DescriptionLine(sbuilder.toString().trim(), currentWidth));
                        currentWidth = 0.0f;
                        numLines++;
                        sbuilder.setLength(0);
                    } else if (word2.charAt(0) == '*') {
                        gl.setText(FontHelper.cardDescFont_N, word2.substring(1));
                        if (currentWidth + gl.width > CN_DESC_BOX_WIDTH) {
                            numLines++;
                            this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = gl.width;
                            sbuilder.append(" ").append(word2).append(" ");
                        } else {
                            sbuilder.append(" ").append(word2).append(" ");
                            currentWidth += gl.width;
                        }
                    } else {
                        for (char c : word2.trim().toCharArray()) {
                            gl.setText(FontHelper.cardDescFont_N, String.valueOf(c));
                            sbuilder.append(c);
                            if (!Settings.manualLineBreak) {
                                if (currentWidth + gl.width > CN_DESC_BOX_WIDTH) {
                                    numLines++;
                                    this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                                    sbuilder.setLength(0);
                                    currentWidth = gl.width;
                                } else {
                                    currentWidth += gl.width;
                                }
                            }
                        }
                    }
                } else {
                    currentWidth = 0.0f;
                }
            }
        }
        if (sbuilder.length() != 0) {
            this.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
        }
        int removeLine = -1;
        for (int i = 0; i < this.description.size(); i++) {
            if (this.description.get(i).text.equals(LocalizedStrings.PERIOD)) {
                StringBuilder sb = new StringBuilder();
                DescriptionLine descriptionLine = this.description.get(i - 1);
                descriptionLine.text = sb.append(descriptionLine.text).append(LocalizedStrings.PERIOD).toString();
                removeLine = i;
            }
        }
        if (removeLine != -1) {
            this.description.remove(removeLine);
        }
        if (Settings.isDev && numLines > 5) {
            logger.info("WARNING: Card " + this.name + " has lots of text");
        }
    }

    public boolean hasTag(CardTags tagToCheck) {
        if (this.tags.contains(tagToCheck)) {
            return true;
        }
        return false;
    }

    public boolean canUpgrade() {
        if (this.type == CardType.CURSE || this.type == CardType.STATUS || this.upgraded) {
            return false;
        }
        return true;
    }

    public void displayUpgrades() {
        if (this.upgradedCost) {
            this.isCostModified = true;
        }
        if (this.upgradedDamage) {
            this.damage = this.baseDamage;
            this.isDamageModified = true;
        }
        if (this.upgradedBlock) {
            this.block = this.baseBlock;
            this.isBlockModified = true;
        }
        if (this.upgradedMagicNumber) {
            this.magicNumber = this.baseMagicNumber;
            this.isMagicNumberModified = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void upgradeDamage(int amount) {
        this.baseDamage += amount;
        this.upgradedDamage = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void upgradeBlock(int amount) {
        this.baseBlock += amount;
        this.upgradedBlock = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void upgradeMagicNumber(int amount) {
        this.baseMagicNumber += amount;
        this.magicNumber = this.baseMagicNumber;
        this.upgradedMagicNumber = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void upgradeName() {
        /*  860 */     this.timesUpgraded++;
        /*  861 */     this.upgraded = true;
        /*  862 */     this.name += "+";
        /*  863 */     initializeTitle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void upgradeBaseCost(int newBaseCost) {
        int diff = this.costForTurn - this.cost;
        this.cost = newBaseCost;
        if (this.costForTurn > 0) {
            this.costForTurn = this.cost + diff;
        }
        if (this.costForTurn < 0) {
            this.costForTurn = 0;
        }
        this.upgradedCost = true;
    }

    private String dedupeKeyword(String keyword) {
        String retVal = GameDictionary.parentWord.get(keyword);
        if (retVal != null) {
            return retVal;
        }
        return keyword;
    }

    public AbstractCard(AbstractCard c) {
        this.chargeCost = -1;
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.retain = false;
        this.selfRetain = false;
        this.dontTriggerOnUseCard = false;
        this.isInnate = false;
        this.isLocked = false;
        this.showEvokeValue = false;
        this.showEvokeOrbCount = 0;
        this.keywords = new ArrayList<>();
        this.isUsed = false;
        this.upgraded = false;
        this.timesUpgraded = 0;
        this.misc = 0;
        this.ignoreEnergyOnUse = false;
        this.isSeen = true;
        this.upgradedCost = false;
        this.upgradedDamage = false;
        this.upgradedBlock = false;
        this.upgradedMagicNumber = false;
        this.isSelected = false;
        this.exhaust = false;
        this.returnToHand = false;
        this.shuffleBackIntoDrawPile = false;
        this.isEthereal = false;
        this.tags = new ArrayList<>();
        this.isMultiDamage = false;
        this.baseDamage = -1;
        this.baseBlock = -1;
        this.baseMagicNumber = -1;
        this.baseHeal = -1;
        this.baseDraw = -1;
        this.baseDiscard = -1;
        this.damage = -1;
        this.block = -1;
        this.magicNumber = -1;
        this.heal = -1;
        this.draw = -1;
        this.discard = -1;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.target = CardTarget.ENEMY;
        this.purgeOnUse = false;
        this.exhaustOnUseOnce = false;
        this.exhaustOnFire = false;
        this.freeToPlayOnce = false;
        this.isInAutoplay = false;
        this.fadingOut = false;
        this.transparency = 1.0f;
        this.targetTransparency = 1.0f;
        this.goldColor = Settings.GOLD_COLOR.cpy();
        this.renderColor = Color.WHITE.cpy();
        this.textColor = Settings.CREAM_COLOR.cpy();
        this.typeColor = new Color(0.35f, 0.35f, 0.35f, 0.0f);
        this.targetAngle = 0.0f;
        this.angle = 0.0f;
        this.glowList = new ArrayList<>();
        this.glowTimer = 0.0f;
        this.isGlowing = false;
        this.portraitImg = null;
        this.useSmallTitleFont = false;
        this.drawScale = 0.7f;
        this.targetDrawScale = 0.7f;
        this.isFlipped = false;
        this.darken = false;
        this.darkTimer = 0.0f;
        this.hb = new Hitbox(IMG_WIDTH_S, IMG_HEIGHT_S);
        this.hoverTimer = 0.0f;
        this.renderTip = false;
        this.hovered = false;
        this.hoverDuration = 0.0f;
        this.cardsToPreview = null;
        this.newGlowTimer = 0.0f;
        this.description = new ArrayList<>();
        this.inBottleFlame = false;
        this.inBottleLightning = false;
        this.inBottleTornado = false;
        this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        this.name = c.name;
        this.rawDescription = c.rawDescription;
        this.cost = c.cost;
        this.type = c.type;
    }

    private void createCardImage() {
        switch (this.color) {
            case RED:
                this.bgColor = RED_BG_COLOR.cpy();
                this.backColor = RED_TYPE_BACK_COLOR.cpy();
                this.frameColor = RED_FRAME_COLOR.cpy();
                this.frameOutlineColor = RED_RARE_OUTLINE_COLOR.cpy();
                this.descBoxColor = RED_DESC_BOX_COLOR.cpy();
                break;
            case GREEN:
                this.bgColor = GREEN_BG_COLOR.cpy();
                this.backColor = GREEN_TYPE_BACK_COLOR.cpy();
                this.frameColor = GREEN_FRAME_COLOR.cpy();
                this.frameOutlineColor = GREEN_RARE_OUTLINE_COLOR.cpy();
                this.descBoxColor = GREEN_DESC_BOX_COLOR.cpy();
                break;
            case BLUE:
                this.bgColor = BLUE_BG_COLOR.cpy();
                this.backColor = BLUE_TYPE_BACK_COLOR.cpy();
                this.frameColor = BLUE_FRAME_COLOR.cpy();
                this.frameOutlineColor = BLUE_RARE_OUTLINE_COLOR.cpy();
                this.descBoxColor = BLUE_DESC_BOX_COLOR.cpy();
            case PURPLE:
                this.bgColor = BLUE_BG_COLOR.cpy();
                this.backColor = BLUE_TYPE_BACK_COLOR.cpy();
                this.frameColor = BLUE_FRAME_COLOR.cpy();
                this.frameOutlineColor = BLUE_RARE_OUTLINE_COLOR.cpy();
                this.descBoxColor = BLUE_DESC_BOX_COLOR.cpy();
                break;
            case COLORLESS:
                this.bgColor = COLORLESS_BG_COLOR.cpy();
                this.backColor = COLORLESS_TYPE_BACK_COLOR.cpy();
                this.frameColor = COLORLESS_FRAME_COLOR.cpy();
                this.frameOutlineColor = Color.WHITE.cpy();
                this.descBoxColor = COLORLESS_DESC_BOX_COLOR.cpy();
                break;
            case CURSE:
                this.bgColor = CURSE_BG_COLOR.cpy();
                this.backColor = CURSE_TYPE_BACK_COLOR.cpy();
                this.frameColor = CURSE_FRAME_COLOR.cpy();
                this.descBoxColor = CURSE_DESC_BOX_COLOR.cpy();
                break;
            default:
                logger.info("ERROR: Card color was NOT set for " + this.name);
                break;
        }
        if (this.rarity == CardRarity.COMMON || this.rarity == CardRarity.BASIC || this.rarity == CardRarity.CURSE) {
            this.bannerColor = BANNER_COLOR_COMMON.cpy();
            this.imgFrameColor = IMG_FRAME_COLOR_COMMON.cpy();
        } else if (this.rarity == CardRarity.UNCOMMON) {
            this.bannerColor = BANNER_COLOR_UNCOMMON.cpy();
            this.imgFrameColor = IMG_FRAME_COLOR_UNCOMMON.cpy();
        } else {
            this.bannerColor = BANNER_COLOR_RARE.cpy();
            this.imgFrameColor = IMG_FRAME_COLOR_RARE.cpy();
        }
        this.tintColor = CardHelper.getColor(43, 37, 65);
        this.tintColor.a = 0.0f;
        this.frameShadowColor = FRAME_SHADOW_COLOR.cpy();
    }

    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = makeStatEquivalentCopy();
        card.uuid = this.uuid;
        return card;
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = makeCopy();
        for (int i = 0; i < this.timesUpgraded; i++) {
            card.upgrade();
        }
        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.freeToPlayOnce = this.freeToPlayOnce;
        return card;
    }

    public void onRemoveFromMasterDeck() {
    }

    public boolean cardPlayable(AbstractMonster m) {
        if (((this.target != CardTarget.ENEMY && this.target != CardTarget.SELF_AND_ENEMY) || m == null || !m.isDying) && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            return true;
        }
        this.cantUseMessage = null;
        return false;
    }

    public boolean hasEnoughEnergy() {
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.cantUseMessage = TEXT[9];
            return false;
        }
        Iterator<AbstractPower> it = AbstractDungeon.player.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            if (!p.canPlayCard(this)) {
                this.cantUseMessage = TEXT[13];
                return false;
            }
        }
        if (!AbstractDungeon.player.hasPower(EntanglePower.POWER_ID) || this.type != CardType.ATTACK) {
            Iterator<AbstractRelic> it2 = AbstractDungeon.player.relics.iterator();
            while (it2.hasNext()) {
                AbstractRelic r = it2.next();
                if (!r.canPlay(this)) {
                    return false;
                }
            }
            Iterator<AbstractBlight> it3 = AbstractDungeon.player.blights.iterator();
            while (it3.hasNext()) {
                AbstractBlight b = it3.next();
                if (!b.canPlay(this)) {
                    return false;
                }
            }
            Iterator<AbstractCard> it4 = AbstractDungeon.player.hand.group.iterator();
            while (it4.hasNext()) {
                AbstractCard c = it4.next();
                if (!c.canPlay(this)) {
                    return false;
                }
            }
            if (EnergyPanel.totalCount >= this.costForTurn || freeToPlay() || this.isInAutoplay) {
                return true;
            }
            this.cantUseMessage = TEXT[11];
            return false;
        }
        this.cantUseMessage = TEXT[10];
        return false;
    }

    public void tookDamage() {
    }

    public void didDiscard() {
    }

    public void switchedStance() {
    }

    @Deprecated
    protected void useBlueCandle(AbstractPlayer p) {
    }

    @Deprecated
    protected void useMedicalKit(AbstractPlayer p) {
    }

    public boolean canPlay(AbstractCard card) {
        return true;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (this.type == CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic(MedicalKit.ID)) {
            return false;
        }
        if ((this.type != CardType.CURSE || this.costForTurn >= -1 || AbstractDungeon.player.hasRelic(BlueCandle.ID)) && cardPlayable(m) && hasEnoughEnergy()) {
            return true;
        }
        return false;
    }

    public void update() {
        updateFlashVfx();
        if (this.hoverTimer != 0.0f) {
            this.hoverTimer -= Gdx.graphics.getDeltaTime();
            if (this.hoverTimer < 0.0f) {
                this.hoverTimer = 0.0f;
            }
        }
        if (AbstractDungeon.player != null && AbstractDungeon.player.isDraggingCard && this == AbstractDungeon.player.hoveredCard) {
            this.current_x = MathHelper.cardLerpSnap(this.current_x, this.target_x);
            this.current_y = MathHelper.cardLerpSnap(this.current_y, this.target_y);
            if (AbstractDungeon.player.hasRelic(Necronomicon.ID)) {
                if (this.cost < 2 || this.type != CardType.ATTACK || !AbstractDungeon.player.getRelic(Necronomicon.ID).checkTrigger()) {
                    AbstractDungeon.player.getRelic(Necronomicon.ID).stopPulse();
                } else {
                    AbstractDungeon.player.getRelic(Necronomicon.ID).beginLongPulse();
                }
            }
        }
        if (Settings.FAST_MODE) {
            this.current_x = MathHelper.cardLerpSnap(this.current_x, this.target_x);
            this.current_y = MathHelper.cardLerpSnap(this.current_y, this.target_y);
        }
        this.current_x = MathHelper.cardLerpSnap(this.current_x, this.target_x);
        this.current_y = MathHelper.cardLerpSnap(this.current_y, this.target_y);
        this.hb.move(this.current_x, this.current_y);
        this.hb.resize(HB_W * this.drawScale, HB_H * this.drawScale);
        if (!this.hb.clickStarted || !this.hb.hovered) {
            this.drawScale = MathHelper.cardScaleLerpSnap(this.drawScale, this.targetDrawScale);
        } else {
            this.drawScale = MathHelper.cardScaleLerpSnap(this.drawScale, this.targetDrawScale * 0.9f);
            this.drawScale = MathHelper.cardScaleLerpSnap(this.drawScale, this.targetDrawScale * 0.9f);
        }
        if (this.angle != this.targetAngle) {
            this.angle = MathHelper.angleLerpSnap(this.angle, this.targetAngle);
        }
        updateTransparency();
        updateColor();
    }

    private void updateFlashVfx() {
        if (this.flashVfx != null) {
            this.flashVfx.update();
            if (this.flashVfx.isDone) {
                this.flashVfx = null;
            }
        }
    }

    private void updateGlow() {
        if (this.isGlowing) {
            this.glowTimer -= Gdx.graphics.getDeltaTime();
            if (this.glowTimer < 0.0f) {
                this.glowList.add(new CardGlowBorder(this, this.glowColor));
                this.glowTimer = 0.3f;
            }
        }
        Iterator<CardGlowBorder> i = this.glowList.iterator();
        while (i.hasNext()) {
            CardGlowBorder e = i.next();
            e.update();
            if (e.isDone) {
                i.remove();
            }
        }
    }

    public boolean isHoveredInHand(float scale) {
        if (this.hoverTimer > 0.0f) {
            return false;
        }
        int x = InputHelper.mX;
        int y = InputHelper.mY;
        return ((float) x) > this.current_x - ((IMG_WIDTH * scale) / 2.0f) && ((float) x) < this.current_x + ((IMG_WIDTH * scale) / 2.0f) && ((float) y) > this.current_y - ((IMG_HEIGHT * scale) / 2.0f) && ((float) y) < this.current_y + ((IMG_HEIGHT * scale) / 2.0f);
    }

    private boolean isOnScreen() {
        return this.current_y >= (-200.0f) * Settings.scale && this.current_y <= ((float) Settings.HEIGHT) + (200.0f * Settings.scale);
    }

    public void render(SpriteBatch sb) {
        if (!Settings.hideCards) {
            render(sb, false);
        }
    }

    public void renderHoverShadow(SpriteBatch sb) {
        if (!Settings.hideCards) {
            renderHelper(sb, Settings.TWO_THIRDS_TRANSPARENT_BLACK_COLOR, ImageMaster.CARD_SUPER_SHADOW, this.current_x, this.current_y, 1.15f);
        }
    }

    public void renderInLibrary(SpriteBatch sb) {
        if (isOnScreen()) {
            if (!SingleCardViewPopup.isViewingUpgrade || !this.isSeen || this.isLocked) {
                updateGlow();
                renderGlow(sb);
                renderImage(sb, this.hovered, false);
                renderType(sb);
                renderTitle(sb);
                if (Settings.lineBreakViaCharacter) {
                    renderDescriptionCN(sb);
                } else {
                    renderDescription(sb);
                }
                renderTint(sb);
                renderEnergy(sb);
                this.hb.render(sb);
                return;
            }
            AbstractCard copy = makeCopy();
            copy.current_x = this.current_x;
            copy.current_y = this.current_y;
            copy.drawScale = this.drawScale;
            copy.upgrade();
            copy.displayUpgrades();
            copy.render(sb);
        }
    }

    public void render(SpriteBatch sb, boolean selected) {
        if (!Settings.hideCards) {
            if (this.flashVfx != null) {
                this.flashVfx.render(sb);
            }
            renderCard(sb, this.hovered, selected);
            this.hb.render(sb);
        }
    }

    public void renderUpgradePreview(SpriteBatch sb) {
        /* 1275 */     this.upgraded = true;
        /* 1276 */     this.name = this.originalName + "+";
        /* 1277 */     initializeTitle();
        /* 1278 */     renderCard(sb, this.hovered, false);
        /* 1279 */     this.name = this.originalName;
        /* 1280 */     initializeTitle();
        /* 1281 */     this.upgraded = false;
        /* 1282 */     resetAttributes();

    }

    public void renderWithSelections(SpriteBatch sb) {
        renderCard(sb, false, true);
    }

    private void renderCard(SpriteBatch sb, boolean hovered, boolean selected) {
        if (!Settings.hideCards && isOnScreen()) {
            if (!this.isFlipped) {
                updateGlow();
                renderGlow(sb);
                renderImage(sb, hovered, selected);
                renderTitle(sb);
                renderType(sb);
                if (Settings.lineBreakViaCharacter) {
                    renderDescriptionCN(sb);
                } else {
                    renderDescription(sb);
                }
                renderTint(sb);
                renderEnergy(sb);
                return;
            }
            renderBack(sb, hovered, selected);
            this.hb.render(sb);
        }
    }

    private void renderTint(SpriteBatch sb) {
        if (!Settings.hideCards) {
            TextureAtlas.AtlasRegion cardBgImg = getCardBgAtlas();
            if (cardBgImg != null) {
                renderHelper(sb, this.tintColor, cardBgImg, this.current_x, this.current_y);
            } else {
                renderHelper(sb, this.tintColor, getCardBg(), this.current_x - 256.0f, this.current_y - 256.0f);
            }
        }
    }

    public void renderOuterGlow(SpriteBatch sb) {
        if (AbstractDungeon.player != null && Settings.hideCards) {
            renderHelper(sb, AbstractDungeon.player.getCardRenderColor(), getCardBgAtlas(), this.current_x, this.current_y, 1.0f + (this.tintColor.a / 5.0f));
        }
    }

    public Texture getCardBg() {
        if (10 >= 5) {
            return null;
        }
        System.out.println("Add special logic here");
        return null;
    }

    public TextureAtlas.AtlasRegion getCardBgAtlas() {
        switch (this.type) {
            case ATTACK:
                return ImageMaster.CARD_ATTACK_BG_SILHOUETTE;
            case CURSE:
            case STATUS:
            case SKILL:
                return ImageMaster.CARD_SKILL_BG_SILHOUETTE;
            case POWER:
                return ImageMaster.CARD_POWER_BG_SILHOUETTE;
            default:
                return null;
        }
    }

    private void renderGlow(SpriteBatch sb) {
        if (!Settings.hideCards) {
            renderMainBorder(sb);
            Iterator<CardGlowBorder> it = this.glowList.iterator();
            while (it.hasNext()) {
                AbstractGameEffect e = it.next();
                e.render(sb);
            }
            sb.setBlendFunction(770, 771);
        }
    }

    public void beginGlowing() {
        this.isGlowing = true;
    }

    public void stopGlowing() {
        this.isGlowing = false;
        Iterator<CardGlowBorder> it = this.glowList.iterator();
        while (it.hasNext()) {
            CardGlowBorder e = it.next();
            e.duration /= 5.0f;
        }
    }

    private void renderMainBorder(SpriteBatch sb) {
        TextureAtlas.AtlasRegion img;
        if (this.isGlowing) {
            sb.setBlendFunction(770, 1);
            switch (this.type) {
                case ATTACK:
                    img = ImageMaster.CARD_ATTACK_BG_SILHOUETTE;
                    break;
                case POWER:
                    img = ImageMaster.CARD_POWER_BG_SILHOUETTE;
                    break;
                default:
                    img = ImageMaster.CARD_SKILL_BG_SILHOUETTE;
                    break;
            }
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                sb.setColor(this.glowColor);
            } else {
                sb.setColor(GREEN_BORDER_GLOW_COLOR);
            }
            sb.draw(img, (this.current_x + img.offsetX) - (img.originalWidth / 2.0f), (this.current_y + img.offsetY) - (img.originalWidth / 2.0f), (img.originalWidth / 2.0f) - img.offsetX, (img.originalWidth / 2.0f) - img.offsetY, img.packedWidth, img.packedHeight, this.drawScale * Settings.scale * 1.04f, this.drawScale * Settings.scale * 1.03f, this.angle);
        }
    }

    private void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, (drawX + img.offsetX) - (img.originalWidth / 2.0f), (drawY + img.offsetY) - (img.originalHeight / 2.0f), (img.originalWidth / 2.0f) - img.offsetX, (img.originalHeight / 2.0f) - img.offsetY, img.packedWidth, img.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
    }

    private void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY, float scale) {
        sb.setColor(color);
        sb.draw(img, (drawX + img.offsetX) - (img.originalWidth / 2.0f), (drawY + img.offsetY) - (img.originalHeight / 2.0f), (img.originalWidth / 2.0f) - img.offsetX, (img.originalHeight / 2.0f) - img.offsetY, img.packedWidth, img.packedHeight, this.drawScale * Settings.scale * scale, this.drawScale * Settings.scale * scale, this.angle);
    }

    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX + 256.0f, drawY + 256.0f, 256.0f, 256.0f, 512.0f, 512.0f, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }

    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY, float scale) {
        sb.setColor(color);
        sb.draw(img, drawX, drawY, 256.0f, 256.0f, 512.0f, 512.0f, this.drawScale * Settings.scale * scale, this.drawScale * Settings.scale * scale, this.angle, 0, 0, 512, 512, false, false);
    }

    public void renderSmallEnergy(SpriteBatch sb, TextureAtlas.AtlasRegion region, float x, float y) {
        sb.setColor(this.renderColor);
        sb.draw(region.getTexture(), this.current_x + (x * Settings.scale * this.drawScale) + (region.offsetX * Settings.scale), this.current_y + (y * Settings.scale * this.drawScale) + (region.offsetY * Settings.scale), 0.0f, 0.0f, region.packedWidth, region.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, 0.0f, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
    }

    private void renderImage(SpriteBatch sb, boolean hovered, boolean selected) {
        if (AbstractDungeon.player != null) {
            if (selected) {
                renderHelper(sb, Color.SKY, getCardBgAtlas(), this.current_x, this.current_y, 1.03f);
            }
            renderHelper(sb, this.frameShadowColor, getCardBgAtlas(), this.current_x + (SHADOW_OFFSET_X * this.drawScale), this.current_y - (SHADOW_OFFSET_Y * this.drawScale));
            if (AbstractDungeon.player.hoveredCard == this && ((AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.isHoveringDropZone) || AbstractDungeon.player.inSingleTargetMode)) {
                renderHelper(sb, HOVER_IMG_COLOR, getCardBgAtlas(), this.current_x, this.current_y);
            } else if (selected) {
                renderHelper(sb, SELECTED_CARD_COLOR, getCardBgAtlas(), this.current_x, this.current_y);
            }
        }
        renderCardBg(sb, this.current_x, this.current_y);
        if (UnlockTracker.betaCardPref.getBoolean(this.cardID, false) || Settings.PLAYTESTER_ART_MODE) {
            renderJokePortrait(sb);
        } else {
            renderPortrait(sb);
        }
        renderPortraitFrame(sb, this.current_x, this.current_y);
        renderBannerImage(sb, this.current_x, this.current_y);
    }

    private void renderCardBg(SpriteBatch sb, float x, float y) {
        switch (this.type) {
            case ATTACK:
                renderAttackBg(sb, x, y);
                return;
            case CURSE:
                renderSkillBg(sb, x, y);
                return;
            case STATUS:
                renderSkillBg(sb, x, y);
                return;
            case SKILL:
                renderSkillBg(sb, x, y);
                return;
            case POWER:
                renderPowerBg(sb, x, y);
                return;
            default:
                return;
        }
    }

    private void renderAttackBg(SpriteBatch sb, float x, float y) {
        switch (this.color) {
            case RED:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_RED, x, y);
                return;
            case GREEN:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_GREEN, x, y);
                return;
            case BLUE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_BLUE, x, y);
                return;
            case PURPLE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_PURPLE, x, y);
                return;
            case COLORLESS:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_GRAY, x, y);
                return;
            case CURSE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                return;
            default:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                return;
        }
    }

    private void renderSkillBg(SpriteBatch sb, float x, float y) {
        switch (this.color) {
            case RED:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_RED, x, y);
                return;
            case GREEN:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_GREEN, x, y);
                return;
            case BLUE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLUE, x, y);
                return;
            case PURPLE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_PURPLE, x, y);
                return;
            case COLORLESS:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_GRAY, x, y);
                return;
            case CURSE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                return;
            default:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                return;
        }
    }

    private void renderPowerBg(SpriteBatch sb, float x, float y) {
        switch (this.color) {
            case RED:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_RED, x, y);
                return;
            case GREEN:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_GREEN, x, y);
                return;
            case BLUE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_BLUE, x, y);
                return;
            case PURPLE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_PURPLE, x, y);
                return;
            case COLORLESS:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_GRAY, x, y);
                return;
            case CURSE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                return;
            default:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                return;
        }
    }

    private void renderPortraitFrame(SpriteBatch sb, float x, float y) {
        float tWidth = 0.0f;
        float tOffset = 0.0f;
        switch (this.type) {
            case ATTACK:
                renderAttackPortrait(sb, x, y);
                tWidth = typeWidthAttack;
                tOffset = typeOffsetAttack;
                break;
            case CURSE:
                renderSkillPortrait(sb, x, y);
                tWidth = typeWidthCurse;
                tOffset = typeOffsetCurse;
                break;
            case STATUS:
                renderSkillPortrait(sb, x, y);
                tWidth = typeWidthStatus;
                tOffset = typeOffsetStatus;
                break;
            case SKILL:
                renderSkillPortrait(sb, x, y);
                tWidth = typeWidthSkill;
                tOffset = typeOffsetSkill;
                break;
            case POWER:
                renderPowerPortrait(sb, x, y);
                tWidth = typeWidthPower;
                tOffset = typeOffsetPower;
                break;
        }
        renderDynamicFrame(sb, x, y, tOffset, tWidth);
    }

    private void renderAttackPortrait(SpriteBatch sb, float x, float y) {
        switch (this.rarity) {
            case BASIC:
            case CURSE:
            case SPECIAL:
            case COMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_ATTACK_COMMON, x, y);
                return;
            case UNCOMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_ATTACK_UNCOMMON, x, y);
                return;
            case RARE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_ATTACK_RARE, x, y);
                return;
            default:
                return;
        }
    }

    private void renderDynamicFrame(SpriteBatch sb, float x, float y, float typeOffset, float typeWidth) {
        if (typeWidth > 1.1f) {
            switch (this.rarity) {
                case BASIC:
                case CURSE:
                case SPECIAL:
                case COMMON:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_MID, x, y, 0.0f, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_LEFT, x, y, -typeOffset, 1.0f);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_RIGHT, x, y, typeOffset, 1.0f);
                    return;
                case UNCOMMON:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_MID, x, y, 0.0f, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_LEFT, x, y, -typeOffset, 1.0f);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_RIGHT, x, y, typeOffset, 1.0f);
                    return;
                case RARE:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_MID, x, y, 0.0f, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_LEFT, x, y, -typeOffset, 1.0f);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_RIGHT, x, y, typeOffset, 1.0f);
                    return;
                default:
                    return;
            }
        }
    }

    private void dynamicFrameRenderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float x, float y, float xOffset, float xScale) {
        sb.draw(img, ((x + img.offsetX) - (img.originalWidth / 2.0f)) + (xOffset * this.drawScale), (y + img.offsetY) - (img.originalHeight / 2.0f), (img.originalWidth / 2.0f) - img.offsetX, (img.originalHeight / 2.0f) - img.offsetY, img.packedWidth, img.packedHeight, this.drawScale * Settings.scale * xScale, this.drawScale * Settings.scale, this.angle);
    }

    private void dynamicFrameRenderHelper(SpriteBatch sb, Texture img, float x, float y, float xOffset, float xScale) {
        sb.draw(img, x + (xOffset * this.drawScale), y, 256.0f, 256.0f, 512.0f, 512.0f, this.drawScale * Settings.scale * xScale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }

    private void renderSkillPortrait(SpriteBatch sb, float x, float y) {
        switch (this.rarity) {
            case BASIC:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_COMMON, x, y);
                return;
            case CURSE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_COMMON, x, y);
                return;
            case SPECIAL:
            default:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_COMMON, x, y);
                return;
            case COMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_COMMON, x, y);
                return;
            case UNCOMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_UNCOMMON, x, y);
                return;
            case RARE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_RARE, x, y);
                return;
        }
    }

    private void renderPowerPortrait(SpriteBatch sb, float x, float y) {
        switch (this.rarity) {
            case BASIC:
            case CURSE:
            case SPECIAL:
            case COMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_POWER_COMMON, x, y);
                return;
            case UNCOMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_POWER_UNCOMMON, x, y);
                return;
            case RARE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_POWER_RARE, x, y);
                return;
            default:
                return;
        }
    }

    private void renderBannerImage(SpriteBatch sb, float drawX, float drawY) {
        switch (this.rarity) {
            case BASIC:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_BANNER_COMMON, drawX, drawY);
                return;
            case CURSE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_BANNER_COMMON, drawX, drawY);
                return;
            case SPECIAL:
            default:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_BANNER_COMMON, drawX, drawY);
                return;
            case COMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_BANNER_COMMON, drawX, drawY);
                return;
            case UNCOMMON:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_BANNER_UNCOMMON, drawX, drawY);
                return;
            case RARE:
                renderHelper(sb, this.renderColor, ImageMaster.CARD_BANNER_RARE, drawX, drawY);
                return;
        }
    }

    private void renderBack(SpriteBatch sb, boolean hovered, boolean selected) {
        renderHelper(sb, this.renderColor, ImageMaster.CARD_BACK, this.current_x, this.current_y);
    }

    private void renderPortrait(SpriteBatch sb) {
        float drawX = this.current_x - 125.0f;
        float drawY = this.current_y - 95.0f;
        Texture img = null;
        if (this.portraitImg != null) {
            img = this.portraitImg;
        }
        if (this.isLocked) {
            sb.draw(this.portraitImg, drawX, drawY + PORTRAIT_OFFSET_Y, 125.0f, 23.0f, 250.0f, 190.0f, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
        } else if (this.portrait != null) {
            float drawX2 = this.current_x - (this.portrait.packedWidth / 2.0f);
            float drawY2 = this.current_y - (this.portrait.packedHeight / 2.0f);
            sb.setColor(this.renderColor);
            sb.draw(this.portrait, drawX2, drawY2 + PORTRAIT_OFFSET_Y, this.portrait.packedWidth / 2.0f, (this.portrait.packedHeight / 2.0f) - PORTRAIT_OFFSET_Y, this.portrait.packedWidth, this.portrait.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
        } else if (img != null) {
            sb.setColor(this.renderColor);
            sb.draw(img, drawX, drawY + PORTRAIT_OFFSET_Y, 125.0f, 23.0f, 250.0f, 190.0f, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
        }
    }

    private void renderJokePortrait(SpriteBatch sb) {
        float drawX = this.current_x - 125.0f;
        float drawY = this.current_y - 95.0f;
        Texture img = null;
        if (this.portraitImg != null) {
            img = this.portraitImg;
        }
        if (this.isLocked) {
            sb.draw(this.portraitImg, drawX, drawY + PORTRAIT_OFFSET_Y, 125.0f, 23.0f, 250.0f, 190.0f, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
        } else if (this.jokePortrait != null) {
            float drawX2 = this.current_x - (this.portrait.packedWidth / 2.0f);
            float drawY2 = this.current_y - (this.portrait.packedHeight / 2.0f);
            sb.setColor(this.renderColor);
            sb.draw(this.jokePortrait, drawX2, drawY2 + PORTRAIT_OFFSET_Y, this.jokePortrait.packedWidth / 2.0f, (this.jokePortrait.packedHeight / 2.0f) - PORTRAIT_OFFSET_Y, this.jokePortrait.packedWidth, this.jokePortrait.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
        } else if (img != null) {
            sb.setColor(this.renderColor);
            sb.draw(img, drawX, drawY + PORTRAIT_OFFSET_Y, 125.0f, 23.0f, 250.0f, 190.0f, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
        }
    }

    private void renderDescription(SpriteBatch sb) {
        String[] cachedTokenizedText;
        if (!this.isSeen || this.isLocked) {
            FontHelper.menuBannerFont.getData().setScale(this.drawScale * 1.25f);
            FontHelper.renderRotatedText(sb, FontHelper.menuBannerFont, "? ? ?", this.current_x, this.current_y, 0.0f, (((-200.0f) * Settings.scale) * this.drawScale) / 2.0f, this.angle, true, this.textColor);
            FontHelper.menuBannerFont.getData().setScale(1.0f);
            return;
        }
        BitmapFont font = getDescFont();
        float draw_y = (this.current_y - ((IMG_HEIGHT * this.drawScale) / 2.0f)) + (DESC_OFFSET_Y * this.drawScale) + (((this.description.size() * font.getCapHeight()) * 0.775f) - (font.getCapHeight() * 0.375f));
        float spacing = ((LINE_SPACING * (-font.getCapHeight())) / Settings.scale) / this.drawScale;
        for (int i = 0; i < this.description.size(); i++) {
            float start_x = this.current_x - ((this.description.get(i).width * this.drawScale) / 2.0f);
            for (String tmp : this.description.get(i).getCachedTokenizedText()) {
                if (tmp.length() > 0 && tmp.charAt(0) == '*') {
                    String tmp2 = tmp.substring(1);
                    String punctuation = "";
                    if (tmp2.length() > 1 && tmp2.charAt(tmp2.length() - 2) != '+' && !Character.isLetter(tmp2.charAt(tmp2.length() - 2))) {
                        String punctuation2 = punctuation + tmp2.charAt(tmp2.length() - 2);
                        tmp2 = tmp2.substring(0, tmp2.length() - 2);
                        punctuation = punctuation2 + ' ';
                    }
                    gl.setText(font, tmp2);
                    FontHelper.renderRotatedText(sb, font, tmp2, this.current_x, this.current_y, (start_x - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.goldColor);
                    float start_x2 = Math.round(start_x + gl.width);
                    gl.setText(font, punctuation);
                    FontHelper.renderRotatedText(sb, font, punctuation, this.current_x, this.current_y, (start_x2 - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                    gl.setText(font, punctuation);
                    start_x = start_x2 + gl.width;
                } else if (tmp.length() <= 0 || tmp.charAt(0) != '!') {
                    if (tmp.equals("[R] ")) {
                        gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                        renderSmallEnergy(sb, orb_red, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        start_x += gl.width;
                    } else if (tmp.equals("[R]. ")) {
                        gl.width = (CARD_ENERGY_IMG_WIDTH * this.drawScale) / Settings.scale;
                        renderSmallEnergy(sb, orb_red, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (CARD_ENERGY_IMG_WIDTH * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                        float start_x3 = start_x + gl.width;
                        gl.setText(font, LocalizedStrings.PERIOD);
                        start_x = start_x3 + gl.width;
                    } else if (tmp.equals("[G] ")) {
                        gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                        renderSmallEnergy(sb, orb_green, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        start_x += gl.width;
                    } else if (tmp.equals("[G]. ")) {
                        gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                        renderSmallEnergy(sb, orb_green, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (CARD_ENERGY_IMG_WIDTH * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                        start_x += gl.width;
                    } else if (tmp.equals("[B] ")) {
                        gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                        renderSmallEnergy(sb, orb_blue, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        start_x += gl.width;
                    } else if (tmp.equals("[B]. ")) {
                        gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                        renderSmallEnergy(sb, orb_blue, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (CARD_ENERGY_IMG_WIDTH * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                        start_x += gl.width;
                    } else if (tmp.equals("[W] ")) {
                        gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                        renderSmallEnergy(sb, orb_purple, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        start_x += gl.width;
                    } else if (tmp.equals("[W]. ")) {
                        gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                        renderSmallEnergy(sb, orb_purple, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                        FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, (start_x - this.current_x) + (CARD_ENERGY_IMG_WIDTH * this.drawScale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                        start_x += gl.width;
                    } else {
                        gl.setText(font, tmp);
                        FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, (start_x - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                        start_x += gl.width;
                    }
                } else if (tmp.length() == 4) {
                    start_x += renderDynamicVariable(tmp.charAt(1), start_x, draw_y, i, font, sb, null);
                } else if (tmp.length() == 5) {
                    start_x += renderDynamicVariable(tmp.charAt(1), start_x, draw_y, i, font, sb, Character.valueOf(tmp.charAt(3)));
                }
            }
        }
        font.getData().setScale(1.0f);
    }

    private String getDynamicValue(char key) {
        switch (key) {
            case 'B':
                if (!this.isBlockModified) {
                    return Integer.toString(this.baseBlock);
                }
                if (this.block >= this.baseBlock) {
                    return "[#7fff00]" + Integer.toString(this.block) + "[]";
                }
                return "[#ff6563]" + Integer.toString(this.block) + "[]";
            case 'D':
                if (!this.isDamageModified) {
                    return Integer.toString(this.baseDamage);
                }
                if (this.damage >= this.baseDamage) {
                    return "[#7fff00]" + Integer.toString(this.damage) + "[]";
                }
                return "[#ff6563]" + Integer.toString(this.damage) + "[]";
            case 'M':
                if (!this.isMagicNumberModified) {
                    return Integer.toString(this.baseMagicNumber);
                }
                if (this.magicNumber >= this.baseMagicNumber) {
                    return "[#7fff00]" + Integer.toString(this.magicNumber) + "[]";
                }
                return "[#ff6563]" + Integer.toString(this.magicNumber) + "[]";
            default:
                logger.info("KEY: " + key);
                return Integer.toString(-99);
        }
    }

    private void renderDescriptionCN(SpriteBatch sb) {
        float start_x;
        String[] cachedTokenizedTextCN;
        float f;
        float f2;
        if (!this.isSeen || this.isLocked) {
            FontHelper.menuBannerFont.getData().setScale(this.drawScale * 1.25f);
            FontHelper.renderRotatedText(sb, FontHelper.menuBannerFont, "? ? ?", this.current_x, this.current_y, 0.0f, (((-200.0f) * Settings.scale) * this.drawScale) / 2.0f, this.angle, true, this.textColor);
            FontHelper.menuBannerFont.getData().setScale(1.0f);
            return;
        }
        BitmapFont font = getDescFont();
        float draw_y = (this.current_y - ((IMG_HEIGHT * this.drawScale) / 2.0f)) + (DESC_OFFSET_Y * this.drawScale) + (((this.description.size() * font.getCapHeight()) * 0.775f) - (font.getCapHeight() * 0.375f));
        float spacing = ((LINE_SPACING * (-font.getCapHeight())) / Settings.scale) / this.drawScale;
        for (int i = 0; i < this.description.size(); i++) {
            if (Settings.leftAlignCards) {
                start_x = (this.current_x - ((DESC_BOX_WIDTH * this.drawScale) / 2.0f)) + (2.0f * Settings.scale);
            } else {
                start_x = (this.current_x - ((this.description.get(i).width * this.drawScale) / 2.0f)) - (14.0f * Settings.scale);
            }
            for (String tmp : this.description.get(i).getCachedTokenizedTextCN()) {
                String updateTmp = null;
                for (int j = 0; j < tmp.length(); j++) {
                    if (tmp.charAt(j) == 'D' || ((tmp.charAt(j) == 'B' && !tmp.contains("[B]")) || tmp.charAt(j) == 'M')) {
                        String updateTmp2 = tmp.substring(0, j);
                        updateTmp = (updateTmp2 + getDynamicValue(tmp.charAt(j))) + tmp.substring(j + 1);
                        break;
                    }
                }
                if (updateTmp != null) {
                    tmp = updateTmp;
                }
                for (int j2 = 0; j2 < tmp.length(); j2++) {
                    if (tmp.charAt(j2) == 'D' || ((tmp.charAt(j2) == 'B' && !tmp.contains("[B]")) || tmp.charAt(j2) == 'M')) {
                        String updateTmp3 = tmp.substring(0, j2);
                        updateTmp = (updateTmp3 + getDynamicValue(tmp.charAt(j2))) + tmp.substring(j2 + 1);
                        break;
                    }
                }
                if (updateTmp != null) {
                    tmp = updateTmp;
                }
                if (tmp.length() > 0 && tmp.charAt(0) == '*') {
                    String tmp2 = tmp.substring(1);
                    String punctuation = "";
                    if (tmp2.length() > 1 && tmp2.charAt(tmp2.length() - 2) != '+' && !Character.isLetter(tmp2.charAt(tmp2.length() - 2))) {
                        String punctuation2 = punctuation + tmp2.charAt(tmp2.length() - 2);
                        tmp2 = tmp2.substring(0, tmp2.length() - 2);
                        punctuation = punctuation2 + ' ';
                    }
                    gl.setText(font, tmp2);
                    FontHelper.renderRotatedText(sb, font, tmp2, this.current_x, this.current_y, (start_x - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.goldColor);
                    float start_x2 = Math.round(start_x + gl.width);
                    gl.setText(font, punctuation);
                    FontHelper.renderRotatedText(sb, font, punctuation, this.current_x, this.current_y, (start_x2 - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                    gl.setText(font, punctuation);
                    f2 = start_x2;
                    f = gl.width;
                } else if (tmp.equals("[R]")) {
                    gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                    renderSmallEnergy(sb, orb_red, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl.width;
                } else if (tmp.equals("[G]")) {
                    gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                    renderSmallEnergy(sb, orb_green, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl.width;
                } else if (tmp.equals("[B]")) {
                    gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                    renderSmallEnergy(sb, orb_blue, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl.width;
                } else if (tmp.equals("[W]")) {
                    gl.width = CARD_ENERGY_IMG_WIDTH * this.drawScale;
                    renderSmallEnergy(sb, orb_purple, ((start_x - this.current_x) / Settings.scale) / this.drawScale, (-100.0f) - (((((this.description.size() - 4.0f) / 2.0f) - i) + 1.0f) * spacing));
                    f2 = start_x;
                    f = gl.width;
                } else {
                    gl.setText(font, tmp);
                    FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, (start_x - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, this.textColor);
                    f2 = start_x;
                    f = gl.width;
                }
                start_x = f2 + f;
            }
        }
        font.getData().setScale(1.0f);
    }

    private float renderDynamicVariable(char key, float start_x, float draw_y, int i, BitmapFont font, SpriteBatch sb, Character end) {
        sbuilder.setLength(0);
        Color c = null;
        int num = 0;
        switch (key) {
            case 'B':
                if (!this.isBlockModified) {
                    c = this.textColor;
                    num = this.baseBlock;
                    break;
                } else {
                    num = this.block;
                    if (this.block < this.baseBlock) {
                        c = Settings.RED_TEXT_COLOR;
                        break;
                    } else {
                        c = Settings.GREEN_TEXT_COLOR;
                        break;
                    }
                }
            case 'D':
                if (!this.isDamageModified) {
                    c = this.textColor;
                    num = this.baseDamage;
                    break;
                } else {
                    num = this.damage;
                    if (this.damage < this.baseDamage) {
                        c = Settings.RED_TEXT_COLOR;
                        break;
                    } else {
                        c = Settings.GREEN_TEXT_COLOR;
                        break;
                    }
                }
            case 'M':
                if (!this.isMagicNumberModified) {
                    c = this.textColor;
                    num = this.baseMagicNumber;
                    break;
                } else {
                    num = this.magicNumber;
                    if (this.magicNumber < this.baseMagicNumber) {
                        c = Settings.RED_TEXT_COLOR;
                        break;
                    } else {
                        c = Settings.GREEN_TEXT_COLOR;
                        break;
                    }
                }
        }
        sbuilder.append(Integer.toString(num));
        gl.setText(font, sbuilder);
        FontHelper.renderRotatedText(sb, font, sbuilder.toString(), this.current_x, this.current_y, (start_x - this.current_x) + (gl.width / 2.0f), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, this.angle, true, c);
        if (end != null) {
            FontHelper.renderRotatedText(sb, font, Character.toString(end.charValue()), this.current_x, this.current_y, (start_x - this.current_x) + gl.width + (4.0f * Settings.scale), ((((i * LINE_SPACING) * (-font.getCapHeight())) + draw_y) - this.current_y) + DESC_OFFSET_Y2, 0.0f, true, Settings.CREAM_COLOR);
            sbuilder.append(end);
        }
        sbuilder.append(' ');
        gl.setText(font, sbuilder);
        return gl.width;
    }

    private BitmapFont getDescFont() {
        BitmapFont font;
        if (this.angle == 0.0f && this.drawScale == 1.0f) {
            font = FontHelper.cardDescFont_N;
        } else {
            font = FontHelper.cardDescFont_L;
        }
        font.getData().setScale(this.drawScale);
        return font;
    }

    private void renderTitle(SpriteBatch sb) {
        if (this.isLocked) {
            FontHelper.cardTitleFont.getData().setScale(this.drawScale);
            FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, LOCKED_STRING, this.current_x, this.current_y, 0.0f, NAME_OFFSET_Y * this.drawScale * Settings.scale, this.angle, false, this.renderColor);
        } else if (!this.isSeen) {
            FontHelper.cardTitleFont.getData().setScale(this.drawScale);
            FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, UNKNOWN_STRING, this.current_x, this.current_y, 0.0f, NAME_OFFSET_Y * this.drawScale * Settings.scale, this.angle, false, this.renderColor);
        } else {
            if (!this.useSmallTitleFont) {
                FontHelper.cardTitleFont.getData().setScale(this.drawScale);
            } else {
                FontHelper.cardTitleFont.getData().setScale(this.drawScale * 0.85f);
            }
            if (this.upgraded) {
                Color color = Settings.GREEN_TEXT_COLOR.cpy();
                color.a = this.renderColor.a;
                FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, this.name, this.current_x, this.current_y, 0.0f, NAME_OFFSET_Y * this.drawScale * Settings.scale, this.angle, false, color);
                return;
            }
            FontHelper.renderRotatedText(sb, FontHelper.cardTitleFont, this.name, this.current_x, this.current_y, 0.0f, NAME_OFFSET_Y * this.drawScale * Settings.scale, this.angle, false, this.renderColor);
        }
    }

    private void renderType(SpriteBatch sb) {
        String text;
        switch (this.type) {
            case ATTACK:
                text = TEXT[0];
                break;
            case CURSE:
                text = TEXT[3];
                break;
            case STATUS:
                text = TEXT[7];
                break;
            case SKILL:
                text = TEXT[1];
                break;
            case POWER:
                text = TEXT[2];
                break;
            default:
                text = TEXT[5];
                break;
        }
        BitmapFont font = FontHelper.cardTypeFont;
        font.getData().setScale(this.drawScale);
        this.typeColor.a = this.renderColor.a;
        FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y - ((22.0f * this.drawScale) * Settings.scale), 0.0f, (-1.0f) * this.drawScale * Settings.scale, this.angle, false, this.typeColor);
    }

    public static int getPrice(CardRarity rarity) {
        switch (rarity) {
            case BASIC:
                logger.info("ERROR: WHY WE SELLIN' BASIC");
                return 9999;
            case CURSE:
            default:
                logger.info("No rarity on this card?");
                return 0;
            case SPECIAL:
                logger.info("ERROR: WHY WE SELLIN' SPECIAL");
                return 9999;
            case COMMON:
                return 50;
            case UNCOMMON:
                return 75;
            case RARE:
                return 150;
        }
    }

    private void renderEnergy(SpriteBatch sb) {
        if (this.cost > -2 && !this.darken && !this.isLocked && this.isSeen) {
            switch (this.color) {
                case RED:
                    renderHelper(sb, this.renderColor, ImageMaster.CARD_RED_ORB, this.current_x, this.current_y);
                    break;
                case GREEN:
                    renderHelper(sb, this.renderColor, ImageMaster.CARD_GREEN_ORB, this.current_x, this.current_y);
                    break;
                case BLUE:
                    renderHelper(sb, this.renderColor, ImageMaster.CARD_BLUE_ORB, this.current_x, this.current_y);
                    break;
                case PURPLE:
                    renderHelper(sb, this.renderColor, ImageMaster.CARD_PURPLE_ORB, this.current_x, this.current_y);
                    break;
                case COLORLESS:
                    renderHelper(sb, this.renderColor, ImageMaster.CARD_COLORLESS_ORB, this.current_x, this.current_y);
                default:
                    renderHelper(sb, this.renderColor, ImageMaster.CARD_COLORLESS_ORB, this.current_x, this.current_y);
                    break;
            }
            Color costColor = Color.WHITE.cpy();
            if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(this) && !hasEnoughEnergy()) {
                costColor = ENERGY_COST_RESTRICTED_COLOR;
            } else if (this.isCostModified || this.isCostModifiedForTurn || freeToPlay()) {
                costColor = ENERGY_COST_MODIFIED_COLOR;
            }
            costColor.a = this.transparency;
            String text = getCost();
            BitmapFont font = getEnergyFont();
            if (this.type == CardType.STATUS && !this.cardID.equals(Slimed.ID)) {
                return;
            }
            if (this.color != CardColor.CURSE || this.cardID.equals(Pride.ID)) {
                FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y, ENERGY_TEXT_OFFSET_X * this.drawScale * Settings.scale, ENERGY_TEXT_OFFSET_Y * this.drawScale * Settings.scale, this.angle, false, costColor);
            }
        }
    }

    public void updateCost(int amt) {
        if ((this.color != CardColor.CURSE || this.cardID.equals(Pride.ID)) && (this.type != CardType.STATUS || this.cardID.equals(Slimed.ID))) {
            int tmpCost = this.cost;
            int diff = this.cost - this.costForTurn;
            int tmpCost2 = tmpCost + amt;
            if (tmpCost2 < 0) {
                tmpCost2 = 0;
            }
            if (tmpCost2 != this.cost) {
                this.isCostModified = true;
                this.cost = tmpCost2;
                this.costForTurn = this.cost - diff;
                if (this.costForTurn < 0) {
                    this.costForTurn = 0;
                    return;
                }
                return;
            }
            return;
        }
        logger.info("Curses/Statuses cannot have their costs modified");
    }

    public void setCostForTurn(int amt) {
        if (this.costForTurn >= 0) {
            this.costForTurn = amt;
            if (this.costForTurn < 0) {
                this.costForTurn = 0;
            }
            if (this.costForTurn != this.cost) {
                this.isCostModifiedForTurn = true;
            }
        }
    }

    public void modifyCostForCombat(int amt) {
        if (this.costForTurn > 0) {
            this.costForTurn += amt;
            if (this.costForTurn < 0) {
                this.costForTurn = 0;
            }
            if (this.cost != this.costForTurn) {
                this.isCostModified = true;
            }
            this.cost = this.costForTurn;
        } else if (this.cost >= 0) {
            this.cost += amt;
            if (this.cost < 0) {
                this.cost = 0;
            }
            this.costForTurn = 0;
            if (this.cost != this.costForTurn) {
                this.isCostModified = true;
            }
        }
    }

    public void resetAttributes() {
        this.block = this.baseBlock;
        this.isBlockModified = false;
        this.damage = this.baseDamage;
        this.isDamageModified = false;
        this.magicNumber = this.baseMagicNumber;
        this.isMagicNumberModified = false;
        this.damageTypeForTurn = this.damageType;
        this.costForTurn = this.cost;
        this.isCostModifiedForTurn = false;
    }

    private String getCost() {
        if (this.cost == -1) {
            return "X";
        }
        if (freeToPlay()) {
            return "0";
        }
        return Integer.toString(this.costForTurn);
    }

    public boolean freeToPlay() {
        if (this.freeToPlayOnce) {
            return true;
        }
        if (AbstractDungeon.player == null || AbstractDungeon.currMapNode == null || AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT || !AbstractDungeon.player.hasPower(FreeAttackPower.POWER_ID) || this.type != CardType.ATTACK) {
            return false;
        }
        return true;
    }

    private BitmapFont getEnergyFont() {
        FontHelper.cardEnergyFont_L.getData().setScale(this.drawScale);
        return FontHelper.cardEnergyFont_L;
    }

    public void hover() {
        if (!this.hovered) {
            this.hovered = true;
            this.drawScale = 1.0f;
            this.targetDrawScale = 1.0f;
        }
    }

    public void unhover() {
        if (this.hovered) {
            this.hovered = false;
            this.hoverDuration = 0.0f;
            this.renderTip = false;
            this.targetDrawScale = 0.75f;
        }
    }

    public void updateHoverLogic() {
        this.hb.update();
        if (this.hb.hovered) {
            hover();
            this.hoverDuration += Gdx.graphics.getDeltaTime();
            if (this.hoverDuration > 0.2f && !Settings.hideCards) {
                this.renderTip = true;
                return;
            }
            return;
        }
        unhover();
    }

    public void untip() {
        this.hoverDuration = 0.0f;
        this.renderTip = false;
    }

    public void moveToDiscardPile() {
        this.target_x = CardGroup.DISCARD_PILE_X;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.target_y = 0.0f;
        } else {
            this.target_y = 0.0f - OverlayMenu.HAND_HIDE_Y;
        }
    }

    public void teleportToDiscardPile() {
        this.current_x = CardGroup.DISCARD_PILE_X;
        this.target_x = this.current_x;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.current_y = 0.0f;
        } else {
            this.current_y = 0.0f - OverlayMenu.HAND_HIDE_Y;
        }
        this.target_y = this.current_y;
        onMoveToDiscard();
    }

    public void onMoveToDiscard() {
    }

    public void renderCardTip(SpriteBatch sb) {
        if (!Settings.hideCards && this.renderTip) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.isDraggingCard && !Settings.isTouchScreen) {
                return;
            }
            if (this.isLocked) {
                ArrayList<String> locked = new ArrayList<>();
                locked.add(0, "locked");
                TipHelper.renderTipForCard(this, sb, locked);
            } else if (!this.isSeen) {
                ArrayList<String> unseen = new ArrayList<>();
                unseen.add(0, "unseen");
                TipHelper.renderTipForCard(this, sb, unseen);
            } else {
                if (!SingleCardViewPopup.isViewingUpgrade || !this.isSeen || this.isLocked) {
                    TipHelper.renderTipForCard(this, sb, this.keywords);
                } else {
                    AbstractCard copy = makeCopy();
                    copy.current_x = this.current_x;
                    copy.current_y = this.current_y;
                    copy.drawScale = this.drawScale;
                    copy.upgrade();
                    TipHelper.renderTipForCard(copy, sb, copy.keywords);
                }
                if (this.cardsToPreview != null) {
                    renderCardPreview(sb);
                }
            }
        }
    }

    public void renderCardPreviewInSingleView(SpriteBatch sb) {
        this.cardsToPreview.current_x = 1435.0f * Settings.scale;
        this.cardsToPreview.current_y = 795.0f * Settings.scale;
        this.cardsToPreview.drawScale = 0.8f;
        this.cardsToPreview.render(sb);
    }

    public void renderCardPreview(SpriteBatch sb) {
        if (AbstractDungeon.player == null || !AbstractDungeon.player.isDraggingCard) {
            float tmpScale = this.drawScale * 0.8f;
            if (this.current_x > Settings.WIDTH * 0.75f) {
                this.cardsToPreview.current_x = this.current_x + (((IMG_WIDTH / 2.0f) + ((IMG_WIDTH / 2.0f) * 0.8f) + CARD_TIP_PAD) * this.drawScale);
            } else {
                this.cardsToPreview.current_x = this.current_x - ((((IMG_WIDTH / 2.0f) + ((IMG_WIDTH / 2.0f) * 0.8f)) + CARD_TIP_PAD) * this.drawScale);
            }
            this.cardsToPreview.current_y = this.current_y + (((IMG_HEIGHT / 2.0f) - ((IMG_HEIGHT / 2.0f) * 0.8f)) * this.drawScale);
            this.cardsToPreview.drawScale = tmpScale;
            this.cardsToPreview.render(sb);
        }
    }

    public void triggerWhenDrawn() {
    }

    public void triggerWhenCopied() {
    }

    public void triggerOnEndOfPlayerTurn() {
        if (this.isEthereal) {
            addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }

    public void triggerOnEndOfTurnForPlayingCard() {
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
    }

    public void triggerOnManualDiscard() {
    }

    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
    }

    public void triggerOnScry() {
    }

    public void triggerExhaustedCardsOnStanceChange(AbstractStance newStance) {
    }

    public void triggerAtStartOfTurn() {
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
    }

    public void atTurnStart() {
    }

    public void atTurnStartPreDraw() {
    }

    public void onChoseThisOption() {
    }

    public void onRetained() {
    }

    public void triggerOnExhaust() {
    }

    public void applyPowers() {
        applyPowersToBlock();
        AbstractPlayer player = AbstractDungeon.player;
        this.isDamageModified = false;
        if (!this.isMultiDamage) {
            float tmp = this.baseDamage;
            Iterator<AbstractRelic> it = player.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                tmp = r.atDamageModify(tmp, this);
                if (this.baseDamage != ((int) tmp)) {
                    this.isDamageModified = true;
                }
            }
            Iterator<AbstractPower> it2 = player.powers.iterator();
            while (it2.hasNext()) {
                AbstractPower p = it2.next();
                tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this);
            }
            float tmp2 = player.stance.atDamageGive(tmp, this.damageTypeForTurn, this);
            if (this.baseDamage != ((int) tmp2)) {
                this.isDamageModified = true;
            }
            Iterator<AbstractPower> it3 = player.powers.iterator();
            while (it3.hasNext()) {
                AbstractPower p2 = it3.next();
                tmp2 = p2.atDamageFinalGive(tmp2, this.damageTypeForTurn, this);
            }
            if (tmp2 < 0.0f) {
                tmp2 = 0.0f;
            }
            if (this.baseDamage != MathUtils.floor(tmp2)) {
                this.isDamageModified = true;
            }
            this.damage = MathUtils.floor(tmp2);
            return;
        }
        ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
        float[] tmp3 = new float[m.size()];
        for (int i = 0; i < tmp3.length; i++) {
            tmp3[i] = this.baseDamage;
        }
        for (int i2 = 0; i2 < tmp3.length; i2++) {
            Iterator<AbstractRelic> it4 = player.relics.iterator();
            while (it4.hasNext()) {
                AbstractRelic r2 = it4.next();
                tmp3[i2] = r2.atDamageModify(tmp3[i2], this);
                if (this.baseDamage != ((int) tmp3[i2])) {
                    this.isDamageModified = true;
                }
            }
            Iterator<AbstractPower> it5 = player.powers.iterator();
            while (it5.hasNext()) {
                AbstractPower p3 = it5.next();
                tmp3[i2] = p3.atDamageGive(tmp3[i2], this.damageTypeForTurn, this);
            }
            tmp3[i2] = player.stance.atDamageGive(tmp3[i2], this.damageTypeForTurn, this);
            if (this.baseDamage != ((int) tmp3[i2])) {
                this.isDamageModified = true;
            }
        }
        for (int i3 = 0; i3 < tmp3.length; i3++) {
            Iterator<AbstractPower> it6 = player.powers.iterator();
            while (it6.hasNext()) {
                AbstractPower p4 = it6.next();
                tmp3[i3] = p4.atDamageFinalGive(tmp3[i3], this.damageTypeForTurn, this);
            }
        }
        for (int i4 = 0; i4 < tmp3.length; i4++) {
            if (tmp3[i4] < 0.0f) {
                tmp3[i4] = 0.0f;
            }
        }
        this.multiDamage = new int[tmp3.length];
        for (int i5 = 0; i5 < tmp3.length; i5++) {
            if (this.baseDamage != ((int) tmp3[i5])) {
                this.isDamageModified = true;
            }
            this.multiDamage[i5] = MathUtils.floor(tmp3[i5]);
        }
        this.damage = this.multiDamage[0];
    }

    protected void applyPowersToBlock() {
        this.isBlockModified = false;
        float tmp = this.baseBlock;
        Iterator<AbstractPower> it = AbstractDungeon.player.powers.iterator();
        while (it.hasNext()) {
            AbstractPower p = it.next();
            tmp = p.modifyBlock(tmp, this);
        }
        Iterator<AbstractPower> it2 = AbstractDungeon.player.powers.iterator();
        while (it2.hasNext()) {
            AbstractPower p2 = it2.next();
            tmp = p2.modifyBlockLast(tmp);
        }
        if (this.baseBlock != MathUtils.floor(tmp)) {
            this.isBlockModified = true;
        }
        if (tmp < 0.0f) {
            tmp = 0.0f;
        }
        this.block = MathUtils.floor(tmp);
    }

    public void calculateDamageDisplay(AbstractMonster mo) {
        calculateCardDamage(mo);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        applyPowersToBlock();
        AbstractPlayer player = AbstractDungeon.player;
        this.isDamageModified = false;
        if (this.isMultiDamage || mo == null) {
            ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
            float[] tmp = new float[m.size()];
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = this.baseDamage;
            }
            for (int i2 = 0; i2 < tmp.length; i2++) {
                Iterator<AbstractRelic> it = player.relics.iterator();
                while (it.hasNext()) {
                    AbstractRelic r = it.next();
                    tmp[i2] = r.atDamageModify(tmp[i2], this);
                    if (this.baseDamage != ((int) tmp[i2])) {
                        this.isDamageModified = true;
                    }
                }
                Iterator<AbstractPower> it2 = player.powers.iterator();
                while (it2.hasNext()) {
                    AbstractPower p = it2.next();
                    tmp[i2] = p.atDamageGive(tmp[i2], this.damageTypeForTurn, this);
                }
                tmp[i2] = player.stance.atDamageGive(tmp[i2], this.damageTypeForTurn, this);
                if (this.baseDamage != ((int) tmp[i2])) {
                    this.isDamageModified = true;
                }
            }
            for (int i3 = 0; i3 < tmp.length; i3++) {
                Iterator<AbstractPower> it3 = m.get(i3).powers.iterator();
                while (it3.hasNext()) {
                    AbstractPower p2 = it3.next();
                    if (!m.get(i3).isDying && !m.get(i3).isEscaping) {
                        tmp[i3] = p2.atDamageReceive(tmp[i3], this.damageTypeForTurn, this);
                    }
                }
            }
            for (int i4 = 0; i4 < tmp.length; i4++) {
                Iterator<AbstractPower> it4 = player.powers.iterator();
                while (it4.hasNext()) {
                    AbstractPower p3 = it4.next();
                    tmp[i4] = p3.atDamageFinalGive(tmp[i4], this.damageTypeForTurn, this);
                }
            }
            for (int i5 = 0; i5 < tmp.length; i5++) {
                Iterator<AbstractPower> it5 = m.get(i5).powers.iterator();
                while (it5.hasNext()) {
                    AbstractPower p4 = it5.next();
                    if (!m.get(i5).isDying && !m.get(i5).isEscaping) {
                        tmp[i5] = p4.atDamageFinalReceive(tmp[i5], this.damageTypeForTurn, this);
                    }
                }
            }
            for (int i6 = 0; i6 < tmp.length; i6++) {
                if (tmp[i6] < 0.0f) {
                    tmp[i6] = 0.0f;
                }
            }
            this.multiDamage = new int[tmp.length];
            for (int i7 = 0; i7 < tmp.length; i7++) {
                if (this.baseDamage != MathUtils.floor(tmp[i7])) {
                    this.isDamageModified = true;
                }
                this.multiDamage[i7] = MathUtils.floor(tmp[i7]);
            }
            this.damage = this.multiDamage[0];
            return;
        }
        float tmp2 = this.baseDamage;
        Iterator<AbstractRelic> it6 = player.relics.iterator();
        while (it6.hasNext()) {
            AbstractRelic r2 = it6.next();
            tmp2 = r2.atDamageModify(tmp2, this);
            if (this.baseDamage != ((int) tmp2)) {
                this.isDamageModified = true;
            }
        }
        Iterator<AbstractPower> it7 = player.powers.iterator();
        while (it7.hasNext()) {
            AbstractPower p5 = it7.next();
            tmp2 = p5.atDamageGive(tmp2, this.damageTypeForTurn, this);
        }
        float tmp3 = player.stance.atDamageGive(tmp2, this.damageTypeForTurn, this);
        if (this.baseDamage != ((int) tmp3)) {
            this.isDamageModified = true;
        }
        Iterator<AbstractPower> it8 = mo.powers.iterator();
        while (it8.hasNext()) {
            AbstractPower p6 = it8.next();
            tmp3 = p6.atDamageReceive(tmp3, this.damageTypeForTurn, this);
        }
        Iterator<AbstractPower> it9 = player.powers.iterator();
        while (it9.hasNext()) {
            AbstractPower p7 = it9.next();
            tmp3 = p7.atDamageFinalGive(tmp3, this.damageTypeForTurn, this);
        }
        Iterator<AbstractPower> it10 = mo.powers.iterator();
        while (it10.hasNext()) {
            AbstractPower p8 = it10.next();
            tmp3 = p8.atDamageFinalReceive(tmp3, this.damageTypeForTurn, this);
        }
        if (tmp3 < 0.0f) {
            tmp3 = 0.0f;
        }
        if (this.baseDamage != MathUtils.floor(tmp3)) {
            this.isDamageModified = true;
        }
        this.damage = MathUtils.floor(tmp3);
    }

    public void setAngle(float degrees, boolean immediate) {
        this.targetAngle = degrees;
        if (immediate) {
            this.angle = this.targetAngle;
        }
    }

    public void shrink() {
        this.targetDrawScale = 0.12f;
    }

    public void shrink(boolean immediate) {
        this.targetDrawScale = 0.12f;
        this.drawScale = 0.12f;
    }

    public void darken(boolean immediate) {
        this.darken = true;
        this.darkTimer = 0.3f;
        if (immediate) {
            this.tintColor.a = 1.0f;
            this.darkTimer = 0.0f;
        }
    }

    public void lighten(boolean immediate) {
        this.darken = false;
        this.darkTimer = 0.3f;
        if (immediate) {
            this.tintColor.a = 0.0f;
            this.darkTimer = 0.0f;
        }
    }

    private void updateColor() {
        if (this.darkTimer != 0.0f) {
            this.darkTimer -= Gdx.graphics.getDeltaTime();
            if (this.darkTimer < 0.0f) {
                this.darkTimer = 0.0f;
            }
            if (this.darken) {
                this.tintColor.a = 1.0f - ((this.darkTimer * 1.0f) / 0.3f);
                return;
            }
            this.tintColor.a = (this.darkTimer * 1.0f) / 0.3f;
        }
    }

    public void superFlash(Color c) {
        this.flashVfx = new CardFlashVfx(this, c, true);
    }

    public void superFlash() {
        this.flashVfx = new CardFlashVfx(this, true);
    }

    public void flash() {
        this.flashVfx = new CardFlashVfx(this);
    }

    public void flash(Color c) {
        this.flashVfx = new CardFlashVfx(this, c);
    }

    public void unfadeOut() {
        this.fadingOut = false;
        this.transparency = 1.0f;
        this.targetTransparency = 1.0f;
        this.bannerColor.a = this.transparency;
        this.backColor.a = this.transparency;
        this.frameColor.a = this.transparency;
        this.bgColor.a = this.transparency;
        this.descBoxColor.a = this.transparency;
        this.imgFrameColor.a = this.transparency;
        this.frameShadowColor.a = this.transparency / 4.0f;
        this.renderColor.a = this.transparency;
        this.goldColor.a = this.transparency;
        if (this.frameOutlineColor != null) {
            this.frameOutlineColor.a = this.transparency;
        }
    }

    private void updateTransparency() {
        if (this.fadingOut && this.transparency != 0.0f) {
            this.transparency -= Gdx.graphics.getDeltaTime() * 2.0f;
            if (this.transparency < 0.0f) {
                this.transparency = 0.0f;
            }
        } else if (this.transparency != this.targetTransparency) {
            this.transparency += Gdx.graphics.getDeltaTime() * 2.0f;
            if (this.transparency > this.targetTransparency) {
                this.transparency = this.targetTransparency;
            }
        }
        this.bannerColor.a = this.transparency;
        this.backColor.a = this.transparency;
        this.frameColor.a = this.transparency;
        this.bgColor.a = this.transparency;
        this.descBoxColor.a = this.transparency;
        this.imgFrameColor.a = this.transparency;
        this.frameShadowColor.a = this.transparency / 4.0f;
        this.renderColor.a = this.transparency;
        this.textColor.a = this.transparency;
        this.goldColor.a = this.transparency;
        if (this.frameOutlineColor != null) {
            this.frameOutlineColor.a = this.transparency;
        }
    }

    public void setAngle(float degrees) {
        setAngle(degrees, false);
    }

    protected String getCantPlayMessage() {
        return TEXT[13];
    }

    public void clearPowers() {
        resetAttributes();
        this.isDamageModified = false;
    }

    public static void debugPrintDetailedCardDataHeader() {
        logger.info(gameDataUploadHeader());
    }

    public static String gameDataUploadHeader() {
        GameDataStringBuilder builder = new GameDataStringBuilder();
        builder.addFieldData("name");
        builder.addFieldData("cardID");
        builder.addFieldData("rawDescription");
        builder.addFieldData("assetURL");
        builder.addFieldData("keywords");
        builder.addFieldData(TwitchTags.COLOR);
        builder.addFieldData("type");
        builder.addFieldData("rarity");
        builder.addFieldData("cost");
        builder.addFieldData("target");
        builder.addFieldData("damageType");
        builder.addFieldData("baseDamage");
        builder.addFieldData("baseBlock");
        builder.addFieldData("baseHeal");
        builder.addFieldData("baseDraw");
        builder.addFieldData("baseDiscard");
        builder.addFieldData("baseMagicNumber");
        builder.addFieldData("isMultiDamage");
        return builder.toString();
    }

    public void debugPrintDetailedCardData() {
        logger.info(gameDataUploadData());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    public String gameDataUploadData() {
        GameDataStringBuilder builder = new GameDataStringBuilder();
        builder.addFieldData(this.name);
        builder.addFieldData(this.cardID);
        builder.addFieldData(this.rawDescription);
        builder.addFieldData(this.assetUrl);
        builder.addFieldData(Arrays.toString(this.keywords.toArray()));
        builder.addFieldData(this.color.name());
        builder.addFieldData(this.type.name());
        builder.addFieldData(this.rarity.name());
        builder.addFieldData(this.cost);
        builder.addFieldData(this.target.name());
        builder.addFieldData(this.damageType.name());
        builder.addFieldData(this.baseDamage);
        builder.addFieldData(this.baseBlock);
        builder.addFieldData(this.baseHeal);
        builder.addFieldData(this.baseDraw);
        builder.addFieldData(this.baseDiscard);
        builder.addFieldData(this.baseMagicNumber);
        builder.addFieldData(this.isMultiDamage);
        return builder.toString();
    }

    public String toString() {
        return this.name;
    }

    public int compareTo(AbstractCard other) {
        return this.cardID.compareTo(other.cardID);
    }

    public void setLocked() {
        this.isLocked = true;
        switch (this.type) {
            case ATTACK:
                this.portraitImg = ImageMaster.CARD_LOCKED_ATTACK;
                break;
            case POWER:
                this.portraitImg = ImageMaster.CARD_LOCKED_POWER;
                break;
            default:
                this.portraitImg = ImageMaster.CARD_LOCKED_SKILL;
                break;
        }
        initializeDescription();
    }

    public void unlock() {
        this.isLocked = false;
        this.portrait = cardAtlas.findRegion(this.assetUrl);
        if (this.portrait == null) {
            this.portrait = oldCardAtlas.findRegion(this.assetUrl);
        }
    }

    public HashMap<String, Serializable> getLocStrings() {
        HashMap<String, Serializable> cardData = new HashMap<>();
        initializeDescription();
        cardData.put("name", this.name);
        cardData.put("description", this.rawDescription);
        return cardData;
    }

    public String getMetricID() {
        /* 3630 */     String id = this.cardID;
        /* 3631 */     if (this.upgraded) {
            /* 3632 */       id = id + "+";
            /* 3633 */       if (this.timesUpgraded > 0) {
                /* 3634 */         id = id + this.timesUpgraded;
                /*      */       }
            /*      */     }
        /* 3637 */     return id;
    }

    public void triggerOnGlowCheck() {
    }
}