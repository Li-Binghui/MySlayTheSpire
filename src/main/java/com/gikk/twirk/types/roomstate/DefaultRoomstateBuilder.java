/*    */ package com.gikk.twirk.types.roomstate;
/*    */ 
/*    */ import com.gikk.twirk.types.TagMap;
/*    */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ 
/*    */ class DefaultRoomstateBuilder
/*    */   implements RoomstateBuilder
/*    */ {
/*    */   String broadcasterLanguage;
/*    */   int r9kMode;
/*    */   int subMode;
/*    */   int slowModeTimer;
/*    */   String rawLine;
/*    */   
/*    */   public Roomstate build(TwitchMessage message) {
/* 17 */     this.rawLine = message.getRaw();
/* 18 */     TagMap r = message.getTagMap();
/*    */     
/* 20 */     this.broadcasterLanguage = r.getAsString("broadcaster-lang");
/* 21 */     this.r9kMode = r.getAsInt("r9k");
/* 22 */     this.slowModeTimer = r.getAsInt("slow");
/* 23 */     this.subMode = r.getAsInt("subs-only");
/*    */     
/* 25 */     return new RoomstateImpl(this);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\roomstate\DefaultRoomstateBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */