/*     */ package com.megacrit.cardcrawl.rooms;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
/*     */ import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
/*     */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*     */ import com.megacrit.cardcrawl.ui.campfire.RecallOption;
/*     */ import com.megacrit.cardcrawl.ui.campfire.RestOption;
/*     */ import com.megacrit.cardcrawl.ui.campfire.SmithOption;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.BobEffect;
/*     */ import com.megacrit.cardcrawl.vfx.campfire.CampfireBubbleEffect;
/*     */ import com.megacrit.cardcrawl.vfx.campfire.CampfireBurningEffect;
/*     */ import com.megacrit.cardcrawl.vfx.campfire.CampfireEndingBurningEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CampfireUI
/*     */   implements ScrollBarListener
/*     */ {
/*  38 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CampfireUI");
/*  39 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public static boolean hidden = false;
/*     */   
/*     */   public boolean somethingSelected = false;
/*  44 */   private float hideStuffTimer = 0.5F; private float charAnimTimer = 2.0F;
/*  45 */   private ArrayList<AbstractCampfireOption> buttons = new ArrayList<>();
/*  46 */   private ArrayList<CampfireBubbleEffect> bubbles = new ArrayList<>();
/*  47 */   private float fireTimer = 0.0F;
/*     */   private static final float FIRE_INTERVAL = 0.05F;
/*  49 */   private ArrayList<AbstractGameEffect> flameEffect = new ArrayList<>();
/*     */   private int bubbleAmt;
/*     */   private String bubbleMsg;
/*  52 */   private BobEffect effect = new BobEffect(2.0F);
/*     */   
/*  54 */   private static final float BUTTON_START_X = Settings.WIDTH * 0.416F;
/*  55 */   private static final float BUTTON_SPACING_X = 300.0F * Settings.xScale;
/*  56 */   private static final float BUTTON_START_Y = Settings.HEIGHT / 2.0F + 180.0F * Settings.scale;
/*  57 */   private static final float BUTTON_SPACING_Y = -200.0F * Settings.scale;
/*  58 */   private static final float BUTTON_EXTRA_SPACING_Y = -70.0F * Settings.scale;
/*     */   
/*     */   private static final int MAX_BUTTONS_BEFORE_SCROLL = 6;
/*     */   
/*  62 */   private static final float START_Y = Settings.HEIGHT - 300.0F * Settings.scale;
/*     */   private boolean grabbedScreen = false;
/*  64 */   private float grabStartY = 0.0F; private float scrollY;
/*  65 */   private float targetY = this.scrollY = START_Y;
/*  66 */   private float scrollLowerBound = Settings.HEIGHT - 300.0F * Settings.scale;
/*  67 */   private float scrollUpperBound = 2400.0F * Settings.scale;
/*     */   
/*     */   private ScrollBar scrollBar;
/*     */   
/*  71 */   public ConfirmButton confirmButton = new ConfirmButton();
/*  72 */   public AbstractCampfireOption touchOption = null;
/*     */   
/*     */   public CampfireUI() {
/*  75 */     this.scrollBar = new ScrollBar(this);
/*     */     
/*  77 */     hidden = false;
/*  78 */     initializeButtons();
/*     */     
/*  80 */     if (this.buttons.size() > 2) {
/*  81 */       this.bubbleAmt = 60;
/*     */     } else {
/*  83 */       this.bubbleAmt = 40;
/*     */     } 
/*     */     
/*  86 */     this.bubbleMsg = getCampMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeButtons() {
/*  92 */     this.buttons.add(new RestOption(true));
/*  93 */     this.buttons.add(new SmithOption((AbstractDungeon.player.masterDeck
/*     */           
/*  95 */           .getUpgradableCards().size() > 0 && !ModHelper.isModEnabled("Midas"))));
/*     */     
/*  97 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  98 */       r.addCampfireOption(this.buttons);
/*     */     }
/*     */ 
/*     */     
/* 102 */     for (AbstractCampfireOption co : this.buttons) {
/* 103 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 104 */         if (!r.canUseCampfireOption(co)) {
/* 105 */           co.usable = false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (Settings.isFinalActAvailable && !Settings.hasRubyKey) {
/* 113 */       this.buttons.add(new RecallOption());
/*     */     }
/*     */ 
/*     */     
/* 117 */     boolean cannotProceed = true;
/* 118 */     for (AbstractCampfireOption opt : this.buttons) {
/* 119 */       if (opt.usable) {
/* 120 */         cannotProceed = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 125 */     if (cannotProceed) {
/* 126 */       AbstractRoom.waitTimer = 0.0F;
/* 127 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/* 132 */     updateCharacterPosition();
/* 133 */     updateTouchscreen();
/* 134 */     updateControllerInput();
/* 135 */     if (!this.scrollBar.update()) {
/* 136 */       updateScrolling();
/*     */     }
/*     */     
/* 139 */     this.effect.update();
/* 140 */     if (!hidden) {
/* 141 */       updateBubbles();
/* 142 */       updateFire();
/* 143 */       for (AbstractCampfireOption o : this.buttons) {
/* 144 */         o.update();
/*     */       }
/*     */     } 
/*     */     
/* 148 */     if (this.somethingSelected) {
/* 149 */       this.hideStuffTimer -= Gdx.graphics.getDeltaTime();
/* 150 */       if (this.hideStuffTimer < 0.0F) {
/* 151 */         hidden = true;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateTouchscreen() {
/* 157 */     if (!Settings.isTouchScreen) {
/*     */       return;
/*     */     }
/*     */     
/* 161 */     this.confirmButton.update();
/*     */ 
/*     */     
/* 164 */     if (this.confirmButton.hb.clicked && this.touchOption != null) {
/* 165 */       this.confirmButton.hb.clicked = false;
/* 166 */       this.confirmButton.hb.clickStarted = false;
/* 167 */       this.confirmButton.isDisabled = true;
/* 168 */       this.confirmButton.hide();
/*     */       
/* 170 */       this.touchOption.useOption();
/* 171 */       this.somethingSelected = true;
/* 172 */       this.touchOption = null;
/*     */     
/*     */     }
/* 175 */     else if (InputHelper.justReleasedClickLeft && this.touchOption != null) {
/* 176 */       this.touchOption = null;
/* 177 */       this.confirmButton.isDisabled = true;
/* 178 */       this.confirmButton.hide();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 183 */     if (!Settings.isControllerMode || AbstractDungeon.player.viewingRelics || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || this.somethingSelected || this.buttons
/*     */       
/* 185 */       .isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 189 */     boolean anyHovered = false;
/* 190 */     int index = 0;
/* 191 */     for (AbstractCampfireOption o : this.buttons) {
/* 192 */       if (o.hb.hovered) {
/* 193 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 196 */       index++;
/*     */     } 
/*     */     
/* 199 */     if (!anyHovered) {
/* 200 */       CInputHelper.setCursor(((AbstractCampfireOption)this.buttons.get(0)).hb);
/*     */     }
/* 202 */     else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 203 */       index--;
/* 204 */       if (index < 0) {
/* 205 */         if (this.buttons.size() == 2) {
/* 206 */           index = 1;
/*     */         } else {
/* 208 */           index = 0;
/*     */         } 
/* 210 */       } else if (index == 1) {
/* 211 */         if (this.buttons.size() == 4) {
/* 212 */           index = 3;
/*     */         } else {
/* 214 */           index = 2;
/*     */         } 
/*     */       } 
/* 217 */       CInputHelper.setCursor(((AbstractCampfireOption)this.buttons.get(index)).hb);
/* 218 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 219 */       index++;
/* 220 */       if (index > this.buttons.size() - 1) {
/* 221 */         if (this.buttons.size() == 2) {
/* 222 */           index = 0;
/* 223 */         } else if (index == 3) {
/* 224 */           index = 2;
/*     */         } else {
/* 226 */           index = 0;
/*     */         } 
/*     */       }
/* 229 */       CInputHelper.setCursor(((AbstractCampfireOption)this.buttons.get(index)).hb);
/* 230 */     } else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && this.buttons
/* 231 */       .size() > 2) {
/* 232 */       if (this.buttons.size() == 5) {
/* 233 */         index -= 2;
/* 234 */         if (index < 0) {
/* 235 */           index = 4;
/*     */         }
/* 237 */       } else if (this.buttons.size() == 3) {
/* 238 */         if (index == 0) {
/* 239 */           index = 2;
/* 240 */         } else if (index == 2) {
/* 241 */           index = 0;
/*     */         }
/*     */       
/* 244 */       } else if (index == 0) {
/* 245 */         index = 2;
/* 246 */       } else if (index == 2) {
/* 247 */         index = 0;
/* 248 */       } else if (index == 3) {
/* 249 */         index = 1;
/*     */       } else {
/* 251 */         index = 3;
/*     */       } 
/*     */       
/* 254 */       CInputHelper.setCursor(((AbstractCampfireOption)this.buttons.get(index)).hb);
/* 255 */     } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.buttons
/* 256 */       .size() > 2) {
/* 257 */       if (this.buttons.size() == 5) {
/* 258 */         if (index == 4) {
/* 259 */           index = 0;
/* 260 */         } else if (index > 2) {
/* 261 */           index = 4;
/*     */         } else {
/* 263 */           index += 2;
/*     */         } 
/* 265 */       } else if (this.buttons.size() == 4) {
/* 266 */         if (index >= 2) {
/* 267 */           index -= 2;
/*     */         } else {
/* 269 */           index += 2;
/*     */         }
/*     */       
/* 272 */       } else if (index == 0 || index == 1) {
/* 273 */         index = 2;
/*     */       } else {
/* 275 */         index = 0;
/*     */       } 
/*     */       
/* 278 */       CInputHelper.setCursor(((AbstractCampfireOption)this.buttons.get(index)).hb);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateScrolling() {
/* 284 */     int y = InputHelper.mY;
/*     */     
/* 286 */     if (!this.grabbedScreen) {
/* 287 */       if (InputHelper.scrolledDown) {
/* 288 */         this.targetY += Settings.SCROLL_SPEED;
/* 289 */       } else if (InputHelper.scrolledUp) {
/* 290 */         this.targetY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 293 */       if (InputHelper.justClickedLeft) {
/* 294 */         this.grabbedScreen = true;
/* 295 */         this.grabStartY = y - this.targetY;
/*     */       }
/*     */     
/* 298 */     } else if (InputHelper.isMouseDown) {
/* 299 */       this.targetY = y - this.grabStartY;
/*     */     } else {
/* 301 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 305 */     this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
/* 306 */     resetScrolling();
/* 307 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void resetScrolling() {
/* 311 */     if (this.targetY < this.scrollLowerBound) {
/* 312 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
/* 313 */     } else if (this.targetY > this.scrollUpperBound) {
/* 314 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateCharacterPosition() {
/* 319 */     this.charAnimTimer -= Gdx.graphics.getDeltaTime();
/* 320 */     if (this.charAnimTimer < 0.0F) {
/* 321 */       this.charAnimTimer = 0.0F;
/*     */     }
/* 323 */     AbstractDungeon.player.animX = Interpolation.exp10In.apply(0.0F, -300.0F * Settings.scale, this.charAnimTimer / 2.0F);
/*     */   }
/*     */   
/*     */   private void updateBubbles() {
/* 327 */     if (this.bubbles.size() < this.bubbleAmt) {
/* 328 */       int s = this.bubbleAmt - this.bubbles.size();
/* 329 */       for (int j = 0; j < s; j++) {
/* 330 */         this.bubbles.add(new CampfireBubbleEffect((this.bubbleAmt == 60)));
/*     */       }
/*     */     } 
/*     */     
/* 334 */     for (Iterator<CampfireBubbleEffect> i = this.bubbles.iterator(); i.hasNext(); ) {
/* 335 */       CampfireBubbleEffect bubble = i.next();
/* 336 */       bubble.update();
/* 337 */       if (bubble.isDone) {
/* 338 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateFire() {
/* 344 */     this.fireTimer -= Gdx.graphics.getDeltaTime();
/* 345 */     if (this.fireTimer < 0.0F) {
/* 346 */       this.fireTimer = 0.05F;
/* 347 */       if (AbstractDungeon.id.equals("TheEnding")) {
/* 348 */         this.flameEffect.add(new CampfireEndingBurningEffect());
/* 349 */         this.flameEffect.add(new CampfireEndingBurningEffect());
/* 350 */         this.flameEffect.add(new CampfireEndingBurningEffect());
/* 351 */         this.flameEffect.add(new CampfireEndingBurningEffect());
/*     */       } else {
/* 353 */         this.flameEffect.add(new CampfireBurningEffect());
/* 354 */         this.flameEffect.add(new CampfireBurningEffect());
/*     */       } 
/*     */     } 
/*     */     
/* 358 */     for (Iterator<AbstractGameEffect> i = this.flameEffect.iterator(); i.hasNext(); ) {
/* 359 */       AbstractGameEffect fires = i.next();
/* 360 */       fires.update();
/* 361 */       if (fires.isDone) {
/* 362 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reopen() {
/* 371 */     hidden = false;
/* 372 */     this.hideStuffTimer = 0.5F;
/* 373 */     this.somethingSelected = false;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 377 */     if (!hidden) {
/* 378 */       renderFire(sb);
/* 379 */       AbstractDungeon.player.render(sb);
/*     */       
/* 381 */       for (CampfireBubbleEffect e : this.bubbles) {
/* 382 */         e.render(sb, 950.0F * Settings.xScale, Settings.HEIGHT / 2.0F + 60.0F * Settings.yScale + this.effect.y / 4.0F);
/*     */       }
/*     */       
/* 385 */       FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.bubbleMsg, 950.0F * Settings.xScale, Settings.HEIGHT / 2.0F + 310.0F * Settings.scale + this.effect.y / 3.0F, Settings.CREAM_COLOR, 1.2F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 394 */       renderCampfireButtons(sb);
/*     */       
/* 396 */       if (shouldShowScrollBar()) {
/* 397 */         this.scrollBar.render(sb);
/*     */       }
/*     */       
/* 400 */       if (Settings.isTouchScreen) {
/* 401 */         this.confirmButton.render(sb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getCampMessage() {
/* 407 */     ArrayList<String> msgs = new ArrayList<>();
/* 408 */     msgs.add(TEXT[0]);
/* 409 */     msgs.add(TEXT[1]);
/* 410 */     msgs.add(TEXT[2]);
/* 411 */     msgs.add(TEXT[3]);
/*     */     
/* 413 */     if (this.buttons.size() > 2) {
/* 414 */       msgs.add(TEXT[4]);
/*     */     }
/*     */     
/* 417 */     if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth / 2) {
/* 418 */       msgs.add(TEXT[5]);
/* 419 */       msgs.add(TEXT[6]);
/*     */     } 
/*     */     
/* 422 */     return msgs.get(MathUtils.random(msgs.size() - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderFire(SpriteBatch sb) {
/* 431 */     for (AbstractGameEffect e : this.flameEffect) {
/* 432 */       e.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderCampfireButtons(SpriteBatch sb) {
/* 437 */     float buttonX = 0.0F;
/* 438 */     float buttonY = 0.0F;
/*     */     
/* 440 */     int maxPossibleStartingIndex = this.buttons.size() + 1 - 6;
/* 441 */     int indexToStartAt = Math.max(
/* 442 */         Math.min(
/* 443 */           (int)((maxPossibleStartingIndex + 1) * MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY)), maxPossibleStartingIndex), 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 451 */     indexToStartAt = MathUtils.ceil((indexToStartAt / 2)) * 2;
/*     */     
/* 453 */     for (AbstractCampfireOption co : this.buttons) {
/* 454 */       if (this.buttons.indexOf(co) >= indexToStartAt && this.buttons.indexOf(co) < indexToStartAt + 6) {
/*     */ 
/*     */         
/* 457 */         if (this.buttons.indexOf(co) == this.buttons.size() - 1 && (this.buttons.size() - indexToStartAt) % 2 == 1) {
/*     */           
/* 459 */           buttonX = BUTTON_START_X + BUTTON_SPACING_X / 2.0F;
/* 460 */         } else if ((this.buttons.indexOf(co) - indexToStartAt) % 2 == 0) {
/*     */           
/* 462 */           buttonX = BUTTON_START_X;
/*     */         } else {
/*     */           
/* 465 */           buttonX = BUTTON_START_X + BUTTON_SPACING_X;
/*     */         } 
/*     */         
/* 468 */         if ((this.buttons.indexOf(co) - indexToStartAt) / 2 == 0) {
/*     */           
/* 470 */           buttonY = BUTTON_START_Y;
/*     */         } else {
/*     */           
/* 473 */           buttonY = BUTTON_START_Y + BUTTON_SPACING_Y * MathUtils.floor(((this.buttons
/* 474 */               .indexOf(co) - indexToStartAt) / 2)) + BUTTON_EXTRA_SPACING_Y;
/*     */         } 
/*     */       } else {
/*     */         
/* 478 */         buttonX = Settings.WIDTH * 2.0F;
/* 479 */         buttonY = Settings.HEIGHT * 2.0F;
/*     */       } 
/* 481 */       co.setPosition(buttonX, buttonY);
/* 482 */       co.render(sb);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 488 */     this.scrollY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 489 */     this.targetY = this.scrollY;
/* 490 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 494 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
/* 495 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */   
/*     */   private boolean shouldShowScrollBar() {
/* 499 */     return (this.buttons.size() > 6);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\CampfireUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */