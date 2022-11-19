/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.powers.ThieveryPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
/*     */ 
/*     */ public class Mugger extends AbstractMonster {
/*  26 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Mugger"); public static final String ID = "Mugger";
/*  27 */   public static final String NAME = monsterStrings.NAME;
/*  28 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  29 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 48; private static final int HP_MAX = 52;
/*     */   private static final int A_2_HP_MIN = 50;
/*     */   private static final int A_2_HP_MAX = 54;
/*     */   public static final String ENCOUNTER_NAME = "City Looters";
/*     */   private int swipeDmg;
/*     */   private int bigSwipeDmg;
/*     */   private int goldAmt;
/*  36 */   private int escapeDef = 11; private static final byte MUG = 1; private static final byte SMOKE_BOMB = 2; private static final byte ESCAPE = 3;
/*     */   private static final byte BIGSWIPE = 4;
/*  38 */   private static final String SLASH_MSG1 = DIALOG[0];
/*  39 */   private static final String RUN_MSG = DIALOG[1];
/*  40 */   private int slashCount = 0;
/*  41 */   private int stolenGold = 0;
/*     */   
/*     */   public Mugger(float x, float y) {
/*  44 */     super(NAME, "Mugger", 52, 0.0F, 0.0F, 200.0F, 220.0F, null, x, y);
/*  45 */     this.dialogX = -30.0F * Settings.scale;
/*  46 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  48 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  49 */       this.goldAmt = 20;
/*     */     } else {
/*  51 */       this.goldAmt = 15;
/*     */     } 
/*     */     
/*  54 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  55 */       setHp(50, 54);
/*     */     } else {
/*  57 */       setHp(48, 52);
/*     */     } 
/*     */     
/*  60 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  61 */       this.swipeDmg = 11;
/*  62 */       this.bigSwipeDmg = 18;
/*     */     } else {
/*  64 */       this.swipeDmg = 10;
/*  65 */       this.bigSwipeDmg = 16;
/*     */     } 
/*     */     
/*  68 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.swipeDmg));
/*  69 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.bigSwipeDmg));
/*     */     
/*  71 */     loadAnimation("images/monsters/theCity/looterAlt/skeleton.atlas", "images/monsters/theCity/looterAlt/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  75 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  76 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  81 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ThieveryPower((AbstractCreature)this, this.goldAmt)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  86 */     switch (this.nextMove) {
/*     */       case 1:
/*  88 */         playSfx();
/*  89 */         if (this.slashCount == 1 && AbstractDungeon.aiRng.randomBoolean(0.6F)) {
/*  90 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, SLASH_MSG1));
/*     */         }
/*     */         
/*  93 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  94 */         AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
/*     */             {
/*     */               public void update()
/*     */               {
/*  98 */                 Mugger.this.stolenGold = Mugger.this.stolenGold + Math.min(Mugger.this.goldAmt, AbstractDungeon.player.gold);
/*  99 */                 this.isDone = true;
/*     */               }
/*     */             });
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 103 */               .get(0), this.goldAmt));
/*     */         
/* 105 */         this.slashCount++;
/* 106 */         if (this.slashCount == 2) {
/* 107 */           if (AbstractDungeon.aiRng.randomBoolean(0.5F)) {
/* 108 */             setMove((byte)2, Intent.DEFEND); break;
/*     */           } 
/* 110 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[0], (byte)4, Intent.ATTACK, ((DamageInfo)this.damage
/* 111 */                 .get(1)).base));
/*     */           break;
/*     */         } 
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, MOVES[1], (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/* 115 */               .get(0)).base));
/*     */         break;
/*     */       
/*     */       case 4:
/* 119 */         playSfx();
/* 120 */         this.slashCount++;
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 122 */         AbstractDungeon.actionManager.addToBottom(new AbstractGameAction()
/*     */             {
/*     */               public void update()
/*     */               {
/* 126 */                 Mugger.this.stolenGold = Mugger.this.stolenGold + Math.min(Mugger.this.goldAmt, AbstractDungeon.player.gold);
/* 127 */                 this.isDone = true;
/*     */               }
/*     */             });
/* 130 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 131 */               .get(1), this.goldAmt));
/* 132 */         setMove((byte)2, Intent.DEFEND);
/*     */         break;
/*     */       case 2:
/* 135 */         if (AbstractDungeon.ascensionLevel >= 17) {
/* 136 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.escapeDef + 6));
/*     */         } else {
/* 138 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.escapeDef));
/*     */         } 
/* 140 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ESCAPE));
/*     */         break;
/*     */       case 3:
/* 143 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, RUN_MSG));
/* 144 */         (AbstractDungeon.getCurrRoom()).mugged = true;
/* 145 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmokeBombEffect(this.hb.cX, this.hb.cY)));
/* 146 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(this));
/* 147 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ESCAPE));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 155 */     int roll = AbstractDungeon.aiRng.random(2);
/* 156 */     if (roll == 0) {
/* 157 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_MUGGER_1A"));
/*     */     } else {
/* 159 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_MUGGER_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 164 */     int roll = AbstractDungeon.aiRng.random(2);
/* 165 */     if (roll == 0) {
/* 166 */       CardCrawlGame.sound.play("VO_MUGGER_2A");
/*     */     } else {
/* 168 */       CardCrawlGame.sound.play("VO_MUGGER_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 174 */     playDeathSfx();
/* 175 */     this.state.setTimeScale(0.1F);
/* 176 */     useShakeAnimation(5.0F);
/* 177 */     if (this.stolenGold > 0) {
/* 178 */       AbstractDungeon.getCurrRoom().addStolenGoldToRewards(this.stolenGold);
/*     */     }
/* 180 */     super.die();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 185 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Mugger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */