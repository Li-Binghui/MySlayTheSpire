/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ 
/*    */ public class RemoveAllBlockAction extends AbstractGameAction {
/*    */   private static final float DUR = 0.25F;
/*    */   
/*    */   public RemoveAllBlockAction(AbstractCreature target, AbstractCreature source) {
/* 10 */     setValues(target, source, this.amount);
/* 11 */     this.actionType = ActionType.BLOCK;
/* 12 */     this.duration = 0.25F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (!this.target.isDying && !this.target.isDead && 
/* 18 */       this.duration == 0.25F && 
/* 19 */       this.target.currentBlock > 0) {
/* 20 */       this.target.loseBlock();
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 25 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\RemoveAllBlockAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */