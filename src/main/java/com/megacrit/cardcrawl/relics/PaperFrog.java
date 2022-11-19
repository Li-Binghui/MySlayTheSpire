/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class PaperFrog
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Paper Frog";
/*    */   public static final float VULN_EFFECTIVENESS = 1.75F;
/*    */   public static final int EFFECTIVENESS_STRING = 75;
/*    */   
/*    */   public PaperFrog() {
/* 10 */     super("Paper Frog", "paperFrog.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new PaperFrog();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\PaperFrog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */