package com.gikk.twirk.types.usernotice;

import com.gikk.twirk.types.AbstractEmoteMessage;
import com.gikk.twirk.types.usernotice.subtype.Raid;
import com.gikk.twirk.types.usernotice.subtype.Ritual;
import com.gikk.twirk.types.usernotice.subtype.Subscription;
import java.util.Optional;

public interface Usernotice extends AbstractEmoteMessage {
  String getMessage();
  
  String getSystemMessage();
  
  boolean isSubscription();
  
  Optional<Subscription> getSubscription();
  
  boolean isRaid();
  
  Optional<Raid> getRaid();
  
  boolean isRitual();
  
  Optional<Ritual> getRitual();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\Usernotice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */