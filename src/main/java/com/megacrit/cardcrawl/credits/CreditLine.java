/*    */ package com.megacrit.cardcrawl.credits;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ 
/*    */ public class CreditLine {
/*    */   public String text;
/* 11 */   private float x = Settings.WIDTH / 2.0F;
/*    */   
/*    */   private float y;
/*    */   
/*    */   public CreditLine(String text, float offset, boolean isHeader) {
/* 16 */     this.text = text;
/* 17 */     this.y = offset * Settings.scale;
/*    */     
/* 19 */     if (isHeader) {
/* 20 */       this.font = FontHelper.tipBodyFont;
/* 21 */       this.color = Settings.GOLD_COLOR.cpy();
/*    */     } else {
/* 23 */       this.font = FontHelper.panelNameFont;
/* 24 */       this.color = Settings.CREAM_COLOR.cpy();
/*    */     } 
/*    */   }
/*    */   private BitmapFont font; private Color color;
/*    */   public CreditLine(String text, float offset, boolean isHeader, boolean left) {
/* 29 */     this.text = text;
/* 30 */     this.y = offset * Settings.scale;
/*    */     
/* 32 */     if (isHeader) {
/* 33 */       this.font = FontHelper.tipBodyFont;
/* 34 */       this.color = Settings.GOLD_COLOR.cpy();
/*    */     } else {
/* 36 */       this.font = FontHelper.panelNameFont;
/* 37 */       this.color = Settings.CREAM_COLOR.cpy();
/*    */     } 
/*    */     
/* 40 */     if (left) {
/* 41 */       this.x = Settings.WIDTH * 0.4F;
/*    */     } else {
/* 43 */       this.x = Settings.WIDTH * 0.6F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb, float scrollY) {
/* 48 */     FontHelper.renderFontCentered(sb, this.font, this.text, this.x, this.y + scrollY, this.color);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\credits\CreditLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */