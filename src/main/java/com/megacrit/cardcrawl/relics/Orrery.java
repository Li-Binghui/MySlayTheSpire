/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Orrery extends AbstractRelic {
/*    */   public static final String ID = "Orrery";
/*    */   
/*    */   public Orrery() {
/*  9 */     super("Orrery", "orrery.png", RelicTier.SHOP, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 20 */     for (int i = 0; i < 4; i++) {
/* 21 */       AbstractDungeon.getCurrRoom().addCardToRewards();
/*    */     }
/*    */     
/* 24 */     AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
/* 25 */     (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new Orrery();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Orrery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */