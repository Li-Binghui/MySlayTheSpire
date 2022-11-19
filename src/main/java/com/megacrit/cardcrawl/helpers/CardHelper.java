/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CardHelper
/*     */ {
/*  20 */   private static final Logger logger = LogManager.getLogger(CardHelper.class.getName());
/*     */   
/*     */   public static final int COMMON_CARD_LIMIT = 3;
/*     */   public static final int UNCOMMON_CARD_LIMIT = 2;
/*  24 */   public static HashMap<String, Integer> obtainedCards = new HashMap<>();
/*  25 */   public static ArrayList<CardInfo> removedCards = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void obtain(String key, AbstractCard.CardRarity rarity, AbstractCard.CardColor color) {
/*  32 */     if (rarity == AbstractCard.CardRarity.SPECIAL || rarity == AbstractCard.CardRarity.BASIC || rarity == AbstractCard.CardRarity.CURSE) {
/*  33 */       logger.info("No need to track rarity type: " + rarity.name());
/*     */       
/*     */       return;
/*     */     } 
/*  37 */     if (obtainedCards.containsKey(key)) {
/*  38 */       int tmp = ((Integer)obtainedCards.get(key)).intValue() + 1;
/*  39 */       obtainedCards.put(key, Integer.valueOf(tmp));
/*  40 */       logger.info("Obtained " + key + " (" + rarity.name() + "). You have " + tmp + " now");
/*     */     } else {
/*  42 */       obtainedCards.put(key, Integer.valueOf(1));
/*  43 */       logger.info("Obtained " + key + " (" + rarity.name() + "). Creating new map entry.");
/*     */     } 
/*     */     
/*  46 */     UnlockTracker.markCardAsSeen(key);
/*     */   }
/*     */   
/*     */   public static void clear() {
/*  50 */     logger.info("Clearing CardHelper (obtained cards)");
/*  51 */     removedCards.clear();
/*  52 */     obtainedCards.clear();
/*     */   }
/*     */   
/*     */   public static Color getColor(int r, int g, int b) {
/*  56 */     return new Color(r / 255.0F, g / 255.0F, b / 255.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static Color getColor(float r, float g, float b) {
/*  60 */     return new Color(r / 255.0F, g / 255.0F, b / 255.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static class CardInfo {
/*     */     String id;
/*     */     String name;
/*     */     AbstractCard.CardRarity rarity;
/*     */     AbstractCard.CardColor color;
/*     */     
/*     */     public CardInfo(String id, String name, AbstractCard.CardRarity rarity, AbstractCard.CardColor color) {
/*  70 */       this.id = id;
/*  71 */       this.name = name;
/*  72 */       this.rarity = rarity;
/*  73 */       this.color = color;
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean hasCardWithXDamage(int damage) {
/*  78 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/*  79 */       if (c.type == AbstractCard.CardType.ATTACK && c.baseDamage >= 10) {
/*  80 */         logger.info(c.name + " is 10 Attack!");
/*  81 */         return true;
/*     */       } 
/*     */     } 
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasCardWithID(String targetID) {
/*  88 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/*  89 */       if (c.cardID.equals(targetID)) {
/*  90 */         return true;
/*     */       }
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasCardType(AbstractCard.CardType hasType) {
/*  97 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/*  98 */       if (c.type == hasType) {
/*  99 */         return true;
/*     */       }
/*     */     } 
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasCardWithType(AbstractCard.CardType type) {
/* 106 */     for (AbstractCard c : (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck)).group) {
/* 107 */       if (c.type == type) {
/* 108 */         return true;
/*     */       }
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public static AbstractCard returnCardOfType(AbstractCard.CardType type, Random rng) {
/* 115 */     ArrayList<AbstractCard> cards = new ArrayList<>();
/*     */     
/* 117 */     for (AbstractCard c : (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck)).group) {
/* 118 */       if (c.type == type) {
/* 119 */         cards.add(c);
/*     */       }
/*     */     } 
/*     */     
/* 123 */     return cards.remove(rng.random(cards.size() - 1));
/*     */   }
/*     */   
/*     */   public static boolean hasUpgradedCard() {
/* 127 */     for (AbstractCard c : (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck)).group) {
/* 128 */       if (c.upgraded == true) {
/* 129 */         return true;
/*     */       }
/*     */     } 
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   public static AbstractCard returnUpgradedCard(Random rng) {
/* 136 */     ArrayList<AbstractCard> cards = new ArrayList<>();
/*     */     
/* 138 */     for (AbstractCard c : (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck)).group) {
/* 139 */       if (c.upgraded == true) {
/* 140 */         cards.add(c);
/*     */       }
/*     */     } 
/*     */     
/* 144 */     return cards.remove(rng.random(cards.size() - 1));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\CardHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */