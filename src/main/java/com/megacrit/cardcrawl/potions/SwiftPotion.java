/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ 
/*    */ public class SwiftPotion extends AbstractPotion {
/* 12 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Swift Potion");
/*    */   
/*    */   public static final String POTION_ID = "Swift Potion";
/*    */ 
/*    */   
/*    */   public SwiftPotion() {
/* 18 */     super(potionStrings.NAME, "Swift Potion", PotionRarity.COMMON, PotionSize.H, PotionColor.SWIFT);
/* 19 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 24 */     this.potency = getPotency();
/* 25 */     this.description = potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[2];
/* 26 */     this.tips.clear();
/* 27 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 32 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, this.potency));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 37 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 42 */     return new SwiftPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\SwiftPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */