/*     */ package com.megacrit.cardcrawl.actions;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ShowMoveNameAction;
/*     */ import com.megacrit.cardcrawl.actions.defect.TriggerEndOfTurnOrbsAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardQueueItem;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.daily.mods.Careless;
/*     */ import com.megacrit.cardcrawl.daily.mods.ControlledChaos;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.monsters.MonsterQueueItem;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.UnceasingTop;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.ThoughtBubble;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
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
/*     */ public class GameActionManager
/*     */ {
/*  49 */   private static final Logger logger = LogManager.getLogger(GameActionManager.class.getName());
/*     */   
/*  51 */   private ArrayList<AbstractGameAction> nextCombatActions = new ArrayList<>();
/*  52 */   public ArrayList<AbstractGameAction> actions = new ArrayList<>();
/*  53 */   public ArrayList<AbstractGameAction> preTurnActions = new ArrayList<>();
/*  54 */   public ArrayList<CardQueueItem> cardQueue = new ArrayList<>();
/*  55 */   public ArrayList<MonsterQueueItem> monsterQueue = new ArrayList<>();
/*  56 */   public ArrayList<AbstractCard> cardsPlayedThisTurn = new ArrayList<>();
/*  57 */   public ArrayList<AbstractCard> cardsPlayedThisCombat = new ArrayList<>();
/*  58 */   public ArrayList<AbstractOrb> orbsChanneledThisCombat = new ArrayList<>();
/*  59 */   public ArrayList<AbstractOrb> orbsChanneledThisTurn = new ArrayList<>();
/*  60 */   public HashMap<String, Integer> uniqueStancesThisCombat = new HashMap<>();
/*  61 */   public int mantraGained = 0;
/*     */   public AbstractGameAction currentAction;
/*  63 */   public AbstractCard lastCard = null; public AbstractGameAction previousAction; public AbstractGameAction turnStartCurrentAction;
/*  64 */   public Phase phase = Phase.WAITING_ON_USER; public boolean hasControl = true; public boolean turnHasEnded = false;
/*     */   public boolean usingCard = false;
/*     */   public boolean monsterAttacksQueued = true;
/*  67 */   public static int totalDiscardedThisTurn = 0, damageReceivedThisTurn = 0, damageReceivedThisCombat = 0;
/*  68 */   public static int hpLossThisCombat = 0; public static int playerHpLastTurn; public static int energyGainedThisCombat;
/*  69 */   public static int turn = 0;
/*     */   
/*     */   public enum Phase {
/*  72 */     WAITING_ON_USER, EXECUTING_ACTIONS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToNextCombat(AbstractGameAction action) {
/*  81 */     this.nextCombatActions.add(action);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useNextCombatActions() {
/*  88 */     for (AbstractGameAction a : this.nextCombatActions) {
/*  89 */       addToBottom(a);
/*     */     }
/*  91 */     this.nextCombatActions.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToBottom(AbstractGameAction action) {
/* 101 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 102 */       this.actions.add(action);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addCardQueueItem(CardQueueItem c, boolean inFrontOfQueue) {
/* 107 */     if (inFrontOfQueue) {
/* 108 */       if (!AbstractDungeon.actionManager.cardQueue.isEmpty()) {
/*     */         
/* 110 */         AbstractDungeon.actionManager.cardQueue.add(1, c);
/*     */       } else {
/* 112 */         AbstractDungeon.actionManager.cardQueue.add(c);
/*     */       } 
/*     */     } else {
/* 115 */       AbstractDungeon.actionManager.cardQueue.add(c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCardQueueItem(CardQueueItem c) {
/* 120 */     addCardQueueItem(c, false);
/*     */   }
/*     */   
/*     */   public void removeFromQueue(AbstractCard c) {
/* 124 */     int index = -1;
/* 125 */     for (int i = 0; i < this.cardQueue.size(); i++) {
/* 126 */       if (((CardQueueItem)this.cardQueue.get(i)).card != null && ((CardQueueItem)this.cardQueue.get(i)).card.equals(c)) {
/* 127 */         index = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 132 */     if (index != -1) {
/* 133 */       this.cardQueue.remove(index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPostCombatActions() {
/* 141 */     for (Iterator<AbstractGameAction> i = this.actions.iterator(); i.hasNext(); ) {
/* 142 */       AbstractGameAction e = i.next();
/* 143 */       if (e instanceof com.megacrit.cardcrawl.actions.common.HealAction || e instanceof com.megacrit.cardcrawl.actions.common.GainBlockAction || e instanceof UseCardAction || e.actionType == AbstractGameAction.ActionType.DAMAGE) {
/*     */         continue;
/*     */       }
/* 146 */       i.remove();
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
/*     */   public void addToTop(AbstractGameAction action) {
/* 158 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 159 */       this.actions.add(0, action);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addToTurnStart(AbstractGameAction action) {
/* 164 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 165 */       this.preTurnActions.add(0, action);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 173 */     switch (this.phase) {
/*     */       case WAITING_ON_USER:
/* 175 */         getNextAction();
/*     */         return;
/*     */       case EXECUTING_ACTIONS:
/* 178 */         if (this.currentAction != null && !this.currentAction.isDone) {
/* 179 */           this.currentAction.update();
/*     */         } else {
/* 181 */           this.previousAction = this.currentAction;
/* 182 */           this.currentAction = null;
/* 183 */           getNextAction();
/*     */ 
/*     */           
/* 186 */           if (this.currentAction == null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !this.usingCard) {
/*     */             
/* 188 */             this.phase = Phase.WAITING_ON_USER;
/* 189 */             AbstractDungeon.player.hand.refreshHandLayout();
/* 190 */             this.hasControl = false;
/*     */           } 
/* 192 */           this.usingCard = false;
/*     */         } 
/*     */         return;
/*     */     } 
/* 196 */     logger.info("This should never be called");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endTurn() {
/* 202 */     AbstractDungeon.player.resetControllerValues();
/* 203 */     this.turnHasEnded = true;
/* 204 */     playerHpLastTurn = AbstractDungeon.player.currentHealth;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void getNextAction() {
/* 210 */     if (!this.actions.isEmpty()) {
/* 211 */       this.currentAction = this.actions.remove(0);
/* 212 */       this.phase = Phase.EXECUTING_ACTIONS;
/* 213 */       this.hasControl = true;
/*     */     
/*     */     }
/* 216 */     else if (!this.preTurnActions.isEmpty()) {
/* 217 */       this.currentAction = this.preTurnActions.remove(0);
/* 218 */       this.phase = Phase.EXECUTING_ACTIONS;
/* 219 */       this.hasControl = true;
/*     */     }
/* 221 */     else if (!this.cardQueue.isEmpty()) {
/* 222 */       this.usingCard = true;
/* 223 */       AbstractCard c = ((CardQueueItem)this.cardQueue.get(0)).card;
/*     */ 
/*     */       
/* 226 */       if (c == null) {
/* 227 */         callEndOfTurnActions();
/* 228 */       } else if (c.equals(this.lastCard)) {
/* 229 */         logger.info("Last card! " + c.name);
/* 230 */         this.lastCard = null;
/*     */       } 
/*     */ 
/*     */       
/* 234 */       if (this.cardQueue.size() == 1 && ((CardQueueItem)this.cardQueue.get(0)).isEndTurnAutoPlay) {
/* 235 */         AbstractRelic top = AbstractDungeon.player.getRelic("Unceasing Top");
/* 236 */         if (top != null) {
/* 237 */           ((UnceasingTop)top).disableUntilTurnEnds();
/*     */         }
/*     */       } 
/*     */       
/* 241 */       boolean canPlayCard = false;
/*     */ 
/*     */       
/* 244 */       if (c != null) {
/* 245 */         c.isInAutoplay = ((CardQueueItem)this.cardQueue.get(0)).autoplayCard;
/*     */       }
/*     */ 
/*     */       
/* 249 */       if (c != null && ((CardQueueItem)this.cardQueue.get(0)).randomTarget) {
/* 250 */         ((CardQueueItem)this.cardQueue.get(0)).monster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 257 */       if (((CardQueueItem)this.cardQueue.get(0)).card != null && (c.canUse(AbstractDungeon.player, ((CardQueueItem)this.cardQueue.get(0)).monster) || 
/* 258 */         ((CardQueueItem)this.cardQueue.get(0)).card.dontTriggerOnUseCard)) {
/* 259 */         canPlayCard = true;
/*     */ 
/*     */         
/* 262 */         if (c.freeToPlay()) {
/* 263 */           c.freeToPlayOnce = true;
/*     */         }
/*     */         
/* 266 */         ((CardQueueItem)this.cardQueue.get(0)).card.energyOnUse = ((CardQueueItem)this.cardQueue.get(0)).energyOnUse;
/* 267 */         if (c.isInAutoplay) {
/*     */           
/* 269 */           ((CardQueueItem)this.cardQueue.get(0)).card.ignoreEnergyOnUse = true;
/*     */         } else {
/* 271 */           ((CardQueueItem)this.cardQueue.get(0)).card.ignoreEnergyOnUse = ((CardQueueItem)this.cardQueue.get(0)).ignoreEnergyTotal;
/*     */         } 
/*     */         
/* 274 */         if (!((CardQueueItem)this.cardQueue.get(0)).card.dontTriggerOnUseCard) {
/* 275 */           for (AbstractPower p : AbstractDungeon.player.powers) {
/* 276 */             p.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card, ((CardQueueItem)this.cardQueue.get(0)).monster);
/*     */           }
/*     */           
/* 279 */           for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 280 */             for (AbstractPower p : m.powers) {
/* 281 */               p.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card, ((CardQueueItem)this.cardQueue.get(0)).monster);
/*     */             }
/*     */           } 
/*     */           
/* 285 */           for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 286 */             r.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card, ((CardQueueItem)this.cardQueue.get(0)).monster);
/*     */           }
/*     */           
/* 289 */           AbstractDungeon.player.stance.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card);
/*     */           
/* 291 */           for (AbstractBlight b : AbstractDungeon.player.blights) {
/* 292 */             b.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card, ((CardQueueItem)this.cardQueue.get(0)).monster);
/*     */           }
/*     */           
/* 295 */           for (AbstractCard card : AbstractDungeon.player.hand.group) {
/* 296 */             card.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card, ((CardQueueItem)this.cardQueue.get(0)).monster);
/*     */           }
/* 298 */           for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
/* 299 */             card.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card, ((CardQueueItem)this.cardQueue.get(0)).monster);
/*     */           }
/*     */           
/* 302 */           for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
/* 303 */             card.onPlayCard(((CardQueueItem)this.cardQueue.get(0)).card, ((CardQueueItem)this.cardQueue.get(0)).monster);
/*     */           }
/*     */           
/* 306 */           AbstractDungeon.player.cardsPlayedThisTurn++;
/* 307 */           this.cardsPlayedThisTurn.add(((CardQueueItem)this.cardQueue.get(0)).card);
/* 308 */           this.cardsPlayedThisCombat.add(((CardQueueItem)this.cardQueue.get(0)).card);
/*     */         } 
/*     */ 
/*     */         
/* 312 */         if (this.cardsPlayedThisTurn.size() == 25) {
/* 313 */           UnlockTracker.unlockAchievement("INFINITY");
/*     */         }
/*     */ 
/*     */         
/* 317 */         if (this.cardsPlayedThisTurn.size() >= 20 && !CardCrawlGame.combo) {
/* 318 */           CardCrawlGame.combo = true;
/*     */         }
/*     */ 
/*     */         
/* 322 */         if (((CardQueueItem)this.cardQueue.get(0)).card instanceof com.megacrit.cardcrawl.cards.tempCards.Shiv) {
/* 323 */           int shivCount = 0;
/* 324 */           for (AbstractCard i : this.cardsPlayedThisTurn) {
/*     */             
/* 326 */             shivCount++;
/* 327 */             if (i instanceof com.megacrit.cardcrawl.cards.tempCards.Shiv && shivCount == 10) {
/* 328 */               UnlockTracker.unlockAchievement("NINJA");
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 336 */         if (((CardQueueItem)this.cardQueue.get(0)).card != null) {
/* 337 */           if (((CardQueueItem)this.cardQueue.get(0)).card.target == AbstractCard.CardTarget.ENEMY && (((CardQueueItem)this.cardQueue.get(0)).monster == null || ((CardQueueItem)this.cardQueue
/* 338 */             .get(0)).monster.isDeadOrEscaped())) {
/*     */             
/* 340 */             for (Iterator<AbstractCard> i = AbstractDungeon.player.limbo.group.iterator(); i.hasNext(); ) {
/* 341 */               AbstractCard e = i.next();
/* 342 */               if (e == ((CardQueueItem)this.cardQueue.get(0)).card) {
/* 343 */                 ((CardQueueItem)this.cardQueue.get(0)).card.fadingOut = true;
/* 344 */                 AbstractDungeon.effectList.add(new ExhaustCardEffect(((CardQueueItem)this.cardQueue.get(0)).card));
/* 345 */                 i.remove();
/*     */               } 
/*     */             } 
/*     */             
/* 349 */             if (((CardQueueItem)this.cardQueue.get(0)).monster == null) {
/* 350 */               ((CardQueueItem)this.cardQueue.get(0)).card.drawScale = ((CardQueueItem)this.cardQueue.get(0)).card.targetDrawScale;
/* 351 */               ((CardQueueItem)this.cardQueue.get(0)).card.angle = ((CardQueueItem)this.cardQueue.get(0)).card.targetAngle;
/* 352 */               ((CardQueueItem)this.cardQueue.get(0)).card.current_x = ((CardQueueItem)this.cardQueue.get(0)).card.target_x;
/* 353 */               ((CardQueueItem)this.cardQueue.get(0)).card.current_y = ((CardQueueItem)this.cardQueue.get(0)).card.target_y;
/* 354 */               AbstractDungeon.effectList.add(new ExhaustCardEffect(((CardQueueItem)this.cardQueue.get(0)).card));
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 359 */             AbstractDungeon.player.useCard(((CardQueueItem)this.cardQueue
/* 360 */                 .get(0)).card, ((CardQueueItem)this.cardQueue
/* 361 */                 .get(0)).monster, ((CardQueueItem)this.cardQueue
/* 362 */                 .get(0)).energyOnUse);
/*     */           } 
/*     */         }
/*     */       } else {
/* 366 */         for (Iterator<AbstractCard> i = AbstractDungeon.player.limbo.group.iterator(); i.hasNext(); ) {
/* 367 */           AbstractCard e = i.next();
/* 368 */           if (e == c) {
/* 369 */             c.fadingOut = true;
/* 370 */             AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
/* 371 */             i.remove();
/*     */           } 
/*     */         } 
/*     */         
/* 375 */         if (c != null) {
/* 376 */           AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, c.cantUseMessage, true));
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 386 */       this.cardQueue.remove(0);
/*     */ 
/*     */       
/* 389 */       if (!canPlayCard && c != null && c.isInAutoplay) {
/* 390 */         c.dontTriggerOnUseCard = true;
/* 391 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new UseCardAction(c));
/*     */       }
/*     */     
/* 394 */     } else if (!this.monsterAttacksQueued) {
/* 395 */       this.monsterAttacksQueued = true;
/* 396 */       if (!(AbstractDungeon.getCurrRoom()).skipMonsterTurn) {
/* 397 */         (AbstractDungeon.getCurrRoom()).monsters.queueMonsters();
/*     */       }
/* 399 */     } else if (!this.monsterQueue.isEmpty()) {
/* 400 */       AbstractMonster m = ((MonsterQueueItem)this.monsterQueue.get(0)).monster;
/* 401 */       if (!m.isDeadOrEscaped() || m.halfDead) {
/* 402 */         if (m.intent != AbstractMonster.Intent.NONE) {
/* 403 */           addToBottom((AbstractGameAction)new ShowMoveNameAction(m));
/* 404 */           addToBottom(new IntentFlashAction(m));
/*     */         } 
/*     */ 
/*     */         
/* 408 */         if (!((Boolean)TipTracker.tips.get("INTENT_TIP")).booleanValue() && AbstractDungeon.player.currentBlock == 0 && (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND))
/*     */         {
/*     */           
/* 411 */           if (AbstractDungeon.floorNum <= 5) {
/* 412 */             TipTracker.blockCounter++;
/*     */           } else {
/* 414 */             TipTracker.neverShowAgain("INTENT_TIP");
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 419 */         m.takeTurn();
/* 420 */         m.applyTurnPowers();
/*     */       } 
/*     */       
/* 423 */       this.monsterQueue.remove(0);
/* 424 */       if (this.monsterQueue.isEmpty()) {
/* 425 */         addToBottom((AbstractGameAction)new WaitAction(1.5F));
/*     */       
/*     */       }
/*     */     }
/* 429 */     else if (this.turnHasEnded && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 430 */       if (!(AbstractDungeon.getCurrRoom()).skipMonsterTurn) {
/* 431 */         (AbstractDungeon.getCurrRoom()).monsters.applyEndOfTurnPowers();
/*     */       }
/* 433 */       AbstractDungeon.player.cardsPlayedThisTurn = 0;
/* 434 */       this.orbsChanneledThisTurn.clear();
/*     */ 
/*     */       
/* 437 */       if (ModHelper.isModEnabled("Careless")) {
/* 438 */         Careless.modAction();
/*     */       }
/* 440 */       if (ModHelper.isModEnabled("ControlledChaos")) {
/* 441 */         ControlledChaos.modAction();
/* 442 */         AbstractDungeon.player.hand.applyPowers();
/*     */       } 
/*     */ 
/*     */       
/* 446 */       AbstractDungeon.player.applyStartOfTurnRelics();
/* 447 */       AbstractDungeon.player.applyStartOfTurnPreDrawCards();
/* 448 */       AbstractDungeon.player.applyStartOfTurnCards();
/* 449 */       AbstractDungeon.player.applyStartOfTurnPowers();
/* 450 */       AbstractDungeon.player.applyStartOfTurnOrbs();
/* 451 */       turn++;
/* 452 */       (AbstractDungeon.getCurrRoom()).skipMonsterTurn = false;
/* 453 */       this.turnHasEnded = false;
/* 454 */       totalDiscardedThisTurn = 0;
/* 455 */       this.cardsPlayedThisTurn.clear();
/* 456 */       damageReceivedThisTurn = 0;
/*     */       
/* 458 */       if (!AbstractDungeon.player.hasPower("Barricade") && !AbstractDungeon.player.hasPower("Blur"))
/*     */       {
/* 460 */         if (!AbstractDungeon.player.hasRelic("Calipers")) {
/* 461 */           AbstractDungeon.player.loseBlock();
/*     */         } else {
/* 463 */           AbstractDungeon.player.loseBlock(15);
/*     */         } 
/*     */       }
/*     */       
/* 467 */       if (!(AbstractDungeon.getCurrRoom()).isBattleOver) {
/* 468 */         addToBottom((AbstractGameAction)new DrawCardAction(null, AbstractDungeon.player.gameHandSize, true));
/* 469 */         AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
/* 470 */         AbstractDungeon.player.applyStartOfTurnPostDrawPowers();
/* 471 */         addToBottom((AbstractGameAction)new EnableEndTurnButtonAction());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void callEndOfTurnActions() {
/* 479 */     AbstractDungeon.getCurrRoom().applyEndOfTurnRelics();
/* 480 */     AbstractDungeon.getCurrRoom().applyEndOfTurnPreCardPowers();
/* 481 */     addToBottom((AbstractGameAction)new TriggerEndOfTurnOrbsAction());
/*     */     
/* 483 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 484 */       c.triggerOnEndOfTurnForPlayingCard();
/*     */     }
/*     */     
/* 487 */     AbstractDungeon.player.stance.onEndOfTurn();
/*     */   }
/*     */   
/*     */   public void callEndTurnEarlySequence() {
/* 491 */     for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
/*     */       
/* 493 */       if (i.autoplayCard) {
/* 494 */         i.card.dontTriggerOnUseCard = true;
/* 495 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new UseCardAction(i.card));
/*     */       } 
/*     */     } 
/* 498 */     AbstractDungeon.actionManager.cardQueue.clear();
/* 499 */     for (AbstractCard c : AbstractDungeon.player.limbo.group) {
/* 500 */       AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
/*     */     }
/* 502 */     AbstractDungeon.player.limbo.group.clear();
/* 503 */     AbstractDungeon.player.releaseCard();
/* 504 */     AbstractDungeon.overlayMenu.endTurnButton.disable(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanCardQueue() {
/* 511 */     for (Iterator<CardQueueItem> iterator = this.cardQueue.iterator(); iterator.hasNext(); ) {
/* 512 */       CardQueueItem e = iterator.next();
/* 513 */       if (AbstractDungeon.player.hand.contains(e.card)) {
/* 514 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */     
/* 518 */     for (Iterator<AbstractCard> i = AbstractDungeon.player.limbo.group.iterator(); i.hasNext(); ) {
/* 519 */       AbstractCard e = i.next();
/* 520 */       e.fadingOut = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 525 */     return this.actions.isEmpty();
/*     */   }
/*     */   
/*     */   public void clearNextRoomCombatActions() {
/* 529 */     this.nextCombatActions.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 534 */     this.actions.clear();
/* 535 */     this.preTurnActions.clear();
/* 536 */     this.currentAction = null;
/* 537 */     this.previousAction = null;
/* 538 */     this.turnStartCurrentAction = null;
/*     */ 
/*     */     
/* 541 */     this.cardsPlayedThisCombat.clear();
/* 542 */     this.cardsPlayedThisTurn.clear();
/* 543 */     this.orbsChanneledThisCombat.clear();
/* 544 */     this.orbsChanneledThisTurn.clear();
/* 545 */     this.uniqueStancesThisCombat.clear();
/* 546 */     this.cardQueue.clear();
/*     */     
/* 548 */     energyGainedThisCombat = 0;
/* 549 */     this.mantraGained = 0;
/* 550 */     damageReceivedThisCombat = 0;
/* 551 */     damageReceivedThisTurn = 0;
/* 552 */     hpLossThisCombat = 0;
/* 553 */     this.turnHasEnded = false;
/* 554 */     turn = 1;
/* 555 */     this.phase = Phase.WAITING_ON_USER;
/* 556 */     totalDiscardedThisTurn = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void incrementDiscard(boolean endOfTurn) {
/* 563 */     totalDiscardedThisTurn++;
/*     */     
/* 565 */     if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
/* 566 */       AbstractDungeon.player.updateCardsOnDiscard();
/* 567 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 568 */         r.onManualDiscard();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateEnergyGain(int energyGain) {
/* 574 */     energyGainedThisCombat += energyGain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void queueExtraCard(AbstractCard card, AbstractMonster m) {
/* 584 */     AbstractCard tmp = card.makeSameInstanceOf();
/* 585 */     AbstractDungeon.player.limbo.addToBottom(tmp);
/* 586 */     tmp.current_x = card.current_x;
/* 587 */     tmp.current_y = card.current_y;
/*     */ 
/*     */     
/* 590 */     int extraCount = 0;
/* 591 */     for (CardQueueItem c : AbstractDungeon.actionManager.cardQueue) {
/* 592 */       if (c.card.uuid.equals(card.uuid)) {
/* 593 */         extraCount++;
/*     */       }
/*     */     } 
/*     */     
/* 597 */     tmp.target_y = Settings.HEIGHT / 2.0F;
/*     */     
/* 599 */     switch (extraCount) {
/*     */       case 0:
/* 601 */         tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.xScale;
/*     */         break;
/*     */       case 1:
/* 604 */         tmp.target_x = Settings.WIDTH / 2.0F + 300.0F * Settings.xScale;
/*     */         break;
/*     */       case 2:
/* 607 */         tmp.target_x = Settings.WIDTH / 2.0F - 600.0F * Settings.xScale;
/*     */         break;
/*     */       case 3:
/* 610 */         tmp.target_x = Settings.WIDTH / 2.0F + 600.0F * Settings.xScale;
/*     */         break;
/*     */       
/*     */       default:
/* 614 */         tmp.target_x = MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F);
/* 615 */         tmp.target_y = MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F);
/*     */         break;
/*     */     } 
/*     */     
/* 619 */     if (m != null) {
/* 620 */       tmp.calculateCardDamage(m);
/*     */     }
/*     */     
/* 623 */     tmp.purgeOnUse = true;
/* 624 */     AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\GameActionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */