/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ 
/*     */ public class GremlinFat extends AbstractMonster {
/*  26 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinFat"); public static final String ID = "GremlinFat";
/*  27 */   public static final String NAME = monsterStrings.NAME;
/*  28 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  29 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP_MIN = 13;
/*     */   private static final int HP_MAX = 17;
/*     */   private static final int A_2_HP_MIN = 14;
/*     */   private static final int A_2_HP_MAX = 18;
/*     */   private static final int BLUNT_DAMAGE = 4;
/*     */   private static final int A_2_BLUNT_DAMAGE = 5;
/*     */   private static final int WEAK_AMT = 1;
/*     */   private static final byte BLUNT = 2;
/*     */   
/*     */   public GremlinFat(float x, float y) {
/*  41 */     super(NAME, "GremlinFat", 17, 0.0F, 0.0F, 110.0F, 220.0F, null, x, y);
/*  42 */     this.dialogY = 30.0F * Settings.scale;
/*     */     
/*  44 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  45 */       setHp(14, 18);
/*     */     } else {
/*  47 */       setHp(13, 17);
/*     */     } 
/*     */     
/*  50 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  51 */       this.damage.add(new DamageInfo((AbstractCreature)this, 5));
/*     */     } else {
/*  53 */       this.damage.add(new DamageInfo((AbstractCreature)this, 4));
/*     */     } 
/*     */     
/*  56 */     loadAnimation("images/monsters/theBottom/fatGremlin/skeleton.atlas", "images/monsters/theBottom/fatGremlin/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  60 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
/*  61 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  66 */     switch (this.nextMove) {
/*     */       case 2:
/*  68 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  69 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  70 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*  71 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  77 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  78 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  85 */         if (this.escapeNext) {
/*  86 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE)); break;
/*     */         } 
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       
/*     */       case 99:
/*  92 */         playSfx();
/*  93 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[1], false));
/*     */         
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this));
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 104 */     int roll = MathUtils.random(2);
/* 105 */     if (roll == 0) {
/* 106 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINFAT_1A"));
/* 107 */     } else if (roll == 1) {
/* 108 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINFAT_1B"));
/*     */     } else {
/* 110 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINFAT_1C"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 115 */     int roll = MathUtils.random(2);
/* 116 */     if (roll == 0) {
/* 117 */       CardCrawlGame.sound.play("VO_GREMLINFAT_2A");
/* 118 */     } else if (roll == 1) {
/* 119 */       CardCrawlGame.sound.play("VO_GREMLINFAT_2B");
/*     */     } else {
/* 121 */       CardCrawlGame.sound.play("VO_GREMLINFAT_2C");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 127 */     super.die();
/* 128 */     playDeathSfx();
/*     */   }
/*     */ 
/*     */   
/*     */   public void escapeNext() {
/* 133 */     if (!this.cannotEscape && 
/* 134 */       !this.escapeNext) {
/* 135 */       this.escapeNext = true;
/* 136 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 144 */     setMove(MOVES[0], (byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deathReact() {
/* 150 */     if (this.intent != Intent.ESCAPE && !this.isDying) {
/* 151 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/* 152 */       setMove((byte)99, Intent.ESCAPE);
/* 153 */       createIntent();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\GremlinFat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */