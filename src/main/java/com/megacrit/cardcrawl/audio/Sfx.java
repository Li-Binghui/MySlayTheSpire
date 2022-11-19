/*    */ package com.megacrit.cardcrawl.audio;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.audio.Sound;
/*    */ import com.badlogic.gdx.files.FileHandle;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class Sfx
/*    */ {
/* 11 */   private static final Logger logger = LogManager.getLogger(Sfx.class.getName());
/*    */   private String url;
/*    */   private Sound sound;
/*    */   
/*    */   public Sfx(String url, boolean preload) {
/* 16 */     if (preload) {
/* 17 */       this.sound = initSound(Gdx.files.internal(url));
/*    */     } else {
/* 19 */       this.url = url;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Sfx(String url) {
/* 24 */     this(url, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public long play(float volume) {
/* 29 */     this.sound = initSound(Gdx.files.internal(this.url));
/* 30 */     if (this.sound != null) {
/* 31 */       return this.sound.play(volume);
/*    */     }
/* 33 */     return 0L;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long play(float volume, float y, float z) {
/* 39 */     this.sound = initSound(Gdx.files.internal(this.url));
/* 40 */     if (this.sound != null) {
/* 41 */       return this.sound.play(volume, y, z);
/*    */     }
/* 43 */     return 0L;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long loop(float volume) {
/* 49 */     this.sound = initSound(Gdx.files.internal(this.url));
/* 50 */     if (this.sound != null) {
/* 51 */       return this.sound.loop(volume);
/*    */     }
/* 53 */     return 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVolume(long id, float volume) {
/* 58 */     if (this.sound != null) {
/* 59 */       this.sound.setVolume(id, volume);
/*    */     }
/*    */   }
/*    */   
/*    */   public void stop() {
/* 64 */     logger.info("stopping");
/* 65 */     if (this.sound != null) {
/* 66 */       this.sound.stop();
/*    */     }
/*    */   }
/*    */   
/*    */   public void stop(long id) {
/* 71 */     if (this.sound != null) {
/* 72 */       this.sound.stop(id);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Sound initSound(FileHandle file) {
/* 84 */     if (this.sound == null) {
/* 85 */       if (file != null) {
/* 86 */         if (Gdx.audio != null) {
/* 87 */           return Gdx.audio.newSound(file);
/*    */         }
/* 89 */         logger.info("WARNING: Gdx.audio is null");
/* 90 */         return null;
/*    */       } 
/*    */       
/* 93 */       logger.info("File: " + this.url + " was not found.");
/* 94 */       return null;
/*    */     } 
/*    */     
/* 97 */     return this.sound;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\audio\Sfx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */