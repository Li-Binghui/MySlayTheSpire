/*    */ package com.gikk.twirk.types.notice;
/*    */ 
/*    */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface NoticeBuilder
/*    */ {
/*    */   static NoticeBuilder getDefault() {
/* 13 */     return new DefaultNoticeBuilder();
/*    */   }
/*    */   
/*    */   Notice build(TwitchMessage paramTwitchMessage);
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\notice\NoticeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */