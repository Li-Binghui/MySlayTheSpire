/*     */ package de.robojumper.ststwitch;
/*     */ 
/*     */ import com.gikk.twirk.Twirk;
/*     */ import com.gikk.twirk.TwirkBuilder;
/*     */ import com.gikk.twirk.events.TwirkListener;
/*     */ import com.gikk.twirk.types.twitchMessage.TwitchMessage;
/*     */ import com.gikk.twirk.types.users.TwitchUser;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class TwitchConnection
/*     */   extends Thread
/*     */ {
/*     */   private final Twirk twirk;
/*     */   
/*     */   public TwitchConfig getTwitchConfig() {
/*  25 */     return this.twitchConfig;
/*     */   }
/*     */   
/*     */   public enum ConnectionStatus {
/*  29 */     CONNECTED, CONNECTING, DISCONNECTING, DISCONNECTED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  34 */   private static final Logger logger = LogManager.getLogger(TwitchConnection.class.getName());
/*     */   
/*     */   private volatile boolean wantsConnection = false;
/*     */   
/*     */   private volatile boolean shuttingDown = false;
/*  39 */   private int connectionAttempts = 0;
/*     */   
/*  41 */   private List<TwitchMessageListener> listeners = new ArrayList<>();
/*     */   
/*     */   private final TwitchConfig twitchConfig;
/*  44 */   private Queue<String> outMessages = new ArrayDeque<>();
/*  45 */   private Queue<MessageUserPair> inMessages = new ArrayDeque<>();
/*     */ 
/*     */   
/*     */   private boolean hasPulse;
/*     */ 
/*     */   
/*  51 */   private final Object gameLock = new Object();
/*     */   
/*  53 */   private static final Pattern QUOTE_PATTERN = Pattern.compile("(?i)\"(.*)\"(?:.*?)-?- ?joinrbs(?:[ \\.,].*)?");
/*     */   
/*     */   public TwitchConnection(TwitchConfig twitchConfig) throws IOException {
/*  56 */     this.twitchConfig = twitchConfig;
/*     */ 
/*     */     
/*  59 */     logger.info("Connecting: username=" + twitchConfig
/*  60 */         .getUsername() + ", token(truncated)=" + truncateToken(twitchConfig
/*  61 */           .getToken()) + ", channel=" + twitchConfig.getChannel());
/*     */ 
/*     */ 
/*     */     
/*  65 */     TwirkBuilder tb = new TwirkBuilder(twitchConfig.getChannel(), twitchConfig.getUsername(), twitchConfig.getToken());
/*  66 */     this.twirk = tb.setSSL(true).build();
/*  67 */     this.twirk.addIrcListener(getMessageListener(this.twirk, this.gameLock, this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String truncateToken(String token) {
/*  78 */     if (token.length() > 10) {
/*  79 */       return token.substring(0, 10);
/*     */     }
/*  81 */     return token;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  87 */     while (!this.shuttingDown) {
/*  88 */       if (this.wantsConnection && !this.twirk.isConnected()) {
/*  89 */         attemptConnect();
/*  90 */       } else if (!this.wantsConnection && this.twirk.isConnected()) {
/*  91 */         this.twirk.disconnect();
/*     */       } 
/*     */       
/*  94 */       if (this.twirk.isConnected()) {
/*  95 */         synchronized (this.gameLock) {
/*     */           String message;
/*  97 */           while ((message = this.outMessages.poll()) != null) {
/*  98 */             this.twirk.channelMessage(message);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 104 */         Thread.sleep(1000L);
/* 105 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */ 
/*     */     
/* 109 */     this.twirk.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 115 */     synchronized (this.gameLock) {
/*     */       MessageUserPair message;
/* 117 */       while ((message = this.inMessages.poll()) != null) {
/* 118 */         for (TwitchMessageListener l : this.listeners) {
/* 119 */           l.onMessage(message.message, message.user);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void registerMessageListener(TwitchMessageListener l) {
/* 126 */     synchronized (this.gameLock) {
/* 127 */       this.listeners.add(l);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setStatus(boolean shouldBeRunning) {
/* 132 */     synchronized (this.twirk) {
/* 133 */       this.wantsConnection = shouldBeRunning;
/*     */     } 
/*     */   }
/*     */   
/*     */   void toggleStatus() {
/* 138 */     synchronized (this.twirk) {
/* 139 */       setStatus(!this.wantsConnection);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendMessage(String msg) {
/* 144 */     synchronized (this.gameLock) {
/* 145 */       this.outMessages.add(msg);
/* 146 */       interrupt();
/*     */     } 
/*     */   }
/*     */   
/*     */   ConnectionStatus getConnectionStatus() {
/* 151 */     if (this.wantsConnection) {
/* 152 */       if (this.twirk.isConnected()) {
/* 153 */         return ConnectionStatus.CONNECTED;
/*     */       }
/* 155 */       return ConnectionStatus.CONNECTING;
/*     */     } 
/*     */     
/* 158 */     if (this.twirk.isConnected()) {
/* 159 */       return ConnectionStatus.DISCONNECTING;
/*     */     }
/* 161 */     return ConnectionStatus.DISCONNECTED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean popPulse() {
/* 167 */     synchronized (this.gameLock) {
/* 168 */       if (this.hasPulse) {
/* 169 */         this.hasPulse = false;
/* 170 */         return true;
/*     */       } 
/* 172 */       return this.hasPulse;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 178 */     this.shuttingDown = true;
/*     */   }
/*     */   
/*     */   private void attemptConnect() {
/* 182 */     if (!this.twirk.isConnected() && !this.shuttingDown) {
/*     */       try {
/* 184 */         this.twirk.connect();
/* 185 */       } catch (IOException|InterruptedException e) {
/* 186 */         this.connectionAttempts++;
/* 187 */         if (this.connectionAttempts > 3) {
/* 188 */           this.connectionAttempts = 0;
/* 189 */           synchronized (this.twirk) {
/* 190 */             this.wantsConnection = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static TwirkListener getMessageListener(Twirk twirk, final Object gameLock, final TwitchConnection conn) {
/* 202 */     return new TwirkListener()
/*     */       {
/*     */         public void onDisconnect() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void onPrivMsg(TwitchUser sender, TwitchMessage message) {
/* 211 */           String content = message.getContent().trim();
/* 212 */           String senderName = sender.getUserName();
/* 213 */           if (content.length() > 1)
/* 214 */             synchronized (gameLock) {
/*     */               
/* 216 */               Matcher m = TwitchConnection.QUOTE_PATTERN.matcher(content);
/* 217 */               if (content.equals("#pulse")) {
/* 218 */                 conn.hasPulse = true;
/*     */               }
/* 220 */               conn.inMessages.add(new TwitchConnection.MessageUserPair(content, senderName));
/*     */             }  
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static class MessageUserPair
/*     */   {
/*     */     final String message;
/*     */     final String user;
/*     */     
/*     */     MessageUserPair(String m, String u) {
/* 232 */       this.message = m;
/* 233 */       this.user = u;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\de\robojumper\ststwitch\TwitchConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */