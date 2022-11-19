/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EvokeAllOrbsAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 14 */     for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
/* 15 */       addToTop(new EvokeOrbAction(1));
/*    */     }
/*    */     
/* 18 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\EvokeAllOrbsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */