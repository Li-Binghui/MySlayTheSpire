/*    */ package com.megacrit.cardcrawl.potions;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ import com.megacrit.cardcrawl.powers.LoseDexterityPower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class SpeedPotion extends AbstractPotion {
/* 17 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("SpeedPotion");
/*    */   
/*    */   public static final String POTION_ID = "SpeedPotion";
/*    */ 
/*    */   
/*    */   public SpeedPotion() {
/* 23 */     super(potionStrings.NAME, "SpeedPotion", PotionRarity.COMMON, PotionSize.BOLT, PotionColor.SKILL);
/* 24 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 29 */     this.potency = getPotency();
/* 30 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[2];
/*    */     
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/* 34 */     this.tips.add(new PowerTip(
/*    */           
/* 36 */           TipHelper.capitalize(GameDictionary.DEXTERITY.NAMES[0]), (String)GameDictionary.keywords
/* 37 */           .get(GameDictionary.DEXTERITY.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 42 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 43 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 44 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DexterityPower((AbstractCreature)abstractPlayer, this.potency), this.potency));
/*    */       
/* 46 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new LoseDexterityPower((AbstractCreature)abstractPlayer, this.potency), this.potency));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 53 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 58 */     return new SpeedPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\SpeedPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */