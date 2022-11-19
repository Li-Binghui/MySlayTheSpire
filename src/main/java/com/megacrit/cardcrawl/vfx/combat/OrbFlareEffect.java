/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class OrbFlareEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static TextureAtlas.AtlasRegion outer;
/*     */   private static TextureAtlas.AtlasRegion inner;
/*     */   private float scaleY;
/*     */   private static final float DUR = 0.5F;
/*     */   private AbstractOrb orb;
/*     */   private OrbFlareColor flareColor;
/*     */   private Color color2;
/*     */   
/*     */   public enum OrbFlareColor
/*     */   {
/*  28 */     LIGHTNING, DARK, PLASMA, FROST;
/*     */   }
/*     */   
/*     */   public OrbFlareEffect(AbstractOrb orb, OrbFlareColor setColor) {
/*  32 */     if (outer == null) {
/*  33 */       outer = ImageMaster.vfxAtlas.findRegion("combat/orbFlareOuter");
/*  34 */       inner = ImageMaster.vfxAtlas.findRegion("combat/orbFlareInner");
/*     */     } 
/*     */     
/*  37 */     this.orb = orb;
/*  38 */     this.renderBehind = true;
/*  39 */     this.duration = 0.5F;
/*  40 */     this.startingDuration = 0.5F;
/*  41 */     this.flareColor = setColor;
/*  42 */     setColor();
/*  43 */     this.scale = 2.0F * Settings.scale;
/*  44 */     this.scaleY = 0.0F;
/*     */   }
/*     */   
/*     */   private void setColor() {
/*  48 */     switch (this.flareColor) {
/*     */       case DARK:
/*  50 */         this.color = Color.VIOLET.cpy();
/*  51 */         this.color2 = Color.BLACK.cpy();
/*     */         break;
/*     */       case FROST:
/*  54 */         this.color = Settings.BLUE_TEXT_COLOR.cpy();
/*  55 */         this.color2 = Color.LIGHT_GRAY.cpy();
/*     */         break;
/*     */       case LIGHTNING:
/*  58 */         this.color = Color.CHARTREUSE.cpy();
/*  59 */         this.color2 = Color.WHITE.cpy();
/*     */         break;
/*     */       case PLASMA:
/*  62 */         this.color = Color.CORAL.cpy();
/*  63 */         this.color2 = Color.CYAN.cpy();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  72 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  73 */     if (this.duration < 0.0F) {
/*  74 */       this.duration = 0.0F;
/*  75 */       this.isDone = true;
/*     */     } 
/*     */     
/*  78 */     this.scaleY = Interpolation.elasticIn.apply(2.2F, 0.8F, this.duration * 2.0F);
/*  79 */     this.scale = Interpolation.elasticIn.apply(2.1F, 1.9F, this.duration * 2.0F);
/*  80 */     this.color.a = Interpolation.pow2Out.apply(0.0F, 0.6F, this.duration * 2.0F);
/*  81 */     this.color2.a = this.color.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  86 */     sb.setColor(this.color2);
/*  87 */     sb.draw((TextureRegion)inner, this.orb.cX - inner.packedWidth / 2.0F, this.orb.cY - inner.packedHeight / 2.0F, inner.packedWidth / 2.0F, inner.packedHeight / 2.0F, inner.packedWidth, inner.packedHeight, this.scale * Settings.scale * 1.1F, this.scaleY * Settings.scale, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  97 */         MathUtils.random(-1.0F, 1.0F));
/*  98 */     sb.setBlendFunction(770, 1);
/*  99 */     sb.setColor(this.color);
/* 100 */     sb.draw((TextureRegion)outer, this.orb.cX - outer.packedWidth / 2.0F, this.orb.cY - outer.packedHeight / 2.0F, outer.packedWidth / 2.0F, outer.packedHeight / 2.0F, outer.packedWidth, outer.packedHeight, this.scale, this.scaleY * Settings.scale, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 110 */         MathUtils.random(-2.0F, 2.0F));
/* 111 */     sb.draw((TextureRegion)outer, this.orb.cX - outer.packedWidth / 2.0F, this.orb.cY - outer.packedHeight / 2.0F, outer.packedWidth / 2.0F, outer.packedHeight / 2.0F, outer.packedWidth, outer.packedHeight, this.scale, this.scaleY * Settings.scale, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 121 */         MathUtils.random(-2.0F, 2.0F));
/* 122 */     sb.setBlendFunction(770, 771);
/* 123 */     sb.setColor(this.color2);
/* 124 */     sb.draw((TextureRegion)inner, this.orb.cX - inner.packedWidth / 2.0F, this.orb.cY - inner.packedHeight / 2.0F, inner.packedWidth / 2.0F, inner.packedHeight / 2.0F, inner.packedWidth, inner.packedHeight, this.scale * Settings.scale * 1.1F, this.scaleY * Settings.scale, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 134 */         MathUtils.random(-1.0F, 1.0F));
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\OrbFlareEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */