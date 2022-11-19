/*    */ package com.megacrit.cardcrawl.potions;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class FocusPotion extends AbstractPotion {
/* 17 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FocusPotion");
/*    */   
/*    */   public static final String POTION_ID = "FocusPotion";
/*    */ 
/*    */   
/*    */   public FocusPotion() {
/* 23 */     super(potionStrings.NAME, "FocusPotion", PotionRarity.COMMON, PotionSize.S, PotionColor.SWIFT);
/* 24 */     this.labOutlineColor = Settings.BLUE_RELIC_COLOR;
/* 25 */     this.isThrown = false;
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
/* 36 */           TipHelper.capitalize(GameDictionary.FOCUS.NAMES[0]), (String)GameDictionary.keywords
/* 37 */           .get(GameDictionary.FOCUS.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 42 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 43 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 44 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new FocusPower((AbstractCreature)abstractPlayer, this.potency), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 50 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 55 */     return new FocusPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\FocusPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */