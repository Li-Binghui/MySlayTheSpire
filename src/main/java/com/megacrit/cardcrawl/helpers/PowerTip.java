/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ 
/*    */ public class PowerTip {
/*    */   public Texture img;
/*    */   public TextureAtlas.AtlasRegion imgRegion;
/*    */   public String header;
/*    */   public String body;
/*    */   
/*    */   public PowerTip() {}
/*    */   
/*    */   public PowerTip(String header, String body) {
/* 15 */     this.header = header;
/* 16 */     this.body = body;
/* 17 */     this.img = null;
/* 18 */     this.imgRegion = null;
/*    */   }
/*    */   
/*    */   public PowerTip(String header, String body, Texture img) {
/* 22 */     this.header = header;
/* 23 */     this.body = body;
/* 24 */     this.img = img;
/* 25 */     this.imgRegion = null;
/*    */   }
/*    */   
/*    */   public PowerTip(String header, String body, TextureAtlas.AtlasRegion region48) {
/* 29 */     this.header = header;
/* 30 */     this.body = body;
/* 31 */     this.imgRegion = region48;
/* 32 */     this.img = null;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\PowerTip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */