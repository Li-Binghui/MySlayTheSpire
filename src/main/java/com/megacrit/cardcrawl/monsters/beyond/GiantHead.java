/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
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
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;
/*     */ 
/*     */ public class GiantHead extends AbstractMonster {
/*     */   public static final String ID = "GiantHead";
/*  24 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GiantHead");
/*  25 */   public static final String NAME = monsterStrings.NAME;
/*  26 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  27 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP = 500;
/*     */   
/*     */   private static final int A_2_HP = 520;
/*     */   private static final float HB_X_F = 0.0F;
/*     */   private static final float HB_Y_F = -40.0F;
/*     */   private static final float HB_W = 460.0F;
/*     */   private static final float HB_H = 300.0F;
/*     */   private static final int COUNT_DMG = 13;
/*  37 */   private int count = 5; private static final int DEATH_DMG = 30; private static final int GLARE_WEAK = 1; private static final int INCREMENT_DMG = 5; private static final int A_2_DEATH_DMG = 40; private int startingDeathDmg; private static final byte GLARE = 1; private static final byte IT_IS_TIME = 2; private static final byte COUNT = 3;
/*     */   
/*     */   public GiantHead() {
/*  40 */     super(NAME, "GiantHead", 500, 0.0F, -40.0F, 460.0F, 300.0F, null, -70.0F, 40.0F);
/*  41 */     this.type = EnemyType.ELITE;
/*  42 */     this.dialogX = -100.0F * Settings.scale;
/*  43 */     this.dialogY -= 20.0F * Settings.scale;
/*     */     
/*  45 */     loadAnimation("images/monsters/theForest/head/skeleton.atlas", "images/monsters/theForest/head/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  49 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle_open", true);
/*  50 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  51 */     e.setTimeScale(0.5F);
/*     */     
/*  53 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  54 */       setHp(520, 520);
/*     */     } else {
/*  56 */       setHp(500, 500);
/*     */     } 
/*     */     
/*  59 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  60 */       this.startingDeathDmg = 40;
/*     */     } else {
/*  62 */       this.startingDeathDmg = 30;
/*     */     } 
/*     */     
/*  65 */     this.damage.add(new DamageInfo((AbstractCreature)this, 13));
/*  66 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg));
/*  67 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 5));
/*  68 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 10));
/*  69 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 15));
/*  70 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 20));
/*  71 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 25));
/*  72 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 30));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  77 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new SlowPower((AbstractCreature)this, 0)));
/*  78 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  79 */       this.count--;
/*     */     }
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int index;
/*  85 */     switch (this.nextMove) {
/*     */       case 1:
/*  87 */         playSfx();
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, "#r~" + 
/*  89 */               Integer.toString(this.count) + "...~", 1.7F, 1.7F));
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/*  98 */         playSfx();
/*  99 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, "#r~" + 
/* 100 */               Integer.toString(this.count) + "...~", 1.7F, 1.7F));
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 102 */               .get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         break;
/*     */       case 2:
/* 105 */         playSfx();
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, getTimeQuote(), 1.7F, 2.0F));
/* 107 */         index = 1 - this.count;
/* 108 */         if (index > 7) {
/* 109 */           index = 7;
/*     */         }
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 112 */               .get(index), AbstractGameAction.AttackEffect.SMASH));
/*     */         break;
/*     */     } 
/* 115 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 119 */     int roll = MathUtils.random(2);
/* 120 */     if (roll == 0) {
/* 121 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GIANTHEAD_1A"));
/* 122 */     } else if (roll == 1) {
/* 123 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GIANTHEAD_1B"));
/*     */     } else {
/* 125 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_GIANTHEAD_1C"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 130 */     int roll = MathUtils.random(2);
/* 131 */     if (roll == 0) {
/* 132 */       CardCrawlGame.sound.play("VO_GIANTHEAD_2A");
/* 133 */     } else if (roll == 1) {
/* 134 */       CardCrawlGame.sound.play("VO_GIANTHEAD_2B");
/*     */     } else {
/* 136 */       CardCrawlGame.sound.play("VO_GIANTHEAD_2C");
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getTimeQuote() {
/* 141 */     ArrayList<String> quotes = new ArrayList<>();
/* 142 */     quotes.add(DIALOG[0]);
/* 143 */     quotes.add(DIALOG[1]);
/* 144 */     quotes.add(DIALOG[2]);
/* 145 */     quotes.add(DIALOG[3]);
/* 146 */     return quotes.get(MathUtils.random(0, quotes.size() - 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 151 */     super.die();
/* 152 */     playDeathSfx();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 157 */     if (this.count <= 1) {
/* 158 */       if (this.count > -6) {
/* 159 */         this.count--;
/*     */       }
/* 161 */       setMove((byte)2, Intent.ATTACK, this.startingDeathDmg - this.count * 5);
/*     */       
/*     */       return;
/*     */     } 
/* 165 */     this.count--;
/*     */ 
/*     */     
/* 168 */     if (num < 50) {
/* 169 */       if (!lastTwoMoves((byte)1)) {
/* 170 */         setMove((byte)1, Intent.DEBUFF);
/*     */       } else {
/* 172 */         setMove((byte)3, Intent.ATTACK, 13);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 177 */     else if (!lastTwoMoves((byte)3)) {
/* 178 */       setMove((byte)3, Intent.ATTACK, 13);
/*     */     } else {
/* 180 */       setMove((byte)1, Intent.DEBUFF);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\GiantHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */