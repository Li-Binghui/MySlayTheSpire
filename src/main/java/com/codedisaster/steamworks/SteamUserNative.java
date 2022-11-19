package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

final class SteamUserNative {
  static native long createCallback(SteamUserCallbackAdapter paramSteamUserCallbackAdapter);
  
  static native long getSteamID();
  
  static native int initiateGameConnection(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, long paramLong, int paramInt3, short paramShort, boolean paramBoolean);
  
  static native void terminateGameConnection(int paramInt, short paramShort);
  
  static native void startVoiceRecording();
  
  static native void stopVoiceRecording();
  
  static native int getAvailableVoice(int[] paramArrayOfint);
  
  static native int getVoice(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  static native int decompressVoice(ByteBuffer paramByteBuffer1, int paramInt1, int paramInt2, ByteBuffer paramByteBuffer2, int paramInt3, int paramInt4, int[] paramArrayOfint, int paramInt5);
  
  static native int getVoiceOptimalSampleRate();
  
  static native int getAuthSessionTicket(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  static native int beginAuthSession(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, long paramLong);
  
  static native void endAuthSession(long paramLong);
  
  static native void cancelAuthTicket(int paramInt);
  
  static native int userHasLicenseForApp(long paramLong, int paramInt);
  
  static native long requestEncryptedAppTicket(long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native boolean getEncryptedAppTicket(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int[] paramArrayOfint);
  
  static native boolean isBehindNAT();
  
  static native void advertiseGame(long paramLong, int paramInt, short paramShort);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUserNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */