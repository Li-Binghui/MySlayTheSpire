/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class NlothsGift
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Nloth's Gift";
/*    */   public static final float MULTIPLIER = 3.0F;
/*    */   
/*    */   public NlothsGift() {
/*  9 */     super("Nloth's Gift", "nlothsGift.png", RelicTier.SPECIAL, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public int changeRareCardRewardChance(int rareCardChance) {
/* 19 */     return rareCardChance * 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 24 */     return new NlothsGift();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\NlothsGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */