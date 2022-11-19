package com.codedisaster.steamworks;

public interface SteamMatchmakingCallback {
  void onFavoritesListChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, int paramInt6);
  
  void onLobbyInvite(SteamID paramSteamID1, SteamID paramSteamID2, long paramLong);
  
  void onLobbyEnter(SteamID paramSteamID, int paramInt, boolean paramBoolean, SteamMatchmaking.ChatRoomEnterResponse paramChatRoomEnterResponse);
  
  void onLobbyDataUpdate(SteamID paramSteamID1, SteamID paramSteamID2, boolean paramBoolean);
  
  void onLobbyChatUpdate(SteamID paramSteamID1, SteamID paramSteamID2, SteamID paramSteamID3, SteamMatchmaking.ChatMemberStateChange paramChatMemberStateChange);
  
  void onLobbyChatMessage(SteamID paramSteamID1, SteamID paramSteamID2, SteamMatchmaking.ChatEntryType paramChatEntryType, int paramInt);
  
  void onLobbyGameCreated(SteamID paramSteamID1, SteamID paramSteamID2, int paramInt, short paramShort);
  
  void onLobbyMatchList(int paramInt);
  
  void onLobbyKicked(SteamID paramSteamID1, SteamID paramSteamID2, boolean paramBoolean);
  
  void onLobbyCreated(SteamResult paramSteamResult, SteamID paramSteamID);
  
  void onFavoritesListAccountsUpdated(SteamResult paramSteamResult);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */