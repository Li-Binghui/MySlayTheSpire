/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class ColorlessPotion extends AbstractPotion {
/*    */   public static final String POTION_ID = "ColorlessPotion";
/* 13 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("ColorlessPotion");
/*    */   
/*    */   public ColorlessPotion() {
/* 16 */     super(potionStrings.NAME, "ColorlessPotion", PotionRarity.COMMON, PotionSize.CARD, PotionColor.WHITE);
/* 17 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 22 */     this.potency = getPotency();
/* 23 */     if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
/* 24 */       this.description = potionStrings.DESCRIPTIONS[0];
/*    */     } else {
/* 26 */       this.description = potionStrings.DESCRIPTIONS[1];
/*    */     } 
/* 28 */     this.tips.clear();
/* 29 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 34 */     addToBot((AbstractGameAction)new DiscoveryAction(true, this.potency));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 39 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 44 */     return new ColorlessPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\ColorlessPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */