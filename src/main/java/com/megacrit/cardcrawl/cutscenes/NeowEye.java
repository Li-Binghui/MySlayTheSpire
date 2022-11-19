/*     */ package com.megacrit.cardcrawl.cutscenes;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class NeowEye {
/*     */   private static Texture lid1;
/*     */   private static Texture lid2;
/*     */   private static Texture lid3;
/*     */   private static Texture lid4;
/*  15 */   private int currentFrame = 0; private static Texture lid5; private static Texture lid6; private static Texture eyeImg; private Texture eyeLid; private float eyeLidTimer; private float leftX; private float rightX;
/*     */   private float y;
/*     */   private float oY;
/*     */   private float scale;
/*     */   private float angle;
/*     */   
/*     */   public NeowEye(int eyePosition) {
/*  22 */     if (lid1 == null) {
/*  23 */       lid1 = ImageMaster.loadImage("images/scenes/neow/lid1.png");
/*  24 */       lid2 = ImageMaster.loadImage("images/scenes/neow/lid2.png");
/*  25 */       lid3 = ImageMaster.loadImage("images/scenes/neow/lid3.png");
/*  26 */       lid4 = ImageMaster.loadImage("images/scenes/neow/lid4.png");
/*  27 */       lid5 = ImageMaster.loadImage("images/scenes/neow/lid5.png");
/*  28 */       lid6 = ImageMaster.loadImage("images/scenes/neow/lid6.png");
/*  29 */       eyeImg = ImageMaster.loadImage("images/scenes/neow/eye.png");
/*     */     } 
/*     */     
/*  32 */     switch (eyePosition) {
/*     */       case 0:
/*  34 */         this.leftX = 1410.0F * Settings.xScale - 128.0F;
/*  35 */         this.rightX = 510.0F * Settings.xScale - 128.0F;
/*  36 */         this.y = Settings.HEIGHT / 2.0F - 180.0F * Settings.scale - 128.0F;
/*  37 */         this.oY = 20.0F * Settings.scale;
/*  38 */         this.scale = Settings.scale;
/*  39 */         this.angle = -10.0F;
/*  40 */         this.eyeLidTimer = 3.0F;
/*     */         break;
/*     */       case 1:
/*  43 */         this.leftX = 1610.0F * Settings.xScale - 128.0F;
/*  44 */         this.rightX = 310.0F * Settings.xScale - 128.0F;
/*  45 */         this.y = Settings.HEIGHT / 2.0F + 80.0F * Settings.scale - 128.0F;
/*  46 */         this.oY = 15.0F * Settings.scale;
/*  47 */         this.scale = 0.75F * Settings.scale;
/*  48 */         this.angle = 10.0F;
/*  49 */         this.eyeLidTimer = 3.15F;
/*     */         break;
/*     */       case 2:
/*  52 */         this.leftX = 1710.0F * Settings.xScale - 128.0F;
/*  53 */         this.rightX = 210.0F * Settings.xScale - 128.0F;
/*  54 */         this.y = Settings.HEIGHT / 2.0F + 290.0F * Settings.scale - 128.0F;
/*  55 */         this.oY = 10.0F * Settings.scale;
/*  56 */         this.scale = 0.5F * Settings.scale;
/*  57 */         this.angle = 15.0F;
/*  58 */         this.eyeLidTimer = 3.3F;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.eyeLid = lid1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  69 */     this.eyeLidTimer -= Gdx.graphics.getDeltaTime();
/*  70 */     if (this.eyeLidTimer < 0.0F) {
/*  71 */       this.currentFrame++;
/*  72 */       if (this.currentFrame > 9) {
/*  73 */         this.currentFrame = 0;
/*     */       }
/*     */       
/*  76 */       switch (this.currentFrame) {
/*     */         case 0:
/*  78 */           this.eyeLid = lid1;
/*  79 */           this.eyeLidTimer += 5.0F;
/*     */           break;
/*     */         case 1:
/*  82 */           this.eyeLid = lid2;
/*  83 */           this.eyeLidTimer += 0.04F;
/*     */           break;
/*     */         case 2:
/*  86 */           this.eyeLid = lid3;
/*  87 */           this.eyeLidTimer += 0.04F;
/*     */           break;
/*     */         case 3:
/*  90 */           this.eyeLid = lid4;
/*  91 */           this.eyeLidTimer += 0.04F;
/*     */           break;
/*     */         case 4:
/*  94 */           this.eyeLid = lid5;
/*  95 */           this.eyeLidTimer += 0.04F;
/*     */           break;
/*     */         case 5:
/*  98 */           this.eyeLid = lid6;
/*  99 */           this.eyeLidTimer = 0.25F;
/*     */           break;
/*     */         case 6:
/* 102 */           this.eyeLid = lid5;
/* 103 */           this.eyeLidTimer += 0.06F;
/*     */           break;
/*     */         case 7:
/* 106 */           this.eyeLid = lid4;
/* 107 */           this.eyeLidTimer += 0.06F;
/*     */           break;
/*     */         case 8:
/* 110 */           this.eyeLid = lid3;
/* 111 */           this.eyeLidTimer += 0.06F;
/*     */           break;
/*     */         case 9:
/* 114 */           this.eyeLid = lid2;
/* 115 */           this.eyeLidTimer += 0.06F;
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderRight(SpriteBatch sb) {
/* 125 */     sb.draw(eyeImg, this.leftX, this.y + 
/*     */ 
/*     */         
/* 128 */         MathUtils.cosDeg((float)(System.currentTimeMillis() / 12L % 360L)) * this.oY, 128.0F, 128.0F, 256.0F, 256.0F, this.scale, this.scale, this.angle, 0, 0, 256, 256, false, false);
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
/* 143 */     sb.draw(this.eyeLid, this.leftX, this.y + 
/*     */ 
/*     */         
/* 146 */         MathUtils.cosDeg((float)(System.currentTimeMillis() / 12L % 360L)) * this.oY, 128.0F, 128.0F, 256.0F, 256.0F, this.scale, this.scale, this.angle, 0, 0, 256, 256, false, false);
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
/*     */   public void renderLeft(SpriteBatch sb) {
/* 163 */     sb.draw(eyeImg, this.rightX, this.y + 
/*     */ 
/*     */         
/* 166 */         MathUtils.cosDeg((float)(System.currentTimeMillis() / 12L % 360L)) * this.oY, 128.0F, 128.0F, 256.0F, 256.0F, this.scale, this.scale, -this.angle, 0, 0, 256, 256, true, false);
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
/* 181 */     sb.draw(this.eyeLid, this.rightX, this.y + 
/*     */ 
/*     */         
/* 184 */         MathUtils.cosDeg((float)(System.currentTimeMillis() / 12L % 360L)) * this.oY, 128.0F, 128.0F, 256.0F, 256.0F, this.scale, this.scale, -this.angle, 0, 0, 256, 256, true, false);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cutscenes\NeowEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */