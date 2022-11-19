/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ 
/*     */ 
/*     */ public class AbandonRunButton
/*     */ {
/*  18 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbandonRunButton");
/*  19 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  21 */   private int W = 440; private int H = 100; private Hitbox hb;
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public AbandonRunButton() {
/*  26 */     this.hb = new Hitbox(340.0F * Settings.scale, 70.0F * Settings.scale);
/*  27 */     this.x = 1430.0F * Settings.xScale;
/*  28 */     this.y = Settings.OPTION_Y + 340.0F * Settings.scale;
/*  29 */     this.hb.move(this.x, this.y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  33 */     this.hb.update();
/*  34 */     if (this.hb.justHovered) {
/*  35 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/*  38 */     if (InputHelper.justClickedLeft && this.hb.hovered) {
/*  39 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*  40 */       this.hb.clickStarted = true;
/*     */     } 
/*     */     
/*  43 */     if (this.hb.clicked || CInputActionSet.proceed.isJustPressed()) {
/*  44 */       CInputActionSet.proceed.unpress();
/*  45 */       this.hb.clicked = false;
/*  46 */       AbstractDungeon.settingsScreen.popup(ConfirmPopup.ConfirmType.ABANDON_MID_RUN);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  51 */     sb.setColor(Color.WHITE);
/*  52 */     sb.draw(ImageMaster.OPTION_ABANDON, this.x - this.W / 2.0F, this.y - this.H / 2.0F, this.W / 2.0F, this.H / 2.0F, this.W, this.H, Settings.scale, Settings.scale, 0.0F, 0, 0, this.W, this.H, false, false);
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
/*  70 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[0], this.x + 15.0F * Settings.scale, this.y + 5.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     if (this.hb.hovered) {
/*  79 */       sb.setBlendFunction(770, 1);
/*  80 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.2F));
/*  81 */       sb.draw(ImageMaster.OPTION_ABANDON, this.x - this.W / 2.0F, this.y - this.H / 2.0F, this.W / 2.0F, this.H / 2.0F, this.W, this.H, Settings.scale, Settings.scale, 0.0F, 0, 0, this.W, this.H, false, false);
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
/*  98 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 101 */     if (Settings.isControllerMode) {
/* 102 */       sb.draw(CInputActionSet.proceed
/* 103 */           .getKeyImg(), this.x - 32.0F - 32.0F * Settings.scale - 
/* 104 */           FontHelper.getSmartWidth(FontHelper.buttonLabelFont, TEXT[0], 99999.0F, 0.0F) / 2.0F, this.y - 32.0F + 5.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\AbandonRunButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */