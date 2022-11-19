/*     */ package com.megacrit.cardcrawl.screens.runHistory;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.GameCursor;
/*     */ import com.megacrit.cardcrawl.screens.stats.BattleStats;
/*     */ import com.megacrit.cardcrawl.screens.stats.BossRelicChoiceStats;
/*     */ import com.megacrit.cardcrawl.screens.stats.CampfireChoice;
/*     */ import com.megacrit.cardcrawl.screens.stats.CardChoiceStats;
/*     */ import com.megacrit.cardcrawl.screens.stats.EventStats;
/*     */ import com.megacrit.cardcrawl.screens.stats.ObtainStats;
/*     */ import com.megacrit.cardcrawl.screens.stats.RunData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RunHistoryPath
/*     */ {
/*     */   public static final String BOSS_TREASURE_LABEL = "T!";
/*     */   public static final String HEART_LABEL = "<3";
/*     */   public static final int MAX_NODES_PER_ROW = 20;
/*     */   private RunData runData;
/*  27 */   public List<RunPathElement> pathElements = new ArrayList<>();
/*     */ 
/*     */   
/*  30 */   private int rows = 0;
/*     */   
/*     */   public void setRunData(RunData newData) {
/*  33 */     this.runData = newData;
/*     */     
/*  35 */     List<String> labels = new ArrayList<>();
/*  36 */     int choiceIndex = 0, outcomeIndex = 0;
/*  37 */     List<String> choices = this.runData.path_taken;
/*  38 */     List<String> outcomes = this.runData.path_per_floor;
/*  39 */     if (choices == null) {
/*  40 */       choices = new LinkedList<>();
/*     */     }
/*  42 */     if (outcomes == null) {
/*  43 */       outcomes = new LinkedList<>();
/*     */     }
/*     */     
/*  46 */     int bossTreasureCount = 0;
/*     */     
/*  48 */     while (choiceIndex < choices.size() || outcomeIndex < outcomes.size()) {
/*  49 */       String choice = (choiceIndex < choices.size()) ? choices.get(choiceIndex) : "_";
/*  50 */       String outcome = (outcomeIndex < outcomes.size()) ? outcomes.get(outcomeIndex) : "_";
/*  51 */       if (outcome == null) {
/*  52 */         outcomeIndex++;
/*  53 */         if (bossTreasureCount < 2) {
/*  54 */           labels.add("T!");
/*  55 */           bossTreasureCount++; continue;
/*  56 */         }  if (bossTreasureCount == 2) {
/*  57 */           labels.add("<3");
/*  58 */           bossTreasureCount++; continue;
/*     */         } 
/*  60 */         labels.add("null");
/*     */         continue;
/*     */       } 
/*  63 */       if (choice.equals("?") && !outcome.equals("?")) {
/*  64 */         labels.add("?(" + outcome + ")");
/*     */       } else {
/*  66 */         labels.add(choice);
/*     */       } 
/*  68 */       choiceIndex++;
/*  69 */       outcomeIndex++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  74 */     HashMap<Integer, BattleStats> battlesByFloor = new HashMap<>();
/*  75 */     for (BattleStats battle : this.runData.damage_taken) {
/*  76 */       battlesByFloor.put(Integer.valueOf(battle.floor), battle);
/*     */     }
/*     */ 
/*     */     
/*  80 */     HashMap<Integer, EventStats> eventsByFloor = new HashMap<>();
/*  81 */     for (EventStats event : this.runData.event_choices) {
/*  82 */       eventsByFloor.put(Integer.valueOf(event.floor), event);
/*     */     }
/*     */ 
/*     */     
/*  86 */     HashMap<Integer, CardChoiceStats> cardsByFloor = new HashMap<>();
/*  87 */     for (CardChoiceStats choice : this.runData.card_choices)
/*     */     {
/*  89 */       cardsByFloor.put(Integer.valueOf(choice.floor), choice);
/*     */     }
/*     */ 
/*     */     
/*  93 */     HashMap<Integer, List<String>> relicsByFloor = new HashMap<>();
/*  94 */     if (this.runData.relics_obtained != null) {
/*  95 */       for (ObtainStats relicData : this.runData.relics_obtained) {
/*  96 */         if (!relicsByFloor.containsKey(Integer.valueOf(relicData.floor))) {
/*  97 */           relicsByFloor.put(Integer.valueOf(relicData.floor), new ArrayList<>());
/*     */         }
/*  99 */         ((List<String>)relicsByFloor.get(Integer.valueOf(relicData.floor))).add(relicData.key);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 104 */     HashMap<Integer, List<String>> potionsByFloor = new HashMap<>();
/* 105 */     if (this.runData.potions_obtained != null) {
/* 106 */       for (ObtainStats potionData : this.runData.potions_obtained) {
/* 107 */         if (!potionsByFloor.containsKey(Integer.valueOf(potionData.floor))) {
/* 108 */           potionsByFloor.put(Integer.valueOf(potionData.floor), new ArrayList<>());
/*     */         }
/* 110 */         ((List<String>)potionsByFloor.get(Integer.valueOf(potionData.floor))).add(potionData.key);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 115 */     HashMap<Integer, CampfireChoice> campfireChoicesByFloor = new HashMap<>();
/* 116 */     if (this.runData.campfire_choices != null) {
/* 117 */       for (CampfireChoice choice : this.runData.campfire_choices) {
/* 118 */         campfireChoicesByFloor.put(Integer.valueOf(choice.floor), choice);
/*     */       }
/*     */     }
/*     */     
/* 122 */     HashMap<Integer, ArrayList<String>> purchasesByFloor = new HashMap<>();
/* 123 */     if (this.runData.items_purchased != null && this.runData.item_purchase_floors != null && this.runData.items_purchased
/* 124 */       .size() == this.runData.item_purchase_floors.size()) {
/* 125 */       for (int j = 0; j < this.runData.items_purchased.size(); j++) {
/* 126 */         int floor = ((Integer)this.runData.item_purchase_floors.get(j)).intValue();
/* 127 */         String key = this.runData.items_purchased.get(j);
/* 128 */         if (!purchasesByFloor.containsKey(Integer.valueOf(floor))) {
/* 129 */           purchasesByFloor.put(Integer.valueOf(floor), new ArrayList<>());
/*     */         }
/* 131 */         ((ArrayList<String>)purchasesByFloor.get(Integer.valueOf(floor))).add(key);
/*     */       } 
/*     */     }
/*     */     
/* 135 */     HashMap<Integer, ArrayList<String>> purgesByFloor = new HashMap<>();
/* 136 */     if (this.runData.items_purged != null && this.runData.items_purged_floors != null && this.runData.items_purged
/* 137 */       .size() == this.runData.items_purged_floors.size()) {
/* 138 */       for (int j = 0; j < this.runData.items_purged.size(); j++) {
/* 139 */         int floor = ((Integer)this.runData.items_purged_floors.get(j)).intValue();
/* 140 */         String key = this.runData.items_purged.get(j);
/* 141 */         if (!purgesByFloor.containsKey(Integer.valueOf(floor))) {
/* 142 */           purgesByFloor.put(Integer.valueOf(floor), new ArrayList<>());
/*     */         }
/* 144 */         ((ArrayList<String>)purgesByFloor.get(Integer.valueOf(floor))).add(key);
/*     */       } 
/*     */     }
/*     */     
/* 148 */     this.pathElements.clear();
/*     */     
/* 150 */     this.rows = 0;
/* 151 */     int bossRelicChoiceIndex = 0;
/* 152 */     int tmpColumn = 0;
/*     */     
/* 154 */     for (int i = 0; i < labels.size(); i++) {
/* 155 */       String label = labels.get(i);
/* 156 */       int floor = i + 1;
/* 157 */       RunPathElement element = new RunPathElement(label, floor);
/*     */       
/* 159 */       if (this.runData.current_hp_per_floor != null && this.runData.max_hp_per_floor != null && i < this.runData.current_hp_per_floor
/* 160 */         .size() && i < this.runData.max_hp_per_floor.size()) {
/* 161 */         element.addHpData(this.runData.current_hp_per_floor.get(i), this.runData.max_hp_per_floor.get(i));
/*     */       }
/* 163 */       if (this.runData.gold_per_floor != null && i < this.runData.gold_per_floor.size()) {
/* 164 */         element.addGoldData(this.runData.gold_per_floor.get(i));
/*     */       }
/*     */       
/* 167 */       if (battlesByFloor.containsKey(Integer.valueOf(floor))) {
/* 168 */         element.addBattleData(battlesByFloor.get(Integer.valueOf(floor)));
/*     */       }
/* 170 */       if (eventsByFloor.containsKey(Integer.valueOf(floor))) {
/* 171 */         element.addEventData(eventsByFloor.get(Integer.valueOf(floor)));
/*     */       }
/* 173 */       if (cardsByFloor.containsKey(Integer.valueOf(floor))) {
/* 174 */         element.addCardChoiceData(cardsByFloor.get(Integer.valueOf(floor)));
/*     */       }
/* 176 */       if (relicsByFloor.containsKey(Integer.valueOf(floor))) {
/* 177 */         element.addRelicObtainStats(relicsByFloor.get(Integer.valueOf(floor)));
/*     */       }
/* 179 */       if (potionsByFloor.containsKey(Integer.valueOf(floor))) {
/* 180 */         element.addPotionObtainStats(potionsByFloor.get(Integer.valueOf(floor)));
/*     */       }
/* 182 */       if (campfireChoicesByFloor.containsKey(Integer.valueOf(floor))) {
/* 183 */         element.addCampfireChoiceData(campfireChoicesByFloor.get(Integer.valueOf(floor)));
/*     */       }
/* 185 */       if (purchasesByFloor.containsKey(Integer.valueOf(floor))) {
/* 186 */         element.addShopPurchaseData(purchasesByFloor.get(Integer.valueOf(floor)));
/*     */       }
/* 188 */       if (purgesByFloor.containsKey(Integer.valueOf(floor))) {
/* 189 */         element.addPurgeData(purgesByFloor.get(Integer.valueOf(floor)));
/*     */       }
/*     */       
/* 192 */       if (label.equals("T!") && this.runData.boss_relics != null && 
/* 193 */         bossRelicChoiceIndex < this.runData.boss_relics.size()) {
/* 194 */         element.addRelicObtainStats(((BossRelicChoiceStats)this.runData.boss_relics.get(bossRelicChoiceIndex)).picked);
/* 195 */         bossRelicChoiceIndex++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 200 */       this.pathElements.add(element);
/* 201 */       element.col = tmpColumn++;
/* 202 */       element.row = this.rows;
/*     */       
/* 204 */       if (isActEndNode(element) || i == labels.size() - 1) {
/* 205 */         tmpColumn = 0;
/* 206 */         this.rows++;
/*     */       } 
/*     */     } 
/*     */     
/* 210 */     this.rows = Math.max(this.rows, this.pathElements.size() / 20);
/*     */   }
/*     */   
/*     */   public void update() {
/* 214 */     boolean isHovered = false;
/* 215 */     for (RunPathElement element : this.pathElements) {
/* 216 */       element.update();
/* 217 */       if (element.isHovered()) {
/* 218 */         isHovered = true;
/*     */       }
/*     */     } 
/* 221 */     if (isHovered) {
/* 222 */       CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isActEndNode(RunPathElement element) {
/* 227 */     return (element.nodeType == RunPathElement.PathNodeType.BOSS_TREASURE || element.nodeType == RunPathElement.PathNodeType.HEART);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb, float x, float y) {
/* 232 */     float offsetX = x;
/* 233 */     float offsetY = y;
/*     */     
/* 235 */     int sinceOffset = 0;
/* 236 */     for (RunPathElement element : this.pathElements) {
/* 237 */       element.position(offsetX, offsetY);
/*     */       
/* 239 */       if (isActEndNode(element) || sinceOffset > 20) {
/* 240 */         offsetX = x;
/* 241 */         offsetY -= RunPathElement.getApproximateHeight();
/* 242 */         sinceOffset = 0; continue;
/*     */       } 
/* 244 */       offsetX += RunPathElement.getApproximateWidth();
/* 245 */       sinceOffset++;
/*     */     } 
/*     */ 
/*     */     
/* 249 */     for (RunPathElement element : this.pathElements) {
/* 250 */       element.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   public float approximateHeight() {
/* 255 */     return this.rows * RunPathElement.getApproximateHeight();
/*     */   }
/*     */   
/*     */   public float approximateMaxWidth() {
/* 259 */     return 16.5F * RunPathElement.getApproximateWidth();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\runHistory\RunHistoryPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */