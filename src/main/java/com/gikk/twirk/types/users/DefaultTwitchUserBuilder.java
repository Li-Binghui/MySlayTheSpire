/*    */ package com.gikk.twirk.types.users;
/*    */ 
/*    */

import com.gikk.twirk.types.AbstractTwitchUserFields;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ 
/*    */ class DefaultTwitchUserBuilder
/*    */   extends AbstractTwitchUserFields
/*    */   implements TwitchUserBuilder {
/*    */   public TwitchUser build(TwitchMessage message) {
/* 10 */     parseUserProperties(message);
/* 11 */     return new TwitchUserImpl(this);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\users\DefaultTwitchUserBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */