package com.gikk.twirk.types;

import com.gikk.twirk.types.emote.Emote;

import java.util.List;

public interface AbstractEmoteMessage extends AbstractType {
  boolean hasEmotes();
  
  List<Emote> getEmotes();
  
  long getSentTimestamp();
  
  long getRoomID();
  
  String getMessageID();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\AbstractEmoteMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */