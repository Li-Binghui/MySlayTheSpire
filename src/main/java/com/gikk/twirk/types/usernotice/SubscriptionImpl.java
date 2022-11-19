/*    */ package com.gikk.twirk.types.usernotice;
/*    */ 
/*    */ import com.gikk.twirk.types.usernotice.subtype.Subscription;
/*    */ import com.gikk.twirk.types.usernotice.subtype.SubscriptionGift;
/*    */ import com.gikk.twirk.types.usernotice.subtype.SubscriptionPlan;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class SubscriptionImpl
/*    */   implements Subscription
/*    */ {
/*    */   private final SubscriptionPlan plan;
/*    */   private final int months;
/*    */   private final int streak;
/*    */   private final boolean shareStreak;
/*    */   private final String planName;
/*    */   private final Optional<SubscriptionGift> subGift;
/*    */   
/*    */   SubscriptionImpl(SubscriptionPlan plan, int months, int streak, boolean sharedStreak, String planName, SubscriptionGift subGift) {
/* 22 */     this.plan = plan;
/* 23 */     this.months = months;
/* 24 */     this.streak = streak;
/* 25 */     this.shareStreak = sharedStreak;
/* 26 */     this.planName = planName;
/* 27 */     this.subGift = Optional.ofNullable(subGift);
/*    */   }
/*    */ 
/*    */   
/*    */   public SubscriptionPlan getSubscriptionPlan() {
/* 32 */     return this.plan;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMonths() {
/* 37 */     return this.months;
/*    */   }
/*    */   
/*    */   public int getStreak() {
/* 41 */     return this.streak;
/*    */   }
/*    */   public boolean isSharedStreak() {
/* 44 */     return this.shareStreak;
/*    */   }
/*    */   
/*    */   public String getSubscriptionPlanName() {
/* 48 */     return this.planName;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isResub() {
/* 53 */     return (this.months > 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGift() {
/* 58 */     return this.subGift.isPresent();
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<SubscriptionGift> getSubscriptionGift() {
/* 63 */     return this.subGift;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\SubscriptionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */