/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.SummonGremlinAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;
/*     */ 
/*     */ public class GremlinLeader extends AbstractMonster {
/*  28 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinLeader"); public static final String ID = "GremlinLeader";
/*  29 */   public static final String NAME = monsterStrings.NAME;
/*  30 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  31 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   private static final int HP_MIN = 140;
/*     */   private static final int HP_MAX = 148;
/*     */   private static final int A_2_HP_MIN = 145;
/*     */   private static final int A_2_HP_MAX = 155;
/*     */   public static final String ENC_NAME = "Gremlin Leader Combat";
/*  37 */   public AbstractMonster[] gremlins = new AbstractMonster[3];
/*  38 */   public static final float[] POSX = new float[] { -366.0F, -170.0F, -532.0F };
/*  39 */   public static final float[] POSY = new float[] { -4.0F, 6.0F, 0.0F };
/*     */ 
/*     */ 
/*     */   
/*     */   private static final byte RALLY = 2;
/*     */ 
/*     */   
/*  46 */   private static final String RALLY_NAME = MOVES[0];
/*     */   
/*     */   private static final byte ENCOURAGE = 3;
/*     */   private static final int STR_AMT = 3;
/*     */   private static final int BLOCK_AMT = 6;
/*     */   private static final int A_2_STR_AMT = 4;
/*     */   private static final int A_18_STR_AMT = 5;
/*     */   private static final int A_18_BLOCK_AMT = 10;
/*     */   private int strAmt;
/*     */   private int blockAmt;
/*     */   private static final byte STAB = 4;
/*  57 */   private int STAB_DMG = 6, STAB_AMT = 3;
/*     */   
/*     */   public GremlinLeader() {
/*  60 */     super(NAME, "GremlinLeader", 148, 0.0F, -15.0F, 200.0F, 310.0F, null, 35.0F, 0.0F);
/*  61 */     this.type = EnemyType.ELITE;
/*     */     
/*  63 */     loadAnimation("images/monsters/theCity/gremlinleader/skeleton.atlas", "images/monsters/theCity/gremlinleader/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  67 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  68 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  69 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  70 */     e.setTimeScale(0.8F);
/*     */     
/*  72 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  73 */       setHp(145, 155);
/*     */     } else {
/*  75 */       setHp(140, 148);
/*     */     } 
/*     */     
/*  78 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  79 */       this.strAmt = 5;
/*  80 */       this.blockAmt = 10;
/*  81 */     } else if (AbstractDungeon.ascensionLevel >= 3) {
/*  82 */       this.strAmt = 4;
/*  83 */       this.blockAmt = 6;
/*     */     } else {
/*  85 */       this.strAmt = 3;
/*  86 */       this.blockAmt = 6;
/*     */     } 
/*     */     
/*  89 */     this.dialogX = -80.0F * Settings.scale;
/*  90 */     this.dialogY = 50.0F * Settings.scale;
/*  91 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.STAB_DMG));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  96 */     this.gremlins[0] = (AbstractDungeon.getMonsters()).monsters.get(0);
/*  97 */     this.gremlins[1] = (AbstractDungeon.getMonsters()).monsters.get(1);
/*  98 */     this.gremlins[2] = null;
/*     */     
/* 100 */     for (AbstractMonster m : this.gremlins) {
/* 101 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new MinionPower((AbstractCreature)this)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 107 */     switch (this.nextMove) {
/*     */       case 2:
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "CALL"));
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SummonGremlinAction(this.gremlins));
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SummonGremlinAction(this.gremlins));
/*     */         break;
/*     */       case 3:
/* 114 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, getEncourageQuote()));
/* 115 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 116 */           if (m == this) {
/* 117 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.strAmt), this.strAmt));
/*     */             continue;
/*     */           } 
/* 120 */           if (!m.isDying) {
/* 121 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.strAmt), this.strAmt));
/*     */             
/* 123 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)this, this.blockAmt));
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 129 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 130 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 131 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 132 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
/* 133 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 134 */               .get(0), AbstractGameAction.AttackEffect.SLASH_VERTICAL, true));
/* 135 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 136 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*     */         break;
/*     */     } 
/* 139 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private String getEncourageQuote() {
/* 143 */     ArrayList<String> list = new ArrayList<>();
/* 144 */     list.add(DIALOG[0]);
/* 145 */     list.add(DIALOG[1]);
/* 146 */     list.add(DIALOG[2]);
/*     */     
/* 148 */     return list.get(AbstractDungeon.aiRng.random(0, list.size() - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 155 */     if (numAliveGremlins() == 0) {
/* 156 */       if (num < 75) {
/* 157 */         if (!lastMove((byte)2)) {
/* 158 */           setMove(RALLY_NAME, (byte)2, Intent.UNKNOWN);
/*     */         } else {
/* 160 */           setMove((byte)4, Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */         }
/*     */       
/* 163 */       } else if (!lastMove((byte)4)) {
/* 164 */         setMove((byte)4, Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */       } else {
/* 166 */         setMove(RALLY_NAME, (byte)2, Intent.UNKNOWN);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 171 */     else if (numAliveGremlins() < 2) {
/* 172 */       if (num < 50) {
/* 173 */         if (!lastMove((byte)2)) {
/* 174 */           setMove(RALLY_NAME, (byte)2, Intent.UNKNOWN);
/*     */         } else {
/* 176 */           getMove(AbstractDungeon.aiRng.random(50, 99));
/*     */         } 
/* 178 */       } else if (num < 80) {
/* 179 */         if (!lastMove((byte)3)) {
/* 180 */           setMove((byte)3, Intent.DEFEND_BUFF);
/*     */         } else {
/* 182 */           setMove((byte)4, Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */         }
/*     */       
/* 185 */       } else if (!lastMove((byte)4)) {
/* 186 */         setMove((byte)4, Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */       } else {
/* 188 */         getMove(AbstractDungeon.aiRng.random(0, 80));
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 193 */     else if (numAliveGremlins() > 1) {
/* 194 */       if (num < 66) {
/* 195 */         if (!lastMove((byte)3)) {
/* 196 */           setMove((byte)3, Intent.DEFEND_BUFF);
/*     */         } else {
/* 198 */           setMove((byte)4, Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */         }
/*     */       
/* 201 */       } else if (!lastMove((byte)4)) {
/* 202 */         setMove((byte)4, Intent.ATTACK, this.STAB_DMG, this.STAB_AMT, true);
/*     */       } else {
/* 204 */         setMove((byte)3, Intent.DEFEND_BUFF);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int numAliveGremlins() {
/* 211 */     int count = 0;
/* 212 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 213 */       if (m != null && m != this && !m.isDying) {
/* 214 */         count++;
/*     */       }
/*     */     } 
/* 217 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 222 */     switch (stateName) {
/*     */       case "ATTACK":
/* 224 */         this.state.setAnimation(0, "Attack", false);
/* 225 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "CALL":
/* 228 */         this.state.setAnimation(0, "Call", false);
/* 229 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 236 */     super.damage(info);
/* 237 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 238 */       this.state.setAnimation(0, "Hit", false);
/* 239 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 245 */     super.die();
/* 246 */     boolean first = true;
/* 247 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 248 */       if (!m.isDying) {
/* 249 */         if (first) {
/* 250 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)m, DIALOG[3], 0.5F, 1.2F));
/* 251 */           first = false; continue;
/*     */         } 
/* 253 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)m, DIALOG[4], 0.5F, 1.2F));
/*     */       } 
/*     */     } 
/*     */     
/* 257 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 258 */       if (!m.isDying)
/* 259 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EscapeAction(m)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\GremlinLeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */