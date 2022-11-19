/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.powers.FrailPower;
/*     */ 
/*     */ public class SphericGuardian extends AbstractMonster {
/*     */   public static final String ID = "SphericGuardian";
/*  26 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SphericGuardian");
/*  27 */   public static final String NAME = monsterStrings.NAME;
/*  28 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  29 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   private static final float IDLE_TIMESCALE = 0.8F;
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = 10.0F;
/*     */   private static final float HB_W = 280.0F;
/*     */   private static final float HB_H = 280.0F;
/*     */   private static final int DMG = 10;
/*     */   private static final int A_2_DMG = 11;
/*     */   private int dmg;
/*     */   private static final int SLAM_AMT = 2;
/*     */   
/*     */   public SphericGuardian() {
/*  41 */     this(0.0F, 0.0F);
/*     */   }
/*     */   private static final int HARDEN_BLOCK = 15; private static final int FRAIL_AMT = 5; private static final int ACTIVATE_BLOCK = 25; private static final int ARTIFACT_AMT = 3; private static final int STARTING_BLOCK_AMT = 40; private static final byte BIG_ATTACK = 1; private static final byte INITIAL_BLOCK_GAIN = 2; private static final byte BLOCK_ATTACK = 3; private static final byte FRAIL_ATTACK = 4; private boolean firstMove = true, secondMove = true;
/*     */   public SphericGuardian(float x, float y) {
/*  45 */     super(NAME, "SphericGuardian", 20, 0.0F, 10.0F, 280.0F, 280.0F, null, x, y);
/*     */     
/*  47 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  48 */       this.dmg = 11;
/*     */     } else {
/*  50 */       this.dmg = 10;
/*     */     } 
/*     */     
/*  53 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.dmg));
/*     */     
/*  55 */     loadAnimation("images/monsters/theCity/sphere/skeleton.atlas", "images/monsters/theCity/sphere/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  61 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  62 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  63 */     this.stateData.setMix("Idle", "Attack", 0.1F);
/*  64 */     this.state.setTimeScale(0.8F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  69 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new BarricadePower((AbstractCreature)this)));
/*  70 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3)));
/*     */     
/*  72 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 40));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  77 */     switch (this.nextMove) {
/*     */       case 1:
/*  79 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  81 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  82 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
/*  83 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  84 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         break;
/*     */       case 2:
/*  87 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  88 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 35));
/*     */         } else {
/*  90 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 25));
/*     */         } 
/*  92 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.2F));
/*  93 */         if (MathUtils.randomBoolean()) {
/*  94 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("SPHERE_DETECT_VO_1")); break;
/*     */         } 
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("SPHERE_DETECT_VO_2"));
/*     */         break;
/*     */       
/*     */       case 3:
/* 100 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 15));
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 103 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         break;
/*     */       case 4:
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 107 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 108 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 5, true), 5));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 122 */     switch (key) {
/*     */       case "ATTACK":
/* 124 */         this.state.setAnimation(0, "Attack", false);
/* 125 */         this.state.setTimeScale(0.8F);
/* 126 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 135 */     super.damage(info);
/* 136 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 137 */       this.state.setAnimation(0, "Hit", false);
/* 138 */       this.state.setTimeScale(0.8F);
/* 139 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 145 */     if (this.firstMove) {
/* 146 */       this.firstMove = false;
/* 147 */       setMove((byte)2, Intent.DEFEND);
/*     */       
/*     */       return;
/*     */     } 
/* 151 */     if (this.secondMove) {
/* 152 */       this.secondMove = false;
/* 153 */       setMove((byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 157 */     if (lastMove((byte)1)) {
/* 158 */       setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(0)).base);
/*     */     } else {
/* 160 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 166 */     super.die();
/* 167 */     if (MathUtils.randomBoolean()) {
/* 168 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("SPHERE_DETECT_VO_1"));
/*     */     } else {
/* 170 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("SPHERE_DETECT_VO_2"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\SphericGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */