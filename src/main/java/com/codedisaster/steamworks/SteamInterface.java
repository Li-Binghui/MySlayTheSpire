/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ import java.nio.Buffer;
/*    */ 
/*    */ abstract class SteamInterface
/*    */ {
/*    */   protected long callback;
/*    */   
/*    */   SteamInterface() {
/* 10 */     this(0L);
/*    */   }
/*    */   
/*    */   SteamInterface(long callback) {
/* 14 */     this.callback = callback;
/*    */   }
/*    */   
/*    */   void setCallback(long callback) {
/* 18 */     this.callback = callback;
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 22 */     deleteCallback(this.callback);
/*    */   }
/*    */   
/*    */   void checkBuffer(Buffer buffer) throws SteamException {
/* 26 */     if (!buffer.isDirect()) {
/* 27 */       throw new SteamException("Direct buffer required.");
/*    */     }
/*    */   }
/*    */   
/*    */   void checkArray(byte[] array, int length) throws SteamException {
/* 32 */     if (array.length < length)
/* 33 */       throw new SteamException("Array too small, " + array.length + " found but " + length + " expected."); 
/*    */   }
/*    */   
/*    */   protected static native void deleteCallback(long paramLong);
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamInterface.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */