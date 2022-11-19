/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class StrangeSpoon
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Strange Spoon";
/*    */   public static final int DISCARD_CHANCE = 50;
/*    */   
/*    */   public StrangeSpoon() {
/*  9 */     super("Strange Spoon", "bigSpoon.png", RelicTier.SHOP, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 19 */     return new StrangeSpoon();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\StrangeSpoon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */