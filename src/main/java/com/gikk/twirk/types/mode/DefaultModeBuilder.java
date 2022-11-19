/*    */ package com.gikk.twirk.types.mode;
/*    */ 
/*    */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class DefaultModeBuilder
/*    */   implements ModeBuilder
/*    */ {
/*    */   Mode.MODE_EVENT event;
/*    */   String user;
/*    */   String rawLine;
/*    */   
/*    */   public Mode build(TwitchMessage message) {
/* 21 */     this.rawLine = message.getRaw();
/* 22 */     String content = message.getContent();
/* 23 */     this.event = content.startsWith("+o") ? Mode.MODE_EVENT.GAINED_MOD : Mode.MODE_EVENT.LOST_MOD;
/* 24 */     this.user = content.substring(content.indexOf(' ') + 1);
/*    */     
/* 26 */     return new ModeImpl(this);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\mode\DefaultModeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */