/*    */ package com.megacrit.cardcrawl.potions;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.EssenceOfDarknessAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class EssenceOfDarkness extends AbstractPotion {
/* 16 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("EssenceOfDarkness"); public static final String POTION_ID = "EssenceOfDarkness";
/*    */   
/*    */   public EssenceOfDarkness() {
/* 19 */     super(potionStrings.NAME, "EssenceOfDarkness", PotionRarity.RARE, PotionSize.MOON, PotionColor.SMOKE);
/* 20 */     this.labOutlineColor = Settings.BLUE_RELIC_COLOR;
/* 21 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 26 */     this.potency = getPotency();
/* 27 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 28 */     this.tips.clear();
/* 29 */     this.tips.add(new PowerTip(this.name, this.description));
/* 30 */     this.tips.add(new PowerTip(
/*    */           
/* 32 */           TipHelper.capitalize(GameDictionary.CHANNEL.NAMES[0]), (String)GameDictionary.keywords
/* 33 */           .get(GameDictionary.CHANNEL.NAMES[0])));
/* 34 */     this.tips.add(new PowerTip(
/*    */           
/* 36 */           TipHelper.capitalize(GameDictionary.DARK.NAMES[0]), (String)GameDictionary.keywords
/* 37 */           .get(GameDictionary.DARK.NAMES[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 42 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 43 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 44 */       addToBot((AbstractGameAction)new EssenceOfDarknessAction(this.potency));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 50 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 55 */     return new EssenceOfDarkness();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\EssenceOfDarkness.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */