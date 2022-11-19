/*     */ package com.megacrit.cardcrawl.neow;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*     */ import com.megacrit.cardcrawl.blights.GrotesqueTrophy;
/*     */ import com.megacrit.cardcrawl.blights.MimicInfestation;
/*     */ import com.megacrit.cardcrawl.blights.Muzzle;
/*     */ import com.megacrit.cardcrawl.blights.Shield;
/*     */ import com.megacrit.cardcrawl.blights.Spear;
/*     */ import com.megacrit.cardcrawl.blights.TimeMaze;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.characters.AnimatedNpc;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*     */ import com.megacrit.cardcrawl.localization.CharacterStrings;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
/*     */ import com.megacrit.cardcrawl.vfx.InfiniteSpeechBubble;
/*     */ import com.megacrit.cardcrawl.vfx.scene.LevelTransitionTextOverlayEffect;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NeowEvent
/*     */   extends AbstractEvent
/*     */ {
/*  45 */   private static final Logger logger = LogManager.getLogger(NeowEvent.class.getName());
/*  46 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Neow Event");
/*     */   
/*  48 */   public static final String[] NAMES = characterStrings.NAMES;
/*  49 */   public static final String[] TEXT = characterStrings.TEXT;
/*  50 */   public static final String[] OPTIONS = characterStrings.OPTIONS;
/*     */   
/*     */   private AnimatedNpc npc;
/*  53 */   public static final String NAME = NAMES[0];
/*  54 */   private int screenNum = 2;
/*     */   private int bossCount;
/*     */   private boolean setPhaseToEvent = false;
/*  57 */   private ArrayList<NeowReward> rewards = new ArrayList<>();
/*  58 */   public static Random rng = null;
/*     */   
/*     */   private boolean pickCard = false;
/*     */   public static boolean waitingToSave = false;
/*  62 */   private static final float DIALOG_X = 1100.0F * Settings.xScale, DIALOG_Y = AbstractDungeon.floorY + 60.0F * Settings.yScale;
/*     */ 
/*     */ 
/*     */   
/*     */   public NeowEvent(boolean isDone) {
/*  67 */     waitingToSave = false;
/*     */     
/*  69 */     if (this.npc == null) {
/*  70 */       this.npc = new AnimatedNpc(1534.0F * Settings.xScale, AbstractDungeon.floorY - 60.0F * Settings.yScale, "images/npcs/neow/skeleton.atlas", "images/npcs/neow/skeleton.json", "idle");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.roomEventText.clear();
/*  79 */     playSfx();
/*     */     
/*  81 */     if (!Settings.isEndless || AbstractDungeon.floorNum <= 1)
/*     */     {
/*  83 */       if (Settings.isStandardRun() || (Settings.isEndless && AbstractDungeon.floorNum <= 1)) {
/*  84 */         this.bossCount = CardCrawlGame.playerPref.getInteger(AbstractDungeon.player.chosenClass.name() + "_SPIRITS", 0);
/*  85 */         AbstractDungeon.bossCount = this.bossCount;
/*  86 */       } else if (Settings.seedSet) {
/*  87 */         this.bossCount = 1;
/*     */       } else {
/*  89 */         this.bossCount = 0;
/*     */       } 
/*     */     }
/*  92 */     this.body = "";
/*     */     
/*  94 */     if (Settings.isEndless && AbstractDungeon.floorNum > 1) {
/*     */       
/*  96 */       talk(TEXT[MathUtils.random(12, 14)]);
/*  97 */       this.screenNum = 999;
/*  98 */       this.roomEventText.addDialogOption(OPTIONS[0]);
/*  99 */     } else if (shouldSkipNeowDialog()) {
/* 100 */       this.screenNum = 10;
/* 101 */       talk(TEXT[10]);
/* 102 */       this.roomEventText.addDialogOption(OPTIONS[1]);
/*     */     }
/* 104 */     else if (!isDone) {
/*     */       
/* 106 */       if (!((Boolean)TipTracker.tips.get("NEOW_INTRO")).booleanValue()) {
/* 107 */         this.screenNum = 0;
/* 108 */         TipTracker.neverShowAgain("NEOW_INTRO");
/* 109 */         talk(TEXT[0]);
/* 110 */         this.roomEventText.addDialogOption(OPTIONS[1]);
/*     */       } else {
/*     */         
/* 113 */         this.screenNum = 1;
/* 114 */         talk(TEXT[MathUtils.random(1, 3)]);
/* 115 */         this.roomEventText.addDialogOption(OPTIONS[1]);
/*     */       } 
/* 117 */       AbstractDungeon.topLevelEffects.add(new LevelTransitionTextOverlayEffect(AbstractDungeon.name, AbstractDungeon.levelNum, true));
/*     */     } else {
/*     */       
/* 120 */       this.screenNum = 99;
/* 121 */       talk(TEXT[8]);
/* 122 */       this.roomEventText.addDialogOption(OPTIONS[3]);
/*     */     } 
/*     */     
/* 125 */     this.hasDialog = true;
/* 126 */     this.hasFocus = true;
/*     */   }
/*     */   
/*     */   public NeowEvent() {
/* 130 */     this(false);
/*     */   }
/*     */   
/*     */   private boolean shouldSkipNeowDialog() {
/* 134 */     if (Settings.seedSet && !Settings.isTrial && !Settings.isDailyRun) {
/* 135 */       return false;
/*     */     }
/* 137 */     return !Settings.isStandardRun();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 142 */     super.update();
/*     */     
/* 144 */     if (this.pickCard && 
/* 145 */       !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 146 */       CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 147 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 148 */         group.addToBottom(c.makeCopy());
/*     */       }
/* 150 */       AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, TEXT[11]);
/* 151 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*     */     } 
/*     */ 
/*     */     
/* 155 */     for (NeowReward r : this.rewards) {
/* 156 */       r.update();
/*     */     }
/*     */     
/* 159 */     if (!this.setPhaseToEvent) {
/* 160 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.EVENT;
/* 161 */       this.setPhaseToEvent = true;
/*     */     } 
/*     */ 
/*     */     
/* 165 */     if (!RoomEventDialog.waitForInput) {
/* 166 */       buttonEffect(this.roomEventText.getSelectedOption());
/*     */     }
/*     */ 
/*     */     
/* 170 */     if (waitingToSave && !AbstractDungeon.isScreenUp && AbstractDungeon.topLevelEffects.isEmpty() && AbstractDungeon.player
/* 171 */       .relicsDoneAnimating()) {
/* 172 */       boolean doneAnims = true;
/*     */       
/* 174 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 175 */         if (!r.isDone) {
/* 176 */           doneAnims = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 181 */       if (doneAnims) {
/* 182 */         waitingToSave = false;
/* 183 */         SaveHelper.saveIfAppropriate(SaveFile.SaveType.POST_NEOW);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void talk(String msg) {
/* 189 */     AbstractDungeon.effectList.add(new InfiniteSpeechBubble(DIALOG_X, DIALOG_Y, msg));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/* 194 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/* 197 */         dismissBubble();
/* 198 */         talk(TEXT[4]);
/* 199 */         if (this.bossCount != 0 || Settings.isTestingNeow) {
/* 200 */           blessing();
/*     */         } else {
/* 202 */           miniBlessing();
/*     */         } 
/*     */         return;
/*     */ 
/*     */       
/*     */       case 1:
/* 208 */         dismissBubble();
/* 209 */         logger.info(Integer.valueOf(this.bossCount));
/*     */         
/* 211 */         if (this.bossCount == 0 && !Settings.isTestingNeow) {
/* 212 */           miniBlessing();
/*     */         } else {
/* 214 */           blessing();
/*     */         } 
/*     */         return;
/*     */ 
/*     */       
/*     */       case 2:
/* 220 */         if (buttonPressed == 0) {
/* 221 */           blessing();
/*     */         } else {
/* 223 */           openMap();
/*     */         } 
/*     */         return;
/*     */ 
/*     */       
/*     */       case 3:
/* 229 */         dismissBubble();
/* 230 */         this.roomEventText.clearRemainingOptions();
/*     */         
/* 232 */         switch (buttonPressed) {
/*     */           case 0:
/* 234 */             ((NeowReward)this.rewards.get(0)).activate();
/* 235 */             talk(TEXT[8]);
/*     */             break;
/*     */           case 1:
/* 238 */             ((NeowReward)this.rewards.get(1)).activate();
/* 239 */             talk(TEXT[8]);
/*     */             break;
/*     */           case 2:
/* 242 */             ((NeowReward)this.rewards.get(2)).activate();
/* 243 */             talk(TEXT[9]);
/*     */             break;
/*     */           case 3:
/* 246 */             ((NeowReward)this.rewards.get(3)).activate();
/* 247 */             talk(TEXT[9]);
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 254 */         this.screenNum = 99;
/* 255 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/* 256 */         this.roomEventText.clearRemainingOptions();
/* 257 */         waitingToSave = true;
/*     */         return;
/*     */ 
/*     */       
/*     */       case 10:
/* 262 */         dailyBlessing();
/* 263 */         this.roomEventText.clearRemainingOptions();
/* 264 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/* 265 */         this.screenNum = 99;
/*     */         return;
/*     */ 
/*     */       
/*     */       case 999:
/* 270 */         endlessBlight();
/* 271 */         this.roomEventText.clearRemainingOptions();
/* 272 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/* 273 */         this.screenNum = 99;
/*     */         return;
/*     */     } 
/* 276 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void endlessBlight() {
/* 283 */     if (AbstractDungeon.player.hasBlight("DeadlyEnemies")) {
/* 284 */       AbstractBlight tmp = AbstractDungeon.player.getBlight("DeadlyEnemies");
/* 285 */       tmp.incrementUp();
/* 286 */       tmp.flash();
/*     */     } else {
/* 288 */       AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new Spear());
/*     */     } 
/*     */     
/* 291 */     if (AbstractDungeon.player.hasBlight("ToughEnemies")) {
/* 292 */       AbstractBlight tmp = AbstractDungeon.player.getBlight("ToughEnemies");
/* 293 */       tmp.incrementUp();
/* 294 */       tmp.flash();
/*     */     } else {
/* 296 */       AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new Shield());
/*     */     } 
/*     */ 
/*     */     
/* 300 */     uniqueBlight();
/*     */   }
/*     */ 
/*     */   
/*     */   private void uniqueBlight() {
/* 305 */     AbstractBlight temp = AbstractDungeon.player.getBlight("MimicInfestation");
/* 306 */     if (temp != null) {
/* 307 */       temp = AbstractDungeon.player.getBlight("TimeMaze");
/* 308 */       if (temp != null) {
/* 309 */         temp = AbstractDungeon.player.getBlight("FullBelly");
/* 310 */         if (temp != null) {
/* 311 */           temp = AbstractDungeon.player.getBlight("GrotesqueTrophy");
/* 312 */           if (temp != null) {
/*     */             
/* 314 */             AbstractDungeon.player.getBlight("GrotesqueTrophy").stack();
/*     */           } else {
/*     */             
/* 317 */             AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new GrotesqueTrophy());
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 324 */           AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new Muzzle());
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 331 */         AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new TimeMaze());
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 338 */       AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new MimicInfestation());
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dailyBlessing() {
/* 347 */     rng = new Random(Settings.seed);
/* 348 */     dismissBubble();
/* 349 */     talk(TEXT[8]);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     if (ModHelper.isModEnabled("Heirloom")) {
/* 355 */       AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, 
/*     */ 
/*     */           
/* 358 */           AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE));
/*     */     }
/*     */ 
/*     */     
/* 362 */     boolean addedCards = false;
/* 363 */     CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*     */ 
/*     */     
/* 366 */     if (ModHelper.isModEnabled("Allstar")) {
/* 367 */       addedCards = true;
/* 368 */       for (int i = 0; i < 5; i++) {
/* 369 */         AbstractCard colorlessCard = AbstractDungeon.getColorlessCardFromPool(
/* 370 */             AbstractDungeon.rollRareOrUncommon(0.5F));
/* 371 */         UnlockTracker.markCardAsSeen(colorlessCard.cardID);
/* 372 */         group.addToBottom(colorlessCard.makeCopy());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 377 */     if (ModHelper.isModEnabled("Specialized")) {
/* 378 */       if (!ModHelper.isModEnabled("SealedDeck") && !ModHelper.isModEnabled("Draft")) {
/* 379 */         addedCards = true;
/* 380 */         AbstractCard rareCard = AbstractDungeon.returnTrulyRandomCard();
/*     */         
/* 382 */         UnlockTracker.markCardAsSeen(rareCard.cardID);
/* 383 */         group.addToBottom(rareCard.makeCopy());
/* 384 */         group.addToBottom(rareCard.makeCopy());
/* 385 */         group.addToBottom(rareCard.makeCopy());
/* 386 */         group.addToBottom(rareCard.makeCopy());
/* 387 */         group.addToBottom(rareCard.makeCopy());
/*     */       } else {
/* 389 */         AbstractCard rareCard = AbstractDungeon.returnTrulyRandomCard();
/* 390 */         for (int i = 0; i < 5; i++) {
/* 391 */           AbstractCard tmpCard = rareCard.makeCopy();
/* 392 */           AbstractDungeon.topLevelEffectsQueue.add(new FastCardObtainEffect(tmpCard, 
/*     */ 
/*     */                 
/* 395 */                 MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), 
/* 396 */                 MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 402 */     if (addedCards) {
/* 403 */       AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, TEXT[11]);
/*     */     }
/*     */ 
/*     */     
/* 407 */     if (ModHelper.isModEnabled("Draft")) {
/* 408 */       AbstractDungeon.cardRewardScreen.draftOpen();
/*     */     }
/*     */ 
/*     */     
/* 412 */     this.pickCard = true;
/* 413 */     if (ModHelper.isModEnabled("SealedDeck")) {
/* 414 */       CardGroup sealedGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*     */ 
/*     */       
/* 417 */       for (int i = 0; i < 30; i++) {
/* 418 */         AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity());
/* 419 */         if (!sealedGroup.contains(card)) {
/* 420 */           sealedGroup.addToBottom(card.makeCopy());
/*     */         } else {
/* 422 */           i--;
/*     */         } 
/*     */       } 
/*     */       
/* 426 */       for (AbstractCard c : sealedGroup.group) {
/* 427 */         UnlockTracker.markCardAsSeen(c.cardID);
/*     */       }
/* 429 */       AbstractDungeon.gridSelectScreen.open(sealedGroup, 10, OPTIONS[4], false);
/*     */     } 
/*     */     
/* 432 */     this.roomEventText.clearRemainingOptions();
/* 433 */     this.screenNum = 99;
/*     */   }
/*     */   
/*     */   private void miniBlessing() {
/* 437 */     AbstractDungeon.bossCount = 0;
/* 438 */     dismissBubble();
/* 439 */     talk(TEXT[MathUtils.random(4, 6)]);
/*     */     
/* 441 */     this.rewards.add(new NeowReward(true));
/* 442 */     this.rewards.add(new NeowReward(false));
/*     */     
/* 444 */     this.roomEventText.clearRemainingOptions();
/* 445 */     this.roomEventText.updateDialogOption(0, ((NeowReward)this.rewards.get(0)).optionLabel);
/* 446 */     this.roomEventText.addDialogOption(((NeowReward)this.rewards.get(1)).optionLabel);
/*     */     
/* 448 */     this.screenNum = 3;
/*     */   }
/*     */   
/*     */   private void blessing() {
/* 452 */     logger.info("BLESSING");
/* 453 */     rng = new Random(Settings.seed);
/* 454 */     logger.info("COUNTER: " + rng.counter);
/* 455 */     AbstractDungeon.bossCount = 0;
/* 456 */     dismissBubble();
/* 457 */     talk(TEXT[7]);
/*     */     
/* 459 */     this.rewards.add(new NeowReward(0));
/* 460 */     this.rewards.add(new NeowReward(1));
/* 461 */     this.rewards.add(new NeowReward(2));
/* 462 */     this.rewards.add(new NeowReward(3));
/*     */     
/* 464 */     this.roomEventText.clearRemainingOptions();
/* 465 */     this.roomEventText.updateDialogOption(0, ((NeowReward)this.rewards.get(0)).optionLabel);
/* 466 */     this.roomEventText.addDialogOption(((NeowReward)this.rewards.get(1)).optionLabel);
/* 467 */     this.roomEventText.addDialogOption(((NeowReward)this.rewards.get(2)).optionLabel);
/* 468 */     this.roomEventText.addDialogOption(((NeowReward)this.rewards.get(3)).optionLabel);
/*     */     
/* 470 */     this.screenNum = 3;
/*     */   }
/*     */   
/*     */   private void dismissBubble() {
/* 474 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 475 */       if (e instanceof InfiniteSpeechBubble) {
/* 476 */         ((InfiniteSpeechBubble)e).dismiss();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 482 */     int roll = MathUtils.random(3);
/* 483 */     if (roll == 0) {
/* 484 */       CardCrawlGame.sound.play("VO_NEOW_1A");
/* 485 */     } else if (roll == 1) {
/* 486 */       CardCrawlGame.sound.play("VO_NEOW_1B");
/* 487 */     } else if (roll == 2) {
/* 488 */       CardCrawlGame.sound.play("VO_NEOW_2A");
/*     */     } else {
/* 490 */       CardCrawlGame.sound.play("VO_NEOW_2B");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void logMetric(String actionTaken) {
/* 495 */     AbstractEvent.logMetric(NAME, actionTaken);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 500 */     this.npc.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 505 */     super.dispose();
/* 506 */     if (this.npc != null) {
/* 507 */       logger.info("Disposing Neow asset.");
/* 508 */       this.npc.dispose();
/* 509 */       this.npc = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\neow\NeowEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */