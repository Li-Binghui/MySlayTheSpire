/*     */ package com.megacrit.cardcrawl.dungeons;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapGenerator;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/*     */ 
/*     */ public class TheEnding
/*     */   extends AbstractDungeon {
/*  28 */   private static final Logger logger = LogManager.getLogger(TheEnding.class.getName());
/*  29 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TheEnding");
/*  30 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  32 */   public static final String NAME = TEXT[0];
/*     */   public static final String ID = "TheEnding";
/*     */   
/*     */   public TheEnding(AbstractPlayer p, ArrayList<String> theList) {
/*  36 */     super(NAME, "TheEnding", p, theList);
/*     */     
/*  38 */     if (scene != null) {
/*  39 */       scene.dispose();
/*     */     }
/*     */     
/*  42 */     scene = (AbstractScene)new TheEndingScene();
/*  43 */     fadeColor = Color.valueOf("140a1eff");
/*  44 */     sourceFadeColor = Color.valueOf("140a1eff");
/*     */     
/*  46 */     initializeLevelSpecificChances();
/*  47 */     mapRng = new Random(Long.valueOf(Settings.seed.longValue() + (AbstractDungeon.actNum * 300)));
/*  48 */     generateSpecialMap();
/*  49 */     CardCrawlGame.music.changeBGM(id);
/*     */   }
/*     */   
/*     */   public TheEnding(AbstractPlayer p, SaveFile saveFile) {
/*  53 */     super(NAME, p, saveFile);
/*  54 */     CardCrawlGame.dungeon = this;
/*     */     
/*  56 */     if (scene != null) {
/*  57 */       scene.dispose();
/*     */     }
/*     */     
/*  60 */     scene = (AbstractScene)new TheEndingScene();
/*  61 */     fadeColor = Color.valueOf("140a1eff");
/*  62 */     sourceFadeColor = Color.valueOf("140a1eff");
/*     */     
/*  64 */     initializeLevelSpecificChances();
/*  65 */     miscRng = new Random(Long.valueOf(Settings.seed.longValue() + saveFile.floor_num));
/*  66 */     CardCrawlGame.music.changeBGM(id);
/*  67 */     mapRng = new Random(Long.valueOf(Settings.seed.longValue() + (saveFile.act_num * 300)));
/*  68 */     generateSpecialMap();
/*  69 */     firstRoomChosen = true;
/*  70 */     populatePathTaken(saveFile);
/*     */   }
/*     */   
/*     */   private void generateSpecialMap() {
/*  74 */     long startTime = System.currentTimeMillis();
/*     */     
/*  76 */     map = new ArrayList<>();
/*     */ 
/*     */     
/*  79 */     ArrayList<MapRoomNode> row1 = new ArrayList<>();
/*  80 */     MapRoomNode restNode = new MapRoomNode(3, 0);
/*  81 */     restNode.room = (AbstractRoom)new RestRoom();
/*  82 */     MapRoomNode shopNode = new MapRoomNode(3, 1);
/*  83 */     shopNode.room = (AbstractRoom)new ShopRoom();
/*  84 */     MapRoomNode enemyNode = new MapRoomNode(3, 2);
/*  85 */     enemyNode.room = (AbstractRoom)new MonsterRoomElite();
/*  86 */     MapRoomNode bossNode = new MapRoomNode(3, 3);
/*  87 */     bossNode.room = (AbstractRoom)new MonsterRoomBoss();
/*  88 */     MapRoomNode victoryNode = new MapRoomNode(3, 4);
/*  89 */     victoryNode.room = (AbstractRoom)new TrueVictoryRoom();
/*     */ 
/*     */     
/*  92 */     connectNode(restNode, shopNode);
/*  93 */     connectNode(shopNode, enemyNode);
/*  94 */     enemyNode.addEdge(new MapEdge(enemyNode.x, enemyNode.y, enemyNode.offsetX, enemyNode.offsetY, bossNode.x, bossNode.y, bossNode.offsetX, bossNode.offsetY, false));
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
/* 107 */     row1.add(new MapRoomNode(0, 0));
/* 108 */     row1.add(new MapRoomNode(1, 0));
/* 109 */     row1.add(new MapRoomNode(2, 0));
/* 110 */     row1.add(restNode);
/* 111 */     row1.add(new MapRoomNode(4, 0));
/* 112 */     row1.add(new MapRoomNode(5, 0));
/* 113 */     row1.add(new MapRoomNode(6, 0));
/*     */ 
/*     */     
/* 116 */     ArrayList<MapRoomNode> row2 = new ArrayList<>();
/* 117 */     row2.add(new MapRoomNode(0, 1));
/* 118 */     row2.add(new MapRoomNode(1, 1));
/* 119 */     row2.add(new MapRoomNode(2, 1));
/* 120 */     row2.add(shopNode);
/* 121 */     row2.add(new MapRoomNode(4, 1));
/* 122 */     row2.add(new MapRoomNode(5, 1));
/* 123 */     row2.add(new MapRoomNode(6, 1));
/*     */ 
/*     */     
/* 126 */     ArrayList<MapRoomNode> row3 = new ArrayList<>();
/* 127 */     row3.add(new MapRoomNode(0, 2));
/* 128 */     row3.add(new MapRoomNode(1, 2));
/* 129 */     row3.add(new MapRoomNode(2, 2));
/* 130 */     row3.add(enemyNode);
/* 131 */     row3.add(new MapRoomNode(4, 2));
/* 132 */     row3.add(new MapRoomNode(5, 2));
/* 133 */     row3.add(new MapRoomNode(6, 2));
/*     */ 
/*     */     
/* 136 */     ArrayList<MapRoomNode> row4 = new ArrayList<>();
/* 137 */     row4.add(new MapRoomNode(0, 3));
/* 138 */     row4.add(new MapRoomNode(1, 3));
/* 139 */     row4.add(new MapRoomNode(2, 3));
/* 140 */     row4.add(bossNode);
/* 141 */     row4.add(new MapRoomNode(4, 3));
/* 142 */     row4.add(new MapRoomNode(5, 3));
/* 143 */     row4.add(new MapRoomNode(6, 3));
/*     */ 
/*     */     
/* 146 */     ArrayList<MapRoomNode> row5 = new ArrayList<>();
/* 147 */     row5.add(new MapRoomNode(0, 4));
/* 148 */     row5.add(new MapRoomNode(1, 4));
/* 149 */     row5.add(new MapRoomNode(2, 4));
/* 150 */     row5.add(victoryNode);
/* 151 */     row5.add(new MapRoomNode(4, 4));
/* 152 */     row5.add(new MapRoomNode(5, 4));
/* 153 */     row5.add(new MapRoomNode(6, 4));
/*     */ 
/*     */     
/* 156 */     map.add(row1);
/* 157 */     map.add(row2);
/* 158 */     map.add(row3);
/* 159 */     map.add(row4);
/* 160 */     map.add(row5);
/*     */     
/* 162 */     logger.info("Generated the following dungeon map:");
/* 163 */     logger.info(MapGenerator.toString(map, Boolean.valueOf(true)));
/* 164 */     logger.info("Game Seed: " + Settings.seed);
/* 165 */     logger.info("Map generation time: " + (System.currentTimeMillis() - startTime) + "ms");
/* 166 */     firstRoomChosen = false;
/* 167 */     fadeIn();
/*     */   }
/*     */   
/*     */   private void connectNode(MapRoomNode src, MapRoomNode dst) {
/* 171 */     src.addEdge(new MapEdge(src.x, src.y, src.offsetX, src.offsetY, dst.x, dst.y, dst.offsetX, dst.offsetY, false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeLevelSpecificChances() {
/* 177 */     shopRoomChance = 0.05F;
/* 178 */     restRoomChance = 0.12F;
/* 179 */     treasureRoomChance = 0.0F;
/* 180 */     eventRoomChance = 0.22F;
/* 181 */     eliteRoomChance = 0.08F;
/*     */ 
/*     */     
/* 184 */     smallChestChance = 0;
/* 185 */     mediumChestChance = 100;
/* 186 */     largeChestChance = 0;
/*     */ 
/*     */     
/* 189 */     commonRelicChance = 0;
/* 190 */     uncommonRelicChance = 100;
/* 191 */     rareRelicChance = 0;
/*     */ 
/*     */     
/* 194 */     colorlessRareChance = 0.3F;
/* 195 */     if (AbstractDungeon.ascensionLevel >= 12) {
/* 196 */       cardUpgradedChance = 0.25F;
/*     */     } else {
/* 198 */       cardUpgradedChance = 0.5F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateMonsters() {
/* 205 */     monsterList = new ArrayList<>();
/* 206 */     monsterList.add("Shield and Spear");
/* 207 */     monsterList.add("Shield and Spear");
/* 208 */     monsterList.add("Shield and Spear");
/*     */     
/* 210 */     eliteMonsterList = new ArrayList<>();
/* 211 */     eliteMonsterList.add("Shield and Spear");
/* 212 */     eliteMonsterList.add("Shield and Spear");
/* 213 */     eliteMonsterList.add("Shield and Spear");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateWeakEnemies(int count) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateStrongEnemies(int count) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateElites(int count) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected ArrayList<String> generateExclusions() {
/* 231 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initializeBoss() {
/* 236 */     bossList.add("The Heart");
/* 237 */     bossList.add("The Heart");
/* 238 */     bossList.add("The Heart");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeEventList() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeEventImg() {
/* 248 */     if (eventBackgroundImg != null) {
/* 249 */       eventBackgroundImg.dispose();
/* 250 */       eventBackgroundImg = null;
/*     */     } 
/* 252 */     eventBackgroundImg = ImageMaster.loadImage("images/ui/event/panel.png");
/*     */   }
/*     */   
/*     */   protected void initializeShrineList() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\dungeons\TheEnding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */