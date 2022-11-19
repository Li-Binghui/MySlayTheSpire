/*     */ package com.megacrit.cardcrawl.metrics;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
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
/*     */ public class MetricData
/*     */ {
/*  45 */   public int campfire_rested = 0;
/*  46 */   public int campfire_upgraded = 0;
/*  47 */   public int purchased_purges = 0;
/*  48 */   public float win_rate = 0.5F;
/*  49 */   public ArrayList<Integer> potions_floor_spawned = new ArrayList<>();
/*  50 */   public ArrayList<Integer> potions_floor_usage = new ArrayList<>();
/*  51 */   public ArrayList<Integer> current_hp_per_floor = new ArrayList<>();
/*  52 */   public ArrayList<Integer> max_hp_per_floor = new ArrayList<>();
/*  53 */   public ArrayList<Integer> gold_per_floor = new ArrayList<>();
/*  54 */   public ArrayList<String> path_per_floor = new ArrayList<>();
/*  55 */   public ArrayList<String> path_taken = new ArrayList<>();
/*  56 */   public ArrayList<String> items_purchased = new ArrayList<>();
/*  57 */   public ArrayList<Integer> item_purchase_floors = new ArrayList<>();
/*  58 */   public ArrayList<String> items_purged = new ArrayList<>();
/*  59 */   public ArrayList<Integer> items_purged_floors = new ArrayList<>();
/*  60 */   public ArrayList<HashMap> card_choices = new ArrayList<>();
/*  61 */   public ArrayList<HashMap> event_choices = new ArrayList<>();
/*  62 */   public ArrayList<HashMap> boss_relics = new ArrayList<>();
/*  63 */   public ArrayList<HashMap> damage_taken = new ArrayList<>();
/*  64 */   public ArrayList<HashMap> potions_obtained = new ArrayList<>();
/*  65 */   public ArrayList<HashMap> relics_obtained = new ArrayList<>();
/*  66 */   public ArrayList<HashMap> campfire_choices = new ArrayList<>();
/*  67 */   public String neowBonus = "";
/*  68 */   public String neowCost = "";
/*     */ 
/*     */   
/*     */   public void clearData() {
/*  72 */     this.campfire_rested = 0;
/*  73 */     this.campfire_upgraded = 0;
/*  74 */     this.purchased_purges = 0;
/*  75 */     this.potions_floor_spawned.clear();
/*  76 */     this.potions_floor_usage.clear();
/*  77 */     this.current_hp_per_floor.clear();
/*  78 */     this.max_hp_per_floor.clear();
/*  79 */     this.gold_per_floor.clear();
/*  80 */     this.path_per_floor.clear();
/*  81 */     this.path_taken.clear();
/*  82 */     this.items_purchased.clear();
/*  83 */     this.item_purchase_floors.clear();
/*  84 */     this.items_purged.clear();
/*  85 */     this.items_purged_floors.clear();
/*  86 */     this.card_choices.clear();
/*  87 */     this.event_choices.clear();
/*  88 */     this.damage_taken.clear();
/*  89 */     this.potions_obtained.clear();
/*  90 */     this.relics_obtained.clear();
/*  91 */     this.campfire_choices.clear();
/*  92 */     this.boss_relics.clear();
/*  93 */     this.neowBonus = "";
/*  94 */     this.neowCost = "";
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEncounterData() {
/*  99 */     HashMap<String, Object> combat = new HashMap<>();
/* 100 */     combat.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/* 101 */     combat.put("enemies", AbstractDungeon.lastCombatMetricKey);
/* 102 */     combat.put("damage", Integer.valueOf(GameActionManager.damageReceivedThisCombat));
/* 103 */     combat.put("turns", Integer.valueOf(GameActionManager.turn));
/* 104 */     this.damage_taken.add(combat);
/*     */   }
/*     */   
/*     */   public void addPotionObtainData(AbstractPotion potion) {
/* 108 */     HashMap<String, Object> obtainInfo = new HashMap<>();
/* 109 */     obtainInfo.put("key", potion.ID);
/* 110 */     obtainInfo.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/* 111 */     this.potions_obtained.add(obtainInfo);
/*     */   }
/*     */   
/*     */   public void addRelicObtainData(AbstractRelic relic) {
/* 115 */     HashMap<String, Object> obtainInfo = new HashMap<>();
/* 116 */     obtainInfo.put("key", relic.relicId);
/* 117 */     obtainInfo.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/* 118 */     this.relics_obtained.add(obtainInfo);
/*     */   }
/*     */   
/*     */   public void addCampfireChoiceData(String choiceKey) {
/* 122 */     addCampfireChoiceData(choiceKey, null);
/*     */   }
/*     */   
/*     */   public void addCampfireChoiceData(String choiceKey, String data) {
/* 126 */     HashMap<String, Object> choice = new HashMap<>();
/* 127 */     choice.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/* 128 */     choice.put("key", choiceKey);
/* 129 */     if (data != null) {
/* 130 */       choice.put("data", data);
/*     */     }
/* 132 */     this.campfire_choices.add(choice);
/*     */   }
/*     */   
/*     */   public void addShopPurchaseData(String key) {
/* 136 */     if (this.items_purchased.size() == this.item_purchase_floors.size())
/*     */     {
/* 138 */       this.item_purchase_floors.add(Integer.valueOf(AbstractDungeon.floorNum));
/*     */     }
/* 140 */     this.items_purchased.add(key);
/*     */   }
/*     */   
/*     */   public void addPurgedItem(String key) {
/* 144 */     if (this.items_purged.size() == this.items_purged_floors.size())
/*     */     {
/* 146 */       this.items_purged_floors.add(Integer.valueOf(AbstractDungeon.floorNum));
/*     */     }
/* 148 */     this.items_purged.add(key);
/* 149 */     this.purchased_purges++;
/*     */   }
/*     */   
/*     */   public void addNeowData(String bonus, String cost) {
/* 153 */     this.neowBonus = bonus;
/* 154 */     this.neowCost = cost;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\metrics\MetricData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */