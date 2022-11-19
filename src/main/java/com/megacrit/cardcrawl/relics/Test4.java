/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class Test4 extends AbstractRelic {
/*    */   public static final String ID = "Test 4";
/*    */   
/*    */   public Test4() {
/*  7 */     super("Test 4", "test4.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 12 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atBattleStart() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 22 */     return new Test4();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Test4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */