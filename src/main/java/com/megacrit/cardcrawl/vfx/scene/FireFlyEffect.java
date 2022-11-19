/*     */ package com.megacrit.cardcrawl.vfx.scene;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.math.Vector2;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class FireFlyEffect extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*     */   private float vX;
/*  19 */   private float trailTimer = 0.0F; private float vY; private float aX; private float waveDst; private float baseAlpha;
/*     */   private static final float TRAIL_TIME = 0.04F;
/*     */   private static final int TRAIL_MAX_AMT = 30;
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   private Color setColor;
/*  24 */   private ArrayList<Vector2> prevPositions = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public FireFlyEffect(Color setColor) {
/*  28 */     this.startingDuration = MathUtils.random(6.0F, 14.0F);
/*  29 */     this.duration = this.startingDuration;
/*  30 */     this.setColor = setColor;
/*     */ 
/*     */     
/*  33 */     this.img = ImageMaster.STRIKE_BLUR;
/*     */ 
/*     */     
/*  36 */     this.x = MathUtils.random(0, Settings.WIDTH) - this.img.packedWidth / 2.0F;
/*  37 */     this.y = MathUtils.random(-100.0F, 400.0F) * Settings.scale + AbstractDungeon.floorY - this.img.packedHeight / 2.0F;
/*  38 */     this.vX = MathUtils.random(18.0F, 90.0F) * Settings.scale;
/*  39 */     this.aX = MathUtils.random(-5.0F, 5.0F) * Settings.scale;
/*  40 */     this.waveDst = this.vX * MathUtils.random(0.03F, 0.07F);
/*  41 */     this.scale = Settings.scale * this.vX / 60.0F;
/*  42 */     if (MathUtils.randomBoolean()) {
/*  43 */       this.vX = -this.vX;
/*     */     }
/*     */     
/*  46 */     this.vY = MathUtils.random(-36.0F, 36.0F) * Settings.scale;
/*  47 */     this.color = setColor.cpy();
/*  48 */     this.baseAlpha = 0.25F;
/*  49 */     this.color.a = 0.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  53 */     this.trailTimer -= Gdx.graphics.getDeltaTime();
/*  54 */     if (this.trailTimer < 0.0F) {
/*  55 */       this.trailTimer = 0.04F;
/*  56 */       this.prevPositions.add(new Vector2(this.x, this.y));
/*  57 */       if (this.prevPositions.size() > 30) {
/*  58 */         this.prevPositions.remove(0);
/*     */       }
/*     */     } 
/*     */     
/*  62 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  63 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/*  64 */     this.vX += this.aX * Gdx.graphics.getDeltaTime();
/*     */     
/*  66 */     if (!this.prevPositions.isEmpty() && (
/*  67 */       ((Vector2)this.prevPositions.get(0)).x < 0.0F || ((Vector2)this.prevPositions.get(0)).x > Settings.WIDTH)) {
/*  68 */       this.isDone = true;
/*     */     }
/*     */ 
/*     */     
/*  72 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*  73 */     this.y += MathUtils.sin(this.duration * this.waveDst) * this.waveDst / 4.0F * Gdx.graphics.getDeltaTime() * 60.0F;
/*     */     
/*  75 */     if (this.duration < 0.0F) {
/*  76 */       this.isDone = true;
/*     */     }
/*     */     
/*  79 */     if (this.duration > this.startingDuration / 2.0F) {
/*  80 */       float tmp = this.duration - this.startingDuration / 2.0F;
/*  81 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.startingDuration / 2.0F - tmp) * this.baseAlpha;
/*     */     } else {
/*  83 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.startingDuration / 2.0F) * this.baseAlpha;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  89 */     sb.setBlendFunction(770, 1);
/*  90 */     this.setColor.a = this.color.a;
/*     */     
/*  92 */     for (int i = this.prevPositions.size() - 1; i > 0; i--) {
/*  93 */       this.setColor.a *= 0.95F;
/*  94 */       sb.setColor(this.setColor);
/*  95 */       sb.draw((TextureRegion)this.img, ((Vector2)this.prevPositions
/*     */           
/*  97 */           .get(i)).x, ((Vector2)this.prevPositions
/*  98 */           .get(i)).y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * (i + 5) / this.prevPositions
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 103 */           .size(), this.scale * (i + 5) / this.prevPositions
/* 104 */           .size(), this.rotation);
/*     */     } 
/*     */     
/* 107 */     sb.setColor(this.color);
/* 108 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 116 */         MathUtils.random(2.5F, 3.0F), this.scale * 
/* 117 */         MathUtils.random(2.5F, 3.0F), this.rotation);
/*     */     
/* 119 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\FireFlyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */