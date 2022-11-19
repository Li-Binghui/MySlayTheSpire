/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class AggregateEnergyAction
/*    */   extends AbstractGameAction {
/*    */   private int divideAmount;
/*    */   
/*    */   public AggregateEnergyAction(int divideAmountNum) {
/* 12 */     this.duration = Settings.ACTION_DUR_FAST;
/* 13 */     this.divideAmount = divideAmountNum;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 19 */       AbstractDungeon.player.gainEnergy(AbstractDungeon.player.drawPile.size() / this.divideAmount);
/*    */     }
/*    */     
/* 22 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\AggregateEnergyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */