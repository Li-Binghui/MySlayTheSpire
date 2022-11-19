/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class DaggerSprayEffect extends AbstractGameEffect {
/*    */   private boolean flipX;
/*    */   
/*    */   public DaggerSprayEffect(boolean shouldFlip) {
/* 13 */     this.flipX = shouldFlip;
/*    */   }
/*    */   
/*    */   public void update() {
/* 17 */     this.isDone = true;
/*    */     
/* 19 */     if (this.flipX) {
/* 20 */       for (int i = 12; i > 0; i--) {
/* 21 */         float x = AbstractDungeon.player.hb.cX - MathUtils.random(0.0F, 450.0F) * Settings.scale;
/*    */         
/* 23 */         AbstractDungeon.effectsQueue.add(new FlyingDaggerEffect(x, AbstractDungeon.player.hb.cY + 120.0F * Settings.scale + i * -18.0F * Settings.scale, (i * 4) - 30.0F, true));
/*    */       
/*    */       }
/*    */ 
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 31 */       for (int i = 0; i < 12; i++) {
/* 32 */         float x = AbstractDungeon.player.hb.cX + MathUtils.random(0.0F, 450.0F) * Settings.scale;
/*    */         
/* 34 */         AbstractDungeon.effectsQueue.add(new FlyingDaggerEffect(x, AbstractDungeon.player.hb.cY - 100.0F * Settings.scale + i * 18.0F * Settings.scale, (i * 4) - 20.0F, false));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DaggerSprayEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */