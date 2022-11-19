/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.DiscardPileViewScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MonsterRoom
/*    */   extends AbstractRoom
/*    */ {
/* 25 */   public DiscardPileViewScreen discardPileViewScreen = new DiscardPileViewScreen();
/*    */   
/*    */   public static final float COMBAT_WAIT_TIME = 0.1F;
/*    */   
/*    */   public void dropReward() {
/* 30 */     if (ModHelper.isModEnabled("Vintage") && 
/* 31 */       !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) && 
/* 32 */       !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
/*    */ 
/*    */       
/* 35 */       AbstractRelic.RelicTier tier = returnRandomRelicTier();
/* 36 */       addRelicToRewards(tier);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private AbstractRelic.RelicTier returnRandomRelicTier() {
/* 42 */     int roll = AbstractDungeon.relicRng.random(0, 99);
/*    */ 
/*    */     
/* 45 */     if (roll < 50) {
/* 46 */       return AbstractRelic.RelicTier.COMMON;
/*    */     }
/* 48 */     if (roll > 85) {
/* 49 */       return AbstractRelic.RelicTier.RARE;
/*    */     }
/*    */ 
/*    */     
/* 53 */     return AbstractRelic.RelicTier.UNCOMMON;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 58 */     playBGM(null);
/* 59 */     if (this.monsters == null) {
/* 60 */       this.monsters = CardCrawlGame.dungeon.getMonsterForRoomCreation();
/* 61 */       this.monsters.init();
/*    */     } 
/* 63 */     waitTimer = 0.1F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMonster(MonsterGroup m) {
/* 70 */     this.monsters = m;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\MonsterRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */