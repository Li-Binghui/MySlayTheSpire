/*     */ package com.megacrit.cardcrawl.screens.compendium;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
/*     */ 
/*     */ public class PotionViewScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  25 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PotionViewScreen");
/*  26 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  28 */   private static final float SPACE = 80.0F * Settings.scale;
/*  29 */   private static final float START_X = 600.0F * Settings.scale; private float scrollY;
/*  30 */   private float targetY = this.scrollY = Settings.HEIGHT;
/*  31 */   private float scrollUpperBound = Settings.HEIGHT - 100.0F * Settings.scale; private float scrollLowerBound = Settings.HEIGHT / 2.0F;
/*  32 */   private int row = 0; private int col = 0;
/*  33 */   public MenuCancelButton button = new MenuCancelButton();
/*     */ 
/*     */   
/*  36 */   private ArrayList<AbstractPotion> commonPotions = null;
/*  37 */   private ArrayList<AbstractPotion> uncommonPotions = null;
/*  38 */   private ArrayList<AbstractPotion> rarePotions = null;
/*     */   
/*     */   private boolean grabbedScreen = false;
/*     */   
/*  42 */   private float grabStartY = 0.0F;
/*     */   private ScrollBar scrollBar;
/*  44 */   private Hitbox controllerPotionHb = null;
/*     */   
/*     */   public PotionViewScreen() {
/*  47 */     this.scrollBar = new ScrollBar(this);
/*     */   }
/*     */   
/*     */   public void open() {
/*  51 */     this.controllerPotionHb = null;
/*  52 */     sortOnOpen();
/*  53 */     this.button.show(TEXT[0]);
/*  54 */     this.targetY = this.scrollUpperBound - 50.0F * Settings.scale;
/*  55 */     this.scrollY = Settings.HEIGHT;
/*  56 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.POTION_VIEW;
/*     */   }
/*     */   
/*     */   private void sortOnOpen() {
/*  60 */     this.commonPotions = PotionHelper.getPotionsByRarity(AbstractPotion.PotionRarity.COMMON);
/*  61 */     this.uncommonPotions = PotionHelper.getPotionsByRarity(AbstractPotion.PotionRarity.UNCOMMON);
/*  62 */     this.rarePotions = PotionHelper.getPotionsByRarity(AbstractPotion.PotionRarity.RARE);
/*     */   }
/*     */   
/*     */   public void update() {
/*  66 */     updateControllerInput();
/*  67 */     if (Settings.isModded && Settings.isControllerMode && this.controllerPotionHb != null) {
/*  68 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.7F) {
/*  69 */         this.targetY += Settings.SCROLL_SPEED;
/*  70 */         if (this.targetY > this.scrollUpperBound) {
/*  71 */           this.targetY = this.scrollUpperBound;
/*     */         }
/*  73 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.3F) {
/*  74 */         this.targetY -= Settings.SCROLL_SPEED;
/*  75 */         if (this.targetY < this.scrollLowerBound) {
/*  76 */           this.targetY = this.scrollLowerBound;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  81 */     this.button.update();
/*  82 */     if (this.button.hb.clicked || InputHelper.pressedEscape) {
/*  83 */       InputHelper.pressedEscape = false;
/*  84 */       this.button.hide();
/*  85 */       CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/*     */     } 
/*     */     
/*  88 */     boolean isScrollingScrollBar = this.scrollBar.update();
/*  89 */     if (!isScrollingScrollBar) {
/*  90 */       updateScrolling();
/*     */     }
/*  92 */     InputHelper.justClickedLeft = false;
/*     */     
/*  94 */     updateList(this.commonPotions);
/*  95 */     updateList(this.uncommonPotions);
/*  96 */     updateList(this.rarePotions);
/*     */   }
/*     */   
/*     */   private enum PotionCategory {
/* 100 */     NONE, COMMON, UNCOMMON, RARE;
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 104 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 108 */     PotionCategory category = PotionCategory.NONE;
/* 109 */     int index = 0;
/* 110 */     boolean anyHovered = false;
/*     */     
/* 112 */     for (AbstractPotion r : this.commonPotions) {
/* 113 */       if (r.hb.hovered) {
/* 114 */         anyHovered = true;
/* 115 */         category = PotionCategory.COMMON;
/*     */         break;
/*     */       } 
/* 118 */       index++;
/*     */     } 
/*     */     
/* 121 */     if (!anyHovered) {
/* 122 */       index = 0;
/* 123 */       for (AbstractPotion r : this.uncommonPotions) {
/* 124 */         if (r.hb.hovered) {
/* 125 */           anyHovered = true;
/* 126 */           category = PotionCategory.UNCOMMON;
/*     */           break;
/*     */         } 
/* 129 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     if (!anyHovered) {
/* 134 */       index = 0;
/* 135 */       for (AbstractPotion r : this.rarePotions) {
/* 136 */         if (r.hb.hovered) {
/* 137 */           anyHovered = true;
/* 138 */           category = PotionCategory.RARE;
/*     */           break;
/*     */         } 
/* 141 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     if (!anyHovered) {
/* 146 */       System.out.println("NONE HOVERED");
/* 147 */       CInputHelper.setCursor(((AbstractPotion)this.commonPotions.get(0)).hb);
/*     */     } else {
/* 149 */       switch (category) {
/*     */         case COMMON:
/* 151 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 152 */             if (index >= 10) {
/* 153 */               index -= 10;
/*     */             }
/* 155 */             CInputHelper.setCursor(((AbstractPotion)this.commonPotions.get(index)).hb);
/* 156 */             this.controllerPotionHb = ((AbstractPotion)this.commonPotions.get(index)).hb; break;
/* 157 */           }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 158 */             index += 10;
/* 159 */             if (index > this.commonPotions.size() - 1) {
/* 160 */               index %= 10;
/* 161 */               if (index > this.uncommonPotions.size() - 1) {
/* 162 */                 index = this.uncommonPotions.size() - 1;
/*     */               }
/* 164 */               CInputHelper.setCursor(((AbstractPotion)this.uncommonPotions.get(index)).hb);
/* 165 */               this.controllerPotionHb = ((AbstractPotion)this.uncommonPotions.get(index)).hb; break;
/*     */             } 
/* 167 */             CInputHelper.setCursor(((AbstractPotion)this.commonPotions.get(index)).hb);
/* 168 */             this.controllerPotionHb = ((AbstractPotion)this.commonPotions.get(index)).hb; break;
/*     */           } 
/* 170 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 171 */             index--;
/* 172 */             if (index < 0) {
/* 173 */               index = this.commonPotions.size() - 1;
/*     */             }
/* 175 */             CInputHelper.setCursor(((AbstractPotion)this.commonPotions.get(index)).hb);
/* 176 */             this.controllerPotionHb = ((AbstractPotion)this.commonPotions.get(index)).hb; break;
/* 177 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 178 */             index++;
/* 179 */             if (index > this.commonPotions.size() - 1) {
/* 180 */               index = 0;
/*     */             }
/* 182 */             CInputHelper.setCursor(((AbstractPotion)this.commonPotions.get(index)).hb);
/* 183 */             this.controllerPotionHb = ((AbstractPotion)this.commonPotions.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case UNCOMMON:
/* 187 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 188 */             index -= 10;
/* 189 */             if (index < 0) {
/* 190 */               index = this.commonPotions.size() - 1;
/* 191 */               CInputHelper.setCursor(((AbstractPotion)this.commonPotions.get(index)).hb);
/* 192 */               this.controllerPotionHb = ((AbstractPotion)this.commonPotions.get(index)).hb; break;
/*     */             } 
/* 194 */             CInputHelper.setCursor(((AbstractPotion)this.uncommonPotions.get(index)).hb);
/* 195 */             this.controllerPotionHb = ((AbstractPotion)this.uncommonPotions.get(index)).hb; break;
/*     */           } 
/* 197 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 198 */             index += 10;
/* 199 */             if (index > this.uncommonPotions.size() - 1) {
/* 200 */               index %= 10;
/* 201 */               if (index > this.rarePotions.size() - 1) {
/* 202 */                 index = this.rarePotions.size() - 1;
/*     */               }
/* 204 */               CInputHelper.setCursor(((AbstractPotion)this.rarePotions.get(index)).hb);
/* 205 */               this.controllerPotionHb = ((AbstractPotion)this.rarePotions.get(index)).hb; break;
/*     */             } 
/* 207 */             CInputHelper.setCursor(((AbstractPotion)this.uncommonPotions.get(index)).hb);
/* 208 */             this.controllerPotionHb = ((AbstractPotion)this.uncommonPotions.get(index)).hb; break;
/*     */           } 
/* 210 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 211 */             index--;
/* 212 */             if (index < 0) {
/* 213 */               index = this.uncommonPotions.size() - 1;
/*     */             }
/* 215 */             CInputHelper.setCursor(((AbstractPotion)this.uncommonPotions.get(index)).hb);
/* 216 */             this.controllerPotionHb = ((AbstractPotion)this.uncommonPotions.get(index)).hb; break;
/* 217 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 218 */             index++;
/* 219 */             if (index > this.uncommonPotions.size() - 1) {
/* 220 */               index = 0;
/*     */             }
/* 222 */             CInputHelper.setCursor(((AbstractPotion)this.uncommonPotions.get(index)).hb);
/* 223 */             this.controllerPotionHb = ((AbstractPotion)this.uncommonPotions.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case RARE:
/* 227 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 228 */             CInputHelper.setCursor(((AbstractPotion)this.uncommonPotions.get(index)).hb);
/* 229 */             this.controllerPotionHb = ((AbstractPotion)this.uncommonPotions.get(index)).hb; break;
/* 230 */           }  if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 231 */             index--;
/* 232 */             if (index < 0) {
/* 233 */               index = this.rarePotions.size() - 1;
/*     */             }
/* 235 */             CInputHelper.setCursor(((AbstractPotion)this.rarePotions.get(index)).hb);
/* 236 */             this.controllerPotionHb = ((AbstractPotion)this.rarePotions.get(index)).hb; break;
/* 237 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 238 */             index++;
/* 239 */             if (index > this.rarePotions.size() - 1) {
/* 240 */               index = 0;
/*     */             }
/* 242 */             CInputHelper.setCursor(((AbstractPotion)this.rarePotions.get(index)).hb);
/* 243 */             this.controllerPotionHb = ((AbstractPotion)this.rarePotions.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateScrolling() {
/* 254 */     int y = InputHelper.mY;
/*     */     
/* 256 */     if (!this.grabbedScreen && Settings.isModded) {
/* 257 */       if (InputHelper.scrolledDown) {
/* 258 */         this.targetY += Settings.SCROLL_SPEED;
/* 259 */       } else if (InputHelper.scrolledUp) {
/* 260 */         this.targetY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 263 */       if (InputHelper.justClickedLeft) {
/* 264 */         this.grabbedScreen = true;
/* 265 */         this.grabStartY = y - this.targetY;
/*     */       }
/*     */     
/* 268 */     } else if (InputHelper.isMouseDown && Settings.isModded) {
/* 269 */       this.targetY = y - this.grabStartY;
/*     */     } else {
/* 271 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 275 */     this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
/* 276 */     resetScrolling();
/* 277 */     updateBarPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 284 */     if (this.targetY < this.scrollLowerBound) {
/* 285 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
/* 286 */     } else if (this.targetY > this.scrollUpperBound) {
/* 287 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateList(ArrayList<AbstractPotion> list) {
/* 292 */     for (AbstractPotion r : list) {
/* 293 */       r.hb.update();
/* 294 */       r.hb.move(r.posX, r.posY);
/* 295 */       r.update();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 300 */     this.row = -1;
/* 301 */     this.col = 0;
/*     */     
/* 303 */     renderList(sb, TEXT[1], TEXT[2], this.commonPotions);
/* 304 */     renderList(sb, TEXT[3], TEXT[4], this.uncommonPotions);
/* 305 */     renderList(sb, TEXT[5], TEXT[6], this.rarePotions);
/*     */     
/* 307 */     this.button.render(sb);
/* 308 */     if (Settings.isModded) {
/* 309 */       this.scrollBar.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderList(SpriteBatch sb, String msg, String desc, ArrayList<AbstractPotion> list) {
/* 314 */     this.row += 2;
/* 315 */     FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, msg, START_X - 50.0F * Settings.scale, this.scrollY + 4.0F * Settings.scale - SPACE * this.row, 99999.0F, 0.0F, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, desc, START_X - 50.0F * Settings.scale + 
/*     */ 
/*     */ 
/*     */         
/* 328 */         FontHelper.getSmartWidth(FontHelper.buttonLabelFont, msg, 99999.0F, 0.0F), this.scrollY - 0.0F * Settings.scale - SPACE * this.row, 99999.0F, 0.0F, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 334 */     this.row++;
/* 335 */     this.col = 0;
/* 336 */     for (AbstractPotion r : list) {
/* 337 */       if (this.col == 10) {
/* 338 */         this.col = 0;
/* 339 */         this.row++;
/*     */       } 
/* 341 */       r.posX = START_X + SPACE * this.col;
/* 342 */       r.posY = this.scrollY - SPACE * this.row;
/* 343 */       r.labRender(sb);
/* 344 */       this.col++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 350 */     if (!Settings.isModded) {
/*     */       return;
/*     */     }
/*     */     
/* 354 */     float newPosition = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 355 */     this.scrollY = newPosition;
/* 356 */     this.targetY = newPosition;
/* 357 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 361 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
/* 362 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\compendium\PotionViewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */