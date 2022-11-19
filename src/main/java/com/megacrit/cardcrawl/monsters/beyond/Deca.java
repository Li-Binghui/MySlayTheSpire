/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ public class Deca extends AbstractMonster {
/*  27 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Deca"); public static final String ID = "Deca";
/*  28 */   public static final String NAME = monsterStrings.NAME;
/*  29 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  30 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final String ENC_NAME = "Donu and Deca";
/*     */   
/*     */   public static final int HP = 250;
/*     */   
/*     */   public static final int A_2_HP = 265;
/*     */   private static final byte BEAM = 0;
/*     */   private static final byte SQUARE_OF_PROTECTION = 2;
/*     */   private static final int ARTIFACT_AMT = 2;
/*     */   private static final int BEAM_DMG = 10;
/*     */   private static final int BEAM_AMT = 2;
/*     */   private static final int A_2_BEAM_DMG = 12;
/*     */   private int beamDmg;
/*     */   private static final int BEAM_DAZE_AMT = 2;
/*     */   private static final int PROTECT_BLOCK = 16;
/*     */   private boolean isAttacking;
/*     */   
/*     */   public Deca() {
/*  49 */     super(NAME, "Deca", 250, 0.0F, -26.0F, 390.0F, 390.0F, null, -350.0F, 30.0F);
/*  50 */     loadAnimation("images/monsters/theForest/deca/skeleton.atlas", "images/monsters/theForest/deca/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  54 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  55 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  56 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  57 */     this.stateData.setMix("Attack_2", "Idle", 0.1F);
/*     */     
/*  59 */     this.type = EnemyType.BOSS;
/*  60 */     this.dialogX = -200.0F * Settings.scale;
/*  61 */     this.dialogY = 10.0F * Settings.scale;
/*     */     
/*  63 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  64 */       setHp(265);
/*     */     } else {
/*  66 */       setHp(250);
/*     */     } 
/*     */     
/*  69 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  70 */       this.beamDmg = 12;
/*     */     } else {
/*  72 */       this.beamDmg = 10;
/*     */     } 
/*     */     
/*  75 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.beamDmg));
/*  76 */     this.isAttacking = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/*  81 */     switch (stateName) {
/*     */       case "ATTACK":
/*  83 */         this.state.setAnimation(0, "Attack_2", false);
/*  84 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/*  91 */     super.damage(info);
/*  92 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/*  93 */       this.state.setAnimation(0, "Hit", false);
/*  94 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 100 */     CardCrawlGame.music.unsilenceBGM();
/* 101 */     AbstractDungeon.scene.fadeOutAmbiance();
/* 102 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
/* 103 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 104 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3)));
/*     */     } else {
/*     */       
/* 107 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2)));
/*     */     } 
/*     */     
/* 110 */     UnlockTracker.markBossAsSeen("DONUT");
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/* 115 */     switch (this.nextMove) {
/*     */       case 0:
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 118 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 119 */         for (i = 0; i < 2; i++) {
/* 120 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 121 */                 .get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         }
/* 123 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Dazed(), 2));
/* 124 */         this.isAttacking = false;
/*     */         break;
/*     */       case 2:
/* 127 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 128 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)this, 16));
/* 129 */           if (AbstractDungeon.ascensionLevel >= 19) {
/* 130 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new PlatedArmorPower((AbstractCreature)m, 3), 3));
/*     */           }
/*     */         } 
/*     */         
/* 134 */         this.isAttacking = true;
/*     */         break;
/*     */     } 
/* 137 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 142 */     if (this.isAttacking) {
/* 143 */       setMove((byte)0, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */     }
/* 145 */     else if (AbstractDungeon.ascensionLevel >= 19) {
/* 146 */       setMove((byte)2, Intent.DEFEND_BUFF);
/*     */     } else {
/* 148 */       setMove((byte)2, Intent.DEFEND);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 155 */     super.die();
/* 156 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 157 */       useFastShakeAnimation(5.0F);
/* 158 */       CardCrawlGame.screenShake.rumble(4.0F);
/* 159 */       onBossVictoryLogic();
/* 160 */       UnlockTracker.hardUnlockOverride("DONUT");
/* 161 */       UnlockTracker.unlockAchievement("SHAPES");
/* 162 */       onFinalBossVictoryLogic();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Deca.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */