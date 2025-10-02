/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RestoreRetainedCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/*    */ 
/*    */ public class DiscardAtEndOfTurnAction
/*    */   extends AbstractGameAction
/*    */ {
/* 16 */   private static final float DURATION = Settings.ACTION_DUR_XFAST;
/*    */ 
/*    */ 
            public DiscardAtEndOfTurnAction() {
                this.duration = DURATION;
            }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == DURATION) {
/*    */ 
/*    */       
/* 27 */       for (Iterator<AbstractCard> c = AbstractDungeon.player.hand.group.iterator(); c.hasNext(); ) {
/* 28 */         AbstractCard e = c.next();
/* 29 */         if (e.retain || e.selfRetain) {
/* 30 */           AbstractDungeon.player.limbo.addToTop(e);
/* 31 */           c.remove();
/*    */         } 
/*    */       } 
/* 34 */       addToTop((AbstractGameAction)new RestoreRetainedCardsAction(AbstractDungeon.player.limbo));
/*    */ 
/*    */       
/* 37 */       if (!AbstractDungeon.player.hasRelic("Runic Pyramid") && !AbstractDungeon.player.hasPower("Equilibrium")) {
/*    */         
/* 39 */         int tempSize = AbstractDungeon.player.hand.size();
/*    */         
/* 41 */         for (int i = 0; i < tempSize; i++) {
/* 42 */           addToTop(new DiscardAction((AbstractCreature)AbstractDungeon.player, null, AbstractDungeon.player.hand
/*    */ 
/*    */ 
/*    */                 
/* 46 */                 .size(), true, true));
/*    */         }
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 54 */       ArrayList<AbstractCard> cards = (ArrayList<AbstractCard>)AbstractDungeon.player.hand.group.clone();
/* 55 */       Collections.shuffle(cards);
/* 56 */       for (AbstractCard abstractCard : cards) {
/* 57 */         abstractCard.triggerOnEndOfPlayerTurn();
/*    */       }
/* 59 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DiscardAtEndOfTurnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */