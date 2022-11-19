/*     */ package com.megacrit.cardcrawl.events;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.GlyphLayout;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
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
/*     */ 
/*     */ public class GenericEventDialog
/*     */ {
/*  27 */   private Texture img = null;
/*  28 */   private static final float DIALOG_MSG_X_IMAGE = 816.0F * Settings.xScale;
/*  29 */   private static final float DIALOG_MSG_W_IMAGE = 900.0F * Settings.scale;
/*     */ 
/*     */   
/*  32 */   private Color panelColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  33 */   private Color titleColor = new Color(1.0F, 0.835F, 0.39F, 0.0F);
/*  34 */   private Color borderColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  35 */   private Color imgColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  36 */   private float animateTimer = 0.0F;
/*     */   
/*     */   private static final float ANIM_SPEED = 0.5F;
/*     */   
/*     */   private static boolean show = false;
/*  41 */   private String title = "";
/*  42 */   private static final float TITLE_X = 570.0F * Settings.xScale;
/*  43 */   private static final float TITLE_Y = Settings.EVENT_Y + 408.0F * Settings.scale;
/*     */ 
/*     */   
/*  46 */   private static float curLineWidth = 0.0F;
/*  47 */   private static int curLine = 0;
/*     */   private static DialogWord.AppearEffect a_effect;
/*     */   private static Scanner s;
/*  50 */   private static GlyphLayout gl = new GlyphLayout();
/*  51 */   private static ArrayList<DialogWord> words = new ArrayList<>();
/*     */   private static boolean textDone = true;
/*  53 */   private static float wordTimer = 0.0F;
/*     */   private static final float WORD_TIME = 0.02F;
/*  55 */   private static final float CHAR_SPACING = 8.0F * Settings.scale;
/*  56 */   private static final float LINE_SPACING = Settings.BIG_TEXT_MODE ? (40.0F * Settings.scale) : (38.0F * Settings.scale);
/*  57 */   private static final float DIALOG_MSG_X_TEXT = 455.0F * Settings.xScale;
/*  58 */   private static final float DIALOG_MSG_Y_TEXT = Settings.isMobile ? (Settings.EVENT_Y + 330.0F * Settings.scale) : (Settings.EVENT_Y + 300.0F * Settings.scale);
/*     */   
/*  60 */   private static final float DIALOG_MSG_W_TEXT = 1000.0F * Settings.scale;
/*  61 */   private static float DIALOG_MSG_X = DIALOG_MSG_X_TEXT;
/*  62 */   private static float DIALOG_MSG_Y = DIALOG_MSG_Y_TEXT;
/*  63 */   private static float DIALOG_MSG_W = DIALOG_MSG_W_TEXT;
/*     */ 
/*     */   
/*  66 */   public ArrayList<LargeDialogOptionButton> optionList = new ArrayList<>();
/*  67 */   public static int selectedOption = -1;
/*     */   public static boolean waitForInput = true;
/*     */   
/*     */   public void loadImage(String imgUrl) {
/*  71 */     if (this.img != null) {
/*  72 */       this.img.dispose();
/*  73 */       this.img = null;
/*     */     } 
/*  75 */     this.img = ImageMaster.loadImage(imgUrl);
/*     */     
/*  77 */     DIALOG_MSG_X = DIALOG_MSG_X_IMAGE;
/*  78 */     DIALOG_MSG_W = DIALOG_MSG_W_IMAGE;
/*     */   }
/*     */   
/*     */   private void clearImage() {
/*  82 */     dispose();
/*  83 */     DIALOG_MSG_X = DIALOG_MSG_X_TEXT;
/*  84 */     DIALOG_MSG_Y = DIALOG_MSG_Y_TEXT;
/*  85 */     DIALOG_MSG_W = DIALOG_MSG_W_TEXT;
/*     */   }
/*     */   
/*     */   public void update() {
/*  89 */     animateIn();
/*     */     
/*  91 */     if (show && this.animateTimer == 0.0F) {
/*  92 */       for (int i = 0; i < this.optionList.size(); i++) {
/*  93 */         ((LargeDialogOptionButton)this.optionList.get(i)).update(this.optionList.size());
/*  94 */         if (((LargeDialogOptionButton)this.optionList.get(i)).pressed && waitForInput) {
/*  95 */           selectedOption = i;
/*  96 */           ((LargeDialogOptionButton)this.optionList.get(i)).pressed = false;
/*  97 */           waitForInput = false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 102 */     if (!Settings.lineBreakViaCharacter) {
/* 103 */       bodyTextEffect();
/*     */     } else {
/* 105 */       bodyTextEffectCN();
/*     */     } 
/*     */     
/* 108 */     for (DialogWord w : words) {
/* 109 */       w.update();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void animateIn() {
/* 117 */     if (show) {
/* 118 */       this.animateTimer -= Gdx.graphics.getDeltaTime();
/* 119 */       if (this.animateTimer < 0.0F) {
/* 120 */         this.animateTimer = 0.0F;
/*     */       }
/* 122 */       this.panelColor.a = MathHelper.slowColorLerpSnap(this.panelColor.a, 1.0F);
/*     */       
/* 124 */       if (this.panelColor.a > 0.8F) {
/* 125 */         this.titleColor.a = MathHelper.slowColorLerpSnap(this.titleColor.a, 1.0F);
/* 126 */         this.borderColor.a = this.titleColor.a;
/* 127 */         if (this.borderColor.a > 0.8F) {
/* 128 */           this.imgColor.a = MathHelper.slowColorLerpSnap(this.imgColor.a, 1.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getSelectedOption() {
/* 135 */     waitForInput = true;
/* 136 */     return selectedOption;
/*     */   }
/*     */   
/*     */   public static void hide() {
/* 140 */     show = false;
/*     */   }
/*     */   
/*     */   public static void show() {
/* 144 */     show = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 151 */     show = false;
/* 152 */     clearImage();
/* 153 */     this.animateTimer = 0.0F;
/* 154 */     this.panelColor.a = 0.0F;
/* 155 */     this.titleColor.a = 0.0F;
/* 156 */     this.imgColor.a = 0.0F;
/* 157 */     this.borderColor.a = 0.0F;
/* 158 */     this.optionList.clear();
/* 159 */     words.clear();
/* 160 */     waitForInput = true;
/*     */   }
/*     */   
/*     */   public static void cleanUp() {
/* 164 */     words.clear();
/* 165 */     show = false;
/* 166 */     waitForInput = true;
/*     */   }
/*     */   
/*     */   public void show(String title, String text) {
/* 170 */     this.title = title;
/* 171 */     updateBodyText(text);
/* 172 */     if (Settings.FAST_MODE) {
/* 173 */       this.animateTimer = 0.125F;
/*     */     } else {
/* 175 */       this.animateTimer = 0.5F;
/*     */     } 
/* 177 */     show = true;
/*     */   }
/*     */   
/*     */   public void clearAllDialogs() {
/* 181 */     this.optionList.clear();
/*     */   }
/*     */   
/*     */   public void removeDialogOption(int slot) {
/* 185 */     if (slot < this.optionList.size()) {
/* 186 */       this.optionList.remove(slot);
/*     */     }
/* 188 */     for (LargeDialogOptionButton b : this.optionList) {
/* 189 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearRemainingOptions() {
/* 194 */     for (int i = this.optionList.size() - 1; i > 0; i--) {
/* 195 */       this.optionList.remove(i);
/*     */     }
/* 197 */     for (LargeDialogOptionButton b : this.optionList) {
/* 198 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text) {
/* 203 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text));
/* 204 */     for (LargeDialogOptionButton b : this.optionList) {
/* 205 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text, AbstractCard previewCard) {
/* 210 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text, previewCard));
/* 211 */     for (LargeDialogOptionButton b : this.optionList) {
/* 212 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text, AbstractRelic previewRelic) {
/* 217 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text, previewRelic));
/* 218 */     for (LargeDialogOptionButton b : this.optionList) {
/* 219 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text, AbstractCard previewCard, AbstractRelic previewRelic) {
/* 224 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text, previewCard, previewRelic));
/* 225 */     for (LargeDialogOptionButton b : this.optionList) {
/* 226 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text, boolean isDisabled) {
/* 231 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text, isDisabled));
/* 232 */     for (LargeDialogOptionButton b : this.optionList) {
/* 233 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text, boolean isDisabled, AbstractCard previewCard) {
/* 238 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text, isDisabled, previewCard));
/* 239 */     for (LargeDialogOptionButton b : this.optionList) {
/* 240 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text, boolean isDisabled, AbstractRelic previewRelic) {
/* 245 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text, isDisabled, previewRelic));
/* 246 */     for (LargeDialogOptionButton b : this.optionList) {
/* 247 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDialogOption(String text, boolean isDisabled, AbstractCard previewCard, AbstractRelic previewRelic) {
/* 252 */     this.optionList.add(new LargeDialogOptionButton(this.optionList.size(), text, isDisabled, previewCard, previewRelic));
/* 253 */     for (LargeDialogOptionButton b : this.optionList) {
/* 254 */       b.calculateY(this.optionList.size());
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateDialogOption(int slot, String text) {
/* 259 */     if (!this.optionList.isEmpty()) {
/* 260 */       if (this.optionList.size() > slot) {
/* 261 */         this.optionList.set(slot, new LargeDialogOptionButton(slot, text));
/*     */       } else {
/* 263 */         this.optionList.add(new LargeDialogOptionButton(slot, text));
/*     */       } 
/*     */     } else {
/* 266 */       this.optionList.add(new LargeDialogOptionButton(slot, text));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateDialogOption(int slot, String text, boolean isDisabled) {
/* 271 */     if (!this.optionList.isEmpty()) {
/* 272 */       if (this.optionList.size() > slot) {
/* 273 */         this.optionList.set(slot, new LargeDialogOptionButton(slot, text, isDisabled));
/*     */       } else {
/* 275 */         this.optionList.add(new LargeDialogOptionButton(slot, text, isDisabled));
/*     */       } 
/*     */     } else {
/* 278 */       this.optionList.add(new LargeDialogOptionButton(slot, text, isDisabled));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateDialogOption(int slot, String text, AbstractCard previewCard) {
/* 283 */     if (!this.optionList.isEmpty()) {
/* 284 */       if (this.optionList.size() > slot) {
/* 285 */         this.optionList.set(slot, new LargeDialogOptionButton(slot, text, previewCard));
/*     */       } else {
/* 287 */         this.optionList.add(new LargeDialogOptionButton(slot, text, previewCard));
/*     */       } 
/*     */     } else {
/* 290 */       this.optionList.add(new LargeDialogOptionButton(slot, text, previewCard));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateBodyText(String text) {
/* 295 */     updateBodyText(text, DialogWord.AppearEffect.BUMP_IN);
/*     */   }
/*     */   
/*     */   public void updateBodyText(String text, DialogWord.AppearEffect ae) {
/* 299 */     s = new Scanner(text);
/* 300 */     words.clear();
/* 301 */     textDone = false;
/* 302 */     a_effect = ae;
/* 303 */     curLineWidth = 0.0F;
/* 304 */     curLine = 0;
/*     */   }
/*     */   
/*     */   private void bodyTextEffectCN() {
/* 308 */     wordTimer -= Gdx.graphics.getDeltaTime();
/* 309 */     if (wordTimer < 0.0F && !textDone) {
/* 310 */       if (Settings.FAST_MODE) {
/* 311 */         wordTimer = 0.005F;
/*     */       } else {
/* 313 */         wordTimer = 0.02F;
/*     */       } 
/*     */       
/* 316 */       if (s.hasNext()) {
/* 317 */         String word = s.next();
/*     */         
/* 319 */         if (word.equals("NL")) {
/* 320 */           curLine++;
/* 321 */           curLineWidth = 0.0F;
/*     */           
/*     */           return;
/*     */         } 
/* 325 */         DialogWord.WordColor color = DialogWord.identifyWordColor(word);
/* 326 */         if (color != DialogWord.WordColor.DEFAULT) {
/* 327 */           word = word.substring(2, word.length());
/*     */         }
/*     */         
/* 330 */         DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
/* 331 */         if (effect != DialogWord.WordEffect.NONE) {
/* 332 */           word = word.substring(1, word.length() - 1);
/*     */         }
/*     */ 
/*     */         
/* 336 */         for (int i = 0; i < word.length(); i++) {
/* 337 */           String tmp = Character.toString(word.charAt(i));
/*     */           
/* 339 */           gl.setText(FontHelper.charDescFont, tmp);
/* 340 */           if (curLineWidth + gl.width > DIALOG_MSG_W) {
/* 341 */             curLine++;
/* 342 */             curLineWidth = gl.width;
/*     */           } else {
/* 344 */             curLineWidth += gl.width;
/*     */           } 
/*     */           
/* 347 */           words.add(new DialogWord(FontHelper.charDescFont, tmp, a_effect, effect, color, DIALOG_MSG_X + curLineWidth - gl.width, DIALOG_MSG_Y - LINE_SPACING * curLine, curLine));
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
/* 358 */           if (!show) {
/* 359 */             ((DialogWord)words.get(words.size() - 1)).dialogFadeOut();
/*     */           }
/*     */         } 
/*     */       } else {
/* 363 */         textDone = true;
/* 364 */         s.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void bodyTextEffect() {
/* 370 */     wordTimer -= Gdx.graphics.getDeltaTime();
/* 371 */     if (wordTimer < 0.0F && !textDone) {
/* 372 */       if (Settings.FAST_MODE) {
/* 373 */         wordTimer = 0.005F;
/*     */       } else {
/* 375 */         wordTimer = 0.02F;
/*     */       } 
/*     */       
/* 378 */       if (s.hasNext()) {
/* 379 */         String word = s.next();
/*     */         
/* 381 */         if (word.equals("NL")) {
/* 382 */           curLine++;
/* 383 */           curLineWidth = 0.0F;
/*     */           
/*     */           return;
/*     */         } 
/* 387 */         DialogWord.WordColor color = DialogWord.identifyWordColor(word);
/* 388 */         if (color != DialogWord.WordColor.DEFAULT) {
/* 389 */           word = word.substring(2, word.length());
/*     */         }
/*     */         
/* 392 */         DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
/* 393 */         if (effect != DialogWord.WordEffect.NONE) {
/* 394 */           word = word.substring(1, word.length() - 1);
/*     */         }
/*     */         
/* 397 */         gl.setText(FontHelper.charDescFont, word);
/* 398 */         if (curLineWidth + gl.width > DIALOG_MSG_W) {
/* 399 */           curLine++;
/* 400 */           curLineWidth = gl.width + CHAR_SPACING;
/*     */         } else {
/* 402 */           curLineWidth += gl.width + CHAR_SPACING;
/*     */         } 
/*     */         
/* 405 */         words.add(new DialogWord(FontHelper.charDescFont, word, a_effect, effect, color, DIALOG_MSG_X + curLineWidth - gl.width, DIALOG_MSG_Y - LINE_SPACING * curLine, curLine));
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
/* 416 */         if (!show) {
/* 417 */           ((DialogWord)words.get(words.size() - 1)).dialogFadeOut();
/*     */         }
/*     */       } else {
/* 420 */         textDone = true;
/* 421 */         s.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 427 */     if (show && !AbstractDungeon.player.isDead) {
/* 428 */       sb.setColor(this.panelColor);
/*     */       
/* 430 */       sb.draw(AbstractDungeon.eventBackgroundImg, Settings.WIDTH / 2.0F - 881.5F - 12.0F * Settings.xScale, Settings.EVENT_Y - 403.0F + 64.0F * Settings.scale, 881.5F, 403.0F, 1763.0F, 806.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 1763, 806, false, false);
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
/* 448 */       if (this.img != null) {
/* 449 */         sb.setColor(this.imgColor);
/* 450 */         sb.draw(this.img, 460.0F * Settings.xScale - 300.0F, Settings.EVENT_Y - 300.0F + 16.0F * Settings.scale, 300.0F, 300.0F, 600.0F, 600.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 600, 600, false, false);
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
/* 468 */         sb.setColor(this.borderColor);
/* 469 */         sb.draw(ImageMaster.EVENT_IMG_FRAME, 460.0F * Settings.xScale - 305.0F, Settings.EVENT_Y - 305.0F + 16.0F * Settings.scale, 305.0F, 305.0F, 610.0F, 610.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 610, 610, false, false);
/*     */       } 
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
/* 488 */       FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.title, TITLE_X, TITLE_Y, this.titleColor, 0.88F);
/*     */       
/* 490 */       for (DialogWord w : words) {
/* 491 */         w.render(sb);
/*     */       }
/*     */       
/* 494 */       for (LargeDialogOptionButton b : this.optionList) {
/* 495 */         b.render(sb);
/*     */       }
/* 497 */       for (LargeDialogOptionButton b : this.optionList) {
/* 498 */         b.renderCardPreview(sb);
/*     */       }
/* 500 */       for (LargeDialogOptionButton b : this.optionList) {
/* 501 */         b.renderRelicPreview(sb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 507 */     if (this.img != null) {
/* 508 */       this.img.dispose();
/* 509 */       this.img = null;
/* 510 */       show = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\GenericEventDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */