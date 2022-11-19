/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ 
/*    */ public class Strawberry
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Strawberry";
/*    */   private static final int HP_AMT = 7;
/*    */   
/*    */   public Strawberry() {
/* 12 */     super("Strawberry", "strawberry.png", RelicTier.COMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + '\007' + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 22 */     AbstractDungeon.player.increaseMaxHp(7, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new Strawberry();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Strawberry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */