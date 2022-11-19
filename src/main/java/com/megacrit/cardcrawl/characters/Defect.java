/*     */ package com.megacrit.cardcrawl.characters;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.blue.Zap;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.EnergyManager;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.events.beyond.SpireHeart;
/*     */ import com.megacrit.cardcrawl.events.city.Vampires;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Prefs;
/*     */ import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.localization.CharacterStrings;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
/*     */ import com.megacrit.cardcrawl.screens.CharSelectInfo;
/*     */ import com.megacrit.cardcrawl.screens.stats.CharStat;
/*     */ import com.megacrit.cardcrawl.screens.stats.StatsScreen;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
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
/*     */ public class Defect
/*     */   extends AbstractPlayer
/*     */ {
/*  55 */   private static final Logger logger = LogManager.getLogger(Defect.class.getName());
/*  56 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Defect");
/*  57 */   public static final String[] NAMES = characterStrings.NAMES;
/*  58 */   public static final String[] TEXT = characterStrings.TEXT;
/*  59 */   private EnergyOrbInterface energyOrb = (EnergyOrbInterface)new EnergyOrbBlue();
/*     */   private Prefs prefs;
/*     */   private CharStat charStat;
/*     */   
/*     */   Defect(String playerName) {
/*  64 */     super(playerName, PlayerClass.DEFECT);
/*  65 */     this.charStat = new CharStat(this);
/*     */     
/*  67 */     this.drawX += 5.0F * Settings.scale;
/*  68 */     this.drawY += 7.0F * Settings.scale;
/*     */     
/*  70 */     this.dialogX = this.drawX + 0.0F * Settings.scale;
/*  71 */     this.dialogY = this.drawY + 170.0F * Settings.scale;
/*     */     
/*  73 */     initializeClass((String)null, "images/characters/defect/shoulder2.png", "images/characters/defect/shoulder.png", "images/characters/defect/corpse.png", 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  78 */         getLoadout(), 0.0F, -5.0F, 240.0F, 244.0F, new EnergyManager(3));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     loadAnimation("images/characters/defect/idle/skeleton.atlas", "images/characters/defect/idle/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  91 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  92 */     e.setTimeScale(0.9F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPortraitImageName() {
/*  97 */     return "defectPortrait.jpg";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingRelics() {
/* 102 */     ArrayList<String> retVal = new ArrayList<>();
/* 103 */     retVal.add("Cracked Core");
/* 104 */     UnlockTracker.markRelicAsSeen("Cracked Core");
/* 105 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingDeck() {
/* 110 */     ArrayList<String> retVal = new ArrayList<>();
/* 111 */     retVal.add("Strike_B");
/* 112 */     retVal.add("Strike_B");
/* 113 */     retVal.add("Strike_B");
/* 114 */     retVal.add("Strike_B");
/* 115 */     retVal.add("Defend_B");
/* 116 */     retVal.add("Defend_B");
/* 117 */     retVal.add("Defend_B");
/* 118 */     retVal.add("Defend_B");
/* 119 */     retVal.add("Zap");
/* 120 */     retVal.add("Dualcast");
/* 121 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard getStartCardForEvent() {
/* 126 */     return (AbstractCard)new Zap();
/*     */   }
/*     */ 
/*     */   
/*     */   public CharSelectInfo getLoadout() {
/* 131 */     return new CharSelectInfo(NAMES[0], TEXT[0], 75, 75, 3, 99, 5, this, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 140 */         getStartingRelics(), 
/* 141 */         getStartingDeck(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle(PlayerClass plyrClass) {
/* 147 */     return AbstractPlayer.uiStrings.TEXT[3];
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard.CardColor getCardColor() {
/* 152 */     return AbstractCard.CardColor.BLUE;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardRenderColor() {
/* 157 */     return Color.SKY;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAchievementKey() {
/* 162 */     return "SAPPHIRE";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
/* 167 */     CardLibrary.addBlueCards(tmpPool);
/* 168 */     if (ModHelper.isModEnabled("Red Cards")) {
/* 169 */       CardLibrary.addRedCards(tmpPool);
/*     */     }
/* 171 */     if (ModHelper.isModEnabled("Green Cards")) {
/* 172 */       CardLibrary.addGreenCards(tmpPool);
/*     */     }
/* 174 */     if (ModHelper.isModEnabled("Purple Cards")) {
/* 175 */       CardLibrary.addPurpleCards(tmpPool);
/*     */     }
/* 177 */     return tmpPool;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardTrailColor() {
/* 182 */     return Color.SKY.cpy();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardCharacterName() {
/* 187 */     return "DEFECT";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getEnergyImage() {
/* 192 */     return ImageMaster.BLUE_ORB_FLASH_VFX;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAscensionMaxHPLoss() {
/* 198 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public BitmapFont getEnergyNumFont() {
/* 203 */     return FontHelper.energyNumFontBlue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
/* 208 */     this.energyOrb.renderOrb(sb, enabled, current_x, current_y);
/*     */   }
/*     */   
/*     */   public void updateOrb(int orbCount) {
/* 212 */     this.energyOrb.updateOrb(orbCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public Prefs getPrefs() {
/* 217 */     if (this.prefs == null) {
/* 218 */       logger.error("prefs need to be initialized first!");
/*     */     }
/* 220 */     return this.prefs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadPrefs() {
/* 225 */     this.prefs = SaveHelper.getPrefs("STSDataDefect");
/*     */   }
/*     */ 
/*     */   
/*     */   public CharStat getCharStat() {
/* 230 */     return this.charStat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUnlockedCardCount() {
/* 235 */     return UnlockTracker.unlockedBlueCardCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeenCardCount() {
/* 240 */     return CardLibrary.seenBlueCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardCount() {
/* 245 */     return CardLibrary.blueCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveFileExists() {
/* 250 */     return SaveAndContinue.saveExistsAndNotCorrupted(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWinStreakKey() {
/* 255 */     return "win_streak_defect";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardWinStreakKey() {
/* 260 */     return "DEFECT_CONSECUTIVE_WINS";
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
/* 265 */     if (!UnlockTracker.isCharacterLocked("Defect")) {
/* 266 */       if (CardCrawlGame.mainMenuScreen.statsScreen.defectHb == null) {
/* 267 */         CardCrawlGame.mainMenuScreen.statsScreen.defectHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale);
/*     */       }
/*     */ 
/*     */       
/* 271 */       StatsScreen.renderHeader(sb, StatsScreen.NAMES[4], screenX, renderY);
/* 272 */       this.charStat.render(sb, screenX, renderY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doCharSelectScreenSelectEffect() {
/* 278 */     CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT", MathUtils.random(-0.2F, 0.2F));
/* 279 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomModeCharacterButtonSoundKey() {
/* 284 */     return "ATTACK_MAGIC_BEAM_SHORT";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getCustomModeCharacterButtonImage() {
/* 289 */     return ImageMaster.FILTER_DEFECT;
/*     */   }
/*     */ 
/*     */   
/*     */   public CharacterStrings getCharacterString() {
/* 294 */     return CardCrawlGame.languagePack.getCharacterString("Defect");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedCharacterName() {
/* 299 */     return NAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshCharStat() {
/* 304 */     this.charStat = new CharStat(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPlayer newInstance() {
/* 309 */     return new Defect(this.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlas.AtlasRegion getOrb() {
/* 314 */     return AbstractCard.orb_blue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 319 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
/* 320 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 321 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 322 */       e.setTime(0.9F);
/*     */     } 
/*     */     
/* 325 */     super.damage(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSpireHeartText() {
/* 330 */     return SpireHeart.DESCRIPTIONS[10];
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSlashAttackColor() {
/* 335 */     return Color.SKY;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
/* 340 */     return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVampireText() {
/* 347 */     return Vampires.DESCRIPTIONS[5];
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\characters\Defect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */