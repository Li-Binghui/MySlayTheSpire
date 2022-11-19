/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.files.FileHandle;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class PatchNotesScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  20 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PatchNotesScreen");
/*  21 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  23 */   private static String text = null;
/*     */   
/*     */   private FileHandle log;
/*  26 */   private static final float START_Y = Settings.HEIGHT - 300.0F * Settings.scale;
/*  27 */   private float targetY = this.scrollY = START_Y; private float scrollY;
/*  28 */   private float scrollLowerBound = Settings.HEIGHT - 300.0F * Settings.scale;
/*  29 */   private float scrollUpperBound = 2400.0F * Settings.scale;
/*  30 */   public MenuCancelButton button = new MenuCancelButton();
/*     */   
/*  32 */   private static final float LINE_WIDTH = 1200.0F * Settings.scale;
/*  33 */   private static final float LINE_SPACING = 30.0F * Settings.scale;
/*     */   
/*     */   private boolean grabbedScreen = false;
/*  36 */   private float grabStartY = 0.0F;
/*     */   private ScrollBar scrollBar;
/*     */   
/*     */   public PatchNotesScreen() {
/*  40 */     this.scrollBar = new ScrollBar(this);
/*     */   }
/*     */   
/*     */   public void open() {
/*  44 */     this.button.show(TEXT[0]);
/*  45 */     this.targetY = this.scrollLowerBound;
/*  46 */     this.scrollY = Settings.HEIGHT - 400.0F * Settings.scale;
/*  47 */     CardCrawlGame.mainMenuScreen.darken();
/*  48 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.PATCH_NOTES;
/*     */     
/*  50 */     if (text == null) {
/*  51 */       if (Settings.isBeta) {
/*  52 */         this.log = Gdx.files.internal("changelog" + File.separator + "notes.txt");
/*     */       } else {
/*  54 */         this.log = Gdx.files.internal("changelog" + File.separator + "notes_main.txt");
/*     */       } 
/*     */       
/*  57 */       openLog();
/*     */       
/*  59 */       this.scrollUpperBound = calculateHeight() + 300.0F * Settings.scale;
/*  60 */       if (this.scrollUpperBound < this.scrollLowerBound) {
/*  61 */         this.scrollUpperBound = this.scrollLowerBound + 100.0F * Settings.scale;
/*     */       }
/*     */     } else {
/*  64 */       this.scrollY = START_Y;
/*  65 */       this.targetY = this.scrollY;
/*     */     } 
/*     */   }
/*     */   
/*     */   private float calculateHeight() {
/*  70 */     return FontHelper.getHeight(FontHelper.tipBodyFont, text, 1.0F);
/*     */   }
/*     */   
/*     */   private void openLog() {
/*  74 */     try (BufferedReader br = new BufferedReader(this.log.reader())) {
/*  75 */       StringBuilder sb = new StringBuilder();
/*  76 */       String line = "";
/*     */       
/*     */       try {
/*  79 */         line = br.readLine();
/*  80 */       } catch (IOException e) {
/*  81 */         e.printStackTrace();
/*     */       } 
/*     */       
/*  84 */       while (line != null) {
/*  85 */         sb.append(line);
/*  86 */         sb.append(System.lineSeparator());
/*  87 */         line = br.readLine();
/*     */       } 
/*     */       
/*  90 */       text = sb.toString();
/*  91 */       br.close();
/*  92 */     } catch (IOException e) {
/*  93 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  99 */     if (Settings.isControllerMode) {
/* 100 */       if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 101 */         this.targetY += Settings.SCROLL_SPEED * 2.0F;
/* 102 */       } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 103 */         this.targetY -= Settings.SCROLL_SPEED * 2.0F;
/* 104 */       } else if (CInputActionSet.drawPile.isJustPressed()) {
/* 105 */         this.targetY -= Settings.SCROLL_SPEED * 10.0F;
/* 106 */       } else if (CInputActionSet.discardPile.isJustPressed()) {
/* 107 */         this.targetY += Settings.SCROLL_SPEED * 10.0F;
/*     */       } 
/*     */     }
/*     */     
/* 111 */     this.button.update();
/* 112 */     if (this.button.hb.clicked || InputHelper.pressedEscape) {
/* 113 */       InputHelper.pressedEscape = false;
/* 114 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 115 */       this.button.hide();
/* 116 */       CardCrawlGame.mainMenuScreen.lighten();
/*     */     } 
/*     */     
/* 119 */     boolean isDraggingScrollBar = this.scrollBar.update();
/* 120 */     if (!isDraggingScrollBar) {
/* 121 */       updateScrolling();
/*     */     }
/* 123 */     InputHelper.justClickedLeft = false;
/*     */   }
/*     */   
/*     */   private void updateScrolling() {
/* 127 */     int y = InputHelper.mY;
/*     */     
/* 129 */     if (!this.grabbedScreen) {
/* 130 */       if (InputHelper.scrolledDown) {
/* 131 */         this.targetY += Settings.SCROLL_SPEED;
/* 132 */       } else if (InputHelper.scrolledUp) {
/* 133 */         this.targetY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 136 */       if (InputHelper.justClickedLeft) {
/* 137 */         this.grabbedScreen = true;
/* 138 */         this.grabStartY = y - this.targetY;
/*     */       }
/*     */     
/* 141 */     } else if (InputHelper.isMouseDown) {
/* 142 */       this.targetY = y - this.grabStartY;
/*     */     } else {
/* 144 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 148 */     this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
/* 149 */     resetScrolling();
/* 150 */     updateBarPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 157 */     if (this.targetY < this.scrollLowerBound) {
/* 158 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
/* 159 */     } else if (this.targetY > this.scrollUpperBound) {
/* 160 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 165 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, TEXT[1], 250.0F * Settings.scale, this.scrollY + 70.0F * Settings.scale, LINE_WIDTH, LINE_SPACING, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, text, 300.0F * Settings.scale, this.scrollY, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     this.button.render(sb);
/* 183 */     this.scrollBar.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 188 */     this.scrollY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 189 */     this.targetY = this.scrollY;
/* 190 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 194 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
/* 195 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\PatchNotesScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */