/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class ObtainKeyEffect extends AbstractGameEffect {
/*    */   private Texture img;
/*    */   private float x;
/*    */   private float y;
/* 16 */   private KeyColor keyColor = null;
/*    */   
/*    */   public enum KeyColor {
/* 19 */     RED, GREEN, BLUE;
/*    */   }
/*    */   
/*    */   public ObtainKeyEffect(KeyColor keyColor) {
/* 23 */     this.keyColor = keyColor;
/*    */     
/* 25 */     switch (keyColor) {
/*    */       case RED:
/* 27 */         this.img = ImageMaster.RUBY_KEY;
/*    */         break;
/*    */       case GREEN:
/* 30 */         this.img = ImageMaster.EMERALD_KEY;
/*    */         break;
/*    */       case BLUE:
/* 33 */         this.img = ImageMaster.SAPPHIRE_KEY;
/*    */         break;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 39 */     this.duration = 0.33F;
/* 40 */     this.startingDuration = 0.33F;
/* 41 */     this.x = -32.0F + 46.0F * Settings.scale;
/* 42 */     this.y = (Settings.HEIGHT - 32) - 35.0F * Settings.scale;
/* 43 */     this.color = Color.WHITE.cpy();
/* 44 */     this.color.a = 0.0F;
/* 45 */     this.rotation = 180.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 50 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 51 */     if (this.duration < 0.0F) {
/* 52 */       this.isDone = true;
/* 53 */       this.duration = 0.0F;
/* 54 */       this.color.a = 0.0F;
/* 55 */       CardCrawlGame.sound.playA("KEY_OBTAIN", -0.2F);
/*    */       
/* 57 */       switch (this.keyColor) {
/*    */         case RED:
/* 59 */           this.img = ImageMaster.RUBY_KEY;
/* 60 */           Settings.hasRubyKey = true;
/*    */           break;
/*    */         case GREEN:
/* 63 */           this.img = ImageMaster.EMERALD_KEY;
/* 64 */           Settings.hasEmeraldKey = true;
/*    */           break;
/*    */         case BLUE:
/* 67 */           this.img = ImageMaster.SAPPHIRE_KEY;
/* 68 */           Settings.hasSapphireKey = true;
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */     
/*    */     } else {
/* 75 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.duration * 3.0F);
/* 76 */       this.scale = Interpolation.pow4In.apply(1.1F, 5.0F, this.duration * 3.0F) * Settings.scale;
/* 77 */       this.rotation = Interpolation.pow4In.apply(0.0F, 180.0F, this.duration * 3.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 83 */     sb.setColor(this.color);
/* 84 */     sb.draw(this.img, this.x, this.y, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/* 85 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.duration * 3.0F));
/* 86 */     sb.setBlendFunction(770, 1);
/* 87 */     sb.draw(this.img, this.x, this.y, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/* 88 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ObtainKeyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */