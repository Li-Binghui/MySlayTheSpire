/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class FairyPotion extends AbstractPotion {
/*    */   public static final String POTION_ID = "FairyPotion";
/* 11 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FairyPotion");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FairyPotion() {
/* 17 */     super(potionStrings.NAME, "FairyPotion", PotionRarity.RARE, PotionSize.FAIRY, PotionColor.FAIRY);
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
/* 31 */     float percent = this.potency / 100.0F;
/* 32 */     int healAmt = (int)(AbstractDungeon.player.maxHealth * percent);
/*    */     
/* 34 */     if (healAmt < 1) {
/* 35 */       healAmt = 1;
/*    */     }
/*    */     
/* 38 */     AbstractDungeon.player.heal(healAmt, true);
/* 39 */     AbstractDungeon.topPanel.destroyPotion(this.slot);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 49 */     return 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 54 */     return new FairyPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\FairyPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */