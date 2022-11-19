/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleXLEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DevotionEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 18 */   int count = 0;
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     if (this.count == 0) {
/* 23 */       AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY, true));
/*    */     }
/* 25 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 26 */     if (this.duration < 0.0F) {
/* 27 */       this.count++;
/* 28 */       this.duration = MathUtils.random(0.1F, 0.2F);
/* 29 */       float x = (Settings.WIDTH * this.count) / 7.0F;
/* 30 */       float y = MathUtils.random(AbstractDungeon.floorY - 80.0F * Settings.scale, AbstractDungeon.floorY + 50.0F * Settings.scale);
/*    */ 
/*    */       
/* 33 */       for (int i = 0; i < 5; i++) {
/* 34 */         AbstractDungeon.effectsQueue.add(new TorchParticleXLEffect(x, y, MathUtils.random(1.1F, 1.6F)));
/*    */       }
/*    */     } 
/*    */     
/* 38 */     if (this.count >= 6)
/* 39 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DevotionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */