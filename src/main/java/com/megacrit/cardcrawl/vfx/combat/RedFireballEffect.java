/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/*    */
/*    */ public class RedFireballEffect extends AbstractGameEffect {
/*    */   private static final float FIREBALL_INTERVAL = 0.016F;
/*    */   private float x;
/*    */   private float y;
/* 11 */   private float vfxTimer = 0.0F; private float startX; private float startY; private float targetX; private float targetY;
/*    */   private int timesUpgraded;
/*    */   
/*    */   public RedFireballEffect(float startX, float startY, float targetX, float targetY, int timesUpgraded) {
/* 15 */     this.startingDuration = 0.3F;
/* 16 */     this.duration = 0.3F;
/* 17 */     this.startX = startX;
/* 18 */     this.startY = startY;
/* 19 */     this.targetX = targetX;
/* 20 */     this.targetY = targetY;
/* 21 */     this.timesUpgraded = timesUpgraded;
/* 22 */     this.x = startX;
/* 23 */     this.y = startY;
/*    */   }
/*    */   
/*    */   public void update() {
/* 27 */     this.x = Interpolation.fade.apply(this.targetX, this.startX, this.duration / this.startingDuration);
/*    */     
/* 29 */     if (this.duration > this.startingDuration / 2.0F) {
/* 30 */       this.y = Interpolation.pow4In.apply(this.startY, this.targetY, (this.duration - this.startingDuration / 2.0F) / this.startingDuration * 2.0F);
/*    */     } else {
/* 32 */       this.y = Interpolation.pow4Out.apply(this.targetY, this.startY, this.duration / this.startingDuration * 2.0F);
/*    */     } 
/*    */     
/* 35 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 36 */     if (this.vfxTimer < 0.0F) {
/* 37 */       this.vfxTimer += 0.016F;
/* 38 */       AbstractDungeon.effectsQueue.add(new RedFireBurstParticleEffect(this.x, this.y, this.timesUpgraded));
/* 39 */       AbstractDungeon.effectsQueue.add(new RedFireBurstParticleEffect(this.x, this.y, this.timesUpgraded));
/*    */     } 
/*    */     
/* 42 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 43 */     if (this.duration < 0.0F)
/* 44 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\RedFireballEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */