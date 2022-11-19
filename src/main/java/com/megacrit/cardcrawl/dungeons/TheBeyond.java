/*     */ package com.megacrit.cardcrawl.dungeons;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.monsters.MonsterInfo;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*     */ import com.megacrit.cardcrawl.scenes.AbstractScene;
/*     */ import com.megacrit.cardcrawl.scenes.TheBeyondScene;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;

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
/*     */ public class TheBeyond
/*     */   extends AbstractDungeon
/*     */ {
/*  36 */   private static final Logger logger = LogManager.getLogger(TheBeyond.class.getName());
/*  37 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TheBeyond");
/*  38 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  40 */   public static final String NAME = TEXT[0];
/*     */   public static final String ID = "TheBeyond";
/*     */   
/*     */   public TheBeyond(AbstractPlayer p, ArrayList<String> theList) {
/*  44 */     super(NAME, "TheBeyond", p, theList);
/*     */     
/*  46 */     if (scene != null) {
/*  47 */       scene.dispose();
/*     */     }
/*  49 */     scene = (AbstractScene)new TheBeyondScene();
/*  50 */     fadeColor = Color.valueOf("140a1eff");
/*  51 */     sourceFadeColor = Color.valueOf("140a1eff");
/*     */     
/*  53 */     initializeLevelSpecificChances();
/*  54 */     mapRng = new Random(Long.valueOf(Settings.seed.longValue() + (AbstractDungeon.actNum * 200)));
/*  55 */     generateMap();
/*     */     
/*  57 */     CardCrawlGame.music.changeBGM(id);
/*     */   }
/*     */   
/*     */   public TheBeyond(AbstractPlayer p, SaveFile saveFile) {
/*  61 */     super(NAME, p, saveFile);
/*  62 */     CardCrawlGame.dungeon = this;
/*     */     
/*  64 */     if (scene != null) {
/*  65 */       scene.dispose();
/*     */     }
/*  67 */     scene = (AbstractScene)new TheBeyondScene();
/*  68 */     fadeColor = Color.valueOf("140a1eff");
/*  69 */     sourceFadeColor = Color.valueOf("140a1eff");
/*     */     
/*  71 */     initializeLevelSpecificChances();
/*  72 */     miscRng = new Random(Long.valueOf(Settings.seed.longValue() + saveFile.floor_num));
/*  73 */     CardCrawlGame.music.changeBGM(id);
/*  74 */     mapRng = new Random(Long.valueOf(Settings.seed.longValue() + (saveFile.act_num * 200)));
/*  75 */     generateMap();
/*  76 */     firstRoomChosen = true;
/*     */     
/*  78 */     populatePathTaken(saveFile);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeLevelSpecificChances() {
/*  84 */     shopRoomChance = 0.05F;
/*  85 */     restRoomChance = 0.12F;
/*  86 */     treasureRoomChance = 0.0F;
/*  87 */     eventRoomChance = 0.22F;
/*  88 */     eliteRoomChance = 0.08F;
/*     */ 
/*     */     
/*  91 */     smallChestChance = 50;
/*  92 */     mediumChestChance = 33;
/*  93 */     largeChestChance = 17;
/*     */ 
/*     */     
/*  96 */     commonRelicChance = 50;
/*  97 */     uncommonRelicChance = 33;
/*  98 */     rareRelicChance = 17;
/*     */ 
/*     */     
/* 101 */     colorlessRareChance = 0.3F;
/* 102 */     if (AbstractDungeon.ascensionLevel >= 12) {
/* 103 */       cardUpgradedChance = 0.25F;
/*     */     } else {
/* 105 */       cardUpgradedChance = 0.5F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void generateMonsters() {
/* 111 */     generateWeakEnemies(2);
/* 112 */     generateStrongEnemies(12);
/* 113 */     generateElites(10);
/*     */   }
/*     */   
/*     */   protected void generateWeakEnemies(int count) {
/* 117 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 118 */     monsters.add(new MonsterInfo("3 Darklings", 2.0F));
/* 119 */     monsters.add(new MonsterInfo("Orb Walker", 2.0F));
/* 120 */     monsters.add(new MonsterInfo("3 Shapes", 2.0F));
/* 121 */     MonsterInfo.normalizeWeights(monsters);
/* 122 */     populateMonsterList(monsters, count, false);
/*     */   }
/*     */   
/*     */   protected void generateStrongEnemies(int count) {
/* 126 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 127 */     monsters.add(new MonsterInfo("Spire Growth", 1.0F));
/* 128 */     monsters.add(new MonsterInfo("Transient", 1.0F));
/* 129 */     monsters.add(new MonsterInfo("4 Shapes", 1.0F));
/* 130 */     monsters.add(new MonsterInfo("Maw", 1.0F));
/* 131 */     monsters.add(new MonsterInfo("Sphere and 2 Shapes", 1.0F));
/* 132 */     monsters.add(new MonsterInfo("Jaw Worm Horde", 1.0F));
/* 133 */     monsters.add(new MonsterInfo("3 Darklings", 1.0F));
/* 134 */     monsters.add(new MonsterInfo("Writhing Mass", 1.0F));
/* 135 */     MonsterInfo.normalizeWeights(monsters);
/* 136 */     populateFirstStrongEnemy(monsters, generateExclusions());
/* 137 */     populateMonsterList(monsters, count, false);
/*     */   }
/*     */   
/*     */   protected void generateElites(int count) {
/* 141 */     ArrayList<MonsterInfo> monsters = new ArrayList<>();
/* 142 */     monsters.add(new MonsterInfo("Giant Head", 2.0F));
/* 143 */     monsters.add(new MonsterInfo("Nemesis", 2.0F));
/* 144 */     monsters.add(new MonsterInfo("Reptomancer", 2.0F));
/* 145 */     MonsterInfo.normalizeWeights(monsters);
/* 146 */     populateMonsterList(monsters, count, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ArrayList<String> generateExclusions() {
/* 151 */     ArrayList<String> retVal = new ArrayList<>();
/* 152 */     switch ((String)monsterList.get(monsterList.size() - 1)) {
/*     */       case "3 Darklings":
/* 154 */         retVal.add("3 Darklings");
/*     */         break;
/*     */       case "Orb Walker":
/* 157 */         retVal.add("Orb Walker");
/*     */         break;
/*     */       case "3 Shapes":
/* 160 */         retVal.add("4 Shapes");
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 165 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeBoss() {
/* 170 */     bossList.clear();
/*     */ 
/*     */     
/* 173 */     if (Settings.isDailyRun) {
/* 174 */       bossList.add("Awakened One");
/* 175 */       bossList.add("Time Eater");
/* 176 */       bossList.add("Donu and Deca");
/* 177 */       Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
/*     */     }
/* 179 */     else if (!UnlockTracker.isBossSeen("CROW")) {
/* 180 */       bossList.add("Awakened One");
/* 181 */     } else if (!UnlockTracker.isBossSeen("DONUT")) {
/* 182 */       bossList.add("Donu and Deca");
/* 183 */     } else if (!UnlockTracker.isBossSeen("WIZARD")) {
/* 184 */       bossList.add("Time Eater");
/*     */     } else {
/* 186 */       bossList.add("Awakened One");
/* 187 */       bossList.add("Time Eater");
/* 188 */       bossList.add("Donu and Deca");
/* 189 */       Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 194 */     if (bossList.size() == 1) {
/* 195 */       bossList.add(bossList.get(0));
/* 196 */     } else if (bossList.isEmpty()) {
/* 197 */       logger.warn("Boss list was empty. How?");
/* 198 */       bossList.add("Awakened One");
/* 199 */       bossList.add("Time Eater");
/* 200 */       bossList.add("Donu and Deca");
/* 201 */       Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeEventList() {
/* 208 */     eventList.add("Falling");
/* 209 */     eventList.add("MindBloom");
/* 210 */     eventList.add("The Moai Head");
/* 211 */     eventList.add("Mysterious Sphere");
/* 212 */     eventList.add("SensoryStone");
/* 213 */     eventList.add("Tomb of Lord Red Mask");
/* 214 */     eventList.add("Winding Halls");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeEventImg() {
/* 219 */     if (eventBackgroundImg != null) {
/* 220 */       eventBackgroundImg.dispose();
/* 221 */       eventBackgroundImg = null;
/*     */     } 
/* 223 */     eventBackgroundImg = ImageMaster.loadImage("images/ui/event/panel.png");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeShrineList() {
/* 228 */     shrineList.add("Match and Keep!");
/* 229 */     shrineList.add("Wheel of Change");
/* 230 */     shrineList.add("Golden Shrine");
/* 231 */     shrineList.add("Transmorgrifier");
/* 232 */     shrineList.add("Purifier");
/* 233 */     shrineList.add("Upgrade Shrine");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\dungeons\TheBeyond.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */