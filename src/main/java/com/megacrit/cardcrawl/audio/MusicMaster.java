/*     */ package com.megacrit.cardcrawl.audio;
/*     */ 
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class MusicMaster
/*     */ {
/*  12 */   private static final Logger logger = LogManager.getLogger(MusicMaster.class.getName());
/*  13 */   private ArrayList<MainMusic> mainTrack = new ArrayList<>();
/*  14 */   private ArrayList<TempMusic> tempTrack = new ArrayList<>();
/*     */   
/*     */   public MusicMaster() {
/*  17 */     Settings.MASTER_VOLUME = Settings.soundPref.getFloat("Master Volume", 0.5F);
/*     */ 
/*     */     
/*  20 */     Settings.MUSIC_VOLUME = Settings.soundPref.getFloat("Music Volume", 0.5F);
/*  21 */     logger.info("Music Volume: " + Settings.MUSIC_VOLUME);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  28 */     updateBGM();
/*  29 */     updateTempBGM();
/*     */   }
/*     */   
/*     */   public void updateVolume() {
/*  33 */     for (MainMusic m : this.mainTrack) {
/*  34 */       m.updateVolume();
/*     */     }
/*  36 */     for (TempMusic m : this.tempTrack) {
/*  37 */       m.updateVolume();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateBGM() {
/*  46 */     for (Iterator<MainMusic> i = this.mainTrack.iterator(); i.hasNext(); ) {
/*  47 */       MainMusic e = i.next();
/*  48 */       e.update();
/*  49 */       if (e.isDone) {
/*  50 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateTempBGM() {
/*  60 */     for (Iterator<TempMusic> i = this.tempTrack.iterator(); i.hasNext(); ) {
/*  61 */       TempMusic e = i.next();
/*  62 */       e.update();
/*  63 */       if (e.isDone) {
/*  64 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fadeOutTempBGM() {
/*  73 */     for (TempMusic m : this.tempTrack) {
/*  74 */       if (!m.isFadingOut) {
/*  75 */         m.fadeOut();
/*     */       }
/*     */     } 
/*  78 */     for (MainMusic m : this.mainTrack) {
/*  79 */       m.unsilence();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void justFadeOutTempBGM() {
/*  87 */     for (TempMusic m : this.tempTrack) {
/*  88 */       if (!m.isFadingOut) {
/*  89 */         m.fadeOut();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playTempBGM(String key) {
/* 100 */     if (key != null) {
/* 101 */       logger.info("Playing " + key);
/* 102 */       this.tempTrack.add(new TempMusic(key, false));
/*     */       
/* 104 */       for (MainMusic m : this.mainTrack) {
/* 105 */         m.silence();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playTempBgmInstantly(String key) {
/* 114 */     if (key != null) {
/* 115 */       logger.info("Playing " + key);
/* 116 */       this.tempTrack.add(new TempMusic(key, true));
/*     */       
/* 118 */       for (MainMusic m : this.mainTrack) {
/* 119 */         m.silenceInstantly();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void precacheTempBgm(String key) {
/* 130 */     if (key != null) {
/* 131 */       logger.info("Pre-caching " + key);
/* 132 */       this.tempTrack.add(new TempMusic(key, true, true, true));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playPrecachedTempBgm() {
/* 140 */     if (!this.tempTrack.isEmpty()) {
/* 141 */       ((TempMusic)this.tempTrack.get(0)).playPrecached();
/*     */       
/* 143 */       for (MainMusic m : this.mainTrack) {
/* 144 */         m.silenceInstantly();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playTempBgmInstantly(String key, boolean loop) {
/* 153 */     if (key != null) {
/* 154 */       logger.info("Playing " + key);
/* 155 */       this.tempTrack.add(new TempMusic(key, true, loop));
/*     */       
/* 157 */       for (MainMusic m : this.mainTrack) {
/* 158 */         m.silenceInstantly();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeBGM(String key) {
/* 170 */     this.mainTrack.add(new MainMusic(key));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fadeOutBGM() {
/* 178 */     for (MainMusic m : this.mainTrack) {
/* 179 */       if (!m.isFadingOut) {
/* 180 */         m.fadeOut();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void silenceBGM() {
/* 186 */     for (MainMusic m : this.mainTrack) {
/* 187 */       m.silence();
/*     */     }
/*     */   }
/*     */   
/*     */   public void silenceBGMInstantly() {
/* 192 */     for (MainMusic m : this.mainTrack) {
/* 193 */       m.silenceInstantly();
/*     */     }
/*     */   }
/*     */   
/*     */   public void unsilenceBGM() {
/* 198 */     for (MainMusic m : this.mainTrack) {
/* 199 */       m.unsilence();
/*     */     }
/*     */   }
/*     */   
/*     */   public void silenceTempBgmInstantly() {
/* 204 */     for (TempMusic m : this.tempTrack) {
/* 205 */       m.silenceInstantly();
/* 206 */       m.isFadingOut = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 215 */     for (MainMusic m : this.mainTrack) {
/* 216 */       m.kill();
/*     */     }
/*     */ 
/*     */     
/* 220 */     for (TempMusic m : this.tempTrack) {
/* 221 */       m.kill();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fadeAll() {
/* 230 */     for (MainMusic m : this.mainTrack) {
/* 231 */       m.fadeOut();
/*     */     }
/*     */ 
/*     */     
/* 235 */     for (TempMusic m : this.tempTrack)
/* 236 */       m.fadeOut(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\audio\MusicMaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */