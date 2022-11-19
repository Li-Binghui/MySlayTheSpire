/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*     */ 
/*     */ public class TimeEater extends AbstractMonster {
/*  40 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TimeEater"); public static final String ID = "TimeEater";
/*  41 */   public static final String NAME = monsterStrings.NAME;
/*  42 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  43 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   public static final int HP = 456;
/*     */   public static final int A_2_HP = 480;
/*     */   private static final byte REVERBERATE = 2;
/*     */   private static final byte RIPPLE = 3;
/*     */   private static final byte HEAD_SLAM = 4;
/*     */   private static final byte HASTE = 5;
/*     */   private static final int REVERB_DMG = 7;
/*     */   private static final int REVERB_AMT = 3;
/*     */   private static final int A_2_REVERB_DMG = 8;
/*     */   private static final int RIPPLE_BLOCK = 20;
/*     */   private static final int HEAD_SLAM_DMG = 26;
/*     */   private static final int A_2_HEAD_SLAM_DMG = 32;
/*     */   private int reverbDmg;
/*     */   private int headSlamDmg;
/*     */   private static final int HEAD_SLAM_STICKY = 1;
/*     */   private static final int RIPPLE_DEBUFF_TURNS = 1;
/*     */   private boolean usedHaste = false, firstTurn = true;
/*     */   
/*     */   public TimeEater() {
/*  63 */     super(NAME, "TimeEater", 456, -10.0F, -30.0F, 476.0F, 410.0F, null, -50.0F, 30.0F);
/*     */     
/*  65 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  66 */       setHp(480);
/*     */     } else {
/*  68 */       setHp(456);
/*     */     } 
/*     */     
/*  71 */     loadAnimation("images/monsters/theForest/timeEater/skeleton.atlas", "images/monsters/theForest/timeEater/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  75 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  76 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  77 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  78 */     e.setTimeScale(0.8F);
/*     */     
/*  80 */     this.type = EnemyType.BOSS;
/*  81 */     this.dialogX = -200.0F * Settings.scale;
/*  82 */     this.dialogY = 10.0F * Settings.scale;
/*     */     
/*  84 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  85 */       this.reverbDmg = 8;
/*  86 */       this.headSlamDmg = 32;
/*     */     } else {
/*  88 */       this.reverbDmg = 7;
/*  89 */       this.headSlamDmg = 26;
/*     */     } 
/*     */     
/*  92 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.reverbDmg, DamageInfo.DamageType.NORMAL));
/*  93 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.headSlamDmg, DamageInfo.DamageType.NORMAL));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  98 */     CardCrawlGame.music.unsilenceBGM();
/*  99 */     AbstractDungeon.scene.fadeOutAmbiance();
/* 100 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
/* 101 */     UnlockTracker.markBossAsSeen("WIZARD");
/* 102 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new TimeWarpPower((AbstractCreature)this)));
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/* 107 */     if (this.firstTurn) {
/* 108 */       if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.WATCHER) {
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2], 0.5F, 2.0F));
/*     */       } else {
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0], 0.5F, 2.0F));
/*     */       } 
/* 113 */       this.firstTurn = false;
/*     */     } 
/* 115 */     switch (this.nextMove) {
/*     */       case 2:
/* 117 */         for (i = 0; i < 3; i++) {
/* 118 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.75F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 127 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 128 */                 .get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 132 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 20));
/* 133 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 139 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 145 */         if (AbstractDungeon.ascensionLevel >= 19) {
/* 146 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 155 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 156 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/* 157 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 158 */               .get(1), AbstractGameAction.AttackEffect.POISON));
/* 159 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new DrawReductionPower((AbstractCreature)AbstractDungeon.player, 1)));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 164 */         if (AbstractDungeon.ascensionLevel >= 19) {
/* 165 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 2));
/*     */         }
/*     */         break;
/*     */       case 5:
/* 169 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[1], 0.5F, 2.0F));
/* 170 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)this));
/* 171 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, "Shackled"));
/*     */         
/* 173 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth / 2 - this.currentHealth));
/* 174 */         if (AbstractDungeon.ascensionLevel >= 19) {
/* 175 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.headSlamDmg));
/*     */         }
/*     */         break;
/*     */     } 
/* 179 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 184 */     switch (stateName) {
/*     */       case "ATTACK":
/* 186 */         this.state.setAnimation(0, "Attack", false);
/* 187 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 194 */     super.damage(info);
/* 195 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 196 */       this.state.setAnimation(0, "Hit", false);
/* 197 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 203 */     if (this.currentHealth < this.maxHealth / 2 && !this.usedHaste) {
/* 204 */       this.usedHaste = true;
/* 205 */       setMove((byte)5, Intent.BUFF);
/*     */       
/*     */       return;
/*     */     } 
/* 209 */     if (num < 45) {
/* 210 */       if (!lastTwoMoves((byte)2)) {
/* 211 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/*     */         return;
/*     */       } 
/* 214 */       getMove(AbstractDungeon.aiRng.random(50, 99));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 219 */     if (num < 80) {
/* 220 */       if (!lastMove((byte)4)) {
/* 221 */         setMove((byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         return;
/*     */       } 
/* 224 */       if (AbstractDungeon.aiRng.randomBoolean(0.66F)) {
/* 225 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
/*     */         return;
/*     */       } 
/* 228 */       setMove((byte)3, Intent.DEFEND_DEBUFF);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 234 */     if (!lastMove((byte)3)) {
/* 235 */       setMove((byte)3, Intent.DEFEND_DEBUFF);
/*     */       return;
/*     */     } 
/* 238 */     getMove(AbstractDungeon.aiRng.random(74));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 245 */     if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
/* 246 */       useFastShakeAnimation(5.0F);
/* 247 */       CardCrawlGame.screenShake.rumble(4.0F);
/* 248 */       super.die();
/* 249 */       onBossVictoryLogic();
/* 250 */       UnlockTracker.hardUnlockOverride("WIZARD");
/* 251 */       UnlockTracker.unlockAchievement("TIME_EATER");
/* 252 */       onFinalBossVictoryLogic();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\TimeEater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */