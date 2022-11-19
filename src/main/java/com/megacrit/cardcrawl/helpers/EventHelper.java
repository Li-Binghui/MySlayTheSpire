/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.beyond.Falling;
/*     */ import com.megacrit.cardcrawl.events.beyond.MindBloom;
/*     */ import com.megacrit.cardcrawl.events.beyond.MoaiHead;
/*     */ import com.megacrit.cardcrawl.events.beyond.MysteriousSphere;
/*     */ import com.megacrit.cardcrawl.events.beyond.SecretPortal;
/*     */ import com.megacrit.cardcrawl.events.beyond.SensoryStone;
/*     */ import com.megacrit.cardcrawl.events.beyond.TombRedMask;
/*     */ import com.megacrit.cardcrawl.events.beyond.WindingHalls;
/*     */ import com.megacrit.cardcrawl.events.city.Addict;
/*     */ import com.megacrit.cardcrawl.events.city.BackToBasics;
/*     */ import com.megacrit.cardcrawl.events.city.Beggar;
/*     */ import com.megacrit.cardcrawl.events.city.Colosseum;
/*     */ import com.megacrit.cardcrawl.events.city.CursedTome;
/*     */ import com.megacrit.cardcrawl.events.city.DrugDealer;
/*     */ import com.megacrit.cardcrawl.events.city.ForgottenAltar;
/*     */ import com.megacrit.cardcrawl.events.city.Ghosts;
/*     */ import com.megacrit.cardcrawl.events.city.KnowingSkull;
/*     */ import com.megacrit.cardcrawl.events.city.MaskedBandits;
/*     */ import com.megacrit.cardcrawl.events.city.Nest;
/*     */ import com.megacrit.cardcrawl.events.city.TheJoust;
/*     */ import com.megacrit.cardcrawl.events.city.TheLibrary;
/*     */ import com.megacrit.cardcrawl.events.city.TheMausoleum;
/*     */ import com.megacrit.cardcrawl.events.city.Vampires;
/*     */ import com.megacrit.cardcrawl.events.exordium.BigFish;
/*     */ import com.megacrit.cardcrawl.events.exordium.Cleric;
/*     */ import com.megacrit.cardcrawl.events.exordium.DeadAdventurer;
/*     */ import com.megacrit.cardcrawl.events.exordium.GoldenIdolEvent;
/*     */ import com.megacrit.cardcrawl.events.exordium.GoldenWing;
/*     */ import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
/*     */ import com.megacrit.cardcrawl.events.exordium.LivingWall;
/*     */ import com.megacrit.cardcrawl.events.exordium.Mushrooms;
/*     */ import com.megacrit.cardcrawl.events.exordium.ScrapOoze;
/*     */ import com.megacrit.cardcrawl.events.exordium.ShiningLight;
/*     */ import com.megacrit.cardcrawl.events.exordium.Sssserpent;
/*     */ import com.megacrit.cardcrawl.events.shrines.AccursedBlacksmith;
/*     */ import com.megacrit.cardcrawl.events.shrines.Bonfire;
/*     */ import com.megacrit.cardcrawl.events.shrines.Designer;
/*     */ import com.megacrit.cardcrawl.events.shrines.Duplicator;
/*     */ import com.megacrit.cardcrawl.events.shrines.FaceTrader;
/*     */ import com.megacrit.cardcrawl.events.shrines.FountainOfCurseRemoval;
/*     */ import com.megacrit.cardcrawl.events.shrines.GoldShrine;
/*     */ import com.megacrit.cardcrawl.events.shrines.GremlinMatchGame;
/*     */ import com.megacrit.cardcrawl.events.shrines.GremlinWheelGame;
/*     */ import com.megacrit.cardcrawl.events.shrines.Lab;
/*     */ import com.megacrit.cardcrawl.events.shrines.Nloth;
/*     */ import com.megacrit.cardcrawl.events.shrines.NoteForYourself;
/*     */ import com.megacrit.cardcrawl.events.shrines.PurificationShrine;
/*     */ import com.megacrit.cardcrawl.events.shrines.Transmogrifier;
/*     */ import com.megacrit.cardcrawl.events.shrines.UpgradeShrine;
/*     */ import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
/*     */ import com.megacrit.cardcrawl.events.shrines.WomanInBlue;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventHelper
/*     */ {
/*  72 */   private static final Logger logger = LogManager.getLogger(EventHelper.class.getName());
/*     */   
/*     */   private static final float BASE_ELITE_CHANCE = 0.1F;
/*     */   
/*     */   private static final float BASE_MONSTER_CHANCE = 0.1F;
/*     */   
/*     */   private static final float BASE_SHOP_CHANCE = 0.03F;
/*     */   
/*     */   private static final float BASE_TREASURE_CHANCE = 0.02F;
/*     */   
/*     */   private static final float RAMP_ELITE_CHANCE = 0.1F;
/*     */   
/*     */   private static final float RAMP_MONSTER_CHANCE = 0.1F;
/*     */   
/*     */   private static final float RAMP_SHOP_CHANCE = 0.03F;
/*     */   
/*     */   private static final float RAMP_TREASURE_CHANCE = 0.02F;
/*     */   
/*     */   private static final float RESET_ELITE_CHANCE = 0.0F;
/*     */   private static final float RESET_MONSTER_CHANCE = 0.1F;
/*     */   private static final float RESET_SHOP_CHANCE = 0.03F;
/*     */   private static final float RESET_TREASURE_CHANCE = 0.02F;
/*  94 */   private static float ELITE_CHANCE = 0.1F;
/*  95 */   private static float MONSTER_CHANCE = 0.1F;
/*  96 */   private static float SHOP_CHANCE = 0.03F;
/*  97 */   public static float TREASURE_CHANCE = 0.02F;
/*     */   private static ArrayList<Float> saveFilePreviousChances;
/*     */   private static String saveFileLastEventChoice;
/*     */   
/*     */   public enum RoomResult
/*     */   {
/* 103 */     EVENT, ELITE, TREASURE, SHOP, MONSTER;
/*     */   }
/*     */   
/*     */   public static RoomResult roll() {
/* 107 */     return roll(AbstractDungeon.eventRng);
/*     */   }
/*     */   
/*     */   public static RoomResult roll(Random eventRng) {
/* 111 */     saveFilePreviousChances = getChances();
/* 112 */     float roll = eventRng.random();
/* 113 */     logger.info("Rolling for room type... EVENT_RNG_COUNTER: " + AbstractDungeon.eventRng.counter);
/*     */     
/* 115 */     boolean forceChest = false;
/* 116 */     if (AbstractDungeon.player.hasRelic("Tiny Chest")) {
/* 117 */       AbstractRelic r = AbstractDungeon.player.getRelic("Tiny Chest");
/* 118 */       r.counter++;
/* 119 */       if (r.counter == 4) {
/* 120 */         r.counter = 0;
/* 121 */         r.flash();
/* 122 */         forceChest = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     logger.info("ROLL: " + roll);
/* 134 */     logger.info("ELIT: " + ELITE_CHANCE);
/* 135 */     logger.info("MNST: " + MONSTER_CHANCE);
/* 136 */     logger.info("SHOP: " + SHOP_CHANCE);
/* 137 */     logger.info("TRSR: " + TREASURE_CHANCE);
/*     */     
/* 139 */     int eliteSize = 0;
/* 140 */     if (ModHelper.isModEnabled("DeadlyEvents")) {
/* 141 */       eliteSize = (int)(ELITE_CHANCE * 100.0F);
/*     */     }
/* 143 */     if (AbstractDungeon.floorNum < 6) {
/* 144 */       eliteSize = 0;
/*     */     }
/* 146 */     int monsterSize = (int)(MONSTER_CHANCE * 100.0F);
/* 147 */     int shopSize = (int)(SHOP_CHANCE * 100.0F);
/* 148 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom) {
/* 149 */       shopSize = 0;
/*     */     }
/* 151 */     int treasureSize = (int)(TREASURE_CHANCE * 100.0F);
/*     */ 
/*     */     
/* 154 */     int fillIndex = 0;
/*     */ 
/*     */     
/* 157 */     RoomResult[] possibleResults = new RoomResult[100];
/* 158 */     Arrays.fill((Object[])possibleResults, RoomResult.EVENT);
/*     */     
/* 160 */     if (ModHelper.isModEnabled("DeadlyEvents")) {
/*     */       
/* 162 */       Arrays.fill((Object[])possibleResults, 
/*     */           
/* 164 */           Math.min(99, fillIndex), 
/* 165 */           Math.min(100, fillIndex + eliteSize), RoomResult.ELITE);
/*     */       
/* 167 */       fillIndex += eliteSize;
/* 168 */       Arrays.fill((Object[])possibleResults, 
/*     */           
/* 170 */           Math.min(99, fillIndex), 
/* 171 */           Math.min(100, fillIndex + eliteSize), RoomResult.ELITE);
/*     */       
/* 173 */       fillIndex += eliteSize;
/*     */     } 
/*     */ 
/*     */     
/* 177 */     Arrays.fill((Object[])possibleResults, 
/*     */         
/* 179 */         Math.min(99, fillIndex), 
/* 180 */         Math.min(100, fillIndex + monsterSize), RoomResult.MONSTER);
/*     */     
/* 182 */     fillIndex += monsterSize;
/*     */ 
/*     */     
/* 185 */     Arrays.fill((Object[])possibleResults, Math.min(99, fillIndex), Math.min(100, fillIndex + shopSize), RoomResult.SHOP);
/* 186 */     fillIndex += shopSize;
/*     */ 
/*     */     
/* 189 */     Arrays.fill((Object[])possibleResults, 
/*     */         
/* 191 */         Math.min(99, fillIndex), 
/* 192 */         Math.min(100, fillIndex + treasureSize), RoomResult.TREASURE);
/*     */ 
/*     */     
/* 195 */     RoomResult choice = possibleResults[(int)(roll * 100.0F)];
/* 196 */     if (forceChest) {
/* 197 */       choice = RoomResult.TREASURE;
/*     */     }
/*     */ 
/*     */     
/* 201 */     if (choice == RoomResult.ELITE) {
/* 202 */       ELITE_CHANCE = 0.0F;
/* 203 */       if (ModHelper.isModEnabled("DeadlyEvents")) {
/* 204 */         ELITE_CHANCE = 0.1F;
/*     */       }
/*     */     } else {
/* 207 */       ELITE_CHANCE += 0.1F;
/*     */     } 
/*     */     
/* 210 */     if (choice == RoomResult.MONSTER) {
/* 211 */       if (AbstractDungeon.player.hasRelic("Juzu Bracelet")) {
/* 212 */         AbstractDungeon.player.getRelic("Juzu Bracelet").flash();
/* 213 */         choice = RoomResult.EVENT;
/*     */       } 
/* 215 */       MONSTER_CHANCE = 0.1F;
/*     */     } else {
/* 217 */       MONSTER_CHANCE += 0.1F;
/*     */     } 
/*     */     
/* 220 */     if (choice == RoomResult.SHOP) {
/* 221 */       SHOP_CHANCE = 0.03F;
/*     */     } else {
/* 223 */       SHOP_CHANCE += 0.03F;
/*     */     } 
/*     */     
/* 226 */     if (Settings.isEndless && AbstractDungeon.player.hasBlight("MimicInfestation")) {
/* 227 */       if (choice == RoomResult.TREASURE) {
/* 228 */         if (AbstractDungeon.player.hasRelic("Juzu Bracelet")) {
/* 229 */           AbstractDungeon.player.getRelic("Juzu Bracelet").flash();
/* 230 */           choice = RoomResult.EVENT;
/*     */         } else {
/* 232 */           choice = RoomResult.ELITE;
/*     */         } 
/* 234 */         TREASURE_CHANCE = 0.02F;
/* 235 */         if (ModHelper.isModEnabled("DeadlyEvents")) {
/* 236 */           TREASURE_CHANCE += 0.02F;
/*     */         }
/*     */       }
/*     */     
/* 240 */     } else if (choice == RoomResult.TREASURE) {
/* 241 */       TREASURE_CHANCE = 0.02F;
/*     */     } else {
/* 243 */       TREASURE_CHANCE += 0.02F;
/* 244 */       if (ModHelper.isModEnabled("DeadlyEvents")) {
/* 245 */         TREASURE_CHANCE += 0.02F;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 250 */     return choice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetProbabilities() {
/* 257 */     saveFilePreviousChances = null;
/* 258 */     ELITE_CHANCE = 0.0F;
/* 259 */     MONSTER_CHANCE = 0.1F;
/* 260 */     SHOP_CHANCE = 0.03F;
/* 261 */     TREASURE_CHANCE = 0.02F;
/*     */   }
/*     */   
/*     */   public static void setChances(ArrayList<Float> chances) {
/* 265 */     ELITE_CHANCE = ((Float)chances.get(0)).floatValue();
/* 266 */     MONSTER_CHANCE = ((Float)chances.get(1)).floatValue();
/* 267 */     SHOP_CHANCE = ((Float)chances.get(2)).floatValue();
/* 268 */     TREASURE_CHANCE = ((Float)chances.get(3)).floatValue();
/*     */   }
/*     */   
/*     */   public static ArrayList<Float> getChances() {
/* 272 */     ArrayList<Float> chances = new ArrayList<>();
/* 273 */     chances.add(Float.valueOf(ELITE_CHANCE));
/* 274 */     chances.add(Float.valueOf(MONSTER_CHANCE));
/* 275 */     chances.add(Float.valueOf(SHOP_CHANCE));
/* 276 */     chances.add(Float.valueOf(TREASURE_CHANCE));
/* 277 */     return chances;
/*     */   }
/*     */   
/*     */   public static ArrayList<Float> getChancesPreRoll() {
/* 281 */     if (saveFilePreviousChances != null) {
/* 282 */       return saveFilePreviousChances;
/*     */     }
/*     */     
/* 285 */     return getChances();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMostRecentEventID() {
/* 290 */     return saveFileLastEventChoice;
/*     */   }
/*     */ 
/*     */   
/*     */   public static AbstractEvent getEvent(String key) {
/* 295 */     if (Settings.isDev);
/*     */ 
/*     */ 
/*     */     
/* 299 */     saveFileLastEventChoice = key;
/* 300 */     switch (key) {
/*     */       
/*     */       case "Accursed Blacksmith":
/* 303 */         return (AbstractEvent)new AccursedBlacksmith();
/*     */       case "Bonfire Elementals":
/* 305 */         return (AbstractEvent)new Bonfire();
/*     */       case "Fountain of Cleansing":
/* 307 */         return (AbstractEvent)new FountainOfCurseRemoval();
/*     */       case "Designer":
/* 309 */         return (AbstractEvent)new Designer();
/*     */       case "Duplicator":
/* 311 */         return (AbstractEvent)new Duplicator();
/*     */       case "Lab":
/* 313 */         return (AbstractEvent)new Lab();
/*     */       case "Match and Keep!":
/* 315 */         return (AbstractEvent)new GremlinMatchGame();
/*     */       case "Golden Shrine":
/* 317 */         return (AbstractEvent)new GoldShrine();
/*     */       case "Purifier":
/* 319 */         return (AbstractEvent)new PurificationShrine();
/*     */       case "Transmorgrifier":
/* 321 */         return (AbstractEvent)new Transmogrifier();
/*     */       case "Wheel of Change":
/* 323 */         return (AbstractEvent)new GremlinWheelGame();
/*     */       case "Upgrade Shrine":
/* 325 */         return (AbstractEvent)new UpgradeShrine();
/*     */       case "FaceTrader":
/* 327 */         return (AbstractEvent)new FaceTrader();
/*     */       case "NoteForYourself":
/* 329 */         return (AbstractEvent)new NoteForYourself();
/*     */       case "WeMeetAgain":
/* 331 */         return (AbstractEvent)new WeMeetAgain();
/*     */       case "The Woman in Blue":
/* 333 */         return (AbstractEvent)new WomanInBlue();
/*     */ 
/*     */       
/*     */       case "Big Fish":
/* 337 */         return (AbstractEvent)new BigFish();
/*     */       case "The Cleric":
/* 339 */         return (AbstractEvent)new Cleric();
/*     */       case "Dead Adventurer":
/* 341 */         return (AbstractEvent)new DeadAdventurer();
/*     */       case "Golden Wing":
/* 343 */         return (AbstractEvent)new GoldenWing();
/*     */       case "Golden Idol":
/* 345 */         return (AbstractEvent)new GoldenIdolEvent();
/*     */       case "World of Goop":
/* 347 */         return (AbstractEvent)new GoopPuddle();
/*     */       case "Forgotten Altar":
/* 349 */         return (AbstractEvent)new ForgottenAltar();
/*     */       case "Scrap Ooze":
/* 351 */         return (AbstractEvent)new ScrapOoze();
/*     */       case "Liars Game":
/* 353 */         return (AbstractEvent)new Sssserpent();
/*     */       case "Living Wall":
/* 355 */         return (AbstractEvent)new LivingWall();
/*     */       case "Mushrooms":
/* 357 */         return (AbstractEvent)new Mushrooms();
/*     */       case "N'loth":
/* 359 */         return (AbstractEvent)new Nloth();
/*     */       case "Shining Light":
/* 361 */         return (AbstractEvent)new ShiningLight();
/*     */ 
/*     */       
/*     */       case "Vampires":
/* 365 */         return (AbstractEvent)new Vampires();
/*     */       case "Ghosts":
/* 367 */         return (AbstractEvent)new Ghosts();
/*     */       case "Addict":
/* 369 */         return (AbstractEvent)new Addict();
/*     */       case "Back to Basics":
/* 371 */         return (AbstractEvent)new BackToBasics();
/*     */       case "Beggar":
/* 373 */         return (AbstractEvent)new Beggar();
/*     */       case "Cursed Tome":
/* 375 */         return (AbstractEvent)new CursedTome();
/*     */       case "Drug Dealer":
/* 377 */         return (AbstractEvent)new DrugDealer();
/*     */       case "Knowing Skull":
/* 379 */         return (AbstractEvent)new KnowingSkull();
/*     */       case "Masked Bandits":
/* 381 */         return (AbstractEvent)new MaskedBandits();
/*     */       case "Nest":
/* 383 */         return (AbstractEvent)new Nest();
/*     */       case "The Library":
/* 385 */         return (AbstractEvent)new TheLibrary();
/*     */       case "The Mausoleum":
/* 387 */         return (AbstractEvent)new TheMausoleum();
/*     */       case "The Joust":
/* 389 */         return (AbstractEvent)new TheJoust();
/*     */       case "Colosseum":
/* 391 */         return (AbstractEvent)new Colosseum();
/*     */ 
/*     */       
/*     */       case "Mysterious Sphere":
/* 395 */         return (AbstractEvent)new MysteriousSphere();
/*     */       case "SecretPortal":
/* 397 */         return (AbstractEvent)new SecretPortal();
/*     */       case "Tomb of Lord Red Mask":
/* 399 */         return (AbstractEvent)new TombRedMask();
/*     */       case "Falling":
/* 401 */         return (AbstractEvent)new Falling();
/*     */       case "Winding Halls":
/* 403 */         return (AbstractEvent)new WindingHalls();
/*     */       case "The Moai Head":
/* 405 */         return (AbstractEvent)new MoaiHead();
/*     */       case "SensoryStone":
/* 407 */         return (AbstractEvent)new SensoryStone();
/*     */       case "MindBloom":
/* 409 */         return (AbstractEvent)new MindBloom();
/*     */     } 
/* 411 */     logger.info("---------------------------\nERROR: Unspecified key: " + key + " in EventHelper.\n---------------------------");
/*     */ 
/*     */     
/* 414 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getEventName(String key) {
/* 419 */     switch (key) {
/*     */       
/*     */       case "Accursed Blacksmith":
/* 422 */         return AccursedBlacksmith.NAME;
/*     */       case "Bonfire Elementals":
/* 424 */         return Bonfire.NAME;
/*     */       case "Fountain of Cleansing":
/* 426 */         return FountainOfCurseRemoval.NAME;
/*     */       case "Designer":
/* 428 */         return Designer.NAME;
/*     */       case "Duplicator":
/* 430 */         return Duplicator.NAME;
/*     */       case "Lab":
/* 432 */         return Lab.NAME;
/*     */       case "Match and Keep!":
/* 434 */         return GremlinMatchGame.NAME;
/*     */       case "Golden Shrine":
/* 436 */         return GoldShrine.NAME;
/*     */       case "Purifier":
/* 438 */         return PurificationShrine.NAME;
/*     */       case "Transmorgrifier":
/* 440 */         return Transmogrifier.NAME;
/*     */       case "Wheel of Change":
/* 442 */         return GremlinWheelGame.NAME;
/*     */       case "Upgrade Shrine":
/* 444 */         return UpgradeShrine.NAME;
/*     */       case "FaceTrader":
/* 446 */         return FaceTrader.NAME;
/*     */       case "NoteForYourself":
/* 448 */         return NoteForYourself.NAME;
/*     */       case "WeMeetAgain":
/* 450 */         return WeMeetAgain.NAME;
/*     */       case "The Woman in Blue":
/* 452 */         return WomanInBlue.NAME;
/*     */ 
/*     */       
/*     */       case "Big Fish":
/* 456 */         return BigFish.NAME;
/*     */       case "The Cleric":
/* 458 */         return Cleric.NAME;
/*     */       case "Dead Adventurer":
/* 460 */         return DeadAdventurer.NAME;
/*     */       case "Golden Wing":
/* 462 */         return GoldenWing.NAME;
/*     */       case "Golden Idol":
/* 464 */         return GoldenIdolEvent.NAME;
/*     */       case "World of Goop":
/* 466 */         return GoopPuddle.NAME;
/*     */       case "Forgotten Altar":
/* 468 */         return ForgottenAltar.NAME;
/*     */       case "Scrap Ooze":
/* 470 */         return ScrapOoze.NAME;
/*     */       case "Liars Game":
/* 472 */         return Sssserpent.NAME;
/*     */       case "Living Wall":
/* 474 */         return LivingWall.NAME;
/*     */       case "Mushrooms":
/* 476 */         return Mushrooms.NAME;
/*     */       case "N'loth":
/* 478 */         return Nloth.NAME;
/*     */       case "Shining Light":
/* 480 */         return ShiningLight.NAME;
/*     */ 
/*     */       
/*     */       case "Vampires":
/* 484 */         return Vampires.NAME;
/*     */       case "Ghosts":
/* 486 */         return Ghosts.NAME;
/*     */       case "Addict":
/* 488 */         return Addict.NAME;
/*     */       case "Back to Basics":
/* 490 */         return BackToBasics.NAME;
/*     */       case "Beggar":
/* 492 */         return Beggar.NAME;
/*     */       case "Cursed Tome":
/* 494 */         return CursedTome.NAME;
/*     */       case "Drug Dealer":
/* 496 */         return DrugDealer.NAME;
/*     */       case "Knowing Skull":
/* 498 */         return KnowingSkull.NAME;
/*     */       case "Masked Bandits":
/* 500 */         return MaskedBandits.NAME;
/*     */       case "Nest":
/* 502 */         return Nest.NAME;
/*     */       case "The Library":
/* 504 */         return TheLibrary.NAME;
/*     */       case "The Mausoleum":
/* 506 */         return TheMausoleum.NAME;
/*     */       case "The Joust":
/* 508 */         return TheJoust.NAME;
/*     */       case "Colosseum":
/* 510 */         return Colosseum.NAME;
/*     */ 
/*     */       
/*     */       case "Mysterious Sphere":
/* 514 */         return MysteriousSphere.NAME;
/*     */       case "SecretPortal":
/* 516 */         return SecretPortal.NAME;
/*     */       case "Tomb of Lord Red Mask":
/* 518 */         return TombRedMask.NAME;
/*     */       case "Falling":
/* 520 */         return Falling.NAME;
/*     */       case "Winding Halls":
/* 522 */         return WindingHalls.NAME;
/*     */       case "The Moai Head":
/* 524 */         return MoaiHead.NAME;
/*     */       case "SensoryStone":
/* 526 */         return SensoryStone.NAME;
/*     */       case "MindBloom":
/* 528 */         return MindBloom.NAME;
/*     */     } 
/* 530 */     return "";
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\EventHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */