/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class RedCirclet extends AbstractRelic {
/*    */   public static final String ID = "Red Circlet";
/*    */   
/*    */   public RedCirclet() {
/*  7 */     super("Red Circlet", "redCirclet.png", RelicTier.SPECIAL, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 12 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 17 */     return new RedCirclet();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\RedCirclet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */