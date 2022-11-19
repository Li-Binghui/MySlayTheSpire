package com.codedisaster.steamworks;

public interface SteamUserCallback {
  void onAuthSessionTicket(SteamAuthTicket paramSteamAuthTicket, SteamResult paramSteamResult);
  
  void onValidateAuthTicket(SteamID paramSteamID1, SteamAuth.AuthSessionResponse paramAuthSessionResponse, SteamID paramSteamID2);
  
  void onMicroTxnAuthorization(int paramInt, long paramLong, boolean paramBoolean);
  
  void onEncryptedAppTicket(SteamResult paramSteamResult);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUserCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */