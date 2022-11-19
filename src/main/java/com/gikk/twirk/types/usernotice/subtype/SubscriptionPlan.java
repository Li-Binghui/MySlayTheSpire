/*    */ package com.gikk.twirk.types.usernotice.subtype;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum SubscriptionPlan
/*    */ {
/* 10 */   SUB_PRIME("Prime", "Prime"),
/*    */ 
/*    */ 
/*    */   
/* 14 */   SUB_LOW("1000", "4.99$"),
/*    */ 
/*    */ 
/*    */   
/* 18 */   SUB_MID("2000", "9.99$"),
/*    */ 
/*    */ 
/*    */   
/* 22 */   SUB_HIGH("3000", "24.99$");
/*    */   private final String message;
/*    */   private final String value;
/*    */   
/*    */   SubscriptionPlan(String msg, String val) {
/* 27 */     this.message = msg;
/* 28 */     this.value = val;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 32 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static SubscriptionPlan of(String paramSubPlan) {
/* 45 */     for (SubscriptionPlan s : values()) {
/* 46 */       if (s.message.equals(paramSubPlan)) {
/* 47 */         return s;
/*    */       }
/*    */     } 
/* 50 */     return SUB_LOW;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\subtype\SubscriptionPlan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */