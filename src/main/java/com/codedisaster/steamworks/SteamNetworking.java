/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class SteamNetworking extends SteamInterface {
/*     */   private final boolean isServer;
/*     */   
/*     */   public enum P2PSend {
/*   9 */     Unreliable,
/*  10 */     UnreliableNoDelay,
/*  11 */     Reliable,
/*  12 */     ReliableWithBuffering;
/*     */   }
/*     */   
/*     */   public enum P2PSessionError {
/*  16 */     None,
/*  17 */     NotRunningApp,
/*  18 */     NoRightsToApp,
/*  19 */     DestinationNotLoggedIn,
/*  20 */     Timeout;
/*     */     
/*  22 */     private static final P2PSessionError[] values = values();
/*     */     
/*     */     public static P2PSessionError byOrdinal(int sessionError) {
/*  25 */       return values[sessionError];
/*     */     }
/*     */     static {
/*     */     
/*     */     } }
/*     */   
/*     */   public static class P2PSessionState { byte connectionActive;
/*     */     byte connecting;
/*     */     byte sessionError;
/*     */     byte usingRelay;
/*     */     int bytesQueuedForSend;
/*     */     int packetsQueuedForSend;
/*     */     int remoteIP;
/*     */     short remotePort;
/*     */     
/*     */     public boolean isConnectionActive() {
/*  41 */       return (this.connectionActive != 0);
/*     */     }
/*     */     
/*     */     public boolean isConnecting() {
/*  45 */       return (this.connecting != 0);
/*     */     }
/*     */     
/*     */     public P2PSessionError getLastSessionError() {
/*  49 */       return P2PSessionError.byOrdinal(this.sessionError);
/*     */     }
/*     */     
/*     */     public boolean isUsingRelay() {
/*  53 */       return (this.usingRelay != 0);
/*     */     }
/*     */     
/*     */     public int getBytesQueuedForSend() {
/*  57 */       return this.bytesQueuedForSend;
/*     */     }
/*     */     
/*     */     public int getPacketsQueuedForSend() {
/*  61 */       return this.packetsQueuedForSend;
/*     */     }
/*     */     
/*     */     public int getRemoteIP() {
/*  65 */       return this.remoteIP;
/*     */     }
/*     */     
/*     */     public short getRemotePort() {
/*  69 */       return this.remotePort;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*  74 */   private final int[] tmpIntResult = new int[1];
/*  75 */   private final long[] tmpLongResult = new long[1];
/*     */   
/*     */   public SteamNetworking(SteamNetworkingCallback callback) {
/*  78 */     this(false, SteamNetworkingNative.createCallback(new SteamNetworkingCallbackAdapter(callback)));
/*     */   }
/*     */   
/*     */   SteamNetworking(boolean isServer, long callback) {
/*  82 */     super(callback);
/*  83 */     this.isServer = isServer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean sendP2PPacket(SteamID steamIDRemote, ByteBuffer data, P2PSend sendType, int channel) throws SteamException {
/*  89 */     if (!data.isDirect()) {
/*  90 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/*  93 */     return SteamNetworkingNative.sendP2PPacket(this.isServer, steamIDRemote.handle, data, data
/*  94 */         .position(), data.remaining(), sendType.ordinal(), channel);
/*     */   }
/*     */   
/*     */   public boolean isP2PPacketAvailable(int channel, int[] msgSize) {
/*  98 */     return SteamNetworkingNative.isP2PPacketAvailable(this.isServer, msgSize, channel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readP2PPacket(SteamID steamIDRemote, ByteBuffer dest, int channel) throws SteamException {
/* 109 */     if (!dest.isDirect()) {
/* 110 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 113 */     if (SteamNetworkingNative.readP2PPacket(this.isServer, dest, dest.position(), dest.remaining(), this.tmpIntResult, this.tmpLongResult, channel)) {
/* 114 */       steamIDRemote.handle = this.tmpLongResult[0];
/* 115 */       return this.tmpIntResult[0];
/*     */     } 
/*     */     
/* 118 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean acceptP2PSessionWithUser(SteamID steamIDRemote) {
/* 122 */     return SteamNetworkingNative.acceptP2PSessionWithUser(this.isServer, steamIDRemote.handle);
/*     */   }
/*     */   
/*     */   public boolean closeP2PSessionWithUser(SteamID steamIDRemote) {
/* 126 */     return SteamNetworkingNative.closeP2PSessionWithUser(this.isServer, steamIDRemote.handle);
/*     */   }
/*     */   
/*     */   public boolean closeP2PChannelWithUser(SteamID steamIDRemote, int channel) {
/* 130 */     return SteamNetworkingNative.closeP2PChannelWithUser(this.isServer, steamIDRemote.handle, channel);
/*     */   }
/*     */   
/*     */   public boolean getP2PSessionState(SteamID steamIDRemote, P2PSessionState connectionState) {
/* 134 */     return SteamNetworkingNative.getP2PSessionState(this.isServer, steamIDRemote.handle, connectionState);
/*     */   }
/*     */   
/*     */   public boolean allowP2PPacketRelay(boolean allow) {
/* 138 */     return SteamNetworkingNative.allowP2PPacketRelay(this.isServer, allow);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamNetworking.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */