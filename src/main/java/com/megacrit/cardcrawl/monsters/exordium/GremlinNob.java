/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.AngerPower;
/*     */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*     */ 
/*     */ public class GremlinNob extends AbstractMonster {
/*     */   public static final String ID = "GremlinNob";
/*  23 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinNob");
/*  24 */   public static final String NAME = monsterStrings.NAME;
/*  25 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  26 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP_MIN = 82;
/*     */   
/*     */   private static final int HP_MAX = 86;
/*     */   
/*     */   private static final int A_2_HP_MIN = 85;
/*     */   
/*     */   private static final int A_2_HP_MAX = 90;
/*     */   
/*     */   private static final int BASH_DMG = 6;
/*     */   
/*     */   private static final int RUSH_DMG = 14;
/*     */   private static final int A_2_BASH_DMG = 8;
/*     */   private static final int A_2_RUSH_DMG = 16;
/*     */   
/*     */   public GremlinNob(float x, float y) {
/*  43 */     this(x, y, true);
/*     */   }
/*     */   private static final int DEBUFF_AMT = 2; private int bashDmg; private int rushDmg; private static final byte BULL_RUSH = 1; private static final byte SKULL_BASH = 2; private static final byte BELLOW = 3; private static final int ANGRY_LEVEL = 2; private boolean usedBellow = false; private boolean canVuln;
/*     */   public GremlinNob(float x, float y, boolean setVuln) {
/*  47 */     super(NAME, "GremlinNob", 86, -70.0F, -10.0F, 270.0F, 380.0F, null, x, y);
/*  48 */     this.intentOffsetX = -30.0F * Settings.scale;
/*  49 */     this.type = EnemyType.ELITE;
/*  50 */     this.dialogX = -60.0F * Settings.scale;
/*  51 */     this.dialogY = 50.0F * Settings.scale;
/*  52 */     this.canVuln = setVuln;
/*     */     
/*  54 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  55 */       setHp(85, 90);
/*     */     } else {
/*  57 */       setHp(82, 86);
/*     */     } 
/*     */     
/*  60 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  61 */       this.bashDmg = 8;
/*  62 */       this.rushDmg = 16;
/*     */     } else {
/*  64 */       this.bashDmg = 6;
/*  65 */       this.rushDmg = 14;
/*     */     } 
/*     */     
/*  68 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.rushDmg));
/*  69 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.bashDmg));
/*     */     
/*  71 */     loadAnimation("images/monsters/theBottom/nobGremlin/skeleton.atlas", "images/monsters/theBottom/nobGremlin/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  75 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
/*  76 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  88 */     switch (this.nextMove) {
/*     */       case 3:
/*  90 */         playSfx();
/*  91 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0], 1.0F, 3.0F));
/*  92 */         if (AbstractDungeon.ascensionLevel >= 18) {
/*  93 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new AngerPower((AbstractCreature)this, 3), 3));
/*     */           break;
/*     */         } 
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new AngerPower((AbstractCreature)this, 2), 2));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 103 */               .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 104 */         if (this.canVuln) {
/* 105 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 116 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 121 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 125 */     int roll = MathUtils.random(2);
/* 126 */     if (roll == 0) {
/* 127 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINNOB_1A"));
/* 128 */     } else if (roll == 1) {
/* 129 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINNOB_1B"));
/*     */     } else {
/* 131 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINNOB_1C"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 139 */     if (!this.usedBellow) {
/* 140 */       this.usedBellow = true;
/* 141 */       setMove((byte)3, Intent.BUFF);
/*     */       
/*     */       return;
/*     */     } 
/* 145 */     if (AbstractDungeon.ascensionLevel >= 18) {
/* 146 */       if (!lastMove((byte)2) && !lastMoveBefore((byte)2)) {
/* 147 */         if (this.canVuln) {
/* 148 */           setMove(MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         } else {
/* 150 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } 
/*     */         return;
/*     */       } 
/* 154 */       if (lastTwoMoves((byte)1)) {
/* 155 */         if (this.canVuln) {
/* 156 */           setMove(MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         } else {
/* 158 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } 
/*     */       } else {
/* 161 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } 
/*     */     } else {
/*     */       
/* 165 */       if (num < 33) {
/* 166 */         if (this.canVuln) {
/* 167 */           setMove(MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         } else {
/* 169 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 175 */       if (lastTwoMoves((byte)1)) {
/* 176 */         if (this.canVuln) {
/* 177 */           setMove(MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         } else {
/* 179 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } 
/*     */       } else {
/* 182 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\GremlinNob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */