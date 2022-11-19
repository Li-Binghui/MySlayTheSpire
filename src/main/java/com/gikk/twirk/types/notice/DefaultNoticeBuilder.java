/*    */ package com.gikk.twirk.types.notice;
/*    */ 
/*    */

import com.gikk.twirk.enums.NOTICE_EVENT;
import com.gikk.twirk.types.TagMap;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultNoticeBuilder
/*    */   implements NoticeBuilder
/*    */ {
/*    */   NOTICE_EVENT event;
/*    */   String message;
/*    */   String rawLine;
/*    */   String rawEvent;
/*    */   
/*    */   public Notice build(TwitchMessage message) {
/* 18 */     TagMap r = message.getTagMap();
/* 19 */     this.rawEvent = r.getAsString("msg-id");
/* 20 */     this.event = NOTICE_EVENT.of(this.rawEvent);
/* 21 */     this.message = message.getContent();
/* 22 */     this.rawLine = message.getRaw();
/*    */     
/* 24 */     return new NoticeImpl(this);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\notice\DefaultNoticeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */