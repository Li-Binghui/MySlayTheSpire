/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*    */ 
/*    */ public class DrawMaster
/*    */ {
/* 13 */   public static List<AbstractDrawable> drawList = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public static void draw(SpriteBatch sb) {
/* 18 */     Collections.sort(drawList);
/* 19 */     sb.setColor(Color.WHITE);
/*    */     
/* 21 */     for (AbstractDrawable o : drawList) {
/* 22 */       o.render(sb);
/*    */     }
/*    */     
/* 25 */     drawList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void queue(Texture img, float x, float y, int z, Color color) {
/* 30 */     drawList.add(new Sprite(img, x, y, z, color));
/*    */   }
/*    */   
/*    */   public static void queue(Texture img, float x, float y, int z, float scale, Color color) {
/* 34 */     drawList.add(new Sprite(img, x, y, z, scale, color));
/*    */   }
/*    */   
/*    */   public static void queue(Texture img, float x, float y, int z, float scale, float rotation, Color color) {
/* 38 */     drawList.add(new Sprite(img, x, y, z, scale, rotation, color));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void queue(BitmapFont font, String label, float x, float y, int z, float scale, Color color) {
/* 43 */     drawList.add(new Label(font, label, x, y, z, scale, color));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\DrawMaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */