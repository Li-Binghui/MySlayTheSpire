package com.codedisaster.steamworks;

final class SteamMatchmakingServersNative {
  static native long requestInternetServerList(int paramInt1, SteamMatchmakingKeyValuePair[] paramArrayOfSteamMatchmakingKeyValuePair, int paramInt2, long paramLong);
  
  static native long requestLANServerList(int paramInt, long paramLong);
  
  static native long requestFriendsServerList(int paramInt1, SteamMatchmakingKeyValuePair[] paramArrayOfSteamMatchmakingKeyValuePair, int paramInt2, long paramLong);
  
  static native long requestFavoritesServerList(int paramInt1, SteamMatchmakingKeyValuePair[] paramArrayOfSteamMatchmakingKeyValuePair, int paramInt2, long paramLong);
  
  static native long requestHistoryServerList(int paramInt1, SteamMatchmakingKeyValuePair[] paramArrayOfSteamMatchmakingKeyValuePair, int paramInt2, long paramLong);
  
  static native long requestSpectatorServerList(int paramInt1, SteamMatchmakingKeyValuePair[] paramArrayOfSteamMatchmakingKeyValuePair, int paramInt2, long paramLong);
  
  static native void releaseRequest(long paramLong);
  
  static native boolean getServerDetails(long paramLong, int paramInt, SteamMatchmakingGameServerItem paramSteamMatchmakingGameServerItem);
  
  static native void cancelQuery(long paramLong);
  
  static native void refreshQuery(long paramLong);
  
  static native boolean isRefreshing(long paramLong);
  
  static native int getServerCount(long paramLong);
  
  static native void refreshServer(long paramLong, int paramInt);
  
  static native int pingServer(int paramInt, short paramShort, long paramLong);
  
  static native int playerDetails(int paramInt, short paramShort, long paramLong);
  
  static native int serverRules(int paramInt, short paramShort, long paramLong);
  
  static native void cancelServerQuery(int paramInt);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingServersNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */