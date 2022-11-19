/*     */ package com.megacrit.cardcrawl.audio;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class SoundMaster
/*     */ {
/*  15 */   private static final Logger logger = LogManager.getLogger(SoundMaster.class.getName());
/*  16 */   private HashMap<String, Sfx> map = new HashMap<>();
/*  17 */   private ArrayList<SoundInfo> fadeOutList = new ArrayList<>();
/*     */   private static final String SFX_DIR = "audio/sound/";
/*     */   
/*     */   public SoundMaster() {
/*  21 */     long startTime = System.currentTimeMillis();
/*  22 */     Settings.SOUND_VOLUME = Settings.soundPref.getFloat("Sound Volume", 0.5F);
/*     */     
/*  24 */     this.map.put("AMBIANCE_BOTTOM", load("SOTE_Level1_Ambience_v6.ogg"));
/*  25 */     this.map.put("AMBIANCE_CITY", load("SOTE_SFX_CityAmb_v1.ogg"));
/*  26 */     this.map.put("AMBIANCE_BEYOND", load("STS_SFX_BeyondAmb_v1.ogg"));
/*  27 */     this.map.put("SCENE_TORCH_EXTINGUISH", load("STS_SFX_BGTorchExtinguish_v1.ogg"));
/*  28 */     this.map.put("APPEAR", load("SOTE_SFX_Appear_v2.ogg"));
/*     */ 
/*     */     
/*  31 */     this.map.put("ATTACK_DAGGER_1", load("STS_SFX_DaggerThrow_1.ogg"));
/*  32 */     this.map.put("ATTACK_DAGGER_2", load("STS_SFX_DaggerThrow_2.ogg"));
/*  33 */     this.map.put("ATTACK_DAGGER_3", load("STS_SFX_DaggerThrow_3.ogg"));
/*  34 */     this.map.put("ATTACK_DAGGER_4", load("STS_SFX_DaggerThrow_2_1.ogg"));
/*  35 */     this.map.put("ATTACK_DAGGER_5", load("STS_SFX_DaggerThrow_2_2.ogg"));
/*  36 */     this.map.put("ATTACK_DAGGER_6", load("STS_SFX_DaggerThrow_2_3.ogg"));
/*  37 */     this.map.put("ATTACK_DEFECT_BEAM", load("STS_SFX_DefectBeam_v1.ogg"));
/*  38 */     this.map.put("ATTACK_FAST", load("SOTE_SFX_FastAtk_v2.ogg"));
/*  39 */     this.map.put("ATTACK_FIRE", load("SOTE_SFX_FireIgnite_2_v1.ogg"));
/*  40 */     this.map.put("ATTACK_FLAME_BARRIER", load("STS_SFX_FlameBarrier_v2.ogg"));
/*  41 */     this.map.put("ATTACK_HEAVY", load("SOTE_SFX_HeavyAtk_v2.ogg"));
/*  42 */     this.map.put("ATTACK_IRON_1", load("SOTE_SFX_IronClad_Atk_RR1_v2.ogg"));
/*  43 */     this.map.put("ATTACK_IRON_2", load("SOTE_SFX_IronClad_Atk_RR2_v2.ogg"));
/*  44 */     this.map.put("ATTACK_IRON_3", load("SOTE_SFX_IronClad_Atk_RR3_v2.ogg"));
/*  45 */     this.map.put("ATTACK_MAGIC_BEAM", load("SOTE_SFX_SlowMagic_Beam_v1.ogg"));
/*  46 */     this.map.put("ATTACK_MAGIC_BEAM_SHORT", load("SOTE_SFX_SlowMagic_BeamShort_v1.ogg"));
/*  47 */     this.map.put("ATTACK_MAGIC_FAST_1", load("SOTE_SFX_MagicFast_1_v1.ogg"));
/*  48 */     this.map.put("ATTACK_MAGIC_FAST_2", load("SOTE_SFX_MagicFast_2_v1.ogg"));
/*  49 */     this.map.put("ATTACK_MAGIC_FAST_3", load("SOTE_SFX_MagicFast_3_v1.ogg"));
/*  50 */     this.map.put("ATTACK_MAGIC_SLOW_1", load("SOTE_SFX_SlowMagic_1_v1.ogg"));
/*  51 */     this.map.put("ATTACK_MAGIC_SLOW_2", load("SOTE_SFX_SlowMagic_2_v1.ogg"));
/*  52 */     this.map.put("ATTACK_PIERCING_WAIL", load("STS_SFX_PiercingWail_v2.ogg"));
/*  53 */     this.map.put("ATTACK_POISON", load("SOTE_SFX_PoisonCard_1_v1.ogg"));
/*  54 */     this.map.put("ATTACK_POISON2", load("SOTE_SFX_PoisonCard_2_v1.ogg"));
/*  55 */     this.map.put("ATTACK_WHIFF_1", load("SOTE_SFX_SlowThrow_1_v1.ogg"));
/*  56 */     this.map.put("ATTACK_WHIFF_2", load("SOTE_SFX_SlowThrow_2_v1.ogg"));
/*  57 */     this.map.put("ATTACK_WHIRLWIND", load("STS_SFX_Whirlwind_v2.ogg"));
/*  58 */     this.map.put("ATTACK_BOWLING", load("bowling.ogg"));
/*     */ 
/*     */     
/*  61 */     this.map.put("CARD_DRAW_8", load("STS_SFX_CardDeal8_v1.ogg"));
/*  62 */     this.map.put("KEY_OBTAIN", load("SOTE_SFX_Key_v2.ogg"));
/*     */     
/*  64 */     this.map.put("AUTOMATON_ORB_SPAWN", load("STS_SFX_AutomatonOrbSpawn_v1.ogg"));
/*  65 */     this.map.put("BATTLE_START_BOSS", load("STS_SFX_BattleStart_Boss_v1.ogg"));
/*  66 */     this.map.put("BATTLE_START_1", load("STS_SFX_BattleStart_1_v1.ogg"));
/*  67 */     this.map.put("BATTLE_START_2", load("STS_SFX_BattleStart_2_v1.ogg"));
/*  68 */     this.map.put("BELL", load("SOTE_SFX_Bell_v1.ogg"));
/*  69 */     this.map.put("BLOCK_ATTACK", load("SOTE_SFX_BlockAtk_v2.ogg"));
/*  70 */     this.map.put("BLOCK_BREAK", load("SOTE_SFX_DefenseBreak_v2.ogg"));
/*  71 */     this.map.put("BLOCK_GAIN_1", load("SOTE_SFX_GainDefense_RR1_v3.ogg"));
/*  72 */     this.map.put("BLOCK_GAIN_2", load("SOTE_SFX_GainDefense_RR3_v3.ogg"));
/*  73 */     this.map.put("BLOCK_GAIN_3", load("SOTE_SFX_GainDefense_RR2_v3.ogg"));
/*  74 */     this.map.put("BLOOD_SPLAT", load("SOTE_SFX_Blood_2_v2.ogg"));
/*  75 */     this.map.put("BLOOD_SWISH", load("SOTE_SFX_Blood_1_v2.ogg"));
/*  76 */     this.map.put("BLUNT_FAST", load("SOTE_SFX_FastBlunt_v2.ogg"));
/*  77 */     this.map.put("BLUNT_HEAVY", load("SOTE_SFX_HeavyBlunt_v2.ogg"));
/*  78 */     this.map.put("BOSS_VICTORY_STINGER", load("STS_BossVictoryStinger_1_v3_SFX.ogg"));
/*  79 */     this.map.put("BUFF_1", load("SOTE_SFX_Buff_1_v1.ogg"));
/*  80 */     this.map.put("BUFF_2", load("SOTE_SFX_Buff_2_v1.ogg"));
/*  81 */     this.map.put("BUFF_3", load("SOTE_SFX_Buff_3_v1.ogg"));
/*  82 */     this.map.put("BYRD_DEATH", load("STS_SFX_ByrdDefeat_v2.ogg"));
/*  83 */     this.map.put("CARD_BURN", load("STS_SFX_BurnCard_v1.ogg"));
/*  84 */     this.map.put("CARD_EXHAUST", load("SOTE_SFX_ExhaustCard.ogg"));
/*  85 */     this.map.put("CARD_OBTAIN", load("SOTE_SFX_ObtainCard_v2.ogg"));
/*  86 */     this.map.put("CARD_REJECT", load("SOTE_SFX_CardReject_v1.ogg"));
/*  87 */     this.map.put("CARD_SELECT", load("SOTE_SFX_CardSelect_v2.ogg"));
/*  88 */     this.map.put("CARD_UPGRADE", load("SOTE_SFX_UpgradeCard_v1.ogg"));
/*  89 */     this.map.put("CEILING_BOOM_1", load("SOTE_SFX_CeilingDust1_Boom_v1.ogg"));
/*  90 */     this.map.put("CEILING_BOOM_2", load("SOTE_SFX_CeilingDust2_Boom_v1.ogg"));
/*  91 */     this.map.put("CEILING_BOOM_3", load("SOTE_SFX_CeilingDust3_Boom_v1.ogg"));
/*  92 */     this.map.put("CEILING_DUST_1", load("SOTE_SFX_CeilingDust1_v1.ogg"));
/*  93 */     this.map.put("CEILING_DUST_2", load("SOTE_SFX_CeilingDust2_v1.ogg"));
/*  94 */     this.map.put("CEILING_DUST_3", load("SOTE_SFX_CeilingDust3_v1.ogg"));
/*  95 */     this.map.put("CHEST_OPEN", load("SOTE_SFX_ChestOpen_v2.ogg"));
/*  96 */     this.map.put("CHOSEN_DEATH", load("STS_SFX_ChosenDefeat_v2.ogg"));
/*  97 */     this.map.put("DARKLING_REGROW_1", load("STS_SFX_DarklingRegrow_v2.ogg"));
/*  98 */     this.map.put("DARKLING_REGROW_2", load("STS_SFX_DarklingRegrow_2_v2.ogg"));
/*  99 */     this.map.put("DEATH_STINGER", load("STS_DeathStinger_v4_SFX.ogg"));
/* 100 */     this.map.put("DEBUFF_1", load("SOTE_SFX_Debuff_1_v1.ogg"));
/* 101 */     this.map.put("DEBUFF_2", load("SOTE_SFX_Debuff_2_v1.ogg"));
/* 102 */     this.map.put("DEBUFF_3", load("SOTE_SFX_Debuff_3_v1.ogg"));
/* 103 */     this.map.put("DECK_CLOSE", load("SOTE_SFX_UI_Parchment_2_v1.ogg"));
/* 104 */     this.map.put("DECK_OPEN", load("SOTE_SFX_UI_Parchment_3_v1.ogg"));
/* 105 */     this.map.put("DUNGEON_TRANSITION", load("SOTE_SFX_DungeonGate.ogg"));
/* 106 */     this.map.put("END_TURN", load("SOTE_SFX_EndTurn_v2.ogg"));
/* 107 */     this.map.put("ENEMY_TURN", load("SOTE_SFX_EnemyTurn_v3.ogg"));
/* 108 */     this.map.put("EVENT_PURCHASE", load("SOTE_SFX_EventPurchase.ogg"));
/*     */ 
/*     */     
/* 111 */     this.map.put("EVENT_ANCIENT", load("STS_SFX_AncientWriting_v1.ogg"));
/* 112 */     this.map.put("EVENT_FALLING", load("STS_SFX_Falling_v1.ogg"));
/* 113 */     this.map.put("EVENT_FORGE", load("STS_SFX_OminousForge_v1.ogg"));
/* 114 */     this.map.put("EVENT_FORGOTTEN", load("STS_SFX_ForgottenShrine_v1.ogg"));
/* 115 */     this.map.put("EVENT_FOUNTAIN", load("STS_SFX_CursedTome_v1.ogg"));
/* 116 */     this.map.put("EVENT_GHOSTS", load("STS_SFX_CouncilGhosts-Mausoleum_v1.ogg"));
/* 117 */     this.map.put("EVENT_GOLDEN", load("STS_SFX_GoldenIdolBoulder_v1.ogg"));
/* 118 */     this.map.put("EVENT_GOOP", load("STS_SFX_WorldOfGoop_v1.ogg"));
/* 119 */     this.map.put("EVENT_LAB", load("STS_SFX_Lab_v1.ogg"));
/* 120 */     this.map.put("EVENT_LIVING_WALL", load("STS_SFX_LivingWall_v1.ogg"));
/* 121 */     this.map.put("EVENT_NLOTH", load("STS_SFX_NLoth_v1.ogg"));
/* 122 */     this.map.put("EVENT_OOZE", load("STS_SFX_ScrapOoze_v1.ogg"));
/* 123 */     this.map.put("EVENT_PORTAL", load("STS_SFX_SecretPortal_v1.ogg"));
/* 124 */     this.map.put("EVENT_SENSORY", load("STS_SFX_SensoryStone_v1.ogg"));
/* 125 */     this.map.put("EVENT_SERPENT", load("STS_SFX_Ssserpent_v1.ogg"));
/* 126 */     this.map.put("EVENT_SHINING", load("STS_SFX_ShiningLight_v1.ogg"));
/* 127 */     this.map.put("EVENT_SKULL", load("STS_SFX_KnowingSkull_v1.ogg"));
/* 128 */     this.map.put("EVENT_SPIRITS", load("STS_SFX_BonfireSpirits_v1.ogg"));
/* 129 */     this.map.put("EVENT_TOME", load("STS_SFX_CursedTome_v1.ogg"));
/* 130 */     this.map.put("EVENT_WINDING", load("STS_SFX_WindingHalls_v1.ogg"));
/* 131 */     this.map.put("EVENT_VAMP_BITE", load("STS_SFX_VampireBite_v2.ogg"));
/*     */     
/* 133 */     this.map.put("GHOST_FLAMES", load("SOTE_SFX_GhostGuardianFlames_v1.ogg"));
/* 134 */     this.map.put("GHOST_ORB_IGNITE_1", load("SOTE_SFX_BossOrbIgnite1_v2.ogg"));
/* 135 */     this.map.put("GHOST_ORB_IGNITE_2", load("SOTE_SFX_BossOrbIgnite2_v2.ogg"));
/* 136 */     this.map.put("GOLD_GAIN", load("SOTE_SFX_Gold_RR1_v3.ogg"));
/* 137 */     this.map.put("GOLD_GAIN_2", load("SOTE_SFX_Gold_RR2_v3.ogg"));
/* 138 */     this.map.put("GOLD_GAIN_3", load("SOTE_SFX_Gold_RR3_v3.ogg"));
/* 139 */     this.map.put("GOLD_GAIN_4", load("SOTE_SFX_Gold_RR4_v3.ogg"));
/* 140 */     this.map.put("GOLD_GAIN_5", load("SOTE_SFX_Gold_RR5_v3.ogg"));
/* 141 */     this.map.put("GOLD_JINGLE", load("SOTE_SFX_Gold_v1.ogg"));
/* 142 */     this.map.put("GUARDIAN_ROLL_UP", load("SOTE_SFX_BossBallTransform_v1.ogg"));
/* 143 */     this.map.put("HEAL_1", load("SOTE_SFX_HealShort_1_v2.ogg"));
/* 144 */     this.map.put("HEAL_2", load("SOTE_SFX_HealShort_2_v2.ogg"));
/* 145 */     this.map.put("HEAL_3", load("SOTE_SFX_HealShort_3_v2.ogg"));
/* 146 */     this.map.put("HEART_BEAT", load("SLS_SFX_HeartBeat_Resonant_v1.ogg"));
/* 147 */     this.map.put("HEART_SIMPLE", load("SLS_SFX_HeartBeat_Simple_v1.ogg"));
/* 148 */     this.map.put("HOVER_CHARACTER", load("SOTE_SFX_UI_Parchment_3_v1.ogg"));
/* 149 */     this.map.put("INTIMIDATE", load("SOTE_SFX_IntimidateCard_v1.ogg"));
/* 150 */     this.map.put("MAP_CLOSE", load("SOTE_SFX_UI_Parchment_1_v2.ogg"));
/* 151 */     this.map.put("MAP_HOVER_1", load("SOTE_SFX_MapHover_1_v1.ogg"));
/* 152 */     this.map.put("MAP_HOVER_2", load("SOTE_SFX_MapHover_2_v1.ogg"));
/* 153 */     this.map.put("MAP_HOVER_3", load("SOTE_SFX_MapHover_3_v1.ogg"));
/* 154 */     this.map.put("MAP_HOVER_4", load("SOTE_SFX_MapHover_4_v1.ogg"));
/* 155 */     this.map.put("MAP_OPEN", load("SOTE_SFX_Map_1_v3.ogg"));
/* 156 */     this.map.put("MAP_OPEN_2", load("SOTE_SFX_Map_2_v3.ogg"));
/* 157 */     this.map.put("MAP_SELECT_1", load("SOTE_SFX_MapSelect_1_v1.ogg"));
/* 158 */     this.map.put("MAP_SELECT_2", load("SOTE_SFX_MapSelect_2_v1.ogg"));
/* 159 */     this.map.put("MAP_SELECT_3", load("SOTE_SFX_MapSelect_3_v1.ogg"));
/* 160 */     this.map.put("MAP_SELECT_4", load("SOTE_SFX_MapSelect_4_v1.ogg"));
/* 161 */     this.map.put("MAW_DEATH", load("STS_SFX_MawDefeat_v2.ogg"));
/* 162 */     this.map.put("NECRONOMICON", load("SOTE_SFX_NecroLaugh_v2.ogg"));
/* 163 */     this.map.put("NULLIFY_SFX", load("STS_SFX_Nullify_v1.ogg"));
/* 164 */     this.map.put("POTION_1", load("SOTE_SFX_Potion_1_v2.ogg"));
/* 165 */     this.map.put("POTION_2", load("SOTE_SFX_Potion_2_v2.ogg"));
/* 166 */     this.map.put("POTION_3", load("SOTE_SFX_Potion_3_v2.ogg"));
/* 167 */     this.map.put("POTION_DROP_1", load("SOTE_SFX_DropPotion_1_v1.ogg"));
/* 168 */     this.map.put("POTION_DROP_2", load("SOTE_SFX_DropPotion_2_v1.ogg"));
/*     */ 
/*     */     
/* 171 */     this.map.put("JAW_WORM_DEATH", load("STS_SFX_JawWormDefeat_v2.ogg"));
/* 172 */     this.map.put("MONSTER_AUTOMATON_SUMMON", load("STS_SFX_BronzeAutomatonSummon_v2.ogg"));
/* 173 */     this.map.put("MONSTER_AWAKENED_ATTACK", load("STS_SFX_AwakenedOne3Atk_v1.ogg"));
/* 174 */     this.map.put("MONSTER_AWAKENED_POUNCE", load("STS_SFX_AwakenedOnePounce_v2.ogg"));
/* 175 */     this.map.put("MONSTER_BYRD_ATTACK_0", load("STS_SFX_ByrdAtk1_v2.ogg"));
/* 176 */     this.map.put("MONSTER_BYRD_ATTACK_1", load("STS_SFX_ByrdAtk2_v2.ogg"));
/* 177 */     this.map.put("MONSTER_BYRD_ATTACK_2", load("STS_SFX_ByrdAtk3_v2.ogg"));
/* 178 */     this.map.put("MONSTER_BYRD_ATTACK_3", load("STS_SFX_ByrdAtk4_v2.ogg"));
/* 179 */     this.map.put("MONSTER_BYRD_ATTACK_4", load("STS_SFX_ByrdAtk5_v2.ogg"));
/* 180 */     this.map.put("MONSTER_BYRD_ATTACK_5", load("STS_SFX_ByrdAtk6_v2.ogg"));
/* 181 */     this.map.put("MONSTER_CHAMP_CHARGE", load("STS_SFX_ChampChargeUp_v2.ogg"));
/* 182 */     this.map.put("MONSTER_CHAMP_SLAP", load("STS_SFX_ChampSlap_v2.ogg"));
/* 183 */     this.map.put("MONSTER_COLLECTOR_DEBUFF", load("STS_SFX_CollectorDebuff_v2.ogg"));
/* 184 */     this.map.put("MONSTER_COLLECTOR_SUMMON", load("STS_SFX_CollectorSummon_v2.ogg"));
/* 185 */     this.map.put("MONSTER_DONU_DEFENSE", load("STS_SFX_DonuDecaDefense_v2.ogg"));
/* 186 */     this.map.put("MONSTER_GUARDIAN_DESTROY", load("STS_SFX_Guardian3Destroy_v2.ogg"));
/* 187 */     this.map.put("MONSTER_JAW_WORM_BELLOW", load("STS_SFX_JawWormBellow_v1.ogg"));
/* 188 */     this.map.put("MONSTER_SLIME_ATTACK", load("STS_SFX_SlimedAtk_v2.ogg"));
/* 189 */     this.map.put("MONSTER_BOOK_STAB_0", load("STS_SFX_BookofStabbing1_v1.ogg"));
/* 190 */     this.map.put("MONSTER_BOOK_STAB_1", load("STS_SFX_BookofStabbing2_v1.ogg"));
/* 191 */     this.map.put("MONSTER_BOOK_STAB_2", load("STS_SFX_BookofStabbing3_v1.ogg"));
/* 192 */     this.map.put("MONSTER_BOOK_STAB_3", load("STS_SFX_BookofStabbing4_v1.ogg"));
/* 193 */     this.map.put("MONSTER_SNECKO_GLARE", load("STS_SFX_SneckoGlareWave_v1.ogg"));
/*     */ 
/*     */     
/* 196 */     this.map.put("POWER_CONFUSION", load("STS_SFX_Confused_v2.ogg"));
/* 197 */     this.map.put("POWER_CONSTRICTED", load("STS_SFX_Constrict_v2.ogg"));
/* 198 */     this.map.put("POWER_DEXTERITY", load("STS_SFX_Dexterity_v2.ogg"));
/* 199 */     this.map.put("POWER_ENTANGLED", load("STS_SFX_Entangle_v2.ogg"));
/* 200 */     this.map.put("POWER_FLIGHT", load("STS_SFX_Flight_v2.ogg"));
/* 201 */     this.map.put("POWER_FOCUS", load("STS_SFX_Focus_v2.ogg"));
/* 202 */     this.map.put("POWER_INTANGIBLE", load("STS_SFX_Intangible_v1.ogg"));
/* 203 */     this.map.put("POWER_METALLICIZE", load("STS_SFX_Metallicize_v2.ogg"));
/* 204 */     this.map.put("POWER_PLATED", load("STS_SFX_PlateArmor_v2.ogg"));
/* 205 */     this.map.put("POWER_POISON", load("STS_SFX_PoisonApply_v1.ogg"));
/* 206 */     this.map.put("POWER_SHACKLE", load("STS_SFX_Shackled_v1.ogg"));
/* 207 */     this.map.put("POWER_STRENGTH", load("STS_SFX_Strength_v1.ogg"));
/* 208 */     this.map.put("POWER_TIME_WARP", load("STS_SFX_TimeWarp_v2.ogg"));
/*     */     
/* 210 */     this.map.put("RAGE", load("SOTE_SFX_RageCard_v1.ogg"));
/* 211 */     this.map.put("RELIC_DROP_CLINK", load("SOTE_SFX_DropRelic_Clink.ogg"));
/* 212 */     this.map.put("RELIC_DROP_FLAT", load("SOTE_SFX_DropRelic_Flat.ogg"));
/* 213 */     this.map.put("RELIC_DROP_HEAVY", load("SOTE_SFX_DropRelic_Heavy.ogg"));
/* 214 */     this.map.put("RELIC_DROP_MAGICAL", load("SOTE_SFX_DropRelic_Magical.ogg"));
/* 215 */     this.map.put("RELIC_DROP_ROCKY", load("SOTE_SFX_DropRelic_Rocky.ogg"));
/* 216 */     this.map.put("REST_FIRE_DRY", load("SOTE_SFX_RestFireDry_v2.ogg"));
/* 217 */     this.map.put("REST_FIRE_WET", load("SOTE_SFX_RestFireWet_v2.ogg"));
/* 218 */     this.map.put("SHOP_CLOSE", load("SOTE_SFX_ShopRugClose_v1.ogg"));
/* 219 */     this.map.put("SHOP_OPEN", load("SOTE_SFX_ShopRugOpen_v1.ogg"));
/* 220 */     this.map.put("SHOP_PURCHASE", load("SOTE_SFX_CashRegister.ogg"));
/* 221 */     this.map.put("SHOVEL", load("sts_sfx_shovel_v1.ogg"));
/* 222 */     this.map.put("SINGING_BOWL", load("SOTE_SFX_Relic_PrayerBowl_Soft.ogg"));
/* 223 */     this.map.put("SLEEP_1-1", load("STS_SleepJingle_1a_NewMix_v1.ogg"));
/* 224 */     this.map.put("SLEEP_1-2", load("STS_SleepJingle_1b_NewMix_v1.ogg"));
/* 225 */     this.map.put("SLEEP_1-3", load("STS_SleepJingle_1c_NewMix_v1.ogg"));
/* 226 */     this.map.put("SLEEP_2-1", load("STS_SleepJingle_2a_NewMix_v1.ogg"));
/* 227 */     this.map.put("SLEEP_2-2", load("STS_SleepJingle_2b_NewMix_v1.ogg"));
/* 228 */     this.map.put("SLEEP_2-3", load("STS_SleepJingle_2c_NewMix_v1.ogg"));
/* 229 */     this.map.put("SLEEP_3-1", load("STS_SleepJingle_3a_NewMix_v1.ogg"));
/* 230 */     this.map.put("SLEEP_3-2", load("STS_SleepJingle_3b_NewMix_v1.ogg"));
/* 231 */     this.map.put("SLEEP_3-3", load("STS_SleepJingle_3c_NewMix_v1.ogg"));
/* 232 */     this.map.put("SLEEP_BLANKET", load("SOTE_SFX_SleepBlanket_v1.ogg"));
/* 233 */     this.map.put("SLIME_ATTACK", load("SOTE_SFX_SlimeAtk_1_v1.ogg"));
/* 234 */     this.map.put("SLIME_ATTACK_2", load("SOTE_SFX_SlimeAtk_2_v1.ogg"));
/* 235 */     this.map.put("SLIME_BLINK_1", load("SOTE_SFX_SlimeBlink_1_v2.ogg"));
/* 236 */     this.map.put("SLIME_BLINK_2", load("SOTE_SFX_SlimeBlink_2_v1.ogg"));
/* 237 */     this.map.put("SLIME_BLINK_3", load("SOTE_SFX_SlimeBlink_3_v1.ogg"));
/* 238 */     this.map.put("SLIME_BLINK_4", load("SOTE_SFX_SlimeBlink_4_v1.ogg"));
/* 239 */     this.map.put("SLIME_SPLIT", load("SOTE_SFX_SlimeSplit_v1.ogg"));
/* 240 */     this.map.put("SNECKO_DEATH", load("STS_SFX_SerpentSneckoDefeat_v2.ogg"));
/* 241 */     this.map.put("SPHERE_DETECT_VO_1", load("STS_SFX_GuardianOutsiderDetected_1_v1.ogg"));
/* 242 */     this.map.put("SPHERE_DETECT_VO_2", load("STS_SFX_GuardianOutsiderDetected_2_v1.ogg"));
/* 243 */     this.map.put("SPLASH", load("SOTE_Logo_Echoing_ShortTail.ogg"));
/* 244 */     this.map.put("SPORE_CLOUD_RELEASE", load("STS_SFX_SporeCloud.ogg"));
/* 245 */     this.map.put("STAB_BOOK_DEATH", load("STS_SFX_BookOfStabbingDefeat_v2.ogg"));
/* 246 */     this.map.put("THUNDERCLAP", load("SOTE_SFX_ThunderclapCard_v1.ogg"));
/* 247 */     this.map.put("TINGSHA", load("SOTE_SFX_Relic_Tingsha.ogg"));
/* 248 */     this.map.put("DAMARU", load("damaru.ogg"));
/* 249 */     this.map.put("TURN_EFFECT", load("SOTE_SFX_PlayerTurn_v4_1.ogg"));
/* 250 */     this.map.put("UI_CLICK_1", load("SOTE_SFX_UIClick_1_v2.wav"));
/* 251 */     this.map.put("UI_CLICK_2", load("SOTE_SFX_UIClick_2_v2.wav"));
/* 252 */     this.map.put("UI_HOVER", load("SOTE_SFX_UIHover_v2.wav"));
/* 253 */     this.map.put("UNLOCK_SCREEN", load("STS_UnlockScreen_v1.ogg"));
/* 254 */     this.map.put("UNLOCK_WHIR", load("STS_XPBar_Classic_v1.ogg"));
/* 255 */     this.map.put("UNLOCK_PING", load("STS_NewUnlock_v1.ogg"));
/* 256 */     this.map.put("VICTORY", load("SOTE_SFX_Victory_v1.ogg"));
/* 257 */     this.map.put("WHEEL", load("SOTE_SFX_Wheel_v2.ogg"));
/* 258 */     this.map.put("WIND", load("SOTE_SFX_WindAmb_v1.ogg"));
/*     */ 
/*     */     
/* 261 */     this.map.put("VO_AWAKENEDONE_1", load("vo/STS_VO_AwakenedOne_1_v2.ogg"));
/* 262 */     this.map.put("VO_AWAKENEDONE_2", load("vo/STS_VO_AwakenedOne_2_v2.ogg"));
/* 263 */     this.map.put("VO_AWAKENEDONE_3", load("vo/STS_VO_AwakenedOne_3_v2.ogg"));
/* 264 */     this.map.put("VO_CULTIST_1A", load("vo/STS_VO_CrowCultist_1a.ogg"));
/* 265 */     this.map.put("VO_CULTIST_1B", load("vo/STS_VO_CrowCultist_1b.ogg"));
/* 266 */     this.map.put("VO_CULTIST_1C", load("vo/STS_VO_CrowCultist_1c.ogg"));
/* 267 */     this.map.put("VO_CULTIST_2A", load("vo/STS_VO_CrowCultist_2a.ogg"));
/* 268 */     this.map.put("VO_CULTIST_2B", load("vo/STS_VO_CrowCultist_2b.ogg"));
/* 269 */     this.map.put("VO_CULTIST_2C", load("vo/STS_VO_CrowCultist_2c.ogg"));
/* 270 */     this.map.put("VO_FLAMEBRUISER_1", load("vo/STS_VO_FlameBruiser_1_v3.ogg"));
/* 271 */     this.map.put("VO_FLAMEBRUISER_2", load("vo/STS_VO_FlameBruiser_2_v3.ogg"));
/* 272 */     this.map.put("VO_GIANTHEAD_1A", load("vo/STS_VO_GiantHead_1a.ogg"));
/* 273 */     this.map.put("VO_GIANTHEAD_1B", load("vo/STS_VO_GiantHead_1b.ogg"));
/* 274 */     this.map.put("VO_GIANTHEAD_1C", load("vo/STS_VO_GiantHead_1c.ogg"));
/* 275 */     this.map.put("VO_GIANTHEAD_2A", load("vo/STS_VO_GiantHead_2a.ogg"));
/* 276 */     this.map.put("VO_GIANTHEAD_2B", load("vo/STS_VO_GiantHead_2b.ogg"));
/* 277 */     this.map.put("VO_GIANTHEAD_2C", load("vo/STS_VO_GiantHead_2c.ogg"));
/* 278 */     this.map.put("VO_GREMLINANGRY_1A", load("vo/STS_VO_GremlinAngry_1a.ogg"));
/* 279 */     this.map.put("VO_GREMLINANGRY_1B", load("vo/STS_VO_GremlinAngry_1b.ogg"));
/* 280 */     this.map.put("VO_GREMLINANGRY_1C", load("vo/STS_VO_GremlinAngry_1c.ogg"));
/* 281 */     this.map.put("VO_GREMLINANGRY_2A", load("vo/STS_VO_GremlinAngry_2a.ogg"));
/* 282 */     this.map.put("VO_GREMLINANGRY_2B", load("vo/STS_VO_GremlinAngry_2b.ogg"));
/* 283 */     this.map.put("VO_GREMLINCALM_1A", load("vo/STS_VO_GremlinCalm_1a.ogg"));
/* 284 */     this.map.put("VO_GREMLINCALM_1B", load("vo/STS_VO_GremlinCalm_1b.ogg"));
/* 285 */     this.map.put("VO_GREMLINCALM_2A", load("vo/STS_VO_GremlinCalm_2a.ogg"));
/* 286 */     this.map.put("VO_GREMLINCALM_2B", load("vo/STS_VO_GremlinCalm_2b.ogg"));
/* 287 */     this.map.put("VO_GREMLINDOPEY_1A", load("vo/STS_VO_GremlinDopey_1a.ogg"));
/* 288 */     this.map.put("VO_GREMLINDOPEY_1B", load("vo/STS_VO_GremlinDopey_1b.ogg"));
/* 289 */     this.map.put("VO_GREMLINDOPEY_2A", load("vo/STS_VO_GremlinDopey_2a.ogg"));
/* 290 */     this.map.put("VO_GREMLINDOPEY_2B", load("vo/STS_VO_GremlinDopey_2b.ogg"));
/* 291 */     this.map.put("VO_GREMLINDOPEY_2C", load("vo/STS_VO_GremlinDopey_2c.ogg"));
/* 292 */     this.map.put("VO_GREMLINFAT_1A", load("vo/STS_VO_GremlinFat_1a.ogg"));
/* 293 */     this.map.put("VO_GREMLINFAT_1B", load("vo/STS_VO_GremlinFat_1b.ogg"));
/* 294 */     this.map.put("VO_GREMLINFAT_1C", load("vo/STS_VO_GremlinFat_1c.ogg"));
/* 295 */     this.map.put("VO_GREMLINFAT_2A", load("vo/STS_VO_GremlinFat_2a.ogg"));
/* 296 */     this.map.put("VO_GREMLINFAT_2B", load("vo/STS_VO_GremlinFat_2b.ogg"));
/* 297 */     this.map.put("VO_GREMLINFAT_2C", load("vo/STS_VO_GremlinFat_2c.ogg"));
/* 298 */     this.map.put("VO_GREMLINNOB_1A", load("vo/STS_VO_GremlinNob_1a_v3.ogg"));
/* 299 */     this.map.put("VO_GREMLINNOB_1B", load("vo/STS_VO_GremlinNob_1b_v3.ogg"));
/* 300 */     this.map.put("VO_GREMLINNOB_1C", load("vo/STS_VO_GremlinNob_1d2b_v3.ogg"));
/* 301 */     this.map.put("VO_GREMLINNOB_2A", load("vo/STS_VO_GremlinNob_2a_v3.ogg"));
/* 302 */     this.map.put("VO_GREMLINSPAZZY_1A", load("vo/STS_VO_GremlinSpazzy_1a.ogg"));
/* 303 */     this.map.put("VO_GREMLINSPAZZY_1B", load("vo/STS_VO_GremlinSpazzy_1b.ogg"));
/* 304 */     this.map.put("VO_GREMLINSPAZZY_2A", load("vo/STS_VO_GremlinSpazzy_2a.ogg"));
/* 305 */     this.map.put("VO_GREMLINSPAZZY_2B", load("vo/STS_VO_GremlinSpazzy_2b.ogg"));
/* 306 */     this.map.put("VO_GREMLINSPAZZY_2C", load("vo/STS_VO_GremlinSpazzy_2c.ogg"));
/* 307 */     this.map.put("VO_HEALER_1A", load("vo/STS_VO_Healer_1a.ogg"));
/* 308 */     this.map.put("VO_HEALER_1B", load("vo/STS_VO_Healer_1b.ogg"));
/* 309 */     this.map.put("VO_HEALER_2A", load("vo/STS_VO_Healer_2a.ogg"));
/* 310 */     this.map.put("VO_HEALER_2B", load("vo/STS_VO_Healer_2b.ogg"));
/* 311 */     this.map.put("VO_HEALER_2C", load("vo/STS_VO_Healer_2c.ogg"));
/* 312 */     this.map.put("VO_IRONCLAD_1A", load("vo/STS_VO_Ironclad_1a.ogg"));
/* 313 */     this.map.put("VO_IRONCLAD_1B", load("vo/STS_VO_Ironclad_1b.ogg"));
/* 314 */     this.map.put("VO_IRONCLAD_1C", load("vo/STS_VO_Ironclad_1c.ogg"));
/* 315 */     this.map.put("VO_IRONCLAD_2A", load("vo/STS_VO_Ironclad_2a.ogg"));
/* 316 */     this.map.put("VO_IRONCLAD_2B", load("vo/STS_VO_Ironclad_2b.ogg"));
/* 317 */     this.map.put("VO_IRONCLAD_2C", load("vo/STS_VO_Ironclad_2c.ogg"));
/* 318 */     this.map.put("VO_LOOTER_1A", load("vo/STS_VO_Looter_1a.ogg"));
/* 319 */     this.map.put("VO_LOOTER_1B", load("vo/STS_VO_Looter_1b.ogg"));
/* 320 */     this.map.put("VO_LOOTER_1C", load("vo/STS_VO_Looter_1c.ogg"));
/* 321 */     this.map.put("VO_LOOTER_2A", load("vo/STS_VO_Looter_2a.ogg"));
/* 322 */     this.map.put("VO_LOOTER_2B", load("vo/STS_VO_Looter_2b.ogg"));
/* 323 */     this.map.put("VO_LOOTER_2C", load("vo/STS_VO_Looter_2c.ogg"));
/* 324 */     this.map.put("VO_MERCENARY_1A", load("vo/STS_VO_Mercenary_1a.ogg"));
/* 325 */     this.map.put("VO_MERCENARY_1B", load("vo/STS_VO_Mercenary_1b.ogg"));
/* 326 */     this.map.put("VO_MERCENARY_2A", load("vo/STS_VO_Mercenary_2a.ogg"));
/* 327 */     this.map.put("VO_MERCENARY_3A", load("vo/STS_VO_Mercenary_3a.ogg"));
/* 328 */     this.map.put("VO_MERCENARY_3B", load("vo/STS_VO_Mercenary_3b.ogg"));
/* 329 */     this.map.put("VO_MERCHANT_2A", load("vo/STS_VO_Merchant_2a.ogg"));
/* 330 */     this.map.put("VO_MERCHANT_2B", load("vo/STS_VO_Merchant_2b.ogg"));
/* 331 */     this.map.put("VO_MERCHANT_2C", load("vo/STS_VO_Merchant_2c.ogg"));
/* 332 */     this.map.put("VO_MERCHANT_3A", load("vo/STS_VO_Merchant_3a.ogg"));
/* 333 */     this.map.put("VO_MERCHANT_3B", load("vo/STS_VO_Merchant_3b.ogg"));
/* 334 */     this.map.put("VO_MERCHANT_3C", load("vo/STS_VO_Merchant_3c.ogg"));
/* 335 */     this.map.put("VO_MERCHANT_KA", load("vo/STS_VO_Merchant_Kekeke_a.ogg"));
/* 336 */     this.map.put("VO_MERCHANT_KB", load("vo/STS_VO_Merchant_Kekeke_b.ogg"));
/* 337 */     this.map.put("VO_MERCHANT_KC", load("vo/STS_VO_Merchant_Kekeke_c.ogg"));
/* 338 */     this.map.put("VO_MERCHANT_MA", load("vo/STS_VO_Merchant_Mlyah_a.ogg"));
/* 339 */     this.map.put("VO_MERCHANT_MB", load("vo/STS_VO_Merchant_Mlyah_b.ogg"));
/* 340 */     this.map.put("VO_MERCHANT_MC", load("vo/STS_VO_Merchant_Mlyah_c.ogg"));
/* 341 */     this.map.put("VO_MUGGER_1A", load("vo/STS_VO_Mugger_1a.ogg"));
/* 342 */     this.map.put("VO_MUGGER_1B", load("vo/STS_VO_Mugger_1b.ogg"));
/* 343 */     this.map.put("VO_MUGGER_2A", load("vo/STS_VO_Mugger_2a.ogg"));
/* 344 */     this.map.put("VO_MUGGER_2B", load("vo/STS_VO_Mugger_2b.ogg"));
/* 345 */     this.map.put("VO_NEMESIS_1A", load("vo/STS_VO_Nemesis_1a.ogg"));
/* 346 */     this.map.put("VO_NEMESIS_1B", load("vo/STS_VO_Nemesis_1b.ogg"));
/* 347 */     this.map.put("VO_NEMESIS_1C", load("vo/STS_VO_Nemesis_1c.ogg"));
/* 348 */     this.map.put("VO_NEMESIS_2A", load("vo/STS_VO_Nemesis_2a.ogg"));
/* 349 */     this.map.put("VO_NEMESIS_2B", load("vo/STS_VO_Nemesis_2b.ogg"));
/* 350 */     this.map.put("VO_NEOW_1A", load("vo/STS_VO_Neow_1a.ogg"));
/* 351 */     this.map.put("VO_NEOW_1B", load("vo/STS_VO_Neow_1b.ogg"));
/* 352 */     this.map.put("VO_NEOW_2A", load("vo/STS_VO_Neow_2a.ogg"));
/* 353 */     this.map.put("VO_NEOW_2B", load("vo/STS_VO_Neow_2b.ogg"));
/* 354 */     this.map.put("VO_NEOW_3A", load("vo/STS_VO_Neow_3a.ogg"));
/* 355 */     this.map.put("VO_NEOW_3B", load("vo/STS_VO_Neow_3b.ogg"));
/* 356 */     this.map.put("VO_SILENT_1A", load("vo/STS_VO_Silent_1a.ogg"));
/* 357 */     this.map.put("VO_SILENT_1B", load("vo/STS_VO_Silent_1b.ogg"));
/* 358 */     this.map.put("VO_SILENT_2A", load("vo/STS_VO_Silent_2a.ogg"));
/* 359 */     this.map.put("VO_SILENT_2B", load("vo/STS_VO_Silent_2b.ogg"));
/* 360 */     this.map.put("VO_SLAVERBLUE_1A", load("vo/STS_VO_SlaverBlue_1a.ogg"));
/* 361 */     this.map.put("VO_SLAVERBLUE_1B", load("vo/STS_VO_SlaverBlue_1b.ogg"));
/* 362 */     this.map.put("VO_SLAVERBLUE_2A", load("vo/STS_VO_SlaverBlue_2a.ogg"));
/* 363 */     this.map.put("VO_SLAVERBLUE_2B", load("vo/STS_VO_SlaverBlue_2b.ogg"));
/* 364 */     this.map.put("VO_SLAVERLEADER_1A", load("vo/STS_VO_SlaverLeader_1a.ogg"));
/* 365 */     this.map.put("VO_SLAVERLEADER_1B", load("vo/STS_VO_SlaverLeader_1b.ogg"));
/* 366 */     this.map.put("VO_SLAVERLEADER_2A", load("vo/STS_VO_SlaverLeader_2a.ogg"));
/* 367 */     this.map.put("VO_SLAVERLEADER_2B", load("vo/STS_VO_SlaverLeader_2b.ogg"));
/* 368 */     this.map.put("VO_SLAVERRED_1A", load("vo/STS_VO_SlaverRed_1a.ogg"));
/* 369 */     this.map.put("VO_SLAVERRED_1B", load("vo/STS_VO_SlaverRed_1b.ogg"));
/* 370 */     this.map.put("VO_SLAVERRED_2A", load("vo/STS_VO_SlaverRed_2a.ogg"));
/* 371 */     this.map.put("VO_SLAVERRED_2B", load("vo/STS_VO_SlaverRed_2b.ogg"));
/* 372 */     this.map.put("VO_SLIMEBOSS_1A", load("vo/STS_VO_SlimeBoss_1a.ogg"));
/* 373 */     this.map.put("VO_SLIMEBOSS_1B", load("vo/STS_VO_SlimeBoss_1b.ogg"));
/* 374 */     this.map.put("VO_SLIMEBOSS_1C", load("vo/STS_VO_SlimeBoss_1c.ogg"));
/* 375 */     this.map.put("VO_SLIMEBOSS_2A", load("vo/STS_VO_SlimeBoss_2a.ogg"));
/* 376 */     this.map.put("VO_TANK_1A", load("vo/STS_VO_Centurion_1_v2.ogg"));
/* 377 */     this.map.put("VO_TANK_1B", load("vo/STS_VO_Centurion_2_v2.ogg"));
/* 378 */     this.map.put("VO_TANK_1C", load("vo/STS_VO_Centurion_3_v2.ogg"));
/* 379 */     this.map.put("VO_CHAMP_1A", load("vo/STS_VO_TheChamp_1.ogg"));
/* 380 */     this.map.put("VO_CHAMP_2A", load("vo/STS_VO_TheChamp_2a.ogg"));
/* 381 */     this.map.put("VO_CHAMP_3A", load("vo/STS_VO_TheChamp_3a.ogg"));
/* 382 */     this.map.put("VO_CHAMP_3B", load("vo/STS_VO_TheChamp_3b.ogg"));
/* 383 */     this.map.put("ORB_DARK_CHANNEL", load("orb/STS_SFX_DarkOrb_Channel_v1.ogg"));
/* 384 */     this.map.put("ORB_DARK_EVOKE", load("orb/STS_SFX_DarkOrb_Evoke_v1.ogg"));
/* 385 */     this.map.put("ORB_FROST_CHANNEL", load("orb/STS_SFX_FrostOrb_Channel_v1.ogg"));
/* 386 */     this.map.put("ORB_FROST_DEFEND_1", load("orb/STS_SFX_FrostOrb_GainDefense_1_v1.ogg"));
/* 387 */     this.map.put("ORB_FROST_DEFEND_2", load("orb/STS_SFX_FrostOrb_GainDefense_2_v1.ogg"));
/* 388 */     this.map.put("ORB_FROST_DEFEND_3", load("orb/STS_SFX_FrostOrb_GainDefense_3_v1.ogg"));
/* 389 */     this.map.put("ORB_FROST_EVOKE", load("orb/STS_SFX_FrostOrb_Evoke_v1.ogg"));
/* 390 */     this.map.put("ORB_LIGHTNING_CHANNEL", load("orb/STS_SFX_LightningOrb_Channel_v1.ogg"));
/* 391 */     this.map.put("ORB_LIGHTNING_EVOKE", load("orb/STS_SFX_LightningOrb_Evoke_v1.ogg"));
/* 392 */     this.map.put("ORB_LIGHTNING_PASSIVE", load("orb/STS_SFX_LightningOrb_Passive_v1.ogg"));
/* 393 */     this.map.put("ORB_PLASMA_CHANNEL", load("orb/STS_SFX_PlasmaOrb_Channel_v1.ogg"));
/* 394 */     this.map.put("ORB_PLASMA_EVOKE", load("orb/STS_SFX_PlasmaOrb_Evoke_v1.ogg"));
/* 395 */     this.map.put("ORB_SLOT_GAIN", load("orb/STS_SFX_GainSlot_v1.ogg"));
/*     */ 
/*     */     
/* 398 */     this.map.put("WATCHER_HEART_PUNCH", load("SOTE_SFX_BossGhostFireAtk_3_v1.ogg"));
/* 399 */     this.map.put("STANCE_ENTER_CALM", load("watcher/STS_SFX_Watcher-Calm_v2.ogg"));
/* 400 */     this.map.put("STANCE_ENTER_WRATH", load("watcher/STS_SFX_Watcher-Wrath_v2.ogg"));
/* 401 */     this.map.put("STANCE_ENTER_DIVINITY", load("watcher/STS_SFX_Watcher-Divinity_v3.ogg"));
/* 402 */     this.map.put("STANCE_LOOP_CALM", load("watcher/STS_SFX_Watcher-CalmLoop_v2.ogg"));
/* 403 */     this.map.put("STANCE_LOOP_WRATH", load("watcher/STS_SFX_Watcher-WrathLoop_v2.ogg"));
/* 404 */     this.map.put("STANCE_LOOP_DIVINITY", load("watcher/STS_SFX_Watcher-DivinityLoop_v2.ogg"));
/* 405 */     this.map.put("SELECT_WATCHER", load("watcher/STS_SFX_Watcher-Select_v2.ogg"));
/* 406 */     this.map.put("POWER_MANTRA", load("watcher/STS_SFX_Watcher-Mantra_v3.ogg"));
/*     */     
/* 408 */     this.map.put("CARD_POWER_WOOSH", load("STS_SFX_PowerWoosh_v1.ogg"));
/* 409 */     this.map.put("CARD_POWER_IMPACT", load("STS_SFX_Power_v1.ogg"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 417 */     logger.info("Sound Effect Volume: " + Settings.SOUND_VOLUME);
/* 418 */     logger.info("Loaded " + this.map.size() + " Sound Effects");
/* 419 */     logger.info("SFX load time: " + (System.currentTimeMillis() - startTime) + "ms");
/*     */   }
/*     */   
/*     */   private Sfx load(String filename) {
/* 423 */     return load(filename, false);
/*     */   }
/*     */   
/*     */   private Sfx load(String filename, boolean preload) {
/* 427 */     return new Sfx("audio/sound/" + filename, preload);
/*     */   }
/*     */   
/*     */   public void update() {
/* 431 */     for (Iterator<SoundInfo> i = this.fadeOutList.iterator(); i.hasNext(); ) {
/* 432 */       SoundInfo e = i.next();
/* 433 */       e.update();
/* 434 */       Sfx sfx = this.map.get(e.name);
/*     */       
/* 436 */       if (sfx != null) {
/* 437 */         if (e.isDone) {
/* 438 */           sfx.stop(e.id);
/* 439 */           i.remove(); continue;
/*     */         } 
/* 441 */         sfx.setVolume(e.id, Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * e.volumeMultiplier);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void preload(String key) {
/* 448 */     if (this.map.containsKey(key)) {
/* 449 */       logger.info("Preloading: " + key);
/* 450 */       long id = ((Sfx)this.map.get(key)).play(0.0F);
/* 451 */       ((Sfx)this.map.get(key)).stop(id);
/*     */     } else {
/* 453 */       logger.info("Missing: " + key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long play(String key, boolean useBgmVolume) {
/* 458 */     if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
/* 459 */       return 0L;
/*     */     }
/*     */     
/* 462 */     if (this.map.containsKey(key)) {
/* 463 */       if (useBgmVolume) {
/* 464 */         return ((Sfx)this.map.get(key)).play(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);
/*     */       }
/* 466 */       return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
/*     */     } 
/*     */     
/* 469 */     logger.info("Missing: " + key);
/* 470 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public long play(String key) {
/* 475 */     if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
/* 476 */       return 0L;
/*     */     }
/*     */     
/* 479 */     return play(key, false);
/*     */   }
/*     */   
/*     */   public long play(String key, float pitchVariation) {
/* 483 */     if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
/* 484 */       return 0L;
/*     */     }
/*     */     
/* 487 */     if (this.map.containsKey(key)) {
/* 488 */       return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + 
/*     */           
/* 490 */           MathUtils.random(-pitchVariation, pitchVariation), 0.0F);
/*     */     }
/*     */     
/* 493 */     logger.info("Missing: " + key);
/* 494 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public long playA(String key, float pitchAdjust) {
/* 499 */     if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
/* 500 */       return 0L;
/*     */     }
/*     */     
/* 503 */     if (this.map.containsKey(key)) {
/* 504 */       return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1.0F + pitchAdjust, 0.0F);
/*     */     }
/* 506 */     logger.info("Missing: " + key);
/* 507 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public long playV(String key, float volumeMod) {
/* 512 */     if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
/* 513 */       return 0L;
/*     */     }
/*     */     
/* 516 */     if (this.map.containsKey(key)) {
/* 517 */       return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * volumeMod, 1.0F, 0.0F);
/*     */     }
/* 519 */     logger.info("Missing: " + key);
/* 520 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public long playAV(String key, float pitchAdjust, float volumeMod) {
/* 525 */     if (CardCrawlGame.MUTE_IF_BG && Settings.isBackgrounded) {
/* 526 */       return 0L;
/*     */     }
/*     */     
/* 529 */     if (this.map.containsKey(key)) {
/* 530 */       return ((Sfx)this.map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME * volumeMod, 1.0F + pitchAdjust, 0.0F);
/*     */     }
/* 532 */     logger.info("Missing: " + key);
/* 533 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public long playAndLoop(String key) {
/* 538 */     if (this.map.containsKey(key)) {
/* 539 */       return ((Sfx)this.map.get(key)).loop(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
/*     */     }
/* 541 */     logger.info("Missing: " + key);
/* 542 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public long playAndLoop(String key, float volume) {
/* 547 */     if (this.map.containsKey(key)) {
/* 548 */       return ((Sfx)this.map.get(key)).loop(volume);
/*     */     }
/* 550 */     logger.info("Missing: " + key);
/* 551 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void adjustVolume(String key, long id, float volume) {
/* 556 */     ((Sfx)this.map.get(key)).setVolume(id, volume);
/*     */   }
/*     */   
/*     */   public void adjustVolume(String key, long id) {
/* 560 */     ((Sfx)this.map.get(key)).setVolume(id, Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
/*     */   }
/*     */   
/*     */   public void fadeOut(String key, long id) {
/* 564 */     this.fadeOutList.add(new SoundInfo(key, id));
/*     */   }
/*     */   
/*     */   public void stop(String key, long id) {
/* 568 */     ((Sfx)this.map.get(key)).stop(id);
/*     */   }
/*     */   
/*     */   public void stop(String key) {
/* 572 */     if (this.map.get(key) != null)
/* 573 */       ((Sfx)this.map.get(key)).stop(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\audio\SoundMaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */