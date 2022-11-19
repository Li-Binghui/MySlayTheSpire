/*    */ package com.gikk.twirk.types.users;
/*    */ 
/*    */ import com.gikk.twirk.enums.USER_TYPE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class TwitchUserImpl
/*    */   implements TwitchUser
/*    */ {
/*    */   private final String userName;
/*    */   private final String displayName;
/*    */   private final boolean isOwner;
/*    */   private final boolean isMod;
/*    */   private final boolean isSub;
/*    */   private final boolean isTurbo;
/*    */   private final int color;
/*    */   private final long userID;
/*    */   private final USER_TYPE userType;
/*    */   private final String[] badges;
/*    */   
/*    */   TwitchUserImpl(DefaultTwitchUserBuilder builder) {
/* 26 */     this.userName = builder.userName;
/* 27 */     this.displayName = builder.displayName;
/* 28 */     this.isOwner = builder.isOwner;
/* 29 */     this.isMod = builder.isMod;
/* 30 */     this.isSub = builder.isSub;
/* 31 */     this.isTurbo = builder.isTurbo;
/* 32 */     this.badges = builder.badges;
/* 33 */     this.userID = builder.userID;
/* 34 */     this.userType = builder.userType;
/* 35 */     this.color = builder.color;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUserName() {
/* 44 */     return this.userName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 49 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOwner() {
/* 54 */     return this.isOwner;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMod() {
/* 59 */     return this.isMod;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTurbo() {
/* 64 */     return this.isTurbo;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSub() {
/* 69 */     return this.isSub;
/*    */   }
/*    */ 
/*    */   
/*    */   public USER_TYPE getUserType() {
/* 74 */     return this.userType;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColor() {
/* 79 */     return this.color;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getBadges() {
/* 84 */     return this.badges;
/*    */   }
/*    */   
/*    */   public long getUserID() {
/* 88 */     return this.userID;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\users\TwitchUserImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */