/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoveAllOrbsAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 14 */     while (AbstractDungeon.player.filledOrbCount() > 0) {
/* 15 */       AbstractDungeon.player.removeNextOrb();
/*    */     }
/*    */     
/* 18 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\RemoveAllOrbsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */