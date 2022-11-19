/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ public class Hitbox
/*     */ {
/*     */   public float x;
/*     */   public float y;
/*     */   public float cX;
/*     */   
/*     */   public Hitbox(float width, float height) {
/*  17 */     this(-10000.0F, -10000.0F, width, height);
/*     */   }
/*     */   public float cY; public float width; public float height; public boolean hovered = false, justHovered = false, clickStarted = false, clicked = false;
/*     */   public Hitbox(float x, float y, float width, float height) {
/*  21 */     this.x = x;
/*  22 */     this.y = y;
/*  23 */     this.width = width;
/*  24 */     this.height = height;
/*  25 */     this.cX = x + width / 2.0F;
/*  26 */     this.cY = y + height / 2.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  30 */     update(this.x, this.y);
/*  31 */     if (this.clickStarted && 
/*  32 */       InputHelper.justReleasedClickLeft) {
/*  33 */       if (this.hovered) {
/*  34 */         this.clicked = true;
/*     */       }
/*  36 */       this.clickStarted = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(float x, float y) {
/*  42 */     if (AbstractDungeon.isFadingOut) {
/*     */       return;
/*     */     }
/*     */     
/*  46 */     this.x = x;
/*  47 */     this.y = y;
/*     */     
/*  49 */     if (this.justHovered) {
/*  50 */       this.justHovered = false;
/*     */     }
/*     */     
/*  53 */     if (!this.hovered) {
/*  54 */       this.hovered = (InputHelper.mX > x && InputHelper.mX < x + this.width && InputHelper.mY > y && InputHelper.mY < y + this.height);
/*     */       
/*  56 */       if (this.hovered) {
/*  57 */         this.justHovered = true;
/*     */       }
/*     */     } else {
/*  60 */       this.hovered = (InputHelper.mX > x && InputHelper.mX < x + this.width && InputHelper.mY > y && InputHelper.mY < y + this.height);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void encapsulatedUpdate(HitboxListener listener) {
/*  66 */     update();
/*     */     
/*  68 */     if (this.justHovered) {
/*  69 */       listener.hoverStarted(this);
/*     */     }
/*     */     
/*  72 */     if (this.hovered && InputHelper.justClickedLeft) {
/*  73 */       this.clickStarted = true;
/*  74 */       listener.startClicking(this);
/*  75 */     } else if (this.clicked || (this.hovered && CInputActionSet.select.isJustPressed())) {
/*  76 */       CInputActionSet.select.unpress();
/*  77 */       this.clicked = false;
/*  78 */       listener.clicked(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unhover() {
/*  83 */     this.hovered = false;
/*  84 */     this.justHovered = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void move(float cX, float cY) {
/*  94 */     this.cX = cX;
/*  95 */     this.cY = cY;
/*  96 */     this.x = cX - this.width / 2.0F;
/*  97 */     this.y = cY - this.height / 2.0F;
/*     */   }
/*     */   
/*     */   public void moveY(float cY) {
/* 101 */     this.cY = cY;
/* 102 */     this.y = cY - this.height / 2.0F;
/*     */   }
/*     */   
/*     */   public void moveX(float cX) {
/* 106 */     this.cX = cX;
/* 107 */     this.x = cX - this.width / 2.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(float x, float y) {
/* 117 */     this.x = x;
/* 118 */     this.y = y;
/* 119 */     this.cX = x + this.width / 2.0F;
/* 120 */     this.cY = y + this.height / 2.0F;
/*     */   }
/*     */   
/*     */   public void resize(float w, float h) {
/* 124 */     this.width = w;
/* 125 */     this.height = h;
/*     */   }
/*     */   
/*     */   public boolean intersects(Hitbox other) {
/* 129 */     return (this.x < other.x + other.width && this.x + this.width > other.x && this.y < other.y + other.height && this.y + this.height > other.y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 134 */     if (Settings.isDebug || Settings.isInfo) {
/* 135 */       if (this.clickStarted) {
/* 136 */         sb.setColor(Color.YELLOW);
/*     */       } else {
/* 138 */         sb.setColor(Color.RED);
/*     */       } 
/* 140 */       sb.draw(ImageMaster.DEBUG_HITBOX_IMG, this.x, this.y, this.width, this.height);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\Hitbox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */