/*    */ package com.megacrit.cardcrawl.potions;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class WeakenPotion extends AbstractPotion {
/* 15 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Weak Potion");
/*    */   
/*    */   public static final String POTION_ID = "Weak Potion";
/*    */ 
/*    */   
/*    */   public WeakenPotion() {
/* 21 */     super(potionStrings.NAME, "Weak Potion", PotionRarity.COMMON, PotionSize.H, PotionColor.WEAK);
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
/* 34 */           TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), (String)GameDictionary.keywords
/* 35 */           .get(GameDictionary.WEAK.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 40 */     addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower(target, this.potency, false), this.potency));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 45 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 50 */     return new WeakenPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\WeakenPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */