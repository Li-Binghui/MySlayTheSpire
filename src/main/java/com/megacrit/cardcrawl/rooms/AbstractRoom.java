/*     */ package com.megacrit.cardcrawl.rooms;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.utils.Disposable;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
/*     */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*     */ import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EndTurnAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainEnergyAndEnableControlsAction;
/*     */ import com.megacrit.cardcrawl.actions.common.MonsterStartTurnAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.SoulGroup;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.daily.mods.Careless;
/*     */ import com.megacrit.cardcrawl.daily.mods.ControlledChaos;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.DevInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.monsters.MonsterGroup;
/*     */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*     */ import com.megacrit.cardcrawl.potions.BlessingOfTheForge;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*     */ import com.megacrit.cardcrawl.ui.buttons.CancelButton;
/*     */ import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.GameSavedEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public abstract class AbstractRoom
/*     */   implements Disposable
/*     */ {
/*  75 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractRoom");
/*  76 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  78 */   private static final Logger logger = LogManager.getLogger(AbstractRoom.class.getName());
/*  79 */   public ArrayList<AbstractPotion> potions = new ArrayList<>();
/*  80 */   public ArrayList<AbstractRelic> relics = new ArrayList<>();
/*  81 */   public ArrayList<RewardItem> rewards = new ArrayList<>();
/*  82 */   public SoulGroup souls = new SoulGroup();
/*     */   public RoomPhase phase;
/*  84 */   public AbstractEvent event = null;
/*     */   public MonsterGroup monsters;
/*  86 */   private float endBattleTimer = 0.0F;
/*  87 */   public float rewardPopOutTimer = 1.0F; private static final float END_TURN_WAIT_DURATION = 1.2F;
/*     */   protected String mapSymbol;
/*     */   protected Texture mapImg;
/*     */   protected Texture mapImgOutline;
/*     */   public boolean isBattleOver = false;
/*     */   public boolean cannotLose = false;
/*     */   public boolean eliteTrigger = false;
/*  94 */   public static int blizzardPotionMod = 0;
/*     */   
/*     */   private static final int BLIZZARD_POTION_MOD_AMT = 10;
/*     */   
/*     */   public boolean mugged = false, smoked = false;
/*     */   public boolean combatEvent = false;
/*     */   public boolean rewardAllowed = true;
/*     */   public boolean rewardTime = false;
/*     */   public boolean skipMonsterTurn = false;
/* 103 */   public int baseRareCardChance = 3;
/* 104 */   public int baseUncommonCardChance = 37;
/* 105 */   public int rareCardChance = this.baseRareCardChance;
/* 106 */   public int uncommonCardChance = this.baseUncommonCardChance;
/*     */   
/* 108 */   public static float waitTimer = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Texture getMapImg() {
/* 116 */     return this.mapImg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Texture getMapImgOutline() {
/* 125 */     return this.mapImgOutline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setMapImg(Texture img, Texture imgOutline) {
/* 134 */     this.mapImg = img;
/* 135 */     this.mapImgOutline = imgOutline;
/*     */   }
/*     */   
/*     */   public abstract void onPlayerEntry();
/*     */   
/*     */   public enum RoomPhase
/*     */   {
/* 142 */     COMBAT, EVENT, COMPLETE, INCOMPLETE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum RoomType
/*     */   {
/* 149 */     SHOP, MONSTER, SHRINE, TREASURE, EVENT, BOSS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyEmeraldEliteBuff() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playBGM(String key) {
/* 161 */     CardCrawlGame.music.playTempBGM(key);
/*     */   }
/*     */   
/*     */   public void playBgmInstantly(String key) {
/* 165 */     CardCrawlGame.music.playTempBgmInstantly(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getMapSymbol() {
/* 172 */     return this.mapSymbol;
/*     */   }
/*     */   
/*     */   public final void setMapSymbol(String newSymbol) {
/* 176 */     this.mapSymbol = newSymbol;
/*     */   }
/*     */   
/*     */   public AbstractCard.CardRarity getCardRarity(int roll) {
/* 180 */     return getCardRarity(roll, true);
/*     */   }
/*     */   
/*     */   public AbstractCard.CardRarity getCardRarity(int roll, boolean useAlternation) {
/* 184 */     this.rareCardChance = this.baseRareCardChance;
/* 185 */     this.uncommonCardChance = this.baseUncommonCardChance;
/* 186 */     if (useAlternation) {
/* 187 */       alterCardRarityProbabilities();
/*     */     }
/* 189 */     if (roll < this.rareCardChance) {
/* 190 */       if (roll >= this.baseRareCardChance) {
/* 191 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 192 */           if (r.changeRareCardRewardChance(this.baseRareCardChance) > this.baseRareCardChance) {
/* 193 */             r.flash();
/*     */           }
/*     */         } 
/*     */       }
/* 197 */       return AbstractCard.CardRarity.RARE;
/* 198 */     }  if (roll < this.rareCardChance + this.uncommonCardChance) {
/* 199 */       if (roll >= this.baseRareCardChance + this.baseUncommonCardChance) {
/* 200 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 201 */           if (r.changeUncommonCardRewardChance(this.baseUncommonCardChance) > this.baseUncommonCardChance) {
/* 202 */             r.flash();
/*     */           }
/*     */         } 
/*     */       }
/* 206 */       return AbstractCard.CardRarity.UNCOMMON;
/*     */     } 
/* 208 */     return AbstractCard.CardRarity.COMMON;
/*     */   }
/*     */   
/*     */   public void alterCardRarityProbabilities() {
/* 212 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 213 */       this.rareCardChance = r.changeRareCardRewardChance(this.rareCardChance);
/*     */     }
/* 215 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 216 */       this.uncommonCardChance = r.changeUncommonCardRewardChance(this.uncommonCardChance);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObjects() {
/* 226 */     this.souls.update();
/*     */ 
/*     */     
/* 229 */     for (Iterator<AbstractPotion> iterator = this.potions.iterator(); iterator.hasNext(); ) {
/* 230 */       AbstractPotion tmpPotion = iterator.next();
/* 231 */       tmpPotion.update();
/* 232 */       if (tmpPotion.isObtained) {
/* 233 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 239 */     for (Iterator<AbstractRelic> i = this.relics.iterator(); i.hasNext(); ) {
/* 240 */       AbstractRelic relic = i.next();
/* 241 */       relic.update();
/* 242 */       if (relic.isDone) {
/* 243 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 252 */     if (!AbstractDungeon.isScreenUp && InputHelper.pressedEscape && AbstractDungeon.overlayMenu.cancelButton.current_x == CancelButton.HIDE_X)
/*     */     {
/* 254 */       AbstractDungeon.settingsScreen.open();
/*     */     }
/*     */     
/* 257 */     if (Settings.isDebug) {
/* 258 */       if (InputHelper.justClickedRight) {
/* 259 */         AbstractDungeon.player.obtainPotion((AbstractPotion)new BlessingOfTheForge());
/* 260 */         AbstractDungeon.scene.randomizeScene();
/*     */       } 
/*     */       
/* 263 */       if (Gdx.input.isKeyJustPressed(49)) {
/* 264 */         AbstractDungeon.player.increaseMaxOrbSlots(1, true);
/*     */       }
/*     */       
/* 267 */       if (DevInputActionSet.gainGold.isJustPressed()) {
/* 268 */         AbstractDungeon.player.gainGold(100);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 273 */     switch (this.phase) {
/*     */       case EVENT:
/* 275 */         this.event.updateDialog();
/*     */         break;
/*     */       case COMBAT:
/* 278 */         this.monsters.update();
/*     */         
/* 280 */         if (waitTimer > 0.0F) {
/* 281 */           if (AbstractDungeon.actionManager.currentAction != null || 
/* 282 */             !AbstractDungeon.actionManager.isEmpty()) {
/* 283 */             AbstractDungeon.actionManager.update();
/*     */           } else {
/* 285 */             waitTimer -= Gdx.graphics.getDeltaTime();
/*     */           } 
/* 287 */           if (waitTimer <= 0.0F) {
/*     */             
/* 289 */             AbstractDungeon.actionManager.turnHasEnded = true;
/* 290 */             if (!AbstractDungeon.isScreenUp) {
/* 291 */               AbstractDungeon.topLevelEffects.add(new BattleStartEffect(false));
/*     */             }
/* 293 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainEnergyAndEnableControlsAction(AbstractDungeon.player.energy.energyMaster));
/*     */             
/* 295 */             AbstractDungeon.player.applyStartOfCombatPreDrawLogic();
/* 296 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player.gameHandSize));
/*     */             
/* 298 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnableEndTurnButtonAction());
/* 299 */             AbstractDungeon.overlayMenu.showCombatPanels();
/* 300 */             AbstractDungeon.player.applyStartOfCombatLogic();
/*     */ 
/*     */             
/* 303 */             if (ModHelper.isModEnabled("Careless")) {
/* 304 */               Careless.modAction();
/*     */             }
/* 306 */             if (ModHelper.isModEnabled("ControlledChaos")) {
/* 307 */               ControlledChaos.modAction();
/*     */             }
/*     */ 
/*     */             
/* 311 */             this.skipMonsterTurn = false;
/* 312 */             AbstractDungeon.player.applyStartOfTurnRelics();
/* 313 */             AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
/* 314 */             AbstractDungeon.player.applyStartOfTurnCards();
/* 315 */             AbstractDungeon.player.applyStartOfTurnPowers();
/* 316 */             AbstractDungeon.player.applyStartOfTurnOrbs();
/* 317 */             AbstractDungeon.actionManager.useNextCombatActions();
/*     */           } 
/*     */         } else {
/* 320 */           if (Settings.isDebug && DevInputActionSet.drawCard.isJustPressed()) {
/* 321 */             AbstractDungeon.actionManager.addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 1));
/*     */           }
/*     */           
/* 324 */           if (!AbstractDungeon.isScreenUp) {
/* 325 */             AbstractDungeon.actionManager.update();
/*     */             
/* 327 */             if (!this.monsters.areMonstersBasicallyDead() && AbstractDungeon.player.currentHealth > 0) {
/* 328 */               AbstractDungeon.player.updateInput();
/*     */             }
/*     */           } 
/*     */           
/* 332 */           if (!AbstractDungeon.screen.equals(AbstractDungeon.CurrentScreen.HAND_SELECT)) {
/* 333 */             AbstractDungeon.player.combatUpdate();
/*     */           }
/*     */           
/* 336 */           if (AbstractDungeon.player.isEndingTurn) {
/* 337 */             endTurn();
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 342 */         if (this.isBattleOver && AbstractDungeon.actionManager.actions.isEmpty()) {
/* 343 */           this.skipMonsterTurn = false;
/* 344 */           this.endBattleTimer -= Gdx.graphics.getDeltaTime();
/* 345 */           if (this.endBattleTimer < 0.0F) {
/* 346 */             this.phase = RoomPhase.COMPLETE;
/*     */             
/* 348 */             if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) || !(CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) || Settings.isEndless)
/*     */             {
/*     */ 
/*     */               
/* 352 */               CardCrawlGame.sound.play("VICTORY");
/*     */             }
/*     */             
/* 355 */             this.endBattleTimer = 0.0F;
/*     */             
/* 357 */             if (this instanceof MonsterRoomBoss && !AbstractDungeon.loading_post_combat) {
/* 358 */               if (!CardCrawlGame.loadingSave) {
/* 359 */                 if (Settings.isDailyRun) {
/* 360 */                   addGoldToRewards(100);
/*     */                 } else {
/* 362 */                   int tmp = 100 + AbstractDungeon.miscRng.random(-5, 5);
/*     */ 
/*     */ 
/*     */                   
/* 366 */                   if (AbstractDungeon.ascensionLevel >= 13) {
/* 367 */                     addGoldToRewards(MathUtils.round(tmp * 0.75F));
/*     */                   } else {
/* 369 */                     addGoldToRewards(tmp);
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */ 
/*     */               
/* 375 */               if (ModHelper.isModEnabled("Cursed Run")) {
/* 376 */                 AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
/*     */                       
/* 378 */                       AbstractDungeon.returnRandomCurse(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */               
/*     */               }
/*     */             
/*     */             }
/* 384 */             else if (this instanceof MonsterRoomElite && !AbstractDungeon.loading_post_combat) {
/* 385 */               if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.Exordium) {
/* 386 */                 CardCrawlGame.elites1Slain++;
/* 387 */                 logger.info("ELITES SLAIN " + CardCrawlGame.elites1Slain);
/* 388 */               } else if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/* 389 */                 CardCrawlGame.elites2Slain++;
/* 390 */                 logger.info("ELITES SLAIN " + CardCrawlGame.elites2Slain);
/* 391 */               } else if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) {
/* 392 */                 CardCrawlGame.elites3Slain++;
/* 393 */                 logger.info("ELITES SLAIN " + CardCrawlGame.elites3Slain);
/*     */               } else {
/* 395 */                 CardCrawlGame.elitesModdedSlain++;
/* 396 */                 logger.info("ELITES SLAIN " + CardCrawlGame.elitesModdedSlain);
/*     */               } 
/*     */               
/* 399 */               if (!CardCrawlGame.loadingSave) {
/* 400 */                 if (Settings.isDailyRun) {
/* 401 */                   addGoldToRewards(30);
/*     */                 } else {
/* 403 */                   addGoldToRewards(AbstractDungeon.treasureRng.random(25, 35));
/*     */                 } 
/*     */               }
/* 406 */             } else if (this instanceof MonsterRoom && 
/* 407 */               !AbstractDungeon.getMonsters().haveMonstersEscaped()) {
/* 408 */               CardCrawlGame.monstersSlain++;
/* 409 */               logger.info("MONSTERS SLAIN " + CardCrawlGame.monstersSlain);
/*     */               
/* 411 */               if (Settings.isDailyRun) {
/* 412 */                 addGoldToRewards(15);
/*     */               } else {
/* 414 */                 addGoldToRewards(AbstractDungeon.treasureRng.random(10, 20));
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 419 */             if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) || (!(CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) && !(CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheEnding)) || Settings.isEndless) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 424 */               if (!AbstractDungeon.loading_post_combat) {
/* 425 */                 dropReward();
/* 426 */                 addPotionToRewards();
/*     */               } 
/* 428 */               int card_seed_before_roll = AbstractDungeon.cardRng.counter;
/* 429 */               int card_randomizer_before_roll = AbstractDungeon.cardBlizzRandomizer;
/*     */               
/* 431 */               if (this.rewardAllowed) {
/* 432 */                 if (this.mugged) {
/* 433 */                   AbstractDungeon.combatRewardScreen.openCombat(TEXT[0]);
/* 434 */                 } else if (this.smoked) {
/* 435 */                   AbstractDungeon.combatRewardScreen.openCombat(TEXT[1], true);
/*     */                 } else {
/* 437 */                   AbstractDungeon.combatRewardScreen.open();
/*     */                 } 
/*     */                 
/* 440 */                 if (!CardCrawlGame.loadingSave && !AbstractDungeon.loading_post_combat) {
/* 441 */                   SaveFile saveFile = new SaveFile(SaveFile.SaveType.POST_COMBAT);
/* 442 */                   saveFile.card_seed_count = card_seed_before_roll;
/* 443 */                   saveFile.card_random_seed_randomizer = card_randomizer_before_roll;
/*     */                   
/* 445 */                   if (this.combatEvent) {
/* 446 */                     saveFile.event_seed_count--;
/*     */                   }
/* 448 */                   SaveAndContinue.save(saveFile);
/* 449 */                   AbstractDungeon.effectList.add(new GameSavedEffect());
/*     */                 } else {
/* 451 */                   CardCrawlGame.loadingSave = false;
/*     */                 } 
/* 453 */                 AbstractDungeon.loading_post_combat = false;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 459 */         this.monsters.updateAnimations();
/*     */         break;
/*     */       case COMPLETE:
/* 462 */         if (!AbstractDungeon.isScreenUp) {
/* 463 */           AbstractDungeon.actionManager.update();
/* 464 */           if (this.event != null) {
/* 465 */             this.event.updateDialog();
/*     */           }
/* 467 */           if (AbstractDungeon.actionManager.isEmpty() && !AbstractDungeon.isFadingOut) {
/*     */             
/* 469 */             if (this.rewardPopOutTimer > 1.0F) {
/* 470 */               this.rewardPopOutTimer = 1.0F;
/*     */             }
/* 472 */             this.rewardPopOutTimer -= Gdx.graphics.getDeltaTime();
/*     */             
/* 474 */             if (this.rewardPopOutTimer < 0.0F) {
/* 475 */               if (this.event == null) {
/* 476 */                 AbstractDungeon.overlayMenu.proceedButton.show(); break;
/* 477 */               }  if (!(this.event instanceof com.megacrit.cardcrawl.events.AbstractImageEvent) && !this.event.hasFocus) {
/* 478 */                 AbstractDungeon.overlayMenu.proceedButton.show();
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case INCOMPLETE:
/*     */         break;
/*     */       default:
/* 487 */         logger.info("MISSING PHASE, bro");
/*     */         break;
/*     */     } 
/*     */     
/* 491 */     AbstractDungeon.player.update();
/* 492 */     AbstractDungeon.player.updateAnimations();
/*     */   }
/*     */ 
/*     */   
/*     */   public void endTurn() {
/* 497 */     AbstractDungeon.player.applyEndOfTurnTriggers();
/*     */     
/* 499 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ClearCardQueueAction());
/* 500 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DiscardAtEndOfTurnAction());
/*     */ 
/*     */     
/* 503 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 504 */       c.resetAttributes();
/*     */     }
/* 506 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 507 */       c.resetAttributes();
/*     */     }
/* 509 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 510 */       c.resetAttributes();
/*     */     }
/*     */     
/* 513 */     if (AbstractDungeon.player.hoveredCard != null) {
/* 514 */       AbstractDungeon.player.hoveredCard.resetAttributes();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 521 */     AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
/*     */         {
/*     */           public void update() {
/* 524 */             addToBot((AbstractGameAction)new EndTurnAction());
/* 525 */             addToBot((AbstractGameAction)new WaitAction(1.2F));
/* 526 */             if (!AbstractRoom.this.skipMonsterTurn) {
/* 527 */               addToBot((AbstractGameAction)new MonsterStartTurnAction());
/*     */             }
/* 529 */             AbstractDungeon.actionManager.monsterAttacksQueued = false;
/* 530 */             this.isDone = true;
/*     */           }
/*     */         });
/*     */     
/* 534 */     AbstractDungeon.player.isEndingTurn = false;
/*     */   }
/*     */   
/*     */   public void endBattle() {
/* 538 */     this.isBattleOver = true;
/*     */ 
/*     */     
/* 541 */     if (AbstractDungeon.player.currentHealth == 1) {
/* 542 */       UnlockTracker.unlockAchievement("SHRUG_IT_OFF");
/*     */     }
/*     */     
/* 545 */     if (AbstractDungeon.player.hasRelic("Meat on the Bone")) {
/* 546 */       AbstractDungeon.player.getRelic("Meat on the Bone").onTrigger();
/*     */     }
/*     */     
/* 549 */     AbstractDungeon.player.onVictory();
/* 550 */     this.endBattleTimer = 0.25F;
/*     */     
/* 552 */     int attackCount = 0, skillCount = 0;
/* 553 */     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
/* 554 */       if (c.type == AbstractCard.CardType.ATTACK) {
/* 555 */         attackCount++; break;
/*     */       } 
/* 557 */       if (c.type == AbstractCard.CardType.SKILL) {
/* 558 */         skillCount++;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 563 */     if (attackCount == 0 && this.smoked != true) {
/* 564 */       UnlockTracker.unlockAchievement("COME_AT_ME");
/*     */     }
/*     */ 
/*     */     
/* 568 */     if (skillCount == 0);
/*     */ 
/*     */     
/* 571 */     if (this.smoked != true)
/*     */     {
/* 573 */       if (GameActionManager.damageReceivedThisCombat - GameActionManager.hpLossThisCombat <= 0 && this instanceof MonsterRoomElite)
/*     */       {
/* 575 */         CardCrawlGame.champion++;
/*     */       }
/*     */     }
/*     */     
/* 579 */     CardCrawlGame.metricData.addEncounterData();
/*     */     
/* 581 */     AbstractDungeon.actionManager.clear();
/* 582 */     AbstractDungeon.player.inSingleTargetMode = false;
/* 583 */     AbstractDungeon.player.releaseCard();
/* 584 */     AbstractDungeon.player.hand.refreshHandLayout();
/* 585 */     AbstractDungeon.player.resetControllerValues();
/* 586 */     AbstractDungeon.overlayMenu.hideCombatPanels();
/*     */     
/* 588 */     if (!AbstractDungeon.player.stance.ID.equals("Neutral") && AbstractDungeon.player.stance != null)
/*     */     {
/* 590 */       AbstractDungeon.player.stance.stopIdleSfx();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropReward() {}
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 599 */     if (this instanceof EventRoom || this instanceof VictoryRoom) {
/*     */       
/* 601 */       if (this.event != null && (!(this.event instanceof com.megacrit.cardcrawl.events.AbstractImageEvent) || this.event.combatTime)) {
/* 602 */         this.event.renderRoomEventPanel(sb);
/* 603 */         if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.VICTORY) {
/* 604 */           AbstractDungeon.player.render(sb);
/*     */         }
/*     */       } 
/* 607 */     } else if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*     */       
/* 609 */       AbstractDungeon.player.render(sb);
/*     */     } 
/*     */     
/* 612 */     if (!(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {
/* 613 */       if (this.monsters != null && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.DEATH) {
/* 614 */         this.monsters.render(sb);
/*     */       }
/*     */ 
/*     */       
/* 618 */       if (this.phase == RoomPhase.COMBAT) {
/* 619 */         AbstractDungeon.player.renderPlayerBattleUi(sb);
/*     */       }
/*     */       
/* 622 */       for (AbstractPotion i : this.potions) {
/* 623 */         if (!i.isObtained) {
/* 624 */           i.render(sb);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 629 */     for (AbstractRelic r : this.relics) {
/* 630 */       r.render(sb);
/*     */     }
/*     */     
/* 633 */     renderTips(sb);
/*     */   }
/*     */   
/*     */   public void renderAboveTopPanel(SpriteBatch sb) {
/* 637 */     for (AbstractPotion i : this.potions) {
/* 638 */       if (i.isObtained) {
/* 639 */         i.render(sb);
/*     */       }
/*     */     } 
/*     */     
/* 643 */     this.souls.render(sb);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 648 */     if (Settings.isInfo) {
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
/* 661 */       String msg = "[GAME MODE DATA]\n isDaily: " + Settings.isDailyRun + "\n isSpecialSeed: " + Settings.isTrial + "\n isAscension: " + AbstractDungeon.isAscensionMode + "\n\n[CARDGROUPS]\n Deck: " + AbstractDungeon.player.masterDeck.size() + "\n Draw Pile: " + AbstractDungeon.player.drawPile.size() + "\n Discard Pile: " + AbstractDungeon.player.discardPile.size() + "\n Exhaust Pile: " + AbstractDungeon.player.exhaustPile.size() + "\n\n[ACTION MANAGER]\n Phase: " + AbstractDungeon.actionManager.phase.name() + "\n turnEnded: " + AbstractDungeon.actionManager.turnHasEnded + "\n numTurns: " + GameActionManager.turn + "\n\n[Misc]\n Publisher Connection: " + CardCrawlGame.publisherIntegration.isInitialized() + "\n CUR_SCREEN: " + AbstractDungeon.screen.name() + "\n Controller Mode: " + Settings.isControllerMode + "\n isFadingOut: " + AbstractDungeon.isFadingOut + "\n isScreenUp: " + AbstractDungeon.isScreenUp + "\n Particle Count: " + AbstractDungeon.effectList.size();
/*     */       
/* 663 */       FontHelper.renderFontCenteredHeight(sb, FontHelper.tipBodyFont, msg, 30.0F, Settings.HEIGHT * 0.5F, Color.WHITE);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTips(SpriteBatch sb) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnRelicAndObtain(float x, float y, AbstractRelic relic) {
/* 677 */     if (relic.relicId == "Circlet" && AbstractDungeon.player.hasRelic("Circlet")) {
/* 678 */       AbstractRelic circ = AbstractDungeon.player.getRelic("Circlet");
/* 679 */       circ.counter++;
/* 680 */       circ.flash();
/*     */     } else {
/* 682 */       relic.spawn(x, y);
/* 683 */       this.relics.add(relic);
/* 684 */       relic.obtain();
/* 685 */       relic.isObtained = true;
/* 686 */       relic.isAnimating = false;
/* 687 */       relic.isDone = false;
/* 688 */       relic.flash();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void spawnBlightAndObtain(float x, float y, AbstractBlight blight) {
/* 693 */     blight.spawn(x, y);
/* 694 */     blight.obtain();
/* 695 */     blight.isObtained = true;
/* 696 */     blight.isAnimating = false;
/* 697 */     blight.isDone = false;
/* 698 */     blight.flash();
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyEndOfTurnRelics() {
/* 703 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 704 */       r.onPlayerEndTurn();
/*     */     }
/*     */     
/* 707 */     for (AbstractBlight b : AbstractDungeon.player.blights) {
/* 708 */       b.onPlayerEndTurn();
/*     */     }
/*     */   }
/*     */   
/*     */   public void applyEndOfTurnPreCardPowers() {
/* 713 */     for (AbstractPower p : AbstractDungeon.player.powers) {
/* 714 */       p.atEndOfTurnPreEndTurnCards(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addRelicToRewards(AbstractRelic.RelicTier tier) {
/* 719 */     this.rewards.add(new RewardItem(AbstractDungeon.returnRandomRelic(tier)));
/*     */   }
/*     */   
/*     */   public void addSapphireKey(RewardItem item) {
/* 723 */     this.rewards.add(new RewardItem(item, RewardItem.RewardType.SAPPHIRE_KEY));
/*     */   }
/*     */   
/*     */   public void removeOneRelicFromRewards() {
/* 727 */     for (Iterator<RewardItem> i = this.rewards.iterator(); i.hasNext(); ) {
/* 728 */       RewardItem rewardItem = i.next();
/* 729 */       if (rewardItem.type == RewardItem.RewardType.RELIC) {
/* 730 */         i.remove();
/* 731 */         if (i.hasNext() && 
/* 732 */           rewardItem.relicLink == i.next()) {
/* 733 */           i.remove();
/*     */         }
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addNoncampRelicToRewards(AbstractRelic.RelicTier tier) {
/* 742 */     this.rewards.add(new RewardItem(AbstractDungeon.returnRandomNonCampfireRelic(tier)));
/*     */   }
/*     */   
/*     */   public void addRelicToRewards(AbstractRelic relic) {
/* 746 */     this.rewards.add(new RewardItem(relic));
/*     */   }
/*     */   
/*     */   public void addPotionToRewards(AbstractPotion potion) {
/* 750 */     this.rewards.add(new RewardItem(potion));
/*     */   }
/*     */   
/*     */   public void addCardToRewards() {
/* 754 */     RewardItem cardReward = new RewardItem();
/* 755 */     if (cardReward.cards.size() > 0) {
/* 756 */       this.rewards.add(cardReward);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPotionToRewards() {
/* 765 */     int chance = 0;
/* 766 */     if (this instanceof MonsterRoomElite) {
/* 767 */       chance = 40;
/* 768 */       chance += blizzardPotionMod;
/* 769 */     } else if (this instanceof MonsterRoom) {
/* 770 */       if (!AbstractDungeon.getMonsters().haveMonstersEscaped()) {
/* 771 */         chance = 40;
/* 772 */         chance += blizzardPotionMod;
/*     */       } 
/* 774 */     } else if (this instanceof EventRoom) {
/* 775 */       chance = 40;
/* 776 */       chance += blizzardPotionMod;
/*     */     } 
/*     */ 
/*     */     
/* 780 */     if (AbstractDungeon.player.hasRelic("White Beast Statue")) {
/* 781 */       chance = 100;
/*     */     }
/*     */ 
/*     */     
/* 785 */     if (this.rewards.size() >= 4) {
/* 786 */       chance = 0;
/*     */     }
/*     */     
/* 789 */     logger.info("POTION CHANCE: " + chance);
/*     */ 
/*     */     
/* 792 */     if (AbstractDungeon.potionRng.random(0, 99) < chance || Settings.isDebug) {
/* 793 */       CardCrawlGame.metricData.potions_floor_spawned.add(Integer.valueOf(AbstractDungeon.floorNum));
/* 794 */       this.rewards.add(new RewardItem(AbstractDungeon.returnRandomPotion()));
/* 795 */       blizzardPotionMod -= 10;
/*     */     } else {
/* 797 */       blizzardPotionMod += 10;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addGoldToRewards(int gold) {
/* 802 */     for (RewardItem i : this.rewards) {
/* 803 */       if (i.type == RewardItem.RewardType.GOLD) {
/* 804 */         i.incrementGold(gold);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 809 */     this.rewards.add(new RewardItem(gold));
/*     */   }
/*     */   
/*     */   public void addStolenGoldToRewards(int gold) {
/* 813 */     for (RewardItem i : this.rewards) {
/* 814 */       if (i.type == RewardItem.RewardType.STOLEN_GOLD) {
/* 815 */         i.incrementGold(gold);
/*     */         return;
/*     */       } 
/*     */     } 
/* 819 */     this.rewards.add(new RewardItem(gold, true));
/*     */   }
/*     */   
/*     */   public boolean isBattleEnding() {
/* 823 */     if (this.isBattleOver) {
/* 824 */       return true;
/*     */     }
/* 826 */     if (this.monsters != null) {
/* 827 */       return this.monsters.areMonstersBasicallyDead();
/*     */     }
/* 829 */     return false;
/*     */   }
/*     */   
/*     */   public void renderEventTexts(SpriteBatch sb) {
/* 833 */     if (this.event != null) {
/* 834 */       this.event.renderText(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearEvent() {
/* 839 */     if (this.event != null) {
/* 840 */       this.event.imageEventText.clear();
/* 841 */       this.event.roomEventText.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void eventControllerInput() {
/* 846 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 850 */     if ((AbstractDungeon.getCurrRoom()).event != null && (AbstractDungeon.getCurrRoom()).phase != RoomPhase.COMBAT && !AbstractDungeon.topPanel.selectPotionMode && AbstractDungeon.topPanel.potionUi.isHidden && !AbstractDungeon.topPanel.potionUi.targetMode && !AbstractDungeon.player.viewingRelics)
/*     */     {
/*     */       
/* 853 */       if (!RoomEventDialog.optionList.isEmpty()) {
/* 854 */         boolean anyHovered = false;
/* 855 */         int index = 0;
/* 856 */         for (LargeDialogOptionButton o : RoomEventDialog.optionList) {
/* 857 */           if (o.hb.hovered) {
/* 858 */             anyHovered = true;
/*     */             break;
/*     */           } 
/* 861 */           index++;
/*     */         } 
/*     */         
/* 864 */         if (!anyHovered) {
/* 865 */           Gdx.input.setCursorPosition(
/* 866 */               (int)((LargeDialogOptionButton)RoomEventDialog.optionList.get(0)).hb.cX, Settings.HEIGHT - 
/* 867 */               (int)((LargeDialogOptionButton)RoomEventDialog.optionList.get(0)).hb.cY);
/*     */         }
/* 869 */         else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 870 */           index++;
/* 871 */           if (index > RoomEventDialog.optionList.size() - 1) {
/* 872 */             index = 0;
/*     */           }
/* 874 */           Gdx.input.setCursorPosition(
/* 875 */               (int)((LargeDialogOptionButton)RoomEventDialog.optionList.get(index)).hb.cX, Settings.HEIGHT - 
/* 876 */               (int)((LargeDialogOptionButton)RoomEventDialog.optionList.get(index)).hb.cY);
/* 877 */         } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 878 */           index--;
/* 879 */           if (index < 0) {
/* 880 */             index = RoomEventDialog.optionList.size() - 1;
/*     */           }
/* 882 */           Gdx.input.setCursorPosition(
/* 883 */               (int)((LargeDialogOptionButton)RoomEventDialog.optionList.get(index)).hb.cX, Settings.HEIGHT - 
/* 884 */               (int)((LargeDialogOptionButton)RoomEventDialog.optionList.get(index)).hb.cY);
/*     */         }
/*     */       
/* 887 */       } else if (!this.event.imageEventText.optionList.isEmpty()) {
/* 888 */         boolean anyHovered = false;
/* 889 */         int index = 0;
/* 890 */         for (LargeDialogOptionButton o : this.event.imageEventText.optionList) {
/* 891 */           if (o.hb.hovered) {
/* 892 */             anyHovered = true;
/*     */             break;
/*     */           } 
/* 895 */           index++;
/*     */         } 
/*     */         
/* 898 */         if (!anyHovered) {
/* 899 */           Gdx.input.setCursorPosition(
/* 900 */               (int)((LargeDialogOptionButton)this.event.imageEventText.optionList.get(0)).hb.cX, Settings.HEIGHT - 
/* 901 */               (int)((LargeDialogOptionButton)this.event.imageEventText.optionList.get(0)).hb.cY);
/*     */         }
/* 903 */         else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 904 */           index++;
/* 905 */           if (index > this.event.imageEventText.optionList.size() - 1) {
/* 906 */             index = 0;
/*     */           }
/* 908 */           Gdx.input.setCursorPosition(
/* 909 */               (int)((LargeDialogOptionButton)this.event.imageEventText.optionList.get(index)).hb.cX, Settings.HEIGHT - 
/* 910 */               (int)((LargeDialogOptionButton)this.event.imageEventText.optionList.get(index)).hb.cY);
/* 911 */         } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 912 */           index--;
/* 913 */           if (index < 0) {
/* 914 */             index = this.event.imageEventText.optionList.size() - 1;
/*     */           }
/* 916 */           Gdx.input.setCursorPosition(
/* 917 */               (int)((LargeDialogOptionButton)this.event.imageEventText.optionList.get(index)).hb.cX, Settings.HEIGHT - 
/* 918 */               (int)((LargeDialogOptionButton)this.event.imageEventText.optionList.get(index)).hb.cY);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCardReward(RewardItem rewardItem) {
/* 927 */     if (!rewardItem.cards.isEmpty()) {
/* 928 */       this.rewards.add(rewardItem);
/*     */     }
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 933 */     if (this.event != null) {
/* 934 */       this.event.dispose();
/*     */     }
/* 936 */     if (this.monsters != null)
/* 937 */       for (AbstractMonster m : this.monsters.monsters)
/* 938 */         m.dispose();  
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rooms\AbstractRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */