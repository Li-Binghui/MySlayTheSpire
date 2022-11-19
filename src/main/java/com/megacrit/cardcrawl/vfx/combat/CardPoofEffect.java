/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class CardPoofEffect
/*    */   extends AbstractGameEffect {
/*    */   public CardPoofEffect(float x, float y) {
/* 10 */     for (int i = 0; i < 50; i++) {
/* 11 */       AbstractDungeon.effectsQueue.add(new CardPoofParticle(x, y));
/*    */     }
/*    */   }
/*    */   
/*    */   public void update() {
/* 16 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\CardPoofEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */