/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ 
/*    */ public class BustedCrown extends AbstractRelic {
/*    */   private static final int CARDS_SUBTRACTED = 2;
/*    */   public static final String ID = "Busted Crown";
/*    */   
/*    */   public BustedCrown() {
/* 12 */     super("Busted Crown", "crown.png", RelicTier.BOSS, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     if (AbstractDungeon.player != null) {
/* 18 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 20 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 25 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 30 */     this.description = setDescription(c);
/* 31 */     this.tips.clear();
/* 32 */     this.tips.add(new PowerTip(this.name, this.description));
/* 33 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 38 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 43 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public int changeNumberOfCardsInReward(int numberOfCards) {
/* 48 */     return numberOfCards - 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 53 */     return new BustedCrown();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BustedCrown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */