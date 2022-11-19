/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.DoorShineParticleEffect;

import java.util.ArrayList;
import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ public class DoorUnlockScreen
/*     */ {
/*     */   public static boolean show = false;
/*     */   private static Texture doorLeft;
/*     */   private static Texture doorRight;
/*     */   private static Texture circleLeft;
/*     */   private static Texture circleRight;
/*  34 */   private Color bgColor = new Color(337060863);
/*     */ 
/*     */   
/*  37 */   private Color fadeColor = Color.BLACK.cpy(); private Color fadeOutColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); private float fadeTimer;
/*     */   private float lightUpTimer;
/*     */   private boolean fadeOut = false;
/*     */   private DoorLock lockGreen;
/*     */   private DoorLock lockBlue;
/*     */   private DoorLock lockRed;
/*  43 */   public ArrayList<AbstractGameEffect> effects = new ArrayList<>();
/*     */   private boolean animateCircle = false;
/*     */   private boolean rotatingCircle = true;
/*     */   private boolean eventVersion;
/*  47 */   private float circleAngle = -45.0F, doorOffset = 1.0F; private float circleTimer;
/*     */   private float circleTime;
/*  49 */   private float renderScale = (Settings.xScale > Settings.yScale) ? Settings.xScale : Settings.yScale; private float autoContinueTimer; private float doorTime;
/*     */   
/*     */   public void open(boolean eventVersion) {
/*  52 */     GameCursor.hidden = true;
/*  53 */     this.eventVersion = eventVersion;
/*     */ 
/*     */     
/*  56 */     if (doorLeft == null) {
/*  57 */       doorLeft = ImageMaster.loadImage("images/ui/door/door_left.png");
/*  58 */       doorRight = ImageMaster.loadImage("images/ui/door/door_right.png");
/*  59 */       circleLeft = ImageMaster.loadImage("images/ui/door/circle_left.png");
/*  60 */       circleRight = ImageMaster.loadImage("images/ui/door/circle_right.png");
/*  61 */     } else if (this.lockRed != null) {
/*  62 */       this.lockRed.reset();
/*  63 */       this.lockGreen.reset();
/*  64 */       this.lockBlue.reset();
/*     */     } 
/*     */     
/*  67 */     this
/*     */       
/*  69 */       .lockRed = new DoorLock(DoorLock.LockColor.RED, CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.IRONCLAD.name() + "_WIN", false), eventVersion);
/*     */ 
/*     */     
/*  72 */     this
/*     */       
/*  74 */       .lockGreen = new DoorLock(DoorLock.LockColor.GREEN, CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.THE_SILENT.name() + "_WIN", false), eventVersion);
/*     */ 
/*     */     
/*  77 */     this
/*     */       
/*  79 */       .lockBlue = new DoorLock(DoorLock.LockColor.BLUE, CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.DEFECT.name() + "_WIN", false), eventVersion);
/*     */ 
/*     */     
/*  82 */     if (Settings.FAST_MODE) {
/*  83 */       this.circleTimer = 1.0F;
/*  84 */       this.circleTime = 1.0F;
/*  85 */       this.lightUpTimer = 1.0F;
/*  86 */       this.autoContinueTimer = 0.01F;
/*  87 */       this.doorTime = 2.0F;
/*  88 */       this.fadeTimer = 1.0F;
/*     */     } else {
/*  90 */       this.circleTimer = 3.0F;
/*  91 */       this.circleTime = 3.0F;
/*  92 */       this.lightUpTimer = 3.0F;
/*  93 */       this.autoContinueTimer = 0.5F;
/*  94 */       this.doorTime = 5.0F;
/*  95 */       this.fadeTimer = 3.0F;
/*     */     } 
/*     */     
/*  98 */     this.circleAngle = -45.0F;
/*  99 */     this.doorOffset = 1.0F;
/* 100 */     this.rotatingCircle = true;
/* 101 */     this.fadeColor = Color.BLACK.cpy();
/* 102 */     CardCrawlGame.music.silenceBGM();
/* 103 */     this.fadeOut = false;
/* 104 */     GameCursor.hidden = true;
/*     */   }
/*     */   
/*     */   public void update() {
/* 108 */     updateFade();
/* 109 */     updateLightUp();
/* 110 */     updateCircle();
/* 111 */     this.lockRed.update();
/* 112 */     this.lockGreen.update();
/* 113 */     this.lockBlue.update();
/* 114 */     updateFadeInput();
/* 115 */     updateVfx();
/*     */   }
/*     */   
/*     */   private void updateFadeInput() {
/* 119 */     if (this.fadeOut) {
/* 120 */       this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 121 */       this.fadeOutColor.a = 1.0F - this.fadeTimer;
/* 122 */       if (this.fadeTimer < 0.0F)
/* 123 */         exit(); 
/*     */       return;
/*     */     } 
/* 126 */     if (!this.animateCircle && this.fadeTimer == 0.0F) {
/* 127 */       if (this.circleTimer == 0.0F) {
/* 128 */         if (this.autoContinueTimer > 0.0F) {
/* 129 */           this.autoContinueTimer -= Gdx.graphics.getDeltaTime();
/* 130 */           if (this.autoContinueTimer < 0.0F) {
/* 131 */             exit();
/*     */           }
/* 133 */         } else if (InputHelper.justClickedLeft || CInputActionSet.proceed.isJustPressed() || CInputActionSet.select
/* 134 */           .isJustPressed()) {
/* 135 */           exit();
/*     */         } 
/* 137 */       } else if (this.circleTimer == this.circleTime && (
/* 138 */         InputHelper.justClickedLeft || CInputActionSet.proceed.isJustPressed() || CInputActionSet.select
/* 139 */         .isJustPressed())) {
/* 140 */         this.fadeOut = true;
/* 141 */         this.fadeTimer = 1.0F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void exit() {
/* 148 */     this.lockRed.dispose();
/* 149 */     this.lockGreen.dispose();
/* 150 */     this.lockBlue.dispose();
/*     */     
/* 152 */     if (!this.eventVersion) {
/* 153 */       GameCursor.hidden = false;
/* 154 */       CardCrawlGame.mainMenuScreen.lighten();
/* 155 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 156 */       CardCrawlGame.music.changeBGM("MENU");
/*     */     } else {
/* 158 */       CardCrawlGame.mode = CardCrawlGame.GameMode.GAMEPLAY;
/* 159 */       CardCrawlGame.nextDungeon = "TheEnding";
/* 160 */       CardCrawlGame.music.fadeOutBGM();
/* 161 */       CardCrawlGame.music.fadeOutTempBGM();
/* 162 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 163 */       AbstractDungeon.fadeOut();
/* 164 */       AbstractDungeon.isDungeonBeaten = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateLightUp() {
/* 169 */     if (this.animateCircle && this.lightUpTimer != 0.0F) {
/* 170 */       this.lightUpTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/* 172 */       if (Settings.FAST_MODE) {
/* 173 */         if (this.lightUpTimer < 1.0F) {
/* 174 */           this.lockRed.flash(this.eventVersion);
/*     */         }
/* 176 */         if (this.lightUpTimer < 0.75F) {
/* 177 */           this.lockGreen.flash(this.eventVersion);
/*     */         }
/* 179 */         if (this.lightUpTimer < 0.5F) {
/* 180 */           this.lockBlue.flash(this.eventVersion);
/*     */         }
/*     */       } else {
/* 183 */         if (this.lightUpTimer < 3.0F) {
/* 184 */           this.lockRed.flash(this.eventVersion);
/*     */         }
/* 186 */         if (this.lightUpTimer < 2.5F) {
/* 187 */           this.lockGreen.flash(this.eventVersion);
/*     */         }
/* 189 */         if (this.lightUpTimer < 2.0F) {
/* 190 */           this.lockBlue.flash(this.eventVersion);
/*     */         }
/*     */       } 
/*     */       
/* 194 */       if (this.lightUpTimer < 0.0F) {
/* 195 */         this.lightUpTimer = 0.0F;
/* 196 */         unlock();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateVfx() {
/* 202 */     for (Iterator<AbstractGameEffect> i = this.effects.iterator(); i.hasNext(); ) {
/* 203 */       AbstractGameEffect e = i.next();
/* 204 */       e.update();
/* 205 */       if (e.isDone) {
/* 206 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateFade() {
/* 212 */     if (this.fadeTimer != 0.0F) {
/* 213 */       this.fadeTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/* 215 */       if (this.fadeTimer < 0.0F) {
/* 216 */         this.fadeTimer = 0.0F;
/* 217 */         this.animateCircle = this.eventVersion;
/*     */       } 
/*     */       
/* 220 */       this.fadeColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.fadeTimer / 3.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void unlock() {
/* 225 */     if (this.animateCircle) {
/* 226 */       CardCrawlGame.sound.playA("ATTACK_HEAVY", 0.4F);
/* 227 */       CardCrawlGame.sound.playA("POWER_SHACKLE", 0.1F);
/* 228 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, true);
/*     */       
/* 230 */       for (int i = 0; i < 50; i++) {
/* 231 */         this.effects.add(new DoorShineParticleEffect(
/*     */               
/* 233 */               MathUtils.random(Settings.WIDTH * 0.45F, Settings.WIDTH * 0.55F), 
/* 234 */               MathUtils.random(Settings.HEIGHT * 0.45F, Settings.HEIGHT * 0.55F)));
/*     */       }
/*     */       
/* 237 */       this.lockRed.unlock();
/* 238 */       this.lockGreen.unlock();
/* 239 */       this.lockBlue.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateCircle() {
/* 244 */     if (this.animateCircle && this.fadeTimer == 0.0F && this.lightUpTimer == 0.0F)
/*     */     {
/* 246 */       if (this.rotatingCircle) {
/* 247 */         this.circleTimer -= Gdx.graphics.getDeltaTime();
/* 248 */         this.circleAngle = Interpolation.fade.apply(0.0F, -45.0F, this.circleTimer / this.circleTime);
/*     */         
/* 250 */         if (this.circleTimer < 0.0F) {
/* 251 */           this.rotatingCircle = false;
/* 252 */           this.circleTimer = this.doorTime;
/* 253 */           this.circleAngle = 0.0F;
/* 254 */           CardCrawlGame.screenShake.mildRumble(this.doorTime - 0.25F);
/* 255 */           CardCrawlGame.sound.playA("RELIC_DROP_ROCKY", 0.3F);
/* 256 */           CardCrawlGame.sound.playA("RELIC_DROP_ROCKY", -0.6F);
/* 257 */           CardCrawlGame.sound.playA("EVENT_GOLDEN", -0.3F);
/* 258 */           CardCrawlGame.sound.playA("EVENT_WINDING", 0.5F);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 263 */         this.circleTimer -= Gdx.graphics.getDeltaTime();
/*     */         
/* 265 */         if (this.circleTimer < 0.0F) {
/* 266 */           this.circleTimer = 0.0F;
/* 267 */           this.animateCircle = false;
/*     */         } 
/*     */         
/* 270 */         this.bgColor.r = MathHelper.slowColorLerpSnap(this.bgColor.r, 0.0F);
/* 271 */         this.bgColor.g = MathHelper.slowColorLerpSnap(this.bgColor.g, 0.0F);
/* 272 */         this.bgColor.b = MathHelper.slowColorLerpSnap(this.bgColor.b, 0.0F);
/* 273 */         this.doorOffset = 1200.0F * Settings.scale * Interpolation.pow3.apply(1.0F, 0.0F, this.circleTimer / this.doorTime);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 279 */     sb.setColor(this.bgColor);
/* 280 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 281 */     renderMainDoor(sb);
/* 282 */     renderCircleMechanism(sb);
/* 283 */     this.lockRed.render(sb);
/* 284 */     this.lockGreen.render(sb);
/* 285 */     this.lockBlue.render(sb);
/* 286 */     renderFade(sb);
/*     */     
/* 288 */     if (this.fadeOut) {
/* 289 */       sb.setColor(this.fadeOutColor);
/* 290 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     } 
/*     */     
/* 293 */     for (AbstractGameEffect e : this.effects) {
/* 294 */       e.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderFade(SpriteBatch sb) {
/* 299 */     sb.setColor(this.fadeColor);
/* 300 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */   }
/*     */   
/*     */   private void renderMainDoor(SpriteBatch sb) {
/* 304 */     sb.setColor(Color.WHITE);
/* 305 */     float yOffset = 0.0F;
/*     */     
/* 307 */     if (this.eventVersion) {
/* 308 */       yOffset = -48.0F * Settings.scale;
/*     */     }
/*     */     
/* 311 */     sb.draw(doorLeft, Settings.WIDTH / 2.0F - 960.0F - this.doorOffset, Settings.HEIGHT / 2.0F - 600.0F + yOffset, 960.0F, 600.0F, 1920.0F, 1200.0F, this.renderScale, this.renderScale, 0.0F, 0, 0, 1920, 1200, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 329 */     sb.draw(doorRight, Settings.WIDTH / 2.0F - 960.0F + this.doorOffset, Settings.HEIGHT / 2.0F - 600.0F + yOffset, 960.0F, 600.0F, 1920.0F, 1200.0F, this.renderScale, this.renderScale, 0.0F, 0, 0, 1920, 1200, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderCircleMechanism(SpriteBatch sb) {
/* 349 */     float yOffset = 0.0F;
/* 350 */     if (this.eventVersion) {
/* 351 */       yOffset = -48.0F * Settings.scale;
/*     */     }
/*     */     
/* 354 */     sb.draw(circleRight, Settings.WIDTH / 2.0F - 960.0F + this.doorOffset, Settings.HEIGHT / 2.0F - 600.0F + yOffset, 960.0F, 600.0F, 1920.0F, 1200.0F, this.renderScale, this.renderScale, this.circleAngle, 2, 2, 1920, 1200, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     sb.draw(circleLeft, Settings.WIDTH / 2.0F - 960.0F - this.doorOffset, Settings.HEIGHT / 2.0F - 600.0F + yOffset, 960.0F, 600.0F, 1920.0F, 1200.0F, this.renderScale, this.renderScale, this.circleAngle, 2, 2, 1920, 1200, false, false);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DoorUnlockScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */