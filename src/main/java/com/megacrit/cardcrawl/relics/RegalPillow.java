/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RegalPillow
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Regal Pillow";
/*    */   public static final int HEAL_AMT = 15;
/*    */   
/*    */   public RegalPillow() {
/* 12 */     super("Regal Pillow", "regal_pillow.png", RelicTier.COMMON, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + '\017' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 22 */     return new RegalPillow();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 27 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\RegalPillow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */