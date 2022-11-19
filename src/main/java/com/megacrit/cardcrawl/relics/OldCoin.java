/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class OldCoin
/*    */   extends AbstractRelic
/*    */ {
/*    */   public static final String ID = "Old Coin";
/*    */   private static final int GOLD_AMT = 300;
/*    */   
/*    */   public OldCoin() {
/* 14 */     super("Old Coin", "oldCoin.png", RelicTier.RARE, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 24 */     CardCrawlGame.sound.play("GOLD_GAIN");
/* 25 */     AbstractDungeon.player.gainGold(300);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 30 */     return ((Settings.isEndless || AbstractDungeon.floorNum <= 48) && 
/* 31 */       !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 36 */     return new OldCoin();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\OldCoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */