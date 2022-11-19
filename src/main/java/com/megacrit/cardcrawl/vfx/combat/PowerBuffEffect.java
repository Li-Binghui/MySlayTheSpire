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
/*    */ public class PowerBuffEffect extends AbstractGameEffect {
/*    */   private static final float TEXT_DURATION = 2.0F;
/* 15 */   private static final float STARTING_OFFSET_Y = 60.0F * Settings.scale;
/* 16 */   private static final float TARGET_OFFSET_Y = 100.0F * Settings.scale;
/*    */   
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public PowerBuffEffect(float x, float y, String msg) {
/* 22 */     this.duration = 2.0F;
/* 23 */     this.startingDuration = 2.0F;
/* 24 */     this.msg = msg;
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.targetColor = Settings.GREEN_TEXT_COLOR;
/* 28 */     this.color = Color.WHITE.cpy();
/* 29 */     this.offsetY = STARTING_OFFSET_Y;
/*    */   }
/*    */   private float offsetY; private String msg; private Color targetColor;
/*    */   
/*    */   public void update() {
/* 34 */     if (this.duration == this.startingDuration && !Settings.DISABLE_EFFECTS) {
/* 35 */       int i; for (i = 0; i < 10; i++) {
/* 36 */         AbstractDungeon.effectsQueue.add(new FlyingSpikeEffect(this.x - 
/*    */               
/* 38 */               MathUtils.random(-120.0F, 120.0F) * Settings.scale, this.y + 
/* 39 */               MathUtils.random(90.0F, 110.0F) * Settings.scale, -90.0F, 0.0F, 
/*    */ 
/*    */               
/* 42 */               MathUtils.random(-200.0F, -50.0F) * Settings.scale, Settings.GREEN_TEXT_COLOR));
/*    */       }
/*    */       
/* 45 */       for (i = 0; i < 10; i++) {
/* 46 */         AbstractDungeon.effectsQueue.add(new FlyingSpikeEffect(this.x - 
/*    */               
/* 48 */               MathUtils.random(-120.0F, 120.0F) * Settings.scale, this.y + 
/* 49 */               MathUtils.random(90.0F, 110.0F) * Settings.scale, 90.0F, 0.0F, 
/*    */ 
/*    */               
/* 52 */               MathUtils.random(200.0F, 50.0F) * Settings.scale, Settings.GREEN_TEXT_COLOR));
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 57 */     this.offsetY = Interpolation.exp10In.apply(TARGET_OFFSET_Y, STARTING_OFFSET_Y, this.duration / 2.0F);
/* 58 */     this.color.r = Interpolation.pow2In.apply(this.targetColor.r, 1.0F, this.duration / this.startingDuration);
/* 59 */     this.color.g = Interpolation.pow2In.apply(this.targetColor.g, 1.0F, this.duration / this.startingDuration);
/* 60 */     this.color.b = Interpolation.pow2In.apply(this.targetColor.b, 1.0F, this.duration / this.startingDuration);
/* 61 */     this.color.a = Interpolation.exp10Out.apply(0.0F, 1.0F, this.duration / 2.0F);
/*    */ 
/*    */     
/* 64 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 65 */     if (this.duration < 0.0F) {
/* 66 */       this.isDone = true;
/* 67 */       this.duration = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 73 */     FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.msg, this.x, this.y + this.offsetY, this.color, 1.25F);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\PowerBuffEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */