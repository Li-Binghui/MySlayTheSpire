/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import com.megacrit.cardcrawl.powers.CurlUpPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ 
/*     */ public class LouseNormal extends AbstractMonster {
/*     */   public static final String ID = "FuzzyLouseNormal";
/*  23 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("FuzzyLouseNormal"); public static final String THREE_LOUSE = "ThreeLouse";
/*  24 */   public static final String NAME = monsterStrings.NAME;
/*  25 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  26 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 10; private static final int HP_MAX = 15;
/*     */   private static final int A_2_HP_MIN = 11;
/*     */   private static final int A_2_HP_MAX = 16;
/*     */   private static final byte BITE = 3;
/*     */   private static final byte STRENGTHEN = 4;
/*     */   private boolean isOpen = true;
/*     */   private static final String CLOSED_STATE = "CLOSED";
/*     */   private static final String OPEN_STATE = "OPEN";
/*     */   private static final String REAR_IDLE = "REAR_IDLE";
/*     */   private int biteDamage;
/*     */   private static final int STR_AMOUNT = 3;
/*     */   
/*     */   public LouseNormal(float x, float y) {
/*  39 */     super(NAME, "FuzzyLouseNormal", 15, 0.0F, -5.0F, 180.0F, 140.0F, null, x, y);
/*     */     
/*  41 */     loadAnimation("images/monsters/theBottom/louseRed/skeleton.atlas", "images/monsters/theBottom/louseRed/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  45 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  46 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  48 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  49 */       setHp(11, 16);
/*     */     } else {
/*  51 */       setHp(10, 15);
/*     */     } 
/*     */     
/*  54 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  55 */       this.biteDamage = AbstractDungeon.monsterHpRng.random(6, 8);
/*     */     } else {
/*  57 */       this.biteDamage = AbstractDungeon.monsterHpRng.random(5, 7);
/*     */     } 
/*     */     
/*  60 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.biteDamage));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  65 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  66 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CurlUpPower((AbstractCreature)this, AbstractDungeon.monsterHpRng
/*  67 */               .random(9, 12))));
/*  68 */     } else if (AbstractDungeon.ascensionLevel >= 7) {
/*  69 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CurlUpPower((AbstractCreature)this, AbstractDungeon.monsterHpRng
/*  70 */               .random(4, 8))));
/*     */     } else {
/*  72 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CurlUpPower((AbstractCreature)this, AbstractDungeon.monsterHpRng
/*  73 */               .random(3, 7))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  79 */     switch (this.nextMove) {
/*     */       case 3:
/*  81 */         if (!this.isOpen) {
/*  82 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "OPEN"));
/*  83 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*     */         } 
/*  85 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  86 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  87 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*     */         break;
/*     */       case 4:
/*  90 */         if (!this.isOpen) {
/*  91 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REAR"));
/*  92 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(1.2F));
/*     */         } else {
/*  94 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REAR_IDLE"));
/*  95 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.9F));
/*     */         } 
/*  97 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  98 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 4), 4));
/*     */           break;
/*     */         } 
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 3), 3));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 113 */     if (stateName.equals("CLOSED")) {
/* 114 */       this.state.setAnimation(0, "transitiontoclosed", false);
/* 115 */       this.state.addAnimation(0, "idle closed", true, 0.0F);
/* 116 */       this.isOpen = false;
/* 117 */     } else if (stateName.equals("OPEN")) {
/* 118 */       this.state.setAnimation(0, "transitiontoopened", false);
/* 119 */       this.state.addAnimation(0, "idle", true, 0.0F);
/* 120 */       this.isOpen = true;
/* 121 */     } else if (stateName.equals("REAR_IDLE")) {
/* 122 */       this.state.setAnimation(0, "rear", false);
/* 123 */       this.state.addAnimation(0, "idle", true, 0.0F);
/* 124 */       this.isOpen = true;
/*     */     } else {
/* 126 */       this.state.setAnimation(0, "transitiontoopened", false);
/* 127 */       this.state.addAnimation(0, "rear", false, 0.0F);
/* 128 */       this.state.addAnimation(0, "idle", true, 0.0F);
/* 129 */       this.isOpen = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 135 */     if (AbstractDungeon.ascensionLevel >= 17) {
/* 136 */       if (num < 25) {
/* 137 */         if (lastMove((byte)4)) {
/* 138 */           setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */         } else {
/* 140 */           setMove(MOVES[0], (byte)4, Intent.BUFF);
/*     */         }
/*     */       
/* 143 */       } else if (lastTwoMoves((byte)3)) {
/* 144 */         setMove(MOVES[0], (byte)4, Intent.BUFF);
/*     */       } else {
/* 146 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/*     */     }
/* 150 */     else if (num < 25) {
/* 151 */       if (lastTwoMoves((byte)4)) {
/* 152 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 154 */         setMove(MOVES[0], (byte)4, Intent.BUFF);
/*     */       }
/*     */     
/* 157 */     } else if (lastTwoMoves((byte)3)) {
/* 158 */       setMove(MOVES[0], (byte)4, Intent.BUFF);
/*     */     } else {
/* 160 */       setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\LouseNormal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */