/*     */ package com.megacrit.cardcrawl.ui.panels.energyorb;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class EnergyOrbPurple
/*     */   implements EnergyOrbInterface {
/*     */   private static final int ORB_W = 128;
/*  12 */   public static float fontScale = 1.0F;
/*  13 */   private static final float ORB_IMG_SCALE = 1.15F * Settings.scale;
/*     */   private float angle4;
/*     */   
/*     */   public void updateOrb(int orbCount) {
/*  17 */     if (orbCount == 0) {
/*     */       
/*  19 */       this.angle4 += Gdx.graphics.getDeltaTime() * 5.0F;
/*  20 */       this.angle3 += Gdx.graphics.getDeltaTime() * -8.0F;
/*  21 */       this.angle2 += Gdx.graphics.getDeltaTime() * 8.0F;
/*     */     } else {
/*     */       
/*  24 */       this.angle4 += Gdx.graphics.getDeltaTime() * 20.0F;
/*  25 */       this.angle3 += Gdx.graphics.getDeltaTime() * -40.0F;
/*  26 */       this.angle2 += Gdx.graphics.getDeltaTime() * 40.0F;
/*     */     } 
/*     */   }
/*     */   private float angle3; private float angle2;
/*     */   public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
/*  31 */     if (enabled) {
/*  32 */       sb.setColor(Color.WHITE);
/*  33 */       sb.draw(ImageMaster.ENERGY_PURPLE_LAYER1, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
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
/*  51 */       sb.draw(ImageMaster.ENERGY_PURPLE_LAYER2, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle2, 0, 0, 128, 128, false, false);
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
/*  69 */       sb.draw(ImageMaster.ENERGY_PURPLE_LAYER3, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle3, 0, 0, 128, 128, false, false);
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
/*  87 */       sb.draw(ImageMaster.ENERGY_PURPLE_LAYER4, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle4, 0, 0, 128, 128, false, false);
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
/*     */ 
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
/* 144 */       sb.setColor(Color.GRAY);
/* 145 */       sb.draw(ImageMaster.ENERGY_PURPLE_LAYER1, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
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
/* 163 */     sb.setColor(Color.WHITE);
/*     */ 
/*     */     
/* 166 */     sb.draw(ImageMaster.ENERGY_PURPLE_BORDER, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\energyorb\EnergyOrbPurple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */