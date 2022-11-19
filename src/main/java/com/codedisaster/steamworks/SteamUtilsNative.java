package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

final class SteamUtilsNative {
  static native long createCallback(SteamUtilsCallbackAdapter paramSteamUtilsCallbackAdapter);
  
  static native int getSecondsSinceAppActive();
  
  static native int getSecondsSinceComputerActive();
  
  static native int getConnectedUniverse();
  
  static native int getServerRealTime();
  
  static native String getIPCountry();
  
  static native int getImageWidth(int paramInt);
  
  static native int getImageHeight(int paramInt);
  
  static native boolean getImageSize(int paramInt, int[] paramArrayOfint);
  
  static native boolean getImageRGBA(int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3);
  
  static native int getAppID();
  
  static native void setOverlayNotificationPosition(int paramInt);
  
  static native boolean isAPICallCompleted(long paramLong, boolean[] paramArrayOfboolean);
  
  static native int getAPICallFailureReason(long paramLong);
  
  static native void enableWarningMessageHook(long paramLong, boolean paramBoolean);
  
  static native boolean isOverlayEnabled();
  
  static native boolean isSteamRunningOnSteamDeck();
  
  static native boolean showFloatingGamepadTextInput(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  static native boolean dismissFloatingGamepadTextInput();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUtilsNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */