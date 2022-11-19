package com.codedisaster.steamworks;

public interface SteamFriendsCallback {
  void onSetPersonaNameResponse(boolean paramBoolean1, boolean paramBoolean2, SteamResult paramSteamResult);
  
  void onPersonaStateChange(SteamID paramSteamID, SteamFriends.PersonaChange paramPersonaChange);
  
  void onGameOverlayActivated(boolean paramBoolean);
  
  void onGameLobbyJoinRequested(SteamID paramSteamID1, SteamID paramSteamID2);
  
  void onAvatarImageLoaded(SteamID paramSteamID, int paramInt1, int paramInt2, int paramInt3);
  
  void onFriendRichPresenceUpdate(SteamID paramSteamID, int paramInt);
  
  void onGameRichPresenceJoinRequested(SteamID paramSteamID, String paramString);
  
  void onGameServerChangeRequested(String paramString1, String paramString2);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamFriendsCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */