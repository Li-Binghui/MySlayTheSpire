/*    */ package com.gikk.twirk.types.usernotice;
/*    */ 
/*    */

import com.gikk.twirk.types.TagMap;
import com.gikk.twirk.types.emote.Emote;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.usernotice.subtype.Raid;
import com.gikk.twirk.types.usernotice.subtype.Ritual;
import com.gikk.twirk.types.usernotice.subtype.Subscription;

import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class DefaultUsernoticeBuilder
/*    */   implements UsernoticeBuilder
/*    */ {
/*    */   String rawLine;
/*    */   List<Emote> emotes;
/*    */   String messageID;
/*    */   int roomID;
/*    */   long sentTimestamp;
/*    */   String systemMessage;
/*    */   Subscription subscription;
/*    */   Raid raid;
/*    */   Ritual ritual;
/*    */   String message;
/*    */   
/*    */   public Usernotice build(TwitchMessage message) {
/* 29 */     this.rawLine = message.getRaw();
/* 30 */     this.emotes = message.getEmotes();
/*    */     
/* 32 */     TagMap map = message.getTagMap();
/* 33 */     this.messageID = map.getAsString("id");
/* 34 */     this.roomID = map.getAsInt("room-id");
/* 35 */     this.sentTimestamp = map.getAsLong("tmi-sent-ts");
/* 36 */     this.systemMessage = map.getAsString("system-msg");
/*    */     
/* 38 */     String type = (String)map.get("msg-id");
/* 39 */     if (type.equals("sub") || type.equals("resub") || type.equals("subgift") || type.equals("anonsubgift")) {
/* 40 */       this.subscription = TypeParser.parseSubscription(map);
/*    */     }
/* 42 */     else if (type.equals("raid")) {
/* 43 */       this.raid = TypeParser.parseRaid(map);
/*    */     }
/* 45 */     else if (type.equals("ritual")) {
/* 46 */       this.ritual = TypeParser.parseRitual(map);
/*    */     } 
/*    */     
/* 49 */     this.message = message.getContent();
/*    */     
/* 51 */     Usernotice un = new UsernoticeImpl(this);
/* 52 */     clear();
/* 53 */     return un;
/*    */   }
/*    */   
/*    */   private void clear() {
/* 57 */     this.subscription = null;
/* 58 */     this.raid = null;
/* 59 */     this.ritual = null;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\DefaultUsernoticeBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */