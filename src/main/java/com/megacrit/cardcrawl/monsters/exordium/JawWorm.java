/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.SetAnimationAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
/*     */ 
/*     */ public class JawWorm extends AbstractMonster {
/*     */   public static final String ID = "JawWorm";
/*  28 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("JawWorm");
/*  29 */   public static final String NAME = monsterStrings.NAME;
/*  30 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  31 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP_MIN = 40;
/*     */   
/*     */   private static final int HP_MAX = 44;
/*     */   
/*     */   private static final int A_2_HP_MIN = 42;
/*     */   
/*     */   private static final int A_2_HP_MAX = 46;
/*     */   
/*     */   private static final float HB_X = 0.0F;
/*     */   
/*     */   private static final float HB_Y = -25.0F;
/*     */   
/*     */   private static final float HB_W = 260.0F;
/*     */   
/*     */   private static final float HB_H = 170.0F;
/*     */   private static final int CHOMP_DMG = 11;
/*     */   private static final int A_2_CHOMP_DMG = 12;
/*     */   private static final int THRASH_DMG = 7;
/*     */   private static final int THRASH_BLOCK = 5;
/*     */   private static final int BELLOW_STR = 3;
/*     */   
/*     */   public JawWorm(float x, float y) {
/*  55 */     this(x, y, false);
/*     */   }
/*     */   private static final int A_2_BELLOW_STR = 4; private static final int A_17_BELLOW_STR = 5; private static final int BELLOW_BLOCK = 6; private static final int A_17_BELLOW_BLOCK = 9; private int bellowBlock; private int chompDmg; private int thrashDmg; private int thrashBlock; private int bellowStr; private static final byte CHOMP = 1; private static final byte BELLOW = 2; private static final byte THRASH = 3; private boolean firstMove = true; private boolean hardMode;
/*     */   public JawWorm(float x, float y, boolean hard) {
/*  59 */     super(NAME, "JawWorm", 44, 0.0F, -25.0F, 260.0F, 170.0F, null, x, y);
/*     */     
/*  61 */     this.hardMode = hard;
/*  62 */     if (this.hardMode) {
/*  63 */       this.firstMove = false;
/*     */     }
/*     */     
/*  66 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  67 */       setHp(42, 46);
/*     */     } else {
/*  69 */       setHp(40, 44);
/*     */     } 
/*     */     
/*  72 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  73 */       this.bellowStr = 5;
/*  74 */       this.bellowBlock = 9;
/*  75 */       this.chompDmg = 12;
/*  76 */       this.thrashDmg = 7;
/*  77 */       this.thrashBlock = 5;
/*  78 */     } else if (AbstractDungeon.ascensionLevel >= 2) {
/*  79 */       this.bellowStr = 4;
/*  80 */       this.bellowBlock = 6;
/*  81 */       this.chompDmg = 12;
/*  82 */       this.thrashDmg = 7;
/*  83 */       this.thrashBlock = 5;
/*     */     } else {
/*  85 */       this.bellowStr = 3;
/*  86 */       this.bellowBlock = 6;
/*  87 */       this.chompDmg = 11;
/*  88 */       this.thrashDmg = 7;
/*  89 */       this.thrashBlock = 5;
/*     */     } 
/*     */     
/*  92 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.chompDmg));
/*  93 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.thrashDmg));
/*     */     
/*  95 */     loadAnimation("images/monsters/theBottom/jawWorm/skeleton.atlas", "images/monsters/theBottom/jawWorm/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  99 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/* 100 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 105 */     if (this.hardMode) {
/* 106 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.bellowStr), this.bellowStr));
/*     */       
/* 108 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.bellowBlock));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 114 */     switch (this.nextMove) {
/*     */       case 1:
/* 116 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetAnimationAction((AbstractCreature)this, "chomp"));
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.3F));
/*     */         
/* 119 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 120 */               .get(0), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */       case 2:
/* 123 */         this.state.setAnimation(0, "tailslam", false);
/* 124 */         this.state.addAnimation(0, "idle", true, 0.0F);
/* 125 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_JAW_WORM_BELLOW"));
/* 126 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShakeScreenAction(0.2F, ScreenShake.ShakeDur.SHORT, ScreenShake.ShakeIntensity.MED));
/*     */         
/* 128 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 129 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.bellowStr), this.bellowStr));
/*     */         
/* 131 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.bellowBlock));
/*     */         break;
/*     */       case 3:
/* 134 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateHopAction((AbstractCreature)this));
/* 135 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 136 */               .get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 137 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.thrashBlock));
/*     */         break;
/*     */     } 
/* 140 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 147 */     if (this.firstMove) {
/* 148 */       this.firstMove = false;
/* 149 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 154 */     if (num < 25) {
/* 155 */       if (lastMove((byte)1)) {
/* 156 */         if (AbstractDungeon.aiRng.randomBoolean(0.5625F)) {
/* 157 */           setMove(MOVES[0], (byte)2, Intent.DEFEND_BUFF);
/*     */         } else {
/* 159 */           setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */         } 
/*     */       } else {
/* 162 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/*     */     }
/* 166 */     else if (num < 55) {
/* 167 */       if (lastTwoMoves((byte)3)) {
/* 168 */         if (AbstractDungeon.aiRng.randomBoolean(0.357F)) {
/* 169 */           setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */         } else {
/* 171 */           setMove(MOVES[0], (byte)2, Intent.DEFEND_BUFF);
/*     */         } 
/*     */       } else {
/* 174 */         setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 179 */     else if (lastMove((byte)2)) {
/* 180 */       if (AbstractDungeon.aiRng.randomBoolean(0.416F)) {
/* 181 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 183 */         setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
/*     */       } 
/*     */     } else {
/* 186 */       setMove(MOVES[0], (byte)2, Intent.DEFEND_BUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 193 */     super.die();
/* 194 */     CardCrawlGame.sound.play("JAW_WORM_DEATH");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\JawWorm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */