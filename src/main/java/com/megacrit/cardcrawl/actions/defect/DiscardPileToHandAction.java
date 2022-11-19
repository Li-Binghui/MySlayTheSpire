/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DiscardPileToHandAction extends AbstractGameAction {
/* 11 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("DiscardPileToHandAction")).TEXT;
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public DiscardPileToHandAction(int amount) {
/* 15 */     this.p = AbstractDungeon.player;
/* 16 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
/* 17 */     this.actionType = ActionType.CARD_MANIPULATION;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     if (this.p.hand.size() >= 10) {
/* 23 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 27 */     if (this.p.discardPile.size() == 1) {
/* 28 */       AbstractCard card = this.p.discardPile.group.get(0);
/* 29 */       if (this.p.hand.size() < 10) {
/* 30 */         this.p.hand.addToHand(card);
/* 31 */         this.p.discardPile.removeCard(card);
/*    */       } 
/* 33 */       card.lighten(false);
/* 34 */       this.p.hand.refreshHandLayout();
/* 35 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 39 */     if (this.duration == 0.5F) {
/* 40 */       AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, TEXT[0], false);
/* 41 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 47 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 48 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 49 */         if (this.p.hand.size() < 10) {
/* 50 */           this.p.hand.addToHand(c);
/* 51 */           this.p.discardPile.removeCard(c);
/*    */         } 
/* 53 */         c.lighten(false);
/* 54 */         c.unhover();
/*    */       } 
/* 56 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 57 */       this.p.hand.refreshHandLayout();
/*    */       
/* 59 */       for (AbstractCard c : this.p.discardPile.group) {
/* 60 */         c.unhover();
/* 61 */         c.target_x = CardGroup.DISCARD_PILE_X;
/* 62 */         c.target_y = 0.0F;
/*    */       } 
/* 64 */       this.isDone = true;
/*    */     } 
/*    */     
/* 67 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\DiscardPileToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */