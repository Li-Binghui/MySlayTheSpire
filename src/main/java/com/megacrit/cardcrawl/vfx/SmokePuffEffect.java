/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
/*    */ 
/*    */ public class SmokePuffEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float DEFAULT_DURATION = 0.8F;
/* 10 */   private ArrayList<FastSmoke> smoke = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public SmokePuffEffect(float x, float y) {
/* 14 */     this.duration = 0.8F;
/* 15 */     for (int i = 0; i < 30; i++) {
/* 16 */       this.smoke.add(new FastSmoke(x, y));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 23 */     if (this.duration < 0.0F) {
/* 24 */       this.isDone = true;
/* 25 */     } else if (this.duration < 0.7F) {
/* 26 */       killSmoke();
/*    */     } 
/*    */     
/* 29 */     for (FastSmoke b : this.smoke) {
/* 30 */       b.update();
/*    */     }
/*    */   }
/*    */   
/*    */   private void killSmoke() {
/* 35 */     for (FastSmoke s : this.smoke) {
/* 36 */       s.kill();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 42 */     for (FastSmoke b : this.smoke)
/* 43 */       b.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\SmokePuffEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */