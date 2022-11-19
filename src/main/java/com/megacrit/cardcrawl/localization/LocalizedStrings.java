/*     */ package com.megacrit.cardcrawl.localization;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Type;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalizedStrings
/*     */ {
/*  20 */   private static final Logger logger = LogManager.getLogger(LocalizedStrings.class.getName());
/*     */   private static final String LOCALIZATION_DIR = "localization";
/*  22 */   public static String PERIOD = null;
/*     */   private static Map<String, MonsterStrings> monsters;
/*     */   private static Map<String, PowerStrings> powers;
/*     */   private static Map<String, CardStrings> cards;
/*     */   private static Map<String, RelicStrings> relics;
/*     */   private static Map<String, EventStrings> events;
/*     */   private static Map<String, PotionStrings> potions;
/*     */   private static Map<String, CreditStrings> credits;
/*     */   private static Map<String, TutorialStrings> tutorials;
/*     */   private static Map<String, KeywordStrings> keywords;
/*     */   private static Map<String, ScoreBonusStrings> scoreBonuses;
/*     */   private static Map<String, CharacterStrings> characters;
/*     */   private static Map<String, UIStrings> ui;
/*     */   private static Map<String, OrbStrings> orb;
/*     */   private static Map<String, StanceStrings> stance;
/*     */   public static Map<String, RunModStrings> mod;
/*     */   private static Map<String, BlightStrings> blights;
/*     */   private static Map<String, AchievementStrings> achievements;
/*  40 */   public static String break_chars = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalizedStrings() {
/*     */     String langPackDir;
/*  47 */     long startTime = System.currentTimeMillis();
/*     */     
/*  49 */     Gson gson = new Gson();
/*     */ 
/*     */     
/*  52 */     switch (Settings.language) {
/*     */       case ENG:
/*  54 */         langPackDir = "localization" + File.separator + "eng";
/*     */         break;
/*     */       case DUT:
/*  57 */         langPackDir = "localization" + File.separator + "dut";
/*     */         break;
/*     */       case EPO:
/*  60 */         langPackDir = "localization" + File.separator + "epo";
/*     */         break;
/*     */       case PTB:
/*  63 */         langPackDir = "localization" + File.separator + "ptb";
/*     */         break;
/*     */       case ZHS:
/*  66 */         langPackDir = "localization" + File.separator + "zhs";
/*     */         break;
/*     */       case ZHT:
/*  69 */         langPackDir = "localization" + File.separator + "zht";
/*     */         break;
/*     */       case FIN:
/*  72 */         langPackDir = "localization" + File.separator + "fin";
/*     */         break;
/*     */       case FRA:
/*  75 */         langPackDir = "localization" + File.separator + "fra";
/*     */         break;
/*     */       case DEU:
/*  78 */         langPackDir = "localization" + File.separator + "deu";
/*     */         break;
/*     */       case GRE:
/*  81 */         langPackDir = "localization" + File.separator + "gre";
/*     */         break;
/*     */       case IND:
/*  84 */         langPackDir = "localization" + File.separator + "ind";
/*     */         break;
/*     */       case ITA:
/*  87 */         langPackDir = "localization" + File.separator + "ita";
/*     */         break;
/*     */       case JPN:
/*  90 */         if (Settings.isConsoleBuild) {
/*  91 */           langPackDir = "localization" + File.separator + "jpn"; break;
/*     */         } 
/*  93 */         langPackDir = "localization" + File.separator + "jpn2";
/*     */         break;
/*     */       
/*     */       case KOR:
/*  97 */         langPackDir = "localization" + File.separator + "kor";
/*     */         break;
/*     */       case NOR:
/* 100 */         langPackDir = "localization" + File.separator + "nor";
/*     */         break;
/*     */       case POL:
/* 103 */         langPackDir = "localization" + File.separator + "pol";
/*     */         break;
/*     */       case RUS:
/* 106 */         langPackDir = "localization" + File.separator + "rus";
/*     */         break;
/*     */       case SPA:
/* 109 */         langPackDir = "localization" + File.separator + "spa";
/*     */         break;
/*     */       case SRP:
/* 112 */         langPackDir = "localization" + File.separator + "srp";
/*     */         break;
/*     */       case SRB:
/* 115 */         langPackDir = "localization" + File.separator + "srb";
/*     */         break;
/*     */       case THA:
/* 118 */         langPackDir = "localization" + File.separator + "tha";
/*     */         break;
/*     */       case TUR:
/* 121 */         langPackDir = "localization" + File.separator + "tur";
/*     */         break;
/*     */       case UKR:
/* 124 */         langPackDir = "localization" + File.separator + "ukr";
/*     */         break;
/*     */       case VIE:
/* 127 */         langPackDir = "localization" + File.separator + "vie";
/*     */         break;
/*     */       case WWW:
/* 130 */         langPackDir = "localization" + File.separator + "www";
/*     */         break;
/*     */       default:
/* 133 */         langPackDir = "localization" + File.separator + "www";
/*     */         break;
/*     */     } 
/* 136 */     String monsterPath = langPackDir + File.separator + "monsters.json";
/*     */     
/* 138 */     Type monstersType = (new TypeToken<Map<String, MonsterStrings>>() {  }).getType();
/* 139 */     monsters = (Map<String, MonsterStrings>)gson.fromJson(loadJson(monsterPath), monstersType);
/*     */     
/* 141 */     String powerPath = langPackDir + File.separator + "powers.json";
/*     */     
/* 143 */     Type powerType = (new TypeToken<Map<String, PowerStrings>>() {  }).getType();
/* 144 */     powers = (Map<String, PowerStrings>)gson.fromJson(loadJson(powerPath), powerType);
/*     */     
/* 146 */     String cardPath = langPackDir + File.separator + "cards.json";
/*     */     
/* 148 */     Type cardType = (new TypeToken<Map<String, CardStrings>>() {  }).getType();
/* 149 */     cards = (Map<String, CardStrings>)gson.fromJson(loadJson(cardPath), cardType);
/*     */     
/* 151 */     String relicPath = langPackDir + File.separator + "relics.json";
/*     */     
/* 153 */     Type relicType = (new TypeToken<Map<String, RelicStrings>>() {  }).getType();
/* 154 */     relics = (Map<String, RelicStrings>)gson.fromJson(loadJson(relicPath), relicType);
/*     */     
/* 156 */     String eventPath = langPackDir + File.separator + "events.json";
/*     */     
/* 158 */     Type eventType = (new TypeToken<Map<String, EventStrings>>() {  }).getType();
/* 159 */     events = (Map<String, EventStrings>)gson.fromJson(loadJson(eventPath), eventType);
/*     */     
/* 161 */     String potionPath = langPackDir + File.separator + "potions.json";
/*     */     
/* 163 */     Type potionType = (new TypeToken<Map<String, PotionStrings>>() {  }).getType();
/* 164 */     potions = (Map<String, PotionStrings>)gson.fromJson(loadJson(potionPath), potionType);
/*     */     
/* 166 */     String creditPath = langPackDir + File.separator + "credits.json";
/*     */     
/* 168 */     Type creditType = (new TypeToken<Map<String, CreditStrings>>() {  }).getType();
/* 169 */     credits = (Map<String, CreditStrings>)gson.fromJson(loadJson(creditPath), creditType);
/*     */     
/* 171 */     String tutorialsPath = langPackDir + File.separator + "tutorials.json";
/*     */     
/* 173 */     Type tutorialType = (new TypeToken<Map<String, TutorialStrings>>() {  }).getType();
/* 174 */     tutorials = (Map<String, TutorialStrings>)gson.fromJson(loadJson(tutorialsPath), tutorialType);
/*     */     
/* 176 */     String keywordsPath = langPackDir + File.separator + "keywords.json";
/*     */     
/* 178 */     Type keywordsType = (new TypeToken<Map<String, KeywordStrings>>() {  }).getType();
/* 179 */     keywords = (Map<String, KeywordStrings>)gson.fromJson(loadJson(keywordsPath), keywordsType);
/*     */     
/* 181 */     String scoreBonusesPath = langPackDir + File.separator + "score_bonuses.json";
/*     */     
/* 183 */     Type scoreBonusType = (new TypeToken<Map<String, ScoreBonusStrings>>() {  }).getType();
/* 184 */     scoreBonuses = (Map<String, ScoreBonusStrings>)gson.fromJson(loadJson(scoreBonusesPath), scoreBonusType);
/*     */     
/* 186 */     String characterPath = langPackDir + File.separator + "characters.json";
/*     */     
/* 188 */     Type characterType = (new TypeToken<Map<String, CharacterStrings>>() {  }).getType();
/* 189 */     characters = (Map<String, CharacterStrings>)gson.fromJson(loadJson(characterPath), characterType);
/*     */     
/* 191 */     String uiPath = langPackDir + File.separator + "ui.json";
/*     */     
/* 193 */     Type uiType = (new TypeToken<Map<String, UIStrings>>() {  }).getType();
/* 194 */     ui = (Map<String, UIStrings>)gson.fromJson(loadJson(uiPath), uiType);
/* 195 */     PERIOD = (getUIString("Period")).TEXT[0];
/*     */     
/* 197 */     String orbPath = langPackDir + File.separator + "orbs.json";
/*     */     
/* 199 */     Type orbType = (new TypeToken<Map<String, OrbStrings>>() {  }).getType();
/* 200 */     orb = (Map<String, OrbStrings>)gson.fromJson(loadJson(orbPath), orbType);
/*     */     
/* 202 */     String stancePath = langPackDir + File.separator + "stances.json";
/*     */     
/* 204 */     Type stanceType = (new TypeToken<Map<String, StanceStrings>>() {  }).getType();
/* 205 */     stance = (Map<String, StanceStrings>)gson.fromJson(loadJson(stancePath), stanceType);
/*     */     
/* 207 */     String modPath = langPackDir + File.separator + "run_mods.json";
/*     */     
/* 209 */     Type modType = (new TypeToken<Map<String, RunModStrings>>() {  }).getType();
/* 210 */     mod = (Map<String, RunModStrings>)gson.fromJson(loadJson(modPath), modType);
/*     */     
/* 212 */     String blightPath = langPackDir + File.separator + "blights.json";
/*     */     
/* 214 */     Type blightType = (new TypeToken<Map<String, BlightStrings>>() {  }).getType();
/* 215 */     blights = (Map<String, BlightStrings>)gson.fromJson(loadJson(blightPath), blightType);
/*     */     
/* 217 */     String achievePath = langPackDir + File.separator + "achievements.json";
/*     */     
/* 219 */     Type achieveType = (new TypeToken<Map<String, AchievementStrings>>() {  }).getType();
/* 220 */     achievements = (Map<String, AchievementStrings>)gson.fromJson(loadJson(achievePath), achieveType);
/*     */     
/* 222 */     String lineBreakPath = langPackDir + File.separator + "line_break.json";
/* 223 */     if (Gdx.files.internal(lineBreakPath).exists()) {
/* 224 */       break_chars = Gdx.files.internal(lineBreakPath).readString(String.valueOf(StandardCharsets.UTF_8));
/*     */     }
/*     */     
/* 227 */     logger.info("Loc Strings load time: " + (System.currentTimeMillis() - startTime) + "ms");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PowerStrings getPowerStrings(String powerName) {
/* 236 */     if (powers.containsKey(powerName)) {
/* 237 */       return powers.get(powerName);
/*     */     }
/* 239 */     logger.info("[ERROR] PowerString: " + powerName + " not found");
/* 240 */     return PowerStrings.getMockPowerString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MonsterStrings getMonsterStrings(String monsterName) {
/* 250 */     if (monsters.containsKey(monsterName)) {
/* 251 */       return monsters.get(monsterName);
/*     */     }
/* 253 */     logger.info("[ERROR] MonsterString: " + monsterName + " not found");
/* 254 */     return MonsterStrings.getMockMonsterString();
/*     */   }
/*     */ 
/*     */   
/*     */   public EventStrings getEventString(String eventName) {
/* 259 */     if (events.containsKey(eventName)) {
/* 260 */       return events.get(eventName);
/*     */     }
/* 262 */     logger.info("[ERROR] EventString: " + eventName + " not found");
/* 263 */     return EventStrings.getMockEventString();
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionStrings getPotionString(String potionName) {
/* 268 */     if (potions.containsKey(potionName)) {
/* 269 */       return potions.get(potionName);
/*     */     }
/* 271 */     logger.info("[ERROR] PotionString: " + potionName + " not found");
/* 272 */     return PotionStrings.getMockPotionString();
/*     */   }
/*     */ 
/*     */   
/*     */   public CreditStrings getCreditString(String creditName) {
/* 277 */     if (credits.containsKey(creditName)) {
/* 278 */       return credits.get(creditName);
/*     */     }
/* 280 */     logger.info("[ERROR] CreditString: " + creditName + " not found");
/* 281 */     return CreditStrings.getMockCreditString();
/*     */   }
/*     */ 
/*     */   
/*     */   public TutorialStrings getTutorialString(String tutorialName) {
/* 286 */     if (tutorials.containsKey(tutorialName)) {
/* 287 */       return tutorials.get(tutorialName);
/*     */     }
/* 289 */     logger.info("[ERROR] TutorialString: " + tutorialName + " not found");
/* 290 */     return TutorialStrings.getMockTutorialString();
/*     */   }
/*     */ 
/*     */   
/*     */   public KeywordStrings getKeywordString(String keywordName) {
/* 295 */     return keywords.get(keywordName);
/*     */   }
/*     */   
/*     */   public CharacterStrings getCharacterString(String characterName) {
/* 299 */     return characters.get(characterName);
/*     */   }
/*     */   
/*     */   public UIStrings getUIString(String uiName) {
/* 303 */     return ui.get(uiName);
/*     */   }
/*     */   
/*     */   public OrbStrings getOrbString(String orbName) {
/* 307 */     if (orb.containsKey(orbName)) {
/* 308 */       return orb.get(orbName);
/*     */     }
/* 310 */     logger.info("[ERROR] OrbStrings: " + orbName + " not found");
/* 311 */     return OrbStrings.getMockOrbString();
/*     */   }
/*     */ 
/*     */   
/*     */   public StanceStrings getStanceString(String stanceName) {
/* 316 */     return stance.get(stanceName);
/*     */   }
/*     */   
/*     */   public RunModStrings getRunModString(String modName) {
/* 320 */     if (mod.containsKey(modName)) {
/* 321 */       return mod.get(modName);
/*     */     }
/* 323 */     logger.info("[ERROR] RunModStrings: " + modName + " not found");
/* 324 */     return RunModStrings.getMockModString();
/*     */   }
/*     */ 
/*     */   
/*     */   public BlightStrings getBlightString(String blightName) {
/* 329 */     if (blights.containsKey(blightName)) {
/* 330 */       return blights.get(blightName);
/*     */     }
/* 332 */     logger.info("[ERROR] BlightStrings: " + blightName + " not found");
/* 333 */     return BlightStrings.getBlightOrbString();
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreBonusStrings getScoreString(String scoreName) {
/* 338 */     if (scoreBonuses.containsKey(scoreName)) {
/* 339 */       return scoreBonuses.get(scoreName);
/*     */     }
/* 341 */     logger.info("[ERROR] ScoreBonusStrings: " + scoreName + " not found");
/* 342 */     return ScoreBonusStrings.getScoreBonusString();
/*     */   }
/*     */ 
/*     */   
/*     */   public AchievementStrings getAchievementString(String achievementName) {
/* 347 */     return achievements.get(achievementName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CardStrings getCardStrings(String cardName) {
/* 356 */     if (cards.containsKey(cardName)) {
/* 357 */       return cards.get(cardName);
/*     */     }
/* 359 */     logger.info("[ERROR] CardString: " + cardName + " not found");
/* 360 */     return CardStrings.getMockCardString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] createMockStringArray(int size) {
/* 371 */     String[] retVal = new String[size];
/* 372 */     for (int i = 0; i < retVal.length; i++) {
/* 373 */       retVal[i] = "[MISSING_" + i + "]";
/*     */     }
/* 375 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RelicStrings getRelicStrings(String relicName) {
/* 384 */     return relics.get(relicName);
/*     */   }
/*     */   
/*     */   private static String loadJson(String jsonPath) {
/* 388 */     return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\localization\LocalizedStrings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */