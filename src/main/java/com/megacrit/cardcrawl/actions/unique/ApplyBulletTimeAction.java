/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApplyBulletTimeAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 16 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 17 */       c.setCostForTurn(-9);
/*    */     }
/* 19 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ApplyBulletTimeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */