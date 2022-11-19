/*     */ package com.megacrit.cardcrawl.screens.select;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
import com.megacrit.cardcrawl.ui.buttons.GridSelectConfirmButton;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GridCardSelectScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  36 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("GridCardSelectScreen");
/*  37 */   public static final String[] TEXT = uiStrings.TEXT; private static float drawStartX; private static float drawStartY;
/*     */   private static float padX;
/*     */   private static float padY;
/*     */   private static final int CARDS_PER_LINE = 5;
/*  41 */   private static final float SCROLL_BAR_THRESHOLD = 500.0F * Settings.scale;
/*  42 */   private float grabStartY = 0.0F; private float currentDiffY = 0.0F;
/*  43 */   public ArrayList<AbstractCard> selectedCards = new ArrayList<>();
/*     */   public CardGroup targetGroup;
/*  45 */   private AbstractCard hoveredCard = null;
/*  46 */   public AbstractCard upgradePreviewCard = null;
/*  47 */   private int numCards = 0; private int cardSelectAmount = 0;
/*  48 */   private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
/*  49 */   private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*     */   private boolean grabbedScreen = false;
/*     */   private boolean canCancel = true;
/*     */   public boolean forUpgrade = false;
/*  53 */   public GridSelectConfirmButton confirmButton = new GridSelectConfirmButton(TEXT[0]); public boolean forTransform = false; public boolean forPurge = false; public boolean confirmScreenUp = false; public boolean isJustForConfirming = false;
/*  54 */   public PeekButton peekButton = new PeekButton();
/*  55 */   private String tipMsg = "";
/*  56 */   private String lastTip = "";
/*  57 */   private float ritualAnimTimer = 0.0F;
/*     */   private static final float RITUAL_ANIM_INTERVAL = 0.1F;
/*  59 */   private int prevDeckSize = 0; public boolean cancelWasOn = false; public boolean anyNumber = false;
/*     */   public boolean forClarity = false;
/*     */   public String cancelText;
/*     */   private ScrollBar scrollBar;
/*  63 */   private AbstractCard controllerCard = null;
/*     */   
/*     */   private static final int ARROW_W = 64;
/*     */   
/*  67 */   private float arrowScale1 = 1.0F, arrowScale2 = 1.0F, arrowScale3 = 1.0F, arrowTimer = 0.0F;
/*     */   
/*     */   public GridCardSelectScreen() {
/*  70 */     drawStartX = Settings.WIDTH;
/*  71 */     drawStartX -= 5.0F * AbstractCard.IMG_WIDTH * 0.75F;
/*  72 */     drawStartX -= 4.0F * Settings.CARD_VIEW_PAD_X;
/*  73 */     drawStartX /= 2.0F;
/*  74 */     drawStartX += AbstractCard.IMG_WIDTH * 0.75F / 2.0F;
/*     */     
/*  76 */     padX = AbstractCard.IMG_WIDTH * 0.75F + Settings.CARD_VIEW_PAD_X;
/*  77 */     padY = AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y;
/*  78 */     this.scrollBar = new ScrollBar(this);
/*  79 */     this.scrollBar.move(0.0F, -30.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  83 */     updateControllerInput();
/*  84 */     updatePeekButton();
/*     */     
/*  86 */     if (PeekButton.isPeeking) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     if (Settings.isControllerMode && this.controllerCard != null && !CardCrawlGame.isPopupOpen && this.upgradePreviewCard == null)
/*     */     {
/*  92 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.75F) {
/*  93 */         this.currentDiffY += Settings.SCROLL_SPEED;
/*  94 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.25F) {
/*  95 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */     }
/*     */     
/*  99 */     boolean isDraggingScrollBar = false;
/* 100 */     if (shouldShowScrollBar()) {
/* 101 */       isDraggingScrollBar = this.scrollBar.update();
/*     */     }
/* 103 */     if (!isDraggingScrollBar) {
/* 104 */       updateScrolling();
/*     */     }
/*     */     
/* 107 */     if (this.forClarity) {
/* 108 */       if (this.selectedCards.size() > 0) {
/* 109 */         this.confirmButton.isDisabled = false;
/*     */       } else {
/* 111 */         this.confirmButton.isDisabled = true;
/*     */       } 
/*     */     }
/*     */     
/* 115 */     this.confirmButton.update();
/*     */ 
/*     */     
/* 118 */     if (this.isJustForConfirming) {
/* 119 */       updateCardPositionsAndHoverLogic();
/* 120 */       if (this.confirmButton.hb.clicked || CInputActionSet.topPanel.isJustPressed()) {
/* 121 */         CInputActionSet.select.unpress();
/* 122 */         this.confirmButton.hb.clicked = false;
/* 123 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/* 124 */         AbstractDungeon.dynamicBanner.hide();
/* 125 */         this.confirmScreenUp = false;
/* 126 */         for (AbstractCard c : this.targetGroup.group) {
/* 127 */           AbstractDungeon.topLevelEffects.add(new FastCardObtainEffect(c, c.current_x, c.current_y));
/*     */         }
/* 129 */         AbstractDungeon.closeCurrentScreen();
/*     */       }  return;
/*     */     } 
/* 132 */     if ((this.anyNumber || this.forClarity) && 
/* 133 */       this.confirmButton.hb.clicked) {
/* 134 */       this.confirmButton.hb.clicked = false;
/* 135 */       AbstractDungeon.closeCurrentScreen();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 140 */     if (!this.confirmScreenUp) {
/* 141 */       updateCardPositionsAndHoverLogic();
/*     */       
/* 143 */       if (this.hoveredCard != null && InputHelper.justClickedLeft) {
/* 144 */         this.hoveredCard.hb.clickStarted = true;
/*     */       }
/*     */       
/* 147 */       if (this.hoveredCard != null && (this.hoveredCard.hb.clicked || CInputActionSet.select.isJustPressed())) {
/* 148 */         this.hoveredCard.hb.clicked = false;
/* 149 */         if (!this.selectedCards.contains(this.hoveredCard)) {
/* 150 */           if (this.forClarity && this.selectedCards.size() > 0) {
/* 151 */             ((AbstractCard)this.selectedCards.get(0)).stopGlowing();
/* 152 */             this.selectedCards.clear();
/* 153 */             this.cardSelectAmount--;
/*     */           } 
/* 155 */           this.selectedCards.add(this.hoveredCard);
/* 156 */           this.hoveredCard.beginGlowing();
/* 157 */           this.hoveredCard.targetDrawScale = 0.75F;
/* 158 */           this.hoveredCard.drawScale = 0.875F;
/*     */           
/* 160 */           this.cardSelectAmount++;
/* 161 */           CardCrawlGame.sound.play("CARD_SELECT");
/*     */           
/* 163 */           if (this.numCards == this.cardSelectAmount) {
/* 164 */             if (this.forUpgrade) {
/*     */               
/* 166 */               this.hoveredCard.untip();
/* 167 */               this.confirmScreenUp = true;
/* 168 */               this.upgradePreviewCard = this.hoveredCard.makeStatEquivalentCopy();
/* 169 */               this.upgradePreviewCard.upgrade();
/* 170 */               this.upgradePreviewCard.displayUpgrades();
/* 171 */               this.upgradePreviewCard.drawScale = 0.875F;
/* 172 */               this.hoveredCard.stopGlowing();
/* 173 */               this.selectedCards.clear();
/* 174 */               AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/* 175 */               this.confirmButton.show();
/* 176 */               this.confirmButton.isDisabled = false;
/* 177 */               this.lastTip = this.tipMsg;
/* 178 */               this.tipMsg = TEXT[2]; return;
/*     */             } 
/* 180 */             if (this.forTransform) {
/*     */               
/* 182 */               this.hoveredCard.untip();
/* 183 */               this.confirmScreenUp = true;
/* 184 */               this.upgradePreviewCard = this.hoveredCard.makeStatEquivalentCopy();
/* 185 */               this.upgradePreviewCard.drawScale = 0.875F;
/* 186 */               this.hoveredCard.stopGlowing();
/* 187 */               this.selectedCards.clear();
/* 188 */               AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/* 189 */               this.confirmButton.show();
/* 190 */               this.confirmButton.isDisabled = false;
/* 191 */               this.lastTip = this.tipMsg;
/* 192 */               this.tipMsg = TEXT[2]; return;
/*     */             } 
/* 194 */             if (this.forPurge) {
/* 195 */               if (this.numCards == 1) {
/* 196 */                 this.hoveredCard.untip();
/* 197 */                 this.hoveredCard.stopGlowing();
/* 198 */                 this.confirmScreenUp = true;
/* 199 */                 this.hoveredCard.current_x = Settings.WIDTH / 2.0F;
/* 200 */                 this.hoveredCard.target_x = Settings.WIDTH / 2.0F;
/* 201 */                 this.hoveredCard.current_y = Settings.HEIGHT / 2.0F;
/* 202 */                 this.hoveredCard.target_y = Settings.HEIGHT / 2.0F;
/* 203 */                 this.hoveredCard.update();
/* 204 */                 this.hoveredCard.targetDrawScale = 1.0F;
/* 205 */                 this.hoveredCard.drawScale = 1.0F;
/* 206 */                 this.selectedCards.clear();
/* 207 */                 this.confirmButton.show();
/* 208 */                 this.confirmButton.isDisabled = false;
/* 209 */                 this.lastTip = this.tipMsg;
/* 210 */                 this.tipMsg = TEXT[2];
/* 211 */                 AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/*     */               } else {
/* 213 */                 AbstractDungeon.closeCurrentScreen();
/*     */               } 
/* 215 */               for (AbstractCard c : this.selectedCards)
/* 216 */                 c.stopGlowing(); 
/*     */               return;
/*     */             } 
/* 219 */             if (!this.anyNumber) {
/*     */               
/* 221 */               AbstractDungeon.closeCurrentScreen();
/* 222 */               if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SHOP) {
/* 223 */                 AbstractDungeon.overlayMenu.cancelButton.hide();
/*     */               } else {
/* 225 */                 AbstractDungeon.overlayMenu.cancelButton.show(TEXT[3]);
/*     */               } 
/*     */               
/* 228 */               for (AbstractCard c : this.selectedCards) {
/* 229 */                 c.stopGlowing();
/*     */               }
/*     */               
/* 232 */               if (this.targetGroup.type == CardGroup.CardGroupType.DISCARD_PILE)
/* 233 */                 for (AbstractCard c : this.targetGroup.group) {
/* 234 */                   c.drawScale = 0.12F;
/* 235 */                   c.targetDrawScale = 0.12F;
/* 236 */                   c.teleportToDiscardPile();
/* 237 */                   c.lighten(true);
/*     */                 }  
/*     */               return;
/*     */             } 
/* 241 */             if (this.cardSelectAmount < this.targetGroup.size() && this.anyNumber) {
/* 242 */               AbstractDungeon.closeCurrentScreen();
/* 243 */               if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SHOP) {
/* 244 */                 AbstractDungeon.overlayMenu.cancelButton.hide();
/*     */               } else {
/* 246 */                 AbstractDungeon.overlayMenu.cancelButton.show(TEXT[3]);
/*     */               } 
/*     */               
/* 249 */               for (AbstractCard c : this.selectedCards) {
/* 250 */                 c.stopGlowing();
/*     */               }
/*     */               
/* 253 */               if (this.targetGroup.type == CardGroup.CardGroupType.DISCARD_PILE) {
/* 254 */                 for (AbstractCard c : this.targetGroup.group) {
/* 255 */                   c.drawScale = 0.12F;
/* 256 */                   c.targetDrawScale = 0.12F;
/* 257 */                   c.teleportToDiscardPile();
/* 258 */                   c.lighten(true);
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/* 263 */         } else if (this.selectedCards.contains(this.hoveredCard)) {
/* 264 */           this.hoveredCard.stopGlowing();
/* 265 */           this.selectedCards.remove(this.hoveredCard);
/* 266 */           this.cardSelectAmount--;
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } else {
/* 272 */       if (this.forTransform) {
/* 273 */         this.ritualAnimTimer -= Gdx.graphics.getDeltaTime();
/* 274 */         if (this.ritualAnimTimer < 0.0F) {
/* 275 */           this
/* 276 */             .upgradePreviewCard = AbstractDungeon.returnTrulyRandomCardFromAvailable(this.upgradePreviewCard).makeCopy();
/* 277 */           this.ritualAnimTimer = 0.1F;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 282 */       if (this.forUpgrade) {
/* 283 */         this.upgradePreviewCard.update();
/*     */       }
/* 285 */       if (!this.forPurge) {
/* 286 */         this.upgradePreviewCard.drawScale = 1.0F;
/* 287 */         this.hoveredCard.update();
/* 288 */         this.hoveredCard.drawScale = 1.0F;
/*     */       } 
/*     */       
/* 291 */       if (this.confirmButton.hb.clicked || CInputActionSet.topPanel.isJustPressed()) {
/* 292 */         CInputActionSet.select.unpress();
/* 293 */         this.confirmButton.hb.clicked = false;
/* 294 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/* 295 */         this.confirmScreenUp = false;
/* 296 */         this.selectedCards.add(this.hoveredCard);
/* 297 */         AbstractDungeon.closeCurrentScreen();
/*     */       } 
/*     */     } 
/*     */     
/* 301 */     if (Settings.isControllerMode) {
/* 302 */       if (this.upgradePreviewCard != null) {
/* 303 */         CInputHelper.setCursor(this.upgradePreviewCard.hb);
/* 304 */       } else if (this.controllerCard != null) {
/* 305 */         CInputHelper.setCursor(this.controllerCard.hb);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void updatePeekButton() {
/* 311 */     this.peekButton.update();
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 315 */     if (!Settings.isControllerMode || this.upgradePreviewCard != null) {
/*     */       return;
/*     */     }
/*     */     
/* 319 */     boolean anyHovered = false;
/* 320 */     int index = 0;
/*     */     
/* 322 */     for (AbstractCard c : this.targetGroup.group) {
/* 323 */       if (c.hb.hovered) {
/* 324 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 327 */       index++;
/*     */     } 
/*     */     
/* 330 */     if (!anyHovered && this.controllerCard == null) {
/* 331 */       CInputHelper.setCursor(((AbstractCard)this.targetGroup.group.get(0)).hb);
/* 332 */       this.controllerCard = this.targetGroup.group.get(0);
/*     */     }
/* 334 */     else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && this.targetGroup
/* 335 */       .size() > 5) {
/*     */       
/* 337 */       if (index < 5) {
/* 338 */         index = this.targetGroup.size() + 2 - 4 - index;
/* 339 */         if (index > this.targetGroup.size() - 1) {
/* 340 */           index -= 5;
/*     */         }
/* 342 */         if (index > this.targetGroup.size() - 1 || index < 0) {
/* 343 */           index = 0;
/*     */         }
/*     */       } else {
/*     */         
/* 347 */         index -= 5;
/*     */       } 
/* 349 */       CInputHelper.setCursor(((AbstractCard)this.targetGroup.group.get(index)).hb);
/* 350 */       this.controllerCard = this.targetGroup.group.get(index);
/* 351 */     } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.targetGroup
/* 352 */       .size() > 5) {
/* 353 */       if (index < this.targetGroup.size() - 5) {
/* 354 */         index += 5;
/*     */       } else {
/* 356 */         index %= 5;
/*     */       } 
/* 358 */       CInputHelper.setCursor(((AbstractCard)this.targetGroup.group.get(index)).hb);
/* 359 */       this.controllerCard = this.targetGroup.group.get(index);
/* 360 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 361 */       if (index % 5 > 0) {
/* 362 */         index--;
/*     */       } else {
/* 364 */         index += 4;
/* 365 */         if (index > this.targetGroup.size() - 1) {
/* 366 */           index = this.targetGroup.size() - 1;
/*     */         }
/*     */       } 
/* 369 */       CInputHelper.setCursor(((AbstractCard)this.targetGroup.group.get(index)).hb);
/* 370 */       this.controllerCard = this.targetGroup.group.get(index);
/* 371 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 372 */       if (index % 5 < 4) {
/* 373 */         index++;
/* 374 */         if (index > this.targetGroup.size() - 1) {
/* 375 */           index -= this.targetGroup.size() % 5;
/*     */         }
/*     */       } else {
/* 378 */         index -= 4;
/* 379 */         if (index < 0) {
/* 380 */           index = 0;
/*     */         }
/*     */       } 
/*     */       
/* 384 */       if (index > this.targetGroup.group.size() - 1) {
/* 385 */         index = 0;
/*     */       }
/* 387 */       CInputHelper.setCursor(((AbstractCard)this.targetGroup.group.get(index)).hb);
/* 388 */       this.controllerCard = this.targetGroup.group.get(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateCardPositionsAndHoverLogic() {
/* 395 */     if (this.isJustForConfirming && this.targetGroup.size() <= 4) {
/* 396 */       switch (this.targetGroup.size()) {
/*     */         case 1:
/* 398 */           (this.targetGroup.getBottomCard()).current_x = Settings.WIDTH / 2.0F;
/* 399 */           (this.targetGroup.getBottomCard()).target_x = Settings.WIDTH / 2.0F;
/*     */           break;
/*     */         case 2:
/* 402 */           ((AbstractCard)this.targetGroup.group.get(0)).current_x = Settings.WIDTH / 2.0F - padX / 2.0F;
/* 403 */           ((AbstractCard)this.targetGroup.group.get(0)).target_x = Settings.WIDTH / 2.0F - padX / 2.0F;
/* 404 */           ((AbstractCard)this.targetGroup.group.get(1)).current_x = Settings.WIDTH / 2.0F + padX / 2.0F;
/* 405 */           ((AbstractCard)this.targetGroup.group.get(1)).target_x = Settings.WIDTH / 2.0F + padX / 2.0F;
/*     */           break;
/*     */         case 3:
/* 408 */           ((AbstractCard)this.targetGroup.group.get(0)).current_x = drawStartX + padX;
/* 409 */           ((AbstractCard)this.targetGroup.group.get(1)).current_x = drawStartX + padX * 2.0F;
/* 410 */           ((AbstractCard)this.targetGroup.group.get(2)).current_x = drawStartX + padX * 3.0F;
/* 411 */           ((AbstractCard)this.targetGroup.group.get(0)).target_x = drawStartX + padX;
/* 412 */           ((AbstractCard)this.targetGroup.group.get(1)).target_x = drawStartX + padX * 2.0F;
/* 413 */           ((AbstractCard)this.targetGroup.group.get(2)).target_x = drawStartX + padX * 3.0F;
/*     */           break;
/*     */         case 4:
/* 416 */           ((AbstractCard)this.targetGroup.group.get(0)).current_x = Settings.WIDTH / 2.0F - padX / 2.0F - padX;
/* 417 */           ((AbstractCard)this.targetGroup.group.get(0)).target_x = Settings.WIDTH / 2.0F - padX / 2.0F - padX;
/* 418 */           ((AbstractCard)this.targetGroup.group.get(1)).current_x = Settings.WIDTH / 2.0F - padX / 2.0F;
/* 419 */           ((AbstractCard)this.targetGroup.group.get(1)).target_x = Settings.WIDTH / 2.0F - padX / 2.0F;
/* 420 */           ((AbstractCard)this.targetGroup.group.get(2)).current_x = Settings.WIDTH / 2.0F + padX / 2.0F;
/* 421 */           ((AbstractCard)this.targetGroup.group.get(2)).target_x = Settings.WIDTH / 2.0F + padX / 2.0F;
/* 422 */           ((AbstractCard)this.targetGroup.group.get(3)).current_x = Settings.WIDTH / 2.0F + padX / 2.0F + padX;
/* 423 */           ((AbstractCard)this.targetGroup.group.get(3)).target_x = Settings.WIDTH / 2.0F + padX / 2.0F + padX;
/*     */           break;
/*     */       } 
/*     */       
/* 427 */       ArrayList<AbstractCard> c2 = this.targetGroup.group;
/*     */       
/* 429 */       for (int j = 0; j < c2.size(); j++) {
/* 430 */         ((AbstractCard)c2.get(j)).target_y = drawStartY + this.currentDiffY;
/* 431 */         ((AbstractCard)c2.get(j)).fadingOut = false;
/* 432 */         ((AbstractCard)c2.get(j)).update();
/* 433 */         ((AbstractCard)c2.get(j)).updateHoverLogic();
/*     */         
/* 435 */         this.hoveredCard = null;
/* 436 */         for (AbstractCard c : c2) {
/* 437 */           if (c.hb.hovered) {
/* 438 */             this.hoveredCard = c;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 447 */     int lineNum = 0;
/* 448 */     ArrayList<AbstractCard> cards = this.targetGroup.group;
/* 449 */     for (int i = 0; i < cards.size(); i++) {
/* 450 */       int mod = i % 5;
/* 451 */       if (mod == 0 && i != 0) {
/* 452 */         lineNum++;
/*     */       }
/* 454 */       ((AbstractCard)cards.get(i)).target_x = drawStartX + mod * padX;
/* 455 */       ((AbstractCard)cards.get(i)).target_y = drawStartY + this.currentDiffY - lineNum * padY;
/* 456 */       ((AbstractCard)cards.get(i)).fadingOut = false;
/* 457 */       ((AbstractCard)cards.get(i)).update();
/* 458 */       ((AbstractCard)cards.get(i)).updateHoverLogic();
/*     */       
/* 460 */       this.hoveredCard = null;
/* 461 */       for (AbstractCard c : cards) {
/* 462 */         if (c.hb.hovered) {
/* 463 */           this.hoveredCard = c;
/*     */         }
/*     */       } 
/*     */     } 
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
/*     */   public void open(CardGroup group, int numCards, boolean anyNumber, String msg) {
/* 478 */     open(group, numCards, msg, false, false, true, false);
/*     */     
/* 480 */     this.anyNumber = anyNumber;
/* 481 */     this.forClarity = !anyNumber;
/* 482 */     this.confirmButton.hideInstantly();
/* 483 */     this.confirmButton.show();
/* 484 */     this.confirmButton.updateText(TEXT[0]);
/* 485 */     this.confirmButton.isDisabled = !anyNumber;
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
/*     */   public void open(CardGroup group, int numCards, String tipMsg, boolean forUpgrade, boolean forTransform, boolean canCancel, boolean forPurge) {
/* 497 */     this.targetGroup = group;
/* 498 */     callOnOpen();
/*     */ 
/*     */     
/* 501 */     this.forUpgrade = forUpgrade;
/* 502 */     this.forTransform = forTransform;
/* 503 */     this.canCancel = canCancel;
/* 504 */     this.forPurge = forPurge;
/* 505 */     this.tipMsg = tipMsg;
/* 506 */     this.numCards = numCards;
/*     */     
/* 508 */     if ((forUpgrade || forTransform || forPurge || AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.SHOP) && canCancel)
/*     */     {
/* 510 */       AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/*     */     }
/*     */     
/* 513 */     if (!canCancel) {
/* 514 */       AbstractDungeon.overlayMenu.cancelButton.hide();
/*     */     }
/*     */ 
/*     */     
/* 518 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 519 */       this.peekButton.hideInstantly();
/* 520 */       this.peekButton.show();
/*     */     } 
/*     */     
/* 523 */     calculateScrollBounds();
/*     */   }
/*     */   
/*     */   public void open(CardGroup group, int numCards, String tipMsg, boolean forUpgrade, boolean forRitual) {
/* 527 */     open(group, numCards, tipMsg, forUpgrade, forRitual, true, false);
/*     */   }
/*     */   
/*     */   public void open(CardGroup group, int numCards, String tipMsg, boolean forUpgrade) {
/* 531 */     open(group, numCards, tipMsg, forUpgrade, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openConfirmationGrid(CardGroup group, String tipMsg) {
/* 541 */     this.targetGroup = group;
/* 542 */     callOnOpen();
/*     */ 
/*     */     
/* 545 */     this.isJustForConfirming = true;
/* 546 */     this.tipMsg = tipMsg;
/*     */ 
/*     */     
/* 549 */     AbstractDungeon.overlayMenu.cancelButton.hideInstantly();
/*     */ 
/*     */     
/* 552 */     this.confirmButton.show();
/* 553 */     this.confirmButton.updateText(TEXT[0]);
/* 554 */     this.confirmButton.isDisabled = false;
/*     */     
/* 556 */     this.canCancel = false;
/*     */     
/* 558 */     if (group.size() <= 5) {
/* 559 */       AbstractDungeon.dynamicBanner.appear(tipMsg);
/*     */     }
/*     */   }
/*     */   
/*     */   private void callOnOpen() {
/* 564 */     if (Settings.isControllerMode) {
/* 565 */       Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/* 566 */       this.controllerCard = null;
/*     */     } 
/*     */     
/* 569 */     this.anyNumber = false;
/* 570 */     this.forClarity = false;
/* 571 */     this.canCancel = false;
/* 572 */     this.forUpgrade = false;
/* 573 */     this.forTransform = false;
/* 574 */     this.forPurge = false;
/* 575 */     this.confirmScreenUp = false;
/* 576 */     this.isJustForConfirming = false;
/* 577 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 578 */     this.controllerCard = null;
/* 579 */     this.hoveredCard = null;
/* 580 */     this.selectedCards.clear();
/* 581 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 582 */     this.cardSelectAmount = 0;
/* 583 */     this.currentDiffY = 0.0F;
/* 584 */     this.grabStartY = 0.0F;
/* 585 */     this.grabbedScreen = false;
/* 586 */     hideCards();
/* 587 */     AbstractDungeon.isScreenUp = true;
/* 588 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.GRID;
/* 589 */     AbstractDungeon.overlayMenu.showBlackScreen(0.75F);
/* 590 */     this.confirmButton.hideInstantly();
/* 591 */     this.peekButton.hideInstantly();
/* 592 */     if (this.targetGroup.group.size() <= 5) {
/* 593 */       drawStartY = Settings.HEIGHT * 0.5F;
/*     */     } else {
/* 595 */       drawStartY = Settings.HEIGHT * 0.66F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 600 */     AbstractDungeon.overlayMenu.showBlackScreen(0.75F);
/* 601 */     AbstractDungeon.isScreenUp = true;
/* 602 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.GRID;
/* 603 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 604 */     if (this.cancelWasOn && !this.isJustForConfirming && this.canCancel) {
/* 605 */       AbstractDungeon.overlayMenu.cancelButton.show(this.cancelText);
/*     */     }
/* 607 */     for (AbstractCard c : this.targetGroup.group) {
/* 608 */       c.targetDrawScale = 0.75F;
/* 609 */       c.drawScale = 0.75F;
/* 610 */       c.lighten(false);
/*     */     } 
/* 612 */     this.scrollBar.reset();
/*     */   }
/*     */   
/*     */   public void hide() {
/* 616 */     if (!AbstractDungeon.overlayMenu.cancelButton.isHidden) {
/* 617 */       this.cancelWasOn = true;
/* 618 */       this.cancelText = AbstractDungeon.overlayMenu.cancelButton.buttonText;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateScrolling() {
/* 623 */     if (PeekButton.isPeeking) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 628 */     if (this.isJustForConfirming && this.targetGroup.size() <= 5) {
/* 629 */       this.currentDiffY = -64.0F * Settings.scale;
/*     */       
/*     */       return;
/*     */     } 
/* 633 */     int y = InputHelper.mY;
/* 634 */     boolean isDraggingScrollBar = this.scrollBar.update();
/*     */     
/* 636 */     if (!isDraggingScrollBar) {
/* 637 */       if (!this.grabbedScreen) {
/* 638 */         if (InputHelper.scrolledDown) {
/* 639 */           this.currentDiffY += Settings.SCROLL_SPEED;
/* 640 */         } else if (InputHelper.scrolledUp) {
/* 641 */           this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */         } 
/*     */         
/* 644 */         if (InputHelper.justClickedLeft) {
/* 645 */           this.grabbedScreen = true;
/* 646 */           this.grabStartY = y - this.currentDiffY;
/*     */         }
/*     */       
/* 649 */       } else if (InputHelper.isMouseDown) {
/* 650 */         this.currentDiffY = y - this.grabStartY;
/*     */       } else {
/* 652 */         this.grabbedScreen = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 657 */     if (this.prevDeckSize != this.targetGroup.size()) {
/* 658 */       calculateScrollBounds();
/*     */     }
/* 660 */     resetScrolling();
/* 661 */     updateBarPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateScrollBounds() {
/* 668 */     int scrollTmp = 0;
/* 669 */     if (this.targetGroup.size() > 10) {
/* 670 */       scrollTmp = this.targetGroup.size() / 5 - 2;
/* 671 */       if (this.targetGroup.size() % 5 != 0) {
/* 672 */         scrollTmp++;
/*     */       }
/* 674 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * padY;
/*     */     } else {
/* 676 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*     */     } 
/*     */     
/* 679 */     this.prevDeckSize = this.targetGroup.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 686 */     if (this.currentDiffY < this.scrollLowerBound) {
/* 687 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
/* 688 */     } else if (this.currentDiffY > this.scrollUpperBound) {
/* 689 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void hideCards() {
/* 694 */     int lineNum = 0;
/* 695 */     ArrayList<AbstractCard> cards = this.targetGroup.group;
/* 696 */     for (int i = 0; i < cards.size(); i++) {
/* 697 */       ((AbstractCard)cards.get(i)).setAngle(0.0F, true);
/* 698 */       int mod = i % 5;
/* 699 */       if (mod == 0 && i != 0) {
/* 700 */         lineNum++;
/*     */       }
/*     */       
/* 703 */       ((AbstractCard)cards.get(i)).lighten(true);
/* 704 */       ((AbstractCard)cards.get(i)).current_x = drawStartX + mod * padX;
/* 705 */       ((AbstractCard)cards.get(i)).current_y = drawStartY + this.currentDiffY - lineNum * padY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
/*     */ 
/*     */       
/* 708 */       ((AbstractCard)cards.get(i)).targetDrawScale = 0.75F;
/* 709 */       ((AbstractCard)cards.get(i)).drawScale = 0.75F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cancelUpgrade() {
/* 714 */     this.cardSelectAmount = 0;
/* 715 */     this.confirmScreenUp = false;
/* 716 */     this.confirmButton.hide();
/* 717 */     this.confirmButton.isDisabled = true;
/* 718 */     this.hoveredCard = null;
/* 719 */     this.upgradePreviewCard = null;
/*     */     
/* 721 */     if (Settings.isControllerMode && this.controllerCard != null) {
/* 722 */       this.hoveredCard = this.controllerCard;
/* 723 */       CInputHelper.setCursor(this.hoveredCard.hb);
/*     */     } 
/*     */     
/* 726 */     if ((this.forUpgrade || this.forTransform || this.forPurge || AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.SHOP) && this.canCancel)
/*     */     {
/* 728 */       AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/*     */     }
/*     */ 
/*     */     
/* 732 */     int lineNum = 0;
/* 733 */     ArrayList<AbstractCard> cards = this.targetGroup.group;
/* 734 */     for (int i = 0; i < cards.size(); i++) {
/* 735 */       int mod = i % 5;
/* 736 */       if (mod == 0 && i != 0) {
/* 737 */         lineNum++;
/*     */       }
/* 739 */       ((AbstractCard)cards.get(i)).current_x = drawStartX + mod * padX;
/* 740 */       ((AbstractCard)cards.get(i)).current_y = drawStartY + this.currentDiffY - lineNum * padY;
/*     */     } 
/*     */     
/* 743 */     this.tipMsg = this.lastTip;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 747 */     if (shouldShowScrollBar()) {
/* 748 */       this.scrollBar.render(sb);
/*     */     }
/*     */     
/* 751 */     if (!PeekButton.isPeeking) {
/* 752 */       if (this.hoveredCard != null) {
/* 753 */         if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 754 */           this.targetGroup.renderExceptOneCard(sb, this.hoveredCard);
/*     */         } else {
/* 756 */           this.targetGroup.renderExceptOneCardShowBottled(sb, this.hoveredCard);
/*     */         } 
/*     */         
/* 759 */         this.hoveredCard.renderHoverShadow(sb);
/* 760 */         this.hoveredCard.render(sb);
/*     */         
/* 762 */         if ((AbstractDungeon.getCurrRoom()).phase != AbstractRoom.RoomPhase.COMBAT) {
/* 763 */           if (this.hoveredCard.inBottleFlame) {
/* 764 */             AbstractRelic tmp = RelicLibrary.getRelic("Bottled Flame");
/* 765 */             float prevX = tmp.currentX;
/* 766 */             float prevY = tmp.currentY;
/* 767 */             tmp.currentX = this.hoveredCard.current_x + 130.0F * Settings.scale;
/* 768 */             tmp.currentY = this.hoveredCard.current_y + 182.0F * Settings.scale;
/* 769 */             tmp.scale = this.hoveredCard.drawScale * Settings.scale * 1.5F;
/* 770 */             tmp.render(sb);
/* 771 */             tmp.currentX = prevX;
/* 772 */             tmp.currentY = prevY;
/* 773 */             tmp = null;
/* 774 */           } else if (this.hoveredCard.inBottleLightning) {
/* 775 */             AbstractRelic tmp = RelicLibrary.getRelic("Bottled Lightning");
/* 776 */             float prevX = tmp.currentX;
/* 777 */             float prevY = tmp.currentY;
/* 778 */             tmp.currentX = this.hoveredCard.current_x + 130.0F * Settings.scale;
/* 779 */             tmp.currentY = this.hoveredCard.current_y + 182.0F * Settings.scale;
/* 780 */             tmp.scale = this.hoveredCard.drawScale * Settings.scale * 1.5F;
/* 781 */             tmp.render(sb);
/* 782 */             tmp.currentX = prevX;
/* 783 */             tmp.currentY = prevY;
/* 784 */             tmp = null;
/* 785 */           } else if (this.hoveredCard.inBottleTornado) {
/* 786 */             AbstractRelic tmp = RelicLibrary.getRelic("Bottled Tornado");
/* 787 */             float prevX = tmp.currentX;
/* 788 */             float prevY = tmp.currentY;
/* 789 */             tmp.currentX = this.hoveredCard.current_x + 130.0F * Settings.scale;
/* 790 */             tmp.currentY = this.hoveredCard.current_y + 182.0F * Settings.scale;
/* 791 */             tmp.scale = this.hoveredCard.drawScale * Settings.scale * 1.5F;
/* 792 */             tmp.render(sb);
/* 793 */             tmp.currentX = prevX;
/* 794 */             tmp.currentY = prevY;
/* 795 */             tmp = null;
/*     */           } 
/*     */         }
/* 798 */         this.hoveredCard.renderCardTip(sb);
/*     */       }
/* 800 */       else if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 801 */         this.targetGroup.render(sb);
/*     */       } else {
/* 803 */         this.targetGroup.renderShowBottled(sb);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 808 */     if (this.confirmScreenUp) {
/* 809 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, 0.8F));
/* 810 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT - 64.0F * Settings.scale);
/*     */       
/* 812 */       if (this.forTransform || this.forUpgrade) {
/* 813 */         renderArrows(sb);
/*     */         
/* 815 */         this.hoveredCard.current_x = Settings.WIDTH * 0.36F;
/* 816 */         this.hoveredCard.current_y = Settings.HEIGHT / 2.0F;
/* 817 */         this.hoveredCard.target_x = Settings.WIDTH * 0.36F;
/* 818 */         this.hoveredCard.target_y = Settings.HEIGHT / 2.0F;
/* 819 */         this.hoveredCard.render(sb);
/* 820 */         this.hoveredCard.updateHoverLogic();
/* 821 */         this.hoveredCard.renderCardTip(sb);
/*     */ 
/*     */         
/* 824 */         this.upgradePreviewCard.current_x = Settings.WIDTH * 0.63F;
/* 825 */         this.upgradePreviewCard.current_y = Settings.HEIGHT / 2.0F;
/* 826 */         this.upgradePreviewCard.target_x = Settings.WIDTH * 0.63F;
/* 827 */         this.upgradePreviewCard.target_y = Settings.HEIGHT / 2.0F;
/* 828 */         this.upgradePreviewCard.render(sb);
/* 829 */         this.upgradePreviewCard.updateHoverLogic();
/* 830 */         this.upgradePreviewCard.renderCardTip(sb);
/*     */       }
/*     */       else {
/*     */         
/* 834 */         this.hoveredCard.current_x = Settings.WIDTH / 2.0F;
/* 835 */         this.hoveredCard.current_y = Settings.HEIGHT / 2.0F;
/* 836 */         this.hoveredCard.render(sb);
/* 837 */         this.hoveredCard.updateHoverLogic();
/*     */       } 
/*     */     } 
/*     */     
/* 841 */     if (!PeekButton.isPeeking && (
/* 842 */       this.forUpgrade || this.forTransform || this.forPurge || this.isJustForConfirming || this.anyNumber || this.forClarity)) {
/* 843 */       this.confirmButton.render(sb);
/*     */     }
/*     */ 
/*     */     
/* 847 */     this.peekButton.render(sb);
/*     */     
/* 849 */     if ((!this.isJustForConfirming || this.targetGroup.size() > 5) && !PeekButton.isPeeking) {
/* 850 */       FontHelper.renderDeckViewTip(sb, this.tipMsg, 96.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderArrows(SpriteBatch sb) {
/* 856 */     float x = Settings.WIDTH / 2.0F - 73.0F * Settings.scale - 32.0F;
/* 857 */     sb.setColor(Color.WHITE);
/* 858 */     sb.draw(ImageMaster.UPGRADE_ARROW, x, Settings.HEIGHT / 2.0F - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.arrowScale1 * Settings.scale, this.arrowScale1 * Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 876 */     x += 64.0F * Settings.scale;
/* 877 */     sb.setColor(Color.WHITE);
/* 878 */     sb.draw(ImageMaster.UPGRADE_ARROW, x, Settings.HEIGHT / 2.0F - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.arrowScale2 * Settings.scale, this.arrowScale2 * Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 896 */     x += 64.0F * Settings.scale;
/*     */     
/* 898 */     sb.draw(ImageMaster.UPGRADE_ARROW, x, Settings.HEIGHT / 2.0F - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.arrowScale3 * Settings.scale, this.arrowScale3 * Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 916 */     this.arrowTimer += Gdx.graphics.getDeltaTime() * 2.0F;
/* 917 */     this.arrowScale1 = 0.8F + (MathUtils.cos(this.arrowTimer) + 1.0F) / 8.0F;
/* 918 */     this.arrowScale2 = 0.8F + (MathUtils.cos(this.arrowTimer - 0.8F) + 1.0F) / 8.0F;
/* 919 */     this.arrowScale3 = 0.8F + (MathUtils.cos(this.arrowTimer - 1.6F) + 1.0F) / 8.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 924 */     this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 925 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 929 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
/* 930 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */   
/*     */   private boolean shouldShowScrollBar() {
/* 934 */     return (!this.confirmScreenUp && this.scrollUpperBound > SCROLL_BAR_THRESHOLD && !PeekButton.isPeeking);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\select\GridCardSelectScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */