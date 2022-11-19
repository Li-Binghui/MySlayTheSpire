package com.gikk.twirk.events;

import com.gikk.twirk.types.clearChat.ClearChat;
import com.gikk.twirk.types.hostTarget.HostTarget;
import com.gikk.twirk.types.mode.Mode;
import com.gikk.twirk.types.notice.Notice;
import com.gikk.twirk.types.roomstate.Roomstate;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.usernotice.Usernotice;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.types.users.Userstate;
import java.util.Collection;

public interface TwirkListener {
  default void onAnything(String unformatedMessage) {}
  
  default void onPrivMsg(TwitchUser sender, TwitchMessage message) {}
  
  default void onWhisper(TwitchUser sender, TwitchMessage message) {}
  
  default void onJoin(String joinedNick) {}
  
  default void onPart(String partedNick) {}
  
  default void onConnect() {}
  
  default void onReconnect() {}
  
  default void onDisconnect() {}
  
  default void onNotice(Notice notice) {}
  
  default void onHost(HostTarget hostNotice) {}
  
  default void onMode(Mode mode) {}
  
  default void onUserstate(Userstate userstate) {}
  
  default void onRoomstate(Roomstate roomstate) {}
  
  default void onClearChat(ClearChat clearChat) {}
  
  default void onNamesList(Collection<String> namesList) {}
  
  default void onUsernotice(TwitchUser user, Usernotice usernotice) {}
  
  default void onUnknown(String unformatedMessage) {}
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\events\TwirkListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */