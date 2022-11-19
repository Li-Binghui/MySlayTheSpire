/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
/*     */ 
/*     */ public class HemokinesisParticle
/*     */   extends AbstractGameEffect {
/*     */   private TextureAtlas.AtlasRegion img;
/*  23 */   private CatmullRomSpline<Vector2> crs = new CatmullRomSpline();
/*  24 */   private ArrayList<Vector2> controlPoints = new ArrayList<>();
/*     */   private static final int TRAIL_ACCURACY = 60;
/*  26 */   private Vector2[] points = new Vector2[60];
/*     */   
/*     */   private Vector2 pos;
/*     */   private Vector2 target;
/*  30 */   private float currentSpeed = 0.0F;
/*  31 */   private static final float MAX_VELOCITY = 4000.0F * Settings.scale;
/*  32 */   private static final float VELOCITY_RAMP_RATE = 3000.0F * Settings.scale;
/*  33 */   private static final float DST_THRESHOLD = 42.0F * Settings.scale;
/*     */   
/*     */   private float rotation;
/*     */   
/*     */   private boolean rotateClockwise = true;
/*     */   private boolean stopRotating = false;
/*     */   private boolean facingLeft;
/*     */   private float rotationRate;
/*     */   
/*     */   public HemokinesisParticle(float sX, float sY, float tX, float tY, boolean facingLeft) {
/*  43 */     this.img = ImageMaster.GLOW_SPARK_2;
/*  44 */     this.pos = new Vector2(sX, sY);
/*     */     
/*  46 */     if (!facingLeft) {
/*  47 */       this.target = new Vector2(tX + DST_THRESHOLD, tY);
/*     */     } else {
/*  49 */       this.target = new Vector2(tX - DST_THRESHOLD, tY);
/*     */     } 
/*  51 */     this.facingLeft = facingLeft;
/*  52 */     this.crs.controlPoints = (Vector2[]) new Vector2[1];
/*  53 */     this.rotateClockwise = MathUtils.randomBoolean();
/*  54 */     this.rotation = MathUtils.random(0, 359);
/*  55 */     this.controlPoints.clear();
/*  56 */     this.rotationRate = MathUtils.random(600.0F, 650.0F) * Settings.scale;
/*  57 */     this.currentSpeed = 1000.0F * Settings.scale;
/*  58 */     this.color = new Color(1.0F, 0.0F, 0.02F, 0.6F);
/*  59 */     this.duration = 0.7F;
/*  60 */     this.scale = 1.0F * Settings.scale;
/*  61 */     this.renderBehind = MathUtils.randomBoolean();
/*     */   }
/*     */   
/*     */   public void update() {
/*  65 */     updateMovement();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateMovement() {
/*  70 */     Vector2 tmp = new Vector2(this.pos.x - this.target.x, this.pos.y - this.target.y);
/*  71 */     tmp.nor();
/*  72 */     float targetAngle = tmp.angle();
/*  73 */     this.rotationRate += Gdx.graphics.getDeltaTime() * 2000.0F;
/*  74 */     this.scale += Gdx.graphics.getDeltaTime() * 1.0F * Settings.scale;
/*     */ 
/*     */     
/*  77 */     if (!this.stopRotating) {
/*  78 */       if (this.rotateClockwise) {
/*  79 */         this.rotation += Gdx.graphics.getDeltaTime() * this.rotationRate;
/*     */       } else {
/*  81 */         this.rotation -= Gdx.graphics.getDeltaTime() * this.rotationRate;
/*  82 */         if (this.rotation < 0.0F) {
/*  83 */           this.rotation += 360.0F;
/*     */         }
/*     */       } 
/*     */       
/*  87 */       this.rotation %= 360.0F;
/*     */       
/*  89 */       if (!this.stopRotating && 
/*  90 */         Math.abs(this.rotation - targetAngle) < Gdx.graphics.getDeltaTime() * this.rotationRate) {
/*  91 */         this.rotation = targetAngle;
/*  92 */         this.stopRotating = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  98 */     tmp.setAngle(this.rotation);
/*     */ 
/*     */     
/* 101 */     tmp.x *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/* 102 */     tmp.y *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/* 103 */     this.pos.sub(tmp);
/*     */     
/* 105 */     if (this.stopRotating) {
/* 106 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 3.0F;
/*     */     } else {
/* 108 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 1.5F;
/*     */     } 
/* 110 */     if (this.currentSpeed > MAX_VELOCITY) {
/* 111 */       this.currentSpeed = MAX_VELOCITY;
/*     */     }
/*     */ 
/*     */     
/* 115 */     if (this.target.dst(this.pos) < DST_THRESHOLD) {
/* 116 */       for (int i = 0; i < 5; i++) {
/* 117 */         if (this.facingLeft) {
/* 118 */           AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(this.target.x + DST_THRESHOLD, this.target.y));
/*     */         } else {
/* 120 */           AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(this.target.x - DST_THRESHOLD, this.target.y));
/*     */         } 
/*     */       } 
/* 123 */       CardCrawlGame.sound.playAV("BLUNT_HEAVY", MathUtils.random(0.6F, 0.9F), 0.5F);
/* 124 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/* 125 */       this.isDone = true;
/*     */     } 
/*     */     
/* 128 */     if (!this.controlPoints.isEmpty()) {
/* 129 */       if (!((Vector2)this.controlPoints.get(0)).equals(this.pos)) {
/* 130 */         this.controlPoints.add(this.pos.cpy());
/*     */       }
/*     */     } else {
/* 133 */       this.controlPoints.add(this.pos.cpy());
/*     */     } 
/*     */     
/* 136 */     if (this.controlPoints.size() > 3) {
/* 137 */       Vector2[] vec2Array = new Vector2[0];
/* 138 */       this.crs.set((Vector2[]) this.controlPoints.toArray((Object[])vec2Array), false);
/* 139 */       for (int i = 0; i < 60; i++) {
/* 140 */         this.points[i] = new Vector2();
/* 141 */         this.crs.valueAt((Vector2) this.points[i], i / 59.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     if (this.controlPoints.size() > 10) {
/* 146 */       this.controlPoints.remove(0);
/*     */     }
/*     */ 
/*     */     
/* 150 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 151 */     if (this.duration < 0.0F) {
/* 152 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 157 */     if (!this.isDone) {
/*     */       
/* 159 */       sb.setColor(Color.BLACK);
/* 160 */       float scaleCpy = this.scale; int i;
/* 161 */       for (i = this.points.length - 1; i > 0; i--) {
/* 162 */         if (this.points[i] != null) {
/* 163 */           sb.draw((TextureRegion)this.img, (this.points[i]).x - (this.img.packedWidth / 2), (this.points[i]).y - (this.img.packedHeight / 2), this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, scaleCpy * 1.5F, scaleCpy * 1.5F, this.rotation);
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
/* 174 */           scaleCpy *= 0.98F;
/*     */         } 
/*     */       } 
/*     */       
/* 178 */       sb.setBlendFunction(770, 1);
/* 179 */       sb.setColor(this.color);
/* 180 */       scaleCpy = this.scale;
/* 181 */       for (i = this.points.length - 1; i > 0; i--) {
/* 182 */         if (this.points[i] != null) {
/* 183 */           sb.draw((TextureRegion)this.img, (this.points[i]).x - (this.img.packedWidth / 2), (this.points[i]).y - (this.img.packedHeight / 2), this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, scaleCpy, scaleCpy, this.rotation);
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
/* 194 */           scaleCpy *= 0.98F;
/*     */         } 
/*     */       } 
/* 197 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\HemokinesisParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */