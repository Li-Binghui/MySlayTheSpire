/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ public class WristBlade extends AbstractRelic {
/*    */   public static final String ID = "WristBlade";
/*    */   
/*    */   public WristBlade() {
/*  9 */     super("WristBlade", "wBlade.png", RelicTier.BOSS, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 19 */     return new WristBlade();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float atDamageModify(float damage, AbstractCard c) {
/* 25 */     if (c.costForTurn == 0 || (c.freeToPlayOnce && c.cost != -1)) {
/* 26 */       return damage + 4.0F;
/*    */     }
/* 28 */     return damage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\WristBlade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */