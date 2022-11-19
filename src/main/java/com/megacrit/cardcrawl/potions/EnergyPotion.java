/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class EnergyPotion extends AbstractPotion {
/* 11 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Energy Potion");
/*    */   
/*    */   public static final String POTION_ID = "Energy Potion";
/*    */ 
/*    */   
/*    */   public EnergyPotion() {
/* 17 */     super(potionStrings.NAME, "Energy Potion", PotionRarity.COMMON, PotionSize.BOLT, PotionColor.ENERGY);
/* 18 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 23 */     this.potency = getPotency();
/* 24 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 25 */     this.tips.clear();
/* 26 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 31 */     addToBot((AbstractGameAction)new GainEnergyAction(this.potency));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 36 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 41 */     return new EnergyPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\EnergyPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */