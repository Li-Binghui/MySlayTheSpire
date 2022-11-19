/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.SpotlightEffect;
/*    */ 
/*    */ public class GrandFinalEffect extends AbstractGameEffect {
/* 11 */   private float timer = 0.1F;
/*    */   
/*    */   public GrandFinalEffect() {
/* 14 */     this.duration = 2.0F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 18 */     if (this.duration == 2.0F) {
/* 19 */       AbstractDungeon.effectsQueue.add(new SpotlightEffect());
/*    */     }
/*    */     
/* 22 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 23 */     this.timer -= Gdx.graphics.getDeltaTime();
/*    */     
/* 25 */     if (this.timer < 0.0F) {
/* 26 */       this.timer += 0.1F;
/* 27 */       AbstractDungeon.effectsQueue.add(new PetalEffect());
/* 28 */       AbstractDungeon.effectsQueue.add(new PetalEffect());
/*    */     } 
/*    */     
/* 31 */     if (this.duration < 0.0F)
/* 32 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\GrandFinalEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */