/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HealEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 15 */   private static final float X_JITTER = 120.0F * Settings.scale;
/* 16 */   private static final float Y_JITTER = 120.0F * Settings.scale;
/* 17 */   private static final float OFFSET_Y = -50.0F * Settings.scale;
/*    */ 
/*    */   
/*    */   public HealEffect(float x, float y, int amount) {
/* 21 */     int roll = MathUtils.random(0, 2);
/* 22 */     if (roll == 0) {
/* 23 */       CardCrawlGame.sound.play("HEAL_1");
/* 24 */     } else if (roll == 1) {
/* 25 */       CardCrawlGame.sound.play("HEAL_2");
/*    */     } else {
/* 27 */       CardCrawlGame.sound.play("HEAL_3");
/*    */     } 
/*    */     
/* 30 */     AbstractDungeon.effectsQueue.add(new HealNumberEffect(x, y, amount));
/*    */     
/* 32 */     for (int i = 0; i < 18; i++) {
/* 33 */       AbstractDungeon.effectsQueue.add(new HealVerticalLineEffect(x + 
/*    */             
/* 35 */             MathUtils.random(-X_JITTER * 1.5F, X_JITTER * 1.5F), y + OFFSET_Y + 
/* 36 */             MathUtils.random(-Y_JITTER, Y_JITTER)));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 42 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\HealEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */