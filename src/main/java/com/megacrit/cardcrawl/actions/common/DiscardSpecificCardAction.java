/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DiscardSpecificCardAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractCard targetCard;
/*    */   private CardGroup group;
/*    */   
/*    */   public DiscardSpecificCardAction(AbstractCard targetCard) {
/* 16 */     this.targetCard = targetCard;
/* 17 */     this.actionType = ActionType.DISCARD;
/* 18 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */   
/*    */   public DiscardSpecificCardAction(AbstractCard targetCard, CardGroup group) {
/* 22 */     this.targetCard = targetCard;
/* 23 */     this.group = group;
/* 24 */     this.actionType = ActionType.DISCARD;
/* 25 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 31 */       if (this.group == null) {
/* 32 */         this.group = AbstractDungeon.player.hand;
/*    */       }
/* 34 */       if (this.group.contains(this.targetCard)) {
/* 35 */         this.group.moveToDiscardPile(this.targetCard);
/* 36 */         GameActionManager.incrementDiscard(false);
/* 37 */         this.targetCard.triggerOnManualDiscard();
/*    */       } 
/*    */     } 
/*    */     
/* 41 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DiscardSpecificCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */