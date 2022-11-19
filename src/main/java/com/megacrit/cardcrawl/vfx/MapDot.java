/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
/*    */ 
/*    */ public class MapDot {
/*    */   private float x;
/*    */   private float y;
/* 12 */   private static final float DIST_JITTER = 4.0F * Settings.scale; private float rotation; private static final int RAW_W = 16;
/* 13 */   private static final float OFFSET_Y = 172.0F * Settings.scale;
/*    */   
/*    */   public MapDot(float x, float y, float rotation, boolean jitter) {
/* 16 */     if (jitter) {
/* 17 */       this.x = x + MathUtils.random(-DIST_JITTER, DIST_JITTER);
/* 18 */       this.y = y + MathUtils.random(-DIST_JITTER, DIST_JITTER);
/* 19 */       this.rotation = rotation + MathUtils.random(-20.0F, 20.0F);
/*    */     } else {
/* 21 */       this.x = x;
/* 22 */       this.y = y;
/* 23 */       this.rotation = rotation;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 28 */     sb.draw(ImageMaster.MAP_DOT_1, this.x - 8.0F, this.y - 8.0F + DungeonMapScreen.offsetY + OFFSET_Y, 8.0F, 8.0F, 16.0F, 16.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 16, 16, false, false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\MapDot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */