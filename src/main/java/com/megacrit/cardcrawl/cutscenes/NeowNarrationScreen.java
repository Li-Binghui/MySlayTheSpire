/*     */ package com.megacrit.cardcrawl.cutscenes;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.ui.SpeechWord;

import java.util.ArrayList;
import java.util.Scanner;
/*     */ 
/*     */ 
/*     */ public class NeowNarrationScreen
/*     */ {
/*     */   private Color bgColor;
/*     */   private Color eyeColor;
/*     */   private NeowEye eye1;
/*     */   private NeowEye eye2;
/*     */   private NeowEye eye3;
/*  33 */   private int currentDialog = 0; private int clickCount = 0;
/*  34 */   private static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("PostCreditsNeow");
/*     */   
/*  36 */   private static float curLineWidth = 0.0F;
/*  37 */   private static int curLine = 0;
/*     */   private static Scanner s;
/*  39 */   private static GlyphLayout gl = new GlyphLayout();
/*  40 */   private static ArrayList<SpeechWord> words = new ArrayList<>();
/*  41 */   private static final float CHAR_SPACING = 8.0F * Settings.scale;
/*  42 */   private static final float LINE_SPACING = 38.0F * Settings.scale;
/*  43 */   private BitmapFont font = FontHelper.panelNameFont;
/*  44 */   private float x = Settings.WIDTH / 2.0F, y = Settings.HEIGHT / 2.0F;
/*  45 */   private float wordTimer = 1.0F;
/*     */   
/*     */   private boolean textDone = false;
/*     */   
/*     */   private boolean fadingOut = false;
/*  50 */   private float fadeOutTimer = 3.0F;
/*     */ 
/*     */   
/*     */   public void open() {
/*  54 */     this.fadingOut = false;
/*  55 */     this.fadeOutTimer = 3.0F;
/*     */ 
/*     */     
/*  58 */     playSfx();
/*  59 */     s = new Scanner(charStrings.TEXT[0]);
/*  60 */     this.textDone = false;
/*  61 */     this.wordTimer = 1.0F;
/*  62 */     words.clear();
/*  63 */     curLineWidth = 0.0F;
/*  64 */     curLine = 0;
/*  65 */     this.currentDialog = 0;
/*  66 */     this.clickCount = 0;
/*     */ 
/*     */     
/*  69 */     this.eye1 = new NeowEye(0);
/*  70 */     this.eye2 = new NeowEye(1);
/*  71 */     this.eye3 = new NeowEye(2);
/*  72 */     this.bgColor = new Color(320149504);
/*  73 */     this.eyeColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  78 */     this.bgColor.a = MathHelper.slowColorLerpSnap(this.bgColor.a, 1.0F);
/*  79 */     this.eyeColor.a = this.bgColor.a;
/*  80 */     this.eye1.update();
/*  81 */     this.eye2.update();
/*  82 */     this.eye3.update();
/*     */ 
/*     */     
/*  85 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/*  86 */     if (this.wordTimer < 0.0F && !this.textDone) {
/*  87 */       if (this.clickCount > 4) {
/*  88 */         this.wordTimer = 0.1F;
/*     */       } else {
/*  90 */         this.wordTimer += 0.4F;
/*     */       } 
/*  92 */       if (Settings.lineBreakViaCharacter) {
/*  93 */         addWordCN();
/*     */       } else {
/*  95 */         addWord();
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     for (SpeechWord w : words) {
/* 100 */       w.update();
/*     */     }
/*     */     
/* 103 */     if (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) {
/* 104 */       this.clickCount++;
/*     */     }
/*     */     
/* 107 */     if (this.fadingOut) {
/* 108 */       if (this.clickCount > 4) {
/* 109 */         this.fadeOutTimer -= Gdx.graphics.getDeltaTime() * 3.0F;
/*     */       } else {
/* 111 */         this.fadeOutTimer -= Gdx.graphics.getDeltaTime();
/*     */       } 
/* 113 */       if (this.fadeOutTimer < 0.0F) {
/* 114 */         this.fadeOutTimer = 0.0F;
/* 115 */         exit();
/*     */         return;
/*     */       } 
/* 118 */     } else if ((InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed() || this.clickCount > 3) && this.textDone) {
/*     */       
/* 120 */       this.currentDialog++;
/* 121 */       if (this.currentDialog > 2) {
/* 122 */         this.fadingOut = true;
/*     */         
/*     */         return;
/*     */       } 
/* 126 */       playSfx();
/* 127 */       s = new Scanner(charStrings.TEXT[this.currentDialog]);
/* 128 */       this.textDone = false;
/* 129 */       if (this.clickCount > 4) {
/* 130 */         this.wordTimer = 0.1F;
/*     */       } else {
/* 132 */         this.wordTimer = 0.3F;
/*     */       } 
/* 134 */       words.clear();
/* 135 */       curLineWidth = 0.0F;
/* 136 */       curLine = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 141 */     int roll = MathUtils.random(3);
/* 142 */     if (roll == 0) {
/* 143 */       CardCrawlGame.sound.playA("VO_NEOW_1A", -0.2F);
/* 144 */       CardCrawlGame.sound.playA("VO_NEOW_1A", -0.4F);
/* 145 */     } else if (roll == 1) {
/* 146 */       CardCrawlGame.sound.playA("VO_NEOW_1B", -0.2F);
/* 147 */       CardCrawlGame.sound.playA("VO_NEOW_1B", -0.4F);
/* 148 */     } else if (roll == 2) {
/* 149 */       CardCrawlGame.sound.playA("VO_NEOW_2A", -0.2F);
/* 150 */       CardCrawlGame.sound.playA("VO_NEOW_2A", -0.4F);
/*     */     } else {
/* 152 */       CardCrawlGame.sound.playA("VO_NEOW_2B", -0.2F);
/* 153 */       CardCrawlGame.sound.playA("VO_NEOW_2B", -0.4F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addWord() {
/* 158 */     if (s.hasNext()) {
/* 159 */       String word = s.next();
/*     */       
/* 161 */       if (word.equals("NL")) {
/* 162 */         curLine++;
/* 163 */         for (SpeechWord w : words) {
/* 164 */           w.shiftY(LINE_SPACING);
/*     */         }
/* 166 */         curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/* 170 */       gl.setText(this.font, word);
/* 171 */       float temp = 0.0F;
/*     */ 
/*     */       
/* 174 */       if (curLineWidth + gl.width > 9999.0F) {
/* 175 */         curLine++;
/* 176 */         for (SpeechWord w : words) {
/* 177 */           w.shiftY(LINE_SPACING);
/*     */         }
/* 179 */         curLineWidth = gl.width + CHAR_SPACING;
/* 180 */         temp = -curLineWidth / 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 184 */         curLineWidth += gl.width;
/* 185 */         temp = -curLineWidth / 2.0F;
/*     */         
/* 187 */         for (SpeechWord w : words) {
/* 188 */           if (w.line == curLine) {
/* 189 */             w.setX(this.x + temp);
/* 190 */             gl.setText(this.font, w.word);
/* 191 */             temp += gl.width + CHAR_SPACING;
/*     */           } 
/*     */         } 
/*     */         
/* 195 */         curLineWidth += CHAR_SPACING;
/* 196 */         gl.setText(this.font, word + " ");
/*     */       } 
/*     */       
/* 199 */       words.add(new SpeechWord(this.font, word, DialogWord.AppearEffect.FADE_IN, DialogWord.WordEffect.SLOW_WAVY, DialogWord.WordColor.WHITE, this.x + temp, this.y - LINE_SPACING * curLine, curLine));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 211 */       this.textDone = true;
/* 212 */       s.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addWordCN() {
/* 217 */     if (s.hasNext()) {
/* 218 */       String word = s.next();
/*     */       
/* 220 */       if (word.equals("NL")) {
/* 221 */         curLine++;
/* 222 */         for (SpeechWord w : words) {
/* 223 */           w.shiftY(LINE_SPACING);
/*     */         }
/* 225 */         curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 230 */       for (int i = 0; i < word.length(); i++) {
/* 231 */         String tmp = Character.toString(word.charAt(i));
/*     */         
/* 233 */         gl.setText(this.font, tmp);
/* 234 */         float temp = 0.0F;
/*     */ 
/*     */         
/* 237 */         if (curLineWidth + gl.width > 9999.0F) {
/* 238 */           curLine++;
/* 239 */           for (SpeechWord w : words) {
/* 240 */             w.shiftY(LINE_SPACING);
/*     */           }
/* 242 */           curLineWidth = gl.width;
/* 243 */           temp = -curLineWidth / 2.0F;
/*     */         }
/*     */         else {
/*     */           
/* 247 */           curLineWidth += gl.width;
/* 248 */           temp = -curLineWidth / 2.0F;
/*     */           
/* 250 */           for (SpeechWord w : words) {
/* 251 */             if (w.line == curLine) {
/* 252 */               w.setX(this.x + temp);
/* 253 */               gl.setText(this.font, w.word);
/* 254 */               temp += gl.width;
/*     */             } 
/*     */           } 
/* 257 */           gl.setText(this.font, tmp + " ");
/*     */         } 
/*     */         
/* 260 */         words.add(new SpeechWord(this.font, tmp, DialogWord.AppearEffect.FADE_IN, DialogWord.WordEffect.SLOW_WAVY, DialogWord.WordColor.WHITE, this.x + temp, this.y - LINE_SPACING * curLine, curLine));
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 272 */       this.textDone = true;
/* 273 */       s.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void exit() {
/* 278 */     GameCursor.hidden = false;
/* 279 */     CardCrawlGame.mainMenuScreen.lighten();
/* 280 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 281 */     CardCrawlGame.music.changeBGM("MENU");
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 285 */     sb.setColor(this.bgColor);
/* 286 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 288 */     sb.setColor(this.eyeColor);
/* 289 */     this.eye1.renderRight(sb);
/* 290 */     this.eye1.renderLeft(sb);
/* 291 */     this.eye2.renderRight(sb);
/* 292 */     this.eye2.renderLeft(sb);
/* 293 */     this.eye3.renderRight(sb);
/* 294 */     this.eye3.renderLeft(sb);
/*     */     
/* 296 */     for (SpeechWord w : words) {
/* 297 */       w.render(sb);
/*     */     }
/*     */     
/* 300 */     if (this.fadingOut) {
/* 301 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, Interpolation.fade.apply(1.0F, 0.0F, this.fadeOutTimer / 3.0F)));
/* 302 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cutscenes\NeowNarrationScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */