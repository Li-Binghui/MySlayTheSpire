/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.vfx.combat.AdditiveSlashImpactEffect;
/*     */ 
/*     */ public class CollectorStakeEffect extends AbstractGameEffect {
/*     */   private static TextureAtlas.AtlasRegion img;
/*     */   private float x;
/*     */   private float y;
/*     */   private float sX;
/*     */   private float sY;
/*     */   
/*     */   public CollectorStakeEffect(float x, float y) {
/*  24 */     if (img == null) {
/*  25 */       img = ImageMaster.vfxAtlas.findRegion("combat/stake");
/*     */     }
/*     */     
/*  28 */     float randomAngle = 0.017453292F * MathUtils.random(-50.0F, 230.0F);
/*  29 */     this.x = MathUtils.cos(randomAngle) * MathUtils.random(200.0F, 600.0F) * Settings.scale + x;
/*  30 */     this.y = MathUtils.sin(randomAngle) * MathUtils.random(200.0F, 500.0F) * Settings.scale + y;
/*  31 */     this.duration = 1.0F;
/*  32 */     this.scale = 0.01F;
/*  33 */     this.targetScale = MathUtils.random(0.4F, 1.1F);
/*     */     
/*  35 */     this.targetAngle = MathUtils.atan2(y - this.y, x - this.x) * 57.295776F + 90.0F;
/*  36 */     this.startingAngle = MathUtils.random(0.0F, 360.0F);
/*  37 */     this.rotation = this.startingAngle;
/*     */     
/*  39 */     this.x -= (img.packedWidth / 2);
/*  40 */     this.y -= (img.packedHeight / 2);
/*  41 */     this.sX = this.x;
/*  42 */     this.sY = this.y;
/*  43 */     this.tX = x - (img.packedWidth / 2);
/*  44 */     this.tY = y - (img.packedHeight / 2);
/*     */     
/*  46 */     this.color = new Color(MathUtils.random(0.5F, 1.0F), MathUtils.random(0.0F, 0.4F), MathUtils.random(0.5F, 1.0F), 0.0F);
/*     */   }
/*     */   private float tX; private float tY; private float targetAngle; private float startingAngle; private float targetScale; private boolean shownSlash = false;
/*     */   
/*     */   public void update() {
/*  51 */     this.rotation = Interpolation.elasticIn.apply(this.targetAngle, this.startingAngle, this.duration);
/*  52 */     if (this.duration > 0.5F) {
/*  53 */       this.scale = Interpolation.elasticIn.apply(this.targetScale, this.targetScale * 10.0F, (this.duration - 0.5F) * 2.0F) * Settings.scale;
/*     */       
/*  55 */       this.color.a = Interpolation.fade.apply(0.6F, 0.0F, (this.duration - 0.5F) * 2.0F);
/*     */     } else {
/*  57 */       this.x = Interpolation.exp10Out.apply(this.tX, this.sX, this.duration * 2.0F);
/*  58 */       this.y = Interpolation.exp10Out.apply(this.tY, this.sY, this.duration * 2.0F);
/*     */     } 
/*     */     
/*  61 */     if (this.duration < 0.05F && 
/*  62 */       !this.shownSlash) {
/*  63 */       AbstractDungeon.effectsQueue.add(new AdditiveSlashImpactEffect(this.tX + img.packedWidth / 2.0F, this.tY + img.packedHeight / 2.0F, this.color
/*  64 */             .cpy()));
/*  65 */       this.shownSlash = true;
/*     */     } 
/*     */ 
/*     */     
/*  69 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  70 */     if (this.duration < 0.0F) {
/*  71 */       this.isDone = true;
/*  72 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, MathUtils.randomBoolean());
/*  73 */       CardCrawlGame.sound.play("ATTACK_FAST", 0.2F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  79 */     sb.setBlendFunction(770, 1);
/*  80 */     sb.setColor(this.color);
/*  81 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  89 */         MathUtils.random(1.0F, 1.2F), this.scale * 
/*  90 */         MathUtils.random(1.0F, 1.2F), this.rotation);
/*     */     
/*  92 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 100 */         MathUtils.random(0.9F, 1.1F), this.scale * 
/* 101 */         MathUtils.random(0.9F, 1.1F), this.rotation);
/*     */     
/* 103 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\CollectorStakeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */