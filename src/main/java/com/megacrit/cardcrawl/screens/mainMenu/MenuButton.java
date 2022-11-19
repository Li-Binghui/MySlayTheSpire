/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MenuButton
/*     */ {
/*  26 */   private static final Logger logger = LogManager.getLogger(MenuButton.class.getName());
/*  27 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("MenuButton");
/*  28 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public ClickResult result;
/*     */   private String label;
/*     */   public Hitbox hb;
/*  33 */   private Color tint = Color.WHITE.cpy();
/*  34 */   private Color highlightColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   private int index;
/*     */   private boolean hidden = false;
/*  37 */   private float x = 0.0F; private float targetX = 0.0F;
/*  38 */   public static final float FONT_X = 120.0F * Settings.scale;
/*  39 */   public static final float START_Y = 120.0F * Settings.scale;
/*  40 */   public static final float SPACE_Y = 50.0F * Settings.scale;
/*  41 */   public static final float FONT_OFFSET_Y = 10.0F * Settings.scale;
/*     */   private boolean confirmation = false;
/*  43 */   private static Texture highlightImg = null;
/*     */   
/*     */   public enum ClickResult {
/*  46 */     PLAY, RESUME_GAME, ABANDON_RUN, INFO, STAT, SETTINGS, PATCH_NOTES, QUIT;
/*     */   }
/*     */   
/*     */   public MenuButton(ClickResult r, int index) {
/*  50 */     if (highlightImg == null) {
/*  51 */       highlightImg = ImageMaster.loadImage("images/ui/mainMenu/menu_option_highlight.png");
/*     */     }
/*  53 */     this.result = r;
/*  54 */     this.index = index;
/*  55 */     setLabel();
/*     */     
/*  57 */     if (Settings.isTouchScreen || Settings.isMobile) {
/*  58 */       this
/*  59 */         .hb = new Hitbox(FontHelper.getSmartWidth(FontHelper.losePowerFont, this.label, 9999.0F, 1.0F) * 1.25F + 100.0F * Settings.scale, SPACE_Y * 2.0F);
/*     */ 
/*     */       
/*  62 */       this.hb.move(this.hb.width / 2.0F + 75.0F * Settings.scale, START_Y + index * SPACE_Y * 2.0F);
/*     */     } else {
/*  64 */       this
/*  65 */         .hb = new Hitbox(FontHelper.getSmartWidth(FontHelper.buttonLabelFont, this.label, 9999.0F, 1.0F) + 100.0F * Settings.scale, SPACE_Y);
/*     */ 
/*     */       
/*  68 */       this.hb.move(this.hb.width / 2.0F + 75.0F * Settings.scale, START_Y + index * SPACE_Y);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setLabel() {
/*  73 */     switch (this.result) {
/*     */       case PLAY:
/*  75 */         this.label = TEXT[1];
/*     */         return;
/*     */       case RESUME_GAME:
/*  78 */         this.label = TEXT[4];
/*     */         return;
/*     */       case ABANDON_RUN:
/*  81 */         this.label = TEXT[10];
/*     */         return;
/*     */       case INFO:
/*  84 */         this.label = TEXT[14];
/*     */         return;
/*     */       case STAT:
/*  87 */         this.label = TEXT[6];
/*     */         return;
/*     */       case SETTINGS:
/*  90 */         this.label = TEXT[12];
/*     */         return;
/*     */       case QUIT:
/*  93 */         this.label = TEXT[8];
/*     */         return;
/*     */       case PATCH_NOTES:
/*  96 */         this.label = TEXT[9];
/*     */         return;
/*     */     } 
/*  99 */     this.label = "ERROR";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 105 */     if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.MAIN_MENU && CardCrawlGame.mainMenuScreen.bg.slider < 0.5F)
/*     */     {
/* 107 */       this.hb.update();
/*     */     }
/*     */     
/* 110 */     this.x = MathHelper.uiLerpSnap(this.x, this.targetX);
/*     */     
/* 112 */     if (this.hb.justHovered && !this.hidden) {
/* 113 */       CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
/*     */     }
/*     */     
/* 116 */     if (this.hb.hovered) {
/* 117 */       this.highlightColor.a = 0.9F;
/* 118 */       this.targetX = 25.0F * Settings.scale;
/* 119 */       if (InputHelper.justClickedLeft) {
/* 120 */         CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/* 121 */         this.hb.clickStarted = true;
/*     */       } 
/* 123 */       this.tint = Color.WHITE.cpy();
/* 124 */     } else if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.MAIN_MENU) {
/* 125 */       this.highlightColor.a = MathHelper.fadeLerpSnap(this.highlightColor.a, 0.0F);
/* 126 */       this.targetX = 0.0F;
/* 127 */       this.hidden = false;
/* 128 */       this.tint.r = MathHelper.fadeLerpSnap(this.tint.r, 0.3F);
/* 129 */       this.tint.g = this.tint.r;
/* 130 */       this.tint.b = this.tint.r;
/*     */     } 
/*     */     
/* 133 */     if (this.hb.hovered && CInputActionSet.select.isJustPressed()) {
/* 134 */       CInputActionSet.select.unpress();
/* 135 */       this.hb.clicked = true;
/* 136 */       CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/*     */     } 
/*     */     
/* 139 */     if (this.hb.clicked) {
/* 140 */       this.hb.clicked = false;
/* 141 */       buttonEffect();
/* 142 */       CardCrawlGame.mainMenuScreen.hideMenuButtons();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hide() {
/* 147 */     this.hb.hovered = false;
/* 148 */     this.targetX = -1000.0F * Settings.scale + 30.0F * Settings.scale * this.index;
/* 149 */     this.hidden = true;
/*     */   }
/*     */   
/*     */   public void buttonEffect() {
/* 153 */     switch (this.result) {
/*     */       case PLAY:
/* 155 */         CardCrawlGame.mainMenuScreen.panelScreen.open(MenuPanelScreen.PanelScreen.PLAY);
/*     */         break;
/*     */       case RESUME_GAME:
/* 158 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.NONE;
/* 159 */         CardCrawlGame.mainMenuScreen.hideMenuButtons();
/* 160 */         CardCrawlGame.mainMenuScreen.darken();
/* 161 */         resumeGame();
/*     */         break;
/*     */       case ABANDON_RUN:
/* 164 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.ABANDON_CONFIRM;
/* 165 */         CardCrawlGame.mainMenuScreen.abandonPopup.show();
/*     */         break;
/*     */       case INFO:
/* 168 */         CardCrawlGame.mainMenuScreen.panelScreen.open(MenuPanelScreen.PanelScreen.COMPENDIUM);
/*     */         break;
/*     */       case STAT:
/* 171 */         CardCrawlGame.mainMenuScreen.panelScreen.open(MenuPanelScreen.PanelScreen.STATS);
/*     */         break;
/*     */       case SETTINGS:
/* 174 */         CardCrawlGame.mainMenuScreen.panelScreen.open(MenuPanelScreen.PanelScreen.SETTINGS);
/*     */         break;
/*     */       case PATCH_NOTES:
/* 177 */         CardCrawlGame.mainMenuScreen.patchNotesScreen.open();
/*     */         break;
/*     */       case QUIT:
/* 180 */         logger.info("Quit Game button clicked!");
/* 181 */         Gdx.app.exit();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resumeGame() {
/* 189 */     CardCrawlGame.loadingSave = true;
/* 190 */     CardCrawlGame.chosenCharacter = (CardCrawlGame.characterManager.loadChosenCharacter()).chosenClass;
/* 191 */     CardCrawlGame.mainMenuScreen.isFadingOut = true;
/* 192 */     CardCrawlGame.mainMenuScreen.fadeOutMusic();
/* 193 */     Settings.isDailyRun = false;
/* 194 */     Settings.isTrial = false;
/* 195 */     ModHelper.setModsFalse();
/* 196 */     if (CardCrawlGame.steelSeries.isEnabled.booleanValue()) {
/* 197 */       CardCrawlGame.steelSeries.event_character_chosen(CardCrawlGame.chosenCharacter);
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 202 */     float lerper = Interpolation.circleIn.apply(CardCrawlGame.mainMenuScreen.bg.slider);
/* 203 */     float sliderX = -1000.0F * Settings.scale * lerper;
/* 204 */     sliderX -= this.index * 250.0F * Settings.scale * lerper;
/* 205 */     if (this.result == ClickResult.ABANDON_RUN) {
/* 206 */       if (this.confirmation) {
/* 207 */         this.label = TEXT[11];
/*     */       } else {
/* 209 */         this.label = TEXT[10];
/*     */       } 
/*     */     }
/*     */     
/* 213 */     sb.setBlendFunction(770, 1);
/* 214 */     sb.setColor(this.highlightColor);
/* 215 */     if (Settings.isTouchScreen || Settings.isMobile) {
/* 216 */       sb.draw(highlightImg, this.x + FONT_X + sliderX - 179.0F + 120.0F * Settings.scale, this.hb.cY - 56.0F, 179.0F, 52.0F, 358.0F, 104.0F, Settings.scale, Settings.scale * 1.2F, 0.0F, 0, 0, 358, 104, false, false);
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */       
/* 234 */       sb.draw(highlightImg, this.x + FONT_X + sliderX - 179.0F + 120.0F * Settings.scale, this.hb.cY - 52.0F, 179.0F, 52.0F, 358.0F, 104.0F, Settings.scale, Settings.scale * 0.8F, 0.0F, 0, 0, 358, 104, false, false);
/*     */     } 
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
/* 252 */     sb.setBlendFunction(770, 771);
/*     */     
/* 254 */     if (Settings.isTouchScreen || Settings.isMobile) {
/* 255 */       FontHelper.renderSmartText(sb, FontHelper.losePowerFont, this.label, this.x + FONT_X + sliderX, this.hb.cY + FONT_OFFSET_Y, 9999.0F, 1.0F, Settings.CREAM_COLOR, 1.25F);
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
/* 266 */       FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, this.label, this.x + FONT_X + sliderX, this.hb.cY + FONT_OFFSET_Y, 9999.0F, 1.0F, Settings.CREAM_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\MenuButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */