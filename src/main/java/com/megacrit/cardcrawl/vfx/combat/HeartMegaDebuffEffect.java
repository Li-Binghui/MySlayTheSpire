/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeartMegaDebuffEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public void update() {
/* 23 */     if (this.duration == this.startingDuration) {
/* 24 */       CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6F);
/*    */     }
/* 26 */     if (this.duration > this.startingDuration / 2.0F) {
/* 27 */       this.color.a = Interpolation.bounceIn.apply(1.0F, 0.0F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 32 */       this.color.a = Interpolation.bounceOut.apply(this.duration * this.startingDuration / 2.0F);
/*    */     } 
/* 34 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 35 */     if (this.duration < 0.0F) {
/* 36 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 42 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a * 0.8F));
/* 43 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 44 */     sb.setColor(this.color);
/* 45 */     sb.draw((TextureRegion)ImageMaster.BORDER_GLOW_2, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\HeartMegaDebuffEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */