/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DeckToHandAction
/*    */   extends AbstractGameAction {
/*    */   public DeckToHandAction(int amount) {
/* 13 */     this.p = AbstractDungeon.player;
/* 14 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
/* 15 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 16 */     this.duration = Settings.ACTION_DUR_MED;
/*    */   }
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 22 */       AbstractDungeon.gridSelectScreen.open(this.p.drawPile, this.amount, "Select a card to add to your hand.", false);
/* 23 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 29 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 30 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 31 */         this.p.hand.addToHand(c);
/* 32 */         this.p.drawPile.removeCard(c);
/* 33 */         c.unhover();
/*    */       } 
/* 35 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 36 */       this.p.hand.refreshHandLayout();
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 45 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DeckToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */