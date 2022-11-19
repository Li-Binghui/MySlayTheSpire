/*     */ package com.megacrit.cardcrawl.dungeons;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*     */ import com.megacrit.cardcrawl.monsters.MonsterInfo;
/*     */ import com.megacrit.cardcrawl.neow.NeowRoom;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.EmptyRoom;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*     */ import com.megacrit.cardcrawl.scenes.AbstractScene;
/*     */ import com.megacrit.cardcrawl.scenes.TheBottomScene;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ 
/*     */ 
/*     */ public class Exordium
/*     */   extends AbstractDungeon
/*     */ {
/*  43 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Exordium");
/*  44 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  46 */   public static final String NAME = TEXT[0];
/*     */   public static final String ID = "Exordium";
/*     */   
/*     */   public Exordium(AbstractPlayer p, ArrayList<String> emptyList) {
/*  50 */     super(NAME, "Exordium", p, emptyList);
/*     */     
/*  52 */     initializeRelicList();
/*     */     
/*  54 */     if (Settings.isEndless) {
/*  55 */       if (floorNum <= 1) {
/*  56 */         blightPool.clear();
/*  57 */         blightPool = new ArrayList<>();
/*     */       } 
/*     */     } else {
/*  60 */       blightPool.clear();
/*     */     } 
/*     */     
/*  63 */     if (scene != null) {
/*  64 */       scene.dispose();
/*     */     }
/*  66 */     scene = (AbstractScene)new TheBottomScene();
/*  67 */     scene.randomizeScene();
/*  68 */     fadeColor = Color.valueOf("1e0f0aff");
/*  69 */     sourceFadeColor = Color.valueOf("1e0f0aff");
/*     */ 
/*     */     
/*  72 */     initializeSpecialOneTimeEventList();
/*     */ 
/*     */     
/*  75 */     initializeLevelSpecificChances();
/*  76 */     mapRng = new Random(Long.valueOf(Settings.seed.longValue() + AbstractDungeon.actNum));
/*  77 */     generateMap();
/*     */     
/*  79 */     CardCrawlGame.music.changeBGM(id);
/*  80 */     AbstractDungeon.currMapNode = new MapRoomNode(0, -1);
/*  81 */     if (Settings.isShowBuild || !((Boolean)TipTracker.tips.get("NEOW_SKIP")).booleanValue()) {
/*  82 */       AbstractDungeon.currMapNode.room = (AbstractRoom)new EmptyRoom();
/*     */     } else {
/*  84 */       AbstractDungeon.currMapNode.room = (AbstractRoom)new NeowRoom(false);
/*     */ 
/*     */       
/*  87 */       if (AbstractDungeon.floorNum > 1) {
/*  88 */         SaveHelper.saveIfAppropriate(SaveFile.SaveType.ENDLESS_NEOW);
/*     */       } else {
/*  90 */         SaveHelper.saveIfAppropriate(SaveFile.SaveType.ENTER_ROOM);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Exordium(AbstractPlayer p, SaveFile saveFile) {
/*  96 */     super(NAME, p, saveFile);
/*  97 */     CardCrawlGame.dungeon = this;
/*  98 */     if (scene != null) {
/*  99 */       scene.dispose();
/*     */     }
/* 101 */     scene = (AbstractScene)new TheBottomScene();
/* 102 */     fadeColor = Color.valueOf("1e0f0aff");
/* 103 */     sourceFadeColor = Color.valueOf("1e0f0aff");
/*     */ 
/*     */     
/* 106 */     initializeLevelSpecificChances();
/* 107 */     miscRng = new Random(Long.valueOf(Settings.seed.longValue() + saveFile.floor_num));
/* 108 */     CardCrawlGame.music.changeBGM(id);
/* 109 */     mapRng = new Random(Long.valueOf(Settings.seed.longValue() + saveFile.act_num));
/* 110 */     generateMap();
/* 111 */     firstRoomChosen = true;
/* 112 */     populatePathTaken(saveFile);
/*     */ 
/*     */     
/* 115 */     if (isLoadingIntoNeow(saveFile)) {
/* 116 */       AbstractDungeon.firstRoomChosen = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeLevelSpecificChances() {
/* 123 */     shopRoomChance = 0.05F;
/* 124 */     restRoomChance = 0.12F;
/* 125 */     treasureRoomChance = 0.0F;
/* 126 */     eventRoomChance = 0.22F;
/* 127 */     eliteRoomChance = 0.08F;
/*     */ 
/*     */     
/* 130 */     smallChestChance = 50;
/* 131 */     mediumChestChance = 33;
/* 132 */     largeChestChance = 17;
/*     */ 
/*     */     
/* 135 */     commonRelicChance = 50;
/* 136 */     uncommonRelicChance = 33;
/* 137 */     rareRelicChance = 17;
/*     */ 
/*     */     
/* 140 */     colorlessRareChance = 0.3F;
/* 141 */     cardUpgradedChance = 0.0F;
/*     */   }
/*     */   
/*     */   protected void generateMonsters() {
/* 145 */     generateWeakEnemies(3);
/* 146 */     generateStrongEnemies(12);
/* 147 */     generateElites(10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateWeakEnemies(int count) {
/* 154 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 155 */     monsters.add(new MonsterInfo("Cultist", 2.0F));
/* 156 */     monsters.add(new MonsterInfo("Jaw Worm", 2.0F));
/* 157 */     monsters.add(new MonsterInfo("2 Louse", 2.0F));
/* 158 */     monsters.add(new MonsterInfo("Small Slimes", 2.0F));
/* 159 */     MonsterInfo.normalizeWeights(monsters);
/* 160 */     populateMonsterList(monsters, count, false);
/*     */   }
/*     */   
/*     */   protected void generateStrongEnemies(int count) {
/* 164 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 165 */     monsters.add(new MonsterInfo("Blue Slaver", 2.0F));
/* 166 */     monsters.add(new MonsterInfo("Gremlin Gang", 1.0F));
/* 167 */     monsters.add(new MonsterInfo("Looter", 2.0F));
/* 168 */     monsters.add(new MonsterInfo("Large Slime", 2.0F));
/* 169 */     monsters.add(new MonsterInfo("Lots of Slimes", 1.0F));
/* 170 */     monsters.add(new MonsterInfo("Exordium Thugs", 1.5F));
/* 171 */     monsters.add(new MonsterInfo("Exordium Wildlife", 1.5F));
/* 172 */     monsters.add(new MonsterInfo("Red Slaver", 1.0F));
/* 173 */     monsters.add(new MonsterInfo("3 Louse", 2.0F));
/* 174 */     monsters.add(new MonsterInfo("2 Fungi Beasts", 2.0F));
/* 175 */     MonsterInfo.normalizeWeights(monsters);
/* 176 */     populateFirstStrongEnemy(monsters, generateExclusions());
/* 177 */     populateMonsterList(monsters, count, false);
/*     */   }
/*     */   
/*     */   protected void generateElites(int count) {
/* 181 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 182 */     monsters.add(new MonsterInfo("Gremlin Nob", 1.0F));
/* 183 */     monsters.add(new MonsterInfo("Lagavulin", 1.0F));
/* 184 */     monsters.add(new MonsterInfo("3 Sentries", 1.0F));
/* 185 */     MonsterInfo.normalizeWeights(monsters);
/* 186 */     populateMonsterList(monsters, count, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ArrayList<String> generateExclusions() {
/* 191 */     ArrayList<String> retVal = new ArrayList<>();
/* 192 */     switch ((String)monsterList.get(monsterList.size() - 1)) {
/*     */       case "Looter":
/* 194 */         retVal.add("Exordium Thugs");
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case "Blue Slaver":
/* 201 */         retVal.add("Red Slaver");
/* 202 */         retVal.add("Exordium Thugs");
/*     */         break;
/*     */       case "2 Louse":
/* 205 */         retVal.add("3 Louse");
/*     */         break;
/*     */       case "Small Slimes":
/* 208 */         retVal.add("Large Slime");
/* 209 */         retVal.add("Lots of Slimes");
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 214 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeBoss() {
/* 219 */     bossList.clear();
/*     */ 
/*     */     
/* 222 */     if (Settings.isDailyRun) {
/* 223 */       bossList.add("The Guardian");
/* 224 */       bossList.add("Hexaghost");
/* 225 */       bossList.add("Slime Boss");
/* 226 */       Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
/*     */     }
/* 228 */     else if (!UnlockTracker.isBossSeen("GUARDIAN")) {
/* 229 */       bossList.add("The Guardian");
/* 230 */     } else if (!UnlockTracker.isBossSeen("GHOST")) {
/* 231 */       bossList.add("Hexaghost");
/* 232 */     } else if (!UnlockTracker.isBossSeen("SLIME")) {
/* 233 */       bossList.add("Slime Boss");
/*     */     } else {
/* 235 */       bossList.add("The Guardian");
/* 236 */       bossList.add("Hexaghost");
/* 237 */       bossList.add("Slime Boss");
/* 238 */       Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 243 */     if (bossList.size() == 1) {
/* 244 */       bossList.add(bossList.get(0));
/* 245 */     } else if (bossList.isEmpty()) {
/* 246 */       logger.warn("Boss list was empty. How?");
/* 247 */       bossList.add("The Guardian");
/* 248 */       bossList.add("Hexaghost");
/* 249 */       bossList.add("Slime Boss");
/* 250 */       Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
/*     */     } 
/*     */ 
/*     */     
/* 254 */     if (Settings.isDemo) {
/* 255 */       bossList.clear();
/* 256 */       bossList.add("Hexaghost");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeEventList() {
/* 262 */     eventList.add("Big Fish");
/* 263 */     eventList.add("The Cleric");
/* 264 */     eventList.add("Dead Adventurer");
/* 265 */     eventList.add("Golden Idol");
/* 266 */     eventList.add("Golden Wing");
/* 267 */     eventList.add("World of Goop");
/* 268 */     eventList.add("Liars Game");
/* 269 */     eventList.add("Living Wall");
/* 270 */     eventList.add("Mushrooms");
/* 271 */     eventList.add("Scrap Ooze");
/* 272 */     eventList.add("Shining Light");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeShrineList() {
/* 277 */     shrineList.add("Match and Keep!");
/* 278 */     shrineList.add("Golden Shrine");
/* 279 */     shrineList.add("Transmorgrifier");
/* 280 */     shrineList.add("Purifier");
/* 281 */     shrineList.add("Upgrade Shrine");
/* 282 */     shrineList.add("Wheel of Change");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeEventImg() {
/* 287 */     if (eventBackgroundImg != null) {
/* 288 */       eventBackgroundImg.dispose();
/* 289 */       eventBackgroundImg = null;
/*     */     } 
/* 291 */     eventBackgroundImg = ImageMaster.loadImage("images/ui/event/panel.png");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\dungeons\Exordium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */