/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.CatmullRomSpline;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.math.Vector;
/*     */ import com.badlogic.gdx.math.Vector2;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class FlyingOrbEffect extends AbstractGameEffect {
/*     */   private TextureAtlas.AtlasRegion img;
/*  20 */   private CatmullRomSpline<Vector2> crs = new CatmullRomSpline();
/*  21 */   private ArrayList<Vector2> controlPoints = new ArrayList<>();
/*     */   private static final int TRAIL_ACCURACY = 60;
/*  23 */   private Vector2[] points = new Vector2[60];
/*     */   
/*     */   private Vector2 pos;
/*     */   private Vector2 target;
/*  27 */   private float currentSpeed = 0.0F;
/*  28 */   private static final float START_VELOCITY = 100.0F * Settings.scale;
/*  29 */   private static final float MAX_VELOCITY = 5000.0F * Settings.scale;
/*  30 */   private static final float VELOCITY_RAMP_RATE = 2000.0F * Settings.scale;
/*  31 */   private static final float DST_THRESHOLD = 36.0F * Settings.scale;
/*  32 */   private static final float HOME_IN_THRESHOLD = 36.0F * Settings.scale;
/*     */   
/*     */   private float rotation;
/*     */   
/*     */   private boolean rotateClockwise = true;
/*     */   private boolean stopRotating = false;
/*     */   private float rotationRate;
/*     */   
/*     */   public FlyingOrbEffect(float x, float y) {
/*  41 */     this.img = ImageMaster.GLOW_SPARK_2;
/*  42 */     this.pos = new Vector2(x, y);
/*  43 */     this
/*     */       
/*  45 */       .target = new Vector2(AbstractDungeon.player.hb.cX - DST_THRESHOLD / 3.0F - 100.0F * Settings.scale, AbstractDungeon.player.hb.cY + MathUtils.random(-50.0F, 50.0F) * Settings.scale);
/*  46 */     this.crs.controlPoints = (Vector2[]) new Vector2[1];
/*  47 */     this.rotateClockwise = MathUtils.randomBoolean();
/*  48 */     this.rotation = MathUtils.random(0, 359);
/*  49 */     this.controlPoints.clear();
/*  50 */     this.rotationRate = MathUtils.random(300.0F, 350.0F) * Settings.scale;
/*  51 */     this.currentSpeed = START_VELOCITY * MathUtils.random(0.2F, 1.0F);
/*  52 */     this.color = new Color(1.0F, 0.15F, 0.2F, 0.4F);
/*  53 */     this.duration = 1.3F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  57 */     updateMovement();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateMovement() {
/*  62 */     Vector2 tmp = new Vector2(this.pos.x - this.target.x, this.pos.y - this.target.y);
/*  63 */     tmp.nor();
/*  64 */     float targetAngle = tmp.angle();
/*  65 */     this.rotationRate += Gdx.graphics.getDeltaTime() * 700.0F;
/*     */ 
/*     */     
/*  68 */     if (!this.stopRotating) {
/*  69 */       if (this.rotateClockwise) {
/*  70 */         this.rotation += Gdx.graphics.getDeltaTime() * this.rotationRate;
/*     */       } else {
/*  72 */         this.rotation -= Gdx.graphics.getDeltaTime() * this.rotationRate;
/*  73 */         if (this.rotation < 0.0F) {
/*  74 */           this.rotation += 360.0F;
/*     */         }
/*     */       } 
/*     */       
/*  78 */       this.rotation %= 360.0F;
/*     */       
/*  80 */       if (!this.stopRotating) {
/*  81 */         if (this.target.dst(this.pos) < HOME_IN_THRESHOLD) {
/*  82 */           this.rotation = targetAngle;
/*  83 */           this.stopRotating = true;
/*  84 */         } else if (Math.abs(this.rotation - targetAngle) < Gdx.graphics.getDeltaTime() * this.rotationRate) {
/*  85 */           this.rotation = targetAngle;
/*  86 */           this.stopRotating = true;
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  92 */     tmp.setAngle(this.rotation);
/*     */ 
/*     */     
/*  95 */     tmp.x *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/*  96 */     tmp.y *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/*  97 */     this.pos.sub(tmp);
/*     */     
/*  99 */     if (this.stopRotating) {
/* 100 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 3.0F;
/*     */     } else {
/* 102 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 1.5F;
/*     */     } 
/* 104 */     if (this.currentSpeed > MAX_VELOCITY) {
/* 105 */       this.currentSpeed = MAX_VELOCITY;
/*     */     }
/*     */ 
/*     */     
/* 109 */     if ((this.target.x < Settings.WIDTH / 2.0F && this.pos.x < 0.0F) || (this.target.x > Settings.WIDTH / 2.0F && this.pos.x > Settings.WIDTH) || this.target
/* 110 */       .dst(this.pos) < DST_THRESHOLD) {
/* 111 */       this.isDone = true;
/*     */       
/*     */       return;
/*     */     } 
/* 115 */     if (!this.controlPoints.isEmpty()) {
/* 116 */       if (!((Vector2)this.controlPoints.get(0)).equals(this.pos)) {
/* 117 */         this.controlPoints.add(this.pos.cpy());
/*     */       }
/*     */     } else {
/* 120 */       this.controlPoints.add(this.pos.cpy());
/*     */     } 
/*     */     
/* 123 */     if (this.controlPoints.size() > 3) {
/* 124 */       Vector2[] vec2Array = new Vector2[0];
/* 125 */       this.crs.set((Vector2[]) this.controlPoints.toArray((Object[])vec2Array), false);
/* 126 */       for (int i = 0; i < 60; i++) {
/* 127 */         this.points[i] = new Vector2();
/* 128 */         this.crs.valueAt((Vector2) this.points[i], i / 59.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     if (this.controlPoints.size() > 10) {
/* 133 */       this.controlPoints.remove(0);
/*     */     }
/*     */ 
/*     */     
/* 137 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 138 */     if (this.duration < 0.0F) {
/* 139 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 144 */     if (!this.isDone) {
/* 145 */       sb.setBlendFunction(770, 1);
/* 146 */       sb.setColor(this.color);
/* 147 */       float scale = Settings.scale * 1.5F;
/* 148 */       for (int i = this.points.length - 1; i > 0; i--) {
/* 149 */         if (this.points[i] != null) {
/* 150 */           sb.draw((TextureRegion)this.img, (this.points[i]).x - (this.img.packedWidth / 2), (this.points[i]).y - (this.img.packedHeight / 2), this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, scale, scale, this.rotation);
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
/* 161 */           scale *= 0.975F;
/*     */         } 
/*     */       } 
/* 164 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlyingOrbEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */