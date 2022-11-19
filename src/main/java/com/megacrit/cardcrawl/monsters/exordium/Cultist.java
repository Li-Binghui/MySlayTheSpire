/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

/*     */
/*     */ public class Cultist extends AbstractMonster {
/*     */   public static final String ID = "Cultist";
/*  23 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Cultist");
/*  24 */   public static final String NAME = monsterStrings.NAME;
/*  25 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  26 */   public static final String[] DIALOG = monsterStrings.DIALOG; public static final String MURDER_ENCOUNTER_KEY = "Murder of Cultists"; private static final int HP_MIN = 48; private static final int HP_MAX = 54; private static final int A_2_HP_MIN = 50; private static final int A_2_HP_MAX = 56; private static final float HB_X = -8.0F;
/*     */   private static final float HB_Y = 10.0F;
/*     */   private static final float HB_W = 230.0F;
/*  29 */   private static final String INCANTATION_NAME = MOVES[2];
/*     */   
/*     */   private static final float HB_H = 240.0F;
/*     */   
/*     */   private static final int ATTACK_DMG = 6;
/*     */   
/*     */   private boolean firstMove = true;
/*     */   private boolean saidPower = false;
/*     */   private static final int RITUAL_AMT = 3;
/*     */   private static final int A_2_RITUAL_AMT = 4;
/*  39 */   private int ritualAmount = 0; private static final byte DARK_STRIKE = 1;
/*     */   private static final byte INCANTATION = 3;
/*     */   private boolean talky = true;
/*     */   
/*     */   public Cultist(float x, float y, boolean talk) {
/*  44 */     super(NAME, "Cultist", 54, -8.0F, 10.0F, 230.0F, 240.0F, null, x, y);
/*     */     
/*  46 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  47 */       setHp(50, 56);
/*     */     } else {
/*  49 */       setHp(48, 54);
/*     */     } 
/*     */     
/*  52 */     this.dialogX = -50.0F * Settings.scale;
/*  53 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  55 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  56 */       this.ritualAmount = 4;
/*     */     } else {
/*  58 */       this.ritualAmount = 3;
/*     */     } 
/*     */     
/*  61 */     this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*     */     
/*  63 */     this.talky = talk;
/*  64 */     if (Settings.FAST_MODE) {
/*  65 */       this.talky = false;
/*     */     }
/*     */     
/*  68 */     loadAnimation("images/monsters/theBottom/cultist/skeleton.atlas", "images/monsters/theBottom/cultist/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  72 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "waving", true);
/*  73 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */   
/*     */   public Cultist(float x, float y) {
/*  77 */     this(x, y, true);
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int temp;
/*  82 */     switch (this.nextMove) {
/*     */       case 3:
/*  84 */         temp = MathUtils.random(1, 10);
/*  85 */         if (this.talky) {
/*  86 */           playSfx();
/*  87 */           if (temp < 4) {
/*  88 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0], 1.0F, 2.0F));
/*  89 */             this.saidPower = true;
/*  90 */           } else if (temp < 7) {
/*  91 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[1], 1.0F, 2.0F));
/*     */           } 
/*     */         } 
/*  94 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  95 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RitualPower((AbstractCreature)this, this.ritualAmount + 1, false)));
/*     */           break;
/*     */         } 
/*  98 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RitualPower((AbstractCreature)this, this.ritualAmount, false)));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 105 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 111 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 115 */     int roll = MathUtils.random(2);
/* 116 */     if (roll == 0) {
/* 117 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1A"));
/* 118 */     } else if (roll == 1) {
/* 119 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1B"));
/*     */     } else {
/* 121 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1C"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 126 */     int roll = MathUtils.random(2);
/* 127 */     if (roll == 0) {
/* 128 */       CardCrawlGame.sound.play("VO_CULTIST_2A");
/* 129 */     } else if (roll == 1) {
/* 130 */       CardCrawlGame.sound.play("VO_CULTIST_2B");
/*     */     } else {
/* 132 */       CardCrawlGame.sound.play("VO_CULTIST_2C");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 138 */     playDeathSfx();
/* 139 */     this.state.setTimeScale(0.1F);
/* 140 */     useShakeAnimation(5.0F);
/* 141 */     if (this.talky && 
/* 142 */       this.saidPower) {
/* 143 */       AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[2], false));
/*     */ 
/*     */       
/* 146 */       this.deathTimer += 1.5F;
/*     */     } 
/*     */     
/* 149 */     super.die();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 155 */     if (this.firstMove) {
/* 156 */       this.firstMove = false;
/* 157 */       setMove(INCANTATION_NAME, (byte)3, Intent.BUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 162 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\Cultist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */