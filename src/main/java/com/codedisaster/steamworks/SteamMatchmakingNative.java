package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

final class SteamMatchmakingNative {
  static native long createCallback(SteamMatchmakingCallbackAdapter paramSteamMatchmakingCallbackAdapter);
  
  static native int getFavoriteGameCount();
  
  static native boolean getFavoriteGame(int paramInt, int[] paramArrayOfint1, int[] paramArrayOfint2, short[] paramArrayOfshort1, short[] paramArrayOfshort2, int[] paramArrayOfint3, int[] paramArrayOfint4);
  
  static native int addFavoriteGame(int paramInt1, int paramInt2, short paramShort1, short paramShort2, int paramInt3, int paramInt4);
  
  static native boolean removeFavoriteGame(int paramInt1, int paramInt2, short paramShort1, short paramShort2, int paramInt3);
  
  static native long requestLobbyList(long paramLong);
  
  static native void addRequestLobbyListStringFilter(String paramString1, String paramString2, int paramInt);
  
  static native void addRequestLobbyListNumericalFilter(String paramString, int paramInt1, int paramInt2);
  
  static native void addRequestLobbyListNearValueFilter(String paramString, int paramInt);
  
  static native void addRequestLobbyListFilterSlotsAvailable(int paramInt);
  
  static native void addRequestLobbyListDistanceFilter(int paramInt);
  
  static native void addRequestLobbyListResultCountFilter(int paramInt);
  
  static native void addRequestLobbyListCompatibleMembersFilter(long paramLong);
  
  static native long getLobbyByIndex(int paramInt);
  
  static native long createLobby(long paramLong, int paramInt1, int paramInt2);
  
  static native long joinLobby(long paramLong1, long paramLong2);
  
  static native void leaveLobby(long paramLong);
  
  static native boolean inviteUserToLobby(long paramLong1, long paramLong2);
  
  static native int getNumLobbyMembers(long paramLong);
  
  static native long getLobbyMemberByIndex(long paramLong, int paramInt);
  
  static native String getLobbyData(long paramLong, String paramString);
  
  static native boolean setLobbyData(long paramLong, String paramString1, String paramString2);
  
  static native String getLobbyMemberData(long paramLong1, long paramLong2, String paramString);
  
  static native void setLobbyMemberData(long paramLong, String paramString1, String paramString2);
  
  static native int getLobbyDataCount(long paramLong);
  
  static native boolean getLobbyDataByIndex(long paramLong, int paramInt, SteamMatchmakingKeyValuePair paramSteamMatchmakingKeyValuePair);
  
  static native boolean deleteLobbyData(long paramLong, String paramString);
  
  static native boolean sendLobbyChatMsg(long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native boolean sendLobbyChatMsg(long paramLong, String paramString);
  
  static native int getLobbyChatEntry(long paramLong, int paramInt1, SteamMatchmaking.ChatEntry paramChatEntry, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3);
  
  static native boolean requestLobbyData(long paramLong);
  
  static native void setLobbyGameServer(long paramLong1, int paramInt, short paramShort, long paramLong2);
  
  static native boolean getLobbyGameServer(long paramLong, int[] paramArrayOfint, short[] paramArrayOfshort, long[] paramArrayOflong);
  
  static native boolean setLobbyMemberLimit(long paramLong, int paramInt);
  
  static native int getLobbyMemberLimit(long paramLong);
  
  static native boolean setLobbyType(long paramLong, int paramInt);
  
  static native boolean setLobbyJoinable(long paramLong, boolean paramBoolean);
  
  static native long getLobbyOwner(long paramLong);
  
  static native boolean setLobbyOwner(long paramLong1, long paramLong2);
  
  static native boolean setLinkedLobby(long paramLong1, long paramLong2);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */