/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class FissionAction
/*    */   extends AbstractGameAction {
/*    */   public FissionAction(boolean upgraded) {
/* 13 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 14 */     this.actionType = ActionType.ENERGY;
/* 15 */     this.upgraded = upgraded;
/*    */   }
/*    */   private boolean upgraded = false;
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_XFAST) {
/* 21 */       int orbCount = AbstractDungeon.player.filledOrbCount();
/* 22 */       addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, orbCount));
/* 23 */       addToTop((AbstractGameAction)new GainEnergyAction(orbCount));
/* 24 */       if (this.upgraded) {
/* 25 */         addToTop(new EvokeAllOrbsAction());
/*    */       } else {
/* 27 */         addToTop(new RemoveAllOrbsAction());
/*    */       } 
/*    */     } 
/*    */     
/* 31 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\FissionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */