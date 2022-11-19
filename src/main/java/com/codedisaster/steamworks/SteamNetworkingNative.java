package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

final class SteamNetworkingNative {
  static native long createCallback(SteamNetworkingCallbackAdapter paramSteamNetworkingCallbackAdapter);
  
  static native boolean sendP2PPacket(boolean paramBoolean, long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  static native boolean isP2PPacketAvailable(boolean paramBoolean, int[] paramArrayOfint, int paramInt);
  
  static native boolean readP2PPacket(boolean paramBoolean, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int[] paramArrayOfint, long[] paramArrayOflong, int paramInt3);
  
  static native boolean acceptP2PSessionWithUser(boolean paramBoolean, long paramLong);
  
  static native boolean closeP2PSessionWithUser(boolean paramBoolean, long paramLong);
  
  static native boolean closeP2PChannelWithUser(boolean paramBoolean, long paramLong, int paramInt);
  
  static native boolean getP2PSessionState(boolean paramBoolean, long paramLong, SteamNetworking.P2PSessionState paramP2PSessionState);
  
  static native boolean allowP2PPacketRelay(boolean paramBoolean1, boolean paramBoolean2);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamNetworkingNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */