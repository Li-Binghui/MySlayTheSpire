/*     */ package com.megacrit.cardcrawl.saveAndContinue;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.ExceptionHandler;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.exceptions.SaveFileLoadError;
import com.megacrit.cardcrawl.helpers.AsyncSaver;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SaveAndContinue
/*     */ {
/*  30 */   private static final Logger logger = LogManager.getLogger(SaveAndContinue.class.getName());
/*  31 */   public static final String SAVE_PATH = "saves" + File.separator;
/*     */   
/*     */   public static String getPlayerSavePath(AbstractPlayer.PlayerClass c) {
/*  34 */     StringBuilder sb = new StringBuilder();
/*  35 */     sb.append(SAVE_PATH);
/*     */     
/*  37 */     if (CardCrawlGame.saveSlot != 0) {
/*  38 */       sb.append(CardCrawlGame.saveSlot).append("_");
/*     */     }
/*     */     
/*  41 */     sb.append(c.name()).append(".autosave");
/*  42 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static boolean saveExistsAndNotCorrupted(AbstractPlayer p) {
/*  46 */     String filepath = getPlayerSavePath(p.chosenClass);
/*  47 */     boolean fileExists = Gdx.files.local(filepath).exists();
/*     */     
/*  49 */     if (fileExists) {
/*     */       try {
/*  51 */         loadSaveFile(filepath);
/*  52 */       } catch (SaveFileLoadError saveFileLoadError) {
/*  53 */         deleteSave(p);
/*  54 */         logger.info(p.chosenClass.name() + " save INVALID!");
/*  55 */         return false;
/*     */       } 
/*  57 */       logger.info(p.chosenClass.name() + " save exists and is valid.");
/*  58 */       return true;
/*     */     } 
/*  60 */     logger.info(p.chosenClass.name() + " save does NOT exist!");
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   public static String loadSaveString(AbstractPlayer.PlayerClass c) {
/*  65 */     return loadSaveString(getPlayerSavePath(c));
/*     */   }
/*     */   
/*     */   private static String loadSaveString(String filePath) {
/*  69 */     FileHandle file = Gdx.files.local(filePath);
/*  70 */     String data = file.readString();
/*     */     
/*  72 */     if (SaveFileObfuscator.isObfuscated(data)) {
/*  73 */       return SaveFileObfuscator.decode(data, "key");
/*     */     }
/*  75 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static SaveFile loadSaveFile(AbstractPlayer.PlayerClass c) {
/*  80 */     String fileName = getPlayerSavePath(c);
/*     */     try {
/*  82 */       return loadSaveFile(fileName);
/*  83 */     } catch (SaveFileLoadError e) {
/*  84 */       logger.info("Exception occurred while loading save!");
/*  85 */       ExceptionHandler.handleException((Exception)e, logger);
/*     */ 
/*     */       
/*  88 */       Gdx.app.exit();
/*     */       
/*  90 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static SaveFile loadSaveFile(String filePath) throws SaveFileLoadError {
/* 102 */     SaveFile saveFile = null;
/* 103 */     Gson gson = new Gson();
/* 104 */     String savestr = null;
/* 105 */     Exception err = null;
/*     */     try {
/* 107 */       savestr = loadSaveString(filePath);
/* 108 */       saveFile = (SaveFile)gson.fromJson(savestr, SaveFile.class);
/* 109 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 112 */       if (Gdx.files.local(filePath).exists())
/*     */       {
/* 114 */         SaveHelper.preserveCorruptFile(filePath);
/*     */       }
/* 116 */       err = e;
/* 117 */       if (!filePath.endsWith(".backUp")) {
/* 118 */         logger.info(filePath + " was corrupt, loading backup...");
/* 119 */         return loadSaveFile(filePath + ".backUp");
/*     */       } 
/*     */     } 
/* 122 */     if (saveFile == null) {
/* 123 */       throw new SaveFileLoadError("Unable to load save file: " + filePath, err);
/*     */     }
/* 125 */     logger.info(filePath + " save file was successfully loaded.");
/* 126 */     return saveFile;
/*     */   }
/*     */   
/*     */   public static void save(SaveFile save) {
/* 130 */     CardCrawlGame.loadingSave = false;
/* 131 */     HashMap<Object, Object> params = new HashMap<>();
/*     */ 
/*     */     
/* 134 */     params.put("name", save.name);
/* 135 */     params.put("loadout", save.loadout);
/* 136 */     params.put("current_health", Integer.valueOf(save.current_health));
/* 137 */     params.put("max_health", Integer.valueOf(save.max_health));
/* 138 */     params.put("max_orbs", Integer.valueOf(save.max_orbs));
/* 139 */     params.put("gold", Integer.valueOf(save.gold));
/* 140 */     params.put("hand_size", Integer.valueOf(save.hand_size));
/* 141 */     params.put("red", Integer.valueOf(save.red));
/* 142 */     params.put("green", Integer.valueOf(save.green));
/* 143 */     params.put("blue", Integer.valueOf(save.blue));
/*     */ 
/*     */     
/* 146 */     params.put("monsters_killed", Integer.valueOf(save.monsters_killed));
/* 147 */     params.put("elites1_killed", Integer.valueOf(save.elites1_killed));
/* 148 */     params.put("elites2_killed", Integer.valueOf(save.elites2_killed));
/* 149 */     params.put("elites3_killed", Integer.valueOf(save.elites3_killed));
/* 150 */     params.put("gold_gained", Integer.valueOf(save.gold_gained));
/* 151 */     params.put("mystery_machine", Integer.valueOf(save.mystery_machine));
/* 152 */     params.put("champions", Integer.valueOf(save.champions));
/* 153 */     params.put("perfect", Integer.valueOf(save.perfect));
/* 154 */     params.put("overkill", Boolean.valueOf(save.overkill));
/* 155 */     params.put("combo", Boolean.valueOf(save.combo));
/*     */ 
/*     */     
/* 158 */     params.put("cards", save.cards);
/* 159 */     params.put("obtained_cards", save.obtained_cards);
/* 160 */     params.put("relics", save.relics);
/* 161 */     params.put("relic_counters", save.relic_counters);
/* 162 */     params.put("potions", save.potions);
/* 163 */     params.put("potion_slots", Integer.valueOf(save.potion_slots));
/*     */ 
/*     */     
/* 166 */     params.put("is_endless_mode", Boolean.valueOf(save.is_endless_mode));
/* 167 */     params.put("blights", save.blights);
/* 168 */     params.put("blight_counters", save.blight_counters);
/* 169 */     params.put("endless_increments", save.endless_increments);
/*     */ 
/*     */     
/* 172 */     params.put("chose_neow_reward", Boolean.valueOf(save.chose_neow_reward));
/* 173 */     params.put("neow_bonus", save.neow_bonus);
/* 174 */     params.put("neow_cost", save.neow_cost);
/* 175 */     params.put("is_ascension_mode", Boolean.valueOf(save.is_ascension_mode));
/* 176 */     params.put("ascension_level", Integer.valueOf(save.ascension_level));
/* 177 */     params.put("level_name", save.level_name);
/* 178 */     params.put("floor_num", Integer.valueOf(save.floor_num));
/* 179 */     params.put("act_num", Integer.valueOf(save.act_num));
/* 180 */     params.put("event_list", save.event_list);
/* 181 */     params.put("one_time_event_list", save.one_time_event_list);
/* 182 */     params.put("potion_chance", Integer.valueOf(save.potion_chance));
/* 183 */     params.put("event_chances", save.event_chances);
/* 184 */     params.put("monster_list", save.monster_list);
/* 185 */     params.put("elite_monster_list", save.elite_monster_list);
/* 186 */     params.put("boss_list", save.boss_list);
/* 187 */     params.put("play_time", Long.valueOf(save.play_time));
/* 188 */     params.put("save_date", Long.valueOf(save.save_date));
/* 189 */     params.put("seed", Long.valueOf(save.seed));
/* 190 */     params.put("special_seed", Long.valueOf(save.special_seed));
/* 191 */     params.put("seed_set", Boolean.valueOf(save.seed_set));
/* 192 */     params.put("is_daily", Boolean.valueOf(save.is_daily));
/* 193 */     params.put("is_final_act_on", Boolean.valueOf(save.is_final_act_on));
/* 194 */     params.put("has_ruby_key", Boolean.valueOf(save.has_ruby_key));
/* 195 */     params.put("has_emerald_key", Boolean.valueOf(save.has_emerald_key));
/* 196 */     params.put("has_sapphire_key", Boolean.valueOf(save.has_sapphire_key));
/* 197 */     params.put("daily_date", Long.valueOf(save.daily_date));
/* 198 */     params.put("is_trial", Boolean.valueOf(save.is_trial));
/* 199 */     params.put("daily_mods", save.daily_mods);
/* 200 */     params.put("custom_mods", save.custom_mods);
/* 201 */     params.put("boss", save.boss);
/* 202 */     params.put("purgeCost", Integer.valueOf(save.purgeCost));
/* 203 */     params.put("monster_seed_count", Integer.valueOf(save.monster_seed_count));
/* 204 */     params.put("event_seed_count", Integer.valueOf(save.event_seed_count));
/* 205 */     params.put("merchant_seed_count", Integer.valueOf(save.merchant_seed_count));
/* 206 */     params.put("card_seed_count", Integer.valueOf(save.card_seed_count));
/* 207 */     params.put("treasure_seed_count", Integer.valueOf(save.treasure_seed_count));
/* 208 */     params.put("relic_seed_count", Integer.valueOf(save.relic_seed_count));
/* 209 */     params.put("potion_seed_count", Integer.valueOf(save.potion_seed_count));
/* 210 */     params.put("ai_seed_count", Integer.valueOf(save.ai_seed_count));
/* 211 */     params.put("shuffle_seed_count", Integer.valueOf(save.shuffle_seed_count));
/* 212 */     params.put("card_random_seed_count", Integer.valueOf(save.card_random_seed_count));
/* 213 */     params.put("card_random_seed_randomizer", Integer.valueOf(save.card_random_seed_randomizer));
/* 214 */     params.put("path_x", save.path_x);
/* 215 */     params.put("path_y", save.path_y);
/* 216 */     params.put("room_x", Integer.valueOf(save.room_x));
/* 217 */     params.put("room_y", Integer.valueOf(save.room_y));
/* 218 */     params.put("spirit_count", Integer.valueOf(save.spirit_count));
/* 219 */     params.put("current_room", save.current_room);
/* 220 */     params.put("common_relics", save.common_relics);
/* 221 */     params.put("uncommon_relics", save.uncommon_relics);
/* 222 */     params.put("rare_relics", save.rare_relics);
/* 223 */     params.put("shop_relics", save.shop_relics);
/* 224 */     params.put("boss_relics", save.boss_relics);
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
/* 236 */     params.put("post_combat", Boolean.valueOf(save.post_combat));
/* 237 */     params.put("mugged", Boolean.valueOf(save.mugged));
/* 238 */     params.put("smoked", Boolean.valueOf(save.smoked));
/* 239 */     params.put("combat_rewards", save.combat_rewards);
/*     */ 
/*     */     
/* 242 */     if (AbstractDungeon.player.hasRelic("Bottled Flame")) {
/* 243 */       saveBottle(params, "Bottled Flame", "bottled_flame", ((BottledFlame)AbstractDungeon.player
/*     */ 
/*     */ 
/*     */           
/* 247 */           .getRelic("Bottled Flame")).card);
/*     */     } else {
/* 249 */       params.put("bottled_flame", null);
/*     */     } 
/*     */ 
/*     */     
/* 253 */     if (AbstractDungeon.player.hasRelic("Bottled Lightning")) {
/* 254 */       saveBottle(params, "Bottled Lightning", "bottled_lightning", ((BottledLightning)AbstractDungeon.player
/*     */ 
/*     */ 
/*     */           
/* 258 */           .getRelic("Bottled Lightning")).card);
/*     */     } else {
/* 260 */       params.put("bottled_lightning", null);
/*     */     } 
/*     */ 
/*     */     
/* 264 */     if (AbstractDungeon.player.hasRelic("Bottled Tornado")) {
/* 265 */       saveBottle(params, "Bottled Tornado", "bottled_tornado", ((BottledTornado)AbstractDungeon.player
/*     */ 
/*     */ 
/*     */           
/* 269 */           .getRelic("Bottled Tornado")).card);
/*     */     } else {
/* 271 */       params.put("bottled_tornado", null);
/*     */     } 
/*     */ 
/*     */     
/* 275 */     params.put("metric_campfire_rested", Integer.valueOf(save.metric_campfire_rested));
/* 276 */     params.put("metric_campfire_upgraded", Integer.valueOf(save.metric_campfire_upgraded));
/* 277 */     params.put("metric_campfire_rituals", Integer.valueOf(save.metric_campfire_rituals));
/* 278 */     params.put("metric_campfire_meditates", Integer.valueOf(save.metric_campfire_meditates));
/* 279 */     params.put("metric_purchased_purges", Integer.valueOf(save.metric_purchased_purges));
/* 280 */     params.put("metric_potions_floor_spawned", save.metric_potions_floor_spawned);
/* 281 */     params.put("metric_potions_floor_usage", save.metric_potions_floor_usage);
/* 282 */     params.put("metric_current_hp_per_floor", save.metric_current_hp_per_floor);
/* 283 */     params.put("metric_max_hp_per_floor", save.metric_max_hp_per_floor);
/* 284 */     params.put("metric_gold_per_floor", save.metric_gold_per_floor);
/* 285 */     params.put("metric_path_per_floor", save.metric_path_per_floor);
/* 286 */     params.put("metric_path_taken", save.metric_path_taken);
/* 287 */     params.put("metric_items_purchased", save.metric_items_purchased);
/* 288 */     params.put("metric_item_purchase_floors", save.metric_item_purchase_floors);
/* 289 */     params.put("metric_items_purged", save.metric_items_purged);
/* 290 */     params.put("metric_items_purged_floors", save.metric_items_purged_floors);
/* 291 */     params.put("metric_card_choices", save.metric_card_choices);
/* 292 */     params.put("metric_event_choices", save.metric_event_choices);
/* 293 */     params.put("metric_boss_relics", save.metric_boss_relics);
/* 294 */     params.put("metric_damage_taken", save.metric_damage_taken);
/* 295 */     params.put("metric_potions_obtained", save.metric_potions_obtained);
/* 296 */     params.put("metric_relics_obtained", save.metric_relics_obtained);
/* 297 */     params.put("metric_campfire_choices", save.metric_campfire_choices);
/* 298 */     params.put("metric_build_version", save.metric_build_version);
/* 299 */     params.put("metric_seed_played", save.metric_seed_played);
/* 300 */     params.put("metric_floor_reached", Integer.valueOf(save.metric_floor_reached));
/* 301 */     params.put("metric_playtime", Long.valueOf(save.metric_playtime));
/*     */     
/* 303 */     Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
/* 304 */     String data = gson.toJson(params);
/* 305 */     String filepath = getPlayerSavePath(AbstractDungeon.player.chosenClass);
/*     */ 
/*     */     
/* 308 */     if (Settings.isBeta) {
/* 309 */       AsyncSaver.save(filepath + "BETA", data);
/*     */     }
/*     */ 
/*     */     
/* 313 */     AsyncSaver.save(filepath, SaveFileObfuscator.encode(data, "key"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void saveBottle(HashMap<Object, Object> params, String bottleId, String save_name, AbstractCard card) {
/* 322 */     if (AbstractDungeon.player.hasRelic(bottleId)) {
/* 323 */       if (card != null) {
/* 324 */         params.put(save_name, card.cardID);
/* 325 */         params.put(save_name + "_upgrade", Integer.valueOf(card.timesUpgraded));
/* 326 */         params.put(save_name + "_misc", Integer.valueOf(card.misc));
/*     */       } else {
/* 328 */         params.put(save_name, null);
/*     */       } 
/*     */     } else {
/* 331 */       params.put(save_name, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deleteSave(AbstractPlayer p) {
/* 342 */     String savePath = p.getSaveFilePath();
/* 343 */     logger.info("DELETING " + savePath + " SAVE");
/* 344 */     Gdx.files.local(savePath).delete();
/* 345 */     Gdx.files.local(savePath + ".backUp").delete();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\saveAndContinue\SaveAndContinue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */