/*    */ package com.gikk.twirk.enums;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum USER_TYPE
/*    */ {
/* 21 */   OWNER(9),
/*    */ 
/*    */   
/* 24 */   MOD(6),
/*    */ 
/*    */   
/* 27 */   GLOBAL_MOD(4),
/*    */ 
/*    */   
/* 30 */   ADMIN(4),
/*    */ 
/*    */   
/* 33 */   STAFF(4),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   SUBSCRIBER(2),
/*    */ 
/*    */   
/* 42 */   DEFAULT(0);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final int value;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   USER_TYPE(int value) {
/* 54 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\enums\USER_TYPE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */