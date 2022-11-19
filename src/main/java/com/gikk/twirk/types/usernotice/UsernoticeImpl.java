/*     */ package com.gikk.twirk.types.usernotice;
/*     */ 
/*     */ import com.gikk.twirk.types.emote.Emote;
/*     */ import com.gikk.twirk.types.usernotice.subtype.Raid;
/*     */ import com.gikk.twirk.types.usernotice.subtype.Ritual;
/*     */ import com.gikk.twirk.types.usernotice.subtype.Subscription;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ class UsernoticeImpl
/*     */   implements Usernotice
/*     */ {
/*     */   private final String raw;
/*     */   private final Optional<Raid> raid;
/*     */   private final Optional<Subscription> subscription;
/*     */   private final Optional<Ritual> ritual;
/*     */   private final String systemMessage;
/*     */   private final String message;
/*     */   private final List<Emote> emotes;
/*     */   private final long sentTimestamp;
/*     */   private final int roomID;
/*     */   private final String messageID;
/*     */   
/*     */   UsernoticeImpl(DefaultUsernoticeBuilder builder) {
/*  27 */     this.raw = builder.rawLine;
/*     */     
/*  29 */     this.raid = Optional.ofNullable(builder.raid);
/*  30 */     this.subscription = Optional.ofNullable(builder.subscription);
/*  31 */     this.ritual = Optional.ofNullable(builder.ritual);
/*     */     
/*  33 */     this.systemMessage = builder.systemMessage;
/*  34 */     this.message = builder.message;
/*  35 */     this.emotes = builder.emotes;
/*     */     
/*  37 */     this.sentTimestamp = builder.sentTimestamp;
/*  38 */     this.roomID = builder.roomID;
/*  39 */     this.messageID = builder.messageID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSystemMessage() {
/*  44 */     return this.systemMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSubscription() {
/*  49 */     return this.subscription.isPresent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<Subscription> getSubscription() {
/*  54 */     return this.subscription;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRaid() {
/*  59 */     return this.raid.isPresent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<Raid> getRaid() {
/*  64 */     return this.raid;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRitual() {
/*  69 */     return this.ritual.isPresent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<Ritual> getRitual() {
/*  74 */     return this.ritual;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  79 */     return this.message;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEmotes() {
/*  84 */     return !this.emotes.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Emote> getEmotes() {
/*  89 */     return this.emotes;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSentTimestamp() {
/*  94 */     return this.sentTimestamp;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getRoomID() {
/*  99 */     return this.roomID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessageID() {
/* 104 */     return this.messageID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRaw() {
/* 109 */     return this.raw;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\UsernoticeImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */