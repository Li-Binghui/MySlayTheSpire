/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
/*     */ 
/*     */ public class ShaderHelper
/*     */ {
/*     */   private static ShaderProgram gsShader;
/*     */   private static ShaderProgram rsShader;
/*     */   private static ShaderProgram wsShader;
/*     */   private static ShaderProgram blurShader;
/*     */   private static ShaderProgram waterShader;
/*     */   private static ShaderProgram outlineShader;
/*     */   
/*     */   public static void initializeShaders() {
/*  18 */     ShaderProgram.pedantic = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  23 */     gsShader = new ShaderProgram(Gdx.files.internal("shaders/grayscale/vertexShader.vs").readString(), Gdx.files.internal("shaders/grayscale/fragShader.fs").readString());
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
/*     */   public static void setShader(SpriteBatch sb, Shader shader) {
/*  62 */     switch (shader) {
/*     */       case BLUR:
/*  64 */         sb.end();
/*  65 */         sb.setShader(blurShader);
/*  66 */         sb.begin();
/*     */         return;
/*     */       case DEFAULT:
/*  69 */         sb.end();
/*  70 */         sb.setShader(null);
/*  71 */         sb.begin();
/*     */         return;
/*     */       case GRAYSCALE:
/*  74 */         sb.end();
/*  75 */         sb.setShader(gsShader);
/*  76 */         sb.begin();
/*     */         return;
/*     */       case OUTLINE:
/*  79 */         sb.end();
/*  80 */         sb.setShader(outlineShader);
/*  81 */         sb.begin();
/*     */         return;
/*     */       case RED_SILHOUETTE:
/*  84 */         sb.end();
/*  85 */         sb.setShader(rsShader);
/*  86 */         sb.begin();
/*     */         return;
/*     */       case WATER:
/*  89 */         sb.end();
/*  90 */         sb.setShader(waterShader);
/*  91 */         sb.begin();
/*     */         return;
/*     */       case WHITE_SILHOUETTE:
/*  94 */         sb.end();
/*  95 */         sb.setShader(wsShader);
/*  96 */         sb.begin();
/*     */         return;
/*     */     } 
/*  99 */     sb.end();
/* 100 */     sb.setShader(null);
/* 101 */     sb.begin();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setShader(PolygonSpriteBatch sb, Shader shader) {
/* 107 */     switch (shader) {
/*     */       case BLUR:
/* 109 */         sb.setShader(blurShader);
/*     */         return;
/*     */       case DEFAULT:
/* 112 */         sb.setShader(null);
/*     */         return;
/*     */       case GRAYSCALE:
/* 115 */         sb.setShader(gsShader);
/*     */         return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     sb.setShader(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Shader
/*     */   {
/* 132 */     BLUR, DEFAULT, GRAYSCALE, RED_SILHOUETTE, WHITE_SILHOUETTE, OUTLINE, WATER;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\ShaderHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */