/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class IncreaseMaxOrbAction
/*    */   extends AbstractGameAction {
/*    */   public IncreaseMaxOrbAction(int slotIncrease) {
/* 10 */     this.duration = Settings.ACTION_DUR_FAST;
/* 11 */     this.amount = slotIncrease;
/* 12 */     this.actionType = ActionType.BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 18 */       for (int i = 0; i < this.amount; i++) {
/* 19 */         AbstractDungeon.player.increaseMaxOrbSlots(1, true);
/*    */       }
/*    */     }
/*    */     
/* 23 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\IncreaseMaxOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */