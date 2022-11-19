/*    */ package com.gikk.twirk.types.clearChat;
/*    */ 
/*    */

import com.gikk.twirk.enums.CLEARCHAT_MODE;
import com.gikk.twirk.types.TagMap;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ class DefaultClearChatBuilder
/*    */   implements ClearChatBuilder {
/*    */   CLEARCHAT_MODE mode;
/* 10 */   String target = "";
/* 11 */   int duration = -1;
/* 12 */   String reason = "";
/*    */   
/*    */   String rawLine;
/*    */   
/*    */   public ClearChat build(TwitchMessage twitchMessage) {
/* 17 */     this.rawLine = twitchMessage.getRaw();
/*    */     
/* 19 */     if (twitchMessage.getContent().isEmpty()) {
/* 20 */       this.mode = CLEARCHAT_MODE.COMPLETE;
/* 21 */       this.target = "";
/*    */     } else {
/*    */       
/* 24 */       this.mode = CLEARCHAT_MODE.USER;
/* 25 */       this.target = twitchMessage.getContent();
/*    */       
/* 27 */       TagMap r = twitchMessage.getTagMap();
/* 28 */       this.duration = r.getAsInt("ban-duration");
/* 29 */       this.reason = r.getAsString("ban-reason");
/*    */     } 
/*    */     
/* 32 */     return new ClearChatImpl(this);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\clearChat\DefaultClearChatBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */