/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class WallopEffect extends AbstractGameEffect {
/*    */   private float x;
/*  8 */   private int damage = 0; private float y;
/*    */   
/*    */   public WallopEffect(int damage, float x, float y) {
/* 11 */     this.damage = damage;
/* 12 */     this.x = x;
/* 13 */     this.y = y;
/* 14 */     if (this.damage > 50) {
/* 15 */       this.damage = 50;
/*    */     }
/*    */   }
/*    */   
/*    */   public void update() {
/* 20 */     for (int i = 0; i < this.damage; i++) {
/* 21 */       AbstractDungeon.effectsQueue.add(new StarBounceEffect(this.x, this.y));
/*    */     }
/* 23 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\WallopEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */