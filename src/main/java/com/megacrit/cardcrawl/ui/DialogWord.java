/*     */ package com.megacrit.cardcrawl.ui;
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ 
/*     */ public class DialogWord {
/*     */   private BitmapFont font;
/*     */   private WordEffect effect;
/*     */   private WordColor wColor;
/*     */   public String word;
/*  17 */   public int line = 0; private float x; private float y;
/*     */   private float target_x;
/*  19 */   private float timer = 0.0F; private float target_y; private float offset_x; private float offset_y; private Color color;
/*     */   private Color targetColor;
/*  21 */   private float scale = 1.0F; private float targetScale = 1.0F;
/*  22 */   private static final float BUMP_OFFSET = 20.0F * Settings.scale;
/*     */   private static GlyphLayout gl;
/*     */   private static final float COLOR_LERP_SPEED = 8.0F;
/*  25 */   private static final float SHAKE_AMT = 2.0F * Settings.scale;
/*  26 */   private static final float DIALOG_FADE_Y = 50.0F * Settings.scale;
/*     */   
/*     */   private static final float WAVY_DIST = 3.0F;
/*     */   private static final float SHAKE_INTERVAL = 0.02F;
/*     */   
/*     */   public enum AppearEffect
/*     */   {
/*  33 */     NONE, FADE_IN, GROW_IN, BUMP_IN;
/*     */   }
/*     */   
/*     */   public enum WordEffect {
/*  37 */     NONE, WAVY, SLOW_WAVY, SHAKY, PULSE;
/*     */   }
/*     */   
/*     */   public enum WordColor {
/*  41 */     DEFAULT, RED, GREEN, BLUE, GOLD, PURPLE, WHITE;
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
/*     */   public DialogWord(BitmapFont font, String word, AppearEffect a_effect, WordEffect effect, WordColor wColor, float x, float y, int line) {
/*  54 */     if (gl == null) {
/*  55 */       gl = new GlyphLayout();
/*     */     }
/*     */     
/*  58 */     this.font = font;
/*  59 */     this.effect = effect;
/*  60 */     this.wColor = wColor;
/*  61 */     this.word = word;
/*  62 */     this.x = x;
/*  63 */     this.y = y;
/*  64 */     this.target_x = x;
/*  65 */     this.target_y = y;
/*  66 */     this.targetColor = getColor();
/*  67 */     this.line = line;
/*  68 */     this.color = new Color(this.targetColor.r, this.targetColor.g, this.targetColor.b, 0.0F);
/*     */     
/*  70 */     if (effect == WordEffect.WAVY || effect == WordEffect.SLOW_WAVY) {
/*  71 */       this.timer = MathUtils.random(1.5707964F);
/*     */     }
/*     */
    switch (a_effect) {
        case FADE_IN:
        default:
            return;
        case GROW_IN:
            this.y -= BUMP_OFFSET;
            this.scale = 0.0f;
            return;
        case BUMP_IN:
            this.y -= BUMP_OFFSET;
            return;
    }

/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Color getColor() {
    switch (this.wColor) {
        case RED:
            return Settings.RED_TEXT_COLOR.cpy();
        case GREEN:
            return Settings.GREEN_TEXT_COLOR.cpy();
        case BLUE:
            return Settings.BLUE_TEXT_COLOR.cpy();
        case GOLD:
            return Settings.GOLD_COLOR.cpy();
        case PURPLE:
            return Settings.PURPLE_COLOR.cpy();
        case WHITE:
            return Settings.CREAM_COLOR.cpy();
        default:
            return Settings.CREAM_COLOR.cpy();
    }

/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 111 */     if (this.x != this.target_x) {
/* 112 */       this.x = MathUtils.lerp(this.x, this.target_x, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     }
/* 114 */     if (this.y != this.target_y) {
/* 115 */       this.y = MathUtils.lerp(this.y, this.target_y, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     }
/*     */ 
/*     */     
/* 119 */     this.color = this.color.lerp(this.targetColor, Gdx.graphics.getDeltaTime() * 8.0F);
/*     */ 
/*     */     
/* 122 */     if (this.scale != this.targetScale) {
/* 123 */       this.scale = MathHelper.scaleLerpSnap(this.scale, this.targetScale);
/*     */     }
/*     */     
/* 126 */     applyEffects();
/*     */   }
/*     */   
/*     */   private void applyEffects() {
/* 130 */     switch (this.effect) {
/*     */       case SHAKY:
/* 132 */         this.timer -= Gdx.graphics.getDeltaTime();
/* 133 */         if (this.timer < 0.0F) {
/* 134 */           this.offset_x = MathUtils.random(-SHAKE_AMT, SHAKE_AMT);
/* 135 */           this.offset_y = MathUtils.random(-SHAKE_AMT, SHAKE_AMT);
/* 136 */           this.timer = 0.02F;
/*     */         } 
/*     */         break;
/*     */       case WAVY:
/* 140 */         this.timer += Gdx.graphics.getDeltaTime() * 6.0F;
/* 141 */         this.offset_y = (float)Math.cos(this.timer) * Settings.scale * 3.0F;
/*     */         break;
/*     */       case SLOW_WAVY:
/* 144 */         this.timer += Gdx.graphics.getDeltaTime() * 3.0F;
/* 145 */         this.offset_y = (float)Math.cos(this.timer) * Settings.scale * 1.5F;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dialogFadeOut() {
/* 154 */     this.targetColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/* 155 */     this.target_y -= DIALOG_FADE_Y;
/*     */   }
/*     */   
/*     */   public void shiftY(float shiftAmount) {
/* 159 */     this.target_y += shiftAmount;
/*     */   }
/*     */   
/*     */   public void shiftX(float shiftAmount) {
/* 163 */     this.target_x += shiftAmount;
/*     */   }
/*     */   
/*     */   public void setX(float newX) {
/* 167 */     this.target_x = newX;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 171 */     this.font.setColor(this.color);
/* 172 */     this.font.getData().setScale(this.scale);
/* 173 */     this.font.draw((Batch)sb, this.word, this.x + this.offset_x, this.y + this.offset_y);
/* 174 */     this.font.getData().setScale(1.0F);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, float y2) {
/* 178 */     this.font.setColor(this.color);
/* 179 */     this.font.getData().setScale(this.scale);
/* 180 */     this.font.draw((Batch)sb, this.word, this.x + this.offset_x, this.y + this.offset_y + y2);
/* 181 */     this.font.getData().setScale(1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WordEffect identifyWordEffect(String word) {
/* 191 */     if (word.length() > 2) {
/* 192 */       if (word.charAt(0) == '@' && word.charAt(word.length() - 1) == '@')
/* 193 */         return WordEffect.SHAKY; 
/* 194 */       if (word.charAt(0) == '~' && word.charAt(word.length() - 1) == '~') {
/* 195 */         return WordEffect.WAVY;
/*     */       }
/*     */     } 
/*     */     
/* 199 */     return WordEffect.NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WordColor identifyWordColor(String word) {
/* 209 */     if (word.charAt(0) == '#') {
/* 210 */       switch (word.charAt(1)) {
/*     */         case 'r':
/* 212 */           return WordColor.RED;
/*     */         case 'g':
/* 214 */           return WordColor.GREEN;
/*     */         case 'b':
/* 216 */           return WordColor.BLUE;
/*     */         case 'y':
/* 218 */           return WordColor.GOLD;
/*     */         case 'p':
/* 220 */           return WordColor.PURPLE;
/*     */       } 
/*     */     
/*     */     }
/* 224 */     return WordColor.DEFAULT;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\DialogWord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */