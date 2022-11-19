/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ 
/*    */ public class Mango
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Mango";
/*    */   private static final int HP_AMT = 14;
/*    */   
/*    */   public Mango() {
/* 12 */     super("Mango", "mango.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + '\016' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 22 */     AbstractDungeon.player.increaseMaxHp(14, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new Mango();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Mango.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */