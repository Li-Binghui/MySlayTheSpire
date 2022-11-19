/*    */ package com.gikk.twirk.types.usernotice;
/*    */ 
/*    */ import com.gikk.twirk.types.usernotice.subtype.Raid;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class RaidImpl
/*    */   implements Raid
/*    */ {
/*    */   private final String displayName;
/*    */   private final String loginName;
/*    */   private final int raidCount;
/*    */   
/*    */   RaidImpl(String displayName, String loginName, int raidCount) {
/* 16 */     this.displayName = displayName;
/* 17 */     this.loginName = loginName;
/* 18 */     this.raidCount = raidCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSourceDisplayName() {
/* 23 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSourceLoginName() {
/* 28 */     return this.loginName;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRaidCount() {
/* 33 */     return this.raidCount;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\RaidImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */