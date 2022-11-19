package com.megacrit.cardcrawl.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.Hoarder;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Courier;
import com.megacrit.cardcrawl.relics.MembershipCard;
import com.megacrit.cardcrawl.relics.SmilingMask;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.FloatyEffect;
import com.megacrit.cardcrawl.vfx.ShopSpeechBubble;
import com.megacrit.cardcrawl.vfx.SpeechTextEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/shop/ShopScreen.class */
public class ShopScreen {
    private static final float RUG_SPEED = 5.0f;
    private static final int NUM_CARDS_PER_LINE = 5;
    private static final float CARD_PRICE_JITTER = 0.1f;
    private static final float MIN_IDLE_MSG_TIME = 40.0f;
    private static final float MAX_IDLE_MSG_TIME = 60.0f;
    private static final float SPEECH_DURATION = 4.0f;
    private static final float RELIC_PRICE_JITTER = 0.05f;
    private static final float POTION_PRICE_JITTER = 0.05f;
    private static final int PURGE_COST_RAMP = 25;
    private float purgeCardX;
    private float purgeCardY;
    private static final float HAND_SPEED = 6.0f;
    private static float HAND_W;
    private static float HAND_H;
    private static final float COLORLESS_PRICE_BUMP = 1.2f;
    private OnSaleTag saleTag;
    private static final Logger logger = LogManager.getLogger(ShopScreen.class.getName());
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Shop Tip");
    public static final String[] MSG = tutorialStrings.TEXT;
    public static final String[] LABEL = tutorialStrings.LABEL;
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Shop Screen");
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;
    private static Texture rugImg = null;
    private static Texture removeServiceImg = null;
    private static Texture soldOutImg = null;
    private static Texture handImg = null;
    private static final float DRAW_START_X = Settings.WIDTH * 0.16f;
    private static final float TOP_ROW_Y = 760.0f * Settings.yScale;
    private static final float BOTTOM_ROW_Y = 337.0f * Settings.yScale;
    private static final float SPEECH_TEXT_R_X = 164.0f * Settings.scale;
    private static final float SPEECH_TEXT_L_X = (-166.0f) * Settings.scale;
    private static final float SPEECH_TEXT_Y = 126.0f * Settings.scale;
    private static final String WELCOME_MSG = NAMES[0];
    public static int purgeCost = 75;
    public static int actualPurgeCost = 75;
    private static final float GOLD_IMG_WIDTH = ImageMaster.UI_GOLD.getWidth() * Settings.scale;
    private static final float GOLD_IMG_OFFSET_X = (-50.0f) * Settings.scale;
    private static final float GOLD_IMG_OFFSET_Y = (-215.0f) * Settings.scale;
    private static final float PRICE_TEXT_OFFSET_X = 16.0f * Settings.scale;
    private static final float PRICE_TEXT_OFFSET_Y = (-180.0f) * Settings.scale;
    public boolean isActive = true;
    private float rugY = (Settings.HEIGHT / 2.0f) + (540.0f * Settings.yScale);
    public ArrayList<AbstractCard> coloredCards = new ArrayList<>();
    public ArrayList<AbstractCard> colorlessCards = new ArrayList<>();
    private float speechTimer = 0.0f;
    private ShopSpeechBubble speechBubble = null;
    private SpeechTextEffect dialogTextEffect = null;
    private ArrayList<String> idleMessages = new ArrayList<>();
    private boolean saidWelcome = false;
    private boolean somethingHovered = false;
    private ArrayList<StoreRelic> relics = new ArrayList<>();
    private ArrayList<StorePotion> potions = new ArrayList<>();
    public boolean purgeAvailable = false;
    private boolean purgeHovered = false;
    private float purgeCardScale = 1.0f;
    private FloatyEffect f_effect = new FloatyEffect(20.0f, 0.1f);
    private float handTimer = 1.0f;
    private float handX = Settings.WIDTH / 2.0f;
    private float handY = Settings.HEIGHT;
    private float handTargetX = 0.0f;
    private float handTargetY = Settings.HEIGHT;
    private float notHoveredTimer = 0.0f;
    public ConfirmButton confirmButton = new ConfirmButton();
    public StoreRelic touchRelic = null;
    public StorePotion touchPotion = null;
    private AbstractCard touchCard = null;
    private boolean touchPurge = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: desktop-1.0.jar:com/megacrit/cardcrawl/shop/ShopScreen$StoreSelectionType.class */
    public enum StoreSelectionType {
        RELIC,
        COLOR_CARD,
        COLORLESS_CARD,
        POTION,
        PURGE
    }

    public void init(ArrayList<AbstractCard> coloredCards, ArrayList<AbstractCard> colorlessCards) {
        this.idleMessages.clear();
        if (AbstractDungeon.id.equals(TheEnding.ID)) {
            Collections.addAll(this.idleMessages, Merchant.ENDING_TEXT);
        } else {
            Collections.addAll(this.idleMessages, TEXT);
        }
        if (rugImg == null) {
            switch (Settings.language) {
                case DEU:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/deu.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/deu.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/deu.png");
                    break;
                case EPO:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/epo.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/epo.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/epo.png");
                    break;
                case FIN:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/fin.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/fin.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/fin.png");
                    break;
                case FRA:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/fra.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/fra.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/fra.png");
                    break;
                case ITA:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/ita.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/ita.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/ita.png");
                    break;
                case JPN:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/jpn.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/jpn.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/jpn.png");
                    break;
                case KOR:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/kor.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/kor.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/kor.png");
                    break;
                case RUS:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/rus.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/rus.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/rus.png");
                    break;
                case THA:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/tha.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/tha.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/tha.png");
                    break;
                case UKR:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/ukr.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/ukr.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/ukr.png");
                    break;
                case ZHS:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/zhs.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/zhs.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/zhs.png");
                    break;
                default:
                    rugImg = ImageMaster.loadImage("images/npcs/rug/eng.png");
                    removeServiceImg = ImageMaster.loadImage("images/npcs/purge/eng.png");
                    soldOutImg = ImageMaster.loadImage("images/npcs/sold_out/eng.png");
                    break;
            }
            handImg = ImageMaster.loadImage("images/npcs/merchantHand.png");
        }
        HAND_W = handImg.getWidth() * Settings.scale;
        HAND_H = handImg.getHeight() * Settings.scale;
        this.coloredCards.clear();
        this.colorlessCards.clear();
        this.coloredCards = coloredCards;
        this.colorlessCards = colorlessCards;
        initCards();
        initRelics();
        initPotions();
        this.purgeAvailable = true;
        this.purgeCardY = -1000.0f;
        this.purgeCardX = Settings.WIDTH * 0.73f * Settings.scale;
        this.purgeCardScale = 0.7f;
        actualPurgeCost = purgeCost;
        if (AbstractDungeon.ascensionLevel >= 16) {
            applyDiscount(1.1f, false);
        }
        if (AbstractDungeon.player.hasRelic(Courier.ID)) {
            applyDiscount(0.8f, true);
        }
        if (AbstractDungeon.player.hasRelic(MembershipCard.ID)) {
            applyDiscount(0.5f, true);
        }
        if (AbstractDungeon.player.hasRelic(SmilingMask.ID)) {
            actualPurgeCost = 50;
        }
    }

    public static void resetPurgeCost() {
        purgeCost = 75;
        actualPurgeCost = 75;
    }

    private void initCards() {
        int tmp = ((int) ((Settings.WIDTH - (DRAW_START_X * 2.0f)) - (AbstractCard.IMG_WIDTH_S * RUG_SPEED))) / 4;
        float padX = (int) (tmp + AbstractCard.IMG_WIDTH_S);
        for (int i = 0; i < this.coloredCards.size(); i++) {
            float tmpPrice = AbstractCard.getPrice(this.coloredCards.get(i).rarity) * AbstractDungeon.merchantRng.random(0.9f, 1.1f);
            AbstractCard c = this.coloredCards.get(i);
            c.price = (int) tmpPrice;
            c.current_x = Settings.WIDTH / 2;
            c.target_x = DRAW_START_X + (AbstractCard.IMG_WIDTH_S / 2.0f) + (padX * i);
            Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
            while (it.hasNext()) {
                AbstractRelic r = it.next();
                r.onPreviewObtainCard(c);
            }
        }
        for (int i2 = 0; i2 < this.colorlessCards.size(); i2++) {
            float tmpPrice2 = AbstractCard.getPrice(this.colorlessCards.get(i2).rarity) * AbstractDungeon.merchantRng.random(0.9f, 1.1f);
            float tmpPrice3 = tmpPrice2 * COLORLESS_PRICE_BUMP;
            AbstractCard c2 = this.colorlessCards.get(i2);
            c2.price = (int) tmpPrice3;
            c2.current_x = Settings.WIDTH / 2;
            c2.target_x = DRAW_START_X + (AbstractCard.IMG_WIDTH_S / 2.0f) + (padX * i2);
            Iterator<AbstractRelic> it2 = AbstractDungeon.player.relics.iterator();
            while (it2.hasNext()) {
                AbstractRelic r2 = it2.next();
                r2.onPreviewObtainCard(c2);
            }
        }
        AbstractCard saleCard = this.coloredCards.get(AbstractDungeon.merchantRng.random(0, 4));
        saleCard.price /= 2;
        this.saleTag = new OnSaleTag(saleCard);
        setStartingCardPositions();
    }

    public static void purgeCard() {
        AbstractDungeon.player.loseGold(actualPurgeCost);
        CardCrawlGame.sound.play("SHOP_PURCHASE", 0.1f);
        purgeCost += 25;
        actualPurgeCost = purgeCost;
        if (AbstractDungeon.player.hasRelic(SmilingMask.ID)) {
            actualPurgeCost = 50;
            AbstractDungeon.player.getRelic(SmilingMask.ID).stopPulse();
        } else if (AbstractDungeon.player.hasRelic(Courier.ID) && AbstractDungeon.player.hasRelic(MembershipCard.ID)) {
            actualPurgeCost = MathUtils.round(purgeCost * 0.8f * 0.5f);
        } else if (AbstractDungeon.player.hasRelic(Courier.ID)) {
            actualPurgeCost = MathUtils.round(purgeCost * 0.8f);
        } else if (AbstractDungeon.player.hasRelic(MembershipCard.ID)) {
            actualPurgeCost = MathUtils.round(purgeCost * 0.5f);
        }
    }

    public void updatePurge() {
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            purgeCard();
            Iterator<AbstractCard> it = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
            while (it.hasNext()) {
                AbstractCard card = it.next();
                CardCrawlGame.metricData.addPurgedItem(card.getMetricID());
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                AbstractDungeon.player.masterDeck.removeCard(card);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.shopScreen.purgeAvailable = false;
        }
    }

    public static String getCantBuyMsg() {
        ArrayList<String> list = new ArrayList<>();
        list.add(NAMES[1]);
        list.add(NAMES[2]);
        list.add(NAMES[3]);
        list.add(NAMES[4]);
        list.add(NAMES[5]);
        list.add(NAMES[6]);
        return list.get(MathUtils.random(list.size() - 1));
    }

    public static String getBuyMsg() {
        ArrayList<String> list = new ArrayList<>();
        list.add(NAMES[7]);
        list.add(NAMES[8]);
        list.add(NAMES[9]);
        list.add(NAMES[10]);
        list.add(NAMES[11]);
        return list.get(MathUtils.random(list.size() - 1));
    }

    @Deprecated
    public void applyUpgrades(AbstractCard.CardType type) {
        Iterator<AbstractCard> it = this.coloredCards.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            if (c.type == type) {
                c.upgrade();
            }
        }
        Iterator<AbstractCard> it2 = this.colorlessCards.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            if (c2.type == type) {
                c2.upgrade();
            }
        }
    }

    public void applyDiscount(float multiplier, boolean affectPurge) {
        Iterator<StoreRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            StoreRelic r = it.next();
            r.price = MathUtils.round(r.price * multiplier);
        }
        Iterator<StorePotion> it2 = this.potions.iterator();
        while (it2.hasNext()) {
            StorePotion p = it2.next();
            p.price = MathUtils.round(p.price * multiplier);
        }
        Iterator<AbstractCard> it3 = this.coloredCards.iterator();
        while (it3.hasNext()) {
            AbstractCard c = it3.next();
            c.price = MathUtils.round(c.price * multiplier);
        }
        Iterator<AbstractCard> it4 = this.colorlessCards.iterator();
        while (it4.hasNext()) {
            AbstractCard c2 = it4.next();
            c2.price = MathUtils.round(c2.price * multiplier);
        }
        if (AbstractDungeon.player.hasRelic(SmilingMask.ID)) {
            actualPurgeCost = 50;
        } else if (affectPurge) {
            actualPurgeCost = MathUtils.round(purgeCost * multiplier);
        }
    }

    private void initRelics() {
        AbstractRelic tempRelic;
        this.relics.clear();
        this.relics = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i != 2) {
                tempRelic = AbstractDungeon.returnRandomRelicEnd(rollRelicTier());
            } else {
                tempRelic = AbstractDungeon.returnRandomRelicEnd(AbstractRelic.RelicTier.SHOP);
            }
            StoreRelic relic = new StoreRelic(tempRelic, i, this);
            if (!Settings.isDailyRun) {
                relic.price = MathUtils.round(relic.price * AbstractDungeon.merchantRng.random(0.95f, 1.05f));
            }
            this.relics.add(relic);
        }
    }

    private void initPotions() {
        this.potions.clear();
        this.potions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            StorePotion potion = new StorePotion(AbstractDungeon.returnRandomPotion(), i, this);
            if (!Settings.isDailyRun) {
                potion.price = MathUtils.round(potion.price * AbstractDungeon.merchantRng.random(0.95f, 1.05f));
            }
            this.potions.add(potion);
        }
    }

    public void getNewPrice(StoreRelic r) {
        int retVal = r.price;
        if (!Settings.isDailyRun) {
            retVal = MathUtils.round(retVal * AbstractDungeon.merchantRng.random(0.95f, 1.05f));
        }
        if (AbstractDungeon.player.hasRelic(Courier.ID)) {
            retVal = applyDiscountToRelic(retVal, 0.8f);
        }
        if (AbstractDungeon.player.hasRelic(MembershipCard.ID)) {
            retVal = applyDiscountToRelic(retVal, 0.5f);
        }
        r.price = retVal;
    }

    public void getNewPrice(StorePotion r) {
        int retVal = r.price;
        if (!Settings.isDailyRun) {
            retVal = MathUtils.round(retVal * AbstractDungeon.merchantRng.random(0.95f, 1.05f));
        }
        if (AbstractDungeon.player.hasRelic(Courier.ID)) {
            retVal = applyDiscountToRelic(retVal, 0.8f);
        }
        if (AbstractDungeon.player.hasRelic(MembershipCard.ID)) {
            retVal = applyDiscountToRelic(retVal, 0.5f);
        }
        r.price = retVal;
    }

    private int applyDiscountToRelic(int price, float multiplier) {
        return MathUtils.round(price * multiplier);
    }

    public static AbstractRelic.RelicTier rollRelicTier() {
        int roll = AbstractDungeon.merchantRng.random(99);
        logger.info("ROLL " + roll);
        if (roll < 48) {
            return AbstractRelic.RelicTier.COMMON;
        }
        if (roll < 82) {
            return AbstractRelic.RelicTier.UNCOMMON;
        }
        return AbstractRelic.RelicTier.RARE;
    }

    private void setStartingCardPositions() {
        int tmp = ((int) ((Settings.WIDTH - (DRAW_START_X * 2.0f)) - (AbstractCard.IMG_WIDTH_S * RUG_SPEED))) / 4;
        float padX = ((int) (tmp + AbstractCard.IMG_WIDTH_S)) + (10.0f * Settings.scale);
        for (int i = 0; i < this.coloredCards.size(); i++) {
            this.coloredCards.get(i).updateHoverLogic();
            this.coloredCards.get(i).targetDrawScale = 0.75f;
            this.coloredCards.get(i).current_x = DRAW_START_X + (AbstractCard.IMG_WIDTH_S / 2.0f) + (padX * i);
            this.coloredCards.get(i).target_x = DRAW_START_X + (AbstractCard.IMG_WIDTH_S / 2.0f) + (padX * i);
            this.coloredCards.get(i).target_y = 9999.0f * Settings.scale;
            this.coloredCards.get(i).current_y = 9999.0f * Settings.scale;
        }
        for (int i2 = 0; i2 < this.colorlessCards.size(); i2++) {
            this.colorlessCards.get(i2).updateHoverLogic();
            this.colorlessCards.get(i2).targetDrawScale = 0.75f;
            this.colorlessCards.get(i2).current_x = DRAW_START_X + (AbstractCard.IMG_WIDTH_S / 2.0f) + (padX * i2);
            this.colorlessCards.get(i2).target_x = DRAW_START_X + (AbstractCard.IMG_WIDTH_S / 2.0f) + (padX * i2);
            this.colorlessCards.get(i2).target_y = 9999.0f * Settings.scale;
            this.colorlessCards.get(i2).current_y = 9999.0f * Settings.scale;
        }
    }

    public void open() {
        resetTouchscreenVars();
        CardCrawlGame.sound.play("SHOP_OPEN");
        setStartingCardPositions();
        this.purgeCardY = -1000.0f;
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.SHOP;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.cancelButton.show(NAMES[12]);
        Iterator<StoreRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            it.next().hide();
        }
        Iterator<StorePotion> it2 = this.potions.iterator();
        while (it2.hasNext()) {
            StorePotion p = it2.next();
            p.hide();
        }
        this.rugY = Settings.HEIGHT;
        this.handX = Settings.WIDTH / 2.0f;
        this.handY = Settings.HEIGHT;
        this.handTargetX = this.handX;
        this.handTargetY = this.handY;
        this.handTimer = 1.0f;
        this.speechTimer = 1.5f;
        this.speechBubble = null;
        this.dialogTextEffect = null;
        AbstractDungeon.overlayMenu.showBlackScreen();
        Iterator<AbstractCard> it3 = this.coloredCards.iterator();
        while (it3.hasNext()) {
            AbstractCard c = it3.next();
            UnlockTracker.markCardAsSeen(c.cardID);
        }
        Iterator<AbstractCard> it4 = this.colorlessCards.iterator();
        while (it4.hasNext()) {
            AbstractCard c2 = it4.next();
            UnlockTracker.markCardAsSeen(c2.cardID);
        }
        Iterator<StoreRelic> it5 = this.relics.iterator();
        while (it5.hasNext()) {
            StoreRelic r = it5.next();
            if (r.relic != null) {
                UnlockTracker.markRelicAsSeen(r.relic.relicId);
            }
        }
        if (ModHelper.isModEnabled(Hoarder.ID)) {
            this.purgeAvailable = false;
        }
    }

    private void resetTouchscreenVars() {
        if (Settings.isTouchScreen) {
            this.confirmButton.hide();
            this.confirmButton.isDisabled = false;
            this.touchRelic = null;
            this.touchCard = null;
            this.touchPotion = null;
            this.touchPurge = false;
        }
    }

    public void update() {
        if (Settings.isTouchScreen) {
            this.confirmButton.update();
            if (InputHelper.justClickedLeft && this.confirmButton.hb.hovered) {
                this.confirmButton.hb.clickStarted = true;
            }
            if (InputHelper.justReleasedClickLeft && !this.confirmButton.hb.hovered) {
                resetTouchscreenVars();
            } else if (this.confirmButton.hb.clicked) {
                this.confirmButton.hb.clicked = false;
                if (this.touchRelic != null) {
                    this.touchRelic.purchaseRelic();
                } else if (this.touchCard != null) {
                    purchaseCard(this.touchCard);
                } else if (this.touchPotion != null) {
                    this.touchPotion.purchasePotion();
                } else if (this.touchPurge) {
                    purchasePurge();
                }
                resetTouchscreenVars();
            }
        }
        if (this.handTimer != 0.0f) {
            this.handTimer -= Gdx.graphics.getDeltaTime();
            if (this.handTimer < 0.0f) {
                this.handTimer = 0.0f;
            }
        }
        this.f_effect.update();
        this.somethingHovered = false;
        updateControllerInput();
        updatePurgeCard();
        updatePurge();
        updateRelics();
        updatePotions();
        updateRug();
        updateSpeech();
        updateCards();
        updateHand();
        AbstractCard hoveredCard = null;
        Iterator<AbstractCard> it = this.coloredCards.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AbstractCard c = it.next();
            if (c.hb.hovered) {
                hoveredCard = c;
                this.somethingHovered = true;
                moveHand(c.current_x - (AbstractCard.IMG_WIDTH / 2.0f), c.current_y);
                break;
            }
        }
        Iterator<AbstractCard> it2 = this.colorlessCards.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            AbstractCard c2 = it2.next();
            if (c2.hb.hovered) {
                hoveredCard = c2;
                this.somethingHovered = true;
                moveHand(c2.current_x - (AbstractCard.IMG_WIDTH / 2.0f), c2.current_y);
                break;
            }
        }
        if (!this.somethingHovered) {
            this.notHoveredTimer += Gdx.graphics.getDeltaTime();
            if (this.notHoveredTimer > 1.0f) {
                this.handTargetY = Settings.HEIGHT;
            }
        } else {
            this.notHoveredTimer = 0.0f;
        }
        if (hoveredCard != null && InputHelper.justClickedLeft) {
            hoveredCard.hb.clickStarted = true;
        }
        if (hoveredCard != null && (InputHelper.justClickedRight || CInputActionSet.proceed.isJustPressed())) {
            InputHelper.justClickedRight = false;
            CardCrawlGame.cardPopup.open(hoveredCard);
        }
        if (hoveredCard == null) {
            return;
        }
        if (hoveredCard.hb.clicked || CInputActionSet.select.isJustPressed()) {
            hoveredCard.hb.clicked = false;
            if (!Settings.isTouchScreen) {
                purchaseCard(hoveredCard);
            } else if (this.touchCard != null) {
            } else {
                if (AbstractDungeon.player.gold < hoveredCard.price) {
                    this.speechTimer = MathUtils.random((float) MIN_IDLE_MSG_TIME, (float) MAX_IDLE_MSG_TIME);
                    playCantBuySfx();
                    createSpeech(getCantBuyMsg());
                    return;
                }
                this.confirmButton.hideInstantly();
                this.confirmButton.show();
                this.confirmButton.isDisabled = false;
                this.confirmButton.hb.clickStarted = false;
                this.touchCard = hoveredCard;
            }
        }
    }

    private void purchaseCard(AbstractCard hoveredCard) {
        AbstractCard c;
        if (AbstractDungeon.player.gold >= hoveredCard.price) {
            CardCrawlGame.metricData.addShopPurchaseData(hoveredCard.getMetricID());
            AbstractDungeon.topLevelEffects.add(new FastCardObtainEffect(hoveredCard, hoveredCard.current_x, hoveredCard.current_y));
            AbstractDungeon.player.loseGold(hoveredCard.price);
            CardCrawlGame.sound.play("SHOP_PURCHASE", 0.1f);
            if (!AbstractDungeon.player.hasRelic(Courier.ID)) {
                this.coloredCards.remove(hoveredCard);
                this.colorlessCards.remove(hoveredCard);
            } else if (hoveredCard.color == AbstractCard.CardColor.COLORLESS) {
                AbstractCard.CardRarity tempRarity = AbstractCard.CardRarity.UNCOMMON;
                if (AbstractDungeon.merchantRng.random() < AbstractDungeon.colorlessRareChance) {
                    tempRarity = AbstractCard.CardRarity.RARE;
                }
                AbstractCard c2 = AbstractDungeon.getColorlessCardFromPool(tempRarity).makeCopy();
                Iterator<AbstractRelic> it = AbstractDungeon.player.relics.iterator();
                while (it.hasNext()) {
                    AbstractRelic r = it.next();
                    r.onPreviewObtainCard(c2);
                }
                c2.current_x = hoveredCard.current_x;
                c2.current_y = hoveredCard.current_y;
                c2.target_x = c2.current_x;
                c2.target_y = c2.current_y;
                setPrice(c2);
                this.colorlessCards.set(this.colorlessCards.indexOf(hoveredCard), c2);
            } else {
                AbstractCard makeCopy = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), hoveredCard.type, false).makeCopy();
                while (true) {
                    c = makeCopy;
                    if (c.color != AbstractCard.CardColor.COLORLESS) {
                        break;
                    }
                    makeCopy = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), hoveredCard.type, false).makeCopy();
                }
                Iterator<AbstractRelic> it2 = AbstractDungeon.player.relics.iterator();
                while (it2.hasNext()) {
                    AbstractRelic r2 = it2.next();
                    r2.onPreviewObtainCard(c);
                }
                c.current_x = hoveredCard.current_x;
                c.current_y = hoveredCard.current_y;
                c.target_x = c.current_x;
                c.target_y = c.current_y;
                setPrice(c);
                this.coloredCards.set(this.coloredCards.indexOf(hoveredCard), c);
            }
            InputHelper.justClickedLeft = false;
            this.notHoveredTimer = 1.0f;
            this.speechTimer = MathUtils.random((float) MIN_IDLE_MSG_TIME, (float) MAX_IDLE_MSG_TIME);
            playBuySfx();
            createSpeech(getBuyMsg());
            return;
        }
        this.speechTimer = MathUtils.random((float) MIN_IDLE_MSG_TIME, (float) MAX_IDLE_MSG_TIME);
        playCantBuySfx();
        createSpeech(getCantBuyMsg());
    }

    private void updateCards() {
        for (int i = 0; i < this.coloredCards.size(); i++) {
            this.coloredCards.get(i).update();
            this.coloredCards.get(i).updateHoverLogic();
            this.coloredCards.get(i).current_y = this.rugY + TOP_ROW_Y;
            this.coloredCards.get(i).target_y = this.coloredCards.get(i).current_y;
        }
        for (int i2 = 0; i2 < this.colorlessCards.size(); i2++) {
            this.colorlessCards.get(i2).update();
            this.colorlessCards.get(i2).updateHoverLogic();
            this.colorlessCards.get(i2).current_y = this.rugY + BOTTOM_ROW_Y;
            this.colorlessCards.get(i2).target_y = this.colorlessCards.get(i2).current_y;
        }
    }

    private void setPrice(AbstractCard card) {
        float tmpPrice = AbstractCard.getPrice(card.rarity) * AbstractDungeon.merchantRng.random(0.9f, 1.1f);
        if (card.color == AbstractCard.CardColor.COLORLESS) {
            tmpPrice *= COLORLESS_PRICE_BUMP;
        }
        if (AbstractDungeon.player.hasRelic(Courier.ID)) {
            tmpPrice *= 0.8f;
        }
        if (AbstractDungeon.player.hasRelic(MembershipCard.ID)) {
            tmpPrice *= 0.5f;
        }
        card.price = (int) tmpPrice;
    }

    public void moveHand(float x, float y) {
        this.handTargetX = x - (50.0f * Settings.xScale);
        this.handTargetY = y + (90.0f * Settings.yScale);
    }

    private void updateControllerInput() {
        if (Settings.isControllerMode && !AbstractDungeon.topPanel.selectPotionMode && AbstractDungeon.topPanel.potionUi.isHidden && !AbstractDungeon.player.viewingRelics) {
            StoreSelectionType type = null;
            int index = 0;
            Iterator<AbstractCard> it = this.coloredCards.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AbstractCard c = it.next();
                if (c.hb.hovered) {
                    type = StoreSelectionType.COLOR_CARD;
                    break;
                }
                index++;
            }
            if (type == null) {
                index = 0;
                Iterator<StoreRelic> it2 = this.relics.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    StoreRelic r = it2.next();
                    if (r.relic.hb.hovered) {
                        type = StoreSelectionType.RELIC;
                        break;
                    }
                    index++;
                }
            }
            if (type == null) {
                index = 0;
                Iterator<AbstractCard> it3 = this.colorlessCards.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    AbstractCard c2 = it3.next();
                    if (c2.hb.hovered) {
                        type = StoreSelectionType.COLORLESS_CARD;
                        break;
                    }
                    index++;
                }
            }
            if (type == null) {
                index = 0;
                Iterator<StorePotion> it4 = this.potions.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    StorePotion p = it4.next();
                    if (p.potion.hb.hovered) {
                        type = StoreSelectionType.POTION;
                        break;
                    }
                    index++;
                }
            }
            if (type == null && this.purgeHovered) {
                type = StoreSelectionType.PURGE;
            }
            if (type != null) {
                switch (type) {
                    case COLOR_CARD:
                        if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                            int index2 = index - 1;
                            if (index2 < 0) {
                                index2 = 0;
                            }
                            Gdx.input.setCursorPosition((int) this.coloredCards.get(index2).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(index2).hb.cY));
                            return;
                        } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                            int index3 = index + 1;
                            if (index3 > this.coloredCards.size() - 1) {
                                index3--;
                            }
                            Gdx.input.setCursorPosition((int) this.coloredCards.get(index3).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(index3).hb.cY));
                            return;
                        } else if (!CInputActionSet.down.isJustPressed() && !CInputActionSet.altDown.isJustPressed()) {
                            return;
                        } else {
                            if (this.coloredCards.get(index).hb.cX >= 550.0f * Settings.scale || this.colorlessCards.isEmpty()) {
                                if (this.coloredCards.get(index).hb.cX < 850.0f * Settings.scale) {
                                    if (!this.colorlessCards.isEmpty()) {
                                        if (this.colorlessCards.size() > 1) {
                                            Gdx.input.setCursorPosition((int) this.colorlessCards.get(1).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(1).hb.cY));
                                            return;
                                        } else {
                                            Gdx.input.setCursorPosition((int) this.colorlessCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(0).hb.cY));
                                            return;
                                        }
                                    } else if (!this.relics.isEmpty()) {
                                        Gdx.input.setCursorPosition((int) this.relics.get(0).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(0).relic.hb.cY));
                                        return;
                                    } else if (!this.potions.isEmpty()) {
                                        Gdx.input.setCursorPosition((int) this.potions.get(0).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(0).potion.hb.cY));
                                    } else if (this.purgeAvailable) {
                                        Gdx.input.setCursorPosition((int) this.purgeCardX, Settings.HEIGHT - ((int) this.purgeCardY));
                                        return;
                                    }
                                }
                                if (this.coloredCards.get(index).hb.cX < 1400.0f * Settings.scale) {
                                    if (!this.relics.isEmpty()) {
                                        Gdx.input.setCursorPosition((int) this.relics.get(0).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(0).relic.hb.cY));
                                        return;
                                    } else if (!this.potions.isEmpty()) {
                                        Gdx.input.setCursorPosition((int) this.potions.get(0).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(0).potion.hb.cY));
                                    }
                                }
                                Gdx.input.setCursorPosition((int) this.purgeCardX, Settings.HEIGHT - ((int) this.purgeCardY));
                                return;
                            }
                            Gdx.input.setCursorPosition((int) this.colorlessCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(0).hb.cY));
                            return;
                        }
                    case COLORLESS_CARD:
                        if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                            int index4 = index - 1;
                            if (index4 < 0) {
                                index4 = 0;
                            }
                            Gdx.input.setCursorPosition((int) this.colorlessCards.get(index4).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(index4).hb.cY));
                            return;
                        } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                            int index5 = index + 1;
                            if (index5 <= this.colorlessCards.size() - 1) {
                                Gdx.input.setCursorPosition((int) this.colorlessCards.get(index5).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(index5).hb.cY));
                                return;
                            } else if (!this.relics.isEmpty()) {
                                Gdx.input.setCursorPosition((int) this.relics.get(0).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(0).relic.hb.cY));
                                return;
                            } else if (!this.potions.isEmpty()) {
                                Gdx.input.setCursorPosition((int) this.potions.get(0).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(0).potion.hb.cY));
                                return;
                            } else if (this.purgeAvailable) {
                                Gdx.input.setCursorPosition((int) this.purgeCardX, Settings.HEIGHT - ((int) this.purgeCardY));
                                return;
                            } else {
                                return;
                            }
                        } else if ((!CInputActionSet.up.isJustPressed() && !CInputActionSet.altUp.isJustPressed()) || this.coloredCards.isEmpty()) {
                            return;
                        } else {
                            if (this.colorlessCards.get(index).hb.cX < 550.0f * Settings.scale) {
                                Gdx.input.setCursorPosition((int) this.coloredCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(0).hb.cY));
                                return;
                            } else if (this.coloredCards.size() > 1) {
                                Gdx.input.setCursorPosition((int) this.coloredCards.get(1).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(1).hb.cY));
                                return;
                            } else {
                                Gdx.input.setCursorPosition((int) this.coloredCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(0).hb.cY));
                                return;
                            }
                        }
                    case RELIC:
                        if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                            int index6 = index - 1;
                            if (index6 >= 0 || this.colorlessCards.isEmpty()) {
                                if (index6 < 0) {
                                    index6 = 0;
                                }
                                Gdx.input.setCursorPosition((int) this.relics.get(index6).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(index6).relic.hb.cY));
                                return;
                            }
                            Gdx.input.setCursorPosition((int) this.colorlessCards.get(this.colorlessCards.size() - 1).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(this.colorlessCards.size() - 1).hb.cY));
                            return;
                        } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                            int index7 = index + 1;
                            if (index7 > this.relics.size() - 1 && this.purgeAvailable) {
                                Gdx.input.setCursorPosition((int) this.purgeCardX, Settings.HEIGHT - ((int) this.purgeCardY));
                                return;
                            } else if (index7 <= this.relics.size() - 1) {
                                Gdx.input.setCursorPosition((int) this.relics.get(index7).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(index7).relic.hb.cY));
                                return;
                            } else {
                                return;
                            }
                        } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && !this.potions.isEmpty()) {
                            if (this.potions.size() - 1 >= index) {
                                Gdx.input.setCursorPosition((int) this.potions.get(index).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(index).potion.hb.cY));
                                return;
                            } else {
                                Gdx.input.setCursorPosition((int) this.potions.get(0).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(0).potion.hb.cY));
                                return;
                            }
                        } else if ((!CInputActionSet.up.isJustPressed() && !CInputActionSet.altUp.isJustPressed()) || this.coloredCards.isEmpty()) {
                            return;
                        } else {
                            if (this.coloredCards.size() > 3) {
                                Gdx.input.setCursorPosition((int) this.coloredCards.get(2).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(2).hb.cY));
                                return;
                            } else {
                                Gdx.input.setCursorPosition((int) this.coloredCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(0).hb.cY));
                                return;
                            }
                        }
                    case POTION:
                        if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                            int index8 = index - 1;
                            if (index8 >= 0 || this.colorlessCards.isEmpty()) {
                                if (index8 < 0) {
                                    index8 = 0;
                                }
                                Gdx.input.setCursorPosition((int) this.potions.get(index8).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(index8).potion.hb.cY));
                                return;
                            }
                            Gdx.input.setCursorPosition((int) this.colorlessCards.get(this.colorlessCards.size() - 1).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(this.colorlessCards.size() - 1).hb.cY));
                            return;
                        } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                            int index9 = index + 1;
                            if (index9 > this.potions.size() - 1 && this.purgeAvailable) {
                                Gdx.input.setCursorPosition((int) this.purgeCardX, Settings.HEIGHT - ((int) this.purgeCardY));
                                return;
                            } else if (index9 <= this.potions.size() - 1) {
                                Gdx.input.setCursorPosition((int) this.potions.get(index9).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(index9).potion.hb.cY));
                                return;
                            } else {
                                return;
                            }
                        } else if (!CInputActionSet.up.isJustPressed() && !CInputActionSet.altUp.isJustPressed()) {
                            return;
                        } else {
                            if (!this.relics.isEmpty()) {
                                if (this.relics.size() - 1 >= index) {
                                    Gdx.input.setCursorPosition((int) this.relics.get(index).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(index).relic.hb.cY));
                                    return;
                                } else {
                                    Gdx.input.setCursorPosition((int) this.relics.get(0).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(0).relic.hb.cY));
                                    return;
                                }
                            } else if (this.coloredCards.isEmpty()) {
                                return;
                            } else {
                                if (this.coloredCards.size() > 3) {
                                    Gdx.input.setCursorPosition((int) this.coloredCards.get(2).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(2).hb.cY));
                                    return;
                                } else {
                                    Gdx.input.setCursorPosition((int) this.coloredCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(0).hb.cY));
                                    return;
                                }
                            }
                        }
                    case PURGE:
                        if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
                            if (!this.relics.isEmpty()) {
                                Gdx.input.setCursorPosition((int) this.relics.get(this.relics.size() - 1).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(this.relics.size() - 1).relic.hb.cY));
                                return;
                            } else if (!this.potions.isEmpty()) {
                                Gdx.input.setCursorPosition((int) this.potions.get(this.potions.size() - 1).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(this.potions.size() - 1).potion.hb.cY));
                                return;
                            } else if (this.colorlessCards.isEmpty()) {
                                Gdx.input.setCursorPosition((int) this.colorlessCards.get(this.colorlessCards.size() - 1).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(this.colorlessCards.size() - 1).hb.cY));
                                return;
                            } else {
                                return;
                            }
                        } else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && !this.coloredCards.isEmpty()) {
                            Gdx.input.setCursorPosition((int) this.coloredCards.get(this.coloredCards.size() - 1).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(this.coloredCards.size() - 1).hb.cY));
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            } else if (!this.coloredCards.isEmpty()) {
                Gdx.input.setCursorPosition((int) this.coloredCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.coloredCards.get(0).hb.cY));
            } else if (!this.colorlessCards.isEmpty()) {
                Gdx.input.setCursorPosition((int) this.colorlessCards.get(0).hb.cX, Settings.HEIGHT - ((int) this.colorlessCards.get(0).hb.cY));
            } else if (!this.relics.isEmpty()) {
                Gdx.input.setCursorPosition((int) this.relics.get(0).relic.hb.cX, Settings.HEIGHT - ((int) this.relics.get(0).relic.hb.cY));
            } else if (!this.potions.isEmpty()) {
                Gdx.input.setCursorPosition((int) this.potions.get(0).potion.hb.cX, Settings.HEIGHT - ((int) this.potions.get(0).potion.hb.cY));
            } else if (this.purgeAvailable) {
                Gdx.input.setCursorPosition((int) this.purgeCardX, Settings.HEIGHT - ((int) this.purgeCardY));
            }
        }
    }

    private void updatePurgeCard() {
        this.purgeCardX = 1554.0f * Settings.xScale;
        this.purgeCardY = this.rugY + BOTTOM_ROW_Y;
        if (this.purgeAvailable) {
            float CARD_W = 110.0f * Settings.scale;
            float CARD_H = 150.0f * Settings.scale;
            if (InputHelper.mX <= this.purgeCardX - CARD_W || InputHelper.mX >= this.purgeCardX + CARD_W || InputHelper.mY <= this.purgeCardY - CARD_H || InputHelper.mY >= this.purgeCardY + CARD_H) {
                this.purgeHovered = false;
            } else {
                this.purgeHovered = true;
                moveHand(this.purgeCardX - (AbstractCard.IMG_WIDTH / 2.0f), this.purgeCardY);
                this.somethingHovered = true;
                this.purgeCardScale = Settings.scale;
            }
            if (!this.purgeHovered) {
                this.purgeCardScale = MathHelper.cardScaleLerpSnap(this.purgeCardScale, 0.75f * Settings.scale);
                return;
            }
            if (InputHelper.justReleasedClickLeft || CInputActionSet.select.isJustPressed()) {
                if (!Settings.isTouchScreen) {
                    CInputActionSet.select.unpress();
                    purchasePurge();
                } else if (!this.touchPurge) {
                    if (AbstractDungeon.player.gold < actualPurgeCost) {
                        playCantBuySfx();
                        createSpeech(getCantBuyMsg());
                    } else {
                        this.confirmButton.hideInstantly();
                        this.confirmButton.show();
                        this.confirmButton.hb.clickStarted = false;
                        this.confirmButton.isDisabled = false;
                        this.touchPurge = true;
                    }
                }
            }
            TipHelper.renderGenericTip(InputHelper.mX - (360.0f * Settings.scale), InputHelper.mY - (70.0f * Settings.scale), LABEL[0], MSG[0] + 25 + MSG[1]);
            return;
        }
        this.purgeCardScale = MathHelper.cardScaleLerpSnap(this.purgeCardScale, 0.75f * Settings.scale);
    }

    private void purchasePurge() {
        this.purgeHovered = false;
        if (AbstractDungeon.player.gold >= actualPurgeCost) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, NAMES[13], false, false, true, true);
            return;
        }
        playCantBuySfx();
        createSpeech(getCantBuyMsg());
    }

    private void updateRelics() {
        Iterator<StoreRelic> i = this.relics.iterator();
        while (i.hasNext()) {
            StoreRelic r = i.next();
            if (Settings.isFourByThree) {
                r.update(this.rugY + (50.0f * Settings.yScale));
            } else {
                r.update(this.rugY);
            }
            if (r.isPurchased) {
                i.remove();
                return;
            }
        }
    }

    private void updatePotions() {
        Iterator<StorePotion> i = this.potions.iterator();
        while (i.hasNext()) {
            StorePotion p = i.next();
            if (Settings.isFourByThree) {
                p.update(this.rugY + (50.0f * Settings.scale));
            } else {
                p.update(this.rugY);
            }
            if (p.isPurchased) {
                i.remove();
                return;
            }
        }
    }

    public void createSpeech(String msg) {
        float offset_x;
        boolean isRight = MathUtils.randomBoolean();
        float x = MathUtils.random(660.0f, 1260.0f) * Settings.scale;
        float y = Settings.HEIGHT - (380.0f * Settings.scale);
        this.speechBubble = new ShopSpeechBubble(x, y, 4.0f, msg, isRight);
        if (isRight) {
            offset_x = SPEECH_TEXT_R_X;
        } else {
            offset_x = SPEECH_TEXT_L_X;
        }
        this.dialogTextEffect = new SpeechTextEffect(x + offset_x, y + SPEECH_TEXT_Y, 4.0f, msg, DialogWord.AppearEffect.BUMP_IN);
    }

    private void updateSpeech() {
        if (this.speechBubble != null) {
            this.speechBubble.update();
            if (this.speechBubble.hb.hovered && this.speechBubble.duration > 0.3f) {
                this.speechBubble.duration = 0.3f;
                this.dialogTextEffect.duration = 0.3f;
            }
            if (this.speechBubble.isDone) {
                this.speechBubble = null;
            }
        }
        if (this.dialogTextEffect != null) {
            this.dialogTextEffect.update();
            if (this.dialogTextEffect.isDone) {
                this.dialogTextEffect = null;
            }
        }
        this.speechTimer -= Gdx.graphics.getDeltaTime();
        if (this.speechBubble == null && this.dialogTextEffect == null && this.speechTimer <= 0.0f) {
            this.speechTimer = MathUtils.random((float) MIN_IDLE_MSG_TIME, (float) MAX_IDLE_MSG_TIME);
            if (!this.saidWelcome) {
                createSpeech(WELCOME_MSG);
                this.saidWelcome = true;
                welcomeSfx();
                return;
            }
            playMiscSfx();
            createSpeech(getIdleMsg());
        }
    }

    private void welcomeSfx() {
        int roll = MathUtils.random(2);
        if (roll == 0) {
            CardCrawlGame.sound.play("VO_MERCHANT_3A");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("VO_MERCHANT_3B");
        } else {
            CardCrawlGame.sound.play("VO_MERCHANT_3C");
        }
    }

    private void playMiscSfx() {
        int roll = MathUtils.random(5);
        if (roll == 0) {
            CardCrawlGame.sound.play("VO_MERCHANT_MA");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("VO_MERCHANT_MB");
        } else if (roll == 2) {
            CardCrawlGame.sound.play("VO_MERCHANT_MC");
        } else if (roll == 3) {
            CardCrawlGame.sound.play("VO_MERCHANT_3A");
        } else if (roll == 4) {
            CardCrawlGame.sound.play("VO_MERCHANT_3B");
        } else {
            CardCrawlGame.sound.play("VO_MERCHANT_3C");
        }
    }

    public void playBuySfx() {
        int roll = MathUtils.random(2);
        if (roll == 0) {
            CardCrawlGame.sound.play("VO_MERCHANT_KA");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("VO_MERCHANT_KB");
        } else {
            CardCrawlGame.sound.play("VO_MERCHANT_KC");
        }
    }

    public void playCantBuySfx() {
        int roll = MathUtils.random(2);
        if (roll == 0) {
            CardCrawlGame.sound.play("VO_MERCHANT_2A");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("VO_MERCHANT_2B");
        } else {
            CardCrawlGame.sound.play("VO_MERCHANT_2C");
        }
    }

    private String getIdleMsg() {
        return this.idleMessages.get(MathUtils.random(this.idleMessages.size() - 1));
    }

    private void updateRug() {
        if (this.rugY != 0.0f) {
            this.rugY = MathUtils.lerp(this.rugY, (Settings.HEIGHT / 2.0f) - (540.0f * Settings.yScale), Gdx.graphics.getDeltaTime() * RUG_SPEED);
            if (Math.abs(this.rugY - 0.0f) < 0.5f) {
                this.rugY = 0.0f;
            }
        }
    }

    private void updateHand() {
        if (this.handTimer == 0.0f) {
            if (this.handX != this.handTargetX) {
                this.handX = MathUtils.lerp(this.handX, this.handTargetX, Gdx.graphics.getDeltaTime() * 6.0f);
            }
            if (this.handY == this.handTargetY) {
                return;
            }
            if (this.handY > this.handTargetY) {
                this.handY = MathUtils.lerp(this.handY, this.handTargetY, Gdx.graphics.getDeltaTime() * 6.0f);
            } else {
                this.handY = MathUtils.lerp(this.handY, this.handTargetY, (Gdx.graphics.getDeltaTime() * 6.0f) / 4.0f);
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(rugImg, 0.0f, this.rugY, Settings.WIDTH, Settings.HEIGHT);
        renderCardsAndPrices(sb);
        renderRelics(sb);
        renderPotions(sb);
        renderPurge(sb);
        sb.draw(handImg, this.handX + this.f_effect.x, this.handY + this.f_effect.y, HAND_W, HAND_H);
        if (this.speechBubble != null) {
            this.speechBubble.render(sb);
        }
        if (this.dialogTextEffect != null) {
            this.dialogTextEffect.render(sb);
        }
        if (Settings.isTouchScreen) {
            this.confirmButton.render(sb);
        }
    }

    private void renderRelics(SpriteBatch sb) {
        Iterator<StoreRelic> it = this.relics.iterator();
        while (it.hasNext()) {
            StoreRelic r = it.next();
            r.render(sb);
        }
    }

    private void renderPotions(SpriteBatch sb) {
        Iterator<StorePotion> it = this.potions.iterator();
        while (it.hasNext()) {
            StorePotion p = it.next();
            p.render(sb);
        }
    }

    private void renderCardsAndPrices(SpriteBatch sb) {
        Iterator<AbstractCard> it = this.coloredCards.iterator();
        while (it.hasNext()) {
            AbstractCard c = it.next();
            c.render(sb);
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.UI_GOLD, c.current_x + GOLD_IMG_OFFSET_X, (c.current_y + GOLD_IMG_OFFSET_Y) - (((c.drawScale - 0.75f) * 200.0f) * Settings.scale), GOLD_IMG_WIDTH, GOLD_IMG_WIDTH);
            Color color = Color.WHITE.cpy();
            if (c.price > AbstractDungeon.player.gold) {
                color = Color.SALMON.cpy();
            } else if (c.equals(this.saleTag.card)) {
                color = Color.SKY.cpy();
            }
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, Integer.toString(c.price), c.current_x + PRICE_TEXT_OFFSET_X, (c.current_y + PRICE_TEXT_OFFSET_Y) - (((c.drawScale - 0.75f) * 200.0f) * Settings.scale), color);
        }
        Iterator<AbstractCard> it2 = this.colorlessCards.iterator();
        while (it2.hasNext()) {
            AbstractCard c2 = it2.next();
            c2.render(sb);
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.UI_GOLD, c2.current_x + GOLD_IMG_OFFSET_X, (c2.current_y + GOLD_IMG_OFFSET_Y) - (((c2.drawScale - 0.75f) * 200.0f) * Settings.scale), GOLD_IMG_WIDTH, GOLD_IMG_WIDTH);
            Color color2 = Color.WHITE.cpy();
            if (c2.price > AbstractDungeon.player.gold) {
                color2 = Color.SALMON.cpy();
            } else if (c2.equals(this.saleTag.card)) {
                color2 = Color.SKY.cpy();
            }
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, Integer.toString(c2.price), c2.current_x + PRICE_TEXT_OFFSET_X, (c2.current_y + PRICE_TEXT_OFFSET_Y) - (((c2.drawScale - 0.75f) * 200.0f) * Settings.scale), color2);
        }
        if (this.coloredCards.contains(this.saleTag.card)) {
            this.saleTag.render(sb);
        }
        if (this.colorlessCards.contains(this.saleTag.card)) {
            this.saleTag.render(sb);
        }
        Iterator<AbstractCard> it3 = this.coloredCards.iterator();
        while (it3.hasNext()) {
            AbstractCard c3 = it3.next();
            c3.renderCardTip(sb);
        }
        Iterator<AbstractCard> it4 = this.colorlessCards.iterator();
        while (it4.hasNext()) {
            AbstractCard c4 = it4.next();
            c4.renderCardTip(sb);
        }
    }

    private void renderPurge(SpriteBatch sb) {
        sb.setColor(Settings.QUARTER_TRANSPARENT_BLACK_COLOR);
        TextureAtlas.AtlasRegion tmpImg = ImageMaster.CARD_SKILL_BG_SILHOUETTE;
        sb.draw(tmpImg, ((this.purgeCardX + (18.0f * Settings.scale)) + tmpImg.offsetX) - (tmpImg.originalWidth / 2.0f), ((this.purgeCardY - (14.0f * Settings.scale)) + tmpImg.offsetY) - (tmpImg.originalHeight / 2.0f), (tmpImg.originalWidth / 2.0f) - tmpImg.offsetX, (tmpImg.originalHeight / 2.0f) - tmpImg.offsetY, tmpImg.packedWidth, tmpImg.packedHeight, this.purgeCardScale, this.purgeCardScale, 0.0f);
        sb.setColor(Color.WHITE);
        if (this.purgeAvailable) {
            sb.draw(removeServiceImg, this.purgeCardX - 256.0f, this.purgeCardY - 256.0f, 256.0f, 256.0f, 512.0f, 512.0f, this.purgeCardScale, this.purgeCardScale, 0.0f, 0, 0, 512, 512, false, false);
            sb.draw(ImageMaster.UI_GOLD, this.purgeCardX + GOLD_IMG_OFFSET_X, (this.purgeCardY + GOLD_IMG_OFFSET_Y) - ((((this.purgeCardScale / Settings.scale) - 0.75f) * 200.0f) * Settings.scale), GOLD_IMG_WIDTH, GOLD_IMG_WIDTH);
            Color color = Color.WHITE;
            if (actualPurgeCost > AbstractDungeon.player.gold) {
                color = Color.SALMON;
            }
            FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, Integer.toString(actualPurgeCost), this.purgeCardX + PRICE_TEXT_OFFSET_X, (this.purgeCardY + PRICE_TEXT_OFFSET_Y) - ((((this.purgeCardScale / Settings.scale) - 0.75f) * 200.0f) * Settings.scale), color);
            return;
        }
        sb.draw(soldOutImg, this.purgeCardX - 256.0f, this.purgeCardY - 256.0f, 256.0f, 256.0f, 512.0f, 512.0f, this.purgeCardScale, this.purgeCardScale, 0.0f, 0, 0, 512, 512, false, false);
    }
}