/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class SkillPotion extends AbstractPotion {
/*    */   public static final String POTION_ID = "SkillPotion";
/* 14 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("SkillPotion");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SkillPotion() {
/* 20 */     super(potionStrings.NAME, "SkillPotion", PotionRarity.COMMON, PotionSize.CARD, PotionColor.GREEN);
/* 21 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 26 */     this.potency = getPotency();
/* 27 */     if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
/* 28 */       this.description = potionStrings.DESCRIPTIONS[0];
/*    */     } else {
/* 30 */       this.description = potionStrings.DESCRIPTIONS[1];
/*    */     } 
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 38 */     addToBot((AbstractGameAction)new DiscoveryAction(AbstractCard.CardType.SKILL, this.potency));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 43 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 48 */     return new SkillPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\SkillPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */