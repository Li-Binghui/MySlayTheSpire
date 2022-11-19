/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GenericStrengthUpPower;
/*     */ 
/*     */ public class OrbWalker extends AbstractMonster {
/*     */   public static final String ID = "Orb Walker";
/*  24 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Orb Walker"); public static final String DOUBLE_ENCOUNTER = "Double Orb Walker";
/*  25 */   public static final String NAME = monsterStrings.NAME;
/*  26 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  27 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 90; private static final int HP_MAX = 96; private static final int A_2_HP_MIN = 92;
/*     */   private static final int A_2_HP_MAX = 102;
/*     */   public static final int LASER_DMG = 10;
/*     */   public static final int CLAW_DMG = 15;
/*     */   public static final int A_2_LASER_DMG = 11;
/*     */   public static final int A_2_CLAW_DMG = 16;
/*     */   private int clawDmg;
/*     */   private int laserDmg;
/*     */   private static final byte LASER = 1;
/*     */   private static final byte CLAW = 2;
/*     */   
/*     */   public OrbWalker(float x, float y) {
/*  39 */     super(NAME, "Orb Walker", AbstractDungeon.monsterHpRng.random(90, 96), -20.0F, -14.0F, 280.0F, 250.0F, null, x, y);
/*     */     
/*  41 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  42 */       setHp(92, 102);
/*     */     } else {
/*  44 */       setHp(90, 96);
/*     */     } 
/*     */     
/*  47 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  48 */       this.clawDmg = 16;
/*  49 */       this.laserDmg = 11;
/*     */     } else {
/*  51 */       this.clawDmg = 15;
/*  52 */       this.laserDmg = 10;
/*     */     } 
/*     */     
/*  55 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.laserDmg));
/*  56 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.clawDmg));
/*     */     
/*  58 */     loadAnimation("images/monsters/theForest/orbWalker/skeleton.atlas", "images/monsters/theForest/orbWalker/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  62 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  63 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  68 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  69 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new GenericStrengthUpPower((AbstractCreature)this, MOVES[0], 5)));
/*     */     } else {
/*     */       
/*  72 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new GenericStrengthUpPower((AbstractCreature)this, MOVES[0], 3)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  79 */     switch (this.nextMove) {
/*     */       case 2:
/*  81 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  82 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  83 */               .get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*     */         break;
/*     */       case 1:
/*  86 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  87 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  89 */               .get(0), AbstractGameAction.AttackEffect.FIRE));
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAndDeckAction((AbstractCard)new Burn()));
/*     */         break;
/*     */     } 
/*  93 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/*  98 */     if (num < 40) {
/*  99 */       if (!lastTwoMoves((byte)2)) {
/* 100 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       } else {
/* 102 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/* 105 */     } else if (!lastTwoMoves((byte)1)) {
/* 106 */       setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */     } else {
/* 108 */       setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 115 */     super.damage(info);
/* 116 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 117 */       this.state.setAnimation(0, "Hit", false);
/* 118 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 124 */     switch (key) {
/*     */       case "ATTACK":
/* 126 */         this.state.setAnimation(0, "Attack", false);
/* 127 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\OrbWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */