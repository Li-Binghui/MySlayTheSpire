/*     */ package com.megacrit.cardcrawl.screens.compendium;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.GameCursor;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.RelicLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class RelicViewScreen
/*     */   implements ScrollBarListener {
/*  25 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RelicViewScreen");
/*  26 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  28 */   private static final float SPACE = 80.0F * Settings.scale;
/*  29 */   private static final float START_X = 600.0F * Settings.scale;
/*  30 */   private static final float START_Y = Settings.HEIGHT - 300.0F * Settings.scale; private float scrollY;
/*  31 */   private float targetY = this.scrollY = START_Y;
/*  32 */   private float scrollLowerBound = Settings.HEIGHT - 100.0F * Settings.scale;
/*  33 */   private float scrollUpperBound = 3000.0F * Settings.scale;
/*  34 */   private int row = 0; private int col = 0;
/*  35 */   public MenuCancelButton button = new MenuCancelButton();
/*     */ 
/*     */   
/*  38 */   private AbstractRelic hoveredRelic = null; private AbstractRelic clickStartedRelic = null;
/*  39 */   private ArrayList<AbstractRelic> relicGroup = null;
/*     */   
/*     */   private boolean grabbedScreen = false;
/*     */   
/*  43 */   private float grabStartY = 0.0F;
/*     */   private ScrollBar scrollBar;
/*  45 */   private Hitbox controllerRelicHb = null;
/*     */   
/*     */   public RelicViewScreen() {
/*  48 */     this.scrollBar = new ScrollBar(this);
/*     */   }
/*     */   
/*     */   public void open() {
/*  52 */     this.controllerRelicHb = null;
/*  53 */     if (Settings.isInfo) {
/*  54 */       RelicLibrary.unlockAndSeeAllRelics();
/*     */     }
/*     */     
/*  57 */     sortOnOpen();
/*  58 */     this.button.show(TEXT[0]);
/*  59 */     this.targetY = this.scrollLowerBound;
/*  60 */     this.scrollY = Settings.HEIGHT - 400.0F * Settings.scale;
/*  61 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.RELIC_VIEW;
/*     */   }
/*     */   
/*     */   private void sortOnOpen() {
/*  65 */     RelicLibrary.starterList = RelicLibrary.sortByStatus(RelicLibrary.starterList, false);
/*  66 */     RelicLibrary.commonList = RelicLibrary.sortByStatus(RelicLibrary.commonList, false);
/*  67 */     RelicLibrary.uncommonList = RelicLibrary.sortByStatus(RelicLibrary.uncommonList, false);
/*  68 */     RelicLibrary.rareList = RelicLibrary.sortByStatus(RelicLibrary.rareList, false);
/*  69 */     RelicLibrary.bossList = RelicLibrary.sortByStatus(RelicLibrary.bossList, false);
/*  70 */     RelicLibrary.specialList = RelicLibrary.sortByStatus(RelicLibrary.specialList, false);
/*  71 */     RelicLibrary.shopList = RelicLibrary.sortByStatus(RelicLibrary.shopList, false);
/*     */   }
/*     */   
/*     */   public void update() {
/*  75 */     if (!CardCrawlGame.relicPopup.isOpen) {
/*  76 */       updateControllerInput();
/*  77 */       if (Settings.isControllerMode && this.controllerRelicHb != null) {
/*  78 */         if (Gdx.input.getY() > Settings.HEIGHT * 0.7F) {
/*  79 */           this.targetY += Settings.SCROLL_SPEED;
/*  80 */           if (this.targetY > this.scrollUpperBound) {
/*  81 */             this.targetY = this.scrollUpperBound;
/*     */           }
/*  83 */         } else if (Gdx.input.getY() < Settings.HEIGHT * 0.3F) {
/*  84 */           this.targetY -= Settings.SCROLL_SPEED;
/*  85 */           if (this.targetY < this.scrollLowerBound) {
/*  86 */             this.targetY = this.scrollLowerBound;
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/*  91 */       if (this.hoveredRelic != null) {
/*  92 */         CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
/*  93 */         if (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) {
/*  94 */           this.clickStartedRelic = this.hoveredRelic;
/*     */         }
/*  96 */         if (InputHelper.justReleasedClickLeft || CInputActionSet.select.isJustPressed()) {
/*  97 */           CInputActionSet.select.unpress();
/*  98 */           if (this.hoveredRelic == this.clickStartedRelic) {
/*  99 */             CardCrawlGame.relicPopup.open(this.hoveredRelic, this.relicGroup);
/* 100 */             this.clickStartedRelic = null;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 104 */         this.clickStartedRelic = null;
/*     */       } 
/*     */       
/* 107 */       this.button.update();
/* 108 */       if (this.button.hb.clicked || InputHelper.pressedEscape) {
/* 109 */         InputHelper.pressedEscape = false;
/* 110 */         this.button.hide();
/* 111 */         CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/*     */       } 
/*     */       
/* 114 */       boolean isScrollingScrollBar = this.scrollBar.update();
/* 115 */       if (!isScrollingScrollBar) {
/* 116 */         updateScrolling();
/*     */       }
/* 118 */       InputHelper.justClickedLeft = false;
/*     */       
/* 120 */       this.hoveredRelic = null;
/* 121 */       this.relicGroup = null;
/* 122 */       updateList(RelicLibrary.starterList);
/* 123 */       updateList(RelicLibrary.commonList);
/* 124 */       updateList(RelicLibrary.uncommonList);
/* 125 */       updateList(RelicLibrary.rareList);
/* 126 */       updateList(RelicLibrary.bossList);
/* 127 */       updateList(RelicLibrary.specialList);
/* 128 */       updateList(RelicLibrary.shopList);
/*     */       
/* 130 */       if (Settings.isControllerMode && this.controllerRelicHb != null)
/* 131 */         Gdx.input.setCursorPosition((int)this.controllerRelicHb.cX, (int)(Settings.HEIGHT - this.controllerRelicHb.cY)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private enum RelicCategory
/*     */   {
/* 137 */     STARTER, COMMON, UNCOMMON, RARE, BOSS, EVENT, SHOP, NONE;
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 141 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 145 */     RelicCategory category = RelicCategory.NONE;
/* 146 */     int index = 0;
/* 147 */     boolean anyHovered = false;
/* 148 */     for (AbstractRelic r : RelicLibrary.starterList) {
/* 149 */       if (r.hb.hovered) {
/* 150 */         anyHovered = true;
/* 151 */         category = RelicCategory.STARTER;
/*     */         break;
/*     */       } 
/* 154 */       index++;
/*     */     } 
/*     */     
/* 157 */     if (!anyHovered) {
/* 158 */       index = 0;
/* 159 */       for (AbstractRelic r : RelicLibrary.commonList) {
/* 160 */         if (r.hb.hovered) {
/* 161 */           anyHovered = true;
/* 162 */           category = RelicCategory.COMMON;
/*     */           break;
/*     */         } 
/* 165 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     if (!anyHovered) {
/* 170 */       index = 0;
/* 171 */       for (AbstractRelic r : RelicLibrary.uncommonList) {
/* 172 */         if (r.hb.hovered) {
/* 173 */           anyHovered = true;
/* 174 */           category = RelicCategory.UNCOMMON;
/*     */           break;
/*     */         } 
/* 177 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 181 */     if (!anyHovered) {
/* 182 */       index = 0;
/* 183 */       for (AbstractRelic r : RelicLibrary.rareList) {
/* 184 */         if (r.hb.hovered) {
/* 185 */           anyHovered = true;
/* 186 */           category = RelicCategory.RARE;
/*     */           break;
/*     */         } 
/* 189 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 193 */     if (!anyHovered) {
/* 194 */       index = 0;
/* 195 */       for (AbstractRelic r : RelicLibrary.bossList) {
/* 196 */         if (r.hb.hovered) {
/* 197 */           anyHovered = true;
/* 198 */           category = RelicCategory.BOSS;
/*     */           break;
/*     */         } 
/* 201 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 205 */     if (!anyHovered) {
/* 206 */       index = 0;
/* 207 */       for (AbstractRelic r : RelicLibrary.specialList) {
/* 208 */         if (r.hb.hovered) {
/* 209 */           anyHovered = true;
/* 210 */           category = RelicCategory.EVENT;
/*     */           break;
/*     */         } 
/* 213 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 217 */     if (!anyHovered) {
/* 218 */       index = 0;
/* 219 */       for (AbstractRelic r : RelicLibrary.shopList) {
/* 220 */         if (r.hb.hovered) {
/* 221 */           anyHovered = true;
/* 222 */           category = RelicCategory.SHOP;
/*     */           break;
/*     */         } 
/* 225 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     if (!anyHovered) {
/* 230 */       Gdx.input.setCursorPosition(
/* 231 */           (int)((AbstractRelic)RelicLibrary.starterList.get(0)).hb.cX, Settings.HEIGHT - 
/* 232 */           (int)((AbstractRelic)RelicLibrary.starterList.get(0)).hb.cY);
/*     */     } else {
/* 234 */       switch (category) {
/*     */         case STARTER:
/* 236 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed())
/* 237 */             break;  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 238 */             index += 10;
/* 239 */             if (index > RelicLibrary.starterList.size() - 1) {
/* 240 */               index %= 10;
/* 241 */               if (index > RelicLibrary.commonList.size() - 1) {
/* 242 */                 index = RelicLibrary.commonList.size() - 1;
/*     */               }
/* 244 */               Gdx.input.setCursorPosition(
/* 245 */                   (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 246 */                   (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cY);
/* 247 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.commonList.get(index)).hb;
/*     */             }  break;
/* 249 */           }  if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 250 */             index--;
/* 251 */             if (index < 0) {
/* 252 */               index = RelicLibrary.starterList.size() - 1;
/*     */             }
/* 254 */             Gdx.input.setCursorPosition(
/* 255 */                 (int)((AbstractRelic)RelicLibrary.starterList.get(index)).hb.cX, Settings.HEIGHT - 
/* 256 */                 (int)((AbstractRelic)RelicLibrary.starterList.get(index)).hb.cY);
/* 257 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.starterList.get(index)).hb; break;
/* 258 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 259 */             index++;
/* 260 */             if (index > RelicLibrary.starterList.size() - 1) {
/* 261 */               index = 0;
/*     */             }
/* 263 */             Gdx.input.setCursorPosition(
/* 264 */                 (int)((AbstractRelic)RelicLibrary.starterList.get(index)).hb.cX, Settings.HEIGHT - 
/* 265 */                 (int)((AbstractRelic)RelicLibrary.starterList.get(index)).hb.cY);
/* 266 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.starterList.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case COMMON:
/* 270 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 271 */             index -= 10;
/* 272 */             if (index < 0) {
/* 273 */               index = RelicLibrary.starterList.size() - 1;
/* 274 */               Gdx.input.setCursorPosition(
/* 275 */                   (int)((AbstractRelic)RelicLibrary.starterList.get(index)).hb.cX, Settings.HEIGHT - 
/* 276 */                   (int)((AbstractRelic)RelicLibrary.starterList.get(index)).hb.cY);
/* 277 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.starterList.get(index)).hb; break;
/*     */             } 
/* 279 */             Gdx.input.setCursorPosition(
/* 280 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 281 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cY);
/* 282 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.commonList.get(index)).hb; break;
/*     */           } 
/* 284 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 285 */             index += 10;
/* 286 */             if (index > RelicLibrary.commonList.size() - 1) {
/* 287 */               index %= 10;
/* 288 */               if (index > RelicLibrary.uncommonList.size() - 1) {
/* 289 */                 index = RelicLibrary.uncommonList.size() - 1;
/*     */               }
/* 291 */               Gdx.input.setCursorPosition(
/* 292 */                   (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 293 */                   (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cY);
/* 294 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb; break;
/*     */             } 
/* 296 */             Gdx.input.setCursorPosition(
/* 297 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 298 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cY);
/* 299 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.commonList.get(index)).hb; break;
/*     */           } 
/* 301 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 302 */             index--;
/* 303 */             if (index < 0) {
/* 304 */               index = RelicLibrary.commonList.size() - 1;
/*     */             }
/* 306 */             Gdx.input.setCursorPosition(
/* 307 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 308 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cY);
/* 309 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.commonList.get(index)).hb; break;
/* 310 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 311 */             index++;
/* 312 */             if (index > RelicLibrary.commonList.size() - 1) {
/* 313 */               index = 0;
/*     */             }
/* 315 */             Gdx.input.setCursorPosition(
/* 316 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 317 */                 (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cY);
/* 318 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.commonList.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case UNCOMMON:
/* 322 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 323 */             index -= 10;
/* 324 */             if (index < 0) {
/* 325 */               index = RelicLibrary.commonList.size() - 1;
/* 326 */               Gdx.input.setCursorPosition(
/* 327 */                   (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 328 */                   (int)((AbstractRelic)RelicLibrary.commonList.get(index)).hb.cY);
/* 329 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.commonList.get(index)).hb; break;
/*     */             } 
/* 331 */             Gdx.input.setCursorPosition(
/* 332 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 333 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cY);
/* 334 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb; break;
/*     */           } 
/* 336 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 337 */             index += 10;
/* 338 */             if (index > RelicLibrary.uncommonList.size() - 1) {
/* 339 */               index %= 10;
/* 340 */               if (index > RelicLibrary.rareList.size() - 1) {
/* 341 */                 index = RelicLibrary.rareList.size() - 1;
/*     */               }
/* 343 */               Gdx.input.setCursorPosition(
/* 344 */                   (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cX, Settings.HEIGHT - 
/* 345 */                   (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cY);
/* 346 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.rareList.get(index)).hb; break;
/*     */             } 
/* 348 */             Gdx.input.setCursorPosition(
/* 349 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 350 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cY);
/* 351 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb; break;
/*     */           } 
/* 353 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 354 */             index--;
/* 355 */             if (index < 0) {
/* 356 */               index = RelicLibrary.uncommonList.size() - 1;
/*     */             }
/* 358 */             Gdx.input.setCursorPosition(
/* 359 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 360 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cY);
/* 361 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb; break;
/* 362 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 363 */             index++;
/* 364 */             if (index > RelicLibrary.uncommonList.size() - 1) {
/* 365 */               index = 0;
/*     */             }
/* 367 */             Gdx.input.setCursorPosition(
/* 368 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 369 */                 (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cY);
/* 370 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case RARE:
/* 374 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 375 */             index -= 10;
/* 376 */             if (index < 0) {
/* 377 */               index = RelicLibrary.uncommonList.size() - 1;
/* 378 */               Gdx.input.setCursorPosition(
/* 379 */                   (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cX, Settings.HEIGHT - 
/* 380 */                   (int)((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb.cY);
/* 381 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.uncommonList.get(index)).hb; break;
/*     */             } 
/* 383 */             Gdx.input.setCursorPosition(
/* 384 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cX, Settings.HEIGHT - 
/* 385 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cY);
/* 386 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.rareList.get(index)).hb; break;
/*     */           } 
/* 388 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 389 */             index += 10;
/* 390 */             if (index > RelicLibrary.rareList.size() - 1) {
/* 391 */               index %= 10;
/* 392 */               if (index > RelicLibrary.bossList.size() - 1) {
/* 393 */                 index = RelicLibrary.bossList.size() - 1;
/*     */               }
/* 395 */               Gdx.input.setCursorPosition(
/* 396 */                   (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cX, Settings.HEIGHT - 
/* 397 */                   (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cY);
/* 398 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.bossList.get(index)).hb; break;
/*     */             } 
/* 400 */             Gdx.input.setCursorPosition(
/* 401 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cX, Settings.HEIGHT - 
/* 402 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cY);
/* 403 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.rareList.get(index)).hb; break;
/*     */           } 
/* 405 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 406 */             index--;
/* 407 */             if (index < 0) {
/* 408 */               index = RelicLibrary.rareList.size() - 1;
/*     */             }
/* 410 */             Gdx.input.setCursorPosition(
/* 411 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cX, Settings.HEIGHT - 
/* 412 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cY);
/* 413 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.rareList.get(index)).hb; break;
/* 414 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 415 */             index++;
/* 416 */             if (index > RelicLibrary.rareList.size() - 1) {
/* 417 */               index = 0;
/*     */             }
/* 419 */             Gdx.input.setCursorPosition(
/* 420 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cX, Settings.HEIGHT - 
/* 421 */                 (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cY);
/* 422 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.rareList.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case BOSS:
/* 426 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 427 */             index -= 10;
/* 428 */             if (index < 0) {
/* 429 */               index = RelicLibrary.rareList.size() - 1;
/* 430 */               Gdx.input.setCursorPosition(
/* 431 */                   (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cX, Settings.HEIGHT - 
/* 432 */                   (int)((AbstractRelic)RelicLibrary.rareList.get(index)).hb.cY);
/* 433 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.rareList.get(index)).hb; break;
/*     */             } 
/* 435 */             Gdx.input.setCursorPosition(
/* 436 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cX, Settings.HEIGHT - 
/* 437 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cY);
/* 438 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.bossList.get(index)).hb; break;
/*     */           } 
/* 440 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 441 */             index += 10;
/* 442 */             if (index > RelicLibrary.bossList.size() - 1) {
/* 443 */               index %= 10;
/* 444 */               if (index > RelicLibrary.specialList.size() - 1) {
/* 445 */                 index = RelicLibrary.specialList.size() - 1;
/*     */               }
/* 447 */               Gdx.input.setCursorPosition(
/* 448 */                   (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cX, Settings.HEIGHT - 
/* 449 */                   (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cY);
/* 450 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.specialList.get(index)).hb; break;
/*     */             } 
/* 452 */             Gdx.input.setCursorPosition(
/* 453 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cX, Settings.HEIGHT - 
/* 454 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cY);
/* 455 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.bossList.get(index)).hb; break;
/*     */           } 
/* 457 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 458 */             index--;
/* 459 */             if (index < 0) {
/* 460 */               index = RelicLibrary.bossList.size() - 1;
/*     */             }
/* 462 */             Gdx.input.setCursorPosition(
/* 463 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cX, Settings.HEIGHT - 
/* 464 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cY);
/* 465 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.bossList.get(index)).hb; break;
/* 466 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 467 */             index++;
/* 468 */             if (index > RelicLibrary.bossList.size() - 1) {
/* 469 */               index = 0;
/*     */             }
/* 471 */             Gdx.input.setCursorPosition(
/* 472 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cX, Settings.HEIGHT - 
/* 473 */                 (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cY);
/* 474 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.bossList.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case EVENT:
/* 478 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 479 */             index -= 10;
/* 480 */             if (index < 0) {
/* 481 */               index = RelicLibrary.bossList.size() - 1;
/* 482 */               Gdx.input.setCursorPosition(
/* 483 */                   (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cX, Settings.HEIGHT - 
/* 484 */                   (int)((AbstractRelic)RelicLibrary.bossList.get(index)).hb.cY);
/* 485 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.bossList.get(index)).hb; break;
/*     */             } 
/* 487 */             Gdx.input.setCursorPosition(
/* 488 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cX, Settings.HEIGHT - 
/* 489 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cY);
/* 490 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.specialList.get(index)).hb; break;
/*     */           } 
/* 492 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 493 */             index += 10;
/* 494 */             if (index > RelicLibrary.specialList.size() - 1) {
/* 495 */               index %= 10;
/* 496 */               if (index > RelicLibrary.shopList.size() - 1) {
/* 497 */                 index = RelicLibrary.shopList.size() - 1;
/*     */               }
/* 499 */               Gdx.input.setCursorPosition(
/* 500 */                   (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cX, Settings.HEIGHT - 
/* 501 */                   (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cY);
/* 502 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.shopList.get(index)).hb; break;
/*     */             } 
/* 504 */             Gdx.input.setCursorPosition(
/* 505 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cX, Settings.HEIGHT - 
/* 506 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cY);
/* 507 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.specialList.get(index)).hb; break;
/*     */           } 
/* 509 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 510 */             index--;
/* 511 */             if (index < 0) {
/* 512 */               index = RelicLibrary.specialList.size() - 1;
/*     */             }
/* 514 */             Gdx.input.setCursorPosition(
/* 515 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cX, Settings.HEIGHT - 
/* 516 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cY);
/* 517 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.specialList.get(index)).hb; break;
/* 518 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 519 */             index++;
/* 520 */             if (index > RelicLibrary.specialList.size() - 1) {
/* 521 */               index = 0;
/*     */             }
/* 523 */             Gdx.input.setCursorPosition(
/* 524 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cX, Settings.HEIGHT - 
/* 525 */                 (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cY);
/* 526 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.specialList.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         case SHOP:
/* 530 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 531 */             index -= 10;
/* 532 */             if (index < 0) {
/* 533 */               index = RelicLibrary.specialList.size() - 1;
/* 534 */               Gdx.input.setCursorPosition(
/* 535 */                   (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cX, Settings.HEIGHT - 
/* 536 */                   (int)((AbstractRelic)RelicLibrary.specialList.get(index)).hb.cY);
/* 537 */               this.controllerRelicHb = ((AbstractRelic)RelicLibrary.specialList.get(index)).hb; break;
/*     */             } 
/* 539 */             if (index > RelicLibrary.shopList.size() - 1) {
/* 540 */               index = RelicLibrary.shopList.size() - 1;
/*     */             }
/* 542 */             Gdx.input.setCursorPosition(
/* 543 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cX, Settings.HEIGHT - 
/* 544 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cY);
/* 545 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.shopList.get(index)).hb; break;
/*     */           } 
/* 547 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 548 */             index += 10;
/* 549 */             if (index > RelicLibrary.shopList.size() - 1) {
/*     */               break;
/*     */             }
/* 552 */             Gdx.input.setCursorPosition(
/* 553 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cX, Settings.HEIGHT - 
/* 554 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cY);
/* 555 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.shopList.get(index)).hb; break;
/*     */           } 
/* 557 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 558 */             index--;
/* 559 */             if (index < 0) {
/* 560 */               index = RelicLibrary.shopList.size() - 1;
/*     */             }
/* 562 */             Gdx.input.setCursorPosition(
/* 563 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cX, Settings.HEIGHT - 
/* 564 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cY);
/* 565 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.shopList.get(index)).hb; break;
/* 566 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 567 */             index++;
/* 568 */             if (index > RelicLibrary.shopList.size() - 1) {
/* 569 */               index = 0;
/*     */             }
/* 571 */             Gdx.input.setCursorPosition(
/* 572 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cX, Settings.HEIGHT - 
/* 573 */                 (int)((AbstractRelic)RelicLibrary.shopList.get(index)).hb.cY);
/* 574 */             this.controllerRelicHb = ((AbstractRelic)RelicLibrary.shopList.get(index)).hb;
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
/* 585 */     if (!CardCrawlGame.relicPopup.isOpen) {
/* 586 */       int y = InputHelper.mY;
/*     */       
/* 588 */       if (!this.grabbedScreen) {
/* 589 */         if (InputHelper.scrolledDown) {
/* 590 */           this.targetY += Settings.SCROLL_SPEED;
/* 591 */         } else if (InputHelper.scrolledUp) {
/* 592 */           this.targetY -= Settings.SCROLL_SPEED;
/*     */         } 
/*     */         
/* 595 */         if (InputHelper.justClickedLeft) {
/* 596 */           this.grabbedScreen = true;
/* 597 */           this.grabStartY = y - this.targetY;
/*     */         }
/*     */       
/* 600 */       } else if (InputHelper.isMouseDown) {
/* 601 */         this.targetY = y - this.grabStartY;
/*     */       } else {
/* 603 */         this.grabbedScreen = false;
/*     */       } 
/*     */ 
/*     */       
/* 607 */       this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
/* 608 */       resetScrolling();
/* 609 */       updateBarPosition();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 617 */     if (this.targetY < this.scrollLowerBound) {
/* 618 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
/* 619 */     } else if (this.targetY > this.scrollUpperBound) {
/* 620 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateList(ArrayList<AbstractRelic> list) {
/* 625 */     if (!CardCrawlGame.relicPopup.isOpen) {
/* 626 */       for (AbstractRelic r : list) {
/* 627 */         r.hb.move(r.currentX, r.currentY);
/* 628 */         r.update();
/*     */         
/* 630 */         if (r.hb.hovered) {
/*     */           
/* 632 */           this.hoveredRelic = r;
/* 633 */           this.relicGroup = list;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 640 */     this.row = -1;
/* 641 */     this.col = 0;
/* 642 */     renderList(sb, TEXT[1], TEXT[2], RelicLibrary.starterList);
/* 643 */     renderList(sb, TEXT[3], TEXT[4], RelicLibrary.commonList);
/* 644 */     renderList(sb, TEXT[5], TEXT[6], RelicLibrary.uncommonList);
/* 645 */     renderList(sb, TEXT[7], TEXT[8], RelicLibrary.rareList);
/* 646 */     renderList(sb, TEXT[9], TEXT[10], RelicLibrary.bossList);
/* 647 */     renderList(sb, TEXT[11], TEXT[12], RelicLibrary.specialList);
/* 648 */     renderList(sb, TEXT[13], TEXT[14], RelicLibrary.shopList);
/*     */     
/* 650 */     this.button.render(sb);
/* 651 */     this.scrollBar.render(sb);
/*     */   }
/*     */   
/*     */   private void renderList(SpriteBatch sb, String msg, String desc, ArrayList<AbstractRelic> list) {
/* 655 */     this.row += 2;
/* 656 */     FontHelper.renderSmartText(sb, FontHelper.buttonLabelFont, msg, START_X - 50.0F * Settings.scale, this.scrollY + 4.0F * Settings.scale - SPACE * this.row, 99999.0F, 0.0F, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 665 */     FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, desc, START_X - 50.0F * Settings.scale + 
/*     */ 
/*     */ 
/*     */         
/* 669 */         FontHelper.getSmartWidth(FontHelper.buttonLabelFont, msg, 99999.0F, 0.0F), this.scrollY - 0.0F * Settings.scale - SPACE * this.row, 99999.0F, 0.0F, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 675 */     this.row++;
/* 676 */     this.col = 0;
/* 677 */     for (AbstractRelic r : list) {
/* 678 */       if (this.col == 10) {
/* 679 */         this.col = 0;
/* 680 */         this.row++;
/*     */       } 
/* 682 */       r.currentX = START_X + SPACE * this.col;
/* 683 */       r.currentY = this.scrollY - SPACE * this.row;
/* 684 */       if (RelicLibrary.redList.contains(r)) {
/* 685 */         if (UnlockTracker.isRelicLocked(r.relicId)) {
/* 686 */           r.renderLock(sb, Settings.RED_RELIC_COLOR);
/*     */         } else {
/* 688 */           r.render(sb, false, Settings.RED_RELIC_COLOR);
/*     */         } 
/* 690 */       } else if (RelicLibrary.greenList.contains(r)) {
/* 691 */         if (UnlockTracker.isRelicLocked(r.relicId)) {
/* 692 */           r.renderLock(sb, Settings.GREEN_RELIC_COLOR);
/*     */         } else {
/* 694 */           r.render(sb, false, Settings.GREEN_RELIC_COLOR);
/*     */         } 
/* 696 */       } else if (RelicLibrary.blueList.contains(r)) {
/* 697 */         if (UnlockTracker.isRelicLocked(r.relicId)) {
/* 698 */           r.renderLock(sb, Settings.BLUE_RELIC_COLOR);
/*     */         } else {
/* 700 */           r.render(sb, false, Settings.BLUE_RELIC_COLOR);
/*     */         } 
/* 702 */       } else if (RelicLibrary.whiteList.contains(r)) {
/* 703 */         if (UnlockTracker.isRelicLocked(r.relicId)) {
/* 704 */           r.renderLock(sb, Settings.PURPLE_RELIC_COLOR);
/*     */         } else {
/* 706 */           r.render(sb, false, Settings.PURPLE_RELIC_COLOR);
/*     */         }
/*     */       
/* 709 */       } else if (UnlockTracker.isRelicLocked(r.relicId)) {
/* 710 */         r.renderLock(sb, Settings.TWO_THIRDS_TRANSPARENT_BLACK_COLOR);
/*     */       } else {
/* 712 */         r.render(sb, false, Settings.TWO_THIRDS_TRANSPARENT_BLACK_COLOR);
/*     */       } 
/*     */       
/* 715 */       this.col++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 721 */     float newPosition = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 722 */     this.scrollY = newPosition;
/* 723 */     this.targetY = newPosition;
/* 724 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 728 */     if (!CardCrawlGame.relicPopup.isOpen) {
/* 729 */       float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
/* 730 */       this.scrollBar.parentScrolledToPercent(percent);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\compendium\RelicViewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */