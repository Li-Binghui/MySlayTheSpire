/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
/*    */ 
/*    */ public class DarkSmokePuffEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float DEFAULT_DURATION = 0.8F;
/* 10 */   private ArrayList<FastDarkSmoke> smoke = new ArrayList<>();
/*    */   
/*    */   public DarkSmokePuffEffect(float x, float y) {
/* 13 */     this.duration = 0.8F;
/* 14 */     for (int i = 0; i < 20; i++) {
/* 15 */       this.smoke.add(new FastDarkSmoke(x, y));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 22 */     if (this.duration < 0.0F) {
/* 23 */       this.isDone = true;
/* 24 */     } else if (this.duration < 0.7F) {
/* 25 */       killSmoke();
/*    */     } 
/*    */     
/* 28 */     for (FastDarkSmoke b : this.smoke) {
/* 29 */       b.update();
/*    */     }
/*    */   }
/*    */   
/*    */   private void killSmoke() {
/* 34 */     for (FastDarkSmoke s : this.smoke) {
/* 35 */       s.kill();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 41 */     for (FastDarkSmoke b : this.smoke)
/* 42 */       b.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\DarkSmokePuffEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */