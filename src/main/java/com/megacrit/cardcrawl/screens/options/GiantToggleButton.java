/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GiantToggleButton
/*     */ {
/*  17 */   private static final Logger logger = LogManager.getLogger(GiantToggleButton.class.getName());
/*     */   public boolean ticked = false;
/*     */   public ToggleType type;
/*     */   private String label;
/*  21 */   Hitbox hb = new Hitbox(320.0F * Settings.scale, 72.0F * Settings.scale); private float x;
/*  22 */   private float scale = Settings.scale;
/*     */   private float y;
/*     */   
/*  25 */   public enum ToggleType { CONTROLLER_ENABLED, TOUCHSCREEN_ENABLED; }
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
/*     */   public GiantToggleButton(ToggleType type, float x, float y, String label) {
/*  37 */     this.type = type;
/*  38 */     this.x = x;
/*  39 */     this.y = y;
/*  40 */     this.label = label;
/*  41 */     this.hb.move(x + 110.0F * Settings.scale, y);
/*  42 */     initialize();
/*     */   }
/*     */   
/*     */   private void initialize() {
/*  46 */     switch (this.type) {
/*     */       case CONTROLLER_ENABLED:
/*  48 */         this.ticked = Settings.gamePref.getBoolean("Controller Enabled", true);
/*     */         return;
/*     */       case TOUCHSCREEN_ENABLED:
/*  51 */         this.ticked = Settings.gamePref.getBoolean("Touchscreen Enabled", false);
/*     */         return;
/*     */     } 
/*  54 */     logger.info(this.type.name() + " not found (initialize())");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  60 */     this.hb.update();
/*  61 */     if (this.hb.justHovered) {
/*  62 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*  64 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  65 */       this.hb.clickStarted = true;
/*  66 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*  67 */     } else if (this.hb.clicked || (this.hb.hovered && CInputActionSet.select.isJustPressed())) {
/*  68 */       CInputActionSet.select.unpress();
/*  69 */       this.hb.clicked = false;
/*  70 */       this.ticked = !this.ticked;
/*  71 */       useEffect();
/*     */     } 
/*     */     
/*  74 */     if (this.hb.hovered) {
/*  75 */       this.scale = Settings.scale * 1.125F;
/*     */     } else {
/*  77 */       this.scale = Settings.scale;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void useEffect() {
/*  82 */     switch (this.type) {
/*     */       case CONTROLLER_ENABLED:
/*  84 */         Settings.gamePref.putBoolean("Controller Enabled", this.ticked);
/*  85 */         Settings.gamePref.flush();
/*  86 */         Settings.CONTROLLER_ENABLED = this.ticked;
/*     */         return;
/*     */       case TOUCHSCREEN_ENABLED:
/*  89 */         Settings.gamePref.putBoolean("Touchscreen Enabled", this.ticked);
/*  90 */         Settings.gamePref.flush();
/*  91 */         Settings.TOUCHSCREEN_ENABLED = this.ticked;
/*  92 */         Settings.isTouchScreen = this.ticked;
/*     */         return;
/*     */     } 
/*  95 */     logger.info(this.type.name() + " not found (useEffect())");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 101 */     sb.setColor(Color.WHITE);
/* 102 */     sb.draw(ImageMaster.CHECKBOX, this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/* 103 */     if (this.ticked) {
/* 104 */       sb.draw(ImageMaster.TICK, this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     }
/*     */     
/* 107 */     if (this.hb.hovered) {
/* 108 */       FontHelper.renderFontLeft(sb, FontHelper.panelEndTurnFont, this.label, this.x + 40.0F * Settings.scale, this.y, Settings.GREEN_TEXT_COLOR);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 116 */       FontHelper.renderFontLeft(sb, FontHelper.panelEndTurnFont, this.label, this.x + 40.0F * Settings.scale, this.y, Settings.CREAM_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\GiantToggleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */