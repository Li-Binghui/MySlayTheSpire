/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
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
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExitGameButton
/*     */ {
/*  23 */   private static final Logger logger = LogManager.getLogger(ExitGameButton.class.getName());
/*  24 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExitGameButton");
/*  25 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  27 */   private int W = 635; private int H = 488; private Hitbox hb;
/*     */   private float x;
/*     */   private float y;
/*  30 */   private String label = TEXT[0];
/*     */   
/*     */   public ExitGameButton() {
/*  33 */     this.hb = new Hitbox(280.0F * Settings.scale, 80.0F * Settings.scale);
/*  34 */     this.x = 1490.0F * Settings.xScale;
/*  35 */     this.y = Settings.OPTION_Y - 202.0F * Settings.scale;
/*  36 */     this.hb.move(this.x + 50.0F * Settings.xScale, this.y - 173.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  40 */     this.hb.update();
/*  41 */     if (this.hb.justHovered) {
/*  42 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/*  45 */     if (this.hb.clicked || CInputActionSet.discardPile.isJustPressed()) {
/*  46 */       this.hb.clicked = false;
/*  47 */       AbstractDungeon.settingsScreen.popup(ConfirmPopup.ConfirmType.EXIT);
/*     */     } 
/*     */     
/*  50 */     if ((this.hb.hovered && InputHelper.justClickedLeft) || CInputActionSet.discardPile.isJustPressed()) {
/*  51 */       if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/*  52 */         logger.info("Exit Game button clicked!");
/*  53 */         Gdx.app.exit();
/*     */       } 
/*  55 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*  56 */       this.hb.clickStarted = true;
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateLabel(String newLabel) {
/*  62 */     this.label = newLabel;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  66 */     sb.setColor(Color.WHITE);
/*  67 */     sb.draw(ImageMaster.OPTION_EXIT, this.x - this.W / 2.0F, this.y - this.H / 2.0F, this.W / 2.0F, this.H / 2.0F, this.W, this.H, Settings.scale, Settings.scale, 0.0F, 0, 0, this.W, this.H, false, false);
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
/*  85 */     FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.label, this.x + 50.0F * Settings.scale, this.y - 170.0F * Settings.scale, Settings.GOLD_COLOR, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     if (this.hb.hovered) {
/*  95 */       sb.setBlendFunction(770, 1);
/*  96 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.2F));
/*  97 */       sb.draw(ImageMaster.OPTION_EXIT, this.x - this.W / 2.0F, this.y - this.H / 2.0F, this.W / 2.0F, this.H / 2.0F, this.W, this.H, Settings.scale, Settings.scale, 0.0F, 0, 0, this.W, this.H, false, false);
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
/* 114 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 117 */     if (Settings.isControllerMode) {
/* 118 */       sb.draw(CInputActionSet.discardPile
/* 119 */           .getKeyImg(), this.x - 32.0F - 32.0F * Settings.scale - 
/* 120 */           FontHelper.getSmartWidth(FontHelper.losePowerFont, this.label, 99999.0F, 0.0F) / 2.0F, this.y - 32.0F - 170.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 138 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\ExitGameButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */