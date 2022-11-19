package com.gikk.twirk.types.users;

import com.gikk.twirk.enums.USER_TYPE;
import com.gikk.twirk.types.AbstractType;

public interface Userstate extends AbstractType {
  int getColor();
  
  String getDisplayName();
  
  boolean isMod();
  
  boolean isSub();
  
  boolean isTurbo();
  
  USER_TYPE getUserType();
  
  int[] getEmoteSets();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\users\Userstate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */