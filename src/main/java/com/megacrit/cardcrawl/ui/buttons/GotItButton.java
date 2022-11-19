/*    */ package com.megacrit.cardcrawl.ui.buttons;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ 
/*    */ public class GotItButton
/*    */ {
/* 13 */   public Hitbox hb = new Hitbox(220.0F * Settings.scale, 60.0F * Settings.scale);
/*    */   private static final int W = 210;
/*    */   private static final int H = 52;
/*    */   
/*    */   public GotItButton(float x, float y) {
/* 18 */     x += 120.0F * Settings.scale;
/* 19 */     y -= 160.0F * Settings.scale;
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/*    */     
/* 23 */     this.hb.move(x, y);
/*    */   }
/*    */   float x; float y;
/*    */   public void update() {
/* 27 */     this.hb.update();
/* 28 */     if (this.hb.justHovered) {
/* 29 */       CardCrawlGame.sound.play("UI_HOVER");
/*    */     }
/*    */     
/* 32 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 33 */       this.hb.clickStarted = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 38 */     sb.draw(ImageMaster.FTUE_BTN, this.x - 105.0F, this.y - 26.0F, 105.0F, 26.0F, 210.0F, 52.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 210, 52, false, false);
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
/* 56 */     if (this.hb.hovered) {
/* 57 */       sb.setBlendFunction(770, 1);
/* 58 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
/* 59 */       sb.draw(ImageMaster.FTUE_BTN, this.x - 105.0F, this.y - 26.0F, 105.0F, 26.0F, 210.0F, 52.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 210, 52, false, false);
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
/* 76 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */     
/* 79 */     this.hb.render(sb);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\GotItButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */