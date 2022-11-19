/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class ReApplyPowersAction extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   private AbstractMonster m;
/*    */   
/*    */   public ReApplyPowersAction(AbstractCard card, AbstractMonster m) {
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/* 14 */     this.card = card;
/* 15 */     this.m = m;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 21 */       this.card.calculateCardDamage(this.m);
/* 22 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ReApplyPowersAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */