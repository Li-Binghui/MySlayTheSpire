/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class PotionSlot extends AbstractPotion {
/*    */   public static final String POTION_ID = "Potion Slot";
/* 10 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Potion Slot");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PotionSlot(int slot) {
/* 18 */     super(potionStrings.NAME, "Potion Slot", PotionRarity.PLACEHOLDER, PotionSize.T, PotionColor.NONE);
/* 19 */     this.isObtained = true;
/* 20 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 21 */     this.name = potionStrings.DESCRIPTIONS[1];
/* 22 */     this.tips.add(new PowerTip(this.name, this.description));
/* 23 */     adjustPosition(slot);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {}
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 32 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 37 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\PotionSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */