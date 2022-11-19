/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.optionCards.ChooseCalm;
/*    */ import com.megacrit.cardcrawl.cards.optionCards.ChooseWrath;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class StancePotion extends AbstractPotion {
/*    */   public static final String POTION_ID = "StancePotion";
/* 20 */   public static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("StancePotion");
/*    */   
/*    */   public StancePotion() {
/* 23 */     super(potionStrings.NAME, "StancePotion", PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.WEAK);
/* 24 */     this.labOutlineColor = Settings.PURPLE_RELIC_COLOR;
/* 25 */     this.description = potionStrings.DESCRIPTIONS[0];
/* 26 */     this.isThrown = false;
/* 27 */     this.tips.clear();
/* 28 */     this.tips.add(new PowerTip(this.name, this.description));
/* 29 */     this.tips.add(new PowerTip(
/*    */           
/* 31 */           TipHelper.capitalize(GameDictionary.CALM.NAMES[0]), (String)GameDictionary.keywords
/* 32 */           .get(GameDictionary.CALM.NAMES[0])));
/* 33 */     this.tips.add(new PowerTip(
/*    */           
/* 35 */           TipHelper.capitalize(GameDictionary.WRATH.NAMES[0]), (String)GameDictionary.keywords
/* 36 */           .get(GameDictionary.WRATH.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 41 */     InputHelper.moveCursorToNeutralPosition();
/* 42 */     ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
/* 43 */     stanceChoices.add(new ChooseWrath());
/* 44 */     stanceChoices.add(new ChooseCalm());
/* 45 */     addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 50 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 55 */     return new StancePotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\StancePotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */