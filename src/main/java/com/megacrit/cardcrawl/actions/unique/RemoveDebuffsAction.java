/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class RemoveDebuffsAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractCreature c;
/*    */   
/*    */   public RemoveDebuffsAction(AbstractCreature c) {
/* 13 */     this.c = c;
/* 14 */     this.duration = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     for (AbstractPower p : this.c.powers) {
/* 20 */       if (p.type == AbstractPower.PowerType.DEBUFF) {
/* 21 */         addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.c, this.c, p.ID));
/*    */       }
/*    */     } 
/*    */     
/* 25 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RemoveDebuffsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */