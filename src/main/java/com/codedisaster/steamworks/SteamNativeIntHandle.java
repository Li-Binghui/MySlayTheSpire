/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public abstract class SteamNativeIntHandle
/*    */ {
/*    */   int handle;
/*    */   
/*    */   SteamNativeIntHandle(int handle) {
/*  8 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T extends SteamNativeIntHandle> int getNativeHandle(T handle) {
/* 15 */     return ((SteamNativeIntHandle)handle).handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 20 */     return Integer.valueOf(this.handle).hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object other) {
/* 25 */     if (other instanceof SteamNativeIntHandle) {
/* 26 */       return (this.handle == ((SteamNativeIntHandle)other).handle);
/*    */     }
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     return Integer.toHexString(this.handle);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamNativeIntHandle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */