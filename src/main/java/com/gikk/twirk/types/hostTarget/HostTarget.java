package com.gikk.twirk.types.hostTarget;

import com.gikk.twirk.enums.HOSTTARGET_MODE;
import com.gikk.twirk.types.AbstractType;

public interface HostTarget extends AbstractType {
  HOSTTARGET_MODE getMode();
  
  String getTarget();
  
  int getViewerCount();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\hostTarget\HostTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */