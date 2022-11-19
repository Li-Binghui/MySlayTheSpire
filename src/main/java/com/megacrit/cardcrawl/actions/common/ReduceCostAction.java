/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class ReduceCostAction
/*    */   extends AbstractGameAction {
/*    */   UUID uuid;
/* 12 */   private AbstractCard card = null;
/*    */   
/*    */   public ReduceCostAction(AbstractCard card) {
/* 15 */     this.card = card;
/*    */   }
/*    */   
/*    */   public ReduceCostAction(UUID targetUUID, int amount) {
/* 19 */     this.uuid = targetUUID;
/* 20 */     this.amount = amount;
/* 21 */     this.duration = Settings.ACTION_DUR_XFAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (this.card == null) {
/* 27 */       for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
/* 28 */         c.modifyCostForCombat(-1);
/*    */       }
/*    */     } else {
/* 31 */       this.card.modifyCostForCombat(-1);
/*    */     } 
/*    */     
/* 34 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ReduceCostAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */