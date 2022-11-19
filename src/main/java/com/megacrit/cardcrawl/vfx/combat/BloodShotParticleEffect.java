/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/*    */
/*    */ public class BloodShotParticleEffect extends AbstractGameEffect {
/*    */   private float sX;
/*    */   private float sY;
/*    */   private float tX;
/*    */   private float tY;
/*    */   private float x;
/*    */   
/*    */   public BloodShotParticleEffect(float sX, float sY, float tX, float tY) {
/* 23 */     this.img = ImageMaster.GLOW_SPARK_2;
/*    */     
/* 25 */     this.sX = sX + MathUtils.random(-90.0F, 90.0F) * Settings.scale;
/* 26 */     this.sY = sY + MathUtils.random(-90.0F, 90.0F) * Settings.scale;
/* 27 */     this.tX = tX + MathUtils.random(-50.0F, 50.0F) * Settings.scale;
/* 28 */     this.tY = tY + MathUtils.random(-50.0F, 50.0F) * Settings.scale;
/* 29 */     this.vX = this.sX + MathUtils.random(-200.0F, 200.0F) * Settings.scale;
/* 30 */     this.vY = this.sY + MathUtils.random(-200.0F, 200.0F) * Settings.scale;
/* 31 */     this.x = this.sX;
/* 32 */     this.y = this.sY;
/*    */     
/* 34 */     this.scale = 0.01F;
/* 35 */     this.startingDuration = 0.8F;
/* 36 */     this.duration = this.startingDuration;
/* 37 */     this.renderBehind = MathUtils.randomBoolean(0.2F);
/* 38 */     this.color = new Color(1.0F, 0.1F, MathUtils.random(0.2F, 0.5F), 1.0F);
/*    */   }
/*    */   private float y; private float vY; private float vX; private TextureAtlas.AtlasRegion img; private boolean activated = false;
/*    */   public void update() {
/* 42 */     if (this.duration > this.startingDuration / 2.0F) {
/* 43 */       this.scale = Interpolation.pow3In.apply(2.5F, this.startingDuration / 2.0F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F) * Settings.scale;
/*    */ 
/*    */ 
/*    */       
/* 47 */       this.x = Interpolation.swingIn.apply(this.sX, this.vX, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/* 48 */       this.y = Interpolation.swingIn.apply(this.sY, this.vY, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*    */     } else {
/* 50 */       this.scale = Interpolation.pow3Out.apply(2.0F, 2.5F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/* 51 */       this.x = Interpolation.swingOut.apply(this.tX, this.vX, this.duration / this.startingDuration / 2.0F);
/* 52 */       this.y = Interpolation.swingOut.apply(this.tY, this.vY, this.duration / this.startingDuration / 2.0F);
/*    */     } 
/*    */     
/* 55 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 57 */     if (this.duration < this.startingDuration / 2.0F && !this.activated) {
/* 58 */       this.activated = true;
/* 59 */       this.sX = this.x;
/* 60 */       this.sY = this.y;
/*    */     } 
/*    */     
/* 63 */     if (this.duration < 0.0F) {
/* 64 */       AbstractDungeon.effectsQueue.add(new AdditiveSlashImpactEffect(this.tX, this.tY, this.color.cpy()));
/* 65 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, MathUtils.randomBoolean());
/* 66 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 72 */     sb.setColor(Color.BLACK);
/* 73 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 2.0F, this.scale * 2.0F, this.rotation);
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
/* 85 */     sb.setColor(this.color);
/* 86 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BloodShotParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */