/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ 
/*    */ public class Pear
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Pear";
/*    */   private static final int HP_AMT = 10;
/*    */   
/*    */   public Pear() {
/* 12 */     super("Pear", "pear.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + '\n' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 22 */     AbstractDungeon.player.increaseMaxHp(10, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new Pear();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Pear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */