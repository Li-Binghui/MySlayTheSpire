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
/*     */ import com.megacrit.cardcrawl.cards.red.Bash;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.EnergyManager;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.events.beyond.SpireHeart;
/*     */ import com.megacrit.cardcrawl.events.city.Vampires;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
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
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
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
/*     */ public class Ironclad
/*     */   extends AbstractPlayer
/*     */ {
/*  53 */   private static final Logger logger = LogManager.getLogger(Ironclad.class.getName());
/*  54 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Ironclad");
/*  55 */   public static final String[] NAMES = characterStrings.NAMES;
/*  56 */   public static final String[] TEXT = characterStrings.TEXT;
/*  57 */   private EnergyOrbInterface energyOrb = (EnergyOrbInterface)new EnergyOrbRed();
/*     */   private Prefs prefs;
/*     */   private CharStat charStat;
/*     */   
/*     */   Ironclad(String playerName) {
/*  62 */     super(playerName, PlayerClass.IRONCLAD);
/*  63 */     this.charStat = new CharStat(this);
/*     */     
/*  65 */     this.dialogX = this.drawX + 0.0F * Settings.scale;
/*  66 */     this.dialogY = this.drawY + 220.0F * Settings.scale;
/*     */     
/*  68 */     initializeClass((String)null, "images/characters/ironclad/shoulder2.png", "images/characters/ironclad/shoulder.png", "images/characters/ironclad/corpse.png", 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  73 */         getLoadout(), -4.0F, -16.0F, 220.0F, 290.0F, new EnergyManager(3));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     loadAnimation("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  86 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  87 */     e.setTimeScale(0.6F);
/*     */     
/*  89 */     if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera") || 
/*  90 */       ModHelper.isModEnabled("Blue Cards"))) {
/*  91 */       this.masterMaxOrbs = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPortraitImageName() {
/*  97 */     return "ironcladPortrait.jpg";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingRelics() {
/* 102 */     ArrayList<String> retVal = new ArrayList<>();
/* 103 */     retVal.add("Burning Blood");
/* 104 */     UnlockTracker.markRelicAsSeen("Burning Blood");
/* 105 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingDeck() {
/* 110 */     ArrayList<String> retVal = new ArrayList<>();
/* 111 */     retVal.add("Strike_R");
/* 112 */     retVal.add("Strike_R");
/* 113 */     retVal.add("Strike_R");
/* 114 */     retVal.add("Strike_R");
/* 115 */     retVal.add("Strike_R");
/* 116 */     retVal.add("Defend_R");
/* 117 */     retVal.add("Defend_R");
/* 118 */     retVal.add("Defend_R");
/* 119 */     retVal.add("Defend_R");
/* 120 */     retVal.add("Bash");
/* 121 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard getStartCardForEvent() {
/* 126 */     return (AbstractCard)new Bash();
/*     */   }
/*     */ 
/*     */   
/*     */   public CharSelectInfo getLoadout() {
/* 131 */     return new CharSelectInfo(NAMES[0], TEXT[0], 80, 80, 0, 99, 5, this, 
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
/* 147 */     return AbstractPlayer.uiStrings.TEXT[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard.CardColor getCardColor() {
/* 152 */     return AbstractCard.CardColor.RED;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardRenderColor() {
/* 157 */     return Color.SCARLET;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAchievementKey() {
/* 162 */     return "RUBY";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
/* 167 */     CardLibrary.addRedCards(tmpPool);
/* 168 */     if (ModHelper.isModEnabled("Green Cards")) {
/* 169 */       CardLibrary.addGreenCards(tmpPool);
/*     */     }
/* 171 */     if (ModHelper.isModEnabled("Blue Cards")) {
/* 172 */       CardLibrary.addBlueCards(tmpPool);
/*     */     }
/* 174 */     if (ModHelper.isModEnabled("Purple Cards")) {
/* 175 */       CardLibrary.addPurpleCards(tmpPool);
/*     */     }
/* 177 */     return tmpPool;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardTrailColor() {
/* 182 */     return new Color(1.0F, 0.4F, 0.1F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardCharacterName() {
/* 187 */     return "IRONCLAD";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getEnergyImage() {
/* 192 */     return ImageMaster.RED_ORB_FLASH_VFX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAscensionMaxHPLoss() {
/* 197 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public BitmapFont getEnergyNumFont() {
/* 202 */     return FontHelper.energyNumFontRed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
/* 207 */     this.energyOrb.renderOrb(sb, enabled, current_x, current_y);
/*     */   }
/*     */   
/*     */   public void updateOrb(int orbCount) {
/* 211 */     this.energyOrb.updateOrb(orbCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public Prefs getPrefs() {
/* 216 */     if (this.prefs == null) {
/* 217 */       logger.error("prefs need to be initialized first!");
/*     */     }
/* 219 */     return this.prefs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadPrefs() {
/* 224 */     this.prefs = SaveHelper.getPrefs("STSDataVagabond");
/*     */   }
/*     */ 
/*     */   
/*     */   public CharStat getCharStat() {
/* 229 */     return this.charStat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUnlockedCardCount() {
/* 234 */     return UnlockTracker.unlockedRedCardCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeenCardCount() {
/* 239 */     return CardLibrary.seenRedCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardCount() {
/* 244 */     return CardLibrary.redCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveFileExists() {
/* 249 */     return SaveAndContinue.saveExistsAndNotCorrupted(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWinStreakKey() {
/* 254 */     return "win_streak_ironclad";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardWinStreakKey() {
/* 259 */     return "IRONCLAD_CONSECUTIVE_WINS";
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
/* 264 */     StatsScreen.renderHeader(sb, StatsScreen.NAMES[2], screenX, renderY);
/* 265 */     this.charStat.render(sb, screenX, renderY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doCharSelectScreenSelectEffect() {
/* 270 */     CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
/* 271 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomModeCharacterButtonSoundKey() {
/* 276 */     return "ATTACK_HEAVY";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getCustomModeCharacterButtonImage() {
/* 281 */     return ImageMaster.FILTER_IRONCLAD;
/*     */   }
/*     */ 
/*     */   
/*     */   public CharacterStrings getCharacterString() {
/* 286 */     return CardCrawlGame.languagePack.getCharacterString("Ironclad");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedCharacterName() {
/* 291 */     return NAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshCharStat() {
/* 296 */     this.charStat = new CharStat(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPlayer newInstance() {
/* 301 */     return new Ironclad(this.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlas.AtlasRegion getOrb() {
/* 306 */     return AbstractCard.orb_red;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 311 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
/* 312 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 313 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 314 */       e.setTimeScale(0.6F);
/*     */     } 
/*     */     
/* 317 */     super.damage(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSpireHeartText() {
/* 322 */     return SpireHeart.DESCRIPTIONS[8];
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSlashAttackColor() {
/* 327 */     return Color.RED;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
/* 332 */     return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVampireText() {
/* 338 */     return Vampires.DESCRIPTIONS[0];
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\characters\Ironclad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */