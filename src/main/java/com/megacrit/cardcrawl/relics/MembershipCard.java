/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class MembershipCard
/*    */   extends AbstractRelic
/*    */ {
/*    */   public static final String ID = "Membership Card";
/*    */   public static final float MULTIPLIER = 0.5F;
/*    */   
/*    */   public MembershipCard() {
/* 12 */     super("Membership Card", "membershipCard.png", RelicTier.SHOP, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom(AbstractRoom room) {
/* 22 */     if (room instanceof com.megacrit.cardcrawl.rooms.ShopRoom) {
/* 23 */       flash();
/* 24 */       this.pulse = true;
/*    */     } else {
/* 26 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new MembershipCard();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MembershipCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */