package net.arikia.dev.drpc.callbacks;

import com.sun.jna.Callback;
import net.arikia.dev.drpc.DiscordUser;

public interface ReadyCallback extends Callback {
  void apply(DiscordUser paramDiscordUser);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\net\arikia\dev\drpc\callbacks\ReadyCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */