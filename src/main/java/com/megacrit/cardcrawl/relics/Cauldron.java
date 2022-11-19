/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ 
/*    */ public class Cauldron extends AbstractRelic {
/*    */   public static final String ID = "Cauldron";
/*    */   
/*    */   public Cauldron() {
/* 11 */     super("Cauldron", "cauldron.png", RelicTier.SHOP, LandingSound.HEAVY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 22 */     for (int i = 0; i < 5; i++) {
/* 23 */       AbstractDungeon.getCurrRoom().addPotionToRewards(PotionHelper.getRandomPotion());
/*    */     }
/*    */     
/* 26 */     AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
/* 27 */     (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.0F;
/*    */ 
/*    */     
/* 30 */     int remove = -1;
/* 31 */     for (int j = 0; j < AbstractDungeon.combatRewardScreen.rewards.size(); j++) {
/* 32 */       if (((RewardItem)AbstractDungeon.combatRewardScreen.rewards.get(j)).type == RewardItem.RewardType.CARD) {
/* 33 */         remove = j;
/*    */         break;
/*    */       } 
/*    */     } 
/* 37 */     if (remove != -1) {
/* 38 */       AbstractDungeon.combatRewardScreen.rewards.remove(remove);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 44 */     return new Cauldron();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Cauldron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */