/*     */ package com.megacrit.cardcrawl.ui.buttons;
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
import com.megacrit.cardcrawl.vfx.TintEffect;
/*     */ 
/*     */ public class ReturnToMenuButton {
/*     */   public static final int RAW_W = 512;
/*  16 */   private static final float BUTTON_W = 240.0F * Settings.scale;
/*  17 */   private static final float BUTTON_H = 160.0F * Settings.scale;
/*     */   private static final float LERP_SPEED = 9.0F;
/*  19 */   private static final Color TEXT_SHOW_COLOR = new Color(0.9F, 0.9F, 0.9F, 1.0F);
/*  20 */   private static final Color HIGHLIGHT_COLOR = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*  21 */   private static final Color IDLE_COLOR = new Color(0.7F, 0.7F, 0.7F, 1.0F);
/*     */   
/*  23 */   private static final Color FADE_COLOR = new Color(0.3F, 0.3F, 0.3F, 1.0F);
/*     */   public String label;
/*     */   public float x;
/*     */   public float y;
/*     */   public Hitbox hb;
/*  28 */   protected TintEffect tint = new TintEffect();
/*  29 */   protected TintEffect textTint = new TintEffect();
/*     */   
/*     */   public boolean pressed = false;
/*     */   public boolean isMoving = false;
/*     */   
/*     */   public ReturnToMenuButton() {
/*  35 */     this.tint.color.a = 0.0F;
/*  36 */     this.textTint.color.a = 0.0F;
/*  37 */     this.hb = new Hitbox(-10000.0F, -10000.0F, BUTTON_W, BUTTON_H);
/*     */   }
/*     */   public boolean show = false; public int height; public int width;
/*     */   public void appear(float x, float y, String label) {
/*  41 */     this.x = x;
/*  42 */     this.y = y;
/*  43 */     this.label = label;
/*  44 */     this.pressed = false;
/*  45 */     this.isMoving = true;
/*  46 */     this.show = true;
/*  47 */     this.tint.changeColor(IDLE_COLOR, 9.0F);
/*  48 */     this.textTint.changeColor(TEXT_SHOW_COLOR, 9.0F);
/*     */   }
/*     */   
/*     */   public void hide() {
/*  52 */     this.show = false;
/*  53 */     this.isMoving = false;
/*  54 */     this.tint.changeColor(FADE_COLOR, 9.0F);
/*  55 */     this.textTint.changeColor(FADE_COLOR, 9.0F);
/*     */   }
/*     */   
/*     */   public void update() {
/*  59 */     this.tint.update();
/*  60 */     this.textTint.update();
/*     */     
/*  62 */     if (this.show) {
/*  63 */       this.hb.move(this.x, this.y);
/*  64 */       this.hb.update();
/*  65 */       if (InputHelper.justClickedLeft && this.hb.hovered) {
/*  66 */         this.hb.clickStarted = true;
/*  67 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*  69 */       if (this.hb.justHovered) {
/*  70 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*     */ 
/*     */       
/*  74 */       if (this.hb.hovered || Settings.isControllerMode) {
/*  75 */         this.tint.changeColor(HIGHLIGHT_COLOR, 18.0F);
/*     */       } else {
/*  77 */         this.tint.changeColor(IDLE_COLOR, 9.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  83 */     if (this.textTint.color.a == 0.0F || this.label == null) {
/*     */       return;
/*     */     }
/*     */     
/*  87 */     if (this.hb.clickStarted) {
/*  88 */       sb.setColor(Color.LIGHT_GRAY);
/*     */     } else {
/*  90 */       sb.setColor(this.tint.color);
/*     */     } 
/*     */     
/*  93 */     sb.draw(ImageMaster.DYNAMIC_BTN_IMG2, this.x - 256.0F, this.y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
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
/* 111 */     if (this.hb.clickStarted) {
/* 112 */       FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, this.label, this.x, this.y, Color.LIGHT_GRAY);
/*     */     } else {
/* 114 */       FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, this.label, this.x, this.y, this.tint.color);
/*     */     } 
/*     */     
/* 117 */     if (Settings.isControllerMode) {
/* 118 */       sb.draw(CInputActionSet.select
/* 119 */           .getKeyImg(), Settings.WIDTH / 2.0F - 32.0F - 100.0F * Settings.scale, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 137 */     if (!this.pressed && this.show)
/* 138 */       this.hb.render(sb); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\ReturnToMenuButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */