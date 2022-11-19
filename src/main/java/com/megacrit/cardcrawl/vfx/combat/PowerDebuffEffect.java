/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class PowerDebuffEffect extends AbstractGameEffect {
/*    */   private static final float TEXT_DURATION = 2.0F;
/* 15 */   private static final float STARTING_OFFSET_Y = 60.0F * Settings.scale;
/* 16 */   private static final float TARGET_OFFSET_Y = 100.0F * Settings.scale;
/*    */   
/*    */   private float x;
/*    */   
/*    */   private float y;
/*    */   
/*    */   public PowerDebuffEffect(float x, float y, String msg) {
/* 23 */     this.duration = 2.0F;
/* 24 */     this.startingDuration = 2.0F;
/* 25 */     this.msg = msg;
/* 26 */     this.x = x;
/* 27 */     this.y = y;
/* 28 */     this.targetColor = Settings.RED_TEXT_COLOR;
/* 29 */     this.color = Color.WHITE.cpy();
/* 30 */     this.offsetY = STARTING_OFFSET_Y;
/* 31 */     this.scale = Settings.scale * 0.5F;
/*    */   }
/*    */   private float offsetY; private String msg; private Color targetColor;
/*    */   
/*    */   public void update() {
/* 36 */     if (this.duration == this.startingDuration && !Settings.DISABLE_EFFECTS) {
/* 37 */       int i; for (i = 0; i < 10; i++) {
/* 38 */         AbstractDungeon.effectsQueue.add(new FlyingSpikeEffect(this.x - 
/*    */               
/* 40 */               MathUtils.random(20.0F) * Settings.scale, this.y + 
/* 41 */               MathUtils.random(40.0F, 160.0F) * Settings.scale, 
/* 42 */               MathUtils.random(360.0F), 
/* 43 */               MathUtils.random(50.0F, 400.0F) * Settings.scale, 0.0F, Settings.RED_TEXT_COLOR));
/*    */       }
/*    */ 
/*    */       
/* 47 */       for (i = 0; i < 10; i++) {
/* 48 */         AbstractDungeon.effectsQueue.add(new FlyingSpikeEffect(this.x + 
/*    */               
/* 50 */               MathUtils.random(20.0F) * Settings.scale, this.y + 
/* 51 */               MathUtils.random(40.0F, 160.0F) * Settings.scale, 
/* 52 */               MathUtils.random(360.0F), 
/* 53 */               MathUtils.random(-400.0F, -50.0F) * Settings.scale, 0.0F, Settings.RED_TEXT_COLOR));
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 59 */     this.offsetY = Interpolation.exp10In.apply(TARGET_OFFSET_Y, STARTING_OFFSET_Y, this.duration / 2.0F);
/* 60 */     this.color.r = Interpolation.pow2In.apply(this.targetColor.r, 1.0F, this.duration / this.startingDuration);
/* 61 */     this.color.g = Interpolation.pow2In.apply(this.targetColor.g, 1.0F, this.duration / this.startingDuration);
/* 62 */     this.color.b = Interpolation.pow2In.apply(this.targetColor.b, 1.0F, this.duration / this.startingDuration);
/* 63 */     this.color.a = Interpolation.exp10Out.apply(0.0F, 1.0F, this.duration / 2.0F);
/*    */ 
/*    */     
/* 66 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 67 */     if (this.duration < 0.0F) {
/* 68 */       this.isDone = true;
/* 69 */       this.duration = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 75 */     FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.msg, this.x, this.y + this.offsetY, this.color, 1.25F);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\PowerDebuffEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */