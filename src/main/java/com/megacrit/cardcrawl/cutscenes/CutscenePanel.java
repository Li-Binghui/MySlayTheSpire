/*    */ package com.megacrit.cardcrawl.cutscenes;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ 
/*    */ public class CutscenePanel
/*    */ {
/*    */   private Texture img;
/* 15 */   private Color color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*    */   public boolean activated = false;
/* 17 */   private String sfx = null; public boolean finished = false; public boolean fadeOut = false;
/*    */   
/*    */   public CutscenePanel(String imgUrl, String sfx) {
/* 20 */     this.img = ImageMaster.loadImage(imgUrl);
/* 21 */     this.sfx = sfx;
/*    */   }
/*    */   
/*    */   public CutscenePanel(String imgUrl) {
/* 25 */     this(imgUrl, null);
/*    */   }
/*    */   
/*    */   public void update() {
/* 29 */     if (this.fadeOut) {
/* 30 */       this.color.a -= Gdx.graphics.getDeltaTime();
/* 31 */       if (this.color.a < 0.0F) {
/* 32 */         this.color.a = 0.0F;
/* 33 */         this.finished = true;
/*    */       } 
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     if (this.activated && !this.finished) {
/* 39 */       if (this.sfx != null) {
/* 40 */         this.color.a += Gdx.graphics.getDeltaTime() * 10.0F;
/*    */       } else {
/* 42 */         this.color.a += Gdx.graphics.getDeltaTime();
/*    */       } 
/*    */       
/* 45 */       if (this.color.a > 1.0F) {
/* 46 */         this.color.a = 1.0F;
/* 47 */         this.finished = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void activate() {
/* 53 */     if (this.sfx != null) {
/* 54 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
/* 55 */       CardCrawlGame.sound.play(this.sfx);
/* 56 */       CardCrawlGame.sound.playA(this.sfx, -0.2F);
/*    */     } 
/* 58 */     this.activated = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 62 */     if (this.img != null) {
/* 63 */       sb.setColor(this.color);
/* 64 */       if (Settings.isSixteenByTen) {
/* 65 */         sb.draw(this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */       } else {
/* 67 */         sb.draw(this.img, 0.0F, -50.0F * Settings.scale, Settings.WIDTH, Settings.HEIGHT + 110.0F * Settings.scale);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void fadeOut() {
/* 73 */     this.fadeOut = true;
/* 74 */     this.finished = false;
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 78 */     if (this.img != null) {
/* 79 */       this.img.dispose();
/* 80 */       this.img = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cutscenes\CutscenePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */