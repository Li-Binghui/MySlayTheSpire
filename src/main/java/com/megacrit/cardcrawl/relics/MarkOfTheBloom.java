/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class MarkOfTheBloom extends AbstractRelic {
/*    */   public static final String ID = "Mark of the Bloom";
/*    */   
/*    */   public MarkOfTheBloom() {
/*  7 */     super("Mark of the Bloom", "bloom.png", RelicTier.SPECIAL, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 12 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public int onPlayerHeal(int healAmount) {
/* 17 */     flash();
/* 18 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 23 */     return new MarkOfTheBloom();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MarkOfTheBloom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */