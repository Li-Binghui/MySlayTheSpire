/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SteamApps
/*    */   extends SteamInterface
/*    */ {
/*    */   public boolean isSubscribed() {
/* 11 */     return SteamAppsNative.isSubscribed();
/*    */   }
/*    */   
/*    */   public boolean isLowViolence() {
/* 15 */     return SteamAppsNative.isLowViolence();
/*    */   }
/*    */   
/*    */   public boolean isCybercafe() {
/* 19 */     return SteamAppsNative.isCybercafe();
/*    */   }
/*    */   
/*    */   public boolean isVACBanned() {
/* 23 */     return SteamAppsNative.isVACBanned();
/*    */   }
/*    */   
/*    */   public String getCurrentGameLanguage() {
/* 27 */     return SteamAppsNative.getCurrentGameLanguage();
/*    */   }
/*    */   
/*    */   public String getAvailableGameLanguages() {
/* 31 */     return SteamAppsNative.getAvailableGameLanguages();
/*    */   }
/*    */   
/*    */   public boolean isSubscribedApp(int appID) {
/* 35 */     return SteamAppsNative.isSubscribedApp(appID);
/*    */   }
/*    */   
/*    */   public boolean isDlcInstalled(int appID) {
/* 39 */     return SteamAppsNative.isDlcInstalled(appID);
/*    */   }
/*    */   
/*    */   public int getEarliestPurchaseUnixTime(int appID) {
/* 43 */     return SteamAppsNative.getEarliestPurchaseUnixTime(appID);
/*    */   }
/*    */   
/*    */   public boolean isSubscribedFromFreeWeekend() {
/* 47 */     return SteamAppsNative.isSubscribedFromFreeWeekend();
/*    */   }
/*    */   
/*    */   public int getDLCCount() {
/* 51 */     return SteamAppsNative.getDLCCount();
/*    */   }
/*    */   
/*    */   public void installDLC(int appID) {
/* 55 */     SteamAppsNative.installDLC(appID);
/*    */   }
/*    */   
/*    */   public void uninstallDLC(int appID) {
/* 59 */     SteamAppsNative.uninstallDLC(appID);
/*    */   }
/*    */   
/*    */   public SteamID getAppOwner() {
/* 63 */     return new SteamID(SteamAppsNative.getAppOwner());
/*    */   }
/*    */   
/*    */   public int getAppBuildId() {
/* 67 */     return SteamAppsNative.getAppBuildId();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamApps.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */