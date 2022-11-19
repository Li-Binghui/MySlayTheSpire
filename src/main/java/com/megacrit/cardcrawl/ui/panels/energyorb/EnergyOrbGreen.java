/*     */ package com.megacrit.cardcrawl.ui.panels.energyorb;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class EnergyOrbGreen
/*     */   implements EnergyOrbInterface
/*     */ {
/*     */   private static final int ORB_W = 128;
/*  13 */   public static float fontScale = 1.0F;
/*  14 */   private static final float ORB_IMG_SCALE = 1.15F * Settings.scale;
/*     */   private float angle4;
/*     */   
/*     */   public void updateOrb(int orbCount) {
/*  18 */     if (orbCount == 0) {
/*  19 */       this.angle4 += Gdx.graphics.getDeltaTime() * 5.0F;
/*  20 */       this.angle3 += Gdx.graphics.getDeltaTime() * -8.0F;
/*  21 */       this.angle2 += Gdx.graphics.getDeltaTime() * 8.0F;
/*     */     } else {
/*  23 */       this.angle4 += Gdx.graphics.getDeltaTime() * 20.0F;
/*  24 */       this.angle3 += Gdx.graphics.getDeltaTime() * -40.0F;
/*  25 */       this.angle2 += Gdx.graphics.getDeltaTime() * 40.0F;
/*     */     } 
/*     */   }
/*     */   private float angle3; private float angle2;
/*     */   public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
/*  30 */     if (enabled) {
/*  31 */       sb.setColor(Color.WHITE);
/*     */       
/*  33 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER2, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
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
/*  51 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER3, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
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
/*  69 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER4, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle3, 0, 0, 128, 128, false, false);
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
/*  87 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER5, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
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
/* 105 */       sb.setBlendFunction(770, 1);
/* 106 */       sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);
/* 107 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER1, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle4, 0, 0, 128, 128, false, false);
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
/* 124 */       sb.setBlendFunction(770, 771);
/*     */       
/* 126 */       sb.setColor(Color.WHITE);
/*     */       
/* 128 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER6, current_x - 128.0F, current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 256, 256, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       sb.setColor(Color.WHITE);
/* 147 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER2D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle2, 0, 0, 128, 128, false, false);
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
/* 165 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER3D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
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
/* 183 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER4D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle3, 0, 0, 128, 128, false, false);
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
/* 202 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER5D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
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
/* 220 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER1D, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle4, 0, 0, 128, 128, false, false);
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
/* 239 */       sb.draw(ImageMaster.ENERGY_GREEN_LAYER6, current_x - 128.0F, current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 256, 256, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\energyorb\EnergyOrbGreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */