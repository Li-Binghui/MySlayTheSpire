/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.unique.CannotLoseAction;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SplitPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class SlimeBoss
/*     */   extends AbstractMonster
/*     */ {
/*  45 */   private static final Logger logger = LogManager.getLogger(SlimeBoss.class.getName());
/*     */   public static final String ID = "SlimeBoss";
/*  47 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SlimeBoss");
/*  48 */   public static final String NAME = monsterStrings.NAME;
/*  49 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  50 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final int HP = 140;
/*     */   
/*     */   public static final int A_2_HP = 150;
/*     */   
/*     */   public static final int TACKLE_DAMAGE = 9;
/*     */   
/*     */   public static final int SLAM_DAMAGE = 35;
/*     */   
/*     */   public static final int A_2_TACKLE_DAMAGE = 10;
/*     */   public static final int A_2_SLAM_DAMAGE = 38;
/*  62 */   private static final String SLAM_NAME = MOVES[0], PREP_NAME = MOVES[1], SPLIT_NAME = MOVES[2]; private int tackleDmg; private int slamDmg; public static final int STICKY_TURNS = 3; private static final byte SLAM = 1; private static final byte PREP_SLAM = 2; private static final byte SPLIT = 3; private static final byte STICKY = 4;
/*  63 */   private static final String STICKY_NAME = MOVES[3];
/*     */   private boolean firstTurn = true;
/*     */   
/*     */   public SlimeBoss() {
/*  67 */     super(NAME, "SlimeBoss", 140, 0.0F, -30.0F, 400.0F, 350.0F, null, 0.0F, 28.0F);
/*  68 */     this.type = EnemyType.BOSS;
/*     */     
/*  70 */     this.dialogX = -150.0F * Settings.scale;
/*  71 */     this.dialogY = -70.0F * Settings.scale;
/*     */     
/*  73 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  74 */       setHp(150);
/*     */     } else {
/*  76 */       setHp(140);
/*     */     } 
/*     */     
/*  79 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  80 */       this.tackleDmg = 10;
/*  81 */       this.slamDmg = 38;
/*     */     } else {
/*  83 */       this.tackleDmg = 9;
/*  84 */       this.slamDmg = 35;
/*     */     } 
/*     */     
/*  87 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.tackleDmg));
/*  88 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.slamDmg));
/*  89 */     this.powers.add(new SplitPower((AbstractCreature)this));
/*     */     
/*  91 */     loadAnimation("images/monsters/theBottom/boss/slime/skeleton.atlas", "images/monsters/theBottom/boss/slime/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  95 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  96 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 101 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 102 */       CardCrawlGame.music.unsilenceBGM();
/* 103 */       AbstractDungeon.scene.fadeOutAmbiance();
/* 104 */       AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BOTTOM");
/*     */     } 
/*     */     
/* 107 */     UnlockTracker.markBossAsSeen("SLIME");
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 112 */     switch (this.nextMove) {
/*     */       case 4:
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_SLIME_ATTACK"));
/* 116 */         if (AbstractDungeon.ascensionLevel >= 19) {
/* 117 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 5));
/*     */         } else {
/*     */           
/* 120 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 3));
/*     */         } 
/*     */         
/* 123 */         setMove(PREP_NAME, (byte)2, Intent.UNKNOWN);
/*     */         break;
/*     */       case 2:
/* 126 */         playSfx();
/* 127 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[0], 1.0F, 2.0F));
/* 128 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShakeScreenAction(0.3F, ScreenShake.ShakeDur.LONG, ScreenShake.ShakeIntensity.LOW));
/*     */         
/* 130 */         setMove(SLAM_NAME, (byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         break;
/*     */       case 1:
/* 133 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateJumpAction((AbstractCreature)this));
/* 134 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(0.1F, 1.0F, 0.1F, 0.0F))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 140 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.8F));
/* 141 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 142 */               .get(1), AbstractGameAction.AttackEffect.POISON));
/* 143 */         setMove(STICKY_NAME, (byte)4, Intent.STRONG_DEBUFF);
/*     */         break;
/*     */       case 3:
/* 146 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CannotLoseAction());
/* 147 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateShakeAction((AbstractCreature)this, 1.0F, 0.1F));
/* 148 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HideHealthBarAction((AbstractCreature)this));
/* 149 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SuicideAction(this, false));
/* 150 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(1.0F));
/* 151 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("SLIME_SPLIT"));
/*     */         
/* 153 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new SpikeSlime_L(-385.0F, 20.0F, 0, this.currentHealth), false));
/*     */         
/* 155 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new AcidSlime_L(120.0F, -8.0F, 0, this.currentHealth), false));
/*     */         
/* 157 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
/* 158 */         setMove(SPLIT_NAME, (byte)3, Intent.UNKNOWN);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 164 */     int roll = MathUtils.random(1);
/* 165 */     if (roll == 0) {
/* 166 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLIMEBOSS_1A"));
/*     */     } else {
/* 168 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLIMEBOSS_1B"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 174 */     super.damage(info);
/*     */     
/* 176 */     if (!this.isDying && this.currentHealth <= this.maxHealth / 2.0F && this.nextMove != 3) {
/* 177 */       logger.info("SPLIT");
/* 178 */       setMove(SPLIT_NAME, (byte)3, Intent.UNKNOWN);
/* 179 */       createIntent();
/* 180 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, TextAboveCreatureAction.TextType.INTERRUPTED));
/* 181 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, SPLIT_NAME, (byte)3, Intent.UNKNOWN));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 187 */     if (this.firstTurn) {
/* 188 */       this.firstTurn = false;
/* 189 */       setMove(STICKY_NAME, (byte)4, Intent.STRONG_DEBUFF);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 196 */     super.die();
/* 197 */     CardCrawlGame.sound.play("VO_SLIMEBOSS_2A");
/*     */     
/* 199 */     for (AbstractGameAction a : AbstractDungeon.actionManager.actions) {
/* 200 */       if (a instanceof SpawnMonsterAction) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 205 */     if (this.currentHealth <= 0) {
/* 206 */       useFastShakeAnimation(5.0F);
/* 207 */       CardCrawlGame.screenShake.rumble(4.0F);
/* 208 */       onBossVictoryLogic();
/* 209 */       UnlockTracker.hardUnlockOverride("SLIME");
/* 210 */       UnlockTracker.unlockAchievement("SLIME_BOSS");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\SlimeBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */