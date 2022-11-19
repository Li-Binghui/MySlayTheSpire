/*     */ package com.megacrit.cardcrawl.events;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.GlyphLayout;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.ui.DialogWord;
/*     */ import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Scanner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoomEventDialog
/*     */ {
/*  23 */   private Color color = new Color(0.0F, 0.0F, 0.0F, 0.0F); private Color targetColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  24 */   private static final Color PANEL_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/*     */   private static final float COLOR_FADE_SPEED = 8.0F;
/*     */   private boolean isMoving = false;
/*  27 */   private float animateTimer = 0.0F;
/*     */   
/*     */   private static final float ANIM_SPEED = 0.5F;
/*     */   
/*     */   private boolean show = false;
/*  32 */   private float curLineWidth = 0.0F;
/*  33 */   private int curLine = 0;
/*     */   private DialogWord.AppearEffect a_effect;
/*     */   private Scanner s;
/*  36 */   private GlyphLayout gl = new GlyphLayout();
/*  37 */   private ArrayList<DialogWord> words = new ArrayList<>();
/*     */   private boolean textDone = true;
/*  39 */   private float wordTimer = 0.0F;
/*     */   private static final float WORD_TIME = 0.02F;
/*  41 */   private static final float CHAR_SPACING = 8.0F * Settings.scale;
/*  42 */   private static final float LINE_SPACING = 34.0F * Settings.scale;
/*  43 */   private static final float DIALOG_MSG_X = Settings.WIDTH * 0.1F;
/*  44 */   private static final float DIALOG_MSG_Y = 250.0F * Settings.scale;
/*  45 */   private static final float DIALOG_MSG_W = Settings.WIDTH * 0.8F;
/*     */ 
/*     */   
/*  48 */   public static ArrayList<LargeDialogOptionButton> optionList = new ArrayList<>();
/*  49 */   public static int selectedOption = -1;
/*     */   public static boolean waitForInput = true;
/*     */   
/*     */   public void update() {
/*  53 */     if (this.isMoving) {
/*  54 */       this.animateTimer -= Gdx.graphics.getDeltaTime();
/*  55 */       if (this.animateTimer < 0.0F) {
/*  56 */         this.animateTimer = 0.0F;
/*  57 */         this.isMoving = false;
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     this.color = this.color.lerp(this.targetColor, Gdx.graphics.getDeltaTime() * 8.0F);
/*     */     
/*  63 */     if (this.show) {
/*  64 */       for (int i = 0; i < optionList.size(); i++) {
/*  65 */         ((LargeDialogOptionButton)optionList.get(i)).update(optionList.size());
/*  66 */         if (((LargeDialogOptionButton)optionList.get(i)).pressed && waitForInput) {
/*  67 */           selectedOption = i;
/*  68 */           ((LargeDialogOptionButton)optionList.get(i)).pressed = false;
/*  69 */           waitForInput = false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  74 */     if (Settings.lineBreakViaCharacter) {
/*  75 */       bodyTextEffectCN();
/*     */     } else {
/*  77 */       bodyTextEffect();
/*     */     } 
/*  79 */     for (DialogWord w : this.words) {
/*  80 */       w.update();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getSelectedOption() {
/*  85 */     waitForInput = true;
/*  86 */     return selectedOption;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  90 */     optionList.clear();
/*  91 */     this.words.clear();
/*  92 */     waitForInput = true;
/*     */   }
/*     */   
/*     */   public void show() {
/*  96 */     this.targetColor = PANEL_COLOR;
/*  97 */     if (Settings.FAST_MODE) {
/*  98 */       this.animateTimer = 0.125F;
/*     */     } else {
/* 100 */       this.animateTimer = 0.5F;
/*     */     } 
/* 102 */     this.show = true;
/* 103 */     this.isMoving = true;
/*     */   }
/*     */   
/*     */   public void show(String text) {
/* 107 */     updateBodyText(text);
/* 108 */     show();
/*     */   }
/*     */   
/*     */   public void hide() {
/* 112 */     this.targetColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/* 113 */     if (Settings.FAST_MODE) {
/* 114 */       this.animateTimer = 0.125F;
/*     */     } else {
/* 116 */       this.animateTimer = 0.5F;
/*     */     } 
/* 118 */     this.show = false;
/* 119 */     this.isMoving = true;
/* 120 */     for (DialogWord w : this.words) {
/* 121 */       w.dialogFadeOut();
/*     */     }
/* 123 */     optionList.clear();
/*     */   }
/*     */   
/*     */   public void removeDialogOption(int slot) {
/* 127 */     optionList.remove(slot);
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text) {
/* 131 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text));
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text, AbstractCard previewCard) {
/* 135 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text, previewCard));
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text, AbstractRelic previewRelic) {
/* 139 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text, previewRelic));
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text, AbstractCard previewCard, AbstractRelic previewRelic) {
/* 143 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text, previewCard, previewRelic));
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text, boolean isDisabled) {
/* 147 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled));
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text, boolean isDisabled, AbstractCard previewCard) {
/* 151 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled, previewCard));
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text, boolean isDisabled, AbstractRelic previewRelic) {
/* 155 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled, previewRelic));
/*     */   }
/*     */   
/*     */   public void addDialogOption(String text, boolean isDisabled, AbstractCard previewCard, AbstractRelic previewRelic) {
/* 159 */     optionList.add(new LargeDialogOptionButton(optionList.size(), text, isDisabled, previewCard, previewRelic));
/*     */   }
/*     */   
/*     */   public void updateDialogOption(int slot, String text) {
/* 163 */     optionList.set(slot, new LargeDialogOptionButton(slot, text));
/*     */   }
/*     */   
/*     */   public void updateBodyText(String text) {
/* 167 */     updateBodyText(text, DialogWord.AppearEffect.BUMP_IN);
/*     */   }
/*     */   
/*     */   public void updateBodyText(String text, DialogWord.AppearEffect ae) {
/* 171 */     this.s = new Scanner(text);
/* 172 */     this.words.clear();
/* 173 */     this.textDone = false;
/* 174 */     this.a_effect = ae;
/* 175 */     this.curLineWidth = 0.0F;
/* 176 */     this.curLine = 0;
/*     */   }
/*     */   
/*     */   public void clearRemainingOptions() {
/* 180 */     for (int i = optionList.size() - 1; i > 0; i--) {
/* 181 */       optionList.remove(i);
/*     */     }
/*     */   }
/*     */   
/*     */   private void bodyTextEffectCN() {
/* 186 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/* 187 */     if (this.wordTimer < 0.0F && !this.textDone) {
/* 188 */       if (Settings.FAST_MODE) {
/* 189 */         this.wordTimer = 0.005F;
/*     */       } else {
/* 191 */         this.wordTimer = 0.02F;
/*     */       } 
/*     */       
/* 194 */       if (this.s.hasNext()) {
/* 195 */         String word = this.s.next();
/*     */         
/* 197 */         if (word.equals("NL")) {
/* 198 */           this.curLine++;
/* 199 */           this.curLineWidth = 0.0F;
/*     */           
/*     */           return;
/*     */         } 
/* 203 */         DialogWord.WordColor color = DialogWord.identifyWordColor(word);
/* 204 */         if (color != DialogWord.WordColor.DEFAULT) {
/* 205 */           word = word.substring(2, word.length());
/*     */         }
/*     */         
/* 208 */         DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
/* 209 */         if (effect != DialogWord.WordEffect.NONE) {
/* 210 */           word = word.substring(1, word.length() - 1);
/*     */         }
/*     */ 
/*     */         
/* 214 */         for (int i = 0; i < word.length(); i++) {
/* 215 */           String tmp = Character.toString(word.charAt(i));
/*     */           
/* 217 */           this.gl.setText(FontHelper.charDescFont, tmp);
/* 218 */           if (this.curLineWidth + this.gl.width > DIALOG_MSG_W) {
/* 219 */             this.curLine++;
/* 220 */             this.curLineWidth = this.gl.width;
/*     */           } else {
/* 222 */             this.curLineWidth += this.gl.width;
/*     */           } 
/*     */           
/* 225 */           this.words.add(new DialogWord(FontHelper.charDescFont, tmp, this.a_effect, effect, color, DIALOG_MSG_X + this.curLineWidth - this.gl.width, DIALOG_MSG_Y - LINE_SPACING * this.curLine, this.curLine));
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
/* 236 */           if (!this.show) {
/* 237 */             ((DialogWord)this.words.get(this.words.size() - 1)).dialogFadeOut();
/*     */           }
/*     */         } 
/*     */       } else {
/* 241 */         this.textDone = true;
/* 242 */         this.s.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void bodyTextEffect() {
/* 248 */     this.wordTimer -= Gdx.graphics.getDeltaTime();
/* 249 */     if (this.wordTimer < 0.0F && !this.textDone) {
/* 250 */       if (Settings.FAST_MODE) {
/* 251 */         this.wordTimer = 0.005F;
/*     */       } else {
/* 253 */         this.wordTimer = 0.02F;
/*     */       } 
/*     */       
/* 256 */       if (this.s.hasNext()) {
/* 257 */         String word = this.s.next();
/*     */         
/* 259 */         if (word.equals("NL")) {
/* 260 */           this.curLine++;
/* 261 */           this.curLineWidth = 0.0F;
/*     */           
/*     */           return;
/*     */         } 
/* 265 */         DialogWord.WordColor color = DialogWord.identifyWordColor(word);
/* 266 */         if (color != DialogWord.WordColor.DEFAULT) {
/* 267 */           word = word.substring(2, word.length());
/*     */         }
/*     */         
/* 270 */         DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
/* 271 */         if (effect != DialogWord.WordEffect.NONE) {
/* 272 */           word = word.substring(1, word.length() - 1);
/*     */         }
/*     */         
/* 275 */         this.gl.setText(FontHelper.charDescFont, word);
/* 276 */         if (this.curLineWidth + this.gl.width > DIALOG_MSG_W) {
/* 277 */           this.curLine++;
/* 278 */           this.curLineWidth = this.gl.width + CHAR_SPACING;
/*     */         } else {
/* 280 */           this.curLineWidth += this.gl.width + CHAR_SPACING;
/*     */         } 
/*     */         
/* 283 */         this.words.add(new DialogWord(FontHelper.charDescFont, word, this.a_effect, effect, color, DIALOG_MSG_X + this.curLineWidth - this.gl.width, DIALOG_MSG_Y - LINE_SPACING * this.curLine, this.curLine));
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
/* 294 */         if (!this.show) {
/* 295 */           ((DialogWord)this.words.get(this.words.size() - 1)).dialogFadeOut();
/*     */         }
/*     */       } else {
/*     */         
/* 299 */         this.textDone = true;
/* 300 */         this.s.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 306 */     if (!AbstractDungeon.player.isDead) {
/* 307 */       for (DialogWord w : this.words) {
/* 308 */         w.render(sb, Settings.HEIGHT - 525.0F * Settings.scale);
/*     */       }
/*     */       
/* 311 */       for (LargeDialogOptionButton b : optionList) {
/* 312 */         b.render(sb);
/*     */       }
/*     */       
/* 315 */       for (LargeDialogOptionButton b : optionList) {
/* 316 */         b.renderCardPreview(sb);
/*     */       }
/*     */       
/* 319 */       for (LargeDialogOptionButton b : optionList)
/* 320 */         b.renderRelicPreview(sb); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\RoomEventDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */