/*    */ package com.megacrit.cardcrawl.actions.common;
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
/*    */ public class UpgradeRandomCardAction
/*    */   extends AbstractGameAction
/*    */ {
/* 15 */   private AbstractPlayer p = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */       
/* 23 */       if (this.p.hand.group.size() <= 0) {
/* 24 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 28 */       CardGroup upgradeable = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*    */       
/* 30 */       for (AbstractCard c : this.p.hand.group) {
/* 31 */         if (c.canUpgrade() && c.type != AbstractCard.CardType.STATUS) {
/* 32 */           upgradeable.addToTop(c);
/*    */         }
/*    */       } 
/*    */       
/* 36 */       if (upgradeable.size() > 0) {
/* 37 */         upgradeable.shuffle();
/* 38 */         ((AbstractCard)upgradeable.group.get(0)).upgrade();
/* 39 */         ((AbstractCard)upgradeable.group.get(0)).superFlash();
/* 40 */         ((AbstractCard)upgradeable.group.get(0)).applyPowers();
/*    */       } 
/*    */       
/* 43 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 48 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\UpgradeRandomCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */