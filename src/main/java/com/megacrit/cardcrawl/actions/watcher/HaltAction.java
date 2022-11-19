/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class HaltAction
/*    */   extends AbstractGameAction {
/*    */   int additionalAmt;
/*    */   
/*    */   public HaltAction(AbstractCreature target, int block, int additional) {
/* 13 */     this.target = target;
/* 14 */     this.amount = block;
/* 15 */     this.additionalAmt = additional;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (AbstractDungeon.player.stance.ID.equals("Wrath")) {
/* 21 */       addToTop((AbstractGameAction)new GainBlockAction(this.target, this.amount + this.additionalAmt));
/*    */     } else {
/* 23 */       addToTop((AbstractGameAction)new GainBlockAction(this.target, this.amount));
/*    */     } 
/*    */     
/* 26 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\HaltAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */