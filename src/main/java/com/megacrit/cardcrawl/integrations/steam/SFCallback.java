package com.megacrit.cardcrawl.integrations.steam;

import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;

public class SFCallback implements SteamFriendsCallback {
  public void onSetPersonaNameResponse(boolean success, boolean localSuccess, SteamResult result) {}
  
  public void onPersonaStateChange(SteamID steamID, SteamFriends.PersonaChange change) {}
  
  public void onGameOverlayActivated(boolean active) {}
  
  public void onGameLobbyJoinRequested(SteamID steamIDLobby, SteamID steamIDFriend) {}
  
  public void onAvatarImageLoaded(SteamID steamID, int image, int width, int height) {}
  
  public void onFriendRichPresenceUpdate(SteamID steamIDFriend, int appID) {}
  
  public void onGameRichPresenceJoinRequested(SteamID steamIDFriend, String connect) {}
  
  public void onGameServerChangeRequested(String server, String password) {}
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\steam\SFCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */