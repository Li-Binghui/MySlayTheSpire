/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class ForTheEyesAction extends AbstractGameAction {
/*    */   public ForTheEyesAction(int weakAmt, AbstractMonster m) {
/* 13 */     this.actionType = ActionType.WAIT;
/* 14 */     this.amount = weakAmt;
/* 15 */     this.m = m;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.m != null && this.m.getIntentBaseDmg() >= 0) {
/* 21 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)this.m, this.amount, false), this.amount));
/*    */     }
/* 23 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   private AbstractMonster m;
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ForTheEyesAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */