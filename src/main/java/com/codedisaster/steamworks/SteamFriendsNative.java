package com.codedisaster.steamworks;

final class SteamFriendsNative {
  static native long createCallback(SteamFriendsCallbackAdapter paramSteamFriendsCallbackAdapter);
  
  static native String getPersonaName();
  
  static native long setPersonaName(long paramLong, String paramString);
  
  static native int getPersonaState();
  
  static native int getFriendCount(int paramInt);
  
  static native long getFriendByIndex(int paramInt1, int paramInt2);
  
  static native int getFriendRelationship(long paramLong);
  
  static native int getFriendPersonaState(long paramLong);
  
  static native String getFriendPersonaName(long paramLong);
  
  static native boolean getFriendGamePlayed(long paramLong, SteamFriends.FriendGameInfo paramFriendGameInfo);
  
  static native void setInGameVoiceSpeaking(long paramLong, boolean paramBoolean);
  
  static native int getSmallFriendAvatar(long paramLong);
  
  static native int getMediumFriendAvatar(long paramLong);
  
  static native int getLargeFriendAvatar(long paramLong);
  
  static native boolean requestUserInformation(long paramLong, boolean paramBoolean);
  
  static native void activateGameOverlay(String paramString);
  
  static native void activateGameOverlayToUser(String paramString, long paramLong);
  
  static native void activateGameOverlayToWebPage(String paramString, int paramInt);
  
  static native void activateGameOverlayToStore(int paramInt1, int paramInt2);
  
  static native void setPlayedWith(long paramLong);
  
  static native void activateGameOverlayInviteDialog(long paramLong);
  
  static native boolean setRichPresence(String paramString1, String paramString2);
  
  static native void clearRichPresence();
  
  static native String getFriendRichPresence(long paramLong, String paramString);
  
  static native int getFriendRichPresenceKeyCount(long paramLong);
  
  static native String getFriendRichPresenceKeyByIndex(long paramLong, int paramInt);
  
  static native void requestFriendRichPresence(long paramLong);
  
  static native boolean inviteUserToGame(long paramLong, String paramString);
  
  static native int getCoplayFriendCount();
  
  static native long getCoplayFriend(int paramInt);
  
  static native int getFriendCoplayTime(long paramLong);
  
  static native int getFriendCoplayGame(long paramLong);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamFriendsNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */