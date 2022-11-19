/*      */ package com.megacrit.cardcrawl.screens.custom;
/*      */ 
/*      */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.RunModStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
import com.megacrit.cardcrawl.trials.AbstractTrial;
import com.megacrit.cardcrawl.trials.CustomTrial;
import com.megacrit.cardcrawl.ui.buttons.GridSelectConfirmButton;
import com.megacrit.cardcrawl.ui.panels.SeedPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Arrays;
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
/*      */ public class CustomModeScreen
/*      */   implements ScrollBarListener
/*      */ {
/*   78 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CustomModeScreen");
/*   79 */   public static final String[] TEXT = uiStrings.TEXT;
/*      */ 
/*      */   
/*      */   private final float imageScale;
/*      */ 
/*      */   
/*   85 */   private MenuCancelButton cancelButton = new MenuCancelButton();
/*   86 */   public GridSelectConfirmButton confirmButton = new GridSelectConfirmButton(CharacterSelectScreen.TEXT[1]);
/*      */ 
/*      */   
/*      */   private Hitbox controllerHb;
/*      */ 
/*      */   
/*   92 */   public ArrayList<CustomModeCharacterButton> options = new ArrayList<>();
/*      */   private static float ASC_RIGHT_W;
/*      */   public static boolean finalActAvailable = false;
/*      */   public boolean isAscensionMode = false;
/*      */   private Hitbox ascensionModeHb;
/*      */   private Hitbox ascLeftHb;
/*      */   private Hitbox ascRightHb;
/*   99 */   public int ascensionLevel = 0;
/*      */ 
/*      */   
/*  102 */   private Hitbox seedHb = new Hitbox(400.0F * Settings.scale, 90.0F * Settings.scale);
/*      */   
/*      */   private String currentSeed;
/*      */   
/*      */   private SeedPanel seedPanel;
/*      */   
/*      */   private ArrayList<CustomMod> modList;
/*      */   
/*      */   private static final String DAILY_MODS = "Daily Mods";
/*      */   
/*      */   private static final String MOD_BLIGHT_CHESTS = "Blight Chests";
/*      */   
/*      */   private static final String MOD_ONE_HIT_WONDER = "One Hit Wonder";
/*      */   
/*      */   private static final String MOD_PRAISE_SNECKO = "Praise Snecko";
/*      */   
/*      */   private static final String MOD_INCEPTION = "Inception";
/*      */   
/*      */   private static final String MOD_MY_TRUE_FORM = "My True Form";
/*      */   private static final String MOD_STARTER_DECK = "Starter Deck";
/*      */   private static final String NEUTRAL_COLOR = "b";
/*      */   private static final String POSITIVE_COLOR = "g";
/*      */   private static final String NEGATIVE_COLOR = "r";
/*      */   public boolean screenUp = false;
/*      */   public static float screenX;
/*  127 */   private float ASCENSION_TEXT_Y = 480.0F;
/*      */   
/*      */   private boolean grabbedScreen = false;
/*      */   
/*  131 */   private float grabStartY = 0.0F; private float targetY = 0.0F; private float scrollY = 0.0F; private float scrollLowerBound;
/*      */   private float scrollUpperBound;
/*      */   private ScrollBar scrollBar;
/*      */   
/*      */   public CustomModeScreen() {
/*  136 */     screenX = Settings.isMobile ? (240.0F * Settings.xScale) : (300.0F * Settings.xScale);
/*  137 */     this.imageScale = Settings.isMobile ? (Settings.scale * 1.2F) : Settings.scale;
/*      */     
/*  139 */     initializeMods();
/*  140 */     initializeCharacters();
/*  141 */     calculateScrollBounds();
/*      */     
/*  143 */     if (Settings.isMobile) {
/*  144 */       this.scrollBar = new ScrollBar(this, Settings.WIDTH - 280.0F * Settings.xScale - ScrollBar.TRACK_W / 2.0F, Settings.HEIGHT / 2.0F, Settings.HEIGHT - 256.0F * Settings.scale, true);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  151 */       this.scrollBar = new ScrollBar(this, Settings.WIDTH - 280.0F * Settings.xScale - ScrollBar.TRACK_W / 2.0F, Settings.HEIGHT / 2.0F, Settings.HEIGHT - 256.0F * Settings.scale);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  158 */     this.seedPanel = new SeedPanel();
/*      */   }
/*      */   
/*      */   private void initializeMods() {
/*  162 */     this.modList = new ArrayList<>();
/*      */ 
/*      */     
/*  165 */     addMod("Daily Mods", "b", false);
/*  166 */     CustomMod draftMod = addDailyMod("Draft", "b");
/*  167 */     CustomMod sealedMod = addDailyMod("SealedDeck", "b");
/*  168 */     CustomMod endingMod = null;
/*  169 */     if (UnlockTracker.isAchievementUnlocked("THE_ENDING")) {
/*  170 */       endingMod = addDailyMod("The Ending", "b");
/*      */     }
/*  172 */     CustomMod endlessMod = addDailyMod("Endless", "b");
/*  173 */     addMod("Blight Chests", "b", false);
/*  174 */     addDailyMod("Hoarder", "b");
/*  175 */     CustomMod insanityMod = addDailyMod("Insanity", "b");
/*  176 */     addDailyMod("Chimera", "b");
/*  177 */     addMod("Praise Snecko", "b", false);
/*  178 */     CustomMod shinyMod = addDailyMod("Shiny", "b");
/*  179 */     addDailyMod("Specialized", "b");
/*  180 */     addDailyMod("Vintage", "b");
/*  181 */     addDailyMod("ControlledChaos", "b");
/*  182 */     addMod("Inception", "b", false);
/*      */ 
/*      */     
/*  185 */     addDailyMod("Allstar", "g");
/*  186 */     CustomMod diverseMod = addDailyMod("Diverse", "g");
/*  187 */     CustomMod redMod = addDailyMod("Red Cards", "g");
/*  188 */     CustomMod greenMod = addDailyMod("Green Cards", "g");
/*  189 */     CustomMod blueMod = addDailyMod("Blue Cards", "g");
/*  190 */     CustomMod purpleMod = null;
/*  191 */     if (!UnlockTracker.isCharacterLocked("Watcher")) {
/*  192 */       purpleMod = addDailyMod("Purple Cards", "g");
/*      */     }
/*  194 */     addDailyMod("Colorless Cards", "g");
/*  195 */     addDailyMod("Heirloom", "g");
/*  196 */     addDailyMod("Time Dilation", "g");
/*  197 */     addDailyMod("Flight", "g");
/*  198 */     addMod("My True Form", "g", false);
/*      */ 
/*      */     
/*  201 */     addDailyMod("DeadlyEvents", "r");
/*  202 */     addDailyMod("Binary", "r");
/*  203 */     addMod("One Hit Wonder", "r", false);
/*  204 */     addDailyMod("Cursed Run", "r");
/*  205 */     addDailyMod("Elite Swarm", "r");
/*  206 */     addDailyMod("Lethality", "r");
/*  207 */     addDailyMod("Midas", "r");
/*  208 */     addDailyMod("Night Terrors", "r");
/*  209 */     addDailyMod("Terminal", "r");
/*  210 */     addDailyMod("Uncertain Future", "r");
/*  211 */     addMod("Starter Deck", "r", false);
/*      */ 
/*      */     
/*  214 */     if (endingMod != null) {
/*  215 */       endingMod.setMutualExclusionPair(endlessMod);
/*      */     }
/*  217 */     insanityMod.setMutualExclusionPair(shinyMod);
/*  218 */     sealedMod.setMutualExclusionPair(draftMod);
/*  219 */     diverseMod.setMutualExclusionPair(redMod);
/*  220 */     diverseMod.setMutualExclusionPair(greenMod);
/*  221 */     diverseMod.setMutualExclusionPair(blueMod);
/*  222 */     if (purpleMod != null) {
/*  223 */       diverseMod.setMutualExclusionPair(purpleMod);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CustomMod addMod(String id, String color, boolean isDailyMod) {
/*  232 */     RunModStrings modString = CardCrawlGame.languagePack.getRunModString(id);
/*  233 */     if (modString != null) {
/*  234 */       CustomMod mod = new CustomMod(id, color, isDailyMod);
/*  235 */       this.modList.add(mod);
/*  236 */       return mod;
/*      */     } 
/*  238 */     return null;
/*      */   }
/*      */   
/*      */   private CustomMod addDailyMod(String id, String color) {
/*  242 */     return addMod(id, color, true);
/*      */   }
/*      */   
/*      */   public void open() {
/*  246 */     this.confirmButton.show();
/*  247 */     this.controllerHb = null;
/*  248 */     this.targetY = 0.0F;
/*  249 */     this.screenUp = true;
/*  250 */     Settings.seed = null;
/*  251 */     Settings.specialSeed = null;
/*      */     
/*  253 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.CUSTOM;
/*  254 */     CardCrawlGame.mainMenuScreen.darken();
/*  255 */     this.cancelButton.show(CharacterSelectScreen.TEXT[5]);
/*  256 */     this.confirmButton.isDisabled = false;
/*  257 */     ASC_RIGHT_W = FontHelper.getSmartWidth(FontHelper.charDescFont, TEXT[4] + "22", 9999.0F, 0.0F) * Settings.xScale;
/*  258 */     this.ascensionModeHb = new Hitbox(80.0F * Settings.scale, 80.0F * Settings.scale);
/*  259 */     this.ascensionModeHb.move(screenX + 130.0F * Settings.xScale, this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale);
/*  260 */     this.ascLeftHb = new Hitbox(95.0F * Settings.scale, 95.0F * Settings.scale);
/*  261 */     this.ascRightHb = new Hitbox(95.0F * Settings.scale, 95.0F * Settings.scale);
/*  262 */     this.ascLeftHb.move(screenX + ASC_RIGHT_W * 1.1F + 250.0F * Settings.xScale, this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale);
/*      */ 
/*      */     
/*  265 */     this.ascRightHb.move(screenX + ASC_RIGHT_W * 1.1F + 350.0F * Settings.xScale, this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void initializeCharacters() {
/*  271 */     this.options.clear();
/*  272 */     this.options.add(new CustomModeCharacterButton(CardCrawlGame.characterManager
/*      */           
/*  274 */           .setChosenCharacter(AbstractPlayer.PlayerClass.IRONCLAD), false));
/*      */     
/*  276 */     this.options.add(new CustomModeCharacterButton(CardCrawlGame.characterManager
/*      */           
/*  278 */           .setChosenCharacter(AbstractPlayer.PlayerClass.THE_SILENT), 
/*  279 */           UnlockTracker.isCharacterLocked("The Silent")));
/*  280 */     this.options.add(new CustomModeCharacterButton(CardCrawlGame.characterManager
/*      */           
/*  282 */           .setChosenCharacter(AbstractPlayer.PlayerClass.DEFECT), 
/*  283 */           UnlockTracker.isCharacterLocked("Defect")));
/*  284 */     this.options.add(new CustomModeCharacterButton(CardCrawlGame.characterManager
/*      */           
/*  286 */           .setChosenCharacter(AbstractPlayer.PlayerClass.WATCHER), 
/*  287 */           UnlockTracker.isCharacterLocked("Watcher")));
/*      */     
/*  289 */     int count = this.options.size();
/*      */     
/*  291 */     for (int i = 0; i < count; i++) {
/*  292 */       ((CustomModeCharacterButton)this.options.get(i)).move(screenX + i * 100.0F * Settings.scale - 200.0F * Settings.xScale, this.scrollY - 80.0F * Settings.scale);
/*      */     }
/*      */ 
/*      */     
/*  296 */     ((CustomModeCharacterButton)this.options.get(0)).hb.clicked = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/*  302 */     updateControllerInput();
/*  303 */     if (Settings.isControllerMode && this.controllerHb != null) {
/*  304 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.75F) {
/*  305 */         this.targetY += Settings.SCROLL_SPEED;
/*  306 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.25F) {
/*  307 */         this.targetY -= Settings.SCROLL_SPEED;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  312 */     this.seedPanel.update();
/*  313 */     if (!this.seedPanel.shown) {
/*      */       
/*  315 */       boolean isDraggingScrollBar = this.scrollBar.update();
/*  316 */       if (!isDraggingScrollBar) {
/*  317 */         updateScrolling();
/*      */       }
/*  319 */       updateCharacterButtons();
/*  320 */       updateAscension();
/*  321 */       updateSeed();
/*  322 */       updateMods();
/*  323 */       updateEmbarkButton();
/*  324 */       updateCancelButton();
/*      */     } 
/*  326 */     this.currentSeed = SeedHelper.getUserFacingSeedString();
/*      */     
/*  328 */     if (Settings.isControllerMode && this.controllerHb != null) {
/*  329 */       CInputHelper.setCursor(this.controllerHb);
/*      */     }
/*      */   }
/*      */   
/*      */   private void updateCancelButton() {
/*  334 */     this.cancelButton.update();
/*  335 */     if (this.cancelButton.hb.clicked || InputHelper.pressedEscape) {
/*  336 */       InputHelper.pressedEscape = false;
/*  337 */       this.cancelButton.hb.clicked = false;
/*  338 */       this.cancelButton.hide();
/*  339 */       CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateEmbarkButton() {
/*  344 */     this.confirmButton.update();
/*  345 */     if (this.confirmButton.hb.clicked || CInputActionSet.proceed.isJustPressed()) {
/*  346 */       this.confirmButton.hb.clicked = false;
/*      */       
/*  348 */       for (CustomModeCharacterButton b : this.options) {
/*  349 */         if (b.selected) {
/*  350 */           CardCrawlGame.chosenCharacter = b.c.chosenClass;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  355 */       CardCrawlGame.mainMenuScreen.isFadingOut = true;
/*  356 */       CardCrawlGame.mainMenuScreen.fadeOutMusic();
/*  357 */       Settings.isTrial = true;
/*  358 */       Settings.isDailyRun = false;
/*  359 */       Settings.isEndless = false;
/*  360 */       finalActAvailable = false;
/*      */       
/*  362 */       AbstractDungeon.isAscensionMode = this.isAscensionMode;
/*  363 */       if (!this.isAscensionMode) {
/*  364 */         AbstractDungeon.ascensionLevel = 0;
/*      */       } else {
/*  366 */         AbstractDungeon.ascensionLevel = this.ascensionLevel;
/*      */       } 
/*      */       
/*  369 */       if (this.currentSeed.isEmpty()) {
/*  370 */         long sourceTime = System.nanoTime();
/*  371 */         Random rng = new Random(Long.valueOf(sourceTime));
/*  372 */         Settings.seed = Long.valueOf(SeedHelper.generateUnoffensiveSeed(rng));
/*      */       } 
/*  374 */       AbstractDungeon.generateSeeds();
/*      */       
/*  376 */       CustomTrial trial = new CustomTrial();
/*  377 */       trial.addDailyMods(getActiveDailyModIds());
/*  378 */       addNonDailyMods(trial, getActiveNonDailyMods());
/*      */ 
/*      */       
/*  381 */       Settings.isEndless = trial.dailyModIDs().contains("Endless");
/*  382 */       finalActAvailable = trial.dailyModIDs().contains("The Ending");
/*  383 */       CardCrawlGame.trial = (AbstractTrial)trial;
/*  384 */       AbstractPlayer.customMods = CardCrawlGame.trial.dailyModIDs();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateCharacterButtons() {
/*  389 */     for (int i = 0; i < this.options.size(); i++) {
/*  390 */       if (Settings.isMobile) {
/*  391 */         ((CustomModeCharacterButton)this.options.get(i)).update(screenX + i * 130.0F * Settings.xScale + 130.0F * Settings.scale, this.scrollY + 640.0F * Settings.scale);
/*      */       }
/*      */       else {
/*      */         
/*  395 */         ((CustomModeCharacterButton)this.options.get(i)).update(screenX + i * 100.0F * Settings.xScale + 130.0F * Settings.scale, this.scrollY + 640.0F * Settings.scale);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateSeed() {
/*  403 */     this.seedHb.move(screenX + 280.0F * Settings.xScale, this.scrollY + 320.0F * Settings.scale);
/*  404 */     this.seedHb.update();
/*      */     
/*  406 */     if (this.seedHb.justHovered) {
/*  407 */       playHoverSound();
/*      */     }
/*      */     
/*  410 */     if (this.seedHb.hovered && InputHelper.justClickedLeft) {
/*  411 */       this.seedHb.clickStarted = true;
/*      */     }
/*      */     
/*  414 */     if (this.seedHb.clicked || (CInputActionSet.select.isJustPressed() && this.seedHb.hovered)) {
/*  415 */       this.seedHb.clicked = false;
/*  416 */       if (Settings.seed == null) {
/*  417 */         Settings.seed = Long.valueOf(0L);
/*      */       }
/*  419 */       this.seedPanel.show(MainMenuScreen.CurScreen.CUSTOM);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateAscension() {
/*  426 */     this.ascLeftHb.moveY(this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale);
/*  427 */     this.ascRightHb.moveY(this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale);
/*  428 */     this.ascensionModeHb.moveY(this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale);
/*      */ 
/*      */     
/*  431 */     this.ascensionModeHb.update();
/*  432 */     this.ascLeftHb.update();
/*  433 */     this.ascRightHb.update();
/*      */     
/*  435 */     if (this.ascensionModeHb.justHovered || this.ascRightHb.justHovered || this.ascLeftHb.justHovered) {
/*  436 */       playHoverSound();
/*      */     }
/*      */     
/*  439 */     if (this.ascensionModeHb.hovered && InputHelper.justClickedLeft) {
/*  440 */       playClickStartSound();
/*  441 */       this.ascensionModeHb.clickStarted = true;
/*  442 */     } else if (this.ascLeftHb.hovered && InputHelper.justClickedLeft) {
/*  443 */       playClickStartSound();
/*  444 */       this.ascLeftHb.clickStarted = true;
/*  445 */     } else if (this.ascRightHb.hovered && InputHelper.justClickedLeft) {
/*  446 */       playClickStartSound();
/*  447 */       this.ascRightHb.clickStarted = true;
/*      */     } 
/*      */ 
/*      */     
/*  451 */     if (this.ascensionModeHb.clicked || CInputActionSet.topPanel.isJustPressed()) {
/*  452 */       CInputActionSet.topPanel.unpress();
/*  453 */       playClickFinishSound();
/*  454 */       this.ascensionModeHb.clicked = false;
/*  455 */       this.isAscensionMode = !this.isAscensionMode;
/*  456 */       if (this.isAscensionMode && this.ascensionLevel == 0) {
/*  457 */         this.ascensionLevel = 1;
/*      */       }
/*      */     }
/*  460 */     else if (this.ascLeftHb.clicked || CInputActionSet.pageLeftViewDeck.isJustPressed()) {
/*  461 */       playClickFinishSound();
/*  462 */       this.ascLeftHb.clicked = false;
/*  463 */       this.ascensionLevel--;
/*  464 */       if (this.ascensionLevel < 1) {
/*  465 */         this.ascensionLevel = 20;
/*      */       }
/*      */     }
/*  468 */     else if (this.ascRightHb.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
/*  469 */       playClickFinishSound();
/*  470 */       this.ascRightHb.clicked = false;
/*  471 */       this.ascensionLevel++;
/*  472 */       if (this.ascensionLevel > 20) {
/*  473 */         this.ascensionLevel = 1;
/*      */       }
/*  475 */       this.isAscensionMode = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateMods() {
/*  480 */     float offset = 0.0F;
/*  481 */     for (int i = 0; i < this.modList.size(); i++) {
/*  482 */       ((CustomMod)this.modList.get(i)).update(this.scrollY + offset);
/*  483 */       offset -= ((CustomMod)this.modList.get(i)).height;
/*      */     } 
/*      */   }
/*      */   
/*      */   private ArrayList<String> getActiveDailyModIds() {
/*  488 */     ArrayList<String> active = new ArrayList<>();
/*  489 */     for (CustomMod mod : this.modList) {
/*  490 */       if (mod.selected && mod.isDailyMod) {
/*  491 */         active.add(mod.ID);
/*      */       }
/*      */     } 
/*  494 */     return active;
/*      */   }
/*      */   
/*      */   private ArrayList<String> getActiveNonDailyMods() {
/*  498 */     ArrayList<String> active = new ArrayList<>();
/*  499 */     for (CustomMod mod : this.modList) {
/*  500 */       if (mod.selected && !mod.isDailyMod) {
/*  501 */         active.add(mod.ID);
/*      */       }
/*      */     } 
/*  504 */     return active;
/*      */   }
/*      */   
/*      */   private void addNonDailyMods(CustomTrial trial, ArrayList<String> modIds) {
/*  508 */     for (String modId : modIds) {
/*  509 */       switch (modId) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "Daily Mods":
/*  515 */           trial.setRandomDailyMods();
/*      */         
/*      */         case "One Hit Wonder":
/*  518 */           trial.setMaxHpOverride(1);
/*      */         
/*      */         case "Praise Snecko":
/*  521 */           trial.addStarterRelic("Snecko Eye");
/*  522 */           trial.setShouldKeepStarterRelic(false);
/*      */         
/*      */         case "Inception":
/*  525 */           trial.addStarterRelic("Unceasing Top");
/*  526 */           trial.setShouldKeepStarterRelic(false);
/*      */         
/*      */         case "My True Form":
/*  529 */           trial.addStarterCards(Arrays.asList(new String[] { "Demon Form", "Wraith Form v2", "Echo Form", "DevaForm" }));
/*      */         
/*      */         case "Starter Deck":
/*  532 */           trial.addStarterRelic("Busted Crown");
/*  533 */           trial.addDailyMod("Binary");
/*      */         
/*      */         case "Blight Chests":
/*  536 */           trial.addDailyMod("Blight Chests");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void playClickStartSound() {
/*  545 */     CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/*      */   }
/*      */ 
/*      */   
/*      */   private void playClickFinishSound() {
/*  550 */     CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/*      */   }
/*      */   
/*      */   private void playHoverSound() {
/*  554 */     CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
/*      */   }
/*      */   
/*      */   public void render(SpriteBatch sb) {
/*  558 */     renderScreen(sb);
/*  559 */     this.scrollBar.render(sb);
/*  560 */     this.cancelButton.render(sb);
/*  561 */     this.confirmButton.render(sb);
/*      */ 
/*      */     
/*  564 */     for (CustomModeCharacterButton o : this.options) {
/*  565 */       o.render(sb);
/*      */     }
/*      */ 
/*      */     
/*  569 */     renderAscension(sb);
/*      */ 
/*      */     
/*  572 */     renderSeed(sb);
/*      */ 
/*      */     
/*  575 */     sb.setColor(Color.WHITE);
/*  576 */     for (CustomMod m : this.modList) {
/*  577 */       m.render(sb);
/*      */     }
/*  579 */     this.seedPanel.render(sb);
/*      */   }
/*      */ 
/*      */   
/*      */   public void renderScreen(SpriteBatch sb) {
/*  584 */     renderTitle(sb, TEXT[0], this.scrollY - 50.0F * Settings.scale);
/*  585 */     renderHeader(sb, TEXT[2], this.scrollY - 120.0F * Settings.scale);
/*  586 */     renderHeader(sb, TEXT[3], this.scrollY - 290.0F * Settings.scale);
/*  587 */     renderHeader(sb, TEXT[7], this.scrollY - 460.0F * Settings.scale);
/*  588 */     renderHeader(sb, TEXT[6], this.scrollY - 630.0F * Settings.scale);
/*      */   }
/*      */   
/*      */   private void renderAscension(SpriteBatch sb) {
/*  592 */     sb.setColor(Color.WHITE);
/*  593 */     if (this.ascensionModeHb.hovered) {
/*  594 */       sb.draw(ImageMaster.CHECKBOX, this.ascensionModeHb.cX - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.imageScale * 1.2F, this.imageScale * 1.2F, 0.0F, 0, 0, 64, 64, false, false);
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
/*  611 */       sb.setColor(Color.GOLD);
/*  612 */       sb.setBlendFunction(770, 1);
/*  613 */       sb.draw(ImageMaster.CHECKBOX, this.ascensionModeHb.cX - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.imageScale * 1.2F, this.imageScale * 1.2F, 0.0F, 0, 0, 64, 64, false, false);
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
/*  630 */       sb.setBlendFunction(770, 771);
/*      */     } else {
/*  632 */       sb.draw(ImageMaster.CHECKBOX, this.ascensionModeHb.cX - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.imageScale, this.imageScale, 0.0F, 0, 0, 64, 64, false, false);
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
/*  651 */     if (this.ascensionModeHb.hovered) {
/*  652 */       FontHelper.renderFontCentered(sb, FontHelper.charDescFont, TEXT[4] + this.ascensionLevel, screenX + 240.0F * Settings.scale, this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale, Color.CYAN);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  660 */       FontHelper.renderFontCentered(sb, FontHelper.charDescFont, TEXT[4] + this.ascensionLevel, screenX + 240.0F * Settings.scale, this.scrollY + this.ASCENSION_TEXT_Y * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  669 */     if (this.isAscensionMode) {
/*  670 */       sb.setColor(Color.WHITE);
/*  671 */       sb.draw(ImageMaster.TICK, this.ascensionModeHb.cX - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.imageScale, this.imageScale, 0.0F, 0, 0, 64, 64, false, false);
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
/*  690 */     if (this.ascensionLevel != 0) {
/*  691 */       FontHelper.renderSmartText(sb, FontHelper.charDescFont, CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = CharacterSelectScreen.A_TEXT[this.ascensionLevel - 1], screenX + ASC_RIGHT_W * 1.1F + 400.0F * Settings.scale, this.ascensionModeHb.cY + 10.0F * Settings.scale, 9999.0F, 32.0F * Settings.scale, Settings.CREAM_COLOR);
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
/*  704 */     if (this.ascLeftHb.hovered || Settings.isControllerMode) {
/*  705 */       sb.setColor(Color.WHITE);
/*      */     } else {
/*  707 */       sb.setColor(Color.LIGHT_GRAY);
/*      */     } 
/*      */     
/*  710 */     sb.draw(ImageMaster.CF_LEFT_ARROW, this.ascLeftHb.cX - 24.0F, this.ascLeftHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, this.imageScale, this.imageScale, 0.0F, 0, 0, 48, 48, false, false);
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
/*  728 */     if (this.ascRightHb.hovered || Settings.isControllerMode) {
/*  729 */       sb.setColor(Color.WHITE);
/*      */     } else {
/*  731 */       sb.setColor(Color.LIGHT_GRAY);
/*      */     } 
/*      */     
/*  734 */     sb.draw(ImageMaster.CF_RIGHT_ARROW, this.ascRightHb.cX - 24.0F, this.ascRightHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, this.imageScale, this.imageScale, 0.0F, 0, 0, 48, 48, false, false);
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
/*  752 */     if (Settings.isControllerMode) {
/*  753 */       sb.draw(CInputActionSet.topPanel
/*  754 */           .getKeyImg(), this.ascensionModeHb.cX - 64.0F * Settings.scale - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*  771 */       sb.draw(CInputActionSet.pageLeftViewDeck
/*  772 */           .getKeyImg(), this.ascLeftHb.cX - 12.0F * Settings.scale - 32.0F, this.ascLeftHb.cY + 40.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*  789 */       sb.draw(CInputActionSet.pageRightViewExhaust
/*  790 */           .getKeyImg(), this.ascRightHb.cX + 12.0F * Settings.scale - 32.0F, this.ascRightHb.cY + 40.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*  808 */     this.ascensionModeHb.render(sb);
/*  809 */     this.ascLeftHb.render(sb);
/*  810 */     this.ascRightHb.render(sb);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderSeed(SpriteBatch sb) {
/*  816 */     if (this.seedHb.hovered) {
/*  817 */       FontHelper.renderSmartText(sb, FontHelper.panelNameFont, TEXT[8] + ": " + this.currentSeed, screenX + 96.0F * Settings.scale, this.seedHb.cY, 9999.0F, 32.0F * Settings.scale, Settings.GREEN_TEXT_COLOR);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  827 */       FontHelper.renderSmartText(sb, FontHelper.turnNumFont, TEXT[8] + ": " + this.currentSeed, screenX + 96.0F * Settings.scale, this.seedHb.cY, 9999.0F, 32.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  837 */     this.seedHb.render(sb);
/*      */   }
/*      */   
/*      */   private void renderHeader(SpriteBatch sb, String text, float y) {
/*  841 */     if (Settings.isMobile) {
/*  842 */       FontHelper.renderSmartText(sb, FontHelper.panelNameFont, text, screenX + 50.0F * Settings.scale, y + 850.0F * Settings.scale, 9999.0F, 32.0F * Settings.scale, Settings.GOLD_COLOR, 1.2F);
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
/*  853 */       FontHelper.renderSmartText(sb, FontHelper.panelNameFont, text, screenX + 50.0F * Settings.scale, y + 850.0F * Settings.scale, 9999.0F, 32.0F * Settings.scale, Settings.GOLD_COLOR);
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
/*      */   private void renderTitle(SpriteBatch sb, String text, float y) {
/*  866 */     FontHelper.renderSmartText(sb, FontHelper.charTitleFont, text, screenX, y + 900.0F * Settings.scale, 9999.0F, 32.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  876 */     if (!Settings.usesTrophies) {
/*  877 */       FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, TEXT[1], screenX + 
/*      */ 
/*      */ 
/*      */           
/*  881 */           FontHelper.getSmartWidth(FontHelper.charTitleFont, text, 9999.0F, 9999.0F) + 18.0F * Settings.scale, y + 888.0F * Settings.scale, 9999.0F, 32.0F * Settings.scale, Settings.RED_TEXT_COLOR);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  887 */       FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, TEXT[9], screenX + 
/*      */ 
/*      */ 
/*      */           
/*  891 */           FontHelper.getSmartWidth(FontHelper.charTitleFont, text, 9999.0F, 9999.0F) + 18.0F * Settings.scale, y + 888.0F * Settings.scale, 9999.0F, 32.0F * Settings.scale, Settings.RED_TEXT_COLOR);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private enum CSelectionType
/*      */   {
/*  900 */     CHARACTER, ASCENSION, SEED, MODIFIERS;
/*      */   }
/*      */   
/*      */   private void updateControllerInput() {
/*  904 */     if (!Settings.isControllerMode) {
/*      */       return;
/*      */     }
/*      */     
/*  908 */     CSelectionType type = CSelectionType.CHARACTER;
/*  909 */     boolean anyHovered = false;
/*  910 */     int index = 0;
/*  911 */     for (CustomModeCharacterButton b : this.options) {
/*  912 */       if (b.hb.hovered) {
/*  913 */         anyHovered = true;
/*      */         break;
/*      */       } 
/*  916 */       index++;
/*      */     } 
/*      */     
/*  919 */     if (!anyHovered && this.ascensionModeHb.hovered) {
/*  920 */       anyHovered = true;
/*  921 */       type = CSelectionType.ASCENSION;
/*      */     } 
/*  923 */     if (!anyHovered && this.seedHb.hovered) {
/*  924 */       anyHovered = true;
/*  925 */       type = CSelectionType.SEED;
/*      */     } 
/*  927 */     if (!anyHovered) {
/*  928 */       index = 0;
/*  929 */       for (CustomMod m : this.modList) {
/*  930 */         if (m.hb.hovered) {
/*  931 */           anyHovered = true;
/*  932 */           type = CSelectionType.MODIFIERS;
/*      */           break;
/*      */         } 
/*  935 */         index++;
/*      */       } 
/*      */     } 
/*      */     
/*  939 */     if (!anyHovered && this.controllerHb == null) {
/*  940 */       CInputHelper.setCursor(((CustomModeCharacterButton)this.options.get(0)).hb);
/*  941 */       this.controllerHb = ((CustomModeCharacterButton)this.options.get(0)).hb;
/*      */     } else {
/*  943 */       switch (type) {
/*      */         case CHARACTER:
/*  945 */           if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  946 */             index++;
/*  947 */             if (index > this.options.size() - 1) {
/*  948 */               index = this.options.size() - 1;
/*      */             }
/*  950 */             CInputHelper.setCursor(((CustomModeCharacterButton)this.options.get(index)).hb);
/*  951 */             this.controllerHb = ((CustomModeCharacterButton)this.options.get(index)).hb; break;
/*  952 */           }  if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  953 */             index--;
/*  954 */             if (index < 0) {
/*  955 */               index = 0;
/*      */             }
/*  957 */             CInputHelper.setCursor(((CustomModeCharacterButton)this.options.get(index)).hb);
/*  958 */             this.controllerHb = ((CustomModeCharacterButton)this.options.get(index)).hb; break;
/*  959 */           }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  960 */             CInputHelper.setCursor(this.ascensionModeHb);
/*  961 */             this.controllerHb = this.ascensionModeHb; break;
/*  962 */           }  if (CInputActionSet.select.isJustPressed()) {
/*  963 */             CInputActionSet.select.unpress();
/*  964 */             ((CustomModeCharacterButton)this.options.get(index)).hb.clicked = true;
/*      */           } 
/*      */           break;
/*      */         case ASCENSION:
/*  968 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  969 */             CInputHelper.setCursor(((CustomModeCharacterButton)this.options.get(0)).hb);
/*  970 */             this.controllerHb = ((CustomModeCharacterButton)this.options.get(0)).hb; break;
/*  971 */           }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  972 */             CInputHelper.setCursor(this.seedHb);
/*  973 */             this.controllerHb = this.seedHb;
/*      */           } 
/*      */           break;
/*      */         case SEED:
/*  977 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  978 */             CInputHelper.setCursor(this.ascensionModeHb);
/*  979 */             this.controllerHb = this.ascensionModeHb; break;
/*  980 */           }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  981 */             CInputHelper.setCursor(((CustomMod)this.modList.get(0)).hb);
/*  982 */             this.controllerHb = ((CustomMod)this.modList.get(0)).hb;
/*      */           } 
/*      */           break;
/*      */         case MODIFIERS:
/*  986 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  987 */             index--;
/*  988 */             if (index < 0) {
/*  989 */               CInputHelper.setCursor(this.seedHb);
/*  990 */               this.controllerHb = this.seedHb; break;
/*      */             } 
/*  992 */             CInputHelper.setCursor(((CustomMod)this.modList.get(index)).hb);
/*  993 */             this.controllerHb = ((CustomMod)this.modList.get(index)).hb; break;
/*      */           } 
/*  995 */           if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  996 */             index++;
/*  997 */             if (index > this.modList.size() - 1) {
/*  998 */               index = this.modList.size() - 1;
/*      */             }
/* 1000 */             CInputHelper.setCursor(((CustomMod)this.modList.get(index)).hb);
/* 1001 */             this.controllerHb = ((CustomMod)this.modList.get(index)).hb; break;
/* 1002 */           }  if (CInputActionSet.select.isJustPressed()) {
/* 1003 */             CInputActionSet.select.unpress();
/* 1004 */             ((CustomMod)this.modList.get(index)).hb.clicked = true;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateScrolling() {
/* 1013 */     int y = InputHelper.mY;
/*      */     
/* 1015 */     if (this.scrollUpperBound > 0.0F) {
/* 1016 */       if (!this.grabbedScreen) {
/* 1017 */         if (InputHelper.scrolledDown) {
/* 1018 */           this.targetY += Settings.SCROLL_SPEED;
/* 1019 */         } else if (InputHelper.scrolledUp) {
/* 1020 */           this.targetY -= Settings.SCROLL_SPEED;
/*      */         } 
/*      */         
/* 1023 */         if (InputHelper.justClickedLeft) {
/* 1024 */           this.grabbedScreen = true;
/* 1025 */           this.grabStartY = y - this.targetY;
/*      */         }
/*      */       
/* 1028 */       } else if (InputHelper.isMouseDown) {
/* 1029 */         this.targetY = y - this.grabStartY;
/*      */       } else {
/* 1031 */         this.grabbedScreen = false;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1036 */     this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
/*      */ 
/*      */     
/* 1039 */     if (this.targetY < this.scrollLowerBound) {
/* 1040 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
/* 1041 */     } else if (this.targetY > this.scrollUpperBound) {
/* 1042 */       this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
/*      */     } 
/*      */     
/* 1045 */     updateBarPosition();
/*      */   }
/*      */   
/*      */   private void calculateScrollBounds() {
/* 1049 */     this.scrollUpperBound = this.modList.size() * 90.0F * Settings.scale + 270.0F * Settings.scale;
/* 1050 */     this.scrollLowerBound = 100.0F * Settings.scale;
/*      */   }
/*      */ 
/*      */   
/*      */   public void scrolledUsingBar(float newPercent) {
/* 1055 */     float newPosition = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 1056 */     this.scrollY = newPosition;
/* 1057 */     this.targetY = newPosition;
/* 1058 */     updateBarPosition();
/*      */   }
/*      */   
/*      */   private void updateBarPosition() {
/* 1062 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
/* 1063 */     this.scrollBar.parentScrolledToPercent(percent);
/*      */   }
/*      */   
/*      */   public void deselectOtherOptions(CustomModeCharacterButton characterOption) {
/* 1067 */     for (CustomModeCharacterButton o : this.options) {
/* 1068 */       if (o != characterOption)
/* 1069 */         o.selected = false; 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\custom\CustomModeScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */