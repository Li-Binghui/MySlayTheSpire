/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class LiquidMemories extends AbstractPotion {
/* 12 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("LiquidMemories"); public static final String POTION_ID = "LiquidMemories";
/*    */   
/*    */   public LiquidMemories() {
/* 15 */     super(potionStrings.NAME, "LiquidMemories", PotionRarity.UNCOMMON, PotionSize.EYE, PotionEffect.NONE, new Color(225754111), new Color(389060095), null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 29 */     this.potency = getPotency();
/* 30 */     if (this.potency == 1) {
/* 31 */       this.description = potionStrings.DESCRIPTIONS[0];
/*    */     } else {
/* 33 */       this.description = potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[2];
/*    */     } 
/* 35 */     this.tips.clear();
/* 36 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 41 */     addToBot((AbstractGameAction)new BetterDiscardPileToHandAction(this.potency, 0));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 46 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 51 */     return new LiquidMemories();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\LiquidMemories.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */