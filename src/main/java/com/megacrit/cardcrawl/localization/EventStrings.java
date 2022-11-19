/*    */ package com.megacrit.cardcrawl.localization;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EventStrings
/*    */ {
/*    */   public String NAME;
/*    */   public String[] DESCRIPTIONS;
/*    */   public String[] OPTIONS;
/*    */   
/*    */   public static EventStrings getMockEventString() {
/* 12 */     EventStrings retVal = new EventStrings();
/* 13 */     retVal.NAME = "[MISSING_NAME]";
/* 14 */     retVal.DESCRIPTIONS = LocalizedStrings.createMockStringArray(12);
/* 15 */     retVal.OPTIONS = LocalizedStrings.createMockStringArray(12);
/* 16 */     return retVal;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\localization\EventStrings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */