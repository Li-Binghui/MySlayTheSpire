/*    */ package com.megacrit.cardcrawl.audio;
/*    */ 
/*    */ import com.badlogic.gdx.audio.Music;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MockMusic
/*    */   implements Music
/*    */ {
/*    */   public void play() {}
/*    */   
/*    */   public void pause() {}
/*    */   
/*    */   public void stop() {}
/*    */   
/*    */   public boolean isPlaying() {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLooping(boolean isLooping) {}
/*    */ 
/*    */   
/*    */   public boolean isLooping() {
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVolume(float volume) {}
/*    */ 
/*    */   
/*    */   public float getVolume() {
/* 41 */     return 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPan(float pan, float volume) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPosition(float position) {}
/*    */ 
/*    */   
/*    */   public float getPosition() {
/* 54 */     return 0.0F;
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */   
/*    */   public void setOnCompletionListener(OnCompletionListener listener) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\audio\MockMusic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */