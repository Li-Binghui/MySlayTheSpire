/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SmokeBombEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   
/*    */   public SmokeBombEffect(float x, float y) {
/* 13 */     this.x = x;
/* 14 */     this.y = y;
/* 15 */     this.duration = 0.2F;
/*    */   }
/*    */   private float y;
/*    */   public void update() {
/* 19 */     if (this.duration == 0.2F) {
/* 20 */       CardCrawlGame.sound.play("ATTACK_WHIFF_2");
/* 21 */       for (int i = 0; i < 90; i++) {
/* 22 */         AbstractDungeon.effectsQueue.add(new SmokeBlurEffect(this.x, this.y));
/*    */       }
/*    */     } 
/* 25 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 26 */     if (this.duration < 0.0F) {
/* 27 */       CardCrawlGame.sound.play("APPEAR");
/* 28 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SmokeBombEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */