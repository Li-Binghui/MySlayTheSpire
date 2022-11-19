/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class GashAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   
/*    */   public GashAction(AbstractCard card, int amount) {
/* 12 */     this.card = card;
/* 13 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     this.card.baseDamage += this.amount;
/* 19 */     this.card.applyPowers();
/*    */     
/* 21 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 22 */       if (c instanceof com.megacrit.cardcrawl.cards.blue.Claw) {
/* 23 */         c.baseDamage += this.amount;
/* 24 */         c.applyPowers();
/*    */       } 
/*    */     } 
/*    */     
/* 28 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 29 */       if (c instanceof com.megacrit.cardcrawl.cards.blue.Claw) {
/* 30 */         c.baseDamage += this.amount;
/* 31 */         c.applyPowers();
/*    */       } 
/*    */     } 
/*    */     
/* 35 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 36 */       if (c instanceof com.megacrit.cardcrawl.cards.blue.Claw) {
/* 37 */         c.baseDamage += this.amount;
/* 38 */         c.applyPowers();
/*    */       } 
/*    */     } 
/*    */     
/* 42 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\GashAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */