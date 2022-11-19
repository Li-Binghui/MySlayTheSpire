/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.*;
/*     */
/*     */
/*     */
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ThieveryPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
/*     */ 
/*     */ public class Looter extends AbstractMonster {
/*     */   public static final String ID = "Looter";
/*  27 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Looter");
/*  28 */   public static final String NAME = monsterStrings.NAME;
/*  29 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  30 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 44; private static final int HP_MAX = 48;
/*     */   private static final int A_2_HP_MIN = 46;
/*     */   private static final int A_2_HP_MAX = 50;
/*     */   private int swipeDmg;
/*     */   private int lungeDmg;
/*  35 */   private int escapeDef = 6; private int goldAmt;
/*     */   private static final byte MUG = 1;
/*  37 */   private static final String SLASH_MSG1 = DIALOG[0]; private static final byte SMOKE_BOMB = 2; private static final byte ESCAPE = 3; private static final byte LUNGE = 4;
/*  38 */   private static final String DEATH_MSG1 = DIALOG[1];
/*  39 */   private static final String SMOKE_BOMB_MSG = DIALOG[2];
/*  40 */   private static final String RUN_MSG = DIALOG[3];
/*  41 */   private int slashCount = 0;
/*  42 */   private int stolenGold = 0;
/*     */   
/*     */   public Looter(float x, float y) {
/*  45 */     super(NAME, "Looter", 48, 0.0F, 0.0F, 200.0F, 220.0F, null, x, y);
/*     */     
/*  47 */     this.dialogX = -30.0F * Settings.scale;
/*  48 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  50 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  51 */       this.goldAmt = 20;
/*     */     } else {
/*  53 */       this.goldAmt = 15;
/*     */     } 
/*     */     
/*  56 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  57 */       setHp(46, 50);
/*     */     } else {
/*  59 */       setHp(44, 48);
/*     */     } 
/*     */     
/*  62 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  63 */       this.swipeDmg = 11;
/*  64 */       this.lungeDmg = 14;
/*     */     } else {
/*  66 */       this.swipeDmg = 10;
/*  67 */       this.lungeDmg = 12;
/*     */     } 
/*     */     
/*  70 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.swipeDmg));
/*  71 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.lungeDmg));
/*     */     
/*  73 */     loadAnimation("images/monsters/theBottom/looter/skeleton.atlas", "images/monsters/theBottom/looter/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  77 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  78 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  83 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ThieveryPower((AbstractCreature)this, this.goldAmt)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  88 */     switch (this.nextMove) {
/*     */       case 1:
/*  90 */         if (this.slashCount == 0 && AbstractDungeon.aiRng.randomBoolean(0.6F)) {
/*  91 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, SLASH_MSG1, 0.3F, 2.0F));
/*     */         }
/*     */         
/*  94 */         playSfx();
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  96 */         AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
/*     */             {
/*     */               public void update()
/*     */               {
/* 100 */                 Looter.this.stolenGold = Looter.this.stolenGold + Math.min(Looter.this.goldAmt, AbstractDungeon.player.gold);
/* 101 */                 this.isDone = true;
/*     */               }
/*     */             });
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 105 */               .get(0), this.goldAmt));
/*     */         
/* 107 */         this.slashCount++;
/* 108 */         if (this.slashCount == 2) {
/* 109 */           if (AbstractDungeon.aiRng.randomBoolean(0.5F)) {
/* 110 */             setMove((byte)2, Intent.DEFEND); break;
/*     */           } 
/* 112 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[0], (byte)4, Intent.ATTACK, ((DamageInfo)this.damage
/* 113 */                 .get(1)).base));
/*     */           break;
/*     */         } 
/* 116 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[1], (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/* 117 */               .get(0)).base));
/*     */         break;
/*     */       
/*     */       case 4:
/* 121 */         playSfx();
/* 122 */         this.slashCount++;
/* 123 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 124 */         AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
/*     */             {
/*     */               public void update()
/*     */               {
/* 128 */                 Looter.this.stolenGold = Looter.this.stolenGold + Math.min(Looter.this.goldAmt, AbstractDungeon.player.gold);
/* 129 */                 this.isDone = true;
/*     */               }
/*     */             });
/* 132 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 133 */               .get(1), this.goldAmt));
/* 134 */         setMove((byte)2, Intent.DEFEND);
/*     */         break;
/*     */       case 2:
/* 137 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, SMOKE_BOMB_MSG, 0.75F, 2.5F));
/* 138 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.escapeDef));
/* 139 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ESCAPE));
/*     */         break;
/*     */       case 3:
/* 142 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, RUN_MSG, 0.3F, 2.5F));
/* 143 */         (AbstractDungeon.getCurrRoom()).mugged = true;
/* 144 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmokeBombEffect(this.hb.cX, this.hb.cY)));
/* 145 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this));
/* 146 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ESCAPE));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 154 */     int roll = MathUtils.random(2);
/* 155 */     if (roll == 0) {
/* 156 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_LOOTER_1A"));
/* 157 */     } else if (roll == 1) {
/* 158 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_LOOTER_1B"));
/*     */     } else {
/* 160 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_LOOTER_1C"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 165 */     int roll = MathUtils.random(2);
/* 166 */     if (roll == 0) {
/* 167 */       CardCrawlGame.sound.play("VO_LOOTER_2A");
/* 168 */     } else if (roll == 1) {
/* 169 */       CardCrawlGame.sound.play("VO_LOOTER_2B");
/*     */     } else {
/* 171 */       CardCrawlGame.sound.play("VO_LOOTER_2C");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 177 */     playDeathSfx();
/* 178 */     this.state.setTimeScale(0.1F);
/* 179 */     useShakeAnimation(5.0F);
/* 180 */     if (MathUtils.randomBoolean(0.3F)) {
/* 181 */       AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.0F, DEATH_MSG1, false));
/* 182 */       if (!Settings.FAST_MODE) {
/* 183 */         this.deathTimer += 1.5F;
/*     */       }
/*     */     } 
/* 186 */     if (this.stolenGold > 0) {
/* 187 */       AbstractDungeon.getCurrRoom().addStolenGoldToRewards(this.stolenGold);
/*     */     }
/* 189 */     super.die();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 194 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\Looter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */