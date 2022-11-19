/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApotheosisAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 21 */       AbstractPlayer p = AbstractDungeon.player;
/*    */       
/* 23 */       upgradeAllCardsInGroup(p.hand);
/* 24 */       upgradeAllCardsInGroup(p.drawPile);
/* 25 */       upgradeAllCardsInGroup(p.discardPile);
/* 26 */       upgradeAllCardsInGroup(p.exhaustPile);
/*    */       
/* 28 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void upgradeAllCardsInGroup(CardGroup cardGroup) {
/* 33 */     for (AbstractCard c : cardGroup.group) {
/* 34 */       if (c.canUpgrade()) {
/* 35 */         if (cardGroup.type == CardGroup.CardGroupType.HAND) {
/* 36 */           c.superFlash();
/*    */         }
/* 38 */         c.upgrade();
/* 39 */         c.applyPowers();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ApotheosisAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */