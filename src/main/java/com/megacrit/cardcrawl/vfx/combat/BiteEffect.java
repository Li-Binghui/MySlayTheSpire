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
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ 
/*     */ public class BiteEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static TextureAtlas.AtlasRegion top;
/*     */   private static TextureAtlas.AtlasRegion bot;
/*     */   private float x;
/*     */   private float y;
/*     */   private float sY;
/*     */   
/*     */   public BiteEffect(float x, float y, Color c) {
/*  26 */     if (top == null) {
/*  27 */       top = ImageMaster.vfxAtlas.findRegion("combat/biteTop");
/*  28 */       bot = ImageMaster.vfxAtlas.findRegion("combat/biteBot");
/*     */     } 
/*     */ 
/*     */     
/*  32 */     this.x = x - top.packedWidth / 2.0F;
/*  33 */     this.sY = y - top.packedHeight / 2.0F + 150.0F * Settings.scale;
/*  34 */     this.dY = y - 0.0F * Settings.scale;
/*  35 */     this.y = this.sY;
/*     */ 
/*     */     
/*  38 */     this.sY2 = y - (top.packedHeight / 2) - 100.0F * Settings.scale;
/*  39 */     this.dY2 = y - 90.0F * Settings.scale;
/*  40 */     this.y2 = this.sY2;
/*     */     
/*  42 */     this.duration = 1.0F;
/*  43 */     this.startingDuration = 1.0F;
/*  44 */     this.color = c;
/*  45 */     this.scale = 1.0F * Settings.scale;
/*     */   }
/*     */   private float dY; private float y2; private float sY2; private float dY2; private static final float DUR = 1.0F; private boolean playedSfx = false;
/*     */   public BiteEffect(float x, float y) {
/*  49 */     this(x, y, new Color(0.7F, 0.9F, 1.0F, 0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  54 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  56 */     if (this.duration < this.startingDuration - 0.3F && !this.playedSfx) {
/*  57 */       this.playedSfx = true;
/*  58 */       CardCrawlGame.sound.play("EVENT_VAMP_BITE", 0.05F);
/*     */     } 
/*  60 */     if (this.duration > this.startingDuration / 2.0F) {
/*  61 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.5F) * 2.0F);
/*  62 */       this.y = Interpolation.bounceIn.apply(this.dY, this.sY, (this.duration - 0.5F) * 2.0F);
/*  63 */       this.y2 = Interpolation.bounceIn.apply(this.dY2, this.sY2, (this.duration - 0.5F) * 2.0F);
/*     */     } else {
/*  65 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
/*  66 */       this.y = Interpolation.fade.apply(this.sY, this.dY, this.duration * 2.0F);
/*  67 */       this.y2 = Interpolation.fade.apply(this.sY2, this.dY2, this.duration * 2.0F);
/*     */     } 
/*     */     
/*  70 */     if (this.duration < 0.0F) {
/*  71 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  77 */     sb.setBlendFunction(770, 1);
/*  78 */     sb.setColor(this.color);
/*  79 */     sb.draw((TextureRegion)top, this.x, this.y, top.packedWidth / 2.0F, top.packedHeight / 2.0F, top.packedWidth, top.packedHeight, this.scale + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  87 */         MathUtils.random(-0.05F, 0.05F), this.scale + 
/*  88 */         MathUtils.random(-0.05F, 0.05F), 0.0F);
/*     */ 
/*     */     
/*  91 */     sb.draw((TextureRegion)bot, this.x, this.y2, top.packedWidth / 2.0F, top.packedHeight / 2.0F, top.packedWidth, top.packedHeight, this.scale + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  99 */         MathUtils.random(-0.05F, 0.05F), this.scale + 
/* 100 */         MathUtils.random(-0.05F, 0.05F), 0.0F);
/*     */     
/* 102 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BiteEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */