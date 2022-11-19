/*    */ package com.megacrit.cardcrawl.potions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class SneckoOil extends AbstractPotion {
/* 14 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("SneckoOil");
/*    */   
/*    */   public static final String POTION_ID = "SneckoOil";
/*    */ 
/*    */   
/*    */   public SneckoOil() {
/* 20 */     super(potionStrings.NAME, "SneckoOil", PotionRarity.RARE, PotionSize.SNECKO, PotionColor.SNECKO);
/* 21 */     this.isThrown = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initializeData() {
/* 26 */     this.potency = getPotency();
/* 27 */     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
/* 28 */     this.tips.clear();
/* 29 */     this.tips.add(new PowerTip(this.name, this.description));
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractCreature target) {
/* 34 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 35 */       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, this.potency));
/* 36 */       addToBot((AbstractGameAction)new RandomizeHandCostAction());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPotency(int ascensionLevel) {
/* 42 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPotion makeCopy() {
/* 47 */     return new SneckoOil();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\SneckoOil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */