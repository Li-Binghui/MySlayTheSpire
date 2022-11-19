/*    */ package com.megacrit.cardcrawl.localization;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PotionStrings
/*    */ {
/*    */   public String NAME;
/*    */   public String[] DESCRIPTIONS;
/*    */   
/*    */   public static PotionStrings getMockPotionString() {
/* 11 */     PotionStrings retVal = new PotionStrings();
/* 12 */     retVal.NAME = "[MISSING_NAME]";
/* 13 */     retVal.DESCRIPTIONS = LocalizedStrings.createMockStringArray(3);
/* 14 */     return retVal;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\localization\PotionStrings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */