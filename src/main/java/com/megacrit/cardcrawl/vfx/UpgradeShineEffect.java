/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ 
/*    */ public class UpgradeShineEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private boolean clang1 = false;
/*    */   private boolean clang2 = false;
/*    */   
/*    */   public UpgradeShineEffect(float x, float y) {
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.duration = 0.8F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration < 0.6F && !this.clang1) {
/* 25 */       CardCrawlGame.sound.play("CARD_UPGRADE");
/* 26 */       this.clang1 = true;
/* 27 */       clank(this.x - 80.0F * Settings.scale, this.y + 0.0F * Settings.scale);
/* 28 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
/*    */     } 
/*    */     
/* 31 */     if (this.duration < 0.2F && !this.clang2) {
/* 32 */       this.clang2 = true;
/* 33 */       clank(this.x + 90.0F * Settings.scale, this.y - 110.0F * Settings.scale);
/* 34 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
/*    */     } 
/*    */     
/* 37 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 38 */     if (this.duration < 0.0F) {
/* 39 */       clank(this.x + 30.0F * Settings.scale, this.y + 120.0F * Settings.scale);
/* 40 */       this.isDone = true;
/* 41 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void clank(float x, float y) {
/* 46 */     AbstractDungeon.topLevelEffectsQueue.add(new UpgradeHammerImprintEffect(x, y));
/*    */     
/* 48 */     if (Settings.DISABLE_EFFECTS) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     for (int i = 0; i < 30; i++)
/* 53 */       AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(x + 
/*    */             
/* 55 */             MathUtils.random(-10.0F, 10.0F) * Settings.scale, y + 
/* 56 */             MathUtils.random(-10.0F, 10.0F) * Settings.scale)); 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\UpgradeShineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */