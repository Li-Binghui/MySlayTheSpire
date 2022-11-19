/*     */ package com.gikk.twirk.commands;
/*     */

import com.gikk.twirk.enums.USER_TYPE;
import com.gikk.twirk.events.TwirkListener;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/*     */
/*     */ public abstract class CommandExampleBase implements TwirkListener {
/*     */   private Set<String> commandPattern;
/*     */   private CommandType type;
/*     */   private USER_TYPE minPrivilidge;
/*     */   
/*     */   public enum CommandType {
/*  12 */     PREFIX_COMMAND, CONTENT_COMMAND;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CommandExampleBase(CommandType type) {
/*  30 */     this.type = type;
/*  31 */     this.commandPattern = compile();
/*  32 */     this.minPrivilidge = getMinUserPrevilidge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPrivMsg(TwitchUser sender, TwitchMessage message) {
/*  49 */     String content = message.getContent().toLowerCase(Locale.ENGLISH).trim();
/*  50 */     String[] split = content.split("\\s", 2);
/*  51 */     String command = split[0];
/*     */     
/*  53 */     if ((sender.getUserType()).value >= this.minPrivilidge.value) {
/*  54 */       if (this.type == CommandType.PREFIX_COMMAND) {
/*  55 */         for (String pattern : this.commandPattern) {
/*  56 */           if (command.startsWith(pattern)) {
/*  57 */             performCommand(pattern, sender, message);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } else {
/*  63 */         for (String pattern : this.commandPattern) {
/*  64 */           if (content.contains(pattern)) {
/*  65 */             performCommand(pattern, sender, message);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract String getCommandWords();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract USER_TYPE getMinUserPrevilidge();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void performCommand(String paramString, TwitchUser paramTwitchUser, TwitchMessage paramTwitchMessage);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Set<String> compile() {
/* 111 */     String[] patterns = getCommandWords().toLowerCase(Locale.ENGLISH).split("\\|");
/* 112 */     HashSet<String> out = new HashSet<>();
/* 113 */     for (String pattern : patterns)
/* 114 */       out.add(pattern); 
/* 115 */     return out;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\commands\CommandExampleBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */