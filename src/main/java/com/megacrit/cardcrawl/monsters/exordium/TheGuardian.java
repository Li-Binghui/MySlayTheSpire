/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class TheGuardian extends AbstractMonster {
/*  36 */   private static final Logger logger = LogManager.getLogger(TheGuardian.class.getName());
/*     */   public static final String ID = "TheGuardian";
/*  38 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TheGuardian");
/*  39 */   public static final String NAME = monsterStrings.NAME;
/*  40 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  41 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final String DEFENSIVE_MODE = "Defensive Mode";
/*     */   
/*     */   private static final String OFFENSIVE_MODE = "Offensive Mode";
/*     */   
/*     */   private static final String RESET_THRESH = "Reset Threshold";
/*     */   public static final int HP = 240;
/*     */   public static final int A_2_HP = 250;
/*     */   private static final int DMG_THRESHOLD = 30;
/*     */   private static final int A_2_DMG_THRESHOLD = 35;
/*     */   private static final int A_19_DMG_THRESHOLD = 40;
/*     */   private int dmgThreshold;
/*  54 */   private int dmgThresholdIncrease = 10;
/*     */   private int dmgTaken;
/*     */   private static final int FIERCE_BASH_DMG = 32;
/*     */   private static final int A_2_FIERCE_BASH_DMG = 36;
/*     */   private static final int ROLL_DMG = 9;
/*     */   private static final int A_2_ROLL_DMG = 10;
/*     */   private int fierceBashDamage;
/*  61 */   private int whirlwindDamage = 5, twinSlamDamage = 8, rollDamage, whirlwindCount = 4, DEFENSIVE_BLOCK = 20;
/*     */   
/*  63 */   private int blockAmount = 9; private int thornsDamage = 3; private int VENT_DEBUFF = 2;
/*     */   private boolean isOpen = true;
/*     */   private boolean closeUpTriggered = false;
/*     */   private static final byte CLOSE_UP = 1;
/*     */   private static final byte FIERCE_BASH = 2;
/*  68 */   private static final String CLOSEUP_NAME = MOVES[0], FIERCEBASH_NAME = MOVES[1], TWINSLAM_NAME = MOVES[3]; private static final byte ROLL_ATTACK = 3; private static final byte TWIN_SLAM = 4; private static final byte WHIRLWIND = 5; private static final byte CHARGE_UP = 6; private static final byte VENT_STEAM = 7;
/*  69 */   private static final String WHIRLWIND_NAME = MOVES[4], CHARGEUP_NAME = MOVES[5], VENTSTEAM_NAME = MOVES[6];
/*     */   
/*     */   public TheGuardian() {
/*  72 */     super(NAME, "TheGuardian", 240, 0.0F, 95.0F, 440.0F, 350.0F, null, -50.0F, -100.0F);
/*  73 */     this.type = EnemyType.BOSS;
/*  74 */     this.dialogX = -100.0F * Settings.scale;
/*  75 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  77 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  78 */       setHp(250);
/*  79 */       this.dmgThreshold = 40;
/*  80 */     } else if (AbstractDungeon.ascensionLevel >= 9) {
/*  81 */       setHp(250);
/*  82 */       this.dmgThreshold = 35;
/*     */     } else {
/*  84 */       setHp(240);
/*  85 */       this.dmgThreshold = 30;
/*     */     } 
/*     */     
/*  88 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  89 */       this.fierceBashDamage = 36;
/*  90 */       this.rollDamage = 10;
/*     */     } else {
/*  92 */       this.fierceBashDamage = 32;
/*  93 */       this.rollDamage = 9;
/*     */     } 
/*     */     
/*  96 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.fierceBashDamage));
/*  97 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.rollDamage));
/*  98 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.whirlwindDamage));
/*  99 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.twinSlamDamage));
/*     */     
/* 101 */     loadAnimation("images/monsters/theBottom/boss/guardian/skeleton.atlas", "images/monsters/theBottom/boss/guardian/skeleton.json", 2.0F);
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.state.setAnimation(0, "idle", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 110 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 111 */       CardCrawlGame.music.unsilenceBGM();
/* 112 */       AbstractDungeon.scene.fadeOutAmbiance();
/* 113 */       AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BOTTOM");
/*     */     } 
/*     */     
/* 116 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ModeShiftPower((AbstractCreature)this, this.dmgThreshold)));
/*     */     
/* 118 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Reset Threshold"));
/* 119 */     UnlockTracker.markBossAsSeen("GUARDIAN");
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 124 */     switch (this.nextMove) {
/*     */       case 1:
/* 126 */         useCloseUp();
/*     */         return;
/*     */       case 2:
/* 129 */         useFierceBash();
/*     */         return;
/*     */       case 7:
/* 132 */         useVentSteam();
/*     */         return;
/*     */       case 3:
/* 135 */         useRollAttack();
/*     */         return;
/*     */       case 4:
/* 138 */         useTwinSmash();
/*     */         return;
/*     */       case 5:
/* 141 */         useWhirlwind();
/*     */         return;
/*     */       case 6:
/* 144 */         useChargeUp();
/*     */         return;
/*     */     } 
/* 147 */     logger.info("ERROR");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void useFierceBash() {
/* 153 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 154 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 155 */           .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 156 */     setMove(VENTSTEAM_NAME, (byte)7, Intent.STRONG_DEBUFF);
/*     */   }
/*     */   
/*     */   private void useVentSteam() {
/* 160 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, this.VENT_DEBUFF, true), this.VENT_DEBUFF));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.VENT_DEBUFF, true), this.VENT_DEBUFF));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     setMove(WHIRLWIND_NAME, (byte)5, Intent.ATTACK, this.whirlwindDamage, this.whirlwindCount, true);
/*     */   }
/*     */   
/*     */   private void useCloseUp() {
/* 176 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, DIALOG[1]));
/* 177 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 178 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new SharpHidePower((AbstractCreature)this, this.thornsDamage + 1)));
/*     */     } else {
/*     */       
/* 181 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new SharpHidePower((AbstractCreature)this, this.thornsDamage)));
/*     */     } 
/*     */     
/* 184 */     setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */   }
/*     */   
/*     */   private void useTwinSmash() {
/* 188 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Offensive Mode"));
/* 189 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 190 */           .get(3), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/* 191 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 192 */           .get(3), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/* 193 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, "Sharp Hide"));
/* 194 */     setMove(WHIRLWIND_NAME, (byte)5, Intent.ATTACK, this.whirlwindDamage, this.whirlwindCount, true);
/*     */   }
/*     */   
/*     */   private void useRollAttack() {
/* 198 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 199 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 200 */           .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 201 */     setMove(TWINSLAM_NAME, (byte)4, Intent.ATTACK_BUFF, this.twinSlamDamage, 2, true);
/*     */   }
/*     */   
/*     */   private void useWhirlwind() {
/* 205 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 206 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_WHIRLWIND"));
/* 207 */     for (int i = 0; i < this.whirlwindCount; i++) {
/* 208 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
/* 209 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new CleaveEffect(true), 0.15F));
/* 210 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 211 */             .get(2), AbstractGameAction.AttackEffect.NONE, true));
/*     */     } 
/*     */     
/* 214 */     setMove(CHARGEUP_NAME, (byte)6, Intent.DEFEND);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void useChargeUp() {
/* 221 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmount));
/* 222 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_GUARDIAN_DESTROY"));
/* 223 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2], 1.0F, 2.5F));
/*     */     
/* 225 */     setMove(FIERCEBASH_NAME, (byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 230 */     if (this.isOpen) {
/* 231 */       setMove(CHARGEUP_NAME, (byte)6, Intent.DEFEND);
/*     */     } else {
/* 233 */       setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 242 */     switch (stateName) {
/*     */       case "Defensive Mode":
/* 244 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, "Mode Shift"));
/*     */         
/* 246 */         CardCrawlGame.sound.play("GUARDIAN_ROLL_UP");
/* 247 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.DEFENSIVE_BLOCK));
/* 248 */         this.stateData.setMix("idle", "transition", 0.1F);
/* 249 */         this.state.setTimeScale(2.0F);
/* 250 */         this.state.setAnimation(0, "transition", false);
/* 251 */         this.state.addAnimation(0, "defensive", true, 0.0F);
/* 252 */         this.dmgThreshold += this.dmgThresholdIncrease;
/* 253 */         setMove(CLOSEUP_NAME, (byte)1, Intent.BUFF);
/* 254 */         createIntent();
/* 255 */         this.isOpen = false;
/* 256 */         updateHitbox(0.0F, 95.0F, 440.0F, 250.0F);
/* 257 */         healthBarUpdatedEvent();
/*     */         break;
/*     */       case "Offensive Mode":
/* 260 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ModeShiftPower((AbstractCreature)this, this.dmgThreshold)));
/*     */         
/* 262 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Reset Threshold"));
/* 263 */         if (this.currentBlock != 0) {
/* 264 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new LoseBlockAction((AbstractCreature)this, (AbstractCreature)this, this.currentBlock));
/*     */         }
/* 266 */         this.stateData.setMix("defensive", "idle", 0.2F);
/* 267 */         this.state.setTimeScale(1.0F);
/* 268 */         this.state.setAnimation(0, "idle", true);
/* 269 */         this.isOpen = true;
/* 270 */         this.closeUpTriggered = false;
/* 271 */         updateHitbox(0.0F, 95.0F, 440.0F, 350.0F);
/* 272 */         healthBarUpdatedEvent();
/*     */         break;
/*     */       case "Reset Threshold":
/* 275 */         this.dmgTaken = 0;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 288 */     int tmpHealth = this.currentHealth;
/* 289 */     super.damage(info);
/*     */     
/* 291 */     if (this.isOpen && !this.closeUpTriggered && 
/* 292 */       tmpHealth > this.currentHealth && !this.isDying) {
/* 293 */       this.dmgTaken += tmpHealth - this.currentHealth;
/* 294 */       if (getPower("Mode Shift") != null) {
/* 295 */         (getPower("Mode Shift")).amount -= tmpHealth - this.currentHealth;
/* 296 */         getPower("Mode Shift").updateDescription();
/*     */       } 
/*     */       
/* 299 */       if (this.dmgTaken >= this.dmgThreshold) {
/* 300 */         this.dmgTaken = 0;
/* 301 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntenseZoomEffect(this.hb.cX, this.hb.cY, false), 0.05F, true));
/*     */         
/* 303 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Defensive Mode"));
/* 304 */         this.closeUpTriggered = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 315 */     super.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 320 */     useFastShakeAnimation(5.0F);
/* 321 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 322 */     super.die();
/* 323 */     onBossVictoryLogic();
/* 324 */     UnlockTracker.hardUnlockOverride("GUARDIAN");
/* 325 */     UnlockTracker.unlockAchievement("GUARDIAN");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\TheGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */