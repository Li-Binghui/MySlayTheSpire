/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.DungeonMap;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.scene.LevelTransitionTextOverlayEffect;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DungeonMapScreen
/*     */ {
/*  34 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DungeonMapScreen");
/*  35 */   public static final String[] TEXT = uiStrings.TEXT;
/*  36 */   public DungeonMap map = new DungeonMap();
/*     */ 
/*     */   
/*  39 */   private ArrayList<MapRoomNode> visibleMapNodes = new ArrayList<>();
/*     */   public boolean dismissable = false;
/*     */   private float mapScrollUpperLimit;
/*  42 */   private static final float MAP_UPPER_SCROLL_DEFAULT = -2300.0F * Settings.scale;
/*  43 */   private static final float MAP_UPPER_SCROLL_FINAL_ACT = -300.0F * Settings.scale;
/*  44 */   private static final float MAP_SCROLL_LOWER = 190.0F * Settings.scale;
/*  45 */   public static final float ICON_SPACING_Y = 120.0F * Settings.scale;
/*  46 */   public static float offsetY = -100.0F * Settings.scale;
/*  47 */   private float targetOffsetY = offsetY;
/*  48 */   private float grabStartY = 0.0F;
/*     */   private boolean grabbedScreen = false;
/*     */   public boolean clicked = false;
/*  51 */   public float clickTimer = 0.0F;
/*     */   
/*     */   private float clickStartX;
/*     */   private float clickStartY;
/*  55 */   private float scrollWaitTimer = 0.0F;
/*     */   
/*     */   private static final float SCROLL_WAIT_TIME = 1.0F;
/*     */   private static final float SPECIAL_ANIMATE_TIME = 3.0F;
/*  59 */   private Color oscillatingColor = Settings.GOLD_COLOR.cpy();
/*     */   private float oscillatingTimer;
/*     */   private float oscillatingFader;
/*  62 */   private float scrollBackTimer = 0.0F;
/*  63 */   public Hitbox mapNodeHb = null;
/*  64 */   private static final float RETICLE_DIST = 20.0F * Settings.scale;
/*     */   private static final int RETICLE_W = 36;
/*     */   
/*     */   public DungeonMapScreen() {
/*  68 */     this.oscillatingFader = 0.0F;
/*  69 */     this.oscillatingTimer = 0.0F;
/*  70 */     this.oscillatingColor.a = 0.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  74 */     if (this.scrollWaitTimer < 0.0F && Settings.isControllerMode && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !this.map.legend.isLegendHighlighted)
/*     */     {
/*  76 */       if (this.scrollBackTimer > 0.0F) {
/*  77 */         this.scrollBackTimer -= Gdx.graphics.getDeltaTime();
/*  78 */         if (Gdx.input.getY() > Settings.HEIGHT * 0.85F) {
/*  79 */           this.targetOffsetY += Settings.SCROLL_SPEED * 2.0F;
/*  80 */         } else if (Gdx.input.getY() < Settings.HEIGHT * 0.15F) {
/*  81 */           this.targetOffsetY -= Settings.SCROLL_SPEED * 2.0F;
/*     */         } 
/*     */         
/*  84 */         if (this.targetOffsetY > MAP_SCROLL_LOWER) {
/*  85 */           this.targetOffsetY = MAP_SCROLL_LOWER;
/*  86 */         } else if (this.targetOffsetY < this.mapScrollUpperLimit) {
/*  87 */           this.targetOffsetY = this.mapScrollUpperLimit;
/*     */         } 
/*  89 */         offsetY = MathUtils.lerp(offsetY, this.targetOffsetY, Gdx.graphics
/*     */ 
/*     */             
/*  92 */             .getDeltaTime() * 12.0F);
/*     */       } 
/*     */     }
/*     */     
/*  96 */     this.map.update();
/*     */     
/*  98 */     if (AbstractDungeon.isScreenUp) {
/*  99 */       for (MapRoomNode n : this.visibleMapNodes) {
/* 100 */         n.update();
/*     */       }
/*     */     }
/*     */     
/* 104 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !this.dismissable && this.scrollWaitTimer < 0.0F) {
/* 105 */       oscillateColor();
/*     */     }
/*     */     
/* 108 */     for (AbstractGameEffect e : AbstractDungeon.topLevelEffects) {
/* 109 */       if (e instanceof com.megacrit.cardcrawl.vfx.MapCircleEffect) {
/*     */         return;
/*     */       }
/*     */     } 
/* 113 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/* 114 */       updateYOffset();
/*     */     }
/*     */     
/* 117 */     updateMouse();
/* 118 */     updateControllerInput();
/*     */     
/* 120 */     if (Settings.isControllerMode && this.mapNodeHb != null && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/* 121 */       int tmpY = (int)(Settings.HEIGHT - this.mapNodeHb.cY);
/* 122 */       if (tmpY < 1) {
/* 123 */         tmpY = 1;
/* 124 */       } else if (tmpY > Settings.HEIGHT - 1) {
/* 125 */         tmpY = Settings.HEIGHT - 1;
/*     */       } 
/*     */       
/* 128 */       Gdx.input.setCursorPosition((int)this.mapNodeHb.cX, tmpY);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateMouse() {
/* 133 */     if (this.clicked) {
/* 134 */       this.clicked = false;
/*     */     }
/*     */     
/* 137 */     if (InputHelper.justReleasedClickLeft && this.clickTimer < 0.4F && Vector2.dst(this.clickStartX, this.clickStartY, InputHelper.mX, InputHelper.mY) < Settings.CLICK_DIST_THRESHOLD)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 142 */       this.clicked = true;
/*     */     }
/*     */     
/* 145 */     if (InputHelper.justClickedLeft || (CInputActionSet.select.isJustPressed() && AbstractDungeon.topPanel.potionUi.isHidden && !AbstractDungeon.topPanel.selectPotionMode)) {
/*     */       
/* 147 */       this.clickTimer = 0.0F;
/* 148 */       this.clickStartX = InputHelper.mX;
/* 149 */       this.clickStartY = InputHelper.mY;
/* 150 */     } else if (InputHelper.isMouseDown) {
/* 151 */       this.clickTimer += Gdx.graphics.getDeltaTime();
/*     */     } 
/*     */     
/* 154 */     if (CInputActionSet.select.isJustPressed() && this.clickTimer < 0.4F && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP)
/*     */     {
/* 156 */       this.clicked = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 161 */     if (this.scrollWaitTimer > 0.0F || !Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || this.map.legend.isLegendHighlighted || AbstractDungeon.player.viewingRelics) {
/*     */ 
/*     */       
/* 164 */       this.mapNodeHb = null;
/*     */       
/*     */       return;
/*     */     } 
/* 168 */     if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 169 */       this.targetOffsetY += Settings.SCROLL_SPEED * 4.0F; return;
/*     */     } 
/* 171 */     if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 172 */       this.targetOffsetY -= Settings.SCROLL_SPEED * 4.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 176 */     if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/* 177 */       .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 178 */       this.scrollBackTimer = 0.1F;
/*     */     }
/*     */     
/* 181 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/* 182 */       ArrayList<MapRoomNode> nodes = new ArrayList<>();
/* 183 */       if (!AbstractDungeon.firstRoomChosen) {
/* 184 */         for (MapRoomNode n : this.visibleMapNodes) {
/* 185 */           if (n.y == 0) {
/* 186 */             nodes.add(n);
/*     */           }
/*     */         } 
/*     */       } else {
/* 190 */         for (MapRoomNode n : this.visibleMapNodes) {
/* 191 */           boolean flightMatters = (AbstractDungeon.player.hasRelic("WingedGreaves") || ModHelper.isModEnabled("Flight"));
/*     */           
/* 193 */           if (AbstractDungeon.currMapNode.isConnectedTo(n) || (flightMatters && AbstractDungeon.currMapNode
/* 194 */             .wingedIsConnectedTo(n))) {
/* 195 */             nodes.add(n);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 200 */       boolean anyHovered = false;
/* 201 */       int index = 0;
/* 202 */       for (MapRoomNode n : nodes) {
/* 203 */         if (n.hb.hovered) {
/* 204 */           anyHovered = true;
/*     */           break;
/*     */         } 
/* 207 */         index++;
/*     */       } 
/*     */ 
/*     */       
/* 211 */       if (!anyHovered && this.mapNodeHb == null && !nodes.isEmpty()) {
/* 212 */         Gdx.input.setCursorPosition(
/* 213 */             (int)((MapRoomNode)nodes.get(nodes.size() / 2)).hb.cX, Settings.HEIGHT - 
/* 214 */             (int)((MapRoomNode)nodes.get(nodes.size() / 2)).hb.cY);
/* 215 */         this.mapNodeHb = ((MapRoomNode)nodes.get(nodes.size() / 2)).hb;
/*     */       
/*     */       }
/* 218 */       else if (!anyHovered && nodes.isEmpty()) {
/* 219 */         Gdx.input.setCursorPosition((int)AbstractDungeon.dungeonMapScreen.map.bossHb.cX, Settings.HEIGHT - (int)AbstractDungeon.dungeonMapScreen.map.bossHb.cY);
/*     */ 
/*     */         
/* 222 */         this.mapNodeHb = null;
/*     */ 
/*     */       
/*     */       }
/* 226 */       else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 227 */         index--;
/* 228 */         if (index < 0) {
/* 229 */           index = nodes.size() - 1;
/*     */         }
/* 231 */         Gdx.input.setCursorPosition(
/* 232 */             (int)((MapRoomNode)nodes.get(index)).hb.cX, Settings.HEIGHT - 
/* 233 */             (int)((MapRoomNode)nodes.get(index)).hb.cY);
/*     */         
/* 235 */         this.mapNodeHb = ((MapRoomNode)nodes.get(index)).hb;
/* 236 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 237 */         index++;
/* 238 */         if (index > nodes.size() - 1) {
/* 239 */           index = 0;
/*     */         }
/* 241 */         Gdx.input.setCursorPosition(
/* 242 */             (int)((MapRoomNode)nodes.get(index)).hb.cX, Settings.HEIGHT - 
/* 243 */             (int)((MapRoomNode)nodes.get(index)).hb.cY);
/* 244 */         this.mapNodeHb = ((MapRoomNode)nodes.get(index)).hb;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateYOffset() {
/* 253 */     if (this.grabbedScreen) {
/* 254 */       if (InputHelper.isMouseDown) {
/* 255 */         this.targetOffsetY = InputHelper.mY - this.grabStartY;
/*     */       } else {
/* 257 */         this.grabbedScreen = false;
/*     */       } 
/* 259 */     } else if (this.scrollWaitTimer < 0.0F) {
/* 260 */       if (InputHelper.scrolledDown) {
/* 261 */         this.targetOffsetY += Settings.MAP_SCROLL_SPEED;
/* 262 */       } else if (InputHelper.scrolledUp) {
/* 263 */         this.targetOffsetY -= Settings.MAP_SCROLL_SPEED;
/*     */       } 
/* 265 */       if (InputHelper.justClickedLeft && this.scrollWaitTimer < 0.0F) {
/* 266 */         this.grabbedScreen = true;
/* 267 */         this.grabStartY = InputHelper.mY - this.targetOffsetY;
/*     */       } 
/*     */     } 
/*     */     
/* 271 */     resetScrolling();
/* 272 */     updateAnimation();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 279 */     if (this.targetOffsetY < this.mapScrollUpperLimit) {
/* 280 */       this.targetOffsetY = MathHelper.scrollSnapLerpSpeed(this.targetOffsetY, this.mapScrollUpperLimit);
/* 281 */     } else if (this.targetOffsetY > MAP_SCROLL_LOWER) {
/* 282 */       this.targetOffsetY = MathHelper.scrollSnapLerpSpeed(this.targetOffsetY, MAP_SCROLL_LOWER);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateAnimation() {
/* 287 */     this.scrollWaitTimer -= Gdx.graphics.getDeltaTime();
/* 288 */     if (this.scrollWaitTimer < 0.0F) {
/* 289 */       offsetY = MathUtils.lerp(offsetY, this.targetOffsetY, Gdx.graphics.getDeltaTime() * 12.0F);
/* 290 */     } else if (this.scrollWaitTimer < 3.0F) {
/* 291 */       offsetY = Interpolation.exp10.apply(MAP_SCROLL_LOWER, this.mapScrollUpperLimit, this.scrollWaitTimer / 3.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateImage() {
/* 302 */     this.visibleMapNodes.clear();
/*     */ 
/*     */     
/* 305 */     for (ArrayList<MapRoomNode> rows : (Iterable<ArrayList<MapRoomNode>>)CardCrawlGame.dungeon.getMap()) {
/* 306 */       for (MapRoomNode node : rows) {
/*     */         
/* 308 */         if (node.hasEdges()) {
/* 309 */           this.visibleMapNodes.add(node);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 316 */     this.map.render(sb);
/*     */     
/* 318 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP)
/*     */     {
/* 320 */       for (MapRoomNode n : this.visibleMapNodes) {
/* 321 */         n.render(sb);
/*     */       }
/*     */     }
/*     */     
/* 325 */     this.map.renderBossIcon(sb);
/*     */     
/* 327 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !this.dismissable && this.scrollWaitTimer < 0.0F) {
/* 328 */       FontHelper.renderDeckViewTip(sb, TEXT[0], 80.0F * Settings.scale, this.oscillatingColor);
/*     */     }
/*     */     
/* 331 */     renderControllerUi(sb);
/*     */   }
/*     */   
/*     */   private void renderControllerUi(SpriteBatch sb) {
/* 335 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/* 338 */     if (this.mapNodeHb != null) {
/* 339 */       renderReticle(sb, this.mapNodeHb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void oscillateColor() {
/* 344 */     this.oscillatingFader += Gdx.graphics.getDeltaTime();
/* 345 */     if (this.oscillatingFader > 1.0F) {
/* 346 */       this.oscillatingFader = 1.0F;
/* 347 */       this.oscillatingTimer += Gdx.graphics.getDeltaTime() * 5.0F;
/*     */     } 
/* 349 */     this.oscillatingColor.a = (0.33F + (MathUtils.cos(this.oscillatingTimer) + 1.0F) / 3.0F) * this.oscillatingFader;
/*     */   }
/*     */   
/*     */   public void open(boolean doScrollingAnimation) {
/* 353 */     this.mapNodeHb = null;
/*     */     
/* 355 */     if (!AbstractDungeon.id.equals("TheEnding")) {
/* 356 */       this.mapScrollUpperLimit = MAP_UPPER_SCROLL_DEFAULT;
/*     */     } else {
/* 358 */       this.mapScrollUpperLimit = MAP_UPPER_SCROLL_FINAL_ACT;
/*     */     } 
/*     */     
/* 361 */     AbstractDungeon.player.releaseCard();
/* 362 */     this.map.legend.isLegendHighlighted = false;
/* 363 */     if (Settings.isDebug) {
/* 364 */       doScrollingAnimation = false;
/*     */     }
/*     */     
/* 367 */     InputHelper.justClickedLeft = false;
/* 368 */     this.clicked = false;
/* 369 */     this.clickTimer = 999.0F;
/* 370 */     this.grabbedScreen = false;
/*     */     
/* 372 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 373 */     this.map.show();
/* 374 */     this.dismissable = !doScrollingAnimation;
/* 375 */     if (MathUtils.randomBoolean()) {
/* 376 */       CardCrawlGame.sound.play("MAP_OPEN", 0.1F);
/*     */     } else {
/* 378 */       CardCrawlGame.sound.play("MAP_OPEN_2", 0.1F);
/*     */     } 
/*     */ 
/*     */     
/* 382 */     if (doScrollingAnimation) {
/* 383 */       this.mapNodeHb = null;
/* 384 */       AbstractDungeon.topLevelEffects.add(new LevelTransitionTextOverlayEffect(AbstractDungeon.name, AbstractDungeon.levelNum));
/*     */       
/* 386 */       this.scrollWaitTimer = 4.0F;
/* 387 */       offsetY = this.mapScrollUpperLimit;
/* 388 */       this.targetOffsetY = MAP_SCROLL_LOWER;
/*     */     } else {
/* 390 */       this.scrollWaitTimer = 0.0F;
/* 391 */       AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/* 392 */       if (AbstractDungeon.getCurrMapNode() == null) {
/* 393 */         offsetY = this.mapScrollUpperLimit;
/*     */       } else {
/* 395 */         offsetY = -50.0F * Settings.scale + (AbstractDungeon.getCurrMapNode()).y * -ICON_SPACING_Y;
/*     */       } 
/* 397 */       this.targetOffsetY = offsetY;
/*     */     } 
/*     */     
/* 400 */     AbstractDungeon.dynamicBanner.hide();
/* 401 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.MAP;
/* 402 */     AbstractDungeon.isScreenUp = true;
/* 403 */     this.grabStartY = 0.0F;
/*     */     
/* 405 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 406 */     AbstractDungeon.overlayMenu.hideCombatPanels();
/* 407 */     AbstractDungeon.overlayMenu.endTurnButton.hide();
/* 408 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 409 */     updateImage();
/*     */   }
/*     */   
/*     */   public void close() {
/* 413 */     this.map.hide();
/* 414 */     AbstractDungeon.overlayMenu.cancelButton.hide();
/* 415 */     this.clicked = false;
/*     */   }
/*     */   
/*     */   public void closeInstantly() {
/* 419 */     this.map.hideInstantly();
/* 420 */     if (AbstractDungeon.overlayMenu != null) {
/* 421 */       AbstractDungeon.overlayMenu.cancelButton.hideInstantly();
/*     */     }
/* 423 */     this.clicked = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderReticle(SpriteBatch sb, Hitbox hb) {
/* 433 */     renderReticleCorner(sb, -hb.width / 2.0F - RETICLE_DIST, hb.height / 2.0F + RETICLE_DIST, hb, false, false);
/* 434 */     renderReticleCorner(sb, hb.width / 2.0F + RETICLE_DIST, hb.height / 2.0F + RETICLE_DIST, hb, true, false);
/* 435 */     renderReticleCorner(sb, -hb.width / 2.0F - RETICLE_DIST, -hb.height / 2.0F - RETICLE_DIST, hb, false, true);
/* 436 */     renderReticleCorner(sb, hb.width / 2.0F + RETICLE_DIST, -hb.height / 2.0F - RETICLE_DIST, hb, true, true);
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
/*     */   private void renderReticleCorner(SpriteBatch sb, float x, float y, Hitbox hb, boolean flipX, boolean flipY) {
/* 450 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.map.targetAlpha / 4.0F));
/* 451 */     sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F + 4.0F * Settings.scale, hb.cY + y - 18.0F - 4.0F * Settings.scale, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
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
/*     */     
/* 470 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.map.targetAlpha));
/* 471 */     sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F, hb.cY + y - 18.0F, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DungeonMapScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */