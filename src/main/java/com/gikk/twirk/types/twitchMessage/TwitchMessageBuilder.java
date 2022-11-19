/*    */ package com.gikk.twirk.types.twitchMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface TwitchMessageBuilder
/*    */ {
/*    */   TwitchMessage build(String paramString);
/*    */   
/*    */   static TwitchMessageBuilder getDefault() {
/* 23 */     return new DefaultTwitchMessageBuilder();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\twitchMessage\TwitchMessageBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */