/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ 
/*     */ public class MathHelper
/*     */ {
/*     */   public static float cardLerpSnap(float startX, float targetX) {
/*  10 */     if (startX != targetX) {
/*  11 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 6.0F);
/*  12 */       if (Math.abs(startX - targetX) < Settings.CARD_SNAP_THRESHOLD) {
/*  13 */         startX = targetX;
/*     */       }
/*     */     } 
/*  16 */     return startX;
/*     */   }
/*     */   
/*     */   public static float cardScaleLerpSnap(float startX, float targetX) {
/*  20 */     if (startX != targetX) {
/*  21 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 7.5F);
/*  22 */       if (Math.abs(startX - targetX) < 0.003F) {
/*  23 */         startX = targetX;
/*     */       }
/*     */     } 
/*  26 */     return startX;
/*     */   }
/*     */   
/*     */   public static float uiLerpSnap(float startX, float targetX) {
/*  30 */     if (startX != targetX) {
/*  31 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 9.0F);
/*  32 */       if (Math.abs(startX - targetX) < Settings.UI_SNAP_THRESHOLD) {
/*  33 */         startX = targetX;
/*     */       }
/*     */     } 
/*  36 */     return startX;
/*     */   }
/*     */   
/*     */   public static float orbLerpSnap(float startX, float targetX) {
/*  40 */     if (startX != targetX) {
/*  41 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 6.0F);
/*  42 */       if (Math.abs(startX - targetX) < Settings.UI_SNAP_THRESHOLD) {
/*  43 */         startX = targetX;
/*     */       }
/*     */     } 
/*  46 */     return startX;
/*     */   }
/*     */   
/*     */   public static float mouseLerpSnap(float startX, float targetX) {
/*  50 */     if (startX != targetX) {
/*  51 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 20.0F);
/*  52 */       if (Math.abs(startX - targetX) < Settings.UI_SNAP_THRESHOLD) {
/*  53 */         startX = targetX;
/*     */       }
/*     */     } 
/*  56 */     return startX;
/*     */   }
/*     */   
/*     */   public static float scaleLerpSnap(float startX, float targetX) {
/*  60 */     if (startX != targetX) {
/*  61 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 8.0F);
/*  62 */       if (Math.abs(startX - targetX) < 0.003F) {
/*  63 */         startX = targetX;
/*     */       }
/*     */     } 
/*  66 */     return startX;
/*     */   }
/*     */   
/*     */   public static float fadeLerpSnap(float startX, float targetX) {
/*  70 */     if (startX != targetX) {
/*  71 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 12.0F);
/*  72 */       if (Math.abs(startX - targetX) < 0.01F) {
/*  73 */         startX = targetX;
/*     */       }
/*     */     } 
/*  76 */     return startX;
/*     */   }
/*     */   
/*     */   public static float popLerpSnap(float startX, float targetX) {
/*  80 */     if (startX != targetX) {
/*  81 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 8.0F);
/*  82 */       if (Math.abs(startX - targetX) < 0.003F) {
/*  83 */         startX = targetX;
/*     */       }
/*     */     } 
/*  86 */     return startX;
/*     */   }
/*     */   
/*     */   public static float angleLerpSnap(float startX, float targetX) {
/*  90 */     if (startX != targetX) {
/*  91 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 12.0F);
/*  92 */       if (Math.abs(startX - targetX) < 0.003F) {
/*  93 */         startX = targetX;
/*     */       }
/*     */     } 
/*  96 */     return startX;
/*     */   }
/*     */   
/*     */   public static float slowColorLerpSnap(float startX, float targetX) {
/* 100 */     if (startX != targetX) {
/* 101 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 3.0F);
/* 102 */       if (Math.abs(startX - targetX) < 0.01F) {
/* 103 */         startX = targetX;
/*     */       }
/*     */     } 
/* 106 */     return startX;
/*     */   }
/*     */   
/*     */   public static float scrollSnapLerpSpeed(float startX, float targetX) {
/* 110 */     if (startX != targetX) {
/* 111 */       startX = MathUtils.lerp(startX, targetX, Gdx.graphics.getDeltaTime() * 10.0F);
/* 112 */       if (Math.abs(startX - targetX) < Settings.UI_SNAP_THRESHOLD) {
/* 113 */         startX = targetX;
/*     */       }
/*     */     } 
/* 116 */     return startX;
/*     */   }
/*     */   
/*     */   public static float valueFromPercentBetween(float min, float max, float percent) {
/* 120 */     float diff = max - min;
/* 121 */     return min + diff * percent;
/*     */   }
/*     */   
/*     */   public static float percentFromValueBetween(float min, float max, float value) {
/* 125 */     float diff = max - min;
/* 126 */     float offset = value - min;
/* 127 */     return offset / diff;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\MathHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */