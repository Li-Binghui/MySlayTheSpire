/*    */ package com.gikk.twirk;
/*    */ 
/*    */ import com.gikk.twirk.events.TwirkListener;
/*    */ import com.gikk.twirk.types.mode.Mode;
/*    */ import com.gikk.twirk.types.users.Userstate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class TwirkMaintainanceListener
/*    */   implements TwirkListener
/*    */ {
/*    */   private final Twirk instance;
/*    */   
/*    */   TwirkMaintainanceListener(Twirk twirk) {
/* 19 */     this.instance = twirk;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAnything(String line) {
/* 24 */     if (this.instance.verboseMode) {
/* 25 */       System.out.println("IN  " + line);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onJoin(String joinedNick) {
/* 31 */     if (!this.instance.online.add(joinedNick)) {
/* 32 */       System.out.println(" was already listed as online....\tUser " + joinedNick);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPart(String partedNick) {
/* 38 */     if (!this.instance.online.remove(partedNick)) {
/* 39 */       System.out.println("\tUser " + partedNick + " was not listed as online....");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMode(Mode mode) {
/* 45 */     if (mode.getEvent() == Mode.MODE_EVENT.GAINED_MOD) {
/* 46 */       this.instance.moderators.add(mode.getUser());
/*    */     } else {
/*    */       
/* 49 */       this.instance.moderators.remove(mode.getUser());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUserstate(Userstate userstate) {
/* 57 */     if (userstate.isMod()) {
/* 58 */       this.instance.setOutputMessageDelay(300);
/*    */     } else {
/* 60 */       this.instance.setOutputMessageDelay(1500);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisconnect() {
/* 66 */     this.instance.online.clear();
/* 67 */     this.instance.moderators.clear();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\TwirkMaintainanceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */