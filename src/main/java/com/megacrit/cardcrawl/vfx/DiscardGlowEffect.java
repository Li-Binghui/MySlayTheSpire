/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class DiscardGlowEffect extends AbstractGameEffect {
/*     */   private static final float EFFECT_DUR = 0.2F;
/*     */   private float effectDuration;
/*     */   private float x;
/*  16 */   private float scaleJitter = 1.0F; private float y; private float vY; private float rotator;
/*     */   private static final float SCALE_JITTER_AMT = 0.1F;
/*  18 */   private Color shadowColor = Color.BLACK.cpy();
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   private boolean isAdditive;
/*     */   
/*     */   public DiscardGlowEffect(boolean isAbove) {
/*  23 */     this.img = getImg();
/*  24 */     setPosition(isAbove);
/*  25 */     this.x -= (this.img.packedWidth / 2);
/*  26 */     this.y -= (this.img.packedHeight / 2);
/*  27 */     this.effectDuration = MathUtils.random(0.4F, 0.9F);
/*  28 */     this.duration = this.effectDuration;
/*  29 */     this.startingDuration = this.effectDuration;
/*  30 */     this.scaleJitter = MathUtils.random(this.scaleJitter - 0.1F, this.scaleJitter + 0.1F);
/*  31 */     this.vY = MathUtils.random(30.0F * Settings.scale, 60.0F * Settings.scale);
/*  32 */     this.color = Settings.DISCARD_COLOR.cpy();
/*  33 */     this.color.r -= MathUtils.random(0.0F, 0.1F);
/*  34 */     this.color.g += MathUtils.random(0.0F, 0.1F);
/*  35 */     this.color.b += MathUtils.random(0.0F, 0.1F);
/*  36 */     this.isAdditive = MathUtils.randomBoolean();
/*  37 */     this.rotator = MathUtils.random(-180.0F, 180.0F);
/*     */   }
/*     */   
/*     */   private TextureAtlas.AtlasRegion getImg() {
/*  41 */     int roll = MathUtils.random(0, 5);
/*  42 */     switch (roll) {
/*     */       case 0:
/*  44 */         return ImageMaster.DECK_GLOW_1;
/*     */       case 1:
/*  46 */         return ImageMaster.DECK_GLOW_2;
/*     */       case 2:
/*  48 */         return ImageMaster.DECK_GLOW_3;
/*     */       case 3:
/*  50 */         return ImageMaster.DECK_GLOW_4;
/*     */       case 4:
/*  52 */         return ImageMaster.DECK_GLOW_5;
/*     */     } 
/*  54 */     return ImageMaster.DECK_GLOW_6;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPosition(boolean isAbove) {
/*  59 */     int roll = MathUtils.random(0, 9);
/*  60 */     if (isAbove) {
/*  61 */       switch (roll) {
/*     */         case 0:
/*  63 */           this.x = 1886.0F * Settings.scale;
/*  64 */           this.y = 86.0F * Settings.scale;
/*     */           return;
/*     */         case 1:
/*  67 */           this.x = 1883.0F * Settings.scale;
/*  68 */           this.y = 80.0F * Settings.scale;
/*     */           return;
/*     */         case 2:
/*  71 */           this.x = 1881.0F * Settings.scale;
/*  72 */           this.y = 67.0F * Settings.scale;
/*     */           return;
/*     */         case 3:
/*  75 */           this.x = 1876.0F * Settings.scale;
/*  76 */           this.y = 54.0F * Settings.scale;
/*     */           return;
/*     */         case 4:
/*  79 */           this.x = 1873.0F * Settings.scale;
/*  80 */           this.y = 45.0F * Settings.scale;
/*     */           return;
/*     */         case 5:
/*  83 */           this.x = 1865.0F * Settings.scale;
/*  84 */           this.y = 36.0F * Settings.scale;
/*     */           return;
/*     */         case 6:
/*  87 */           this.x = 1849.0F * Settings.scale;
/*  88 */           this.y = 32.0F * Settings.scale;
/*     */           return;
/*     */         case 7:
/*  91 */           this.x = 1841.0F * Settings.scale;
/*  92 */           this.y = 36.0F * Settings.scale;
/*     */           return;
/*     */         case 8:
/*  95 */           this.x = 1830.0F * Settings.scale;
/*  96 */           this.y = 36.0F * Settings.scale;
/*     */           return;
/*     */       } 
/*  99 */       this.x = 1819.0F * Settings.scale;
/* 100 */       this.y = 43.0F * Settings.scale;
/*     */       
/*     */       return;
/*     */     } 
/* 104 */     switch (roll) {
/*     */       case 0:
/* 106 */         this.x = 1810.0F * Settings.scale;
/* 107 */         this.y = 84.0F * Settings.scale;
/*     */         return;
/*     */       case 1:
/* 110 */         this.x = 1820.0F * Settings.scale;
/* 111 */         this.y = 88.0F * Settings.scale;
/*     */         return;
/*     */       case 2:
/* 114 */         this.x = 1830.0F * Settings.scale;
/* 115 */         this.y = 94.0F * Settings.scale;
/*     */         return;
/*     */       case 3:
/* 118 */         this.x = 1834.0F * Settings.scale;
/* 119 */         this.y = 96.0F * Settings.scale;
/*     */         return;
/*     */       case 4:
/* 122 */         this.x = 1837.0F * Settings.scale;
/* 123 */         this.y = 96.0F * Settings.scale;
/*     */         return;
/*     */       case 5:
/* 126 */         this.x = 1841.0F * Settings.scale;
/* 127 */         this.y = 98.0F * Settings.scale;
/*     */         return;
/*     */       case 6:
/* 130 */         this.x = 1854.0F * Settings.scale;
/* 131 */         this.y = 99.0F * Settings.scale;
/*     */         return;
/*     */       case 7:
/* 134 */         this.x = 1859.0F * Settings.scale;
/* 135 */         this.y = 91.0F * Settings.scale;
/*     */         return;
/*     */       case 8:
/* 138 */         this.x = 1871.0F * Settings.scale;
/* 139 */         this.y = 87.0F * Settings.scale;
/*     */         return;
/*     */     } 
/* 142 */     this.x = 1877.0F * Settings.scale;
/* 143 */     this.y = 84.0F * Settings.scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 150 */     this.rotation += this.rotator * Gdx.graphics.getDeltaTime();
/* 151 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 152 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/* 154 */     if (this.duration < 0.1F) {
/* 155 */       this.scale = Settings.scale * this.duration / this.effectDuration * 2.0F + Settings.scale / 2.0F;
/*     */     }
/*     */     
/* 158 */     if (this.duration < 0.25F) {
/* 159 */       this.color.a = this.duration * 4.0F;
/*     */     }
/*     */     
/* 162 */     if (this.duration < 0.0F) {
/* 163 */       this.isDone = true;
/*     */     }
/*     */     
/* 166 */     this.color.a /= 2.0F;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, float x2, float y2) {
/* 170 */     if (this.isAdditive) {
/* 171 */       sb.setBlendFunction(770, 1);
/*     */     }
/* 173 */     sb.setColor(this.color);
/* 174 */     sb.draw((TextureRegion)this.img, this.x + x2, this.y + y2, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * this.scaleJitter, this.scale * this.scaleJitter, this.rotation);
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
/* 185 */     if (this.isAdditive)
/* 186 */       sb.setBlendFunction(770, 771); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */   
/*     */   public void render(SpriteBatch sb) {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\DiscardGlowEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */