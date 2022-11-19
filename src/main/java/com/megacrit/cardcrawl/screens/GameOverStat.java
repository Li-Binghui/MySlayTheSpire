/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ public class GameOverStat {
/*     */   public final String label;
/*  15 */   private static final float VALUE_X = 430.0F * Settings.scale; public final String description; public final String value;
/*     */   public boolean hidden = true;
/*  17 */   private Color color = Settings.CREAM_COLOR.cpy();
/*  18 */   private float offsetX = -50.0F * Settings.scale;
/*  19 */   public Hitbox hb = null;
/*     */   
/*     */   public GameOverStat() {
/*  22 */     this.label = null;
/*  23 */     this.description = null;
/*  24 */     this.value = null;
/*  25 */     this.hb = new Hitbox(250.0F * Settings.scale, 32.0F * Settings.scale);
/*  26 */     this.color.a = 0.0F;
/*     */   }
/*     */   
/*     */   public GameOverStat(String label, String description, String value) {
/*  30 */     this.label = label;
/*     */     
/*  32 */     if (description != null && description.equals("")) {
/*  33 */       this.description = null;
/*     */     } else {
/*  35 */       this.description = description;
/*     */     } 
/*     */     
/*  38 */     this.hb = new Hitbox(250.0F * Settings.scale, 32.0F * Settings.scale);
/*  39 */     this.value = value;
/*  40 */     this.color.a = 0.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  44 */     if (!this.hidden) {
/*  45 */       this.color.a = MathHelper.slowColorLerpSnap(this.color.a, 1.0F);
/*  46 */       this.offsetX = MathHelper.uiLerpSnap(this.offsetX, 0.0F);
/*  47 */       if (this.hb != null) {
/*  48 */         this.hb.update();
/*  49 */         if (this.hb.hovered && this.description != null) {
/*  50 */           TipHelper.renderGenericTip(InputHelper.mX + 80.0F * Settings.scale, InputHelper.mY, this.label, this.description);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderLine(SpriteBatch sb, boolean isWide, float y) {
/*  63 */     if (isWide) {
/*  64 */       sb.setColor(this.color);
/*  65 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, Settings.WIDTH / 2.0F - 525.0F * Settings.scale, y - 10.0F * Settings.scale, 1050.0F * Settings.scale, 4.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  72 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 4.0F));
/*  73 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, Settings.WIDTH / 2.0F - 525.0F * Settings.scale, y - 10.0F * Settings.scale, 1050.0F * Settings.scale, 1.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/*  82 */       sb.setColor(this.color);
/*  83 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, Settings.WIDTH / 2.0F - 222.0F * Settings.scale, y - 10.0F * Settings.scale, 434.0F * Settings.scale, 4.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 4.0F));
/*  91 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, Settings.WIDTH / 2.0F - 222.0F * Settings.scale, y - 10.0F * Settings.scale, 434.0F * Settings.scale, 1.0F * Settings.scale);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb, float x, float y) {
/* 101 */     if (this.label == null || this.value == null) {
/*     */       return;
/*     */     }
/*     */     
/* 105 */     Color prevColor = null;
/* 106 */     if (this.hb != null && this.hb.hovered) {
/* 107 */       prevColor = this.color;
/* 108 */       this.color = new Color(0.0F, 0.9F, 0.0F, this.color.a);
/*     */     } 
/*     */ 
/*     */     
/* 112 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, this.label, x + this.offsetX, y, this.color);
/*     */ 
/*     */     
/* 115 */     FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, this.value, x + VALUE_X + this.offsetX, y, this.color);
/*     */     
/* 117 */     if (this.hb != null) {
/* 118 */       this.hb.move(x + 120.0F * Settings.scale, y - 8.0F * Settings.scale);
/* 119 */       this.hb.render(sb);
/*     */       
/* 121 */       if (this.hb.hovered)
/* 122 */         this.color = prevColor; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\GameOverStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */