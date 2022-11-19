/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.unique.GainBlockRandomMonsterAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ 
/*     */ public class Centurion extends AbstractMonster {
/*     */   public static final String ID = "Centurion";
/*  20 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Centurion");
/*  21 */   public static final String NAME = monsterStrings.NAME;
/*  22 */   public static final String[] MOVES = monsterStrings.MOVES; private static final float IDLE_TIMESCALE = 0.8F; private static final int HP_MIN = 76; private static final int HP_MAX = 80; private static final int A_2_HP_MIN = 78; private static final int A_2_HP_MAX = 83; private static final int SLASH_DMG = 12; private static final int FURY_DMG = 6;
/*  23 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int FURY_HITS = 3;
/*     */   private static final int A_2_SLASH_DMG = 14;
/*     */   private static final int A_2_FURY_DMG = 7;
/*     */   private int slashDmg;
/*     */   private int furyDmg;
/*     */   private int furyHits;
/*     */   private int blockAmount;
/*  32 */   private int BLOCK_AMOUNT = 15; private int A_17_BLOCK_AMOUNT = 20; private static final byte SLASH = 1; private static final byte PROTECT = 2;
/*     */   private static final byte FURY = 3;
/*     */   
/*     */   public Centurion(float x, float y) {
/*  36 */     super(NAME, "Centurion", 80, -14.0F, -20.0F, 250.0F, 330.0F, null, x, y);
/*     */     
/*  38 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  39 */       setHp(78, 83);
/*     */     } else {
/*  41 */       setHp(76, 80);
/*     */     } 
/*     */     
/*  44 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  45 */       this.blockAmount = this.A_17_BLOCK_AMOUNT;
/*     */     } else {
/*  47 */       this.blockAmount = this.BLOCK_AMOUNT;
/*     */     } 
/*     */     
/*  50 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  51 */       this.slashDmg = 14;
/*  52 */       this.furyDmg = 7;
/*  53 */       this.furyHits = 3;
/*     */     } else {
/*  55 */       this.slashDmg = 12;
/*  56 */       this.furyDmg = 6;
/*  57 */       this.furyHits = 3;
/*     */     } 
/*     */     
/*  60 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.slashDmg));
/*  61 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.furyDmg));
/*     */     
/*  63 */     loadAnimation("images/monsters/theCity/tank/skeleton.atlas", "images/monsters/theCity/tank/skeleton.json", 1.0F);
/*  64 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  65 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  66 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  67 */     this.state.setTimeScale(0.8F);
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/*  72 */     switch (this.nextMove) {
/*     */       case 1:
/*  74 */         playSfx();
/*  75 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "MACE_HIT"));
/*  76 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*  77 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  78 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*     */         break;
/*     */       case 2:
/*  81 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.25F));
/*  82 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockRandomMonsterAction((AbstractCreature)this, this.blockAmount));
/*     */         break;
/*     */       case 3:
/*  85 */         for (i = 0; i < this.furyHits; i++) {
/*  86 */           playSfx();
/*  87 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "MACE_HIT"));
/*  88 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*  89 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  90 */                 .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*  96 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 100 */     int roll = MathUtils.random(1);
/* 101 */     if (roll == 0) {
/* 102 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_TANK_1A"));
/* 103 */     } else if (roll == 1) {
/* 104 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_TANK_1B"));
/*     */     } else {
/* 106 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_TANK_1C"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 112 */     switch (key) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case "MACE_HIT":
/* 118 */         this.state.setAnimation(0, "Attack", false);
/* 119 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 128 */     if (num >= 65 && !lastTwoMoves((byte)2) && !lastTwoMoves((byte)3)) {
/* 129 */       int i = 0;
/*     */ 
/*     */       
/* 132 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 133 */         if (!m.isDying && !m.isEscaping) {
/* 134 */           i++;
/*     */         }
/*     */       } 
/*     */       
/* 138 */       if (i > 1) {
/* 139 */         setMove((byte)2, Intent.DEFEND);
/*     */         return;
/*     */       } 
/* 142 */       setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, this.furyHits, true);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 147 */     if (!lastTwoMoves((byte)1)) {
/* 148 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       return;
/*     */     } 
/* 151 */     int aliveCount = 0;
/*     */ 
/*     */     
/* 154 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 155 */       if (!m.isDying && !m.isEscaping) {
/* 156 */         aliveCount++;
/*     */       }
/*     */     } 
/*     */     
/* 160 */     if (aliveCount > 1) {
/* 161 */       setMove((byte)2, Intent.DEFEND);
/*     */       return;
/*     */     } 
/* 164 */     setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, this.furyHits, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 172 */     super.damage(info);
/* 173 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 174 */       this.state.setAnimation(0, "Hit", false);
/* 175 */       this.state.setTimeScale(0.8F);
/* 176 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 182 */     this.state.setTimeScale(0.1F);
/* 183 */     useShakeAnimation(5.0F);
/* 184 */     super.die();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Centurion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */