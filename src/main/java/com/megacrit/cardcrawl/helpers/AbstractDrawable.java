/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractDrawable
/*    */   implements Comparable<AbstractDrawable>
/*    */ {
/*    */   public int z;
/*    */   
/*    */   public int compareTo(AbstractDrawable other) {
/* 12 */     return Integer.compare(this.z, other.z);
/*    */   }
/*    */   
/*    */   public abstract void render(SpriteBatch paramSpriteBatch);
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\AbstractDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */