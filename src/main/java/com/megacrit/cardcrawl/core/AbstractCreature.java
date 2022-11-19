/*      */ package com.megacrit.cardcrawl.core;
/*      */ 
/*      */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.TintEffect;
import com.megacrit.cardcrawl.vfx.combat.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
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
/*      */ public abstract class AbstractCreature
/*      */ {
/*   54 */   private static final Logger logger = LogManager.getLogger(AbstractCreature.class.getName()); public String name;
/*      */   public String id;
/*   56 */   public ArrayList<AbstractPower> powers = new ArrayList<>(); public boolean isPlayer; public boolean isBloodied;
/*      */   public float drawX;
/*      */   public float drawY;
/*      */   public float dialogX;
/*      */   public float dialogY;
/*      */   public Hitbox hb;
/*      */   public int gold;
/*      */   public int displayGold;
/*      */   public boolean isDying = false, isDead = false, halfDead = false;
/*      */   public boolean flipHorizontal = false;
/*      */   public boolean flipVertical = false;
/*   67 */   public float escapeTimer = 0.0F;
/*      */   
/*      */   public boolean isEscaping = false;
/*      */   
/*   71 */   protected static final float TIP_X_THRESHOLD = 1544.0F * Settings.scale;
/*   72 */   protected static final float MULTI_TIP_Y_OFFSET = 80.0F * Settings.scale;
/*   73 */   protected static final float TIP_OFFSET_R_X = 20.0F * Settings.scale;
/*   74 */   protected static final float TIP_OFFSET_L_X = -380.0F * Settings.scale;
/*   75 */   protected static final float TIP_OFFSET_Y = 80.0F * Settings.scale;
/*   76 */   protected ArrayList<PowerTip> tips = new ArrayList<>();
/*      */ 
/*      */   
/*   79 */   private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractCreature");
/*   80 */   public static final String[] TEXT = uiStrings.TEXT;
/*      */   
/*      */   public Hitbox healthHb;
/*   83 */   private float healthHideTimer = 0.0F;
/*   84 */   public int lastDamageTaken = 0;
/*      */   public float hb_x;
/*      */   public float hb_y;
/*      */   public float hb_w;
/*      */   public float hb_h;
/*   89 */   private float hbShowTimer = 0.0F; public int currentHealth; public int maxHealth; public int currentBlock; private float healthBarWidth; private float targetHealthBarWidth;
/*   90 */   private float healthBarAnimTimer = 0.0F;
/*   91 */   private float blockAnimTimer = 0.0F;
/*   92 */   private float blockOffset = 0.0F;
/*   93 */   private float blockScale = 1.0F;
/*   94 */   public float hbAlpha = 0.0F;
/*   95 */   private float hbYOffset = HB_Y_OFFSET_DIST * 5.0F;
/*   96 */   private Color hbBgColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*   97 */   private Color hbShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*   98 */   private Color blockColor = new Color(0.6F, 0.93F, 0.98F, 0.0F);
/*   99 */   private Color blockOutlineColor = new Color(0.6F, 0.93F, 0.98F, 0.0F);
/*  100 */   private Color blockTextColor = new Color(0.9F, 0.9F, 0.9F, 0.0F);
/*  101 */   private Color redHbBarColor = new Color(0.8F, 0.05F, 0.05F, 0.0F);
/*  102 */   private Color greenHbBarColor = Color.valueOf("78c13c00");
/*  103 */   private Color blueHbBarColor = Color.valueOf("31568c00");
/*  104 */   private Color orangeHbBarColor = new Color(1.0F, 0.5F, 0.0F, 0.0F);
/*  105 */   private Color hbTextColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*      */   private static final float BLOCK_ANIM_TIME = 0.7F;
/*  107 */   private static final float BLOCK_OFFSET_DIST = 12.0F * Settings.scale;
/*      */   private static final float SHOW_HB_TIME = 0.7F;
/*  109 */   private static final float HB_Y_OFFSET_DIST = 12.0F * Settings.scale;
/*  110 */   protected static final float BLOCK_ICON_X = -14.0F * Settings.scale;
/*  111 */   protected static final float BLOCK_ICON_Y = -14.0F * Settings.scale;
/*      */   private static final int BLOCK_W = 64;
/*      */   private static final float HEALTH_BAR_PAUSE_DURATION = 1.2F;
/*  114 */   private static final float HEALTH_BAR_HEIGHT = 20.0F * Settings.scale;
/*  115 */   private static final float HEALTH_BAR_OFFSET_Y = -28.0F * Settings.scale;
/*  116 */   private static final float HEALTH_TEXT_OFFSET_Y = 6.0F * Settings.scale;
/*  117 */   private static final float POWER_ICON_PADDING_X = Settings.isMobile ? (55.0F * Settings.scale) : (48.0F * Settings.scale);
/*  118 */   private static final float HEALTH_BG_OFFSET_X = 31.0F * Settings.scale;
/*      */ 
/*      */   
/*  121 */   public TintEffect tint = new TintEffect();
/*      */   public static SkeletonRenderer sr;
/*      */   private boolean shakeToggle = true;
/*  124 */   private static final float SHAKE_THRESHOLD = Settings.scale * 8.0F; private static final float SHAKE_SPEED = 150.0F * Settings.scale; public float animX; public float animY;
/*      */   protected float vX;
/*      */   protected float vY;
/*      */   protected CreatureAnimation animation;
/*  128 */   protected float animationTimer = 0.0F; protected static final float SLOW_ATTACK_ANIM_DUR = 1.0F; protected static final float STAGGER_ANIM_DUR = 0.3F;
/*      */   protected static final float FAST_ATTACK_ANIM_DUR = 0.4F;
/*      */   protected static final float HOP_ANIM_DURATION = 0.7F;
/*  131 */   private static final float STAGGER_MOVE_SPEED = 20.0F * Settings.scale;
/*  132 */   protected TextureAtlas atlas = null;
/*      */   
/*      */   protected Skeleton skeleton;
/*      */   
/*      */   public AnimationState state;
/*      */   protected AnimationStateData stateData;
/*      */   private static final int RETICLE_W = 36;
/*  139 */   public float reticleAlpha = 0.0F;
/*  140 */   private Color reticleColor = new Color(1.0F, 1.0F, 1.0F, 0.0F); private Color reticleShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*      */   public boolean reticleRendered = false;
/*  142 */   private float reticleOffset = 0.0F;
/*  143 */   private float reticleAnimTimer = 0.0F;
/*  144 */   private static final float RETICLE_OFFSET_DIST = 15.0F * Settings.scale;
/*      */   public abstract void damage(DamageInfo paramDamageInfo);
/*      */   
/*  147 */   public enum CreatureAnimation { FAST_SHAKE, SHAKE, ATTACK_FAST, ATTACK_SLOW, STAGGER, HOP, JUMP; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void brokeBlock() {
/*  153 */     if (this instanceof AbstractMonster) {
/*  154 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  155 */         r.onBlockBroken(this);
/*      */       }
/*      */     }
/*      */     
/*  159 */     AbstractDungeon.effectList.add(new HbBlockBrokenEffect(this.hb.cX - this.hb.width / 2.0F + BLOCK_ICON_X, this.hb.cY - this.hb.height / 2.0F + BLOCK_ICON_Y));
/*      */     
/*  161 */     CardCrawlGame.sound.play("BLOCK_BREAK");
/*      */   }
/*      */   
/*      */   protected int decrementBlock(DamageInfo info, int damageAmount) {
/*  165 */     if (info.type != DamageInfo.DamageType.HP_LOSS && this.currentBlock > 0) {
/*  166 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*      */ 
/*      */       
/*  169 */       if (damageAmount > this.currentBlock) {
/*  170 */         damageAmount -= this.currentBlock;
/*  171 */         if (Settings.SHOW_DMG_BLOCK) {
/*  172 */           AbstractDungeon.effectList.add(new BlockedNumberEffect(this.hb.cX, this.hb.cY + this.hb.height / 2.0F, 
/*  173 */                 Integer.toString(this.currentBlock)));
/*      */         }
/*  175 */         loseBlock();
/*  176 */         brokeBlock();
/*      */       
/*      */       }
/*  179 */       else if (damageAmount == this.currentBlock) {
/*  180 */         damageAmount = 0;
/*  181 */         loseBlock();
/*  182 */         brokeBlock();
/*  183 */         AbstractDungeon.effectList.add(new BlockedWordEffect(this, this.hb.cX, this.hb.cY, TEXT[1]));
/*      */       }
/*      */       else {
/*      */         
/*  187 */         CardCrawlGame.sound.play("BLOCK_ATTACK");
/*  188 */         loseBlock(damageAmount);
/*  189 */         for (int i = 0; i < 18; i++) {
/*  190 */           AbstractDungeon.effectList.add(new BlockImpactLineEffect(this.hb.cX, this.hb.cY));
/*      */         }
/*  192 */         if (Settings.SHOW_DMG_BLOCK) {
/*  193 */           AbstractDungeon.effectList.add(new BlockedNumberEffect(this.hb.cX, this.hb.cY + this.hb.height / 2.0F, 
/*  194 */                 Integer.toString(damageAmount)));
/*      */         }
/*  196 */         damageAmount = 0;
/*      */       } 
/*      */     } 
/*  199 */     return damageAmount;
/*      */   }
/*      */   
/*      */   public void increaseMaxHp(int amount, boolean showEffect) {
/*  203 */     if (!Settings.isEndless || !AbstractDungeon.player.hasBlight("FullBelly")) {
/*      */ 
/*      */       
/*  206 */       if (amount < 0) {
/*  207 */         logger.info("Why are we decreasing health with increaseMaxHealth()?");
/*      */       }
/*      */       
/*  210 */       this.maxHealth += amount;
/*  211 */       AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(this.hb.cX - this.animX, this.hb.cY, TEXT[2] + 
/*      */ 
/*      */ 
/*      */             
/*  215 */             Integer.toString(amount), Settings.GREEN_TEXT_COLOR));
/*      */       
/*  217 */       heal(amount, true);
/*  218 */       healthBarUpdatedEvent();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void decreaseMaxHealth(int amount) {
/*  223 */     if (amount < 0) {
/*  224 */       logger.info("Why are we increasing health with decreaseMaxHealth()?");
/*      */     }
/*      */     
/*  227 */     this.maxHealth -= amount;
/*  228 */     if (this.maxHealth <= 1) {
/*  229 */       this.maxHealth = 1;
/*      */     }
/*  231 */     if (this.currentHealth > this.maxHealth) {
/*  232 */       this.currentHealth = this.maxHealth;
/*      */     }
/*  234 */     healthBarUpdatedEvent();
/*      */   }
/*      */   
/*      */   protected void refreshHitboxLocation() {
/*  238 */     this.hb.move(this.drawX + this.hb_x + this.animX, this.drawY + this.hb_y + this.hb_h / 2.0F);
/*  239 */     this.healthHb.move(this.hb.cX, this.hb.cY - this.hb_h / 2.0F - this.healthHb.height / 2.0F);
/*      */   }
/*      */   
/*      */   public void updateAnimations() {
/*  243 */     if (this.animationTimer != 0.0F) {
/*  244 */       switch (this.animation) {
/*      */         case ATTACK_FAST:
/*  246 */           updateFastAttackAnimation();
/*      */           break;
/*      */         case ATTACK_SLOW:
/*  249 */           updateSlowAttackAnimation();
/*      */           break;
/*      */         case FAST_SHAKE:
/*  252 */           updateFastShakeAnimation();
/*      */           break;
/*      */         case HOP:
/*  255 */           updateHopAnimation();
/*      */           break;
/*      */         case JUMP:
/*  258 */           updateJumpAnimation();
/*      */           break;
/*      */         case SHAKE:
/*  261 */           updateShakeAnimation();
/*      */           break;
/*      */         case STAGGER:
/*  264 */           updateStaggerAnimation();
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     }
/*  270 */     refreshHitboxLocation();
/*  271 */     if (!this.isPlayer) {
/*  272 */       ((AbstractMonster)this).refreshIntentHbLocation();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateFastAttackAnimation() {
/*  280 */     this.animationTimer -= Gdx.graphics.getDeltaTime();
/*  281 */     float targetPos = 90.0F * Settings.scale;
/*  282 */     if (!this.isPlayer) {
/*  283 */       targetPos = -targetPos;
/*      */     }
/*      */ 
/*      */     
/*  287 */     if (this.animationTimer > 0.5F) {
/*  288 */       this.animX = Interpolation.exp5In.apply(0.0F, targetPos, (1.0F - this.animationTimer / 1.0F) * 2.0F);
/*      */     
/*      */     }
/*  291 */     else if (this.animationTimer < 0.0F) {
/*  292 */       this.animationTimer = 0.0F;
/*  293 */       this.animX = 0.0F;
/*      */     }
/*      */     else {
/*      */       
/*  297 */       this.animX = Interpolation.fade.apply(0.0F, targetPos, this.animationTimer / 1.0F * 2.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateSlowAttackAnimation() {
/*  305 */     this.animationTimer -= Gdx.graphics.getDeltaTime();
/*  306 */     float targetPos = 90.0F * Settings.scale;
/*  307 */     if (!this.isPlayer) {
/*  308 */       targetPos = -targetPos;
/*      */     }
/*      */ 
/*      */     
/*  312 */     if (this.animationTimer > 0.5F) {
/*  313 */       this.animX = Interpolation.exp10In.apply(0.0F, targetPos, (1.0F - this.animationTimer / 1.0F) * 2.0F);
/*      */     
/*      */     }
/*  316 */     else if (this.animationTimer < 0.0F) {
/*  317 */       this.animationTimer = 0.0F;
/*  318 */       this.animX = 0.0F;
/*      */     }
/*      */     else {
/*      */       
/*  322 */       this.animX = Interpolation.fade.apply(0.0F, targetPos, this.animationTimer / 1.0F * 2.0F);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateFastShakeAnimation() {
/*  327 */     this.animationTimer -= Gdx.graphics.getDeltaTime();
/*  328 */     if (this.animationTimer < 0.0F) {
/*  329 */       this.animationTimer = 0.0F;
/*  330 */       this.animX = 0.0F;
/*      */     }
/*  332 */     else if (this.shakeToggle) {
/*  333 */       this.animX += SHAKE_SPEED * Gdx.graphics.getDeltaTime();
/*  334 */       if (this.animX > SHAKE_THRESHOLD / 2.0F) {
/*  335 */         this.shakeToggle = !this.shakeToggle;
/*      */       }
/*      */     } else {
/*  338 */       this.animX -= SHAKE_SPEED * Gdx.graphics.getDeltaTime();
/*  339 */       if (this.animX < -SHAKE_THRESHOLD / 2.0F) {
/*  340 */         this.shakeToggle = !this.shakeToggle;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateHopAnimation() {
/*  347 */     this.vY -= 17.0F * Settings.scale;
/*  348 */     this.animY += this.vY * Gdx.graphics.getDeltaTime();
/*  349 */     if (this.animY < 0.0F) {
/*  350 */       this.animationTimer = 0.0F;
/*  351 */       this.animY = 0.0F;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateJumpAnimation() {
/*  356 */     this.vY -= 17.0F * Settings.scale;
/*  357 */     this.animY += this.vY * Gdx.graphics.getDeltaTime();
/*  358 */     if (this.animY < 0.0F) {
/*  359 */       this.animationTimer = 0.0F;
/*  360 */       this.animY = 0.0F;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateStaggerAnimation() {
/*  365 */     if (this.animationTimer != 0.0F) {
/*  366 */       this.animationTimer -= Gdx.graphics.getDeltaTime();
/*  367 */       if (!this.isPlayer) {
/*  368 */         this.animX = Interpolation.pow2.apply(STAGGER_MOVE_SPEED, 0.0F, 1.0F - this.animationTimer / 0.3F);
/*      */       } else {
/*  370 */         this.animX = Interpolation.pow2.apply(-STAGGER_MOVE_SPEED, 0.0F, 1.0F - this.animationTimer / 0.3F);
/*      */       } 
/*  372 */       if (this.animationTimer < 0.0F) {
/*  373 */         this.animationTimer = 0.0F;
/*  374 */         this.animX = 0.0F;
/*  375 */         this.vX = STAGGER_MOVE_SPEED;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateShakeAnimation() {
/*  381 */     this.animationTimer -= Gdx.graphics.getDeltaTime();
/*  382 */     if (this.animationTimer < 0.0F) {
/*  383 */       this.animationTimer = 0.0F;
/*  384 */       this.animX = 0.0F;
/*      */     }
/*  386 */     else if (this.shakeToggle) {
/*  387 */       this.animX += SHAKE_SPEED * Gdx.graphics.getDeltaTime();
/*  388 */       if (this.animX > SHAKE_THRESHOLD) {
/*  389 */         this.shakeToggle = !this.shakeToggle;
/*      */       }
/*      */     } else {
/*  392 */       this.animX -= SHAKE_SPEED * Gdx.graphics.getDeltaTime();
/*  393 */       if (this.animX < -SHAKE_THRESHOLD) {
/*  394 */         this.shakeToggle = !this.shakeToggle;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
/*  401 */     this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
/*  402 */     SkeletonJson json = new SkeletonJson(this.atlas);
/*      */ 
/*      */     
/*  405 */     if (CardCrawlGame.dungeon != null && AbstractDungeon.player != null) {
/*  406 */       if (AbstractDungeon.player.hasRelic("PreservedInsect") && !this.isPlayer && 
/*  407 */         (AbstractDungeon.getCurrRoom()).eliteTrigger) {
/*  408 */         scale += 0.3F;
/*      */       }
/*  410 */       if (ModHelper.isModEnabled("MonsterHunter") && !this.isPlayer) {
/*  411 */         scale -= 0.3F;
/*      */       }
/*      */     } 
/*      */     
/*  415 */     json.setScale(Settings.renderScale / scale);
/*  416 */     SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
/*  417 */     this.skeleton = new Skeleton(skeletonData);
/*  418 */     this.skeleton.setColor(Color.WHITE);
/*  419 */     this.stateData = new AnimationStateData(skeletonData);
/*  420 */     this.state = new AnimationState(this.stateData);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void heal(int healAmount, boolean showEffect) {
/*  429 */     if (Settings.isEndless && this.isPlayer && AbstractDungeon.player.hasBlight("FullBelly")) {
/*  430 */       healAmount /= 2;
/*  431 */       if (healAmount < 1) {
/*  432 */         healAmount = 1;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  437 */     if (this.isDying) {
/*      */       return;
/*      */     }
/*      */     
/*  441 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  442 */       if (this.isPlayer) {
/*  443 */         healAmount = r.onPlayerHeal(healAmount);
/*      */       }
/*      */     } 
/*      */     
/*  447 */     for (AbstractPower p : this.powers) {
/*  448 */       healAmount = p.onHeal(healAmount);
/*      */     }
/*      */     
/*  451 */     this.currentHealth += healAmount;
/*  452 */     if (this.currentHealth > this.maxHealth) {
/*  453 */       this.currentHealth = this.maxHealth;
/*      */     }
/*      */     
/*  456 */     if (this.currentHealth > this.maxHealth / 2.0F && 
/*  457 */       this.isBloodied) {
/*  458 */       this.isBloodied = false;
/*  459 */       for (AbstractRelic r2 : AbstractDungeon.player.relics) {
/*  460 */         r2.onNotBloodied();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  465 */     if (healAmount > 0) {
/*  466 */       if (showEffect && this.isPlayer) {
/*  467 */         AbstractDungeon.topPanel.panelHealEffect();
/*  468 */         AbstractDungeon.effectsQueue.add(new HealEffect(this.hb.cX - this.animX, this.hb.cY, healAmount));
/*      */       } 
/*  470 */       healthBarUpdatedEvent();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void heal(int amount) {
/*  475 */     heal(amount, true);
/*      */   }
/*      */   
/*      */   public void addBlock(int blockAmount) {
/*  479 */     float tmp = blockAmount;
/*  480 */     if (this.isPlayer) {
/*  481 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  482 */         tmp = r.onPlayerGainedBlock(tmp);
/*      */       }
/*  484 */       if (tmp > 0.0F) {
/*  485 */         for (AbstractPower p : this.powers) {
/*  486 */           p.onGainedBlock(tmp);
/*      */         }
/*      */       }
/*      */     } 
/*  490 */     boolean effect = false;
/*  491 */     if (this.currentBlock == 0) {
/*  492 */       effect = true;
/*      */     }
/*  494 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/*  495 */       for (AbstractPower p : m.powers) {
/*  496 */         tmp = p.onPlayerGainedBlock(tmp);
/*      */       }
/*      */     } 
/*  499 */     this.currentBlock += MathUtils.floor(tmp);
/*      */ 
/*      */     
/*  502 */     if (this.currentBlock >= 99 && this.isPlayer) {
/*  503 */       UnlockTracker.unlockAchievement("IMPERVIOUS");
/*      */     }
/*      */ 
/*      */     
/*  507 */     if (this.currentBlock > 999) {
/*  508 */       this.currentBlock = 999;
/*      */     }
/*      */ 
/*      */     
/*  512 */     if (this.currentBlock == 999) {
/*  513 */       UnlockTracker.unlockAchievement("BARRICADED");
/*      */     }
/*      */     
/*  516 */     if (effect && this.currentBlock > 0) {
/*  517 */       gainBlockAnimation();
/*  518 */     } else if (blockAmount > 0 && 
/*  519 */       blockAmount > 0) {
/*  520 */       Color tmpCol = Settings.GOLD_COLOR.cpy();
/*  521 */       tmpCol.a = this.blockTextColor.a;
/*  522 */       this.blockTextColor = tmpCol;
/*  523 */       this.blockScale = 5.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void loseBlock(int amount, boolean noAnimation) {
/*  529 */     boolean effect = false;
/*  530 */     if (this.currentBlock != 0) {
/*  531 */       effect = true;
/*      */     }
/*      */     
/*  534 */     this.currentBlock -= amount;
/*  535 */     if (this.currentBlock < 0) {
/*  536 */       this.currentBlock = 0;
/*      */     }
/*      */     
/*  539 */     if (this.currentBlock == 0 && effect) {
/*  540 */       if (!noAnimation) {
/*  541 */         AbstractDungeon.effectList.add(new HbBlockBrokenEffect(this.hb.cX - this.hb.width / 2.0F + BLOCK_ICON_X, this.hb.cY - this.hb.height / 2.0F + BLOCK_ICON_Y));
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  546 */     else if (this.currentBlock > 0 && amount > 0) {
/*  547 */       Color tmp = Color.RED.cpy();
/*  548 */       tmp.a = this.blockTextColor.a;
/*  549 */       this.blockTextColor = tmp;
/*  550 */       this.blockScale = 5.0F;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void loseBlock() {
/*  555 */     loseBlock(this.currentBlock);
/*      */   }
/*      */   
/*      */   public void loseBlock(boolean noAnimation) {
/*  559 */     loseBlock(this.currentBlock, noAnimation);
/*      */   }
/*      */   
/*      */   public void loseBlock(int amount) {
/*  563 */     loseBlock(amount, false);
/*      */   }
/*      */   
/*      */   public void showHealthBar() {
/*  567 */     this.hbShowTimer = 0.7F;
/*  568 */     this.hbAlpha = 0.0F;
/*      */   }
/*      */   
/*      */   public void hideHealthBar() {
/*  572 */     this.hbAlpha = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPower(AbstractPower powerToApply) {
/*  582 */     boolean hasBuffAlready = false;
/*      */     
/*  584 */     for (AbstractPower p : this.powers) {
/*  585 */       if (p.ID.equals(powerToApply.ID)) {
/*  586 */         p.stackPower(powerToApply.amount);
/*  587 */         p.updateDescription();
/*  588 */         hasBuffAlready = true;
/*      */       } 
/*      */     } 
/*      */     
/*  592 */     if (!hasBuffAlready) {
/*  593 */       this.powers.add(powerToApply);
/*      */ 
/*      */       
/*  596 */       if (this.isPlayer) {
/*  597 */         int buffCount = 0;
/*  598 */         for (AbstractPower p : this.powers) {
/*  599 */           if (p.type == AbstractPower.PowerType.BUFF) {
/*  600 */             buffCount++;
/*      */           }
/*      */         } 
/*  603 */         if (buffCount >= 10) {
/*  604 */           UnlockTracker.unlockAchievement("POWERFUL");
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void applyStartOfTurnPowers() {
/*  611 */     for (AbstractPower p : this.powers) {
/*  612 */       p.atStartOfTurn();
/*      */     }
/*      */   }
/*      */   
/*      */   public void applyTurnPowers() {
/*  617 */     for (AbstractPower p : this.powers) {
/*  618 */       p.duringTurn();
/*      */     }
/*      */   }
/*      */   
/*      */   public void applyStartOfTurnPostDrawPowers() {
/*  623 */     for (AbstractPower p : this.powers) {
/*  624 */       p.atStartOfTurnPostDraw();
/*      */     }
/*      */   }
/*      */   
/*      */   public void applyEndOfTurnTriggers() {
/*  629 */     for (AbstractPower p : this.powers) {
/*  630 */       if (!this.isPlayer) {
/*  631 */         p.atEndOfTurnPreEndTurnCards(false);
/*      */       }
/*  633 */       p.atEndOfTurn(this.isPlayer);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void healthBarUpdatedEvent() {
/*  641 */     this.healthBarAnimTimer = 1.2F;
/*  642 */     this.targetHealthBarWidth = this.hb.width * this.currentHealth / this.maxHealth;
/*  643 */     if (this.maxHealth == this.currentHealth) {
/*  644 */       this.healthBarWidth = this.targetHealthBarWidth;
/*  645 */     } else if (this.currentHealth == 0) {
/*  646 */       this.healthBarWidth = 0.0F;
/*  647 */       this.targetHealthBarWidth = 0.0F;
/*      */     } 
/*      */     
/*  650 */     if (this.targetHealthBarWidth > this.healthBarWidth) {
/*  651 */       this.healthBarWidth = this.targetHealthBarWidth;
/*      */     }
/*      */   }
/*      */   
/*      */   public void healthBarRevivedEvent() {
/*  656 */     this.healthBarAnimTimer = 1.2F;
/*  657 */     this.targetHealthBarWidth = this.hb.width * this.currentHealth / this.maxHealth;
/*  658 */     this.healthBarWidth = this.targetHealthBarWidth;
/*  659 */     this.hbBgColor.a = 0.75F;
/*  660 */     this.hbShadowColor.a = 0.5F;
/*  661 */     this.hbTextColor.a = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateHealthBar() {
/*  668 */     updateHbHoverFade();
/*  669 */     updateBlockAnimations();
/*  670 */     updateHbPopInAnimation();
/*  671 */     updateHbDamageAnimation();
/*  672 */     updateHbAlpha();
/*      */   }
/*      */   
/*      */   private void updateHbHoverFade() {
/*  676 */     if (this.healthHb.hovered) {
/*  677 */       this.healthHideTimer -= Gdx.graphics.getDeltaTime() * 4.0F;
/*  678 */       if (this.healthHideTimer < 0.2F) {
/*  679 */         this.healthHideTimer = 0.2F;
/*      */       }
/*      */     } else {
/*  682 */       this.healthHideTimer += Gdx.graphics.getDeltaTime() * 4.0F;
/*  683 */       if (this.healthHideTimer > 1.0F) {
/*  684 */         this.healthHideTimer = 1.0F;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateHbAlpha() {
/*  694 */     if (this instanceof AbstractMonster && ((AbstractMonster)this).isEscaping) {
/*  695 */       this.hbAlpha = MathHelper.fadeLerpSnap(this.hbAlpha, 0.0F);
/*  696 */       this.targetHealthBarWidth = 0.0F;
/*  697 */       this.hbBgColor.a = this.hbAlpha * 0.75F;
/*  698 */       this.hbShadowColor.a = this.hbAlpha * 0.5F;
/*  699 */       this.hbTextColor.a = this.hbAlpha;
/*  700 */       this.orangeHbBarColor.a = this.hbAlpha;
/*  701 */       this.redHbBarColor.a = this.hbAlpha;
/*  702 */       this.greenHbBarColor.a = this.hbAlpha;
/*  703 */       this.blueHbBarColor.a = this.hbAlpha;
/*  704 */       this.blockOutlineColor.a = this.hbAlpha;
/*      */     }
/*  706 */     else if (this.targetHealthBarWidth == 0.0F && this.healthBarAnimTimer <= 0.0F) {
/*  707 */       this.hbShadowColor.a = MathHelper.fadeLerpSnap(this.hbShadowColor.a, 0.0F);
/*  708 */       this.hbBgColor.a = MathHelper.fadeLerpSnap(this.hbBgColor.a, 0.0F);
/*  709 */       this.hbTextColor.a = MathHelper.fadeLerpSnap(this.hbTextColor.a, 0.0F);
/*  710 */       this.blockOutlineColor.a = MathHelper.fadeLerpSnap(this.blockOutlineColor.a, 0.0F);
/*      */     } else {
/*      */       
/*  713 */       this.hbBgColor.a = this.hbAlpha * 0.5F;
/*  714 */       this.hbShadowColor.a = this.hbAlpha * 0.2F;
/*  715 */       this.hbTextColor.a = this.hbAlpha;
/*  716 */       this.orangeHbBarColor.a = this.hbAlpha;
/*  717 */       this.redHbBarColor.a = this.hbAlpha;
/*  718 */       this.greenHbBarColor.a = this.hbAlpha;
/*  719 */       this.blueHbBarColor.a = this.hbAlpha;
/*  720 */       this.blockOutlineColor.a = this.hbAlpha;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void gainBlockAnimation() {
/*  725 */     this.blockAnimTimer = 0.7F;
/*  726 */     this.blockTextColor.a = 0.0F;
/*  727 */     this.blockColor.a = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateBlockAnimations() {
/*  732 */     if (this.currentBlock > 0) {
/*  733 */       if (this.blockAnimTimer > 0.0F) {
/*  734 */         this.blockAnimTimer -= Gdx.graphics.getDeltaTime();
/*  735 */         if (this.blockAnimTimer < 0.0F) {
/*  736 */           this.blockAnimTimer = 0.0F;
/*      */         }
/*      */ 
/*      */         
/*  740 */         this.blockOffset = Interpolation.swingOut.apply(BLOCK_OFFSET_DIST * 3.0F, 0.0F, 1.0F - this.blockAnimTimer / 0.7F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  746 */         this.blockScale = Interpolation.pow3In.apply(3.0F, 1.0F, 1.0F - this.blockAnimTimer / 0.7F);
/*      */ 
/*      */         
/*  749 */         this.blockColor.a = Interpolation.pow2Out.apply(0.0F, 1.0F, 1.0F - this.blockAnimTimer / 0.7F);
/*  750 */         this.blockTextColor.a = Interpolation.pow5In.apply(0.0F, 1.0F, 1.0F - this.blockAnimTimer / 0.7F);
/*      */       }
/*  752 */       else if (this.blockScale != 1.0F) {
/*  753 */         this.blockScale = MathHelper.scaleLerpSnap(this.blockScale, 1.0F);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  758 */       if (this.blockTextColor.r != 1.0F) {
/*  759 */         this.blockTextColor.r = MathHelper.slowColorLerpSnap(this.blockTextColor.r, 1.0F);
/*      */       }
/*  761 */       if (this.blockTextColor.g != 1.0F) {
/*  762 */         this.blockTextColor.g = MathHelper.slowColorLerpSnap(this.blockTextColor.g, 1.0F);
/*      */       }
/*  764 */       if (this.blockTextColor.b != 1.0F) {
/*  765 */         this.blockTextColor.b = MathHelper.slowColorLerpSnap(this.blockTextColor.b, 1.0F);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateHbPopInAnimation() {
/*  772 */     if (this.hbShowTimer > 0.0F) {
/*  773 */       this.hbShowTimer -= Gdx.graphics.getDeltaTime();
/*  774 */       if (this.hbShowTimer < 0.0F) {
/*  775 */         this.hbShowTimer = 0.0F;
/*      */       }
/*      */       
/*  778 */       this.hbAlpha = Interpolation.fade.apply(0.0F, 1.0F, 1.0F - this.hbShowTimer / 0.7F);
/*  779 */       this.hbYOffset = Interpolation.exp10Out.apply(HB_Y_OFFSET_DIST * 5.0F, 0.0F, 1.0F - this.hbShowTimer / 0.7F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateHbDamageAnimation() {
/*  789 */     if (this.healthBarAnimTimer > 0.0F) {
/*  790 */       this.healthBarAnimTimer -= Gdx.graphics.getDeltaTime();
/*      */     }
/*      */ 
/*      */     
/*  794 */     if (this.healthBarWidth != this.targetHealthBarWidth && this.healthBarAnimTimer <= 0.0F && this.targetHealthBarWidth < this.healthBarWidth)
/*      */     {
/*  796 */       this.healthBarWidth = MathHelper.uiLerpSnap(this.healthBarWidth, this.targetHealthBarWidth);
/*      */     }
/*      */   }
/*      */   
/*      */   public void updatePowers() {
/*  801 */     for (int i = 0; i < this.powers.size(); i++) {
/*  802 */       ((AbstractPower)this.powers.get(i)).update(i);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void initialize() {
/*  807 */     sr = new SkeletonRenderer();
/*  808 */     sr.setPremultipliedAlpha(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderPowerTips(SpriteBatch sb) {
/*  817 */     this.tips.clear();
/*      */     
/*  819 */     for (AbstractPower p : this.powers) {
/*  820 */       if (p.region48 != null) {
/*  821 */         this.tips.add(new PowerTip(p.name, p.description, p.region48)); continue;
/*      */       } 
/*  823 */       this.tips.add(new PowerTip(p.name, p.description, p.img));
/*      */     } 
/*      */ 
/*      */     
/*  827 */     if (!this.tips.isEmpty())
/*      */     {
/*      */       
/*  830 */       if (this.hb.cX + this.hb.width / 2.0F < TIP_X_THRESHOLD) {
/*  831 */         TipHelper.queuePowerTips(this.hb.cX + this.hb.width / 2.0F + TIP_OFFSET_R_X, this.hb.cY + 
/*      */             
/*  833 */             TipHelper.calculateAdditionalOffset(this.tips, this.hb.cY), this.tips);
/*      */       } else {
/*      */         
/*  836 */         TipHelper.queuePowerTips(this.hb.cX - this.hb.width / 2.0F + TIP_OFFSET_L_X, this.hb.cY + 
/*      */             
/*  838 */             TipHelper.calculateAdditionalOffset(this.tips, this.hb.cY), this.tips);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void useFastAttackAnimation() {
/*  845 */     this.animX = 0.0F;
/*  846 */     this.animY = 0.0F;
/*  847 */     this.animationTimer = 0.4F;
/*  848 */     this.animation = CreatureAnimation.ATTACK_FAST;
/*      */   }
/*      */   
/*      */   public void useSlowAttackAnimation() {
/*  852 */     this.animX = 0.0F;
/*  853 */     this.animY = 0.0F;
/*  854 */     this.animationTimer = 1.0F;
/*  855 */     this.animation = CreatureAnimation.ATTACK_SLOW;
/*      */   }
/*      */   
/*      */   public void useHopAnimation() {
/*  859 */     this.animX = 0.0F;
/*  860 */     this.animY = 0.0F;
/*  861 */     this.vY = 300.0F * Settings.scale;
/*  862 */     this.animationTimer = 0.7F;
/*  863 */     this.animation = CreatureAnimation.HOP;
/*      */   }
/*      */   
/*      */   public void useJumpAnimation() {
/*  867 */     this.animX = 0.0F;
/*  868 */     this.animY = 0.0F;
/*  869 */     this.vY = 500.0F * Settings.scale;
/*  870 */     this.animationTimer = 0.7F;
/*  871 */     this.animation = CreatureAnimation.JUMP;
/*      */   }
/*      */   
/*      */   public void useStaggerAnimation() {
/*  875 */     if (this.animY == 0.0F) {
/*  876 */       this.animX = 0.0F;
/*  877 */       this.animationTimer = 0.3F;
/*  878 */       this.animation = CreatureAnimation.STAGGER;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void useFastShakeAnimation(float duration) {
/*  883 */     if (this.animY == 0.0F) {
/*  884 */       this.animX = 0.0F;
/*  885 */       this.animationTimer = duration;
/*  886 */       this.animation = CreatureAnimation.FAST_SHAKE;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void useShakeAnimation(float duration) {
/*  891 */     if (this.animY == 0.0F) {
/*  892 */       this.animX = 0.0F;
/*  893 */       this.animationTimer = duration;
/*  894 */       this.animation = CreatureAnimation.SHAKE;
/*      */     } 
/*      */   }
/*      */   
/*      */   public AbstractPower getPower(String targetID) {
/*  899 */     for (AbstractPower p : this.powers) {
/*  900 */       if (p.ID.equals(targetID)) {
/*  901 */         return p;
/*      */       }
/*      */     } 
/*  904 */     return null;
/*      */   }
/*      */   
/*      */   public boolean hasPower(String targetID) {
/*  908 */     for (AbstractPower p : this.powers) {
/*  909 */       if (p.ID.equals(targetID)) {
/*  910 */         return true;
/*      */       }
/*      */     } 
/*  913 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isDeadOrEscaped() {
/*  917 */     if (this.isDying || this.halfDead) {
/*  918 */       return true;
/*      */     }
/*      */     
/*  921 */     if (!this.isPlayer) {
/*  922 */       AbstractMonster m = (AbstractMonster)this;
/*  923 */       if (m.isEscaping) {
/*  924 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  928 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void loseGold(int goldAmount) {
/*  933 */     if (goldAmount > 0) {
/*  934 */       this.gold -= goldAmount;
/*  935 */       if (this.gold < 0) {
/*  936 */         this.gold = 0;
/*      */       }
/*      */     } else {
/*  939 */       logger.info("NEGATIVE MONEY???");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void gainGold(int amount) {
/*  945 */     if (amount < 0) {
/*  946 */       logger.info("NEGATIVE MONEY???");
/*      */     } else {
/*  948 */       this.gold += amount;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderReticle(SpriteBatch sb) {
/*  958 */     this.reticleRendered = true;
/*      */ 
/*      */     
/*  961 */     renderReticleCorner(sb, -this.hb.width / 2.0F + this.reticleOffset, this.hb.height / 2.0F - this.reticleOffset, false, false);
/*  962 */     renderReticleCorner(sb, this.hb.width / 2.0F - this.reticleOffset, this.hb.height / 2.0F - this.reticleOffset, true, false);
/*  963 */     renderReticleCorner(sb, -this.hb.width / 2.0F + this.reticleOffset, -this.hb.height / 2.0F + this.reticleOffset, false, true);
/*  964 */     renderReticleCorner(sb, this.hb.width / 2.0F - this.reticleOffset, -this.hb.height / 2.0F + this.reticleOffset, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderReticle(SpriteBatch sb, Hitbox hb) {
/*  973 */     this.reticleRendered = true;
/*      */ 
/*      */     
/*  976 */     renderReticleCorner(sb, -hb.width / 2.0F + this.reticleOffset, hb.height / 2.0F - this.reticleOffset, hb, false, false);
/*  977 */     renderReticleCorner(sb, hb.width / 2.0F - this.reticleOffset, hb.height / 2.0F - this.reticleOffset, hb, true, false);
/*  978 */     renderReticleCorner(sb, -hb.width / 2.0F + this.reticleOffset, -hb.height / 2.0F + this.reticleOffset, hb, false, true);
/*  979 */     renderReticleCorner(sb, hb.width / 2.0F - this.reticleOffset, -hb.height / 2.0F + this.reticleOffset, hb, true, true);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateReticle() {
/*  984 */     if (this.reticleRendered) {
/*  985 */       this.reticleRendered = false;
/*  986 */       this.reticleAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
/*  987 */       if (this.reticleAlpha > 1.0F) {
/*  988 */         this.reticleAlpha = 1.0F;
/*      */       }
/*      */ 
/*      */       
/*  992 */       this.reticleAnimTimer += Gdx.graphics.getDeltaTime();
/*  993 */       if (this.reticleAnimTimer > 1.0F) {
/*  994 */         this.reticleAnimTimer = 1.0F;
/*      */       }
/*  996 */       this.reticleOffset = Interpolation.elasticOut.apply(RETICLE_OFFSET_DIST, 0.0F, this.reticleAnimTimer);
/*      */     }
/*      */     else {
/*      */       
/* 1000 */       this.reticleAlpha = 0.0F;
/* 1001 */       this.reticleAnimTimer = 0.0F;
/* 1002 */       this.reticleOffset = RETICLE_OFFSET_DIST;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderHealth(SpriteBatch sb) {
/* 1012 */     if (Settings.hideCombatElements) {
/*      */       return;
/*      */     }
/*      */     
/* 1016 */     float x = this.hb.cX - this.hb.width / 2.0F;
/* 1017 */     float y = this.hb.cY - this.hb.height / 2.0F + this.hbYOffset;
/*      */     
/* 1019 */     renderHealthBg(sb, x, y);
/*      */     
/* 1021 */     if (this.targetHealthBarWidth != 0.0F) {
/* 1022 */       renderOrangeHealthBar(sb, x, y);
/* 1023 */       if (hasPower("Poison")) {
/* 1024 */         renderGreenHealthBar(sb, x, y);
/*      */       }
/* 1026 */       renderRedHealthBar(sb, x, y);
/*      */     } 
/*      */ 
/*      */     
/* 1030 */     if (this.currentBlock != 0 && this.hbAlpha != 0.0F) {
/* 1031 */       renderBlockOutline(sb, x, y);
/*      */     }
/*      */     
/* 1034 */     renderHealthText(sb, y);
/*      */ 
/*      */     
/* 1037 */     if (this.currentBlock != 0 && this.hbAlpha != 0.0F) {
/* 1038 */       renderBlockIconAndValue(sb, x, y);
/*      */     }
/*      */     
/* 1041 */     renderPowerIcons(sb, x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderBlockOutline(SpriteBatch sb, float x, float y) {
/* 1050 */     sb.setColor(this.blockOutlineColor);
/* 1051 */     sb.setBlendFunction(770, 1);
/*      */ 
/*      */     
/* 1054 */     sb.draw(ImageMaster.BLOCK_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1062 */     sb.draw(ImageMaster.BLOCK_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.hb.width, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */     
/* 1065 */     sb.draw(ImageMaster.BLOCK_BAR_R, x + this.hb.width, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/* 1066 */     sb.setBlendFunction(770, 771);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderBlockIconAndValue(SpriteBatch sb, float x, float y) {
/* 1075 */     sb.setColor(this.blockColor);
/* 1076 */     sb.draw(ImageMaster.BLOCK_ICON, x + BLOCK_ICON_X - 32.0F, y + BLOCK_ICON_Y - 32.0F + this.blockOffset, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 1094 */     FontHelper.renderFontCentered(sb, FontHelper.blockInfoFont, 
/*      */ 
/*      */         
/* 1097 */         Integer.toString(this.currentBlock), x + BLOCK_ICON_X, y - 16.0F * Settings.scale, this.blockTextColor, this.blockScale);
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
/*      */   private void renderHealthBg(SpriteBatch sb, float x, float y) {
/* 1111 */     sb.setColor(this.hbShadowColor);
/*      */     
/* 1113 */     sb.draw(ImageMaster.HB_SHADOW_L, x - HEALTH_BAR_HEIGHT, y - HEALTH_BG_OFFSET_X + 3.0F * Settings.scale, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1121 */     sb.draw(ImageMaster.HB_SHADOW_B, x, y - HEALTH_BG_OFFSET_X + 3.0F * Settings.scale, this.hb.width, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */     
/* 1124 */     sb.draw(ImageMaster.HB_SHADOW_R, x + this.hb.width, y - HEALTH_BG_OFFSET_X + 3.0F * Settings.scale, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1132 */     sb.setColor(this.hbBgColor);
/* 1133 */     if (this.currentHealth != this.maxHealth) {
/*      */ 
/*      */       
/* 1136 */       sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1144 */       sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.hb.width, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */       
/* 1147 */       sb.draw(ImageMaster.HEALTH_BAR_R, x + this.hb.width, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
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
/*      */   private void renderOrangeHealthBar(SpriteBatch sb, float x, float y) {
/* 1163 */     sb.setColor(this.orangeHbBarColor);
/*      */ 
/*      */     
/* 1166 */     sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1174 */     sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.healthBarWidth, HEALTH_BAR_HEIGHT);
/* 1175 */     sb.draw(ImageMaster.HEALTH_BAR_R, x + this.healthBarWidth, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
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
/*      */   private void renderGreenHealthBar(SpriteBatch sb, float x, float y) {
/* 1190 */     sb.setColor(this.greenHbBarColor);
/*      */ 
/*      */     
/* 1193 */     if (this.currentHealth > 0) {
/* 1194 */       sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1203 */     sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.targetHealthBarWidth, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */     
/* 1206 */     sb.draw(ImageMaster.HEALTH_BAR_R, x + this.targetHealthBarWidth, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
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
/*      */   private void renderRedHealthBar(SpriteBatch sb, float x, float y) {
/* 1221 */     if (this.currentBlock > 0) {
/* 1222 */       sb.setColor(this.blueHbBarColor);
/*      */     } else {
/* 1224 */       sb.setColor(this.redHbBarColor);
/*      */     } 
/*      */     
/* 1227 */     if (!hasPower("Poison")) {
/*      */       
/* 1229 */       if (this.currentHealth > 0) {
/* 1230 */         sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1239 */       sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.targetHealthBarWidth, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */       
/* 1242 */       sb.draw(ImageMaster.HEALTH_BAR_R, x + this.targetHealthBarWidth, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1249 */       int poisonAmt = (getPower("Poison")).amount;
/* 1250 */       if (poisonAmt > 0 && hasPower("Intangible")) {
/* 1251 */         poisonAmt = 1;
/*      */       }
/* 1253 */       if (this.currentHealth > poisonAmt) {
/* 1254 */         float w = 1.0F - (this.currentHealth - poisonAmt) / this.currentHealth;
/* 1255 */         w *= this.targetHealthBarWidth;
/*      */         
/* 1257 */         if (this.currentHealth > 0) {
/* 1258 */           sb.draw(ImageMaster.HEALTH_BAR_L, x - HEALTH_BAR_HEIGHT, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1267 */         sb.draw(ImageMaster.HEALTH_BAR_B, x, y + HEALTH_BAR_OFFSET_Y, this.targetHealthBarWidth - w, HEALTH_BAR_HEIGHT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1275 */         sb.draw(ImageMaster.HEALTH_BAR_R, x + this.targetHealthBarWidth - w, y + HEALTH_BAR_OFFSET_Y, HEALTH_BAR_HEIGHT, HEALTH_BAR_HEIGHT);
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
/*      */   private void renderHealthText(SpriteBatch sb, float y) {
/* 1291 */     if (this.targetHealthBarWidth != 0.0F) {
/* 1292 */       float tmp = this.hbTextColor.a;
/* 1293 */       this.hbTextColor.a *= this.healthHideTimer;
/*      */       
/* 1295 */       FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, this.currentHealth + "/" + this.maxHealth, this.hb.cX, y + HEALTH_BAR_OFFSET_Y + HEALTH_TEXT_OFFSET_Y + 5.0F * Settings.scale, this.hbTextColor);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1303 */       this.hbTextColor.a = tmp;
/*      */     } else {
/* 1305 */       FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, TEXT[0], this.hb.cX, y + HEALTH_BAR_OFFSET_Y + HEALTH_TEXT_OFFSET_Y - 1.0F * Settings.scale, this.hbTextColor);
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
/*      */   private void renderPowerIcons(SpriteBatch sb, float x, float y) {
/* 1321 */     float offset = 10.0F * Settings.scale;
/*      */     
/* 1323 */     for (AbstractPower p : this.powers) {
/* 1324 */       if (Settings.isMobile) {
/* 1325 */         p.renderIcons(sb, x + offset, y - 53.0F * Settings.scale, this.hbTextColor);
/*      */       } else {
/* 1327 */         p.renderIcons(sb, x + offset, y - 48.0F * Settings.scale, this.hbTextColor);
/*      */       } 
/* 1329 */       offset += POWER_ICON_PADDING_X;
/*      */     } 
/*      */     
/* 1332 */     offset = 0.0F * Settings.scale;
/*      */     
/* 1334 */     for (AbstractPower p : this.powers) {
/* 1335 */       if (Settings.isMobile) {
/* 1336 */         p.renderAmount(sb, x + offset + 32.0F * Settings.scale, y - 75.0F * Settings.scale, this.hbTextColor);
/*      */       } else {
/* 1338 */         p.renderAmount(sb, x + offset + 32.0F * Settings.scale, y - 66.0F * Settings.scale, this.hbTextColor);
/*      */       } 
/* 1340 */       offset += POWER_ICON_PADDING_X;
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
/*      */   private void renderReticleCorner(SpriteBatch sb, float x, float y, Hitbox hb, boolean flipX, boolean flipY) {
/* 1355 */     this.reticleShadowColor.a = this.reticleAlpha / 4.0F;
/* 1356 */     sb.setColor(this.reticleShadowColor);
/* 1357 */     sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F + 4.0F * Settings.scale, hb.cY + y - 18.0F - 4.0F * Settings.scale, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
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
/* 1376 */     this.reticleColor.a = this.reticleAlpha;
/* 1377 */     sb.setColor(this.reticleColor);
/* 1378 */     sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F, hb.cY + y - 18.0F, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderReticleCorner(SpriteBatch sb, float x, float y, boolean flipX, boolean flipY) {
/* 1408 */     this.reticleShadowColor.a = this.reticleAlpha / 4.0F;
/* 1409 */     sb.setColor(this.reticleShadowColor);
/* 1410 */     sb.draw(ImageMaster.RETICLE_CORNER, this.hb.cX + x - 18.0F + 4.0F * Settings.scale, this.hb.cY + y - 18.0F - 4.0F * Settings.scale, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
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
/* 1429 */     this.reticleColor.a = this.reticleAlpha;
/* 1430 */     sb.setColor(this.reticleColor);
/* 1431 */     sb.draw(ImageMaster.RETICLE_CORNER, this.hb.cX + x - 18.0F, this.hb.cY + y - 18.0F, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
/*      */   }
/*      */   
/*      */   public abstract void render(SpriteBatch paramSpriteBatch);
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\AbstractCreature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */