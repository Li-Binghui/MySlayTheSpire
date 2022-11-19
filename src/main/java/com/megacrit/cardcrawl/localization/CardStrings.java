/*    */ package com.megacrit.cardcrawl.localization;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardStrings
/*    */ {
/*    */   public String NAME;
/*    */   public String DESCRIPTION;
/*    */   public String UPGRADE_DESCRIPTION;
/*    */   public String[] EXTENDED_DESCRIPTION;
/*    */   
/*    */   public static CardStrings getMockCardString() {
/* 13 */     CardStrings retVal = new CardStrings();
/* 14 */     retVal.NAME = "[MISSING_TITLE]";
/* 15 */     retVal.DESCRIPTION = "[MISSING_DESCRIPTION]";
/* 16 */     retVal.UPGRADE_DESCRIPTION = "[MISSING_DESCRIPTION+]";
/* 17 */     retVal.EXTENDED_DESCRIPTION = new String[] { "[MISSING_0]", "[MISSING_1]", "[MISSING_2]" };
/* 18 */     return retVal;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\localization\CardStrings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */