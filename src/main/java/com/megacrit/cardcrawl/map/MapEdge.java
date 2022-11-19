/*     */ package com.megacrit.cardcrawl.map;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.MapDot;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class MapEdge
/*     */   implements Comparable<MapEdge>
/*     */ {
/*     */   public final int dstX;
/*     */   public final int dstY;
/*     */   public final int srcX;
/*     */   public final int srcY;
/*  19 */   public static final float ICON_SRC_RADIUS = 29.0F * Settings.scale;
/*  20 */   private static final float ICON_DST_RADIUS = 20.0F * Settings.scale;
/*  21 */   private static final float SPACING = Settings.isMobile ? (20.0F * Settings.xScale) : (17.0F * Settings.xScale);
/*  22 */   private ArrayList<MapDot> dots = new ArrayList<>();
/*  23 */   private static final Color DISABLED_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.25F);
/*  24 */   public Color color = DISABLED_COLOR.cpy();
/*     */   public boolean taken = false;
/*  26 */   private static final float SPACE_X = Settings.isMobile ? (140.8F * Settings.xScale) : (128.0F * Settings.xScale);
/*     */   
/*     */   public MapEdge(int srcX, int srcY, int dstX, int dstY) {
/*  29 */     this.srcX = srcX;
/*  30 */     this.srcY = srcY;
/*  31 */     this.dstX = dstX;
/*  32 */     this.dstY = dstY;
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
/*     */   public MapEdge(int srcX, int srcY, float srcOffsetX, float srcOffsetY, int dstX, int dstY, float dstOffsetX, float dstOffsetY, boolean isBoss) {
/*  46 */     this.srcX = srcX;
/*  47 */     this.srcY = srcY;
/*  48 */     this.dstX = dstX;
/*  49 */     this.dstY = dstY;
/*     */     
/*  51 */     float tmpSX = getX(srcX) + srcOffsetX;
/*  52 */     float tmpDX = getX(dstX) + dstOffsetX;
/*  53 */     float tmpSY = srcY * Settings.MAP_DST_Y + srcOffsetY;
/*  54 */     float tmpDY = dstY * Settings.MAP_DST_Y + dstOffsetY;
/*     */     
/*  56 */     Vector2 vec2 = (new Vector2(tmpDX, tmpDY)).sub(new Vector2(tmpSX, tmpSY));
/*  57 */     float length = vec2.len();
/*  58 */     float START = SPACING * MathUtils.random() / 2.0F;
/*     */     
/*  60 */     float tmpRadius = ICON_DST_RADIUS;
/*  61 */     if (isBoss) {
/*  62 */       tmpRadius = 164.0F * Settings.scale;
/*     */     }
/*     */     float i;
/*  65 */     for (i = START + tmpRadius; i < length - ICON_SRC_RADIUS; i += SPACING) {
/*  66 */       vec2.clamp(length - i, length - i);
/*  67 */       if (i != START + tmpRadius && i <= length - ICON_SRC_RADIUS - SPACING) {
/*  68 */         this.dots.add(new MapDot(tmpSX + vec2.x, tmpSY + vec2.y, (new Vector2(tmpSX - tmpDX, tmpSY - tmpDY))
/*     */ 
/*     */ 
/*     */               
/*  72 */               .nor().angle() + 90.0F, true));
/*     */       } else {
/*     */         
/*  75 */         this.dots.add(new MapDot(tmpSX + vec2.x, tmpSY + vec2.y, (new Vector2(tmpSX - tmpDX, tmpSY - tmpDY))
/*     */ 
/*     */ 
/*     */               
/*  79 */               .nor().angle() + 90.0F, false));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float getX(int x) {
/*  86 */     return x * SPACE_X + MapRoomNode.OFFSET_X;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  91 */     return "(" + this.dstX + "," + this.dstY + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(MapEdge e) {
/*  96 */     if (this.dstX > e.dstX)
/*  97 */       return 1; 
/*  98 */     if (this.dstX < e.dstX) {
/*  99 */       return -1;
/*     */     }
/* 101 */     if (this.dstY > e.dstY)
/* 102 */       return 1; 
/* 103 */     if (this.dstY < e.dstY)
/* 104 */       return -1; 
/* 105 */     if (this.dstY == e.dstY) {
/* 106 */       return 0;
/*     */     }
/*     */     assert false;
/* 109 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markAsTaken() {
/* 116 */     this.taken = true;
/* 117 */     this.color = MapRoomNode.AVAILABLE_COLOR;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 121 */     sb.setColor(this.color);
/* 122 */     for (MapDot d : this.dots)
/* 123 */       d.render(sb); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\map\MapEdge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */