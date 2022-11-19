/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Label
/*    */   extends AbstractDrawable
/*    */ {
/*    */   private BitmapFont font;
/*    */   private String msg;
/*    */   private Color color;
/*    */   private float x;
/*    */   private float y;
/*    */   private float scale;
/*    */   
/*    */   public Label(BitmapFont font, String msg, float x, float y, int z, float scale, Color color) {
/* 35 */     this.font = font;
/* 36 */     this.msg = msg;
/* 37 */     this.x = x;
/* 38 */     this.y = y;
/* 39 */     this.z = z;
/* 40 */     this.scale = scale;
/* 41 */     this.color = color;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 45 */     this.font.getData().setScale(this.scale);
/* 46 */     FontHelper.renderFontCentered(sb, this.font, this.msg, this.x, this.y, this.color);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\Label.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */