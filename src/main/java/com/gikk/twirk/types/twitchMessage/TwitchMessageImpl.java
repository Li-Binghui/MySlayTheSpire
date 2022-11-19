/*     */ package com.gikk.twirk.types.twitchMessage;
/*     */ 
/*     */

import com.gikk.twirk.types.TagMap;
import com.gikk.twirk.types.cheer.Cheer;
import com.gikk.twirk.types.emote.Emote;

import java.util.List;
/*     */ 
/*     */ class TwitchMessageImpl
/*     */   implements TwitchMessage {
/*     */   private final String line;
/*     */   private final String tag;
/*     */   private final String prefix;
/*     */   private final String command;
/*     */   private final String target;
/*     */   private final String content;
/*     */   private final String id;
/*     */   private final int roomID;
/*     */   private final List<Emote> emotes;
/*     */   private final List<Cheer> cheers;
/*     */   private final TagMap tagMap;
/*     */   
/*     */   TwitchMessageImpl(DefaultTwitchMessageBuilder builder) {
/*  23 */     this.line = builder.line;
/*  24 */     this.tag = builder.tag;
/*  25 */     this.prefix = builder.prefix;
/*  26 */     this.command = builder.command;
/*  27 */     this.target = builder.target;
/*  28 */     this.content = builder.content;
/*  29 */     this.emotes = builder.emotes;
/*  30 */     this.cheers = builder.cheers;
/*  31 */     this.tagMap = builder.tagMap;
/*  32 */     this.id = builder.id;
/*  33 */     this.roomID = builder.roomID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRaw() {
/*  40 */     return this.line;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTag() {
/*  45 */     return this.tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/*  50 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommand() {
/*  55 */     return this.command;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTarget() {
/*  60 */     return this.target;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContent() {
/*  65 */     return this.content;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEmotes() {
/*  70 */     return !this.emotes.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Emote> getEmotes() {
/*  75 */     return this.emotes;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCheer() {
/*  80 */     return !this.cheers.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Cheer> getCheers() {
/*  85 */     return this.cheers;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  90 */     return this.line;
/*     */   }
/*     */ 
/*     */   
/*     */   public TagMap getTagMap() {
/*  95 */     return this.tagMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBits() {
/* 100 */     int bits = 0;
/* 101 */     for (Cheer c : getCheers()) {
/* 102 */       bits += c.getBits();
/*     */     }
/* 104 */     return bits;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSentTimestamp() {
/* 109 */     return this.tagMap.getAsLong("tmi-sent-ts");
/*     */   }
/*     */ 
/*     */   
/*     */   public long getRoomID() {
/* 114 */     return this.roomID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessageID() {
/* 119 */     return this.id;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\twitchMessage\TwitchMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */