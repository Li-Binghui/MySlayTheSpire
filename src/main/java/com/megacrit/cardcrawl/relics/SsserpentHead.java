/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class SsserpentHead
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "SsserpentHead";
/*    */   private static final int GOLD_AMT = 50;
/*    */   
/*    */   public SsserpentHead() {
/* 12 */     super("SsserpentHead", "serpentHead.png", RelicTier.SPECIAL, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + '2' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom(AbstractRoom room) {
/* 22 */     if (room instanceof com.megacrit.cardcrawl.rooms.EventRoom) {
/* 23 */       flash();
/* 24 */       AbstractDungeon.player.gainGold(50);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new SsserpentHead();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\SsserpentHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */