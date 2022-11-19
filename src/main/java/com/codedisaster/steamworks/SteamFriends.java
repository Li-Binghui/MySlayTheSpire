/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class SteamFriends
/*     */   extends SteamInterface
/*     */ {
/*     */   public enum FriendRelationship {
/*   9 */     None,
/*  10 */     Blocked,
/*  11 */     Recipient,
/*  12 */     Friend,
/*  13 */     RequestInitiator,
/*  14 */     Ignored,
/*  15 */     IgnoredFriend,
/*  16 */     Suggested_DEPRECATED,
/*  17 */     Max;
/*     */     
/*  19 */     private static final FriendRelationship[] values = values();
/*     */     
/*     */     static FriendRelationship byOrdinal(int friendRelationship) {
/*  22 */       return values[friendRelationship];
/*     */     }
/*     */     static {
/*     */     
/*     */     } }
/*  27 */   public enum PersonaState { Offline,
/*  28 */     Online,
/*  29 */     Busy,
/*  30 */     Away,
/*  31 */     Snooze,
/*  32 */     LookingToTrade,
/*  33 */     LookingToPlay,
/*  34 */     Invisible;
/*     */     
/*  36 */     private static final PersonaState[] values = values(); static {
/*     */     
/*     */     } static PersonaState byOrdinal(int personaState) {
/*  39 */       return values[personaState];
/*     */     } }
/*     */ 
/*     */   
/*     */   public enum FriendFlags
/*     */   {
/*  45 */     None(0),
/*  46 */     Blocked(1),
/*  47 */     FriendshipRequested(2),
/*  48 */     Immediate(4),
/*  49 */     ClanMember(8),
/*  50 */     OnGameServer(16),
/*     */ 
/*     */     
/*  53 */     RequestingFriendship(128),
/*  54 */     RequestingInfo(256),
/*  55 */     Ignored(512),
/*  56 */     IgnoredFriend(1024),
/*     */     
/*  58 */     ChatMember(4096),
/*  59 */     All(65535);
/*     */     
/*     */     private final int bits;
/*     */     
/*     */     FriendFlags(int bits) {
/*  64 */       this.bits = bits;
/*     */     }
/*     */     
/*     */     static int asBits(Collection<FriendFlags> friendFlags) {
/*  68 */       int bits = 0;
/*  69 */       for (FriendFlags flags : friendFlags) {
/*  70 */         bits |= flags.bits;
/*     */       }
/*  72 */       return bits;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum PersonaChange
/*     */   {
/*  78 */     Name(1),
/*  79 */     Status(2),
/*  80 */     ComeOnline(4),
/*  81 */     GoneOffline(8),
/*  82 */     GamePlayed(16),
/*  83 */     GameServer(32),
/*  84 */     Avatar(64),
/*  85 */     JoinedSource(128),
/*  86 */     LeftSource(256),
/*  87 */     RelationshipChanged(512),
/*  88 */     NameFirstSet(1024),
/*  89 */     Broadcast(2048),
/*  90 */     Nickname(4096),
/*  91 */     SteamLevel(8192),
/*  92 */     RichPresence(16384);
/*     */     
/*     */     private final int bits;
/*     */     
/*     */     PersonaChange(int bits) {
/*  97 */       this.bits = bits;
/*     */     }
/*     */     
/*     */     static boolean isSet(PersonaChange value, int bitMask) {
/* 101 */       return ((value.bits & bitMask) == value.bits);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FriendGameInfo
/*     */   {
/*     */     private long gameID;
/*     */     private int gameIP;
/*     */     private short gamePort;
/*     */     private short queryPort;
/*     */     private long steamIDLobby;
/*     */     
/*     */     public long getGameID() {
/* 114 */       return this.gameID;
/*     */     }
/*     */     
/*     */     public int getGameIP() {
/* 118 */       return this.gameIP;
/*     */     }
/*     */     
/*     */     public short getGamePort() {
/* 122 */       return this.gamePort;
/*     */     }
/*     */     
/*     */     public short getQueryPort() {
/* 126 */       return this.queryPort;
/*     */     }
/*     */     
/*     */     public SteamID getSteamIDLobby() {
/* 130 */       return new SteamID(this.steamIDLobby);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum OverlayDialog
/*     */   {
/* 136 */     Friends("Friends"),
/* 137 */     Community("Community"),
/* 138 */     Players("Players"),
/* 139 */     Settings("Settings"),
/* 140 */     OfficialGameGroup("OfficialGameGroup"),
/* 141 */     Stats("Stats"),
/* 142 */     Achievements("Achievements");
/*     */     
/*     */     private final String id;
/*     */     
/*     */     OverlayDialog(String id) {
/* 147 */       this.id = id;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum OverlayToUserDialog
/*     */   {
/* 153 */     SteamID("steamid"),
/* 154 */     Chat("chat"),
/* 155 */     JoinTrade("jointrade"),
/* 156 */     Stats("stats"),
/* 157 */     Achievements("achievements"),
/* 158 */     FriendAdd("friendadd"),
/* 159 */     FriendRemove("friendremove"),
/* 160 */     FriendRequestAccept("friendrequestaccept"),
/* 161 */     FriendRequestIgnore("friendrequestignore");
/*     */     
/*     */     private final String id;
/*     */     
/*     */     OverlayToUserDialog(String id) {
/* 166 */       this.id = id;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum OverlayToStoreFlag
/*     */   {
/* 172 */     None,
/* 173 */     AddToCart,
/* 174 */     AddToCartAndShow;
/*     */   }
/*     */   
/*     */   public enum OverlayToWebPageMode {
/* 178 */     Default,
/* 179 */     Modal;
/*     */   }
/*     */   
/*     */   public SteamFriends(SteamFriendsCallback callback) {
/* 183 */     super(SteamFriendsNative.createCallback(new SteamFriendsCallbackAdapter(callback)));
/*     */   }
/*     */   
/*     */   public String getPersonaName() {
/* 187 */     return SteamFriendsNative.getPersonaName();
/*     */   }
/*     */   
/*     */   public SteamAPICall setPersonaName(String personaName) {
/* 191 */     return new SteamAPICall(SteamFriendsNative.setPersonaName(this.callback, personaName));
/*     */   }
/*     */   
/*     */   public PersonaState getPersonaState() {
/* 195 */     return PersonaState.byOrdinal(SteamFriendsNative.getPersonaState());
/*     */   }
/*     */   
/*     */   public int getFriendCount(FriendFlags friendFlag) {
/* 199 */     return SteamFriendsNative.getFriendCount(friendFlag.bits);
/*     */   }
/*     */   
/*     */   public int getFriendCount(Collection<FriendFlags> friendFlags) {
/* 203 */     return SteamFriendsNative.getFriendCount(FriendFlags.asBits(friendFlags));
/*     */   }
/*     */   
/*     */   public SteamID getFriendByIndex(int friend, FriendFlags friendFlag) {
/* 207 */     return new SteamID(SteamFriendsNative.getFriendByIndex(friend, friendFlag.bits));
/*     */   }
/*     */   
/*     */   public SteamID getFriendByIndex(int friend, Collection<FriendFlags> friendFlags) {
/* 211 */     return new SteamID(SteamFriendsNative.getFriendByIndex(friend, FriendFlags.asBits(friendFlags)));
/*     */   }
/*     */   
/*     */   public FriendRelationship getFriendRelationship(SteamID steamIDFriend) {
/* 215 */     return FriendRelationship.byOrdinal(SteamFriendsNative.getFriendRelationship(steamIDFriend.handle));
/*     */   }
/*     */   
/*     */   public PersonaState getFriendPersonaState(SteamID steamIDFriend) {
/* 219 */     return PersonaState.byOrdinal(SteamFriendsNative.getFriendPersonaState(steamIDFriend.handle));
/*     */   }
/*     */   
/*     */   public String getFriendPersonaName(SteamID steamIDFriend) {
/* 223 */     return SteamFriendsNative.getFriendPersonaName(steamIDFriend.handle);
/*     */   }
/*     */   
/*     */   public boolean getFriendGamePlayed(SteamID steamIDFriend, FriendGameInfo friendGameInfo) {
/* 227 */     return SteamFriendsNative.getFriendGamePlayed(steamIDFriend.handle, friendGameInfo);
/*     */   }
/*     */   
/*     */   public void setInGameVoiceSpeaking(SteamID steamID, boolean speaking) {
/* 231 */     SteamFriendsNative.setInGameVoiceSpeaking(steamID.handle, speaking);
/*     */   }
/*     */   
/*     */   public int getSmallFriendAvatar(SteamID steamID) {
/* 235 */     return SteamFriendsNative.getSmallFriendAvatar(steamID.handle);
/*     */   }
/*     */   
/*     */   public int getMediumFriendAvatar(SteamID steamID) {
/* 239 */     return SteamFriendsNative.getMediumFriendAvatar(steamID.handle);
/*     */   }
/*     */   
/*     */   public int getLargeFriendAvatar(SteamID steamID) {
/* 243 */     return SteamFriendsNative.getLargeFriendAvatar(steamID.handle);
/*     */   }
/*     */   
/*     */   public boolean requestUserInformation(SteamID steamID, boolean requireNameOnly) {
/* 247 */     return SteamFriendsNative.requestUserInformation(steamID.handle, requireNameOnly);
/*     */   }
/*     */   
/*     */   public void activateGameOverlay(OverlayDialog dialog) {
/* 251 */     SteamFriendsNative.activateGameOverlay(dialog.id);
/*     */   }
/*     */   
/*     */   public void activateGameOverlayToUser(OverlayToUserDialog dialog, SteamID steamID) {
/* 255 */     SteamFriendsNative.activateGameOverlayToUser(dialog.id, steamID.handle);
/*     */   }
/*     */   
/*     */   public void activateGameOverlayToWebPage(String url, OverlayToWebPageMode mode) {
/* 259 */     SteamFriendsNative.activateGameOverlayToWebPage(url, mode.ordinal());
/*     */   }
/*     */   
/*     */   public void activateGameOverlayToStore(int appID, OverlayToStoreFlag flag) {
/* 263 */     SteamFriendsNative.activateGameOverlayToStore(appID, flag.ordinal());
/*     */   }
/*     */   
/*     */   public void setPlayedWith(SteamID steamIDUserPlayedWith) {
/* 267 */     SteamFriendsNative.setPlayedWith(steamIDUserPlayedWith.handle);
/*     */   }
/*     */   
/*     */   public void activateGameOverlayInviteDialog(SteamID steamIDLobby) {
/* 271 */     SteamFriendsNative.activateGameOverlayInviteDialog(steamIDLobby.handle);
/*     */   }
/*     */   
/*     */   public boolean setRichPresence(String key, String value) {
/* 275 */     return SteamFriendsNative.setRichPresence(key, (value != null) ? value : "");
/*     */   }
/*     */   
/*     */   public void clearRichPresence() {
/* 279 */     SteamFriendsNative.clearRichPresence();
/*     */   }
/*     */   
/*     */   public String getFriendRichPresence(SteamID steamIDFriend, String key) {
/* 283 */     return SteamFriendsNative.getFriendRichPresence(steamIDFriend.handle, key);
/*     */   }
/*     */   
/*     */   public int getFriendRichPresenceKeyCount(SteamID steamIDFriend) {
/* 287 */     return SteamFriendsNative.getFriendRichPresenceKeyCount(steamIDFriend.handle);
/*     */   }
/*     */   
/*     */   public String getFriendRichPresenceKeyByIndex(SteamID steamIDFriend, int index) {
/* 291 */     return SteamFriendsNative.getFriendRichPresenceKeyByIndex(steamIDFriend.handle, index);
/*     */   }
/*     */   
/*     */   public void requestFriendRichPresence(SteamID steamIDFriend) {
/* 295 */     SteamFriendsNative.requestFriendRichPresence(steamIDFriend.handle);
/*     */   }
/*     */   
/*     */   public boolean inviteUserToGame(SteamID steamIDFriend, String connectString) {
/* 299 */     return SteamFriendsNative.inviteUserToGame(steamIDFriend.handle, connectString);
/*     */   }
/*     */   
/*     */   public int getCoplayFriendCount() {
/* 303 */     return SteamFriendsNative.getCoplayFriendCount();
/*     */   }
/*     */   
/*     */   public SteamID getCoplayFriend(int index) {
/* 307 */     return new SteamID(SteamFriendsNative.getCoplayFriend(index));
/*     */   }
/*     */   
/*     */   public int getFriendCoplayTime(SteamID steamIDFriend) {
/* 311 */     return SteamFriendsNative.getFriendCoplayTime(steamIDFriend.handle);
/*     */   }
/*     */   
/*     */   public int getFriendCoplayGame(SteamID steamIDFriend) {
/* 315 */     return SteamFriendsNative.getFriendCoplayGame(steamIDFriend.handle);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamFriends.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */