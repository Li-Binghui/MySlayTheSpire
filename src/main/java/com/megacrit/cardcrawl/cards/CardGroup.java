/*      */ package com.megacrit.cardcrawl.cards;
/*      */ 
/*      */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*      */ import com.badlogic.gdx.math.MathUtils;
/*      */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*      */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*      */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*      */ import com.megacrit.cardcrawl.core.Settings;
/*      */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*      */ import com.megacrit.cardcrawl.helpers.RelicLibrary;
/*      */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*      */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*      */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*      */ import com.megacrit.cardcrawl.random.Random;
/*      */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*      */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*      */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*      */ import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CardGroup
/*      */ {
/*   46 */   private static final Logger logger = LogManager.getLogger(CardGroup.class.getName());
/*   47 */   public ArrayList<AbstractCard> group = new ArrayList<>();
/*   48 */   public float HAND_START_X = Settings.WIDTH * 0.36F; public float HAND_OFFSET_X = AbstractCard.IMG_WIDTH * 0.35F;
/*      */   private static final float HAND_HOVER_PUSH_AMT = 0.4F;
/*      */   private static final float PUSH_TAPER = 0.25F;
/*   51 */   public static final float DRAW_PILE_X = Settings.WIDTH * 0.04F; private static final float TWO_CARD_PUSH_AMT = 0.2F; private static final float THREE_FOUR_CARD_PUSH_AMT = 0.27F; public static final float DRAW_PILE_Y = 50.0F * Settings.scale;
/*   52 */   public static final int DISCARD_PILE_X = (int)(Settings.WIDTH + AbstractCard.IMG_WIDTH_S / 2.0F + 100.0F * Settings.scale);
/*      */   public static final int DISCARD_PILE_Y = 0;
/*      */   public CardGroupType type;
/*   55 */   public HashMap<Integer, Integer> handPositioningMap = new HashMap<>();
/*   56 */   private ArrayList<AbstractCard> queued = new ArrayList<>();
/*   57 */   private ArrayList<AbstractCard> inHand = new ArrayList<>();
/*      */   
/*      */   public CardGroup(CardGroupType type) {
/*   60 */     this.type = type;
/*      */   }
/*      */   
/*      */   public CardGroup(CardGroup g, CardGroupType type) {
/*   64 */     this.type = type;
/*   65 */     for (AbstractCard c : g.group) {
/*   66 */       this.group.add(c.makeSameInstanceOf());
/*      */     }
/*      */   }
/*      */   
/*      */   public ArrayList<CardSave> getCardDeck() {
/*   71 */     ArrayList<CardSave> retVal = new ArrayList<>();
/*   72 */     for (AbstractCard card : this.group) {
/*   73 */       retVal.add(new CardSave(card.cardID, card.timesUpgraded, card.misc));
/*      */     }
/*   75 */     return retVal;
/*      */   }
/*      */   
/*      */   public ArrayList<String> getCardNames() {
/*   79 */     ArrayList<String> retVal = new ArrayList<>();
/*   80 */     for (AbstractCard card : this.group) {
/*   81 */       retVal.add(card.cardID);
/*      */     }
/*   83 */     return retVal;
/*      */   }
/*      */   
/*      */   public ArrayList<String> getCardIdsForMetrics() {
/*   87 */     ArrayList<String> retVal = new ArrayList<>();
/*   88 */     for (AbstractCard card : this.group) {
/*   89 */       retVal.add(card.getMetricID());
/*      */     }
/*   91 */     return retVal;
/*      */   }
/*      */   
/*      */   public void clear() {
/*   95 */     this.group.clear();
/*      */   }
/*      */   
/*      */   public boolean contains(AbstractCard c) {
/*   99 */     return this.group.contains(c);
/*      */   }
/*      */   
/*      */   public boolean canUseAnyCard() {
/*  103 */     for (AbstractCard c : this.group) {
/*  104 */       if (c.hasEnoughEnergy()) {
/*  105 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  109 */     return false;
/*      */   }
/*      */   
/*      */   public int fullSetCheck() {
/*  113 */     int times = 0;
/*      */     
/*  115 */     ArrayList<String> cardIDS = new ArrayList<>();
/*  116 */     for (AbstractCard c : this.group) {
/*  117 */       if (c.rarity != AbstractCard.CardRarity.BASIC) {
/*  118 */         cardIDS.add(c.cardID);
/*      */       }
/*      */     } 
/*      */     
/*  122 */     HashMap<String, Integer> cardCount = new HashMap<>();
/*      */     
/*  124 */     for (String cardID : cardIDS) {
/*  125 */       if (cardCount.containsKey(cardID)) {
/*      */         
/*  127 */         cardCount.put(cardID, Integer.valueOf(((Integer)cardCount.get(cardID)).intValue() + 1));
/*      */         continue;
/*      */       } 
/*  130 */       cardCount.put(cardID, Integer.valueOf(1));
/*      */     } 
/*      */ 
/*      */     
/*  134 */     for (Map.Entry<String, Integer> card : cardCount.entrySet()) {
/*  135 */       if (((Integer)card.getValue()).intValue() >= 4) {
/*  136 */         times++;
/*      */       }
/*      */     } 
/*      */     
/*  140 */     return times;
/*      */   }
/*      */   
/*      */   public boolean pauperCheck() {
/*  144 */     for (AbstractCard c : this.group) {
/*  145 */       if (c.rarity == AbstractCard.CardRarity.RARE) {
/*  146 */         return false;
/*      */       }
/*      */     } 
/*  149 */     return true;
/*      */   }
/*      */   
/*      */   public boolean cursedCheck() {
/*  153 */     int count = 0;
/*  154 */     for (AbstractCard c : this.group) {
/*  155 */       if (c.type == AbstractCard.CardType.CURSE) {
/*  156 */         count++;
/*      */       }
/*      */     } 
/*  159 */     return (count >= 5);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean highlanderCheck() {
/*  164 */     ArrayList<String> cardIDS = new ArrayList<>();
/*  165 */     for (AbstractCard c : this.group) {
/*  166 */       if (c.rarity != AbstractCard.CardRarity.BASIC) {
/*  167 */         cardIDS.add(c.cardID);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  172 */     Set<String> set = new HashSet<>(cardIDS);
/*      */     
/*  174 */     if (set.size() < cardIDS.size())
/*      */     {
/*  176 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  180 */     return true;
/*      */   }
/*      */   
/*      */   public void applyPowers() {
/*  184 */     for (AbstractCard c : this.group) {
/*  185 */       c.applyPowers();
/*      */     }
/*      */   }
/*      */   
/*      */   public void removeCard(AbstractCard c) {
/*  190 */     this.group.remove(c);
/*  191 */     if (this.type == CardGroupType.MASTER_DECK) {
/*  192 */       c.onRemoveFromMasterDeck();
/*  193 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  194 */         r.onMasterDeckChange();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeCard(String targetID) {
/*  205 */     for (Iterator<AbstractCard> i = this.group.iterator(); i.hasNext(); ) {
/*  206 */       AbstractCard e = i.next();
/*  207 */       if (e.cardID.equals(targetID)) {
/*  208 */         i.remove();
/*  209 */         return true;
/*      */       } 
/*      */     } 
/*  212 */     return false;
/*      */   }
/*      */   
/*      */   public void addToHand(AbstractCard c) {
/*  216 */     c.untip();
/*  217 */     this.group.add(c);
/*      */   }
/*      */   
/*      */   public void refreshHandLayout() {
/*  221 */     if ((AbstractDungeon.getCurrRoom()).monsters != null && (AbstractDungeon.getCurrRoom()).monsters
/*  222 */       .areMonstersBasicallyDead()) {
/*      */       return;
/*      */     }
/*      */     
/*  226 */     if (AbstractDungeon.player.hasPower("Surrounded") && 
/*  227 */       (AbstractDungeon.getCurrRoom()).monsters != null) {
/*  228 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/*  229 */         if (AbstractDungeon.player.flipHorizontal) {
/*  230 */           if (AbstractDungeon.player.drawX < m.drawX) {
/*  231 */             m.applyPowers(); continue;
/*      */           } 
/*  233 */           m.applyPowers();
/*  234 */           m.removeSurroundedPower(); continue;
/*      */         } 
/*  236 */         if (!AbstractDungeon.player.flipHorizontal) {
/*  237 */           if (AbstractDungeon.player.drawX > m.drawX) {
/*  238 */             m.applyPowers(); continue;
/*      */           } 
/*  240 */           m.applyPowers();
/*  241 */           m.removeSurroundedPower();
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  247 */     for (AbstractOrb o : AbstractDungeon.player.orbs) {
/*  248 */       o.hideEvokeValues();
/*      */     }
/*      */ 
/*      */     
/*  252 */     if (AbstractDungeon.player.hand.size() + AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile
/*  253 */       .size() <= 3 && 
/*  254 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && (AbstractDungeon.getCurrRoom()).monsters != null && 
/*  255 */       !(AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead() && AbstractDungeon.floorNum > 3) {
/*  256 */       UnlockTracker.unlockAchievement("PURITY");
/*      */     }
/*      */     
/*  259 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  260 */       r.onRefreshHand();
/*      */     }
/*      */ 
/*      */     
/*  264 */     float angleRange = 50.0F - (10 - this.group.size()) * 5.0F;
/*  265 */     float incrementAngle = angleRange / this.group.size();
/*  266 */     float sinkStart = 80.0F * Settings.scale;
/*  267 */     float sinkRange = 300.0F * Settings.scale;
/*  268 */     float incrementSink = sinkRange / this.group.size() / 2.0F;
/*  269 */     int middle = this.group.size() / 2;
/*      */     
/*  271 */     for (int i = 0; i < this.group.size(); i++) {
/*  272 */       ((AbstractCard)this.group.get(i)).setAngle(angleRange / 2.0F - incrementAngle * i - incrementAngle / 2.0F);
/*      */       
/*  274 */       int t = i - middle;
/*  275 */       if (t >= 0) {
/*  276 */         if (this.group.size() % 2 == 0) {
/*  277 */           t++;
/*  278 */           t = -t;
/*      */         } else {
/*  280 */           t = -t;
/*      */         } 
/*      */       }
/*      */       
/*  284 */       if (this.group.size() % 2 == 0) {
/*  285 */         t++;
/*      */       }
/*  287 */       t = (int)(t * 1.7F);
/*  288 */       ((AbstractCard)this.group.get(i)).target_y = sinkStart + incrementSink * t;
/*      */     } 
/*      */     
/*  291 */     for (AbstractCard c : this.group) {
/*  292 */       c.targetDrawScale = 0.75F;
/*      */     }
/*      */     
/*  295 */     switch (this.group.size()) {
/*      */       case 0:
/*      */         return;
/*      */       case 1:
/*  299 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F;
/*      */         break;
/*      */       case 2:
/*  302 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.47F;
/*  303 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.53F;
/*      */         break;
/*      */       case 3:
/*  306 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.9F;
/*  307 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F;
/*  308 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.9F;
/*  309 */         ((AbstractCard)this.group.get(0)).target_y += 20.0F * Settings.scale;
/*  310 */         ((AbstractCard)this.group.get(2)).target_y += 20.0F * Settings.scale;
/*      */         break;
/*      */       case 4:
/*  313 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.36F;
/*  314 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.46F;
/*  315 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.46F;
/*  316 */         ((AbstractCard)this.group.get(3)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.36F;
/*  317 */         ((AbstractCard)this.group.get(1)).target_y -= 10.0F * Settings.scale;
/*  318 */         ((AbstractCard)this.group.get(2)).target_y -= 10.0F * Settings.scale;
/*      */         break;
/*      */       case 5:
/*  321 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.7F;
/*  322 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.9F;
/*  323 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F;
/*  324 */         ((AbstractCard)this.group.get(3)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.9F;
/*  325 */         ((AbstractCard)this.group.get(4)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.7F;
/*  326 */         ((AbstractCard)this.group.get(0)).target_y += 25.0F * Settings.scale;
/*  327 */         ((AbstractCard)this.group.get(2)).target_y -= 10.0F * Settings.scale;
/*  328 */         ((AbstractCard)this.group.get(4)).target_y += 25.0F * Settings.scale;
/*      */         break;
/*      */       case 6:
/*  331 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 2.1F;
/*  332 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.3F;
/*  333 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.43F;
/*  334 */         ((AbstractCard)this.group.get(3)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.43F;
/*  335 */         ((AbstractCard)this.group.get(4)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.3F;
/*  336 */         ((AbstractCard)this.group.get(5)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 2.1F;
/*  337 */         ((AbstractCard)this.group.get(0)).target_y += 10.0F * Settings.scale;
/*  338 */         ((AbstractCard)this.group.get(5)).target_y += 10.0F * Settings.scale;
/*      */         break;
/*      */       case 7:
/*  341 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 2.4F;
/*  342 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.7F;
/*  343 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.9F;
/*  344 */         ((AbstractCard)this.group.get(3)).target_x = Settings.WIDTH / 2.0F;
/*  345 */         ((AbstractCard)this.group.get(4)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.9F;
/*  346 */         ((AbstractCard)this.group.get(5)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.7F;
/*  347 */         ((AbstractCard)this.group.get(6)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 2.4F;
/*  348 */         ((AbstractCard)this.group.get(0)).target_y += 25.0F * Settings.scale;
/*  349 */         ((AbstractCard)this.group.get(1)).target_y += 18.0F * Settings.scale;
/*  350 */         ((AbstractCard)this.group.get(3)).target_y -= 6.0F * Settings.scale;
/*  351 */         ((AbstractCard)this.group.get(5)).target_y += 18.0F * Settings.scale;
/*  352 */         ((AbstractCard)this.group.get(6)).target_y += 25.0F * Settings.scale;
/*      */         break;
/*      */       case 8:
/*  355 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 2.5F;
/*  356 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.82F;
/*  357 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.1F;
/*  358 */         ((AbstractCard)this.group.get(3)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.38F;
/*  359 */         ((AbstractCard)this.group.get(4)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.38F;
/*  360 */         ((AbstractCard)this.group.get(5)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.1F;
/*  361 */         ((AbstractCard)this.group.get(6)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.77F;
/*  362 */         ((AbstractCard)this.group.get(7)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 2.5F;
/*  363 */         ((AbstractCard)this.group.get(1)).target_y += 10.0F * Settings.scale;
/*  364 */         ((AbstractCard)this.group.get(6)).target_y += 10.0F * Settings.scale;
/*      */         
/*  366 */         for (AbstractCard c : this.group) {
/*  367 */           c.targetDrawScale = 0.7125F;
/*      */         }
/*      */         break;
/*      */       case 9:
/*  371 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 2.8F;
/*  372 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 2.2F;
/*  373 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.53F;
/*  374 */         ((AbstractCard)this.group.get(3)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.8F;
/*  375 */         ((AbstractCard)this.group.get(4)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.0F;
/*  376 */         ((AbstractCard)this.group.get(5)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.8F;
/*  377 */         ((AbstractCard)this.group.get(6)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.53F;
/*  378 */         ((AbstractCard)this.group.get(7)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 2.2F;
/*  379 */         ((AbstractCard)this.group.get(8)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 2.8F;
/*  380 */         ((AbstractCard)this.group.get(1)).target_y += 22.0F * Settings.scale;
/*  381 */         ((AbstractCard)this.group.get(2)).target_y += 18.0F * Settings.scale;
/*  382 */         ((AbstractCard)this.group.get(3)).target_y += 12.0F * Settings.scale;
/*  383 */         ((AbstractCard)this.group.get(5)).target_y += 12.0F * Settings.scale;
/*  384 */         ((AbstractCard)this.group.get(6)).target_y += 18.0F * Settings.scale;
/*  385 */         ((AbstractCard)this.group.get(7)).target_y += 22.0F * Settings.scale;
/*      */         
/*  387 */         for (AbstractCard c : this.group) {
/*  388 */           c.targetDrawScale = 0.67499995F;
/*      */         }
/*      */         break;
/*      */       case 10:
/*  392 */         ((AbstractCard)this.group.get(0)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 2.9F;
/*  393 */         ((AbstractCard)this.group.get(1)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 2.4F;
/*  394 */         ((AbstractCard)this.group.get(2)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.8F;
/*  395 */         ((AbstractCard)this.group.get(3)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 1.1F;
/*  396 */         ((AbstractCard)this.group.get(4)).target_x = Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH_S * 0.4F;
/*  397 */         ((AbstractCard)this.group.get(5)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 0.4F;
/*  398 */         ((AbstractCard)this.group.get(6)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.1F;
/*  399 */         ((AbstractCard)this.group.get(7)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 1.8F;
/*  400 */         ((AbstractCard)this.group.get(8)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 2.4F;
/*  401 */         ((AbstractCard)this.group.get(9)).target_x = Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH_S * 2.9F;
/*  402 */         ((AbstractCard)this.group.get(1)).target_y += 20.0F * Settings.scale;
/*  403 */         ((AbstractCard)this.group.get(2)).target_y += 17.0F * Settings.scale;
/*  404 */         ((AbstractCard)this.group.get(3)).target_y += 12.0F * Settings.scale;
/*  405 */         ((AbstractCard)this.group.get(4)).target_y += 5.0F * Settings.scale;
/*  406 */         ((AbstractCard)this.group.get(5)).target_y += 5.0F * Settings.scale;
/*  407 */         ((AbstractCard)this.group.get(6)).target_y += 12.0F * Settings.scale;
/*  408 */         ((AbstractCard)this.group.get(7)).target_y += 17.0F * Settings.scale;
/*  409 */         ((AbstractCard)this.group.get(8)).target_y += 20.0F * Settings.scale;
/*      */         
/*  411 */         for (AbstractCard c : this.group) {
/*  412 */           c.targetDrawScale = 0.63750005F;
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/*  418 */         logger.info("WTF MATE, why so many cards");
/*      */         break;
/*      */     } 
/*      */     
/*  422 */     AbstractCard card = AbstractDungeon.player.hoveredCard;
/*  423 */     if (card != null) {
/*  424 */       card.setAngle(0.0F);
/*  425 */       card.target_x = (card.current_x + card.target_x) / 2.0F;
/*  426 */       card.target_y = card.current_y;
/*      */     } 
/*      */     
/*  429 */     for (CardQueueItem q : AbstractDungeon.actionManager.cardQueue) {
/*  430 */       if (q.card != null) {
/*  431 */         q.card.setAngle(0.0F);
/*  432 */         q.card.target_x = q.card.current_x;
/*  433 */         q.card.target_y = q.card.current_y;
/*      */       } 
/*      */     } 
/*      */     
/*  437 */     glowCheck();
/*      */   }
/*      */   
/*      */   public void glowCheck() {
/*  441 */     for (AbstractCard c : this.group) {
/*  442 */       if (c.canUse(AbstractDungeon.player, null) && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.HAND_SELECT) {
/*  443 */         c.beginGlowing();
/*      */       } else {
/*  445 */         c.stopGlowing();
/*      */       } 
/*  447 */       c.triggerOnGlowCheck();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void stopGlowing() {
/*  452 */     for (AbstractCard c : this.group) {
/*  453 */       c.stopGlowing();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void hoverCardPush(AbstractCard c) {
/*  463 */     if (this.group.size() > 1) {
/*  464 */       int cardNum = -1;
/*      */       
/*  466 */       for (int i = 0; i < this.group.size(); i++) {
/*  467 */         if (c.equals(this.group.get(i))) {
/*  468 */           cardNum = i;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  473 */       float pushAmt = 0.4F;
/*  474 */       if (this.group.size() == 2) {
/*  475 */         pushAmt = 0.2F;
/*  476 */       } else if (this.group.size() == 3 || this.group.size() == 4) {
/*  477 */         pushAmt = 0.27F;
/*      */       } 
/*      */       
/*  480 */       int currentSlot = cardNum + 1;
/*      */ 
/*      */       
/*  483 */       while (currentSlot < this.group.size()) {
/*  484 */         ((AbstractCard)this.group.get(currentSlot)).target_x += AbstractCard.IMG_WIDTH_S * pushAmt;
/*  485 */         pushAmt *= 0.25F;
/*  486 */         currentSlot++;
/*      */       } 
/*      */       
/*  489 */       pushAmt = 0.4F;
/*      */       
/*  491 */       if (this.group.size() == 2) {
/*  492 */         pushAmt = 0.2F;
/*  493 */       } else if (this.group.size() == 3 || this.group.size() == 4) {
/*  494 */         pushAmt = 0.27F;
/*      */       } 
/*      */       
/*  497 */       currentSlot = cardNum - 1;
/*      */ 
/*      */       
/*  500 */       while (currentSlot > -1 && currentSlot < this.group.size()) {
/*  501 */         ((AbstractCard)this.group.get(currentSlot)).target_x -= AbstractCard.IMG_WIDTH_S * pushAmt;
/*  502 */         pushAmt *= 0.25F;
/*  503 */         currentSlot--;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addToTop(AbstractCard c) {
/*  509 */     this.group.add(c);
/*      */   }
/*      */   
/*      */   public void addToBottom(AbstractCard c) {
/*  513 */     this.group.add(0, c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToRandomSpot(AbstractCard c) {
/*  520 */     if (this.group.size() == 0) {
/*  521 */       this.group.add(c);
/*      */     } else {
/*  523 */       this.group.add(AbstractDungeon.cardRandomRng.random(this.group.size() - 1), c);
/*      */     } 
/*      */   }
/*      */   
/*      */   public AbstractCard getTopCard() {
/*  528 */     return this.group.get(this.group.size() - 1);
/*      */   }
/*      */   
/*      */   public AbstractCard getNCardFromTop(int num) {
/*  532 */     return this.group.get(this.group.size() - 1 - num);
/*      */   }
/*      */   
/*      */   public AbstractCard getBottomCard() {
/*  536 */     return this.group.get(0);
/*      */   }
/*      */   
/*      */   public AbstractCard getHoveredCard() {
/*  540 */     for (AbstractCard c : this.group) {
/*  541 */       if (c.isHoveredInHand(0.7F)) {
/*  542 */         boolean success = true;
/*  543 */         for (CardQueueItem q : AbstractDungeon.actionManager.cardQueue) {
/*  544 */           if (q.card == c) {
/*  545 */             success = false;
/*      */             break;
/*      */           } 
/*      */         } 
/*  549 */         if (success) {
/*  550 */           return c;
/*      */         }
/*      */       } 
/*      */     } 
/*  554 */     return null;
/*      */   }
/*      */   
/*      */   public AbstractCard getRandomCard(Random rng) {
/*  558 */     return this.group.get(rng.random(this.group.size() - 1));
/*      */   }
/*      */   
/*      */   public AbstractCard getRandomCard(boolean useRng) {
/*  562 */     if (useRng) {
/*  563 */       return this.group.get(AbstractDungeon.cardRng.random(this.group.size() - 1));
/*      */     }
/*  565 */     return this.group.get(MathUtils.random(this.group.size() - 1));
/*      */   }
/*      */ 
/*      */   
/*      */   public AbstractCard getRandomCard(boolean useRng, AbstractCard.CardRarity rarity) {
/*  570 */     ArrayList<AbstractCard> tmp = new ArrayList<>();
/*  571 */     for (AbstractCard c : this.group) {
/*  572 */       if (c.rarity == rarity) {
/*  573 */         tmp.add(c);
/*      */       }
/*      */     } 
/*      */     
/*  577 */     if (tmp.isEmpty()) {
/*  578 */       logger.info("ERROR: No cards left for type: " + this.type.name());
/*  579 */       return null;
/*      */     } 
/*      */     
/*  582 */     Collections.sort(tmp);
/*  583 */     if (useRng) {
/*  584 */       return tmp.get(AbstractDungeon.cardRng.random(tmp.size() - 1));
/*      */     }
/*  586 */     return tmp.get(MathUtils.random(tmp.size() - 1));
/*      */   }
/*      */ 
/*      */   
/*      */   public AbstractCard getRandomCard(Random rng, AbstractCard.CardRarity rarity) {
/*  591 */     ArrayList<AbstractCard> tmp = new ArrayList<>();
/*  592 */     for (AbstractCard c : this.group) {
/*  593 */       if (c.rarity == rarity) {
/*  594 */         tmp.add(c);
/*      */       }
/*      */     } 
/*      */     
/*  598 */     if (tmp.isEmpty()) {
/*  599 */       logger.info("ERROR: No cards left for type: " + this.type.name());
/*  600 */       return null;
/*      */     } 
/*      */     
/*  603 */     Collections.sort(tmp);
/*  604 */     return tmp.get(rng.random(tmp.size() - 1));
/*      */   }
/*      */   
/*      */   public AbstractCard getRandomCard(AbstractCard.CardType type, boolean useRng) {
/*  608 */     ArrayList<AbstractCard> tmp = new ArrayList<>();
/*  609 */     for (AbstractCard c : this.group) {
/*  610 */       if (c.type == type) {
/*  611 */         tmp.add(c);
/*      */       }
/*      */     } 
/*      */     
/*  615 */     if (tmp.isEmpty()) {
/*  616 */       logger.info("ERROR: No cards left for type: " + type.name());
/*  617 */       return null;
/*      */     } 
/*      */     
/*  620 */     Collections.sort(tmp);
/*  621 */     if (useRng) {
/*  622 */       return tmp.get(AbstractDungeon.cardRng.random(tmp.size() - 1));
/*      */     }
/*  624 */     return tmp.get(MathUtils.random(tmp.size() - 1));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeTopCard() {
/*  630 */     this.group.remove(this.group.size() - 1);
/*      */   }
/*      */   
/*      */   public void shuffle() {
/*  634 */     Collections.shuffle(this.group, new java.util.Random(AbstractDungeon.shuffleRng.randomLong()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void shuffle(Random rng) {
/*  643 */     Collections.shuffle(this.group, new java.util.Random(rng.randomLong()));
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  648 */     StringBuilder sb = new StringBuilder("");
/*  649 */     for (AbstractCard c : this.group) {
/*  650 */       sb.append(c.cardID);
/*  651 */       sb.append("\n");
/*      */     } 
/*  653 */     return sb.toString();
/*      */   }
/*      */   
/*      */   public void update() {
/*  657 */     for (AbstractCard c : this.group) {
/*  658 */       c.update();
/*      */     }
/*      */   }
/*      */   
/*      */   public void updateHoverLogic() {
/*  663 */     for (AbstractCard c : this.group) {
/*  664 */       c.updateHoverLogic();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(SpriteBatch sb) {
/*  674 */     for (AbstractCard c : this.group) {
/*  675 */       c.render(sb);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderShowBottled(SpriteBatch sb) {
/*  685 */     for (AbstractCard c : this.group) {
/*  686 */       c.render(sb);
/*  687 */       if (c.inBottleFlame) {
/*  688 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Flame");
/*  689 */         float prevX = tmp.currentX;
/*  690 */         float prevY = tmp.currentY;
/*  691 */         tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  692 */         tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  693 */         tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  694 */         tmp.render(sb);
/*  695 */         tmp.currentX = prevX;
/*  696 */         tmp.currentY = prevY;
/*  697 */         tmp = null; continue;
/*  698 */       }  if (c.inBottleLightning) {
/*  699 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Lightning");
/*  700 */         float prevX = tmp.currentX;
/*  701 */         float prevY = tmp.currentY;
/*  702 */         tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  703 */         tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  704 */         tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  705 */         tmp.render(sb);
/*  706 */         tmp.currentX = prevX;
/*  707 */         tmp.currentY = prevY;
/*  708 */         tmp = null; continue;
/*  709 */       }  if (c.inBottleTornado) {
/*  710 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Tornado");
/*  711 */         float prevX = tmp.currentX;
/*  712 */         float prevY = tmp.currentY;
/*  713 */         tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  714 */         tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  715 */         tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  716 */         tmp.render(sb);
/*  717 */         tmp.currentX = prevX;
/*  718 */         tmp.currentY = prevY;
/*  719 */         tmp = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderMasterDeck(SpriteBatch sb) {
/*  730 */     for (AbstractCard c : this.group) {
/*  731 */       c.render(sb);
/*  732 */       if (c.inBottleFlame) {
/*  733 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Flame");
/*  734 */         float prevX = tmp.currentX;
/*  735 */         float prevY = tmp.currentY;
/*  736 */         tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  737 */         tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  738 */         tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  739 */         tmp.render(sb);
/*  740 */         tmp.currentX = prevX;
/*  741 */         tmp.currentY = prevY;
/*  742 */         tmp = null; continue;
/*  743 */       }  if (c.inBottleLightning) {
/*  744 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Lightning");
/*  745 */         float prevX = tmp.currentX;
/*  746 */         float prevY = tmp.currentY;
/*  747 */         tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  748 */         tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  749 */         tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  750 */         tmp.render(sb);
/*  751 */         tmp.currentX = prevX;
/*  752 */         tmp.currentY = prevY;
/*  753 */         tmp = null; continue;
/*  754 */       }  if (c.inBottleTornado) {
/*  755 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Tornado");
/*  756 */         float prevX = tmp.currentX;
/*  757 */         float prevY = tmp.currentY;
/*  758 */         tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  759 */         tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  760 */         tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  761 */         tmp.render(sb);
/*  762 */         tmp.currentX = prevX;
/*  763 */         tmp.currentY = prevY;
/*  764 */         tmp = null;
/*      */       } 
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
/*      */   public void renderExceptOneCard(SpriteBatch sb, AbstractCard card) {
/*  777 */     for (AbstractCard c : this.group) {
/*  778 */       if (!c.equals(card)) {
/*  779 */         c.render(sb);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void renderExceptOneCardShowBottled(SpriteBatch sb, AbstractCard card) {
/*  785 */     for (AbstractCard c : this.group) {
/*  786 */       if (!c.equals(card)) {
/*  787 */         c.render(sb);
/*  788 */         if (c.inBottleFlame) {
/*  789 */           AbstractRelic tmp = RelicLibrary.getRelic("Bottled Flame");
/*  790 */           float prevX = tmp.currentX;
/*  791 */           float prevY = tmp.currentY;
/*  792 */           tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  793 */           tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  794 */           tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  795 */           tmp.render(sb);
/*  796 */           tmp.currentX = prevX;
/*  797 */           tmp.currentY = prevY;
/*  798 */           tmp = null; continue;
/*  799 */         }  if (c.inBottleLightning) {
/*  800 */           AbstractRelic tmp = RelicLibrary.getRelic("Bottled Lightning");
/*  801 */           float prevX = tmp.currentX;
/*  802 */           float prevY = tmp.currentY;
/*  803 */           tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  804 */           tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  805 */           tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  806 */           tmp.render(sb);
/*  807 */           tmp.currentX = prevX;
/*  808 */           tmp.currentY = prevY;
/*  809 */           tmp = null; continue;
/*  810 */         }  if (c.inBottleTornado) {
/*  811 */           AbstractRelic tmp = RelicLibrary.getRelic("Bottled Tornado");
/*  812 */           float prevX = tmp.currentX;
/*  813 */           float prevY = tmp.currentY;
/*  814 */           tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  815 */           tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  816 */           tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  817 */           tmp.render(sb);
/*  818 */           tmp.currentX = prevX;
/*  819 */           tmp.currentY = prevY;
/*  820 */           tmp = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void renderMasterDeckExceptOneCard(SpriteBatch sb, AbstractCard card) {
/*  827 */     for (AbstractCard c : this.group) {
/*  828 */       if (!c.equals(card)) {
/*  829 */         c.render(sb);
/*  830 */         if (c.inBottleFlame) {
/*  831 */           AbstractRelic tmp = RelicLibrary.getRelic("Bottled Flame");
/*  832 */           float prevX = tmp.currentX;
/*  833 */           float prevY = tmp.currentY;
/*  834 */           tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  835 */           tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  836 */           tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  837 */           tmp.render(sb);
/*  838 */           tmp.currentX = prevX;
/*  839 */           tmp.currentY = prevY;
/*  840 */           tmp = null; continue;
/*  841 */         }  if (c.inBottleLightning) {
/*  842 */           AbstractRelic tmp = RelicLibrary.getRelic("Bottled Lightning");
/*  843 */           float prevX = tmp.currentX;
/*  844 */           float prevY = tmp.currentY;
/*  845 */           tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  846 */           tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  847 */           tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  848 */           tmp.render(sb);
/*  849 */           tmp.currentX = prevX;
/*  850 */           tmp.currentY = prevY;
/*  851 */           tmp = null; continue;
/*  852 */         }  if (c.inBottleTornado) {
/*  853 */           AbstractRelic tmp = RelicLibrary.getRelic("Bottled Tornado");
/*  854 */           float prevX = tmp.currentX;
/*  855 */           float prevY = tmp.currentY;
/*  856 */           tmp.currentX = c.current_x + 390.0F * c.drawScale / 3.0F * Settings.scale;
/*  857 */           tmp.currentY = c.current_y + 546.0F * c.drawScale / 3.0F * Settings.scale;
/*  858 */           tmp.scale = c.drawScale * Settings.scale * 1.5F;
/*  859 */           tmp.render(sb);
/*  860 */           tmp.currentX = prevX;
/*  861 */           tmp.currentY = prevY;
/*  862 */           tmp = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void renderHand(SpriteBatch sb, AbstractCard exceptThis) {
/*  869 */     for (AbstractCard c : this.group) {
/*  870 */       if (c != exceptThis) {
/*  871 */         boolean inQueue = false;
/*  872 */         for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
/*  873 */           if (i.card != null && i.card.equals(c)) {
/*  874 */             this.queued.add(c);
/*  875 */             inQueue = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*  879 */         if (!inQueue) {
/*  880 */           this.inHand.add(c);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  885 */     for (AbstractCard c : this.inHand) {
/*  886 */       c.render(sb);
/*      */     }
/*      */     
/*  889 */     for (AbstractCard c : this.queued) {
/*  890 */       c.render(sb);
/*      */     }
/*  892 */     this.inHand.clear();
/*  893 */     this.queued.clear();
/*      */   }
/*      */   
/*      */   public void renderInLibrary(SpriteBatch sb) {
/*  897 */     for (AbstractCard c : this.group) {
/*  898 */       c.renderInLibrary(sb);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderTip(SpriteBatch sb) {
/*  908 */     for (AbstractCard c : this.group) {
/*  909 */       c.renderCardTip(sb);
/*      */     }
/*      */   }
/*      */   
/*      */   public void renderWithSelections(SpriteBatch sb) {
/*  914 */     for (AbstractCard c : this.group) {
/*  915 */       c.renderWithSelections(sb);
/*      */     }
/*      */   }
/*      */   
/*      */   public void renderDiscardPile(SpriteBatch sb) {
/*  920 */     for (AbstractCard c : this.group) {
/*  921 */       c.render(sb);
/*      */     }
/*      */   }
/*      */   
/*      */   public void moveToDiscardPile(AbstractCard c) {
/*  926 */     resetCardBeforeMoving(c);
/*  927 */     c.shrink();
/*  928 */     c.darken(false);
/*  929 */     (AbstractDungeon.getCurrRoom()).souls.discard(c);
/*  930 */     AbstractDungeon.player.onCardDrawOrDiscard();
/*      */   }
/*      */   
/*      */   public void empower(AbstractCard c) {
/*  934 */     resetCardBeforeMoving(c);
/*  935 */     c.shrink();
/*  936 */     (AbstractDungeon.getCurrRoom()).souls.empower(c);
/*      */   }
/*      */   
/*      */   public void moveToExhaustPile(AbstractCard c) {
/*  940 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  941 */       r.onExhaust(c);
/*      */     }
/*  943 */     for (AbstractPower p : AbstractDungeon.player.powers) {
/*  944 */       p.onExhaust(c);
/*      */     }
/*  946 */     c.triggerOnExhaust();
/*  947 */     resetCardBeforeMoving(c);
/*  948 */     AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
/*  949 */     AbstractDungeon.player.exhaustPile.addToTop(c);
/*  950 */     AbstractDungeon.player.onCardDrawOrDiscard();
/*      */   }
/*      */   
/*      */   public void moveToHand(AbstractCard c, CardGroup group) {
/*  954 */     c.unhover();
/*  955 */     c.lighten(true);
/*  956 */     c.setAngle(0.0F);
/*  957 */     c.drawScale = 0.12F;
/*  958 */     c.targetDrawScale = 0.75F;
/*  959 */     c.current_x = DRAW_PILE_X;
/*  960 */     c.current_y = DRAW_PILE_Y;
/*  961 */     group.removeCard(c);
/*  962 */     AbstractDungeon.player.hand.addToTop(c);
/*  963 */     AbstractDungeon.player.hand.refreshHandLayout();
/*  964 */     AbstractDungeon.player.hand.applyPowers();
/*      */   }
/*      */   
/*      */   public void moveToHand(AbstractCard c) {
/*  968 */     resetCardBeforeMoving(c);
/*  969 */     c.unhover();
/*  970 */     c.lighten(true);
/*  971 */     c.setAngle(0.0F);
/*  972 */     c.drawScale = 0.12F;
/*  973 */     c.targetDrawScale = 0.75F;
/*  974 */     c.current_x = DRAW_PILE_X;
/*  975 */     c.current_y = DRAW_PILE_Y;
/*  976 */     AbstractDungeon.player.hand.addToTop(c);
/*  977 */     AbstractDungeon.player.hand.refreshHandLayout();
/*  978 */     AbstractDungeon.player.hand.applyPowers();
/*      */   }
/*      */   
/*      */   public void moveToDeck(AbstractCard c, boolean randomSpot) {
/*  982 */     resetCardBeforeMoving(c);
/*  983 */     c.shrink();
/*  984 */     (AbstractDungeon.getCurrRoom()).souls.onToDeck(c, randomSpot);
/*      */   }
/*      */   
/*      */   public void moveToBottomOfDeck(AbstractCard c) {
/*  988 */     resetCardBeforeMoving(c);
/*  989 */     c.shrink();
/*  990 */     (AbstractDungeon.getCurrRoom()).souls.onToBottomOfDeck(c);
/*      */   }
/*      */   
/*      */   private void resetCardBeforeMoving(AbstractCard c) {
/*  994 */     if (AbstractDungeon.player.hoveredCard == c) {
/*  995 */       AbstractDungeon.player.releaseCard();
/*      */     }
/*  997 */     AbstractDungeon.actionManager.removeFromQueue(c);
/*  998 */     c.unhover();
/*  999 */     c.untip();
/* 1000 */     c.stopGlowing();
/* 1001 */     this.group.remove(c);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1005 */     return this.group.isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void discardAll(CardGroup discardPile) {
/* 1015 */     for (AbstractCard c : this.group) {
/* 1016 */       c.target_x = DISCARD_PILE_X;
/* 1017 */       c.target_y = 0.0F;
/* 1018 */       discardPile.addToTop(c);
/*      */     } 
/* 1020 */     this.group.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initializeDeck(CardGroup masterDeck) {
/* 1029 */     clear();
/* 1030 */     CardGroup copy = new CardGroup(masterDeck, CardGroupType.DRAW_PILE);
/* 1031 */     copy.shuffle(AbstractDungeon.shuffleRng);
/*      */     
/* 1033 */     ArrayList<AbstractCard> placeOnTop = new ArrayList<>();
/*      */     
/* 1035 */     for (AbstractCard c : copy.group) {
/* 1036 */       if (c.isInnate) {
/* 1037 */         placeOnTop.add(c);
/*      */         continue;
/*      */       } 
/* 1040 */       if (c.inBottleFlame || c.inBottleLightning || c.inBottleTornado) {
/* 1041 */         placeOnTop.add(c);
/*      */         
/*      */         continue;
/*      */       } 
/* 1045 */       c.target_x = DRAW_PILE_X;
/* 1046 */       c.target_y = DRAW_PILE_Y;
/* 1047 */       c.current_x = DRAW_PILE_X;
/* 1048 */       c.current_y = DRAW_PILE_Y;
/* 1049 */       addToTop(c);
/*      */     } 
/*      */     
/* 1052 */     for (AbstractCard c : placeOnTop) {
/* 1053 */       addToTop(c);
/*      */     }
/*      */ 
/*      */     
/* 1057 */     if (placeOnTop.size() > AbstractDungeon.player.masterHandSize) {
/* 1058 */       AbstractDungeon.actionManager.addToTurnStart((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, placeOnTop
/* 1059 */             .size() - AbstractDungeon.player.masterHandSize));
/*      */     }
/*      */     
/* 1062 */     placeOnTop.clear();
/*      */   }
/*      */   
/*      */   public int size() {
/* 1066 */     return this.group.size();
/*      */   }
/*      */   
/*      */   public CardGroup getUpgradableCards() {
/* 1070 */     CardGroup retVal = new CardGroup(CardGroupType.UNSPECIFIED);
/* 1071 */     for (AbstractCard c : this.group) {
/* 1072 */       if (c.canUpgrade()) {
/* 1073 */         retVal.group.add(c);
/*      */       }
/*      */     } 
/* 1076 */     return retVal;
/*      */   }
/*      */   
/*      */   public Boolean hasUpgradableCards() {
/* 1080 */     for (AbstractCard c : this.group) {
/* 1081 */       if (c.canUpgrade()) {
/* 1082 */         return Boolean.valueOf(true);
/*      */       }
/*      */     } 
/* 1085 */     return Boolean.valueOf(false);
/*      */   }
/*      */   
/*      */   public CardGroup getPurgeableCards() {
/* 1089 */     CardGroup retVal = new CardGroup(CardGroupType.UNSPECIFIED);
/* 1090 */     for (AbstractCard c : this.group) {
/* 1091 */       if (!c.cardID.equals("Necronomicurse") && !c.cardID.equals("CurseOfTheBell") && !c.cardID.equals("AscendersBane"))
/*      */       {
/* 1093 */         retVal.group.add(c);
/*      */       }
/*      */     } 
/* 1096 */     return retVal;
/*      */   }
/*      */   
/*      */   public AbstractCard getSpecificCard(AbstractCard targetCard) {
/* 1100 */     if (this.group.contains(targetCard)) {
/* 1101 */       return targetCard;
/*      */     }
/* 1103 */     return null;
/*      */   }
/*      */   
/*      */   public enum CardGroupType
/*      */   {
/* 1108 */     DRAW_PILE, MASTER_DECK, HAND, DISCARD_PILE, EXHAUST_PILE, CARD_POOL, UNSPECIFIED;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void triggerOnOtherCardPlayed(AbstractCard usedCard) {
/* 1117 */     for (AbstractCard c : this.group) {
/* 1118 */       if (c != usedCard) {
/* 1119 */         c.triggerOnOtherCardPlayed(usedCard);
/*      */       }
/*      */     } 
/* 1122 */     for (AbstractPower p : AbstractDungeon.player.powers)
/* 1123 */       p.onAfterCardPlayed(usedCard); 
/*      */   }
/*      */   
/*      */   private class CardRarityComparator
/*      */     implements Comparator<AbstractCard>
/*      */   {
/*      */     private CardRarityComparator() {}
/*      */     
/*      */     public int compare(AbstractCard c1, AbstractCard c2) {
/* 1132 */       return c1.rarity.compareTo(c2.rarity);
/*      */     } }
/*      */   
/*      */   private class StatusCardsLastComparator implements Comparator<AbstractCard> {
/*      */     private StatusCardsLastComparator() {}
/*      */     
/*      */     public int compare(AbstractCard c1, AbstractCard c2) {
/* 1139 */       if (c1.type == AbstractCard.CardType.STATUS && c2.type != AbstractCard.CardType.STATUS)
/* 1140 */         return 1; 
/* 1141 */       if (c1.type != AbstractCard.CardType.STATUS && c2.type == AbstractCard.CardType.STATUS) {
/* 1142 */         return -1;
/*      */       }
/* 1144 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */   private class CardTypeComparator implements Comparator<AbstractCard> {
/*      */     private CardTypeComparator() {}
/*      */     
/*      */     public int compare(AbstractCard c1, AbstractCard c2) {
/* 1152 */       return c1.type.compareTo(c2.type);
/*      */     } }
/*      */   
/*      */   private class CardLockednessComparator implements Comparator<AbstractCard> {
/*      */     private CardLockednessComparator() {}
/*      */     
/*      */     public int compare(AbstractCard c1, AbstractCard c2) {
/* 1159 */       int c1Rank = 0;
/* 1160 */       if (UnlockTracker.isCardLocked(c1.cardID)) {
/* 1161 */         c1Rank = 2;
/* 1162 */       } else if (!UnlockTracker.isCardSeen(c1.cardID)) {
/* 1163 */         c1Rank = 1;
/*      */       } 
/*      */       
/* 1166 */       int c2Rank = 0;
/* 1167 */       if (UnlockTracker.isCardLocked(c2.cardID)) {
/* 1168 */         c2Rank = 2;
/* 1169 */       } else if (!UnlockTracker.isCardSeen(c2.cardID)) {
/* 1170 */         c2Rank = 1;
/*      */       } 
/*      */       
/* 1173 */       return c1Rank - c2Rank;
/*      */     }
/*      */   }
/*      */   
/*      */   private class CardNameComparator implements Comparator<AbstractCard> { private CardNameComparator() {}
/*      */     
/*      */     public int compare(AbstractCard c1, AbstractCard c2) {
/* 1180 */       return c1.name.compareTo(c2.name);
/*      */     } }
/*      */   
/*      */   private class CardCostComparator implements Comparator<AbstractCard> {
/*      */     private CardCostComparator() {}
/*      */     
/*      */     public int compare(AbstractCard c1, AbstractCard c2) {
/* 1187 */       return c1.cost - c2.cost;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void sortWithComparator(Comparator<AbstractCard> comp, boolean ascending) {
/* 1198 */     if (ascending) {
/* 1199 */       this.group.sort(comp);
/*      */     } else {
/* 1201 */       this.group.sort(Collections.reverseOrder(comp));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sortByRarity(boolean ascending) {
/* 1209 */     sortWithComparator(new CardRarityComparator(), ascending);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sortByRarityPlusStatusCardType(boolean ascending) {
/* 1216 */     sortWithComparator(new CardRarityComparator(), ascending);
/* 1217 */     sortWithComparator(new StatusCardsLastComparator(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sortByType(boolean ascending) {
/* 1224 */     sortWithComparator(new CardTypeComparator(), ascending);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sortByAcquisition() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sortByStatus(boolean ascending) {
/* 1237 */     sortWithComparator(new CardLockednessComparator(), ascending);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sortAlphabetically(boolean ascending) {
/* 1246 */     sortWithComparator(new CardNameComparator(), ascending);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sortByCost(boolean ascending) {
/* 1255 */     sortWithComparator(new CardCostComparator(), ascending);
/*      */   }
/*      */   
/*      */   public CardGroup getSkills() {
/* 1259 */     return getCardsOfType(AbstractCard.CardType.SKILL);
/*      */   }
/*      */   
/*      */   public CardGroup getAttacks() {
/* 1263 */     return getCardsOfType(AbstractCard.CardType.ATTACK);
/*      */   }
/*      */   
/*      */   public CardGroup getPowers() {
/* 1267 */     return getCardsOfType(AbstractCard.CardType.POWER);
/*      */   }
/*      */   
/*      */   public CardGroup getCardsOfType(AbstractCard.CardType cardType) {
/* 1271 */     CardGroup retVal = new CardGroup(CardGroupType.UNSPECIFIED);
/* 1272 */     for (AbstractCard card : this.group) {
/* 1273 */       if (card.type == cardType) {
/* 1274 */         retVal.addToBottom(card);
/*      */       }
/*      */     } 
/*      */     
/* 1278 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CardGroup getGroupedByColor() {
/* 1287 */     ArrayList<CardGroup> colorGroups = new ArrayList<>();
/*      */     
/* 1289 */     for (AbstractCard.CardColor color : AbstractCard.CardColor.values()) {
/* 1290 */       colorGroups.add(new CardGroup(CardGroupType.UNSPECIFIED));
/*      */     }
/* 1292 */     for (AbstractCard card : this.group) {
/* 1293 */       ((CardGroup)colorGroups.get(card.color.ordinal())).addToTop(card);
/*      */     }
/*      */     
/* 1296 */     CardGroup retVal = new CardGroup(CardGroupType.UNSPECIFIED);
/* 1297 */     for (CardGroup group : colorGroups) {
/* 1298 */       retVal.group.addAll(group.group);
/*      */     }
/* 1300 */     return retVal;
/*      */   }
/*      */   
/*      */   public AbstractCard findCardById(String id) {
/* 1304 */     for (AbstractCard c : this.group) {
/* 1305 */       if (c.cardID.equals(id)) {
/* 1306 */         return c;
/*      */       }
/*      */     } 
/* 1309 */     return null;
/*      */   }
/*      */   
/*      */   public static CardGroup getGroupWithoutBottledCards(CardGroup group) {
/* 1313 */     CardGroup retVal = new CardGroup(CardGroupType.UNSPECIFIED);
/* 1314 */     for (AbstractCard c : group.group) {
/* 1315 */       if (!c.inBottleFlame && !c.inBottleLightning && !c.inBottleTornado) {
/* 1316 */         retVal.addToTop(c);
/*      */       }
/*      */     } 
/* 1319 */     return retVal;
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\CardGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */