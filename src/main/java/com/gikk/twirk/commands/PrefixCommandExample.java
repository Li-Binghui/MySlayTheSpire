/*    */ package com.gikk.twirk.commands;
/*    */ 
/*    */

import com.gikk.twirk.Twirk;
import com.gikk.twirk.enums.USER_TYPE;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

import java.util.Calendar;
import java.util.Date;
/*    */ 
/*    */ public class PrefixCommandExample
/*    */   extends CommandExampleBase {
/*    */   private static final String patternA = "!timezone";
/*    */   private static final String patternB = "!time";
/*    */   private final Twirk twirk;
/*    */   
/*    */   public PrefixCommandExample(Twirk twirk) {
/* 17 */     super(CommandType.PREFIX_COMMAND);
/* 18 */     this.twirk = twirk;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getCommandWords() {
/* 23 */     return "!timezone|!time";
/*    */   }
/*    */ 
/*    */   
/*    */   protected USER_TYPE getMinUserPrevilidge() {
/* 28 */     return USER_TYPE.DEFAULT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void performCommand(String command, TwitchUser sender, TwitchMessage message) {
/* 33 */     if (command.equals("!timezone")) {
/* 34 */       this.twirk.channelMessage(sender.getDisplayName() + ": Local time zone is " + Calendar.getInstance().getTimeZone().getDisplayName());
/* 35 */     } else if (command.equals("!time")) {
/* 36 */       this.twirk.channelMessage(sender.getDisplayName() + ": Local time is " + (new Date()).toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\commands\PrefixCommandExample.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */