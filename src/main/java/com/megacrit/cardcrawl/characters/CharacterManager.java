/*     */ package com.megacrit.cardcrawl.characters;
/*     */ 
/*     */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharacterManager
/*     */ {
/*  19 */   private static final Logger logger = LogManager.getLogger(CharacterManager.class.getName());
/*  20 */   private static ArrayList<AbstractPlayer> masterCharacterList = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public CharacterManager() {
/*  24 */     if (masterCharacterList.isEmpty()) {
/*  25 */       masterCharacterList.add(new Ironclad(CardCrawlGame.playerName));
/*  26 */       masterCharacterList.add(new TheSilent(CardCrawlGame.playerName));
/*  27 */       masterCharacterList.add(new Defect(CardCrawlGame.playerName));
/*  28 */       masterCharacterList.add(new Watcher(CardCrawlGame.playerName));
/*     */     } else {
/*     */       
/*  31 */       for (AbstractPlayer c : masterCharacterList) {
/*  32 */         c.loadPrefs();
/*     */       }
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
/*     */   public AbstractPlayer setChosenCharacter(AbstractPlayer.PlayerClass c) {
/*  45 */     for (AbstractPlayer character : masterCharacterList) {
/*  46 */       if (character.chosenClass == c) {
/*  47 */         AbstractDungeon.player = character;
/*  48 */         return character;
/*     */       } 
/*     */     } 
/*  51 */     logger.error("The character " + c.name() + " does not exist in the CharacterManager's master character list");
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean anySaveFileExists() {
/*  59 */     for (AbstractPlayer character : masterCharacterList) {
/*  60 */       if (character.saveFileExists()) {
/*  61 */         return true;
/*     */       }
/*     */     } 
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPlayer loadChosenCharacter() {
/*  71 */     for (AbstractPlayer character : masterCharacterList) {
/*  72 */       if (character.saveFileExists()) {
/*  73 */         AbstractDungeon.player = character;
/*  74 */         return character;
/*     */       } 
/*     */     } 
/*  77 */     logger.info("No character save file was found!");
/*  78 */     return null;
/*     */   }
/*     */   
/*     */   public ArrayList<CharStat> getAllCharacterStats() {
/*  82 */     ArrayList<CharStat> allCharStats = new ArrayList<>();
/*  83 */     for (AbstractPlayer c : masterCharacterList) {
/*  84 */       allCharStats.add(c.getCharStat());
/*     */     }
/*  86 */     return allCharStats;
/*     */   }
/*     */   
/*     */   public void refreshAllCharStats() {
/*  90 */     for (AbstractPlayer c : masterCharacterList) {
/*  91 */       c.refreshCharStat();
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList<Prefs> getAllPrefs() {
/*  96 */     ArrayList<Prefs> allPrefs = new ArrayList<>();
/*  97 */     for (AbstractPlayer c : masterCharacterList) {
/*  98 */       allPrefs.add(c.getPrefs());
/*     */     }
/* 100 */     return allPrefs;
/*     */   }
/*     */   
/*     */   public AbstractPlayer getRandomCharacter(Random rng) {
/* 104 */     int index = rng.random(masterCharacterList.size() - 1);
/* 105 */     return masterCharacterList.get(index);
/*     */   }
/*     */   
/*     */   public AbstractPlayer recreateCharacter(AbstractPlayer.PlayerClass p) {
/* 109 */     for (AbstractPlayer old : masterCharacterList) {
/* 110 */       if (old.chosenClass == p) {
/* 111 */         AbstractPlayer newChar = old.newInstance();
/* 112 */         masterCharacterList.set(masterCharacterList.indexOf(old), newChar);
/* 113 */         old.dispose();
/* 114 */         logger.info("Successfully recreated " + newChar.chosenClass.name());
/* 115 */         return newChar;
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public AbstractPlayer getCharacter(AbstractPlayer.PlayerClass c) {
/* 123 */     for (AbstractPlayer character : masterCharacterList) {
/* 124 */       if (character.chosenClass == c) {
/* 125 */         return character;
/*     */       }
/*     */     } 
/* 128 */     logger.error("The character " + c.name() + " does not exist in the CharacterManager's master character list");
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public ArrayList<AbstractPlayer> getAllCharacters() {
/* 133 */     return masterCharacterList;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\characters\CharacterManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */