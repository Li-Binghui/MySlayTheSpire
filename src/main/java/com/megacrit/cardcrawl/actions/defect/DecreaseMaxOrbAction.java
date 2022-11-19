/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DecreaseMaxOrbAction
/*    */   extends AbstractGameAction {
/*    */   public DecreaseMaxOrbAction(int slotDecrease) {
/* 10 */     this.duration = Settings.ACTION_DUR_FAST;
/* 11 */     this.amount = slotDecrease;
/* 12 */     this.actionType = ActionType.BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 18 */       for (int i = 0; i < this.amount; i++) {
/* 19 */         AbstractDungeon.player.decreaseMaxOrbSlots(1);
/*    */       }
/*    */     }
/*    */     
/* 23 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\DecreaseMaxOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */