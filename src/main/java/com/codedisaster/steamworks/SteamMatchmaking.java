/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class SteamMatchmaking
/*     */   extends SteamInterface
/*     */ {
/*     */   public enum LobbyType {
/*   9 */     Private,
/*  10 */     FriendsOnly,
/*  11 */     Public,
/*  12 */     Invisible,
/*  13 */     PrivateUnique;
/*     */   }
/*     */   
/*     */   public enum LobbyComparison {
/*  17 */     EqualToOrLessThan(-2),
/*  18 */     LessThan(-1),
/*  19 */     Equal(0),
/*  20 */     GreaterThan(1),
/*  21 */     EqualToOrGreaterThan(2),
/*  22 */     NotEqual(3);
/*     */     
/*     */     private final int value;
/*     */     
/*     */     LobbyComparison(int value) {
/*  27 */       this.value = value;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum LobbyDistanceFilter {
/*  32 */     Close,
/*  33 */     Default,
/*  34 */     Far,
/*  35 */     Worldwide;
/*     */   }
/*     */   
/*     */   public enum ChatRoomEnterResponse {
/*  39 */     Success(1),
/*  40 */     DoesntExist(2),
/*  41 */     NotAllowed(3),
/*  42 */     Full(4),
/*  43 */     Error(5),
/*  44 */     Banned(6),
/*  45 */     Limited(7),
/*  46 */     ClanDisabled(8),
/*  47 */     CommunityBan(9),
/*  48 */     MemberBlockedYou(10),
/*  49 */     YouBlockedMember(11),
/*  50 */     RatelimitExceeded(15);
/*     */     
/*     */     private final int code;
/*  53 */     private static final ChatRoomEnterResponse[] values = values();
/*     */     
/*     */     ChatRoomEnterResponse(int code) {
/*  56 */       this.code = code;
/*     */     } static {
/*     */     
/*     */     } static ChatRoomEnterResponse byValue(int code) {
/*  60 */       for (ChatRoomEnterResponse value : values) {
/*  61 */         if (value.code == code) {
/*  62 */           return value;
/*     */         }
/*     */       } 
/*  65 */       return Error;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ChatMemberStateChange {
/*  70 */     Entered(1),
/*  71 */     Left(2),
/*  72 */     Disconnected(4),
/*  73 */     Kicked(8),
/*  74 */     Banned(16);
/*     */     
/*     */     private final int bits;
/*     */     
/*     */     ChatMemberStateChange(int bits) {
/*  79 */       this.bits = bits;
/*     */     }
/*     */     
/*     */     static boolean isSet(ChatMemberStateChange value, int bitMask) {
/*  83 */       return ((value.bits & bitMask) == value.bits);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ChatEntryType {
/*  88 */     Invalid(0),
/*  89 */     ChatMsg(1),
/*  90 */     Typing(2),
/*  91 */     InviteGame(3),
/*  92 */     Emote(4),
/*  93 */     LeftConversation(6),
/*  94 */     Entered(7),
/*  95 */     WasKicked(8),
/*  96 */     WasBanned(9),
/*  97 */     Disconnected(10),
/*  98 */     HistoricalChat(11),
/*  99 */     Reserved1(12),
/* 100 */     Reserved2(13),
/* 101 */     LinkBlocked(14);
/*     */     
/*     */     private final int code;
/* 104 */     private static final ChatEntryType[] values = values(); static {
/*     */     
/*     */     } ChatEntryType(int code) {
/* 107 */       this.code = code;
/*     */     }
/*     */     
/*     */     static ChatEntryType byValue(int code) {
/* 111 */       for (ChatEntryType value : values) {
/* 112 */         if (value.code == code) {
/* 113 */           return value;
/*     */         }
/*     */       } 
/* 116 */       return Invalid;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChatEntry
/*     */   {
/*     */     private long steamIDUser;
/*     */     private int chatEntryType;
/*     */     
/*     */     public SteamID getSteamIDUser() {
/* 126 */       return new SteamID(this.steamIDUser);
/*     */     }
/*     */     
/*     */     public ChatEntryType getChatEntryType() {
/* 130 */       return ChatEntryType.byValue(this.chatEntryType);
/*     */     }
/*     */   }
/*     */   
/*     */   public SteamMatchmaking(SteamMatchmakingCallback callback) {
/* 135 */     super(SteamMatchmakingNative.createCallback(new SteamMatchmakingCallbackAdapter(callback)));
/*     */   }
/*     */   
/*     */   public int getFavoriteGameCount() {
/* 139 */     return SteamMatchmakingNative.getFavoriteGameCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFavoriteGame(int game, int[] appID, int[] ip, short[] connPort, short[] queryPort, int[] flags, int[] lastPlayedOnServer) {
/* 144 */     return SteamMatchmakingNative.getFavoriteGame(game, appID, ip, connPort, queryPort, flags, lastPlayedOnServer);
/*     */   }
/*     */   
/*     */   public int addFavoriteGame(int appID, int ip, short connPort, short queryPort, int flags, int lastPlayedOnServer) {
/* 148 */     return SteamMatchmakingNative.addFavoriteGame(appID, ip, connPort, queryPort, flags, lastPlayedOnServer);
/*     */   }
/*     */   
/*     */   public boolean removeFavoriteGame(int appID, int ip, short connPort, short queryPort, int flags) {
/* 152 */     return SteamMatchmakingNative.removeFavoriteGame(appID, ip, connPort, queryPort, flags);
/*     */   }
/*     */   
/*     */   public SteamAPICall requestLobbyList() {
/* 156 */     return new SteamAPICall(SteamMatchmakingNative.requestLobbyList(this.callback));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRequestLobbyListStringFilter(String keyToMatch, String valueToMatch, LobbyComparison comparisonType) {
/* 162 */     SteamMatchmakingNative.addRequestLobbyListStringFilter(keyToMatch, valueToMatch, comparisonType.value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRequestLobbyListNumericalFilter(String keyToMatch, int valueToMatch, LobbyComparison comparisonType) {
/* 168 */     SteamMatchmakingNative.addRequestLobbyListNumericalFilter(keyToMatch, valueToMatch, comparisonType.value);
/*     */   }
/*     */   
/*     */   public void addRequestLobbyListNearValueFilter(String keyToMatch, int valueToBeCloseTo) {
/* 172 */     SteamMatchmakingNative.addRequestLobbyListNearValueFilter(keyToMatch, valueToBeCloseTo);
/*     */   }
/*     */   
/*     */   public void addRequestLobbyListFilterSlotsAvailable(int slotsAvailable) {
/* 176 */     SteamMatchmakingNative.addRequestLobbyListFilterSlotsAvailable(slotsAvailable);
/*     */   }
/*     */   
/*     */   public void addRequestLobbyListDistanceFilter(LobbyDistanceFilter lobbyDistanceFilter) {
/* 180 */     SteamMatchmakingNative.addRequestLobbyListDistanceFilter(lobbyDistanceFilter.ordinal());
/*     */   }
/*     */   
/*     */   public void addRequestLobbyListResultCountFilter(int maxResults) {
/* 184 */     SteamMatchmakingNative.addRequestLobbyListResultCountFilter(maxResults);
/*     */   }
/*     */   
/*     */   public void addRequestLobbyListCompatibleMembersFilter(SteamID steamIDLobby) {
/* 188 */     SteamMatchmakingNative.addRequestLobbyListCompatibleMembersFilter(steamIDLobby.handle);
/*     */   }
/*     */   
/*     */   public SteamID getLobbyByIndex(int lobby) {
/* 192 */     return new SteamID(SteamMatchmakingNative.getLobbyByIndex(lobby));
/*     */   }
/*     */   
/*     */   public SteamAPICall createLobby(LobbyType lobbyType, int maxMembers) {
/* 196 */     return new SteamAPICall(SteamMatchmakingNative.createLobby(this.callback, lobbyType.ordinal(), maxMembers));
/*     */   }
/*     */   
/*     */   public SteamAPICall joinLobby(SteamID steamIDLobby) {
/* 200 */     return new SteamAPICall(SteamMatchmakingNative.joinLobby(this.callback, steamIDLobby.handle));
/*     */   }
/*     */   
/*     */   public void leaveLobby(SteamID steamIDLobby) {
/* 204 */     SteamMatchmakingNative.leaveLobby(steamIDLobby.handle);
/*     */   }
/*     */   
/*     */   public boolean inviteUserToLobby(SteamID steamIDLobby, SteamID steamIDInvitee) {
/* 208 */     return SteamMatchmakingNative.inviteUserToLobby(steamIDLobby.handle, steamIDInvitee.handle);
/*     */   }
/*     */   
/*     */   public int getNumLobbyMembers(SteamID steamIDLobby) {
/* 212 */     return SteamMatchmakingNative.getNumLobbyMembers(steamIDLobby.handle);
/*     */   }
/*     */   
/*     */   public SteamID getLobbyMemberByIndex(SteamID steamIDLobby, int memberIndex) {
/* 216 */     return new SteamID(SteamMatchmakingNative.getLobbyMemberByIndex(steamIDLobby.handle, memberIndex));
/*     */   }
/*     */   
/*     */   public String getLobbyData(SteamID steamIDLobby, String key) {
/* 220 */     return SteamMatchmakingNative.getLobbyData(steamIDLobby.handle, key);
/*     */   }
/*     */   
/*     */   public boolean setLobbyData(SteamID steamIDLobby, String key, String value) {
/* 224 */     return SteamMatchmakingNative.setLobbyData(steamIDLobby.handle, key, value);
/*     */   }
/*     */   
/*     */   public boolean setLobbyData(SteamID steamIDLobby, SteamMatchmakingKeyValuePair keyValuePair) {
/* 228 */     return SteamMatchmakingNative.setLobbyData(steamIDLobby.handle, keyValuePair.getKey(), keyValuePair.getValue());
/*     */   }
/*     */   
/*     */   public String getLobbyMemberData(SteamID steamIDLobby, SteamID steamIDUser, String key) {
/* 232 */     return SteamMatchmakingNative.getLobbyMemberData(steamIDLobby.handle, steamIDUser.handle, key);
/*     */   }
/*     */   
/*     */   public void setLobbyMemberData(SteamID steamIDLobby, String key, String value) {
/* 236 */     SteamMatchmakingNative.setLobbyMemberData(steamIDLobby.handle, key, value);
/*     */   }
/*     */   
/*     */   public void setLobbyMemberData(SteamID steamIDLobby, SteamMatchmakingKeyValuePair keyValuePair) {
/* 240 */     SteamMatchmakingNative.setLobbyMemberData(steamIDLobby.handle, keyValuePair.getKey(), keyValuePair.getValue());
/*     */   }
/*     */   
/*     */   public int getLobbyDataCount(SteamID steamIDLobby) {
/* 244 */     return SteamMatchmakingNative.getLobbyDataCount(steamIDLobby.handle);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getLobbyDataByIndex(SteamID steamIDLobby, int lobbyDataIndex, SteamMatchmakingKeyValuePair keyValuePair) {
/* 249 */     return SteamMatchmakingNative.getLobbyDataByIndex(steamIDLobby.handle, lobbyDataIndex, keyValuePair);
/*     */   }
/*     */   
/*     */   public boolean deleteLobbyData(SteamID steamIDLobby, String key) {
/* 253 */     return SteamMatchmakingNative.deleteLobbyData(steamIDLobby.handle, key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean sendLobbyChatMsg(SteamID steamIDLobby, ByteBuffer data) throws SteamException {
/* 261 */     if (!data.isDirect()) {
/* 262 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 265 */     return SteamMatchmakingNative.sendLobbyChatMsg(steamIDLobby.handle, data, data.position(), data.remaining());
/*     */   }
/*     */   
/*     */   public boolean sendLobbyChatMsg(SteamID steamIDLobby, String data) {
/* 269 */     return SteamMatchmakingNative.sendLobbyChatMsg(steamIDLobby.handle, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLobbyChatEntry(SteamID steamIDLobby, int chatID, ChatEntry chatEntry, ByteBuffer dest) throws SteamException {
/* 279 */     if (!dest.isDirect()) {
/* 280 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 283 */     return SteamMatchmakingNative.getLobbyChatEntry(steamIDLobby.handle, chatID, chatEntry, dest, dest
/* 284 */         .position(), dest.remaining());
/*     */   }
/*     */   
/*     */   public boolean requestLobbyData(SteamID steamIDLobby) {
/* 288 */     return SteamMatchmakingNative.requestLobbyData(steamIDLobby.handle);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLobbyGameServer(SteamID steamIDLobby, int gameServerIP, short gameServerPort, SteamID steamIDGameServer) {
/* 293 */     SteamMatchmakingNative.setLobbyGameServer(steamIDLobby.handle, gameServerIP, gameServerPort, steamIDGameServer.handle);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getLobbyGameServer(SteamID steamIDLobby, int[] gameServerIP, short[] gameServerPort, SteamID steamIDGameServer) {
/* 298 */     long[] id = new long[1];
/*     */     
/* 300 */     if (SteamMatchmakingNative.getLobbyGameServer(steamIDLobby.handle, gameServerIP, gameServerPort, id)) {
/* 301 */       steamIDGameServer.handle = id[0];
/* 302 */       return true;
/*     */     } 
/*     */     
/* 305 */     return false;
/*     */   }
/*     */   
/*     */   public boolean setLobbyMemberLimit(SteamID steamIDLobby, int maxMembers) {
/* 309 */     return SteamMatchmakingNative.setLobbyMemberLimit(steamIDLobby.handle, maxMembers);
/*     */   }
/*     */   
/*     */   public int getLobbyMemberLimit(SteamID steamIDLobby) {
/* 313 */     return SteamMatchmakingNative.getLobbyMemberLimit(steamIDLobby.handle);
/*     */   }
/*     */   
/*     */   public boolean setLobbyType(SteamID steamIDLobby, LobbyType lobbyType) {
/* 317 */     return SteamMatchmakingNative.setLobbyType(steamIDLobby.handle, lobbyType.ordinal());
/*     */   }
/*     */   
/*     */   public boolean setLobbyJoinable(SteamID steamIDLobby, boolean joinable) {
/* 321 */     return SteamMatchmakingNative.setLobbyJoinable(steamIDLobby.handle, joinable);
/*     */   }
/*     */   
/*     */   public SteamID getLobbyOwner(SteamID steamIDLobby) {
/* 325 */     return new SteamID(SteamMatchmakingNative.getLobbyOwner(steamIDLobby.handle));
/*     */   }
/*     */   
/*     */   public boolean setLobbyOwner(SteamID steamIDLobby, SteamID steamIDNewOwner) {
/* 329 */     return SteamMatchmakingNative.setLobbyOwner(steamIDLobby.handle, steamIDNewOwner.handle);
/*     */   }
/*     */   
/*     */   public boolean setLinkedLobby(SteamID steamIDLobby, SteamID steamIDLobbyDependent) {
/* 333 */     return SteamMatchmakingNative.setLinkedLobby(steamIDLobby.handle, steamIDLobbyDependent.handle);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmaking.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */