package com.codedisaster.steamworks;

final class SteamAppsNative {
  static native boolean isSubscribed();
  
  static native boolean isLowViolence();
  
  static native boolean isCybercafe();
  
  static native boolean isVACBanned();
  
  static native String getCurrentGameLanguage();
  
  static native String getAvailableGameLanguages();
  
  static native boolean isSubscribedApp(int paramInt);
  
  static native boolean isDlcInstalled(int paramInt);
  
  static native int getEarliestPurchaseUnixTime(int paramInt);
  
  static native boolean isSubscribedFromFreeWeekend();
  
  static native int getDLCCount();
  
  static native void installDLC(int paramInt);
  
  static native void uninstallDLC(int paramInt);
  
  static native long getAppOwner();
  
  static native int getAppBuildId();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamAppsNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */