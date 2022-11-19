/*     */ package com.megacrit.cardcrawl.vfx.scene;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class WatcherVictoryEffect
/*     */   extends AbstractGameEffect {
/*     */   private float x;
/*     */   
/*     */   public WatcherVictoryEffect() {
/*  18 */     this.renderBehind = true;
/*  19 */     img = ImageMaster.EYE_ANIM_0;
/*  20 */     this.x = Settings.WIDTH / 2.0F - img.packedWidth / 2.0F;
/*  21 */     this.y = Settings.HEIGHT / 2.0F - img.packedHeight / 2.0F;
/*  22 */     this.scale = 1.5F * Settings.scale;
/*  23 */     this.color = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   private float y; private static TextureAtlas.AtlasRegion img;
/*     */   
/*     */   public void update() {
/*  28 */     this.color.a = MathHelper.slowColorLerpSnap(this.color.a, 1.0F);
/*  29 */     this.duration += Gdx.graphics.getDeltaTime();
/*  30 */     this.rotation += 5.0F * Gdx.graphics.getDeltaTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderHelper(SpriteBatch sb, float offsetX, float offsetY, float rotation, Color color, float scaleMod) {
/*  41 */     sb.setColor(color);
/*     */     
/*  43 */     offsetX *= Settings.scale;
/*  44 */     offsetY *= Settings.scale;
/*     */     
/*  46 */     sb.draw((TextureRegion)
/*  47 */         getImg(this.rotation + rotation + offsetX / 100.0F), this.x, this.y, img.packedWidth / 2.0F - offsetX, img.packedHeight / 2.0F - offsetY, img.packedWidth, img.packedHeight, this.scale, this.scale * 2.0F, this.rotation + rotation);
/*     */   }
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
/*     */   private TextureAtlas.AtlasRegion getImg(float input) {
/*  60 */     input %= 10.0F;
/*     */     
/*  62 */     if (input < 0.5F)
/*  63 */       return ImageMaster.EYE_ANIM_1; 
/*  64 */     if (input < 1.2F)
/*  65 */       return ImageMaster.EYE_ANIM_2; 
/*  66 */     if (input < 2.0F)
/*  67 */       return ImageMaster.EYE_ANIM_3; 
/*  68 */     if (input < 3.0F)
/*  69 */       return ImageMaster.EYE_ANIM_4; 
/*  70 */     if (input < 4.2F)
/*  71 */       return ImageMaster.EYE_ANIM_5; 
/*  72 */     if (input < 6.0F)
/*  73 */       return ImageMaster.EYE_ANIM_6; 
/*  74 */     if (input < 7.5F)
/*  75 */       return ImageMaster.EYE_ANIM_5; 
/*  76 */     if (input < 8.5F)
/*  77 */       return ImageMaster.EYE_ANIM_4; 
/*  78 */     if (input < 9.3F) {
/*  79 */       return ImageMaster.EYE_ANIM_3;
/*     */     }
/*  81 */     return ImageMaster.EYE_ANIM_2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  87 */     sb.setBlendFunction(770, 1);
/*  88 */     float angle = 0.0F;
/*     */     
/*  90 */     for (int i = 0; i < 24; i++) {
/*  91 */       this.color.r = 0.9F;
/*  92 */       this.color.g = 0.46F + i * 0.01F;
/*  93 */       this.color.b = 0.3F + (12 - i) * 0.05F;
/*     */       
/*  95 */       renderHelper(sb, -760.0F, -760.0F, angle, this.color, 1.0F);
/*  96 */       renderHelper(sb, -630.0F, -630.0F, angle, this.color, 1.0F);
/*  97 */       renderHelper(sb, -510.0F, -510.0F, angle, this.color, 1.0F);
/*  98 */       renderHelper(sb, -400.0F, -400.0F, angle, this.color, 1.0F);
/*  99 */       renderHelper(sb, -300.0F, -300.0F, angle, this.color, 1.0F);
/* 100 */       renderHelper(sb, -220.0F, -220.0F, angle, this.color, 1.0F);
/* 101 */       renderHelper(sb, -170.0F, -170.0F, angle, this.color, 1.0F);
/* 102 */       renderHelper(sb, -130.0F, -130.0F, angle, this.color, 1.0F);
/* 103 */       renderHelper(sb, -100.0F, -100.0F, angle, this.color, 1.0F);
/*     */       
/* 105 */       renderHelper(sb, 760.0F, -760.0F, angle, this.color, 1.0F);
/* 106 */       renderHelper(sb, 630.0F, -630.0F, angle, this.color, 1.0F);
/* 107 */       renderHelper(sb, 510.0F, -510.0F, angle, this.color, 1.0F);
/* 108 */       renderHelper(sb, 400.0F, -400.0F, angle, this.color, 1.0F);
/* 109 */       renderHelper(sb, 300.0F, -300.0F, angle, this.color, 1.0F);
/* 110 */       renderHelper(sb, 220.0F, -220.0F, angle, this.color, 1.0F);
/* 111 */       renderHelper(sb, 170.0F, -170.0F, angle, this.color, 1.0F);
/* 112 */       renderHelper(sb, 130.0F, -130.0F, angle, this.color, 1.0F);
/* 113 */       renderHelper(sb, 100.0F, -100.0F, angle, this.color, 1.0F);
/* 114 */       angle += 15.0F;
/*     */     } 
/*     */     
/* 117 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\WatcherVictoryEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */