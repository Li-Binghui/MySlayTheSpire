package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

final class SteamHTTPNative {
  static native long createCallback(SteamHTTPCallbackAdapter paramSteamHTTPCallbackAdapter);
  
  static native long createHTTPRequest(boolean paramBoolean, int paramInt, String paramString);
  
  static native boolean setHTTPRequestContextValue(boolean paramBoolean, long paramLong1, long paramLong2);
  
  static native boolean setHTTPRequestNetworkActivityTimeout(boolean paramBoolean, long paramLong, int paramInt);
  
  static native boolean setHTTPRequestHeaderValue(boolean paramBoolean, long paramLong, String paramString1, String paramString2);
  
  static native boolean setHTTPRequestGetOrPostParameter(boolean paramBoolean, long paramLong, String paramString1, String paramString2);
  
  static native long sendHTTPRequest(boolean paramBoolean, long paramLong1, long paramLong2);
  
  static native long sendHTTPRequestAndStreamResponse(boolean paramBoolean, long paramLong);
  
  static native int getHTTPResponseHeaderSize(boolean paramBoolean, long paramLong, String paramString);
  
  static native boolean getHTTPResponseHeaderValue(boolean paramBoolean, long paramLong, String paramString, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native int getHTTPResponseBodySize(boolean paramBoolean, long paramLong);
  
  static native boolean getHTTPResponseBodyData(boolean paramBoolean, long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native boolean getHTTPStreamingResponseBodyData(boolean paramBoolean, long paramLong, int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3);
  
  static native boolean releaseHTTPRequest(boolean paramBoolean, long paramLong);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamHTTPNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */