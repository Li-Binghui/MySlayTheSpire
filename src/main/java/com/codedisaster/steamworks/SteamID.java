/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public class SteamID
/*    */   extends SteamNativeHandle {
/*  5 */   private static final long InvalidSteamID = getInvalidSteamID();
/*    */   
/*    */   public SteamID() {
/*  8 */     super(InvalidSteamID);
/*    */   }
/*    */   
/*    */   public SteamID(SteamID steamID) {
/* 12 */     super(steamID.handle);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SteamID(long id) {
/* 24 */     super(id);
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 28 */     return isValid(this.handle);
/*    */   }
/*    */   
/*    */   public int getAccountID() {
/* 32 */     return (int)(this.handle % 4294967296L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static SteamID createFromNativeHandle(long id) {
/* 40 */     return new SteamID(id);
/*    */   }
/*    */   
/*    */   private static native boolean isValid(long paramLong);
/*    */   
/*    */   private static native long getInvalidSteamID();
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamID.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */