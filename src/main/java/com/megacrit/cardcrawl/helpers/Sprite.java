/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sprite
/*     */   extends AbstractDrawable
/*     */ {
/*     */   private Texture img;
/*     */   private String label;
/*     */   private Color color;
/*     */   private float x;
/*     */   private float y;
/*     */   private float scale;
/*     */   private float rotation;
/*     */   private boolean text;
/*     */   
/*     */   public Sprite(Texture img, float x, float y, int z, float scale, float rotation, Color color) {
/*  29 */     this.img = img;
/*  30 */     this.x = x;
/*  31 */     this.y = y;
/*  32 */     this.z = z;
/*  33 */     this.scale = scale;
/*  34 */     this.rotation = rotation;
/*  35 */     this.color = color;
/*  36 */     this.text = false;
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
/*     */   public Sprite(Texture img, float x, float y, int z, float scale, Color color) {
/*  49 */     this(img, x, y, z, Settings.scale, 0.0F, color);
/*     */   }
/*     */   
/*     */   public Sprite(Texture img, float x, float y, int z) {
/*  53 */     this(img, x, y, z, Settings.scale, null);
/*     */   }
/*     */   
/*     */   public Sprite(Texture img, float x, float y, int z, Color color) {
/*  57 */     this(img, x, y, z, Settings.scale, color);
/*     */   }
/*     */   
/*     */   public Sprite(String label, float x, float y, int z, Color color) {
/*  61 */     this.label = label;
/*  62 */     this.x = x;
/*  63 */     this.y = y;
/*  64 */     this.z = z;
/*  65 */     this.color = color;
/*  66 */     this.text = true;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  70 */     if (!this.text) {
/*  71 */       int w = this.img.getWidth();
/*  72 */       int h = this.img.getHeight();
/*     */       
/*  74 */       if (this.color != null) {
/*  75 */         sb.setColor(this.color);
/*     */       }
/*     */       
/*  78 */       if (isVisible()) {
/*  79 */         sb.draw(this.img, this.x - w / 2.0F, this.y - h / 2.0F, w / 2.0F, h / 2.0F, w, h, this.scale, this.scale, this.rotation, 0, 0, w, h, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, this.label, this.x, this.y, this.color);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isVisible() {
/* 105 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\Sprite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */