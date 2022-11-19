/*     */ package com.megacrit.cardcrawl.map;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ 
/*     */ public class LegendItem {
/*  12 */   private static final float ICON_X = 1575.0F * Settings.xScale;
/*  13 */   private static final float TEXT_X = 1670.0F * Settings.xScale;
/*  14 */   private static final float SPACE_Y = Settings.isMobile ? (64.0F * Settings.yScale) : (58.0F * Settings.yScale);
/*  15 */   private static final float OFFSET_Y = Settings.isMobile ? (110.0F * Settings.yScale) : (100.0F * Settings.yScale);
/*     */   private Texture img;
/*     */   private static final int W = 128;
/*     */   private int index;
/*     */   private String label;
/*     */   private String header;
/*     */   private String body;
/*  22 */   public Hitbox hb = new Hitbox(230.0F * Settings.xScale, SPACE_Y - 2.0F);
/*     */   
/*     */   public LegendItem(String label, Texture img, String tipHeader, String tipBody, int index) {
/*  25 */     this.label = label;
/*  26 */     this.img = img;
/*  27 */     this.header = tipHeader;
/*  28 */     this.body = tipBody;
/*  29 */     this.index = index;
/*     */   }
/*     */   
/*     */   public void update() {
/*  33 */     this.hb.update();
/*  34 */     if (this.hb.hovered) {
/*  35 */       TipHelper.renderGenericTip(1500.0F * Settings.xScale, 270.0F * Settings.scale, this.header, this.body);
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, Color c) {
/*  40 */     sb.setColor(c);
/*  41 */     if (!Settings.isMobile) {
/*  42 */       if (this.hb.hovered) {
/*  43 */         sb.draw(this.img, ICON_X - 64.0F, Legend.Y - SPACE_Y * this.index + OFFSET_Y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale / 1.2F, Settings.scale / 1.2F, 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  61 */         sb.draw(this.img, ICON_X - 64.0F, Legend.Y - SPACE_Y * this.index + OFFSET_Y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale / 1.65F, Settings.scale / 1.65F, 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  80 */     else if (this.hb.hovered) {
/*  81 */       sb.draw(this.img, ICON_X - 64.0F, Legend.Y - SPACE_Y * this.index + OFFSET_Y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/*  99 */       sb.draw(this.img, ICON_X - 64.0F, Legend.Y - SPACE_Y * this.index + OFFSET_Y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale / 1.3F, Settings.scale / 1.3F, 0.0F, 0, 0, 128, 128, false, false);
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
/* 119 */     if (Settings.isMobile) {
/* 120 */       FontHelper.panelNameFont.getData().setScale(1.2F);
/*     */     }
/* 122 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, this.label, TEXT_X - 50.0F * Settings.scale, Legend.Y - SPACE_Y * this.index + OFFSET_Y + 13.0F * Settings.yScale, c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (Settings.isMobile) {
/* 130 */       FontHelper.panelNameFont.getData().setScale(1.0F);
/*     */     }
/*     */     
/* 133 */     this.hb.move(TEXT_X, Legend.Y - SPACE_Y * this.index + OFFSET_Y);
/*     */     
/* 135 */     if (c.a != 0.0F)
/* 136 */       this.hb.render(sb); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\map\LegendItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */