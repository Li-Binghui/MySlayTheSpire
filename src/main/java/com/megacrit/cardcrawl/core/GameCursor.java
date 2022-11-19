/*     */ package com.megacrit.cardcrawl.core;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ 
/*     */ public class GameCursor
/*     */ {
/*     */   private Texture img;
/*     */   private Texture mImg;
/*  15 */   private static final float OFFSET_X = 24.0F * Settings.scale; public static final int W = 64; public static boolean hidden = false; private float rotation;
/*  16 */   private static final float OFFSET_Y = -24.0F * Settings.scale;
/*  17 */   private static final float SHADOW_OFFSET_X = -10.0F * Settings.scale;
/*  18 */   private static final float SHADOW_OFFSET_Y = 8.0F * Settings.scale;
/*  19 */   private static final Color SHADOW_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.15F);
/*     */   private static final float TILT_ANGLE = 6.0F;
/*  21 */   private CursorType type = CursorType.NORMAL;
/*  22 */   public Color color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   
/*     */   public enum CursorType {
/*  25 */     NORMAL, INSPECT;
/*     */   }
/*     */   
/*     */   public GameCursor() {
/*  29 */     this.img = ImageMaster.loadImage("images/ui/cursors/gold2.png");
/*  30 */     this.mImg = ImageMaster.loadImage("images/ui/cursors/magGlass2.png");
/*     */   }
/*     */   
/*     */   public void update() {
/*  34 */     if (InputHelper.isMouseDown) {
/*  35 */       this.rotation = 6.0F;
/*     */     } else {
/*  37 */       this.rotation = 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeType(CursorType type) {
/*  42 */     this.type = type;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  46 */     if (hidden || Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/*  50 */     if (!Settings.isTouchScreen || Settings.isDev) {
/*  51 */       switch (this.type) {
/*     */         case NORMAL:
/*  53 */           sb.setColor(SHADOW_COLOR);
/*  54 */           sb.draw(this.img, InputHelper.mX - 32.0F - SHADOW_OFFSET_X + OFFSET_X, InputHelper.mY - 32.0F - SHADOW_OFFSET_Y + OFFSET_Y, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 64, 64, false, false);
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
/*  72 */           sb.setColor(Color.WHITE);
/*  73 */           sb.draw(this.img, InputHelper.mX - 32.0F + OFFSET_X, InputHelper.mY - 32.0F + OFFSET_Y, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */           break;
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
/*     */         case INSPECT:
/*  92 */           sb.setColor(SHADOW_COLOR);
/*  93 */           sb.draw(this.mImg, InputHelper.mX - 32.0F - SHADOW_OFFSET_X + OFFSET_X, InputHelper.mY - 32.0F - SHADOW_OFFSET_Y + OFFSET_Y, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 64, 64, false, false);
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
/* 111 */           sb.setColor(Color.WHITE);
/* 112 */           sb.draw(this.mImg, InputHelper.mX - 32.0F + OFFSET_X, InputHelper.mY - 32.0F + OFFSET_Y, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */           break;
/*     */       } 
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
/* 133 */       changeType(CursorType.NORMAL);
/*     */     } else {
/* 135 */       this.color.a = MathHelper.slowColorLerpSnap(this.color.a, 0.0F);
/* 136 */       sb.setColor(this.color);
/* 137 */       sb.draw(ImageMaster.WOBBLY_ORB_VFX, InputHelper.mX - 16.0F + OFFSET_X - 24.0F * Settings.scale, InputHelper.mY - 16.0F + OFFSET_Y + 24.0F * Settings.scale, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 32, 32, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\GameCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */