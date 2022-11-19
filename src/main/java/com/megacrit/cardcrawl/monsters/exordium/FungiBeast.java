/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SporeCloudPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ 
/*     */ public class FungiBeast extends AbstractMonster {
/*     */   public static final String ID = "FungiBeast";
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("FungiBeast"); public static final String DOUBLE_ENCOUNTER = "TwoFungiBeasts";
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 22; private static final int HP_MAX = 28;
/*     */   private static final int A_2_HP_MIN = 24;
/*     */   private static final int A_2_HP_MAX = 28;
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = -16.0F;
/*     */   private static final float HB_W = 260.0F;
/*     */   private static final float HB_H = 170.0F;
/*     */   private int biteDamage;
/*     */   private int strAmt;
/*     */   private static final int BITE_DMG = 6;
/*     */   private static final int GROW_STR = 3;
/*     */   private static final int A_2_GROW_STR = 4;
/*     */   private static final byte BITE = 1;
/*     */   private static final byte GROW = 2;
/*     */   private static final int VULN_AMT = 2;
/*     */   
/*     */   public FungiBeast(float x, float y) {
/*  42 */     super(NAME, "FungiBeast", 28, 0.0F, -16.0F, 260.0F, 170.0F, null, x, y);
/*     */     
/*  44 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  45 */       setHp(24, 28);
/*     */     } else {
/*  47 */       setHp(22, 28);
/*     */     } 
/*     */     
/*  50 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  51 */       this.strAmt = 4;
/*  52 */       this.biteDamage = 6;
/*     */     } else {
/*  54 */       this.strAmt = 3;
/*  55 */       this.biteDamage = 6;
/*     */     } 
/*     */     
/*  58 */     loadAnimation("images/monsters/theBottom/fungi/skeleton.atlas", "images/monsters/theBottom/fungi/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  62 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  63 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  64 */     e.setTimeScale(MathUtils.random(0.7F, 1.0F));
/*     */     
/*  66 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.biteDamage));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  71 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new SporeCloudPower((AbstractCreature)this, 2)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  77 */     switch (this.nextMove) {
/*     */       case 1:
/*  79 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*  81 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  82 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*     */         break;
/*     */       case 2:
/*  85 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  86 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt + 1), this.strAmt + 1));
/*     */           break;
/*     */         } 
/*  89 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt), this.strAmt));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  95 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 102 */     if (num < 60) {
/* 103 */       if (lastTwoMoves((byte)1)) {
/* 104 */         setMove(MOVES[0], (byte)2, Intent.BUFF);
/*     */       } else {
/* 106 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 111 */     else if (lastMove((byte)2)) {
/* 112 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } else {
/* 114 */       setMove(MOVES[0], (byte)2, Intent.BUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 121 */     switch (key) {
/*     */       case "ATTACK":
/* 123 */         this.state.setAnimation(0, "Attack", false);
/* 124 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 133 */     super.damage(info);
/* 134 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 135 */       this.state.setAnimation(0, "Hit", false);
/* 136 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\FungiBeast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */