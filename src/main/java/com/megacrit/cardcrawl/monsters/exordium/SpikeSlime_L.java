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
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.SplitPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ public class SpikeSlime_L
/*     */   extends AbstractMonster {
/*     */   public static final String ID = "SpikeSlime_L";
/*  38 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SpikeSlime_L");
/*  39 */   public static final String NAME = monsterStrings.NAME; public static final int HP_MIN = 64; public static final int HP_MAX = 70; public static final int A_2_HP_MIN = 67; public static final int A_2_HP_MAX = 73; public static final int TACKLE_DAMAGE = 16; public static final int A_2_TACKLE_DAMAGE = 18;
/*  40 */   public static final String[] MOVES = monsterStrings.MOVES;
/*     */   
/*     */   public static final int FRAIL_TURNS = 2;
/*     */   
/*     */   public static final int WOUND_COUNT = 2;
/*     */   
/*     */   private static final byte FLAME_TACKLE = 1;
/*     */   private static final byte SPLIT = 3;
/*     */   private static final byte FRAIL_LICK = 4;
/*  49 */   private static final String FRAIL_NAME = MOVES[0]; private static final String SPLIT_NAME = MOVES[1]; private float saveX;
/*     */   private float saveY;
/*     */   private boolean splitTriggered;
/*     */   
/*     */   public SpikeSlime_L(float x, float y) {
/*  54 */     this(x, y, 0, 70);
/*     */     
/*  56 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  57 */       setHp(67, 73);
/*     */     } else {
/*  59 */       setHp(64, 70);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SpikeSlime_L(float x, float y, int poisonAmount, int newHealth) {
/*  64 */     super(NAME, "SpikeSlime_L", newHealth, 0.0F, -30.0F, 300.0F, 180.0F, null, x, y, true);
/*     */     
/*  66 */     this.saveX = x;
/*  67 */     this.saveY = y;
/*  68 */     this.splitTriggered = false;
/*     */     
/*  70 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  71 */       this.damage.add(new DamageInfo((AbstractCreature)this, 18));
/*     */     } else {
/*  73 */       this.damage.add(new DamageInfo((AbstractCreature)this, 16));
/*     */     } 
/*     */     
/*  76 */     this.powers.add(new SplitPower((AbstractCreature)this));
/*     */     
/*  78 */     if (poisonAmount >= 1) {
/*  79 */       this.powers.add(new PoisonPower((AbstractCreature)this, (AbstractCreature)this, poisonAmount));
/*     */     }
/*     */     
/*  82 */     loadAnimation("images/monsters/theBottom/slimeAltL/skeleton.atlas", "images/monsters/theBottom/slimeAltL/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  86 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  87 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  92 */     switch (this.nextMove) {
/*     */       case 4:
/*  94 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  95 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  96 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */         
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 112 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 114 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Slimed(), 2));
/*     */         break;
/*     */       case 3:
/* 118 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CannotLoseAction());
/* 119 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateShakeAction((AbstractCreature)this, 1.0F, 0.1F));
/* 120 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HideHealthBarAction((AbstractCreature)this));
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SuicideAction(this, false));
/* 122 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(1.0F));
/* 123 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("SLIME_SPLIT"));
/*     */         
/* 125 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new SpikeSlime_M(this.saveX - 134.0F, this.saveY + 
/*     */                 
/* 127 */                 MathUtils.random(-4.0F, 4.0F), 0, this.currentHealth), false));
/*     */ 
/*     */         
/* 130 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new SpikeSlime_M(this.saveX + 134.0F, this.saveY + 
/*     */                 
/* 132 */                 MathUtils.random(-4.0F, 4.0F), 0, this.currentHealth), false));
/*     */ 
/*     */         
/* 135 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new CanLoseAction());
/* 136 */         setMove(SPLIT_NAME, (byte)3, Intent.UNKNOWN);
/*     */         break;
/*     */     } 
/* 139 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 144 */     super.damage(info);
/*     */     
/* 146 */     if (!this.isDying && this.currentHealth <= this.maxHealth / 2.0F && this.nextMove != 3 && !this.splitTriggered) {
/* 147 */       setMove(SPLIT_NAME, (byte)3, Intent.UNKNOWN);
/* 148 */       createIntent();
/* 149 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, TextAboveCreatureAction.TextType.INTERRUPTED));
/* 150 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, SPLIT_NAME, (byte)3, Intent.UNKNOWN));
/* 151 */       this.splitTriggered = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 158 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/* 160 */       if (num < 30) {
/* 161 */         if (lastTwoMoves((byte)1)) {
/* 162 */           setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */         } else {
/* 164 */           setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */         }
/*     */       
/* 167 */       } else if (lastMove((byte)4)) {
/* 168 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 170 */         setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 175 */     else if (num < 30) {
/* 176 */       if (lastTwoMoves((byte)1)) {
/* 177 */         setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */       } else {
/* 179 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/* 182 */     } else if (lastTwoMoves((byte)4)) {
/* 183 */       setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */     } else {
/* 185 */       setMove(FRAIL_NAME, (byte)4, Intent.DEBUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 193 */     super.die();
/*     */     
/* 195 */     for (AbstractGameAction a : AbstractDungeon.actionManager.actions) {
/* 196 */       if (a instanceof SpawnMonsterAction) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 201 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() && 
/* 202 */       AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 203 */       onBossVictoryLogic();
/* 204 */       UnlockTracker.hardUnlockOverride("SLIME");
/* 205 */       UnlockTracker.unlockAchievement("SLIME_BOSS");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\SpikeSlime_L.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */