/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ public class SteamMatchmakingGameServerItem
/*     */ {
/*   5 */   SteamMatchmakingServerNetAdr netAdr = new SteamMatchmakingServerNetAdr();
/*     */   
/*     */   int ping;
/*     */   
/*     */   boolean hadSuccessfulResponse;
/*     */   
/*     */   boolean doNotRefresh;
/*     */   
/*     */   String gameDir;
/*     */   
/*     */   String map;
/*     */   
/*     */   String gameDescription;
/*     */   
/*     */   int appID;
/*     */   int players;
/*     */   int maxPlayers;
/*     */   int botPlayers;
/*     */   boolean password;
/*     */   boolean secure;
/*     */   int timeLastPlayed;
/*     */   int serverVersion;
/*     */   String serverName;
/*     */   String gameTags;
/*     */   long steamID;
/*     */   
/*     */   public SteamMatchmakingServerNetAdr getNetAdr() {
/*  32 */     return this.netAdr;
/*     */   }
/*     */   
/*     */   public int getPing() {
/*  36 */     return this.ping;
/*     */   }
/*     */   
/*     */   public boolean hadSuccessfulResponse() {
/*  40 */     return this.hadSuccessfulResponse;
/*     */   }
/*     */   
/*     */   public boolean doNotRefresh() {
/*  44 */     return this.doNotRefresh;
/*     */   }
/*     */   
/*     */   public String getGameDir() {
/*  48 */     return this.gameDir;
/*     */   }
/*     */   
/*     */   public String getMap() {
/*  52 */     return this.map;
/*     */   }
/*     */   
/*     */   public String getGameDescription() {
/*  56 */     return this.gameDescription;
/*     */   }
/*     */   
/*     */   public int getAppID() {
/*  60 */     return this.appID;
/*     */   }
/*     */   
/*     */   public int getPlayers() {
/*  64 */     return this.players;
/*     */   }
/*     */   
/*     */   public int getMaxPlayers() {
/*  68 */     return this.maxPlayers;
/*     */   }
/*     */   
/*     */   public int getBotPlayers() {
/*  72 */     return this.botPlayers;
/*     */   }
/*     */   
/*     */   public boolean hasPassword() {
/*  76 */     return this.password;
/*     */   }
/*     */   
/*     */   public boolean isSecure() {
/*  80 */     return this.secure;
/*     */   }
/*     */   
/*     */   public int getTimeLastPlayed() {
/*  84 */     return this.timeLastPlayed;
/*     */   }
/*     */   
/*     */   public int getServerVersion() {
/*  88 */     return this.serverVersion;
/*     */   }
/*     */   
/*     */   public String getServerName() {
/*  92 */     return this.serverName;
/*     */   }
/*     */   
/*     */   public String getGameTags() {
/*  96 */     return this.gameTags;
/*     */   }
/*     */   
/*     */   public SteamID getSteamID() {
/* 100 */     return new SteamID(this.steamID);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingGameServerItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */