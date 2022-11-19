/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class EscapePlanAction
/*    */   extends AbstractGameAction {
/*    */   private int blockGain;
/*    */   
/*    */   public EscapePlanAction(int blockGain) {
/* 15 */     this.duration = 0.0F;
/* 16 */     this.actionType = ActionType.WAIT;
/* 17 */     this.blockGain = blockGain;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     for (AbstractCard c : DrawCardAction.drawnCards) {
/* 23 */       if (c.type == AbstractCard.CardType.SKILL) {
/* 24 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.blockGain));
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 29 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\EscapePlanAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */