/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExhaustAllNonAttackAction
/*    */   extends AbstractGameAction
/*    */ {
/* 17 */   private float startingDuration = Settings.ACTION_DUR_FAST;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == this.startingDuration) {
/* 24 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 25 */         if (c.type != AbstractCard.CardType.ATTACK) {
/* 26 */           addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
/*    */         }
/*    */       } 
/* 29 */       this.isDone = true;
/*    */ 
/*    */       
/* 32 */       if (AbstractDungeon.player.exhaustPile.size() >= 20)
/* 33 */         UnlockTracker.unlockAchievement("THE_PACT"); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ExhaustAllNonAttackAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */