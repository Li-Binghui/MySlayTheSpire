/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EscapeAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SetMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.unique.GainBlockRandomMonsterAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ 
/*     */ public class GremlinTsundere extends AbstractMonster {
/*     */   public static final String ID = "GremlinTsundere";
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinTsundere");
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP_MIN = 12;
/*     */   
/*     */   private static final int HP_MAX = 15;
/*     */   
/*     */   private static final int A_2_HP_MIN = 13;
/*     */   
/*     */   private static final int A_2_HP_MAX = 17;
/*     */   
/*     */   private static final int BLOCK_AMOUNT = 7;
/*     */   
/*     */   private static final int A_2_BLOCK_AMOUNT = 8;
/*     */   
/*     */   public GremlinTsundere(float x, float y) {
/*  40 */     super(NAME, "GremlinTsundere", 15, 0.0F, 0.0F, 120.0F, 200.0F, null, x, y);
/*  41 */     this.dialogY = 60.0F * Settings.scale;
/*     */     
/*  43 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  44 */       setHp(13, 17);
/*  45 */       this.blockAmt = 11;
/*  46 */     } else if (AbstractDungeon.ascensionLevel >= 7) {
/*  47 */       setHp(13, 17);
/*  48 */       this.blockAmt = 8;
/*     */     } else {
/*  50 */       setHp(12, 15);
/*  51 */       this.blockAmt = 7;
/*     */     } 
/*     */     
/*  54 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  55 */       this.bashDmg = 8;
/*     */     } else {
/*  57 */       this.bashDmg = 6;
/*     */     } 
/*     */     
/*  60 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.bashDmg));
/*     */     
/*  62 */     loadAnimation("images/monsters/theBottom/femaleGremlin/skeleton.atlas", "images/monsters/theBottom/femaleGremlin/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  66 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  67 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */   private static final int A_17_BLOCK_AMOUNT = 11; private static final int BASH_DAMAGE = 6; private static final int A_2_BASH_DAMAGE = 8; private int blockAmt; private int bashDmg; private static final byte PROTECT = 1; private static final byte BASH = 2;
/*     */   public void takeTurn() {
/*     */     int aliveCount;
/*  72 */     switch (this.nextMove) {
/*     */       
/*     */       case 1:
/*  75 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockRandomMonsterAction((AbstractCreature)this, this.blockAmt));
/*     */         
/*  77 */         aliveCount = 0;
/*     */ 
/*     */         
/*  80 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/*  81 */           if (!m.isDying && !m.isEscaping) {
/*  82 */             aliveCount++;
/*     */           }
/*     */         } 
/*     */         
/*  86 */         if (this.escapeNext) {
/*  87 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE)); break;
/*     */         } 
/*  89 */         if (aliveCount > 1) {
/*  90 */           setMove(MOVES[0], (byte)1, Intent.DEFEND); break;
/*     */         } 
/*  92 */         setMove(MOVES[1], (byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*  97 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  98 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  99 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 100 */         if (this.escapeNext) {
/* 101 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE)); break;
/*     */         } 
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[1], (byte)2, Intent.ATTACK, ((DamageInfo)this.damage
/* 104 */               .get(0)).base));
/*     */         break;
/*     */       
/*     */       case 99:
/* 108 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[1], false));
/*     */         
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this));
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 121 */     super.die();
/*     */   }
/*     */ 
/*     */   
/*     */   public void escapeNext() {
/* 126 */     if (!this.cannotEscape && 
/* 127 */       !this.escapeNext) {
/* 128 */       this.escapeNext = true;
/* 129 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 136 */     setMove(MOVES[0], (byte)1, Intent.DEFEND);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deathReact() {
/* 141 */     if (this.intent != Intent.ESCAPE && !this.isDying) {
/* 142 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/* 143 */       setMove((byte)99, Intent.ESCAPE);
/* 144 */       createIntent();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\GremlinTsundere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */