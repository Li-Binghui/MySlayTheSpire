/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DiscardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Shiv;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class BladeFuryAction
/*    */   extends AbstractGameAction {
/*    */   public BladeFuryAction(boolean upgraded) {
/* 14 */     this.upgrade = upgraded;
/*    */   }
/*    */   private boolean upgrade;
/*    */   
/*    */   public void update() {
/* 19 */     int theSize = AbstractDungeon.player.hand.size();
/*    */     
/* 21 */     if (this.upgrade) {
/* 22 */       AbstractCard s = (new Shiv()).makeCopy();
/* 23 */       s.upgrade();
/* 24 */       addToTop((AbstractGameAction)new MakeTempCardInHandAction(s, theSize));
/*    */     } else {
/* 26 */       addToTop((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Shiv(), theSize));
/*    */     } 
/*    */     
/* 29 */     addToTop((AbstractGameAction)new DiscardAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, theSize, false));
/*    */     
/* 31 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\BladeFuryAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */