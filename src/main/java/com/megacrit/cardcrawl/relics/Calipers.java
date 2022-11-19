/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class Calipers
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Calipers";
/*    */   public static final int BLOCK_LOSS = 15;
/*    */   
/*    */   public Calipers() {
/*  9 */     super("Calipers", "calipers.png", RelicTier.RARE, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0] + '\017' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 19 */     return new Calipers();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Calipers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */