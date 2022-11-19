/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class RetainCardsAction
/*    */   extends AbstractGameAction {
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public RetainCardsAction(AbstractCreature source, int amount) {
/* 17 */     setValues((AbstractCreature)AbstractDungeon.player, source, amount);
/* 18 */     this.actionType = ActionType.CARD_MANIPULATION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == 0.5F) {
/* 25 */       AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
/* 26 */       addToBot((AbstractGameAction)new WaitAction(0.25F));
/* 27 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 32 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/*    */       
/* 34 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 35 */         if (!c.isEthereal) {
/* 36 */           c.retain = true;
/*    */         }
/* 38 */         AbstractDungeon.player.hand.addToTop(c);
/*    */       } 
/* 40 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     } 
/*    */     
/* 43 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RetainCardsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */