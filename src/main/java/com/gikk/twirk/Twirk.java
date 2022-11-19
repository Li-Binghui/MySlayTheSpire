/*     */ package com.gikk.twirk;
/*     */ 
/*     */ import com.gikk.twirk.events.TwirkListener;
/*     */ import com.gikk.twirk.types.clearChat.ClearChat;
/*     */ import com.gikk.twirk.types.clearChat.ClearChatBuilder;
/*     */ import com.gikk.twirk.types.hostTarget.HostTarget;
/*     */ import com.gikk.twirk.types.hostTarget.HostTargetBuilder;
/*     */ import com.gikk.twirk.types.mode.Mode;
/*     */ import com.gikk.twirk.types.mode.ModeBuilder;
/*     */ import com.gikk.twirk.types.notice.Notice;
/*     */ import com.gikk.twirk.types.notice.NoticeBuilder;
/*     */ import com.gikk.twirk.types.reconnect.ReconnectBuilder;
/*     */ import com.gikk.twirk.types.roomstate.Roomstate;
/*     */ import com.gikk.twirk.types.roomstate.RoomstateBuilder;
/*     */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*     */ import com.gikk.twirk.types.twitchMessage.TwitchMessageBuilder;
/*     */ import com.gikk.twirk.types.usernotice.Usernotice;
/*     */ import com.gikk.twirk.types.usernotice.UsernoticeBuilder;
/*     */ import com.gikk.twirk.types.users.TwitchUser;
/*     */ import com.gikk.twirk.types.users.TwitchUserBuilder;
/*     */ import com.gikk.twirk.types.users.Userstate;
/*     */ import com.gikk.twirk.types.users.UserstateBuilder;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.Socket;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
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
/*     */ public final class Twirk
/*     */ {
/*     */   private final String nick;
/*     */   private final String pass;
/*     */   private final String channel;
/*     */   final boolean verboseMode;
/*     */   private OutputThread outThread;
/*     */   private InputThread inThread;
/*     */   private final OutputQueue queue;
/*     */   private boolean resourcesCreated = false;
/*     */   private boolean isConnected = false;
/*     */   private boolean isDisposed = false;
/*  77 */   private BufferedWriter writer = null;
/*  78 */   private BufferedReader reader = null;
/*     */   
/*  80 */   private final ArrayList<TwirkListener> listeners = new ArrayList<>();
/*  81 */   final Set<String> moderators = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*  82 */   final Set<String> online = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */   
/*     */   private final ClearChatBuilder clearChatBuilder;
/*     */   
/*     */   private final HostTargetBuilder hostTargetBuilder;
/*     */   
/*     */   private final ModeBuilder modeBuilder;
/*     */   
/*     */   private final NoticeBuilder noticeBuilder;
/*     */   private final RoomstateBuilder roomstateBuilder;
/*     */   private final TwitchMessageBuilder twitchMessageBuilder;
/*     */   private final TwitchUserBuilder twitchUserBuilder;
/*     */   private final UserstateBuilder userstateBuilder;
/*     */   private final UsernoticeBuilder usernoticeBuilder;
/*     */   private final ReconnectBuilder reconnectBuilder;
/*     */   private final Socket socket;
/*     */   
/*     */   Twirk(TwirkBuilder builder) {
/* 100 */     this.nick = builder.nick;
/* 101 */     this.pass = builder.oauth;
/* 102 */     this.channel = builder.channel;
/* 103 */     this.verboseMode = builder.verboseMode;
/*     */     
/* 105 */     this.clearChatBuilder = builder.getClearChatBuilder();
/* 106 */     this.hostTargetBuilder = builder.getHostTargetBuilder();
/* 107 */     this.modeBuilder = builder.getModeBuilder();
/* 108 */     this.noticeBuilder = builder.getNoticeBuilder();
/* 109 */     this.roomstateBuilder = builder.getRoomstateBuilder();
/* 110 */     this.twitchUserBuilder = builder.getTwitchUserBuilder();
/* 111 */     this.userstateBuilder = builder.getUserstateBuilder();
/* 112 */     this.twitchMessageBuilder = builder.getTwitchMessageBuilder();
/* 113 */     this.usernoticeBuilder = builder.getUsernoticeBuilder();
/* 114 */     this.reconnectBuilder = builder.getReconnectBuilder();
/*     */     
/* 116 */     this.socket = builder.getSocket();
/*     */     
/* 118 */     this.queue = new OutputQueue();
/*     */     
/* 120 */     addIrcListener(new TwirkMaintainanceListener(this));
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
/*     */ 
/*     */   
/*     */   public void serverMessage(String message) {
/* 135 */     this.outThread.quickSend(message);
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
/*     */   public void whisper(TwitchUser receiver, String message) {
/* 148 */     whisper(receiver.getUserName(), message);
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
/*     */   public void whisper(String userName, String message) {
/* 161 */     this.queue.add("PRIVMSG " + this.channel + " :/w " + userName + " " + message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void channelMessage(String message) {
/* 170 */     this.queue.add("PRIVMSG " + this.channel + " :" + message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void priorityChannelMessage(String message) {
/* 178 */     this.queue.addFirst("PRIVMSG " + this.channel + " :" + message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConnected() {
/* 187 */     return this.isConnected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDisposed() {
/* 196 */     return this.isDisposed;
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
/*     */ 
/*     */   
/*     */   public Set<String> getUsersOnline() {
/* 211 */     Set<String> out = new HashSet<>();
/* 212 */     synchronized (this.online) {
/* 213 */       for (String s : this.online) {
/* 214 */         out.add(s);
/*     */       }
/*     */     } 
/* 217 */     return out;
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
/*     */   public Set<String> getModsOnline() {
/* 229 */     Set<String> out = new HashSet<>();
/* 230 */     synchronized (this.moderators) {
/* 231 */       for (String s : this.moderators) {
/* 232 */         out.add(s);
/*     */       }
/*     */     } 
/* 235 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNick() {
/* 243 */     return this.nick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addIrcListener(TwirkListener listener) {
/* 251 */     synchronized (this.listeners) {
/* 252 */       this.listeners.add(listener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeIrcListener(TwirkListener listener) {
/* 262 */     synchronized (this.listeners) {
/* 263 */       return this.listeners.remove(listener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean connect() throws IOException, InterruptedException {
/* 274 */     if (this.isDisposed) {
/* 275 */       System.err.println("\tError. Cannot connect. This Twirk instance has been disposed.");
/* 276 */       return false;
/*     */     } 
/* 278 */     if (this.isConnected) {
/* 279 */       System.err.println("\tError. Cannot connect. Already connected to Twitch server");
/* 280 */       return false;
/*     */     } 
/*     */     
/* 283 */     if (!this.resourcesCreated) {
/* 284 */       createResources();
/*     */     }
/*     */     
/* 287 */     int oldTimeout = this.socket.getSoTimeout();
/* 288 */     this.socket.setSoTimeout(10000);
/* 289 */     this.isConnected = doConnect();
/* 290 */     this.socket.setSoTimeout(oldTimeout);
/*     */     
/* 292 */     if (this.isConnected) {
/*     */       
/* 294 */       this.outThread.start();
/*     */ 
/*     */       
/* 297 */       addCapacies();
/* 298 */       Thread.sleep(1000L);
/*     */ 
/*     */       
/* 301 */       this.inThread.start();
/*     */ 
/*     */       
/* 304 */       serverMessage("JOIN " + this.channel);
/*     */       
/* 306 */       for (TwirkListener listener : this.listeners) {
/* 307 */         listener.onConnect();
/*     */       }
/*     */       
/* 310 */       return true;
/*     */     } 
/* 312 */     return false;
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
/*     */   
/*     */   public synchronized void disconnect() {
/* 326 */     if (!this.isConnected || this.isDisposed) {
/*     */       return;
/*     */     }
/*     */     
/* 330 */     this.isConnected = false;
/*     */     
/* 332 */     System.out.println("\n\tDisconnecting from Twitch chat...");
/* 333 */     releaseResources();
/* 334 */     System.out.println("\tDisconnected from Twitch chat\n");
/*     */     
/* 336 */     for (TwirkListener l : this.listeners) {
/* 337 */       l.onDisconnect();
/*     */     }
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
/*     */   public synchronized void close() {
/* 350 */     if (this.isDisposed) {
/*     */       return;
/*     */     }
/*     */     
/* 354 */     this.isConnected = false;
/* 355 */     this.isDisposed = true;
/*     */     
/* 357 */     System.out.println("\n\tDisposing of IRC...");
/* 358 */     releaseResources();
/* 359 */     System.out.println("\tDisposing of IRC completed\n");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createResources() throws IOException {
/* 367 */     this.socket.setSoTimeout(360000);
/*     */     
/* 369 */     this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8));
/* 370 */     this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8));
/*     */     
/* 372 */     this.outThread = new OutputThread(this, this.queue, this.reader, this.writer);
/* 373 */     this.inThread = new InputThread(this, this.reader, this.writer);
/*     */     
/* 375 */     this.resourcesCreated = true;
/*     */   }
/*     */   
/*     */   private void releaseResources() {
/* 379 */     this.resourcesCreated = false;
/*     */     
/* 381 */     this.outThread.end();
/* 382 */     this.inThread.end();
/*     */     try {
/* 384 */       this.socket.close();
/* 385 */     } catch (IOException iOException) {}
/*     */     try {
/* 387 */       this.reader.close();
/* 388 */     } catch (IOException iOException) {}
/*     */     try {
/* 390 */       this.writer.close();
/* 391 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean doConnect() throws IOException {
/* 397 */     this.writer.write("PASS " + this.pass + "\r\n");
/* 398 */     this.writer.write("NICK " + this.nick + "\r\n");
/* 399 */     this.writer.write("USER " + this.nick + " 8 * : GikkBot\r\n");
/* 400 */     this.writer.flush();
/*     */ 
/*     */     
/*     */     String line;
/*     */     
/* 405 */     while ((line = this.reader.readLine()) != null) {
/* 406 */       if (this.verboseMode) {
/* 407 */         System.out.println("IN  " + line);
/*     */       }
/*     */       
/* 410 */       if (line.contains("004")) {
/* 411 */         return true;
/*     */       }
/* 413 */       if (line.contains("Error logging in")) {
/* 414 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 419 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addCapacies() {
/* 427 */     serverMessage("CAP REQ :twitch.tv/membership");
/* 428 */     serverMessage("CAP REQ :twitch.tv/commands");
/* 429 */     serverMessage("CAP REQ :twitch.tv/tags");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setOutputMessageDelay(int millis) {
/* 438 */     this.outThread.setMessageDelay(millis);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void incommingMessage(String line) {
/* 444 */     if (line.contains("PING")) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 449 */       System.out.println("IN  " + line);
/* 450 */       serverMessage("PONG " + line.substring(5));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 455 */     synchronized (this.listeners) {
/*     */       String str1; Mode mode; String userName; ClearChat clearChat; TwitchUser twitchUser1; Notice notice; Userstate userstate; TwitchUser user; Roomstate roomstate; HostTarget hostTarget; List<String> list; Set<String> users; Usernotice usernotice;
/* 457 */       for (TwirkListener l : this.listeners) {
/* 458 */         l.onAnything(line);
/*     */       }
/*     */       
/* 461 */       TwitchMessage message = this.twitchMessageBuilder.build(line);
/*     */ 
/*     */       
/* 464 */       String s = message.getCommand();
/* 465 */       switch (s) {
/*     */         
/*     */         case "JOIN":
/* 468 */           str1 = parseUsername(message.getPrefix());
/* 469 */           for (TwirkListener l : this.listeners) {
/* 470 */             l.onJoin(str1);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "MODE":
/* 476 */           mode = this.modeBuilder.build(message);
/* 477 */           for (TwirkListener l : this.listeners) {
/* 478 */             l.onMode(mode);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "PART":
/* 484 */           userName = parseUsername(message.getPrefix());
/* 485 */           for (TwirkListener l : this.listeners) {
/* 486 */             l.onPart(userName);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "CLEARCHAT":
/* 492 */           clearChat = this.clearChatBuilder.build(message);
/* 493 */           for (TwirkListener l : this.listeners) {
/* 494 */             l.onClearChat(clearChat);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "PRIVMSG":
/* 500 */           twitchUser1 = this.twitchUserBuilder.build(message);
/* 501 */           for (TwirkListener l : this.listeners) {
/* 502 */             l.onPrivMsg(twitchUser1, message);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "WHISPER":
/* 508 */           twitchUser1 = this.twitchUserBuilder.build(message);
/* 509 */           for (TwirkListener l : this.listeners) {
/* 510 */             l.onWhisper(twitchUser1, message);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "NOTICE":
/* 516 */           notice = this.noticeBuilder.build(message);
/* 517 */           for (TwirkListener l : this.listeners) {
/* 518 */             l.onNotice(notice);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "USERSTATE":
/* 524 */           userstate = this.userstateBuilder.build(message);
/* 525 */           for (TwirkListener l : this.listeners) {
/* 526 */             l.onUserstate(userstate);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "USERNOTICE":
/* 532 */           user = this.twitchUserBuilder.build(message);
/* 533 */           usernotice = this.usernoticeBuilder.build(message);
/* 534 */           for (TwirkListener l : this.listeners) {
/* 535 */             l.onUsernotice(user, usernotice);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "ROOMSTATE":
/* 541 */           roomstate = this.roomstateBuilder.build(message);
/* 542 */           for (TwirkListener l : this.listeners) {
/* 543 */             l.onRoomstate(roomstate);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "HOSTTARGET":
/* 549 */           hostTarget = this.hostTargetBuilder.build(message);
/* 550 */           for (TwirkListener l : this.listeners) {
/* 551 */             l.onHost(hostTarget);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "RECONNECT":
/* 557 */           for (TwirkListener l : this.listeners) {
/* 558 */             l.onReconnect();
/*     */           }
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case "353":
/* 565 */           list = Arrays.asList(message.getContent().split(" "));
/* 566 */           this.online.addAll(list);
/*     */           break;
/*     */ 
/*     */         
/*     */         case "366":
/* 571 */           users = Collections.unmodifiableSet(this.online);
/* 572 */           for (TwirkListener l : this.listeners) {
/* 573 */             l.onNamesList(users);
/*     */           }
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 579 */           for (TwirkListener l : this.listeners) {
/* 580 */             l.onUnknown(line);
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String parseUsername(String prefix) {
/* 593 */     return prefix.substring((prefix.charAt(0) == ':') ? 1 : 0, prefix.indexOf('!'));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\Twirk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */