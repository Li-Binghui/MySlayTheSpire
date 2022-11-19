/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.integrations.DistributorFactory;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.screens.mainMenu.SaveSlotScreen;
import com.megacrit.cardcrawl.vfx.GameSavedEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SaveHelper
/*     */ {
/*  29 */   private static final Logger logger = LogManager.getLogger(SaveHelper.class.getName());
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initialize() {}
/*     */ 
/*     */   
/*     */   private static Boolean isGog() {
/*  37 */     return Boolean.valueOf((CardCrawlGame.publisherIntegration.getType() == DistributorFactory.Distributor.GOG));
/*     */   }
/*     */   
/*     */   private static String getSaveDir() {
/*  41 */     if (Settings.isBeta || isGog().booleanValue()) {
/*  42 */       return "betaPreferences";
/*     */     }
/*  44 */     return "preferences";
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean doesPrefExist(String name) {
/*  49 */     return Gdx.files.local(getSaveDir() + File.separator + name).exists();
/*     */   }
/*     */   
/*     */   public static void deletePrefs(int slot) {
/*  53 */     String dir = getSaveDir() + File.separator;
/*     */ 
/*     */     
/*  56 */     deleteFile(dir + slotName("STSDataVagabond", slot));
/*  57 */     deleteFile(dir + slotName("STSDataTheSilent", slot));
/*  58 */     deleteFile(dir + slotName("STSDataDefect", slot));
/*  59 */     deleteFile(dir + slotName("STSDataWatcher", slot));
/*     */ 
/*     */     
/*  62 */     deleteFile(dir + slotName("STSAchievements", slot));
/*  63 */     deleteFile(dir + slotName("STSDaily", slot));
/*  64 */     deleteFile(dir + slotName("STSSeenBosses", slot));
/*  65 */     deleteFile(dir + slotName("STSSeenCards", slot));
/*  66 */     deleteFile(dir + slotName("STSBetaCardPreference", slot));
/*  67 */     deleteFile(dir + slotName("STSSeenRelics", slot));
/*  68 */     deleteFile(dir + slotName("STSUnlockProgress", slot));
/*  69 */     deleteFile(dir + slotName("STSUnlocks", slot));
/*     */ 
/*     */     
/*  72 */     deleteFile(dir + slotName("STSGameplaySettings", slot));
/*  73 */     deleteFile(dir + slotName("STSInputSettings", slot));
/*  74 */     deleteFile(dir + slotName("STSInputSettings_Controller", slot));
/*  75 */     deleteFile(dir + slotName("STSSound", slot));
/*     */ 
/*     */     
/*  78 */     deleteFile(dir + slotName("STSPlayer", slot));
/*  79 */     deleteFile(dir + slotName("STSTips", slot));
/*     */ 
/*     */     
/*  82 */     dir = "runs" + File.separator;
/*  83 */     deleteFolder(dir + slotName("IRONCLAD", slot));
/*  84 */     deleteFolder(dir + slotName("THE_SILENT", slot));
/*  85 */     deleteFolder(dir + slotName("DEFECT", slot));
/*  86 */     deleteFolder(dir + slotName("WATCHER", slot));
/*  87 */     deleteFolder(dir + slotName("DAILY", slot));
/*     */ 
/*     */     
/*  90 */     dir = "saves" + File.separator;
/*  91 */     deleteFile(dir + slotName("IRONCLAD.autosave", slot));
/*  92 */     deleteFile(dir + slotName("DEFECT.autosave", slot));
/*  93 */     deleteFile(dir + slotName("THE_SILENT.autosave", slot));
/*  94 */     deleteFile(dir + slotName("WATCHER.autosave", slot));
/*  95 */     deleteFile(dir + slotName("IRONCLAD.autosave.backUp", slot));
/*  96 */     deleteFile(dir + slotName("DEFECT.autosave.backUp", slot));
/*  97 */     deleteFile(dir + slotName("THE_SILENT.autosave.backUp", slot));
/*  98 */     deleteFile(dir + slotName("WATCHER.autosave.backUp", slot));
/*     */     
/* 100 */     if (Settings.isBeta || isGog().booleanValue()) {
/* 101 */       deleteFile(dir + slotName("IRONCLAD.autosaveBETA", slot));
/* 102 */       deleteFile(dir + slotName("DEFECT.autosaveBETA", slot));
/* 103 */       deleteFile(dir + slotName("THE_SILENT.autosaveBETA", slot));
/* 104 */       deleteFile(dir + slotName("WATCHER.autosaveBETA", slot));
/* 105 */       deleteFile(dir + slotName("IRONCLAD.autosaveBETA.backUp", slot));
/* 106 */       deleteFile(dir + slotName("DEFECT.autosaveBETA.backUp", slot));
/* 107 */       deleteFile(dir + slotName("THE_SILENT.autosaveBETA.backUp", slot));
/* 108 */       deleteFile(dir + slotName("WATCHER.autosaveBETA.backUp", slot));
/*     */     } 
/*     */ 
/*     */     
/* 112 */     CardCrawlGame.saveSlotPref.putString(slotName("PROFILE_NAME", slot), "");
/* 113 */     CardCrawlGame.saveSlotPref.putFloat(slotName("COMPLETION", slot), 0.0F);
/* 114 */     CardCrawlGame.saveSlotPref.putLong(slotName("PLAYTIME", slot), 0L);
/* 115 */     CardCrawlGame.saveSlotPref.flush();
/*     */ 
/*     */     
/* 118 */     if (slot == CardCrawlGame.saveSlot || CardCrawlGame.saveSlot == -1) {
/* 119 */       String name = "";
/* 120 */       boolean newDefaultSet = false;
/*     */       
/* 122 */       for (int i = 0; i < 3; i++) {
/* 123 */         name = CardCrawlGame.saveSlotPref.getString(slotName("PROFILE_NAME", i), "");
/* 124 */         if (!name.equals("")) {
/* 125 */           logger.info("Current slot deleted, DEFAULT_SLOT is now " + i);
/* 126 */           CardCrawlGame.saveSlotPref.putInteger("DEFAULT_SLOT", i);
/* 127 */           newDefaultSet = true;
/* 128 */           SaveSlotScreen.slotDeleted = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 134 */       if (!newDefaultSet) {
/* 135 */         logger.info("All slots deleted, DEFAULT_SLOT is now -1");
/* 136 */         CardCrawlGame.saveSlotPref.putInteger("DEFAULT_SLOT", -1);
/*     */       } 
/*     */       
/* 139 */       CardCrawlGame.saveSlotPref.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void deleteFile(String fileName) {
/* 144 */     logger.info("Deleting " + fileName);
/* 145 */     if (Gdx.files.local(fileName).delete()) {
/* 146 */       logger.info(fileName + " deleted.");
/*     */     }
/*     */     
/* 149 */     if (Gdx.files.local(fileName + ".backUp").delete()) {
/* 150 */       logger.info(fileName + ".backUp deleted.");
/*     */     }
/*     */   }
/*     */   
/*     */   private static void deleteFolder(String dirName) {
/* 155 */     logger.info("Deleting " + dirName);
/* 156 */     if (Gdx.files.local(dirName).deleteDirectory()) {
/* 157 */       logger.info(dirName + " deleted.");
/*     */     }
/*     */   }
/*     */   
/*     */   public static String slotName(String name, int slot) {
/* 162 */     switch (slot) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 173 */         return name;
/*     */     } 
/*     */     name = slot + "_" + name;
/*     */
    return name;
} public static Prefs getPrefs(String name) {
/* 177 */     switch (CardCrawlGame.saveSlot) {
/*     */       case 0:
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 185 */         name = CardCrawlGame.saveSlot + "_" + name;
/*     */         break;
/*     */     } 
/*     */     
/* 189 */     Gson gson = new Gson();
/* 190 */     Prefs retVal = new Prefs();
/*     */ 
/*     */     
/* 193 */     Type type = (new TypeToken<Map<String, String>>() {  }).getType();
/*     */     
/* 195 */     String filepath = getSaveDir() + File.separator + name;
/*     */     
/* 197 */     String jsonStr = null;
/*     */     try {
/* 199 */       jsonStr = loadJson(filepath);
/* 200 */       if (jsonStr.isEmpty()) {
/* 201 */         logger.error("Empty Pref file: name=" + name + ", filepath=" + filepath);
/* 202 */         handleCorruption(jsonStr, filepath, name);
/* 203 */         retVal = getPrefs(name);
/*     */       } else {
/* 205 */         retVal.data = (Map<String, String>)gson.fromJson(jsonStr, type);
/*     */       } 
/* 207 */     } catch (JsonSyntaxException e) {
/* 208 */       logger.error("Corrupt Pref file", (Throwable)e);
/* 209 */       handleCorruption(jsonStr, filepath, name);
/* 210 */       retVal = getPrefs(name);
/*     */     } 
/* 212 */     retVal.filepath = filepath;
/* 213 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void handleCorruption(String jsonStr, String filepath, String name) {
/* 218 */     preserveCorruptFile(filepath);
/* 219 */     FileHandle backup = Gdx.files.local(filepath + ".backUp");
/* 220 */     if (backup.exists()) {
/* 221 */       backup.moveTo(Gdx.files.local(filepath));
/* 222 */       logger.info("Original corrupted, backup loaded for " + filepath);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preserveCorruptFile(String filePath) {
/* 230 */     FileHandle file = Gdx.files.local(filePath);
/* 231 */     FileHandle corruptFile = Gdx.files.local("sendToDevs" + File.separator + filePath + ".corrupt");
/* 232 */     file.moveTo(corruptFile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String loadJson(String filepath) {
/* 242 */     if (Gdx.files.local(filepath).exists()) {
/* 243 */       return Gdx.files.local(filepath).readString(String.valueOf(StandardCharsets.UTF_8));
/*     */     }
/* 245 */     Map<String, String> map = new HashMap<>();
/* 246 */     Gson gson = new Gson();
/* 247 */     AsyncSaver.save(filepath, gson.toJson(map));
/* 248 */     return "{}";
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean saveExists() {
/* 253 */     StringBuilder retVal = new StringBuilder();
/*     */     
/* 255 */     retVal.append(getSaveDir()).append(File.separator);
/*     */     
/* 257 */     switch (CardCrawlGame.saveSlot)
/*     */     { case 0:
/* 259 */         retVal.append("STSPlayer");
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
/* 273 */         return Gdx.files.local(retVal.toString()).exists();case 1: case 2: case 3: retVal.append(CardCrawlGame.saveSlot).append("_STSPlayer"); return Gdx.files.local(retVal.toString()).exists(); }  retVal.append("STSPlayer"); return Gdx.files.local(retVal.toString()).exists();
/*     */   }
/*     */   
/*     */   public static void saveIfAppropriate(SaveFile.SaveType saveType) {
/* 277 */     if (!shouldSave()) {
/*     */       return;
/*     */     }
/* 280 */     SaveFile saveFile = new SaveFile(saveType);
/* 281 */     SaveAndContinue.save(saveFile);
/* 282 */     AbstractDungeon.effectList.add(new GameSavedEffect());
/*     */   }
/*     */   
/*     */   public static boolean shouldSave() {
/* 286 */     if (AbstractDungeon.nextRoom != null && AbstractDungeon.nextRoom.getRoom() instanceof com.megacrit.cardcrawl.rooms.TrueVictoryRoom) {
/* 287 */       return false;
/*     */     }
/* 289 */     return !Settings.isDemo;
/*     */   }
/*     */   
/*     */   public static boolean shouldDeleteSave() {
/* 293 */     return !Settings.isDemo;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\SaveHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */