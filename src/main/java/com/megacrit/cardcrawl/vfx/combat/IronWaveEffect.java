/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class IronWaveEffect extends AbstractGameEffect {
/* 11 */   private float waveTimer = 0.0F;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public IronWaveEffect(float x, float y, float cX) {
/* 16 */     this.x = x + 120.0F * Settings.scale;
/* 17 */     this.y = y - 20.0F * Settings.scale;
/* 18 */     this.cX = cX;
/*    */   }
/*    */   private float cX; private static final float WAVE_INTERVAL = 0.03F;
/*    */   
/*    */   public void update() {
/* 23 */     this.waveTimer -= Gdx.graphics.getDeltaTime();
/* 24 */     if (this.waveTimer < 0.0F) {
/* 25 */       this.waveTimer = 0.03F;
/* 26 */       this.x += 160.0F * Settings.scale;
/* 27 */       this.y -= 15.0F * Settings.scale;
/* 28 */       AbstractDungeon.effectsQueue.add(new IronWaveParticle(this.x, this.y));
/* 29 */       if (this.x > this.cX) {
/* 30 */         this.isDone = true;
/* 31 */         CardCrawlGame.sound.playA("ATTACK_DAGGER_6", -0.3F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\IronWaveEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */