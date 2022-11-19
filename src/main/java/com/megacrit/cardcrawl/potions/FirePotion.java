/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class FirePotion
/*    */   extends AbstractPotion {
/*    */   public static final String POTION_ID = "Fire Potion";
/* 15 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Fire Potion");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FirePotion() {
/* 21 */     super(potionStrings.NAME, "Fire Potion", PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.FIRE);
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
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 36 */     DamageInfo info = new DamageInfo((AbstractCreature)AbstractDungeon.player, this.potency, DamageInfo.DamageType.THORNS);
/* 37 */     info.applyEnemyPowersOnly(target);
/* 38 */     addToBot((AbstractGameAction)new DamageAction(target, info, AbstractGameAction.AttackEffect.FIRE));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 43 */     return 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 48 */     return new FirePotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\FirePotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */