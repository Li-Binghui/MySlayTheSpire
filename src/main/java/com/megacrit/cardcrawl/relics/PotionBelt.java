/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.PotionSlot;
/*    */ 
/*    */ public class PotionBelt extends AbstractRelic {
/*    */   public static final String ID = "Potion Belt";
/*    */   
/*    */   public PotionBelt() {
/* 11 */     super("Potion Belt", "potion_belt.png", RelicTier.COMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 21 */     AbstractDungeon.player.potionSlots += 2;
/* 22 */     AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 2));
/* 23 */     AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 28 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new PotionBelt();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\PotionBelt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */