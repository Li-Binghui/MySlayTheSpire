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
/*    */ 
/*    */ 
/*    */ 
/*    */ class UserstateImpl
/*    */   implements Userstate
/*    */ {
/*    */   private final int color;
/*    */   private final String displayName;
/*    */   private final boolean isMod;
/*    */   private final boolean isSub;
/*    */   private final boolean isTurbo;
/*    */   private final USER_TYPE userType;
/*    */   private final int[] emoteSets;
/*    */   private final String rawLine;
/*    */   
/*    */   UserstateImpl(DefaultUserstateBuilder builder) {
/* 27 */     this.color = builder.color;
/* 28 */     this.displayName = builder.displayName;
/* 29 */     this.isMod = builder.isMod;
/* 30 */     this.isSub = builder.isSub;
/* 31 */     this.isTurbo = builder.isTurbo;
/* 32 */     this.userType = builder.userType;
/* 33 */     this.emoteSets = builder.emoteSets;
/* 34 */     this.rawLine = builder.rawLine;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColor() {
/* 39 */     return this.color;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 44 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMod() {
/* 49 */     return this.isMod;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSub() {
/* 54 */     return this.isSub;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTurbo() {
/* 59 */     return this.isTurbo;
/*    */   }
/*    */ 
/*    */   
/*    */   public USER_TYPE getUserType() {
/* 64 */     return this.userType;
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] getEmoteSets() {
/* 69 */     return this.emoteSets;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRaw() {
/* 74 */     return this.rawLine;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\users\UserstateImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */