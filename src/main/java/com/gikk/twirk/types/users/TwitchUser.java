package com.gikk.twirk.types.users;

import com.gikk.twirk.enums.USER_TYPE;

public interface TwitchUser {
  String getUserName();
  
  String getDisplayName();
  
  boolean isOwner();
  
  boolean isMod();
  
  boolean isTurbo();
  
  boolean isSub();
  
  USER_TYPE getUserType();
  
  int getColor();
  
  String[] getBadges();
  
  long getUserID();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\users\TwitchUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */