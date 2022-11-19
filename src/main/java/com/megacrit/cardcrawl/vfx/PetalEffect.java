/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class PetalEffect extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*  14 */   private int frame = 0; private float vY; private float vX; private float scaleY;
/*  15 */   private float animTimer = 0.05F;
/*     */   private static final int W = 32;
/*     */   
/*     */   public PetalEffect() {
/*  19 */     this.x = MathUtils.random(100.0F * Settings.scale, 1820.0F * Settings.scale);
/*  20 */     this.y = Settings.HEIGHT + MathUtils.random(20.0F, 300.0F) * Settings.scale;
/*     */     
/*  22 */     this.frame = MathUtils.random(8);
/*  23 */     this.rotation = MathUtils.random(-10.0F, 10.0F);
/*  24 */     this.scale = MathUtils.random(1.0F, 2.5F);
/*  25 */     this.scaleY = MathUtils.random(1.0F, 1.2F);
/*  26 */     if (this.scale < 1.5F) {
/*  27 */       this.renderBehind = true;
/*     */     }
/*  29 */     this.vY = MathUtils.random(200.0F, 300.0F) * this.scale * Settings.scale;
/*  30 */     this.vX = MathUtils.random(-100.0F, 100.0F) * this.scale * Settings.scale;
/*  31 */     this.scale *= Settings.scale;
/*  32 */     if (MathUtils.randomBoolean()) {
/*  33 */       this.rotation += 180.0F;
/*     */     }
/*  35 */     this.color = new Color(1.0F, MathUtils.random(0.7F, 0.9F), MathUtils.random(0.7F, 0.9F), 1.0F);
/*  36 */     this.duration = 4.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  40 */     this.y -= this.vY * Gdx.graphics.getDeltaTime();
/*  41 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/*  42 */     this.animTimer -= Gdx.graphics.getDeltaTime() / this.scale;
/*  43 */     if (this.animTimer < 0.0F) {
/*  44 */       this.animTimer += 0.05F;
/*  45 */       this.frame++;
/*  46 */       if (this.frame > 11) {
/*  47 */         this.frame = 0;
/*     */       }
/*     */     } 
/*     */     
/*  51 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  52 */     if (this.duration < 0.0F) {
/*  53 */       this.isDone = true;
/*  54 */     } else if (this.duration < 1.0F) {
/*  55 */       this.color.a = this.duration;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  60 */     sb.setColor(this.color);
/*  61 */     switch (this.frame) {
/*     */       case 0:
/*  63 */         renderImg(sb, ImageMaster.PETAL_VFX[0], false, false);
/*     */         break;
/*     */       case 1:
/*  66 */         renderImg(sb, ImageMaster.PETAL_VFX[1], false, false);
/*     */         break;
/*     */       case 2:
/*  69 */         renderImg(sb, ImageMaster.PETAL_VFX[2], false, false);
/*     */         break;
/*     */       case 3:
/*  72 */         renderImg(sb, ImageMaster.PETAL_VFX[3], false, false);
/*     */         break;
/*     */       case 4:
/*  75 */         renderImg(sb, ImageMaster.PETAL_VFX[2], true, true);
/*     */         break;
/*     */       case 5:
/*  78 */         renderImg(sb, ImageMaster.PETAL_VFX[1], true, true);
/*     */         break;
/*     */       case 6:
/*  81 */         renderImg(sb, ImageMaster.PETAL_VFX[0], true, true);
/*     */         break;
/*     */       case 7:
/*  84 */         renderImg(sb, ImageMaster.PETAL_VFX[1], true, true);
/*     */         break;
/*     */       case 8:
/*  87 */         renderImg(sb, ImageMaster.PETAL_VFX[2], true, true);
/*     */         break;
/*     */       case 9:
/*  90 */         renderImg(sb, ImageMaster.PETAL_VFX[3], true, true);
/*     */         break;
/*     */       case 10:
/*  93 */         renderImg(sb, ImageMaster.PETAL_VFX[2], false, false);
/*     */         break;
/*     */       case 11:
/*  96 */         renderImg(sb, ImageMaster.PETAL_VFX[1], false, false);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderImg(SpriteBatch sb, Texture img, boolean flipH, boolean flipV) {
/* 108 */     sb.setBlendFunction(770, 1);
/* 109 */     sb.draw(img, this.x, this.y, 16.0F, 16.0F, 32.0F, 32.0F, this.scale, this.scale * this.scaleY, this.rotation, 0, 0, 32, 32, flipH, flipV);
/* 110 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\PetalEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */