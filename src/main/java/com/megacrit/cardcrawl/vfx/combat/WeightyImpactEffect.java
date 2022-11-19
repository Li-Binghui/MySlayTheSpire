/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
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
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*     */ import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
/*     */ 
/*     */ public class WeightyImpactEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float EFFECT_DUR = 1.0F;
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public WeightyImpactEffect(float x, float y) {
/*  27 */     this(x, y, new Color(1.0F, 1.0F, 0.1F, 0.0F));
/*     */   }
/*     */   private float targetY; private static TextureAtlas.AtlasRegion img; private boolean impactHook = false;
/*     */   public WeightyImpactEffect(float x, float y, Color newColor) {
/*  31 */     if (img == null) {
/*  32 */       img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");
/*     */     }
/*     */ 
/*     */     
/*  36 */     this.scale = Settings.scale;
/*  37 */     this.x = x - img.packedWidth / 2.0F;
/*  38 */     this.y = Settings.HEIGHT - img.packedHeight / 2.0F;
/*  39 */     this.duration = 1.0F;
/*  40 */     this.targetY = y - 180.0F * Settings.scale;
/*  41 */     this.rotation = MathUtils.random(-1.0F, 1.0F);
/*  42 */     this.color = newColor;
/*     */   }
/*     */   
/*     */   public void update() {
/*  46 */     this.y = Interpolation.fade.apply(Settings.HEIGHT, this.targetY, 1.0F - this.duration / 1.0F);
/*     */     
/*  48 */     this.scale += Gdx.graphics.getDeltaTime();
/*  49 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  51 */     if (this.duration < 0.0F) {
/*  52 */       this.isDone = true;
/*  53 */       CardCrawlGame.sound.playA("ATTACK_IRON_2", -0.5F);
/*  54 */     } else if (this.duration < 0.2F) {
/*  55 */       if (!this.impactHook) {
/*  56 */         this.impactHook = true;
/*  57 */         AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.ORANGE));
/*  58 */         CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true); int i;
/*  59 */         for (i = 0; i < 5; i++) {
/*  60 */           AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(this.x + img.packedWidth / 2.0F, this.y + img.packedWidth / 2.0F));
/*     */         }
/*     */         
/*  63 */         for (i = 0; i < 30; i++) {
/*  64 */           AbstractDungeon.effectsQueue.add(new UpgradeShineParticleEffect(this.x + 
/*     */                 
/*  66 */                 MathUtils.random(-100.0F, 100.0F) * Settings.scale + img.packedWidth / 2.0F, this.y + 
/*  67 */                 MathUtils.random(-50.0F, 120.0F) * Settings.scale + img.packedHeight / 2.0F));
/*     */         }
/*     */       } 
/*  70 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, 0.2F / this.duration);
/*     */     } else {
/*  72 */       this.color.a = Interpolation.pow2Out.apply(0.6F, 0.0F, this.duration / 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  78 */     sb.setBlendFunction(770, 1);
/*  79 */     this.color.g = 1.0F;
/*  80 */     sb.setColor(this.color);
/*  81 */     sb.draw((TextureRegion)img, this.x, this.y + 140.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (this.duration + 0.2F) * 5.0F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  89 */         MathUtils.random(0.99F, 1.01F) * 0.3F, this.scale * 
/*  90 */         MathUtils.random(0.99F, 1.01F) * 2.0F * (this.duration + 0.8F), this.rotation);
/*     */     
/*  92 */     this.color.g = 0.6F;
/*  93 */     sb.setColor(this.color);
/*  94 */     sb.draw((TextureRegion)img, this.x, this.y + 40.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (this.duration + 0.2F) * 5.0F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 102 */         MathUtils.random(0.99F, 1.01F) * 0.7F, this.scale * 
/* 103 */         MathUtils.random(0.99F, 1.01F) * 1.3F * (this.duration + 0.8F), this.rotation);
/*     */     
/* 105 */     this.color.g = 0.2F;
/* 106 */     sb.setColor(this.color);
/* 107 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (this.duration + 0.2F) * 5.0F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 115 */         MathUtils.random(0.99F, 1.01F), this.scale * 
/* 116 */         MathUtils.random(0.99F, 1.01F) * (this.duration + 0.8F), this.rotation);
/*     */     
/* 118 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\WeightyImpactEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */