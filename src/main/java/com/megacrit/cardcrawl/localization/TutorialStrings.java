/*    */ package com.megacrit.cardcrawl.localization;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TutorialStrings
/*    */ {
/*    */   public String[] TEXT;
/*    */   public String[] LABEL;
/*    */   
/*    */   public static TutorialStrings getMockTutorialString() {
/* 11 */     TutorialStrings retVal = new TutorialStrings();
/* 12 */     retVal.TEXT = LocalizedStrings.createMockStringArray(25);
/* 13 */     retVal.LABEL = LocalizedStrings.createMockStringArray(8);
/* 14 */     return retVal;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\localization\TutorialStrings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */