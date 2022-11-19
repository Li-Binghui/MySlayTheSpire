/*     */ package net.arikia.dev.drpc;
/*     */ 
/*     */ import com.sun.jna.Structure;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class DiscordRichPresence
/*     */   extends Structure
/*     */ {
/*     */   public String state;
/*     */   public String details;
/*     */   public long startTimestamp;
/*     */   
/*     */   public List<String> getFieldOrder() {
/*  16 */     return Arrays.asList(new String[] { "state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance" });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long endTimestamp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String largeImageKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String largeImageText;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String smallImageKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String smallImageText;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String partyId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int partySize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int partyMax;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String matchSecret;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String spectateSecret;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String joinSecret;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int instance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Builder
/*     */   {
/*     */     private DiscordRichPresence p;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder(String state) {
/* 111 */       this.p = new DiscordRichPresence();
/* 112 */       this.p.state = state;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setDetails(String details) {
/* 122 */       this.p.details = details;
/* 123 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setStartTimestamps(long start) {
/* 133 */       this.p.startTimestamp = start;
/* 134 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setEndTimestamp(long end) {
/* 144 */       this.p.endTimestamp = end;
/* 145 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setBigImage(String key, String text) {
/* 156 */       if (text != null && !text.equalsIgnoreCase("") && key == null) {
/* 157 */         throw new IllegalArgumentException("Image key must not be null when assigning a hover text.");
/*     */       }
/* 159 */       this.p.largeImageKey = key;
/* 160 */       this.p.largeImageText = text;
/* 161 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setSmallImage(String key, String text) {
/* 172 */       if (text != null && !text.equalsIgnoreCase("") && key == null) {
/* 173 */         throw new IllegalArgumentException("Image key must not be null when assigning a hover text.");
/*     */       }
/* 175 */       this.p.smallImageKey = key;
/* 176 */       this.p.smallImageText = text;
/* 177 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setParty(String party, int size, int max) {
/* 189 */       this.p.partyId = party;
/* 190 */       this.p.partySize = size;
/* 191 */       this.p.partyMax = max;
/* 192 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Builder setSecrets(String match, String join, String spectate) {
/* 200 */       this.p.matchSecret = match;
/* 201 */       this.p.joinSecret = join;
/* 202 */       this.p.spectateSecret = spectate;
/* 203 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setSecrets(String join, String spectate) {
/* 214 */       this.p.joinSecret = join;
/* 215 */       this.p.spectateSecret = spectate;
/* 216 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Builder setInstance(boolean i) {
/* 224 */       this.p.instance = i ? 1 : 0;
/* 225 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DiscordRichPresence build() {
/* 234 */       return this.p;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\net\arikia\dev\drpc\DiscordRichPresence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */