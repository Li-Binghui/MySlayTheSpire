/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class TinyChest extends AbstractRelic {
/*    */   public static final String ID = "Tiny Chest";
/*    */   public static final int ROOM_COUNT = 4;
/*    */   
/*    */   public TinyChest() {
/* 11 */     super("Tiny Chest", "tinyChest.png", RelicTier.COMMON, LandingSound.SOLID);
/* 12 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + '\004' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 22 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 27 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 35);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new TinyChest();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\TinyChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */