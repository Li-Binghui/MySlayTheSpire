/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class EnlightenmentAction extends AbstractGameAction {
/*    */   private AbstractPlayer p;
/*    */   private boolean forCombat = false;
/*    */   
/*    */   public EnlightenmentAction(boolean forRestOfCombat) {
/* 14 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 15 */     this.p = AbstractDungeon.player;
/* 16 */     this.duration = Settings.ACTION_DUR_FAST;
/* 17 */     this.forCombat = forRestOfCombat;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 23 */       for (AbstractCard c : this.p.hand.group) {
/* 24 */         if (c.costForTurn > 1) {
/* 25 */           c.costForTurn = 1;
/* 26 */           c.isCostModifiedForTurn = true;
/*    */         } 
/* 28 */         if (this.forCombat && c.cost > 1) {
/* 29 */           c.cost = 1;
/* 30 */           c.isCostModified = true;
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 35 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\EnlightenmentAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */