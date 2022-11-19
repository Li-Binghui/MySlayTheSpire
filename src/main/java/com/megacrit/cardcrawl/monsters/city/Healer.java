/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ 
/*     */ public class Healer extends AbstractMonster {
/*     */   public static final String ID = "Healer";
/*  24 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Healer");
/*  25 */   public static final String NAME = monsterStrings.NAME;
/*  26 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  27 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final float IDLE_TIMESCALE = 0.8F;
/*     */   
/*     */   public static final String ENC_NAME = "HealerTank";
/*     */   
/*     */   private static final int HP_MIN = 48;
/*     */   private static final int HP_MAX = 56;
/*     */   private static final int A_2_HP_MIN = 50;
/*     */   private static final int A_2_HP_MAX = 58;
/*     */   private static final int MAGIC_DMG = 8;
/*     */   private static final int HEAL_AMT = 16;
/*     */   
/*     */   public Healer(float x, float y) {
/*  41 */     super(NAME, "Healer", 56, 0.0F, -20.0F, 230.0F, 250.0F, null, x, y);
/*     */     
/*  43 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  44 */       setHp(50, 58);
/*     */     } else {
/*  46 */       setHp(48, 56);
/*     */     } 
/*     */     
/*  49 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  50 */       this.magicDmg = 9;
/*  51 */       this.strAmt = 4;
/*  52 */       this.healAmt = 20;
/*  53 */     } else if (AbstractDungeon.ascensionLevel >= 2) {
/*  54 */       this.magicDmg = 9;
/*  55 */       this.strAmt = 3;
/*  56 */       this.healAmt = 16;
/*     */     } else {
/*  58 */       this.magicDmg = 8;
/*  59 */       this.strAmt = 2;
/*  60 */       this.healAmt = 16;
/*     */     } 
/*     */     
/*  63 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.magicDmg));
/*     */     
/*  65 */     loadAnimation("images/monsters/theCity/healer/skeleton.atlas", "images/monsters/theCity/healer/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  69 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  70 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  71 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  72 */     this.state.setTimeScale(0.8F);
/*     */   }
/*     */   private static final int STR_AMOUNT = 2; private static final int A_2_MAGIC_DMG = 9; private static final int A_2_STR_AMOUNT = 3; private int magicDmg; private int strAmt; private int healAmt; private static final byte ATTACK = 1; private static final byte HEAL = 2; private static final byte BUFF = 3;
/*     */   
/*     */   public void takeTurn() {
/*  77 */     switch (this.nextMove) {
/*     */       case 1:
/*  79 */         playSfx();
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  81 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  82 */               .get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  83 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/*  91 */         playSfx();
/*  92 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "STAFF_RAISE"));
/*  93 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.25F));
/*  94 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/*  95 */           if (!m.isDying && !m.isEscaping) {
/*  96 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)m, (AbstractCreature)this, this.healAmt));
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 3:
/* 101 */         playSfx();
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "STAFF_RAISE"));
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.25F));
/* 104 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 105 */           if (!m.isDying && !m.isEscaping) {
/* 106 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.strAmt), this.strAmt));
/*     */           }
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 114 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 118 */     if (MathUtils.randomBoolean()) {
/* 119 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_HEALER_1A"));
/*     */     } else {
/* 121 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_HEALER_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 126 */     int roll = MathUtils.random(2);
/* 127 */     if (roll == 0) {
/* 128 */       CardCrawlGame.sound.play("VO_HEALER_2A");
/* 129 */     } else if (roll == 1) {
/* 130 */       CardCrawlGame.sound.play("VO_HEALER_2B");
/*     */     } else {
/* 132 */       CardCrawlGame.sound.play("VO_HEALER_2C");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 138 */     switch (key) {
/*     */       case "STAFF_RAISE":
/* 140 */         this.state.setAnimation(0, "Attack", false);
/* 141 */         this.state.setTimeScale(0.8F);
/* 142 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 151 */     int needToHeal = 0;
/*     */     
/* 153 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 154 */       if (!m.isDying && !m.isEscaping) {
/* 155 */         needToHeal += m.maxHealth - m.currentHealth;
/*     */       }
/*     */     } 
/*     */     
/* 159 */     if (AbstractDungeon.ascensionLevel >= 17) {
/* 160 */       if (needToHeal > 20 && !lastTwoMoves((byte)2)) {
/* 161 */         setMove((byte)2, Intent.BUFF);
/*     */         
/*     */         return;
/*     */       } 
/* 165 */     } else if (needToHeal > 15 && !lastTwoMoves((byte)2)) {
/* 166 */       setMove((byte)2, Intent.BUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 171 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/* 173 */       if (num >= 40 && !lastMove((byte)1)) {
/* 174 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/* 179 */     } else if (num >= 40 && !lastTwoMoves((byte)1)) {
/* 180 */       setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 185 */     if (!lastTwoMoves((byte)3)) {
/* 186 */       setMove((byte)3, Intent.BUFF);
/*     */       return;
/*     */     } 
/* 189 */     setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 196 */     super.damage(info);
/* 197 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 198 */       this.state.setAnimation(0, "Hit", false);
/* 199 */       this.state.setTimeScale(0.8F);
/* 200 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 206 */     playDeathSfx();
/* 207 */     this.state.setTimeScale(0.1F);
/* 208 */     useShakeAnimation(5.0F);
/* 209 */     super.die();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Healer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */