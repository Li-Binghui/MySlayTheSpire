/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class MiracleEffect
/*     */   extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*  19 */   private String sfxUrl = "HEAL_3"; private TextureAtlas.AtlasRegion img; private Color altColor;
/*     */   
/*     */   public MiracleEffect(Color setColor, Color altColor, String setSfx) {
/*  22 */     if (this.img == null) {
/*  23 */       this.img = ImageMaster.CRYSTAL_IMPACT;
/*     */     }
/*     */     
/*  26 */     this.x = AbstractDungeon.player.hb.cX - this.img.packedWidth / 2.0F;
/*  27 */     this.y = AbstractDungeon.player.hb.cY - this.img.packedHeight / 2.0F;
/*     */     
/*  29 */     this.startingDuration = 0.7F;
/*  30 */     this.duration = this.startingDuration;
/*  31 */     this.scale = Settings.scale;
/*  32 */     this.altColor = altColor;
/*  33 */     this.color = setColor.cpy();
/*  34 */     this.color.a = 0.0F;
/*  35 */     this.renderBehind = false;
/*  36 */     this.sfxUrl = setSfx;
/*     */   }
/*     */   
/*     */   public MiracleEffect() {
/*  40 */     if (this.img == null) {
/*  41 */       this.img = ImageMaster.CRYSTAL_IMPACT;
/*     */     }
/*     */     
/*  44 */     this.x = AbstractDungeon.player.hb.cX - this.img.packedWidth / 2.0F;
/*  45 */     this.y = AbstractDungeon.player.hb.cY - this.img.packedHeight / 2.0F;
/*     */     
/*  47 */     this.startingDuration = 0.7F;
/*  48 */     this.duration = this.startingDuration;
/*  49 */     this.scale = Settings.scale;
/*  50 */     this.altColor = new Color(1.0F, 0.6F, 0.2F, 0.0F);
/*  51 */     this.color = Settings.GOLD_COLOR.cpy();
/*  52 */     this.color.a = 0.0F;
/*  53 */     this.renderBehind = false;
/*     */   }
/*     */   
/*     */   public void update() {
/*  57 */     if (this.duration == this.startingDuration) {
/*  58 */       CardCrawlGame.sound.playA(this.sfxUrl, 0.5F);
/*     */     }
/*     */     
/*  61 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  63 */     if (this.duration > this.startingDuration / 2.0F) {
/*  64 */       this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*     */     } else {
/*  66 */       this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*     */     } 
/*     */     
/*  69 */     this.scale = Interpolation.pow5In.apply(2.4F, 0.3F, this.duration / this.startingDuration) * Settings.scale;
/*     */     
/*  71 */     if (this.duration < 0.0F) {
/*  72 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  78 */     sb.setBlendFunction(770, 1);
/*  79 */     this.altColor.a = this.color.a;
/*  80 */     sb.setColor(this.altColor);
/*  81 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.1F, this.scale * 1.1F, 0.0F);
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
/*  93 */     sb.setColor(this.color);
/*  94 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.9F, this.scale * 0.9F, 0.0F);
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
/* 105 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\MiracleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */