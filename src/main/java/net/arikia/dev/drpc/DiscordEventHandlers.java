/*    */ package net.arikia.dev.drpc;
/*    */ 
/*    */ import com.sun.jna.Structure;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.arikia.dev.drpc.callbacks.DisconnectedCallback;
/*    */ import net.arikia.dev.drpc.callbacks.ErroredCallback;
/*    */ import net.arikia.dev.drpc.callbacks.JoinGameCallback;
/*    */ import net.arikia.dev.drpc.callbacks.JoinRequestCallback;
/*    */ import net.arikia.dev.drpc.callbacks.ReadyCallback;
/*    */ import net.arikia.dev.drpc.callbacks.SpectateGameCallback;
/*    */ 
/*    */ public class DiscordEventHandlers
/*    */   extends Structure {
/*    */   public ReadyCallback ready;
/*    */   public DisconnectedCallback disconnected;
/*    */   public ErroredCallback errored;
/*    */   
/*    */   public List<String> getFieldOrder() {
/* 20 */     return Arrays.asList(new String[] { "ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest" });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JoinGameCallback joinGame;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SpectateGameCallback spectateGame;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JoinRequestCallback joinRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Builder
/*    */   {
/* 58 */     DiscordEventHandlers h = new DiscordEventHandlers();
/*    */ 
/*    */     
/*    */     public Builder setReadyEventHandler(ReadyCallback r) {
/* 62 */       this.h.ready = r;
/* 63 */       return this;
/*    */     }
/*    */     
/*    */     public Builder setDisconnectedEventHandler(DisconnectedCallback d) {
/* 67 */       this.h.disconnected = d;
/* 68 */       return this;
/*    */     }
/*    */     
/*    */     public Builder setErroredEventHandler(ErroredCallback e) {
/* 72 */       this.h.errored = e;
/* 73 */       return this;
/*    */     }
/*    */     
/*    */     public Builder setJoinGameEventHandler(JoinGameCallback j) {
/* 77 */       this.h.joinGame = j;
/* 78 */       return this;
/*    */     }
/*    */     
/*    */     public Builder setSpectateGameEventHandler(SpectateGameCallback s) {
/* 82 */       this.h.spectateGame = s;
/* 83 */       return this;
/*    */     }
/*    */     
/*    */     public Builder setJoinRequestEventHandler(JoinRequestCallback j) {
/* 87 */       this.h.joinRequest = j;
/* 88 */       return this;
/*    */     }
/*    */     
/*    */     public DiscordEventHandlers build() {
/* 92 */       return this.h;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\net\arikia\dev\drpc\DiscordEventHandlers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */