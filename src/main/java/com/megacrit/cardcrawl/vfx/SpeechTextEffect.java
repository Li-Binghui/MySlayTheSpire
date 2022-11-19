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
/*     */ public class SpeechTextEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static GlyphLayout gl;
/*     */   private BitmapFont font;
/*     */   private DialogWord.AppearEffect a_effect;
/*  21 */   private static final float DEFAULT_WIDTH = 280.0F * Settings.scale;
/*  22 */   private static final float LINE_SPACING = Settings.isMobile ? (18.0F * Settings.scale) : (15.0F * Settings.scale);
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
/*     */   public SpeechTextEffect(float x, float y, float duration, String msg, DialogWord.AppearEffect a_effect) {
/*  35 */     if (gl == null) {
/*  36 */       gl = new GlyphLayout();
/*     */     }
/*     */     
/*  39 */     this.duration = duration;
/*  40 */     this.x = x;
/*  41 */     this.y = y;
/*  42 */     this.font = FontHelper.turnNumFont;
/*  43 */     this.a_effect = a_effect;
/*  44 */     this.s = new Scanner(msg);
/*     */   }
/*     */   
/*     */   public void update() {
/*  48 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/*  49 */     if (this.wordTimer < 0.0F && !this.textDone) {
/*  50 */       if (Settings.lineBreakViaCharacter) {
/*  51 */         addWordCN();
/*     */       } else {
/*  53 */         addWord();
/*     */       } 
/*     */     }
/*     */     
/*  57 */     for (SpeechWord w : this.words) {
/*  58 */       w.update();
/*     */     }
/*     */ 
/*     */     
/*  62 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  63 */     if (this.duration < 0.0F) {
/*  64 */       this.words.clear();
/*  65 */       this.isDone = true;
/*     */     } 
/*     */     
/*  68 */     if (this.duration < 0.3F) {
/*  69 */       for (SpeechWord w : this.words) {
/*  70 */         w.fadeOut();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addWord() {
/*  76 */     this.wordTimer = 0.03F;
/*     */     
/*  78 */     if (this.s.hasNext()) {
/*  79 */       String word = this.s.next();
/*     */       
/*  81 */       if (word.equals("NL")) {
/*  82 */         this.curLine++;
/*  83 */         for (SpeechWord w : this.words) {
/*  84 */           w.shiftY(LINE_SPACING);
/*     */         }
/*  86 */         this.curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/*  90 */       DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
/*  91 */       if (color != DialogWord.WordColor.DEFAULT) {
/*  92 */         word = word.substring(2, word.length());
/*     */       }
/*     */       
/*  95 */       DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
/*  96 */       if (effect != DialogWord.WordEffect.NONE) {
/*  97 */         word = word.substring(1, word.length() - 1);
/*     */       }
/*     */       
/* 100 */       gl.setText(this.font, word);
/* 101 */       float temp = 0.0F;
/*     */ 
/*     */       
/* 104 */       if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
/* 105 */         this.curLine++;
/* 106 */         for (SpeechWord w : this.words) {
/* 107 */           w.shiftY(LINE_SPACING);
/*     */         }
/* 109 */         this.curLineWidth = gl.width + CHAR_SPACING;
/* 110 */         temp = -this.curLineWidth / 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 114 */         this.curLineWidth += gl.width;
/* 115 */         temp = -this.curLineWidth / 2.0F;
/*     */         
/* 117 */         for (SpeechWord w : this.words) {
/* 118 */           if (w.line == this.curLine) {
/* 119 */             w.setX(this.x + temp);
/* 120 */             gl.setText(this.font, w.word);
/* 121 */             temp += gl.width + CHAR_SPACING;
/*     */           } 
/*     */         } 
/*     */         
/* 125 */         this.curLineWidth += CHAR_SPACING;
/* 126 */         gl.setText(this.font, word + " ");
/*     */       } 
/*     */       
/* 129 */       this.words.add(new SpeechWord(this.font, word, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * this.curLine, this.curLine));
/*     */     }
/*     */     else {
/*     */       
/* 133 */       this.textDone = true;
/* 134 */       this.s.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addWordCN() {
/* 139 */     this.wordTimer = 0.03F;
/*     */     
/* 141 */     if (this.s.hasNext()) {
/* 142 */       String word = this.s.next();
/*     */       
/* 144 */       if (word.equals("NL")) {
/* 145 */         this.curLine++;
/* 146 */         for (SpeechWord w : this.words) {
/* 147 */           w.shiftY(LINE_SPACING);
/*     */         }
/* 149 */         this.curLineWidth = 0.0F;
/*     */         
/*     */         return;
/*     */       } 
/* 153 */       DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
/* 154 */       if (color != DialogWord.WordColor.DEFAULT) {
/* 155 */         word = word.substring(2, word.length());
/*     */       }
/*     */       
/* 158 */       DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
/* 159 */       if (effect != DialogWord.WordEffect.NONE) {
/* 160 */         word = word.substring(1, word.length() - 1);
/*     */       }
/*     */ 
/*     */       
/* 164 */       for (int i = 0; i < word.length(); i++) {
/* 165 */         String tmp = Character.toString(word.charAt(i));
/*     */         
/* 167 */         gl.setText(this.font, tmp);
/* 168 */         float temp = 0.0F;
/*     */ 
/*     */         
/* 171 */         if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
/* 172 */           this.curLine++;
/* 173 */           for (SpeechWord w : this.words) {
/* 174 */             w.shiftY(LINE_SPACING);
/*     */           }
/* 176 */           this.curLineWidth = gl.width;
/* 177 */           temp = -this.curLineWidth / 2.0F;
/*     */         }
/*     */         else {
/*     */           
/* 181 */           this.curLineWidth += gl.width;
/* 182 */           temp = -this.curLineWidth / 2.0F;
/*     */           
/* 184 */           for (SpeechWord w : this.words) {
/* 185 */             if (w.line == this.curLine) {
/* 186 */               w.setX(this.x + temp);
/* 187 */               gl.setText(this.font, w.word);
/* 188 */               temp += gl.width;
/*     */             } 
/*     */           } 
/* 191 */           gl.setText(this.font, tmp + " ");
/*     */         } 
/*     */         
/* 194 */         this.words.add(new SpeechWord(this.font, tmp, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * this.curLine, this.curLine));
/*     */       } 
/*     */     } else {
/*     */       
/* 198 */       this.textDone = true;
/* 199 */       this.s.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 204 */     for (SpeechWord w : this.words)
/* 205 */       w.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\SpeechTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */