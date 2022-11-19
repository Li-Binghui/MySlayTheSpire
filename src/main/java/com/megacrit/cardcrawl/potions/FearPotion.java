/*    */ package com.megacrit.cardcrawl.potions;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ 
/*    */ public class FearPotion extends AbstractPotion {
/* 15 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FearPotion");
/*    */   
/*    */   public static final String POTION_ID = "FearPotion";
/*    */ 
/*    */   
/*    */   public FearPotion() {
/* 21 */     super(potionStrings.NAME, "FearPotion", PotionRarity.COMMON, PotionSize.H, PotionColor.FEAR);
/* 22 */     this.isThrown = true;
/* 23 */     this.targetRequired = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 28 */     this.potency = getPotency();
/* 29 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 30 */     this.tips.clear();
/* 31 */     this.tips.add(new PowerTip(this.name, this.description));
/* 32 */     this.tips.add(new PowerTip(
/*    */           
/* 34 */           TipHelper.capitalize(GameDictionary.VULNERABLE.NAMES[0]), (String)GameDictionary.keywords
/* 35 */           .get(GameDictionary.VULNERABLE.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 40 */     addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VulnerablePower(target, this.potency, false), this.potency));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 46 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 51 */     return new FearPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\FearPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */