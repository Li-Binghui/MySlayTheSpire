/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
/*    */ 
/*    */ public class WhirlwindEffect extends AbstractGameEffect {
/* 11 */   private int count = 0;
/* 12 */   private float timer = 0.0F;
/*    */   private boolean reverse = false;
/*    */   
/*    */   public WhirlwindEffect(Color setColor, boolean reverse) {
/* 16 */     this.color = setColor.cpy();
/* 17 */     this.reverse = reverse;
/*    */   }
/*    */   
/*    */   public WhirlwindEffect() {
/* 21 */     this(new Color(0.9F, 0.9F, 1.0F, 1.0F), false);
/*    */   }
/*    */   
/*    */   public void update() {
/* 25 */     this.timer -= Gdx.graphics.getDeltaTime();
/* 26 */     if (this.timer < 0.0F) {
/* 27 */       this.timer += 0.05F;
/*    */       
/* 29 */       if (this.count == 0) {
/* 30 */         AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(this.color.cpy()));
/*    */       }
/*    */       
/* 33 */       AbstractDungeon.effectsQueue.add(new WindyParticleEffect(this.color, this.reverse));
/* 34 */       this.count++;
/*    */       
/* 36 */       if (this.count == 18)
/* 37 */         this.isDone = true; 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\WhirlwindEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */