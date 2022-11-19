/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public abstract class SteamNativeHandle
/*    */ {
/*    */   long handle;
/*    */   
/*    */   SteamNativeHandle(long handle) {
/*  8 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T extends SteamNativeHandle> long getNativeHandle(T handle) {
/* 18 */     return ((SteamNativeHandle)handle).handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 23 */     return Long.valueOf(this.handle).hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object other) {
/* 28 */     if (other instanceof SteamNativeHandle) {
/* 29 */       return (this.handle == ((SteamNativeHandle)other).handle);
/*    */     }
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     return Long.toHexString(this.handle);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamNativeHandle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */