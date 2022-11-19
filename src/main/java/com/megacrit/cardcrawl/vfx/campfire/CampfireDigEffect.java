/*    */ package com.megacrit.cardcrawl.vfx.campfire;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class CampfireDigEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float DUR = 2.0F;
/*    */   private boolean hasDug = false;
/* 20 */   private Color screenColor = AbstractDungeon.fadeColor.cpy();
/*    */   
/*    */   public CampfireDigEffect() {
/* 23 */     this.duration = 2.0F;
/* 24 */     this.screenColor.a = 0.0F;
/* 25 */     ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 31 */     updateBlackScreenColor();
/*    */ 
/*    */     
/* 34 */     if (this.duration < 1.0F && !this.hasDug) {
/* 35 */       this.hasDug = true;
/* 36 */       CardCrawlGame.sound.play("SHOVEL");
/*    */       
/* 38 */       (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 39 */       (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(
/* 40 */             AbstractDungeon.returnRandomRelic(AbstractDungeon.returnRandomRelicTier())));
/* 41 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 42 */       AbstractDungeon.combatRewardScreen.open();
/* 43 */       CardCrawlGame.metricData.addCampfireChoiceData("DIG");
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     if (this.duration < 0.0F) {
/* 53 */       this.isDone = true;
/* 54 */       ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
/* 55 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void updateBlackScreenColor() {
/* 64 */     if (this.duration > 1.5F) {
/* 65 */       this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
/*    */     
/*    */     }
/* 68 */     else if (this.duration < 1.0F) {
/* 69 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
/*    */     } else {
/* 71 */       this.screenColor.a = 1.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 77 */     sb.setColor(this.screenColor);
/* 78 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireDigEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */