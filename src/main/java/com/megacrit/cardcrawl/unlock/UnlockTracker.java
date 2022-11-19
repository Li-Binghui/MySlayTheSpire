/*      */ package com.megacrit.cardcrawl.unlock;
/*      */ 
/*      */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.screens.stats.AchievementItem;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.unlock.cards.defect.*;
import com.megacrit.cardcrawl.unlock.cards.ironclad.*;
import com.megacrit.cardcrawl.unlock.cards.silent.*;
import com.megacrit.cardcrawl.unlock.cards.watcher.*;
import com.megacrit.cardcrawl.unlock.relics.defect.*;
import com.megacrit.cardcrawl.unlock.relics.ironclad.*;
import com.megacrit.cardcrawl.unlock.relics.silent.*;
import com.megacrit.cardcrawl.unlock.relics.watcher.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class UnlockTracker
/*      */ {
/*  149 */   private static final Logger logger = LogManager.getLogger(UnlockTracker.class.getName()); public static Prefs unlockPref;
/*      */   public static Prefs seenPref;
/*      */   public static Prefs betaCardPref;
/*  152 */   public static HashMap<String, String> unlockReqs = new HashMap<>(); public static Prefs bossSeenPref; public static Prefs relicSeenPref; public static Prefs achievementPref; public static Prefs unlockProgress;
/*  153 */   public static ArrayList<String> lockedCards = new ArrayList<>();
/*  154 */   public static ArrayList<String> lockedCharacters = new ArrayList<>();
/*  155 */   public static ArrayList<String> lockedLoadouts = new ArrayList<>();
/*  156 */   public static ArrayList<String> lockedRelics = new ArrayList<>(); public static int lockedRedCardCount; public static int unlockedRedCardCount;
/*      */   public static int lockedGreenCardCount;
/*      */   public static int unlockedGreenCardCount;
/*      */   public static int lockedBlueCardCount;
/*      */   public static int unlockedBlueCardCount;
/*      */   public static int lockedPurpleCardCount;
/*      */   public static int unlockedPurpleCardCount;
/*      */   public static int lockedRelicCount;
/*      */   public static int unlockedRelicCount;
/*      */   private static final int STARTING_UNLOCK_COST = 300;
/*      */   
/*      */   public static void initialize() {
/*  168 */     achievementPref = SaveHelper.getPrefs("STSAchievements");
/*  169 */     unlockPref = SaveHelper.getPrefs("STSUnlocks");
/*  170 */     unlockProgress = SaveHelper.getPrefs("STSUnlockProgress");
/*  171 */     seenPref = SaveHelper.getPrefs("STSSeenCards");
/*  172 */     betaCardPref = SaveHelper.getPrefs("STSBetaCardPreference");
/*  173 */     bossSeenPref = SaveHelper.getPrefs("STSSeenBosses");
/*  174 */     relicSeenPref = SaveHelper.getPrefs("STSSeenRelics");
/*  175 */     refresh();
/*      */   }
/*      */   
/*      */   public static void retroactiveUnlock() {
/*  179 */     ArrayList<String> cardKeys = new ArrayList<>();
/*  180 */     ArrayList<String> relicKeys = new ArrayList<>();
/*  181 */     ArrayList<AbstractUnlock> bundle = new ArrayList<>();
/*      */     
/*  183 */     appendRetroactiveUnlockList(AbstractPlayer.PlayerClass.IRONCLAD, unlockProgress
/*      */         
/*  185 */         .getInteger(AbstractPlayer.PlayerClass.IRONCLAD.toString() + "UnlockLevel", -1), bundle, cardKeys, relicKeys);
/*      */ 
/*      */ 
/*      */     
/*  189 */     appendRetroactiveUnlockList(AbstractPlayer.PlayerClass.THE_SILENT, unlockProgress
/*      */         
/*  191 */         .getInteger(AbstractPlayer.PlayerClass.THE_SILENT.toString() + "UnlockLevel", -1), bundle, cardKeys, relicKeys);
/*      */ 
/*      */ 
/*      */     
/*  195 */     appendRetroactiveUnlockList(AbstractPlayer.PlayerClass.DEFECT, unlockProgress
/*      */         
/*  197 */         .getInteger(AbstractPlayer.PlayerClass.DEFECT.toString() + "UnlockLevel", -1), bundle, cardKeys, relicKeys);
/*      */ 
/*      */ 
/*      */     
/*  201 */     appendRetroactiveUnlockList(AbstractPlayer.PlayerClass.WATCHER, unlockProgress
/*      */         
/*  203 */         .getInteger(AbstractPlayer.PlayerClass.WATCHER.toString() + "UnlockLevel", -1), bundle, cardKeys, relicKeys);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  208 */     boolean changed = false;
/*      */     
/*  210 */     for (String k : cardKeys) {
/*  211 */       if (unlockPref.getInteger(k) != 2) {
/*  212 */         unlockPref.putInteger(k, 2);
/*  213 */         changed = true;
/*  214 */         logger.info("RETROACTIVE CARD UNLOCK:  " + k);
/*      */       } 
/*      */     } 
/*      */     
/*  218 */     for (String k : relicKeys) {
/*  219 */       if (unlockPref.getInteger(k) != 2) {
/*  220 */         unlockPref.putInteger(k, 2);
/*  221 */         changed = true;
/*  222 */         logger.info("RETROACTIVE RELIC UNLOCK: " + k);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  227 */     if (isCharacterLocked("Watcher") && !isCharacterLocked("Defect") && (isAchievementUnlocked("RUBY") || 
/*  228 */       isAchievementUnlocked("EMERALD") || isAchievementUnlocked("SAPPHIRE"))) {
/*      */       
/*  230 */       unlockPref.putInteger("Watcher", 2);
/*  231 */       lockedCharacters.remove("Watcher");
/*  232 */       changed = true;
/*      */     } 
/*      */     
/*  235 */     if (changed) {
/*  236 */       logger.info("RETRO UNLOCKED, SAVING");
/*  237 */       unlockPref.flush();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void appendRetroactiveUnlockList(AbstractPlayer.PlayerClass c, int lvl, ArrayList<AbstractUnlock> bundle, ArrayList<String> cardKeys, ArrayList<String> relicKeys) {
/*  248 */     while (lvl > 0) {
/*  249 */       bundle = getUnlockBundle(c, lvl - 1);
/*  250 */       for (AbstractUnlock u : bundle) {
/*  251 */         if (u.type == AbstractUnlock.UnlockType.RELIC) {
/*  252 */           logger.info(u.key + " should be unlocked.");
/*  253 */           relicKeys.add(u.key); continue;
/*  254 */         }  if (u.type == AbstractUnlock.UnlockType.CARD) {
/*  255 */           logger.info(u.key + " should be unlocked.");
/*  256 */           cardKeys.add(u.key);
/*      */         } 
/*      */       } 
/*  259 */       lvl--;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void refresh() {
/*  264 */     lockedCards.clear();
/*  265 */     lockedCharacters.clear();
/*  266 */     lockedLoadouts.clear();
/*  267 */     lockedRelics.clear();
/*      */ 
/*      */     
/*  270 */     addCard("Havoc");
/*  271 */     addCard("Sentinel");
/*  272 */     addCard("Exhume");
/*      */     
/*  274 */     addCard("Wild Strike");
/*  275 */     addCard("Evolve");
/*  276 */     addCard("Immolate");
/*      */     
/*  278 */     addCard("Heavy Blade");
/*  279 */     addCard("Spot Weakness");
/*  280 */     addCard("Limit Break");
/*      */ 
/*      */     
/*  283 */     addCard("Concentrate");
/*  284 */     addCard("Setup");
/*  285 */     addCard("Grand Finale");
/*      */     
/*  287 */     addCard("Cloak And Dagger");
/*  288 */     addCard("Accuracy");
/*  289 */     addCard("Storm of Steel");
/*      */     
/*  291 */     addCard("Bane");
/*  292 */     addCard("Catalyst");
/*  293 */     addCard("Corpse Explosion");
/*      */ 
/*      */     
/*  296 */     addCard("Rebound");
/*  297 */     addCard("Undo");
/*  298 */     addCard("Echo Form");
/*      */     
/*  300 */     addCard("Turbo");
/*  301 */     addCard("Sunder");
/*  302 */     addCard("Meteor Strike");
/*      */     
/*  304 */     addCard("Hyperbeam");
/*  305 */     addCard("Recycle");
/*  306 */     addCard("Core Surge");
/*      */ 
/*      */     
/*  309 */     addCard("Prostrate");
/*  310 */     addCard("Blasphemy");
/*  311 */     addCard("Devotion");
/*      */     
/*  313 */     addCard("ForeignInfluence");
/*  314 */     addCard("Alpha");
/*  315 */     addCard("MentalFortress");
/*      */     
/*  317 */     addCard("SpiritShield");
/*  318 */     addCard("Wish");
/*  319 */     addCard("Wireheading");
/*      */ 
/*      */     
/*  322 */     addCharacter("The Silent");
/*  323 */     addCharacter("Defect");
/*  324 */     addCharacter("Watcher");
/*      */ 
/*      */ 
/*      */     
/*  328 */     addRelic("Omamori");
/*  329 */     addRelic("Prayer Wheel");
/*  330 */     addRelic("Shovel");
/*      */     
/*  332 */     addRelic("Art of War");
/*  333 */     addRelic("The Courier");
/*  334 */     addRelic("Pandora's Box");
/*      */ 
/*      */     
/*  337 */     addRelic("Blue Candle");
/*  338 */     addRelic("Dead Branch");
/*  339 */     addRelic("Singing Bowl");
/*      */     
/*  341 */     addRelic("Du-Vu Doll");
/*  342 */     addRelic("Smiling Mask");
/*  343 */     addRelic("Tiny Chest");
/*      */ 
/*      */     
/*  346 */     addRelic("Cables");
/*  347 */     addRelic("DataDisk");
/*  348 */     addRelic("Emotion Chip");
/*      */     
/*  350 */     addRelic("Runic Capacitor");
/*  351 */     addRelic("Turnip");
/*  352 */     addRelic("Symbiotic Virus");
/*      */ 
/*      */     
/*  355 */     addRelic("Akabeko");
/*  356 */     addRelic("Yang");
/*  357 */     addRelic("CeramicFish");
/*      */     
/*  359 */     addRelic("StrikeDummy");
/*  360 */     addRelic("TeardropLocket");
/*  361 */     addRelic("CloakClasp");
/*      */     
/*  363 */     countUnlockedCards();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int incrementUnlockRamp(int currentCost) {
/*  373 */     switch (currentCost) {
/*      */       case 300:
/*  375 */         return 750;
/*      */       
/*      */       case 500:
/*  378 */         return 1000;
/*      */       case 750:
/*  380 */         return 1000;
/*      */       case 1000:
/*  382 */         return 1500;
/*      */       case 1500:
/*  384 */         return 2000;
/*      */       case 2000:
/*  386 */         return 2500;
/*      */       case 2500:
/*  388 */         return 3000;
/*      */       case 3000:
/*  390 */         return 3000;
/*      */       case 4000:
/*  392 */         return 4000;
/*      */     } 
/*  394 */     return currentCost + 250;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void resetUnlockProgress(AbstractPlayer.PlayerClass c) {
/*  399 */     unlockProgress.putInteger(c.toString() + "UnlockLevel", 0);
/*  400 */     unlockProgress.putInteger(c.toString() + "Progress", 0);
/*  401 */     unlockProgress.putInteger(c.toString() + "CurrentCost", 300);
/*  402 */     unlockProgress.putInteger(c.toString() + "TotalScore", 0);
/*  403 */     unlockProgress.putInteger(c.toString() + "HighScore", 0);
/*      */   }
/*      */   
/*      */   public static int getUnlockLevel(AbstractPlayer.PlayerClass c) {
/*  407 */     return unlockProgress.getInteger(c.toString() + "UnlockLevel", 0);
/*      */   }
/*      */   
/*      */   public static int getCurrentProgress(AbstractPlayer.PlayerClass c) {
/*  411 */     return unlockProgress.getInteger(c.toString() + "Progress", 0);
/*      */   }
/*      */   
/*      */   public static int getCurrentScoreCost(AbstractPlayer.PlayerClass c) {
/*  415 */     return unlockProgress.getInteger(c.toString() + "CurrentCost", 300);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addScore(AbstractPlayer.PlayerClass c, int scoreGained) {
/*  426 */     String key_unlock_level = c.toString() + "UnlockLevel";
/*  427 */     String key_progress = c.toString() + "Progress";
/*  428 */     String key_current_cost = c.toString() + "CurrentCost";
/*  429 */     String key_total_score = c.toString() + "TotalScore";
/*  430 */     String key_high_score = c.toString() + "HighScore";
/*  431 */     logger.info("Keys");
/*  432 */     logger.info(key_unlock_level);
/*  433 */     logger.info(key_progress);
/*  434 */     logger.info(key_current_cost);
/*  435 */     logger.info(key_total_score);
/*  436 */     logger.info(key_high_score);
/*      */ 
/*      */     
/*  439 */     int p = unlockProgress.getInteger(key_progress, 0);
/*  440 */     p += scoreGained;
/*      */     
/*  442 */     if (p >= unlockProgress.getInteger(key_current_cost, 300)) {
/*  443 */       logger.info("[DEBUG] Level up!");
/*      */ 
/*      */       
/*  446 */       int lvl = unlockProgress.getInteger(key_unlock_level, 0);
/*  447 */       lvl++;
/*  448 */       unlockProgress.putInteger(key_unlock_level, lvl);
/*      */ 
/*      */       
/*  451 */       p -= unlockProgress.getInteger(key_current_cost, 300);
/*  452 */       unlockProgress.putInteger(key_progress, p);
/*  453 */       logger.info("[DEBUG] Score Progress: " + key_progress);
/*      */ 
/*      */       
/*  456 */       int current_cost = unlockProgress.getInteger(key_current_cost, 300);
/*  457 */       unlockProgress.putInteger(key_current_cost, incrementUnlockRamp(current_cost));
/*      */       
/*  459 */       if (p > unlockProgress.getInteger(key_current_cost, 300)) {
/*  460 */         unlockProgress.putInteger(key_progress, unlockProgress
/*      */             
/*  462 */             .getInteger(key_current_cost, 300) - 1);
/*  463 */         logger.info("Overfloat maxes out next level");
/*      */       } 
/*      */     } else {
/*      */       
/*  467 */       unlockProgress.putInteger(key_progress, p);
/*      */     } 
/*      */ 
/*      */     
/*  471 */     int total = unlockProgress.getInteger(key_total_score, 0);
/*  472 */     total += scoreGained;
/*  473 */     unlockProgress.putInteger(key_total_score, total);
/*  474 */     logger.info("[DEBUG] Total score: " + total);
/*      */ 
/*      */     
/*  477 */     int highscore = unlockProgress.getInteger(key_high_score, 0);
/*  478 */     if (scoreGained > highscore) {
/*  479 */       unlockProgress.putInteger(key_high_score, scoreGained);
/*  480 */       logger.info("[DEBUG] New high score: " + scoreGained);
/*      */     } 
/*      */ 
/*      */     
/*  484 */     unlockProgress.flush();
/*      */   }
/*      */   
/*      */   public static void countUnlockedCards() {
/*  488 */     ArrayList<String> tmp = new ArrayList<>();
/*  489 */     int count = 0;
/*      */ 
/*      */     
/*  492 */     tmp.add("Havoc");
/*  493 */     tmp.add("Sentinel");
/*  494 */     tmp.add("Exhume");
/*      */     
/*  496 */     tmp.add("Wild Strike");
/*  497 */     tmp.add("Evolve");
/*  498 */     tmp.add("Immolate");
/*      */     
/*  500 */     tmp.add("Heavy Blade");
/*  501 */     tmp.add("Spot Weakness");
/*  502 */     tmp.add("Limit Break");
/*      */     
/*  504 */     for (String s : tmp) {
/*  505 */       if (!isCardLocked(s)) {
/*  506 */         count++;
/*      */       }
/*      */     } 
/*      */     
/*  510 */     lockedRedCardCount = tmp.size();
/*  511 */     unlockedRedCardCount = count;
/*  512 */     tmp.clear();
/*  513 */     count = 0;
/*      */ 
/*      */     
/*  516 */     tmp.add("Concentrate");
/*  517 */     tmp.add("Setup");
/*  518 */     tmp.add("Grand Finale");
/*      */     
/*  520 */     tmp.add("Cloak And Dagger");
/*  521 */     tmp.add("Accuracy");
/*  522 */     tmp.add("Storm of Steel");
/*      */     
/*  524 */     tmp.add("Bane");
/*  525 */     tmp.add("Catalyst");
/*  526 */     tmp.add("Corpse Explosion");
/*      */     
/*  528 */     for (String s : tmp) {
/*  529 */       if (!isCardLocked(s)) {
/*  530 */         count++;
/*      */       }
/*      */     } 
/*      */     
/*  534 */     lockedGreenCardCount = tmp.size();
/*  535 */     unlockedGreenCardCount = count;
/*  536 */     tmp.clear();
/*  537 */     count = 0;
/*      */ 
/*      */     
/*  540 */     tmp.add("Rebound");
/*  541 */     tmp.add("Undo");
/*  542 */     tmp.add("Echo Form");
/*      */     
/*  544 */     tmp.add("Turbo");
/*  545 */     tmp.add("Sunder");
/*  546 */     tmp.add("Meteor Strike");
/*      */     
/*  548 */     tmp.add("Hyperbeam");
/*  549 */     tmp.add("Recycle");
/*  550 */     tmp.add("Core Surge");
/*      */     
/*  552 */     for (String s : tmp) {
/*  553 */       if (!isCardLocked(s)) {
/*  554 */         count++;
/*      */       }
/*      */     } 
/*      */     
/*  558 */     lockedBlueCardCount = tmp.size();
/*  559 */     unlockedBlueCardCount = count;
/*  560 */     tmp.clear();
/*  561 */     count = 0;
/*      */ 
/*      */     
/*  564 */     tmp.add("Prostrate");
/*  565 */     tmp.add("Blasphemy");
/*  566 */     tmp.add("Devotion");
/*      */     
/*  568 */     tmp.add("ForeignInfluence");
/*  569 */     tmp.add("Alpha");
/*  570 */     tmp.add("MentalFortress");
/*      */     
/*  572 */     tmp.add("SpiritShield");
/*  573 */     tmp.add("Wish");
/*  574 */     tmp.add("Wireheading");
/*      */     
/*  576 */     for (String s : tmp) {
/*  577 */       if (!isCardLocked(s)) {
/*  578 */         count++;
/*      */       }
/*      */     } 
/*      */     
/*  582 */     lockedPurpleCardCount = tmp.size();
/*  583 */     unlockedPurpleCardCount = count;
/*  584 */     tmp.clear();
/*  585 */     count = 0;
/*      */ 
/*      */     
/*  588 */     tmp.add("Omamori");
/*  589 */     tmp.add("Prayer Wheel");
/*  590 */     tmp.add("Shovel");
/*      */     
/*  592 */     tmp.add("Art of War");
/*  593 */     tmp.add("The Courier");
/*  594 */     tmp.add("Pandora's Box");
/*      */     
/*  596 */     tmp.add("Blue Candle");
/*  597 */     tmp.add("Dead Branch");
/*  598 */     tmp.add("Singing Bowl");
/*      */     
/*  600 */     tmp.add("Du-Vu Doll");
/*  601 */     tmp.add("Smiling Mask");
/*  602 */     tmp.add("Tiny Chest");
/*      */ 
/*      */     
/*  605 */     tmp.add("Cables");
/*  606 */     tmp.add("DataDisk");
/*  607 */     tmp.add("Emotion Chip");
/*  608 */     tmp.add("Runic Capacitor");
/*  609 */     tmp.add("Turnip");
/*  610 */     tmp.add("Symbiotic Virus");
/*      */ 
/*      */     
/*  613 */     tmp.add("Akabeko");
/*  614 */     tmp.add("Yang");
/*  615 */     tmp.add("CeramicFish");
/*      */     
/*  617 */     tmp.add("StrikeDummy");
/*  618 */     tmp.add("TeardropLocket");
/*  619 */     tmp.add("CloakClasp");
/*      */     
/*  621 */     for (String s : tmp) {
/*  622 */       if (!isRelicLocked(s)) {
/*  623 */         count++;
/*      */       }
/*      */     } 
/*      */     
/*  627 */     lockedRelicCount = tmp.size();
/*  628 */     unlockedRelicCount = count;
/*      */     
/*  630 */     logger.info("RED UNLOCKS:   " + unlockedRedCardCount + "/" + lockedRedCardCount);
/*  631 */     logger.info("GREEN UNLOCKS: " + unlockedGreenCardCount + "/" + lockedGreenCardCount);
/*  632 */     logger.info("BLUE UNLOCKS: " + unlockedBlueCardCount + "/" + lockedBlueCardCount);
/*  633 */     logger.info("PURPLE UNLOCKS: " + unlockedPurpleCardCount + "/" + lockedPurpleCardCount);
/*  634 */     logger.info("RELIC UNLOCKS: " + unlockedRelicCount + "/" + lockedRelicCount);
/*  635 */     logger.info("CARDS SEEN:    " + seenPref.get().keySet().size() + "/" + CardLibrary.totalCardCount);
/*  636 */     logger.info("RELICS SEEN:   " + relicSeenPref.get().keySet().size() + "/" + RelicLibrary.totalRelicCount);
/*      */   }
/*      */   
/*      */   public static String getCardsSeenString() {
/*  640 */     return (CardLibrary.seenRedCards + CardLibrary.seenGreenCards + CardLibrary.seenBlueCards + CardLibrary.seenPurpleCards + CardLibrary.seenColorlessCards + CardLibrary.seenCurseCards) + "/" + CardLibrary.totalCardCount;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getRelicsSeenString() {
/*  646 */     return RelicLibrary.seenRelics + "/" + RelicLibrary.totalRelicCount;
/*      */   }
/*      */   
/*      */   public static void addCard(String key) {
/*  650 */     if (unlockPref.getString(key).equals("true")) {
/*  651 */       unlockPref.putInteger(key, 2);
/*  652 */       logger.info("Converting " + key + " from bool to int");
/*  653 */       unlockPref.flush();
/*  654 */     } else if (unlockPref.getString(key).equals("false")) {
/*  655 */       unlockPref.putInteger(key, 0);
/*  656 */       logger.info("Converting " + key + " from bool to int");
/*  657 */       unlockPref.flush();
/*      */     } 
/*      */     
/*  660 */     if (unlockPref.getInteger(key, 0) != 2) {
/*  661 */       lockedCards.add(key);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void addCharacter(String key) {
/*  666 */     if (unlockPref.getString(key).equals("true")) {
/*  667 */       unlockPref.putInteger(key, 2);
/*  668 */       logger.info("Converting " + key + " from bool to int");
/*  669 */       unlockPref.flush();
/*  670 */     } else if (unlockPref.getString(key).equals("false")) {
/*  671 */       unlockPref.putInteger(key, 0);
/*  672 */       logger.info("Converting " + key + " from bool to int");
/*  673 */       unlockPref.flush();
/*      */     } 
/*      */     
/*  676 */     if (unlockPref.getInteger(key, 0) != 2) {
/*  677 */       lockedCharacters.add(key);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void addRelic(String key) {
/*  682 */     if (unlockPref.getInteger(key, 0) != 2) {
/*  683 */       lockedRelics.add(key);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void unlockAchievement(String key) {
/*  693 */     if (Settings.isModded || Settings.isShowBuild || !Settings.isStandardRun()) {
/*      */       return;
/*      */     }
/*      */     
/*  697 */     CardCrawlGame.publisherIntegration.unlockAchievement(key);
/*      */     
/*  699 */     if (!achievementPref.getBoolean(key, false)) {
/*  700 */       achievementPref.putBoolean(key, true);
/*  701 */       logger.info("Achievement Unlocked: " + key);
/*      */     } 
/*      */     
/*  704 */     if (allAchievementsExceptPlatinumUnlocked() && !isAchievementUnlocked("ETERNAL_ONE")) {
/*      */       
/*  706 */       CardCrawlGame.publisherIntegration.unlockAchievement("ETERNAL_ONE");
/*  707 */       achievementPref.putBoolean("ETERNAL_ONE", true);
/*  708 */       logger.info("Achievement Unlocked: ETERNAL_ONE");
/*      */     } 
/*      */     
/*  711 */     achievementPref.flush();
/*      */   }
/*      */   
/*      */   public static boolean allAchievementsExceptPlatinumUnlocked() {
/*  715 */     return (achievementPref.data.entrySet().size() >= 45);
/*      */   }
/*      */   
/*      */   public static boolean isAchievementUnlocked(String key) {
/*  719 */     return achievementPref.getBoolean(key, false);
/*      */   }
/*      */   
/*      */   public static void unlockLuckyDay() {
/*  723 */     if (Settings.isModded) {
/*      */       return;
/*      */     }
/*  726 */     String key = "LUCKY_DAY";
/*  727 */     CardCrawlGame.publisherIntegration.unlockAchievement(key);
/*      */     
/*  729 */     if (!achievementPref.getBoolean(key, false)) {
/*  730 */       achievementPref.putBoolean(key, true);
/*  731 */       achievementPref.flush();
/*  732 */       logger.info("Achievement Unlocked: " + key);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void hardUnlock(String key) {
/*  742 */     if (Settings.isShowBuild) {
/*      */       return;
/*      */     }
/*      */     
/*  746 */     if (unlockPref.getInteger(key, 0) == 1) {
/*  747 */       unlockPref.putInteger(key, 2);
/*  748 */       unlockPref.flush();
/*  749 */       logger.info("Hard Unlock: " + key);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void hardUnlockOverride(String key) {
/*  759 */     if (Settings.isShowBuild) {
/*      */       return;
/*      */     }
/*      */     
/*  763 */     unlockPref.putInteger(key, 2);
/*  764 */     unlockPref.flush();
/*  765 */     logger.info("Hard Unlock: " + key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isCardLocked(String key) {
/*  775 */     return lockedCards.contains(key);
/*      */   }
/*      */   
/*      */   public static void unlockCard(String key) {
/*  779 */     seenPref.putInteger(key, 1);
/*  780 */     seenPref.flush();
/*  781 */     unlockPref.putInteger(key, 2);
/*  782 */     unlockPref.flush();
/*  783 */     lockedCards.remove(key);
/*      */     
/*  785 */     if (CardLibrary.getCard(key) != null) {
/*  786 */       (CardLibrary.getCard(key)).isSeen = true;
/*  787 */       CardLibrary.getCard(key).unlock();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isCharacterLocked(String key) {
/*  798 */     if (key.equals("The Silent") && Settings.isDemo)
/*  799 */       return false; 
/*  800 */     if (Settings.isAlpha) {
/*  801 */       return false;
/*      */     }
/*  803 */     return lockedCharacters.contains(key);
/*      */   }
/*      */   
/*      */   public static boolean isAscensionUnlocked(AbstractPlayer p) {
/*  807 */     int victories = StatsScreen.getVictory(p.getCharStat());
/*      */     
/*  809 */     if (victories > 0) {
/*      */       
/*  811 */       if (!achievementPref.getBoolean("ASCEND_0", false)) {
/*  812 */         unlockAchievement("ASCEND_0");
/*      */       }
/*      */ 
/*      */       
/*  816 */       if (!achievementPref.getBoolean("ASCEND_10", false)) {
/*  817 */         StatsScreen.retroactiveAscend10Unlock(p.getPrefs());
/*      */       }
/*      */ 
/*      */       
/*  821 */       if (!achievementPref.getBoolean("ASCEND_20", false)) {
/*  822 */         StatsScreen.retroactiveAscend20Unlock(p.getPrefs());
/*      */       }
/*  824 */       return true;
/*      */     } 
/*      */     
/*  827 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isRelicLocked(String key) {
/*  837 */     return lockedRelics.contains(key);
/*      */   }
/*      */   
/*      */   public static void markCardAsSeen(String key) {
/*  841 */     if (CardLibrary.getCard(key) != null && !(CardLibrary.getCard(key)).isSeen) {
/*  842 */       (CardLibrary.getCard(key)).isSeen = true;
/*  843 */       seenPref.putInteger(key, 1);
/*  844 */       seenPref.flush();
/*      */     } else {
/*  846 */       logger.info("Already seen: " + key);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isCardSeen(String key) {
/*  851 */     return (seenPref.getInteger(key, 0) != 0);
/*      */   }
/*      */   
/*      */   public static void markRelicAsSeen(String key) {
/*  855 */     if (RelicLibrary.getRelic(key) != null && !(RelicLibrary.getRelic(key)).isSeen) {
/*  856 */       (RelicLibrary.getRelic(key)).isSeen = true;
/*  857 */       relicSeenPref.putInteger(key, 1);
/*  858 */       relicSeenPref.flush();
/*  859 */     } else if (Settings.isDebug) {
/*  860 */       logger.info("Already seen: " + key);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isRelicSeen(String key) {
/*  865 */     return (relicSeenPref.getInteger(key, 0) == 1);
/*      */   }
/*      */   
/*      */   public static void markBossAsSeen(String originalName) {
/*  869 */     if (bossSeenPref.getInteger(originalName) != 1) {
/*  870 */       bossSeenPref.putInteger(originalName, 1);
/*  871 */       bossSeenPref.flush();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isBossSeen(String key) {
/*  876 */     if (bossSeenPref.getInteger(key, 0) == 1) {
/*  877 */       return true;
/*      */     }
/*  879 */     return false;
/*      */   }
/*      */   
/*      */   public static ArrayList<AbstractUnlock> getUnlockBundle(AbstractPlayer.PlayerClass c, int unlockLevel) {
/*  883 */     ArrayList<AbstractUnlock> tmpBundle = new ArrayList<>();
/*  884 */     switch (c) {
/*      */       case IRONCLAD:
/*  886 */         switch (unlockLevel) {
/*      */           case 0:
/*  888 */             tmpBundle.add(new HeavyBladeUnlock());
/*  889 */             tmpBundle.add(new SpotWeaknessUnlock());
/*  890 */             tmpBundle.add(new LimitBreakUnlock());
/*      */             break;
/*      */           case 1:
/*  893 */             tmpBundle.add(new OmamoriUnlock());
/*  894 */             tmpBundle.add(new PrayerWheelUnlock());
/*  895 */             tmpBundle.add(new ShovelUnlock());
/*      */             break;
/*      */           case 2:
/*  898 */             tmpBundle.add(new WildStrikeUnlock());
/*  899 */             tmpBundle.add(new EvolveUnlock());
/*  900 */             tmpBundle.add(new ImmolateUnlock());
/*      */             break;
/*      */           case 3:
/*  903 */             tmpBundle.add(new HavocUnlock());
/*  904 */             tmpBundle.add(new SentinelUnlock());
/*  905 */             tmpBundle.add(new ExhumeUnlock());
/*      */             break;
/*      */           case 4:
/*  908 */             tmpBundle.add(new BlueCandleUnlock());
/*  909 */             tmpBundle.add(new DeadBranchUnlock());
/*  910 */             tmpBundle.add(new SingingBowlUnlock());
/*      */             break;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case THE_SILENT:
/*  917 */         switch (unlockLevel) {
/*      */           case 0:
/*  919 */             tmpBundle.add(new BaneUnlock());
/*  920 */             tmpBundle.add(new CatalystUnlock());
/*  921 */             tmpBundle.add(new CorpseExplosionUnlock());
/*      */             break;
/*      */           case 1:
/*  924 */             tmpBundle.add(new DuvuDollUnlock());
/*  925 */             tmpBundle.add(new SmilingMaskUnlock());
/*  926 */             tmpBundle.add(new TinyChestUnlock());
/*      */             break;
/*      */           case 2:
/*  929 */             tmpBundle.add(new CloakAndDaggerUnlock());
/*  930 */             tmpBundle.add(new AccuracyUnlock());
/*  931 */             tmpBundle.add(new StormOfSteelUnlock());
/*      */             break;
/*      */           case 3:
/*  934 */             tmpBundle.add(new ArtOfWarUnlock());
/*  935 */             tmpBundle.add(new CourierUnlock());
/*  936 */             tmpBundle.add(new PandorasBoxUnlock());
/*      */             break;
/*      */           case 4:
/*  939 */             tmpBundle.add(new ConcentrateUnlock());
/*  940 */             tmpBundle.add(new SetupUnlock());
/*  941 */             tmpBundle.add(new GrandFinaleUnlock());
/*      */             break;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case DEFECT:
/*  948 */         switch (unlockLevel) {
/*      */           case 0:
/*  950 */             tmpBundle.add(new ReboundUnlock());
/*  951 */             tmpBundle.add(new UndoUnlock());
/*  952 */             tmpBundle.add(new EchoFormUnlock());
/*      */             break;
/*      */           case 1:
/*  955 */             tmpBundle.add(new TurboUnlock());
/*  956 */             tmpBundle.add(new SunderUnlock());
/*  957 */             tmpBundle.add(new MeteorStrikeUnlock());
/*      */             break;
/*      */           case 2:
/*  960 */             tmpBundle.add(new HyperbeamUnlock());
/*  961 */             tmpBundle.add(new RecycleUnlock());
/*  962 */             tmpBundle.add(new NovaUnlock());
/*      */             break;
/*      */           case 3:
/*  965 */             tmpBundle.add(new CablesUnlock());
/*  966 */             tmpBundle.add(new TurnipUnlock());
/*  967 */             tmpBundle.add(new RunicCapacitorUnlock());
/*      */             break;
/*      */           case 4:
/*  970 */             tmpBundle.add(new EmotionChipUnlock());
/*  971 */             tmpBundle.add(new VirusUnlock());
/*  972 */             tmpBundle.add(new DataDiskUnlock());
/*      */             break;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case WATCHER:
/*  979 */         switch (unlockLevel) {
/*      */           case 0:
/*  981 */             tmpBundle.add(new ProstrateUnlock());
/*  982 */             tmpBundle.add(new BlasphemyUnlock());
/*  983 */             tmpBundle.add(new DevotionUnlock());
/*      */             break;
/*      */           case 1:
/*  986 */             tmpBundle.add(new ForeignInfluenceUnlock());
/*  987 */             tmpBundle.add(new AlphaUnlock());
/*  988 */             tmpBundle.add(new MentalFortressUnlock());
/*      */             break;
/*      */           case 2:
/*  991 */             tmpBundle.add(new ClarityUnlock());
/*  992 */             tmpBundle.add(new WishUnlock());
/*  993 */             tmpBundle.add(new ForesightUnlock());
/*      */             break;
/*      */           case 3:
/*  996 */             tmpBundle.add(new AkabekoUnlock());
/*  997 */             tmpBundle.add(new YangUnlock());
/*  998 */             tmpBundle.add(new CeramicFishUnlock());
/*      */             break;
/*      */           case 4:
/* 1001 */             tmpBundle.add(new StrikeDummyUnlock());
/* 1002 */             tmpBundle.add(new TeardropUnlock());
/* 1003 */             tmpBundle.add(new CloakClaspUnlock());
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */     } 
/*      */     
/* 1011 */     return tmpBundle;
/*      */   }
/*      */   
/*      */   public static void addCardUnlockToList(HashMap<String, AbstractUnlock> map, String key, AbstractUnlock unlock) {
/* 1015 */     if (isCardLocked(key)) {
/* 1016 */       map.put(key, unlock);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void addRelicUnlockToList(HashMap<String, AbstractUnlock> map, String key, AbstractUnlock unlock) {
/* 1021 */     if (isRelicLocked(key)) {
/* 1022 */       map.put(key, unlock);
/*      */     }
/*      */   }
/*      */   
/*      */   public static float getCompletionPercentage() {
/* 1027 */     float totalPercent = 0.0F;
/* 1028 */     totalPercent += getAscensionProgress() * 0.3F;
/* 1029 */     totalPercent += getUnlockProgress() * 0.25F;
/* 1030 */     totalPercent += getAchievementProgress() * 0.35F;
/* 1031 */     totalPercent += getSeenCardsProgress() * 0.05F;
/* 1032 */     totalPercent += getSeenRelicsProgress() * 0.05F;
/* 1033 */     return totalPercent * 100.0F;
/*      */   }
/*      */   
/*      */   private static float getAscensionProgress() {
/* 1037 */     ArrayList<Prefs> allCharacterPrefs = CardCrawlGame.characterManager.getAllPrefs();
/* 1038 */     int sum = 0;
/* 1039 */     for (Prefs p : allCharacterPrefs) {
/* 1040 */       sum += p.getInteger("ASCENSION_LEVEL", 0);
/*      */     }
/*      */     
/* 1043 */     float retVal = sum / 60.0F;
/* 1044 */     logger.info("Ascension Progress: " + retVal);
/* 1045 */     if (retVal > 1.0F) {
/* 1046 */       retVal = 1.0F;
/*      */     }
/* 1048 */     return retVal;
/*      */   }
/*      */   
/*      */   private static float getUnlockProgress() {
/* 1052 */     int sum = Math.min(getUnlockLevel(AbstractPlayer.PlayerClass.IRONCLAD), 5);
/* 1053 */     sum += Math.min(getUnlockLevel(AbstractPlayer.PlayerClass.THE_SILENT), 5);
/* 1054 */     sum += Math.min(getUnlockLevel(AbstractPlayer.PlayerClass.DEFECT), 5);
/* 1055 */     sum += Math.min(getUnlockLevel(AbstractPlayer.PlayerClass.WATCHER), 5);
/*      */     
/* 1057 */     float retVal = sum / 15.0F;
/* 1058 */     logger.info("Unlock IC: " + getUnlockLevel(AbstractPlayer.PlayerClass.IRONCLAD));
/* 1059 */     logger.info("Unlock Silent: " + getUnlockLevel(AbstractPlayer.PlayerClass.THE_SILENT));
/* 1060 */     logger.info("Unlock Defect: " + getUnlockLevel(AbstractPlayer.PlayerClass.DEFECT));
/* 1061 */     logger.info("Unlock Watcher: " + getUnlockLevel(AbstractPlayer.PlayerClass.WATCHER));
/* 1062 */     logger.info("Unlock Progress: " + retVal);
/* 1063 */     if (retVal > 1.0F) {
/* 1064 */       retVal = 1.0F;
/*      */     }
/* 1066 */     return retVal;
/*      */   }
/*      */   
/*      */   private static float getAchievementProgress() {
/* 1070 */     int sum = 0;
/* 1071 */     for (AchievementItem item : StatsScreen.achievements.items) {
/* 1072 */       if (item.isUnlocked) {
/* 1073 */         sum++;
/*      */       }
/*      */     } 
/*      */     
/* 1077 */     float retVal = sum / StatsScreen.achievements.items.size();
/* 1078 */     logger.info("Achievement Progress: " + retVal);
/* 1079 */     if (retVal > 1.0F) {
/* 1080 */       retVal = 1.0F;
/*      */     }
/* 1082 */     return retVal;
/*      */   }
/*      */   
/*      */   private static float getSeenCardsProgress() {
/* 1086 */     int sum = 0;
/* 1087 */     for (Map.Entry<String, AbstractCard> c : (Iterable<Map.Entry<String, AbstractCard>>)CardLibrary.cards.entrySet()) {
/* 1088 */       if (((AbstractCard)c.getValue()).isSeen) {
/* 1089 */         sum++;
/*      */       }
/*      */     } 
/*      */     
/* 1093 */     float retVal = sum / CardLibrary.cards.size();
/* 1094 */     logger.info("Seen Cards Progress: " + retVal);
/* 1095 */     if (retVal > 1.0F) {
/* 1096 */       retVal = 1.0F;
/*      */     }
/* 1098 */     return retVal;
/*      */   }
/*      */   
/*      */   private static float getSeenRelicsProgress() {
/* 1102 */     float retVal = RelicLibrary.seenRelics / RelicLibrary.totalRelicCount;
/* 1103 */     logger.info("Seen Relics Progress: " + retVal);
/* 1104 */     if (retVal > 1.0F) {
/* 1105 */       retVal = 1.0F;
/*      */     }
/* 1107 */     return retVal;
/*      */   }
/*      */   
/*      */   public static long getTotalPlaytime() {
/* 1111 */     return Settings.totalPlayTime;
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\unlock\UnlockTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */