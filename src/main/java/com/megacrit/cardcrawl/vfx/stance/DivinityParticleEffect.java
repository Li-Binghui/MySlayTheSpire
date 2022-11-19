/*     */ package com.megacrit.cardcrawl.vfx.stance;
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
/*     */ public class DivinityParticleEffect extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public DivinityParticleEffect() {
/*  20 */     this.scale = Settings.scale;
/*  21 */     this.img = ImageMaster.EYE_ANIM_0;
/*  22 */     this.scale = MathUtils.random(1.0F, 1.5F);
/*  23 */     this.startingDuration = this.scale + 0.8F;
/*  24 */     this.duration = this.startingDuration;
/*  25 */     this.scale *= Settings.scale;
/*     */     
/*  27 */     this.dur_div2 = this.duration / 2.0F;
/*  28 */     this.color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.5F, 0.7F), MathUtils.random(0.8F, 1.0F), 0.0F);
/*     */     
/*  30 */     this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 2.0F - 50.0F * Settings.scale, AbstractDungeon.player.hb.width / 2.0F + 50.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */     
/*  34 */     this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 2.0F + 10.0F * Settings.scale, AbstractDungeon.player.hb.height / 2.0F - 20.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */     
/*  38 */     this.renderBehind = MathUtils.randomBoolean();
/*  39 */     this.rotation = MathUtils.random(12.0F, 6.0F);
/*  40 */     if (this.x > AbstractDungeon.player.hb.cX) {
/*  41 */       this.rotation = -this.rotation;
/*     */     }
/*     */     
/*  44 */     this.x -= this.img.packedWidth / 2.0F;
/*  45 */     this.y -= this.img.packedHeight / 2.0F;
/*     */   }
/*     */   private float vY; private float dur_div2; private TextureAtlas.AtlasRegion img;
/*     */   
/*     */   public void update() {
/*  50 */     if (this.duration > this.dur_div2) {
/*  51 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*     */     } else {
/*  53 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
/*     */     } 
/*     */     
/*  56 */     if (this.duration > this.startingDuration * 0.85F) {
/*  57 */       this.vY = 12.0F * Settings.scale;
/*  58 */       this.img = ImageMaster.EYE_ANIM_0;
/*  59 */     } else if (this.duration > this.startingDuration * 0.8F) {
/*  60 */       this.vY = 8.0F * Settings.scale;
/*  61 */       this.img = ImageMaster.EYE_ANIM_1;
/*  62 */     } else if (this.duration > this.startingDuration * 0.75F) {
/*  63 */       this.vY = 4.0F * Settings.scale;
/*  64 */       this.img = ImageMaster.EYE_ANIM_2;
/*  65 */     } else if (this.duration > this.startingDuration * 0.7F) {
/*  66 */       this.vY = 3.0F * Settings.scale;
/*  67 */       this.img = ImageMaster.EYE_ANIM_3;
/*  68 */     } else if (this.duration > this.startingDuration * 0.65F) {
/*  69 */       this.img = ImageMaster.EYE_ANIM_4;
/*  70 */     } else if (this.duration > this.startingDuration * 0.6F) {
/*  71 */       this.img = ImageMaster.EYE_ANIM_5;
/*  72 */     } else if (this.duration > this.startingDuration * 0.55F) {
/*  73 */       this.img = ImageMaster.EYE_ANIM_6;
/*  74 */     } else if (this.duration > this.startingDuration * 0.38F) {
/*  75 */       this.img = ImageMaster.EYE_ANIM_5;
/*  76 */     } else if (this.duration > this.startingDuration * 0.3F) {
/*  77 */       this.img = ImageMaster.EYE_ANIM_4;
/*  78 */     } else if (this.duration > this.startingDuration * 0.25F) {
/*  79 */       this.vY = 3.0F * Settings.scale;
/*  80 */       this.img = ImageMaster.EYE_ANIM_3;
/*  81 */     } else if (this.duration > this.startingDuration * 0.2F) {
/*  82 */       this.vY = 4.0F * Settings.scale;
/*  83 */       this.img = ImageMaster.EYE_ANIM_2;
/*  84 */     } else if (this.duration > this.startingDuration * 0.15F) {
/*  85 */       this.vY = 8.0F * Settings.scale;
/*  86 */       this.img = ImageMaster.EYE_ANIM_1;
/*     */     } else {
/*  88 */       this.vY = 12.0F * Settings.scale;
/*  89 */       this.img = ImageMaster.EYE_ANIM_0;
/*     */     } 
/*     */     
/*  92 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  93 */     if (this.duration < 0.0F) {
/*  94 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 100 */     sb.setColor(this.color);
/* 101 */     sb.setBlendFunction(770, 1);
/* 102 */     sb.draw((TextureRegion)this.img, this.x, this.y + this.vY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 113 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\DivinityParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */