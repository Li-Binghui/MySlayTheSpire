/*     */ package com.megacrit.cardcrawl.map;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Legend
/*     */ {
/*  19 */   public static final float X = 1670.0F * Settings.xScale; public static final float Y = 600.0F * Settings.yScale; private static final int LW = 512;
/*     */   private static final int LH = 800;
/*  21 */   public Color c = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  22 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Legend");
/*  23 */   public static final String[] TEXT = uiStrings.TEXT;
/*  24 */   public ArrayList<LegendItem> items = new ArrayList<>();
/*     */   public boolean isLegendHighlighted = false;
/*  26 */   private static Texture img = null;
/*     */   
/*     */   public Legend() {
/*  29 */     this.items.add(new LegendItem(TEXT[0], ImageMaster.MAP_NODE_EVENT, TEXT[1], TEXT[2], 0));
/*  30 */     this.items.add(new LegendItem(TEXT[3], ImageMaster.MAP_NODE_MERCHANT, TEXT[4], TEXT[5], 1));
/*  31 */     this.items.add(new LegendItem(TEXT[6], ImageMaster.MAP_NODE_TREASURE, TEXT[7], TEXT[8], 2));
/*  32 */     this.items.add(new LegendItem(TEXT[9], ImageMaster.MAP_NODE_REST, TEXT[10], TEXT[11], 3));
/*  33 */     this.items.add(new LegendItem(TEXT[12], ImageMaster.MAP_NODE_ENEMY, TEXT[13], TEXT[14], 4));
/*  34 */     this.items.add(new LegendItem(TEXT[15], ImageMaster.MAP_NODE_ELITE, TEXT[16], TEXT[17], 5));
/*  35 */     if (img == null) {
/*  36 */       img = ImageMaster.loadImage("images/ui/map/selectBox.png");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isIconHovered(String nodeHovered) {
/*  41 */     switch (nodeHovered) {
/*     */       case "?":
/*  43 */         if (((LegendItem)this.items.get(0)).hb.hovered) {
/*  44 */           return true;
/*     */         }
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
/*  75 */         return false;case "$": if (((LegendItem)this.items.get(1)).hb.hovered) return true;  return false;case "T": if (((LegendItem)this.items.get(2)).hb.hovered) return true;  return false;case "R": if (((LegendItem)this.items.get(3)).hb.hovered) return true;  return false;case "M": if (((LegendItem)this.items.get(4)).hb.hovered) return true;  return false;case "E": if (((LegendItem)this.items.get(5)).hb.hovered) return true;  return false;
/*     */     } 
/*     */     return false;
/*     */   } public void update(float mapAlpha, boolean isMapScreen) {
/*  79 */     if (mapAlpha >= 0.8F && isMapScreen) {
/*  80 */       updateControllerInput();
/*  81 */       this.c.a = MathHelper.fadeLerpSnap(this.c.a, 1.0F);
/*  82 */       for (LegendItem i : this.items) {
/*  83 */         i.update();
/*     */       }
/*     */     } else {
/*  86 */       this.c.a = MathHelper.fadeLerpSnap(this.c.a, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/*  91 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     if (this.isLegendHighlighted) {
/*  96 */       if (CInputActionSet.proceed.isJustPressed() || CInputActionSet.cancel.isJustPressed() || CInputActionSet.left
/*  97 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  98 */         CInputActionSet.cancel.unpress();
/*  99 */         this.isLegendHighlighted = false;
/*     */         
/*     */         return;
/*     */       } 
/* 103 */     } else if (CInputActionSet.proceed.isJustPressed()) {
/* 104 */       this.isLegendHighlighted = true;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 109 */     if (!this.isLegendHighlighted) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     boolean anyHovered = false;
/* 114 */     int index = 0;
/* 115 */     for (LegendItem i : this.items) {
/* 116 */       if (i.hb.hovered) {
/* 117 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 120 */       index++;
/*     */     } 
/*     */     
/* 123 */     if (!anyHovered) {
/* 124 */       Gdx.input.setCursorPosition((int)((LegendItem)this.items.get(0)).hb.cX, Settings.HEIGHT - (int)((LegendItem)this.items.get(0)).hb.cY);
/*     */     }
/* 126 */     else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 127 */       index++;
/* 128 */       if (index > this.items.size() - 1) {
/* 129 */         index = 0;
/*     */       }
/* 131 */       Gdx.input.setCursorPosition(
/* 132 */           (int)((LegendItem)this.items.get(index)).hb.cX, Settings.HEIGHT - 
/* 133 */           (int)((LegendItem)this.items.get(index)).hb.cY);
/* 134 */     } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 135 */       index--;
/* 136 */       if (index < 0) {
/* 137 */         index = this.items.size() - 1;
/*     */       }
/* 139 */       Gdx.input.setCursorPosition(
/* 140 */           (int)((LegendItem)this.items.get(index)).hb.cX, Settings.HEIGHT - 
/* 141 */           (int)((LegendItem)this.items.get(index)).hb.cY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 147 */     sb.setColor(this.c);
/* 148 */     if (!Settings.isMobile) {
/* 149 */       sb.draw(ImageMaster.MAP_LEGEND, X - 256.0F, Y - 400.0F, 256.0F, 400.0F, 512.0F, 800.0F, Settings.scale, Settings.yScale, 0.0F, 0, 0, 512, 800, false, false);
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */       
/* 167 */       sb.draw(ImageMaster.MAP_LEGEND, X - 256.0F, Y - 400.0F, 256.0F, 400.0F, 512.0F, 800.0F, Settings.scale * 1.1F, Settings.yScale * 1.1F, 0.0F, 0, 0, 512, 800, false, false);
/*     */     } 
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
/* 187 */     Color c2 = new Color(MapRoomNode.AVAILABLE_COLOR.r, MapRoomNode.AVAILABLE_COLOR.g, MapRoomNode.AVAILABLE_COLOR.b, this.c.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     if (Settings.isMobile) {
/* 194 */       FontHelper.renderFontCentered(sb, FontHelper.menuBannerFont, TEXT[18], X, Y + 190.0F * Settings.yScale, c2, 1.4F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 203 */       FontHelper.renderFontCentered(sb, FontHelper.menuBannerFont, TEXT[18], X, Y + 170.0F * Settings.yScale, c2);
/*     */     } 
/*     */     
/* 206 */     sb.setColor(c2);
/*     */     
/* 208 */     for (LegendItem i : this.items) {
/* 209 */       i.render(sb, c2);
/*     */     }
/*     */     
/* 212 */     if (Settings.isControllerMode) {
/* 213 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, c2.a));
/* 214 */       sb.draw(CInputActionSet.proceed
/* 215 */           .getKeyImg(), 1570.0F * Settings.xScale - 32.0F, Y + 170.0F * Settings.yScale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 232 */       if (this.isLegendHighlighted) {
/* 233 */         sb.setColor(new Color(1.0F, 0.9F, 0.5F, 0.6F + 
/* 234 */               MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) / 5.0F));
/* 235 */         float doop = 1.0F + (1.0F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L))) / 50.0F;
/* 236 */         sb.draw(img, 1670.0F * Settings.scale - 160.0F, (Settings.HEIGHT - Gdx.input
/*     */ 
/*     */             
/* 239 */             .getY()) - 52.0F + 4.0F * Settings.scale, 160.0F, 52.0F, 320.0F, 104.0F, Settings.scale * doop, Settings.scale * doop, 0.0F, 0, 0, 320, 104, false, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\map\Legend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */