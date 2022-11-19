/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
/*     */ 
/*     */ public class SwirlyBloodEffect extends AbstractGameEffect {
/*     */   private TextureAtlas.AtlasRegion img;
/*  20 */   private CatmullRomSpline<Vector2> crs = new CatmullRomSpline();
/*  21 */   private ArrayList<Vector2> controlPoints = new ArrayList<>();
/*     */   private static final int TRAIL_ACCURACY = 60;
/*  23 */   private Vector2[] points = new Vector2[60];
/*     */   
/*     */   private Vector2 pos;
/*     */   private Vector2 target;
/*  27 */   private float currentSpeed = 0.0F;
/*  28 */   private static final float VELOCITY_RAMP_RATE = 3000.0F * Settings.scale;
/*     */   
/*     */   private float rotation;
/*     */   
/*     */   private boolean rotateClockwise = true;
/*     */   private boolean stopRotating = false;
/*     */   private float rotationRate;
/*     */   
/*     */   public SwirlyBloodEffect(float sX, float sY) {
/*  37 */     this.img = ImageMaster.GLOW_SPARK_2;
/*  38 */     this.pos = new Vector2(sX, sY);
/*  39 */     this.target = new Vector2(sX + 100.0F, sY + 100.0F);
/*  40 */     this.crs.controlPoints = (Vector2[]) new Vector2[1];
/*  41 */     this.rotateClockwise = MathUtils.randomBoolean();
/*  42 */     this.rotation = MathUtils.random(0, 359);
/*  43 */     this.controlPoints.clear();
/*  44 */     this.rotationRate = MathUtils.random(800.0F, 1000.0F) * Settings.scale;
/*  45 */     this.currentSpeed = this.rotationRate / 2.0F;
/*  46 */     this.color = new Color(MathUtils.random(0.3F, 1.0F), 0.3F, MathUtils.random(0.6F, 1.0F), 0.25F);
/*  47 */     this.duration = MathUtils.random(1.0F, 1.5F);
/*  48 */     this.renderBehind = MathUtils.randomBoolean();
/*  49 */     this.scale = 1.0E-5F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  53 */     updateMovement();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateMovement() {
/*  58 */     Vector2 tmp = new Vector2(this.pos.x - this.target.x, this.pos.y - this.target.y);
/*  59 */     tmp.nor();
/*     */ 
/*     */     
/*  62 */     if (!this.stopRotating) {
/*  63 */       if (this.rotateClockwise) {
/*  64 */         this.rotation += Gdx.graphics.getDeltaTime() * this.rotationRate;
/*     */       } else {
/*  66 */         this.rotation -= Gdx.graphics.getDeltaTime() * this.rotationRate;
/*  67 */         if (this.rotation < 0.0F) {
/*  68 */           this.rotation += 360.0F;
/*     */         }
/*     */       } 
/*     */       
/*  72 */       this.rotation %= 360.0F;
/*     */     } 
/*     */ 
/*     */     
/*  76 */     tmp.setAngle(this.rotation);
/*     */ 
/*     */     
/*  79 */     tmp.x *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/*  80 */     tmp.y *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/*  81 */     this.pos.sub(tmp);
/*     */     
/*  83 */     this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 1.5F;
/*     */     
/*  85 */     if (!this.controlPoints.isEmpty()) {
/*  86 */       if (!((Vector2)this.controlPoints.get(0)).equals(this.pos)) {
/*  87 */         this.controlPoints.add(this.pos.cpy());
/*     */       }
/*     */     } else {
/*  90 */       this.controlPoints.add(this.pos.cpy());
/*     */     } 
/*     */     
/*  93 */     if (this.controlPoints.size() > 3) {
/*  94 */       Vector2[] vec2Array = new Vector2[0];
/*  95 */       this.crs.set((Vector2[]) this.controlPoints.toArray((Object[])vec2Array), false);
/*  96 */       for (int i = 0; i < 60; i++) {
/*  97 */         this.points[i] = new Vector2();
/*  98 */         this.crs.valueAt((Vector2) this.points[i], i / 59.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     if (this.controlPoints.size() > 5) {
/* 103 */       this.controlPoints.remove(0);
/*     */     }
/*     */ 
/*     */     
/* 107 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 108 */     this.scale = Interpolation.pow2In.apply(2.0F, 0.01F, this.duration) * Settings.scale;
/* 109 */     if (this.duration < 0.5F) {
/* 110 */       this.color.a = this.duration / 2.0F;
/*     */     }
/* 112 */     if (this.duration < 0.0F) {
/* 113 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 118 */     if (!this.isDone) {
/*     */       
/* 120 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 2.0F));
/* 121 */       float tmpScale = this.scale * 2.0F; int i;
/* 122 */       for (i = this.points.length - 1; i > 0; i--) {
/* 123 */         if (this.points[i] != null) {
/* 124 */           sb.draw((TextureRegion)this.img, (this.points[i]).x - (this.img.packedWidth / 2), (this.points[i]).y - (this.img.packedHeight / 2), this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, tmpScale, tmpScale, this.rotation);
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
/* 135 */           tmpScale *= 0.975F;
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       sb.setBlendFunction(770, 1);
/* 140 */       sb.setColor(this.color);
/* 141 */       tmpScale = this.scale * 1.5F;
/* 142 */       for (i = this.points.length - 1; i > 0; i--) {
/* 143 */         if (this.points[i] != null) {
/* 144 */           sb.draw((TextureRegion)this.img, (this.points[i]).x - (this.img.packedWidth / 2), (this.points[i]).y - (this.img.packedHeight / 2), this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, tmpScale, tmpScale, this.rotation);
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
/* 155 */           tmpScale *= 0.975F;
/*     */         } 
/*     */       } 
/* 158 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SwirlyBloodEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */