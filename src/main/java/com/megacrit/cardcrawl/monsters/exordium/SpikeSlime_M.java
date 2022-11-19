/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
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
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.FrailPower;
/*     */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ public class SpikeSlime_M extends AbstractMonster {
/*  25 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SpikeSlime_M"); public static final String ID = "SpikeSlime_M";
/*  26 */   public static final String NAME = monsterStrings.NAME;
/*  27 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  28 */   public static final String[] DIALOG = monsterStrings.DIALOG; public static final int HP_MIN = 28;
/*     */   public static final int HP_MAX = 32;
/*     */   public static final int A_2_HP_MIN = 29;
/*     */   public static final int A_2_HP_MAX = 34;
/*     */   public static final int TACKLE_DAMAGE = 8;
/*     */   public static final int WOUND_COUNT = 1;
/*     */   public static final int A_2_TACKLE_DAMAGE = 10;
/*     */   public static final int FRAIL_TURNS = 1;
/*     */   private static final byte FLAME_TACKLE = 1;
/*     */   private static final byte FRAIL_LICK = 4;
/*  38 */   private static final String FRAIL_NAME = MOVES[0];
/*     */   
/*     */   public SpikeSlime_M(float x, float y) {
/*  41 */     this(x, y, 0, 32);
/*     */     
/*  43 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  44 */       setHp(29, 34);
/*     */     } else {
/*  46 */       setHp(28, 32);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SpikeSlime_M(float x, float y, int poisonAmount, int newHealth) {
/*  51 */     super(NAME, "SpikeSlime_M", newHealth, 0.0F, -25.0F, 170.0F, 130.0F, null, x, y, true);
/*     */     
/*  53 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  54 */       this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*     */     } else {
/*  56 */       this.damage.add(new DamageInfo((AbstractCreature)this, 8));
/*     */     } 
/*     */     
/*  59 */     if (poisonAmount >= 1) {
/*  60 */       this.powers.add(new PoisonPower((AbstractCreature)this, (AbstractCreature)this, poisonAmount));
/*     */     }
/*     */     
/*  63 */     loadAnimation("images/monsters/theBottom/slimeAltM/skeleton.atlas", "images/monsters/theBottom/slimeAltM/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  67 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  68 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  73 */     switch (this.nextMove) {
/*     */       case 4:
/*  75 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  76 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/*  84 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  85 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  86 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  87 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 1));
/*     */         break;
/*     */     } 
/*  90 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/*  96 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/*  98 */       if (num < 30) {
/*  99 */         if (lastTwoMoves((byte)1)) {
/* 100 */           setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */         } else {
/* 102 */           setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         }
/*     */       
/* 105 */       } else if (lastMove((byte)4)) {
/* 106 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 108 */         setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 113 */     else if (num < 30) {
/* 114 */       if (lastTwoMoves((byte)1)) {
/* 115 */         setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */       } else {
/* 117 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/* 120 */     } else if (lastTwoMoves((byte)4)) {
/* 121 */       setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */     } else {
/* 123 */       setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 131 */     super.die();
/*     */     
/* 133 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() && 
/* 134 */       AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 135 */       onBossVictoryLogic();
/* 136 */       UnlockTracker.hardUnlockOverride("SLIME");
/* 137 */       UnlockTracker.unlockAchievement("SLIME_BOSS");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\SpikeSlime_M.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */