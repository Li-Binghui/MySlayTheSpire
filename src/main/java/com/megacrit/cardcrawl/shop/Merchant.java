package com.megacrit.cardcrawl.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AnimatedNpc;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Merchant implements Disposable {
    public AnimatedNpc anim;
    public Hitbox hb;
    private ArrayList<AbstractCard> cards1;
    private ArrayList<AbstractCard> cards2;
    private ArrayList<String> idleMessages;
    private float speechTimer;
    private boolean saidWelcome;
    private static final float MIN_IDLE_MSG_TIME = 40.0f;
    private static final float MAX_IDLE_MSG_TIME = 60.0f;
    private static final float SPEECH_DURATION = 3.0f;
    private int shopScreen;
    protected float modX;
    protected float modY;
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Merchant");
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;
    public static final String[] ENDING_TEXT = characterStrings.OPTIONS;
    public static final float DRAW_X = (Settings.WIDTH * 0.5f) + (34.0f * Settings.xScale);
    public static final float DRAW_Y = AbstractDungeon.floorY - (109.0f * Settings.scale);

    public Merchant() {
        this(0.0f, 0.0f, 1);
    }

    public Merchant(float x, float y, int newShopScreen) {
        AbstractCard c;
        AbstractCard c2;
        AbstractCard c3;
        AbstractCard c4;
        AbstractCard c5;
        this.hb = new Hitbox(360.0f * Settings.scale, 300.0f * Settings.scale);
        this.cards1 = new ArrayList<>();
        this.cards2 = new ArrayList<>();
        this.idleMessages = new ArrayList<>();
        this.speechTimer = 1.5f;
        this.saidWelcome = false;
        this.shopScreen = 1;
        this.anim = new AnimatedNpc(DRAW_X + (256.0f * Settings.scale), AbstractDungeon.floorY + (30.0f * Settings.scale), "images/npcs/merchant/skeleton.atlas", "images/npcs/merchant/skeleton.json", "idle");
        AbstractCard makeCopy = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
        while (true) {
            c = makeCopy;
            if (c.color != AbstractCard.CardColor.COLORLESS) {
                break;
            }
            makeCopy = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
        }
        this.cards1.add(c);
        AbstractCard makeCopy2 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
        while (true) {
            c2 = makeCopy2;
            if (!Objects.equals(c2.cardID, this.cards1.get(this.cards1.size() - 1).cardID) && c2.color != AbstractCard.CardColor.COLORLESS) {
                break;
            }
            makeCopy2 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
        }
        this.cards1.add(c2);
        AbstractCard makeCopy3 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
        while (true) {
            c3 = makeCopy3;
            if (c3.color != AbstractCard.CardColor.COLORLESS) {
                break;
            }
            makeCopy3 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
        }
        this.cards1.add(c3);
        AbstractCard makeCopy4 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
        while (true) {
            c4 = makeCopy4;
            if (!Objects.equals(c4.cardID, this.cards1.get(this.cards1.size() - 1).cardID) && c4.color != AbstractCard.CardColor.COLORLESS) {
                break;
            }
            makeCopy4 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
        }
        this.cards1.add(c4);
        AbstractCard makeCopy5 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.POWER, true).makeCopy();
        while (true) {
            c5 = makeCopy5;
            if (c5.color != AbstractCard.CardColor.COLORLESS) {
                break;
            }
            makeCopy5 = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.POWER, true).makeCopy();
        }
        this.cards1.add(c5);
        this.cards2.add(AbstractDungeon.getColorlessCardFromPool(AbstractCard.CardRarity.UNCOMMON).makeCopy());
        this.cards2.add(AbstractDungeon.getColorlessCardFromPool(AbstractCard.CardRarity.RARE).makeCopy());
        if (AbstractDungeon.id.equals(TheEnding.ID)) {
            Collections.addAll(this.idleMessages, ENDING_TEXT);
        } else {
            Collections.addAll(this.idleMessages, TEXT);
        }
        this.speechTimer = 1.5f;
        this.modX = x;
        this.modY = y;
        this.hb.move(DRAW_X + ((250.0f + x) * Settings.scale), DRAW_Y + ((130.0f + y) * Settings.scale));
        this.shopScreen = newShopScreen;
        AbstractDungeon.shopScreen.init(this.cards1, this.cards2);
    }

    public void update() {
        this.hb.update();
        if (((this.hb.hovered && InputHelper.justClickedLeft) || CInputActionSet.select.isJustPressed()) && !AbstractDungeon.isScreenUp && !AbstractDungeon.isFadingOut && !AbstractDungeon.player.viewingRelics) {
            AbstractDungeon.overlayMenu.proceedButton.setLabel(NAMES[0]);
            this.saidWelcome = true;
            AbstractDungeon.shopScreen.open();
            this.hb.hovered = false;
        }
        this.speechTimer -= Gdx.graphics.getDeltaTime();
        if (this.speechTimer < 0.0f && this.shopScreen == 1) {
            String msg = this.idleMessages.get(MathUtils.random(0, this.idleMessages.size() - 1));
            if (!this.saidWelcome) {
                this.saidWelcome = true;
                welcomeSfx();
                msg = NAMES[1];
            } else {
                playMiscSfx();
            }
            if (MathUtils.randomBoolean()) {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX - (50.0f * Settings.xScale), this.hb.cY + (70.0f * Settings.yScale), 3.0f, msg, false));
            } else {
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX - (50.0f * Settings.xScale), this.hb.cY + (70.0f * Settings.yScale), 3.0f, msg, true));
            }
            this.speechTimer = MathUtils.random((float) MIN_IDLE_MSG_TIME, (float) MAX_IDLE_MSG_TIME);
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

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.MERCHANT_RUG_IMG, DRAW_X + this.modX, DRAW_Y + this.modY, 512.0f * Settings.scale, 512.0f * Settings.scale);
        if (this.hb.hovered) {
            sb.setBlendFunction(770, 1);
            sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);
            sb.draw(ImageMaster.MERCHANT_RUG_IMG, DRAW_X + this.modX, DRAW_Y + this.modY, 512.0f * Settings.scale, 512.0f * Settings.scale);
            sb.setBlendFunction(770, 771);
        }
        if (this.anim != null) {
            this.anim.render(sb);
        }
        if (Settings.isControllerMode) {
            sb.setColor(Color.WHITE);
            sb.draw(CInputActionSet.select.getKeyImg(), (DRAW_X - 32.0f) + (150.0f * Settings.scale), (DRAW_Y - 32.0f) + (100.0f * Settings.scale), 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        }
        this.hb.render(sb);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.anim != null) {
            this.anim.dispose();
        }
    }
}