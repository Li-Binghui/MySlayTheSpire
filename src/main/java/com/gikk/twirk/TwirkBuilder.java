/*     */ package com.gikk.twirk;
/*     */ 
/*     */ import com.gikk.twirk.types.clearChat.ClearChatBuilder;
/*     */ import com.gikk.twirk.types.hostTarget.HostTargetBuilder;
/*     */ import com.gikk.twirk.types.mode.ModeBuilder;
/*     */ import com.gikk.twirk.types.notice.NoticeBuilder;
/*     */ import com.gikk.twirk.types.reconnect.ReconnectBuilder;
/*     */ import com.gikk.twirk.types.roomstate.RoomstateBuilder;
/*     */ import com.gikk.twirk.types.twitchMessage.TwitchMessageBuilder;
/*     */ import com.gikk.twirk.types.usernotice.UsernoticeBuilder;
/*     */ import com.gikk.twirk.types.users.TwitchUserBuilder;
/*     */ import com.gikk.twirk.types.users.UserstateBuilder;
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TwirkBuilder
/*     */ {
/*     */   boolean verboseMode = false;
/*  35 */   String server = "irc.chat.twitch.tv";
/*  36 */   int port = 6697;
/*     */   
/*     */   boolean useSSL = true;
/*  39 */   String nick = "";
/*  40 */   String oauth = "";
/*  41 */   String channel = "";
/*     */   
/*     */   private ClearChatBuilder clearChatBuilder;
/*     */   
/*     */   private HostTargetBuilder hostTargetBuilder;
/*     */   
/*     */   private ModeBuilder modeBuilder;
/*     */   
/*     */   private NoticeBuilder noticeBuilder;
/*     */   
/*     */   private RoomstateBuilder roomstateBuilder;
/*     */   
/*     */   private TwitchMessageBuilder twitchMessageBuilder;
/*     */   
/*     */   private TwitchUserBuilder twitchUserBuilder;
/*     */   
/*     */   private UserstateBuilder userstateBuilder;
/*     */   
/*     */   private UsernoticeBuilder usernoticeBuilder;
/*     */   
/*     */   private ReconnectBuilder reconnectBuilder;
/*     */   
/*     */   private Socket socket;
/*     */ 
/*     */   
/*     */   public TwirkBuilder(String channel, String nick, String oauth) {
/*  67 */     this.channel = channel;
/*  68 */     this.nick = nick;
/*  69 */     this.oauth = oauth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setServer(String server) {
/*  81 */     this.server = server;
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setPort(int port) {
/*  91 */     this.port = port;
/*  92 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setSSL(boolean ssl) {
/* 105 */     this.useSSL = ssl;
/* 106 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setVerboseMode(boolean verboseMode) {
/* 116 */     this.verboseMode = verboseMode;
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setClearChatBuilder(ClearChatBuilder clearChatBuilder) {
/* 127 */     this.clearChatBuilder = clearChatBuilder;
/* 128 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setHostTargetBuilder(HostTargetBuilder hostTargetBuilder) {
/* 138 */     this.hostTargetBuilder = hostTargetBuilder;
/* 139 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setModeBuilder(ModeBuilder modeBuilder) {
/* 149 */     this.modeBuilder = modeBuilder;
/* 150 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setNoticeBuilder(NoticeBuilder noticeBuilder) {
/* 160 */     this.noticeBuilder = noticeBuilder;
/* 161 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setRoomstateBuilder(RoomstateBuilder roomstateBuilder) {
/* 171 */     this.roomstateBuilder = roomstateBuilder;
/* 172 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setTwitchMessageBuilder(TwitchMessageBuilder twitchMessageBuilder) {
/* 182 */     this.twitchMessageBuilder = twitchMessageBuilder;
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setTwitchUserBuilder(TwitchUserBuilder twitchUserBuilder) {
/* 193 */     this.twitchUserBuilder = twitchUserBuilder;
/* 194 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setUsernoticeBuilder(UsernoticeBuilder usernoticeBuilder) {
/* 204 */     this.usernoticeBuilder = usernoticeBuilder;
/* 205 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setReconnectBuilder(ReconnectBuilder reconnectBuilder) {
/* 215 */     this.reconnectBuilder = reconnectBuilder;
/* 216 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setUserstateBuilder(UserstateBuilder userstateBuilder) {
/* 226 */     this.userstateBuilder = userstateBuilder;
/* 227 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwirkBuilder setSocket(Socket socket) {
/* 237 */     this.socket = socket;
/* 238 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClearChatBuilder getClearChatBuilder() {
/* 246 */     return (this.clearChatBuilder != null) ? this.clearChatBuilder : ClearChatBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HostTargetBuilder getHostTargetBuilder() {
/* 254 */     return (this.hostTargetBuilder != null) ? this.hostTargetBuilder : HostTargetBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModeBuilder getModeBuilder() {
/* 262 */     return (this.modeBuilder != null) ? this.modeBuilder : ModeBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NoticeBuilder getNoticeBuilder() {
/* 270 */     return (this.noticeBuilder != null) ? this.noticeBuilder : NoticeBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RoomstateBuilder getRoomstateBuilder() {
/* 278 */     return (this.roomstateBuilder != null) ? this.roomstateBuilder : RoomstateBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwitchMessageBuilder getTwitchMessageBuilder() {
/* 286 */     return (this.twitchMessageBuilder != null) ? this.twitchMessageBuilder : TwitchMessageBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TwitchUserBuilder getTwitchUserBuilder() {
/* 294 */     return (this.twitchUserBuilder != null) ? this.twitchUserBuilder : TwitchUserBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserstateBuilder getUserstateBuilder() {
/* 302 */     return (this.userstateBuilder != null) ? this.userstateBuilder : UserstateBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UsernoticeBuilder getUsernoticeBuilder() {
/* 310 */     return (this.usernoticeBuilder != null) ? this.usernoticeBuilder : UsernoticeBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReconnectBuilder getReconnectBuilder() {
/* 318 */     return (this.reconnectBuilder != null) ? this.reconnectBuilder : ReconnectBuilder.getDefault();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Socket getSocket() {
/* 328 */     return this.socket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Twirk build() throws IOException {
/* 338 */     if (this.socket == null) {
/* 339 */       if (this.useSSL) {
/* 340 */         this.socket = SSLSocketFactory.getDefault().createSocket(this.server, this.port);
/*     */       } else {
/*     */         
/* 343 */         this.socket = new Socket(this.server, this.port);
/*     */       } 
/*     */     }
/*     */     
/* 347 */     return new Twirk(this);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\TwirkBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */