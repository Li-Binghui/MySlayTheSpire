/*     */ package com.megacrit.cardcrawl.screens.charSelect;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharacterOption
/*     */ {
/*  29 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CharacterOption");
/*  30 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private Texture buttonImg;
/*     */   
/*     */   private Texture portraitImg;
/*     */   
/*     */   private String portraitUrl;
/*  37 */   private static final float HB_W = 150.0F * Settings.scale; public AbstractPlayer c; public boolean selected = false; public boolean locked = false; public Hitbox hb;
/*     */   private static final int BUTTON_W = 220;
/*     */   public static final String ASSETS_DIR = "images/ui/charSelect/";
/*  40 */   private static final Color BLACK_OUTLINE_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/*  41 */   private Color glowColor = new Color(1.0F, 0.8F, 0.2F, 0.0F);
/*     */   
/*     */   private static final int ICON_W = 64;
/*     */   
/*  45 */   private static final float DEST_INFO_X = Settings.isMobile ? (160.0F * Settings.scale) : (200.0F * Settings.scale);
/*  46 */   private static final float START_INFO_X = -800.0F * Settings.xScale;
/*  47 */   private float infoX = START_INFO_X;
/*  48 */   private float infoY = Settings.HEIGHT / 2.0F;
/*  49 */   public String name = "";
/*  50 */   private static final float NAME_OFFSET_Y = 200.0F * Settings.scale;
/*     */   private String hp;
/*     */   private int gold;
/*     */   private String flavorText;
/*     */   private CharSelectInfo charInfo;
/*     */   private int unlocksRemaining;
/*  56 */   private int maxAscensionLevel = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterOption(String optionName, AbstractPlayer c, Texture buttonImg, Texture portraitImg) {
/*  62 */     this.name = optionName;
/*  63 */     this.hb = new Hitbox(HB_W, HB_W);
/*  64 */     this.buttonImg = buttonImg;
/*  65 */     this.portraitImg = portraitImg;
/*  66 */     this.c = c;
/*  67 */     this.charInfo = null;
/*  68 */     this.charInfo = c.getLoadout();
/*  69 */     this.hp = this.charInfo.hp;
/*  70 */     this.gold = this.charInfo.gold;
/*  71 */     this.flavorText = this.charInfo.flavorText;
/*  72 */     this.unlocksRemaining = 5 - UnlockTracker.getUnlockLevel(c.chosenClass);
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
/*     */   public CharacterOption(String optionName, AbstractPlayer c, String buttonUrl, String portraitImg) {
/*  84 */     this.name = optionName;
/*  85 */     this.hb = new Hitbox(HB_W, HB_W);
/*  86 */     this.buttonImg = ImageMaster.loadImage("images/ui/charSelect/" + buttonUrl);
/*  87 */     this.portraitUrl = c.getPortraitImageName();
/*  88 */     this.c = c;
/*  89 */     this.charInfo = null;
/*  90 */     this.charInfo = c.getLoadout();
/*  91 */     this.hp = this.charInfo.hp;
/*  92 */     this.gold = this.charInfo.gold;
/*  93 */     this.flavorText = this.charInfo.flavorText;
/*  94 */     this.unlocksRemaining = 5 - UnlockTracker.getUnlockLevel(c.chosenClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharacterOption(AbstractPlayer c) {
/* 101 */     this.hb = new Hitbox(HB_W, HB_W);
/* 102 */     this.buttonImg = ImageMaster.CHAR_SELECT_LOCKED;
/* 103 */     this.locked = true;
/* 104 */     this.c = c;
/*     */   }
/*     */   
/*     */   public void saveChosenAscensionLevel(int level) {
/* 108 */     Prefs pref = this.c.getPrefs();
/* 109 */     pref.putInteger("LAST_ASCENSION_LEVEL", level);
/* 110 */     pref.flush();
/*     */   }
/*     */   
/*     */   public void incrementAscensionLevel(int level) {
/* 114 */     if (level > this.maxAscensionLevel) {
/*     */       return;
/*     */     }
/*     */     
/* 118 */     saveChosenAscensionLevel(level);
/* 119 */     CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = level;
/* 120 */     CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = CharacterSelectScreen.A_TEXT[level - 1];
/*     */   }
/*     */   
/*     */   public void decrementAscensionLevel(int level) {
/* 124 */     if (level == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 128 */     saveChosenAscensionLevel(level);
/* 129 */     CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = level;
/* 130 */     CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = CharacterSelectScreen.A_TEXT[level - 1];
/*     */   }
/*     */   
/*     */   public void update() {
/* 134 */     updateHitbox();
/* 135 */     updateInfoPosition();
/*     */   }
/*     */   
/*     */   private void updateHitbox() {
/* 139 */     this.hb.update();
/* 140 */     if (this.hb.justHovered) {
/* 141 */       CardCrawlGame.sound.playA("UI_HOVER", -0.3F);
/*     */     }
/*     */     
/* 144 */     if (this.hb.hovered && this.locked) {
/* 145 */       if (this.c.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
/* 146 */         TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.xScale, InputHelper.mY - 10.0F * Settings.scale, TEXT[0], TEXT[1]);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 151 */       else if (this.c.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
/* 152 */         TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.xScale, InputHelper.mY - 10.0F * Settings.scale, TEXT[0], TEXT[3]);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 157 */       else if (this.c.chosenClass == AbstractPlayer.PlayerClass.WATCHER) {
/* 158 */         TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.xScale, InputHelper.mY - 10.0F * Settings.scale, TEXT[0], TEXT[10]);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (InputHelper.justClickedLeft && !this.locked && 
/* 167 */       this.hb.hovered) {
/* 168 */       CardCrawlGame.sound.playA("UI_CLICK_1", -0.4F);
/* 169 */       this.hb.clickStarted = true;
/*     */     } 
/*     */ 
/*     */     
/* 173 */     if (this.hb.clicked) {
/* 174 */       this.hb.clicked = false;
/*     */       
/* 176 */       if (!this.selected) {
/* 177 */         CardCrawlGame.mainMenuScreen.charSelectScreen.deselectOtherOptions(this);
/* 178 */         this.selected = true;
/* 179 */         CardCrawlGame.mainMenuScreen.charSelectScreen.justSelected();
/* 180 */         CardCrawlGame.chosenCharacter = this.c.chosenClass;
/* 181 */         CardCrawlGame.mainMenuScreen.charSelectScreen.confirmButton.isDisabled = false;
/* 182 */         CardCrawlGame.mainMenuScreen.charSelectScreen.confirmButton.show();
/*     */ 
/*     */         
/* 185 */         if (this.portraitUrl != null) {
/* 186 */           CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg = ImageMaster.loadImage("images/ui/charSelect/" + this.portraitUrl);
/*     */         }
/*     */         else {
/*     */           
/* 190 */           CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg = this.portraitImg;
/*     */         } 
/*     */         
/* 193 */         Prefs pref = this.c.getPrefs();
/* 194 */         if (!this.locked) {
/* 195 */           this.c.doCharSelectScreenSelectEffect();
/*     */         }
/*     */         
/* 198 */         if (pref != null) {
/* 199 */           CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = pref.getInteger("LAST_ASCENSION_LEVEL", 1);
/*     */ 
/*     */           
/* 202 */           if (20 < CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel) {
/* 203 */             CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = 20;
/*     */           }
/* 205 */           this.maxAscensionLevel = pref.getInteger("ASCENSION_LEVEL", 1);
/* 206 */           if (20 < this.maxAscensionLevel) {
/* 207 */             this.maxAscensionLevel = 20;
/*     */           }
/* 209 */           int ascensionLevel = CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel;
/* 210 */           if (ascensionLevel > this.maxAscensionLevel) {
/* 211 */             ascensionLevel = this.maxAscensionLevel;
/*     */           }
/*     */           
/* 214 */           if (ascensionLevel > 0) {
/* 215 */             CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = CharacterSelectScreen.A_TEXT[ascensionLevel - 1];
/*     */           } else {
/*     */             
/* 218 */             CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = "";
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateInfoPosition() {
/* 226 */     if (this.selected) {
/* 227 */       this.infoX = MathHelper.uiLerpSnap(this.infoX, DEST_INFO_X);
/*     */     } else {
/* 229 */       this.infoX = MathHelper.uiLerpSnap(this.infoX, START_INFO_X);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 234 */     renderOptionButton(sb);
/* 235 */     renderInfo(sb);
/* 236 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderOptionButton(SpriteBatch sb) {
/* 240 */     if (this.selected) {
/* 241 */       this.glowColor.a = 0.25F + (MathUtils.cosDeg((float)(System.currentTimeMillis() / 4L % 360L)) + 1.25F) / 3.5F;
/* 242 */       sb.setColor(this.glowColor);
/*     */     } else {
/* 244 */       sb.setColor(BLACK_OUTLINE_COLOR);
/*     */     } 
/*     */     
/* 247 */     sb.draw(ImageMaster.CHAR_OPT_HIGHLIGHT, this.hb.cX - 110.0F, this.hb.cY - 110.0F, 110.0F, 110.0F, 220.0F, 220.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 220, 220, false, false);
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
/* 265 */     if (this.selected || this.hb.hovered) {
/* 266 */       sb.setColor(Color.WHITE);
/*     */     } else {
/* 268 */       sb.setColor(Color.LIGHT_GRAY);
/*     */     } 
/*     */     
/* 271 */     sb.draw(this.buttonImg, this.hb.cX - 110.0F, this.hb.cY - 110.0F, 110.0F, 110.0F, 220.0F, 220.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 220, 220, false, false);
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
/*     */   private void renderInfo(SpriteBatch sb) {
/* 291 */     if (!this.name.equals("")) {
/* 292 */       if (!Settings.isMobile) {
/* 293 */         FontHelper.renderSmartText(sb, FontHelper.bannerNameFont, this.name, this.infoX - 35.0F * Settings.scale, this.infoY + NAME_OFFSET_Y, 99999.0F, 38.0F * Settings.scale, Settings.GOLD_COLOR);
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
/* 304 */         sb.draw(ImageMaster.TP_HP, this.infoX - 10.0F * Settings.scale - 32.0F, this.infoY + 95.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 322 */         FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, TEXT[4] + this.hp, this.infoX + 18.0F * Settings.scale, this.infoY + 102.0F * Settings.scale, 10000.0F, 10000.0F, Settings.RED_TEXT_COLOR);
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
/* 333 */         sb.draw(ImageMaster.TP_GOLD, this.infoX + 190.0F * Settings.scale - 32.0F, this.infoY + 95.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 351 */         FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, TEXT[5] + 
/*     */ 
/*     */             
/* 354 */             Integer.toString(this.gold), this.infoX + 220.0F * Settings.scale, this.infoY + 102.0F * Settings.scale, 10000.0F, 10000.0F, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 362 */         FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, this.flavorText, this.infoX - 26.0F * Settings.scale, this.infoY + 40.0F * Settings.scale, 10000.0F, 30.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 372 */         if (this.unlocksRemaining > 0) {
/* 373 */           FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, 
/*     */ 
/*     */               
/* 376 */               Integer.toString(this.unlocksRemaining) + TEXT[6], this.infoX - 26.0F * Settings.scale, this.infoY - 112.0F * Settings.scale, 10000.0F, 10000.0F, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 383 */           int unlockProgress = UnlockTracker.getCurrentProgress(this.c.chosenClass);
/* 384 */           int unlockCost = UnlockTracker.getCurrentScoreCost(this.c.chosenClass);
/*     */           
/* 386 */           FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, 
/*     */ 
/*     */               
/* 389 */               Integer.toString(unlockProgress) + "/" + unlockCost + TEXT[9], this.infoX - 26.0F * Settings.scale, this.infoY - 140.0F * Settings.scale, 10000.0F, 10000.0F, Settings.CREAM_COLOR);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 397 */         renderRelics(sb);
/*     */       }
/*     */       else {
/*     */         
/* 401 */         FontHelper.renderSmartText(sb, FontHelper.bannerNameFont, this.name, this.infoX - 35.0F * Settings.scale, this.infoY + 350.0F * Settings.scale, 99999.0F, 38.0F * Settings.scale, Settings.GOLD_COLOR, 1.1F);
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
/* 413 */         sb.draw(ImageMaster.TP_HP, this.infoX - 10.0F * Settings.scale - 32.0F, this.infoY + 230.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 431 */         FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, TEXT[4] + this.hp, this.infoX + 18.0F * Settings.scale, this.infoY + 243.0F * Settings.scale, 10000.0F, 10000.0F, Settings.RED_TEXT_COLOR, 0.8F);
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
/* 443 */         sb.draw(ImageMaster.TP_GOLD, this.infoX + 260.0F * Settings.scale - 32.0F, this.infoY + 230.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 461 */         FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, TEXT[5] + 
/*     */ 
/*     */             
/* 464 */             Integer.toString(this.gold), this.infoX + 290.0F * Settings.scale, this.infoY + 243.0F * Settings.scale, 10000.0F, 10000.0F, Settings.GOLD_COLOR, 0.8F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 472 */         if (this.selected)
/*     */         {
/* 474 */           FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, this.flavorText, this.infoX - 26.0F * Settings.scale, this.infoY + 170.0F * Settings.scale, 10000.0F, 40.0F * Settings.scale, Settings.CREAM_COLOR, 0.9F);
/*     */         }
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
/* 486 */         if (this.unlocksRemaining > 0) {
/* 487 */           FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, 
/*     */ 
/*     */               
/* 490 */               Integer.toString(this.unlocksRemaining) + TEXT[6], this.infoX - 26.0F * Settings.scale, this.infoY - 60.0F * Settings.scale, 10000.0F, 10000.0F, Settings.CREAM_COLOR, 0.8F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 498 */           int unlockProgress = UnlockTracker.getCurrentProgress(this.c.chosenClass);
/* 499 */           int unlockCost = UnlockTracker.getCurrentScoreCost(this.c.chosenClass);
/*     */           
/* 501 */           FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, 
/*     */ 
/*     */               
/* 504 */               Integer.toString(unlockProgress) + "/" + unlockCost + TEXT[9], this.infoX - 26.0F * Settings.scale, this.infoY - 100.0F * Settings.scale, 10000.0F, 10000.0F, Settings.CREAM_COLOR, 0.8F);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 513 */         renderRelics(sb);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderRelics(SpriteBatch sb) {
/* 519 */     if (this.charInfo.relics.size() == 1) {
/* 520 */       if (!Settings.isMobile)
/*     */       {
/* 522 */         sb.setColor(Settings.QUARTER_TRANSPARENT_BLACK_COLOR);
/* 523 */         sb.draw(
/* 524 */             (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).outlineImg, this.infoX - 64.0F, this.infoY - 60.0F * Settings.scale - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/* 541 */         sb.setColor(Color.WHITE);
/* 542 */         sb.draw(
/* 543 */             (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).img, this.infoX - 64.0F, this.infoY - 60.0F * Settings.scale - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/* 560 */         FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, 
/*     */ 
/*     */             
/* 563 */             (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).name, this.infoX + 44.0F * Settings.scale, this.infoY - 40.0F * Settings.scale, 10000.0F, 10000.0F, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 570 */         String relicString = (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).description;
/* 571 */         if (this.charInfo.name.equals(TEXT[7])) {
/* 572 */           relicString = TEXT[8];
/*     */         }
/*     */         
/* 575 */         FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, relicString, this.infoX + 44.0F * Settings.scale, this.infoY - 66.0F * Settings.scale, 10000.0F, 10000.0F, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 587 */         sb.setColor(Settings.QUARTER_TRANSPARENT_BLACK_COLOR);
/* 588 */         sb.draw(
/* 589 */             (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).outlineImg, this.infoX - 64.0F, this.infoY + 30.0F * Settings.scale - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 1.4F, Settings.scale * 1.4F, 0.0F, 0, 0, 128, 128, false, false);
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
/* 606 */         sb.setColor(Color.WHITE);
/* 607 */         sb.draw(
/* 608 */             (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).img, this.infoX - 64.0F, this.infoY + 30.0F * Settings.scale - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 1.4F, Settings.scale * 1.4F, 0.0F, 0, 0, 128, 128, false, false);
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
/* 625 */         FontHelper.renderSmartText(sb, FontHelper.topPanelInfoFont, 
/*     */ 
/*     */             
/* 628 */             (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).name, this.infoX + 60.0F * Settings.scale, this.infoY + 60.0F * Settings.scale, 10000.0F, 10000.0F, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 635 */         String relicString = (RelicLibrary.getRelic((String)this.charInfo.relics.get(0))).description;
/* 636 */         if (this.charInfo.name.equals(TEXT[7])) {
/* 637 */           relicString = TEXT[8];
/*     */         }
/*     */         
/* 640 */         if (this.selected) {
/* 641 */           FontHelper.renderSmartText(sb, FontHelper.topPanelInfoFont, relicString, this.infoX + 60.0F * Settings.scale, this.infoY + 24.0F * Settings.scale, 10000.0F, 10000.0F, Settings.CREAM_COLOR);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 654 */       for (int i = 0; i < this.charInfo.relics.size(); i++) {
/* 655 */         AbstractRelic r = RelicLibrary.getRelic(this.charInfo.relics.get(i));
/* 656 */         r.updateDescription(this.charInfo.player.chosenClass);
/*     */ 
/*     */ 
/*     */         
/* 660 */         Hitbox relicHitbox = new Hitbox(80.0F * Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics.size()), 80.0F * Settings.scale);
/*     */         
/* 662 */         relicHitbox.move(this.infoX + i * 72.0F * Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics
/* 663 */             .size()), this.infoY - 60.0F * Settings.scale);
/*     */         
/* 665 */         relicHitbox.render(sb);
/* 666 */         relicHitbox.update();
/* 667 */         if (relicHitbox.hovered) {
/* 668 */           if (InputHelper.mX < 1400.0F * Settings.scale) {
/* 669 */             TipHelper.queuePowerTips(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, r.tips);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 674 */             TipHelper.queuePowerTips(InputHelper.mX - 350.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, r.tips);
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 682 */         sb.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
/* 683 */         sb.draw(r.outlineImg, this.infoX - 64.0F + i * 72.0F * Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics
/*     */             
/* 685 */             .size()), this.infoY - 60.0F * Settings.scale - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 691 */             .size()), Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics
/* 692 */             .size()), 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 701 */         sb.setColor(Color.WHITE);
/* 702 */         sb.draw(r.img, this.infoX - 64.0F + i * 72.0F * Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics
/*     */             
/* 704 */             .size()), this.infoY - 60.0F * Settings.scale - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 710 */             .size()), Settings.scale * (0.01F + 1.0F - 0.019F * this.charInfo.relics
/* 711 */             .size()), 0.0F, 0, 0, 128, 128, false, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\charSelect\CharacterOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */