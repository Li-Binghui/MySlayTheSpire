/*     */ package com.megacrit.cardcrawl.ui.campfire;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
/*     */ 
/*     */ public abstract class AbstractCampfireOption {
/*     */   protected String label;
/*     */   protected String description;
/*     */   protected Texture img;
/*     */   private static final int W = 256;
/*  24 */   private static final float SHDW_X = 11.0F * Settings.scale; private static final float SHDW_Y = -8.0F * Settings.scale;
/*  25 */   private Color color = Color.WHITE.cpy();
/*  26 */   private Color descriptionColor = Settings.CREAM_COLOR.cpy();
/*  27 */   private static final float NORM_SCALE = 0.9F * Settings.scale;
/*  28 */   private static final float HOVER_SCALE = Settings.scale;
/*  29 */   private float scale = NORM_SCALE;
/*  30 */   public Hitbox hb = new Hitbox(216.0F * Settings.scale, 140.0F * Settings.scale);
/*     */   public boolean usable = true;
/*     */   
/*     */   public void setPosition(float x, float y) {
/*  34 */     this.hb.move(x, y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  38 */     this.hb.update();
/*  39 */     boolean canClick = (!((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI.somethingSelected && this.usable);
/*  40 */     if (this.hb.hovered && canClick) {
/*  41 */       if (this.hb.justHovered) {
/*  42 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*  44 */       if (InputHelper.justClickedLeft) {
/*  45 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*  46 */         this.hb.clickStarted = true;
/*     */       } 
/*  48 */       if (!this.hb.clickStarted) {
/*  49 */         this.scale = MathHelper.scaleLerpSnap(this.scale, HOVER_SCALE);
/*  50 */         this.scale = MathHelper.scaleLerpSnap(this.scale, HOVER_SCALE);
/*     */       } else {
/*  52 */         this.scale = MathHelper.scaleLerpSnap(this.scale, NORM_SCALE);
/*     */       } 
/*     */     } else {
/*  55 */       this.scale = MathHelper.scaleLerpSnap(this.scale, NORM_SCALE);
/*     */     } 
/*     */     
/*  58 */     if (this.hb.clicked || (CInputActionSet.select.isJustPressed() && canClick && this.hb.hovered)) {
/*  59 */       this.hb.clicked = false;
/*     */       
/*  61 */       if (!Settings.isTouchScreen) {
/*  62 */         useOption();
/*  63 */         ((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI.somethingSelected = true;
/*     */       } else {
/*  65 */         CampfireUI campfire = ((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI;
/*  66 */         if (campfire.touchOption != this) {
/*  67 */           campfire.touchOption = this;
/*  68 */           campfire.confirmButton.hideInstantly();
/*  69 */           campfire.confirmButton.isDisabled = false;
/*  70 */           campfire.confirmButton.show();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract void useOption();
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  79 */     float scaler = (this.scale - NORM_SCALE) * 10.0F / Settings.scale;
/*     */     
/*  81 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 5.0F));
/*  82 */     sb.draw(this.img, this.hb.cX - 128.0F + SHDW_X, this.hb.cY - 128.0F + SHDW_Y, 128.0F, 128.0F, 256.0F, 256.0F, this.scale, this.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/* 100 */     sb.setColor(new Color(1.0F, 0.93F, 0.45F, scaler));
/* 101 */     sb.draw(ImageMaster.CAMPFIRE_HOVER_BUTTON, this.hb.cX - 128.0F, this.hb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.scale * 1.075F, this.scale * 1.075F, 0.0F, 0, 0, 256, 256, false, false);
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
/* 119 */     sb.setColor(this.color);
/* 120 */     if (!this.usable) {
/* 121 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);
/*     */     }
/* 123 */     sb.draw(this.img, this.hb.cX - 128.0F, this.hb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.scale, this.scale, 0.0F, 0, 0, 256, 256, false, false);
/* 124 */     if (!this.usable) {
/* 125 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
/*     */     }
/*     */     
/* 128 */     if (!this.usable) {
/* 129 */       FontHelper.renderFontCenteredTopAligned(sb, FontHelper.topPanelInfoFont, this.label, this.hb.cX, this.hb.cY - 60.0F * Settings.scale - 50.0F * Settings.scale * this.scale / Settings.scale, Color.LIGHT_GRAY);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 137 */       FontHelper.renderFontCenteredTopAligned(sb, FontHelper.topPanelInfoFont, this.label, this.hb.cX, this.hb.cY - 60.0F * Settings.scale - 50.0F * Settings.scale * this.scale / Settings.scale, Settings.GOLD_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     this.descriptionColor.a = scaler;
/* 147 */     FontHelper.renderFontCenteredTopAligned(sb, FontHelper.topPanelInfoFont, this.description, 950.0F * Settings.xScale, Settings.HEIGHT / 2.0F + 20.0F * Settings.scale, this.descriptionColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\campfire\AbstractCampfireOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */