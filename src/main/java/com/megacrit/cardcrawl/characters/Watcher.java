/*     */ package com.megacrit.cardcrawl.characters;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.AnimationStateData;
/*     */ import com.esotericsoftware.spine.Bone;
/*     */ import com.esotericsoftware.spine.Skeleton;
/*     */ import com.esotericsoftware.spine.SkeletonData;
/*     */ import com.esotericsoftware.spine.SkeletonJson;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.purple.Eruption;
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
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
/*     */ import com.megacrit.cardcrawl.screens.stats.CharStat;
/*     */ import com.megacrit.cardcrawl.screens.stats.StatsScreen;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbPurple;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Watcher
/*     */   extends AbstractPlayer
/*     */ {
/*  71 */   private static final Logger logger = LogManager.getLogger(Watcher.class.getName());
/*  72 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Watcher");
/*  73 */   public static final String[] NAMES = characterStrings.NAMES;
/*  74 */   public static final String[] TEXT = characterStrings.TEXT;
/*  75 */   private EnergyOrbInterface energyOrb = (EnergyOrbInterface)new EnergyOrbPurple();
/*     */   
/*     */   private Prefs prefs;
/*     */   private CharStat charStat;
/*     */   private Bone eyeBone;
/*  80 */   protected TextureAtlas eyeAtlas = null;
/*     */   protected Skeleton eyeSkeleton;
/*     */   public AnimationState eyeState;
/*     */   protected AnimationStateData eyeStateData;
/*     */   
/*     */   Watcher(String playerName) {
/*  86 */     super(playerName, PlayerClass.WATCHER);
/*  87 */     this.charStat = new CharStat(this);
/*     */     
/*  89 */     this.drawY += 7.0F * Settings.scale;
/*  90 */     this.dialogX = this.drawX + 0.0F * Settings.scale;
/*  91 */     this.dialogY = this.drawY + 170.0F * Settings.scale;
/*     */     
/*  93 */     initializeClass((String)null, "images/characters/watcher/shoulder2.png", "images/characters/watcher/shoulder.png", "images/characters/watcher/corpse.png", 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  98 */         getLoadout(), 0.0F, -5.0F, 240.0F, 270.0F, new EnergyManager(3));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     loadAnimation("images/characters/watcher/idle/skeleton.atlas", "images/characters/watcher/idle/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     loadEyeAnimation();
/*     */     
/* 112 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/* 113 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/* 114 */     e.setTimeScale(0.7F);
/* 115 */     this.eyeBone = this.skeleton.findBone("eye_anchor");
/*     */     
/* 117 */     if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera") || 
/* 118 */       ModHelper.isModEnabled("Blue Cards"))) {
/* 119 */       this.masterMaxOrbs = 1;
/*     */     }
/*     */   }
/*     */   
/*     */   private void loadEyeAnimation() {
/* 124 */     this.eyeAtlas = new TextureAtlas(Gdx.files.internal("images/characters/watcher/eye_anim/skeleton.atlas"));
/* 125 */     SkeletonJson json = new SkeletonJson(this.eyeAtlas);
/* 126 */     json.setScale(Settings.scale / 1.0F);
/* 127 */     SkeletonData skeletonData = json.readSkeletonData(Gdx.files
/* 128 */         .internal("images/characters/watcher/eye_anim/skeleton.json"));
/* 129 */     this.eyeSkeleton = new Skeleton(skeletonData);
/* 130 */     this.eyeSkeleton.setColor(Color.WHITE);
/* 131 */     this.eyeStateData = new AnimationStateData(skeletonData);
/* 132 */     this.eyeState = new AnimationState(this.eyeStateData);
/* 133 */     this.eyeStateData.setDefaultMix(0.2F);
/* 134 */     this.eyeState.setAnimation(0, "None", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPortraitImageName() {
/* 139 */     return "watcherPortrait.jpg";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingRelics() {
/* 144 */     ArrayList<String> retVal = new ArrayList<>();
/* 145 */     retVal.add("PureWater");
/* 146 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getStartingDeck() {
/* 151 */     ArrayList<String> retVal = new ArrayList<>();
/* 152 */     retVal.add("Strike_P");
/* 153 */     retVal.add("Strike_P");
/* 154 */     retVal.add("Strike_P");
/* 155 */     retVal.add("Strike_P");
/* 156 */     retVal.add("Defend_P");
/* 157 */     retVal.add("Defend_P");
/* 158 */     retVal.add("Defend_P");
/* 159 */     retVal.add("Defend_P");
/* 160 */     retVal.add("Eruption");
/* 161 */     retVal.add("Vigilance");
/* 162 */     return retVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard getStartCardForEvent() {
/* 167 */     return (AbstractCard)new Eruption();
/*     */   }
/*     */ 
/*     */   
/*     */   public CharSelectInfo getLoadout() {
/* 172 */     return new CharSelectInfo(NAMES[0], TEXT[0], 72, 72, 0, 99, 5, this, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 181 */         getStartingRelics(), 
/* 182 */         getStartingDeck(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle(PlayerClass plyrClass) {
/* 188 */     return AbstractPlayer.uiStrings.TEXT[4];
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCard.CardColor getCardColor() {
/* 193 */     return AbstractCard.CardColor.PURPLE;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardRenderColor() {
/* 198 */     return Settings.PURPLE_COLOR;
/*     */   }
/*     */ 
/*     */   
/*     */   public CharacterOption getCharacterSelectOption() {
/* 203 */     return new CharacterOption(CharacterSelectScreen.TEXT[14], this, ImageMaster.CHAR_SELECT_WATCHER, ImageMaster.CHAR_SELECT_BG_WATCHER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAchievementKey() {
/* 212 */     return "AMETHYST";
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
/* 217 */     CardLibrary.addPurpleCards(tmpPool);
/* 218 */     if (ModHelper.isModEnabled("Red Cards")) {
/* 219 */       CardLibrary.addRedCards(tmpPool);
/*     */     }
/* 221 */     if (ModHelper.isModEnabled("Green Cards")) {
/* 222 */       CardLibrary.addGreenCards(tmpPool);
/*     */     }
/* 224 */     if (ModHelper.isModEnabled("Blue Cards")) {
/* 225 */       CardLibrary.addBlueCards(tmpPool);
/*     */     }
/* 227 */     if (ModHelper.isModEnabled("Purple Cards")) {
/* 228 */       CardLibrary.addPurpleCards(tmpPool);
/*     */     }
/*     */     
/* 231 */     return tmpPool;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getCardTrailColor() {
/* 236 */     return Color.PURPLE.cpy();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardCharacterName() {
/* 241 */     return "WATCHER";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getEnergyImage() {
/* 246 */     return ImageMaster.PURPLE_ORB_FLASH_VFX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAscensionMaxHPLoss() {
/* 251 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public BitmapFont getEnergyNumFont() {
/* 256 */     return FontHelper.energyNumFontPurple;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
/* 261 */     this.energyOrb.renderOrb(sb, enabled, current_x, current_y);
/*     */   }
/*     */   
/*     */   public void updateOrb(int orbCount) {
/* 265 */     this.energyOrb.updateOrb(orbCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public Prefs getPrefs() {
/* 270 */     if (this.prefs == null) {
/* 271 */       logger.error("prefs need to be initialized first!");
/*     */     }
/* 273 */     return this.prefs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadPrefs() {
/* 278 */     this.prefs = SaveHelper.getPrefs("STSDataWatcher");
/*     */   }
/*     */ 
/*     */   
/*     */   public CharStat getCharStat() {
/* 283 */     return this.charStat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUnlockedCardCount() {
/* 288 */     return UnlockTracker.unlockedPurpleCardCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeenCardCount() {
/* 293 */     return CardLibrary.seenPurpleCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCardCount() {
/* 298 */     return CardLibrary.purpleCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveFileExists() {
/* 303 */     return SaveAndContinue.saveExistsAndNotCorrupted(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWinStreakKey() {
/* 308 */     return "win_streak_watcher";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeaderboardWinStreakKey() {
/* 313 */     return "WATCHER_CONSECUTIVE_WINS";
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
/* 318 */     if (!UnlockTracker.isCharacterLocked("Watcher")) {
/* 319 */       if (CardCrawlGame.mainMenuScreen.statsScreen.watcherHb == null) {
/* 320 */         CardCrawlGame.mainMenuScreen.statsScreen.watcherHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale);
/*     */       }
/*     */ 
/*     */       
/* 324 */       StatsScreen.renderHeader(sb, StatsScreen.NAMES[5], screenX, renderY);
/* 325 */       this.charStat.render(sb, screenX, renderY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doCharSelectScreenSelectEffect() {
/* 331 */     CardCrawlGame.sound.playA("SELECT_WATCHER", MathUtils.random(-0.15F, 0.15F));
/* 332 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomModeCharacterButtonSoundKey() {
/* 337 */     return "SELECT_WATCHER";
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture getCustomModeCharacterButtonImage() {
/* 342 */     return ImageMaster.FILTER_WATCHER;
/*     */   }
/*     */ 
/*     */   
/*     */   public CharacterStrings getCharacterString() {
/* 347 */     return CardCrawlGame.languagePack.getCharacterString("Watcher");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedCharacterName() {
/* 352 */     return NAMES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshCharStat() {
/* 357 */     this.charStat = new CharStat(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPlayer newInstance() {
/* 362 */     return new Watcher(this.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlas.AtlasRegion getOrb() {
/* 367 */     return AbstractCard.orb_purple;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 372 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0 && this.atlas != null) {
/*     */       
/* 374 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 375 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 376 */       e.setTime(0.9F);
/*     */     } 
/*     */     
/* 379 */     super.damage(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSpireHeartText() {
/* 384 */     return SpireHeart.DESCRIPTIONS[15];
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getSlashAttackColor() {
/* 389 */     return Color.MAGENTA;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
/* 394 */     return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.BLUNT_LIGHT, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_LIGHT, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_LIGHT };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVampireText() {
/* 401 */     return Vampires.DESCRIPTIONS[1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStanceChange(String newStance) {
/* 407 */     if (newStance.equals("Calm")) {
/* 408 */       this.eyeState.setAnimation(0, "Calm", true);
/* 409 */     } else if (newStance.equals("Wrath")) {
/* 410 */       this.eyeState.setAnimation(0, "Wrath", true);
/* 411 */     } else if (newStance.equals("Divinity")) {
/* 412 */       this.eyeState.setAnimation(0, "Divinity", true);
/* 413 */     } else if (newStance.equals("Neutral")) {
/* 414 */       this.eyeState.setAnimation(0, "None", true);
/*     */     } else {
/* 416 */       this.eyeState.setAnimation(0, "None", true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderPlayerImage(SpriteBatch sb) {
/* 423 */     this.state.update(Gdx.graphics.getDeltaTime());
/* 424 */     this.state.apply(this.skeleton);
/* 425 */     this.skeleton.updateWorldTransform();
/* 426 */     this.skeleton.setPosition(this.drawX + this.animX, this.drawY + this.animY);
/* 427 */     this.skeleton.setColor(this.tint.color);
/* 428 */     this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*     */ 
/*     */     
/* 431 */     this.eyeState.update(Gdx.graphics.getDeltaTime());
/* 432 */     this.eyeState.apply(this.eyeSkeleton);
/* 433 */     this.eyeSkeleton.updateWorldTransform();
/* 434 */     this.eyeSkeleton.setPosition(this.skeleton.getX() + this.eyeBone.getWorldX(), this.skeleton.getY() + this.eyeBone.getWorldY());
/* 435 */     this.eyeSkeleton.setColor(this.tint.color);
/* 436 */     this.eyeSkeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*     */     
/* 438 */     sb.end();
/* 439 */     CardCrawlGame.psb.begin();
/* 440 */     sr.draw(CardCrawlGame.psb, this.skeleton);
/* 441 */     sr.draw(CardCrawlGame.psb, this.eyeSkeleton);
/* 442 */     CardCrawlGame.psb.end();
/* 443 */     sb.begin();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\characters\Watcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */