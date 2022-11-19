/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.daily.mods.*;
import com.megacrit.cardcrawl.metrics.BotDataUploader;

import java.util.*;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModHelper
/*     */ {
/*  48 */   private static HashMap<String, AbstractDailyMod> starterMods = new HashMap<>();
/*  49 */   private static HashMap<String, AbstractDailyMod> genericMods = new HashMap<>();
/*  50 */   private static HashMap<String, AbstractDailyMod> difficultyMods = new HashMap<>();
/*     */   
/*  52 */   private static HashMap<String, AbstractDailyMod> legacyMods = new HashMap<>();
/*  53 */   public static ArrayList<AbstractDailyMod> enabledMods = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public static void initialize() {
/*  57 */     addStarterMod((AbstractDailyMod)new Shiny());
/*  58 */     addStarterMod((AbstractDailyMod)new Allstar());
/*  59 */     addStarterMod((AbstractDailyMod)new Draft());
/*  60 */     addStarterMod((AbstractDailyMod)new SealedDeck());
/*  61 */     addStarterMod((AbstractDailyMod)new Insanity());
/*  62 */     addStarterMod((AbstractDailyMod)new Heirloom());
/*  63 */     addStarterMod((AbstractDailyMod)new Specialized());
/*  64 */     addStarterMod((AbstractDailyMod)new Chimera());
/*  65 */     addStarterMod((AbstractDailyMod)new CursedRun());
/*     */ 
/*     */     
/*  68 */     addGenericMod((AbstractDailyMod)new Diverse());
/*  69 */     addGenericMod((AbstractDailyMod)new RedCards());
/*  70 */     addGenericMod((AbstractDailyMod)new GreenCards());
/*  71 */     addGenericMod((AbstractDailyMod)new BlueCards());
/*  72 */     addGenericMod((AbstractDailyMod)new PurpleCards());
/*  73 */     addGenericMod((AbstractDailyMod)new ColorlessCards());
/*  74 */     addGenericMod((AbstractDailyMod)new TimeDilation());
/*  75 */     addGenericMod((AbstractDailyMod)new Vintage());
/*  76 */     addGenericMod((AbstractDailyMod)new Hoarder());
/*  77 */     addGenericMod((AbstractDailyMod)new Flight());
/*  78 */     addGenericMod((AbstractDailyMod)new CertainFuture());
/*  79 */     addGenericMod((AbstractDailyMod)new ControlledChaos());
/*     */ 
/*     */     
/*  82 */     addDifficultyMod((AbstractDailyMod)new BigGameHunter());
/*  83 */     addDifficultyMod((AbstractDailyMod)new Lethality());
/*  84 */     addDifficultyMod((AbstractDailyMod)new NightTerrors());
/*  85 */     addDifficultyMod((AbstractDailyMod)new Binary());
/*  86 */     addDifficultyMod((AbstractDailyMod)new Midas());
/*  87 */     addDifficultyMod((AbstractDailyMod)new Terminal());
/*  88 */     addDifficultyMod((AbstractDailyMod)new DeadlyEvents());
/*     */ 
/*     */     
/*  91 */     addLegacyMod((AbstractDailyMod)new Brewmaster());
/*  92 */     addLegacyMod((AbstractDailyMod)new Colossus());
/*     */   }
/*     */   
/*     */   private static void addStarterMod(AbstractDailyMod mod) {
/*  96 */     starterMods.put(mod.modID, mod);
/*     */   }
/*     */   
/*     */   private static void addGenericMod(AbstractDailyMod mod) {
/* 100 */     genericMods.put(mod.modID, mod);
/*     */   }
/*     */   
/*     */   private static void addDifficultyMod(AbstractDailyMod mod) {
/* 104 */     difficultyMods.put(mod.modID, mod);
/*     */   }
/*     */   
/*     */   private static void addLegacyMod(AbstractDailyMod mod) {
/* 108 */     legacyMods.put(mod.modID, mod);
/*     */   }
/*     */   
/*     */   public static void setMods(List<String> modIDs) {
/* 112 */     setModsFalse();
/*     */     
/* 114 */     for (String m : modIDs) {
/* 115 */       if (!m.equals("Endless")) {
/* 116 */         enabledMods.add(getMod(m));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AbstractDailyMod getMod(String key) {
/* 122 */     AbstractDailyMod mod = starterMods.get(key);
/* 123 */     if (mod == null) {
/* 124 */       mod = genericMods.get(key);
/*     */     }
/* 126 */     if (mod == null) {
/* 127 */       mod = difficultyMods.get(key);
/*     */     }
/* 129 */     if (mod == null) {
/* 130 */       mod = legacyMods.get(key);
/*     */     }
/* 132 */     return mod;
/*     */   }
/*     */   
/*     */   public static ArrayList<String> getEnabledModIDs() {
/* 136 */     ArrayList<String> enabled = new ArrayList<>();
/* 137 */     for (AbstractDailyMod m : enabledMods) {
/* 138 */       if (m != null) {
/* 139 */         enabled.add(m.modID);
/*     */       }
/*     */     } 
/* 142 */     return enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setTheMods(HashMap<String, AbstractDailyMod> modMap, long daysSince1970, AbstractPlayer.PlayerClass characterClass) {
/* 149 */     ArrayList<AbstractDailyMod> shuffledList = new ArrayList<>();
/*     */     
/* 151 */     for (Map.Entry<String, AbstractDailyMod> m : modMap.entrySet()) {
/*     */       
/* 153 */       if (((AbstractDailyMod)m.getValue()).classToExclude != characterClass) {
/* 154 */         shuffledList.add(m.getValue());
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     int rotationConstant = 5;
/*     */ 
/*     */ 
/*     */     
/* 167 */     int modSelectionIndex = (int)(daysSince1970 % rotationConstant);
/*     */ 
/*     */     
/* 170 */     if (modSelectionIndex < 0) {
/* 171 */       modSelectionIndex += rotationConstant;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     int shuffleInterval = (int)(daysSince1970 / rotationConstant);
/* 178 */     Collections.shuffle(shuffledList, new Random(shuffleInterval));
/*     */ 
/*     */     
/* 181 */     enabledMods.add(shuffledList.get(modSelectionIndex));
/*     */   }
/*     */   
/*     */   public static void setTodaysMods(long daysSince1970, AbstractPlayer.PlayerClass chosenClass) {
/* 185 */     setModsFalse();
/* 186 */     setTheMods(starterMods, daysSince1970, chosenClass);
/* 187 */     setTheMods(genericMods, daysSince1970, chosenClass);
/* 188 */     setTheMods(difficultyMods, daysSince1970, chosenClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isModEnabled(String modID) {
/* 195 */     for (AbstractDailyMod m : enabledMods) {
/* 196 */       if (m != null && m.modID != null && m.modID.equals(modID)) {
/* 197 */         return true;
/*     */       }
/*     */     } 
/* 200 */     return false;
/*     */   }
/*     */   
/*     */   public static void setModsFalse() {
/* 204 */     enabledMods.clear();
/*     */   }
/*     */   
/*     */   public static void uploadModData() {
/* 208 */     ArrayList<String> data = new ArrayList<>();
/*     */     
/* 210 */     for (Map.Entry<String, AbstractDailyMod> m : starterMods.entrySet()) {
/* 211 */       data.add(((AbstractDailyMod)m.getValue()).gameDataUploadData());
/*     */     }
/*     */     
/* 214 */     for (Map.Entry<String, AbstractDailyMod> m : genericMods.entrySet()) {
/* 215 */       data.add(((AbstractDailyMod)m.getValue()).gameDataUploadData());
/*     */     }
/*     */     
/* 218 */     for (Map.Entry<String, AbstractDailyMod> m : difficultyMods.entrySet()) {
/* 219 */       data.add(((AbstractDailyMod)m.getValue()).gameDataUploadData());
/*     */     }
/*     */     
/* 222 */     BotDataUploader.uploadDataAsync(BotDataUploader.GameDataType.DAILY_MOD_DATA, AbstractDailyMod.gameDataUploadHeader(), data);
/*     */   }
/*     */   
/*     */   public static void clearNulls() {
/* 226 */     for (Iterator<AbstractDailyMod> m = enabledMods.iterator(); m.hasNext(); ) {
/* 227 */       AbstractDailyMod derp = m.next();
/* 228 */       if (derp == null)
/* 229 */         m.remove(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\ModHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */