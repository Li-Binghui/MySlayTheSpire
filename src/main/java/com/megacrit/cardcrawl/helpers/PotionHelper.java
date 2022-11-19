/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.BotDataUploader;
import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.random.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PotionHelper
/*     */ {
/*  61 */   private static final Logger logger = LogManager.getLogger(PotionHelper.class.getName());
/*  62 */   public static ArrayList<String> potions = new ArrayList<>();
/*     */   
/*  64 */   public static int POTION_COMMON_CHANCE = 65;
/*  65 */   public static int POTION_UNCOMMON_CHANCE = 25;
/*     */ 
/*     */   
/*     */   public static void initialize(AbstractPlayer.PlayerClass chosenClass) {
/*  69 */     potions.clear();
/*  70 */     potions = getPotions(chosenClass, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<AbstractPotion> getPotionsByRarity(AbstractPotion.PotionRarity rarity) {
/*  80 */     ArrayList<AbstractPotion> retVal = new ArrayList<>();
/*     */     
/*  82 */     for (String s : getPotions(null, true)) {
/*  83 */       AbstractPotion p = getPotion(s);
/*  84 */       if (p.rarity == rarity) {
/*  85 */         retVal.add(p);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<String> getPotions(AbstractPlayer.PlayerClass c, boolean getAll) {
/* 100 */     ArrayList<String> retVal = new ArrayList<>();
/*     */     
/* 102 */     if (!getAll) {
/* 103 */       switch (c) {
/*     */         case IRONCLAD:
/* 105 */           retVal.add("BloodPotion");
/* 106 */           retVal.add("ElixirPotion");
/* 107 */           retVal.add("HeartOfIron");
/*     */           break;
/*     */         case THE_SILENT:
/* 110 */           retVal.add("Poison Potion");
/* 111 */           retVal.add("CunningPotion");
/* 112 */           retVal.add("GhostInAJar");
/*     */           break;
/*     */         case DEFECT:
/* 115 */           retVal.add("FocusPotion");
/* 116 */           retVal.add("PotionOfCapacity");
/* 117 */           retVal.add("EssenceOfDarkness");
/*     */           break;
/*     */         case WATCHER:
/* 120 */           retVal.add("BottledMiracle");
/* 121 */           retVal.add("StancePotion");
/* 122 */           retVal.add("Ambrosia");
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } else {
/* 129 */       retVal.add("BloodPotion");
/* 130 */       retVal.add("ElixirPotion");
/* 131 */       retVal.add("HeartOfIron");
/* 132 */       retVal.add("Poison Potion");
/* 133 */       retVal.add("CunningPotion");
/* 134 */       retVal.add("GhostInAJar");
/* 135 */       retVal.add("FocusPotion");
/* 136 */       retVal.add("PotionOfCapacity");
/* 137 */       retVal.add("EssenceOfDarkness");
/* 138 */       retVal.add("BottledMiracle");
/* 139 */       retVal.add("StancePotion");
/* 140 */       retVal.add("Ambrosia");
/*     */     } 
/*     */ 
/*     */     
/* 144 */     retVal.add("Block Potion");
/* 145 */     retVal.add("Dexterity Potion");
/* 146 */     retVal.add("Energy Potion");
/* 147 */     retVal.add("Explosive Potion");
/* 148 */     retVal.add("Fire Potion");
/* 149 */     retVal.add("Strength Potion");
/* 150 */     retVal.add("Swift Potion");
/* 151 */     retVal.add("Weak Potion");
/* 152 */     retVal.add("FearPotion");
/* 153 */     retVal.add("AttackPotion");
/* 154 */     retVal.add("SkillPotion");
/* 155 */     retVal.add("PowerPotion");
/* 156 */     retVal.add("ColorlessPotion");
/* 157 */     retVal.add("SteroidPotion");
/* 158 */     retVal.add("SpeedPotion");
/* 159 */     retVal.add("BlessingOfTheForge");
/*     */     
/* 161 */     retVal.add("Regen Potion");
/* 162 */     retVal.add("Ancient Potion");
/* 163 */     retVal.add("LiquidBronze");
/* 164 */     retVal.add("GamblersBrew");
/* 165 */     retVal.add("EssenceOfSteel");
/* 166 */     retVal.add("DuplicationPotion");
/* 167 */     retVal.add("DistilledChaos");
/* 168 */     retVal.add("LiquidMemories");
/*     */ 
/*     */     
/* 171 */     retVal.add("CultistPotion");
/* 172 */     retVal.add("Fruit Juice");
/* 173 */     retVal.add("SneckoOil");
/* 174 */     retVal.add("FairyPotion");
/* 175 */     retVal.add("SmokeBomb");
/* 176 */     retVal.add("EntropicBrew");
/*     */     
/* 178 */     return retVal;
/*     */   }
/*     */   
/*     */   public static AbstractPotion getRandomPotion(Random rng) {
/* 182 */     String randomKey = potions.get(rng.random(potions.size() - 1));
/* 183 */     return getPotion(randomKey);
/*     */   }
/*     */   
/*     */   public static AbstractPotion getRandomPotion() {
/* 187 */     String randomKey = potions.get(AbstractDungeon.potionRng.random(potions.size() - 1));
/* 188 */     return getPotion(randomKey);
/*     */   }
/*     */   
/*     */   public static boolean isAPotion(String key) {
/* 192 */     return getPotions(null, true).contains(key);
/*     */   }
/*     */   
/*     */   public static AbstractPotion getPotion(String name) {
/* 196 */     if (name == null || name.equals("")) {
/* 197 */       return null;
/*     */     }
/*     */     
/* 200 */     switch (name) {
/*     */       case "Ambrosia":
/* 202 */         return (AbstractPotion)new Ambrosia();
/*     */       case "BottledMiracle":
/* 204 */         return (AbstractPotion)new BottledMiracle();
/*     */       case "EssenceOfDarkness":
/* 206 */         return (AbstractPotion)new EssenceOfDarkness();
/*     */       case "Block Potion":
/* 208 */         return (AbstractPotion)new BlockPotion();
/*     */       case "Dexterity Potion":
/* 210 */         return (AbstractPotion)new DexterityPotion();
/*     */       case "Energy Potion":
/* 212 */         return (AbstractPotion)new EnergyPotion();
/*     */       case "Explosive Potion":
/* 214 */         return (AbstractPotion)new ExplosivePotion();
/*     */       case "Fire Potion":
/* 216 */         return (AbstractPotion)new FirePotion();
/*     */       case "Strength Potion":
/* 218 */         return (AbstractPotion)new StrengthPotion();
/*     */       case "Swift Potion":
/* 220 */         return (AbstractPotion)new SwiftPotion();
/*     */       case "Poison Potion":
/* 222 */         return (AbstractPotion)new PoisonPotion();
/*     */       case "Weak Potion":
/* 224 */         return (AbstractPotion)new WeakenPotion();
/*     */       case "FearPotion":
/* 226 */         return (AbstractPotion)new FearPotion();
/*     */       case "SkillPotion":
/* 228 */         return (AbstractPotion)new SkillPotion();
/*     */       case "PowerPotion":
/* 230 */         return (AbstractPotion)new PowerPotion();
/*     */       case "AttackPotion":
/* 232 */         return (AbstractPotion)new AttackPotion();
/*     */       case "ColorlessPotion":
/* 234 */         return (AbstractPotion)new ColorlessPotion();
/*     */       case "SteroidPotion":
/* 236 */         return (AbstractPotion)new SteroidPotion();
/*     */       case "SpeedPotion":
/* 238 */         return (AbstractPotion)new SpeedPotion();
/*     */       case "BlessingOfTheForge":
/* 240 */         return (AbstractPotion)new BlessingOfTheForge();
/*     */       
/*     */       case "PotionOfCapacity":
/* 243 */         return (AbstractPotion)new PotionOfCapacity();
/*     */       case "CunningPotion":
/* 245 */         return (AbstractPotion)new CunningPotion();
/*     */       case "DistilledChaos":
/* 247 */         return (AbstractPotion)new DistilledChaosPotion();
/*     */       case "Ancient Potion":
/* 249 */         return (AbstractPotion)new AncientPotion();
/*     */       case "Regen Potion":
/* 251 */         return (AbstractPotion)new RegenPotion();
/*     */       case "GhostInAJar":
/* 253 */         return (AbstractPotion)new GhostInAJar();
/*     */       case "FocusPotion":
/* 255 */         return (AbstractPotion)new FocusPotion();
/*     */       case "LiquidBronze":
/* 257 */         return (AbstractPotion)new LiquidBronze();
/*     */       case "LiquidMemories":
/* 259 */         return (AbstractPotion)new LiquidMemories();
/*     */       case "GamblersBrew":
/* 261 */         return (AbstractPotion)new GamblersBrew();
/*     */       case "EssenceOfSteel":
/* 263 */         return (AbstractPotion)new EssenceOfSteel();
/*     */       case "BloodPotion":
/* 265 */         return (AbstractPotion)new BloodPotion();
/*     */       case "StancePotion":
/* 267 */         return (AbstractPotion)new StancePotion();
/*     */       case "DuplicationPotion":
/* 269 */         return (AbstractPotion)new DuplicationPotion();
/*     */       case "ElixirPotion":
/* 271 */         return (AbstractPotion)new Elixir();
/*     */       
/*     */       case "CultistPotion":
/* 274 */         return (AbstractPotion)new CultistPotion();
/*     */       case "Fruit Juice":
/* 276 */         return (AbstractPotion)new FruitJuice();
/*     */       case "SneckoOil":
/* 278 */         return (AbstractPotion)new SneckoOil();
/*     */       case "FairyPotion":
/* 280 */         return (AbstractPotion)new FairyPotion();
/*     */       case "SmokeBomb":
/* 282 */         return (AbstractPotion)new SmokeBomb();
/*     */       case "EntropicBrew":
/* 284 */         return (AbstractPotion)new EntropicBrew();
/*     */       case "HeartOfIron":
/* 286 */         return (AbstractPotion)new HeartOfIron();
/*     */       
/*     */       case "Potion Slot":
/* 289 */         return null;
/*     */     } 
/* 291 */     logger.info("MISSING KEY: POTIONHELPER 37: " + name);
/* 292 */     return (AbstractPotion)new FirePotion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void uploadPotionData() {
/* 299 */     initialize(AbstractPlayer.PlayerClass.IRONCLAD);
/* 300 */     HashSet<String> ironcladPotions = new HashSet<>(potions);
/* 301 */     HashSet<String> sharedPotions = new HashSet<>(potions);
/*     */     
/* 303 */     initialize(AbstractPlayer.PlayerClass.THE_SILENT);
/* 304 */     HashSet<String> silentPotions = new HashSet<>(potions);
/* 305 */     sharedPotions.retainAll(potions);
/*     */     
/* 307 */     initialize(AbstractPlayer.PlayerClass.DEFECT);
/* 308 */     HashSet<String> defectPotions = new HashSet<>(potions);
/* 309 */     sharedPotions.retainAll(potions);
/*     */     
/* 311 */     initialize(AbstractPlayer.PlayerClass.WATCHER);
/* 312 */     HashSet<String> watcherPotions = new HashSet<>(potions);
/* 313 */     sharedPotions.retainAll(potions);
/*     */ 
/*     */     
/* 316 */     ironcladPotions.removeAll(sharedPotions);
/* 317 */     silentPotions.removeAll(sharedPotions);
/* 318 */     defectPotions.removeAll(sharedPotions);
/* 319 */     watcherPotions.removeAll(sharedPotions);
/*     */     
/* 321 */     potions.clear();
/*     */     
/* 323 */     ArrayList<String> data = new ArrayList<>();
/* 324 */     for (String id : ironcladPotions) {
/* 325 */       data.add(getPotion(id).getUploadData("RED"));
/*     */     }
/* 327 */     for (String id : silentPotions) {
/* 328 */       data.add(getPotion(id).getUploadData("GREEN"));
/*     */     }
/* 330 */     for (String id : defectPotions) {
/* 331 */       data.add(getPotion(id).getUploadData("BLUE"));
/*     */     }
/* 333 */     for (String id : watcherPotions) {
/* 334 */       data.add(getPotion(id).getUploadData("PURPLE"));
/*     */     }
/* 336 */     for (String id : sharedPotions) {
/* 337 */       data.add(getPotion(id).getUploadData("ALL"));
/*     */     }
/* 339 */     BotDataUploader.uploadDataAsync(BotDataUploader.GameDataType.POTION_DATA, AbstractPotion.gameDataUploadHeader(), data);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\PotionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */