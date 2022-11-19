/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class InspirationAction extends AbstractGameAction {
/*    */   public InspirationAction(int drawAmt) {
/* 11 */     this.source = (AbstractCreature)AbstractDungeon.player;
/* 12 */     this.duration = Settings.ACTION_DUR_FAST;
/* 13 */     this.amount = drawAmt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (this.duration == Settings.ACTION_DUR_FAST && 
/* 19 */       this.amount - AbstractDungeon.player.hand.size() > 0) {
/* 20 */       addToTop((AbstractGameAction)new DrawCardAction(this.source, this.amount - AbstractDungeon.player.hand.size()));
/*    */     }
/*    */ 
/*    */     
/* 24 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\InspirationAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */