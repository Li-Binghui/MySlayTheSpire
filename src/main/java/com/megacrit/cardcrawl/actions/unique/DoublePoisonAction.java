/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */ 
/*    */ public class DoublePoisonAction extends AbstractGameAction {
/*    */   public DoublePoisonAction(AbstractCreature target, AbstractCreature source) {
/* 11 */     this.target = target;
/* 12 */     this.source = source;
/* 13 */     this.actionType = ActionType.DEBUFF;
/* 14 */     this.attackEffect = AttackEffect.FIRE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     if (this.target != null && this.target.hasPower("Poison")) {
/* 20 */       addToTop((AbstractGameAction)new ApplyPowerAction(this.target, this.source, (AbstractPower)new PoisonPower(this.target, this.source, 
/*    */ 
/*    */ 
/*    */               
/* 24 */               (this.target.getPower("Poison")).amount), 
/* 25 */             (this.target.getPower("Poison")).amount));
/*    */     }
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DoublePoisonAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */