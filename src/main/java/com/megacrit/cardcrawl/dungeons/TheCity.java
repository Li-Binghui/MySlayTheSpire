/*     */ package com.megacrit.cardcrawl.dungeons;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*     */ import com.megacrit.cardcrawl.monsters.MonsterInfo;
///*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.EmptyRoom;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*     */ import com.megacrit.cardcrawl.scenes.AbstractScene;
/*     */ import com.megacrit.cardcrawl.scenes.TheCityScene;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TheCity
/*     */   extends AbstractDungeon
/*     */ {
/*  44 */   private static final Logger logger = LogManager.getLogger(TheCity.class.getName());
/*  45 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TheCity");
/*  46 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  48 */   public static final String NAME = TEXT[0];
/*     */   public static final String ID = "TheCity";
/*     */   
/*     */   public TheCity(AbstractPlayer p, ArrayList<String> theList) {
/*  52 */     super(NAME, "TheCity", p, theList);
/*     */     
/*  54 */     if (scene != null) {
/*  55 */       scene.dispose();
/*     */     }
/*  57 */     scene = (AbstractScene)new TheCityScene();
/*  58 */     fadeColor = Color.valueOf("0a1e1eff");
/*  59 */     sourceFadeColor = Color.valueOf("0a1e1eff");
/*     */     
/*  61 */     initializeLevelSpecificChances();
/*  62 */     mapRng = new com.megacrit.cardcrawl.random.Random(Long.valueOf(Settings.seed.longValue() + (AbstractDungeon.actNum * 100)));
/*  63 */     generateMap();
/*     */     
/*  65 */     CardCrawlGame.music.changeBGM(id);
/*  66 */     AbstractDungeon.currMapNode = new MapRoomNode(0, -1);
/*  67 */     AbstractDungeon.currMapNode.room = (AbstractRoom)new EmptyRoom();
/*     */   }
/*     */   
/*     */   public TheCity(AbstractPlayer p, SaveFile saveFile) {
/*  71 */     super(NAME, p, saveFile);
/*     */     
/*  73 */     if (scene != null) {
/*  74 */       scene.dispose();
/*     */     }
/*  76 */     scene = (AbstractScene)new TheCityScene();
/*  77 */     fadeColor = Color.valueOf("0a1e1eff");
/*  78 */     sourceFadeColor = Color.valueOf("0a1e1eff");
/*     */     
/*  80 */     initializeLevelSpecificChances();
/*  81 */     miscRng = new com.megacrit.cardcrawl.random.Random(Long.valueOf(Settings.seed.longValue() + saveFile.floor_num));
/*  82 */     CardCrawlGame.music.changeBGM(id);
/*  83 */     mapRng = new com.megacrit.cardcrawl.random.Random(Long.valueOf(Settings.seed.longValue() + (saveFile.act_num * 100)));
/*  84 */     generateMap();
/*  85 */     firstRoomChosen = true;
/*     */     
/*  87 */     populatePathTaken(saveFile);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeLevelSpecificChances() {
/*  93 */     shopRoomChance = 0.05F;
/*  94 */     restRoomChance = 0.12F;
/*  95 */     treasureRoomChance = 0.0F;
/*  96 */     eventRoomChance = 0.22F;
/*  97 */     eliteRoomChance = 0.08F;
/*     */ 
/*     */     
/* 100 */     smallChestChance = 50;
/* 101 */     mediumChestChance = 33;
/* 102 */     largeChestChance = 17;
/*     */ 
/*     */     
/* 105 */     commonRelicChance = 50;
/* 106 */     uncommonRelicChance = 33;
/* 107 */     rareRelicChance = 17;
/*     */ 
/*     */     
/* 110 */     colorlessRareChance = 0.3F;
/* 111 */     if (AbstractDungeon.ascensionLevel >= 12) {
/* 112 */       cardUpgradedChance = 0.125F;
/*     */     } else {
/* 114 */       cardUpgradedChance = 0.25F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void generateMonsters() {
/* 120 */     generateWeakEnemies(2);
/* 121 */     generateStrongEnemies(12);
/* 122 */     generateElites(10);
/*     */   }
/*     */   
/*     */   protected void generateWeakEnemies(int count) {
/* 126 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 127 */     monsters.add(new MonsterInfo("Spheric Guardian", 2.0F));
/* 128 */     monsters.add(new MonsterInfo("Chosen", 2.0F));
/* 129 */     monsters.add(new MonsterInfo("Shell Parasite", 2.0F));
/* 130 */     monsters.add(new MonsterInfo("3 Byrds", 2.0F));
/* 131 */     monsters.add(new MonsterInfo("2 Thieves", 2.0F));
/* 132 */     MonsterInfo.normalizeWeights(monsters);
/* 133 */     populateMonsterList(monsters, count, false);
/*     */   }
/*     */   
/*     */   protected void generateStrongEnemies(int count) {
/* 137 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 138 */     monsters.add(new MonsterInfo("Chosen and Byrds", 2.0F));
/* 139 */     monsters.add(new MonsterInfo("Sentry and Sphere", 2.0F));
/* 140 */     monsters.add(new MonsterInfo("Snake Plant", 6.0F));
/* 141 */     monsters.add(new MonsterInfo("Snecko", 4.0F));
/* 142 */     monsters.add(new MonsterInfo("Centurion and Healer", 6.0F));
/* 143 */     monsters.add(new MonsterInfo("Cultist and Chosen", 3.0F));
/* 144 */     monsters.add(new MonsterInfo("3 Cultists", 3.0F));
/* 145 */     monsters.add(new MonsterInfo("Shelled Parasite and Fungi", 3.0F));
/* 146 */     MonsterInfo.normalizeWeights(monsters);
/* 147 */     populateFirstStrongEnemy(monsters, generateExclusions());
/* 148 */     populateMonsterList(monsters, count, false);
/*     */   }
/*     */   
/*     */   protected void generateElites(int count) {
/* 152 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 153 */     monsters.add(new MonsterInfo("Gremlin Leader", 1.0F));
/* 154 */     monsters.add(new MonsterInfo("Slavers", 1.0F));
/* 155 */     monsters.add(new MonsterInfo("Book of Stabbing", 1.0F));
/* 156 */     MonsterInfo.normalizeWeights(monsters);
/* 157 */     populateMonsterList(monsters, count, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ArrayList<String> generateExclusions() {
/* 162 */     ArrayList<String> retVal = new ArrayList<>();
/* 163 */     switch ((String)monsterList.get(monsterList.size() - 1)) {
/*     */       case "Spheric Guardian":
/* 165 */         retVal.add("Sentry and Sphere");
/*     */         break;
/*     */       case "3 Byrds":
/* 168 */         retVal.add("Chosen and Byrds");
/*     */         break;
/*     */       case "Chosen":
/* 171 */         retVal.add("Chosen and Byrds");
/* 172 */         retVal.add("Cultist and Chosen");
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 177 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeBoss() {
/* 182 */     bossList.clear();
/*     */ 
/*     */     
/* 185 */     if (Settings.isDailyRun) {
/* 186 */       bossList.add("Automaton");
/* 187 */       bossList.add("Collector");
/* 188 */       bossList.add("Champ");
/* 189 */       Collections.shuffle(bossList, new Random(monsterRng.randomLong()));
/*     */     }
/* 191 */     else if (!UnlockTracker.isBossSeen("CHAMP")) {
/* 192 */       bossList.add("Champ");
/* 193 */     } else if (!UnlockTracker.isBossSeen("AUTOMATON")) {
/* 194 */       bossList.add("Automaton");
/* 195 */     } else if (!UnlockTracker.isBossSeen("COLLECTOR")) {
/* 196 */       bossList.add("Collector");
/*     */     } else {
/* 198 */       bossList.add("Automaton");
/* 199 */       bossList.add("Collector");
/* 200 */       bossList.add("Champ");
/* 201 */       Collections.shuffle(bossList, new Random(monsterRng.randomLong()));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 206 */     if (bossList.size() == 1) {
/* 207 */       bossList.add(bossList.get(0));
/* 208 */     } else if (bossList.isEmpty()) {
/* 209 */       logger.warn("Boss list was empty. How?");
/* 210 */       bossList.add("Automaton");
/* 211 */       bossList.add("Collector");
/* 212 */       bossList.add("Champ");
/* 213 */       Collections.shuffle(bossList, new Random(monsterRng.randomLong()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeEventList() {
/* 219 */     eventList.add("Addict");
/* 220 */     eventList.add("Back to Basics");
/* 221 */     eventList.add("Beggar");
/* 222 */     eventList.add("Colosseum");
/* 223 */     eventList.add("Cursed Tome");
/* 224 */     eventList.add("Drug Dealer");
/* 225 */     eventList.add("Forgotten Altar");
/* 226 */     eventList.add("Ghosts");
/* 227 */     eventList.add("Masked Bandits");
/* 228 */     eventList.add("Nest");
/* 229 */     eventList.add("The Library");
/* 230 */     eventList.add("The Mausoleum");
/* 231 */     eventList.add("Vampires");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeEventImg() {
/* 236 */     if (eventBackgroundImg != null) {
/* 237 */       eventBackgroundImg.dispose();
/* 238 */       eventBackgroundImg = null;
/*     */     } 
/* 240 */     eventBackgroundImg = ImageMaster.loadImage("images/ui/event/panel.png");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeShrineList() {
/* 245 */     shrineList.add("Match and Keep!");
/* 246 */     shrineList.add("Wheel of Change");
/* 247 */     shrineList.add("Golden Shrine");
/* 248 */     shrineList.add("Transmorgrifier");
/* 249 */     shrineList.add("Purifier");
/* 250 */     shrineList.add("Upgrade Shrine");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\dungeons\TheCity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */