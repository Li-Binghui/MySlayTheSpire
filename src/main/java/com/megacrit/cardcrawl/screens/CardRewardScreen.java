/*      */ package com.megacrit.cardcrawl.screens;
/*      */ 
/*      */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.mainMenu.HorizontalScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import de.robojumper.ststwitch.TwitchPanel;
import de.robojumper.ststwitch.TwitchVoteListener;
import de.robojumper.ststwitch.TwitchVoteOption;
import de.robojumper.ststwitch.TwitchVoter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CardRewardScreen
/*      */   implements ScrollBarListener
/*      */ {
/*   49 */   private static final Logger logger = LogManager.getLogger(CardRewardScreen.class.getName());
/*   50 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardRewardScreen");
/*   51 */   public static final String[] TEXT = uiStrings.TEXT;
/*      */   
/*   53 */   private static final float PAD_X = 40.0F * Settings.xScale;
/*   54 */   private static final float CARD_TARGET_Y = Settings.HEIGHT * 0.45F;
/*      */   public ArrayList<AbstractCard> rewardGroup;
/*   56 */   public AbstractCard discoveryCard = null;
/*      */   public boolean hasTakenAll = false;
/*   58 */   public RewardItem rItem = null; public boolean cardOnly = false;
/*      */   private boolean draft = false, discovery = false, chooseOne = false;
/*      */   private boolean skippable = false;
/*   61 */   private String header = "";
/*   62 */   private SkipCardButton skipButton = new SkipCardButton();
/*   63 */   private SingingBowlButton bowlButton = new SingingBowlButton();
/*   64 */   private PeekButton peekButton = new PeekButton();
/*   65 */   private final int SKIP_BUTTON_IDX = 0;
/*   66 */   private final int BOWL_BUTTON_IDX = 1;
/*   67 */   private int draftCount = 0;
/*      */   
/*      */   private static final int MAX_CARDS_ON_SCREEN = 4;
/*      */   
/*      */   private static final int MAX_CARDS_BEFORE_SCROLL = 5;
/*      */   
/*   73 */   private static final float START_X = Settings.WIDTH - 300.0F * Settings.xScale;
/*      */   private boolean grabbedScreen = false;
/*   75 */   private float grabStartX = 0.0F; private float scrollX;
/*   76 */   private float targetX = this.scrollX = START_X;
/*   77 */   private float scrollLowerBound = Settings.WIDTH - 300.0F * Settings.xScale;
/*   78 */   private float scrollUpperBound = 2400.0F * Settings.scale;
/*      */   
/*      */   private HorizontalScrollBar scrollBar;
/*      */   
/*   82 */   public ConfirmButton confirmButton = new ConfirmButton();
/*   83 */   private AbstractCard touchCard = null;
/*      */   
/*      */   @Deprecated
/*      */   private boolean codex = false;
/*      */   @Deprecated
/*   88 */   public AbstractCard codexCard = null;
/*      */ 
/*      */   
/*      */   static {
/*   92 */     TwitchVoter.registerListener(new TwitchVoteListener() {
/*      */           public void onTwitchAvailable() {
/*   94 */             AbstractDungeon.cardRewardScreen.updateVote();
/*      */           }
/*      */           
/*      */           public void onTwitchUnavailable() {
/*   98 */             AbstractDungeon.cardRewardScreen.updateVote();
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private boolean isVoting = false;
/*      */   private boolean mayVote = false;
/*      */   
/*      */   public CardRewardScreen() {
/*  107 */     this.scrollBar = new HorizontalScrollBar(this, Settings.WIDTH / 2.0F, 50.0F * Settings.scale + HorizontalScrollBar.TRACK_H / 2.0F, Settings.WIDTH - 256.0F * Settings.scale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/*  115 */     if (Settings.isTouchScreen) {
/*  116 */       this.confirmButton.update();
/*  117 */       if (this.confirmButton.hb.clicked && this.touchCard != null) {
/*  118 */         this.confirmButton.hb.clicked = false;
/*  119 */         this.confirmButton.hb.clickStarted = false;
/*  120 */         this.confirmButton.isDisabled = true;
/*  121 */         this.confirmButton.hide();
/*      */         
/*  123 */         this.touchCard.hb.clicked = false;
/*  124 */         this.skipButton.hide();
/*  125 */         this.bowlButton.hide();
/*      */         
/*  127 */         if (this.chooseOne) {
/*  128 */           this.touchCard.onChoseThisOption();
/*  129 */         } else if (this.codex) {
/*  130 */           this.codexCard = this.touchCard;
/*  131 */         } else if (this.discovery) {
/*  132 */           this.discoveryCard = this.touchCard;
/*      */         } else {
/*  134 */           acquireCard(this.touchCard);
/*      */         } 
/*      */         
/*  137 */         takeReward();
/*      */         
/*  139 */         if (!this.draft || this.draftCount >= 15) {
/*  140 */           AbstractDungeon.closeCurrentScreen();
/*  141 */           this.draftCount = 0;
/*      */         } else {
/*  143 */           draftOpen();
/*      */         } 
/*  145 */         this.touchCard = null;
/*      */       } 
/*      */     } 
/*      */     
/*  149 */     this.peekButton.update();
/*  150 */     if (!PeekButton.isPeeking) {
/*  151 */       this.skipButton.update();
/*  152 */       this.bowlButton.update();
/*      */     } 
/*  154 */     updateControllerInput();
/*      */     
/*  156 */     if (!PeekButton.isPeeking) {
/*  157 */       if (!this.scrollBar.update()) {
/*  158 */         updateScrolling();
/*      */       }
/*  160 */       cardSelectUpdate();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateControllerInput() {
/*  165 */     if (!Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.player.viewingRelics) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  170 */     int index = 0;
/*  171 */     boolean anyHovered = false;
/*  172 */     for (AbstractCard c : this.rewardGroup) {
/*  173 */       if (c.hb.hovered) {
/*  174 */         anyHovered = true;
/*      */         break;
/*      */       } 
/*  177 */       index++;
/*      */     } 
/*      */     
/*  180 */     if (!anyHovered) {
/*  181 */       index = 0;
/*  182 */       Gdx.input.setCursorPosition(
/*  183 */           (int)((AbstractCard)this.rewardGroup.get(index)).hb.cX, Settings.HEIGHT - 
/*  184 */           (int)((AbstractCard)this.rewardGroup.get(index)).hb.cY);
/*      */     }
/*  186 */     else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  187 */       index--;
/*  188 */       if (index < 0) {
/*  189 */         index = this.rewardGroup.size() - 1;
/*      */       }
/*      */       
/*  192 */       Gdx.input.setCursorPosition(
/*  193 */           (int)((AbstractCard)this.rewardGroup.get(index)).hb.cX, Settings.HEIGHT - 
/*  194 */           (int)((AbstractCard)this.rewardGroup.get(index)).hb.cY);
/*  195 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  196 */       index++;
/*  197 */       if (index > this.rewardGroup.size() - 1) {
/*  198 */         index = 0;
/*      */       }
/*      */       
/*  201 */       Gdx.input.setCursorPosition(
/*  202 */           (int)((AbstractCard)this.rewardGroup.get(index)).hb.cX, Settings.HEIGHT - 
/*  203 */           (int)((AbstractCard)this.rewardGroup.get(index)).hb.cY);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateScrolling() {
/*  209 */     int x = InputHelper.mX;
/*      */     
/*  211 */     if (!this.grabbedScreen) {
/*  212 */       if (InputHelper.scrolledDown) {
/*  213 */         this.targetX += Settings.SCROLL_SPEED;
/*  214 */       } else if (InputHelper.scrolledUp) {
/*  215 */         this.targetX -= Settings.SCROLL_SPEED;
/*      */       } 
/*      */       
/*  218 */       if (InputHelper.justClickedLeft) {
/*  219 */         this.grabbedScreen = true;
/*  220 */         this.grabStartX = -x - this.targetX;
/*      */       }
/*      */     
/*  223 */     } else if (InputHelper.isMouseDown) {
/*  224 */       this.targetX = -x - this.grabStartX;
/*      */     } else {
/*  226 */       this.grabbedScreen = false;
/*      */     } 
/*      */ 
/*      */     
/*  230 */     this.scrollX = MathHelper.scrollSnapLerpSpeed(this.scrollX, this.targetX);
/*  231 */     resetScrolling();
/*  232 */     updateBarPosition();
/*      */   }
/*      */   
/*      */   private void resetScrolling() {
/*  236 */     if (this.targetX < this.scrollLowerBound) {
/*  237 */       this.targetX = MathHelper.scrollSnapLerpSpeed(this.targetX, this.scrollLowerBound);
/*  238 */     } else if (this.targetX > this.scrollUpperBound) {
/*  239 */       this.targetX = MathHelper.scrollSnapLerpSpeed(this.targetX, this.scrollUpperBound);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cardSelectUpdate() {
/*  244 */     AbstractCard hoveredCard = null;
/*  245 */     for (AbstractCard c : this.rewardGroup) {
/*  246 */       c.update();
/*  247 */       c.updateHoverLogic();
/*  248 */       if (c.hb.justHovered) {
/*  249 */         CardCrawlGame.sound.playV("CARD_OBTAIN", 0.4F);
/*      */       }
/*  251 */       if (c.hb.hovered) {
/*  252 */         hoveredCard = c;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  257 */     if (hoveredCard != null && InputHelper.justClickedLeft) {
/*  258 */       hoveredCard.hb.clickStarted = true;
/*      */     }
/*      */     
/*  261 */     if (hoveredCard != null && (InputHelper.justClickedRight || CInputActionSet.proceed.isJustPressed())) {
/*  262 */       InputHelper.justClickedRight = false;
/*      */       
/*  264 */       CardCrawlGame.cardPopup.open(hoveredCard);
/*      */     } 
/*      */     
/*  267 */     if (hoveredCard != null && CInputActionSet.select.isJustPressed()) {
/*  268 */       hoveredCard.hb.clicked = true;
/*      */     }
/*      */     
/*  271 */     if (hoveredCard != null && hoveredCard.hb.clicked) {
/*  272 */       hoveredCard.hb.clicked = false;
/*      */       
/*  274 */       if (!Settings.isTouchScreen) {
/*  275 */         this.skipButton.hide();
/*  276 */         this.bowlButton.hide();
/*      */         
/*  278 */         if (this.codex) {
/*  279 */           this.codexCard = hoveredCard;
/*  280 */         } else if (this.discovery) {
/*  281 */           this.discoveryCard = hoveredCard;
/*  282 */         } else if (this.chooseOne) {
/*  283 */           hoveredCard.onChoseThisOption();
/*  284 */           AbstractDungeon.effectList.add(new ExhaustCardEffect(hoveredCard));
/*      */         } else {
/*  286 */           acquireCard(hoveredCard);
/*      */         } 
/*      */         
/*  289 */         takeReward();
/*      */         
/*  291 */         if (!this.draft || this.draftCount >= 15) {
/*  292 */           AbstractDungeon.closeCurrentScreen();
/*  293 */           this.draftCount = 0;
/*      */         } else {
/*  295 */           draftOpen();
/*      */         }
/*      */       
/*  298 */       } else if (!this.confirmButton.hb.clicked) {
/*  299 */         this.touchCard = hoveredCard;
/*  300 */         this.confirmButton.show();
/*  301 */         this.confirmButton.isDisabled = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  307 */     if (InputHelper.justReleasedClickLeft && Settings.isTouchScreen && hoveredCard == null && !this.confirmButton.isDisabled && !this.confirmButton.hb.hovered) {
/*      */       
/*  309 */       this.confirmButton.hb.clickStarted = false;
/*  310 */       this.confirmButton.hide();
/*  311 */       this.touchCard = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void acquireCard(AbstractCard hoveredCard) {
/*  316 */     recordMetrics(hoveredCard);
/*  317 */     InputHelper.justClickedLeft = false;
/*  318 */     AbstractDungeon.effectsQueue.add(new FastCardObtainEffect(hoveredCard, hoveredCard.current_x, hoveredCard.current_y));
/*      */   }
/*      */ 
/*      */   
/*      */   private void recordMetrics(AbstractCard hoveredCard) {
/*  323 */     HashMap<String, Object> choice = new HashMap<>();
/*  324 */     ArrayList<String> notpicked = new ArrayList<>();
/*  325 */     for (AbstractCard card : this.rewardGroup) {
/*  326 */       if (card != hoveredCard) {
/*  327 */         notpicked.add(card.getMetricID());
/*      */       }
/*      */     } 
/*  330 */     choice.put("picked", hoveredCard.getMetricID());
/*  331 */     choice.put("not_picked", notpicked);
/*  332 */     choice.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/*  333 */     CardCrawlGame.metricData.card_choices.add(choice);
/*      */   }
/*      */   
/*      */   private void recordMetrics(String pickText) {
/*  337 */     HashMap<String, Object> choice = new HashMap<>();
/*  338 */     ArrayList<String> notpicked = new ArrayList<>();
/*  339 */     for (AbstractCard card : this.rewardGroup) {
/*  340 */       notpicked.add(card.getMetricID());
/*      */     }
/*  342 */     choice.put("picked", pickText);
/*  343 */     choice.put("not_picked", notpicked);
/*  344 */     choice.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/*  345 */     CardCrawlGame.metricData.card_choices.add(choice);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void takeReward() {
/*  352 */     if (this.rItem != null) {
/*  353 */       AbstractDungeon.combatRewardScreen.rewards.remove(this.rItem);
/*  354 */       AbstractDungeon.combatRewardScreen.positionRewards();
/*  355 */       if (AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
/*  356 */         AbstractDungeon.combatRewardScreen.hasTakenAll = true;
/*  357 */         AbstractDungeon.overlayMenu.proceedButton.show();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void completeVoting(int option) {
/*  363 */     if (!this.isVoting) {
/*      */       return;
/*      */     }
/*  366 */     this.isVoting = false;
/*  367 */     if (getVoter().isPresent()) {
/*  368 */       TwitchVoter twitchVoter = getVoter().get();
/*  369 */       AbstractDungeon.topPanel.twitch.ifPresent(twitchPanel -> twitchPanel.connection.sendMessage("Voting on card ended... chose " + (twitchVoter.getOptions()[option]).displayName));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  375 */     while (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.CARD_REWARD) {
/*  376 */       AbstractDungeon.closeCurrentScreen();
/*      */     }
/*  378 */     if (option != 0)
/*      */     {
/*      */       
/*  381 */       if (!this.bowlButton.isHidden() && option == 1) {
/*  382 */         this.bowlButton.onClick();
/*  383 */       } else if (!this.bowlButton.isHidden()) {
/*  384 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/*  385 */         acquireCard(this.rewardGroup.get(option - 2));
/*  386 */       } else if (option < this.rewardGroup.size() + 1) {
/*      */         
/*  388 */         AbstractDungeon.overlayMenu.cancelButton.hide();
/*  389 */         acquireCard(this.rewardGroup.get(option - 1));
/*      */       }  } 
/*  391 */     takeReward();
/*  392 */     AbstractDungeon.closeCurrentScreen();
/*      */   }
/*      */   
/*      */   private void renderTwitchVotes(SpriteBatch sb) {
/*  396 */     if (!this.isVoting) {
/*      */       return;
/*      */     }
/*  399 */     if (getVoter().isPresent()) {
/*  400 */       TwitchVoter twitchVoter = getVoter().get();
/*  401 */       TwitchVoteOption[] options = twitchVoter.getOptions();
/*  402 */       int voteCountOffset = this.bowlButton.isHidden() ? 1 : 2;
/*  403 */       int sum = ((Integer)Arrays.<TwitchVoteOption>stream(options).map(c -> Integer.valueOf(c.voteCount)).reduce(Integer.valueOf(0), Integer::sum)).intValue();
/*  404 */       for (int i = 0; i < this.rewardGroup.size(); i++) {
/*  405 */         AbstractCard c = this.rewardGroup.get(i);
/*      */         
/*  407 */         StringBuilder cardVoteText = (new StringBuilder("#")).append(i + voteCountOffset).append(": ").append((options[i + voteCountOffset]).voteCount);
/*      */         
/*  409 */         if (sum > 0) {
/*  410 */           cardVoteText.append(" (").append((options[i + voteCountOffset]).voteCount * 100 / sum).append("%)");
/*      */         }
/*  412 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, cardVoteText
/*      */ 
/*      */             
/*  415 */             .toString(), c.target_x, c.target_y - 200.0F * Settings.scale, Color.WHITE
/*      */ 
/*      */             
/*  418 */             .cpy());
/*      */       } 
/*      */ 
/*      */       
/*  422 */       StringBuilder skipVoteText = (new StringBuilder("#0: ")).append((options[0]).voteCount);
/*  423 */       if (sum > 0) {
/*  424 */         skipVoteText.append(" (").append((options[0]).voteCount * 100 / sum).append("%)");
/*      */       }
/*      */ 
/*      */       
/*  428 */       if (this.bowlButton.isHidden()) {
/*  429 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, skipVoteText
/*      */ 
/*      */             
/*  432 */             .toString(), Settings.WIDTH / 2.0F, 150.0F * Settings.scale, Color.WHITE
/*      */ 
/*      */             
/*  435 */             .cpy());
/*      */       } else {
/*  437 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, skipVoteText
/*      */ 
/*      */             
/*  440 */             .toString(), Settings.WIDTH / 2.0F - 100.0F, 150.0F * Settings.scale, Color.WHITE
/*      */ 
/*      */             
/*  443 */             .cpy());
/*      */         
/*  445 */         StringBuilder bowlVoteText = (new StringBuilder("#1: ")).append((options[1]).voteCount);
/*  446 */         if (sum > 0) {
/*  447 */           bowlVoteText.append(" (").append((options[1]).voteCount * 100 / sum).append("%)");
/*      */         }
/*  449 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, bowlVoteText
/*      */ 
/*      */             
/*  452 */             .toString(), Settings.WIDTH / 2.0F + 100.0F, 150.0F * Settings.scale, Color.WHITE
/*      */ 
/*      */             
/*  455 */             .cpy());
/*      */       } 
/*      */ 
/*      */       
/*  459 */       FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[3] + twitchVoter
/*      */ 
/*      */           
/*  462 */           .getSecondsRemaining() + TEXT[4], Settings.WIDTH / 2.0F, AbstractDungeon.dynamicBanner.y - 70.0F * Settings.scale, Color.WHITE
/*      */ 
/*      */           
/*  465 */           .cpy());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void render(SpriteBatch sb) {
/*  470 */     this.peekButton.render(sb);
/*      */     
/*  472 */     if (!PeekButton.isPeeking) {
/*  473 */       this.confirmButton.render(sb);
/*  474 */       this.skipButton.render(sb);
/*  475 */       this.bowlButton.render(sb);
/*  476 */       renderCardReward(sb);
/*      */       
/*  478 */       if (shouldShowScrollBar()) {
/*  479 */         this.scrollBar.render(sb);
/*      */       }
/*      */       
/*  482 */       if (!this.codex && !this.discovery) {
/*  483 */         renderTwitchVotes(sb);
/*      */       }
/*      */     } 
/*      */     
/*  487 */     if (this.codex || this.discovery) {
/*  488 */       AbstractDungeon.overlayMenu.combatDeckPanel.render(sb);
/*  489 */       AbstractDungeon.overlayMenu.discardPilePanel.render(sb);
/*  490 */       AbstractDungeon.overlayMenu.exhaustPanel.render(sb);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderCardReward(SpriteBatch sb) {
/*  495 */     if (this.rewardGroup.size() > 5) {
/*      */ 
/*      */       
/*  498 */       int maxPossibleStartingIndex = this.rewardGroup.size() - 4;
/*  499 */       int indexToStartAt = Math.max(
/*  500 */           Math.min(
/*  501 */             (int)((maxPossibleStartingIndex + 1) * MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollX)), maxPossibleStartingIndex), 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  507 */       for (AbstractCard c : this.rewardGroup) {
/*  508 */         if (this.rewardGroup.indexOf(c) >= indexToStartAt && this.rewardGroup.indexOf(c) < indexToStartAt + 4) {
/*      */ 
/*      */           
/*  511 */           c.target_x = Settings.WIDTH / 2.0F + ((this.rewardGroup.indexOf(c) - indexToStartAt) - 1.5F) * (AbstractCard.IMG_WIDTH + PAD_X);
/*      */ 
/*      */         
/*      */         }
/*  515 */         else if (this.rewardGroup.indexOf(c) < indexToStartAt) {
/*      */           
/*  517 */           c.target_x = -Settings.WIDTH * 0.25F;
/*      */         } else {
/*      */           
/*  520 */           c.target_x = Settings.WIDTH * 1.25F;
/*      */         } 
/*      */         
/*  523 */         c.target_y = Settings.HEIGHT / 2.0F;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  528 */       for (AbstractCard c : this.rewardGroup) {
/*  529 */         c.target_x = Settings.WIDTH / 2.0F + (this.rewardGroup.indexOf(c) - (this.rewardGroup.size() - 1) / 2.0F) * (AbstractCard.IMG_WIDTH + PAD_X);
/*      */         
/*  531 */         c.target_y = CARD_TARGET_Y;
/*      */       } 
/*      */     } 
/*  534 */     for (AbstractCard c : this.rewardGroup) {
/*  535 */       c.render(sb);
/*      */     }
/*  537 */     for (AbstractCard c : this.rewardGroup) {
/*  538 */       c.renderCardTip(sb);
/*      */     }
/*      */   }
/*      */   
/*      */   public void reopen() {
/*  543 */     this.confirmButton.hideInstantly();
/*  544 */     this.touchCard = null;
/*      */     
/*  546 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*      */     
/*  548 */     if (this.draft || this.codex || this.discovery || this.chooseOne) {
/*  549 */       if (this.skippable) {
/*  550 */         this.skipButton.show();
/*      */       } else {
/*  552 */         this.skipButton.hide();
/*      */       } 
/*  554 */       this.bowlButton.hide();
/*      */     }
/*  556 */     else if (AbstractDungeon.player.hasRelic("Singing Bowl")) {
/*  557 */       if (this.skippable) {
/*  558 */         this.skipButton.show(true);
/*      */       } else {
/*  560 */         this.skipButton.hide();
/*      */       } 
/*  562 */       this.bowlButton.show(this.rItem);
/*      */     } else {
/*  564 */       if (this.skippable) {
/*  565 */         this.skipButton.show();
/*      */       } else {
/*  567 */         this.skipButton.hide();
/*      */       } 
/*  569 */       this.bowlButton.hide();
/*      */     } 
/*      */ 
/*      */     
/*  573 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  574 */     AbstractDungeon.isScreenUp = true;
/*      */     
/*  576 */     AbstractDungeon.dynamicBanner.appear(this.header);
/*      */     
/*  578 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*      */     
/*  580 */     this.skipButton.screenDisabled = true;
/*      */   }
/*      */   
/*      */   public void open(ArrayList<AbstractCard> cards, RewardItem rItem, String header) {
/*  584 */     this.peekButton.hideInstantly();
/*  585 */     this.confirmButton.hideInstantly();
/*  586 */     this.touchCard = null;
/*      */     
/*  588 */     this.codex = false;
/*  589 */     this.discovery = false;
/*  590 */     this.chooseOne = false;
/*  591 */     this.draft = false;
/*  592 */     this.rItem = rItem;
/*      */     
/*  594 */     this.skippable = true;
/*  595 */     if (AbstractDungeon.player.hasRelic("Singing Bowl")) {
/*  596 */       this.skipButton.show(true);
/*  597 */       this.bowlButton.show(rItem);
/*      */     } else {
/*  599 */       this.skipButton.show();
/*  600 */       this.bowlButton.hide();
/*      */     } 
/*      */     
/*  603 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  604 */     this.rewardGroup = cards;
/*  605 */     AbstractDungeon.isScreenUp = true;
/*  606 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*      */     
/*  608 */     this.header = header;
/*  609 */     AbstractDungeon.dynamicBanner.appear(header);
/*  610 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*  611 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  612 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/*  614 */     for (AbstractCard c : cards) {
/*  615 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */ 
/*      */     
/*  619 */     for (AbstractCard c : cards) {
/*  620 */       if (c.type == AbstractCard.CardType.POWER && !((Boolean)TipTracker.tips.get("POWER_TIP")).booleanValue()) {
/*  621 */         AbstractDungeon.ftue = new FtueTip(AbstractPlayer.LABEL[0], AbstractPlayer.MSG[0], Settings.WIDTH / 2.0F - 500.0F * Settings.scale, Settings.HEIGHT / 2.0F, c);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  627 */         AbstractDungeon.ftue.type = FtueTip.TipType.POWER;
/*  628 */         TipTracker.neverShowAgain("POWER_TIP");
/*  629 */         this.skipButton.hide();
/*  630 */         this.bowlButton.hide();
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  635 */     this.mayVote = true;
/*  636 */     if (AbstractDungeon.topPanel.twitch.isPresent()) {
/*  637 */       updateVote();
/*      */     }
/*      */   }
/*      */   
/*      */   public void customCombatOpen(ArrayList<AbstractCard> choices, String text, boolean skippable) {
/*  642 */     this.confirmButton.hideInstantly();
/*  643 */     this.touchCard = null;
/*      */     
/*  645 */     this.rItem = null;
/*  646 */     this.codex = false;
/*  647 */     this.discovery = true;
/*  648 */     this.chooseOne = false;
/*  649 */     this.discoveryCard = null;
/*  650 */     this.draft = false;
/*  651 */     this.codexCard = null;
/*      */     
/*  653 */     this.header = text;
/*      */     
/*  655 */     this.peekButton.hideInstantly();
/*  656 */     this.peekButton.show();
/*  657 */     this.skippable = skippable;
/*  658 */     if (skippable) {
/*  659 */       this.skipButton.show();
/*      */     } else {
/*  661 */       this.skipButton.hide();
/*      */     } 
/*  663 */     this.bowlButton.hide();
/*  664 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  665 */     this.rewardGroup = choices;
/*      */     
/*  667 */     AbstractDungeon.isScreenUp = true;
/*  668 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  669 */     AbstractDungeon.dynamicBanner.appear(this.header);
/*  670 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  671 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/*  673 */     for (AbstractCard c : this.rewardGroup) {
/*  674 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */   
/*      */   public void chooseOneOpen(ArrayList<AbstractCard> choices) {
/*  679 */     this.rItem = null;
/*  680 */     this.codex = false;
/*  681 */     this.discovery = false;
/*  682 */     this.chooseOne = true;
/*  683 */     this.discoveryCard = null;
/*  684 */     this.draft = false;
/*  685 */     this.codexCard = null;
/*      */     
/*  687 */     this.header = TEXT[6];
/*      */     
/*  689 */     this.peekButton.hideInstantly();
/*  690 */     this.peekButton.show();
/*  691 */     this.bowlButton.hide();
/*  692 */     this.skippable = false;
/*  693 */     this.skipButton.hide();
/*      */     
/*  695 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  696 */     this.rewardGroup = choices;
/*  697 */     AbstractDungeon.isScreenUp = true;
/*  698 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  699 */     AbstractDungeon.dynamicBanner.appear(this.header);
/*  700 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  701 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/*  703 */     for (AbstractCard c : choices) {
/*  704 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */   
/*      */   public void draftOpen() {
/*  709 */     this.confirmButton.hideInstantly();
/*  710 */     this.touchCard = null;
/*      */     
/*  712 */     this.draftCount++;
/*  713 */     this.rItem = null;
/*  714 */     this.codex = false;
/*  715 */     this.discovery = false;
/*  716 */     this.discoveryCard = null;
/*  717 */     this.chooseOne = false;
/*  718 */     this.draft = true;
/*  719 */     this.codexCard = null;
/*      */     
/*  721 */     this.header = TEXT[1];
/*      */     
/*  723 */     this.bowlButton.hide();
/*  724 */     this.skippable = false;
/*  725 */     this.skipButton.hide();
/*      */     
/*  727 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  728 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*      */     
/*  730 */     while (derp.size() != 3) {
/*  731 */       boolean dupe = false;
/*  732 */       AbstractCard tmp = AbstractDungeon.returnRandomCard();
/*  733 */       for (AbstractCard c : derp) {
/*  734 */         if (c.cardID.equals(tmp.cardID)) {
/*  735 */           dupe = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  739 */       if (!dupe) {
/*  740 */         derp.add(tmp.makeCopy());
/*      */       }
/*      */     } 
/*      */     
/*  744 */     this.rewardGroup = derp;
/*  745 */     AbstractDungeon.isScreenUp = true;
/*  746 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  747 */     AbstractDungeon.dynamicBanner.appear(this.header);
/*  748 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  749 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */ 
/*      */     
/*  752 */     for (AbstractCard c : this.rewardGroup) {
/*  753 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void placeCards(float x, float y) {
/*  759 */     int maxPossibleStartingIndex = this.rewardGroup.size() - 4;
/*  760 */     int indexToStartAt = (int)((maxPossibleStartingIndex + 1) * MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollX));
/*      */ 
/*      */ 
/*      */     
/*  764 */     if (indexToStartAt > maxPossibleStartingIndex) {
/*  765 */       indexToStartAt = maxPossibleStartingIndex;
/*      */     }
/*      */     
/*  768 */     for (AbstractCard c : this.rewardGroup) {
/*  769 */       c.drawScale = 0.75F;
/*  770 */       c.targetDrawScale = 0.75F;
/*  771 */       if (this.rewardGroup.size() > 5) {
/*      */         
/*  773 */         if (this.rewardGroup.indexOf(c) < indexToStartAt) {
/*  774 */           c.current_x = -Settings.WIDTH * 0.25F;
/*  775 */         } else if (this.rewardGroup.indexOf(c) >= indexToStartAt + 4) {
/*  776 */           c.current_x = Settings.WIDTH * 1.25F;
/*      */         } else {
/*  778 */           c.current_x = x;
/*      */         } 
/*      */       } else {
/*  781 */         c.current_x = x;
/*      */       } 
/*  783 */       c.current_y = y;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void onClose() {
/*  788 */     if (AbstractDungeon.topPanel.twitch.isPresent()) {
/*  789 */       this.mayVote = false;
/*  790 */       updateVote();
/*      */     } 
/*      */     
/*  793 */     if (Settings.isControllerMode) {
/*  794 */       InputHelper.moveCursorToNeutralPosition();
/*      */     }
/*      */   }
/*      */   
/*      */   public void reset() {
/*  799 */     this.draftCount = 0;
/*  800 */     this.codex = false;
/*  801 */     this.discovery = false;
/*  802 */     this.chooseOne = false;
/*  803 */     this.draft = false;
/*      */   }
/*      */   
/*      */   public void skippedCards() {
/*  807 */     recordMetrics("SKIP");
/*      */   }
/*      */   
/*      */   public void closeFromBowlButton() {
/*  811 */     recordMetrics("Singing Bowl");
/*      */   }
/*      */   
/*      */   private Optional<TwitchVoter> getVoter() {
/*  815 */     return TwitchPanel.getDefaultVoter();
/*      */   }
/*      */   
/*      */   private void updateVote() {
/*  819 */     if (getVoter().isPresent()) {
/*  820 */       TwitchVoter twitchVoter = getVoter().get();
/*  821 */       if (this.mayVote && twitchVoter.isVotingConnected() && !this.isVoting) {
/*  822 */         logger.info("Publishing Card Reward Vote");
/*  823 */         if (this.bowlButton.isHidden()) {
/*  824 */           this.isVoting = twitchVoter.initiateSimpleNumberVote(
/*  825 */               (String[])Stream.concat(Stream.of(TEXT[0]), this.rewardGroup.stream().map(AbstractCard::toString)).toArray(x$0 -> new String[x$0]), this::completeVoting);
/*      */         }
/*      */         else {
/*      */           
/*  829 */           this.isVoting = twitchVoter.initiateSimpleNumberVote(
/*  830 */               (String[])Stream.concat(
/*  831 */                 Stream.builder().add(TEXT[0]).add(TEXT[2]).build(), this.rewardGroup
/*  832 */                 .stream().map(AbstractCard::toString)).toArray(x$0 -> new String[x$0]), this::completeVoting);
/*      */         }
/*      */       
/*  835 */       } else if (this.isVoting && (!this.mayVote || !twitchVoter.isVotingConnected())) {
/*  836 */         twitchVoter.endVoting(true);
/*  837 */         this.isVoting = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void scrolledUsingBar(float newPercent) {
/*  844 */     this.scrollX = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/*  845 */     this.targetX = this.scrollX;
/*  846 */     updateBarPosition();
/*      */   }
/*      */   
/*      */   private void updateBarPosition() {
/*  850 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollX);
/*  851 */     this.scrollBar.parentScrolledToPercent(percent);
/*      */   }
/*      */   
/*      */   private boolean shouldShowScrollBar() {
/*  855 */     return (this.rewardGroup.size() > 5);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void discoveryOpen() {
/*  860 */     this.confirmButton.hideInstantly();
/*  861 */     this.touchCard = null;
/*      */     
/*  863 */     this.rItem = null;
/*  864 */     this.codex = false;
/*  865 */     this.discovery = true;
/*  866 */     this.chooseOne = false;
/*  867 */     this.discoveryCard = null;
/*  868 */     this.draft = false;
/*  869 */     this.codexCard = null;
/*      */     
/*  871 */     this.peekButton.hideInstantly();
/*  872 */     this.peekButton.show();
/*  873 */     this.bowlButton.hide();
/*  874 */     this.skipButton.hide();
/*      */     
/*  876 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  877 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*      */     
/*  879 */     while (derp.size() != 3) {
/*  880 */       boolean dupe = false;
/*  881 */       AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
/*  882 */       for (AbstractCard c : derp) {
/*  883 */         if (c.cardID.equals(tmp.cardID)) {
/*  884 */           dupe = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  888 */       if (!dupe) {
/*  889 */         derp.add(tmp.makeCopy());
/*      */       }
/*      */     } 
/*      */     
/*  893 */     this.rewardGroup = derp;
/*  894 */     AbstractDungeon.isScreenUp = true;
/*  895 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  896 */     AbstractDungeon.dynamicBanner.appear(TEXT[1]);
/*  897 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  898 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/*  900 */     for (AbstractCard c : this.rewardGroup) {
/*  901 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void chooseOneColorless() {
/*  907 */     this.confirmButton.hideInstantly();
/*  908 */     this.touchCard = null;
/*      */     
/*  910 */     this.rItem = null;
/*  911 */     this.codex = false;
/*  912 */     this.discovery = true;
/*  913 */     this.chooseOne = false;
/*  914 */     this.discoveryCard = null;
/*  915 */     this.draft = false;
/*  916 */     this.codexCard = null;
/*      */     
/*  918 */     this.bowlButton.hide();
/*  919 */     this.skipButton.show();
/*      */     
/*  921 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  922 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*      */     
/*  924 */     while (derp.size() != 3) {
/*  925 */       boolean dupe = false;
/*  926 */       AbstractCard tmp = AbstractDungeon.returnColorlessCard();
/*  927 */       for (AbstractCard c : derp) {
/*  928 */         if (c.cardID.equals(tmp.cardID)) {
/*  929 */           dupe = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  933 */       if (!dupe) {
/*  934 */         derp.add(tmp.makeCopy());
/*      */       }
/*      */     } 
/*      */     
/*  938 */     this.rewardGroup = derp;
/*  939 */     AbstractDungeon.isScreenUp = true;
/*  940 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  941 */     AbstractDungeon.dynamicBanner.appear(TEXT[1]);
/*  942 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  943 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/*  945 */     for (AbstractCard c : this.rewardGroup) {
/*  946 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void discoveryOpen(AbstractCard.CardType type) {
/*  952 */     this.confirmButton.hideInstantly();
/*  953 */     this.touchCard = null;
/*      */     
/*  955 */     this.rItem = null;
/*  956 */     this.codex = false;
/*  957 */     this.discovery = true;
/*  958 */     this.chooseOne = false;
/*  959 */     this.discoveryCard = null;
/*  960 */     this.draft = false;
/*  961 */     this.codexCard = null;
/*      */     
/*  963 */     this.bowlButton.hide();
/*  964 */     this.skipButton.show();
/*      */     
/*  966 */     AbstractDungeon.topPanel.unhoverHitboxes();
/*  967 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*      */     
/*  969 */     while (derp.size() != 3) {
/*  970 */       boolean dupe = false;
/*  971 */       AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat(type);
/*  972 */       for (AbstractCard c : derp) {
/*  973 */         if (c.cardID.equals(tmp.cardID)) {
/*  974 */           dupe = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  978 */       if (!dupe) {
/*  979 */         derp.add(tmp.makeCopy());
/*      */       }
/*      */     } 
/*      */     
/*  983 */     this.rewardGroup = derp;
/*  984 */     AbstractDungeon.isScreenUp = true;
/*  985 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/*  986 */     AbstractDungeon.dynamicBanner.appear(TEXT[1]);
/*  987 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  988 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/*  990 */     for (AbstractCard c : this.rewardGroup) {
/*  991 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void carveRealityOpen(ArrayList<AbstractCard> cardsToPickBetween) {
/*  998 */     this.rItem = null;
/*  999 */     this.codex = false;
/* 1000 */     this.discovery = true;
/* 1001 */     this.chooseOne = false;
/* 1002 */     this.discoveryCard = null;
/* 1003 */     this.draft = false;
/* 1004 */     this.codexCard = null;
/*      */     
/* 1006 */     this.bowlButton.hide();
/* 1007 */     this.skipButton.show();
/*      */     
/* 1009 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 1010 */     this.rewardGroup = cardsToPickBetween;
/* 1011 */     AbstractDungeon.isScreenUp = true;
/* 1012 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/* 1013 */     AbstractDungeon.dynamicBanner.appear(TEXT[1]);
/* 1014 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 1015 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/* 1017 */     for (AbstractCard c : this.rewardGroup) {
/* 1018 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void foreignInfluenceOpen() {
/* 1028 */     this.rItem = null;
/* 1029 */     this.codex = false;
/* 1030 */     this.discovery = true;
/* 1031 */     this.chooseOne = false;
/* 1032 */     this.discoveryCard = null;
/* 1033 */     this.draft = false;
/* 1034 */     this.codexCard = null;
/*      */     
/* 1036 */     this.peekButton.hideInstantly();
/* 1037 */     this.peekButton.show();
/* 1038 */     this.bowlButton.hide();
/* 1039 */     this.skipButton.show();
/*      */     
/* 1041 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 1042 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*      */ 
/*      */     
/* 1045 */     while (derp.size() != 3) {
/* 1046 */       AbstractCard.CardRarity cardRarity; boolean dupe = false;
/*      */ 
/*      */       
/* 1049 */       int roll = AbstractDungeon.cardRandomRng.random(99);
/* 1050 */       if (roll < 55) {
/* 1051 */         cardRarity = AbstractCard.CardRarity.COMMON;
/* 1052 */       } else if (roll < 85) {
/* 1053 */         cardRarity = AbstractCard.CardRarity.UNCOMMON;
/*      */       } else {
/* 1055 */         cardRarity = AbstractCard.CardRarity.RARE;
/*      */       } 
/*      */       
/* 1058 */       AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.ATTACK, cardRarity);
/*      */       
/* 1060 */       for (AbstractCard c : derp) {
/* 1061 */         if (c.cardID.equals(tmp.cardID)) {
/* 1062 */           dupe = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1067 */       if (!dupe) {
/* 1068 */         derp.add(tmp.makeCopy());
/*      */       }
/*      */     } 
/*      */     
/* 1072 */     this.rewardGroup = derp;
/* 1073 */     AbstractDungeon.isScreenUp = true;
/* 1074 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/* 1075 */     AbstractDungeon.dynamicBanner.appear(TEXT[1]);
/* 1076 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 1077 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/* 1079 */     for (AbstractCard c : this.rewardGroup) {
/* 1080 */       UnlockTracker.markCardAsSeen(c.cardID);
/*      */     }
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void codexOpen() {
/* 1086 */     this.confirmButton.hideInstantly();
/* 1087 */     this.touchCard = null;
/*      */     
/* 1089 */     this.rItem = null;
/* 1090 */     this.codex = true;
/* 1091 */     this.discovery = false;
/* 1092 */     this.discoveryCard = null;
/* 1093 */     this.chooseOne = false;
/* 1094 */     this.draft = false;
/* 1095 */     this.codexCard = null;
/*      */     
/* 1097 */     this.peekButton.hideInstantly();
/* 1098 */     this.peekButton.show();
/* 1099 */     this.bowlButton.hide();
/* 1100 */     this.skipButton.show();
/*      */     
/* 1102 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 1103 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*      */     
/* 1105 */     while (derp.size() != 3) {
/* 1106 */       boolean dupe = false;
/* 1107 */       AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
/* 1108 */       for (AbstractCard c : derp) {
/* 1109 */         if (c.cardID.equals(tmp.cardID)) {
/* 1110 */           dupe = true;
/*      */           break;
/*      */         } 
/*      */       } 
/* 1114 */       if (!dupe) {
/* 1115 */         derp.add(tmp.makeCopy());
/*      */       }
/*      */     } 
/*      */     
/* 1119 */     this.rewardGroup = derp;
/* 1120 */     AbstractDungeon.isScreenUp = true;
/* 1121 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
/* 1122 */     AbstractDungeon.dynamicBanner.appear(TEXT[1]);
/* 1123 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 1124 */     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
/*      */     
/* 1126 */     for (AbstractCard c : this.rewardGroup)
/* 1127 */       UnlockTracker.markCardAsSeen(c.cardID); 
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\CardRewardScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */