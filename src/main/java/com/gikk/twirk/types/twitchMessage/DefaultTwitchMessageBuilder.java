/*     */ package com.gikk.twirk.types.twitchMessage;
/*     */ 
/*     */

import com.gikk.twirk.types.TagMap;
import com.gikk.twirk.types.cheer.Cheer;
import com.gikk.twirk.types.cheer.CheerParser;
import com.gikk.twirk.types.emote.Emote;
import com.gikk.twirk.types.emote.EmoteParser;

import java.util.List;
/*     */ 
/*     */ class DefaultTwitchMessageBuilder
/*     */   implements TwitchMessageBuilder
/*     */ {
/*     */   String line;
/*     */   String tag;
/*     */   String prefix;
/*     */   String command;
/*     */   String target;
/*     */   String content;
/*     */   String id;
/*     */   int roomID;
/*     */   List<Emote> emotes;
/*     */   List<Cheer> cheers;
/*     */   TagMap tagMap;
/*     */   
/*     */   public TwitchMessage build(String chatLine) {
/*  26 */     if (chatLine.startsWith("@")) {
/*  27 */       parseWithTag(chatLine);
/*     */     } else {
/*  29 */       parseWithoutTag(chatLine);
/*     */     } 
/*     */     
/*  32 */     this.line = chatLine;
/*  33 */     this.tagMap = TagMap.getDefault(this.tag);
/*  34 */     this.id = this.tagMap.getAsString("id");
/*  35 */     this.roomID = this.tagMap.getAsInt("room-id");
/*  36 */     this.emotes = EmoteParser.parseEmotes(this.content, this.tag);
/*  37 */     this.cheers = CheerParser.parseCheer(this.tagMap, this.content);
/*     */     
/*  39 */     return new TwitchMessageImpl(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseWithTag(String line) {
/*  46 */     String[] parts = line.split(" ", 5);
/*     */     
/*  48 */     if (parts.length == 5) {
/*  49 */       this.content = parts[4].startsWith(":") ? parts[4].substring(1) : parts[4];
/*     */     } else {
/*  51 */       this.content = "";
/*     */     } 
/*  53 */     if (parts.length >= 4) {
/*  54 */       this.target = parts[3];
/*     */     } else {
/*  56 */       this.target = "";
/*     */     } 
/*  58 */     if (parts.length >= 3) {
/*  59 */       this.command = parts[2];
/*     */     } else {
/*  61 */       this.command = "";
/*     */     } 
/*  63 */     if (parts.length >= 2) {
/*  64 */       this.prefix = parts[1];
/*     */     } else {
/*  66 */       this.prefix = "";
/*     */     } 
/*  68 */     if (parts.length >= 1) {
/*  69 */       this.tag = parts[0];
/*     */     } else {
/*  71 */       this.tag = "";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseWithoutTag(String line) {
/*  76 */     this.tag = "";
/*     */     
/*  78 */     StringBuilder build = new StringBuilder();
/*  79 */     int i = 0;
/*     */     
/*     */     char c;
/*  82 */     while ((c = line.charAt(i++)) != ' ') {
/*  83 */       build.append(c);
/*     */     }
/*  85 */     this.prefix = build.toString().trim();
/*  86 */     build.setLength(0);
/*     */ 
/*     */     
/*     */     do {
/*  90 */       build.append(c);
/*  91 */     } while (i < line.length() && (c = line.charAt(i++)) != ' ');
/*  92 */     this.command = build.toString().trim();
/*  93 */     build.setLength(0);
/*     */ 
/*     */     
/*     */     do {
/*  97 */       build.append(c);
/*  98 */     } while (i < line.length() && (c = line.charAt(i++)) != ':' && c != '+' && c != '-');
/*  99 */     this.target = build.toString().trim();
/* 100 */     build.setLength(0);
/*     */     
/* 102 */     if (i == line.length()) {
/* 103 */       this.content = "";
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     do {
/* 109 */       build.append(c);
/* 110 */     } while (i < line.length() && (c = line.charAt(i++)) != '\r');
/* 111 */     String temp = build.toString().trim();
/* 112 */     this.content = temp.startsWith(":") ? temp.substring(1) : temp;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\twitchMessage\DefaultTwitchMessageBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */