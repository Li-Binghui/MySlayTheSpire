/*    */ package com.megacrit.cardcrawl.potions;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class HeartOfIron extends AbstractPotion {
/* 15 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("HeartOfIron"); public static final String POTION_ID = "HeartOfIron";
/*    */   
/*    */   public HeartOfIron() {
/* 18 */     super(potionStrings.NAME, "HeartOfIron", PotionRarity.RARE, PotionSize.HEART, PotionColor.SWIFT);
/* 19 */     this.labOutlineColor = Settings.RED_RELIC_COLOR;
/* 20 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 25 */     this.potency = getPotency();
/* 26 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 27 */     this.tips.clear();
/* 28 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 33 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 34 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 35 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new MetallicizePower((AbstractCreature)abstractPlayer, this.potency), this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 42 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 47 */     return new HeartOfIron();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\HeartOfIron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */