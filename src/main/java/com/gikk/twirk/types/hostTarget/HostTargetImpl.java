/*    */ package com.gikk.twirk.types.hostTarget;
/*    */ 
/*    */ import com.gikk.twirk.enums.HOSTTARGET_MODE;
/*    */ 
/*    */ class HostTargetImpl
/*    */   implements HostTarget {
/*    */   final HOSTTARGET_MODE mode;
/*    */   final String target;
/*    */   final int viwerAmount;
/*    */   private final String rawLine;
/*    */   
/*    */   HostTargetImpl(DefaultHostTargetBuilder builder) {
/* 13 */     this.mode = builder.mode;
/* 14 */     this.target = builder.target;
/* 15 */     this.viwerAmount = builder.viwerAmount;
/* 16 */     this.rawLine = builder.rawLine;
/*    */   }
/*    */ 
/*    */   
/*    */   public HOSTTARGET_MODE getMode() {
/* 21 */     return this.mode;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTarget() {
/* 26 */     return this.target;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getViewerCount() {
/* 31 */     return this.viwerAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRaw() {
/* 36 */     return this.rawLine;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\hostTarget\HostTargetImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */