/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class CorpseExplosionAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public CorpseExplosionAction(AbstractCreature target, AbstractCreature source) {
/* 12 */     this.target = target;
/* 13 */     this.source = source;
/* 14 */     this.actionType = ActionType.WAIT;
/* 15 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 21 */       if (this.target.hasPower("Poison")) {
/*    */         
/* 23 */         addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.target, this.source, "Poison"));
/*    */       } else {
/* 25 */         this.isDone = true;
/*    */       } 
/*    */     }
/*    */     
/* 29 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\CorpseExplosionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */