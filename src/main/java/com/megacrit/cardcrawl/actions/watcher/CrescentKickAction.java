/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.deprecated.DEPRECATEDCrescentKick;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class CrescentKickAction
/*    */   extends AbstractGameAction {
/*    */   public CrescentKickAction(AbstractPlayer p, DEPRECATEDCrescentKick card) {
/* 15 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 16 */     this.actionType = ActionType.BLOCK;
/* 17 */     this.card = card;
/* 18 */     this.target = (AbstractCreature)p;
/*    */   }
/*    */   private DEPRECATEDCrescentKick card;
/*    */   
/*    */   public void update() {
/* 23 */     if (this.card.hadVigor && this.target != null) {
/* 24 */       addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 1));
/* 25 */       addToTop((AbstractGameAction)new GainEnergyAction(1));
/*    */     } 
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\CrescentKickAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */