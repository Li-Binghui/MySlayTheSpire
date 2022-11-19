/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public abstract class AbstractPanel
/*     */ {
/*  14 */   private static final Logger logger = LogManager.getLogger(AbstractPanel.class.getName());
/*     */ 
/*     */   
/*     */   private static final float SNAP_THRESHOLD = 0.5F;
/*     */ 
/*     */   
/*     */   private static final float LERP_SPEED = 7.0F;
/*     */ 
/*     */   
/*     */   public float hide_x;
/*     */ 
/*     */   
/*     */   public float hide_y;
/*     */   
/*     */   public float show_x;
/*     */   
/*     */   public float show_y;
/*     */   
/*     */   protected Texture img;
/*     */   
/*     */   protected float img_width;
/*     */   
/*     */   protected float img_height;
/*     */   
/*     */   public boolean isHidden = false;
/*     */   
/*     */   public float target_x;
/*     */   
/*     */   public float target_y;
/*     */   
/*     */   public float current_x;
/*     */   
/*     */   public float current_y;
/*     */   
/*     */   public boolean doneAnimating = true;
/*     */ 
/*     */   
/*     */   public AbstractPanel(float show_x, float show_y, float hide_x, float hide_y, float shadow_offset_x, float shadow_offset_y, Texture img, boolean startHidden) {
/*  52 */     this.img = img;
/*  53 */     this.show_x = show_x;
/*  54 */     this.show_y = show_y;
/*  55 */     this.hide_x = hide_x;
/*  56 */     this.hide_y = hide_y;
/*  57 */     if (img != null) {
/*  58 */       this.img_width = img.getWidth() * Settings.scale;
/*  59 */       this.img_height = img.getHeight() * Settings.scale;
/*     */     } 
/*  61 */     if (startHidden) {
/*  62 */       this.current_x = hide_x;
/*  63 */       this.current_y = hide_y;
/*  64 */       this.target_x = hide_x;
/*  65 */       this.target_y = hide_y;
/*  66 */       this.isHidden = true;
/*     */     } else {
/*  68 */       this.current_x = show_x;
/*  69 */       this.current_y = show_y;
/*  70 */       this.target_x = show_x;
/*  71 */       this.target_y = show_y;
/*  72 */       this.isHidden = false;
/*     */     } 
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
/*     */   public AbstractPanel(float show_x, float show_y, float hide_x, float hide_y, Texture img, boolean startHidden) {
/*  90 */     this(show_x, show_y, hide_x, hide_y, 0.0F, 0.0F, img, startHidden);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void show() {
/*  97 */     if (this.isHidden) {
/*  98 */       this.target_x = this.show_x;
/*  99 */       this.target_y = this.show_y;
/* 100 */       this.isHidden = false;
/* 101 */       this.doneAnimating = false;
/*     */     }
/* 103 */     else if (Settings.isDebug) {
/* 104 */       logger.info("Attempting to show panel through already shown");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void hide() {
/* 113 */     if (!this.isHidden) {
/* 114 */       this.target_x = this.hide_x;
/* 115 */       this.target_y = this.hide_y;
/* 116 */       this.isHidden = true;
/* 117 */       this.doneAnimating = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updatePositions() {
/* 125 */     if (this.current_x != this.target_x) {
/* 126 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 7.0F);
/* 127 */       if (Math.abs(this.current_x - this.target_x) < 0.5F) {
/* 128 */         this.current_x = this.target_x;
/* 129 */         this.doneAnimating = true;
/*     */       } else {
/* 131 */         this.doneAnimating = false;
/*     */       } 
/*     */     } 
/* 134 */     if (this.current_y != this.target_y) {
/* 135 */       this.current_y = MathUtils.lerp(this.current_y, this.target_y, Gdx.graphics.getDeltaTime() * 7.0F);
/* 136 */       if (Math.abs(this.current_y - this.target_y) < 0.5F) {
/* 137 */         this.current_y = this.target_y;
/* 138 */         this.doneAnimating = true;
/*     */       } else {
/* 140 */         this.doneAnimating = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 151 */     if (this.img != null) {
/* 152 */       sb.setColor(Color.WHITE);
/* 153 */       sb.draw(this.img, this.current_x, this.current_y, this.img_width, this.img_height, 0, 0, this.img
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 161 */           .getWidth(), this.img
/* 162 */           .getHeight(), false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\AbstractPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */