/*    */ package com.gikk.twirk.types.clearChat;
/*    */ 
/*    */ import com.gikk.twirk.enums.CLEARCHAT_MODE;
/*    */ 
/*    */ class ClearChatImpl
/*    */   implements ClearChat {
/*    */   public final CLEARCHAT_MODE mode;
/*    */   public final String target;
/*    */   private final String reason;
/*    */   private final String rawLine;
/*    */   private final int duration;
/*    */   
/*    */   ClearChatImpl(DefaultClearChatBuilder builder) {
/* 14 */     this.mode = builder.mode;
/* 15 */     this.target = builder.target;
/* 16 */     this.reason = builder.reason;
/* 17 */     this.duration = builder.duration;
/* 18 */     this.rawLine = builder.rawLine;
/*    */   }
/*    */ 
/*    */   
/*    */   public CLEARCHAT_MODE getMode() {
/* 23 */     return this.mode;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTarget() {
/* 28 */     return this.target;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDuration() {
/* 33 */     return this.duration;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getReason() {
/* 38 */     return this.reason;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRaw() {
/* 43 */     return this.rawLine;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\clearChat\ClearChatImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */