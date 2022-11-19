/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.Bone;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
/*     */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.ShoutAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EscapeAction;
/*     */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*     */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SetMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.status.VoidCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.CuriosityPower;
/*     */ import com.megacrit.cardcrawl.powers.RegenerateMonsterPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.powers.UnawakenedPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.AwakenedEyeParticle;
/*     */ import com.megacrit.cardcrawl.vfx.AwakenedWingParticle;
/*     */ import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AwakenedOne
/*     */   extends AbstractMonster
/*     */ {
/*  61 */   private static final Logger logger = LogManager.getLogger(AwakenedOne.class.getName());
/*     */   public static final String ID = "AwakenedOne";
/*  63 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("AwakenedOne");
/*  64 */   public static final String NAME = monsterStrings.NAME;
/*  65 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  66 */   public static final String[] DIALOG = monsterStrings.DIALOG; private boolean form1 = true; private boolean firstTurn = true;
/*     */   private boolean saidPower = false;
/*     */   public static final int STAGE_1_HP = 300;
/*     */   public static final int STAGE_2_HP = 300;
/*     */   public static final int A_9_STAGE_1_HP = 320;
/*     */   public static final int A_9_STAGE_2_HP = 320;
/*     */   private static final int A_4_STR = 2;
/*     */   private static final byte SLASH = 1;
/*     */   private static final byte SOUL_STRIKE = 2;
/*     */   private static final byte REBIRTH = 3;
/*  76 */   private static final String SS_NAME = MOVES[0];
/*     */   private static final int SLASH_DMG = 20;
/*     */   private static final int SS_DMG = 6;
/*     */   private static final int SS_AMT = 4;
/*     */   private static final int REGEN_AMT = 10;
/*  81 */   private static final String DARK_ECHO_NAME = MOVES[1]; private static final int STR_AMT = 1; private static final byte DARK_ECHO = 5; private static final byte SLUDGE = 6; private static final byte TACKLE = 8; private static final String SLUDGE_NAME = MOVES[3]; private static final int ECHO_DMG = 40;
/*     */   private static final int SLUDGE_DMG = 18;
/*     */   private static final int TACKLE_DMG = 10;
/*     */   private static final int TACKLE_AMT = 3;
/*  85 */   private float fireTimer = 0.0F; private static final float FIRE_TIME = 0.1F;
/*     */   private Bone eye;
/*     */   private Bone back;
/*     */   private boolean animateParticles = false;
/*  89 */   private ArrayList<AwakenedWingParticle> wParticles = new ArrayList<>();
/*     */   
/*     */   public AwakenedOne(float x, float y) {
/*  92 */     super(NAME, "AwakenedOne", 300, 40.0F, -30.0F, 460.0F, 250.0F, null, x, y);
/*     */     
/*  94 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  95 */       setHp(320);
/*     */     } else {
/*  97 */       setHp(300);
/*     */     } 
/*     */     
/* 100 */     loadAnimation("images/monsters/theForest/awakenedOne/skeleton.atlas", "images/monsters/theForest/awakenedOne/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/* 104 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle_1", true);
/* 105 */     e.setTime(e.getEndTime() * MathUtils.random());
/* 106 */     this.stateData.setMix("Hit", "Idle_1", 0.3F);
/* 107 */     this.stateData.setMix("Hit", "Idle_2", 0.2F);
/* 108 */     this.stateData.setMix("Attack_1", "Idle_1", 0.2F);
/* 109 */     this.stateData.setMix("Attack_2", "Idle_2", 0.2F);
/* 110 */     this.state.getData().setMix("Idle_1", "Idle_2", 1.0F);
/*     */     
/* 112 */     this.eye = this.skeleton.findBone("Eye");
/*     */     
/* 114 */     for (Bone b : this.skeleton.getBones()) {
/* 115 */       logger.info(b.getData().getName());
/*     */     }
/* 117 */     this.back = this.skeleton.findBone("Hips");
/*     */     
/* 119 */     this.type = EnemyType.BOSS;
/* 120 */     this.dialogX = -200.0F * Settings.scale;
/* 121 */     this.dialogY = 10.0F * Settings.scale;
/*     */     
/* 123 */     this.damage.add(new DamageInfo((AbstractCreature)this, 20));
/* 124 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/* 125 */     this.damage.add(new DamageInfo((AbstractCreature)this, 40));
/* 126 */     this.damage.add(new DamageInfo((AbstractCreature)this, 18));
/* 127 */     this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 132 */     CardCrawlGame.music.unsilenceBGM();
/* 133 */     AbstractDungeon.scene.fadeOutAmbiance();
/* 134 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
/* 135 */     (AbstractDungeon.getCurrRoom()).cannotLose = true;
/*     */     
/* 137 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 138 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegenerateMonsterPower(this, 15)));
/*     */       
/* 140 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CuriosityPower((AbstractCreature)this, 2)));
/*     */     } else {
/*     */       
/* 143 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegenerateMonsterPower(this, 10)));
/*     */       
/* 145 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CuriosityPower((AbstractCreature)this, 1)));
/*     */     } 
/*     */     
/* 148 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new UnawakenedPower((AbstractCreature)this)));
/*     */     
/* 150 */     if (AbstractDungeon.ascensionLevel >= 4) {
/* 151 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 2), 2));
/*     */     }
/*     */ 
/*     */     
/* 155 */     UnlockTracker.markBossAsSeen("CROW");
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/* 160 */     switch (this.nextMove) {
/*     */       case 1:
/* 162 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_AWAKENED_POUNCE"));
/* 163 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK_1"));
/* 164 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 165 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 166 */               .get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */         break;
/*     */       case 2:
/* 169 */         for (i = 0; i < 4; i++) {
/* 170 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 171 */                 .get(1), AbstractGameAction.AttackEffect.FIRE));
/*     */         }
/*     */         break;
/*     */       case 3:
/* 175 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_AWAKENEDONE_1"));
/* 176 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
/*     */         
/* 178 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REBIRTH"));
/*     */         break;
/*     */       case 5:
/* 181 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK_2"));
/* 182 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/* 183 */         this.firstTurn = false;
/* 184 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_AWAKENEDONE_3"));
/* 185 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, new Color(0.1F, 0.0F, 0.2F, 1.0F), ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 190 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, new Color(0.3F, 0.2F, 0.4F, 1.0F), ShockWaveEffect.ShockWaveType.CHAOTIC), 1.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 195 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 196 */               .get(2), AbstractGameAction.AttackEffect.SMASH));
/*     */         break;
/*     */       case 6:
/* 199 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK_2"));
/* 200 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 201 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 202 */               .get(3), AbstractGameAction.AttackEffect.POISON));
/* 203 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new VoidCard(), 1, true, true));
/*     */         break;
/*     */       
/*     */       case 8:
/* 207 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_AWAKENED_ATTACK"));
/* 208 */         for (i = 0; i < 3; i++) {
/* 209 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 210 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.06F));
/* 211 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 212 */                 .get(4), AbstractGameAction.AttackEffect.FIRE, true));
/*     */         } 
/*     */         break;
/*     */     } 
/* 216 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 221 */     switch (key) {
/*     */       case "REBIRTH":
/* 223 */         if (AbstractDungeon.ascensionLevel >= 9) {
/* 224 */           this.maxHealth = 320;
/*     */         } else {
/* 226 */           this.maxHealth = 300;
/*     */         } 
/* 228 */         if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
/* 229 */           float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
/* 230 */           this.maxHealth = (int)(this.maxHealth * mod);
/*     */         } 
/*     */         
/* 233 */         if (ModHelper.isModEnabled("MonsterHunter")) {
/* 234 */           this.currentHealth = (int)(this.currentHealth * 1.5F);
/*     */         }
/*     */         
/* 237 */         this.state.setAnimation(0, "Idle_2", true);
/* 238 */         this.halfDead = false;
/* 239 */         this.animateParticles = true;
/*     */         
/* 241 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth));
/* 242 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
/*     */         break;
/*     */       case "ATTACK_1":
/* 245 */         this.state.setAnimation(0, "Attack_1", false);
/* 246 */         this.state.addAnimation(0, "Idle_1", true, 0.0F);
/*     */         break;
/*     */       case "ATTACK_2":
/* 249 */         this.state.setAnimation(0, "Attack_2", false);
/* 250 */         this.state.addAnimation(0, "Idle_2", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 261 */     if (this.form1) {
/* 262 */       if (this.firstTurn) {
/* 263 */         setMove((byte)1, Intent.ATTACK, 20);
/* 264 */         this.firstTurn = false;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 269 */       if (num < 25) {
/* 270 */         if (!lastMove((byte)2)) {
/* 271 */           setMove(SS_NAME, (byte)2, Intent.ATTACK, 6, 4, true);
/*     */         } else {
/* 273 */           setMove((byte)1, Intent.ATTACK, 20);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 278 */       else if (!lastTwoMoves((byte)1)) {
/* 279 */         setMove((byte)1, Intent.ATTACK, 20);
/*     */       } else {
/* 281 */         setMove(SS_NAME, (byte)2, Intent.ATTACK, 6, 4, true);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 287 */       if (this.firstTurn) {
/* 288 */         setMove(DARK_ECHO_NAME, (byte)5, Intent.ATTACK, 40);
/*     */         
/*     */         return;
/*     */       } 
/* 292 */       if (num < 50) {
/* 293 */         if (!lastTwoMoves((byte)6)) {
/* 294 */           setMove(SLUDGE_NAME, (byte)6, Intent.ATTACK_DEBUFF, 18);
/*     */         } else {
/* 296 */           setMove((byte)8, Intent.ATTACK, 10, 3, true);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 301 */       else if (!lastTwoMoves((byte)8)) {
/* 302 */         setMove((byte)8, Intent.ATTACK, 10, 3, true);
/*     */       } else {
/* 304 */         setMove(SLUDGE_NAME, (byte)6, Intent.ATTACK_DEBUFF, 18);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 312 */     super.damage(info);
/*     */     
/* 314 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 315 */       this.state.setAnimation(0, "Hit", false);
/* 316 */       if (this.form1) {
/* 317 */         this.state.addAnimation(0, "Idle_1", true, 0.0F);
/*     */       } else {
/* 319 */         this.state.addAnimation(0, "Idle_2", true, 0.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 323 */     if (this.currentHealth <= 0 && !this.halfDead) {
/* 324 */       if ((AbstractDungeon.getCurrRoom()).cannotLose == true) {
/* 325 */         this.halfDead = true;
/*     */       }
/* 327 */       for (AbstractPower p : this.powers) {
/* 328 */         p.onDeath();
/*     */       }
/* 330 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 331 */         r.onMonsterDeath(this);
/*     */       }
/* 333 */       addToTop((AbstractGameAction)new ClearCardQueueAction());
/*     */       
/* 335 */       for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
/* 336 */         AbstractPower p = s.next();
/* 337 */         if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals("Curiosity") || p.ID.equals("Unawakened") || p.ID
/* 338 */           .equals("Shackled")) {
/* 339 */           s.remove();
/*     */         }
/*     */       } 
/*     */       
/* 343 */       setMove((byte)3, Intent.UNKNOWN);
/* 344 */       createIntent();
/* 345 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[0]));
/* 346 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.UNKNOWN));
/* 347 */       applyPowers();
/* 348 */       this.firstTurn = true;
/* 349 */       this.form1 = false;
/*     */ 
/*     */       
/* 352 */       if (GameActionManager.turn <= 1) {
/* 353 */         UnlockTracker.unlockAchievement("YOU_ARE_NOTHING");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 360 */     super.update();
/* 361 */     if (!this.isDying && this.animateParticles) {
/* 362 */       this.fireTimer -= Gdx.graphics.getDeltaTime();
/* 363 */       if (this.fireTimer < 0.0F) {
/* 364 */         this.fireTimer = 0.1F;
/* 365 */         AbstractDungeon.effectList.add(new AwakenedEyeParticle(this.skeleton
/* 366 */               .getX() + this.eye.getWorldX(), this.skeleton.getY() + this.eye.getWorldY()));
/* 367 */         this.wParticles.add(new AwakenedWingParticle());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 372 */     for (Iterator<AwakenedWingParticle> p = this.wParticles.iterator(); p.hasNext(); ) {
/* 373 */       AwakenedWingParticle e = p.next();
/* 374 */       e.update();
/* 375 */       if (e.isDone) {
/* 376 */         p.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 383 */     for (AwakenedWingParticle p : this.wParticles) {
/* 384 */       if (p.renderBehind) {
/* 385 */         p.render(sb, this.skeleton.getX() + this.back.getWorldX(), this.skeleton.getY() + this.back.getWorldY());
/*     */       }
/*     */     } 
/*     */     
/* 389 */     super.render(sb);
/*     */     
/* 391 */     for (AwakenedWingParticle p : this.wParticles) {
/* 392 */       if (!p.renderBehind) {
/* 393 */         p.render(sb, this.skeleton.getX() + this.back.getWorldX(), this.skeleton.getY() + this.back.getWorldY());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 400 */     if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
/* 401 */       super.die();
/* 402 */       useFastShakeAnimation(5.0F);
/* 403 */       CardCrawlGame.screenShake.rumble(4.0F);
/* 404 */       if (this.saidPower) {
/* 405 */         CardCrawlGame.sound.play("VO_AWAKENEDONE_2");
/* 406 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[1], false));
/*     */         
/* 408 */         this.saidPower = true;
/*     */       } 
/*     */       
/* 411 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 412 */         if (!m.isDying && m instanceof com.megacrit.cardcrawl.monsters.exordium.Cultist) {
/* 413 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(m));
/*     */         }
/*     */       } 
/*     */       
/* 417 */       onBossVictoryLogic();
/* 418 */       UnlockTracker.hardUnlockOverride("CROW");
/* 419 */       UnlockTracker.unlockAchievement("CROW");
/* 420 */       onFinalBossVictoryLogic();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\AwakenedOne.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */