/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ public class TungstenRod extends AbstractRelic {
/*    */   public static final String ID = "TungstenRod";
/*    */   
/*    */   public TungstenRod() {
/*  7 */     super("TungstenRod", "tungsten.png", RelicTier.RARE, LandingSound.HEAVY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 12 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public int onLoseHpLast(int damageAmount) {
/* 17 */     if (damageAmount > 0) {
/* 18 */       flash();
/* 19 */       return damageAmount - 1;
/*    */     } 
/* 21 */     return damageAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new TungstenRod();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\TungstenRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */