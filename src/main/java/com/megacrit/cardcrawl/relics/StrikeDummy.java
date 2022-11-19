/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ public class StrikeDummy extends AbstractRelic {
/*    */   public static final String ID = "StrikeDummy";
/*    */   
/*    */   public StrikeDummy() {
/*  9 */     super("StrikeDummy", "dummy.png", RelicTier.UNCOMMON, LandingSound.HEAVY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float atDamageModify(float damage, AbstractCard c) {
/* 20 */     if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
/* 21 */       return damage + 3.0F;
/*    */     }
/* 23 */     return damage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new StrikeDummy();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\StrikeDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */