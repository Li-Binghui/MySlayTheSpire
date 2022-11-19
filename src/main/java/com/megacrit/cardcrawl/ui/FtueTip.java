/*     */ package com.megacrit.cardcrawl.ui;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*     */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*     */ import com.megacrit.cardcrawl.ui.buttons.GotItButton;
/*     */ 
/*     */ public class FtueTip
/*     */ {
/*  20 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("FTUE Tips"); private GotItButton button; private float x; private float y; private static final int W = 622; private static final int H = 284;
/*  21 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*     */   
/*     */   private String header;
/*     */   
/*     */   private String body;
/*     */   
/*     */   private AbstractCard c;
/*     */   private AbstractCreature m;
/*     */   private AbstractPotion p;
/*  30 */   public TipType type = null;
/*     */   public FtueTip() {}
/*     */   
/*  33 */   public enum TipType { ENERGY, CREATURE, CARD, POTION, CARD_REWARD, INTENT, SHUFFLE, NO_FTUE, COMBAT, RELIC, MULTI, POWER; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FtueTip(String header, String body, float x, float y, AbstractPotion potion) {
/*  40 */     openScreen(header, body, x, y);
/*  41 */     this.type = TipType.POTION;
/*  42 */     this.p = potion;
/*     */   }
/*     */   
/*     */   public FtueTip(String header, String body, float x, float y, TipType type) {
/*  46 */     openScreen(header, body, x, y);
/*  47 */     this.type = type;
/*     */   }
/*     */   
/*     */   public FtueTip(String header, String body, float x, float y, AbstractCard c) {
/*  51 */     openScreen(header, body, x, y);
/*  52 */     this.c = c;
/*  53 */     this.type = TipType.CARD;
/*     */   }
/*     */   
/*     */   public void openScreen(String header, String body, float x, float y) {
/*  57 */     this.header = header;
/*  58 */     this.body = body;
/*  59 */     this.x = x;
/*  60 */     this.y = y;
/*  61 */     this.c = null;
/*  62 */     this.m = null;
/*  63 */     this.p = null;
/*  64 */     this.button = new GotItButton(x, y);
/*     */     
/*  66 */     AbstractDungeon.player.releaseCard();
/*  67 */     if (AbstractDungeon.isScreenUp) {
/*  68 */       AbstractDungeon.dynamicBanner.hide();
/*  69 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */     } 
/*  71 */     AbstractDungeon.isScreenUp = true;
/*  72 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.FTUE;
/*  73 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*     */   }
/*     */   
/*     */   public void update() {
/*  77 */     this.button.update();
/*  78 */     if (this.button.hb.clicked || CInputActionSet.proceed.isJustPressed()) {
/*  79 */       CInputActionSet.proceed.unpress();
/*  80 */       CardCrawlGame.sound.play("DECK_OPEN");
/*  81 */       if (this.type == TipType.POWER) {
/*  82 */         AbstractDungeon.cardRewardScreen.reopen();
/*     */       } else {
/*  84 */         AbstractDungeon.closeCurrentScreen();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void render(SpriteBatch sb) {
/*     */     float pScale;
/*  90 */     sb.setColor(Color.WHITE);
/*  91 */     sb.draw(ImageMaster.FTUE, this.x - 311.0F, this.y - 142.0F, 311.0F, 142.0F, 622.0F, 284.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 622, 284, false, false);
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
/*     */ 
/*     */     
/* 109 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F + (
/* 110 */           MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) + 1.25F) / 5.0F));
/* 111 */     this.button.render(sb);
/*     */     
/* 113 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, LABEL[0] + this.header, this.x - 190.0F * Settings.scale, this.y + 80.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, this.body, this.x - 250.0F * Settings.scale, this.y + 20.0F * Settings.scale, 450.0F * Settings.scale, 26.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, LABEL[1], this.x + 194.0F * Settings.scale, this.y - 150.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     switch (this.type) {
/*     */       case CARD:
/* 141 */         this.c.render(sb);
/*     */         break;
/*     */       case POWER:
/* 144 */         pScale = this.c.drawScale;
/* 145 */         this.c.drawScale = 1.0F;
/* 146 */         this.c.render(sb);
/* 147 */         this.c.drawScale = pScale;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case CREATURE:
/* 153 */         if (this.m.isPlayer) {
/* 154 */           this.m.render(sb);
/*     */         } else {
/* 156 */           this.m.render(sb);
/*     */         } 
/* 158 */         if (this.m.hb.hovered) {
/* 159 */           this.m.renderPowerTips(sb);
/*     */         }
/*     */         break;
/*     */       case ENERGY:
/* 163 */         AbstractDungeon.overlayMenu.energyPanel.render(sb);
/*     */         break;
/*     */       case POTION:
/* 166 */         this.p.render(sb);
/*     */         break;
/*     */ 
/*     */       
/*     */       case SHUFFLE:
/* 171 */         AbstractDungeon.overlayMenu.combatDeckPanel.render(sb);
/* 172 */         AbstractDungeon.overlayMenu.discardPilePanel.render(sb);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (Settings.isControllerMode) {
/* 179 */       sb.setColor(Color.WHITE);
/* 180 */       sb.draw(CInputActionSet.proceed
/* 181 */           .getKeyImg(), this.button.hb.cX - 32.0F + 130.0F * Settings.scale, this.button.hb.cY - 32.0F + 2.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\FtueTip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */