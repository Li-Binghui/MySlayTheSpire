/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ 
/*     */ public class GremlinThief extends AbstractMonster {
/*     */   public static final String ID = "GremlinThief";
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinThief");
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int THIEF_DAMAGE = 9;
/*     */   
/*     */   private static final int A_2_THIEF_DAMAGE = 10;
/*     */   
/*     */   private static final byte PUNCTURE = 1;
/*     */   private static final int HP_MIN = 10;
/*     */   
/*     */   public GremlinThief(float x, float y) {
/*  35 */     super(NAME, "GremlinThief", 14, 0.0F, 0.0F, 120.0F, 160.0F, null, x, y);
/*  36 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  38 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  39 */       setHp(11, 15);
/*     */     } else {
/*  41 */       setHp(10, 14);
/*     */     } 
/*     */     
/*  44 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  45 */       this.thiefDamage = 10;
/*     */     } else {
/*  47 */       this.thiefDamage = 9;
/*     */     } 
/*     */     
/*  50 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.thiefDamage));
/*     */     
/*  52 */     loadAnimation("images/monsters/theBottom/thiefGremlin/skeleton.atlas", "images/monsters/theBottom/thiefGremlin/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  56 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
/*  57 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */   private static final int HP_MAX = 14; private static final int A_2_HP_MIN = 11; private static final int A_2_HP_MAX = 15; private int thiefDamage;
/*     */   
/*     */   public void takeTurn() {
/*  62 */     switch (this.nextMove) {
/*     */       case 1:
/*  64 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  65 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  66 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*     */         
/*  68 */         if (!this.escapeNext) {
/*  69 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, Intent.ATTACK, this.thiefDamage));
/*     */           break;
/*     */         } 
/*  72 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE));
/*     */         break;
/*     */       
/*     */       case 99:
/*  76 */         playSfx();
/*  77 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[1], false));
/*     */         
/*  79 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this));
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/*  88 */     int roll = MathUtils.random(1);
/*  89 */     if (roll == 0) {
/*  90 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINSPAZZY_1A"));
/*     */     } else {
/*  92 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINSPAZZY_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/*  97 */     int roll = MathUtils.random(2);
/*  98 */     if (roll == 0) {
/*  99 */       CardCrawlGame.sound.play("VO_GREMLINSPAZZY_2A");
/* 100 */     } else if (roll == 1) {
/* 101 */       CardCrawlGame.sound.play("VO_GREMLINSPAZZY_2B");
/*     */     } else {
/* 103 */       CardCrawlGame.sound.play("VO_GREMLINSPAZZY_2C");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 109 */     super.die();
/* 110 */     playDeathSfx();
/*     */   }
/*     */ 
/*     */   
/*     */   public void escapeNext() {
/* 115 */     if (!this.cannotEscape && 
/* 116 */       !this.escapeNext) {
/* 117 */       this.escapeNext = true;
/* 118 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 125 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deathReact() {
/* 130 */     if (this.intent != Intent.ESCAPE && !this.isDying) {
/* 131 */       AbstractDungeon.effectList.add(new SpeechBubble(this.dialogX, this.dialogY, 3.0F, DIALOG[2], false));
/* 132 */       setMove((byte)99, Intent.ESCAPE);
/* 133 */       createIntent();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\GremlinThief.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */