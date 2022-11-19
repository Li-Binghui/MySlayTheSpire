package com.codedisaster.steamworks;

final class SteamControllerNative {
  static native boolean init();
  
  static native boolean shutdown();
  
  static native void runFrame();
  
  static native int getConnectedControllers(long[] paramArrayOflong);
  
  static native boolean showBindingPanel(long paramLong);
  
  static native long getActionSetHandle(String paramString);
  
  static native void activateActionSet(long paramLong1, long paramLong2);
  
  static native long getCurrentActionSet(long paramLong);
  
  static native long getDigitalActionHandle(String paramString);
  
  static native void getDigitalActionData(long paramLong1, long paramLong2, SteamControllerDigitalActionData paramSteamControllerDigitalActionData);
  
  static native int getDigitalActionOrigins(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfint);
  
  static native long getAnalogActionHandle(String paramString);
  
  static native void getAnalogActionData(long paramLong1, long paramLong2, SteamControllerAnalogActionData paramSteamControllerAnalogActionData);
  
  static native int getAnalogActionOrigins(long paramLong1, long paramLong2, long paramLong3, int[] paramArrayOfint);
  
  static native void stopAnalogActionMomentum(long paramLong1, long paramLong2);
  
  static native void triggerHapticPulse(long paramLong, int paramInt1, int paramInt2);
  
  static native void triggerRepeatedHapticPulse(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  static native void triggerVibration(long paramLong, short paramShort1, short paramShort2);
  
  static native void setLEDColor(long paramLong, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt);
  
  static native int getGamepadIndexForController(long paramLong);
  
  static native long getControllerForGamepadIndex(int paramInt);
  
  static native void getMotionData(long paramLong, float[] paramArrayOffloat);
  
  static native String getStringForActionOrigin(int paramInt);
  
  static native String getGlyphForActionOrigin(int paramInt);
  
  static native int getInputTypeForHandle(long paramLong);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamControllerNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */