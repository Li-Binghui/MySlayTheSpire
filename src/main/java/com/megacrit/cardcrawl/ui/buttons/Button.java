/*    */ package com.megacrit.cardcrawl.ui.buttons;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ 
/*    */ public class Button
/*    */ {
/*    */   public float x;
/*    */   public float y;
/* 13 */   protected Color activeColor = Color.WHITE; private Texture img; protected Hitbox hb;
/* 14 */   protected Color inactiveColor = new Color(0.6F, 0.6F, 0.6F, 1.0F);
/*    */   
/*    */   public boolean pressed = false;
/*    */   public int height;
/*    */   public int width;
/*    */   
/*    */   public Button(float x, float y, Texture img) {
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/* 23 */     this.img = img;
/* 24 */     this.hb = new Hitbox(x, y, img.getWidth(), img.getHeight());
/* 25 */     this.height = img.getHeight();
/* 26 */     this.width = img.getWidth();
/*    */   }
/*    */   
/*    */   public void update() {
/* 30 */     this.hb.update(this.x, this.y);
/* 31 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 32 */       this.pressed = true;
/* 33 */       InputHelper.justClickedLeft = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 38 */     if (this.hb.hovered) {
/* 39 */       sb.setColor(this.activeColor);
/*    */     } else {
/* 41 */       sb.setColor(this.inactiveColor);
/*    */     } 
/* 43 */     sb.draw(this.img, this.x, this.y);
/* 44 */     sb.setColor(Color.WHITE);
/*    */     
/* 46 */     this.hb.render(sb);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\Button.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */