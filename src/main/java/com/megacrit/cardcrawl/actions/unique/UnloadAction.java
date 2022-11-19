/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class UnloadAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public UnloadAction(AbstractCreature source) {
/* 14 */     this.source = source;
/* 15 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 21 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 22 */         if (c.type != AbstractCard.CardType.ATTACK) {
/* 23 */           addToTop((AbstractGameAction)new DiscardSpecificCardAction(c));
/*    */         }
/*    */       } 
/* 26 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\UnloadAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */