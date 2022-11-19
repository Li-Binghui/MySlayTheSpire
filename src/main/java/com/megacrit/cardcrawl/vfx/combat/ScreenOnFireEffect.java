/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
/*    */ 
/*    */ public class ScreenOnFireEffect extends AbstractGameEffect {
/* 12 */   private float timer = 0.0F;
/*    */   private static final float INTERVAL = 0.05F;
/*    */   
/*    */   public ScreenOnFireEffect() {
/* 16 */     this.duration = 3.0F;
/* 17 */     this.startingDuration = this.duration;
/*    */   }
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == this.startingDuration) {
/* 22 */       CardCrawlGame.sound.play("GHOST_FLAMES");
/* 23 */       AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.FIREBRICK));
/*    */     } 
/*    */     
/* 26 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 27 */     this.timer -= Gdx.graphics.getDeltaTime();
/* 28 */     if (this.timer < 0.0F) {
/* 29 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 30 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 31 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 32 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 33 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 34 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 35 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 36 */       AbstractDungeon.effectsQueue.add(new GiantFireEffect());
/* 37 */       this.timer = 0.05F;
/*    */     } 
/* 39 */     if (this.duration < 0.0F)
/* 40 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ScreenOnFireEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */