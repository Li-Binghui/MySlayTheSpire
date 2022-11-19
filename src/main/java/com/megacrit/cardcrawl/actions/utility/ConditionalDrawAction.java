/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ConditionalDrawAction extends AbstractGameAction {
/*    */   private AbstractCard.CardType restrictedType;
/*    */   
/*    */   public ConditionalDrawAction(int newAmount, AbstractCard.CardType restrictedType) {
/* 14 */     this.duration = Settings.ACTION_DUR_FAST;
/* 15 */     this.actionType = ActionType.WAIT;
/* 16 */     this.source = (AbstractCreature)AbstractDungeon.player;
/* 17 */     this.target = (AbstractCreature)AbstractDungeon.player;
/* 18 */     this.amount = newAmount;
/* 19 */     this.restrictedType = restrictedType;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */       
/* 26 */       if (checkCondition()) {
/* 27 */         addToTop((AbstractGameAction)new DrawCardAction(this.source, this.amount));
/*    */       }
/*    */       
/* 30 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean checkCondition() {
/* 35 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 36 */       if (c.type == this.restrictedType) {
/* 37 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ConditionalDrawAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */