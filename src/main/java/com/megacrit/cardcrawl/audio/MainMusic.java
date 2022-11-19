/*     */ package com.megacrit.cardcrawl.audio;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.audio.Music;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainMusic
/*     */ {
/*  18 */   private static final Logger logger = LogManager.getLogger(MainMusic.class.getName());
/*     */   
/*     */   private Music music;
/*     */   
/*     */   public String key;
/*     */   private static final String DIR = "audio/music/";
/*     */   private static final String TITLE_BGM = "STS_MenuTheme_NewMix_v1.ogg";
/*     */   private static final String LEVEL_1_1_BGM = "STS_Level1_NewMix_v1.ogg";
/*     */   private static final String LEVEL_1_2_BGM = "STS_Level1-2_v2.ogg";
/*     */   private static final String LEVEL_2_1_BGM = "STS_Level2_NewMix_v1.ogg";
/*     */   private static final String LEVEL_2_2_BGM = "STS_Level2-2_v2.ogg";
/*     */   private static final String LEVEL_3_1_BGM = "STS_Level3_v2.ogg";
/*     */   private static final String LEVEL_3_2_BGM = "STS_Level3-2_v2.ogg";
/*     */   private static final String LEVEL_4_1_BGM = "STS_Act4_BGM_v2.ogg";
/*     */   public boolean isSilenced = false;
/*  33 */   private float silenceTimer = 0.0F; private float silenceTime = 0.0F;
/*     */   
/*     */   private static final float SILENCE_TIME = 4.0F;
/*     */   private static final float FAST_SILENCE_TIME = 0.25F;
/*     */   private float silenceStartVolume;
/*     */   private static final float FADE_IN_TIME = 4.0F;
/*     */   private static final float FADE_OUT_TIME = 4.0F;
/*  40 */   private float fadeTimer = 0.0F;
/*     */ 
/*     */   
/*     */   public boolean isFadingOut = false;
/*     */   
/*     */   private float fadeOutStartVolume;
/*     */   
/*     */   public boolean isDone = false;
/*     */ 
/*     */   
/*     */   public MainMusic(String key) {
/*  51 */     this.key = key;
/*  52 */     this.music = getSong(key);
/*  53 */     this.fadeTimer = 4.0F;
/*  54 */     this.music.setLooping(true);
/*  55 */     this.music.play();
/*  56 */     this.music.setVolume(0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Music getSong(String key) {
/*  66 */     switch (key) {
/*     */       case "Exordium":
/*  68 */         switch (AbstractDungeon.miscRng.random(1)) {
/*     */           case 0:
/*  70 */             return newMusic("audio/music/STS_Level1_NewMix_v1.ogg");
/*     */         } 
/*  72 */         return newMusic("audio/music/STS_Level1-2_v2.ogg");
/*     */       
/*     */       case "TheCity":
/*  75 */         switch (AbstractDungeon.miscRng.random(1)) {
/*     */           case 0:
/*  77 */             return newMusic("audio/music/STS_Level2_NewMix_v1.ogg");
/*     */         } 
/*  79 */         return newMusic("audio/music/STS_Level2-2_v2.ogg");
/*     */       
/*     */       case "TheBeyond":
/*  82 */         switch (AbstractDungeon.miscRng.random(1)) {
/*     */           case 0:
/*  84 */             return newMusic("audio/music/STS_Level3_v2.ogg");
/*     */         } 
/*  86 */         return newMusic("audio/music/STS_Level3-2_v2.ogg");
/*     */       
/*     */       case "TheEnding":
/*  89 */         return newMusic("audio/music/STS_Act4_BGM_v2.ogg");
/*     */       case "MENU":
/*  91 */         return newMusic("audio/music/STS_MenuTheme_NewMix_v1.ogg");
/*     */     } 
/*  93 */     logger.info("NO SUCH MAIN BGM (playing level_1 instead): " + key);
/*  94 */     return newMusic("audio/music/STS_Level1_NewMix_v1.ogg");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Music newMusic(String path) {
/* 105 */     if (Gdx.audio == null) {
/* 106 */       logger.info("WARNING: Gdx.audio is null so no Music instance can be initialized.");
/* 107 */       return new MockMusic();
/*     */     } 
/* 109 */     return Gdx.audio.newMusic(Gdx.files.internal(path));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateVolume() {
/* 114 */     if (!this.isFadingOut && !this.isSilenced) {
/* 115 */       this.music.setVolume(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fadeOut() {
/* 123 */     this.isFadingOut = true;
/* 124 */     this.fadeOutStartVolume = this.music.getVolume();
/* 125 */     this.fadeTimer = 4.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void silence() {
/* 132 */     this.isSilenced = true;
/* 133 */     this.silenceTimer = 4.0F;
/* 134 */     this.silenceTime = 4.0F;
/* 135 */     this.silenceStartVolume = this.music.getVolume();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void silenceInstantly() {
/* 142 */     this.isSilenced = true;
/* 143 */     this.silenceTimer = 0.25F;
/* 144 */     this.silenceTime = 0.25F;
/* 145 */     this.silenceStartVolume = this.music.getVolume();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsilence() {
/* 152 */     if (this.isSilenced) {
/* 153 */       logger.info("Unsilencing " + this.key);
/* 154 */       this.isSilenced = false;
/* 155 */       this.fadeTimer = 4.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void kill() {
/* 163 */     this.music.dispose();
/* 164 */     this.isDone = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 171 */     if (!this.isFadingOut) {
/* 172 */       updateFadeIn();
/*     */     } else {
/* 174 */       updateFadeOut();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateFadeIn() {
/* 182 */     if (!this.isSilenced) {
/* 183 */       this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 184 */       if (this.fadeTimer < 0.0F) {
/* 185 */         this.fadeTimer = 0.0F;
/*     */       }
/*     */       
/* 188 */       if (!Settings.isBackgrounded) {
/* 189 */         this.music.setVolume(Interpolation.fade
/* 190 */             .apply(0.0F, 1.0F, 1.0F - this.fadeTimer / 4.0F) * Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
/*     */       } else {
/*     */         
/* 193 */         this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
/*     */       } 
/*     */     } else {
/* 196 */       this.silenceTimer -= Gdx.graphics.getDeltaTime();
/* 197 */       if (this.silenceTimer < 0.0F) {
/* 198 */         this.silenceTimer = 0.0F;
/*     */       }
/* 200 */       if (!Settings.isBackgrounded) {
/* 201 */         this.music.setVolume(Interpolation.fade.apply(this.silenceStartVolume, 0.0F, 1.0F - this.silenceTimer / this.silenceTime));
/*     */       } else {
/* 203 */         this.music.setVolume(MathHelper.slowColorLerpSnap(this.music.getVolume(), 0.0F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateFadeOut() {
/* 212 */     if (!this.isSilenced) {
/* 213 */       this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 214 */       if (this.fadeTimer < 0.0F) {
/* 215 */         this.fadeTimer = 0.0F;
/* 216 */         this.isDone = true;
/* 217 */         logger.info("Disposing MainMusic: " + this.key);
/* 218 */         this.music.dispose();
/*     */       } else {
/* 220 */         this.music.setVolume(Interpolation.fade.apply(this.fadeOutStartVolume, 0.0F, 1.0F - this.fadeTimer / 4.0F));
/*     */       } 
/*     */     } else {
/* 223 */       this.silenceTimer -= Gdx.graphics.getDeltaTime();
/* 224 */       if (this.silenceTimer < 0.0F) {
/* 225 */         this.silenceTimer = 0.0F;
/*     */       }
/* 227 */       this.music.setVolume(Interpolation.fade.apply(this.silenceStartVolume, 0.0F, 1.0F - this.silenceTimer / this.silenceTime));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\audio\MainMusic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */