/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.ui.SpeechWord;

import java.util.ArrayList;
import java.util.Scanner;
/*     */ 
/*     */ 
/*     */ public class MegaDialogTextEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static GlyphLayout gl;
/*     */   private BitmapFont font;
/*     */   private DialogWord.AppearEffect a_effect;
/*  21 */   private static final float DEFAULT_WIDTH = 320.0F * Settings.scale;
/*  22 */   private static final float LINE_SPACING = Settings.isMobile ? (33.0F * Settings.scale) : (30.0F * Settings.scale);
/*  23 */   private static final float CHAR_SPACING = 8.0F * Settings.scale;
/*     */   private static final float WORD_TIME = 0.03F;
/*  25 */   private float wordTimer = 0.0F;
/*     */   
/*     */   private boolean textDone = false;
/*  28 */   private ArrayList<SpeechWord> words = new ArrayList<>(); private float x; private float y;
/*  29 */   private int curLine = 0;
/*     */   private Scanner s;
/*  31 */   private float curLineWidth = 0.0F;
/*     */   private static final float FADE_TIME = 0.3F;
/*     */   
/*     */   public MegaDialogTextEffect(float x, float y, float duration, String msg, DialogWord.AppearEffect a_effect) {
/*  35 */     if (gl == null) {
/*  36 */       gl = new GlyphLayout();
/*     */     }
/*     */     
/*  39 */     this.duration = duration;
/*  40 */     this.x = x;
/*  41 */     this.y = y;
/*  42 */     this.font = FontHelper.panelNameFont;
/*  43 */     this.a_effect = a_effect;
/*  44 */     this.s = new Scanner(msg);
/*     */   }
/*     */   
/*     */   public void update() {
/*  48 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/*  49 */     if (this.wordTimer < 0.0F && !this.textDone) {
/*  50 */       this.wordTimer = 0.03F;
/*  51 */       if (Settings.lineBreakViaCharacter) {
/*  52 */         addWordCN();
/*     */       } else {
/*  54 */         addWord();
/*     */       } 
/*     */     } 
/*     */     
/*  58 */     for (SpeechWord w : this.words) {
/*  59 */       w.update();
/*     */     }
/*     */ 
/*     */     
/*  63 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  64 */     if (this.duration < 0.0F) {
/*  65 */       this.words.clear();
/*  66 */       this.isDone = true;
/*     */     } 
/*     */     
/*  69 */     if (this.duration < 0.3F) {
/*  70 */       for (SpeechWord w : this.words) {
/*  71 */         w.fadeOut();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addWord() {
/*  77 */     this.wordTimer = 0.03F;
/*     */     
/*  79 */     if (this.s.hasNext()) {
/*  80 */       String word = this.s.next();
/*     */       
/*  82 */       if (word.equals("NL")) {
/*  83 */         this.curLine++;
/*  84 */         for (SpeechWord w : this.words) {
/*  85 */           w.shiftY(LINE_SPACING);
/*     */         }
/*  87 */         this.curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/*  91 */       DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
/*  92 */       if (color != DialogWord.WordColor.DEFAULT) {
/*  93 */         word = word.substring(2, word.length());
/*     */       }
/*     */       
/*  96 */       DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
/*  97 */       if (effect != DialogWord.WordEffect.NONE) {
/*  98 */         word = word.substring(1, word.length() - 1);
/*     */       }
/*     */       
/* 101 */       gl.setText(this.font, word);
/* 102 */       float temp = 0.0F;
/*     */ 
/*     */       
/* 105 */       if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
/* 106 */         this.curLine++;
/* 107 */         for (SpeechWord w : this.words) {
/* 108 */           w.shiftY(LINE_SPACING);
/*     */         }
/* 110 */         this.curLineWidth = gl.width + CHAR_SPACING;
/* 111 */         temp = -this.curLineWidth / 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 115 */         this.curLineWidth += gl.width;
/* 116 */         temp = -this.curLineWidth / 2.0F;
/*     */         
/* 118 */         for (SpeechWord w : this.words) {
/* 119 */           if (w.line == this.curLine) {
/* 120 */             w.setX(this.x + temp);
/* 121 */             gl.setText(this.font, w.word);
/* 122 */             temp += gl.width + CHAR_SPACING;
/*     */           } 
/*     */         } 
/*     */         
/* 126 */         this.curLineWidth += CHAR_SPACING;
/* 127 */         gl.setText(this.font, word + " ");
/*     */       } 
/*     */       
/* 130 */       this.words.add(new SpeechWord(this.font, word, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * this.curLine, this.curLine));
/*     */     }
/*     */     else {
/*     */       
/* 134 */       this.textDone = true;
/* 135 */       this.s.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addWordCN() {
/* 140 */     this.wordTimer = 0.03F;
/*     */     
/* 142 */     if (this.s.hasNext()) {
/* 143 */       String word = this.s.next();
/*     */       
/* 145 */       if (word.equals("NL")) {
/* 146 */         this.curLine++;
/* 147 */         for (SpeechWord w : this.words) {
/* 148 */           w.shiftY(LINE_SPACING);
/*     */         }
/* 150 */         this.curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/* 154 */       DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
/* 155 */       if (color != DialogWord.WordColor.DEFAULT) {
/* 156 */         word = word.substring(2, word.length());
/*     */       }
/*     */       
/* 159 */       DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
/* 160 */       if (effect != DialogWord.WordEffect.NONE) {
/* 161 */         word = word.substring(1, word.length() - 1);
/*     */       }
/*     */ 
/*     */       
/* 165 */       for (int i = 0; i < word.length(); i++) {
/* 166 */         String tmp = Character.toString(word.charAt(i));
/*     */         
/* 168 */         gl.setText(this.font, tmp);
/* 169 */         float temp = 0.0F;
/*     */ 
/*     */         
/* 172 */         if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
/* 173 */           this.curLine++;
/* 174 */           for (SpeechWord w : this.words) {
/* 175 */             w.shiftY(LINE_SPACING);
/*     */           }
/* 177 */           this.curLineWidth = gl.width;
/* 178 */           temp = -this.curLineWidth / 2.0F;
/*     */         }
/*     */         else {
/*     */           
/* 182 */           this.curLineWidth += gl.width;
/* 183 */           temp = -this.curLineWidth / 2.0F;
/*     */           
/* 185 */           for (SpeechWord w : this.words) {
/* 186 */             if (w.line == this.curLine) {
/* 187 */               w.setX(this.x + temp);
/* 188 */               gl.setText(this.font, w.word);
/* 189 */               temp += gl.width;
/*     */             } 
/*     */           } 
/* 192 */           gl.setText(this.font, tmp + " ");
/*     */         } 
/*     */         
/* 195 */         this.words.add(new SpeechWord(this.font, tmp, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * this.curLine, this.curLine));
/*     */       } 
/*     */     } else {
/*     */       
/* 199 */       this.textDone = true;
/* 200 */       this.s.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 205 */     for (SpeechWord w : this.words)
/* 206 */       w.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\MegaDialogTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */