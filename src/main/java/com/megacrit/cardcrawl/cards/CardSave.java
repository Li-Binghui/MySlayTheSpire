/*    */ package com.megacrit.cardcrawl.cards;
/*    */ 
/*    */ public class CardSave
/*    */ {
/*    */   public int upgrades;
/*    */   
/*    */   public CardSave(String cardID, int timesUpgraded, int misc) {
/*  8 */     this.id = cardID;
/*  9 */     this.upgrades = timesUpgraded;
/* 10 */     this.misc = misc;
/*    */   }
/*    */   
/*    */   public int misc;
/*    */   public String id;
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\CardSave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */