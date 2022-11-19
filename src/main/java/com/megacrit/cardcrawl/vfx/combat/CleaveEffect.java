/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class CleaveEffect extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*  18 */   private float fadeInTimer = 0.05F; private float vX; private static final float FADE_IN_TIME = 0.05F; private static final float FADE_OUT_TIME = 0.4F; private float fadeOutTimer = 0.4F;
/*     */   private float stallTimer;
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   
/*     */   public CleaveEffect(boolean usedByMonster) {
/*  23 */     this.img = ImageMaster.vfxAtlas.findRegion("combat/cleave");
/*  24 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  25 */     this.x = Settings.WIDTH * 0.3F - this.img.packedWidth / 2.0F;
/*  26 */     this.y = AbstractDungeon.floorY + 100.0F * Settings.scale - this.img.packedHeight / 2.0F;
/*  27 */     this.vX = 100.0F * Settings.scale;
/*  28 */     this.stallTimer = MathUtils.random(0.0F, 0.2F);
/*  29 */     this.scale = 1.2F * Settings.scale;
/*  30 */     this.rotation = MathUtils.random(-5.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public CleaveEffect() {
/*  34 */     this.img = ImageMaster.vfxAtlas.findRegion("combat/cleave");
/*  35 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  36 */     this.x = Settings.WIDTH * 0.7F - this.img.packedWidth / 2.0F;
/*  37 */     this.y = AbstractDungeon.floorY + 100.0F * Settings.scale - this.img.packedHeight / 2.0F;
/*  38 */     this.vX = 100.0F * Settings.scale;
/*  39 */     this.stallTimer = MathUtils.random(0.0F, 0.2F);
/*  40 */     this.scale = 1.2F * Settings.scale;
/*  41 */     this.rotation = MathUtils.random(-5.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  46 */     if (this.stallTimer > 0.0F) {
/*  47 */       this.stallTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  52 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/*  53 */     this.rotation += MathUtils.random(-0.5F, 0.5F);
/*  54 */     this.scale += 0.005F * Settings.scale;
/*     */ 
/*     */     
/*  57 */     if (this.fadeInTimer != 0.0F) {
/*  58 */       this.fadeInTimer -= Gdx.graphics.getDeltaTime();
/*  59 */       if (this.fadeInTimer < 0.0F) {
/*  60 */         this.fadeInTimer = 0.0F;
/*     */       }
/*  62 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.fadeInTimer / 0.05F);
/*  63 */     } else if (this.fadeOutTimer != 0.0F) {
/*  64 */       this.fadeOutTimer -= Gdx.graphics.getDeltaTime();
/*  65 */       if (this.fadeOutTimer < 0.0F) {
/*  66 */         this.fadeOutTimer = 0.0F;
/*     */       }
/*  68 */       this.color.a = Interpolation.pow2.apply(0.0F, 1.0F, this.fadeOutTimer / 0.4F);
/*     */     } else {
/*  70 */       this.isDone = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  76 */     sb.setColor(this.color);
/*  77 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     sb.setBlendFunction(770, 1);
/*  90 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\CleaveEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */