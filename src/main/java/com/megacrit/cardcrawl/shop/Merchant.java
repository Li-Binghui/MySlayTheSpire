/*     */ package com.megacrit.cardcrawl.shop;
/*     */ 
/*     */

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
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Merchant
/*     */   implements Disposable
/*     */ {
/*  30 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Merchant");
/*  31 */   public static final String[] NAMES = characterStrings.NAMES;
/*  32 */   public static final String[] TEXT = characterStrings.TEXT;
/*  33 */   public static final String[] ENDING_TEXT = characterStrings.OPTIONS;
/*     */   
/*     */   public AnimatedNpc anim;
/*  36 */   public static final float DRAW_X = Settings.WIDTH * 0.5F + 34.0F * Settings.xScale; public static final float DRAW_Y = AbstractDungeon.floorY - 109.0F * Settings.scale;
/*     */   
/*  38 */   public Hitbox hb = new Hitbox(360.0F * Settings.scale, 300.0F * Settings.scale);
/*  39 */   private ArrayList<AbstractCard> cards1 = new ArrayList<>();
/*  40 */   private ArrayList<AbstractCard> cards2 = new ArrayList<>();
/*     */ 
/*     */   
/*  43 */   private ArrayList<String> idleMessages = new ArrayList<>();
/*  44 */   private float speechTimer = 1.5F;
/*     */   private boolean saidWelcome = false;
/*     */   private static final float MIN_IDLE_MSG_TIME = 40.0F;
/*     */   private static final float MAX_IDLE_MSG_TIME = 60.0F;
/*     */   private static final float SPEECH_DURATION = 3.0F;
/*  49 */   private int shopScreen = 1;
/*     */   protected float modX;
/*     */   protected float modY;
/*     */   
/*     */   public Merchant() {
/*  54 */     this(0.0F, 0.0F, 1);
/*     */   }
/*     */   
/*     */   public Merchant(float x, float y, int newShopScreen) {
/*  58 */     this.anim = new AnimatedNpc(DRAW_X + 256.0F * Settings.scale, AbstractDungeon.floorY + 30.0F * Settings.scale, "images/npcs/merchant/skeleton.atlas", "images/npcs/merchant/skeleton.json", "idle");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     AbstractCard c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
/*  71 */     while (c.color == AbstractCard.CardColor.COLORLESS) {
/*  72 */       c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
/*     */     }
/*  74 */     this.cards1.add(c);
/*     */ 
/*     */     
/*  77 */     c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
/*  78 */     while (Objects.equals(c.cardID, ((AbstractCard)this.cards1.get(this.cards1.size() - 1)).cardID) || c.color == AbstractCard.CardColor.COLORLESS)
/*     */     {
/*  80 */       c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
/*     */     }
/*  82 */     this.cards1.add(c);
/*     */ 
/*     */     
/*  85 */     c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
/*  86 */     while (c.color == AbstractCard.CardColor.COLORLESS) {
/*  87 */       c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
/*     */     }
/*  89 */     this.cards1.add(c);
/*     */ 
/*     */     
/*  92 */     c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
/*  93 */     while (Objects.equals(c.cardID, ((AbstractCard)this.cards1.get(this.cards1.size() - 1)).cardID) || c.color == AbstractCard.CardColor.COLORLESS)
/*     */     {
/*  95 */       c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.SKILL, true).makeCopy();
/*     */     }
/*  97 */     this.cards1.add(c);
/*     */ 
/*     */     
/* 100 */     c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.POWER, true).makeCopy();
/* 101 */     while (c.color == AbstractCard.CardColor.COLORLESS) {
/* 102 */       c = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), AbstractCard.CardType.POWER, true).makeCopy();
/*     */     }
/* 104 */     this.cards1.add(c);
/*     */ 
/*     */     
/* 107 */     this.cards2.add(AbstractDungeon.getColorlessCardFromPool(AbstractCard.CardRarity.UNCOMMON).makeCopy());
/* 108 */     this.cards2.add(AbstractDungeon.getColorlessCardFromPool(AbstractCard.CardRarity.RARE).makeCopy());
/*     */ 
/*     */     
/* 111 */     if (AbstractDungeon.id.equals("TheEnding")) {
/* 112 */       Collections.addAll(this.idleMessages, ENDING_TEXT);
/*     */     } else {
/* 114 */       Collections.addAll(this.idleMessages, TEXT);
/*     */     } 
/*     */     
/* 117 */     this.speechTimer = 1.5F;
/* 118 */     this.modX = x;
/* 119 */     this.modY = y;
/* 120 */     this.hb.move(DRAW_X + (250.0F + x) * Settings.scale, DRAW_Y + (130.0F + y) * Settings.scale);
/* 121 */     this.shopScreen = newShopScreen;
/* 122 */     AbstractDungeon.shopScreen.init(this.cards1, this.cards2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 127 */     this.hb.update();
/* 128 */     if (((this.hb.hovered && InputHelper.justClickedLeft) || CInputActionSet.select.isJustPressed()) && !AbstractDungeon.isScreenUp && !AbstractDungeon.isFadingOut && !AbstractDungeon.player.viewingRelics) {
/*     */ 
/*     */       
/* 131 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(NAMES[0]);
/* 132 */       this.saidWelcome = true;
/* 133 */       AbstractDungeon.shopScreen.open();
/*     */       
/* 135 */       this.hb.hovered = false;
/*     */     } 
/*     */     
/* 138 */     this.speechTimer -= Gdx.graphics.getDeltaTime();
/*     */     
/* 140 */     if (this.speechTimer < 0.0F && this.shopScreen == 1) {
/* 141 */       String msg = this.idleMessages.get(MathUtils.random(0, this.idleMessages.size() - 1));
/* 142 */       if (!this.saidWelcome) {
/* 143 */         this.saidWelcome = true;
/* 144 */         welcomeSfx();
/* 145 */         msg = NAMES[1];
/*     */       } else {
/* 147 */         playMiscSfx();
/*     */       } 
/*     */       
/* 150 */       if (MathUtils.randomBoolean()) {
/* 151 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX - 50.0F * Settings.xScale, this.hb.cY + 70.0F * Settings.yScale, 3.0F, msg, false));
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 159 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX - 50.0F * Settings.xScale, this.hb.cY + 70.0F * Settings.yScale, 3.0F, msg, true));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       this.speechTimer = MathUtils.random(40.0F, 60.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void welcomeSfx() {
/* 172 */     int roll = MathUtils.random(2);
/* 173 */     if (roll == 0) {
/* 174 */       CardCrawlGame.sound.play("VO_MERCHANT_3A");
/* 175 */     } else if (roll == 1) {
/* 176 */       CardCrawlGame.sound.play("VO_MERCHANT_3B");
/*     */     } else {
/* 178 */       CardCrawlGame.sound.play("VO_MERCHANT_3C");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playMiscSfx() {
/* 183 */     int roll = MathUtils.random(5);
/* 184 */     if (roll == 0) {
/* 185 */       CardCrawlGame.sound.play("VO_MERCHANT_MA");
/* 186 */     } else if (roll == 1) {
/* 187 */       CardCrawlGame.sound.play("VO_MERCHANT_MB");
/* 188 */     } else if (roll == 2) {
/* 189 */       CardCrawlGame.sound.play("VO_MERCHANT_MC");
/* 190 */     } else if (roll == 3) {
/* 191 */       CardCrawlGame.sound.play("VO_MERCHANT_3A");
/* 192 */     } else if (roll == 4) {
/* 193 */       CardCrawlGame.sound.play("VO_MERCHANT_3B");
/*     */     } else {
/* 195 */       CardCrawlGame.sound.play("VO_MERCHANT_3C");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 200 */     sb.setColor(Color.WHITE);
/* 201 */     sb.draw(ImageMaster.MERCHANT_RUG_IMG, DRAW_X + this.modX, DRAW_Y + this.modY, 512.0F * Settings.scale, 512.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     if (this.hb.hovered) {
/* 209 */       sb.setBlendFunction(770, 1);
/* 210 */       sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);
/* 211 */       sb.draw(ImageMaster.MERCHANT_RUG_IMG, DRAW_X + this.modX, DRAW_Y + this.modY, 512.0F * Settings.scale, 512.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 217 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 220 */     if (this.anim != null) {
/* 221 */       this.anim.render(sb);
/*     */     }
/*     */     
/* 224 */     if (Settings.isControllerMode) {
/* 225 */       sb.setColor(Color.WHITE);
/* 226 */       sb.draw(CInputActionSet.select
/* 227 */           .getKeyImg(), DRAW_X - 32.0F + 150.0F * Settings.scale, DRAW_Y - 32.0F + 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 248 */     if (this.anim != null)
/* 249 */       this.anim.dispose(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\shop\Merchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */