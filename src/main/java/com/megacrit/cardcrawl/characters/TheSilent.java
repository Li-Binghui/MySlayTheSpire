/*     */ package com.megacrit.cardcrawl.characters;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbGreen;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
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
/*     */ public class TheSilent
/*     */   extends AbstractPlayer
/*     */ {
/*  57 */   private static final Logger logger = LogManager.getLogger(TheSilent.class.getName());
/*  58 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Silent");
/*  59 */   public static final String[] NAMES = characterStrings.NAMES;
/*  60 */   public static final String[] TEXT = characterStrings.TEXT;
/*  61 */   private EnergyOrbInterface energyOrb = (EnergyOrbInterface)new EnergyOrbGreen();
/*     */   private Prefs prefs;
/*     */   private CharStat charStat;
/*     */   
/*     */   TheSilent(String playerName) {
/*  66 */     super(playerName, PlayerClass.THE_SILENT);
/*  67 */     this.charStat = new CharStat(this);
/*     */     
/*  69 */     this.dialogX = this.drawX + 0.0F * Settings.scale;
/*  70 */     this.dialogY = this.drawY + 170.0F * Settings.scale;
/*     */     
/*  72 */     initializeClass((String)null, "images/characters/theSilent/shoulder2.png", "images/characters/theSilent/shoulder.png", "images/characters/theSilent/corpse.png", 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  77 */         getLoadout(), -20.0F, -24.0F, 240.0F, 240.0F, new EnergyManager(3));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     loadAnimation("images/characters/theSilent/idle/skeleton.atlas", "images/characters/theSilent/idle/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  88 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  89 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  90 */     e.setTimeScale(0.9F);
/*     */     
/*  92 */     if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera") || 
/*  93 */       ModHelper.isModEnabled("Blue Cards"))) {
/*  94 */       this.masterMaxOrbs = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPortraitImageName() {
/* 100 */     return "silentPortrait.jpg";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingDeck() {
/* 105 */     ArrayList<String> retVal = new ArrayList<>();
/* 106 */     retVal.add("Strike_G");
/* 107 */     retVal.add("Strike_G");
/* 108 */     retVal.add("Strike_G");
/* 109 */     retVal.add("Strike_G");
/* 110 */     retVal.add("Strike_G");
/* 111 */     retVal.add("Defend_G");
/* 112 */     retVal.add("Defend_G");
/* 113 */     retVal.add("Defend_G");
/* 114 */     retVal.add("Defend_G");
/* 115 */     retVal.add("Defend_G");
/* 116 */     retVal.add("Survivor");
/* 117 */     retVal.add("Neutralize");
/* 118 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard getStartCardForEvent() {
/* 123 */     return (AbstractCard)new Neutralize();
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingRelics() {
/* 128 */     ArrayList<String> retVal = new ArrayList<>();
/* 129 */     retVal.add("Ring of the Snake");
/* 130 */     UnlockTracker.markRelicAsSeen("Ring of the Snake");
/* 131 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public CharSelectInfo getLoadout() {
/* 136 */     return new CharSelectInfo(NAMES[0], TEXT[0], 70, 70, 0, 99, 5, this, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 145 */         getStartingRelics(), 
/* 146 */         getStartingDeck(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle(PlayerClass plyrClass) {
/* 152 */     return AbstractPlayer.uiStrings.TEXT[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard.CardColor getCardColor() {
/* 157 */     return AbstractCard.CardColor.GREEN;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardRenderColor() {
/* 162 */     return Color.CHARTREUSE;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAchievementKey() {
/* 167 */     return "EMERALD";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
/* 172 */     CardLibrary.addGreenCards(tmpPool);
/* 173 */     if (ModHelper.isModEnabled("Red Cards")) {
/* 174 */       CardLibrary.addRedCards(tmpPool);
/*     */     }
/* 176 */     if (ModHelper.isModEnabled("Blue Cards")) {
/* 177 */       CardLibrary.addBlueCards(tmpPool);
/*     */     }
/* 179 */     if (ModHelper.isModEnabled("Purple Cards")) {
/* 180 */       CardLibrary.addPurpleCards(tmpPool);
/*     */     }
/* 182 */     return tmpPool;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardTrailColor() {
/* 187 */     return Color.CHARTREUSE.cpy();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardCharacterName() {
/* 192 */     return "SILENT";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getEnergyImage() {
/* 197 */     return ImageMaster.GREEN_ORB_FLASH_VFX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAscensionMaxHPLoss() {
/* 202 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public BitmapFont getEnergyNumFont() {
/* 207 */     return FontHelper.energyNumFontGreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
/* 212 */     this.energyOrb.renderOrb(sb, enabled, current_x, current_y);
/*     */   }
/*     */   
/*     */   public void updateOrb(int orbCount) {
/* 216 */     this.energyOrb.updateOrb(orbCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public Prefs getPrefs() {
/* 221 */     if (this.prefs == null) {
/* 222 */       logger.error("prefs need to be initialized first!");
/*     */     }
/* 224 */     return this.prefs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadPrefs() {
/* 229 */     this.prefs = SaveHelper.getPrefs("STSDataTheSilent");
/*     */   }
/*     */ 
/*     */   
/*     */   public CharStat getCharStat() {
/* 234 */     return this.charStat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUnlockedCardCount() {
/* 239 */     return UnlockTracker.unlockedGreenCardCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeenCardCount() {
/* 244 */     return CardLibrary.seenGreenCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardCount() {
/* 249 */     return CardLibrary.greenCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveFileExists() {
/* 254 */     return SaveAndContinue.saveExistsAndNotCorrupted(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWinStreakKey() {
/* 259 */     return "win_streak_silent";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardWinStreakKey() {
/* 264 */     return "SILENT_CONSECUTIVE_WINS";
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
/* 269 */     if (!UnlockTracker.isCharacterLocked("The Silent")) {
/* 270 */       if (CardCrawlGame.mainMenuScreen.statsScreen.silentHb == null) {
/* 271 */         CardCrawlGame.mainMenuScreen.statsScreen.silentHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale);
/*     */       }
/*     */ 
/*     */       
/* 275 */       StatsScreen.renderHeader(sb, StatsScreen.NAMES[3], screenX, renderY);
/* 276 */       this.charStat.render(sb, screenX, renderY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doCharSelectScreenSelectEffect() {
/* 282 */     CardCrawlGame.sound.playA("ATTACK_DAGGER_2", MathUtils.random(-0.2F, 0.2F));
/* 283 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomModeCharacterButtonSoundKey() {
/* 288 */     return "ATTACK_DAGGER_2";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getCustomModeCharacterButtonImage() {
/* 293 */     return ImageMaster.FILTER_SILENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public CharacterStrings getCharacterString() {
/* 298 */     return CardCrawlGame.languagePack.getCharacterString("Silent");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedCharacterName() {
/* 303 */     return NAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshCharStat() {
/* 308 */     this.charStat = new CharStat(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPlayer newInstance() {
/* 313 */     return new TheSilent(this.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlas.AtlasRegion getOrb() {
/* 318 */     return AbstractCard.orb_green;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 323 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
/* 324 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 325 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 326 */       e.setTimeScale(0.9F);
/*     */     } 
/*     */     
/* 329 */     super.damage(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSpireHeartText() {
/* 334 */     return SpireHeart.DESCRIPTIONS[9];
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSlashAttackColor() {
/* 339 */     return Color.GREEN;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
/* 344 */     return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVampireText() {
/* 351 */     return Vampires.DESCRIPTIONS[1];
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\characters\TheSilent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */