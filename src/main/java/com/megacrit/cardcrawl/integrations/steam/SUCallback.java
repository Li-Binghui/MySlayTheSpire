package com.megacrit.cardcrawl.integrations.steam;

import com.codedisaster.steamworks.SteamAuth;
import com.codedisaster.steamworks.SteamAuthTicket;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUserCallback;

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