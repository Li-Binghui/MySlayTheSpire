/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.unique.CannotLoseAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.SplitPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ public class AcidSlime_L
/*     */   extends AbstractMonster {
/*     */   public static final String ID = "AcidSlime_L";
/*  39 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("AcidSlime_L");
/*  40 */   public static final String NAME = monsterStrings.NAME;
/*  41 */   public static final String[] MOVES = monsterStrings.MOVES;
/*     */   
/*  43 */   private static final String WOUND_NAME = MOVES[0]; private static final String SPLIT_NAME = MOVES[1]; private static final String WEAK_NAME = MOVES[2];
/*     */   
/*     */   public static final int HP_MIN = 65;
/*     */   
/*     */   public static final int HP_MAX = 69;
/*     */   public static final int A_2_HP_MIN = 68;
/*     */   public static final int A_2_HP_MAX = 72;
/*     */   public static final int W_TACKLE_DMG = 11;
/*     */   public static final int N_TACKLE_DMG = 16;
/*     */   public static final int A_2_W_TACKLE_DMG = 12;
/*     */   public static final int A_2_N_TACKLE_DMG = 18;
/*     */   
/*     */   public AcidSlime_L(float x, float y) {
/*  56 */     this(x, y, 0, 69);
/*     */     
/*  58 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  59 */       setHp(68, 72);
/*     */     } else {
/*  61 */       setHp(65, 69);
/*     */     } 
/*     */   }
/*     */   public static final int WEAK_TURNS = 2; public static final int WOUND_COUNT = 2; private static final byte SLIME_TACKLE = 1; private static final byte NORMAL_TACKLE = 2; private static final byte SPLIT = 3; private static final byte WEAK_LICK = 4; private float saveX; private float saveY; private boolean splitTriggered;
/*     */   public AcidSlime_L(float x, float y, int poisonAmount, int newHealth) {
/*  66 */     super(NAME, "AcidSlime_L", newHealth, 0.0F, 0.0F, 300.0F, 180.0F, null, x, y, true);
/*     */     
/*  68 */     this.saveX = x;
/*  69 */     this.saveY = y;
/*  70 */     this.splitTriggered = false;
/*     */     
/*  72 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  73 */       this.damage.add(new DamageInfo((AbstractCreature)this, 12));
/*  74 */       this.damage.add(new DamageInfo((AbstractCreature)this, 18));
/*     */     } else {
/*  76 */       this.damage.add(new DamageInfo((AbstractCreature)this, 11));
/*  77 */       this.damage.add(new DamageInfo((AbstractCreature)this, 16));
/*     */     } 
/*     */     
/*  80 */     this.powers.add(new SplitPower((AbstractCreature)this));
/*     */     
/*  82 */     if (poisonAmount >= 1) {
/*  83 */       this.powers.add(new PoisonPower((AbstractCreature)this, (AbstractCreature)this, poisonAmount));
/*     */     }
/*     */     
/*  86 */     loadAnimation("images/monsters/theBottom/slimeL/skeleton.atlas", "images/monsters/theBottom/slimeL/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  90 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  91 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  92 */     this.state.addListener((AnimationState.AnimationStateListener)new SlimeAnimListener());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  97 */     switch (this.nextMove) {
/*     */       case 4:
/*  99 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 100 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 1:
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_SLIME_ATTACK"));
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 112 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 2));
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 2:
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 118 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 119 */               .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 120 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 3:
/* 123 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CannotLoseAction());
/* 124 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateShakeAction((AbstractCreature)this, 1.0F, 0.1F));
/* 125 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HideHealthBarAction((AbstractCreature)this));
/* 126 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SuicideAction(this, false));
/* 127 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(1.0F));
/* 128 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("SLIME_SPLIT"));
/*     */         
/* 130 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new AcidSlime_M(this.saveX - 134.0F, this.saveY + 
/*     */                 
/* 132 */                 MathUtils.random(-4.0F, 4.0F), 0, this.currentHealth), false));
/*     */         
/* 134 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new AcidSlime_M(this.saveX + 134.0F, this.saveY + 
/*     */                 
/* 136 */                 MathUtils.random(-4.0F, 4.0F), 0, this.currentHealth), false));
/*     */ 
/*     */         
/* 139 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
/* 140 */         setMove(SPLIT_NAME, (byte)3, Intent.UNKNOWN);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 147 */     super.damage(info);
/*     */     
/* 149 */     if (!this.isDying && this.currentHealth <= this.maxHealth / 2.0F && this.nextMove != 3 && !this.splitTriggered) {
/* 150 */       setMove(SPLIT_NAME, (byte)3, Intent.UNKNOWN);
/* 151 */       createIntent();
/* 152 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, TextAboveCreatureAction.TextType.INTERRUPTED));
/* 153 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, SPLIT_NAME, (byte)3, Intent.UNKNOWN));
/* 154 */       this.splitTriggered = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 160 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/* 162 */       if (num < 40) {
/* 163 */         if (lastTwoMoves((byte)1)) {
/* 164 */           if (AbstractDungeon.aiRng.randomBoolean(0.6F)) {
/* 165 */             setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */           } else {
/* 167 */             setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */           } 
/*     */         } else {
/* 170 */           setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         }
/*     */       
/*     */       }
/* 174 */       else if (num < 70) {
/* 175 */         if (lastTwoMoves((byte)2)) {
/* 176 */           if (AbstractDungeon.aiRng.randomBoolean(0.6F)) {
/* 177 */             setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */           } else {
/* 179 */             setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */           } 
/*     */         } else {
/* 182 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 187 */       else if (lastMove((byte)4)) {
/* 188 */         if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 189 */           setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         } else {
/* 191 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } 
/*     */       } else {
/* 194 */         setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 200 */     else if (num < 30) {
/* 201 */       if (lastTwoMoves((byte)1)) {
/* 202 */         if (AbstractDungeon.aiRng.randomBoolean()) {
/* 203 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } else {
/* 205 */           setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */         } 
/*     */       } else {
/* 208 */         setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/*     */     }
/* 212 */     else if (num < 70) {
/* 213 */       if (lastMove((byte)2)) {
/* 214 */         if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 215 */           setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         } else {
/* 217 */           setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */         } 
/*     */       } else {
/* 220 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 225 */     else if (lastTwoMoves((byte)4)) {
/* 226 */       if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 227 */         setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 229 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       } 
/*     */     } else {
/* 232 */       setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 240 */     super.die();
/*     */     
/* 242 */     for (AbstractGameAction a : AbstractDungeon.actionManager.actions) {
/* 243 */       if (a instanceof SpawnMonsterAction) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 248 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() && 
/* 249 */       AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 250 */       onBossVictoryLogic();
/* 251 */       UnlockTracker.hardUnlockOverride("SLIME");
/* 252 */       UnlockTracker.unlockAchievement("SLIME_BOSS");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\AcidSlime_L.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */