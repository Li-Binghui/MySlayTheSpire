/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class LosePercentHPAction extends AbstractGameAction {
/*    */   public LosePercentHPAction(int percent) {
/*  9 */     this.amount = percent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 14 */     float percentConversion = this.amount / 100.0F;
/* 15 */     int amountToLose = (int)(AbstractDungeon.player.currentHealth * percentConversion);
/* 16 */     addToTop(new LoseHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, amountToLose, AttackEffect.FIRE));
/* 17 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\LosePercentHPAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */