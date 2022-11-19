/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ public class Donu extends AbstractMonster {
/*  25 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Donu"); public static final String ID = "Donu";
/*  26 */   public static final String NAME = monsterStrings.NAME;
/*  27 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  28 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final int HP = 250;
/*     */   public static final int A_2_HP = 265;
/*     */   private static final byte BEAM = 0;
/*     */   private static final byte CIRCLE_OF_PROTECTION = 2;
/*     */   private static final int ARTIFACT_AMT = 2;
/*     */   private static final int BEAM_DMG = 10;
/*     */   private static final int BEAM_AMT = 2;
/*     */   private static final int A_2_BEAM_DMG = 12;
/*     */   private int beamDmg;
/*  39 */   private static final String CIRCLE_NAME = MOVES[0];
/*     */   
/*     */   private static final int CIRCLE_STR_AMT = 3;
/*     */   private boolean isAttacking;
/*     */   
/*     */   public Donu() {
/*  45 */     super(NAME, "Donu", 250, 0.0F, -20.0F, 390.0F, 390.0F, null, 100.0F, 20.0F);
/*  46 */     loadAnimation("images/monsters/theForest/donu/skeleton.atlas", "images/monsters/theForest/donu/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  50 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  51 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  52 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  53 */     this.stateData.setMix("Attack_2", "Idle", 0.1F);
/*     */     
/*  55 */     this.type = EnemyType.BOSS;
/*  56 */     this.dialogX = -200.0F * Settings.scale;
/*  57 */     this.dialogY = 10.0F * Settings.scale;
/*     */     
/*  59 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  60 */       setHp(265);
/*     */     } else {
/*  62 */       setHp(250);
/*     */     } 
/*     */     
/*  65 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  66 */       this.beamDmg = 12;
/*     */     } else {
/*  68 */       this.beamDmg = 10;
/*     */     } 
/*     */     
/*  71 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.beamDmg));
/*  72 */     this.isAttacking = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/*  77 */     switch (stateName) {
/*     */       case "ATTACK":
/*  79 */         this.state.setAnimation(0, "Attack_2", false);
/*  80 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/*  87 */     super.damage(info);
/*  88 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/*  89 */       this.state.setAnimation(0, "Hit", false);
/*  90 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  96 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  97 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3)));
/*     */     } else {
/*     */       
/* 100 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/* 107 */     switch (this.nextMove) {
/*     */       case 0:
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 111 */         for (i = 0; i < 2; i++) {
/* 112 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 113 */                 .get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         }
/* 115 */         this.isAttacking = false;
/*     */         break;
/*     */       case 2:
/* 118 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 119 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_DONU_DEFENSE"));
/* 120 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, 3), 3));
/*     */         } 
/*     */         
/* 123 */         this.isAttacking = true;
/*     */         break;
/*     */     } 
/* 126 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 131 */     if (this.isAttacking) {
/* 132 */       setMove((byte)0, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */     } else {
/* 134 */       setMove(CIRCLE_NAME, (byte)2, Intent.BUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 140 */     super.die();
/* 141 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 142 */       useFastShakeAnimation(5.0F);
/* 143 */       CardCrawlGame.screenShake.rumble(4.0F);
/* 144 */       onBossVictoryLogic();
/* 145 */       UnlockTracker.hardUnlockOverride("DONUT");
/* 146 */       UnlockTracker.unlockAchievement("SHAPES");
/* 147 */       onFinalBossVictoryLogic();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Donu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */