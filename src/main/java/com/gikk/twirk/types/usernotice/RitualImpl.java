/*    */ package com.gikk.twirk.types.usernotice;
/*    */ 
/*    */ import com.gikk.twirk.types.usernotice.subtype.Ritual;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class RitualImpl
/*    */   implements Ritual
/*    */ {
/*    */   private final String ritualName;
/*    */   
/*    */   public RitualImpl(String ritualName) {
/* 14 */     this.ritualName = ritualName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRitualName() {
/* 19 */     return this.ritualName;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\RitualImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */