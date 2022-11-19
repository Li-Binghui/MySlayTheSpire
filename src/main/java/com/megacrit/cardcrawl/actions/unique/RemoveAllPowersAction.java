/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class RemoveAllPowersAction
/*    */   extends AbstractGameAction {
/*    */   private boolean debuffsOnly;
/*    */   private AbstractCreature c;
/*    */   
/*    */   public RemoveAllPowersAction(AbstractCreature c, boolean debuffsOnly) {
/* 14 */     this.debuffsOnly = debuffsOnly;
/* 15 */     this.c = c;
/* 16 */     this.duration = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     for (AbstractPower p : this.c.powers) {
/* 22 */       if (p.type == AbstractPower.PowerType.DEBUFF || !this.debuffsOnly) {
/* 23 */         addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.c, this.c, p.ID));
/*    */       }
/*    */     } 
/*    */     
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RemoveAllPowersAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */