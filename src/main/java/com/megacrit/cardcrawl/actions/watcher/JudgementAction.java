/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class JudgementAction
/*    */   extends AbstractGameAction {
/*    */   private int cutoff;
/*    */   
/*    */   public JudgementAction(AbstractCreature target, int cutoff) {
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/* 14 */     this.source = null;
/* 15 */     this.target = target;
/* 16 */     this.cutoff = cutoff;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_FAST && 
/* 22 */       this.target.currentHealth <= this.cutoff && this.target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
/* 23 */       addToTop((AbstractGameAction)new InstantKillAction(this.target));
/*    */     }
/*    */ 
/*    */     
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\JudgementAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */