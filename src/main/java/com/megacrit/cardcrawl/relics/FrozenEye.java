/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class FrozenEye extends AbstractRelic {
/*    */   public static final String ID = "Frozen Eye";
/*    */   
/*    */   public FrozenEye() {
/*  7 */     super("Frozen Eye", "frozenEye.png", RelicTier.SHOP, LandingSound.SOLID);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 12 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 17 */     return new FrozenEye();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\FrozenEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */