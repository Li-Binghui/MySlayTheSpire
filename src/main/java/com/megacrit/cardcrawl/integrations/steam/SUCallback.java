package com.megacrit.cardcrawl.integrations.steam;

import com.codedisaster.steamworks.*;

public class SUCallback implements SteamUserCallback {
  public void onAuthSessionTicket(SteamAuthTicket authTicket, SteamResult result) {}
  
  public void onValidateAuthTicket(SteamID steamID, SteamAuth.AuthSessionResponse authSessionResponse, SteamID ownerSteamID) {}
  
  public void onMicroTxnAuthorization(int appID, long orderID, boolean authorized) {}
  
  public void onEncryptedAppTicket(SteamResult result) {}
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\steam\SUCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */