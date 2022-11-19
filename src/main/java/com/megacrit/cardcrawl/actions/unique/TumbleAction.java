/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class TumbleAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public TumbleAction(AbstractCreature source, int amount) {
/* 15 */     this.source = source;
/* 16 */     this.amount = amount;
/* 17 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 23 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 24 */         if (c.type == AbstractCard.CardType.ATTACK) {
/* 25 */           addToTop((AbstractGameAction)new GainBlockAction(this.source, this.source, this.amount));
/* 26 */           addToTop((AbstractGameAction)new DiscardSpecificCardAction(c));
/*    */         } 
/*    */       } 
/*    */     }
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\TumbleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */