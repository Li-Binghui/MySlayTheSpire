/*    */ package com.megacrit.cardcrawl.potions;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class RegenPotion extends AbstractPotion {
/* 16 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Regen Potion");
/*    */   
/*    */   public static final String POTION_ID = "Regen Potion";
/*    */ 
/*    */   
/*    */   public RegenPotion() {
/* 22 */     super(potionStrings.NAME, "Regen Potion", PotionRarity.UNCOMMON, PotionSize.HEART, PotionColor.WHITE);
/* 23 */     this.isThrown = false;
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
/* 34 */           TipHelper.capitalize(GameDictionary.REGEN.NAMES[0]), (String)GameDictionary.keywords
/* 35 */           .get(GameDictionary.REGEN.NAMES[0])));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 41 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 42 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new RegenPower((AbstractCreature)AbstractDungeon.player, this.potency), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 53 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 58 */     return new RegenPotion();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\RegenPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */