/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SteamMatchmakingServerNetAdr
/*    */ {
/*    */   short connectionPort;
/*    */   short queryPort;
/*    */   int ip;
/*    */   
/*    */   SteamMatchmakingServerNetAdr() {}
/*    */   
/*    */   public SteamMatchmakingServerNetAdr(int ip, short queryPort, short connectionPort) {
/* 14 */     this.ip = ip;
/* 15 */     this.queryPort = queryPort;
/* 16 */     this.connectionPort = connectionPort;
/*    */   }
/*    */   
/*    */   public short getConnectionPort() {
/* 20 */     return this.connectionPort;
/*    */   }
/*    */   
/*    */   public short getQueryPort() {
/* 24 */     return this.queryPort;
/*    */   }
/*    */   
/*    */   public int getIP() {
/* 28 */     return this.ip;
/*    */   }
/*    */   
/*    */   public String getConnectionAddressString() {
/* 32 */     return toString(this.ip, this.connectionPort);
/*    */   }
/*    */   
/*    */   public String getQueryAddressString() {
/* 36 */     return toString(this.ip, this.queryPort);
/*    */   }
/*    */   
/*    */   private static String toString(int ip, short port) {
/* 40 */     return String.format("%d.%d.%d.%d:%d", new Object[] {
/* 41 */           Integer.valueOf(ip >> 24 & 0xFF), Integer.valueOf(ip >> 16 & 0xFF), Integer.valueOf(ip >> 8 & 0xFF), Integer.valueOf(ip & 0xFF), Short.valueOf(port)
/*    */         });
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingServerNetAdr.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */