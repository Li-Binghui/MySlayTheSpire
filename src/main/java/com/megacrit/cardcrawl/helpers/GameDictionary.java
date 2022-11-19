/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.KeywordStrings;

import java.util.TreeMap;
/*     */ 
/*     */ public class GameDictionary
/*     */ {
/*  10 */   private static final KeywordStrings keywordStrings = CardCrawlGame.languagePack.getKeywordString("Game Dictionary");
/*  11 */   public static final String[] TEXT = keywordStrings.TEXT;
/*  12 */   public static final Keyword ARTIFACT = keywordStrings.ARTIFACT;
/*  13 */   public static final Keyword BLOCK = keywordStrings.BLOCK;
/*  14 */   public static final Keyword EVOKE = keywordStrings.EVOKE;
/*  15 */   public static final Keyword CONFUSED = keywordStrings.CONFUSED;
/*  16 */   public static final Keyword CHANNEL = keywordStrings.CHANNEL;
/*  17 */   public static final Keyword CURSE = keywordStrings.CURSE;
/*  18 */   public static final Keyword DARK = keywordStrings.DARK;
/*  19 */   public static final Keyword DEXTERITY = keywordStrings.DEXTERITY;
/*  20 */   public static final Keyword ETHEREAL = keywordStrings.ETHEREAL;
/*  21 */   public static final Keyword EXHAUST = keywordStrings.EXHAUST;
/*  22 */   public static final Keyword FRAIL = keywordStrings.FRAIL;
/*  23 */   public static final Keyword FROST = keywordStrings.FROST;
/*  24 */   public static final Keyword INNATE = keywordStrings.INNATE;
/*  25 */   public static final Keyword INTANGIBLE = keywordStrings.INTANGIBLE;
/*  26 */   public static final Keyword FOCUS = keywordStrings.FOCUS;
/*  27 */   public static final Keyword LIGHTNING = keywordStrings.LIGHTNING;
/*  28 */   public static final Keyword LOCKED = keywordStrings.LOCKED;
/*  29 */   public static final Keyword LOCK_ON = keywordStrings.LOCK_ON;
/*  30 */   public static final Keyword OPENER = keywordStrings.OPENER;
/*  31 */   public static final Keyword PLASMA = keywordStrings.PLASMA;
/*  32 */   public static final Keyword POISON = keywordStrings.POISON;
/*  33 */   public static final Keyword RETAIN = keywordStrings.RETAIN;
/*  34 */   public static final Keyword SHIV = keywordStrings.SHIV;
/*  35 */   public static final Keyword STATUS = keywordStrings.STATUS;
/*  36 */   public static final Keyword STRENGTH = keywordStrings.STRENGTH;
/*  37 */   public static final Keyword STRIKE = keywordStrings.STRIKE;
/*  38 */   public static final Keyword TRANSFORM = keywordStrings.TRANSFORM;
/*  39 */   public static final Keyword UNKNOWN = keywordStrings.UNKNOWN;
/*  40 */   public static final Keyword UNPLAYABLE = keywordStrings.UNPLAYABLE;
/*  41 */   public static final Keyword UPGRADE = keywordStrings.UPGRADE;
/*  42 */   public static final Keyword VIGOR = keywordStrings.VIGOR;
/*  43 */   public static final Keyword VOID = keywordStrings.VOID;
/*  44 */   public static final Keyword VULNERABLE = keywordStrings.VULNERABLE;
/*  45 */   public static final Keyword WEAK = keywordStrings.WEAK;
/*  46 */   public static final Keyword WOUND = keywordStrings.WOUND;
/*  47 */   public static final Keyword DAZED = keywordStrings.DAZED;
/*  48 */   public static final Keyword BURN = keywordStrings.BURN;
/*  49 */   public static final Keyword THORNS = keywordStrings.THORNS;
/*  50 */   public static final Keyword STANCE = keywordStrings.STANCE;
/*  51 */   public static final Keyword WRATH = keywordStrings.WRATH;
/*  52 */   public static final Keyword CALM = keywordStrings.CALM;
/*  53 */   public static final Keyword ENLIGHTENMENT = keywordStrings.DIVINITY;
/*  54 */   public static final Keyword SCRY = keywordStrings.SCRY;
/*  55 */   public static final Keyword PRAYER = keywordStrings.PRAYER;
/*  56 */   public static final Keyword REGEN = keywordStrings.REGEN;
/*  57 */   public static final Keyword RITUAL = keywordStrings.RITUAL;
/*  58 */   public static final Keyword FATAL = keywordStrings.FATAL;
/*     */   
/*  60 */   public static final TreeMap<String, String> keywords = new TreeMap<>();
/*  61 */   public static final TreeMap<String, String> parentWord = new TreeMap<>();
/*     */   
/*     */   public static void initialize() {
/*  64 */     keywords.put("[R]", TEXT[0]);
/*  65 */     keywords.put("[G]", TEXT[0]);
/*  66 */     keywords.put("[B]", TEXT[0]);
/*  67 */     keywords.put("[W]", TEXT[0]);
/*  68 */     keywords.put("[E]", TEXT[0]);
/*     */     
/*  70 */     createDictionaryEntry(ARTIFACT.NAMES, ARTIFACT.DESCRIPTION);
/*  71 */     createDictionaryEntry(BLOCK.NAMES, BLOCK.DESCRIPTION);
/*  72 */     createDictionaryEntry(BURN.NAMES, BURN.DESCRIPTION);
/*  73 */     createDictionaryEntry(CALM.NAMES, CALM.DESCRIPTION);
/*  74 */     createDictionaryEntry(CHANNEL.NAMES, CHANNEL.DESCRIPTION);
/*  75 */     createDictionaryEntry(CONFUSED.NAMES, CONFUSED.DESCRIPTION);
/*  76 */     createDictionaryEntry(CURSE.NAMES, CURSE.DESCRIPTION);
/*  77 */     createDictionaryEntry(DARK.NAMES, DARK.DESCRIPTION);
/*  78 */     createDictionaryEntry(DAZED.NAMES, DAZED.DESCRIPTION);
/*  79 */     createDictionaryEntry(DEXTERITY.NAMES, DEXTERITY.DESCRIPTION);
/*  80 */     createDictionaryEntry(ENLIGHTENMENT.NAMES, ENLIGHTENMENT.DESCRIPTION);
/*  81 */     createDictionaryEntry(ETHEREAL.NAMES, ETHEREAL.DESCRIPTION);
/*  82 */     createDictionaryEntry(EVOKE.NAMES, EVOKE.DESCRIPTION);
/*  83 */     createDictionaryEntry(EXHAUST.NAMES, EXHAUST.DESCRIPTION);
/*  84 */     createDictionaryEntry(FOCUS.NAMES, FOCUS.DESCRIPTION);
/*  85 */     createDictionaryEntry(FRAIL.NAMES, FRAIL.DESCRIPTION);
/*  86 */     createDictionaryEntry(FROST.NAMES, FROST.DESCRIPTION);
/*  87 */     createDictionaryEntry(INNATE.NAMES, INNATE.DESCRIPTION);
/*  88 */     createDictionaryEntry(INTANGIBLE.NAMES, INTANGIBLE.DESCRIPTION);
/*  89 */     createDictionaryEntry(LIGHTNING.NAMES, LIGHTNING.DESCRIPTION);
/*  90 */     createDictionaryEntry(LOCK_ON.NAMES, LOCK_ON.DESCRIPTION);
/*  91 */     createDictionaryEntry(LOCKED.NAMES, LOCKED.DESCRIPTION);
/*  92 */     createDictionaryEntry(OPENER.NAMES, OPENER.DESCRIPTION);
/*  93 */     createDictionaryEntry(PLASMA.NAMES, PLASMA.DESCRIPTION);
/*  94 */     createDictionaryEntry(POISON.NAMES, POISON.DESCRIPTION);
/*  95 */     createDictionaryEntry(PRAYER.NAMES, PRAYER.DESCRIPTION);
/*  96 */     createDictionaryEntry(RETAIN.NAMES, RETAIN.DESCRIPTION);
/*  97 */     createDictionaryEntry(SCRY.NAMES, SCRY.DESCRIPTION);
/*  98 */     createDictionaryEntry(SHIV.NAMES, SHIV.DESCRIPTION);
/*  99 */     createDictionaryEntry(STANCE.NAMES, STANCE.DESCRIPTION);
/* 100 */     createDictionaryEntry(STATUS.NAMES, STATUS.DESCRIPTION);
/* 101 */     createDictionaryEntry(STRENGTH.NAMES, STRENGTH.DESCRIPTION);
/* 102 */     createDictionaryEntry(STRIKE.NAMES, STRIKE.DESCRIPTION);
/* 103 */     createDictionaryEntry(THORNS.NAMES, THORNS.DESCRIPTION);
/* 104 */     createDictionaryEntry(TRANSFORM.NAMES, TRANSFORM.DESCRIPTION);
/* 105 */     createDictionaryEntry(UNKNOWN.NAMES, UNKNOWN.DESCRIPTION);
/* 106 */     createDictionaryEntry(UNPLAYABLE.NAMES, UNPLAYABLE.DESCRIPTION);
/* 107 */     createDictionaryEntry(UPGRADE.NAMES, UPGRADE.DESCRIPTION);
/* 108 */     createDictionaryEntry(VIGOR.NAMES, VIGOR.DESCRIPTION);
/* 109 */     createDictionaryEntry(VOID.NAMES, VOID.DESCRIPTION);
/* 110 */     createDictionaryEntry(VULNERABLE.NAMES, VULNERABLE.DESCRIPTION);
/* 111 */     createDictionaryEntry(WEAK.NAMES, WEAK.DESCRIPTION);
/* 112 */     createDictionaryEntry(WOUND.NAMES, WOUND.DESCRIPTION);
/* 113 */     createDictionaryEntry(WRATH.NAMES, WRATH.DESCRIPTION);
/* 114 */     createDictionaryEntry(REGEN.NAMES, REGEN.DESCRIPTION);
/* 115 */     createDictionaryEntry(RITUAL.NAMES, RITUAL.DESCRIPTION);
/* 116 */     createDictionaryEntry(FATAL.NAMES, FATAL.DESCRIPTION);
/*     */   }
/*     */   
/*     */   private static void createDictionaryEntry(String[] names, String desc) {
/* 120 */     for (String n : names) {
/* 121 */       keywords.put(n, desc);
/* 122 */       parentWord.put(n, names[0]);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\GameDictionary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */