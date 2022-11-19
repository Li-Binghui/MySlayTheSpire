/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
/*     */ 
/*     */ public class SpireGrowth extends AbstractMonster {
/*     */   public static final String ID = "Serpent";
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Serpent");
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int START_HP = 170;
/*     */   private static final int A_2_START_HP = 190;
/*  29 */   private int tackleDmg = 16, smashDmg = 22, constrictDmg = 10;
/*  30 */   private int A_2_tackleDmg = 18; private int A_2_smashDmg = 25;
/*     */   private int tackleDmgActual;
/*     */   private int smashDmgActual;
/*     */   
/*     */   public SpireGrowth() {
/*  35 */     super(NAME, "Serpent", 170, -10.0F, -35.0F, 480.0F, 430.0F, null, 0.0F, 10.0F);
/*  36 */     loadAnimation("images/monsters/theForest/spireGrowth/skeleton.atlas", "images/monsters/theForest/spireGrowth/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  41 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  42 */       setHp(190);
/*     */     } else {
/*  44 */       setHp(170);
/*     */     } 
/*     */     
/*  47 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  48 */       this.tackleDmgActual = this.A_2_tackleDmg;
/*  49 */       this.smashDmgActual = this.A_2_smashDmg;
/*     */     } else {
/*  51 */       this.tackleDmgActual = this.tackleDmg;
/*  52 */       this.smashDmgActual = this.smashDmg;
/*     */     } 
/*     */     
/*  55 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  56 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  57 */     e.setTimeScale(1.3F);
/*  58 */     this.stateData.setMix("Hurt", "Idle", 0.2F);
/*  59 */     this.stateData.setMix("Idle", "Hurt", 0.2F);
/*     */     
/*  61 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.tackleDmgActual));
/*  62 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.smashDmgActual));
/*     */   }
/*     */   private static final byte QUICK_TACKLE = 1; private static final byte CONSTRICT = 2; private static final byte SMASH = 3;
/*     */   
/*     */   public void takeTurn() {
/*  67 */     switch (this.nextMove) {
/*     */       case 1:
/*  69 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/*  70 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  71 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         break;
/*     */       case 2:
/*  74 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  75 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  76 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new ConstrictedPower((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, this.constrictDmg + 2)));
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/*  82 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new ConstrictedPower((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, this.constrictDmg)));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  91 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  92 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  93 */               .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         break;
/*     */     } 
/*  96 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 102 */     if (AbstractDungeon.ascensionLevel >= 17 && 
/* 103 */       !AbstractDungeon.player.hasPower("Constricted") && !lastMove((byte)2)) {
/* 104 */       setMove((byte)2, Intent.STRONG_DEBUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 109 */     if (num < 50 && !lastTwoMoves((byte)1)) {
/* 110 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 114 */     if (!AbstractDungeon.player.hasPower("Constricted") && !lastMove((byte)2)) {
/* 115 */       setMove((byte)2, Intent.STRONG_DEBUFF);
/*     */       
/*     */       return;
/*     */     } 
/* 119 */     if (!lastTwoMoves((byte)3)) {
/* 120 */       setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       return;
/*     */     } 
/* 123 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 130 */     super.damage(info);
/* 131 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 132 */       this.state.setAnimation(0, "Hurt", false);
/* 133 */       this.state.setTimeScale(1.3F);
/* 134 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 140 */     switch (key) {
/*     */       case "ATTACK":
/* 142 */         this.state.setAnimation(0, "Attack", false);
/* 143 */         this.state.setTimeScale(1.3F);
/* 144 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\SpireGrowth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */