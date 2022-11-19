/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
/*    */ 
/*    */ public class BlizzardEffect
/*    */   extends AbstractGameEffect {
/*    */   private int frostCount;
/*    */   private boolean flipped = false;
/*    */   
/*    */   public BlizzardEffect(int frostCount, boolean flipped) {
/* 17 */     this.frostCount = 5 + frostCount;
/* 18 */     this.flipped = flipped;
/*    */ 
/*    */     
/* 21 */     if (this.frostCount > 50) {
/* 22 */       this.frostCount = 50;
/*    */     }
/*    */   }
/*    */   
/*    */   public void update() {
/* 27 */     CardCrawlGame.sound.playA("ORB_FROST_CHANNEL", -0.25F - this.frostCount / 200.0F);
/* 28 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
/* 29 */     AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
/*    */     
/* 31 */     for (int i = 0; i < this.frostCount; i++) {
/* 32 */       AbstractDungeon.effectsQueue.add(new FallingIceEffect(this.frostCount, this.flipped));
/*    */     }
/* 34 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BlizzardEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */