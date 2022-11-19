/*    */ package com.megacrit.cardcrawl.potions;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */ 
/*    */ public class PoisonPotion extends AbstractPotion {
/* 16 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Poison Potion");
/*    */   
/*    */   public static final String POTION_ID = "Poison Potion";
/*    */ 
/*    */   
/*    */   public PoisonPotion() {
/* 22 */     super(potionStrings.NAME, "Poison Potion", PotionRarity.COMMON, PotionSize.M, PotionColor.POISON);
/* 23 */     this.labOutlineColor = Settings.GREEN_RELIC_COLOR;
/* 24 */     this.isThrown = true;
/* 25 */     this.targetRequired = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 30 */     this.potency = getPotency();
/* 31 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/* 34 */     this.tips.add(new PowerTip(
/*    */           
/* 36 */           TipHelper.capitalize(GameDictionary.POISON.NAMES[0]), (String)GameDictionary.keywords
/* 37 */           .get(GameDictionary.POISON.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PoisonPower(target, (AbstractCreature)AbstractDungeon.player, this.potency), this.potency));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 52 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 57 */     return new PoisonPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\PoisonPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */