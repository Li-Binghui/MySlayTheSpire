/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class IceWallAction extends AbstractGameAction {
/*    */   private int perOrbAmt;
/*    */   
/*    */   public IceWallAction(int blockAmt, int perOrbAmt) {
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/* 14 */     this.amount = blockAmt;
/* 15 */     this.perOrbAmt = perOrbAmt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 21 */       int count = 0;
/* 22 */       for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
/* 23 */         if (AbstractDungeon.player.orbs.get(i) instanceof com.megacrit.cardcrawl.orbs.Frost) {
/* 24 */           count++;
/*    */         }
/*    */       } 
/* 27 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.amount + count * this.perOrbAmt));
/*    */     } 
/*    */     
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\IceWallAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */