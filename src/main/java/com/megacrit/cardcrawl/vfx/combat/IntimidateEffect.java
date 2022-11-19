/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class IntimidateEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 1.0F;
/*    */   private float x;
/*    */   
/*    */   public IntimidateEffect(float newX, float newY) {
/* 15 */     this.duration = 1.0F;
/* 16 */     this.x = newX;
/* 17 */     this.y = newY;
/*    */   }
/*    */   private float y; private float vfxTimer; private static final float VFX_INTERVAL = 0.016F;
/*    */   public void update() {
/* 21 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 22 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 23 */     if (this.vfxTimer < 0.0F) {
/* 24 */       this.vfxTimer = 0.016F;
/* 25 */       AbstractDungeon.effectsQueue.add(new WobblyLineEffect(this.x, this.y, Settings.CREAM_COLOR.cpy()));
/*    */     } 
/* 27 */     if (this.duration < 0.0F)
/* 28 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\IntimidateEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */