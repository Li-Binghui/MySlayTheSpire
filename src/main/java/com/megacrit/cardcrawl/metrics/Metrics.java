/*     */ package com.megacrit.cardcrawl.metrics;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
/*     */ 
/*     */ 
/*     */ public class Metrics
/*     */   implements Runnable
/*     */ {
/*  33 */   private static final Logger logger = LogManager.getLogger(Metrics.class.getName());
/*  34 */   private HashMap<Object, Object> params = new HashMap<>();
/*  35 */   private Gson gson = new Gson();
/*     */   
/*     */   private long lastPlaytimeEnd;
/*  38 */   public static final SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
/*     */   
/*     */   public boolean death;
/*     */   public boolean trueVictory;
/*  42 */   public MonsterGroup monsters = null;
/*     */   public MetricRequestType type;
/*     */   
/*     */   public enum MetricRequestType {
/*  46 */     UPLOAD_METRICS, UPLOAD_CRASH, NONE;
/*     */   }
/*     */   
/*     */   public void setValues(boolean death, boolean trueVictor, MonsterGroup monsters, MetricRequestType type) {
/*  50 */     this.death = death;
/*  51 */     this.trueVictory = trueVictor;
/*  52 */     this.monsters = monsters;
/*  53 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendPost(String fileName) {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void addData(Object key, Object value) {
/*  63 */     this.params.put(key, value);
/*     */   }
/*     */   
/*     */   private void sendPost(String url, final String fileToDelete) {
/*  67 */     HashMap<String, Serializable> event = new HashMap<>();
/*  68 */     event.put("event", this.params);
/*  69 */     if (Settings.isBeta) {
/*  70 */       event.put("host", CardCrawlGame.playerName);
/*     */     } else {
/*  72 */       event.put("host", CardCrawlGame.alias);
/*     */     } 
/*  74 */     event.put("time", Long.valueOf(System.currentTimeMillis() / 1000L));
/*  75 */     String data = this.gson.toJson(event);
/*  76 */     logger.info("UPLOADING METRICS TO: url=" + url + ",data=" + data);
/*  77 */     HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
/*     */ 
/*     */     
/*  80 */     Net.HttpRequest httpRequest = requestBuilder.newRequest().method("POST").url(url).header("Content-Type", "application/json").header("Accept", "application/json").header("User-Agent", "curl/7.43.0").build();
/*  81 */     httpRequest.setContent(data);
/*  82 */     Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener()
/*     */         {
/*     */           public void handleHttpResponse(Net.HttpResponse httpResponse) {
/*  85 */             Metrics.logger.info("Metrics: http request response: " + httpResponse.getResultAsString());
/*  86 */             if (fileToDelete != null) {
/*  87 */               Gdx.files.local(fileToDelete).delete();
/*     */             }
/*     */           }
/*     */ 
/*     */           
/*     */           public void failed(Throwable t) {
/*  93 */             Metrics.logger.info("Metrics: http request failed: " + t.toString());
/*     */           }
/*     */ 
/*     */           
/*     */           public void cancelled() {
/*  98 */             Metrics.logger.info("Metrics: http request cancelled.");
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private void gatherAllData(boolean death, boolean trueVictor, MonsterGroup monsters) {
/* 105 */     addData("play_id", UUID.randomUUID().toString());
/* 106 */     addData("build_version", CardCrawlGame.TRUE_VERSION_NUM);
/* 107 */     addData("seed_played", Settings.seed.toString());
/* 108 */     addData("chose_seed", Boolean.valueOf(Settings.seedSet));
/* 109 */     addData("seed_source_timestamp", Long.valueOf(Settings.seedSourceTimestamp));
/* 110 */     addData("is_daily", Boolean.valueOf(Settings.isDailyRun));
/* 111 */     addData("special_seed", Settings.specialSeed);
/* 112 */     if (ModHelper.enabledMods.size() > 0) {
/* 113 */       addData("daily_mods", ModHelper.getEnabledModIDs());
/*     */     }
/*     */ 
/*     */     
/* 117 */     addData("is_trial", Boolean.valueOf(Settings.isTrial));
/* 118 */     addData("is_endless", Boolean.valueOf(Settings.isEndless));
/*     */     
/* 120 */     if (death) {
/* 121 */       AbstractPlayer player = AbstractDungeon.player;
/* 122 */       CardCrawlGame.metricData.current_hp_per_floor.add(Integer.valueOf(player.currentHealth));
/* 123 */       CardCrawlGame.metricData.max_hp_per_floor.add(Integer.valueOf(player.maxHealth));
/* 124 */       CardCrawlGame.metricData.gold_per_floor.add(Integer.valueOf(player.gold));
/*     */     } 
/*     */ 
/*     */     
/* 128 */     addData("is_ascension_mode", Boolean.valueOf(AbstractDungeon.isAscensionMode));
/* 129 */     addData("ascension_level", Integer.valueOf(AbstractDungeon.ascensionLevel));
/*     */     
/* 131 */     addData("neow_bonus", CardCrawlGame.metricData.neowBonus);
/* 132 */     addData("neow_cost", CardCrawlGame.metricData.neowCost);
/*     */     
/* 134 */     addData("is_beta", Boolean.valueOf(Settings.isBeta));
/* 135 */     addData("is_prod", Boolean.valueOf(Settings.isDemo));
/* 136 */     addData("victory", Boolean.valueOf(!death));
/* 137 */     addData("floor_reached", Integer.valueOf(AbstractDungeon.floorNum));
/* 138 */     if (trueVictor) {
/* 139 */       addData("score", Integer.valueOf(VictoryScreen.calcScore(!death)));
/*     */     } else {
/* 141 */       addData("score", Integer.valueOf(DeathScreen.calcScore(!death)));
/*     */     } 
/* 143 */     this.lastPlaytimeEnd = System.currentTimeMillis() / 1000L;
/* 144 */     addData("timestamp", Long.valueOf(this.lastPlaytimeEnd));
/* 145 */     addData("local_time", timestampFormatter.format(Calendar.getInstance().getTime()));
/* 146 */     addData("playtime", Long.valueOf((long)CardCrawlGame.playtime));
/* 147 */     addData("player_experience", Long.valueOf(Settings.totalPlayTime));
/* 148 */     addData("master_deck", AbstractDungeon.player.masterDeck.getCardIdsForMetrics());
/* 149 */     addData("relics", AbstractDungeon.player.getRelicNames());
/* 150 */     addData("gold", Integer.valueOf(AbstractDungeon.player.gold));
/* 151 */     addData("campfire_rested", Integer.valueOf(CardCrawlGame.metricData.campfire_rested));
/* 152 */     addData("campfire_upgraded", Integer.valueOf(CardCrawlGame.metricData.campfire_upgraded));
/* 153 */     addData("purchased_purges", Integer.valueOf(CardCrawlGame.metricData.purchased_purges));
/* 154 */     addData("potions_floor_spawned", CardCrawlGame.metricData.potions_floor_spawned);
/* 155 */     addData("potions_floor_usage", CardCrawlGame.metricData.potions_floor_usage);
/* 156 */     addData("current_hp_per_floor", CardCrawlGame.metricData.current_hp_per_floor);
/* 157 */     addData("max_hp_per_floor", CardCrawlGame.metricData.max_hp_per_floor);
/* 158 */     addData("gold_per_floor", CardCrawlGame.metricData.gold_per_floor);
/* 159 */     addData("path_per_floor", CardCrawlGame.metricData.path_per_floor);
/* 160 */     addData("path_taken", CardCrawlGame.metricData.path_taken);
/* 161 */     addData("items_purchased", CardCrawlGame.metricData.items_purchased);
/* 162 */     addData("item_purchase_floors", CardCrawlGame.metricData.item_purchase_floors);
/* 163 */     addData("items_purged", CardCrawlGame.metricData.items_purged);
/* 164 */     addData("items_purged_floors", CardCrawlGame.metricData.items_purged_floors);
/* 165 */     addData("character_chosen", AbstractDungeon.player.chosenClass.name());
/* 166 */     addData("card_choices", CardCrawlGame.metricData.card_choices);
/* 167 */     addData("event_choices", CardCrawlGame.metricData.event_choices);
/* 168 */     addData("boss_relics", CardCrawlGame.metricData.boss_relics);
/* 169 */     addData("damage_taken", CardCrawlGame.metricData.damage_taken);
/* 170 */     addData("potions_obtained", CardCrawlGame.metricData.potions_obtained);
/* 171 */     addData("relics_obtained", CardCrawlGame.metricData.relics_obtained);
/* 172 */     addData("campfire_choices", CardCrawlGame.metricData.campfire_choices);
/*     */ 
/*     */     
/* 175 */     addData("circlet_count", Integer.valueOf(AbstractDungeon.player.getCircletCount()));
/*     */ 
/*     */     
/* 178 */     Prefs pref = AbstractDungeon.player.getPrefs();
/*     */     
/* 180 */     int numVictory = pref.getInteger("WIN_COUNT", 0);
/* 181 */     int numDeath = pref.getInteger("LOSE_COUNT", 0);
/*     */     
/* 183 */     if (numVictory <= 0) {
/* 184 */       addData("win_rate", Float.valueOf(0.0F));
/*     */     } else {
/* 186 */       addData("win_rate", Integer.valueOf(numVictory / (numDeath + numVictory)));
/*     */     } 
/*     */     
/* 189 */     if (death && monsters != null) {
/* 190 */       addData("killed_by", AbstractDungeon.lastCombatMetricKey);
/*     */     } else {
/* 192 */       addData("killed_by", null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void gatherAllDataAndSend(boolean death, boolean trueVictor, MonsterGroup monsters) {
/* 197 */     if (DeathScreen.shouldUploadMetricData()) {
/* 198 */       gatherAllData(death, trueVictor, monsters);
/* 199 */       sendPost(null);
/*     */     } 
/*     */   }
/*     */   public void gatherAllDataAndSave(boolean death, boolean trueVictor, MonsterGroup monsters) {
/*     */     FileHandle file;
/* 204 */     gatherAllData(death, trueVictor, monsters);
/*     */     
/* 206 */     String data = this.gson.toJson(this.params);
/*     */ 
/*     */     
/* 209 */     if (!Settings.isDailyRun) {
/* 210 */       String local_runs_save_path = "runs" + File.separator;
/*     */       
/* 212 */       switch (CardCrawlGame.saveSlot) {
/*     */         case 0:
/*     */           break;
/*     */         
/*     */         default:
/* 217 */           local_runs_save_path = local_runs_save_path + CardCrawlGame.saveSlot + "_";
/*     */           break;
/*     */       } 
/*     */       
/* 221 */       local_runs_save_path = local_runs_save_path + AbstractDungeon.player.chosenClass.name() + File.separator + this.lastPlaytimeEnd + ".run";
/*     */ 
/*     */       
/* 224 */       file = Gdx.files.local(local_runs_save_path);
/*     */     } else {
/* 226 */       String tmpPath = "runs" + File.separator;
/*     */       
/* 228 */       switch (CardCrawlGame.saveSlot) {
/*     */         case 0:
/*     */           break;
/*     */         
/*     */         default:
/* 233 */           tmpPath = tmpPath + CardCrawlGame.saveSlot + "_";
/*     */           break;
/*     */       } 
/*     */       
/* 237 */       file = Gdx.files.local(tmpPath + "DAILY" + File.separator + this.lastPlaytimeEnd + ".run");
/*     */     } 
/*     */     
/* 240 */     file.writeString(data, false);
/* 241 */     removeExcessRunFiles();
/*     */   }
/*     */   
/*     */   private void removeExcessRunFiles() {
/* 245 */     if (!Settings.isConsoleBuild) {
/*     */       return;
/*     */     }
/*     */     
/* 249 */     FileHandle fh = Gdx.files.local("runs");
/* 250 */     FileHandle[] allFolders = fh.list();
/* 251 */     HashMap<String, FileHandle> map = new HashMap<>();
/* 252 */     List<String> runNames = new ArrayList<>();
/*     */ 
/*     */     
/* 255 */     for (FileHandle fileHandle : allFolders) {
/* 256 */       FileHandle[] runs = fileHandle.list("run");
/* 257 */       for (FileHandle j : runs) {
/* 258 */         runNames.add(j.name());
/* 259 */         map.put(j.name(), j);
/*     */       } 
/*     */     } 
/*     */     
/* 263 */     int excessFileThreshold = 500;
/* 264 */     int numFilesToDelete = runNames.size() - excessFileThreshold;
/*     */ 
/*     */     
/* 267 */     if (runNames.size() < excessFileThreshold) {
/*     */       return;
/*     */     }
/*     */     
/* 271 */     Collections.sort(runNames);
/*     */ 
/*     */     
/* 274 */     for (int i = 0; i < numFilesToDelete; i++) {
/* 275 */       if (map.containsKey(runNames.get(i))) {
/* 276 */         logger.info("DELETING EXCESS RUN: " + map.get(((String)runNames.get(i)).toString()));
/* 277 */         ((FileHandle)map.get(runNames.get(i))).delete();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 284 */     switch (this.type) {
/*     */       case UPLOAD_CRASH:
/* 286 */         if (!Settings.isModded) {
/* 287 */           gatherAllDataAndSend(this.death, false, this.monsters);
/*     */         }
/*     */         return;
/*     */       case UPLOAD_METRICS:
/* 291 */         if (!Settings.isModded) {
/* 292 */           gatherAllDataAndSend(this.death, this.trueVictory, this.monsters);
/*     */         }
/*     */         return;
/*     */     } 
/* 296 */     logger.info("Unspecified MetricRequestType: " + this.type.name() + " in run()");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\metrics\Metrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */