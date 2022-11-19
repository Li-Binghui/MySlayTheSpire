/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class HemokinesisEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public HemokinesisEffect(float sX, float sY, float tX, float tY) {
/* 14 */     this.x = sX;
/* 15 */     this.y = sY;
/* 16 */     this.tX = tX;
/* 17 */     this.tY = tY;
/*    */     
/* 19 */     this.scale = 0.12F;
/* 20 */     this.duration = 0.5F;
/*    */   }
/*    */   private float tX; private float tY;
/*    */   public void update() {
/* 24 */     this.scale -= Gdx.graphics.getDeltaTime();
/* 25 */     if (this.scale < 0.0F) {
/* 26 */       AbstractDungeon.effectsQueue.add(new HemokinesisParticle(this.x + 
/*    */             
/* 28 */             MathUtils.random(60.0F, -60.0F) * Settings.scale, this.y + 
/* 29 */             MathUtils.random(60.0F, -60.0F) * Settings.scale, this.tX, this.tY, AbstractDungeon.player.flipHorizontal));
/*    */ 
/*    */ 
/*    */       
/* 33 */       this.scale = 0.04F;
/*    */     } 
/*    */     
/* 36 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 37 */     if (this.duration < 0.0F)
/* 38 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\HemokinesisEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */