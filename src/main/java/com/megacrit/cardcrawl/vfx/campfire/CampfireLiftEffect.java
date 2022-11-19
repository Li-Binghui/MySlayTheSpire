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
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.rooms.RestRoom;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CampfireLiftEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float DUR = 2.0F;
/*    */   private boolean hasTrained = false;
/* 25 */   private Color screenColor = AbstractDungeon.fadeColor.cpy();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CampfireLiftEffect() {
/* 31 */     this.duration = 2.0F;
/* 32 */     this.screenColor.a = 0.0F;
/* 33 */     ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 38 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 39 */     updateBlackScreenColor();
/*    */ 
/*    */     
/* 42 */     if (this.duration < 1.0F && !this.hasTrained) {
/* 43 */       this.hasTrained = true;
/* 44 */       if (AbstractDungeon.player.hasRelic("Girya")) {
/* 45 */         AbstractDungeon.player.getRelic("Girya").flash();
/* 46 */         (AbstractDungeon.player.getRelic("Girya")).counter++;
/* 47 */         CardCrawlGame.sound.play("ATTACK_HEAVY");
/* 48 */         CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, true);
/* 49 */         CardCrawlGame.metricData.addCampfireChoiceData("LIFT", 
/*    */             
/* 51 */             Integer.toString((AbstractDungeon.player.getRelic("Girya")).counter));
/*    */       } 
/* 53 */       AbstractDungeon.topLevelEffects.add(new BorderFlashEffect(new Color(0.8F, 0.6F, 0.1F, 0.0F)));
/*    */     } 
/*    */ 
/*    */     
/* 57 */     if (this.duration < 0.0F) {
/* 58 */       this.isDone = true;
/* 59 */       ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
/* 60 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void updateBlackScreenColor() {
/* 69 */     if (this.duration > 1.5F) {
/* 70 */       this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
/*    */     
/*    */     }
/* 73 */     else if (this.duration < 1.0F) {
/* 74 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
/*    */     } else {
/* 76 */       this.screenColor.a = 1.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 82 */     sb.setColor(this.screenColor);
/* 83 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireLiftEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */