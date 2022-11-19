/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class DistilledChaosPotion extends AbstractPotion {
/* 13 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("DistilledChaos"); public static final String POTION_ID = "DistilledChaos";
/*    */   
/*    */   public DistilledChaosPotion() {
/* 16 */     super(potionStrings.NAME, "DistilledChaos", PotionRarity.UNCOMMON, PotionSize.T, PotionEffect.RAINBOW, Color.WHITE, null, null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 30 */     this.potency = getPotency();
/* 31 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 38 */     for (int i = 0; i < this.potency; i++) {
/* 39 */       addToBot((AbstractGameAction)new PlayTopCardAction(
/*    */             
/* 41 */             (AbstractCreature)(AbstractDungeon.getCurrRoom()).monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 48 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 53 */     return new DistilledChaosPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\DistilledChaosPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */