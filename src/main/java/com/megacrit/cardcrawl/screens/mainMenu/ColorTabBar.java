/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
/*     */ 
/*     */ public class ColorTabBar {
/*  17 */   private static final float TAB_SPACING = 198.0F * Settings.xScale; private static final int BAR_W = 1334; private static final int BAR_H = 102;
/*     */   private static final int TAB_W = 274;
/*     */   private static final int TAB_H = 68;
/*     */   private static final int TICKBOX_W = 48;
/*     */   public Hitbox redHb;
/*  22 */   public CurrentTab curTab = CurrentTab.RED; public Hitbox greenHb; public Hitbox blueHb; public Hitbox purpleHb; public Hitbox colorlessHb; public Hitbox curseHb;
/*     */   public Hitbox viewUpgradeHb;
/*     */   private TabBarListener delegate;
/*     */   
/*  26 */   public enum CurrentTab { RED, GREEN, BLUE, PURPLE, COLORLESS, CURSE; }
/*     */ 
/*     */   
/*     */   public ColorTabBar(TabBarListener delegate) {
/*  30 */     float w = 200.0F * Settings.scale;
/*  31 */     float h = 50.0F * Settings.scale;
/*     */     
/*  33 */     this.redHb = new Hitbox(w, h);
/*  34 */     this.greenHb = new Hitbox(w, h);
/*  35 */     this.blueHb = new Hitbox(w, h);
/*  36 */     this.purpleHb = new Hitbox(w, h);
/*  37 */     this.colorlessHb = new Hitbox(w, h);
/*  38 */     this.curseHb = new Hitbox(w, h);
/*  39 */     this.delegate = delegate;
/*  40 */     this.viewUpgradeHb = new Hitbox(360.0F * Settings.scale, 48.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update(float y) {
/*  44 */     float x = 470.0F * Settings.xScale;
/*  45 */     this.redHb.move(x, y + 50.0F * Settings.scale);
/*  46 */     this.greenHb.move(x += TAB_SPACING, y + 50.0F * Settings.scale);
/*  47 */     this.blueHb.move(x += TAB_SPACING, y + 50.0F * Settings.scale);
/*  48 */     this.purpleHb.move(x += TAB_SPACING, y + 50.0F * Settings.scale);
/*  49 */     this.colorlessHb.move(x += TAB_SPACING, y + 50.0F * Settings.scale);
/*  50 */     this.curseHb.move(x += TAB_SPACING, y + 50.0F * Settings.scale);
/*  51 */     this.viewUpgradeHb.move(1410.0F * Settings.xScale, y);
/*     */     
/*  53 */     this.redHb.update();
/*  54 */     this.greenHb.update();
/*  55 */     this.blueHb.update();
/*  56 */     this.purpleHb.update();
/*  57 */     this.colorlessHb.update();
/*  58 */     this.curseHb.update();
/*  59 */     this.viewUpgradeHb.update();
/*     */     
/*  61 */     if (this.redHb.justHovered || this.greenHb.justHovered || this.blueHb.justHovered || this.colorlessHb.justHovered || this.curseHb.justHovered || this.purpleHb.justHovered)
/*     */     {
/*  63 */       CardCrawlGame.sound.playA("UI_HOVER", -0.4F);
/*     */     }
/*     */     
/*  66 */     if (Settings.isControllerMode) {
/*  67 */       if (CInputActionSet.pageRightViewExhaust.isJustPressed()) {
/*  68 */         if (this.curTab == CurrentTab.RED) {
/*  69 */           this.curTab = CurrentTab.GREEN;
/*  70 */         } else if (this.curTab == CurrentTab.GREEN) {
/*  71 */           this.curTab = CurrentTab.BLUE;
/*  72 */         } else if (this.curTab == CurrentTab.BLUE) {
/*  73 */           this.curTab = CurrentTab.PURPLE;
/*  74 */         } else if (this.curTab == CurrentTab.PURPLE) {
/*  75 */           this.curTab = CurrentTab.COLORLESS;
/*  76 */         } else if (this.curTab == CurrentTab.COLORLESS) {
/*  77 */           this.curTab = CurrentTab.CURSE;
/*  78 */         } else if (this.curTab == CurrentTab.CURSE) {
/*  79 */           this.curTab = CurrentTab.RED;
/*     */         } 
/*  81 */         this.delegate.didChangeTab(this, this.curTab);
/*  82 */       } else if (CInputActionSet.pageLeftViewDeck.isJustPressed()) {
/*  83 */         if (this.curTab == CurrentTab.RED) {
/*  84 */           this.curTab = CurrentTab.CURSE;
/*  85 */         } else if (this.curTab == CurrentTab.GREEN) {
/*  86 */           this.curTab = CurrentTab.RED;
/*  87 */         } else if (this.curTab == CurrentTab.BLUE) {
/*  88 */           this.curTab = CurrentTab.GREEN;
/*  89 */         } else if (this.curTab == CurrentTab.PURPLE) {
/*  90 */           this.curTab = CurrentTab.BLUE;
/*  91 */         } else if (this.curTab == CurrentTab.COLORLESS) {
/*  92 */           this.curTab = CurrentTab.PURPLE;
/*  93 */         } else if (this.curTab == CurrentTab.CURSE) {
/*  94 */           this.curTab = CurrentTab.COLORLESS;
/*     */         } 
/*  96 */         this.delegate.didChangeTab(this, this.curTab);
/*     */       } 
/*     */     }
/*     */     
/* 100 */     if (InputHelper.justClickedLeft) {
/* 101 */       CurrentTab oldTab = this.curTab;
/* 102 */       if (this.redHb.hovered) {
/* 103 */         this.curTab = CurrentTab.RED;
/* 104 */       } else if (this.greenHb.hovered) {
/* 105 */         this.curTab = CurrentTab.GREEN;
/* 106 */       } else if (this.blueHb.hovered) {
/* 107 */         this.curTab = CurrentTab.BLUE;
/* 108 */       } else if (this.purpleHb.hovered) {
/* 109 */         this.curTab = CurrentTab.PURPLE;
/* 110 */       } else if (this.colorlessHb.hovered) {
/* 111 */         this.curTab = CurrentTab.COLORLESS;
/* 112 */       } else if (this.curseHb.hovered) {
/* 113 */         this.curTab = CurrentTab.CURSE;
/*     */       } 
/* 115 */       if (oldTab != this.curTab) {
/* 116 */         this.delegate.didChangeTab(this, this.curTab);
/*     */       }
/*     */     } 
/*     */     
/* 120 */     if (this.viewUpgradeHb.justHovered) {
/* 121 */       CardCrawlGame.sound.playA("UI_HOVER", -0.3F);
/*     */     }
/*     */     
/* 124 */     if (this.viewUpgradeHb.hovered && InputHelper.justClickedLeft) {
/* 125 */       this.viewUpgradeHb.clickStarted = true;
/*     */     }
/*     */     
/* 128 */     if (this.viewUpgradeHb.clicked || (this.viewUpgradeHb.hovered && CInputActionSet.select.isJustPressed())) {
/* 129 */       this.viewUpgradeHb.clicked = false;
/* 130 */       CardCrawlGame.sound.playA("UI_CLICK_1", -0.2F);
/* 131 */       SingleCardViewPopup.isViewingUpgrade = !SingleCardViewPopup.isViewingUpgrade;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Hitbox hoveredHb() {
/* 136 */     if (this.redHb.hovered)
/* 137 */       return this.redHb; 
/* 138 */     if (this.greenHb.hovered)
/* 139 */       return this.greenHb; 
/* 140 */     if (this.blueHb.hovered)
/* 141 */       return this.blueHb; 
/* 142 */     if (this.purpleHb.hovered)
/* 143 */       return this.purpleHb; 
/* 144 */     if (this.colorlessHb.hovered)
/* 145 */       return this.colorlessHb; 
/* 146 */     if (this.curseHb.hovered)
/* 147 */       return this.curseHb; 
/* 148 */     if (this.viewUpgradeHb.hovered) {
/* 149 */       return this.viewUpgradeHb;
/*     */     }
/*     */     
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   public Color getBarColor() {
/* 156 */     switch (this.curTab) {
/*     */       case RED:
/* 158 */         return new Color(0.5F, 0.1F, 0.1F, 1.0F);
/*     */       case GREEN:
/* 160 */         return new Color(0.25F, 0.55F, 0.0F, 1.0F);
/*     */       case BLUE:
/* 162 */         return new Color(0.01F, 0.34F, 0.52F, 1.0F);
/*     */       case PURPLE:
/* 164 */         return new Color(0.37F, 0.22F, 0.49F, 1.0F);
/*     */       case COLORLESS:
/* 166 */         return new Color(0.4F, 0.4F, 0.4F, 1.0F);
/*     */       case CURSE:
/* 168 */         return new Color(0.18F, 0.18F, 0.16F, 1.0F);
/*     */     } 
/*     */ 
/*     */     
/* 172 */     return Color.WHITE;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, float y) {
/* 176 */     sb.setColor(Color.GRAY);
/*     */     
/* 178 */     if (this.curTab != CurrentTab.CURSE) {
/* 179 */       renderTab(sb, ImageMaster.COLOR_TAB_CURSE, this.curseHb.cX, y, CardLibraryScreen.TEXT[5], true);
/*     */     }
/* 181 */     if (this.curTab != CurrentTab.COLORLESS) {
/* 182 */       renderTab(sb, ImageMaster.COLOR_TAB_COLORLESS, this.colorlessHb.cX, y, CardLibraryScreen.TEXT[4], true);
/*     */     }
/* 184 */     if (this.curTab != CurrentTab.BLUE) {
/* 185 */       renderTab(sb, ImageMaster.COLOR_TAB_BLUE, this.blueHb.cX, y, CardLibraryScreen.TEXT[3], true);
/*     */     }
/* 187 */     if (this.curTab != CurrentTab.PURPLE) {
/* 188 */       renderTab(sb, ImageMaster.COLOR_TAB_PURPLE, this.purpleHb.cX, y, CardLibraryScreen.TEXT[8], true);
/*     */     }
/* 190 */     if (this.curTab != CurrentTab.GREEN) {
/* 191 */       renderTab(sb, ImageMaster.COLOR_TAB_GREEN, this.greenHb.cX, y, CardLibraryScreen.TEXT[2], true);
/*     */     }
/* 193 */     if (this.curTab != CurrentTab.RED) {
/* 194 */       renderTab(sb, ImageMaster.COLOR_TAB_RED, this.redHb.cX, y, CardLibraryScreen.TEXT[1], true);
/*     */     }
/*     */     
/* 197 */     sb.setColor(getBarColor());
/* 198 */     sb.draw(ImageMaster.COLOR_TAB_BAR, Settings.WIDTH / 2.0F - 667.0F, y - 51.0F, 667.0F, 51.0F, 1334.0F, 102.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 1334, 102, false, false);
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
/* 216 */     sb.setColor(Color.WHITE);
/*     */     
/* 218 */     switch (this.curTab) {
/*     */       case RED:
/* 220 */         renderTab(sb, ImageMaster.COLOR_TAB_RED, this.redHb.cX, y, CardLibraryScreen.TEXT[1], false);
/*     */         break;
/*     */       case GREEN:
/* 223 */         renderTab(sb, ImageMaster.COLOR_TAB_GREEN, this.greenHb.cX, y, CardLibraryScreen.TEXT[2], false);
/*     */         break;
/*     */       case BLUE:
/* 226 */         renderTab(sb, ImageMaster.COLOR_TAB_BLUE, this.blueHb.cX, y, CardLibraryScreen.TEXT[3], false);
/*     */         break;
/*     */       case PURPLE:
/* 229 */         renderTab(sb, ImageMaster.COLOR_TAB_PURPLE, this.purpleHb.cX, y, CardLibraryScreen.TEXT[8], false);
/*     */         break;
/*     */       case COLORLESS:
/* 232 */         renderTab(sb, ImageMaster.COLOR_TAB_COLORLESS, this.colorlessHb.cX, y, CardLibraryScreen.TEXT[4], false);
/*     */         break;
/*     */       case CURSE:
/* 235 */         renderTab(sb, ImageMaster.COLOR_TAB_CURSE, this.curseHb.cX, y, CardLibraryScreen.TEXT[5], false);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 241 */     renderViewUpgrade(sb, y);
/*     */     
/* 243 */     this.redHb.render(sb);
/* 244 */     this.greenHb.render(sb);
/* 245 */     this.blueHb.render(sb);
/* 246 */     this.purpleHb.render(sb);
/* 247 */     this.colorlessHb.render(sb);
/* 248 */     this.curseHb.render(sb);
/* 249 */     this.viewUpgradeHb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderTab(SpriteBatch sb, Texture img, float x, float y, String label, boolean selected) {
/* 253 */     sb.draw(img, x - 137.0F, y - 34.0F + 53.0F * Settings.scale, 137.0F, 34.0F, 274.0F, 68.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 274, 68, false, false);
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
/* 271 */     Color c = Settings.GOLD_COLOR;
/* 272 */     if (selected) {
/* 273 */       c = Color.GRAY;
/*     */     }
/*     */     
/* 276 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, label, x, y + 50.0F * Settings.scale, c, 0.9F);
/*     */   }
/*     */   
/*     */   private void renderViewUpgrade(SpriteBatch sb, float y) {
/* 280 */     Color c = Settings.CREAM_COLOR;
/*     */     
/* 282 */     if (this.viewUpgradeHb.hovered) {
/* 283 */       c = Settings.GOLD_COLOR;
/*     */     }
/*     */     
/* 286 */     FontHelper.renderFontRightAligned(sb, FontHelper.topPanelInfoFont, CardLibraryScreen.TEXT[7], 1546.0F * Settings.xScale, y, c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     Texture img = SingleCardViewPopup.isViewingUpgrade ? ImageMaster.COLOR_TAB_BOX_TICKED : ImageMaster.COLOR_TAB_BOX_UNTICKED;
/*     */ 
/*     */     
/* 297 */     sb.setColor(c);
/* 298 */     sb.draw(img, 1532.0F * Settings.xScale - 
/*     */         
/* 300 */         FontHelper.getSmartWidth(FontHelper.topPanelInfoFont, CardLibraryScreen.TEXT[7], 9999.0F, 0.0F) - 24.0F, y - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\ColorTabBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */