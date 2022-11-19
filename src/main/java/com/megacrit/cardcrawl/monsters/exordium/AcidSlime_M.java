/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.status.Slimed;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ public class AcidSlime_M extends AbstractMonster {
/*     */   public static final String ID = "AcidSlime_M";
/*  26 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("AcidSlime_M");
/*  27 */   public static final String NAME = monsterStrings.NAME;
/*  28 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  29 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*  31 */   private static final String WOUND_NAME = MOVES[0]; private static final String WEAK_NAME = MOVES[1];
/*     */   
/*     */   public static final int HP_MIN = 28;
/*     */   
/*     */   public static final int HP_MAX = 32;
/*     */   public static final int A_2_HP_MIN = 29;
/*     */   public static final int A_2_HP_MAX = 34;
/*     */   public static final int W_TACKLE_DMG = 7;
/*     */   public static final int WOUND_COUNT = 1;
/*     */   
/*     */   public AcidSlime_M(float x, float y) {
/*  42 */     this(x, y, 0, 32);
/*     */     
/*  44 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  45 */       setHp(29, 34);
/*     */     } else {
/*  47 */       setHp(28, 32);
/*     */     } 
/*     */   }
/*     */   public static final int N_TACKLE_DMG = 10; public static final int A_2_W_TACKLE_DMG = 8; public static final int A_2_N_TACKLE_DMG = 12; public static final int WEAK_TURNS = 1; private static final byte WOUND_TACKLE = 1; private static final byte NORMAL_TACKLE = 2; private static final byte WEAK_LICK = 4;
/*     */   public AcidSlime_M(float x, float y, int poisonAmount, int newHealth) {
/*  52 */     super(NAME, "AcidSlime_M", newHealth, 0.0F, 0.0F, 170.0F, 130.0F, null, x, y, true);
/*     */     
/*  54 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  55 */       this.damage.add(new DamageInfo((AbstractCreature)this, 8));
/*  56 */       this.damage.add(new DamageInfo((AbstractCreature)this, 12));
/*     */     } else {
/*  58 */       this.damage.add(new DamageInfo((AbstractCreature)this, 7));
/*  59 */       this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*     */     } 
/*     */     
/*  62 */     if (poisonAmount >= 1) {
/*  63 */       this.powers.add(new PoisonPower((AbstractCreature)this, (AbstractCreature)this, poisonAmount));
/*     */     }
/*     */     
/*  66 */     loadAnimation("images/monsters/theBottom/slimeM/skeleton.atlas", "images/monsters/theBottom/slimeM/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  70 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  71 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  72 */     this.state.addListener((AnimationState.AnimationStateListener)new SlimeAnimListener());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  77 */     switch (this.nextMove) {
/*     */       case 4:
/*  79 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  86 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 1:
/*  89 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  91 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  92 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 1));
/*  93 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 2:
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  97 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  98 */               .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  99 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 106 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/* 108 */       if (num < 40) {
/* 109 */         if (lastTwoMoves((byte)1)) {
/* 110 */           if (AbstractDungeon.aiRng.randomBoolean()) {
/* 111 */             setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */           } else {
/* 113 */             setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */           } 
/*     */         } else {
/* 116 */           setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         }
/*     */       
/*     */       }
/* 120 */       else if (num < 80) {
/* 121 */         if (lastTwoMoves((byte)2)) {
/* 122 */           if (AbstractDungeon.aiRng.randomBoolean(0.5F)) {
/* 123 */             setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */           } else {
/* 125 */             setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */           } 
/*     */         } else {
/* 128 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 133 */       else if (lastMove((byte)4)) {
/* 134 */         if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 135 */           setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         } else {
/* 137 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } 
/*     */       } else {
/* 140 */         setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 145 */     else if (num < 30) {
/* 146 */       if (lastTwoMoves((byte)1)) {
/* 147 */         if (AbstractDungeon.aiRng.randomBoolean()) {
/* 148 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } else {
/* 150 */           setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */         } 
/*     */       } else {
/* 153 */         setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/*     */     }
/* 157 */     else if (num < 70) {
/* 158 */       if (lastMove((byte)2)) {
/* 159 */         if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 160 */           setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         } else {
/* 162 */           setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */         } 
/*     */       } else {
/* 165 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 170 */     else if (lastTwoMoves((byte)4)) {
/* 171 */       if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 172 */         setMove(WOUND_NAME, (byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 174 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       } 
/*     */     } else {
/* 177 */       setMove(WEAK_NAME, (byte)4, Intent.DEBUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 185 */     super.die();
/*     */     
/* 187 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() && 
/* 188 */       AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 189 */       onBossVictoryLogic();
/* 190 */       UnlockTracker.hardUnlockOverride("SLIME");
/* 191 */       UnlockTracker.unlockAchievement("SLIME_BOSS");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\AcidSlime_M.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */