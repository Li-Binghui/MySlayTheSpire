/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class MonsterRoomBoss
/*    */   extends MonsterRoom {
/* 11 */   private static final Logger logger = LogManager.getLogger(MonsterRoomBoss.class.getName());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 20 */     this.monsters = CardCrawlGame.dungeon.getBoss();
/* 21 */     logger.info("BOSSES: " + AbstractDungeon.bossList.size());
/* 22 */     CardCrawlGame.metricData.path_taken.add("BOSS");
/* 23 */     CardCrawlGame.music.silenceBGM();
/* 24 */     AbstractDungeon.bossList.remove(0);
/*    */     
/* 26 */     if (this.monsters != null) {
/* 27 */       this.monsters.init();
/*    */     }
/*    */     
/* 30 */     waitTimer = 0.1F;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard.CardRarity getCardRarity(int roll) {
/* 35 */     return AbstractCard.CardRarity.RARE;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\MonsterRoomBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */