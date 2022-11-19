/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class DiscardPileToTopOfDeckAction extends AbstractGameAction {
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public DiscardPileToTopOfDeckAction(AbstractCreature source) {
/* 18 */     this.p = AbstractDungeon.player;
/* 19 */     setValues(null, source, this.amount);
/* 20 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 21 */     this.duration = Settings.ACTION_DUR_FASTER;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
/* 27 */       this.isDone = true;
/*    */       return;
/*    */     } 
/* 30 */     if (this.duration == Settings.ACTION_DUR_FASTER) {
/* 31 */       if (this.p.discardPile.isEmpty()) {
/* 32 */         this.isDone = true; return;
/*    */       } 
/* 34 */       if (this.p.discardPile.size() == 1) {
/* 35 */         AbstractCard tmp = this.p.discardPile.getTopCard();
/* 36 */         this.p.discardPile.removeCard(tmp);
/* 37 */         this.p.discardPile.moveToDeck(tmp, false);
/*    */       } 
/*    */       
/* 40 */       if (this.p.discardPile.group.size() > this.amount) {
/* 41 */         AbstractDungeon.gridSelectScreen.open(this.p.discardPile, 1, TEXT[0], false, false, false, false);
/* 42 */         tickDuration();
/*    */ 
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/*    */     
/* 49 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 50 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 51 */         this.p.discardPile.removeCard(c);
/* 52 */         this.p.hand.moveToDeck(c, false);
/*    */       } 
/* 54 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 55 */       AbstractDungeon.player.hand.refreshHandLayout();
/*    */     } 
/*    */     
/* 58 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DiscardPileToTopOfDeckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */