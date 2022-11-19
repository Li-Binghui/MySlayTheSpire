/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.megacrit.cardcrawl.blights.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.BotDataUploader;
import com.megacrit.cardcrawl.random.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlightHelper
/*     */ {
/*  28 */   private static final Logger logger = LogManager.getLogger(BlightHelper.class.getName());
/*  29 */   public static ArrayList<String> blights = new ArrayList<>();
/*  30 */   public static ArrayList<String> chestBlights = new ArrayList<>();
/*     */   
/*     */   public static void initialize() {
/*  33 */     blights.clear();
/*  34 */     blights.add("DeadlyEnemies");
/*  35 */     blights.add("ToughEnemies");
/*  36 */     blights.add("TimeMaze");
/*  37 */     blights.add("MimicInfestation");
/*  38 */     blights.add("FullBelly");
/*  39 */     blights.add("GrotesqueTrophy");
/*     */ 
/*     */     
/*  42 */     blights.add("Accursed");
/*  43 */     blights.add("Scatterbrain");
/*  44 */     blights.add("TwistingMind");
/*  45 */     blights.add("BlightedDurian");
/*  46 */     blights.add("VoidEssence");
/*  47 */     blights.add("GraspOfShadows");
/*  48 */     blights.add("MetallicRebirth");
/*     */     
/*  50 */     chestBlights.clear();
/*  51 */     chestBlights.add("Accursed");
/*  52 */     chestBlights.add("Scatterbrain");
/*  53 */     chestBlights.add("TwistingMind");
/*  54 */     chestBlights.add("BlightedDurian");
/*  55 */     chestBlights.add("VoidEssence");
/*  56 */     chestBlights.add("GraspOfShadows");
/*  57 */     chestBlights.add("MetallicRebirth");
/*     */   }
/*     */   
/*     */   public static AbstractBlight getBlight(String id) {
/*  61 */     switch (id) {
/*     */       
/*     */       case "DeadlyEnemies":
/*  64 */         return (AbstractBlight)new Spear();
/*     */       case "ToughEnemies":
/*  66 */         return (AbstractBlight)new Shield();
/*     */       case "TimeMaze":
/*  68 */         return (AbstractBlight)new TimeMaze();
/*     */       case "MimicInfestation":
/*  70 */         return (AbstractBlight)new MimicInfestation();
/*     */       case "FullBelly":
/*  72 */         return (AbstractBlight)new Muzzle();
/*     */       case "GrotesqueTrophy":
/*  74 */         return (AbstractBlight)new GrotesqueTrophy();
/*     */ 
/*     */       
/*     */       case "TwistingMind":
/*  78 */         return (AbstractBlight)new TwistingMind();
/*     */       case "Scatterbrain":
/*  80 */         return (AbstractBlight)new Scatterbrain();
/*     */       case "Accursed":
/*  82 */         return (AbstractBlight)new Accursed();
/*     */       case "BlightedDurian":
/*  84 */         return (AbstractBlight)new Durian();
/*     */       case "VoidEssence":
/*  86 */         return (AbstractBlight)new VoidEssence();
/*     */       case "GraspOfShadows":
/*  88 */         return (AbstractBlight)new Hauntings();
/*     */       case "MetallicRebirth":
/*  90 */         return (AbstractBlight)new AncientAugmentation();
/*     */     } 
/*  92 */     logger.info("MISSING KEY: " + id);
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbstractBlight getRandomChestBlight(ArrayList<String> exclusion) {
/*  98 */     ArrayList<String> blightTmp = new ArrayList<>();
/*     */ 
/*     */     
/* 101 */     for (String s : chestBlights) {
/* 102 */       boolean exclude = false;
/* 103 */       for (String s2 : exclusion) {
/* 104 */         if (s.equals(s2)) {
/* 105 */           logger.info(s + " EXCLUDED");
/* 106 */           exclude = true;
/*     */         } 
/*     */       } 
/*     */       
/* 110 */       if (!exclude) {
/* 111 */         blightTmp.add(s);
/*     */       }
/*     */     } 
/*     */     
/* 115 */     String randomKey = blightTmp.get(AbstractDungeon.relicRng.random(blightTmp.size() - 1));
/* 116 */     return getBlight(randomKey);
/*     */   }
/*     */   
/*     */   public static AbstractBlight getRandomBlight(Random rng) {
/* 120 */     String randomKey = blights.get(rng.random(blights.size() - 1));
/* 121 */     return getBlight(randomKey);
/*     */   }
/*     */   
/*     */   public static AbstractBlight getRandomBlight() {
/* 125 */     String randomKey = chestBlights.get(AbstractDungeon.relicRng.random(chestBlights.size() - 1));
/* 126 */     if (AbstractDungeon.player.maxHealth <= 20) {
/* 127 */       while (randomKey.equals("BlightedDurian")) {
/* 128 */         randomKey = chestBlights.get(AbstractDungeon.relicRng.random(chestBlights.size() - 1));
/*     */       }
/*     */     }
/* 131 */     return getBlight(randomKey);
/*     */   }
/*     */   
/*     */   public static void uploadBlightData() {
/* 135 */     ArrayList<String> data = new ArrayList<>();
/*     */     
/* 137 */     if (blights.isEmpty()) {
/* 138 */       initialize();
/*     */     }
/*     */     
/* 141 */     ArrayList<String> allBlights = new ArrayList<>(blights);
/* 142 */     allBlights.addAll(chestBlights);
/*     */     
/* 144 */     for (String b : allBlights) {
/* 145 */       AbstractBlight blight = getBlight(b);
/* 146 */       if (blight != null) {
/* 147 */         data.add(blight.gameDataUploadData());
/*     */       }
/*     */     } 
/* 150 */     BotDataUploader.uploadDataAsync(BotDataUploader.GameDataType.BLIGHT_DATA, AbstractBlight.gameDataUploadHeader(), data);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\BlightHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */