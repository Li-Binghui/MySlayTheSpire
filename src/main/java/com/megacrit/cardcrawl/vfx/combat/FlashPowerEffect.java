/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class FlashPowerEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*  18 */   private float scale = Settings.scale; private Texture img; private TextureAtlas.AtlasRegion region128; private static final int W = 32;
/*     */   
/*     */   public FlashPowerEffect(AbstractPower power) {
/*  21 */     if (!power.owner.isDeadOrEscaped()) {
/*  22 */       this.x = power.owner.hb.cX;
/*  23 */       this.y = power.owner.hb.cY;
/*     */     } 
/*     */     
/*  26 */     this.img = power.img;
/*  27 */     this.region128 = power.region128;
/*  28 */     if (this.img == null) {
/*  29 */       this.x -= (this.region128.packedWidth / 2);
/*  30 */       this.y -= (this.region128.packedHeight / 2);
/*     */     } 
/*  32 */     this.duration = 0.7F;
/*  33 */     this.startingDuration = 0.7F;
/*  34 */     this.color = Color.WHITE.cpy();
/*  35 */     this.renderBehind = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  40 */     super.update();
/*  41 */     this.scale = Interpolation.exp5In.apply(Settings.scale, Settings.scale * 0.3F, this.duration / this.startingDuration);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  46 */     sb.setBlendFunction(770, 1);
/*  47 */     sb.setColor(this.color);
/*  48 */     if (this.img != null) {
/*  49 */       sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 12.0F, this.scale * 12.0F, 0.0F, 0, 0, 32, 32, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  66 */       sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 10.0F, this.scale * 10.0F, 0.0F, 0, 0, 32, 32, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  83 */       sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 8.0F, this.scale * 8.0F, 0.0F, 0, 0, 32, 32, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 7.0F, this.scale * 7.0F, 0.0F, 0, 0, 32, 32, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 118 */       sb.draw((TextureRegion)this.region128, this.x, this.y, this.region128.packedWidth / 2.0F, this.region128.packedHeight / 2.0F, this.region128.packedWidth, this.region128.packedHeight, this.scale * 3.0F, this.scale * 3.0F, 0.0F);
/*     */     } 
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
/* 130 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlashPowerEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */