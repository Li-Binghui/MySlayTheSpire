/*    */ package com.megacrit.cardcrawl.localization;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrbStrings
/*    */ {
/*    */   public String NAME;
/*    */   public String[] DESCRIPTION;
/*    */   
/*    */   public static OrbStrings getMockOrbString() {
/* 11 */     OrbStrings retVal = new OrbStrings();
/* 12 */     retVal.NAME = "[MISSING_NAME]";
/* 13 */     retVal.DESCRIPTION = LocalizedStrings.createMockStringArray(5);
/* 14 */     return retVal;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\localization\OrbStrings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */