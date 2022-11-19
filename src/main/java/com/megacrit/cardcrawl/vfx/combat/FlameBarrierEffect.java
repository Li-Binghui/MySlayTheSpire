/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleXLEffect;
/*    */ 
/*    */ public class FlameBarrierEffect extends AbstractGameEffect {
/*    */   private float x;
/* 20 */   private static final float X_RADIUS = 200.0F * Settings.scale; private float y; private static final float Y_RADIUS = 250.0F * Settings.scale;
/*    */   private boolean flashedBorder = true;
/* 22 */   private Vector2 v = new Vector2(0.0F, 0.0F);
/*    */   
/*    */   public FlameBarrierEffect(float x, float y) {
/* 25 */     this.duration = 0.5F;
/* 26 */     this.x = x;
/* 27 */     this.y = y;
/* 28 */     this.renderBehind = false;
/*    */   }
/*    */   
/*    */   public void update() {
/* 32 */     if (this.flashedBorder) {
/* 33 */       CardCrawlGame.sound.play("ATTACK_FLAME_BARRIER", 0.05F);
/* 34 */       this.flashedBorder = false;
/* 35 */       AbstractDungeon.effectsQueue.add(new BorderFlashEffect(new Color(1.0F, 1.0F, 0.1F, 1.0F)));
/* 36 */       AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.4F, 0.1F, 1.0F)));
/*    */     } 
/*    */     
/* 39 */     float tmp = Interpolation.fade.apply(-209.0F, 30.0F, this.duration / 0.5F) * 0.017453292F;
/* 40 */     this.v.x = MathUtils.cos(tmp) * X_RADIUS;
/* 41 */     this.v.y = -MathUtils.sin(tmp) * Y_RADIUS;
/*    */     
/* 43 */     AbstractDungeon.effectsQueue.add(new TorchParticleXLEffect(this.x + this.v.x + 
/*    */           
/* 45 */           MathUtils.random(-30.0F, 30.0F) * Settings.scale, this.y + this.v.y + 
/* 46 */           MathUtils.random(-10.0F, 10.0F) * Settings.scale));
/* 47 */     AbstractDungeon.effectsQueue.add(new TorchParticleXLEffect(this.x + this.v.x + 
/*    */           
/* 49 */           MathUtils.random(-30.0F, 30.0F) * Settings.scale, this.y + this.v.y + 
/* 50 */           MathUtils.random(-10.0F, 10.0F) * Settings.scale));
/* 51 */     AbstractDungeon.effectsQueue.add(new TorchParticleXLEffect(this.x + this.v.x + 
/*    */           
/* 53 */           MathUtils.random(-30.0F, 30.0F) * Settings.scale, this.y + this.v.y + 
/* 54 */           MathUtils.random(-10.0F, 10.0F) * Settings.scale));
/*    */     
/* 56 */     AbstractDungeon.effectsQueue.add(new ExhaustEmberEffect(this.x + this.v.x, this.y + this.v.y));
/*    */     
/* 58 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 59 */     if (this.duration < 0.0F)
/* 60 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlameBarrierEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */