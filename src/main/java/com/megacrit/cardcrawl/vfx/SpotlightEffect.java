/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpotlightEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public void update() {
/* 20 */     if (this.duration == 3.0F) {
/* 21 */       CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6F);
/*    */     }
/*    */     
/* 24 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 26 */     if (this.duration > 1.5F) {
/* 27 */       this.color.a = Interpolation.pow5In.apply(0.5F, 0.0F, (this.duration - 1.5F) / 1.5F);
/*    */     } else {
/* 29 */       this.color.a = Interpolation.exp10In.apply(0.0F, 0.5F, this.duration / 1.5F);
/*    */     } 
/*    */     
/* 32 */     if (this.duration < 0.0F) {
/* 33 */       this.color.a = 0.0F;
/* 34 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 40 */     sb.setColor(this.color);
/* 41 */     sb.setBlendFunction(770, 1);
/* 42 */     sb.draw(ImageMaster.SPOTLIGHT_VFX, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 43 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\SpotlightEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */