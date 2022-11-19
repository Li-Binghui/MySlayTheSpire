/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DiscardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class CalculatedGambleAction extends AbstractGameAction {
/*    */   private float startingDuration;
/*    */   
/*    */   public CalculatedGambleAction(boolean upgraded) {
/* 14 */     this.target = (AbstractCreature)AbstractDungeon.player;
/* 15 */     this.actionType = ActionType.WAIT;
/* 16 */     this.startingDuration = Settings.ACTION_DUR_FAST;
/* 17 */     this.duration = Settings.ACTION_DUR_FAST;
/* 18 */     this.isUpgraded = upgraded;
/*    */   }
/*    */   private boolean isUpgraded;
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == this.startingDuration) {
/* 24 */       int count = AbstractDungeon.player.hand.size();
/* 25 */       if (this.isUpgraded) {
/* 26 */         addToTop((AbstractGameAction)new DrawCardAction(this.target, count + 1));
/* 27 */         addToTop((AbstractGameAction)new DiscardAction(this.target, this.target, count, true));
/*    */       }
/* 29 */       else if (count != 0) {
/* 30 */         addToTop((AbstractGameAction)new DrawCardAction(this.target, count));
/* 31 */         addToTop((AbstractGameAction)new DiscardAction(this.target, this.target, count, true));
/*    */       } 
/*    */       
/* 34 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\CalculatedGambleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */