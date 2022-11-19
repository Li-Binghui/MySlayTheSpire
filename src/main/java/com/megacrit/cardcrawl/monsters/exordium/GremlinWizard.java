/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*     */ 
/*     */ public class GremlinWizard extends AbstractMonster {
/*     */   public static final String ID = "GremlinWizard";
/*  23 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinWizard");
/*  24 */   public static final String NAME = monsterStrings.NAME;
/*  25 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  26 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   private static final int HP_MIN = 21;
/*     */   private static final int HP_MAX = 25;
/*     */   private static final int A_2_HP_MIN = 22;
/*     */   private static final int A_2_HP_MAX = 26;
/*     */   private static final int MAGIC_DAMAGE = 25;
/*     */   private static final int A_2_MAGIC_DAMAGE = 30;
/*     */   private static final int CHARGE_LIMIT = 3;
/*  34 */   private int currentCharge = 1;
/*     */   private static final byte DOPE_MAGIC = 1;
/*     */   
/*     */   public GremlinWizard(float x, float y) {
/*  38 */     super(NAME, "GremlinWizard", 25, 40.0F, -5.0F, 130.0F, 180.0F, null, x - 35.0F, y);
/*     */     
/*  40 */     this.dialogX = 0.0F * Settings.scale;
/*  41 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  43 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  44 */       setHp(22, 26);
/*     */     } else {
/*  46 */       setHp(21, 25);
/*     */     } 
/*     */     
/*  49 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  50 */       this.damage.add(new DamageInfo((AbstractCreature)this, 30));
/*     */     } else {
/*  52 */       this.damage.add(new DamageInfo((AbstractCreature)this, 25));
/*     */     } 
/*     */     
/*  55 */     loadAnimation("images/monsters/theBottom/wizardGremlin/skeleton.atlas", "images/monsters/theBottom/wizardGremlin/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  59 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
/*  60 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */   private static final byte CHARGE = 2;
/*     */   
/*     */   public void takeTurn() {
/*  65 */     switch (this.nextMove) {
/*     */       case 2:
/*  67 */         this.currentCharge++;
/*  68 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, DIALOG[1]));
/*     */         
/*  70 */         if (this.escapeNext) {
/*  71 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE)); break;
/*     */         } 
/*  73 */         if (this.currentCharge == 3) {
/*  74 */           playSfx();
/*  75 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2], 1.5F, 3.0F));
/*  76 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[1], (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/*  77 */                 .get(0)).base)); break;
/*     */         } 
/*  79 */         setMove(MOVES[0], (byte)2, Intent.UNKNOWN);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/*  84 */         this.currentCharge = 0;
/*  85 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  86 */               .get(0), AbstractGameAction.AttackEffect.FIRE));
/*  87 */         if (this.escapeNext) {
/*  88 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE)); break;
/*     */         } 
/*  90 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  91 */           setMove(MOVES[1], (byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base); break;
/*     */         } 
/*  93 */         setMove(MOVES[0], (byte)2, Intent.UNKNOWN);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 99:
/*  98 */         AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[3], false));
/*     */         
/* 100 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this));
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)99, Intent.ESCAPE));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 109 */     int roll = MathUtils.random(1);
/* 110 */     if (roll == 0) {
/* 111 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINDOPEY_1A"));
/*     */     } else {
/* 113 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GREMLINDOPEY_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 118 */     int roll = MathUtils.random(2);
/* 119 */     if (roll == 0) {
/* 120 */       CardCrawlGame.sound.play("VO_GREMLINDOPEY_2A");
/* 121 */     } else if (roll == 1) {
/* 122 */       CardCrawlGame.sound.play("VO_GREMLINDOPEY_2B");
/*     */     } else {
/* 124 */       CardCrawlGame.sound.play("VO_GREMLINDOPEY_2C");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 130 */     super.die();
/* 131 */     playDeathSfx();
/*     */   }
/*     */ 
/*     */   
/*     */   public void escapeNext() {
/* 136 */     if (!this.cannotEscape && 
/* 137 */       !this.escapeNext) {
/* 138 */       this.escapeNext = true;
/* 139 */       AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 3.0F, DIALOG[4], false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 147 */     setMove(MOVES[0], (byte)2, Intent.UNKNOWN);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deathReact() {
/* 152 */     if (this.intent != Intent.ESCAPE && !this.isDying) {
/* 153 */       AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 3.0F, DIALOG[4], false));
/* 154 */       setMove((byte)99, Intent.ESCAPE);
/* 155 */       createIntent();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\GremlinWizard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */