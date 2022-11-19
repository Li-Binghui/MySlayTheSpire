/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EscapeAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SetMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.AngryPower;
/*     */ import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ 
/*     */ public class GremlinWarrior extends AbstractMonster {
/*  24 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinWarrior"); public static final String ID = "GremlinWarrior";
/*  25 */   public static final String NAME = monsterStrings.NAME;
/*  26 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  27 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   private static final int SCRATCH_DAMAGE = 4;
/*     */   private static final int A_2_SCRATCH_DAMAGE = 5;
/*     */   private static final byte SCRATCH = 1;
/*     */   private static final int HP_MIN = 20;
/*     */   private static final int HP_MAX = 24;
/*     */   private static final int A_2_HP_MIN = 21;
/*     */   private static final int A_2_HP_MAX = 25;
/*     */   
/*     */   public GremlinWarrior(float x, float y) {
/*  37 */     super(NAME, "GremlinWarrior", 24, -4.0F, 12.0F, 130.0F, 194.0F, null, x, y);
/*  38 */     this.dialogY = 30.0F * Settings.scale;
/*     */     
/*  40 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  41 */       setHp(21, 25);
/*     */     } else {
/*  43 */       setHp(20, 24);
/*     */     } 
/*     */     
/*  46 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  47 */       this.damage.add(new DamageInfo((AbstractCreature)this, 5));
/*     */     } else {
/*  49 */       this.damage.add(new DamageInfo((AbstractCreature)this, 4));
/*     */     } 
/*     */     
/*  52 */     loadAnimation("images/monsters/theBottom/angryGremlin/skeleton.atlas", "images/monsters/theBottom/angryGremlin/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  56 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  57 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  62 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  63 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new AngryPower((AbstractCreature)this, 2)));
/*     */     } else {
/*  65 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new AngryPower((AbstractCreature)this, 1)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  71 */     switch (this.nextMove) {
/*     */       case 1:
/*  73 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  74 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  75 */               .get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */         
/*  77 */         if (this.escapeNext) {
/*  78 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE)); break;
/*     */         } 
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/*  81 */               .get(0)).base));
/*     */         break;
/*     */       
/*     */       case 99:
/*  85 */         playSfx();
/*  86 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[1], false));
/*     */         
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this));
/*  89 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/*  97 */     int roll = MathUtils.random(2);
/*  98 */     if (roll == 0) {
/*  99 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINANGRY_1A"));
/* 100 */     } else if (roll == 1) {
/* 101 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINANGRY_1B"));
/*     */     } else {
/* 103 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINANGRY_1C"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 108 */     int roll = MathUtils.random(1);
/* 109 */     if (roll == 0) {
/* 110 */       CardCrawlGame.sound.play("VO_GREMLINANGRY_2A");
/*     */     } else {
/* 112 */       CardCrawlGame.sound.play("VO_GREMLINANGRY_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 118 */     super.die();
/* 119 */     playDeathSfx();
/*     */   }
/*     */ 
/*     */   
/*     */   public void escapeNext() {
/* 124 */     if (!this.cannotEscape && 
/* 125 */       !this.escapeNext) {
/* 126 */       this.escapeNext = true;
/* 127 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 134 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deathReact() {
/* 139 */     if (this.intent != Intent.ESCAPE && !this.isDying) {
/* 140 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/* 141 */       setMove((byte)99, Intent.ESCAPE);
/* 142 */       createIntent();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\GremlinWarrior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */