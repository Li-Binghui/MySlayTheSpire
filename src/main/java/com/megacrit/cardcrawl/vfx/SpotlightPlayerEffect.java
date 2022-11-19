/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpotlightPlayerEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public void update() {
/* 21 */     if (this.duration == 3.0F) {
/* 22 */       CardCrawlGame.sound.playA("INTIMIDATE", -0.6F);
/*    */     }
/*    */     
/* 25 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 27 */     if (this.duration > 1.5F) {
/* 28 */       this.color.a = Interpolation.pow5In.apply(0.5F, 0.0F, (this.duration - 1.5F) / 1.5F);
/*    */     } else {
/* 30 */       this.color.a = Interpolation.exp10In.apply(0.0F, 0.5F, this.duration / 1.5F);
/*    */     } 
/*    */     
/* 33 */     if (this.duration < 0.0F) {
/* 34 */       this.color.a = 0.0F;
/* 35 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 41 */     sb.setColor(this.color);
/* 42 */     sb.setBlendFunction(770, 1);
/* 43 */     sb.draw(ImageMaster.SPOTLIGHT_VFX, 0.0F, 0.0F, AbstractDungeon.player.drawX + AbstractDungeon.player.hb_w * 2.0F, Settings.HEIGHT);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\SpotlightPlayerEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */