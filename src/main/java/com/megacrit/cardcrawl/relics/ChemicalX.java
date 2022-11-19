/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ 
/*    */ public class ChemicalX extends AbstractRelic {
/*    */   public static final String ID = "Chemical X";
/*    */   public static final int BOOST = 2;
/*    */   
/*    */   public ChemicalX() {
/* 10 */     super("Chemical X", "chemicalX.png", RelicTier.SHOP, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     if (!this.DESCRIPTIONS[1].equals("")) {
/* 16 */       return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */     }
/* 18 */     return this.DESCRIPTIONS[0] + '\002' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 24 */     return new ChemicalX();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\ChemicalX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */