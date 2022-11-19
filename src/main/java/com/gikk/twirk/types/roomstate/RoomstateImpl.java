/*    */ package com.gikk.twirk.types.roomstate;
/*    */ 
/*    */ class RoomstateImpl
/*    */   implements Roomstate {
/*    */   private static final String LANGUAGE_IDENTIFIER = "broadcaster-lang=";
/*    */   private static final String R9K_IDENTIFIER = "r9k=";
/*    */   private static final String SUBS_IDENTIFIER = "subs-only=";
/*    */   private static final String SLOW_IDENTIFIER = "slow=";
/*    */   private final String broadcasterLanguage;
/*    */   private final int r9kMode;
/*    */   private final int subMode;
/*    */   private final int slowModeTimer;
/*    */   private final String rawLine;
/*    */   
/*    */   RoomstateImpl(DefaultRoomstateBuilder builder) {
/* 16 */     this.broadcasterLanguage = builder.broadcasterLanguage;
/* 17 */     this.r9kMode = builder.r9kMode;
/* 18 */     this.subMode = builder.subMode;
/* 19 */     this.slowModeTimer = builder.slowModeTimer;
/* 20 */     this.rawLine = builder.rawLine;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getBroadcasterLanguage() {
/* 25 */     return this.broadcasterLanguage;
/*    */   }
/*    */ 
/*    */   
/*    */   public int get9kMode() {
/* 30 */     return this.r9kMode;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSubMode() {
/* 35 */     return this.subMode;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSlowModeTimer() {
/* 40 */     return this.slowModeTimer;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     return "broadcaster-lang=" + this.broadcasterLanguage + " " + ((this.r9kMode == 0) ? "" : ("r9k=" + this.r9kMode)) + " " + ((this.slowModeTimer == 0) ? "" : ("slow=" + this.slowModeTimer)) + " " + ((this.subMode == 0) ? "" : ("subs-only=" + this.subMode));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getRaw() {
/* 55 */     return this.rawLine;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\roomstate\RoomstateImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */