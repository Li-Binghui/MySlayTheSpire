/*    */ package com.gikk.twirk.types.notice;
/*    */ 
/*    */ import com.gikk.twirk.enums.NOTICE_EVENT;
/*    */ 
/*    */ class NoticeImpl implements Notice {
/*    */   private final NOTICE_EVENT event;
/*    */   private final String message;
/*    */   private final String rawLine;
/*    */   private final String rawEvent;
/*    */   
/*    */   NoticeImpl(DefaultNoticeBuilder builder) {
/* 12 */     this.event = builder.event;
/* 13 */     this.message = builder.message;
/* 14 */     this.rawLine = builder.rawLine;
/* 15 */     this.rawEvent = builder.rawEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public NOTICE_EVENT getEvent() {
/* 20 */     return this.event;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 25 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRaw() {
/* 30 */     return this.rawLine;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRawNoticeID() {
/* 35 */     return this.rawEvent;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\notice\NoticeImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */