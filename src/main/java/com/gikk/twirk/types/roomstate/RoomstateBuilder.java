/*    */ package com.gikk.twirk.types.roomstate;
/*    */ 
/*    */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface RoomstateBuilder
/*    */ {
/*    */   static RoomstateBuilder getDefault() {
/* 13 */     return new DefaultRoomstateBuilder();
/*    */   }
/*    */   
/*    */   Roomstate build(TwitchMessage paramTwitchMessage);
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\roomstate\RoomstateBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */