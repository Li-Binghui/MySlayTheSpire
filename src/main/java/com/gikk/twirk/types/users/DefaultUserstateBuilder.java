/*    */ package com.gikk.twirk.types.users;
/*    */ 
/*    */ import com.gikk.twirk.types.AbstractTwitchUserFields;
/*    */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ class DefaultUserstateBuilder
/*    */   extends AbstractTwitchUserFields
/*    */   implements UserstateBuilder {
/*    */   public Userstate build(TwitchMessage message) {
/* 10 */     parseUserProperties(message);
/* 11 */     return new UserstateImpl(this);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\users\DefaultUserstateBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */