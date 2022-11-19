/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class UpgradeSpecificCardAction extends AbstractGameAction {
/*    */   private AbstractCard c;
/*    */   
/*    */   public UpgradeSpecificCardAction(AbstractCard cardToUpgrade) {
/* 11 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 12 */     this.c = cardToUpgrade;
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 19 */       if (this.c.canUpgrade() && this.c.type != AbstractCard.CardType.STATUS) {
/* 20 */         this.c.upgrade();
/* 21 */         this.c.superFlash();
/* 22 */         this.c.applyPowers();
/*    */       } 
/*    */       
/* 25 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 29 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\UpgradeSpecificCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */