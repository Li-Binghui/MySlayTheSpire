/*    */ package com.gikk.twirk.types.usernotice;
/*    */ 
/*    */

import com.gikk.twirk.types.TagMap;
import com.gikk.twirk.types.TwitchTags;
import com.gikk.twirk.types.usernotice.subtype.*;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class TypeParser
/*    */ {
/*    */   static Raid parseRaid(TagMap map) {
/* 17 */     String displayName = map.getAsString("msg-param-displayName");
/* 18 */     String loginName = map.getAsString("msg-param-login");
/* 19 */     int count = map.getAsInt("msg-param-viewerCount");
/* 20 */     return new RaidImpl(displayName, loginName, count);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static Subscription parseSubscription(TagMap map) {
/* 26 */     int months = map.getAsInt("msg-param-cumulative-months");
/* 27 */     int streak = 0;
/* 28 */     boolean shareStreak = map.getAsBoolean("msg-param-should-share-streak");
/* 29 */     if (shareStreak) {
/* 30 */       streak = map.getAsInt("msg-param-streak-months");
/*    */     }
/* 32 */     if (months <= 0) {
/* 33 */       months = 1;
/*    */     }
/*    */     
/* 36 */     String plan = map.getAsString("msg-param-sub-plan");
/* 37 */     SubscriptionPlan subPlan = SubscriptionPlan.of(plan);
/* 38 */     String planName = map.getAsString(TwitchTags.PARAM_SUB_PLAN_NAME);
/*    */     
/* 40 */     SubscriptionGift sg = parseSubscriptionGift(map);
/*    */     
/* 42 */     return new SubscriptionImpl(subPlan, months, streak, shareStreak, planName, sg);
/*    */   }
/*    */   
/*    */   static Ritual parseRitual(TagMap map) {
/* 46 */     String ritualName = map.getAsString(TwitchTags.PARAM_RITUAL_NAME);
/* 47 */     return new RitualImpl(ritualName);
/*    */   }
/*    */   
/*    */   private static SubscriptionGift parseSubscriptionGift(TagMap map) {
/* 51 */     if (!map.containsKey(TwitchTags.PARAM_RECIPIANT_ID)) {
/* 52 */       return null;
/*    */     }
/* 54 */     long receiverID = map.getAsLong(TwitchTags.PARAM_RECIPIANT_ID);
/* 55 */     String recipientDisplayName = map.getAsString(TwitchTags.PARAM_RECIPIANT_DISPLAY_NAME);
/* 56 */     String recipientUserName = map.getAsString(TwitchTags.PARAM_RECIPIANT_NAME);
/* 57 */     return new SubscriptionGiftImpl(recipientUserName, recipientDisplayName, receiverID);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\type\\usernotice\TypeParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */