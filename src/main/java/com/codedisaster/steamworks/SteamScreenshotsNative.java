package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

final class SteamScreenshotsNative {
  static native long createCallback(SteamScreenshotsCallbackAdapter paramSteamScreenshotsCallbackAdapter);
  
  static native int writeScreenshot(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3);
  
  static native int addScreenshotToLibrary(String paramString1, String paramString2, int paramInt1, int paramInt2);
  
  static native void triggerScreenshot();
  
  static native void hookScreenshots(boolean paramBoolean);
  
  static native boolean setLocation(int paramInt, String paramString);
  
  static native boolean tagUser(int paramInt, long paramLong);
  
  static native boolean tagPublishedFile(int paramInt, long paramLong);
  
  static native boolean isScreenshotsHooked();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamScreenshotsNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */