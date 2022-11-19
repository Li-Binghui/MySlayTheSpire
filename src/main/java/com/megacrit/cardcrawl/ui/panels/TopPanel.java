/*      */ package com.megacrit.cardcrawl.ui.panels;
/*      */ 
/*      */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.DailyScreen;
import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HealPanelEffect;
import com.megacrit.cardcrawl.vfx.combat.PingHpEffect;
import de.robojumper.ststwitch.TwitchConfig;
import de.robojumper.ststwitch.TwitchConnection;
import de.robojumper.ststwitch.TwitchPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TopPanel
/*      */ {
/*   51 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Top Panel Tips");
/*      */   
/*   53 */   public static final String[] MSG = tutorialStrings.TEXT;
/*   54 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*   55 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TopPanel");
/*   56 */   public static final String[] TEXT = uiStrings.TEXT;
/*      */ 
/*      */   
/*   59 */   private static final float TOPBAR_H = Settings.isMobile ? (164.0F * Settings.scale) : (128.0F * Settings.scale);
/*   60 */   private static final float TIP_Y = Settings.HEIGHT - 120.0F * Settings.scale, TIP_OFF_X = 140.0F * Settings.scale;
/*   61 */   private static final float ICON_W = 64.0F * Settings.scale;
/*   62 */   private static final float ICON_Y = Settings.isMobile ? (Settings.HEIGHT - ICON_W - 12.0F * Settings.scale) : (Settings.HEIGHT - ICON_W);
/*      */   
/*   64 */   private static final float INFO_TEXT_Y = Settings.isMobile ? (Settings.HEIGHT - 36.0F * Settings.scale) : (Settings.HEIGHT - 24.0F * Settings.scale);
/*      */   
/*      */   private String name;
/*      */   
/*      */   private String title;
/*   69 */   private static final float NAME_Y = Settings.isMobile ? (Settings.HEIGHT - 12.0F * Settings.scale) : (Settings.HEIGHT - 20.0F * Settings.scale);
/*      */   
/*   71 */   private GlyphLayout gl = new GlyphLayout();
/*      */   private float nameX;
/*      */   private float titleX;
/*      */   private float titleY;
/*   75 */   private static final Color DISABLED_BTN_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.4F);
/*   76 */   private static final float TOP_RIGHT_TIP_X = 1550.0F * Settings.scale;
/*   77 */   private static final float TOP_RIGHT_PAD_X = 10.0F * Settings.scale;
/*   78 */   private static final float SETTINGS_X = Settings.WIDTH - (ICON_W + TOP_RIGHT_PAD_X) * 1.0F;
/*   79 */   private float settingsAngle = 0.0F;
/*   80 */   private static final float DECK_X = Settings.WIDTH - (ICON_W + TOP_RIGHT_PAD_X) * 2.0F;
/*   81 */   private float deckAngle = 0.0F;
/*   82 */   private static final float MAP_X = Settings.WIDTH - (ICON_W + TOP_RIGHT_PAD_X) * 3.0F;
/*   83 */   private float mapAngle = -5.0F;
/*      */   private boolean settingsButtonDisabled = true;
/*   85 */   private float rotateTimer = 0.0F;
/*      */   private boolean deckButtonDisabled = true;
/*      */   private boolean mapButtonDisabled = true;
/*      */   private float hpIconX;
/*   89 */   private static final float HP_TIP_W = 150.0F * Settings.scale;
/*   90 */   private static final float HP_NUM_OFFSET_X = 60.0F * Settings.scale;
/*      */   
/*      */   private float goldIconX;
/*      */   
/*   94 */   private static final float GOLD_TIP_W = 120.0F * Settings.scale;
/*   95 */   private static final float GOLD_NUM_OFFSET_X = 65.0F * Settings.scale;
/*      */ 
/*      */   
/*   98 */   private static Texture potionSelectBox = null;
/*   99 */   public PotionPopUp potionUi = new PotionPopUp();
/*      */   private static final float POTION_PITCH_VAR = 0.1F;
/*      */   public static float potionX;
/*  102 */   private float flashRedTimer = 0.0F;
/*      */   private static final float FLASH_RED_TIME = 1.0F;
/*      */   public boolean potionCombine = false;
/*  105 */   public int combinePotionSlot = 0;
/*      */ 
/*      */   
/*  108 */   public Hitbox leftScrollHb = new Hitbox(64.0F * Settings.scale, 64.0F * Settings.scale);
/*  109 */   public Hitbox rightScrollHb = new Hitbox(64.0F * Settings.scale, 64.0F * Settings.scale);
/*      */   
/*      */   public boolean selectPotionMode = false;
/*      */   
/*  113 */   private TopSection section = TopSection.NONE;
/*      */ 
/*      */   
/*  116 */   private String ascensionString = "";
/*      */   
/*      */   private static float floorX;
/*      */   
/*      */   private static float dailyModX;
/*      */   
/*      */   public Hitbox settingsHb;
/*      */   public Hitbox deckHb;
/*  124 */   public Hitbox[] modHbs = new Hitbox[0]; public Hitbox mapHb; public Hitbox goldHb;
/*      */   public Hitbox hpHb;
/*      */   public Hitbox ascensionHb;
/*      */   public Hitbox timerHb;
/*      */   public Optional<TwitchPanel> twitch;
/*  129 */   private static final Logger logger = LogManager.getLogger(TopPanel.class.getName());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TopPanel() {
/*  136 */     this.settingsHb = new Hitbox(ICON_W, ICON_W);
/*  137 */     this.deckHb = new Hitbox(ICON_W, ICON_W);
/*  138 */     this.mapHb = new Hitbox(ICON_W, ICON_W);
/*  139 */     this.ascensionHb = new Hitbox(210.0F * Settings.scale, ICON_W);
/*  140 */     this.timerHb = new Hitbox(140.0F * Settings.scale, ICON_W);
/*  141 */     this.timerHb.move(1610.0F * Settings.scale, ICON_Y + ICON_W / 2.0F);
/*  142 */     this.settingsHb.move(SETTINGS_X + ICON_W / 2.0F, ICON_Y + ICON_W / 2.0F);
/*  143 */     this.deckHb.move(DECK_X + ICON_W / 2.0F, ICON_Y + ICON_W / 2.0F);
/*  144 */     this.mapHb.move(MAP_X + ICON_W / 2.0F, ICON_Y + ICON_W / 2.0F);
/*  145 */     this.leftScrollHb.move(32.0F * Settings.scale, Settings.HEIGHT - 102.0F * Settings.scale);
/*  146 */     this.rightScrollHb.move(Settings.WIDTH - 32.0F * Settings.scale, Settings.HEIGHT - 102.0F * Settings.scale);
/*      */     
/*  148 */     if (potionSelectBox == null) {
/*  149 */       potionSelectBox = ImageMaster.loadImage("images/ui/potionPopUp/potionSelectBox.png");
/*      */     }
/*      */ 
/*      */     
/*  153 */     if (TwitchConfig.configFileExists()) {
/*  154 */       logger.info("Twitch Integration enabled due to presence of 'twitch.properties` file.");
/*  155 */       Optional<TwitchConfig> optTwitchConfig = TwitchConfig.readConfig();
/*  156 */       if (optTwitchConfig.isPresent()) {
/*  157 */         TwitchConfig twitchConfig = optTwitchConfig.get();
/*  158 */         if (twitchConfig.isEnabled()) {
/*      */           try {
/*  160 */             this.twitch = Optional.of(new TwitchPanel(new TwitchConnection(twitchConfig)));
/*  161 */           } catch (IOException e) {
/*  162 */             logger.info("ERROR: ", e);
/*  163 */             this.twitch = Optional.empty();
/*      */           } 
/*  165 */           logger.info("Started Twitch Panel");
/*      */         } else {
/*  167 */           logger.info("Not starting twitch integration because enabled=" + twitchConfig.isEnabled());
/*  168 */           this.twitch = Optional.empty();
/*      */         } 
/*      */       } else {
/*  171 */         logger.info("Not starting twitch integration because config is invalid.");
/*  172 */         this.twitch = Optional.empty();
/*      */       } 
/*      */     } else {
/*  175 */       logger.info("Twitch Integration disabled due to missing 'twitch.properties` file.");
/*  176 */       this.twitch = Optional.empty();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlayerName() {
/*  184 */     this.name = CardCrawlGame.playerName;
/*      */     
/*  186 */     if (!Settings.isEndless && !Settings.isFinalActAvailable) {
/*  187 */       this.nameX = 24.0F * Settings.scale;
/*      */     } else {
/*  189 */       this.nameX = 88.0F * Settings.scale;
/*      */     } 
/*      */     
/*  192 */     this.title = AbstractDungeon.player.title;
/*  193 */     this.gl.setText(FontHelper.panelNameFont, this.name);
/*  194 */     this.titleX = Settings.isMobile ? (this.nameX + 42.0F * Settings.scale) : (this.gl.width + this.nameX + 18.0F * Settings.scale);
/*  195 */     this.titleY = Settings.HEIGHT - 26.0F * Settings.scale;
/*  196 */     this.gl.setText(FontHelper.tipBodyFont, this.title);
/*  197 */     this.hpIconX = this.titleX + this.gl.width + 20.0F * Settings.scale;
/*  198 */     this.goldIconX = Settings.isMobile ? (this.hpIconX + 172.0F * Settings.scale) : (this.hpIconX + 162.0F * Settings.scale);
/*  199 */     this.gl.reset();
/*  200 */     potionX = Settings.isMobile ? (this.goldIconX + 166.0F * Settings.scale) : (this.goldIconX + 154.0F * Settings.scale);
/*  201 */     floorX = potionX + 310.0F * Settings.scale;
/*  202 */     dailyModX = floorX + 366.0F * Settings.scale;
/*      */     
/*  204 */     int index = 0;
/*  205 */     for (AbstractPotion tmpPotion : AbstractDungeon.player.potions) {
/*  206 */       tmpPotion.adjustPosition(index);
/*  207 */       index++;
/*      */     } 
/*      */     
/*  210 */     setupAscensionMode();
/*      */     
/*  212 */     if (ModHelper.enabledMods.size() > 0) {
/*  213 */       this.modHbs = new Hitbox[ModHelper.enabledMods.size()];
/*  214 */       for (int i = 0; i < this.modHbs.length; i++) {
/*  215 */         this.modHbs[i] = new Hitbox(52.0F * Settings.scale, ICON_W);
/*  216 */         this.modHbs[i].move(dailyModX + i * 52.0F * Settings.scale, ICON_Y + ICON_W / 2.0F);
/*      */       } 
/*      */     } 
/*      */     
/*  220 */     this.twitch.ifPresent(twitchPanel -> twitchPanel.setPosition(floorX - 80.0F * Settings.scale, Settings.HEIGHT));
/*  221 */     AbstractDungeon.player.adjustPotionPositions();
/*  222 */     adjustHitboxes();
/*      */   }
/*      */   
/*      */   public void setupAscensionMode() {
/*  226 */     if (!AbstractDungeon.isAscensionMode) {
/*      */       return;
/*      */     }
/*      */     
/*  230 */     this.ascensionHb.move(floorX + 100.0F * Settings.scale, ICON_Y + ICON_W / 2.0F);
/*  231 */     if (AbstractDungeon.isAscensionMode) {
/*  232 */       StringBuilder sb = new StringBuilder();
/*  233 */       for (int i = 0; i < AbstractDungeon.ascensionLevel; i++) {
/*  234 */         sb.append(CharacterSelectScreen.A_TEXT[i]);
/*  235 */         if (i != AbstractDungeon.ascensionLevel - 1) {
/*  236 */           sb.append(" NL ");
/*      */         }
/*      */       } 
/*  239 */       this.ascensionString = sb.toString();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void unhoverHitboxes() {
/*  244 */     this.settingsHb.unhover();
/*  245 */     this.deckHb.unhover();
/*  246 */     this.mapHb.unhover();
/*  247 */     this.goldHb.unhover();
/*  248 */     this.hpHb.unhover();
/*      */     
/*  250 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  251 */       r.hb.unhover();
/*      */     }
/*      */     
/*  254 */     this.twitch.ifPresent(TwitchPanel::unhover);
/*      */   }
/*      */   
/*      */   private void adjustHitboxes() {
/*  258 */     this.hpHb = new Hitbox(HP_TIP_W, ICON_W);
/*  259 */     this.hpHb.move(this.hpIconX + HP_TIP_W / 2.0F, ICON_Y + ICON_W / 2.0F);
/*  260 */     this.goldHb = new Hitbox(GOLD_TIP_W, ICON_W);
/*  261 */     this.goldHb.move(this.goldIconX + GOLD_TIP_W / 2.0F, ICON_Y + ICON_W / 2.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/*  268 */     if (AbstractDungeon.screen != null && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.UNLOCK && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NO_INTERACT) {
/*      */ 
/*      */       
/*  271 */       AbstractRelic.updateOffsetX();
/*  272 */       updateGold();
/*  273 */       updatePotions();
/*  274 */       updateRelics();
/*  275 */       this.potionUi.update();
/*      */       
/*  277 */       if (Settings.isControllerMode) {
/*  278 */         if (CInputActionSet.topPanel.isJustPressed() && !CardCrawlGame.isPopupOpen && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.FTUE) {
/*      */ 
/*      */           
/*  281 */           CInputActionSet.topPanel.unpress();
/*  282 */           AbstractDungeon.player.viewingRelics = false;
/*  283 */           this.selectPotionMode = !this.selectPotionMode;
/*  284 */           if (!this.selectPotionMode) {
/*  285 */             Gdx.input.setCursorPosition(0, 0);
/*      */           } else {
/*  287 */             AbstractDungeon.player.releaseCard();
/*  288 */             CInputHelper.setCursor(((AbstractPotion)AbstractDungeon.player.potions.get(0)).hb);
/*      */           } 
/*  290 */         } else if (CInputActionSet.cancel.isJustPressed() || (CInputActionSet.pageLeftViewDeck.isJustPressed() && !CardCrawlGame.isPopupOpen)) {
/*      */           
/*  292 */           if (this.selectPotionMode) {
/*  293 */             this.selectPotionMode = false;
/*  294 */             Gdx.input.setCursorPosition(0, 0);
/*  295 */             CInputActionSet.cancel.unpress();
/*      */           } 
/*      */         } 
/*      */         
/*  299 */         if (this.selectPotionMode && this.potionUi.isHidden && !this.potionUi.targetMode) {
/*  300 */           updateControllerInput();
/*  301 */         } else if (AbstractDungeon.player.viewingRelics) {
/*  302 */           AbstractDungeon.player.updateViewRelicControls();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  307 */     if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NO_INTERACT && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.DOOR_UNLOCK) {
/*      */       
/*  309 */       if (ModHelper.enabledMods.size() > 0) {
/*  310 */         for (Hitbox hb : this.modHbs) {
/*  311 */           hb.update();
/*      */         }
/*      */       }
/*      */       
/*  315 */       this.hpHb.update();
/*  316 */       this.goldHb.update();
/*  317 */       updateAscensionHover();
/*  318 */       updateButtons();
/*  319 */       this.twitch.ifPresent(TwitchPanel::update);
/*      */       
/*  321 */       if (AbstractDungeon.screen != null && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.UNLOCK) {
/*  322 */         updateTips();
/*      */       }
/*      */     } 
/*      */     
/*  326 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp)
/*      */     {
/*  328 */       if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth / 4) {
/*  329 */         boolean hasHpEffect = false;
/*  330 */         for (AbstractGameEffect e : AbstractDungeon.topLevelEffects) {
/*  331 */           if (e instanceof PingHpEffect) {
/*  332 */             hasHpEffect = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  337 */         if (!hasHpEffect)
/*  338 */           AbstractDungeon.topLevelEffectsQueue.add(new PingHpEffect(this.hpIconX)); 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private enum TopSection
/*      */   {
/*  345 */     HP, GOLD, POTIONS, ASCENSION, MODS, NONE;
/*      */   }
/*      */   
/*      */   private void updateControllerInput() {
/*  349 */     ArrayList<AbstractPotion> pots = AbstractDungeon.player.potions;
/*  350 */     this.section = TopSection.NONE;
/*  351 */     int index = 0;
/*      */     
/*  353 */     for (AbstractPotion p : AbstractDungeon.player.potions) {
/*  354 */       if (p.hb.hovered) {
/*  355 */         this.section = TopSection.POTIONS;
/*      */         break;
/*      */       } 
/*  358 */       index++;
/*      */     } 
/*      */     
/*  361 */     if (this.section == TopSection.NONE) {
/*  362 */       index = 0;
/*  363 */       if (ModHelper.enabledMods.size() > 0) {
/*  364 */         for (Hitbox hb : this.modHbs) {
/*  365 */           if (hb.hovered) {
/*  366 */             this.section = TopSection.MODS;
/*      */             break;
/*      */           } 
/*  369 */           index++;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  374 */     if (this.section == TopSection.NONE && 
/*  375 */       this.ascensionHb != null && this.ascensionHb.hovered) {
/*  376 */       this.section = TopSection.ASCENSION;
/*      */     }
/*      */ 
/*      */     
/*  380 */     if (this.section == TopSection.NONE && 
/*  381 */       this.goldHb.hovered) {
/*  382 */       this.section = TopSection.GOLD;
/*      */     }
/*      */ 
/*      */     
/*  386 */     if (this.section == TopSection.NONE && 
/*  387 */       this.hpHb.hovered) {
/*  388 */       this.section = TopSection.HP;
/*      */     }
/*      */ 
/*      */     
/*  392 */     switch (this.section) {
/*      */       case HP:
/*  394 */         if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  395 */           CInputHelper.setCursor(this.goldHb); break;
/*  396 */         }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  397 */           controllerViewRelics();
/*      */         }
/*      */         break;
/*      */       case GOLD:
/*  401 */         if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  402 */           CInputHelper.setCursor(this.hpHb); break;
/*  403 */         }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  404 */           CInputHelper.setCursor(((AbstractPotion)pots.get(0)).hb); break;
/*  405 */         }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  406 */           controllerViewRelics();
/*      */         }
/*      */         break;
/*      */       case POTIONS:
/*  410 */         if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  411 */           index++;
/*  412 */           if (index > pots.size() - 1) {
/*  413 */             if (AbstractDungeon.isAscensionMode) {
/*  414 */               CInputHelper.setCursor(this.ascensionHb); break;
/*  415 */             }  if (this.modHbs.length != 0)
/*  416 */               CInputHelper.setCursor(this.modHbs[0]); 
/*      */             break;
/*      */           } 
/*  419 */           CInputHelper.setCursor(((AbstractPotion)pots.get(index)).hb); break;
/*      */         } 
/*  421 */         if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  422 */           index--;
/*  423 */           if (index < 0) {
/*  424 */             CInputHelper.setCursor(this.goldHb); break;
/*      */           } 
/*  426 */           CInputHelper.setCursor(((AbstractPotion)pots.get(index)).hb); break;
/*      */         } 
/*  428 */         if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  429 */           controllerViewRelics();
/*      */         }
/*      */         break;
/*      */       case ASCENSION:
/*  433 */         if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  434 */           CInputHelper.setCursor(((AbstractPotion)pots.get(pots.size() - 1)).hb); break;
/*  435 */         }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  436 */           if (this.modHbs.length != 0)
/*  437 */             CInputHelper.setCursor(this.modHbs[0]);  break;
/*      */         } 
/*  439 */         if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  440 */           controllerViewRelics();
/*      */         }
/*      */         break;
/*      */       case MODS:
/*  444 */         if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  445 */           index--;
/*  446 */           if (index < 0) {
/*  447 */             if (AbstractDungeon.isAscensionMode) {
/*  448 */               CInputHelper.setCursor(this.ascensionHb); break;
/*      */             } 
/*  450 */             CInputHelper.setCursor(((AbstractPotion)pots.get(pots.size() - 1)).hb);
/*      */             break;
/*      */           } 
/*  453 */           CInputHelper.setCursor(this.modHbs[index]); break;
/*      */         } 
/*  455 */         if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  456 */           index++;
/*  457 */           if (index > this.modHbs.length - 1) {
/*  458 */             index = this.modHbs.length - 1;
/*      */           }
/*  460 */           CInputHelper.setCursor(this.modHbs[index]); break;
/*  461 */         }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  462 */           controllerViewRelics();
/*      */         }
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void controllerViewRelics() {
/*  471 */     AbstractDungeon.player.viewingRelics = true;
/*  472 */     this.selectPotionMode = false;
/*  473 */     CInputHelper.setCursor(((AbstractRelic)AbstractDungeon.player.relics
/*  474 */         .get(AbstractRelic.relicPage * AbstractRelic.MAX_RELICS_PER_PAGE)).hb);
/*      */   }
/*      */   
/*      */   private void updateAscensionHover() {
/*  478 */     this.ascensionHb.update();
/*      */     
/*  480 */     if (this.ascensionHb.hovered && 
/*  481 */       AbstractDungeon.isAscensionMode) {
/*  482 */       TipHelper.renderGenericTip(InputHelper.mX + 50.0F * Settings.scale, TIP_Y, CharacterSelectScreen.TEXT[8], this.ascensionString);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateGold() {
/*  493 */     if (AbstractDungeon.player.gold < AbstractDungeon.player.displayGold) {
/*  494 */       if (AbstractDungeon.player.displayGold - AbstractDungeon.player.gold > 99) {
/*  495 */         AbstractDungeon.player.displayGold -= 10;
/*  496 */       } else if (AbstractDungeon.player.displayGold - AbstractDungeon.player.gold > 9) {
/*  497 */         AbstractDungeon.player.displayGold -= 3;
/*      */       } else {
/*  499 */         AbstractDungeon.player.displayGold--;
/*      */       }
/*      */     
/*  502 */     } else if (AbstractDungeon.player.gold > AbstractDungeon.player.displayGold) {
/*  503 */       if (AbstractDungeon.player.gold - AbstractDungeon.player.displayGold > 99) {
/*  504 */         AbstractDungeon.player.displayGold += 10;
/*  505 */       } else if (AbstractDungeon.player.gold - AbstractDungeon.player.displayGold > 9) {
/*  506 */         AbstractDungeon.player.displayGold += 3;
/*      */       } else {
/*  508 */         AbstractDungeon.player.displayGold++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void flashRed() {
/*  514 */     this.flashRedTimer = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updatePotions() {
/*  521 */     if (this.flashRedTimer != 0.0F) {
/*  522 */       this.flashRedTimer -= Gdx.graphics.getDeltaTime();
/*  523 */       if (this.flashRedTimer < 0.0F) {
/*  524 */         this.flashRedTimer = 0.0F;
/*      */       }
/*      */     } 
/*      */     
/*  528 */     for (AbstractPotion p : AbstractDungeon.player.potions) {
/*  529 */       p.hb.update();
/*      */       
/*  531 */       if (!p.isObtained) {
/*      */         continue;
/*      */       }
/*      */       
/*  535 */       if (p instanceof PotionSlot) {
/*  536 */         if (p.hb.hovered) {
/*  537 */           p.scale = Settings.scale * 1.3F; continue;
/*      */         } 
/*  539 */         p.scale = Settings.scale;
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  544 */       if (p.hb.justHovered) {
/*  545 */         if (MathUtils.randomBoolean()) {
/*  546 */           CardCrawlGame.sound.play("POTION_1", 0.1F);
/*      */         } else {
/*  548 */           CardCrawlGame.sound.play("POTION_3", 0.1F);
/*      */         } 
/*      */       }
/*      */       
/*  552 */       if (p.hb.hovered) {
/*  553 */         p.scale = Settings.scale * 1.4F;
/*  554 */         if ((AbstractDungeon.player.hoveredCard == null && InputHelper.justClickedLeft) || CInputActionSet.select
/*  555 */           .isJustPressed()) {
/*  556 */           CInputActionSet.select.unpress();
/*  557 */           InputHelper.justClickedLeft = false;
/*  558 */           this.potionUi.open(p.slot, p);
/*      */         }  continue;
/*      */       } 
/*  561 */       p.scale = MathHelper.scaleLerpSnap(p.scale, Settings.scale);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateRelics() {
/*  567 */     if (AbstractDungeon.player.relics.size() >= AbstractRelic.MAX_RELICS_PER_PAGE + 1 && AbstractRelic.relicPage > 0) {
/*      */       
/*  569 */       this.leftScrollHb.update();
/*  570 */       if (this.leftScrollHb.justHovered) {
/*  571 */         CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
/*      */       }
/*  573 */       if (this.leftScrollHb.hovered && InputHelper.justClickedLeft) {
/*  574 */         this.leftScrollHb.clickStarted = true;
/*  575 */         CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/*      */       } 
/*      */       
/*  578 */       if (this.leftScrollHb.clicked) {
/*  579 */         this.leftScrollHb.clicked = false;
/*  580 */         CardCrawlGame.sound.playA("DECK_OPEN", -0.1F);
/*  581 */         if (AbstractRelic.relicPage > 0) {
/*  582 */           AbstractRelic.relicPage--;
/*  583 */           adjustRelicHbs();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  588 */     if (AbstractDungeon.player.relics.size() - (AbstractRelic.relicPage + 1) * AbstractRelic.MAX_RELICS_PER_PAGE > 0) {
/*      */       
/*  590 */       this.rightScrollHb.update();
/*  591 */       if (this.rightScrollHb.justHovered) {
/*  592 */         CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
/*      */       }
/*  594 */       if (this.rightScrollHb.hovered && InputHelper.justClickedLeft) {
/*  595 */         this.rightScrollHb.clickStarted = true;
/*  596 */         CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/*      */       } 
/*      */       
/*  599 */       if (this.rightScrollHb.clicked) {
/*  600 */         this.rightScrollHb.clicked = false;
/*  601 */         CardCrawlGame.sound.playA("DECK_OPEN", -0.1F);
/*  602 */         if (AbstractRelic.relicPage < AbstractDungeon.player.relics.size() / AbstractRelic.MAX_RELICS_PER_PAGE) {
/*      */           
/*  604 */           AbstractRelic.relicPage++;
/*  605 */           adjustRelicHbs();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void adjustRelicHbs() {
/*  612 */     if (AbstractDungeon.player == null) {
/*      */       return;
/*      */     }
/*  615 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  616 */       if (AbstractDungeon.player.relics.size() >= AbstractRelic.MAX_RELICS_PER_PAGE + 1) {
/*  617 */         r.hb.move(r.currentX - (AbstractRelic.relicPage * Settings.WIDTH) + AbstractRelic.relicPage * (AbstractRelic.PAD_X + 36.0F * Settings.scale) + 32.0F * Settings.scale, r.currentY);
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  622 */       r.hb.move(r.currentX - (AbstractRelic.relicPage * Settings.WIDTH) + AbstractRelic.relicPage * (AbstractRelic.PAD_X + 36.0F * Settings.scale), r.currentY);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroyPotion(int slot) {
/*  631 */     AbstractDungeon.player.potions.set(slot, new PotionSlot(slot));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateButtons() {
/*  638 */     updateSettingsButtonLogic();
/*  639 */     updateDeckViewButtonLogic();
/*  640 */     updateMapButtonLogic();
/*      */ 
/*      */     
/*  643 */     if (this.settingsHb.justHovered || this.deckHb.justHovered || this.mapHb.justHovered) {
/*  644 */       CardCrawlGame.sound.play("UI_HOVER");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateSettingsButtonLogic() {
/*  654 */     this.settingsButtonDisabled = false;
/*  655 */     if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.FTUE) {
/*  656 */       this.settingsHb.update();
/*      */     }
/*      */     
/*  659 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS) {
/*      */       
/*  661 */       this.settingsAngle += Gdx.graphics.getDeltaTime() * 300.0F;
/*  662 */       if (this.settingsAngle > 360.0F) {
/*  663 */         this.settingsAngle -= 360.0F;
/*      */       }
/*      */     }
/*  666 */     else if (this.settingsHb.hovered) {
/*  667 */       this.settingsAngle = MathHelper.angleLerpSnap(this.settingsAngle, -90.0F);
/*      */     } else {
/*  669 */       this.settingsAngle = MathHelper.angleLerpSnap(this.settingsAngle, 0.0F);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  674 */     if ((this.settingsHb.hovered && InputHelper.justClickedLeft) || InputHelper.pressedEscape || CInputActionSet.settings
/*  675 */       .isJustPressed()) {
/*      */ 
/*      */       
/*  678 */       if (AbstractDungeon.gridSelectScreen.isJustForConfirming) {
/*  679 */         AbstractDungeon.dynamicBanner.hide();
/*      */       }
/*      */ 
/*      */       
/*  683 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DOOR_UNLOCK) {
/*  684 */         InputHelper.pressedEscape = false;
/*  685 */         CInputActionSet.settings.unpress();
/*  686 */         InputHelper.justClickedLeft = false;
/*      */         
/*      */         return;
/*      */       } 
/*  690 */       if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.CARD_REWARD && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.GRID && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SHOP && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MAP)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  697 */         InputHelper.pressedEscape = false;
/*      */       }
/*      */       
/*  700 */       CInputActionSet.settings.unpress();
/*      */       
/*  702 */       if (AbstractDungeon.isScreenUp && (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS)) {
/*      */ 
/*      */         
/*  705 */         CardCrawlGame.sound.play("UI_CLICK_2");
/*      */         
/*  707 */         AbstractDungeon.screenSwap = false;
/*      */         
/*  709 */         if ((AbstractDungeon.previousScreen == null || AbstractDungeon.screen != AbstractDungeon.CurrentScreen.INPUT_SETTINGS) && (
/*  710 */           AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS))
/*      */         {
/*  712 */           AbstractDungeon.previousScreen = null;
/*      */         }
/*      */         
/*  715 */         AbstractDungeon.closeCurrentScreen();
/*  716 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/*  717 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*  718 */         AbstractDungeon.dynamicBanner.hide();
/*  719 */         AbstractDungeon.settingsScreen.open();
/*  720 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
/*  721 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
/*  722 */         AbstractDungeon.settingsScreen.open();
/*  723 */         AbstractDungeon.deathScreen.hide();
/*  724 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.VICTORY) {
/*  725 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.VICTORY;
/*  726 */         AbstractDungeon.settingsScreen.open();
/*  727 */         AbstractDungeon.victoryScreen.hide();
/*  728 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*  729 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
/*  730 */         AbstractDungeon.settingsScreen.open();
/*  731 */         AbstractDungeon.bossRelicScreen.hide();
/*  732 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
/*  733 */         if (!InputHelper.pressedEscape) {
/*  734 */           AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
/*  735 */           AbstractDungeon.settingsScreen.open();
/*      */         } else {
/*  737 */           AbstractDungeon.closeCurrentScreen();
/*      */         } 
/*  739 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
/*  740 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
/*  741 */         AbstractDungeon.settingsScreen.open();
/*  742 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/*  743 */         if (!InputHelper.pressedEscape) {
/*  744 */           if (AbstractDungeon.previousScreen == null) {
/*  745 */             AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
/*      */           } else {
/*  747 */             AbstractDungeon.screenSwap = true;
/*      */           } 
/*  749 */           AbstractDungeon.settingsScreen.open();
/*      */         } else {
/*  751 */           AbstractDungeon.closeCurrentScreen();
/*      */         } 
/*  753 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
/*  754 */         if (!InputHelper.pressedEscape) {
/*  755 */           if (AbstractDungeon.previousScreen == null) {
/*  756 */             AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW;
/*      */           } else {
/*  758 */             AbstractDungeon.screenSwap = true;
/*      */           } 
/*  760 */           AbstractDungeon.settingsScreen.open();
/*      */         } else {
/*  762 */           AbstractDungeon.closeCurrentScreen();
/*      */         } 
/*  764 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
/*  765 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  766 */         AbstractDungeon.dynamicBanner.hide();
/*  767 */         AbstractDungeon.settingsScreen.open();
/*  768 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
/*  769 */         if (!InputHelper.pressedEscape) {
/*  770 */           AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
/*  771 */           AbstractDungeon.gridSelectScreen.hide();
/*  772 */           AbstractDungeon.settingsScreen.open();
/*      */         } 
/*  774 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NEOW_UNLOCK) {
/*  775 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.NEOW_UNLOCK;
/*  776 */         AbstractDungeon.dynamicBanner.hide();
/*  777 */         AbstractDungeon.settingsScreen.open();
/*  778 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
/*  779 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
/*  780 */         AbstractDungeon.settingsScreen.open();
/*      */       } else {
/*  782 */         AbstractDungeon.settingsScreen.open();
/*      */       } 
/*      */       
/*  785 */       InputHelper.justClickedLeft = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateDeckViewButtonLogic() {
/*  793 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
/*  794 */       this.rotateTimer += Gdx.graphics.getDeltaTime() * 4.0F;
/*  795 */       this.deckAngle = MathHelper.angleLerpSnap(this.deckAngle, MathUtils.sin(this.rotateTimer) * 15.0F);
/*      */     }
/*  797 */     else if (this.deckHb.hovered) {
/*  798 */       this.deckAngle = MathHelper.angleLerpSnap(this.deckAngle, 15.0F);
/*      */     } else {
/*  800 */       this.deckAngle = MathHelper.angleLerpSnap(this.deckAngle, 0.0F);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  805 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NONE || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.VICTORY || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  814 */       this.deckButtonDisabled = false;
/*  815 */       this.deckHb.update();
/*      */     } else {
/*  817 */       this.deckButtonDisabled = true;
/*  818 */       this.deckHb.hovered = false;
/*      */     } 
/*      */ 
/*      */     
/*  822 */     boolean clickedDeckButton = (this.deckHb.hovered && InputHelper.justClickedLeft);
/*  823 */     if ((clickedDeckButton || InputActionSet.masterDeck.isJustPressed() || CInputActionSet.pageLeftViewDeck
/*  824 */       .isJustPressed()) && !CardCrawlGame.isPopupOpen) {
/*  825 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/*  826 */         AbstractDungeon.closeCurrentScreen();
/*  827 */         AbstractDungeon.deckViewScreen.open();
/*  828 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*  829 */       } else if (!AbstractDungeon.isScreenUp) {
/*  830 */         AbstractDungeon.deckViewScreen.open();
/*  831 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
/*      */         
/*  833 */         AbstractDungeon.screenSwap = false;
/*  834 */         if (AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
/*  835 */           AbstractDungeon.previousScreen = null;
/*      */         }
/*  837 */         AbstractDungeon.closeCurrentScreen();
/*  838 */         CardCrawlGame.sound.play("DECK_CLOSE", 0.05F);
/*  839 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
/*  840 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
/*  841 */         AbstractDungeon.deathScreen.hide();
/*  842 */         AbstractDungeon.deckViewScreen.open();
/*  843 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*  844 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
/*  845 */         AbstractDungeon.bossRelicScreen.hide();
/*  846 */         AbstractDungeon.deckViewScreen.open();
/*  847 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
/*  848 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/*  849 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
/*  850 */         AbstractDungeon.deckViewScreen.open();
/*  851 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
/*  852 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
/*  853 */         AbstractDungeon.deckViewScreen.open();
/*  854 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/*      */         
/*  856 */         if (AbstractDungeon.previousScreen != null) {
/*  857 */           AbstractDungeon.screenSwap = true;
/*      */         }
/*  859 */         AbstractDungeon.closeCurrentScreen();
/*  860 */         AbstractDungeon.deckViewScreen.open();
/*  861 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS && clickedDeckButton) {
/*  862 */         if (AbstractDungeon.previousScreen != null) {
/*  863 */           AbstractDungeon.screenSwap = true;
/*      */         }
/*  865 */         AbstractDungeon.closeCurrentScreen();
/*  866 */         AbstractDungeon.deckViewScreen.open();
/*  867 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
/*  868 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  869 */         AbstractDungeon.dynamicBanner.hide();
/*  870 */         AbstractDungeon.deckViewScreen.open();
/*  871 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
/*  872 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
/*  873 */         AbstractDungeon.gridSelectScreen.hide();
/*  874 */         AbstractDungeon.deckViewScreen.open();
/*  875 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
/*  876 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
/*  877 */         AbstractDungeon.deckViewScreen.open();
/*      */       } 
/*      */       
/*  880 */       InputHelper.justClickedLeft = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateMapButtonLogic() {
/*  888 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/*  889 */       this.rotateTimer += Gdx.graphics.getDeltaTime() * 4.0F;
/*  890 */       this.mapAngle = MathHelper.angleLerpSnap(this.mapAngle, MathUtils.sin(this.rotateTimer) * 15.0F);
/*      */     }
/*  892 */     else if (this.mapHb.hovered) {
/*  893 */       this.mapAngle = MathHelper.angleLerpSnap(this.mapAngle, 10.0F);
/*      */     } else {
/*  895 */       this.mapAngle = MathHelper.angleLerpSnap(this.mapAngle, -5.0F);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  900 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NONE || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.VICTORY || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  909 */       this.mapButtonDisabled = false;
/*  910 */       this.mapHb.update();
/*      */     } else {
/*  912 */       this.mapButtonDisabled = true;
/*  913 */       this.mapHb.hovered = false;
/*      */     } 
/*      */ 
/*      */     
/*  917 */     boolean clickedMapButton = (this.mapHb.hovered && InputHelper.justClickedLeft);
/*      */     
/*  919 */     if (!CardCrawlGame.cardPopup.isOpen && (clickedMapButton || InputActionSet.map.isJustPressed() || CInputActionSet.map
/*  920 */       .isJustPressed())) {
/*      */ 
/*      */       
/*  923 */       for (AbstractGameEffect e : AbstractDungeon.topLevelEffects) {
/*  924 */         if (e instanceof com.megacrit.cardcrawl.vfx.FadeWipeParticle) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */       
/*  929 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
/*      */         
/*  931 */         CardCrawlGame.sound.play("CARD_REJECT");
/*  932 */       } else if (!AbstractDungeon.isScreenUp) {
/*  933 */         AbstractDungeon.dungeonMapScreen.open(false);
/*  934 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/*  935 */         AbstractDungeon.closeCurrentScreen();
/*  936 */         AbstractDungeon.dungeonMapScreen.open(false);
/*  937 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*  938 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*  939 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
/*  940 */         AbstractDungeon.bossRelicScreen.hide();
/*  941 */         AbstractDungeon.dungeonMapScreen.open(false);
/*  942 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
/*  943 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/*  944 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
/*  945 */         AbstractDungeon.dungeonMapScreen.open(false);
/*  946 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/*  947 */         AbstractDungeon.screenSwap = false;
/*  948 */         if (AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.MAP) {
/*  949 */           AbstractDungeon.previousScreen = null;
/*      */         }
/*  951 */         AbstractDungeon.closeCurrentScreen();
/*  952 */         CardCrawlGame.sound.play("MAP_CLOSE", 0.05F);
/*  953 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
/*  954 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
/*  955 */         AbstractDungeon.deathScreen.hide();
/*  956 */         AbstractDungeon.dungeonMapScreen.open(false);
/*  957 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
/*      */         
/*  959 */         if (AbstractDungeon.dungeonMapScreen.dismissable) {
/*  960 */           if (AbstractDungeon.previousScreen != null) {
/*  961 */             AbstractDungeon.screenSwap = true;
/*      */           }
/*  963 */           AbstractDungeon.closeCurrentScreen();
/*  964 */           AbstractDungeon.dungeonMapScreen.open(false);
/*      */         } else {
/*  966 */           AbstractDungeon.closeCurrentScreen();
/*      */         } 
/*  968 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS && clickedMapButton) {
/*  969 */         if (AbstractDungeon.dungeonMapScreen.dismissable) {
/*  970 */           if (AbstractDungeon.previousScreen != null) {
/*  971 */             AbstractDungeon.screenSwap = true;
/*      */           }
/*  973 */           AbstractDungeon.closeCurrentScreen();
/*  974 */           AbstractDungeon.dungeonMapScreen.open(false);
/*      */         } 
/*  976 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
/*  977 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  978 */         AbstractDungeon.dynamicBanner.hide();
/*  979 */         AbstractDungeon.dungeonMapScreen.open(false);
/*  980 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
/*  981 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
/*  982 */         AbstractDungeon.gridSelectScreen.hide();
/*  983 */         AbstractDungeon.dungeonMapScreen.open(false);
/*  984 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
/*  985 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
/*  986 */         AbstractDungeon.dungeonMapScreen.open(false);
/*      */       } 
/*      */       
/*  989 */       InputHelper.justClickedLeft = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateTips() {
/*  997 */     if (!Settings.hideTopBar) {
/*  998 */       if (this.hpHb.hovered) {
/*  999 */         TipHelper.renderGenericTip(InputHelper.mX - TIP_OFF_X, TIP_Y, LABEL[3], MSG[3]);
/* 1000 */       } else if (this.goldHb.hovered) {
/* 1001 */         TipHelper.renderGenericTip(InputHelper.mX - TIP_OFF_X, TIP_Y, LABEL[4], MSG[4]);
/*      */       } else {
/* 1003 */         renderPotionTips();
/* 1004 */         if (ModHelper.enabledMods.size() > 0) {
/* 1005 */           renderModTips();
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPotionTips() {
/* 1015 */     if (!Settings.hideTopBar && this.potionUi.isHidden) {
/* 1016 */       for (AbstractPotion p : AbstractDungeon.player.potions) {
/* 1017 */         if (p.hb.hovered) {
/* 1018 */           TipHelper.queuePowerTips(InputHelper.mX - TIP_OFF_X, TIP_Y, p.tips);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void renderModTips() {
/* 1025 */     for (int i = 0; i < this.modHbs.length; i++) {
/* 1026 */       if (this.modHbs[i] != null && (this.modHbs[i]).hovered && ModHelper.enabledMods.get(i) != null) {
/* 1027 */         TipHelper.renderGenericTip(InputHelper.mX - TIP_OFF_X, TIP_Y, ((AbstractDailyMod)ModHelper.enabledMods
/*      */ 
/*      */             
/* 1030 */             .get(i)).name, ((AbstractDailyMod)ModHelper.enabledMods
/* 1031 */             .get(i)).description);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(SpriteBatch sb) {
/* 1042 */     if (!Settings.hideTopBar) {
/*      */       
/* 1044 */       sb.setColor(Color.WHITE);
/* 1045 */       sb.draw(ImageMaster.TOP_PANEL_BAR, 0.0F, Settings.HEIGHT - TOPBAR_H, Settings.WIDTH, TOPBAR_H);
/*      */       
/* 1047 */       if (CardCrawlGame.displayVersion) {
/* 1048 */         FontHelper.renderFontRightTopAligned(sb, FontHelper.cardDescFont_N, CardCrawlGame.VERSION_NUM, Settings.WIDTH - 16.0F * Settings.scale, Settings.HEIGHT - TOPBAR_H + 48.0F * Settings.scale, Settings.QUARTER_TRANSPARENT_WHITE_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1055 */         FontHelper.renderFontRightTopAligned(sb, FontHelper.cardDescFont_N, 
/*      */ 
/*      */             
/* 1058 */             SeedHelper.getUserFacingSeedString(), Settings.WIDTH - 16.0F * Settings.scale, Settings.HEIGHT - TOPBAR_H + 24.0F * Settings.scale, Settings.QUARTER_TRANSPARENT_WHITE_COLOR);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1064 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP || CardCrawlGame.stopClock) {
/* 1065 */         this.timerHb.update();
/* 1066 */         sb.draw(ImageMaster.TIMER_ICON, Settings.WIDTH - 380.0F * Settings.scale, ICON_Y, ICON_W, ICON_W);
/* 1067 */         if (CardCrawlGame.stopClock) {
/* 1068 */           FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, 
/*      */ 
/*      */               
/* 1071 */               CharStat.formatHMSM(CardCrawlGame.playtime), Settings.WIDTH - 320.0F * Settings.scale, INFO_TEXT_Y, Settings.GREEN_TEXT_COLOR);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1076 */           FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, 
/*      */ 
/*      */               
/* 1079 */               CharStat.formatHMSM(CardCrawlGame.playtime), Settings.WIDTH - 320.0F * Settings.scale, INFO_TEXT_Y, Settings.GOLD_COLOR);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1084 */         if (this.timerHb.hovered) {
/* 1085 */           TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, LABEL[5], MSG[7]);
/*      */         }
/* 1087 */         this.timerHb.render(sb);
/*      */       } 
/*      */       
/* 1090 */       renderName(sb);
/* 1091 */       renderHP(sb);
/* 1092 */       renderGold(sb);
/* 1093 */       renderDungeonInfo(sb);
/*      */       
/* 1095 */       if (ModHelper.enabledMods.size() > 0) {
/* 1096 */         renderDailyMods(sb);
/*      */       }
/*      */       
/* 1099 */       renderTopRightIcons(sb);
/* 1100 */       renderRelics(sb);
/* 1101 */       AbstractDungeon.player.renderBlights(sb);
/* 1102 */       renderPotions(sb);
/*      */       
/* 1104 */       if (Settings.isControllerMode) {
/* 1105 */         renderControllerUi(sb);
/*      */       }
/*      */       
/* 1108 */       this.potionUi.render(sb);
/* 1109 */       this.twitch.ifPresent(twitchPanel -> twitchPanel.render(sb));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderControllerUi(SpriteBatch sb) {
/* 1114 */     sb.setColor(Color.WHITE);
/* 1115 */     if (this.selectPotionMode) {
/* 1116 */       sb.setColor(new Color(1.0F, 0.9F, 0.4F, 0.6F + 
/* 1117 */             MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) / 5.0F));
/* 1118 */       float doop = (1.0F + (1.0F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L))) / 50.0F) * Settings.scale;
/* 1119 */       switch (this.section) {
/*      */         case HP:
/* 1121 */           sb.draw(potionSelectBox, this.hpHb.cX - 137.0F, Settings.HEIGHT - 53.0F - 34.0F * Settings.scale, 137.0F, 53.0F, 274.0F, 106.0F, doop * 0.8F, doop, 0.0F, 0, 0, 274, 106, false, false);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case GOLD:
/* 1140 */           sb.draw(potionSelectBox, this.goldHb.cX - 137.0F, Settings.HEIGHT - 53.0F - 34.0F * Settings.scale, 137.0F, 53.0F, 274.0F, 106.0F, doop * 0.7F, doop, 0.0F, 0, 0, 274, 106, false, false);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case POTIONS:
/* 1159 */           sb.draw(potionSelectBox, potionX - 137.0F + 72.0F * Settings.scale, Settings.HEIGHT - 53.0F - 34.0F * Settings.scale, 137.0F, 53.0F, 100.0F + AbstractDungeon.player.potionSlots * 76.0F * Settings.scale, 106.0F, doop, doop, 0.0F, 0, 0, 274, 106, false, false);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case ASCENSION:
/* 1178 */           sb.draw(potionSelectBox, this.ascensionHb.cX - 137.0F, Settings.HEIGHT - 53.0F - 34.0F * Settings.scale, 137.0F, 53.0F, 274.0F, 106.0F, doop, doop, 0.0F, 0, 0, 274, 106, false, false);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case MODS:
/* 1197 */           sb.draw(potionSelectBox, (this.modHbs[0]).cX - 137.0F - 33.0F * Settings.scale, Settings.HEIGHT - 53.0F - 34.0F * Settings.scale, 137.0F, 53.0F, 340.0F, 106.0F, doop, doop, 0.0F, 0, 0, 274, 106, false, false);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1218 */       sb.setColor(Color.WHITE);
/*      */     } else {
/* 1220 */       sb.draw(CInputActionSet.topPanel
/* 1221 */           .getKeyImg(), AbstractDungeon.player.potionSlots * Settings.POTION_W - 32.0F + potionX, Settings.HEIGHT - 32.0F - 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.7F, Settings.scale * 0.7F, 0.0F, 0, 0, 64, 64, false, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1239 */     float iconY = -52.0F * Settings.scale;
/* 1240 */     sb.draw(CInputActionSet.map
/* 1241 */         .getKeyImg(), Settings.WIDTH - 32.0F - 204.0F * Settings.scale, Settings.HEIGHT - 32.0F + iconY, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.6F, Settings.scale * 0.6F, 0.0F, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1258 */     sb.draw(CInputActionSet.pageLeftViewDeck
/* 1259 */         .getKeyImg(), Settings.WIDTH - 32.0F - 136.0F * Settings.scale, Settings.HEIGHT - 32.0F + iconY, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.6F, Settings.scale * 0.6F, 0.0F, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1276 */     sb.draw(CInputActionSet.settings
/* 1277 */         .getKeyImg(), Settings.WIDTH - 32.0F - 68.0F * Settings.scale, Settings.HEIGHT - 32.0F + iconY, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.6F, Settings.scale * 0.6F, 0.0F, 0, 0, 64, 64, false, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderName(SpriteBatch sb) {
/* 1301 */     if (Settings.isEndless) {
/* 1302 */       sb.draw(ImageMaster.ENDLESS_ICON, -32.0F + 46.0F * Settings.scale, ICON_Y - 32.0F + 29.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1319 */     else if (Settings.isFinalActAvailable) {
/* 1320 */       sb.draw(ImageMaster.KEY_SLOTS_ICON, -32.0F + 46.0F * Settings.scale, ICON_Y - 32.0F + 29.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1338 */       if (Settings.hasRubyKey) {
/* 1339 */         sb.draw(ImageMaster.RUBY_KEY, -32.0F + 46.0F * Settings.scale, ICON_Y - 32.0F + 29.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1358 */       if (Settings.hasEmeraldKey) {
/* 1359 */         sb.draw(ImageMaster.EMERALD_KEY, -32.0F + 46.0F * Settings.scale, ICON_Y - 32.0F + 29.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1378 */       if (Settings.hasSapphireKey) {
/* 1379 */         sb.draw(ImageMaster.SAPPHIRE_KEY, -32.0F + 46.0F * Settings.scale, ICON_Y - 32.0F + 29.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1399 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, this.name, this.nameX, NAME_Y, Color.WHITE);
/*      */     
/* 1401 */     if (Settings.isMobile) {
/* 1402 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, this.title, this.nameX, this.titleY - 30.0F * Settings.scale, Color.LIGHT_GRAY);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 1410 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, this.title, this.titleX, this.titleY, Color.LIGHT_GRAY);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderHP(SpriteBatch sb) {
/* 1420 */     sb.setColor(Color.WHITE);
/* 1421 */     if (this.hpHb.hovered) {
/* 1422 */       sb.draw(ImageMaster.TP_HP, this.hpIconX - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 1.2F, Settings.scale * 1.2F, 0.0F, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1440 */       sb.draw(ImageMaster.TP_HP, this.hpIconX - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1459 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, AbstractDungeon.player.currentHealth + "/" + AbstractDungeon.player.maxHealth, this.hpIconX + HP_NUM_OFFSET_X, INFO_TEXT_Y, Color.SALMON);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1467 */     this.hpHb.render(sb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderPotions(SpriteBatch sb) {
/* 1474 */     if (this.flashRedTimer != 0.0F) {
/* 1475 */       sb.setColor(new Color(1.0F, 0.0F, 0.0F, this.flashRedTimer / 2.0F));
/* 1476 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, potionX - 40.0F * Settings.scale, Settings.HEIGHT - 64.0F * Settings.scale, AbstractDungeon.player.potionSlots * 64.0F * Settings.scale, 64.0F * Settings.scale);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     for (AbstractPotion p : AbstractDungeon.player.potions) {
/* 1485 */       if (p.isObtained) {
/* 1486 */         p.renderOutline(sb);
/* 1487 */         p.render(sb);
/* 1488 */         if (p.hb.hovered) {
/* 1489 */           p.renderShiny(sb);
/*      */         }
/*      */       } 
/*      */       
/* 1493 */       p.hb.render(sb);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderRelics(SpriteBatch sb) {
/* 1498 */     AbstractDungeon.player.renderRelics(sb);
/* 1499 */     sb.setColor(Color.WHITE);
/*      */     
/* 1501 */     if (AbstractDungeon.player.relics.size() >= AbstractRelic.MAX_RELICS_PER_PAGE + 1 && AbstractRelic.relicPage > 0) {
/*      */       
/* 1503 */       this.leftScrollHb.render(sb);
/* 1504 */       sb.draw(ImageMaster.CF_LEFT_ARROW, this.leftScrollHb.cX - 24.0F, this.leftScrollHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1523 */     if (AbstractDungeon.player.relics.size() - (AbstractRelic.relicPage + 1) * AbstractRelic.MAX_RELICS_PER_PAGE > 0) {
/*      */       
/* 1525 */       this.rightScrollHb.render(sb);
/* 1526 */       sb.draw(ImageMaster.CF_RIGHT_ARROW, this.rightScrollHb.cX - 24.0F, this.rightScrollHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderGold(SpriteBatch sb) {
/* 1552 */     sb.setColor(Color.WHITE);
/*      */     
/* 1554 */     if (this.goldHb.hovered) {
/* 1555 */       sb.draw(ImageMaster.TP_GOLD, this.goldIconX - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 1.2F, Settings.scale * 1.2F, 0.0F, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1573 */       sb.draw(ImageMaster.TP_GOLD, this.goldIconX - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1592 */     if (AbstractDungeon.player.displayGold == AbstractDungeon.player.gold) {
/* 1593 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */           
/* 1596 */           Integer.toString(AbstractDungeon.player.displayGold), this.goldIconX + GOLD_NUM_OFFSET_X, INFO_TEXT_Y, Settings.GOLD_COLOR);
/*      */ 
/*      */     
/*      */     }
/* 1600 */     else if (AbstractDungeon.player.displayGold > AbstractDungeon.player.gold) {
/* 1601 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */           
/* 1604 */           Integer.toString(AbstractDungeon.player.displayGold), this.goldIconX + GOLD_NUM_OFFSET_X, INFO_TEXT_Y, Settings.RED_TEXT_COLOR);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1609 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */           
/* 1612 */           Integer.toString(AbstractDungeon.player.displayGold), this.goldIconX + GOLD_NUM_OFFSET_X, INFO_TEXT_Y, Settings.GREEN_TEXT_COLOR);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1618 */     this.goldHb.render(sb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderDungeonInfo(SpriteBatch sb) {
/* 1627 */     if (AbstractDungeon.floorNum > 0) {
/* 1628 */       sb.draw(ImageMaster.TP_FLOOR, floorX, ICON_Y, ICON_W, ICON_W);
/* 1629 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */           
/* 1632 */           Integer.toString(AbstractDungeon.floorNum), floorX + 60.0F * Settings.scale, INFO_TEXT_Y, Settings.CREAM_COLOR);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1638 */     if (AbstractDungeon.isAscensionMode) {
/* 1639 */       sb.draw(ImageMaster.TP_ASCENSION, floorX + 106.0F * Settings.scale, ICON_Y, ICON_W, ICON_W);
/* 1640 */       if (AbstractDungeon.ascensionLevel == 20) {
/* 1641 */         FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */             
/* 1644 */             Integer.toString(AbstractDungeon.ascensionLevel), floorX + 166.0F * Settings.scale, INFO_TEXT_Y, Settings.GOLD_COLOR);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1649 */         FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */             
/* 1652 */             Integer.toString(AbstractDungeon.ascensionLevel), floorX + 166.0F * Settings.scale, INFO_TEXT_Y, Settings.RED_TEXT_COLOR);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1659 */     if (this.ascensionHb != null) {
/* 1660 */       this.ascensionHb.render(sb);
/*      */     }
/*      */   }
/*      */   
/*      */   private void renderDailyMods(SpriteBatch sb) {
/* 1665 */     if (ModHelper.enabledMods.size() > 0) {
/* 1666 */       float offsetX = 0.0F;
/*      */       
/* 1668 */       sb.setColor(Color.WHITE);
/* 1669 */       for (int i = 0; i < ModHelper.enabledMods.size(); i++) {
/* 1670 */         if (ModHelper.enabledMods.get(i) != null) {
/* 1671 */           if ((this.modHbs[i]).hovered) {
/* 1672 */             sb.draw(((AbstractDailyMod)ModHelper.enabledMods
/* 1673 */                 .get(i)).img, dailyModX - 32.0F + offsetX, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 1.3F, Settings.scale * 1.3F, 0.0F, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1690 */             sb.draw(((AbstractDailyMod)ModHelper.enabledMods
/* 1691 */                 .get(i)).img, dailyModX - 32.0F + offsetX, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1708 */           offsetX += 52.0F * Settings.scale;
/*      */         } 
/*      */       } 
/*      */       
/* 1712 */       FontHelper.renderFontRightTopAligned(sb, FontHelper.tipBodyFont, DailyScreen.TEXT[13], dailyModX - 30.0F * Settings.scale, INFO_TEXT_Y + 3.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1720 */       for (Hitbox hb : this.modHbs) {
/* 1721 */         hb.render(sb);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String getOrdinalNaming(int i) {
/* 1727 */     (new String[10])[0] = "th"; (new String[10])[1] = "st"; (new String[10])[2] = "nd"; (new String[10])[3] = "rd"; (new String[10])[4] = "th"; (new String[10])[5] = "th"; (new String[10])[6] = "th"; (new String[10])[7] = "th"; (new String[10])[8] = "th"; (new String[10])[9] = "th"; return (i % 100 == 11 || i % 100 == 12 || i % 100 == 13) ? "th" : (new String[10])[i % 10];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderTopRightIcons(SpriteBatch sb) {
/* 1738 */     if (this.settingsButtonDisabled) {
/* 1739 */       sb.setColor(DISABLED_BTN_COLOR);
/*      */     }
/* 1741 */     else if (this.settingsHb.hovered && 
/* 1742 */       AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.INPUT_SETTINGS) {
/*      */       
/* 1744 */       TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, LABEL[0] + " (" + InputActionSet.cancel
/*      */ 
/*      */           
/* 1747 */           .getKeyString() + ")", MSG[0]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1752 */     renderSettingsIcon(sb);
/*      */ 
/*      */     
/* 1755 */     Color tmpColor = Color.WHITE.cpy();
/* 1756 */     if (this.deckButtonDisabled) {
/* 1757 */       sb.setColor(DISABLED_BTN_COLOR);
/* 1758 */       tmpColor = DISABLED_BTN_COLOR;
/*      */     }
/* 1760 */     else if (this.deckHb.hovered) {
/* 1761 */       sb.setColor(Color.CYAN);
/* 1762 */       if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
/* 1763 */         TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, LABEL[1] + " (" + InputActionSet.masterDeck
/*      */ 
/*      */             
/* 1766 */             .getKeyString() + ")", MSG[1]);
/*      */       }
/*      */     } else {
/*      */       
/* 1770 */       sb.setColor(Color.WHITE);
/*      */     } 
/*      */     
/* 1773 */     renderDeckIcon(sb);
/* 1774 */     FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelAmountFont, 
/*      */ 
/*      */         
/* 1777 */         Integer.toString(AbstractDungeon.player.masterDeck.size()), DECK_X + 58.0F * Settings.scale, ICON_Y + 25.0F * Settings.scale, tmpColor);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1783 */     if (this.mapButtonDisabled) {
/* 1784 */       sb.setColor(DISABLED_BTN_COLOR);
/*      */     }
/* 1786 */     else if (this.mapHb.hovered) {
/* 1787 */       sb.setColor(Color.CYAN);
/* 1788 */       if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
/* 1789 */         TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, LABEL[2] + " (" + InputActionSet.map
/*      */ 
/*      */             
/* 1792 */             .getKeyString() + ")", MSG[2]);
/*      */       }
/*      */     } else {
/*      */       
/* 1796 */       sb.setColor(Color.WHITE);
/*      */     } 
/*      */     
/* 1799 */     renderMapIcon(sb);
/*      */   }
/*      */   
/*      */   private void renderSettingsIcon(SpriteBatch sb) {
/* 1803 */     sb.draw(ImageMaster.SETTINGS_ICON, SETTINGS_X - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.settingsAngle, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1820 */     if (this.settingsHb.hovered) {
/* 1821 */       sb.setBlendFunction(770, 1);
/* 1822 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
/* 1823 */       sb.draw(ImageMaster.SETTINGS_ICON, SETTINGS_X - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.settingsAngle, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1840 */       sb.setBlendFunction(770, 771);
/*      */     } 
/* 1842 */     this.settingsHb.render(sb);
/*      */   }
/*      */   
/*      */   private void renderDeckIcon(SpriteBatch sb) {
/* 1846 */     sb.setColor(Color.WHITE);
/* 1847 */     sb.draw(ImageMaster.DECK_ICON, DECK_X - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.deckAngle, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1864 */     if (this.deckHb.hovered) {
/* 1865 */       sb.setBlendFunction(770, 1);
/* 1866 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
/* 1867 */       sb.draw(ImageMaster.DECK_ICON, DECK_X - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.deckAngle, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1884 */       sb.setBlendFunction(770, 771);
/*      */     } 
/* 1886 */     this.deckHb.render(sb);
/*      */   }
/*      */   
/*      */   private void renderMapIcon(SpriteBatch sb) {
/* 1890 */     sb.setColor(Color.WHITE);
/* 1891 */     sb.draw(ImageMaster.MAP_ICON, MAP_X - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.mapAngle, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1908 */     if (this.mapHb.hovered) {
/* 1909 */       sb.setBlendFunction(770, 1);
/* 1910 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
/* 1911 */       sb.draw(ImageMaster.MAP_ICON, MAP_X - 32.0F + 32.0F * Settings.scale, ICON_Y - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.mapAngle, 0, 0, 64, 64, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1928 */       sb.setBlendFunction(770, 771);
/*      */     } 
/* 1930 */     this.mapHb.render(sb);
/*      */   }
/*      */   
/*      */   public void panelHealEffect() {
/* 1934 */     AbstractDungeon.topLevelEffectsQueue.add(new HealPanelEffect(this.hpIconX));
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\TopPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */