/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ 
/*     */ public class Taskmaster extends AbstractMonster {
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SlaverBoss"); public static final String ID = "SlaverBoss";
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 54; private static final int HP_MAX = 60;
/*     */   private static final int A_2_HP_MIN = 57;
/*     */   private static final int A_2_HP_MAX = 64;
/*     */   private static final int WHIP_DMG = 4;
/*     */   private static final int SCOURING_WHIP_DMG = 7;
/*     */   private static final int WOUNDS = 1;
/*     */   private static final int A_2_WOUNDS = 2;
/*     */   private int woundCount;
/*     */   private static final byte SCOURING_WHIP = 2;
/*     */   
/*     */   public Taskmaster(float x, float y) {
/*  36 */     super(NAME, "SlaverBoss", AbstractDungeon.monsterHpRng.random(54, 60), -10.0F, -8.0F, 200.0F, 280.0F, null, x, y);
/*  37 */     this.type = EnemyType.ELITE;
/*     */     
/*  39 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  40 */       setHp(57, 64);
/*     */     } else {
/*  42 */       setHp(54, 60);
/*     */     } 
/*     */     
/*  45 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  46 */       this.woundCount = 3;
/*  47 */     } else if (AbstractDungeon.ascensionLevel >= 3) {
/*  48 */       this.woundCount = 2;
/*     */     } else {
/*  50 */       this.woundCount = 1;
/*     */     } 
/*     */     
/*  53 */     this.damage.add(new DamageInfo((AbstractCreature)this, 4));
/*  54 */     this.damage.add(new DamageInfo((AbstractCreature)this, 7));
/*     */     
/*  56 */     loadAnimation("images/monsters/theCity/slaverMaster/skeleton.atlas", "images/monsters/theCity/slaverMaster/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  60 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  61 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  66 */     switch (this.nextMove) {
/*     */       case 2:
/*  68 */         playSfx();
/*  69 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  70 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  71 */               .get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*  72 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Wound(), this.woundCount));
/*  73 */         if (AbstractDungeon.ascensionLevel >= 18) {
/*  74 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 1), 1));
/*     */         }
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  81 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/*  86 */     setMove((byte)2, Intent.ATTACK_DEBUFF, 7);
/*     */   }
/*     */   
/*     */   private void playSfx() {
/*  90 */     int roll = MathUtils.random(1);
/*  91 */     if (roll == 0) {
/*  92 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLAVERLEADER_1A"));
/*     */     } else {
/*  94 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLAVERLEADER_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/*  99 */     int roll = MathUtils.random(1);
/* 100 */     if (roll == 0) {
/* 101 */       CardCrawlGame.sound.play("VO_SLAVERLEADER_2A");
/*     */     } else {
/* 103 */       CardCrawlGame.sound.play("VO_SLAVERLEADER_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 109 */     super.die();
/* 110 */     playDeathSfx();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Taskmaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */