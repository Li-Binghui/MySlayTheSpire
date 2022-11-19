/*    */ package com.megacrit.cardcrawl.rooms;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class RestRoom
/*    */   extends AbstractRoom
/*    */ {
/*    */   public long fireSoundId;
/* 13 */   public static long lastFireSoundId = 0L;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CampfireUI campfireUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPlayerEntry() {
/* 26 */     if (!AbstractDungeon.id.equals("TheEnding")) {
/* 27 */       CardCrawlGame.music.silenceBGM();
/*    */     }
/* 29 */     this.fireSoundId = CardCrawlGame.sound.playAndLoop("REST_FIRE_WET");
/* 30 */     lastFireSoundId = this.fireSoundId;
/* 31 */     this.campfireUI = new CampfireUI();
/*    */     
/* 33 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 34 */       r.onEnterRestRoom();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard.CardRarity getCardRarity(int roll) {
/* 40 */     return getCardRarity(roll, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 45 */     super.update();
/* 46 */     if (this.campfireUI != null) {
/* 47 */       this.campfireUI.update();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fadeIn() {
/* 55 */     if (!AbstractDungeon.id.equals("TheEnding")) {
/* 56 */       CardCrawlGame.music.unsilenceBGM();
/*    */     }
/*    */   }
/*    */   
/*    */   public void cutFireSound() {
/* 61 */     CardCrawlGame.sound.fadeOut("REST_FIRE_WET", ((RestRoom)AbstractDungeon.getCurrRoom()).fireSoundId);
/*    */   }
/*    */   
/*    */   public void updateAmbience() {
/* 65 */     CardCrawlGame.sound.adjustVolume("REST_FIRE_WET", this.fireSoundId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 71 */     if (this.campfireUI != null) {
/* 72 */       this.campfireUI.render(sb);
/*    */     }
/* 74 */     super.render(sb);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\RestRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */