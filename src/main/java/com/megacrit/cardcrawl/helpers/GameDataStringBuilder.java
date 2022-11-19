/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameDataStringBuilder
/*    */ {
/*  8 */   private StringBuilder bldr = new StringBuilder();
/*    */ 
/*    */   
/*    */   public void addFieldData(String value) {
/* 12 */     this.bldr.append(value).append("\t");
/*    */   }
/*    */   
/*    */   public void addFieldData(int value) {
/* 16 */     addFieldData(Integer.toString(value));
/*    */   }
/*    */   
/*    */   public void addFieldData(boolean value) {
/* 20 */     addFieldData(Boolean.toString(value));
/*    */   }
/*    */   
/*    */   public String toString() {
/* 24 */     return this.bldr.toString();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\GameDataStringBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */