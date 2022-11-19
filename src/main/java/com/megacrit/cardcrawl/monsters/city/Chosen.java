/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
/*     */ 
/*     */ public class Chosen extends AbstractMonster {
/*     */   public static final String ID = "Chosen";
/*  27 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Chosen");
/*  28 */   public static final String NAME = monsterStrings.NAME;
/*  29 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  30 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final float IDLE_TIMESCALE = 0.8F;
/*     */   
/*     */   private static final int HP_MIN = 95;
/*     */   
/*     */   private static final int HP_MAX = 99;
/*     */   
/*     */   private static final int A_2_HP_MIN = 98;
/*     */   
/*     */   private static final int A_2_HP_MAX = 103;
/*     */   
/*     */   private static final float HB_X = 5.0F;
/*     */   
/*     */   private static final float HB_Y = -10.0F;
/*     */   
/*     */   private static final float HB_W = 200.0F;
/*     */   private static final float HB_H = 280.0F;
/*     */   private static final int ZAP_DMG = 18;
/*     */   private static final int A_2_ZAP_DMG = 21;
/*     */   private static final int DEBILITATE_DMG = 10;
/*     */   private static final int A_2_DEBILITATE_DMG = 12;
/*     */   private static final int POKE_DMG = 5;
/*     */   
/*     */   public Chosen() {
/*  55 */     this(0.0F, 0.0F);
/*     */   }
/*     */   private static final int A_2_POKE_DMG = 6; private int zapDmg; private int debilitateDmg; private int pokeDmg; private static final int DEBILITATE_VULN = 2; private static final int DRAIN_STR = 3; private static final int DRAIN_WEAK = 3; private static final byte ZAP = 1; private static final byte DRAIN = 2; private static final byte DEBILITATE = 3; private static final byte HEX = 4; private static final byte POKE = 5; private static final int HEX_AMT = 1; private boolean firstTurn = true, usedHex = false;
/*     */   public Chosen(float x, float y) {
/*  59 */     super(NAME, "Chosen", 99, 5.0F, -10.0F, 200.0F, 280.0F, null, x, -20.0F + y);
/*  60 */     this.dialogX = -30.0F * Settings.scale;
/*  61 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  63 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  64 */       setHp(98, 103);
/*     */     } else {
/*  66 */       setHp(95, 99);
/*     */     } 
/*     */     
/*  69 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  70 */       this.zapDmg = 21;
/*  71 */       this.debilitateDmg = 12;
/*  72 */       this.pokeDmg = 6;
/*     */     } else {
/*  74 */       this.zapDmg = 18;
/*  75 */       this.debilitateDmg = 10;
/*  76 */       this.pokeDmg = 5;
/*     */     } 
/*     */     
/*  79 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.zapDmg));
/*  80 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.debilitateDmg));
/*  81 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.pokeDmg));
/*     */     
/*  83 */     loadAnimation("images/monsters/theCity/chosen/skeleton.atlas", "images/monsters/theCity/chosen/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  87 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  88 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  89 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  90 */     this.stateData.setMix("Attack", "Idle", 0.2F);
/*  91 */     this.state.setTimeScale(0.8F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  96 */     switch (this.nextMove) {
/*     */       case 5:
/*  98 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  99 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 100 */               .get(2), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 102 */               .get(2), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
/*     */         break;
/*     */       case 1:
/* 105 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new FastShakeAction((AbstractCreature)this, 0.3F, 0.5F));
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 107 */               .get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         break;
/*     */       case 2:
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 3, true), 3));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 116 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 3), 3));
/*     */         break;
/*     */       
/*     */       case 3:
/* 120 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 122 */               .get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/* 123 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 131 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0]));
/* 132 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 133 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.2F));
/* 134 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new HexPower((AbstractCreature)AbstractDungeon.player, 1)));
/*     */         break;
/*     */     } 
/*     */     
/* 138 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 143 */     switch (key) {
/*     */       case "ATTACK":
/* 145 */         this.state.setAnimation(0, "Attack", false);
/* 146 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 155 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/* 157 */       if (!this.usedHex) {
/* 158 */         this.usedHex = true;
/* 159 */         setMove((byte)4, Intent.STRONG_DEBUFF);
/*     */         
/*     */         return;
/*     */       } 
/* 163 */       if (!lastMove((byte)3) && !lastMove((byte)2)) {
/* 164 */         if (num < 50) {
/* 165 */           setMove((byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */           return;
/*     */         } 
/* 168 */         setMove((byte)2, Intent.DEBUFF);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 173 */       if (num < 40) {
/* 174 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */         return;
/*     */       } 
/* 177 */       setMove((byte)5, Intent.ATTACK, ((DamageInfo)this.damage.get(2)).base, 2, true);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 182 */     if (this.firstTurn) {
/* 183 */       this.firstTurn = false;
/* 184 */       setMove((byte)5, Intent.ATTACK, ((DamageInfo)this.damage.get(2)).base, 2, true);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 189 */     if (!this.usedHex) {
/* 190 */       this.usedHex = true;
/* 191 */       setMove((byte)4, Intent.STRONG_DEBUFF);
/*     */       
/*     */       return;
/*     */     } 
/* 195 */     if (!lastMove((byte)3) && !lastMove((byte)2)) {
/* 196 */       if (num < 50) {
/* 197 */         setMove((byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         return;
/*     */       } 
/* 200 */       setMove((byte)2, Intent.DEBUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 205 */     if (num < 40) {
/* 206 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       return;
/*     */     } 
/* 209 */     setMove((byte)5, Intent.ATTACK, ((DamageInfo)this.damage.get(2)).base, 2, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 219 */     super.damage(info);
/* 220 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 221 */       this.state.setAnimation(0, "Hit", false);
/* 222 */       this.state.setTimeScale(0.8F);
/* 223 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 229 */     super.die();
/* 230 */     CardCrawlGame.sound.play("CHOSEN_DEATH");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Chosen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */