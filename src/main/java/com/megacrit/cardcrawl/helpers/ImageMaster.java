/*      */ package com.megacrit.cardcrawl.helpers;
/*      */ 
/*      */ import com.badlogic.gdx.Gdx;
/*      */ import com.badlogic.gdx.graphics.Texture;
/*      */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*      */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ImageMaster
/*      */ {
/*   18 */   private static final Logger logger = LogManager.getLogger(ImageMaster.class.getName());
/*      */   
/*      */   public static TextureAtlas vfxAtlas;
/*      */   
/*      */   public static TextureAtlas.AtlasRegion DECK_GLOW_1;
/*      */   
/*      */   public static TextureAtlas.AtlasRegion DECK_GLOW_2;
/*      */   public static TextureAtlas.AtlasRegion DECK_GLOW_3;
/*      */   public static TextureAtlas.AtlasRegion DECK_GLOW_4;
/*      */   public static TextureAtlas.AtlasRegion DECK_GLOW_5;
/*      */   public static TextureAtlas.AtlasRegion DECK_GLOW_6;
/*      */   public static TextureAtlas.AtlasRegion DUST_1;
/*      */   public static TextureAtlas.AtlasRegion DUST_2;
/*      */   public static TextureAtlas.AtlasRegion DUST_3;
/*      */   public static TextureAtlas.AtlasRegion DUST_4;
/*      */   public static TextureAtlas.AtlasRegion DUST_5;
/*      */   public static TextureAtlas.AtlasRegion DUST_6;
/*      */   public static TextureAtlas.AtlasRegion DEATH_VFX_1;
/*      */   public static TextureAtlas.AtlasRegion DEATH_VFX_2;
/*      */   public static TextureAtlas.AtlasRegion DEATH_VFX_3;
/*      */   public static TextureAtlas.AtlasRegion DEATH_VFX_4;
/*      */   public static TextureAtlas.AtlasRegion DEATH_VFX_5;
/*      */   public static TextureAtlas.AtlasRegion DEATH_VFX_6;
/*      */   public static TextureAtlas.AtlasRegion SCENE_TRANSITION_FADER;
/*      */   public static TextureAtlas.AtlasRegion SMOKE_1;
/*      */   public static TextureAtlas.AtlasRegion SMOKE_2;
/*      */   public static TextureAtlas.AtlasRegion SMOKE_3;
/*      */   public static TextureAtlas.AtlasRegion CONE_1;
/*      */   public static TextureAtlas.AtlasRegion CONE_2;
/*      */   public static TextureAtlas.AtlasRegion CONE_3;
/*      */   public static TextureAtlas.AtlasRegion CONE_4;
/*      */   public static TextureAtlas.AtlasRegion CONE_5;
/*      */   public static TextureAtlas.AtlasRegion EXHAUST_L;
/*      */   public static TextureAtlas.AtlasRegion EXHAUST_S;
/*      */   public static TextureAtlas.AtlasRegion ATK_BLUNT_HEAVY;
/*      */   public static TextureAtlas.AtlasRegion ATK_BLUNT_LIGHT;
/*      */   public static TextureAtlas.AtlasRegion ATK_FIRE;
/*      */   public static TextureAtlas.AtlasRegion ATK_POISON;
/*      */   public static TextureAtlas.AtlasRegion ATK_SHIELD;
/*      */   public static TextureAtlas.AtlasRegion ATK_SLASH_HEAVY;
/*      */   public static TextureAtlas.AtlasRegion ATK_SLASH_H;
/*      */   public static TextureAtlas.AtlasRegion ATK_SLASH_V;
/*      */   public static TextureAtlas.AtlasRegion ATK_SLASH_D;
/*      */   public static TextureAtlas.AtlasRegion ATK_SLASH_RED;
/*      */   public static TextureAtlas.AtlasRegion ROOM_SHINE_1;
/*      */   public static TextureAtlas.AtlasRegion ROOM_SHINE_2;
/*      */   public static TextureAtlas.AtlasRegion COPPER_COIN_1;
/*      */   public static TextureAtlas.AtlasRegion COPPER_COIN_2;
/*      */   public static TextureAtlas.AtlasRegion GRAB_COIN;
/*      */   public static TextureAtlas.AtlasRegion FLAME_1;
/*      */   public static TextureAtlas.AtlasRegion FLAME_2;
/*      */   public static TextureAtlas.AtlasRegion FLAME_3;
/*      */   public static TextureAtlas.AtlasRegion STRIKE_LINE;
/*      */   public static TextureAtlas.AtlasRegion STRIKE_LINE_2;
/*      */   public static TextureAtlas.AtlasRegion STRIKE_BLUR;
/*      */   public static TextureAtlas.AtlasRegion GLOW_SPARK;
/*      */   public static TextureAtlas.AtlasRegion GLOW_SPARK_2;
/*      */   public static TextureAtlas.AtlasRegion THICK_3D_LINE;
/*      */   public static TextureAtlas.AtlasRegion THICK_3D_LINE_2;
/*      */   public static TextureAtlas.AtlasRegion MOVE_NAME_BG;
/*      */   public static TextureAtlas.AtlasRegion VERTICAL_AURA;
/*      */   public static TextureAtlas.AtlasRegion POWER_UP_1;
/*      */   public static TextureAtlas.AtlasRegion POWER_UP_2;
/*      */   public static TextureAtlas.AtlasRegion WOBBLY_LINE;
/*      */   public static TextureAtlas.AtlasRegion BLUR_WAVE;
/*      */   public static TextureAtlas.AtlasRegion DAGGER_STREAK;
/*      */   public static TextureAtlas.AtlasRegion WHITE_RING;
/*      */   public static TextureAtlas.AtlasRegion VERTICAL_IMPACT;
/*      */   public static TextureAtlas.AtlasRegion BORDER_GLOW;
/*      */   public static TextureAtlas.AtlasRegion BORDER_GLOW_2;
/*      */   public static TextureAtlas.AtlasRegion UPGRADE_HAMMER_IMPACT;
/*      */   public static TextureAtlas.AtlasRegion UPGRADE_HAMMER_LINE;
/*      */   public static TextureAtlas.AtlasRegion CRYSTAL_IMPACT;
/*      */   public static TextureAtlas.AtlasRegion TORCH_FIRE_1;
/*      */   public static TextureAtlas.AtlasRegion TORCH_FIRE_2;
/*      */   public static TextureAtlas.AtlasRegion TORCH_FIRE_3;
/*      */   public static TextureAtlas.AtlasRegion TINY_STAR;
/*      */   public static TextureAtlas.AtlasRegion EYE_ANIM_0;
/*      */   public static TextureAtlas.AtlasRegion EYE_ANIM_1;
/*      */   public static TextureAtlas.AtlasRegion EYE_ANIM_2;
/*      */   public static TextureAtlas.AtlasRegion EYE_ANIM_3;
/*      */   public static TextureAtlas.AtlasRegion EYE_ANIM_4;
/*      */   public static TextureAtlas.AtlasRegion EYE_ANIM_5;
/*      */   public static TextureAtlas.AtlasRegion EYE_ANIM_6;
/*      */   public static Texture PROFILE_A;
/*      */   public static Texture PROFILE_B;
/*      */   public static Texture PROFILE_C;
/*      */   public static Texture PROFILE_SLOT;
/*      */   public static Texture PROFILE_DELETE;
/*      */   public static Texture PROFILE_RENAME;
/*      */   public static Texture CHAR_SELECT_IRONCLAD;
/*      */   public static Texture CHAR_SELECT_SILENT;
/*      */   public static Texture CHAR_SELECT_DEFECT;
/*      */   public static Texture CHAR_SELECT_WATCHER;
/*      */   public static Texture CHAR_SELECT_LOCKED;
/*      */   public static Texture CHAR_SELECT_BG_IRONCLAD;
/*      */   public static Texture CHAR_SELECT_BG_SILENT;
/*      */   public static Texture CHAR_SELECT_BG_DEFECT;
/*      */   public static Texture CHAR_SELECT_BG_WATCHER;
/*      */   private static TextureAtlas cardUiAtlas;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_PURPLE;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_PURPLE;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_PURPLE;
/*      */   public static TextureAtlas.AtlasRegion CARD_PURPLE_ORB;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_RED;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_GREEN;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_BLUE;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_GRAY;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_SILHOUETTE;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_RED;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_GREEN;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_BLUE;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_BLACK;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_GRAY;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_SILHOUETTE;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_RED;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_GREEN;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_BLUE;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_GRAY;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_SILHOUETTE;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_COMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_UNCOMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_RARE;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_COMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_UNCOMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_RARE;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_COMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_UNCOMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_RARE;
/*      */   public static TextureAtlas.AtlasRegion CARD_BANNER_COMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_BANNER_UNCOMMON;
/*      */   public static TextureAtlas.AtlasRegion CARD_BANNER_RARE;
/*      */   public static TextureAtlas.AtlasRegion CARD_SUPER_SHADOW;
/*      */   public static TextureAtlas.AtlasRegion CARD_BACK;
/*      */   public static TextureAtlas.AtlasRegion CARD_BG;
/*      */   public static TextureAtlas.AtlasRegion CARD_RED_ORB;
/*      */   public static TextureAtlas.AtlasRegion CARD_GREEN_ORB;
/*      */   public static TextureAtlas.AtlasRegion CARD_BLUE_ORB;
/*      */   public static TextureAtlas.AtlasRegion CARD_COLORLESS_ORB;
/*      */   public static TextureAtlas.AtlasRegion CARD_COMMON_FRAME_LEFT;
/*      */   public static TextureAtlas.AtlasRegion CARD_COMMON_FRAME_MID;
/*      */   public static TextureAtlas.AtlasRegion CARD_COMMON_FRAME_RIGHT;
/*      */   public static TextureAtlas.AtlasRegion CARD_UNCOMMON_FRAME_LEFT;
/*      */   public static TextureAtlas.AtlasRegion CARD_UNCOMMON_FRAME_MID;
/*      */   public static TextureAtlas.AtlasRegion CARD_UNCOMMON_FRAME_RIGHT;
/*      */   public static TextureAtlas.AtlasRegion CARD_RARE_FRAME_LEFT;
/*      */   public static TextureAtlas.AtlasRegion CARD_RARE_FRAME_MID;
/*      */   public static TextureAtlas.AtlasRegion CARD_RARE_FRAME_RIGHT;
/*      */   public static TextureAtlas.AtlasRegion CARD_FLASH_VFX;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_RED_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_GREEN_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_BLUE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_PURPLE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_ATTACK_BG_GRAY_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_RED_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_GREEN_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_BLUE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_PURPLE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_GRAY_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_SKILL_BG_BLACK_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_RED_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_GREEN_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_BLUE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_PURPLE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_POWER_BG_GRAY_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_COMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_UNCOMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_RARE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_COMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_UNCOMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_RARE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_COMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_UNCOMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_RARE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_BANNER_RARE_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_BANNER_UNCOMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_BANNER_COMMON_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_LOCKED_ATTACK_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_LOCKED_SKILL_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_LOCKED_POWER_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_COMMON_FRAME_LEFT_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_COMMON_FRAME_MID_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_COMMON_FRAME_RIGHT_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_UNCOMMON_FRAME_LEFT_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_UNCOMMON_FRAME_MID_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_UNCOMMON_FRAME_RIGHT_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_RARE_FRAME_LEFT_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_RARE_FRAME_MID_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_RARE_FRAME_RIGHT_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_RED_ORB_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_GREEN_ORB_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_BLUE_ORB_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_PURPLE_ORB_L;
/*      */   public static TextureAtlas.AtlasRegion CARD_GRAY_ORB_L;
/*      */   public static Texture CARD_LOCKED_ATTACK;
/*      */   public static Texture CARD_LOCKED_SKILL;
/*      */   public static Texture CARD_LOCKED_POWER;
/*      */   public static Texture CONTROLLER_A;
/*      */   public static Texture CONTROLLER_B;
/*      */   public static Texture CONTROLLER_X;
/*      */   public static Texture CONTROLLER_Y;
/*      */   public static Texture CONTROLLER_BACK;
/*      */   public static Texture CONTROLLER_START;
/*      */   public static Texture CONTROLLER_LB;
/*      */   public static Texture CONTROLLER_RB;
/*      */   public static Texture CONTROLLER_LS;
/*      */   public static Texture CONTROLLER_LS_UP;
/*      */   public static Texture CONTROLLER_LS_DOWN;
/*      */   public static Texture CONTROLLER_LS_LEFT;
/*      */   public static Texture CONTROLLER_LS_RIGHT;
/*      */   public static Texture CONTROLLER_RS;
/*      */   public static Texture CONTROLLER_RS_UP;
/*      */   public static Texture CONTROLLER_RS_DOWN;
/*      */   public static Texture CONTROLLER_RS_LEFT;
/*      */   public static Texture CONTROLLER_RS_RIGHT;
/*      */   public static Texture CONTROLLER_D_UP;
/*      */   public static Texture CONTROLLER_D_DOWN;
/*      */   public static Texture CONTROLLER_D_LEFT;
/*      */   public static Texture CONTROLLER_D_RIGHT;
/*      */   public static Texture CONTROLLER_LT;
/*      */   public static Texture CONTROLLER_RT;
/*      */   public static Texture CONTROLLER_HB_HIGHLIGHT;
/*      */   public static Texture TIMER_ICON;
/*      */   public static Texture TOP_PANEL_BAR;
/*      */   public static Texture DECK_COUNT_CIRCLE;
/*      */   public static Texture HEALTH_BAR_B;
/*      */   public static Texture HEALTH_BAR_L;
/*      */   public static Texture HEALTH_BAR_R;
/*      */   public static Texture BLOCK_BAR_B;
/*      */   public static Texture BLOCK_BAR_R;
/*      */   public static Texture BLOCK_BAR_L;
/*      */   public static Texture BLOCK_ICON;
/*      */   public static Texture BLOCK_ICON_L;
/*      */   public static Texture BLOCK_ICON_R;
/*      */   public static Texture HB_SHADOW_L;
/*      */   public static Texture HB_SHADOW_B;
/*      */   public static Texture HB_SHADOW_R;
/*      */   public static Texture DRAW_PILE_BANNER;
/*      */   public static Texture DISCARD_PILE_BANNER;
/*      */   public static Texture DECK_BTN_BASE;
/*      */   public static Texture DISCARD_BTN_BASE;
/*      */   public static Texture PEEK_BUTTON;
/*      */   public static Texture CAMPFIRE_REST_BUTTON;
/*      */   public static Texture CAMPFIRE_SMITH_BUTTON;
/*      */   public static Texture CAMPFIRE_TOKE_BUTTON;
/*      */   public static Texture CAMPFIRE_TRAIN_BUTTON;
/*      */   public static Texture CAMPFIRE_DIG_BUTTON;
/*      */   public static Texture CAMPFIRE_RECALL_BUTTON;
/*      */   public static Texture CAMPFIRE_HOVER_BUTTON;
/*      */   public static Texture CF_LEFT_ARROW;
/*      */   public static Texture CF_RIGHT_ARROW;
/*      */   public static Texture UPGRADE_ARROW;
/*      */   public static Texture INTENT_ATK_1;
/*      */   public static Texture INTENT_ATK_2;
/*      */   public static Texture INTENT_ATK_3;
/*      */   public static Texture INTENT_ATK_4;
/*      */   public static Texture INTENT_ATK_5;
/*      */   public static Texture INTENT_ATK_6;
/*      */   public static Texture INTENT_ATK_7;
/*      */   public static Texture INTENT_ATK_TIP_1;
/*      */   public static Texture INTENT_ATK_TIP_2;
/*      */   public static Texture INTENT_ATK_TIP_3;
/*      */   public static Texture INTENT_ATK_TIP_4;
/*      */   public static Texture INTENT_ATK_TIP_5;
/*      */   public static Texture INTENT_ATK_TIP_6;
/*      */   public static Texture INTENT_ATK_TIP_7;
/*      */   public static Texture INTENT_BUFF;
/*      */   public static Texture INTENT_BUFF_L;
/*      */   public static Texture INTENT_DEBUFF;
/*      */   public static Texture INTENT_DEBUFF_L;
/*      */   public static Texture INTENT_DEBUFF2;
/*      */   public static Texture INTENT_DEBUFF2_L;
/*      */   public static Texture INTENT_DEFEND;
/*      */   public static Texture INTENT_DEFEND_L;
/*      */   public static Texture INTENT_DEFEND_BUFF;
/*      */   public static Texture INTENT_DEFEND_BUFF_L;
/*      */   public static Texture INTENT_ESCAPE;
/*      */   public static Texture INTENT_ESCAPE_L;
/*      */   public static Texture INTENT_MAGIC;
/*      */   public static Texture INTENT_MAGIC_L;
/*      */   public static Texture INTENT_SLEEP;
/*      */   public static Texture INTENT_SLEEP_L;
/*      */   public static Texture INTENT_STUN;
/*  301 */   public static Texture[] PETAL_VFX = new Texture[4]; public static Texture INTENT_UNKNOWN; public static Texture INTENT_UNKNOWN_L; public static Texture INTENT_ATTACK_BUFF; public static Texture INTENT_ATTACK_DEBUFF; public static Texture INTENT_ATTACK_DEFEND; public static Texture CHECKBOX; public static Texture TICK; public static Texture ORB_DARK; public static Texture ORB_LIGHTNING; public static Texture ORB_PLASMA; public static Texture COLOR_TAB_RED; public static Texture COLOR_TAB_GREEN; public static Texture COLOR_TAB_BLUE; public static Texture COLOR_TAB_PURPLE; public static Texture COLOR_TAB_COLORLESS; public static Texture COLOR_TAB_CURSE; public static Texture COLOR_TAB_BAR; public static Texture COLOR_TAB_LOCK; public static Texture COLOR_TAB_BOX_UNTICKED; public static Texture COLOR_TAB_BOX_TICKED; public static Texture FILTER_IRONCLAD; public static Texture FILTER_SILENT; public static Texture FILTER_DEFECT; public static Texture FILTER_WATCHER; public static Texture FILTER_FRIENDS; public static Texture FILTER_GLOBAL; public static Texture FILTER_SCORE; public static Texture FILTER_CHAIN; public static Texture FILTER_TIME; public static Texture P_STANDARD; public static Texture P_DAILY; public static Texture P_LOOP; public static Texture P_INFO_CARD; public static Texture P_INFO_RELIC; public static Texture P_INFO_POTION; public static Texture P_STAT_CHAR; public static Texture P_STAT_LEADERBOARD; public static Texture P_STAT_HISTORY; public static Texture P_SETTING_GAME; public static Texture P_SETTING_INPUT; public static Texture P_SETTING_CREDITS; public static Texture P_LOCK; public static Texture MENU_PANEL_BG_GRAY; public static Texture MENU_PANEL_BG_RED; public static Texture MENU_PANEL_BG_BEIGE; public static Texture MENU_PANEL_BG_BLUE; public static Texture MENU_PANEL_FRAME; public static Texture FILTER_GLOW_BG; public static Texture CHAR_OPT_HIGHLIGHT; public static Texture RELIC_LINKED; public static Texture RELIC_POPUP; public static Texture POPUP_ARROW; public static Texture RELIC_LOCK; public static Texture RELIC_LOCK_OUTLINE; public static Texture FILTER_ARROW; public static Texture SCROLL_GRADIENT; public static Texture WHITE_GRADIENT; public static Texture MAP_ICON; public static Texture DECK_ICON; public static Texture SETTINGS_ICON; public static Texture ENDLESS_ICON; public static Texture KEY_SLOTS_ICON; public static Texture RUBY_KEY; public static Texture EMERALD_KEY; public static Texture SAPPHIRE_KEY; public static Texture RETICLE_CORNER; public static Texture TARGET_UI_ARROW; public static Texture TARGET_UI_CIRCLE; public static Texture LARGE_CLOUD; public static Texture SMALL_CLOUD; public static Texture SPEECH_BUBBLE_IMG; public static Texture SHOP_SPEECH_BUBBLE_IMG; public static Texture WHITE_SQUARE_IMG; public static Texture MERCHANT_RUG_IMG; public static Texture TP_HP; public static Texture TP_GOLD; public static Texture TP_FLOOR; public static Texture TP_ASCENSION; public static Texture UI_GOLD; public static Texture SETTINGS_BACKGROUND; public static Texture OPTION_ABANDON; public static Texture OPTION_EXIT; public static Texture OPTION_BANNER; public static Texture OPTION_TOGGLE; public static Texture OPTION_TOGGLE_ON; public static Texture OPTION_SLIDER_BG; public static Texture OPTION_SLIDER; public static Texture OPTION_CONFIRM; public static Texture RENAME_BOX; public static Texture OPTION_YES; public static Texture OPTION_NO; public static Texture OPTION_VERTICAL_SLIDER; public static Texture SCROLL_BAR_TOP; public static Texture SCROLL_BAR_MIDDLE; public static Texture SCROLL_BAR_BOTTOM; public static Texture SCROLL_BAR_TRAIN; public static Texture SCROLL_BAR_LEFT; public static Texture SCROLL_BAR_HORIZONTAL_MIDDLE; public static Texture SCROLL_BAR_RIGHT; public static Texture SCROLL_BAR_HORIZONTAL_TRAIN; public static Texture INPUT_SETTINGS_BG; public static Texture INPUT_SETTINGS_EDGES; public static Texture INPUT_SETTINGS_ROW; public static Texture ENERGY_RED_LAYER1; public static Texture ENERGY_RED_LAYER2; public static Texture ENERGY_RED_LAYER3; public static Texture ENERGY_RED_LAYER4; public static Texture ENERGY_RED_LAYER5; public static Texture ENERGY_RED_LAYER6; public static Texture ENERGY_RED_LAYER1D; public static Texture ENERGY_RED_LAYER2D; public static Texture ENERGY_RED_LAYER3D; public static Texture ENERGY_RED_LAYER4D; public static Texture ENERGY_RED_LAYER5D; public static Texture ENERGY_GREEN_LAYER1; public static Texture ENERGY_GREEN_LAYER2; public static Texture ENERGY_GREEN_LAYER3; public static Texture ENERGY_GREEN_LAYER4; public static Texture ENERGY_GREEN_LAYER5; public static Texture ENERGY_GREEN_LAYER6; public static Texture ENERGY_GREEN_LAYER1D; public static Texture ENERGY_GREEN_LAYER2D; public static Texture ENERGY_GREEN_LAYER3D; public static Texture ENERGY_GREEN_LAYER4D; public static Texture ENERGY_GREEN_LAYER5D; public static Texture ENERGY_BLUE_LAYER1; public static Texture ENERGY_BLUE_LAYER2; public static Texture ENERGY_BLUE_LAYER3; public static Texture ENERGY_BLUE_LAYER4; public static Texture ENERGY_BLUE_LAYER5; public static Texture ENERGY_BLUE_LAYER6; public static Texture ENERGY_BLUE_LAYER1D; public static Texture ENERGY_BLUE_LAYER2D; public static Texture ENERGY_BLUE_LAYER3D; public static Texture ENERGY_BLUE_LAYER4D; public static Texture ENERGY_BLUE_LAYER5D; public static Texture ENERGY_PURPLE_LAYER1; public static Texture ENERGY_PURPLE_LAYER2; public static Texture ENERGY_PURPLE_LAYER3; public static Texture ENERGY_PURPLE_LAYER4; public static Texture ENERGY_PURPLE_BORDER; public static Texture RED_ORB_FLASH_VFX; public static Texture GREEN_ORB_FLASH_VFX; public static Texture BLUE_ORB_FLASH_VFX; public static Texture PURPLE_ORB_FLASH_VFX; public static Texture KEYWORD_TOP; public static Texture KEYWORD_BODY; public static Texture KEYWORD_BOT; public static Texture FTUE; public static Texture FTUE_BTN; public static Texture DYNAMIC_BTN_IMG2; public static Texture DYNAMIC_BTN_IMG3; public static Texture VICTORY_BANNER; public static Texture REWARD_SCREEN_SHEET; public static Texture REWARD_SCREEN_TAKE_BUTTON; public static Texture REWARD_SCREEN_TAKE_USED_BUTTON; public static Texture REWARD_SCREEN_ITEM; public static Texture REWARD_CARD_NORMAL; public static Texture REWARD_CARD_BOSS; public static Texture POTION_UI_BG; public static Texture POTION_UI_TOP; public static Texture POTION_UI_BOT; public static Texture POTION_T_CONTAINER; public static Texture POTION_T_LIQUID; public static Texture POTION_T_HYBRID; public static Texture POTION_T_SPOTS; public static Texture POTION_T_OUTLINE; public static Texture POTION_S_CONTAINER; public static Texture POTION_S_LIQUID; public static Texture POTION_S_HYBRID; public static Texture POTION_S_SPOTS; public static Texture POTION_S_OUTLINE; public static Texture POTION_M_CONTAINER; public static Texture POTION_M_LIQUID; public static Texture POTION_M_HYBRID; public static Texture POTION_M_SPOTS; public static Texture POTION_M_OUTLINE; public static Texture POTION_SPHERE_CONTAINER; public static Texture POTION_SPHERE_LIQUID; public static Texture POTION_SPHERE_HYBRID; public static Texture POTION_SPHERE_SPOTS; public static Texture POTION_SPHERE_OUTLINE; public static Texture POTION_H_CONTAINER; public static Texture POTION_H_LIQUID; public static Texture POTION_H_HYBRID; public static Texture POTION_H_SPOTS; public static Texture POTION_H_OUTLINE; public static Texture POTION_BOTTLE_CONTAINER; public static Texture POTION_BOTTLE_LIQUID; public static Texture POTION_BOTTLE_HYBRID; public static Texture POTION_BOTTLE_SPOTS; public static Texture POTION_BOTTLE_OUTLINE; public static Texture POTION_HEART_CONTAINER; public static Texture POTION_HEART_LIQUID; public static Texture POTION_HEART_HYBRID; public static Texture POTION_HEART_SPOTS; public static Texture POTION_HEART_OUTLINE; public static Texture POTION_SNECKO_CONTAINER; public static Texture POTION_SNECKO_LIQUID; public static Texture POTION_SNECKO_HYBRID; public static Texture POTION_SNECKO_SPOTS; public static Texture POTION_SNECKO_OUTLINE; public static Texture POTION_FAIRY_CONTAINER; public static Texture POTION_FAIRY_LIQUID; public static Texture POTION_FAIRY_HYBRID; public static Texture POTION_FAIRY_SPOTS; public static Texture POTION_FAIRY_OUTLINE; public static Texture POTION_GHOST_CONTAINER; public static Texture POTION_GHOST_LIQUID; public static Texture POTION_GHOST_HYBRID; public static Texture POTION_GHOST_SPOTS; public static Texture POTION_GHOST_OUTLINE; public static Texture POTION_JAR_CONTAINER; public static Texture POTION_JAR_LIQUID; public static Texture POTION_JAR_HYBRID; public static Texture POTION_JAR_SPOTS; public static Texture POTION_JAR_OUTLINE; public static Texture POTION_BOLT_CONTAINER; public static Texture POTION_BOLT_LIQUID; public static Texture POTION_BOLT_HYBRID; public static Texture POTION_BOLT_SPOTS; public static Texture POTION_BOLT_OUTLINE; public static Texture POTION_CARD_CONTAINER; public static Texture POTION_CARD_LIQUID; public static Texture POTION_CARD_HYBRID; public static Texture POTION_CARD_SPOTS; public static Texture POTION_CARD_OUTLINE; public static Texture POTION_MOON_CONTAINER; public static Texture POTION_MOON_LIQUID; public static Texture POTION_MOON_HYBRID; public static Texture POTION_MOON_OUTLINE; public static Texture POTION_SPIKY_CONTAINER; public static Texture POTION_SPIKY_LIQUID; public static Texture POTION_SPIKY_HYBRID; public static Texture POTION_SPIKY_OUTLINE; public static Texture POTION_EYE_CONTAINER; public static Texture POTION_EYE_LIQUID; public static Texture POTION_EYE_HYBRID; public static Texture POTION_EYE_OUTLINE; public static Texture POTION_ANVIL_CONTAINER; public static Texture POTION_ANVIL_LIQUID; public static Texture POTION_ANVIL_HYBRID; public static Texture POTION_ANVIL_OUTLINE; public static Texture POTION_PLACEHOLDER; private static final String BUTTON_DIR = "images/ui/topPanel/"; public static Texture EVENT_BUTTON_ENABLED; public static Texture EVENT_BUTTON_DISABLED; public static Texture UNLOCK_TEXT_BG; public static Texture EVENT_IMG_FRAME; public static Texture EVENT_ROOM_PANEL; public static Texture CANCEL_BUTTON; public static Texture CANCEL_BUTTON_SHADOW; public static Texture CANCEL_BUTTON_OUTLINE; public static Texture CONFIRM_BUTTON; public static Texture CONFIRM_BUTTON_SHADOW; public static Texture CONFIRM_BUTTON_OUTLINE; public static Texture PROCEED_BUTTON; public static Texture PROCEED_BUTTON_SHADOW; public static Texture PROCEED_BUTTON_OUTLINE; public static Texture END_TURN_BUTTON; public static Texture END_TURN_BUTTON_GLOW; public static Texture END_TURN_HOVER; public static Texture GHOST_ORB_1; public static Texture GHOST_ORB_2; public static Texture WARNING_ICON_VFX; public static Texture SPOTLIGHT_VFX; public static Texture DEBUG_HITBOX_IMG; public static Texture WOBBLY_ORB_VFX; public static Texture ANIMATED_SLASH_VFX; public static Texture DEBUFF_VFX_1; public static Texture DEBUFF_VFX_2; public static Texture DEBUFF_VFX_3; public static Texture FLAME_ANIM_1; public static Texture FLAME_ANIM_2; public static Texture FLAME_ANIM_3; public static Texture FLAME_ANIM_4; public static Texture FLAME_ANIM_5; public static Texture FLAME_ANIM_6; public static Texture HORIZONTAL_LINE; public static Texture WEB_VFX;
/*  302 */   public static Texture[] WATER_DROP_VFX = new Texture[6]; public static Texture FROST_ORB_RIGHT; public static Texture FROST_ORB_LEFT; public static Texture FROST_ORB_MIDDLE;
/*      */   public static Texture FROST_ACTIVATE_VFX_1;
/*      */   public static Texture FROST_ACTIVATE_VFX_2;
/*      */   public static Texture FROST_ACTIVATE_VFX_3;
/*      */   public static Texture ORB_SLOT_1;
/*      */   public static Texture ORB_SLOT_2;
/*  308 */   public static ArrayList<Texture> LIGHTNING_PASSIVE_VFX = new ArrayList<>(); public static Texture DARK_ORB_ACTIVATE_VFX; public static Texture DARK_ORB_PASSIVE_VFX_1; public static Texture DARK_ORB_PASSIVE_VFX_2; public static Texture DARK_ORB_PASSIVE_VFX_3;
/*      */   public static Texture MAP_NODE_ELITE;
/*      */   public static Texture MAP_NODE_ELITE_OUTLINE;
/*      */   public static Texture MAP_NODE_ENEMY;
/*      */   public static Texture MAP_NODE_ENEMY_OUTLINE;
/*      */   public static Texture MAP_NODE_REST;
/*      */   public static Texture MAP_NODE_REST_OUTLINE;
/*      */   public static Texture MAP_NODE_TREASURE;
/*      */   public static Texture MAP_NODE_TREASURE_OUTLINE;
/*      */   public static Texture MAP_NODE_MERCHANT;
/*      */   public static Texture MAP_NODE_MERCHANT_OUTLINE;
/*      */   public static Texture MAP_LEGEND;
/*      */   public static Texture MAP_CIRCLE_1;
/*      */   public static Texture MAP_CIRCLE_2;
/*      */   public static Texture MAP_CIRCLE_3;
/*      */   public static Texture MAP_CIRCLE_4;
/*      */   public static Texture MAP_CIRCLE_5;
/*      */   public static Texture MAP_NODE_EVENT;
/*      */   public static Texture MAP_NODE_EVENT_OUTLINE;
/*      */   public static Texture MAP_DOT_1;
/*      */   public static Texture TINY_CARD_ATTACK;
/*      */   public static Texture TINY_CARD_SKILL;
/*      */   public static Texture TINY_CARD_POWER;
/*      */   public static Texture TINY_CARD_PORTRAIT_SHADOW;
/*      */   public static Texture TINY_CARD_BACKGROUND;
/*      */   public static Texture TINY_CARD_BANNER;
/*      */   public static Texture TINY_CARD_BANNER_SHADOW;
/*      */   public static Texture TINY_CARD_DESCRIPTION;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_BOSS;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_CHEST;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_BOSS_CHEST;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_ELITE;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_EVENT;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_MONSTER;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_REST;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_SHOP;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_UNKNOWN_CHEST;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_UNKNOWN_MONSTER;
/*      */   public static Texture RUN_HISTORY_MAP_ICON_UNKNOWN_SHOP;
/*  347 */   public static HashMap<String, Texture> relicImages = new HashMap<>();
/*  348 */   public static HashMap<String, Texture> relicOutlineImages = new HashMap<>(); public static Texture BOSS_CHEST; public static Texture BOSS_CHEST_OPEN; public static Texture BOSS_CHEST_SMOKE;
/*      */   public static Texture L_CHEST;
/*      */   public static Texture L_CHEST_OPEN;
/*      */   public static Texture M_CHEST;
/*      */   public static Texture M_CHEST_OPEN;
/*      */   public static Texture S_CHEST;
/*      */   public static Texture S_CHEST_OPEN;
/*      */   
/*      */   public static void initialize() {
/*  357 */     long startTime = System.currentTimeMillis();
/*      */ 
/*      */     
/*  360 */     vfxAtlas = new TextureAtlas(Gdx.files.internal("vfx/vfx.atlas"));
/*  361 */     DECK_GLOW_1 = vfxAtlas.findRegion("ui/p1");
/*  362 */     DECK_GLOW_2 = vfxAtlas.findRegion("ui/p2");
/*  363 */     DECK_GLOW_3 = vfxAtlas.findRegion("ui/p3");
/*  364 */     DECK_GLOW_4 = vfxAtlas.findRegion("ui/p4");
/*  365 */     DECK_GLOW_5 = vfxAtlas.findRegion("ui/p5");
/*  366 */     DECK_GLOW_6 = vfxAtlas.findRegion("ui/p6");
/*  367 */     SCENE_TRANSITION_FADER = vfxAtlas.findRegion("ui/topDownFader");
/*  368 */     DEATH_VFX_1 = vfxAtlas.findRegion("env/death1");
/*  369 */     DEATH_VFX_2 = vfxAtlas.findRegion("env/death2");
/*  370 */     DEATH_VFX_3 = vfxAtlas.findRegion("env/death3");
/*  371 */     DEATH_VFX_4 = vfxAtlas.findRegion("env/death4");
/*  372 */     DEATH_VFX_5 = vfxAtlas.findRegion("env/death5");
/*  373 */     DEATH_VFX_6 = vfxAtlas.findRegion("env/death6");
/*  374 */     DUST_1 = vfxAtlas.findRegion("env/dust1");
/*  375 */     DUST_2 = vfxAtlas.findRegion("env/dust2");
/*  376 */     DUST_3 = vfxAtlas.findRegion("env/dust3");
/*  377 */     DUST_4 = vfxAtlas.findRegion("env/dust4");
/*  378 */     DUST_5 = vfxAtlas.findRegion("env/dust5");
/*  379 */     DUST_6 = vfxAtlas.findRegion("env/dust6");
/*  380 */     SMOKE_1 = vfxAtlas.findRegion("env/smoke1");
/*  381 */     SMOKE_2 = vfxAtlas.findRegion("env/smoke2");
/*  382 */     SMOKE_3 = vfxAtlas.findRegion("env/smoke3");
/*  383 */     CONE_1 = vfxAtlas.findRegion("cone7");
/*  384 */     CONE_2 = vfxAtlas.findRegion("cone8");
/*  385 */     CONE_3 = vfxAtlas.findRegion("cone9");
/*  386 */     CONE_4 = vfxAtlas.findRegion("cone5");
/*  387 */     CONE_5 = vfxAtlas.findRegion("cone6");
/*  388 */     EXHAUST_L = vfxAtlas.findRegion("exhaust/bigBlur");
/*  389 */     EXHAUST_S = vfxAtlas.findRegion("exhaust/smallBlur");
/*  390 */     ATK_BLUNT_HEAVY = vfxAtlas.findRegion("attack/blunt_heavy");
/*  391 */     ATK_BLUNT_LIGHT = vfxAtlas.findRegion("attack/blunt_light");
/*  392 */     ATK_FIRE = vfxAtlas.findRegion("attack/fire");
/*  393 */     ATK_POISON = vfxAtlas.findRegion("attack/poison");
/*  394 */     ATK_SHIELD = vfxAtlas.findRegion("attack/shield");
/*  395 */     ATK_SLASH_HEAVY = vfxAtlas.findRegion("attack/slash_heavy");
/*  396 */     ATK_SLASH_H = vfxAtlas.findRegion("attack/slash_horizontal");
/*  397 */     ATK_SLASH_V = vfxAtlas.findRegion("attack/slash_vertical");
/*  398 */     ATK_SLASH_D = vfxAtlas.findRegion("attack/slash_light");
/*  399 */     ATK_SLASH_RED = vfxAtlas.findRegion("attack/slash1");
/*  400 */     ROOM_SHINE_1 = vfxAtlas.findRegion("shine1");
/*  401 */     ROOM_SHINE_2 = vfxAtlas.findRegion("shine2");
/*  402 */     COPPER_COIN_1 = vfxAtlas.findRegion("copperCoin");
/*  403 */     COPPER_COIN_2 = vfxAtlas.findRegion("copperCoin2");
/*  404 */     GRAB_COIN = vfxAtlas.findRegion("shineLines");
/*  405 */     FLAME_1 = vfxAtlas.findRegion("combat/flame4");
/*  406 */     FLAME_2 = vfxAtlas.findRegion("combat/flame5");
/*  407 */     FLAME_3 = vfxAtlas.findRegion("combat/flame6");
/*  408 */     STRIKE_LINE = vfxAtlas.findRegion("combat/strikeLine2");
/*  409 */     STRIKE_LINE_2 = vfxAtlas.findRegion("combat/strikeLine3");
/*  410 */     STRIKE_BLUR = vfxAtlas.findRegion("combat/blurDot");
/*  411 */     GLOW_SPARK = vfxAtlas.findRegion("glowSpark");
/*  412 */     GLOW_SPARK_2 = vfxAtlas.findRegion("glowSpark2");
/*  413 */     THICK_3D_LINE = vfxAtlas.findRegion("combat/spike");
/*  414 */     THICK_3D_LINE_2 = vfxAtlas.findRegion("combat/spike2");
/*  415 */     MOVE_NAME_BG = vfxAtlas.findRegion("combat/moveNameBg");
/*  416 */     VERTICAL_AURA = vfxAtlas.findRegion("combat/verticalAura");
/*  417 */     POWER_UP_1 = vfxAtlas.findRegion("combat/empowerCircle1");
/*  418 */     POWER_UP_2 = vfxAtlas.findRegion("combat/empowerCircle2");
/*  419 */     WOBBLY_LINE = vfxAtlas.findRegion("combat/wobblyLine");
/*  420 */     BLUR_WAVE = vfxAtlas.findRegion("combat/blurWave");
/*  421 */     DAGGER_STREAK = vfxAtlas.findRegion("combat/streak");
/*  422 */     WHITE_RING = vfxAtlas.findRegion("whiteRing");
/*  423 */     VERTICAL_IMPACT = vfxAtlas.findRegion("combat/verticalImpact");
/*  424 */     BORDER_GLOW = vfxAtlas.findRegion("borderGlow");
/*  425 */     BORDER_GLOW_2 = vfxAtlas.findRegion("borderGlow2");
/*  426 */     UPGRADE_HAMMER_IMPACT = vfxAtlas.findRegion("ui/hammerImprint");
/*  427 */     UPGRADE_HAMMER_LINE = vfxAtlas.findRegion("ui/impactLineThick");
/*  428 */     CRYSTAL_IMPACT = vfxAtlas.findRegion("blurRing");
/*  429 */     TORCH_FIRE_1 = vfxAtlas.findRegion("env/fire1");
/*  430 */     TORCH_FIRE_2 = vfxAtlas.findRegion("env/fire2");
/*  431 */     TORCH_FIRE_3 = vfxAtlas.findRegion("env/fire3");
/*  432 */     TINY_STAR = vfxAtlas.findRegion("combat/tinyStar2");
/*      */ 
/*      */     
/*  435 */     EYE_ANIM_0 = vfxAtlas.findRegion("combat/stance/eye0");
/*  436 */     EYE_ANIM_1 = vfxAtlas.findRegion("combat/stance/eye1");
/*  437 */     EYE_ANIM_2 = vfxAtlas.findRegion("combat/stance/eye2");
/*  438 */     EYE_ANIM_3 = vfxAtlas.findRegion("combat/stance/eye3");
/*  439 */     EYE_ANIM_4 = vfxAtlas.findRegion("combat/stance/eye4");
/*  440 */     EYE_ANIM_5 = vfxAtlas.findRegion("combat/stance/eye5");
/*  441 */     EYE_ANIM_6 = vfxAtlas.findRegion("combat/stance/eye6");
/*      */ 
/*      */     
/*  444 */     CHAR_SELECT_IRONCLAD = loadImage("images/ui/charSelect/ironcladButton.png");
/*  445 */     CHAR_SELECT_SILENT = loadImage("images/ui/charSelect/silentButton.png");
/*  446 */     CHAR_SELECT_DEFECT = loadImage("images/ui/charSelect/defectButton.png");
/*  447 */     CHAR_SELECT_WATCHER = loadImage("images/ui/charSelect/watcherButton.png");
/*  448 */     CHAR_SELECT_LOCKED = loadImage("images/ui/charSelect/lockedButton.png");
/*  449 */     CHAR_SELECT_BG_IRONCLAD = loadImage("images/ui/charSelect/ironcladPortrait.jpg");
/*  450 */     CHAR_SELECT_BG_SILENT = loadImage("images/ui/charSelect/silentPortrait.jpg");
/*  451 */     CHAR_SELECT_BG_DEFECT = loadImage("images/ui/charSelect/defectPortrait.jpg");
/*  452 */     CHAR_SELECT_BG_WATCHER = loadImage("images/ui/charSelect/watcherPortrait.jpg");
/*      */ 
/*      */     
/*  455 */     PROFILE_A = loadImage("images/ui/profile/1.png");
/*  456 */     PROFILE_B = loadImage("images/ui/profile/2.png");
/*  457 */     PROFILE_C = loadImage("images/ui/profile/3.png");
/*  458 */     PROFILE_SLOT = loadImage("images/ui/profile/save_panel.png");
/*  459 */     PROFILE_DELETE = loadImage("images/ui/profile/delete_button.png");
/*  460 */     PROFILE_RENAME = loadImage("images/ui/profile/rename_button.png");
/*      */ 
/*      */     
/*  463 */     cardUiAtlas = new TextureAtlas(Gdx.files.internal("cardui/cardui.atlas"));
/*  464 */     CARD_BACK = cardUiAtlas.findRegion("512/card_back");
/*  465 */     CARD_BG = cardUiAtlas.findRegion("512/card_bg");
/*  466 */     CARD_ATTACK_BG_PURPLE = cardUiAtlas.findRegion("512/bg_attack_purple");
/*  467 */     CARD_SKILL_BG_PURPLE = cardUiAtlas.findRegion("512/bg_skill_purple");
/*  468 */     CARD_POWER_BG_PURPLE = cardUiAtlas.findRegion("512/bg_power_purple");
/*  469 */     CARD_PURPLE_ORB = cardUiAtlas.findRegion("512/card_purple_orb");
/*  470 */     CARD_ATTACK_BG_RED = cardUiAtlas.findRegion("512/bg_attack_red");
/*  471 */     CARD_ATTACK_BG_GREEN = cardUiAtlas.findRegion("512/bg_attack_green");
/*  472 */     CARD_ATTACK_BG_BLUE = cardUiAtlas.findRegion("512/bg_attack_blue");
/*  473 */     CARD_ATTACK_BG_GRAY = cardUiAtlas.findRegion("512/bg_attack_gray");
/*  474 */     CARD_ATTACK_BG_SILHOUETTE = cardUiAtlas.findRegion("512/bg_attack_silhouette");
/*  475 */     CARD_SKILL_BG_RED = cardUiAtlas.findRegion("512/bg_skill_red");
/*  476 */     CARD_SKILL_BG_GREEN = cardUiAtlas.findRegion("512/bg_skill_green");
/*  477 */     CARD_SKILL_BG_BLUE = cardUiAtlas.findRegion("512/bg_skill_blue");
/*  478 */     CARD_SKILL_BG_BLACK = cardUiAtlas.findRegion("512/bg_skill_black");
/*  479 */     CARD_SKILL_BG_GRAY = cardUiAtlas.findRegion("512/bg_skill_gray");
/*  480 */     CARD_SKILL_BG_SILHOUETTE = cardUiAtlas.findRegion("512/bg_skill_silhouette");
/*  481 */     CARD_POWER_BG_RED = cardUiAtlas.findRegion("512/bg_power_red");
/*  482 */     CARD_POWER_BG_GREEN = cardUiAtlas.findRegion("512/bg_power_green");
/*  483 */     CARD_POWER_BG_BLUE = cardUiAtlas.findRegion("512/bg_power_blue");
/*  484 */     CARD_POWER_BG_GRAY = cardUiAtlas.findRegion("512/bg_power_gray");
/*  485 */     CARD_POWER_BG_SILHOUETTE = cardUiAtlas.findRegion("512/bg_power_silhouette");
/*  486 */     CARD_FRAME_ATTACK_COMMON = cardUiAtlas.findRegion("512/frame_attack_common");
/*  487 */     CARD_FRAME_ATTACK_UNCOMMON = cardUiAtlas.findRegion("512/frame_attack_uncommon");
/*  488 */     CARD_FRAME_ATTACK_RARE = cardUiAtlas.findRegion("512/frame_attack_rare");
/*  489 */     CARD_FRAME_SKILL_COMMON = cardUiAtlas.findRegion("512/frame_skill_common");
/*  490 */     CARD_FRAME_SKILL_UNCOMMON = cardUiAtlas.findRegion("512/frame_skill_uncommon");
/*  491 */     CARD_FRAME_SKILL_RARE = cardUiAtlas.findRegion("512/frame_skill_rare");
/*  492 */     CARD_FRAME_POWER_COMMON = cardUiAtlas.findRegion("512/frame_power_common");
/*  493 */     CARD_FRAME_POWER_UNCOMMON = cardUiAtlas.findRegion("512/frame_power_uncommon");
/*  494 */     CARD_FRAME_POWER_RARE = cardUiAtlas.findRegion("512/frame_power_rare");
/*  495 */     CARD_BANNER_COMMON = cardUiAtlas.findRegion("512/banner_common");
/*  496 */     CARD_BANNER_UNCOMMON = cardUiAtlas.findRegion("512/banner_uncommon");
/*  497 */     CARD_BANNER_RARE = cardUiAtlas.findRegion("512/banner_rare");
/*  498 */     CARD_RED_ORB = cardUiAtlas.findRegion("512/card_red_orb");
/*  499 */     CARD_GREEN_ORB = cardUiAtlas.findRegion("512/card_green_orb");
/*  500 */     CARD_BLUE_ORB = cardUiAtlas.findRegion("512/card_blue_orb");
/*  501 */     CARD_COLORLESS_ORB = cardUiAtlas.findRegion("512/card_colorless_orb");
/*  502 */     CARD_SUPER_SHADOW = cardUiAtlas.findRegion("512/card_super_shadow");
/*  503 */     CARD_COMMON_FRAME_LEFT = cardUiAtlas.findRegion("512/common_left");
/*  504 */     CARD_COMMON_FRAME_MID = cardUiAtlas.findRegion("512/common_center");
/*  505 */     CARD_COMMON_FRAME_RIGHT = cardUiAtlas.findRegion("512/common_right");
/*  506 */     CARD_UNCOMMON_FRAME_LEFT = cardUiAtlas.findRegion("512/uncommon_left");
/*  507 */     CARD_UNCOMMON_FRAME_MID = cardUiAtlas.findRegion("512/uncommon_center");
/*  508 */     CARD_UNCOMMON_FRAME_RIGHT = cardUiAtlas.findRegion("512/uncommon_right");
/*  509 */     CARD_RARE_FRAME_LEFT = cardUiAtlas.findRegion("512/rare_left");
/*  510 */     CARD_RARE_FRAME_MID = cardUiAtlas.findRegion("512/rare_center");
/*  511 */     CARD_RARE_FRAME_RIGHT = cardUiAtlas.findRegion("512/rare_right");
/*  512 */     CARD_FLASH_VFX = cardUiAtlas.findRegion("512/card_flash_vfx");
/*      */     
/*  514 */     CARD_LOCKED_ATTACK = loadImage("images/cards/locked_attack.png");
/*  515 */     CARD_LOCKED_SKILL = loadImage("images/cards/locked_skill.png");
/*  516 */     CARD_LOCKED_POWER = loadImage("images/cards/locked_power.png");
/*      */ 
/*      */     
/*  519 */     CARD_ATTACK_BG_PURPLE_L = cardUiAtlas.findRegion("1024/bg_attack_purple");
/*  520 */     CARD_SKILL_BG_PURPLE_L = cardUiAtlas.findRegion("1024/bg_skill_purple");
/*  521 */     CARD_POWER_BG_PURPLE_L = cardUiAtlas.findRegion("1024/bg_power_purple");
/*  522 */     CARD_PURPLE_ORB_L = cardUiAtlas.findRegion("1024/card_purple_orb");
/*  523 */     CARD_ATTACK_BG_RED_L = cardUiAtlas.findRegion("1024/bg_attack_red");
/*  524 */     CARD_ATTACK_BG_GREEN_L = cardUiAtlas.findRegion("1024/bg_attack_green");
/*  525 */     CARD_ATTACK_BG_BLUE_L = cardUiAtlas.findRegion("1024/bg_attack_blue");
/*  526 */     CARD_ATTACK_BG_GRAY_L = cardUiAtlas.findRegion("1024/bg_attack_colorless");
/*  527 */     CARD_SKILL_BG_RED_L = cardUiAtlas.findRegion("1024/bg_skill_red");
/*  528 */     CARD_SKILL_BG_GREEN_L = cardUiAtlas.findRegion("1024/bg_skill_green");
/*  529 */     CARD_SKILL_BG_BLUE_L = cardUiAtlas.findRegion("1024/bg_skill_blue");
/*  530 */     CARD_SKILL_BG_GRAY_L = cardUiAtlas.findRegion("1024/bg_skill_colorless");
/*  531 */     CARD_SKILL_BG_BLACK_L = cardUiAtlas.findRegion("1024/bg_skill_black");
/*  532 */     CARD_POWER_BG_RED_L = cardUiAtlas.findRegion("1024/bg_power_red");
/*  533 */     CARD_POWER_BG_GREEN_L = cardUiAtlas.findRegion("1024/bg_power_green");
/*  534 */     CARD_POWER_BG_BLUE_L = cardUiAtlas.findRegion("1024/bg_power_blue");
/*  535 */     CARD_POWER_BG_GRAY_L = cardUiAtlas.findRegion("1024/bg_power_colorless");
/*  536 */     CARD_FRAME_ATTACK_COMMON_L = cardUiAtlas.findRegion("1024/frame_attack_common");
/*  537 */     CARD_FRAME_ATTACK_UNCOMMON_L = cardUiAtlas.findRegion("1024/frame_attack_uncommon");
/*  538 */     CARD_FRAME_ATTACK_RARE_L = cardUiAtlas.findRegion("1024/frame_attack_rare");
/*  539 */     CARD_FRAME_SKILL_COMMON_L = cardUiAtlas.findRegion("1024/frame_skill_common");
/*  540 */     CARD_FRAME_SKILL_UNCOMMON_L = cardUiAtlas.findRegion("1024/frame_skill_uncommon");
/*  541 */     CARD_FRAME_SKILL_RARE_L = cardUiAtlas.findRegion("1024/frame_skill_rare");
/*  542 */     CARD_FRAME_POWER_COMMON_L = cardUiAtlas.findRegion("1024/frame_power_common");
/*  543 */     CARD_FRAME_POWER_UNCOMMON_L = cardUiAtlas.findRegion("1024/frame_power_uncommon");
/*  544 */     CARD_FRAME_POWER_RARE_L = cardUiAtlas.findRegion("1024/frame_power_rare");
/*  545 */     CARD_BANNER_COMMON_L = cardUiAtlas.findRegion("1024/banner_common");
/*  546 */     CARD_BANNER_UNCOMMON_L = cardUiAtlas.findRegion("1024/banner_uncommon");
/*  547 */     CARD_BANNER_RARE_L = cardUiAtlas.findRegion("1024/banner_rare");
/*  548 */     CARD_RED_ORB_L = cardUiAtlas.findRegion("1024/card_red_orb");
/*  549 */     CARD_GREEN_ORB_L = cardUiAtlas.findRegion("1024/card_green_orb");
/*  550 */     CARD_BLUE_ORB_L = cardUiAtlas.findRegion("1024/card_blue_orb");
/*  551 */     CARD_GRAY_ORB_L = cardUiAtlas.findRegion("1024/card_colorless_orb");
/*  552 */     CARD_LOCKED_ATTACK_L = cardUiAtlas.findRegion("ocked_attack_l");
/*  553 */     CARD_LOCKED_SKILL_L = cardUiAtlas.findRegion("ocked_skill_l");
/*  554 */     CARD_LOCKED_POWER_L = cardUiAtlas.findRegion("ocked_power_l");
/*  555 */     CARD_COMMON_FRAME_LEFT_L = cardUiAtlas.findRegion("1024/common_left");
/*  556 */     CARD_COMMON_FRAME_MID_L = cardUiAtlas.findRegion("1024/common_center");
/*  557 */     CARD_COMMON_FRAME_RIGHT_L = cardUiAtlas.findRegion("1024/common_right");
/*  558 */     CARD_UNCOMMON_FRAME_LEFT_L = cardUiAtlas.findRegion("1024/uncommon_left");
/*  559 */     CARD_UNCOMMON_FRAME_MID_L = cardUiAtlas.findRegion("1024/uncommon_center");
/*  560 */     CARD_UNCOMMON_FRAME_RIGHT_L = cardUiAtlas.findRegion("1024/uncommon_right");
/*  561 */     CARD_RARE_FRAME_LEFT_L = cardUiAtlas.findRegion("1024/rare_left");
/*  562 */     CARD_RARE_FRAME_MID_L = cardUiAtlas.findRegion("1024/rare_center");
/*  563 */     CARD_RARE_FRAME_RIGHT_L = cardUiAtlas.findRegion("1024/rare_right");
/*      */     
/*  565 */     MERCHANT_RUG_IMG = loadImage("images/npcs/merchantObjects.png");
/*      */ 
/*      */     
/*  568 */     POTION_UI_BG = loadImage("images/ui/potionPopUp/bg.png");
/*  569 */     POTION_UI_TOP = loadImage("images/ui/potionPopUp/top.png");
/*  570 */     POTION_UI_BOT = loadImage("images/ui/potionPopUp/bot.png");
/*      */ 
/*      */     
/*  573 */     POTION_T_CONTAINER = loadImage("images/potion/potion_t_glass.png");
/*  574 */     POTION_T_LIQUID = loadImage("images/potion/potion_t_liquid.png");
/*  575 */     POTION_T_HYBRID = loadImage("images/potion/potion_t_hybrid.png");
/*  576 */     POTION_T_SPOTS = loadImage("images/potion/potion_t_spots.png");
/*  577 */     POTION_T_OUTLINE = loadImage("images/potion/potion_t_outline.png");
/*  578 */     POTION_S_CONTAINER = loadImage("images/potion/potion_s_glass.png");
/*  579 */     POTION_S_LIQUID = loadImage("images/potion/potion_s_liquid.png");
/*  580 */     POTION_S_HYBRID = loadImage("images/potion/potion_s_hybrid.png");
/*  581 */     POTION_S_SPOTS = loadImage("images/potion/potion_s_spots.png");
/*  582 */     POTION_S_OUTLINE = loadImage("images/potion/potion_s_outline.png");
/*  583 */     POTION_M_CONTAINER = loadImage("images/potion/potion_m_glass.png");
/*  584 */     POTION_M_LIQUID = loadImage("images/potion/potion_m_liquid.png");
/*  585 */     POTION_M_HYBRID = loadImage("images/potion/potion_m_hybrid.png");
/*  586 */     POTION_M_SPOTS = loadImage("images/potion/potion_m_spots.png");
/*  587 */     POTION_M_OUTLINE = loadImage("images/potion/potion_m_outline.png");
/*      */ 
/*      */     
/*  590 */     POTION_SPHERE_CONTAINER = loadImage("images/potion/sphere/body.png");
/*  591 */     POTION_SPHERE_LIQUID = loadImage("images/potion/sphere/liquid.png");
/*  592 */     POTION_SPHERE_HYBRID = loadImage("images/potion/sphere/hybrid.png");
/*  593 */     POTION_SPHERE_SPOTS = loadImage("images/potion/sphere/spots.png");
/*  594 */     POTION_SPHERE_OUTLINE = loadImage("images/potion/sphere/outline.png");
/*      */ 
/*      */     
/*  597 */     POTION_H_CONTAINER = loadImage("images/potion/potion_h_glass.png");
/*  598 */     POTION_H_LIQUID = loadImage("images/potion/potion_h_liquid.png");
/*  599 */     POTION_H_HYBRID = loadImage("images/potion/potion_h_hybrid.png");
/*  600 */     POTION_H_SPOTS = loadImage("images/potion/potion_h_spots.png");
/*  601 */     POTION_H_OUTLINE = loadImage("images/potion/potion_h_outline.png");
/*      */ 
/*      */     
/*  604 */     POTION_BOTTLE_CONTAINER = loadImage("images/potion/bottle/body.png");
/*  605 */     POTION_BOTTLE_LIQUID = loadImage("images/potion/bottle/liquid.png");
/*  606 */     POTION_BOTTLE_HYBRID = loadImage("images/potion/bottle/hybrid.png");
/*  607 */     POTION_BOTTLE_SPOTS = loadImage("images/potion/bottle/spots.png");
/*  608 */     POTION_BOTTLE_OUTLINE = loadImage("images/potion/bottle/outline.png");
/*      */ 
/*      */     
/*  611 */     POTION_HEART_CONTAINER = loadImage("images/potion/heart/body.png");
/*  612 */     POTION_HEART_LIQUID = loadImage("images/potion/heart/liquid.png");
/*  613 */     POTION_HEART_HYBRID = loadImage("images/potion/heart/hybrid.png");
/*  614 */     POTION_HEART_SPOTS = loadImage("images/potion/heart/spots.png");
/*  615 */     POTION_HEART_OUTLINE = loadImage("images/potion/heart/outline.png");
/*      */ 
/*      */     
/*  618 */     POTION_SNECKO_CONTAINER = loadImage("images/potion/snecko/body.png");
/*  619 */     POTION_SNECKO_LIQUID = loadImage("images/potion/snecko/liquid.png");
/*  620 */     POTION_SNECKO_HYBRID = loadImage("images/potion/snecko/hybrid.png");
/*  621 */     POTION_SNECKO_SPOTS = loadImage("images/potion/snecko/spots.png");
/*  622 */     POTION_SNECKO_OUTLINE = loadImage("images/potion/snecko/outline.png");
/*      */ 
/*      */     
/*  625 */     POTION_FAIRY_CONTAINER = loadImage("images/potion/fairy/body.png");
/*  626 */     POTION_FAIRY_LIQUID = loadImage("images/potion/fairy/liquid.png");
/*  627 */     POTION_FAIRY_HYBRID = loadImage("images/potion/fairy/hybrid.png");
/*  628 */     POTION_FAIRY_SPOTS = loadImage("images/potion/fairy/spots.png");
/*  629 */     POTION_FAIRY_OUTLINE = loadImage("images/potion/fairy/outline.png");
/*      */ 
/*      */     
/*  632 */     POTION_GHOST_CONTAINER = loadImage("images/potion/ghost/body.png");
/*  633 */     POTION_GHOST_LIQUID = loadImage("images/potion/ghost/liquid.png");
/*  634 */     POTION_GHOST_HYBRID = loadImage("images/potion/ghost/hybrid.png");
/*  635 */     POTION_GHOST_SPOTS = loadImage("images/potion/ghost/spots.png");
/*  636 */     POTION_GHOST_OUTLINE = loadImage("images/potion/ghost/outline.png");
/*      */ 
/*      */     
/*  639 */     POTION_JAR_CONTAINER = loadImage("images/potion/jar/body.png");
/*  640 */     POTION_JAR_LIQUID = loadImage("images/potion/jar/liquid.png");
/*  641 */     POTION_JAR_HYBRID = loadImage("images/potion/jar/hybrid.png");
/*  642 */     POTION_JAR_SPOTS = loadImage("images/potion/jar/spots.png");
/*  643 */     POTION_JAR_OUTLINE = loadImage("images/potion/jar/outline.png");
/*      */ 
/*      */     
/*  646 */     POTION_BOLT_CONTAINER = loadImage("images/potion/bolt/body.png");
/*  647 */     POTION_BOLT_LIQUID = loadImage("images/potion/bolt/liquid.png");
/*  648 */     POTION_BOLT_HYBRID = loadImage("images/potion/bolt/hybrid.png");
/*  649 */     POTION_BOLT_SPOTS = loadImage("images/potion/bolt/spots.png");
/*  650 */     POTION_BOLT_OUTLINE = loadImage("images/potion/bolt/outline.png");
/*      */ 
/*      */     
/*  653 */     POTION_CARD_CONTAINER = loadImage("images/potion/card/body.png");
/*  654 */     POTION_CARD_LIQUID = loadImage("images/potion/card/liquid.png");
/*  655 */     POTION_CARD_HYBRID = loadImage("images/potion/card/hybrid.png");
/*  656 */     POTION_CARD_SPOTS = loadImage("images/potion/card/spots.png");
/*  657 */     POTION_CARD_OUTLINE = loadImage("images/potion/card/outline.png");
/*      */ 
/*      */     
/*  660 */     POTION_MOON_CONTAINER = loadImage("images/potion/moon/body.png");
/*  661 */     POTION_MOON_LIQUID = loadImage("images/potion/moon/liquid.png");
/*  662 */     POTION_MOON_HYBRID = loadImage("images/potion/moon/hybrid.png");
/*  663 */     POTION_MOON_OUTLINE = loadImage("images/potion/moon/outline.png");
/*      */ 
/*      */     
/*  666 */     POTION_SPIKY_CONTAINER = loadImage("images/potion/spiky/body.png");
/*  667 */     POTION_SPIKY_LIQUID = loadImage("images/potion/spiky/liquid.png");
/*  668 */     POTION_SPIKY_HYBRID = loadImage("images/potion/spiky/hybrid.png");
/*  669 */     POTION_SPIKY_OUTLINE = loadImage("images/potion/spiky/outline.png");
/*      */ 
/*      */     
/*  672 */     POTION_EYE_CONTAINER = loadImage("images/potion/eye/body.png");
/*  673 */     POTION_EYE_LIQUID = loadImage("images/potion/eye/liquid.png");
/*  674 */     POTION_EYE_HYBRID = loadImage("images/potion/eye/hybrid.png");
/*  675 */     POTION_EYE_OUTLINE = loadImage("images/potion/eye/outline.png");
/*      */ 
/*      */     
/*  678 */     POTION_ANVIL_CONTAINER = loadImage("images/potion/anvil/body.png");
/*  679 */     POTION_ANVIL_LIQUID = loadImage("images/potion/anvil/liquid.png");
/*  680 */     POTION_ANVIL_HYBRID = loadImage("images/potion/anvil/hybrid.png");
/*  681 */     POTION_ANVIL_OUTLINE = loadImage("images/potion/anvil/outline.png");
/*      */     
/*  683 */     POTION_PLACEHOLDER = loadImage("images/potion/potion_placeholder.png");
/*      */ 
/*      */     
/*  686 */     CHECKBOX = loadImage("images/ui/checkbox.png");
/*  687 */     TICK = loadImage("images/ui/tick.png");
/*      */ 
/*      */     
/*  690 */     ORB_DARK = loadImage("images/orbs/dark.png");
/*  691 */     ORB_LIGHTNING = loadImage("images/orbs/lightning.png");
/*  692 */     ORB_PLASMA = loadImage("images/orbs/plasma.png");
/*      */ 
/*      */     
/*  695 */     COLOR_TAB_RED = loadImage("images/ui/cardlibrary/redTab.png");
/*  696 */     COLOR_TAB_GREEN = loadImage("images/ui/cardlibrary/greenTab.png");
/*  697 */     COLOR_TAB_BLUE = loadImage("images/ui/cardlibrary/blueTab.png");
/*  698 */     COLOR_TAB_PURPLE = loadImage("images/ui/cardlibrary/purpleTab.png");
/*  699 */     COLOR_TAB_COLORLESS = loadImage("images/ui/cardlibrary/colorlessTab.png");
/*  700 */     COLOR_TAB_CURSE = loadImage("images/ui/cardlibrary/curseTab.png");
/*  701 */     COLOR_TAB_BAR = loadImage("images/ui/cardlibrary/colorTabBar.png");
/*  702 */     COLOR_TAB_LOCK = loadImage("images/ui/cardlibrary/colorTabLock.png");
/*  703 */     COLOR_TAB_BOX_UNTICKED = loadImage("images/ui/cardlibrary/tickbox_unticked.png");
/*  704 */     COLOR_TAB_BOX_TICKED = loadImage("images/ui/cardlibrary/tickbox_ticked.png");
/*      */     
/*  706 */     FILTER_IRONCLAD = loadImage("images/ui/leaderboards/ironclad.png");
/*  707 */     FILTER_SILENT = loadImage("images/ui/leaderboards/silent.png");
/*  708 */     FILTER_DEFECT = loadImage("images/ui/leaderboards/defect.png");
/*  709 */     FILTER_WATCHER = loadImage("images/ui/leaderboards/watcher.png");
/*  710 */     FILTER_FRIENDS = loadImage("images/ui/leaderboards/friends.png");
/*  711 */     FILTER_GLOBAL = loadImage("images/ui/leaderboards/global.png");
/*  712 */     FILTER_SCORE = loadImage("images/ui/leaderboards/score.png");
/*  713 */     FILTER_CHAIN = loadImage("images/ui/leaderboards/chain.png");
/*  714 */     FILTER_TIME = loadImage("images/ui/leaderboards/time.png");
/*  715 */     P_STANDARD = loadImage("images/ui/mainMenu/portrait/standard.jpg");
/*  716 */     P_DAILY = loadImage("images/ui/mainMenu/portrait/daily.jpg");
/*  717 */     P_LOOP = loadImage("images/ui/mainMenu/portrait/loop.jpg");
/*  718 */     P_INFO_CARD = loadImage("images/ui/mainMenu/portrait/card.jpg");
/*  719 */     P_INFO_RELIC = loadImage("images/ui/mainMenu/portrait/relics.jpg");
/*  720 */     P_INFO_POTION = loadImage("images/ui/mainMenu/portrait/potion.jpg");
/*  721 */     P_STAT_CHAR = loadImage("images/ui/mainMenu/portrait/charstat.jpg");
/*  722 */     P_STAT_LEADERBOARD = loadImage("images/ui/mainMenu/portrait/leaderboards.jpg");
/*  723 */     P_STAT_HISTORY = loadImage("images/ui/mainMenu/portrait/history.jpg");
/*  724 */     P_SETTING_GAME = loadImage("images/ui/mainMenu/portrait/gamesettings.jpg");
/*  725 */     P_SETTING_INPUT = loadImage("images/ui/mainMenu/portrait/input_settings.jpg");
/*  726 */     P_SETTING_CREDITS = loadImage("images/ui/mainMenu/portrait/credits.jpg");
/*  727 */     P_LOCK = loadImage("images/ui/mainMenu/portrait/lock.png");
/*  728 */     MENU_PANEL_BG_BLUE = loadImage("images/ui/mainMenu/menuPanel.png");
/*  729 */     MENU_PANEL_BG_GRAY = loadImage("images/ui/mainMenu/menuPanel4.png");
/*  730 */     MENU_PANEL_BG_RED = loadImage("images/ui/mainMenu/menuPanel3.png");
/*  731 */     MENU_PANEL_BG_BEIGE = loadImage("images/ui/mainMenu/menuPanel2.png");
/*  732 */     MENU_PANEL_FRAME = loadImage("images/ui/mainMenu/menuPanelFrame.png");
/*  733 */     FILTER_GLOW_BG = loadImage("images/ui/leaderboards/glow.png");
/*  734 */     CHAR_OPT_HIGHLIGHT = loadImage("images/ui/charSelect/highlightButton2.png");
/*  735 */     POPUP_ARROW = loadImage("images/ui/popupArrow.png");
/*  736 */     RELIC_POPUP = loadImage("images/ui/relicPopup.png");
/*  737 */     RELIC_LINKED = loadImage("images/ui/reward/relic_link.png");
/*  738 */     RELIC_LOCK = loadImage("images/relics/lock.png");
/*  739 */     RELIC_LOCK_OUTLINE = loadImage("images/relics/outline/lock.png");
/*  740 */     FILTER_ARROW = loadImage("images/ui/filterArrow.png");
/*  741 */     SCROLL_GRADIENT = loadImage("images/ui/scrollGradient.png");
/*  742 */     WHITE_GRADIENT = loadImage("images/ui/white_gradient.png");
/*  743 */     MAP_ICON = loadImage("images/ui/topPanel/map.png");
/*  744 */     DECK_ICON = loadImage("images/ui/topPanel/deck.png");
/*  745 */     SETTINGS_ICON = loadImage("images/ui/topPanel/settings.png");
/*  746 */     ENDLESS_ICON = loadImage("images/ui/topPanel/endless.png");
/*  747 */     KEY_SLOTS_ICON = loadImage("images/ui/topPanel/key_slots.png");
/*  748 */     RUBY_KEY = loadImage("images/ui/topPanel/key_red.png");
/*  749 */     EMERALD_KEY = loadImage("images/ui/topPanel/key_green.png");
/*  750 */     SAPPHIRE_KEY = loadImage("images/ui/topPanel/key_blue.png");
/*  751 */     RETICLE_CORNER = loadImage("images/ui/combat/reticleCorner.png");
/*  752 */     TARGET_UI_ARROW = loadImage("images/ui/combat/reticleArrow.png");
/*  753 */     TARGET_UI_CIRCLE = loadImage("images/ui/combat/reticleBlock.png");
/*  754 */     LARGE_CLOUD = loadImage("images/ui/dialog/largeCircle2.png");
/*  755 */     SMALL_CLOUD = loadImage("images/ui/dialog/smallCircle.png");
/*  756 */     GHOST_ORB_1 = loadImage("images/monsters/theBottom/boss/fire1.png");
/*  757 */     GHOST_ORB_2 = loadImage("images/monsters/theBottom/boss/fire2.png");
/*      */     
/*  759 */     CONTROLLER_HB_HIGHLIGHT = loadImage("images/ui/selectionBox.png");
/*  760 */     DEBUG_HITBOX_IMG = new Texture("images/debugHitbox.png");
/*  761 */     DEBUG_HITBOX_IMG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
/*      */ 
/*      */     
/*  764 */     WARNING_ICON_VFX = loadImage("images/vfx/ui/warning.png");
/*  765 */     SPOTLIGHT_VFX = loadImage("images/vfx/spotlight.jpg");
/*  766 */     WOBBLY_ORB_VFX = loadImage("images/vfx/orb.png");
/*  767 */     ANIMATED_SLASH_VFX = loadImage("images/vfx/combat/slash_1.png");
/*  768 */     DEBUFF_VFX_1 = loadImage("images/ui/intent/debuffVFX1.png");
/*  769 */     DEBUFF_VFX_2 = loadImage("images/ui/intent/debuffVFX2.png");
/*  770 */     DEBUFF_VFX_3 = loadImage("images/ui/intent/debuffVFX3.png");
/*  771 */     FLAME_ANIM_1 = loadImage("images/vfx/map/flame_1_0.png");
/*  772 */     FLAME_ANIM_2 = loadImage("images/vfx/map/flame_1_1.png");
/*  773 */     FLAME_ANIM_3 = loadImage("images/vfx/map/flame_1_2.png");
/*  774 */     FLAME_ANIM_4 = loadImage("images/vfx/map/flame_1_3.png");
/*  775 */     FLAME_ANIM_5 = loadImage("images/vfx/map/flame_1_4.png");
/*  776 */     FLAME_ANIM_6 = loadImage("images/vfx/map/flame_1_5.png");
/*  777 */     WEB_VFX = loadImage("images/vfx/web.png");
/*  778 */     HORIZONTAL_LINE = loadImage("images/vfx/horizontal_line.png");
/*  779 */     PETAL_VFX[0] = loadImage("images/vfx/petal/petal1.png");
/*  780 */     PETAL_VFX[1] = loadImage("images/vfx/petal/petal2.png");
/*  781 */     PETAL_VFX[2] = loadImage("images/vfx/petal/petal3.png");
/*  782 */     PETAL_VFX[3] = loadImage("images/vfx/petal/petal4.png");
/*  783 */     WATER_DROP_VFX[0] = loadImage("images/vfx/water_drop/drop1.png");
/*  784 */     WATER_DROP_VFX[1] = loadImage("images/vfx/water_drop/drop2.png");
/*  785 */     WATER_DROP_VFX[2] = loadImage("images/vfx/water_drop/drop3.png");
/*  786 */     WATER_DROP_VFX[3] = loadImage("images/vfx/water_drop/drop4.png");
/*  787 */     WATER_DROP_VFX[4] = loadImage("images/vfx/water_drop/drop5.png");
/*  788 */     WATER_DROP_VFX[5] = loadImage("images/vfx/water_drop/drop6.png");
/*      */ 
/*      */     
/*  791 */     ORB_SLOT_1 = loadImage("images/orbs/empty1.png");
/*  792 */     ORB_SLOT_2 = loadImage("images/orbs/empty2.png");
/*  793 */     FROST_ORB_RIGHT = loadImage("images/orbs/frostRight.png");
/*  794 */     FROST_ORB_LEFT = loadImage("images/orbs/frostLeft.png");
/*  795 */     FROST_ORB_MIDDLE = loadImage("images/orbs/frostMid.png");
/*  796 */     FROST_ACTIVATE_VFX_1 = loadImage("images/orbs/f1.png");
/*  797 */     FROST_ACTIVATE_VFX_2 = loadImage("images/orbs/f2.png");
/*  798 */     FROST_ACTIVATE_VFX_3 = loadImage("images/orbs/f3.png");
/*  799 */     LIGHTNING_PASSIVE_VFX.add(loadImage("images/vfx/defect/lightning_passive_1.png"));
/*  800 */     LIGHTNING_PASSIVE_VFX.add(loadImage("images/vfx/defect/lightning_passive_2.png"));
/*  801 */     LIGHTNING_PASSIVE_VFX.add(loadImage("images/vfx/defect/lightning_passive_3.png"));
/*  802 */     LIGHTNING_PASSIVE_VFX.add(loadImage("images/vfx/defect/lightning_passive_4.png"));
/*  803 */     LIGHTNING_PASSIVE_VFX.add(loadImage("images/vfx/defect/lightning_passive_5.png"));
/*      */     
/*  805 */     DARK_ORB_PASSIVE_VFX_1 = loadImage("images/orbs/d1.png");
/*  806 */     DARK_ORB_PASSIVE_VFX_2 = loadImage("images/orbs/d2.png");
/*  807 */     DARK_ORB_PASSIVE_VFX_3 = loadImage("images/orbs/d3.png");
/*  808 */     DARK_ORB_ACTIVATE_VFX = loadImage("images/orbs/darkEvoke2.png");
/*      */     
/*  810 */     SPEECH_BUBBLE_IMG = loadImage("images/ui/dialog/speechBubble2.png");
/*  811 */     SHOP_SPEECH_BUBBLE_IMG = loadImage("images/ui/dialog/speechBubble3.png");
/*  812 */     KEYWORD_TOP = loadImage("images/ui/tip/tipTop.png");
/*  813 */     KEYWORD_BODY = loadImage("images/ui/tip/tipMid.png");
/*  814 */     KEYWORD_BOT = loadImage("images/ui/tip/tipBot.png");
/*  815 */     FTUE = loadImage("images/ui/tip/ftue.png");
/*  816 */     FTUE_BTN = loadImage("images/ui/tip/ftueButton.png");
/*  817 */     DYNAMIC_BTN_IMG2 = loadImage("images/ui/topPanel/buttonL.png");
/*  818 */     DYNAMIC_BTN_IMG3 = loadImage("images/ui/topPanel/buttonLRed.png");
/*  819 */     WHITE_SQUARE_IMG = new Texture("images/whiteSquare32.png");
/*  820 */     WHITE_SQUARE_IMG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
/*  821 */     DECK_COUNT_CIRCLE = loadImage("images/ui/topPanel/countCircle.png");
/*  822 */     TOP_PANEL_BAR = loadImage("images/ui/topPanel/bar.png");
/*  823 */     TIMER_ICON = loadImage("images/ui/timerIcon.png");
/*      */ 
/*      */     
/*  826 */     VICTORY_BANNER = loadImage("images/ui/selectBanner.png");
/*  827 */     REWARD_SCREEN_SHEET = loadImage("images/ui/reward/rewardScreenSheet.png");
/*  828 */     REWARD_SCREEN_TAKE_BUTTON = loadImage("images/ui/reward/takeAll.png");
/*  829 */     REWARD_SCREEN_TAKE_USED_BUTTON = loadImage("images/ui/reward/takeAllUsed.png");
/*  830 */     REWARD_SCREEN_ITEM = loadImage("images/ui/reward/rewardListItemPanel.png");
/*  831 */     REWARD_CARD_NORMAL = loadImage("images/ui/reward/normalCardReward.png");
/*  832 */     REWARD_CARD_BOSS = loadImage("images/ui/reward/bossCardReward.png");
/*      */ 
/*      */     
/*  835 */     UI_GOLD = loadImage("images/ui/topPanel/gold.png");
/*  836 */     TP_HP = loadImage("images/ui/topPanel/panelHeart.png");
/*  837 */     TP_GOLD = loadImage("images/ui/topPanel/panelGoldBag.png");
/*  838 */     TP_FLOOR = loadImage("images/ui/topPanel/floor.png");
/*  839 */     TP_ASCENSION = loadImage("images/ui/topPanel/ascension.png");
/*  840 */     HEALTH_BAR_B = loadImage("images/ui/combat/body7.png");
/*  841 */     HEALTH_BAR_L = loadImage("images/ui/combat/left7.png");
/*  842 */     HEALTH_BAR_R = loadImage("images/ui/combat/right7.png");
/*  843 */     HB_SHADOW_L = loadImage("images/ui/combat/leftBg.png");
/*  844 */     HB_SHADOW_R = loadImage("images/ui/combat/rightBg.png");
/*  845 */     HB_SHADOW_B = loadImage("images/ui/combat/bodyBg.png");
/*  846 */     BLOCK_ICON = loadImage("images/ui/combat/block.png");
/*  847 */     BLOCK_ICON_L = loadImage("images/ui/combat/blockL.png");
/*  848 */     BLOCK_ICON_R = loadImage("images/ui/combat/blockR.png");
/*  849 */     BLOCK_BAR_B = loadImage("images/ui/combat/blockBody3.png");
/*  850 */     BLOCK_BAR_R = loadImage("images/ui/combat/blockRight3.png");
/*  851 */     BLOCK_BAR_L = loadImage("images/ui/combat/blockLeft3.png");
/*  852 */     DRAW_PILE_BANNER = loadImage("images/ui/combat/combatDeckBanner.png");
/*  853 */     DISCARD_PILE_BANNER = loadImage("images/ui/combat/discardPileBanner.png");
/*      */     
/*  855 */     SETTINGS_BACKGROUND = loadImage("images/ui/option/settingsBackground.png");
/*  856 */     OPTION_ABANDON = loadImage("images/ui/option/abandon.png");
/*  857 */     OPTION_TOGGLE = loadImage("images/ui/option/toggleButtonBase.png");
/*  858 */     OPTION_TOGGLE_ON = loadImage("images/ui/option/toggleButtonOverlay2.png");
/*  859 */     OPTION_SLIDER_BG = loadImage("images/ui/option/sliderBg.png");
/*  860 */     OPTION_SLIDER = loadImage("images/ui/option/slider.png");
/*  861 */     OPTION_EXIT = loadImage("images/ui/option/quitButton.png");
/*  862 */     OPTION_CONFIRM = loadImage("images/ui/option/confirm.png");
/*  863 */     RENAME_BOX = loadImage("images/ui/option/nameBox.png");
/*  864 */     OPTION_YES = loadImage("images/ui/option/yes.png");
/*  865 */     OPTION_NO = loadImage("images/ui/option/no.png");
/*  866 */     SCROLL_BAR_TOP = loadImage("images/ui/mainMenu/scrollBar/scrollBarTrackTop.png");
/*  867 */     SCROLL_BAR_MIDDLE = loadImage("images/ui/mainMenu/scrollBar/scrollBarTrackMid.png");
/*  868 */     SCROLL_BAR_BOTTOM = loadImage("images/ui/mainMenu/scrollBar/scrollBarTrackBottom.png");
/*  869 */     SCROLL_BAR_TRAIN = loadImage("images/ui/mainMenu/scrollBar/scrollBarTrain.png");
/*  870 */     SCROLL_BAR_LEFT = loadImage("images/ui/mainMenu/scrollBar/scrollBarTrackLeft.png");
/*  871 */     SCROLL_BAR_HORIZONTAL_MIDDLE = loadImage("images/ui/mainMenu/scrollBar/scrollBarTrackHorizontalMid.png");
/*  872 */     SCROLL_BAR_RIGHT = loadImage("images/ui/mainMenu/scrollBar/scrollBarTrackRight.png");
/*  873 */     SCROLL_BAR_HORIZONTAL_TRAIN = loadImage("images/ui/mainMenu/scrollBar/scrollBarHorizontalTrain.png");
/*  874 */     INPUT_SETTINGS_BG = loadImage("images/ui/option/inputPaneBg2.png");
/*  875 */     INPUT_SETTINGS_EDGES = loadImage("images/ui/option/inputPaneFg.png");
/*  876 */     INPUT_SETTINGS_ROW = loadImage("images/ui/option/inputCell2.png");
/*      */ 
/*      */     
/*  879 */     DECK_BTN_BASE = loadImage("images/ui/deckButton/base.png");
/*  880 */     DISCARD_BTN_BASE = loadImage("images/ui/discardButton/base.png");
/*  881 */     PEEK_BUTTON = loadImage("images/ui/topPanel/peek_button.png");
/*      */ 
/*      */     
/*  884 */     CAMPFIRE_REST_BUTTON = loadImage("images/ui/campfire/sleep.png");
/*  885 */     CAMPFIRE_SMITH_BUTTON = loadImage("images/ui/campfire/smith.png");
/*  886 */     CAMPFIRE_TOKE_BUTTON = loadImage("images/ui/campfire/toke.png");
/*  887 */     CAMPFIRE_TRAIN_BUTTON = loadImage("images/ui/campfire/train.png");
/*  888 */     CAMPFIRE_DIG_BUTTON = loadImage("images/ui/campfire/dig.png");
/*  889 */     CAMPFIRE_RECALL_BUTTON = loadImage("images/ui/campfire/recall.png");
/*  890 */     CAMPFIRE_HOVER_BUTTON = loadImage("images/ui/campfire/outline.png");
/*  891 */     UPGRADE_ARROW = loadImage("images/ui/campfire/upgradeArrow.png");
/*      */     
/*  893 */     CF_LEFT_ARROW = loadImage("images/ui/charSelect/tinyLeftArrow.png");
/*  894 */     CF_RIGHT_ARROW = loadImage("images/ui/charSelect/tinyRightArrow.png");
/*      */ 
/*      */     
/*  897 */     INTENT_ATK_1 = loadImage("images/ui/intent/attack/attack_intent_1.png");
/*  898 */     INTENT_ATK_2 = loadImage("images/ui/intent/attack/attack_intent_2.png");
/*  899 */     INTENT_ATK_3 = loadImage("images/ui/intent/attack/attack_intent_3.png");
/*  900 */     INTENT_ATK_4 = loadImage("images/ui/intent/attack/attack_intent_4.png");
/*  901 */     INTENT_ATK_5 = loadImage("images/ui/intent/attack/attack_intent_5.png");
/*  902 */     INTENT_ATK_6 = loadImage("images/ui/intent/attack/attack_intent_6.png");
/*  903 */     INTENT_ATK_7 = loadImage("images/ui/intent/attack/attack_intent_7.png");
/*  904 */     INTENT_ATK_TIP_1 = loadImage("images/ui/intent/tip/1.png");
/*  905 */     INTENT_ATK_TIP_2 = loadImage("images/ui/intent/tip/2.png");
/*  906 */     INTENT_ATK_TIP_3 = loadImage("images/ui/intent/tip/3.png");
/*  907 */     INTENT_ATK_TIP_4 = loadImage("images/ui/intent/tip/4.png");
/*  908 */     INTENT_ATK_TIP_5 = loadImage("images/ui/intent/tip/5.png");
/*  909 */     INTENT_ATK_TIP_6 = loadImage("images/ui/intent/tip/6.png");
/*  910 */     INTENT_ATK_TIP_7 = loadImage("images/ui/intent/tip/7.png");
/*  911 */     INTENT_BUFF = loadImage("images/ui/intent/buff1.png");
/*  912 */     INTENT_BUFF_L = loadImage("images/ui/intent/buff1L.png");
/*  913 */     INTENT_DEBUFF = loadImage("images/ui/intent/debuff1.png");
/*  914 */     INTENT_DEBUFF_L = loadImage("images/ui/intent/debuff1L.png");
/*  915 */     INTENT_DEBUFF2 = loadImage("images/ui/intent/debuff2.png");
/*  916 */     INTENT_DEBUFF2_L = loadImage("images/ui/intent/debuff2L.png");
/*  917 */     INTENT_DEFEND = loadImage("images/ui/intent/defend.png");
/*  918 */     INTENT_DEFEND_L = loadImage("images/ui/intent/defendL.png");
/*  919 */     INTENT_DEFEND_BUFF = loadImage("images/ui/intent/defendBuff.png");
/*  920 */     INTENT_DEFEND_BUFF_L = loadImage("images/ui/intent/defendBuffL.png");
/*  921 */     INTENT_ESCAPE = loadImage("images/ui/intent/escape.png");
/*  922 */     INTENT_ESCAPE_L = loadImage("images/ui/intent/escapeL.png");
/*  923 */     INTENT_MAGIC = loadImage("images/ui/intent/magic.png");
/*  924 */     INTENT_MAGIC_L = loadImage("images/ui/intent/magicL.png");
/*  925 */     INTENT_SLEEP = loadImage("images/ui/intent/sleep.png");
/*  926 */     INTENT_SLEEP_L = loadImage("images/ui/intent/sleepL.png");
/*  927 */     INTENT_STUN = loadImage("images/ui/intent/stun.png");
/*  928 */     INTENT_UNKNOWN = loadImage("images/ui/intent/unknown.png");
/*  929 */     INTENT_UNKNOWN_L = loadImage("images/ui/intent/unknownL.png");
/*  930 */     INTENT_ATTACK_BUFF = loadImage("images/ui/intent/attackBuff.png");
/*  931 */     INTENT_ATTACK_DEBUFF = loadImage("images/ui/intent/attackDebuff.png");
/*  932 */     INTENT_ATTACK_DEFEND = loadImage("images/ui/intent/attackDefend.png");
/*      */     
/*  934 */     ENERGY_RED_LAYER6 = loadImage("images/ui/topPanel/red/layer6.png");
/*  935 */     ENERGY_RED_LAYER5 = loadImage("images/ui/topPanel/red/layer5.png");
/*  936 */     ENERGY_RED_LAYER4 = loadImage("images/ui/topPanel/red/layer4.png");
/*  937 */     ENERGY_RED_LAYER3 = loadImage("images/ui/topPanel/red/layer3.png");
/*  938 */     ENERGY_RED_LAYER2 = loadImage("images/ui/topPanel/red/layer2.png");
/*  939 */     ENERGY_RED_LAYER1 = loadImage("images/ui/topPanel/red/layer1.png");
/*  940 */     ENERGY_RED_LAYER5D = loadImage("images/ui/topPanel/red/layer5d.png");
/*  941 */     ENERGY_RED_LAYER4D = loadImage("images/ui/topPanel/red/layer4d.png");
/*  942 */     ENERGY_RED_LAYER3D = loadImage("images/ui/topPanel/red/layer3d.png");
/*  943 */     ENERGY_RED_LAYER2D = loadImage("images/ui/topPanel/red/layer2d.png");
/*  944 */     ENERGY_RED_LAYER1D = loadImage("images/ui/topPanel/red/layer1d.png");
/*      */     
/*  946 */     ENERGY_GREEN_LAYER6 = loadImage("images/ui/topPanel/green/layer6.png");
/*  947 */     ENERGY_GREEN_LAYER5 = loadImage("images/ui/topPanel/green/layer5.png");
/*  948 */     ENERGY_GREEN_LAYER4 = loadImage("images/ui/topPanel/green/layer4.png");
/*  949 */     ENERGY_GREEN_LAYER3 = loadImage("images/ui/topPanel/green/layer3.png");
/*  950 */     ENERGY_GREEN_LAYER2 = loadImage("images/ui/topPanel/green/layer2.png");
/*  951 */     ENERGY_GREEN_LAYER1 = loadImage("images/ui/topPanel/green/layer1.png");
/*  952 */     ENERGY_GREEN_LAYER5D = loadImage("images/ui/topPanel/green/layer5d.png");
/*  953 */     ENERGY_GREEN_LAYER4D = loadImage("images/ui/topPanel/green/layer4d.png");
/*  954 */     ENERGY_GREEN_LAYER3D = loadImage("images/ui/topPanel/green/layer3d.png");
/*  955 */     ENERGY_GREEN_LAYER2D = loadImage("images/ui/topPanel/green/layer2d.png");
/*  956 */     ENERGY_GREEN_LAYER1D = loadImage("images/ui/topPanel/green/layer1d.png");
/*      */     
/*  958 */     ENERGY_BLUE_LAYER6 = loadImage("images/ui/topPanel/blue/border.png");
/*  959 */     ENERGY_BLUE_LAYER5 = loadImage("images/ui/topPanel/blue/5.png");
/*  960 */     ENERGY_BLUE_LAYER4 = loadImage("images/ui/topPanel/blue/4.png");
/*  961 */     ENERGY_BLUE_LAYER3 = loadImage("images/ui/topPanel/blue/3.png");
/*  962 */     ENERGY_BLUE_LAYER2 = loadImage("images/ui/topPanel/blue/2.png");
/*  963 */     ENERGY_BLUE_LAYER1 = loadImage("images/ui/topPanel/blue/1.png");
/*  964 */     ENERGY_BLUE_LAYER5D = loadImage("images/ui/topPanel/blue/5d.png");
/*  965 */     ENERGY_BLUE_LAYER4D = loadImage("images/ui/topPanel/blue/4d.png");
/*  966 */     ENERGY_BLUE_LAYER3D = loadImage("images/ui/topPanel/blue/3d.png");
/*  967 */     ENERGY_BLUE_LAYER2D = loadImage("images/ui/topPanel/blue/2d.png");
/*  968 */     ENERGY_BLUE_LAYER1D = loadImage("images/ui/topPanel/blue/1d.png");
/*      */     
/*  970 */     ENERGY_PURPLE_BORDER = loadImage("images/ui/topPanel/purple/border.png");
/*  971 */     ENERGY_PURPLE_LAYER1 = loadImage("images/ui/topPanel/purple/l1.png");
/*  972 */     ENERGY_PURPLE_LAYER2 = loadImage("images/ui/topPanel/purple/l2.png");
/*  973 */     ENERGY_PURPLE_LAYER3 = loadImage("images/ui/topPanel/purple/l3.png");
/*  974 */     ENERGY_PURPLE_LAYER4 = loadImage("images/ui/topPanel/purple/l4.png");
/*      */     
/*  976 */     RED_ORB_FLASH_VFX = loadImage("images/ui/topPanel/energyRedVFX.png");
/*  977 */     GREEN_ORB_FLASH_VFX = loadImage("images/ui/topPanel/energyGreenVFX.png");
/*  978 */     BLUE_ORB_FLASH_VFX = loadImage("images/ui/topPanel/energyBlueVFX.png");
/*  979 */     PURPLE_ORB_FLASH_VFX = loadImage("images/ui/topPanel/energyPurpleVFX.png");
/*      */ 
/*      */     
/*  982 */     MAP_NODE_ELITE = loadImage("images/ui/map/elite.png");
/*  983 */     MAP_NODE_ELITE_OUTLINE = loadImage("images/ui/map/eliteOutline.png");
/*  984 */     MAP_NODE_ENEMY = loadImage("images/ui/map/monster.png");
/*  985 */     MAP_NODE_ENEMY_OUTLINE = loadImage("images/ui/map/monsterOutline.png");
/*  986 */     MAP_NODE_REST = loadImage("images/ui/map/rest.png");
/*  987 */     MAP_NODE_REST_OUTLINE = loadImage("images/ui/map/restOutline.png");
/*  988 */     MAP_NODE_MERCHANT = loadImage("images/ui/map/shop.png");
/*  989 */     MAP_NODE_MERCHANT_OUTLINE = loadImage("images/ui/map/shopOutline.png");
/*  990 */     MAP_LEGEND = loadImage("images/ui/map/legend2.png");
/*  991 */     MAP_NODE_TREASURE = loadImage("images/ui/map/chest.png");
/*  992 */     MAP_NODE_TREASURE_OUTLINE = loadImage("images/ui/map/chestOutline.png");
/*  993 */     MAP_CIRCLE_1 = loadImage("images/ui/map/circle1.png");
/*  994 */     MAP_CIRCLE_2 = loadImage("images/ui/map/circle2.png");
/*  995 */     MAP_CIRCLE_3 = loadImage("images/ui/map/circle3.png");
/*  996 */     MAP_CIRCLE_4 = loadImage("images/ui/map/circle4.png");
/*  997 */     MAP_CIRCLE_5 = loadImage("images/ui/map/circle5.png");
/*  998 */     MAP_NODE_EVENT = loadImage("images/ui/map/event.png");
/*  999 */     MAP_NODE_EVENT_OUTLINE = loadImage("images/ui/map/eventOutline.png");
/* 1000 */     MAP_DOT_1 = loadImage("images/ui/map/dot1.png");
/*      */ 
/*      */     
/* 1003 */     CANCEL_BUTTON = loadImage("images/ui/topPanel/cancelButton.png");
/* 1004 */     CANCEL_BUTTON_SHADOW = loadImage("images/ui/topPanel/cancelButtonShadow.png");
/* 1005 */     CANCEL_BUTTON_OUTLINE = loadImage("images/ui/topPanel/cancelButtonOutline.png");
/* 1006 */     CONFIRM_BUTTON = loadImage("images/ui/topPanel/confirmButton.png");
/* 1007 */     CONFIRM_BUTTON_SHADOW = loadImage("images/ui/topPanel/confirmButtonShadow.png");
/* 1008 */     CONFIRM_BUTTON_OUTLINE = loadImage("images/ui/topPanel/confirmButtonOutline.png");
/* 1009 */     PROCEED_BUTTON = loadImage("images/ui/topPanel/proceedButton.png");
/* 1010 */     PROCEED_BUTTON_SHADOW = loadImage("images/ui/topPanel/proceedButtonShadow.png");
/* 1011 */     PROCEED_BUTTON_OUTLINE = loadImage("images/ui/topPanel/proceedButtonOutline.png");
/*      */     
/* 1013 */     UNLOCK_TEXT_BG = loadImage("images/ui/unlockBlur.png");
/* 1014 */     EVENT_IMG_FRAME = loadImage("images/ui/event/imgFrame.png");
/* 1015 */     EVENT_BUTTON_ENABLED = loadImage("images/ui/event/enabledButton.png");
/* 1016 */     EVENT_BUTTON_DISABLED = loadImage("images/ui/event/disabledButton.png");
/* 1017 */     EVENT_ROOM_PANEL = loadImage("images/ui/event/roomTextPanel.png");
/* 1018 */     END_TURN_BUTTON = loadImage("images/ui/topPanel/endTurnButton.png");
/* 1019 */     END_TURN_BUTTON_GLOW = loadImage("images/ui/topPanel/endTurnButtonGlow.png");
/* 1020 */     END_TURN_HOVER = loadImage("images/ui/topPanel/endTurnHover.png");
/*      */ 
/*      */     
/* 1023 */     TINY_CARD_ATTACK = loadImage("images/tinyCards/attackPortrait.png", false);
/* 1024 */     TINY_CARD_SKILL = loadImage("images/tinyCards/skillPortrait.png", false);
/* 1025 */     TINY_CARD_POWER = loadImage("images/tinyCards/powerPortrait.png", false);
/* 1026 */     TINY_CARD_PORTRAIT_SHADOW = loadImage("images/tinyCards/portraitShadow.png", false);
/* 1027 */     TINY_CARD_BACKGROUND = loadImage("images/tinyCards/cardBack.png", false);
/* 1028 */     TINY_CARD_BANNER = loadImage("images/tinyCards/banner.png", false);
/* 1029 */     TINY_CARD_BANNER_SHADOW = loadImage("images/tinyCards/bannerShadow.png", false);
/* 1030 */     TINY_CARD_DESCRIPTION = loadImage("images/tinyCards/descBox.png", false);
/*      */ 
/*      */     
/* 1033 */     RUN_HISTORY_MAP_ICON_BOSS = loadImage("images/ui/runHistory/mapIcons/boss.png", false);
/* 1034 */     RUN_HISTORY_MAP_ICON_BOSS_CHEST = loadImage("images/ui/runHistory/mapIcons/bosschest.png", false);
/* 1035 */     RUN_HISTORY_MAP_ICON_CHEST = loadImage("images/ui/runHistory/mapIcons/chest.png", false);
/* 1036 */     RUN_HISTORY_MAP_ICON_ELITE = loadImage("images/ui/runHistory/mapIcons/elite.png", false);
/* 1037 */     RUN_HISTORY_MAP_ICON_EVENT = loadImage("images/ui/runHistory/mapIcons/unknown.png", false);
/* 1038 */     RUN_HISTORY_MAP_ICON_MONSTER = loadImage("images/ui/runHistory/mapIcons/monster.png", false);
/* 1039 */     RUN_HISTORY_MAP_ICON_REST = loadImage("images/ui/runHistory/mapIcons/rest.png", false);
/* 1040 */     RUN_HISTORY_MAP_ICON_SHOP = loadImage("images/ui/runHistory/mapIcons/shop.png", false);
/* 1041 */     RUN_HISTORY_MAP_ICON_UNKNOWN_CHEST = loadImage("images/ui/runHistory/mapIcons/unknownChest.png", false);
/* 1042 */     RUN_HISTORY_MAP_ICON_UNKNOWN_MONSTER = loadImage("images/ui/runHistory/mapIcons/unknownMonster.png", false);
/* 1043 */     RUN_HISTORY_MAP_ICON_UNKNOWN_SHOP = loadImage("images/ui/runHistory/mapIcons/unknownShop.png", false);
/*      */ 
/*      */     
/* 1046 */     BOSS_CHEST = loadImage("images/npcs/bossChest.png");
/* 1047 */     BOSS_CHEST_OPEN = loadImage("images/npcs/bossChestOpened.png");
/* 1048 */     BOSS_CHEST_SMOKE = loadImage("images/ui/bossRelicScreenOverlay.png");
/* 1049 */     L_CHEST = loadImage("images/npcs/largeChest.png");
/* 1050 */     L_CHEST_OPEN = loadImage("images/npcs/largeChestOpened.png");
/* 1051 */     M_CHEST = loadImage("images/npcs/mediumChest.png");
/* 1052 */     M_CHEST_OPEN = loadImage("images/npcs/mediumChestOpened.png");
/* 1053 */     S_CHEST = loadImage("images/npcs/smallChest.png");
/* 1054 */     S_CHEST_OPEN = loadImage("images/npcs/smallChestOpened.png");
/*      */     
/* 1056 */     logger.info("Texture load time: " + (System.currentTimeMillis() - startTime) + "ms");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void loadRelicImg(String id, String imgUrl) {
/* 1067 */     if (!relicImages.containsKey(id)) {
/* 1068 */       relicImages.put(id, loadImage("images/relics/" + imgUrl));
/* 1069 */       relicOutlineImages.put(id, loadImage("images/relics/outline/" + imgUrl));
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Texture getRelicImg(String id) {
/* 1074 */     return relicImages.get(id);
/*      */   }
/*      */   
/*      */   public static Texture getRelicOutlineImg(String id) {
/* 1078 */     return relicOutlineImages.get(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void loadControllerImages(CInputHelper.ControllerModel model) {
/* 1083 */     if (CONTROLLER_A != null) {
/* 1084 */       CONTROLLER_A.dispose();
/* 1085 */       CONTROLLER_B.dispose();
/* 1086 */       CONTROLLER_X.dispose();
/* 1087 */       CONTROLLER_Y.dispose();
/* 1088 */       CONTROLLER_LB.dispose();
/* 1089 */       CONTROLLER_RB.dispose();
/* 1090 */       CONTROLLER_BACK.dispose();
/* 1091 */       CONTROLLER_START.dispose();
/* 1092 */       CONTROLLER_LS.dispose();
/* 1093 */       CONTROLLER_LS_UP.dispose();
/* 1094 */       CONTROLLER_LS_DOWN.dispose();
/* 1095 */       CONTROLLER_LS_LEFT.dispose();
/* 1096 */       CONTROLLER_LS_RIGHT.dispose();
/* 1097 */       CONTROLLER_RS.dispose();
/* 1098 */       CONTROLLER_RS_UP.dispose();
/* 1099 */       CONTROLLER_RS_DOWN.dispose();
/* 1100 */       CONTROLLER_RS_LEFT.dispose();
/* 1101 */       CONTROLLER_RS_RIGHT.dispose();
/* 1102 */       CONTROLLER_D_UP.dispose();
/* 1103 */       CONTROLLER_D_DOWN.dispose();
/* 1104 */       CONTROLLER_D_LEFT.dispose();
/* 1105 */       CONTROLLER_D_RIGHT.dispose();
/* 1106 */       CONTROLLER_LT.dispose();
/* 1107 */       CONTROLLER_RT.dispose();
/*      */     } 
/*      */     
/* 1110 */     String dirName = "xbox360";
/*      */     
/* 1112 */     switch (model) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case XBOX_ONE:
/* 1124 */         dirName = "xboxOne";
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1130 */     CONTROLLER_A = loadImage("images/ui/controller/" + dirName + "/a.png");
/* 1131 */     CONTROLLER_B = loadImage("images/ui/controller/" + dirName + "/b.png");
/* 1132 */     CONTROLLER_X = loadImage("images/ui/controller/" + dirName + "/x.png");
/* 1133 */     CONTROLLER_Y = loadImage("images/ui/controller/" + dirName + "/y.png");
/* 1134 */     CONTROLLER_LB = loadImage("images/ui/controller/" + dirName + "/lb.png");
/* 1135 */     CONTROLLER_RB = loadImage("images/ui/controller/" + dirName + "/rb.png");
/* 1136 */     CONTROLLER_BACK = loadImage("images/ui/controller/" + dirName + "/back.png");
/* 1137 */     CONTROLLER_START = loadImage("images/ui/controller/" + dirName + "/start.png");
/* 1138 */     CONTROLLER_LS = loadImage("images/ui/controller/" + dirName + "/ls.png");
/* 1139 */     CONTROLLER_LS_UP = loadImage("images/ui/controller/" + dirName + "/ls_up.png");
/* 1140 */     CONTROLLER_LS_DOWN = loadImage("images/ui/controller/" + dirName + "/ls_down.png");
/* 1141 */     CONTROLLER_LS_LEFT = loadImage("images/ui/controller/" + dirName + "/ls_left.png");
/* 1142 */     CONTROLLER_LS_RIGHT = loadImage("images/ui/controller/" + dirName + "/ls_right.png");
/* 1143 */     CONTROLLER_RS = loadImage("images/ui/controller/" + dirName + "/rs.png");
/* 1144 */     CONTROLLER_RS_UP = loadImage("images/ui/controller/" + dirName + "/rs_up.png");
/* 1145 */     CONTROLLER_RS_DOWN = loadImage("images/ui/controller/" + dirName + "/rs_down.png");
/* 1146 */     CONTROLLER_RS_LEFT = loadImage("images/ui/controller/" + dirName + "/rs_left.png");
/* 1147 */     CONTROLLER_RS_RIGHT = loadImage("images/ui/controller/" + dirName + "/rs_right.png");
/* 1148 */     CONTROLLER_D_UP = loadImage("images/ui/controller/" + dirName + "/up.png");
/* 1149 */     CONTROLLER_D_DOWN = loadImage("images/ui/controller/" + dirName + "/down.png");
/* 1150 */     CONTROLLER_D_LEFT = loadImage("images/ui/controller/" + dirName + "/left.png");
/* 1151 */     CONTROLLER_D_RIGHT = loadImage("images/ui/controller/" + dirName + "/right.png");
/* 1152 */     CONTROLLER_LT = loadImage("images/ui/controller/" + dirName + "/lt.png");
/* 1153 */     CONTROLLER_RT = loadImage("images/ui/controller/" + dirName + "/rt.png");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Texture loadImage(String imgUrl) {
/* 1159 */     assert imgUrl != null : "DO NOT CALL LOAD IMAGE WITH NULL";
/*      */     
/*      */     try {
/* 1162 */       Texture retVal = new Texture(imgUrl);
/* 1163 */       retVal.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/* 1164 */       return retVal;
/* 1165 */     } catch (Exception e) {
/* 1166 */       logger.info("[WARNING] No image at " + imgUrl);
/* 1167 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Texture loadImage(String imgUrl, boolean linearFiltering) {
/* 1174 */     assert imgUrl != null : "DO NOT CALL LOAD IMAGE WITH NULL";
/*      */     
/*      */     try {
/* 1177 */       Texture retVal = new Texture(imgUrl);
/* 1178 */       if (linearFiltering) {
/* 1179 */         retVal.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/*      */       } else {
/* 1181 */         retVal.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
/*      */       } 
/* 1183 */       return retVal;
/* 1184 */     } catch (Exception e) {
/* 1185 */       logger.info("[ERROR] " + e.getMessage());
/* 1186 */       return null;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\ImageMaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */