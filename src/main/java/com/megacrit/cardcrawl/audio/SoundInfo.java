/*    */ package com.megacrit.cardcrawl.audio;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ 
/*    */ public class SoundInfo {
/*    */   public String name;
/*    */   public long id;
/*    */   public boolean isDone = false;
/*    */   private static final float FADE_OUT_DURATION = 5.0F;
/* 11 */   private float fadeDuration = 5.0F;
/* 12 */   public float volumeMultiplier = 1.0F;
/*    */   
/*    */   public SoundInfo(String name, long id) {
/* 15 */     this.name = name;
/* 16 */     this.id = id;
/*    */   }
/*    */   
/*    */   public void update() {
/* 20 */     if (this.fadeDuration != 0.0F) {
/* 21 */       this.fadeDuration -= Gdx.graphics.getDeltaTime();
/* 22 */       this.volumeMultiplier = Interpolation.fade.apply(1.0F, 0.0F, 1.0F - this.fadeDuration / 5.0F);
/* 23 */       if (this.fadeDuration < 0.0F) {
/* 24 */         this.isDone = true;
/* 25 */         this.fadeDuration = 0.0F;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\audio\SoundInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */