/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class SteamUser
/*     */   extends SteamInterface
/*     */ {
/*     */   public enum VoiceResult {
/*   9 */     OK,
/*  10 */     NotInitialized,
/*  11 */     NotRecording,
/*  12 */     NoData,
/*  13 */     BufferTooSmall,
/*  14 */     DataCorrupted,
/*  15 */     Restricted,
/*  16 */     UnsupportedCodec,
/*  17 */     ReceiverOutOfDate,
/*  18 */     ReceiverDidNotAnswer;
/*     */     
/*  20 */     private static final VoiceResult[] values = values();
/*     */     
/*     */     static VoiceResult byOrdinal(int voiceResult) {
/*  23 */       return values[voiceResult];
/*     */     } static {
/*     */     
/*     */     } }
/*     */   public SteamUser(SteamUserCallback callback) {
/*  28 */     super(SteamUserNative.createCallback(new SteamUserCallbackAdapter(callback)));
/*     */   }
/*     */   
/*     */   public SteamID getSteamID() {
/*  32 */     return new SteamID(SteamUserNative.getSteamID());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int initiateGameConnection(ByteBuffer authBlob, SteamID steamIDGameServer, int serverIP, short serverPort, boolean secure) throws SteamException {
/*  39 */     if (!authBlob.isDirect()) {
/*  40 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/*  43 */     int bytesWritten = SteamUserNative.initiateGameConnection(authBlob, authBlob.position(), authBlob.remaining(), steamIDGameServer.handle, serverIP, serverPort, secure);
/*     */ 
/*     */     
/*  46 */     if (bytesWritten > 0) {
/*  47 */       authBlob.limit(bytesWritten);
/*     */     }
/*     */     
/*  50 */     return bytesWritten;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void terminateGameConnection(int serverIP, short serverPort) {
/*  55 */     SteamUserNative.terminateGameConnection(serverIP, serverPort);
/*     */   }
/*     */   
/*     */   public void startVoiceRecording() {
/*  59 */     SteamUserNative.startVoiceRecording();
/*     */   }
/*     */   
/*     */   public void stopVoiceRecording() {
/*  63 */     SteamUserNative.stopVoiceRecording();
/*     */   }
/*     */   
/*     */   public VoiceResult getAvailableVoice(int[] bytesAvailable) {
/*  67 */     int result = SteamUserNative.getAvailableVoice(bytesAvailable);
/*     */     
/*  69 */     return VoiceResult.byOrdinal(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoiceResult getVoice(ByteBuffer voiceData, int[] bytesWritten) throws SteamException {
/*  74 */     if (!voiceData.isDirect()) {
/*  75 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/*  78 */     int result = SteamUserNative.getVoice(voiceData, voiceData.position(), voiceData.remaining(), bytesWritten);
/*     */     
/*  80 */     return VoiceResult.byOrdinal(result);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoiceResult decompressVoice(ByteBuffer voiceData, ByteBuffer audioData, int[] bytesWritten, int desiredSampleRate) throws SteamException {
/*  85 */     if (!voiceData.isDirect()) {
/*  86 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/*  89 */     if (!audioData.isDirect()) {
/*  90 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/*  93 */     int result = SteamUserNative.decompressVoice(voiceData, voiceData
/*  94 */         .position(), voiceData.remaining(), audioData, audioData
/*  95 */         .position(), audioData.remaining(), bytesWritten, desiredSampleRate);
/*     */ 
/*     */     
/*  98 */     return VoiceResult.byOrdinal(result);
/*     */   }
/*     */   
/*     */   public int getVoiceOptimalSampleRate() {
/* 102 */     return SteamUserNative.getVoiceOptimalSampleRate();
/*     */   }
/*     */ 
/*     */   
/*     */   public SteamAuthTicket getAuthSessionTicket(ByteBuffer authTicket, int[] sizeInBytes) throws SteamException {
/* 107 */     if (!authTicket.isDirect()) {
/* 108 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 111 */     int ticket = SteamUserNative.getAuthSessionTicket(authTicket, authTicket
/* 112 */         .position(), authTicket.remaining(), sizeInBytes);
/*     */     
/* 114 */     if (ticket != 0L) {
/* 115 */       authTicket.limit(sizeInBytes[0]);
/*     */     }
/*     */     
/* 118 */     return new SteamAuthTicket(ticket);
/*     */   }
/*     */ 
/*     */   
/*     */   public SteamAuth.BeginAuthSessionResult beginAuthSession(ByteBuffer authTicket, SteamID steamID) throws SteamException {
/* 123 */     if (!authTicket.isDirect()) {
/* 124 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 127 */     int result = SteamUserNative.beginAuthSession(authTicket, authTicket
/* 128 */         .position(), authTicket.remaining(), steamID.handle);
/*     */     
/* 130 */     return SteamAuth.BeginAuthSessionResult.byOrdinal(result);
/*     */   }
/*     */   
/*     */   public void endAuthSession(SteamID steamID) {
/* 134 */     SteamUserNative.endAuthSession(steamID.handle);
/*     */   }
/*     */   
/*     */   public void cancelAuthTicket(SteamAuthTicket authTicket) {
/* 138 */     SteamUserNative.cancelAuthTicket((int)authTicket.handle);
/*     */   }
/*     */   
/*     */   public SteamAuth.UserHasLicenseForAppResult userHasLicenseForApp(SteamID steamID, int appID) {
/* 142 */     return SteamAuth.UserHasLicenseForAppResult.byOrdinal(
/* 143 */         SteamUserNative.userHasLicenseForApp(steamID.handle, appID));
/*     */   }
/*     */ 
/*     */   
/*     */   public SteamAPICall requestEncryptedAppTicket(ByteBuffer dataToInclude) throws SteamException {
/* 148 */     if (!dataToInclude.isDirect()) {
/* 149 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 152 */     return new SteamAPICall(SteamUserNative.requestEncryptedAppTicket(this.callback, dataToInclude, dataToInclude
/* 153 */           .position(), dataToInclude.remaining()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getEncryptedAppTicket(ByteBuffer ticket, int[] sizeInBytes) throws SteamException {
/* 158 */     if (!ticket.isDirect()) {
/* 159 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 162 */     return SteamUserNative.getEncryptedAppTicket(ticket, ticket.position(), ticket.remaining(), sizeInBytes);
/*     */   }
/*     */   
/*     */   public boolean isBehindNAT() {
/* 166 */     return SteamUserNative.isBehindNAT();
/*     */   }
/*     */   
/*     */   public void advertiseGame(SteamID steamIDGameServer, int serverIP, short serverPort) {
/* 170 */     SteamUserNative.advertiseGame(steamIDGameServer.handle, serverIP, serverPort);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */