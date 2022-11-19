/*     */ package com.megacrit.cardcrawl.screens.select;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.ui.buttons.CardSelectConfirmButton;
/*     */ import com.megacrit.cardcrawl.ui.buttons.PeekButton;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HandCardSelectScreen
/*     */ {
/*  28 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("HandCardSelectScreen");
/*  29 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public int numCardsToSelect;
/*  32 */   public CardGroup selectedCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED); public AbstractCard hoveredCard;
/*  33 */   public AbstractCard upgradePreviewCard = null;
/*     */   public String selectionReason;
/*     */   public boolean wereCardsRetrieved = false;
/*     */   public boolean canPickZero = false;
/*     */   public boolean upTo = false;
/*  38 */   private String message = "";
/*  39 */   public CardSelectConfirmButton button = new CardSelectConfirmButton();
/*  40 */   private PeekButton peekButton = new PeekButton();
/*     */   private boolean anyNumber = false;
/*  42 */   public int numSelected = 0; private boolean forTransform = false; private boolean forUpgrade;
/*  43 */   public static final float MIN_HOVER_DIST = 64.0F * Settings.scale;
/*     */   
/*     */   private boolean waitThenClose = false;
/*  46 */   private float waitToCloseTimer = 0.0F;
/*     */   
/*     */   private CardGroup hand;
/*     */   
/*  50 */   public static final float HOVER_CARD_Y_POSITION = 210.0F * Settings.scale;
/*     */   
/*     */   private static final int ARROW_W = 64;
/*     */   
/*  54 */   private float arrowScale1 = 0.75F, arrowScale2 = 0.75F, arrowScale3 = 0.75F, arrowTimer = 0.0F;
/*     */   
/*     */   public void update() {
/*  57 */     updateControllerInput();
/*  58 */     this.peekButton.update();
/*     */     
/*  60 */     if (!PeekButton.isPeeking) {
/*  61 */       updateHand();
/*  62 */       updateSelectedCards();
/*     */       
/*  64 */       if (this.waitThenClose) {
/*  65 */         this.waitToCloseTimer -= Gdx.graphics.getDeltaTime();
/*  66 */         if (this.waitToCloseTimer < 0.0F) {
/*  67 */           this.waitThenClose = false;
/*  68 */           AbstractDungeon.closeCurrentScreen();
/*     */           
/*  70 */           if (this.forTransform && this.selectedCards.size() == 1) {
/*  71 */             if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/*  72 */               AbstractDungeon.srcTransformCard(this.selectedCards.getBottomCard());
/*     */             } else {
/*  74 */               AbstractDungeon.transformCard(this.selectedCards.getBottomCard());
/*     */             } 
/*  76 */             this.selectedCards.group.clear();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  81 */       if (Settings.FAST_HAND_CONF && this.numCardsToSelect == 1 && this.selectedCards.size() == 1 && !this.canPickZero && 
/*  82 */         !this.waitThenClose) {
/*  83 */         InputHelper.justClickedLeft = false;
/*  84 */         this.waitToCloseTimer = 0.25F;
/*  85 */         this.waitThenClose = true;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  90 */       this.button.update();
/*  91 */       if (this.button.hb.clicked || CInputActionSet.proceed.isJustPressed() || InputActionSet.confirm
/*  92 */         .isJustPressed()) {
/*  93 */         CInputActionSet.proceed.unpress();
/*  94 */         this.button.hb.clicked = false;
/*  95 */         if (this.canPickZero && this.selectedCards.size() == 0) {
/*  96 */           InputHelper.justClickedLeft = false;
/*  97 */           AbstractDungeon.closeCurrentScreen();
/*     */           
/*     */           return;
/*     */         } 
/* 101 */         if (this.anyNumber || this.upTo) {
/* 102 */           InputHelper.justClickedLeft = false;
/* 103 */           AbstractDungeon.closeCurrentScreen();
/*     */           
/*     */           return;
/*     */         } 
/* 107 */         if (this.selectedCards.size() == this.numCardsToSelect) {
/* 108 */           InputHelper.justClickedLeft = false;
/* 109 */           AbstractDungeon.closeCurrentScreen();
/*     */           
/* 111 */           if (this.forTransform && this.selectedCards.size() == 1) {
/* 112 */             if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 113 */               AbstractDungeon.srcTransformCard(this.selectedCards.getBottomCard());
/*     */             } else {
/* 115 */               AbstractDungeon.transformCard(this.selectedCards.getBottomCard());
/*     */             } 
/* 117 */             this.selectedCards.group.clear();
/*     */           } 
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 126 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 130 */     boolean inHand = true;
/* 131 */     boolean anyHovered = false;
/* 132 */     int index = 0;
/* 133 */     int y = 0;
/*     */     
/* 135 */     for (AbstractCard c : this.selectedCards.group) {
/* 136 */       if (c.hb.hovered) {
/* 137 */         anyHovered = true;
/* 138 */         inHand = false;
/*     */         break;
/*     */       } 
/* 141 */       index++;
/*     */     } 
/*     */     
/* 144 */     if (inHand) {
/* 145 */       index = 0;
/* 146 */       for (AbstractCard c : this.hand.group) {
/* 147 */         if (c == this.hoveredCard) {
/* 148 */           anyHovered = true;
/*     */           break;
/*     */         } 
/* 151 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     if (!anyHovered) {
/* 156 */       if (!this.hand.group.isEmpty()) {
/* 157 */         setHoveredCard(this.hand.group.get(0));
/* 158 */         Gdx.input.setCursorPosition((int)this.hoveredCard.hb.cX, Settings.HEIGHT - (int)this.hoveredCard.hb.cY);
/* 159 */       } else if (!this.selectedCards.isEmpty()) {
/* 160 */         Gdx.input.setCursorPosition(
/* 161 */             (int)((AbstractCard)this.selectedCards.group.get(0)).hb.cX, Settings.HEIGHT - 
/* 162 */             (int)((AbstractCard)this.selectedCards.group.get(0)).hb.cY);
/*     */       }
/*     */     
/* 165 */     } else if (!inHand) {
/* 166 */       if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && 
/* 167 */         !this.hand.group.isEmpty()) {
/* 168 */         index = 0;
/* 169 */         if (((AbstractCard)this.hand.group.get(index)).hb.cY < 0.0F) {
/* 170 */           y = 1;
/*     */         } else {
/* 172 */           y = (int)((AbstractCard)this.hand.group.get(index)).hb.cY;
/*     */         } 
/* 174 */         Gdx.input.setCursorPosition((int)((AbstractCard)this.hand.group.get(index)).hb.cX, Settings.HEIGHT - y);
/* 175 */         setHoveredCard(this.hand.group.get(index));
/* 176 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 177 */         index++;
/* 178 */         if (index > this.selectedCards.size() - 1) {
/* 179 */           index = 0;
/*     */         }
/* 181 */         Gdx.input.setCursorPosition(
/* 182 */             (int)((AbstractCard)this.selectedCards.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 183 */             (int)((AbstractCard)this.selectedCards.group.get(index)).hb.cY);
/* 184 */         unhoverCard(this.hoveredCard);
/* 185 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 186 */         index--;
/* 187 */         if (index < 0) {
/* 188 */           index = this.selectedCards.size() - 1;
/*     */         }
/* 190 */         Gdx.input.setCursorPosition(
/* 191 */             (int)((AbstractCard)this.selectedCards.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 192 */             (int)((AbstractCard)this.selectedCards.group.get(index)).hb.cY);
/* 193 */         unhoverCard(this.hoveredCard);
/* 194 */       } else if (CInputActionSet.select.isJustPressed()) {
/* 195 */         CInputActionSet.select.unpress();
/* 196 */         if (((AbstractCard)this.selectedCards.group.get(index)).hb.hovered) {
/* 197 */           AbstractCard tmp = this.selectedCards.group.get(index);
/* 198 */           AbstractDungeon.player.hand.addToTop(tmp);
/* 199 */           this.selectedCards.group.remove(tmp);
/* 200 */           refreshSelectedCards();
/* 201 */           updateMessage();
/* 202 */           this.hand.refreshHandLayout();
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 208 */     else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && 
/* 209 */       !this.selectedCards.isEmpty()) {
/* 210 */       index = 0;
/* 211 */       if (((AbstractCard)this.selectedCards.group.get(index)).hb.cY < 0.0F) {
/* 212 */         y = 1;
/*     */       } else {
/* 214 */         y = (int)((AbstractCard)this.selectedCards.group.get(index)).hb.cY;
/*     */       } 
/*     */       
/* 217 */       unhoverCard(this.hoveredCard);
/* 218 */       Gdx.input.setCursorPosition((int)((AbstractCard)this.selectedCards.group.get(index)).hb.cX, Settings.HEIGHT - y);
/* 219 */     } else if (this.hand.size() > 1 && (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft
/* 220 */       .isJustPressed())) {
/* 221 */       index--;
/* 222 */       if (index < 0) {
/* 223 */         index = this.hand.size() - 1;
/*     */       }
/*     */       
/* 226 */       unhoverCard(this.hoveredCard);
/* 227 */       setHoveredCard(this.hand.group.get(index));
/* 228 */       Gdx.input.setCursorPosition((int)this.hoveredCard.hb.cX, Settings.HEIGHT - (int)this.hoveredCard.hb.cY);
/* 229 */     } else if (this.hand.size() > 1 && (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/* 230 */       .isJustPressed())) {
/* 231 */       index++;
/* 232 */       if (index > this.hand.size() - 1) {
/* 233 */         index = 0;
/*     */       }
/*     */       
/* 236 */       unhoverCard(this.hoveredCard);
/* 237 */       setHoveredCard(this.hand.group.get(index));
/* 238 */       Gdx.input.setCursorPosition((int)this.hoveredCard.hb.cX, Settings.HEIGHT - (int)this.hoveredCard.hb.cY);
/* 239 */     } else if (this.hand.size() == 1 && this.hoveredCard == null) {
/* 240 */       setHoveredCard(this.hand.group.get(index));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void unhoverCard(AbstractCard card) {
/* 247 */     if (card == null) {
/*     */       return;
/*     */     }
/*     */     
/* 251 */     card.targetDrawScale = 0.7F;
/* 252 */     card.hoverTimer = 0.25F;
/* 253 */     card.unhover();
/* 254 */     card = null;
/* 255 */     this.hand.refreshHandLayout();
/*     */   }
/*     */   
/*     */   private void setHoveredCard(AbstractCard card) {
/* 259 */     this.hoveredCard = card;
/* 260 */     this.hoveredCard.current_y = HOVER_CARD_Y_POSITION;
/* 261 */     this.hoveredCard.target_y = HOVER_CARD_Y_POSITION;
/* 262 */     this.hoveredCard.drawScale = 1.0F;
/* 263 */     this.hoveredCard.targetDrawScale = 1.0F;
/* 264 */     this.hoveredCard.setAngle(0.0F, true);
/* 265 */     this.hand.hoverCardPush(this.hoveredCard);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateHand() {
/* 273 */     if (!Settings.isControllerMode) {
/* 274 */       hoverCheck();
/* 275 */       unhoverCheck();
/*     */     } 
/* 277 */     startDraggingCardCheck();
/* 278 */     hotkeyCheck();
/*     */   }
/*     */   
/*     */   private void refreshSelectedCards() {
/* 282 */     for (AbstractCard c : this.selectedCards.group) {
/* 283 */       c.target_y = Settings.HEIGHT / 2.0F + 160.0F * Settings.scale;
/*     */     }
/*     */     
/* 286 */     switch (this.selectedCards.size()) {
/*     */       case 1:
/* 288 */         if (this.forUpgrade) {
/* 289 */           ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH * 0.37F; break;
/*     */         } 
/* 291 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F;
/*     */         break;
/*     */       
/*     */       case 2:
/* 295 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 120.0F * Settings.scale;
/* 296 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F + 120.0F * Settings.scale;
/*     */         break;
/*     */       case 3:
/* 299 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 240.0F * Settings.scale;
/* 300 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F;
/* 301 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F + 240.0F * Settings.scale;
/*     */         break;
/*     */       case 4:
/* 304 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 360.0F * Settings.scale;
/* 305 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F - 120.0F * Settings.scale;
/* 306 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F + 120.0F * Settings.scale;
/* 307 */         ((AbstractCard)this.selectedCards.group.get(3)).target_x = Settings.WIDTH / 2.0F + 360.0F * Settings.scale;
/*     */         break;
/*     */       case 5:
/* 310 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 360.0F * Settings.scale;
/* 311 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F - 180.0F * Settings.scale;
/* 312 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F;
/* 313 */         ((AbstractCard)this.selectedCards.group.get(3)).target_x = Settings.WIDTH / 2.0F + 180.0F * Settings.scale;
/* 314 */         ((AbstractCard)this.selectedCards.group.get(4)).target_x = Settings.WIDTH / 2.0F + 360.0F * Settings.scale;
/*     */         break;
/*     */       case 6:
/* 317 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 450.0F * Settings.scale;
/* 318 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F - 270.0F * Settings.scale;
/* 319 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F - 90.0F * Settings.scale;
/* 320 */         ((AbstractCard)this.selectedCards.group.get(3)).target_x = Settings.WIDTH / 2.0F + 90.0F * Settings.scale;
/* 321 */         ((AbstractCard)this.selectedCards.group.get(4)).target_x = Settings.WIDTH / 2.0F + 270.0F * Settings.scale;
/* 322 */         ((AbstractCard)this.selectedCards.group.get(5)).target_x = Settings.WIDTH / 2.0F + 450.0F * Settings.scale;
/*     */         break;
/*     */       case 7:
/* 325 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 540.0F * Settings.scale;
/* 326 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F - 360.0F * Settings.scale;
/* 327 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F - 180.0F * Settings.scale;
/* 328 */         ((AbstractCard)this.selectedCards.group.get(3)).target_x = Settings.WIDTH / 2.0F;
/* 329 */         ((AbstractCard)this.selectedCards.group.get(4)).target_x = Settings.WIDTH / 2.0F + 180.0F * Settings.scale;
/* 330 */         ((AbstractCard)this.selectedCards.group.get(5)).target_x = Settings.WIDTH / 2.0F + 360.0F * Settings.scale;
/* 331 */         ((AbstractCard)this.selectedCards.group.get(6)).target_x = Settings.WIDTH / 2.0F + 540.0F * Settings.scale;
/*     */         break;
/*     */       case 8:
/* 334 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 630.0F * Settings.scale;
/* 335 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F - 450.0F * Settings.scale;
/* 336 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F - 270.0F * Settings.scale;
/* 337 */         ((AbstractCard)this.selectedCards.group.get(3)).target_x = Settings.WIDTH / 2.0F - 90.0F * Settings.scale;
/* 338 */         ((AbstractCard)this.selectedCards.group.get(4)).target_x = Settings.WIDTH / 2.0F + 90.0F * Settings.scale;
/* 339 */         ((AbstractCard)this.selectedCards.group.get(5)).target_x = Settings.WIDTH / 2.0F + 270.0F * Settings.scale;
/* 340 */         ((AbstractCard)this.selectedCards.group.get(6)).target_x = Settings.WIDTH / 2.0F + 450.0F * Settings.scale;
/* 341 */         ((AbstractCard)this.selectedCards.group.get(7)).target_x = Settings.WIDTH / 2.0F + 630.0F * Settings.scale;
/*     */         break;
/*     */       case 9:
/* 344 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 720.0F * Settings.scale;
/* 345 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F - 540.0F * Settings.scale;
/* 346 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F - 360.0F * Settings.scale;
/* 347 */         ((AbstractCard)this.selectedCards.group.get(3)).target_x = Settings.WIDTH / 2.0F - 180.0F * Settings.scale;
/* 348 */         ((AbstractCard)this.selectedCards.group.get(4)).target_x = Settings.WIDTH / 2.0F;
/* 349 */         ((AbstractCard)this.selectedCards.group.get(5)).target_x = Settings.WIDTH / 2.0F + 180.0F * Settings.scale;
/* 350 */         ((AbstractCard)this.selectedCards.group.get(6)).target_x = Settings.WIDTH / 2.0F + 360.0F * Settings.scale;
/* 351 */         ((AbstractCard)this.selectedCards.group.get(7)).target_x = Settings.WIDTH / 2.0F + 540.0F * Settings.scale;
/* 352 */         ((AbstractCard)this.selectedCards.group.get(8)).target_x = Settings.WIDTH / 2.0F + 720.0F * Settings.scale;
/*     */         break;
/*     */       case 10:
/* 355 */         ((AbstractCard)this.selectedCards.group.get(0)).target_x = Settings.WIDTH / 2.0F - 810.0F * Settings.scale;
/* 356 */         ((AbstractCard)this.selectedCards.group.get(1)).target_x = Settings.WIDTH / 2.0F - 630.0F * Settings.scale;
/* 357 */         ((AbstractCard)this.selectedCards.group.get(2)).target_x = Settings.WIDTH / 2.0F - 450.0F * Settings.scale;
/* 358 */         ((AbstractCard)this.selectedCards.group.get(3)).target_x = Settings.WIDTH / 2.0F - 270.0F * Settings.scale;
/* 359 */         ((AbstractCard)this.selectedCards.group.get(4)).target_x = Settings.WIDTH / 2.0F - 90.0F * Settings.scale;
/* 360 */         ((AbstractCard)this.selectedCards.group.get(5)).target_x = Settings.WIDTH / 2.0F + 90.0F * Settings.scale;
/* 361 */         ((AbstractCard)this.selectedCards.group.get(6)).target_x = Settings.WIDTH / 2.0F + 270.0F * Settings.scale;
/* 362 */         ((AbstractCard)this.selectedCards.group.get(7)).target_x = Settings.WIDTH / 2.0F + 450.0F * Settings.scale;
/* 363 */         ((AbstractCard)this.selectedCards.group.get(8)).target_x = Settings.WIDTH / 2.0F + 630.0F * Settings.scale;
/* 364 */         ((AbstractCard)this.selectedCards.group.get(9)).target_x = Settings.WIDTH / 2.0F + 810.0F * Settings.scale;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 370 */     if (this.upTo) {
/* 371 */       this.button.enable();
/* 372 */     } else if (this.selectedCards.size() == this.numCardsToSelect) {
/* 373 */       this.button.enable();
/* 374 */     } else if (this.selectedCards.size() > 1 && this.anyNumber && !this.canPickZero) {
/* 375 */       this.button.enable();
/* 376 */     } else if (this.selectedCards.size() != this.numCardsToSelect && !this.anyNumber) {
/* 377 */       this.button.disable();
/* 378 */     } else if (this.anyNumber && this.canPickZero) {
/* 379 */       this.button.enable();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void hoverCheck() {
/* 384 */     if (this.hoveredCard == null) {
/* 385 */       this.hoveredCard = this.hand.getHoveredCard();
/* 386 */       if (this.hoveredCard != null) {
/* 387 */         this.hoveredCard.current_y = HOVER_CARD_Y_POSITION;
/* 388 */         this.hoveredCard.target_y = HOVER_CARD_Y_POSITION;
/* 389 */         this.hoveredCard.drawScale = 1.0F;
/* 390 */         this.hoveredCard.targetDrawScale = 1.0F;
/* 391 */         this.hoveredCard.setAngle(0.0F, true);
/* 392 */         this.hand.hoverCardPush(this.hoveredCard);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void unhoverCheck() {
/* 398 */     if (this.hoveredCard != null && !this.hoveredCard.isHoveredInHand(1.0F)) {
/* 399 */       this.hoveredCard.targetDrawScale = 0.7F;
/* 400 */       this.hoveredCard.hoverTimer = 0.25F;
/* 401 */       this.hoveredCard.unhover();
/* 402 */       this.hoveredCard = null;
/* 403 */       this.hand.refreshHandLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startDraggingCardCheck() {
/* 408 */     if (this.hoveredCard != null && (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed())) {
/* 409 */       CInputActionSet.select.unpress();
/* 410 */       if (!Settings.FAST_HAND_CONF || this.numCardsToSelect != 1 || this.selectedCards.size() != 1)
/*     */       {
/*     */         
/* 413 */         selectHoveredCard();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void selectHoveredCard() {
/* 419 */     if (this.numCardsToSelect > this.selectedCards.group.size()) {
/* 420 */       InputHelper.justClickedLeft = false;
/* 421 */       CardCrawlGame.sound.play("CARD_OBTAIN");
/* 422 */       this.hand.removeCard(this.hoveredCard);
/* 423 */       this.hand.refreshHandLayout();
/* 424 */       this.hoveredCard.setAngle(0.0F, false);
/* 425 */       this.selectedCards.addToTop(this.hoveredCard);
/* 426 */       refreshSelectedCards();
/* 427 */       this.hoveredCard = null;
/* 428 */       updateMessage();
/* 429 */     } else if (this.numCardsToSelect == 1 && this.selectedCards.group.size() == 1) {
/* 430 */       InputHelper.justClickedLeft = false;
/* 431 */       CardCrawlGame.sound.play("CARD_OBTAIN");
/* 432 */       this.hand.removeCard(this.hoveredCard);
/* 433 */       this.hoveredCard.setAngle(0.0F, false);
/* 434 */       this.selectedCards.addToBottom(this.hoveredCard);
/* 435 */       refreshSelectedCards();
/* 436 */       this.hoveredCard = null;
/* 437 */       AbstractDungeon.player.hand.addToTop(this.selectedCards.getTopCard());
/* 438 */       this.selectedCards.removeTopCard();
/* 439 */       refreshSelectedCards();
/* 440 */       this.hand.refreshHandLayout();
/* 441 */       if (this.forUpgrade && this.selectedCards.size() == 1) {
/* 442 */         this.upgradePreviewCard = ((AbstractCard)this.selectedCards.group.get(0)).makeStatEquivalentCopy();
/* 443 */         this.upgradePreviewCard.upgrade();
/* 444 */         this.upgradePreviewCard.displayUpgrades();
/* 445 */         this.upgradePreviewCard.drawScale = 0.75F;
/*     */       } 
/*     */     } 
/*     */     
/* 449 */     InputHelper.moveCursorToNeutralPosition();
/*     */   }
/*     */   
/*     */   private void hotkeyCheck() {
/* 453 */     AbstractCard hotkeyCard = InputHelper.getCardSelectedByHotkey(this.hand);
/* 454 */     if (hotkeyCard != null) {
/* 455 */       this.hoveredCard = hotkeyCard;
/* 456 */       this.hoveredCard.setAngle(0.0F, false);
/* 457 */       selectHoveredCard();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateSelectedCards() {
/* 465 */     this.selectedCards.update();
/*     */     
/* 467 */     for (Iterator<AbstractCard> i = this.selectedCards.group.iterator(); i.hasNext(); ) {
/* 468 */       AbstractCard e = i.next();
/* 469 */       e.current_x = MathHelper.cardLerpSnap(e.current_x, e.target_x);
/* 470 */       e.current_y = MathHelper.cardLerpSnap(e.current_y, e.target_y);
/* 471 */       e.hb.update();
/* 472 */       if (this.selectedCards.group.size() >= 5) {
/* 473 */         e.targetDrawScale = 0.5F;
/* 474 */         if (Math.abs(e.current_x - e.target_x) < MIN_HOVER_DIST && e.hb.hovered) {
/* 475 */           e.targetDrawScale = 0.66F;
/*     */         }
/*     */       } else {
/* 478 */         e.targetDrawScale = 0.66F;
/* 479 */         if (this.forUpgrade) {
/* 480 */           e.targetDrawScale = 0.75F;
/*     */         }
/* 482 */         if (Math.abs(e.current_x - e.target_x) < MIN_HOVER_DIST && e.hb.hovered) {
/* 483 */           if (this.forUpgrade) {
/* 484 */             e.targetDrawScale = 0.85F;
/*     */           } else {
/* 486 */             e.targetDrawScale = 0.75F;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 491 */       if (!this.waitThenClose && Math.abs(e.current_x - e.target_x) < MIN_HOVER_DIST && e.hb.hovered && (InputHelper.justClickedLeft || CInputActionSet.select
/* 492 */         .isJustPressed())) {
/* 493 */         InputHelper.justClickedLeft = false;
/* 494 */         AbstractDungeon.player.hand.addToTop(e);
/* 495 */         i.remove();
/* 496 */         refreshSelectedCards();
/* 497 */         updateMessage();
/*     */         
/* 499 */         if (Settings.isControllerMode) {
/* 500 */           this.hand.refreshHandLayout();
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 506 */     if (this.selectedCards.isEmpty() && !this.canPickZero) {
/* 507 */       this.button.disable();
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateMessage() {
/* 512 */     if (this.selectedCards.group.size() == 0) {
/* 513 */       this.upgradePreviewCard = null;
/* 514 */       if (this.selectedCards.group.size() == this.numCardsToSelect) {
/* 515 */         if (this.numCardsToSelect == 1) {
/* 516 */           this.message = TEXT[0] + this.selectionReason;
/*     */         } else {
/* 518 */           this.message = TEXT[1] + this.selectionReason;
/*     */         } 
/* 520 */       } else if (this.numCardsToSelect != 1) {
/* 521 */         if (!this.anyNumber) {
/* 522 */           this.message = TEXT[2] + this.numCardsToSelect + TEXT[3] + this.selectionReason;
/*     */         } else {
/* 524 */           this.message = TEXT[4] + this.selectionReason;
/*     */         } 
/*     */       } else {
/* 527 */         this.message = TEXT[5] + this.selectionReason;
/*     */       } 
/* 529 */     } else if (this.selectedCards.group.size() != 0) {
/* 530 */       int numLeft = this.numCardsToSelect - this.selectedCards.group.size();
/* 531 */       if (this.selectedCards.group.size() == this.numCardsToSelect) {
/* 532 */         if (this.numCardsToSelect == 1) {
/* 533 */           this.message = TEXT[0] + this.selectionReason;
/*     */         } else {
/* 535 */           this.message = TEXT[1] + this.selectionReason;
/*     */         } 
/* 537 */         if (this.forUpgrade && this.selectedCards.size() == 1) {
/* 538 */           if (this.upgradePreviewCard == null) {
/* 539 */             this.upgradePreviewCard = ((AbstractCard)this.selectedCards.group.get(0)).makeStatEquivalentCopy();
/*     */           }
/* 541 */           this.upgradePreviewCard.upgrade();
/* 542 */           this.upgradePreviewCard.displayUpgrades();
/* 543 */           this.upgradePreviewCard.drawScale = 0.75F;
/* 544 */           this.upgradePreviewCard.targetDrawScale = 0.75F;
/*     */         } else {
/* 546 */           this.upgradePreviewCard = null;
/*     */         } 
/* 548 */       } else if (numLeft != 1) {
/* 549 */         this.upgradePreviewCard = null;
/* 550 */         if (!this.anyNumber) {
/* 551 */           this.message = TEXT[2] + numLeft + TEXT[3] + this.selectionReason;
/*     */         } else {
/* 553 */           this.message = TEXT[4] + this.selectionReason;
/*     */         } 
/*     */       } else {
/* 556 */         this.upgradePreviewCard = null;
/* 557 */         this.message = TEXT[5] + this.selectionReason;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 563 */     AbstractDungeon.overlayMenu.showBlackScreen(0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(String msg, int amount, boolean anyNumber, boolean canPickZero, boolean forTransform, boolean forUpgrade, boolean upTo) {
/* 574 */     prep();
/* 575 */     this.numCardsToSelect = amount;
/* 576 */     this.canPickZero = canPickZero;
/* 577 */     this.anyNumber = anyNumber;
/* 578 */     this.selectionReason = msg;
/* 579 */     this.upTo = upTo;
/*     */     
/* 581 */     if (canPickZero) {
/* 582 */       this.button.isDisabled = true;
/* 583 */       this.button.enable();
/*     */     } else {
/* 585 */       this.button.isDisabled = false;
/* 586 */       this.button.disable();
/*     */     } 
/*     */     
/* 589 */     this.forTransform = forTransform;
/* 590 */     this.forUpgrade = forUpgrade;
/*     */     
/* 592 */     if (!forUpgrade) {
/* 593 */       this.upgradePreviewCard = null;
/*     */     }
/*     */     
/* 596 */     this.button.hideInstantly();
/* 597 */     this.button.show();
/* 598 */     this.peekButton.hideInstantly();
/* 599 */     this.peekButton.show();
/*     */     
/* 601 */     updateMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(String msg, int amount, boolean anyNumber, boolean canPickZero, boolean forTransform, boolean forUpgrade) {
/* 611 */     open(msg, amount, anyNumber, canPickZero, forTransform, forUpgrade, false);
/*     */   }
/*     */   
/*     */   public void open(String msg, int amount, boolean anyNumber, boolean canPickZero, boolean forTransform) {
/* 615 */     open(msg, amount, anyNumber, canPickZero, forTransform, false);
/*     */   }
/*     */   
/*     */   public void open(String msg, int amount, boolean anyNumber, boolean canPickZero) {
/* 619 */     prep();
/*     */     
/* 621 */     this.numCardsToSelect = amount;
/* 622 */     this.canPickZero = canPickZero;
/* 623 */     this.anyNumber = anyNumber;
/* 624 */     this.selectionReason = msg;
/*     */     
/* 626 */     if (canPickZero) {
/* 627 */       this.button.isDisabled = true;
/* 628 */       this.button.enable();
/*     */     } else {
/* 630 */       this.button.isDisabled = false;
/* 631 */       this.button.disable();
/*     */     } 
/*     */     
/* 634 */     this.button.hideInstantly();
/* 635 */     this.button.show();
/*     */     
/* 637 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 638 */       this.peekButton.hideInstantly();
/* 639 */       this.peekButton.show();
/*     */     } 
/*     */     
/* 642 */     updateMessage();
/*     */   }
/*     */   
/*     */   public void open(String msg, int amount, boolean anyNumber) {
/* 646 */     open(msg, amount, anyNumber, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 651 */     AbstractDungeon.player.hand.render(sb);
/* 652 */     AbstractDungeon.overlayMenu.energyPanel.render(sb);
/*     */     
/* 654 */     if (!PeekButton.isPeeking) {
/*     */ 
/*     */       
/* 657 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.message, (Settings.WIDTH / 2), Settings.HEIGHT - 180.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 666 */       if (!Settings.FAST_HAND_CONF || this.numCardsToSelect != 1 || this.canPickZero)
/*     */       {
/*     */         
/* 669 */         this.button.render(sb);
/*     */       }
/*     */ 
/*     */       
/* 673 */       this.selectedCards.render(sb);
/*     */ 
/*     */       
/* 676 */       if (this.forUpgrade && this.upgradePreviewCard != null) {
/* 677 */         renderArrows(sb);
/* 678 */         this.upgradePreviewCard.current_x = Settings.WIDTH * 0.63F;
/* 679 */         this.upgradePreviewCard.current_y = Settings.HEIGHT / 2.0F + 160.0F * Settings.scale;
/* 680 */         this.upgradePreviewCard.target_x = Settings.WIDTH * 0.63F;
/* 681 */         this.upgradePreviewCard.target_y = Settings.HEIGHT / 2.0F + 160.0F * Settings.scale;
/*     */         
/* 683 */         this.upgradePreviewCard.displayUpgrades();
/* 684 */         boolean t1 = this.upgradePreviewCard.isDamageModified;
/* 685 */         boolean t2 = this.upgradePreviewCard.isBlockModified;
/* 686 */         boolean t3 = this.upgradePreviewCard.isMagicNumberModified;
/* 687 */         boolean t4 = this.upgradePreviewCard.isCostModified;
/* 688 */         this.upgradePreviewCard.applyPowers();
/*     */         
/* 690 */         if (!this.upgradePreviewCard.isDamageModified && t1) {
/* 691 */           this.upgradePreviewCard.isDamageModified = true;
/*     */         }
/* 693 */         if (!this.upgradePreviewCard.isBlockModified && t2) {
/* 694 */           this.upgradePreviewCard.isBlockModified = true;
/*     */         }
/* 696 */         if (!this.upgradePreviewCard.isMagicNumberModified && t3) {
/* 697 */           this.upgradePreviewCard.isMagicNumberModified = true;
/*     */         }
/* 699 */         if (!this.upgradePreviewCard.isCostModified && t4) {
/* 700 */           this.upgradePreviewCard.isCostModified = true;
/*     */         }
/*     */         
/* 703 */         this.upgradePreviewCard.render(sb);
/* 704 */         this.upgradePreviewCard.updateHoverLogic();
/* 705 */         this.upgradePreviewCard.renderCardTip(sb);
/*     */       } 
/*     */     } 
/*     */     
/* 709 */     this.peekButton.render(sb);
/* 710 */     AbstractDungeon.overlayMenu.combatDeckPanel.render(sb);
/* 711 */     AbstractDungeon.overlayMenu.discardPilePanel.render(sb);
/* 712 */     AbstractDungeon.overlayMenu.exhaustPanel.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderArrows(SpriteBatch sb) {
/* 717 */     float x = Settings.WIDTH / 2.0F - 96.0F * Settings.scale - 10.0F * Settings.scale;
/* 718 */     sb.setColor(Color.WHITE);
/* 719 */     sb.draw(ImageMaster.UPGRADE_ARROW, x, Settings.HEIGHT / 2.0F + 120.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.arrowScale1 * Settings.scale, this.arrowScale1 * Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 737 */     x += 64.0F * Settings.scale;
/* 738 */     sb.setColor(Color.WHITE);
/* 739 */     sb.draw(ImageMaster.UPGRADE_ARROW, x, Settings.HEIGHT / 2.0F + 120.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.arrowScale2 * Settings.scale, this.arrowScale2 * Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 757 */     x += 64.0F * Settings.scale;
/*     */     
/* 759 */     sb.draw(ImageMaster.UPGRADE_ARROW, x, Settings.HEIGHT / 2.0F + 120.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.arrowScale3 * Settings.scale, this.arrowScale3 * Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 777 */     this.arrowTimer += Gdx.graphics.getDeltaTime() * 2.0F;
/* 778 */     this.arrowScale1 = 0.8F + (MathUtils.cos(this.arrowTimer) + 1.0F) / 8.0F;
/* 779 */     this.arrowScale2 = 0.8F + (MathUtils.cos(this.arrowTimer - 0.8F) + 1.0F) / 8.0F;
/* 780 */     this.arrowScale3 = 0.8F + (MathUtils.cos(this.arrowTimer - 1.6F) + 1.0F) / 8.0F;
/*     */   }
/*     */   
/*     */   private void prep() {
/* 784 */     AbstractDungeon.player.resetControllerValues();
/* 785 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 786 */       c.unhover();
/*     */     }
/* 788 */     this.upTo = false;
/* 789 */     this.forTransform = false;
/* 790 */     this.forUpgrade = false;
/* 791 */     this.canPickZero = false;
/* 792 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 793 */     AbstractDungeon.actionManager.cleanCardQueue();
/* 794 */     this.hand = AbstractDungeon.player.hand;
/* 795 */     AbstractDungeon.player.releaseCard();
/* 796 */     (AbstractDungeon.getMonsters()).hoveredMonster = null;
/* 797 */     this.waitThenClose = false;
/* 798 */     this.waitToCloseTimer = 0.0F;
/* 799 */     this.selectedCards.clear();
/* 800 */     this.hoveredCard = null;
/* 801 */     this.wereCardsRetrieved = false;
/* 802 */     AbstractDungeon.isScreenUp = true;
/* 803 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.HAND_SELECT;
/* 804 */     AbstractDungeon.player.hand.stopGlowing();
/* 805 */     AbstractDungeon.player.hand.refreshHandLayout();
/* 806 */     AbstractDungeon.overlayMenu.showBlackScreen(0.75F);
/* 807 */     this.numSelected = 0;
/*     */     
/* 809 */     if (Settings.isControllerMode)
/* 810 */       Gdx.input.setCursorPosition((int)((AbstractCard)this.hand.group.get(0)).hb.cX, (int)((AbstractCard)this.hand.group.get(0)).hb.cY); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\select\HandCardSelectScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */