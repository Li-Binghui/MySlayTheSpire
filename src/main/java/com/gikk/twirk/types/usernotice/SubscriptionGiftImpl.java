/*    */ package com.gikk.twirk.types.usernotice;
/*    */ 
/*    */ import com.gikk.twirk.types.usernotice.subtype.SubscriptionGift;
/*    */ 
/*    */ 
/*    */ 
/*    */ class SubscriptionGiftImpl
/*    */   implements SubscriptionGift
/*    */ {
/*    */   private final String recipiantUserName;
/*    */   private final String recipiantDisplayName;
/*    */   private final long RecipiantUserID;
/*    */   
/*    */   public SubscriptionGiftImpl(String recipientUserName, String recipiantDisplayName, long RecipiantUserID) {
/* 15 */     this.recipiantUserName = recipientUserName;
/* 16 */     this.recipiantDisplayName = recipiantDisplayName;
/* 17 */     this.RecipiantUserID = RecipiantUserID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRecipiantDisplayName() {
/* 22 */     return this.recipiantDisplayName;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getRecipiantUserID() {
/* 27 */     return this.RecipiantUserID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRecipiantUserName() {
/* 32 */     return this.recipiantUserName;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\SubscriptionGiftImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */