/*    */ package com.gikk.twirk.commands;
/*    */ 
/*    */ import com.gikk.twirk.Twirk;
/*    */ import com.gikk.twirk.enums.USER_TYPE;
/*    */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*    */ import com.gikk.twirk.types.users.TwitchUser;
/*    */ 
/*    */ public class PatternCommandExample extends CommandExampleBase {
/*  9 */   private static String PATTERN = "tick";
/*    */   
/*    */   private final Twirk twirk;
/*    */   
/*    */   public PatternCommandExample(Twirk twirk) {
/* 14 */     super(CommandType.CONTENT_COMMAND);
/* 15 */     this.twirk = twirk;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getCommandWords() {
/* 20 */     return PATTERN;
/*    */   }
/*    */ 
/*    */   
/*    */   protected USER_TYPE getMinUserPrevilidge() {
/* 25 */     return USER_TYPE.DEFAULT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void performCommand(String command, TwitchUser sender, TwitchMessage message) {
/* 30 */     this.twirk.channelMessage("Tock");
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\commands\PatternCommandExample.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */