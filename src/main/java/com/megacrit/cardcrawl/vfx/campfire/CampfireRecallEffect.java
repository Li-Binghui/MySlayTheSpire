/*    */ package com.megacrit.cardcrawl.vfx.campfire;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.rooms.RestRoom;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;
/*    */ 
/*    */ public class CampfireRecallEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float DUR = 2.0F;
/*    */   private boolean hasRecalled = false;
/* 21 */   private Color screenColor = AbstractDungeon.fadeColor.cpy();
/*    */   
/*    */   public CampfireRecallEffect() {
/* 24 */     this.duration = 2.0F;
/* 25 */     this.screenColor.a = 0.0F;
/* 26 */     ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 32 */     updateBlackScreenColor();
/*    */ 
/*    */     
/* 35 */     if (this.duration < 1.0F && !this.hasRecalled) {
/* 36 */       this.hasRecalled = true;
/* 37 */       CardCrawlGame.sound.play("ATTACK_MAGIC_SLOW_2");
/*    */       
/* 39 */       (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 40 */       AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.RED));
/* 41 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 42 */       CardCrawlGame.metricData.addCampfireChoiceData("RECALL");
/*    */     } 
/*    */ 
/*    */     
/* 46 */     if (this.duration < 0.0F) {
/* 47 */       this.isDone = true;
/* 48 */       ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
/* 49 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void updateBlackScreenColor() {
/* 58 */     if (this.duration > 1.5F) {
/* 59 */       this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
/*    */     
/*    */     }
/* 62 */     else if (this.duration < 1.0F) {
/* 63 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
/*    */     } else {
/* 65 */       this.screenColor.a = 1.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 71 */     sb.setColor(this.screenColor);
/* 72 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireRecallEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */