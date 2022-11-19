/*      */ package com.megacrit.cardcrawl.monsters;
/*      */ 
/*      */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BackAttackPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.combat.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.*;
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
/*      */ public abstract class AbstractMonster
/*      */   extends AbstractCreature
/*      */ {
/*   81 */   private static final Logger logger = LogManager.getLogger(AbstractMonster.class.getName());
/*   82 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractMonster");
/*   83 */   public static final String[] TEXT = uiStrings.TEXT;
/*      */   
/*      */   private static final float DEATH_TIME = 1.8F;
/*      */   private static final float ESCAPE_TIME = 3.0F;
/*   87 */   public float deathTimer = 0.0F; protected static final byte ESCAPE = 99; protected static final byte ROLL = 98;
/*   88 */   private Color nameColor = new Color(); private Color nameBgColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*      */   protected Texture img;
/*      */   public boolean tintFadeOutCalled = false;
/*   91 */   protected HashMap<Byte, String> moveSet = new HashMap<>(); public boolean escaped = false;
/*      */   public boolean escapeNext = false;
/*   93 */   private PowerTip intentTip = new PowerTip();
/*   94 */   public EnemyType type = EnemyType.NORMAL;
/*   95 */   private float hoverTimer = 0.0F;
/*      */   
/*      */   public boolean cannotEscape = false;
/*      */   
/*   99 */   public ArrayList<DamageInfo> damage = new ArrayList<>();
/*      */   
/*      */   private EnemyMoveInfo move;
/*      */   
/*  103 */   private float intentParticleTimer = 0.0F; private float intentAngle = 0.0F;
/*  104 */   public ArrayList<Byte> moveHistory = new ArrayList<>();
/*  105 */   private ArrayList<AbstractGameEffect> intentVfx = new ArrayList<>();
/*  106 */   public byte nextMove = -1;
/*      */   private static final int INTENT_W = 128;
/*  108 */   private BobEffect bobEffect = new BobEffect();
/*  109 */   private static final float INTENT_HB_W = 64.0F * Settings.scale;
/*      */   public Hitbox intentHb;
/*  111 */   public Intent intent = Intent.DEBUG; public Intent tipIntent = Intent.DEBUG;
/*  112 */   public float intentAlpha = 0.0F; public float intentAlphaTarget = 0.0F; public float intentOffsetX = 0.0F;
/*  113 */   private Texture intentImg = null; private Texture intentBg = null;
/*  114 */   private int intentDmg = -1; private int intentBaseDmg = -1; private int intentMultiAmt = 0;
/*      */   private boolean isMultiDmg = false;
/*  116 */   private Color intentColor = Color.WHITE.cpy();
/*  117 */   public String moveName = null;
/*  118 */   protected List<Disposable> disposables = new ArrayList<>();
/*      */   public static String[] MOVES;
/*      */   public static String[] DIALOG;
/*      */   public static Comparator<AbstractMonster> sortByHitbox;
/*      */   
/*      */   public enum Intent
/*      */   {
/*  125 */     ATTACK, ATTACK_BUFF, ATTACK_DEBUFF, ATTACK_DEFEND, BUFF, DEBUFF, STRONG_DEBUFF, DEBUG, DEFEND, DEFEND_DEBUFF, DEFEND_BUFF, ESCAPE, MAGIC, NONE, SLEEP, STUN, UNKNOWN;
/*      */   }
/*      */   
/*      */   public enum EnemyType {
/*  129 */     NORMAL, ELITE, BOSS; }
/*      */   
/*      */   static {
/*  132 */     sortByHitbox = ((o1, o2) -> (int)(o1.hb.cX - o2.hb.cX));
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
/*      */   public AbstractMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
/*  145 */     this(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, false);
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
/*      */   public AbstractMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY, boolean ignoreBlights) {
/*  161 */     this.isPlayer = false;
/*  162 */     this.name = name;
/*  163 */     this.id = id;
/*  164 */     this.maxHealth = maxHealth;
/*      */     
/*  166 */     if (!ignoreBlights && Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
/*  167 */       float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
/*  168 */       this.maxHealth = (int)(maxHealth * mod);
/*      */     } 
/*      */     
/*  171 */     if (ModHelper.isModEnabled("MonsterHunter")) {
/*  172 */       this.currentHealth = (int)(this.currentHealth * 1.5F);
/*      */     }
/*      */     
/*  175 */     if (Settings.isMobile) {
/*  176 */       hb_w *= 1.17F;
/*      */     }
/*      */     
/*  179 */     this.currentHealth = this.maxHealth;
/*  180 */     this.currentBlock = 0;
/*  181 */     this.drawX = Settings.WIDTH * 0.75F + offsetX * Settings.xScale;
/*  182 */     this.drawY = AbstractDungeon.floorY + offsetY * Settings.yScale;
/*  183 */     this.hb_w = hb_w * Settings.scale;
/*  184 */     this.hb_h = hb_h * Settings.xScale;
/*  185 */     this.hb_x = hb_x * Settings.scale;
/*  186 */     this.hb_y = hb_y * Settings.scale;
/*      */     
/*  188 */     if (imgUrl != null) {
/*  189 */       this.img = ImageMaster.loadImage(imgUrl);
/*      */     }
/*      */     
/*  192 */     this.intentHb = new Hitbox(INTENT_HB_W, INTENT_HB_W);
/*  193 */     this.hb = new Hitbox(this.hb_w, this.hb_h);
/*  194 */     this.healthHb = new Hitbox(this.hb_w, 72.0F * Settings.scale);
/*  195 */     refreshHitboxLocation();
/*  196 */     refreshIntentHbLocation();
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
/*      */   public AbstractMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl) {
/*  208 */     this(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, 0.0F, 0.0F);
/*      */   }
/*      */   
/*      */   public void refreshIntentHbLocation() {
/*  212 */     this.intentHb.move(this.hb.cX + this.intentOffsetX, this.hb.cY + this.hb_h / 2.0F + INTENT_HB_W / 2.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/*  219 */     for (AbstractPower p : this.powers) {
/*  220 */       p.updateParticles();
/*      */     }
/*  222 */     updateReticle();
/*  223 */     updateHealthBar();
/*  224 */     updateAnimations();
/*  225 */     updateDeathAnimation();
/*  226 */     updateEscapeAnimation();
/*  227 */     updateIntent();
/*  228 */     this.tint.update();
/*      */   }
/*      */   
/*      */   public void unhover() {
/*  232 */     this.healthHb.hovered = false;
/*  233 */     this.hb.hovered = false;
/*  234 */     this.intentHb.hovered = false;
/*      */   }
/*      */   
/*      */   private void updateIntent() {
/*  238 */     this.bobEffect.update();
/*      */ 
/*      */     
/*  241 */     if (this.intentAlpha != this.intentAlphaTarget && this.intentAlphaTarget == 1.0F) {
/*  242 */       this.intentAlpha += Gdx.graphics.getDeltaTime();
/*  243 */       if (this.intentAlpha > this.intentAlphaTarget) {
/*  244 */         this.intentAlpha = this.intentAlphaTarget;
/*      */       }
/*      */     }
/*  247 */     else if (this.intentAlphaTarget == 0.0F) {
/*  248 */       this.intentAlpha -= Gdx.graphics.getDeltaTime() / 1.5F;
/*  249 */       if (this.intentAlpha < 0.0F) {
/*  250 */         this.intentAlpha = 0.0F;
/*      */       }
/*      */     } 
/*      */     
/*  254 */     if (!this.isDying && !this.isEscaping) {
/*  255 */       updateIntentVFX();
/*      */     }
/*      */ 
/*      */     
/*  259 */     for (Iterator<AbstractGameEffect> i = this.intentVfx.iterator(); i.hasNext(); ) {
/*  260 */       AbstractGameEffect e = i.next();
/*  261 */       e.update();
/*  262 */       if (e.isDone) {
/*  263 */         i.remove();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateIntentVFX() {
/*  269 */     if (this.intentAlpha > 0.0F) {
/*  270 */       if (this.intent == Intent.ATTACK_DEBUFF || this.intent == Intent.DEBUFF || this.intent == Intent.STRONG_DEBUFF || this.intent == Intent.DEFEND_DEBUFF) {
/*      */         
/*  272 */         this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
/*  273 */         if (this.intentParticleTimer < 0.0F) {
/*  274 */           this.intentParticleTimer = 1.0F;
/*  275 */           this.intentVfx.add(new DebuffParticleEffect(this.intentHb.cX, this.intentHb.cY));
/*      */         } 
/*  277 */       } else if (this.intent == Intent.ATTACK_BUFF || this.intent == Intent.BUFF || this.intent == Intent.DEFEND_BUFF) {
/*  278 */         this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
/*  279 */         if (this.intentParticleTimer < 0.0F) {
/*  280 */           this.intentParticleTimer = 0.1F;
/*  281 */           this.intentVfx.add(new BuffParticleEffect(this.intentHb.cX, this.intentHb.cY));
/*      */         } 
/*  283 */       } else if (this.intent == Intent.ATTACK_DEFEND) {
/*  284 */         this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
/*  285 */         if (this.intentParticleTimer < 0.0F) {
/*  286 */           this.intentParticleTimer = 0.5F;
/*  287 */           this.intentVfx.add(new ShieldParticleEffect(this.intentHb.cX, this.intentHb.cY));
/*      */         } 
/*  289 */       } else if (this.intent == Intent.UNKNOWN) {
/*  290 */         this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
/*  291 */         if (this.intentParticleTimer < 0.0F) {
/*  292 */           this.intentParticleTimer = 0.5F;
/*  293 */           this.intentVfx.add(new UnknownParticleEffect(this.intentHb.cX, this.intentHb.cY));
/*      */         } 
/*  295 */       } else if (this.intent == Intent.STUN) {
/*  296 */         this.intentParticleTimer -= Gdx.graphics.getDeltaTime();
/*  297 */         if (this.intentParticleTimer < 0.0F) {
/*  298 */           this.intentParticleTimer = 0.67F;
/*  299 */           this.intentVfx.add(new StunStarEffect(this.intentHb.cX, this.intentHb.cY));
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void renderTip(SpriteBatch sb) {
/*  306 */     this.tips.clear();
/*      */     
/*  308 */     if (this.intentAlphaTarget == 1.0F && !AbstractDungeon.player.hasRelic("Runic Dome") && this.intent != Intent.NONE) {
/*  309 */       this.tips.add(this.intentTip);
/*      */     }
/*      */     
/*  312 */     for (AbstractPower p : this.powers) {
/*  313 */       if (p.region48 != null) {
/*  314 */         this.tips.add(new PowerTip(p.name, p.description, p.region48)); continue;
/*      */       } 
/*  316 */       this.tips.add(new PowerTip(p.name, p.description, p.img));
/*      */     } 
/*      */ 
/*      */     
/*  320 */     if (!this.tips.isEmpty())
/*      */     {
/*      */       
/*  323 */       if (this.hb.cX + this.hb.width / 2.0F < TIP_X_THRESHOLD) {
/*  324 */         TipHelper.queuePowerTips(this.hb.cX + this.hb.width / 2.0F + TIP_OFFSET_R_X, this.hb.cY + 
/*      */             
/*  326 */             TipHelper.calculateAdditionalOffset(this.tips, this.hb.cY), this.tips);
/*      */       }
/*      */       else {
/*      */         
/*  330 */         TipHelper.queuePowerTips(this.hb.cX - this.hb.width / 2.0F + TIP_OFFSET_L_X, this.hb.cY + 
/*      */             
/*  332 */             TipHelper.calculateAdditionalOffset(this.tips, this.hb.cY), this.tips);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateIntentTip() {
/*  342 */     switch (this.intent) {
/*      */       case ATTACK:
/*  344 */         this.intentTip.header = TEXT[0];
/*  345 */         if (this.isMultiDmg) {
/*  346 */           this.intentTip.body = TEXT[1] + this.intentDmg + TEXT[2] + this.intentMultiAmt + TEXT[3];
/*      */         } else {
/*  348 */           this.intentTip.body = TEXT[4] + this.intentDmg + TEXT[5];
/*      */         } 
/*  350 */         this.intentTip.img = getAttackIntentTip();
/*      */         return;
/*      */       case ATTACK_BUFF:
/*  353 */         this.intentTip.header = TEXT[6];
/*  354 */         if (this.isMultiDmg) {
/*  355 */           this.intentTip.body = TEXT[7] + this.intentDmg + TEXT[2] + this.intentMultiAmt + TEXT[8];
/*      */         } else {
/*  357 */           this.intentTip.body = TEXT[9] + this.intentDmg + TEXT[5];
/*      */         } 
/*  359 */         this.intentTip.img = ImageMaster.INTENT_ATTACK_BUFF;
/*      */         return;
/*      */       case ATTACK_DEBUFF:
/*  362 */         this.intentTip.header = TEXT[10];
/*  363 */         this.intentTip.body = TEXT[11] + this.intentDmg + TEXT[5];
/*  364 */         this.intentTip.img = ImageMaster.INTENT_ATTACK_DEBUFF;
/*      */         return;
/*      */       case ATTACK_DEFEND:
/*  367 */         this.intentTip.header = TEXT[0];
/*  368 */         if (this.isMultiDmg) {
/*  369 */           this.intentTip.body = TEXT[12] + this.intentDmg + TEXT[2] + this.intentMultiAmt + TEXT[3];
/*      */         } else {
/*  371 */           this.intentTip.body = TEXT[12] + this.intentDmg + TEXT[5];
/*      */         } 
/*  373 */         this.intentTip.img = ImageMaster.INTENT_ATTACK_DEFEND;
/*      */         return;
/*      */       case BUFF:
/*  376 */         this.intentTip.header = TEXT[10];
/*  377 */         this.intentTip.body = TEXT[19];
/*  378 */         this.intentTip.img = ImageMaster.INTENT_BUFF;
/*      */         return;
/*      */       case DEBUFF:
/*  381 */         this.intentTip.header = TEXT[10];
/*  382 */         this.intentTip.body = TEXT[20];
/*  383 */         this.intentTip.img = ImageMaster.INTENT_DEBUFF;
/*      */         return;
/*      */       case STRONG_DEBUFF:
/*  386 */         this.intentTip.header = TEXT[10];
/*  387 */         this.intentTip.body = TEXT[21];
/*  388 */         this.intentTip.img = ImageMaster.INTENT_DEBUFF2;
/*      */         return;
/*      */       case DEFEND:
/*  391 */         this.intentTip.header = TEXT[13];
/*  392 */         this.intentTip.body = TEXT[22];
/*  393 */         this.intentTip.img = ImageMaster.INTENT_DEFEND;
/*      */         return;
/*      */       case DEFEND_DEBUFF:
/*  396 */         this.intentTip.header = TEXT[13];
/*  397 */         this.intentTip.body = TEXT[23];
/*  398 */         this.intentTip.img = ImageMaster.INTENT_DEFEND;
/*      */         return;
/*      */       case DEFEND_BUFF:
/*  401 */         this.intentTip.header = TEXT[13];
/*  402 */         this.intentTip.body = TEXT[24];
/*  403 */         this.intentTip.img = ImageMaster.INTENT_DEFEND_BUFF;
/*      */         return;
/*      */       case ESCAPE:
/*  406 */         this.intentTip.header = TEXT[14];
/*  407 */         this.intentTip.body = TEXT[25];
/*  408 */         this.intentTip.img = ImageMaster.INTENT_ESCAPE;
/*      */         return;
/*      */       case MAGIC:
/*  411 */         this.intentTip.header = TEXT[15];
/*  412 */         this.intentTip.body = TEXT[26];
/*  413 */         this.intentTip.img = ImageMaster.INTENT_MAGIC;
/*      */         return;
/*      */       case SLEEP:
/*  416 */         this.intentTip.header = TEXT[16];
/*  417 */         this.intentTip.body = TEXT[27];
/*  418 */         this.intentTip.img = ImageMaster.INTENT_SLEEP;
/*      */         return;
/*      */       case STUN:
/*  421 */         this.intentTip.header = TEXT[17];
/*  422 */         this.intentTip.body = TEXT[28];
/*  423 */         this.intentTip.img = ImageMaster.INTENT_STUN;
/*      */         return;
/*      */       case UNKNOWN:
/*  426 */         this.intentTip.header = TEXT[18];
/*  427 */         this.intentTip.body = TEXT[29];
/*  428 */         this.intentTip.img = ImageMaster.INTENT_UNKNOWN;
/*      */         return;
/*      */       case NONE:
/*  431 */         this.intentTip.header = "";
/*  432 */         this.intentTip.body = "";
/*  433 */         this.intentTip.img = ImageMaster.INTENT_UNKNOWN;
/*      */         return;
/*      */     } 
/*  436 */     this.intentTip.header = "NOT SET";
/*  437 */     this.intentTip.body = "NOT SET";
/*  438 */     this.intentTip.img = ImageMaster.INTENT_UNKNOWN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void heal(int healAmount) {
/*  446 */     if (this.isDying) {
/*      */       return;
/*      */     }
/*      */     
/*  450 */     for (AbstractPower p : this.powers) {
/*  451 */       healAmount = p.onHeal(healAmount);
/*      */     }
/*      */     
/*  454 */     this.currentHealth += healAmount;
/*  455 */     if (this.currentHealth > this.maxHealth) {
/*  456 */       this.currentHealth = this.maxHealth;
/*      */     }
/*      */     
/*  459 */     if (healAmount > 0) {
/*  460 */       AbstractDungeon.effectList.add(new HealEffect(this.hb.cX - this.animX, this.hb.cY, healAmount));
/*  461 */       healthBarUpdatedEvent();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flashIntent() {
/*  469 */     if (this.intentImg != null) {
/*  470 */       AbstractDungeon.effectList.add(new FlashIntentEffect(this.intentImg, this));
/*      */     }
/*  472 */     this.intentAlphaTarget = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createIntent() {
/*  479 */     this.intent = this.move.intent;
/*  480 */     this.intentParticleTimer = 0.5F;
/*  481 */     this.nextMove = this.move.nextMove;
/*  482 */     this.intentBaseDmg = this.move.baseDamage;
/*      */     
/*  484 */     if (this.move.baseDamage > -1) {
/*  485 */       calculateDamage(this.intentBaseDmg);
/*  486 */       if (this.move.isMultiDamage) {
/*  487 */         this.intentMultiAmt = this.move.multiplier;
/*  488 */         this.isMultiDmg = true;
/*      */       } else {
/*  490 */         this.intentMultiAmt = -1;
/*  491 */         this.isMultiDmg = false;
/*      */       } 
/*      */     } 
/*      */     
/*  495 */     this.intentImg = getIntentImg();
/*  496 */     this.intentBg = getIntentBg();
/*  497 */     this.tipIntent = this.intent;
/*  498 */     this.intentAlpha = 0.0F;
/*  499 */     this.intentAlphaTarget = 1.0F;
/*  500 */     updateIntentTip();
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
/*      */   public void setMove(String moveName, byte nextMove, Intent intent, int baseDamage, int multiplier, boolean isMultiDamage) {
/*  512 */     this.moveName = moveName;
/*      */ 
/*      */     
/*  515 */     if (nextMove != -1) {
/*  516 */       this.moveHistory.add(Byte.valueOf(nextMove));
/*      */     }
/*      */     
/*  519 */     this.move = new EnemyMoveInfo(nextMove, intent, baseDamage, multiplier, isMultiDamage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMove(byte nextMove, Intent intent, int baseDamage, int multiplier, boolean isMultiDamage) {
/*  526 */     setMove((String)null, nextMove, intent, baseDamage, multiplier, isMultiDamage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMove(byte nextMove, Intent intent, int baseDamage) {
/*  533 */     setMove((String)null, nextMove, intent, baseDamage, 0, false);
/*      */   }
/*      */   
/*      */   public void setMove(String moveName, byte nextMove, Intent intent, int baseDamage) {
/*  537 */     setMove(moveName, nextMove, intent, baseDamage, 0, false);
/*      */   }
/*      */   
/*      */   public void setMove(String moveName, byte nextMove, Intent intent) {
/*  541 */     if (intent == Intent.ATTACK || intent == Intent.ATTACK_BUFF || intent == Intent.ATTACK_DEFEND || intent == Intent.ATTACK_DEBUFF) {
/*      */       
/*  543 */       for (int i = 0; i < 8; i++) {
/*  544 */         AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(
/*      */               
/*  546 */               MathUtils.random(Settings.WIDTH * 0.25F, Settings.WIDTH * 0.75F), 
/*  547 */               MathUtils.random(Settings.HEIGHT * 0.25F, Settings.HEIGHT * 0.75F), "ENEMY MOVE " + moveName + " IS SET INCORRECTLY! REPORT TO DEV", Color.RED
/*      */               
/*  549 */               .cpy()));
/*      */       }
/*  551 */       logger.info("ENEMY MOVE " + moveName + " IS SET INCORRECTLY! REPORT TO DEV");
/*      */     } 
/*  553 */     setMove(moveName, nextMove, intent, -1, 0, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMove(byte nextMove, Intent intent) {
/*  560 */     setMove((String)null, nextMove, intent, -1, 0, false);
/*      */   }
/*      */   
/*      */   public void rollMove() {
/*  564 */     getMove(AbstractDungeon.aiRng.random(99));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean lastMove(byte move) {
/*  574 */     if (this.moveHistory.isEmpty()) {
/*  575 */       return false;
/*      */     }
/*      */     
/*  578 */     if (((Byte)this.moveHistory.get(this.moveHistory.size() - 1)).byteValue() == move) {
/*  579 */       return true;
/*      */     }
/*      */     
/*  582 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean lastMoveBefore(byte move) {
/*  586 */     if (this.moveHistory.isEmpty()) {
/*  587 */       return false;
/*      */     }
/*      */     
/*  590 */     if (this.moveHistory.size() < 2) {
/*  591 */       return false;
/*      */     }
/*      */     
/*  594 */     if (((Byte)this.moveHistory.get(this.moveHistory.size() - 2)).byteValue() == move) {
/*  595 */       return true;
/*      */     }
/*      */     
/*  598 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean lastTwoMoves(byte move) {
/*  608 */     if (this.moveHistory.size() < 2) {
/*  609 */       return false;
/*      */     }
/*      */     
/*  612 */     if (((Byte)this.moveHistory.get(this.moveHistory.size() - 1)).byteValue() == move && ((Byte)this.moveHistory.get(this.moveHistory.size() - 2)).byteValue() == move) {
/*  613 */       return true;
/*      */     }
/*      */     
/*  616 */     return false;
/*      */   }
/*      */   
/*      */   private Texture getIntentImg() {
/*  620 */     switch (this.intent) {
/*      */       case ATTACK:
/*  622 */         return getAttackIntent();
/*      */       case ATTACK_BUFF:
/*  624 */         return getAttackIntent();
/*      */       case ATTACK_DEBUFF:
/*  626 */         return getAttackIntent();
/*      */       case ATTACK_DEFEND:
/*  628 */         return getAttackIntent();
/*      */       case BUFF:
/*  630 */         return ImageMaster.INTENT_BUFF_L;
/*      */       case DEBUFF:
/*  632 */         return ImageMaster.INTENT_DEBUFF_L;
/*      */       case STRONG_DEBUFF:
/*  634 */         return ImageMaster.INTENT_DEBUFF2_L;
/*      */       case DEFEND:
/*  636 */         return ImageMaster.INTENT_DEFEND_L;
/*      */       case DEFEND_DEBUFF:
/*  638 */         return ImageMaster.INTENT_DEFEND_L;
/*      */       case DEFEND_BUFF:
/*  640 */         return ImageMaster.INTENT_DEFEND_BUFF_L;
/*      */       case ESCAPE:
/*  642 */         return ImageMaster.INTENT_ESCAPE_L;
/*      */       case MAGIC:
/*  644 */         return ImageMaster.INTENT_MAGIC_L;
/*      */       case SLEEP:
/*  646 */         return ImageMaster.INTENT_SLEEP_L;
/*      */       case STUN:
/*  648 */         return null;
/*      */       case UNKNOWN:
/*  650 */         return ImageMaster.INTENT_UNKNOWN_L;
/*      */     } 
/*  652 */     return ImageMaster.INTENT_UNKNOWN_L;
/*      */   }
/*      */ 
/*      */   
/*      */   private Texture getIntentBg() {
/*  657 */     switch (this.intent) {
/*      */       case ATTACK_DEFEND:
/*  659 */         return null;
/*      */     } 
/*  661 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Texture getAttackIntent(int dmg) {
/*  666 */     if (dmg < 5)
/*  667 */       return ImageMaster.INTENT_ATK_1; 
/*  668 */     if (dmg < 10)
/*  669 */       return ImageMaster.INTENT_ATK_2; 
/*  670 */     if (dmg < 15)
/*  671 */       return ImageMaster.INTENT_ATK_3; 
/*  672 */     if (dmg < 20)
/*  673 */       return ImageMaster.INTENT_ATK_4; 
/*  674 */     if (dmg < 25)
/*  675 */       return ImageMaster.INTENT_ATK_5; 
/*  676 */     if (dmg < 30) {
/*  677 */       return ImageMaster.INTENT_ATK_6;
/*      */     }
/*  679 */     return ImageMaster.INTENT_ATK_7;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Texture getAttackIntent() {
/*      */     int tmp;
/*  685 */     if (this.isMultiDmg) {
/*  686 */       tmp = this.intentDmg * this.intentMultiAmt;
/*      */     } else {
/*  688 */       tmp = this.intentDmg;
/*      */     } 
/*      */     
/*  691 */     if (tmp < 5)
/*  692 */       return ImageMaster.INTENT_ATK_1; 
/*  693 */     if (tmp < 10)
/*  694 */       return ImageMaster.INTENT_ATK_2; 
/*  695 */     if (tmp < 15)
/*  696 */       return ImageMaster.INTENT_ATK_3; 
/*  697 */     if (tmp < 20)
/*  698 */       return ImageMaster.INTENT_ATK_4; 
/*  699 */     if (tmp < 25)
/*  700 */       return ImageMaster.INTENT_ATK_5; 
/*  701 */     if (tmp < 30) {
/*  702 */       return ImageMaster.INTENT_ATK_6;
/*      */     }
/*  704 */     return ImageMaster.INTENT_ATK_7;
/*      */   }
/*      */ 
/*      */   
/*      */   private Texture getAttackIntentTip() {
/*      */     int tmp;
/*  710 */     if (this.isMultiDmg) {
/*  711 */       tmp = this.intentDmg * this.intentMultiAmt;
/*      */     } else {
/*  713 */       tmp = this.intentDmg;
/*      */     } 
/*      */     
/*  716 */     if (tmp < 5)
/*  717 */       return ImageMaster.INTENT_ATK_TIP_1; 
/*  718 */     if (tmp < 10)
/*  719 */       return ImageMaster.INTENT_ATK_TIP_2; 
/*  720 */     if (tmp < 15)
/*  721 */       return ImageMaster.INTENT_ATK_TIP_3; 
/*  722 */     if (tmp < 20)
/*  723 */       return ImageMaster.INTENT_ATK_TIP_4; 
/*  724 */     if (tmp < 25)
/*  725 */       return ImageMaster.INTENT_ATK_TIP_5; 
/*  726 */     if (tmp < 30) {
/*  727 */       return ImageMaster.INTENT_ATK_TIP_6;
/*      */     }
/*  729 */     return ImageMaster.INTENT_ATK_TIP_7;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void damage(DamageInfo info) {
/*  739 */     if (info.output > 0 && hasPower("IntangiblePlayer")) {
/*  740 */       info.output = 1;
/*      */     }
/*  742 */     int damageAmount = info.output;
/*      */ 
/*      */     
/*  745 */     if (this.isDying || this.isEscaping) {
/*      */       return;
/*      */     }
/*      */     
/*  749 */     if (damageAmount < 0) {
/*  750 */       damageAmount = 0;
/*      */     }
/*  752 */     boolean hadBlock = true;
/*  753 */     if (this.currentBlock == 0) {
/*  754 */       hadBlock = false;
/*      */     }
/*      */     
/*  757 */     boolean weakenedToZero = (damageAmount == 0);
/*      */ 
/*      */     
/*  760 */     damageAmount = decrementBlock(info, damageAmount);
/*      */ 
/*      */     
/*  763 */     if (info.owner == AbstractDungeon.player) {
/*  764 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  765 */         damageAmount = r.onAttackToChangeDamage(info, damageAmount);
/*      */       }
/*      */     }
/*  768 */     if (info.owner != null) {
/*  769 */       for (AbstractPower p : info.owner.powers) {
/*  770 */         damageAmount = p.onAttackToChangeDamage(info, damageAmount);
/*      */       }
/*      */     }
/*  773 */     for (AbstractPower p : this.powers) {
/*  774 */       damageAmount = p.onAttackedToChangeDamage(info, damageAmount);
/*      */     }
/*      */     
/*  777 */     if (info.owner == AbstractDungeon.player) {
/*  778 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  779 */         r.onAttack(info, damageAmount, this);
/*      */       }
/*      */     }
/*      */     
/*  783 */     for (AbstractPower p : this.powers) {
/*  784 */       p.wasHPLost(info, damageAmount);
/*      */     }
/*      */ 
/*      */     
/*  788 */     if (info.owner != null) {
/*  789 */       for (AbstractPower p : info.owner.powers) {
/*  790 */         p.onAttack(info, damageAmount, this);
/*      */       }
/*      */     }
/*      */     
/*  794 */     for (AbstractPower p : this.powers) {
/*  795 */       damageAmount = p.onAttacked(info, damageAmount);
/*      */     }
/*      */     
/*  798 */     this.lastDamageTaken = Math.min(damageAmount, this.currentHealth);
/*      */ 
/*      */     
/*  801 */     boolean probablyInstantKill = (this.currentHealth == 0);
/*      */     
/*  803 */     if (damageAmount > 0) {
/*  804 */       if (info.owner != this) {
/*  805 */         useStaggerAnimation();
/*      */       }
/*      */       
/*  808 */       if (damageAmount >= 99 && !CardCrawlGame.overkill) {
/*  809 */         CardCrawlGame.overkill = true;
/*      */       }
/*      */       
/*  812 */       this.currentHealth -= damageAmount;
/*  813 */       if (!probablyInstantKill) {
/*  814 */         AbstractDungeon.effectList.add(new StrikeEffect(this, this.hb.cX, this.hb.cY, damageAmount));
/*      */       }
/*  816 */       if (this.currentHealth < 0) {
/*  817 */         this.currentHealth = 0;
/*      */       }
/*  819 */       healthBarUpdatedEvent();
/*  820 */     } else if (!probablyInstantKill) {
/*  821 */       if (weakenedToZero && this.currentBlock == 0) {
/*  822 */         if (hadBlock) {
/*  823 */           AbstractDungeon.effectList.add(new BlockedWordEffect(this, this.hb.cX, this.hb.cY, TEXT[30]));
/*      */         } else {
/*  825 */           AbstractDungeon.effectList.add(new StrikeEffect(this, this.hb.cX, this.hb.cY, 0));
/*      */         } 
/*  827 */       } else if (Settings.SHOW_DMG_BLOCK) {
/*  828 */         AbstractDungeon.effectList.add(new BlockedWordEffect(this, this.hb.cX, this.hb.cY, TEXT[30]));
/*      */       } 
/*      */     } 
/*      */     
/*  832 */     if (this.currentHealth <= 0) {
/*  833 */       die();
/*  834 */       if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/*  835 */         AbstractDungeon.actionManager.cleanCardQueue();
/*  836 */         AbstractDungeon.effectList.add(new DeckPoofEffect(64.0F * Settings.scale, 64.0F * Settings.scale, true));
/*  837 */         AbstractDungeon.effectList.add(new DeckPoofEffect(Settings.WIDTH - 64.0F * Settings.scale, 64.0F * Settings.scale, false));
/*      */         
/*  839 */         AbstractDungeon.overlayMenu.hideCombatPanels();
/*      */       } 
/*  841 */       if (this.currentBlock > 0) {
/*  842 */         loseBlock();
/*  843 */         AbstractDungeon.effectList.add(new HbBlockBrokenEffect(this.hb.cX - this.hb.width / 2.0F + BLOCK_ICON_X, this.hb.cY - this.hb.height / 2.0F + BLOCK_ICON_Y));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void init() {
/*  852 */     rollMove();
/*  853 */     healthBarUpdatedEvent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(SpriteBatch sb) {
/*  862 */     if (!this.isDead && !this.escaped) {
/*  863 */       if (this.atlas == null) {
/*  864 */         sb.setColor(this.tint.color);
/*  865 */         if (this.img != null) {
/*  866 */           sb.draw(this.img, this.drawX - this.img
/*      */               
/*  868 */               .getWidth() * Settings.scale / 2.0F + this.animX, this.drawY + this.animY, this.img
/*      */               
/*  870 */               .getWidth() * Settings.scale, this.img
/*  871 */               .getHeight() * Settings.scale, 0, 0, this.img
/*      */ 
/*      */               
/*  874 */               .getWidth(), this.img
/*  875 */               .getHeight(), this.flipHorizontal, this.flipVertical);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  880 */         this.state.update(Gdx.graphics.getDeltaTime());
/*  881 */         this.state.apply(this.skeleton);
/*  882 */         this.skeleton.updateWorldTransform();
/*  883 */         this.skeleton.setPosition(this.drawX + this.animX, this.drawY + this.animY);
/*  884 */         this.skeleton.setColor(this.tint.color);
/*  885 */         this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*  886 */         sb.end();
/*  887 */         CardCrawlGame.psb.begin();
/*  888 */         sr.draw(CardCrawlGame.psb, this.skeleton);
/*  889 */         CardCrawlGame.psb.end();
/*  890 */         sb.begin();
/*  891 */         sb.setBlendFunction(770, 771);
/*      */       } 
/*      */       
/*  894 */       if (this == (AbstractDungeon.getCurrRoom()).monsters.hoveredMonster && 
/*  895 */         this.atlas == null) {
/*  896 */         sb.setBlendFunction(770, 1);
/*  897 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.1F));
/*  898 */         if (this.img != null) {
/*  899 */           sb.draw(this.img, this.drawX - this.img
/*      */               
/*  901 */               .getWidth() * Settings.scale / 2.0F + this.animX, this.drawY + this.animY, this.img
/*      */               
/*  903 */               .getWidth() * Settings.scale, this.img
/*  904 */               .getHeight() * Settings.scale, 0, 0, this.img
/*      */ 
/*      */               
/*  907 */               .getWidth(), this.img
/*  908 */               .getHeight(), this.flipHorizontal, this.flipVertical);
/*      */ 
/*      */           
/*  911 */           sb.setBlendFunction(770, 771);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  916 */       if (!this.isDying && !this.isEscaping && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.player.isDead)
/*      */       {
/*  918 */         if (!AbstractDungeon.player.hasRelic("Runic Dome") && this.intent != Intent.NONE && !Settings.hideCombatElements) {
/*      */           
/*  920 */           renderIntentVfxBehind(sb);
/*  921 */           renderIntent(sb);
/*  922 */           renderIntentVfxAfter(sb);
/*  923 */           renderDamageRange(sb);
/*      */         } 
/*      */       }
/*      */       
/*  927 */       this.hb.render(sb);
/*  928 */       this.intentHb.render(sb);
/*  929 */       this.healthHb.render(sb);
/*      */     } 
/*      */     
/*  932 */     if (!AbstractDungeon.player.isDead) {
/*  933 */       renderHealth(sb);
/*  934 */       renderName(sb);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void setHp(int minHp, int maxHp) {
/*  939 */     this.currentHealth = AbstractDungeon.monsterHpRng.random(minHp, maxHp);
/*  940 */     if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
/*  941 */       float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
/*  942 */       this.currentHealth = (int)(this.currentHealth * mod);
/*      */     } 
/*  944 */     if (ModHelper.isModEnabled("MonsterHunter")) {
/*  945 */       this.currentHealth = (int)(this.currentHealth * 1.5F);
/*      */     }
/*  947 */     this.maxHealth = this.currentHealth;
/*      */   }
/*      */   
/*      */   protected void setHp(int hp) {
/*  951 */     setHp(hp, hp);
/*      */   }
/*      */   
/*      */   private void renderDamageRange(SpriteBatch sb) {
/*  955 */     if (this.intent.name().contains("ATTACK")) {
/*  956 */       if (this.isMultiDmg) {
/*  957 */         FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */             
/*  960 */             Integer.toString(this.intentDmg) + "x" + Integer.toString(this.intentMultiAmt), this.intentHb.cX - 30.0F * Settings.scale, this.intentHb.cY + this.bobEffect.y - 12.0F * Settings.scale, this.intentColor);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  965 */         FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */             
/*  968 */             Integer.toString(this.intentDmg), this.intentHb.cX - 30.0F * Settings.scale, this.intentHb.cY + this.bobEffect.y - 12.0F * Settings.scale, this.intentColor);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderIntentVfxBehind(SpriteBatch sb) {
/*  977 */     for (AbstractGameEffect e : this.intentVfx) {
/*  978 */       if (e.renderBehind) {
/*  979 */         e.render(sb);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderIntentVfxAfter(SpriteBatch sb) {
/*  985 */     for (AbstractGameEffect e : this.intentVfx) {
/*  986 */       if (!e.renderBehind) {
/*  987 */         e.render(sb);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderName(SpriteBatch sb) {
/*  993 */     if (!this.hb.hovered) {
/*  994 */       this.hoverTimer = MathHelper.fadeLerpSnap(this.hoverTimer, 0.0F);
/*      */     } else {
/*  996 */       this.hoverTimer += Gdx.graphics.getDeltaTime();
/*      */     } 
/*      */     
/*  999 */     if ((!AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.hoveredCard == null || AbstractDungeon.player.hoveredCard.target == AbstractCard.CardTarget.ENEMY) && !this.isDying) {
/*      */ 
/*      */       
/* 1002 */       if (this.hoverTimer != 0.0F) {
/* 1003 */         if (this.hoverTimer * 2.0F > 1.0F) {
/* 1004 */           this.nameColor.a = 1.0F;
/*      */         } else {
/* 1006 */           this.nameColor.a = this.hoverTimer * 2.0F;
/*      */         } 
/*      */       } else {
/* 1009 */         this.nameColor.a = MathHelper.slowColorLerpSnap(this.nameColor.a, 0.0F);
/*      */       } 
/*      */       
/* 1012 */       float tmp = Interpolation.exp5Out.apply(1.5F, 2.0F, this.hoverTimer);
/* 1013 */       this.nameColor.r = Interpolation.fade.apply(Color.DARK_GRAY.r, Settings.CREAM_COLOR.r, this.hoverTimer * 10.0F);
/* 1014 */       this.nameColor.g = Interpolation.fade.apply(Color.DARK_GRAY.g, Settings.CREAM_COLOR.g, this.hoverTimer * 3.0F);
/* 1015 */       this.nameColor.b = Interpolation.fade.apply(Color.DARK_GRAY.b, Settings.CREAM_COLOR.b, this.hoverTimer * 3.0F);
/*      */       
/* 1017 */       float y = Interpolation.exp10Out.apply(this.healthHb.cY, this.healthHb.cY - 8.0F * Settings.scale, this.nameColor.a);
/* 1018 */       float x = this.hb.cX - this.animX;
/*      */       
/* 1020 */       this.nameBgColor.a = this.nameColor.a / 2.0F * this.hbAlpha;
/* 1021 */       sb.setColor(this.nameBgColor);
/* 1022 */       TextureAtlas.AtlasRegion img = ImageMaster.MOVE_NAME_BG;
/* 1023 */       sb.draw((TextureRegion)img, x - img.packedWidth / 2.0F, y - img.packedHeight / 2.0F, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, Settings.scale * tmp, Settings.scale * 2.0F, 0.0F);
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
/* 1035 */       this.nameColor.a *= this.hbAlpha;
/* 1036 */       FontHelper.renderFontCentered(sb, FontHelper.tipHeaderFont, this.name, x, y, this.nameColor);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderIntent(SpriteBatch sb) {
/* 1041 */     this.intentColor.a = this.intentAlpha;
/* 1042 */     sb.setColor(this.intentColor);
/*      */     
/* 1044 */     if (this.intentBg != null) {
/* 1045 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.intentAlpha / 2.0F));
/* 1046 */       if (Settings.isMobile) {
/* 1047 */         sb.draw(this.intentBg, this.intentHb.cX - 64.0F, this.intentHb.cY - 64.0F + this.bobEffect.y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 1.2F, Settings.scale * 1.2F, 0.0F, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1065 */         sb.draw(this.intentBg, this.intentHb.cX - 64.0F, this.intentHb.cY - 64.0F + this.bobEffect.y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
/*      */       } 
/*      */     } 
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
/* 1086 */     if (this.intentImg != null && this.intent != Intent.UNKNOWN && this.intent != Intent.STUN) {
/* 1087 */       if (this.intent == Intent.DEBUFF || this.intent == Intent.STRONG_DEBUFF) {
/* 1088 */         this.intentAngle += Gdx.graphics.getDeltaTime() * 150.0F;
/*      */       } else {
/* 1090 */         this.intentAngle = 0.0F;
/*      */       } 
/*      */       
/* 1093 */       sb.setColor(this.intentColor);
/*      */       
/* 1095 */       if (Settings.isMobile) {
/* 1096 */         sb.draw(this.intentImg, this.intentHb.cX - 64.0F, this.intentHb.cY - 64.0F + this.bobEffect.y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 1.2F, Settings.scale * 1.2F, this.intentAngle, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1114 */         sb.draw(this.intentImg, this.intentHb.cX - 64.0F, this.intentHb.cY - 64.0F + this.bobEffect.y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, this.intentAngle, 0, 0, 128, 128, false, false);
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
/*      */   protected void updateHitbox(float hb_x, float hb_y, float hb_w, float hb_h) {
/* 1136 */     this.hb_w = hb_w * Settings.scale;
/* 1137 */     this.hb_h = hb_h * Settings.xScale;
/* 1138 */     this.hb_y = hb_y * Settings.scale;
/* 1139 */     this.hb_x = hb_x * Settings.scale;
/*      */     
/* 1141 */     this.hb = new Hitbox(this.hb_w, this.hb_h);
/* 1142 */     this.hb.move(this.drawX + this.hb_x + this.animX, this.drawY + this.hb_y + this.hb_h / 2.0F);
/* 1143 */     this.healthHb.move(this.hb.cX, this.hb.cY - this.hb_h / 2.0F - this.healthHb.height / 2.0F);
/* 1144 */     this.intentHb.move(this.hb.cX + this.intentOffsetX, this.hb.cY + this.hb_h / 2.0F + 32.0F * Settings.scale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateDeathAnimation() {
/* 1153 */     if (this.isDying) {
/* 1154 */       this.deathTimer -= Gdx.graphics.getDeltaTime();
/* 1155 */       if (this.deathTimer < 1.8F && !this.tintFadeOutCalled) {
/* 1156 */         this.tintFadeOutCalled = true;
/* 1157 */         this.tint.fadeOut();
/*      */       } 
/*      */     } 
/*      */     
/* 1161 */     if (this.deathTimer < 0.0F) {
/* 1162 */       this.isDead = true;
/* 1163 */       if (AbstractDungeon.getMonsters().areMonstersDead() && !(AbstractDungeon.getCurrRoom()).isBattleOver && 
/* 1164 */         !(AbstractDungeon.getCurrRoom()).cannotLose) {
/* 1165 */         AbstractDungeon.getCurrRoom().endBattle();
/*      */       }
/* 1167 */       dispose();
/* 1168 */       this.powers.clear();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void dispose() {
/* 1173 */     if (this.img != null) {
/* 1174 */       logger.info("Disposed monster img asset");
/* 1175 */       this.img.dispose();
/* 1176 */       this.img = null;
/*      */     } 
/*      */     
/* 1179 */     for (Disposable d : this.disposables) {
/* 1180 */       logger.info("Disposed extra monster assets");
/* 1181 */       d.dispose();
/*      */     } 
/*      */     
/* 1184 */     if (this.atlas != null) {
/* 1185 */       this.atlas.dispose();
/* 1186 */       this.atlas = null;
/* 1187 */       logger.info("Disposed Texture: " + this.name);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateEscapeAnimation() {
/* 1195 */     if (this.escapeTimer != 0.0F) {
/* 1196 */       this.flipHorizontal = true;
/* 1197 */       this.escapeTimer -= Gdx.graphics.getDeltaTime();
/* 1198 */       this.drawX += Gdx.graphics.getDeltaTime() * 400.0F * Settings.scale;
/*      */     } 
/* 1200 */     if (this.escapeTimer < 0.0F) {
/* 1201 */       this.escaped = true;
/* 1202 */       if (AbstractDungeon.getMonsters().areMonstersDead() && !(AbstractDungeon.getCurrRoom()).isBattleOver && 
/* 1203 */         !(AbstractDungeon.getCurrRoom()).cannotLose) {
/* 1204 */         AbstractDungeon.getCurrRoom().endBattle();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void escapeNext() {
/* 1213 */     this.escapeNext = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void deathReact() {}
/*      */ 
/*      */   
/*      */   public void escape() {
/* 1221 */     hideHealthBar();
/* 1222 */     this.isEscaping = true;
/* 1223 */     this.escapeTimer = 3.0F;
/*      */   }
/*      */   
/*      */   public void die() {
/* 1227 */     die(true);
/*      */   }
/*      */   
/*      */   public void die(boolean triggerRelics) {
/* 1231 */     if (!this.isDying) {
/* 1232 */       this.isDying = true;
/* 1233 */       if (this.currentHealth <= 0 && 
/* 1234 */         triggerRelics) {
/* 1235 */         for (AbstractPower p : this.powers) {
/* 1236 */           p.onDeath();
/*      */         }
/*      */       }
/*      */ 
/*      */       
/* 1241 */       if (triggerRelics) {
/* 1242 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 1243 */           r.onMonsterDeath(this);
/*      */         }
/*      */       }
/*      */ 
/*      */       
/* 1248 */       if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 1249 */         AbstractDungeon.overlayMenu.endTurnButton.disable();
/*      */         
/* 1251 */         for (AbstractCard c : AbstractDungeon.player.limbo.group) {
/* 1252 */           AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
/*      */         }
/* 1254 */         AbstractDungeon.player.limbo.clear();
/*      */       } 
/*      */       
/* 1257 */       if (this.currentHealth < 0) {
/* 1258 */         this.currentHealth = 0;
/*      */       }
/*      */       
/* 1261 */       if (!Settings.FAST_MODE) {
/* 1262 */         this.deathTimer += 1.8F;
/*      */       } else {
/* 1264 */         this.deathTimer++;
/*      */       } 
/*      */       
/* 1267 */       StatsScreen.incrementEnemySlain();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void usePreBattleAction() {}
/*      */ 
/*      */   
/*      */   public void useUniversalPreBattleAction() {
/* 1276 */     if (ModHelper.isModEnabled("Lethality")) {
/* 1277 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new StrengthPower(this, 3), 3));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1282 */     for (AbstractBlight b : AbstractDungeon.player.blights) {
/* 1283 */       b.onCreateEnemy(this);
/*      */     }
/*      */ 
/*      */     
/* 1287 */     if (ModHelper.isModEnabled("Time Dilation") && !this.id.equals("GiantHead")) {
/* 1288 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(this, this, (AbstractPower)new SlowPower(this, 0)));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void calculateDamage(int dmg) {
/* 1294 */     AbstractPlayer target = AbstractDungeon.player;
/* 1295 */     float tmp = dmg;
/*      */ 
/*      */     
/* 1298 */     if (Settings.isEndless && AbstractDungeon.player.hasBlight("DeadlyEnemies")) {
/* 1299 */       float mod = AbstractDungeon.player.getBlight("DeadlyEnemies").effectFloat();
/* 1300 */       tmp *= mod;
/*      */     } 
/*      */ 
/*      */     
/* 1304 */     for (AbstractPower p : this.powers) {
/* 1305 */       tmp = p.atDamageGive(tmp, DamageInfo.DamageType.NORMAL);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1311 */     for (AbstractPower p : target.powers) {
/* 1312 */       tmp = p.atDamageReceive(tmp, DamageInfo.DamageType.NORMAL);
/*      */     }
/*      */ 
/*      */     
/* 1316 */     tmp = AbstractDungeon.player.stance.atDamageReceive(tmp, DamageInfo.DamageType.NORMAL);
/*      */ 
/*      */     
/* 1319 */     if (applyBackAttack()) {
/* 1320 */       tmp = (int)(tmp * 1.5F);
/*      */     }
/*      */ 
/*      */     
/* 1324 */     for (AbstractPower p : this.powers) {
/* 1325 */       tmp = p.atDamageFinalGive(tmp, DamageInfo.DamageType.NORMAL);
/*      */     }
/*      */ 
/*      */     
/* 1329 */     for (AbstractPower p : target.powers) {
/* 1330 */       tmp = p.atDamageFinalReceive(tmp, DamageInfo.DamageType.NORMAL);
/*      */     }
/*      */ 
/*      */     
/* 1334 */     dmg = MathUtils.floor(tmp);
/* 1335 */     if (dmg < 0) {
/* 1336 */       dmg = 0;
/*      */     }
/*      */ 
/*      */     
/* 1340 */     this.intentDmg = dmg;
/*      */   }
/*      */   
/*      */   public void applyPowers() {
/* 1344 */     boolean applyBackAttack = applyBackAttack();
/*      */     
/* 1346 */     if (applyBackAttack && !hasPower("BackAttack")) {
/* 1347 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new ApplyPowerAction(this, null, (AbstractPower)new BackAttackPower(this)));
/*      */     }
/*      */     
/* 1350 */     for (DamageInfo dmg : this.damage) {
/* 1351 */       dmg.applyPowers(this, (AbstractCreature)AbstractDungeon.player);
/* 1352 */       if (applyBackAttack) {
/* 1353 */         dmg.output = (int)(dmg.output * 1.5F);
/*      */       }
/*      */     } 
/*      */     
/* 1357 */     if (this.move.baseDamage > -1) {
/* 1358 */       calculateDamage(this.move.baseDamage);
/*      */     }
/*      */     
/* 1361 */     this.intentImg = getIntentImg();
/* 1362 */     updateIntentTip();
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean applyBackAttack() {
/* 1367 */     if (AbstractDungeon.player.hasPower("Surrounded") && ((
/* 1368 */       AbstractDungeon.player.flipHorizontal && AbstractDungeon.player.drawX < this.drawX) || (!AbstractDungeon.player.flipHorizontal && AbstractDungeon.player.drawX > this.drawX)))
/*      */     {
/* 1370 */       return true;
/*      */     }
/*      */     
/* 1373 */     return false;
/*      */   }
/*      */   
/*      */   public void removeSurroundedPower() {
/* 1377 */     if (hasPower("BackAttack")) {
/* 1378 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this, null, "BackAttack"));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void changeState(String stateName) {}
/*      */   
/*      */   public void addToBot(AbstractGameAction action) {
/* 1386 */     AbstractDungeon.actionManager.addToBottom(action);
/*      */   }
/*      */   
/*      */   public void addToTop(AbstractGameAction action) {
/* 1390 */     AbstractDungeon.actionManager.addToTop(action);
/*      */   }
/*      */   
/*      */   protected void onBossVictoryLogic() {
/* 1394 */     if (Settings.FAST_MODE) {
/* 1395 */       this.deathTimer += 0.7F;
/*      */     } else {
/* 1397 */       this.deathTimer += 1.5F;
/*      */     } 
/* 1399 */     AbstractDungeon.scene.fadeInAmbiance();
/*      */     
/* 1401 */     if ((AbstractDungeon.getCurrRoom()).event == null) {
/* 1402 */       AbstractDungeon.bossCount++;
/* 1403 */       StatsScreen.incrementBossSlain();
/*      */ 
/*      */       
/* 1406 */       if (GameActionManager.turn <= 1) {
/* 1407 */         UnlockTracker.unlockAchievement("YOU_ARE_NOTHING");
/*      */       }
/*      */ 
/*      */       
/* 1411 */       if (GameActionManager.damageReceivedThisCombat - GameActionManager.hpLossThisCombat <= 0) {
/* 1412 */         UnlockTracker.unlockAchievement("PERFECT");
/* 1413 */         CardCrawlGame.perfect++;
/*      */       } 
/*      */     } 
/*      */     
/* 1417 */     CardCrawlGame.music.silenceTempBgmInstantly();
/* 1418 */     CardCrawlGame.music.silenceBGMInstantly();
/* 1419 */     playBossStinger();
/*      */     
/* 1421 */     for (AbstractBlight b : AbstractDungeon.player.blights) {
/* 1422 */       b.onBossDefeat();
/*      */     }
/*      */   }
/*      */   
/*      */   protected void onFinalBossVictoryLogic() {
/* 1427 */     if (AbstractDungeon.ascensionLevel < 20 || AbstractDungeon.bossList.size() != 2)
/*      */     {
/*      */       
/* 1430 */       if (!Settings.isEndless) {
/* 1431 */         if (!Settings.isFinalActAvailable || !Settings.hasRubyKey || !Settings.hasEmeraldKey || !Settings.hasSapphireKey)
/*      */         {
/*      */ 
/*      */           
/* 1435 */           CardCrawlGame.stopClock = true;
/*      */         }
/*      */ 
/*      */         
/* 1439 */         if (CardCrawlGame.playtime <= 1200.0F) {
/* 1440 */           UnlockTracker.unlockAchievement("SPEED_CLIMBER");
/*      */         }
/*      */ 
/*      */         
/* 1444 */         if (AbstractDungeon.player.masterDeck.size() <= 5) {
/* 1445 */           UnlockTracker.unlockAchievement("MINIMALIST");
/*      */         }
/*      */ 
/*      */         
/* 1449 */         boolean commonSenseUnlocked = true;
/* 1450 */         for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 1451 */           if (c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE) {
/* 1452 */             commonSenseUnlocked = false;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1456 */         if (commonSenseUnlocked) {
/* 1457 */           UnlockTracker.unlockAchievement("COMMON_SENSE");
/*      */         }
/*      */ 
/*      */         
/* 1461 */         if (AbstractDungeon.player.relics.size() == 1) {
/* 1462 */           UnlockTracker.unlockAchievement("ONE_RELIC");
/*      */         }
/*      */ 
/*      */         
/* 1466 */         if (Settings.isDailyRun && !Settings.seedSet) {
/* 1467 */           UnlockTracker.unlockLuckyDay();
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static void playBossStinger() {
/* 1474 */     CardCrawlGame.sound.play("BOSS_VICTORY_STINGER");
/*      */     
/* 1476 */     if (AbstractDungeon.id.equals("TheEnding")) {
/* 1477 */       CardCrawlGame.music.playTempBgmInstantly("STS_EndingStinger_v1.ogg", false);
/*      */     } else {
/* 1479 */       switch (MathUtils.random(0, 3)) {
/*      */         case 0:
/* 1481 */           CardCrawlGame.music.playTempBgmInstantly("STS_BossVictoryStinger_1_v3_MUSIC.ogg", false);
/*      */           return;
/*      */         case 1:
/* 1484 */           CardCrawlGame.music.playTempBgmInstantly("STS_BossVictoryStinger_2_v3_MUSIC.ogg", false);
/*      */           return;
/*      */         case 2:
/* 1487 */           CardCrawlGame.music.playTempBgmInstantly("STS_BossVictoryStinger_3_v3_MUSIC.ogg", false);
/*      */           return;
/*      */         case 3:
/* 1490 */           CardCrawlGame.music.playTempBgmInstantly("STS_BossVictoryStinger_4_v3_MUSIC.ogg", false);
/*      */           return;
/*      */       } 
/* 1493 */       logger.info("[ERROR] Attempted to play boss stinger but failed.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public HashMap<String, Serializable> getLocStrings() {
/* 1500 */     HashMap<String, Serializable> data = new HashMap<>();
/* 1501 */     data.put("name", this.name);
/* 1502 */     data.put("moves", MOVES);
/* 1503 */     data.put("dialogs", DIALOG);
/* 1504 */     return data;
/*      */   }
/*      */   
/*      */   public int getIntentDmg() {
/* 1508 */     return this.intentDmg;
/*      */   }
/*      */   
/*      */   public int getIntentBaseDmg() {
/* 1512 */     return this.intentBaseDmg;
/*      */   }
/*      */   
/*      */   public void setIntentBaseDmg(int amount) {
/* 1516 */     this.intentBaseDmg = amount;
/*      */   }
/*      */   
/*      */   public abstract void takeTurn();
/*      */   
/*      */   protected abstract void getMove(int paramInt);
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\AbstractMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */