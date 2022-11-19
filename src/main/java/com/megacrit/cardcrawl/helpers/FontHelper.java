/*      */ package com.megacrit.cardcrawl.helpers;
/*      */ 
/*      */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FontHelper
/*      */ {
/*   33 */   private static final Logger logger = LogManager.getLogger(FontHelper.class.getName());
/*   34 */   private static FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
/*   35 */   private static FreeTypeFontGenerator.FreeTypeBitmapFontData data = new FreeTypeFontGenerator.FreeTypeBitmapFontData();
/*   36 */   private static HashMap<String, FreeTypeFontGenerator> generators = new HashMap<>();
/*   37 */   private static FileHandle fontFile = null;
/*   38 */   private static float fontScale = 1.0F;
/*      */ 
/*      */   
/*   41 */   private static Vector2 rotatedTextTmp = new Vector2(0.0F, 0.0F);
/*   42 */   private static Matrix4 rotatedTextMatrix = new Matrix4();
/*      */   
/*      */   private static final String TINY_NUMBERS_FONT = "font/04b03.ttf";
/*      */   
/*      */   private static final String ENG_DEFAULT_FONT = "font/Kreon-Regular.ttf";
/*      */   
/*      */   private static final String ENG_BOLD_FONT = "font/Kreon-Bold.ttf";
/*      */   
/*      */   private static final String ENG_ITALIC_FONT = "font/ZillaSlab-RegularItalic.otf";
/*      */   
/*      */   private static final String ENG_DRAMATIC_FONT = "font/FeDPrm27C.otf";
/*      */   
/*      */   private static final String ZHS_DEFAULT_FONT = "font/zhs/NotoSansMonoCJKsc-Regular.otf";
/*      */   
/*      */   private static final String ZHS_BOLD_FONT = "font/zhs/SourceHanSerifSC-Bold.otf";
/*      */   
/*      */   private static final String ZHS_ITALIC_FONT = "font/zhs/SourceHanSerifSC-Medium.otf";
/*      */   
/*      */   private static final String ZHT_DEFAULT_FONT = "font/zht/NotoSansCJKtc-Regular.otf";
/*      */   
/*      */   private static final String ZHT_BOLD_FONT = "font/zht/NotoSansCJKtc-Bold.otf";
/*      */   
/*      */   private static final String ZHT_ITALIC_FONT = "font/zht/NotoSansCJKtc-Medium.otf";
/*      */   
/*      */   private static final String EPO_DEFAULT_FONT = "font/epo/Andada-Regular.otf";
/*      */   
/*      */   private static final String EPO_BOLD_FONT = "font/epo/Andada-Bold.otf";
/*      */   
/*      */   private static final String EPO_ITALIC_FONT = "font/epo/Andada-Italic.otf";
/*      */   
/*      */   private static final String GRE_DEFAULT_FONT = "font/gre/Roboto-Regular.ttf";
/*      */   
/*      */   private static final String GRE_BOLD_FONT = "font/gre/Roboto-Bold.ttf";
/*      */   
/*      */   private static final String GRE_ITALIC_FONT = "font/gre/Roboto-Italic.ttf";
/*      */   
/*      */   private static final String JPN_DEFAULT_FONT = "font/jpn/NotoSansCJKjp-Regular.otf";
/*      */   
/*      */   private static final String JPN_BOLD_FONT = "font/jpn/NotoSansCJKjp-Bold.otf";
/*      */   
/*      */   private static final String JPN_ITALIC_FONT = "font/jpn/NotoSansCJKjp-Medium.otf";
/*      */   
/*      */   private static final String KOR_DEFAULT_FONT = "font/kor/GyeonggiCheonnyeonBatangBold.ttf";
/*      */   private static final String KOR_BOLD_FONT = "font/kor/GyeonggiCheonnyeonBatangBold.ttf";
/*      */   private static final String KOR_ITALIC_FONT = "font/kor/GyeonggiCheonnyeonBatangBold.ttf";
/*      */   private static final String RUS_DEFAULT_FONT = "font/rus/FiraSansExtraCondensed-Regular.ttf";
/*      */   private static final String RUS_BOLD_FONT = "font/rus/FiraSansExtraCondensed-Bold.ttf";
/*      */   private static final String RUS_ITALIC_FONT = "font/rus/FiraSansExtraCondensed-Italic.ttf";
/*      */   private static final String SRB_DEFAULT_FONT = "font/srb/InfluBG.otf";
/*      */   private static final String SRB_BOLD_FONT = "font/srb/InfluBG-Bold.otf";
/*      */   private static final String SRB_ITALIC_FONT = "font/srb/InfluBG-Italic.otf";
/*      */   private static final String THA_DEFAULT_FONT = "font/tha/CSChatThaiUI.ttf";
/*      */   private static final String THA_BOLD_FONT = "font/tha/CSChatThaiUI.ttf";
/*      */   private static final String THA_ITALIC_FONT = "font/tha/CSChatThaiUI.ttf";
/*      */   private static final String VIE_DEFAULT_FONT = "font/vie/Grenze-Regular.ttf";
/*      */   private static final String VIE_BOLD_FONT = "font/vie/Grenze-SemiBold.ttf";
/*      */   private static final String VIE_DRAMATIC_FONT = "font/vie/Grenze-Black.ttf";
/*      */   private static final String VIE_ITALIC_FONT = "font/vie/Grenze-RegularItalic.ttf";
/*      */   public static BitmapFont charDescFont;
/*      */   public static BitmapFont charTitleFont;
/*      */   public static BitmapFont cardTitleFont;
/*      */   public static BitmapFont cardTypeFont;
/*      */   public static BitmapFont cardEnergyFont_L;
/*      */   public static BitmapFont cardDescFont_N;
/*      */   public static BitmapFont cardDescFont_L;
/*      */   public static BitmapFont SCP_cardDescFont;
/*      */   public static BitmapFont SCP_cardEnergyFont;
/*      */   public static BitmapFont SCP_cardTitleFont_small;
/*      */   public static BitmapFont SRV_quoteFont;
/*      */   public static BitmapFont losePowerFont;
/*      */   public static BitmapFont energyNumFontRed;
/*      */   public static BitmapFont energyNumFontGreen;
/*      */   public static BitmapFont energyNumFontBlue;
/*      */   public static BitmapFont energyNumFontPurple;
/*      */   public static BitmapFont turnNumFont;
/*      */   public static BitmapFont damageNumberFont;
/*      */   public static BitmapFont buttonLabelFont;
/*      */   public static BitmapFont dungeonTitleFont;
/*      */   public static BitmapFont bannerNameFont;
/*  121 */   private static final float CARD_ENERGY_IMG_WIDTH = 26.0F * Settings.scale;
/*      */ 
/*      */   
/*      */   public static BitmapFont topPanelAmountFont;
/*      */ 
/*      */   
/*      */   public static BitmapFont powerAmountFont;
/*      */ 
/*      */   
/*      */   public static BitmapFont panelNameFont;
/*      */ 
/*      */   
/*      */   public static BitmapFont healthInfoFont;
/*      */ 
/*      */   
/*      */   public static BitmapFont blockInfoFont;
/*      */ 
/*      */   
/*      */   public static BitmapFont topPanelInfoFont;
/*      */ 
/*      */   
/*      */   public static BitmapFont tipHeaderFont;
/*      */ 
/*      */   
/*      */   public static BitmapFont tipBodyFont;
/*      */   
/*      */   public static BitmapFont panelEndTurnFont;
/*      */   
/*      */   public static BitmapFont largeDialogOptionFont;
/*      */   
/*      */   public static BitmapFont smallDialogOptionFont;
/*      */   
/*      */   public static BitmapFont largeCardFont;
/*      */   
/*      */   public static BitmapFont menuBannerFont;
/*      */   
/*      */   public static BitmapFont leaderboardFont;
/*      */   
/*  159 */   public static GlyphLayout layout = new GlyphLayout();
/*      */   
/*      */   private static TextureAtlas.AtlasRegion orb;
/*      */   private static Color color;
/*      */   private static float curWidth;
/*  164 */   private static Matrix4 mx4 = new Matrix4(); private static float curHeight; private static float spaceWidth; private static int currentLine;
/*  165 */   private static StringBuilder newMsg = new StringBuilder("");
/*      */   
/*      */   private static final int TIP_OFFSET_X = 50;
/*      */   
/*      */   private static final float TIP_PADDING = 8.0F;
/*      */   
/*      */   public static void initialize() {
/*  172 */     long startTime = System.currentTimeMillis();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  180 */     generators.clear();
/*  181 */     HashMap<Character, Integer> paramCreator = new HashMap<>();
/*      */     
/*  183 */     switch (Settings.language) {
/*      */       case ZHS:
/*  185 */         fontFile = Gdx.files.internal("font/zhs/NotoSansMonoCJKsc-Regular.otf");
/*      */         break;
/*      */       case ZHT:
/*  188 */         fontFile = Gdx.files.internal("font/zht/NotoSansCJKtc-Regular.otf");
/*      */         break;
/*      */       case EPO:
/*  191 */         fontFile = Gdx.files.internal("font/epo/Andada-Regular.otf");
/*      */         break;
/*      */       case GRE:
/*  194 */         fontFile = Gdx.files.internal("font/gre/Roboto-Regular.ttf");
/*      */         break;
/*      */       case JPN:
/*  197 */         fontFile = Gdx.files.internal("font/jpn/NotoSansCJKjp-Regular.otf");
/*      */         break;
/*      */       case KOR:
/*  200 */         fontFile = Gdx.files.internal("font/kor/GyeonggiCheonnyeonBatangBold.ttf");
/*      */         break;
/*      */       case POL:
/*      */       case RUS:
/*      */       case UKR:
/*  205 */         fontFile = Gdx.files.internal("font/rus/FiraSansExtraCondensed-Regular.ttf");
/*      */         break;
/*      */       case SRP:
/*      */       case SRB:
/*  209 */         fontFile = Gdx.files.internal("font/srb/InfluBG.otf");
/*      */         break;
/*      */       case THA:
/*  212 */         fontFile = Gdx.files.internal("font/tha/CSChatThaiUI.ttf");
/*  213 */         fontScale = 0.95F;
/*      */         break;
/*      */       case VIE:
/*  216 */         fontFile = Gdx.files.internal("font/vie/Grenze-Regular.ttf");
/*      */         break;
/*      */       default:
/*  219 */         fontFile = Gdx.files.internal("font/Kreon-Regular.ttf");
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  225 */     data.xChars = new char[] { '动' };
/*  226 */     data.capChars = new char[] { '动' };
/*      */ 
/*      */     
/*  229 */     param.hinting = FreeTypeFontGenerator.Hinting.Slight;
/*  230 */     param.spaceX = 0;
/*  231 */     param.kerning = true;
/*      */ 
/*      */     
/*  234 */     paramCreator.clear();
/*  235 */     param.borderWidth = 0.0F;
/*  236 */     param.gamma = 0.9F;
/*  237 */     param.borderGamma = 0.9F;
/*  238 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  239 */     param.shadowOffsetX = (int)(4.0F * Settings.scale);
/*  240 */     charDescFont = Settings.isMobile ? prepFont(31.0F, false) : prepFont(30.0F, false);
/*      */     
/*  242 */     param.gamma = 1.8F;
/*  243 */     param.borderGamma = 1.8F;
/*  244 */     param.shadowOffsetX = (int)(6.0F * Settings.scale);
/*  245 */     charTitleFont = prepFont(44.0F, false);
/*      */ 
/*      */     
/*  248 */     param.gamma = 0.9F;
/*  249 */     param.borderGamma = 0.9F;
/*  250 */     param.shadowOffsetX = Math.round(3.0F * Settings.scale);
/*  251 */     param.shadowOffsetY = Math.round(3.0F * Settings.scale);
/*  252 */     param.borderStraight = false;
/*  253 */     param.shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.25F);
/*  254 */     param.borderColor = new Color(0.35F, 0.35F, 0.35F, 1.0F);
/*  255 */     param.borderWidth = 2.0F * Settings.scale;
/*  256 */     cardTitleFont = prepFont(27.0F, true);
/*  257 */     param.borderWidth = 2.25F * Settings.scale;
/*      */ 
/*      */     
/*  260 */     param.borderWidth = 0.0F;
/*  261 */     param.shadowOffsetX = 1;
/*  262 */     param.shadowOffsetY = 1;
/*  263 */     param.spaceX = 0;
/*  264 */     cardDescFont_N = prepFont(24.0F, false);
/*  265 */     cardDescFont_L = prepFont(24.0F, true);
/*      */ 
/*      */     
/*  268 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  269 */     param.shadowOffsetX = Math.round(4.0F * Settings.scale);
/*  270 */     param.shadowOffsetY = Math.round(3.0F * Settings.scale);
/*  271 */     SCP_cardDescFont = prepFont(48.0F, true);
/*  272 */     param.shadowOffsetX = Math.round(6.0F * Settings.scale);
/*  273 */     param.shadowOffsetY = Math.round(6.0F * Settings.scale);
/*  274 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  275 */     param.borderColor = new Color(0.35F, 0.35F, 0.35F, 1.0F);
/*  276 */     param.borderWidth = 4.0F * Settings.scale;
/*  277 */     SCP_cardTitleFont_small = prepFont(46.0F, true);
/*      */ 
/*      */     
/*  280 */     param.borderWidth = 0.0F;
/*  281 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  282 */     param.shadowOffsetX = Math.round(3.0F * Settings.scale);
/*  283 */     param.shadowOffsetY = Math.round(3.0F * Settings.scale);
/*  284 */     panelNameFont = prepFont(34.0F, true);
/*      */ 
/*      */     
/*  287 */     param.shadowOffsetX = (int)(3.0F * Settings.scale);
/*  288 */     param.shadowOffsetY = (int)(3.0F * Settings.scale);
/*  289 */     param.borderColor = new Color(0.67F, 0.06F, 0.22F, 1.0F);
/*  290 */     param.gamma = 0.9F;
/*  291 */     param.borderGamma = 0.9F;
/*  292 */     param.borderColor = new Color(0.4F, 0.1F, 0.1F, 1.0F);
/*  293 */     param.borderWidth = 0.0F;
/*  294 */     tipBodyFont = prepFont(22.0F, false);
/*      */ 
/*      */     
/*  297 */     param.borderColor = new Color(0.1F, 0.1F, 0.1F, 0.5F);
/*  298 */     param.borderWidth = 2.0F * Settings.scale;
/*  299 */     topPanelAmountFont = prepFont(24.0F, false);
/*      */ 
/*      */     
/*  302 */     param.borderColor = Color.valueOf("42514dff");
/*  303 */     param.shadowOffsetX = (int)(4.0F * Settings.scale);
/*  304 */     param.shadowOffsetY = (int)(4.0F * Settings.scale);
/*  305 */     param.borderWidth = 3.0F * Settings.scale;
/*  306 */     panelEndTurnFont = prepFont(26.0F, false);
/*      */ 
/*      */     
/*  309 */     param.spaceX = 0;
/*  310 */     param.borderWidth = 0.0F;
/*  311 */     param.shadowOffsetX = (int)(3.0F * Settings.scale);
/*  312 */     param.shadowOffsetY = (int)(3.0F * Settings.scale);
/*  313 */     largeDialogOptionFont = prepFont(30.0F, false);
/*  314 */     (largeDialogOptionFont.getData()).markupEnabled = false;
/*  315 */     smallDialogOptionFont = prepFont(26.0F, false);
/*  316 */     (smallDialogOptionFont.getData()).markupEnabled = false;
/*      */ 
/*      */     
/*  319 */     param.shadowOffsetX = 0;
/*  320 */     param.shadowOffsetY = 0;
/*  321 */     turnNumFont = prepFont(32.0F, true);
/*      */ 
/*      */     
/*  324 */     param.borderWidth = 4.0F * Settings.scale;
/*  325 */     param.borderColor = new Color(0.3F, 0.3F, 0.3F, 1.0F);
/*  326 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  327 */     param.shadowOffsetX = Math.round(3.0F * Settings.scale);
/*  328 */     param.shadowOffsetY = Math.round(3.0F * Settings.scale);
/*  329 */     losePowerFont = prepFont(36.0F, true);
/*      */ 
/*      */     
/*  332 */     param.borderWidth = 3.0F * Settings.scale;
/*  333 */     param.borderColor = Color.DARK_GRAY;
/*  334 */     damageNumberFont = prepFont(48.0F, true);
/*  335 */     damageNumberFont.getData().setLineHeight((damageNumberFont.getData()).lineHeight * 0.85F);
/*  336 */     param.borderWidth = 0.0F;
/*      */ 
/*      */     
/*  339 */     param.borderWidth = 2.7F * Settings.scale;
/*  340 */     param.shadowOffsetX = (int)(3.0F * Settings.scale);
/*  341 */     param.shadowOffsetY = (int)(3.0F * Settings.scale);
/*  342 */     param.borderColor = new Color(0.45F, 0.1F, 0.12F, 1.0F);
/*  343 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  344 */     healthInfoFont = Settings.isMobile ? prepFont(29.0F, false) : prepFont(22.0F, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  350 */     param.borderWidth = 4.0F * Settings.scale;
/*  351 */     param.spaceX = (int)(-2.5F * Settings.scale);
/*  352 */     param.borderColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  353 */     buttonLabelFont = Settings.isMobile ? prepFont(37.0F, true) : prepFont(32.0F, true);
/*      */     
/*  355 */     param.spaceX = 0;
/*      */ 
/*      */     
/*  358 */     fontScale = 1.0F;
/*  359 */     fontFile = Gdx.files.internal("font/Kreon-Bold.ttf");
/*      */ 
/*      */     
/*  362 */     param.borderStraight = true;
/*  363 */     param.borderWidth = 4.0F * Settings.scale;
/*  364 */     param.borderColor = new Color(0.4F, 0.15F, 0.15F, 1.0F);
/*  365 */     energyNumFontRed = prepFont(36.0F, true);
/*  366 */     param.borderColor = new Color(0.15F, 0.4F, 0.15F, 1.0F);
/*  367 */     energyNumFontGreen = prepFont(36.0F, true);
/*  368 */     param.borderColor = new Color(0.1F, 0.2F, 0.4F, 1.0F);
/*  369 */     energyNumFontBlue = prepFont(36.0F, true);
/*  370 */     param.borderColor = new Color(1595767551);
/*  371 */     energyNumFontPurple = prepFont(36.0F, true);
/*  372 */     param.borderWidth = 4.0F * Settings.scale;
/*  373 */     param.borderColor = new Color(0.3F, 0.3F, 0.3F, 1.0F);
/*  374 */     cardEnergyFont_L = prepFont(38.0F, true);
/*  375 */     param.borderWidth = 8.0F * Settings.scale;
/*  376 */     SCP_cardEnergyFont = prepFont(76.0F, true);
/*      */ 
/*      */     
/*  379 */     param.shadowOffsetX = (int)(2.0F * Settings.scale);
/*  380 */     param.shadowOffsetY = (int)(2.0F * Settings.scale);
/*  381 */     param.borderColor = new Color(0.0F, 0.33F, 0.2F, 0.8F);
/*  382 */     param.borderWidth = 2.7F * Settings.scale;
/*  383 */     param.spaceX = (int)(-0.9F * Settings.scale);
/*  384 */     blockInfoFont = Settings.isMobile ? prepFont(30.0F, false) : prepFont(24.0F, false);
/*      */ 
/*      */     
/*  387 */     switch (Settings.language) {
/*      */       case ZHS:
/*  389 */         fontFile = Gdx.files.internal("font/zhs/SourceHanSerifSC-Bold.otf");
/*      */         break;
/*      */       case ZHT:
/*  392 */         fontFile = Gdx.files.internal("font/zht/NotoSansCJKtc-Bold.otf");
/*      */         break;
/*      */       case EPO:
/*  395 */         fontFile = Gdx.files.internal("font/epo/Andada-Bold.otf");
/*      */         break;
/*      */       case GRE:
/*  398 */         fontFile = Gdx.files.internal("font/gre/Roboto-Bold.ttf");
/*      */         break;
/*      */       case JPN:
/*  401 */         fontFile = Gdx.files.internal("font/jpn/NotoSansCJKjp-Bold.otf");
/*      */         break;
/*      */       case KOR:
/*  404 */         fontFile = Gdx.files.internal("font/kor/GyeonggiCheonnyeonBatangBold.ttf");
/*      */         break;
/*      */       case POL:
/*      */       case RUS:
/*      */       case UKR:
/*  409 */         fontFile = Gdx.files.internal("font/rus/FiraSansExtraCondensed-Bold.ttf");
/*      */         break;
/*      */       case SRP:
/*      */       case SRB:
/*  413 */         fontFile = Gdx.files.internal("font/srb/InfluBG-Bold.otf");
/*      */         break;
/*      */       case THA:
/*  416 */         fontScale = 0.95F;
/*  417 */         fontFile = Gdx.files.internal("font/tha/CSChatThaiUI.ttf");
/*      */         break;
/*      */       case VIE:
/*  420 */         fontFile = Gdx.files.internal("font/vie/Grenze-SemiBold.ttf");
/*      */         break;
/*      */       default:
/*  423 */         fontFile = Gdx.files.internal("font/Kreon-Bold.ttf");
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  428 */     param.gamma = 1.2F;
/*  429 */     param.borderWidth = 0.0F;
/*  430 */     param.shadowOffsetX = 0;
/*  431 */     param.shadowOffsetY = 0;
/*  432 */     if (Settings.WIDTH >= 1600) {
/*  433 */       param.spaceX = -1;
/*      */     }
/*      */     
/*  436 */     cardTypeFont = prepFont(17.0F, true);
/*      */ 
/*      */     
/*  439 */     param.gamma = 1.2F;
/*  440 */     param.borderGamma = 1.2F;
/*  441 */     param.borderWidth = 0.0F;
/*  442 */     param.shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.12F);
/*  443 */     param.shadowOffsetX = (int)(5.0F * Settings.scale);
/*  444 */     param.shadowOffsetY = (int)(4.0F * Settings.scale);
/*  445 */     menuBannerFont = prepFont(38.0F, true);
/*  446 */     param.characters = "?";
/*  447 */     param.shadowOffsetX = (int)(15.0F * Settings.scale);
/*  448 */     param.shadowOffsetY = (int)(12.0F * Settings.scale);
/*  449 */     largeCardFont = prepFont(120.0F, true);
/*      */ 
/*      */     
/*  452 */     param.shadowOffsetX = 2;
/*  453 */     param.shadowOffsetY = 2;
/*  454 */     param.shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.33F);
/*  455 */     param.gamma = 2.0F;
/*  456 */     param.borderGamma = 2.0F;
/*  457 */     param.borderStraight = true;
/*  458 */     param.borderColor = Color.DARK_GRAY;
/*  459 */     param.borderWidth = 2.0F * Settings.scale;
/*      */     
/*  461 */     param.shadowOffsetX = 1;
/*  462 */     param.shadowOffsetY = 1;
/*  463 */     tipHeaderFont = prepFont(23.0F, false);
/*  464 */     param.shadowOffsetX = 2;
/*  465 */     param.shadowOffsetY = 2;
/*  466 */     topPanelInfoFont = prepFont(26.0F, false);
/*  467 */     param.spaceX = 0;
/*  468 */     param.gamma = 0.9F;
/*  469 */     param.borderGamma = 0.9F;
/*  470 */     param.borderWidth = 0.0F;
/*      */ 
/*      */     
/*  473 */     fontScale = 1.0F;
/*  474 */     fontFile = Gdx.files.internal("font/04b03.ttf");
/*  475 */     param.borderWidth = 2.0F * Settings.scale;
/*  476 */     powerAmountFont = Settings.isMobile ? prepFont(20.0F, false) : prepFont(16.0F, false);
/*      */ 
/*      */     
/*  479 */     switch (Settings.language) {
/*      */       case ZHS:
/*  481 */         fontFile = Gdx.files.internal("font/zhs/SourceHanSerifSC-Bold.otf");
/*      */         break;
/*      */       case ZHT:
/*  484 */         fontFile = Gdx.files.internal("font/zht/NotoSansCJKtc-Bold.otf");
/*      */         break;
/*      */       case EPO:
/*  487 */         fontFile = Gdx.files.internal("font/epo/Andada-Bold.otf");
/*      */         break;
/*      */       case GRE:
/*  490 */         fontFile = Gdx.files.internal("font/gre/Roboto-Bold.ttf");
/*      */         break;
/*      */       case JPN:
/*  493 */         fontFile = Gdx.files.internal("font/jpn/NotoSansCJKjp-Bold.otf");
/*      */         break;
/*      */       case KOR:
/*  496 */         fontFile = Gdx.files.internal("font/kor/GyeonggiCheonnyeonBatangBold.ttf");
/*      */         break;
/*      */       case POL:
/*      */       case RUS:
/*      */       case UKR:
/*  501 */         fontFile = Gdx.files.internal("font/rus/FiraSansExtraCondensed-Bold.ttf");
/*      */         break;
/*      */       case SRP:
/*      */       case SRB:
/*  505 */         fontFile = Gdx.files.internal("font/srb/InfluBG-Bold.otf");
/*      */         break;
/*      */       case THA:
/*  508 */         fontScale = 0.95F;
/*  509 */         fontFile = Gdx.files.internal("font/tha/CSChatThaiUI.ttf");
/*      */         break;
/*      */       case VIE:
/*  512 */         fontFile = Gdx.files.internal("font/vie/Grenze-Black.ttf");
/*      */         break;
/*      */       default:
/*  515 */         fontFile = Gdx.files.internal("font/FeDPrm27C.otf");
/*      */         break;
/*      */     } 
/*      */     
/*  519 */     param.gamma = 0.5F;
/*  520 */     param.borderGamma = 0.5F;
/*  521 */     param.shadowOffsetX = 0;
/*  522 */     param.shadowOffsetY = 0;
/*  523 */     param.borderWidth = 6.0F * Settings.scale;
/*  524 */     param.borderColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/*  525 */     param.spaceX = (int)(-5.0F * Settings.scale);
/*  526 */     dungeonTitleFont = prepFont(115.0F, true);
/*  527 */     dungeonTitleFont.getData().setScale(1.25F);
/*  528 */     param.borderWidth = 4.0F * Settings.scale;
/*  529 */     param.borderColor = new Color(0.0F, 0.0F, 0.0F, 0.33F);
/*  530 */     param.spaceX = (int)(-2.0F * Settings.scale);
/*  531 */     bannerNameFont = prepFont(72.0F, true);
/*      */ 
/*      */     
/*  534 */     fontScale = 1.0F;
/*  535 */     switch (Settings.language) {
/*      */       case ZHS:
/*  537 */         fontFile = Gdx.files.internal("font/zhs/SourceHanSerifSC-Medium.otf");
/*      */         break;
/*      */       case ZHT:
/*  540 */         fontFile = Gdx.files.internal("font/zht/NotoSansCJKtc-Medium.otf");
/*      */         break;
/*      */       case EPO:
/*  543 */         fontFile = Gdx.files.internal("font/epo/Andada-Italic.otf");
/*      */         break;
/*      */       case GRE:
/*  546 */         fontFile = Gdx.files.internal("font/gre/Roboto-Italic.ttf");
/*      */         break;
/*      */       case JPN:
/*  549 */         fontFile = Gdx.files.internal("font/jpn/NotoSansCJKjp-Medium.otf");
/*      */         break;
/*      */       case KOR:
/*  552 */         fontFile = Gdx.files.internal("font/kor/GyeonggiCheonnyeonBatangBold.ttf");
/*      */         break;
/*      */       case POL:
/*      */       case RUS:
/*      */       case UKR:
/*  557 */         fontFile = Gdx.files.internal("font/rus/FiraSansExtraCondensed-Italic.ttf");
/*      */         break;
/*      */       case SRP:
/*      */       case SRB:
/*  561 */         fontFile = Gdx.files.internal("font/srb/InfluBG-Italic.otf");
/*      */         break;
/*      */       case THA:
/*  564 */         fontScale = 0.95F;
/*  565 */         fontFile = Gdx.files.internal("font/tha/CSChatThaiUI.ttf");
/*      */         break;
/*      */       case VIE:
/*  568 */         fontFile = Gdx.files.internal("font/vie/Grenze-RegularItalic.ttf");
/*      */         break;
/*      */       default:
/*  571 */         fontFile = Gdx.files.internal("font/ZillaSlab-RegularItalic.otf");
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  576 */     param.shadowOffsetX = 0;
/*  577 */     param.shadowOffsetY = 0;
/*  578 */     param.borderWidth = 0.0F;
/*  579 */     param.shadowOffsetX = Math.round(2.0F * Settings.scale);
/*  580 */     param.shadowOffsetY = Math.round(2.0F * Settings.scale);
/*  581 */     param.spaceX = 0;
/*  582 */     SRV_quoteFont = prepFont(28.0F, false);
/*      */ 
/*      */     
/*  585 */     fontScale = 1.0F;
/*  586 */     fontFile = Gdx.files.internal("font/zhs/NotoSansMonoCJKsc-Regular.otf");
/*  587 */     leaderboardFont = prepFont(30.0F, false);
/*      */     
/*  589 */     logger.info("Font load time: " + (System.currentTimeMillis() - startTime) + "ms");
/*      */   }
/*      */   
/*      */   public static void ClearSCPFontTextures() {
/*  593 */     System.out.println("Clearing SCP font textures...");
/*  594 */     SCP_cardDescFont.dispose();
/*  595 */     SCP_cardEnergyFont.dispose();
/*  596 */     SCP_cardTitleFont_small.dispose();
/*      */     
/*  598 */     fontScale = (Settings.language == Settings.GameLanguage.THA) ? 0.95F : 1.0F;
/*  599 */     fontFile = SCP_cardDescFont.getData().getFontFile();
/*  600 */     param.borderWidth = 0.0F;
/*  601 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  602 */     param.shadowOffsetX = Math.round(4.0F * Settings.scale);
/*  603 */     param.shadowOffsetY = Math.round(3.0F * Settings.scale);
/*  604 */     SCP_cardDescFont = prepFont(48.0F, true);
/*      */     
/*  606 */     fontScale = 1.0F;
/*  607 */     param.shadowOffsetX = Math.round(6.0F * Settings.scale);
/*  608 */     param.shadowOffsetY = Math.round(6.0F * Settings.scale);
/*  609 */     param.borderColor = new Color(0.35F, 0.35F, 0.35F, 1.0F);
/*  610 */     param.borderWidth = 4.0F * Settings.scale;
/*  611 */     SCP_cardTitleFont_small = prepFont(46.0F, true);
/*      */     
/*  613 */     param.borderStraight = true;
/*  614 */     param.borderColor = new Color(0.3F, 0.3F, 0.3F, 1.0F);
/*  615 */     param.borderWidth = 8.0F * Settings.scale;
/*  616 */     SCP_cardEnergyFont = prepFont(76.0F, true);
/*      */   }
/*      */   
/*      */   public static void ClearSRVFontTextures() {
/*  620 */     System.out.println("Clearing SRV font textures...");
/*  621 */     SRV_quoteFont.dispose();
/*  622 */     SCP_cardDescFont.dispose();
/*      */     
/*  624 */     fontScale = (Settings.language == Settings.GameLanguage.THA) ? 0.95F : 1.0F;
/*  625 */     fontFile = SCP_cardDescFont.getData().getFontFile();
/*  626 */     param.borderWidth = 0.0F;
/*  627 */     param.shadowColor = Settings.QUARTER_TRANSPARENT_BLACK_COLOR;
/*  628 */     param.shadowOffsetX = Math.round(4.0F * Settings.scale);
/*  629 */     param.shadowOffsetY = Math.round(3.0F * Settings.scale);
/*  630 */     SCP_cardDescFont = prepFont(48.0F, true);
/*      */     
/*  632 */     fontScale = 1.0F;
/*  633 */     fontFile = SRV_quoteFont.getData().getFontFile();
/*  634 */     param.shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.33F);
/*  635 */     param.shadowOffsetX = Math.round(2.0F * Settings.scale);
/*  636 */     param.shadowOffsetY = Math.round(2.0F * Settings.scale);
/*  637 */     param.spaceX = 0;
/*  638 */     SRV_quoteFont = prepFont(28.0F, false);
/*      */   }
/*      */   
/*      */   public static void ClearLeaderboardFontTextures() {
/*  642 */     System.out.println("Clearing leaderboard font textures...");
/*  643 */     leaderboardFont.dispose();
/*  644 */     fontScale = 1.0F;
/*  645 */     param.shadowOffsetX = 0;
/*  646 */     param.shadowOffsetY = 0;
/*  647 */     param.borderWidth = 0.0F;
/*  648 */     param.spaceX = 0;
/*  649 */     fontFile = leaderboardFont.getData().getFontFile();
/*  650 */     leaderboardFont = prepFont(30.0F, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BitmapFont prepFont(float size, boolean isLinearFiltering) {
/*      */     FreeTypeFontGenerator g;
/*  662 */     if (generators.containsKey(fontFile.path())) {
/*      */       
/*  664 */       g = generators.get(fontFile.path());
/*      */     } else {
/*      */       
/*  667 */       g = new FreeTypeFontGenerator(fontFile);
/*  668 */       generators.put(fontFile.path(), g);
/*      */     } 
/*      */     
/*  671 */     if (Settings.BIG_TEXT_MODE) {
/*  672 */       size *= 1.2F;
/*      */     }
/*      */     
/*  675 */     return prepFont(g, size, isLinearFiltering);
/*      */   }
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
/*      */   
/*      */   private static BitmapFont prepFont(FreeTypeFontGenerator g, float size, boolean isLinearFiltering) {
/*  689 */     FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
/*      */ 
/*      */     
/*  692 */     p.characters = "";
/*  693 */     p.incremental = true;
/*  694 */     p.size = Math.round(size * fontScale * Settings.scale);
/*  695 */     p.gamma = param.gamma;
/*  696 */     p.spaceX = param.spaceX;
/*  697 */     p.spaceY = param.spaceY;
/*      */ 
/*      */     
/*  700 */     p.borderColor = param.borderColor;
/*  701 */     p.borderStraight = param.borderStraight;
/*  702 */     p.borderWidth = param.borderWidth;
/*  703 */     p.borderGamma = param.borderGamma;
/*      */ 
/*      */     
/*  706 */     p.shadowColor = param.shadowColor;
/*  707 */     p.shadowOffsetX = param.shadowOffsetX;
/*  708 */     p.shadowOffsetY = param.shadowOffsetY;
/*      */ 
/*      */     
/*  711 */     if (isLinearFiltering) {
/*  712 */       p.minFilter = Texture.TextureFilter.Linear;
/*  713 */       p.magFilter = Texture.TextureFilter.Linear;
/*      */     } else {
/*  715 */       p.minFilter = Texture.TextureFilter.Nearest;
/*  716 */       p.magFilter = Texture.TextureFilter.MipMapLinearNearest;
/*      */     } 
/*  718 */     g.scaleForPixelHeight(p.size);
/*      */     
/*  720 */     BitmapFont font = g.generateFont(p);
/*  721 */     font.setUseIntegerPositions(!isLinearFiltering);
/*  722 */     (font.getData()).markupEnabled = true;
/*      */     
/*  724 */     if (LocalizedStrings.break_chars != null) {
/*  725 */       (font.getData()).breakChars = LocalizedStrings.break_chars.toCharArray();
/*      */     }
/*      */     
/*  728 */     (font.getData()).fontFile = fontFile;
/*  729 */     return font;
/*      */   }
/*      */   
/*      */   public static void renderTipLeft(SpriteBatch sb, String msg) {
/*  733 */     layout.setText(cardDescFont_N, msg);
/*  734 */     sb.setColor(Color.BLACK);
/*  735 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, InputHelper.mX - layout.width - 16.0F - 12.5F, InputHelper.mY - layout.height, layout.width + 16.0F, layout.height + 16.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  741 */     renderFont(sb, cardDescFont_N, msg, InputHelper.mX - layout.width - 8.0F - 12.0F, InputHelper.mY + 8.0F, Color.WHITE);
/*      */   }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFont(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/*  761 */     font.setColor(c);
/*  762 */     font.draw((Batch)sb, msg, x, y);
/*      */   }
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderRotatedText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float offsetX, float offsetY, float angle, boolean roundY, Color c) {
/*  789 */     if (roundY) {
/*  790 */       y = Math.round(y) + 0.25F;
/*      */     }
/*      */     
/*  793 */     if ((font.getData()).scaleX == 1.0F) {
/*  794 */       x = MathUtils.round(x);
/*  795 */       y = MathUtils.round(y);
/*  796 */       offsetX = MathUtils.round(offsetX);
/*  797 */       offsetY = MathUtils.round(offsetY);
/*      */     } 
/*      */     
/*  800 */     mx4.setToRotation(0.0F, 0.0F, 1.0F, angle);
/*  801 */     rotatedTextTmp.x = offsetX;
/*  802 */     rotatedTextTmp.y = offsetY;
/*  803 */     rotatedTextTmp.rotate(angle);
/*  804 */     mx4.trn(x + rotatedTextTmp.x, y + rotatedTextTmp.y, 0.0F);
/*  805 */     sb.end();
/*  806 */     sb.setTransformMatrix(mx4);
/*  807 */     sb.begin();
/*      */     
/*  809 */     font.setColor(c);
/*  810 */     layout.setText(font, msg);
/*  811 */     font.draw((Batch)sb, msg, -layout.width / 2.0F, layout.height / 2.0F);
/*      */     
/*  813 */     sb.end();
/*  814 */     sb.setTransformMatrix(rotatedTextMatrix);
/*  815 */     sb.begin();
/*      */   }
/*      */   
/*      */   public static void renderWrappedText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float width) {
/*  819 */     renderWrappedText(sb, font, msg, x, y, width, Color.WHITE, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderWrappedText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float width, float scale) {
/*  830 */     renderWrappedText(sb, font, msg, x, y, width, Color.WHITE, scale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderWrappedText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float width, Color c, float scale) {
/*  842 */     font.getData().setScale(scale);
/*  843 */     font.setColor(c);
/*  844 */     layout.setText(font, msg, Color.WHITE, width, 1, true);
/*  845 */     font.draw((Batch)sb, msg, x - width / 2.0F, y + layout.height / 2.0F * scale, width, 1, true);
/*  846 */     font.getData().setScale(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontLeftDownAligned(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/*  856 */     layout.setText(font, msg);
/*  857 */     renderFont(sb, font, msg, x, y + layout.height, c);
/*      */   }
/*      */   
/*      */   public static void renderFontRightToLeft(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/*  861 */     layout.setText(font, msg, c, 1.0F, 18, false);
/*  862 */     font.setColor(c);
/*  863 */     font.draw((Batch)sb, msg, x - layout.width, y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontRightTopAligned(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/*  873 */     layout.setText(font, msg);
/*  874 */     renderFont(sb, font, msg, x - layout.width, y, c);
/*      */   }
/*      */   
/*      */   public static void renderFontRightAligned(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/*  878 */     layout.setText(font, msg);
/*  879 */     renderFont(sb, font, msg, x - layout.width, y + layout.height / 2.0F, c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontRightTopAligned(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float scale, Color c) {
/*  890 */     font.getData().setScale(1.0F);
/*  891 */     layout.setText(font, msg);
/*  892 */     float offsetX = layout.width / 2.0F;
/*  893 */     float offsetY = layout.height;
/*  894 */     font.getData().setScale(scale);
/*  895 */     layout.setText(font, msg);
/*  896 */     renderFont(sb, font, msg, x - layout.width / 2.0F - offsetX, y + layout.height / 2.0F + offsetY, c);
/*      */   }
/*      */   
/*      */   public static void renderSmartText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color baseColor) {
/*  900 */     renderSmartText(sb, font, msg, x, y, Float.MAX_VALUE, font.getLineHeight(), baseColor);
/*      */   }
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
/*      */   
/*      */   public static void renderSmartText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float lineWidth, float lineSpacing, Color baseColor) {
/*  924 */     if (msg == null) {
/*      */       return;
/*      */     }
/*      */     
/*  928 */     if (Settings.lineBreakViaCharacter && (font.getData()).markupEnabled) {
/*  929 */       exampleNonWordWrappedText(sb, font, msg, x, y, baseColor, lineWidth, lineSpacing);
/*      */       
/*      */       return;
/*      */     } 
/*  933 */     curWidth = 0.0F;
/*  934 */     curHeight = 0.0F;
/*  935 */     layout.setText(font, " ");
/*  936 */     spaceWidth = layout.width;
/*      */     
/*  938 */     for (String word : msg.split(" ")) {
/*  939 */       if (word.equals("NL")) {
/*  940 */         curWidth = 0.0F;
/*  941 */         curHeight -= lineSpacing;
/*      */       }
/*  943 */       else if (word.equals("TAB")) {
/*  944 */         curWidth += spaceWidth * 5.0F;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  949 */         orb = identifyOrb(word);
/*  950 */         if (orb == null) {
/*      */ 
/*      */           
/*  953 */           color = identifyColor(word).cpy();
/*  954 */           if (!color.equals(Color.WHITE)) {
/*  955 */             word = word.substring(2, word.length());
/*  956 */             color.a = baseColor.a;
/*  957 */             font.setColor(color);
/*      */           } else {
/*  959 */             font.setColor(baseColor);
/*      */           } 
/*      */           
/*  962 */           layout.setText(font, word);
/*      */ 
/*      */           
/*  965 */           if (curWidth + layout.width > lineWidth) {
/*  966 */             curHeight -= lineSpacing;
/*  967 */             font.draw((Batch)sb, word, x, y + curHeight);
/*  968 */             curWidth = layout.width + spaceWidth;
/*      */           } else {
/*  970 */             font.draw((Batch)sb, word, x + curWidth, y + curHeight);
/*  971 */             curWidth += layout.width + spaceWidth;
/*      */           } 
/*      */         } else {
/*  974 */           sb.setColor(new Color(1.0F, 1.0F, 1.0F, baseColor.a));
/*      */ 
/*      */           
/*  977 */           if (curWidth + CARD_ENERGY_IMG_WIDTH > lineWidth) {
/*  978 */             curHeight -= lineSpacing;
/*  979 */             sb.draw((TextureRegion)orb, x - orb.packedWidth / 2.0F + 13.0F * Settings.scale, y + curHeight - orb.packedHeight / 2.0F - 8.0F * Settings.scale, orb.packedWidth / 2.0F, orb.packedHeight / 2.0F, orb.packedWidth, orb.packedHeight, Settings.scale, Settings.scale, 0.0F);
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
/*  990 */             curWidth = CARD_ENERGY_IMG_WIDTH + spaceWidth;
/*      */           } else {
/*  992 */             sb.draw((TextureRegion)orb, x + curWidth - orb.packedWidth / 2.0F + 13.0F * Settings.scale, y + curHeight - orb.packedHeight / 2.0F - 8.0F * Settings.scale, orb.packedWidth / 2.0F, orb.packedHeight / 2.0F, orb.packedWidth, orb.packedHeight, Settings.scale, Settings.scale, 0.0F);
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
/* 1003 */             curWidth += CARD_ENERGY_IMG_WIDTH + spaceWidth;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1008 */     layout.setText(font, msg);
/*      */   }
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
/*      */   
/*      */   public static void renderSmartText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float lineWidth, float lineSpacing, Color baseColor, float scale) {
/* 1022 */     BitmapFont.BitmapFontData data = font.getData();
/* 1023 */     float prevScale = data.scaleX;
/* 1024 */     data.setScale(scale);
/* 1025 */     renderSmartText(sb, font, msg, x, y, lineWidth, lineSpacing, baseColor);
/* 1026 */     data.setScale(prevScale);
/*      */   }
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
/*      */   public static float getSmartHeight(BitmapFont font, String msg, float lineWidth, float lineSpacing) {
/* 1039 */     if (msg == null) {
/* 1040 */       return 0.0F;
/*      */     }
/*      */     
/* 1043 */     if (Settings.lineBreakViaCharacter) {
/* 1044 */       return -getHeightForCharLineBreak(font, msg, lineWidth, lineSpacing);
/*      */     }
/*      */     
/* 1047 */     curWidth = 0.0F;
/* 1048 */     curHeight = 0.0F;
/* 1049 */     layout.setText(font, " ");
/* 1050 */     spaceWidth = layout.width;
/*      */     
/* 1052 */     for (String word : msg.split(" ")) {
/* 1053 */       if (word.equals("NL")) {
/* 1054 */         curWidth = 0.0F;
/* 1055 */         curHeight -= lineSpacing;
/*      */       }
/* 1057 */       else if (word.equals("TAB")) {
/* 1058 */         curWidth += spaceWidth * 5.0F;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1063 */         orb = identifyOrb(word);
/* 1064 */         if (orb == null) {
/* 1065 */           if (!identifyColor(word).equals(Color.WHITE)) {
/* 1066 */             word = word.substring(2, word.length());
/*      */           }
/*      */           
/* 1069 */           layout.setText(font, word);
/*      */ 
/*      */           
/* 1072 */           if (curWidth + layout.width > lineWidth) {
/* 1073 */             curHeight -= lineSpacing;
/* 1074 */             curWidth = layout.width + spaceWidth;
/*      */           } else {
/* 1076 */             curWidth += layout.width + spaceWidth;
/*      */           }
/*      */         
/*      */         }
/* 1080 */         else if (curWidth + CARD_ENERGY_IMG_WIDTH > lineWidth) {
/* 1081 */           curHeight -= lineSpacing;
/* 1082 */           curWidth = CARD_ENERGY_IMG_WIDTH + spaceWidth;
/*      */         } else {
/* 1084 */           curWidth += CARD_ENERGY_IMG_WIDTH + spaceWidth;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1090 */     return curHeight;
/*      */   }
/*      */   
/*      */   private static float getHeightForCharLineBreak(BitmapFont font, String msg, float lineWidth, float lineSpacing) {
/* 1094 */     newMsg.setLength(0);
/* 1095 */     for (String word : msg.split(" ")) {
/* 1096 */       if (word.equals("NL")) {
/* 1097 */         newMsg.append("\n");
/*      */       }
/* 1099 */       else if (word.length() > 0 && word.charAt(0) == '#') {
/* 1100 */         newMsg.append(word.substring(2));
/*      */       } else {
/* 1102 */         newMsg.append(word);
/*      */       } 
/*      */     } 
/*      */     
/* 1106 */     msg = newMsg.toString();
/*      */     
/* 1108 */     if (msg != null && msg.length() > 0) {
/* 1109 */       layout.setText(font, msg, Color.WHITE, lineWidth, -1, true);
/*      */     }
/* 1111 */     return layout.height - 16.0F * Settings.scale;
/*      */   }
/*      */   
/*      */   public static float getHeight(BitmapFont font) {
/* 1115 */     layout.setText(font, "gl0!");
/* 1116 */     return layout.height;
/*      */   }
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
/*      */   public static float getSmartWidth(BitmapFont font, String msg, float lineWidth, float lineSpacing) {
/* 1129 */     curWidth = 0.0F;
/* 1130 */     layout.setText(font, " ");
/* 1131 */     spaceWidth = layout.width;
/*      */     
/* 1133 */     for (String word : msg.split(" ")) {
/* 1134 */       if (word.equals("NL")) {
/* 1135 */         curWidth = 0.0F;
/*      */       }
/* 1137 */       else if (word.equals("TAB")) {
/* 1138 */         curWidth += spaceWidth * 5.0F;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1143 */         orb = identifyOrb(word);
/* 1144 */         if (orb == null) {
/* 1145 */           if (!identifyColor(word).equals(Color.WHITE)) {
/* 1146 */             word = word.substring(2, word.length());
/*      */           }
/*      */           
/* 1149 */           layout.setText(font, word);
/*      */ 
/*      */           
/* 1152 */           if (curWidth + layout.width > lineWidth) {
/* 1153 */             curWidth = layout.width + spaceWidth;
/*      */           } else {
/* 1155 */             curWidth += layout.width + spaceWidth;
/*      */           }
/*      */         
/*      */         }
/* 1159 */         else if (curWidth + CARD_ENERGY_IMG_WIDTH > lineWidth) {
/* 1160 */           curWidth = CARD_ENERGY_IMG_WIDTH + spaceWidth;
/*      */         } else {
/* 1162 */           curWidth += CARD_ENERGY_IMG_WIDTH + spaceWidth;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1167 */     return curWidth;
/*      */   }
/*      */   
/*      */   public static float getSmartWidth(BitmapFont font, String msg, float lineWidth, float lineSpacing, float scale) {
/* 1171 */     BitmapFont.BitmapFontData data = font.getData();
/* 1172 */     float prevScale = data.scaleX;
/* 1173 */     data.setScale(scale);
/* 1174 */     float retVal = getSmartWidth(font, msg, lineWidth, lineSpacing);
/* 1175 */     data.setScale(prevScale);
/* 1176 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlas.AtlasRegion identifyOrb(String word) {
/* 1186 */     switch (word) {
/*      */       case "[E]":
/* 1188 */         return (AbstractDungeon.player != null) ? AbstractDungeon.player.getOrb() : AbstractCard.orb_red;
/*      */       case "[R]":
/* 1190 */         return AbstractCard.orb_red;
/*      */       case "[G]":
/* 1192 */         return AbstractCard.orb_green;
/*      */       case "[B]":
/* 1194 */         return AbstractCard.orb_blue;
/*      */       case "[W]":
/* 1196 */         return AbstractCard.orb_purple;
/*      */       case "[C]":
/* 1198 */         return AbstractCard.orb_card;
/*      */       case "[P]":
/* 1200 */         return AbstractCard.orb_potion;
/*      */       case "[T]":
/* 1202 */         return AbstractCard.orb_relic;
/*      */       case "[S]":
/* 1204 */         return AbstractCard.orb_special;
/*      */     } 
/* 1206 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Color identifyColor(String word) {
/* 1217 */     if (word.length() > 0 && word.charAt(0) == '#') {
/* 1218 */       switch (word.charAt(1)) {
/*      */         case 'r':
/* 1220 */           return Settings.RED_TEXT_COLOR;
/*      */         case 'g':
/* 1222 */           return Settings.GREEN_TEXT_COLOR;
/*      */         case 'b':
/* 1224 */           return Settings.BLUE_TEXT_COLOR;
/*      */         case 'y':
/* 1226 */           return Settings.GOLD_COLOR;
/*      */         case 'p':
/* 1228 */           return Settings.PURPLE_COLOR;
/*      */       } 
/* 1230 */       return Color.WHITE;
/*      */     } 
/*      */     
/* 1233 */     return Color.WHITE;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderDeckViewTip(SpriteBatch sb, String msg, float y, Color color) {
/* 1238 */     layout.setText(cardDescFont_N, msg);
/* 1239 */     sb.setColor(Settings.TWO_THIRDS_TRANSPARENT_BLACK_COLOR);
/* 1240 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, Settings.WIDTH / 2.0F - layout.width / 2.0F - 12.0F * Settings.scale, y - 24.0F * Settings.scale, layout.width + 24.0F * Settings.scale, 48.0F * Settings.scale);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1247 */     renderFontCentered(sb, cardDescFont_N, msg, Settings.WIDTH / 2.0F, y, color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontLeftTopAligned(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 1257 */     layout.setText(font, msg);
/* 1258 */     renderFont(sb, font, msg, x, y, c);
/*      */   }
/*      */   
/*      */   public static void renderFontCentered(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 1262 */     layout.setText(font, msg);
/* 1263 */     renderFont(sb, font, msg, x - layout.width / 2.0F, y + layout.height / 2.0F, c);
/*      */   }
/*      */   
/*      */   public static void renderFontLeft(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 1267 */     layout.setText(font, msg);
/* 1268 */     renderFont(sb, font, msg, x, y + layout.height / 2.0F, c);
/*      */   }
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
/*      */   public static void exampleNonWordWrappedText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c, float widthMax, float lineSpacing) {
/* 1281 */     layout.setText(font, msg, Color.WHITE, 0.0F, -1, false);
/* 1282 */     currentLine = 0;
/* 1283 */     curWidth = 0.0F;
/*      */     
/* 1285 */     for (String word : msg.split(" ")) {
/* 1286 */       if (word.length() != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1291 */         if (word.equals("NL")) {
/* 1292 */           curWidth = 0.0F;
/* 1293 */           currentLine++;
/*      */         
/*      */         }
/* 1296 */         else if (word.equals("TAB")) {
/* 1297 */           layout.setText(font, word);
/* 1298 */           curWidth += layout.width;
/*      */ 
/*      */         
/*      */         }
/* 1302 */         else if (word.charAt(0) == '[') {
/* 1303 */           orb = identifyOrb(word);
/*      */           
/* 1305 */           if (orb != null) {
/* 1306 */             sb.setColor(new Color(1.0F, 1.0F, 1.0F, c.a));
/* 1307 */             if (CARD_ENERGY_IMG_WIDTH <= widthMax * 2.0F) {
/* 1308 */               if (curWidth + CARD_ENERGY_IMG_WIDTH > widthMax) {
/* 1309 */                 sb.draw((TextureRegion)orb, x - orb.packedWidth / 2.0F + 14.0F * Settings.scale, y - currentLine * lineSpacing - orb.packedHeight / 2.0F - 38.0F * Settings.scale, orb.packedWidth / 2.0F, orb.packedHeight / 2.0F, orb.packedWidth, orb.packedHeight, Settings.scale, Settings.scale, 0.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               }
/*      */               else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1321 */                 sb.draw((TextureRegion)orb, x + curWidth - orb.packedWidth / 2.0F + 14.0F * Settings.scale, y - currentLine * lineSpacing - orb.packedHeight / 2.0F - 8.0F * Settings.scale, orb.packedWidth / 2.0F, orb.packedHeight / 2.0F, orb.packedWidth, orb.packedHeight, Settings.scale, Settings.scale, 0.0F);
/*      */               } 
/*      */             }
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
/* 1334 */             curWidth += CARD_ENERGY_IMG_WIDTH;
/* 1335 */             if (curWidth > widthMax) {
/* 1336 */               curWidth = CARD_ENERGY_IMG_WIDTH;
/* 1337 */               currentLine++;
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1343 */         else if (word.charAt(0) == '#') {
/* 1344 */           layout.setText(font, word.substring(2));
/* 1345 */           switch (word.charAt(1)) {
/*      */             case 'r':
/* 1347 */               word = "[#ff6563]" + word.substring(2) + "[]";
/*      */               break;
/*      */             case 'g':
/* 1350 */               word = "[#7fff00]" + word.substring(2) + "[]";
/*      */               break;
/*      */             case 'b':
/* 1353 */               word = "[#87ceeb]" + word.substring(2) + "[]";
/*      */               break;
/*      */             case 'y':
/* 1356 */               word = "[#efc851]" + word.substring(2) + "[]";
/*      */               break;
/*      */             case 'p':
/* 1359 */               word = "[#0e82ee]" + word.substring(2) + "[]";
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1365 */           curWidth += layout.width;
/* 1366 */           if (curWidth > widthMax) {
/* 1367 */             curWidth = 0.0F;
/* 1368 */             currentLine++;
/* 1369 */             font.draw((Batch)sb, word, x + curWidth, y - lineSpacing * currentLine);
/* 1370 */             curWidth = layout.width;
/*      */           } else {
/* 1372 */             font.draw((Batch)sb, word, x + curWidth - layout.width, y - lineSpacing * currentLine);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1377 */           font.setColor(c);
/* 1378 */           for (int i = 0; i < word.length(); i++) {
/* 1379 */             String j = Character.toString(word.charAt(i));
/* 1380 */             layout.setText(font, j);
/* 1381 */             curWidth += layout.width;
/* 1382 */             if (curWidth > widthMax && !j.equals(LocalizedStrings.PERIOD)) {
/* 1383 */               curWidth = layout.width;
/* 1384 */               currentLine++;
/*      */             } 
/* 1386 */             font.draw((Batch)sb, j, x + curWidth - layout.width, y - lineSpacing * currentLine);
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontCenteredTopAligned(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 1400 */     layout.setText(font, "lL");
/* 1401 */     font.setColor(c);
/* 1402 */     font.draw((Batch)sb, msg, x, y + layout.height / 2.0F, 0.0F, 1, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontCentered(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c, float scale) {
/* 1413 */     font.getData().setScale(scale);
/* 1414 */     layout.setText(font, msg);
/* 1415 */     renderFont(sb, font, msg, x - layout.width / 2.0F, y + layout.height / 2.0F, c);
/* 1416 */     font.getData().setScale(1.0F);
/*      */   }
/*      */   
/*      */   public static void renderFontCentered(SpriteBatch sb, BitmapFont font, String msg, float x, float y) {
/* 1420 */     layout.setText(font, msg);
/* 1421 */     renderFont(sb, font, msg, x - layout.width / 2.0F, y + layout.height / 2.0F, Color.WHITE);
/*      */   }
/*      */   
/*      */   public static void renderFontCenteredWidth(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 1425 */     layout.setText(font, msg);
/* 1426 */     renderFont(sb, font, msg, x - layout.width / 2.0F, y, c);
/*      */   }
/*      */   
/*      */   public static void renderFontCenteredWidth(SpriteBatch sb, BitmapFont font, String msg, float x, float y) {
/* 1430 */     layout.setText(font, msg);
/* 1431 */     renderFont(sb, font, msg, x - layout.width / 2.0F, y, Color.WHITE);
/*      */   }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontCenteredHeight(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float lineWidth, Color c) {
/* 1453 */     layout.setText(font, msg, c, lineWidth, 1, true);
/* 1454 */     font.setColor(c);
/* 1455 */     font.draw((Batch)sb, msg, x, y + layout.height / 2.0F, lineWidth, 1, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontCenteredHeight(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float lineWidth, Color c, float scale) {
/* 1467 */     font.getData().setScale(scale);
/* 1468 */     layout.setText(font, msg, c, lineWidth, 1, true);
/* 1469 */     font.setColor(c);
/* 1470 */     font.draw((Batch)sb, msg, x, y + layout.height / 2.0F, lineWidth, 1, true);
/* 1471 */     font.getData().setScale(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void renderFontCenteredHeight(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 1481 */     layout.setText(font, msg);
/* 1482 */     renderFont(sb, font, msg, x, y + layout.height / 2.0F, c);
/*      */   }
/*      */   
/*      */   public static void renderFontCenteredHeight(SpriteBatch sb, BitmapFont font, String msg, float x, float y) {
/* 1486 */     layout.setText(font, msg);
/* 1487 */     renderFont(sb, font, msg, x, y + layout.height / 2.0F, Color.WHITE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String colorString(String input, String colorValue) {
/* 1499 */     newMsg.setLength(0);
/* 1500 */     for (String word : input.split(" ")) {
/* 1501 */       newMsg.append("#").append(colorValue).append(word).append(' ');
/*      */     }
/* 1503 */     return newMsg.toString().trim();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getWidth(BitmapFont font, String text, float scale) {
/* 1515 */     layout.setText(font, text);
/* 1516 */     return layout.width * scale;
/*      */   }
/*      */   
/*      */   public static float getHeight(BitmapFont font, String text, float scale) {
/* 1520 */     layout.setText(font, text);
/* 1521 */     return layout.height * scale;
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\FontHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */