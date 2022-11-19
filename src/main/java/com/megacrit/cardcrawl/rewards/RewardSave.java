/*    */ package com.megacrit.cardcrawl.rewards;
/*    */ 
/*    */ public class RewardSave
/*    */ {
/*    */   public String type;
/*    */   public String id;
/*    */   
/*    */   public RewardSave(String type, String id, int amount, int bonusGold) {
/*  9 */     this.type = type;
/* 10 */     this.id = id;
/* 11 */     this.amount = amount;
/*    */   }
/*    */   public int amount; public int bonusGold;
/*    */   public RewardSave(String type, String id) {
/* 15 */     this(type, id, 0, 0);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rewards\RewardSave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */