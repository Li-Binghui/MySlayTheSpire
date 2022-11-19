/*     */ package com.megacrit.cardcrawl.audio;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class TempMusic
/*     */ {
/*  13 */   private static final Logger logger = LogManager.getLogger(TempMusic.class.getName());
/*     */   
/*     */   private Music music;
/*     */   
/*     */   public String key;
/*     */   private static final String DIR = "audio/music/";
/*     */   private static final String SHOP_BGM = "STS_Merchant_NewMix_v1.ogg";
/*     */   private static final String SHRINE_BGM = "STS_Shrine_NewMix_v1.ogg";
/*     */   private static final String MINDBLOOM_BGM = "STS_Boss1MindBloom_v1.ogg";
/*     */   private static final String LEVEL_1_BOSS_BGM = "STS_Boss1_NewMix_v1.ogg";
/*     */   private static final String LEVEL_2_BOSS_BGM = "STS_Boss2_NewMix_v1.ogg";
/*     */   private static final String LEVEL_3_BOSS_BGM = "STS_Boss3_NewMix_v1.ogg";
/*     */   private static final String LEVEL_4_BOSS_BGM = "STS_Boss4_v6.ogg";
/*     */   private static final String ELITE_BGM = "STS_EliteBoss_NewMix_v1.ogg";
/*     */   private static final String CREDITS = "STS_Credits_v5.ogg";
/*     */   public boolean isSilenced = false;
/*  29 */   private float silenceTimer = 0.0F; private float silenceTime = 0.0F;
/*     */   private static final float FAST_SILENCE_TIME = 0.25F;
/*     */   private float silenceStartVolume;
/*     */   private static final float FADE_IN_TIME = 4.0F;
/*     */   private static final float FAST_FADE_IN_TIME = 0.25F;
/*     */   private static final float FADE_OUT_TIME = 4.0F;
/*     */   private float fadeTimer;
/*     */   private float fadeTime;
/*     */   public boolean isFadingOut = false;
/*     */   private float fadeOutStartVolume;
/*     */   public boolean isDone = false;
/*     */   
/*     */   public TempMusic(String key, boolean isFast) {
/*  42 */     this(key, isFast, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TempMusic(String key, boolean isFast, boolean loop) {
/*  51 */     this.key = key;
/*  52 */     this.music = getSong(key);
/*  53 */     if (isFast) {
/*  54 */       this.fadeTimer = 0.25F;
/*  55 */       this.fadeTime = 0.25F;
/*     */     } else {
/*  57 */       this.fadeTimer = 4.0F;
/*  58 */       this.fadeTime = 4.0F;
/*     */     } 
/*  60 */     this.music.setLooping(loop);
/*  61 */     this.music.play();
/*  62 */     this.music.setVolume(0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TempMusic(String key, boolean isFast, boolean loop, boolean precache) {
/*  71 */     this.key = key;
/*  72 */     this.music = getSong(key);
/*  73 */     if (isFast) {
/*  74 */       this.fadeTimer = 0.25F;
/*  75 */       this.fadeTime = 0.25F;
/*     */     } else {
/*  77 */       this.fadeTimer = 4.0F;
/*  78 */       this.fadeTime = 4.0F;
/*     */     } 
/*  80 */     this.music.setLooping(loop);
/*  81 */     this.music.setVolume(0.0F);
/*     */   }
/*     */   
/*     */   public void playPrecached() {
/*  85 */     if (!this.music.isPlaying()) {
/*  86 */       this.music.play();
/*     */     } else {
/*  88 */       logger.info("[WARNING] Attempted to play music that is already playing.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Music getSong(String key) {
/*  99 */     switch (key) {
/*     */       case "SHOP":
/* 101 */         return MainMusic.newMusic("audio/music/STS_Merchant_NewMix_v1.ogg");
/*     */       case "SHRINE":
/* 103 */         return MainMusic.newMusic("audio/music/STS_Shrine_NewMix_v1.ogg");
/*     */       case "MINDBLOOM":
/* 105 */         return MainMusic.newMusic("audio/music/STS_Boss1MindBloom_v1.ogg");
/*     */       case "BOSS_BOTTOM":
/* 107 */         return MainMusic.newMusic("audio/music/STS_Boss1_NewMix_v1.ogg");
/*     */       case "BOSS_CITY":
/* 109 */         return MainMusic.newMusic("audio/music/STS_Boss2_NewMix_v1.ogg");
/*     */       case "BOSS_BEYOND":
/* 111 */         return MainMusic.newMusic("audio/music/STS_Boss3_NewMix_v1.ogg");
/*     */       case "BOSS_ENDING":
/* 113 */         return MainMusic.newMusic("audio/music/STS_Boss4_v6.ogg");
/*     */       case "ELITE":
/* 115 */         return MainMusic.newMusic("audio/music/STS_EliteBoss_NewMix_v1.ogg");
/*     */       case "CREDITS":
/* 117 */         return MainMusic.newMusic("audio/music/STS_Credits_v5.ogg");
/*     */     } 
/* 119 */     return MainMusic.newMusic("audio/music/" + key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fadeOut() {
/* 127 */     this.isFadingOut = true;
/* 128 */     this.fadeOutStartVolume = this.music.getVolume();
/* 129 */     this.fadeTimer = 4.0F;
/*     */   }
/*     */   
/*     */   public void silenceInstantly() {
/* 133 */     this.isSilenced = true;
/* 134 */     this.silenceTimer = 0.25F;
/* 135 */     this.silenceTime = 0.25F;
/* 136 */     this.silenceStartVolume = this.music.getVolume();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void kill() {
/* 143 */     logger.info("Disposing TempMusic: " + this.key);
/* 144 */     this.music.dispose();
/* 145 */     this.isDone = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 152 */     if (this.music.isPlaying()) {
/* 153 */       if (!this.isFadingOut) {
/* 154 */         updateFadeIn();
/*     */       } else {
/* 156 */         updateFadeOut();
/*     */       } 
/* 158 */     } else if (this.isFadingOut) {
/* 159 */       kill();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateFadeIn() {
/* 167 */     if (!this.isSilenced) {
/* 168 */       this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 169 */       if (this.fadeTimer < 0.0F) {
/* 170 */         this.fadeTimer = 0.0F;
/* 171 */         if (!Settings.isBackgrounded) {
/* 172 */           this.music.setVolume(Interpolation.fade
/* 173 */               .apply(0.0F, 1.0F, 1.0F - this.fadeTimer / this.fadeTime) * Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
/*     */         } else {
/*     */           
/* 176 */           this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
/*     */         }
/*     */       
/* 179 */       } else if (!Settings.isBackgrounded) {
/* 180 */         this.music.setVolume(Interpolation.fade
/* 181 */             .apply(0.0F, 1.0F, 1.0F - this.fadeTimer / this.fadeTime) * Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
/*     */       } else {
/*     */         
/* 184 */         this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
/*     */       } 
/*     */     } else {
/*     */       
/* 188 */       this.silenceTimer -= Gdx.graphics.getDeltaTime();
/* 189 */       if (this.silenceTimer < 0.0F) {
/* 190 */         this.silenceTimer = 0.0F;
/*     */       }
/* 192 */       if (!Settings.isBackgrounded) {
/* 193 */         this.music.setVolume(Interpolation.fade.apply(this.silenceStartVolume, 0.0F, 1.0F - this.silenceTimer / this.silenceTime));
/*     */       } else {
/* 195 */         this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateFadeOut() {
/* 204 */     this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 205 */     if (this.fadeTimer < 0.0F) {
/* 206 */       this.fadeTimer = 0.0F;
/* 207 */       this.isDone = true;
/* 208 */       logger.info("Disposing TempMusic: " + this.key);
/* 209 */       this.music.dispose();
/*     */     } else {
/* 211 */       this.music.setVolume(Interpolation.fade.apply(this.fadeOutStartVolume, 0.0F, 1.0F - this.fadeTimer / 4.0F));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateVolume() {
/* 216 */     if (!this.isFadingOut && !this.isSilenced)
/* 217 */       this.music.setVolume(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\audio\TempMusic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */