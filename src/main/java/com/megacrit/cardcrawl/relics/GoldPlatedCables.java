/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class GoldPlatedCables extends AbstractRelic {
/*    */   public static final String ID = "Cables";
/*    */   
/*    */   public GoldPlatedCables() {
/*  7 */     super("Cables", "cables.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 12 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 17 */     return new GoldPlatedCables();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\GoldPlatedCables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */