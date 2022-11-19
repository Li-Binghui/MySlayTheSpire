/*     */ package com.megacrit.cardcrawl.monsters.ending;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ 
/*     */ public class SpireSpear extends AbstractMonster {
/*     */   public static final String ID = "SpireSpear";
/*  27 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SpireSpear");
/*  28 */   public static final String NAME = monsterStrings.NAME;
/*  29 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  30 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */ 
/*     */   
/*  33 */   private int moveCount = 0;
/*     */   
/*     */   private static final byte BURN_STRIKE = 1;
/*     */   private static final byte PIERCER = 2;
/*     */   
/*     */   public SpireSpear() {
/*  39 */     super(NAME, "SpireSpear", 160, 0.0F, -15.0F, 380.0F, 290.0F, null, 70.0F, 10.0F);
/*  40 */     this.type = EnemyType.ELITE;
/*     */     
/*  42 */     loadAnimation("images/monsters/theEnding/spear/skeleton.atlas", "images/monsters/theEnding/spear/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  46 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  47 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  48 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  49 */     e.setTimeScale(0.7F);
/*     */     
/*  51 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  52 */       setHp(180);
/*     */     } else {
/*  54 */       setHp(160);
/*     */     } 
/*     */     
/*  57 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  58 */       this.skewerCount = 4;
/*  59 */       this.damage.add(new DamageInfo((AbstractCreature)this, 6));
/*  60 */       this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*     */     } else {
/*  62 */       this.skewerCount = 3;
/*  63 */       this.damage.add(new DamageInfo((AbstractCreature)this, 5));
/*  64 */       this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*     */     } 
/*     */   }
/*     */   private static final byte SKEWER = 3; private static final int BURN_STRIKE_COUNT = 2; private int skewerCount;
/*     */   
/*     */   public void usePreBattleAction() {
/*  70 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  71 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2)));
/*     */     } else {
/*  73 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 1)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/*  79 */     switch (this.nextMove) {
/*     */       case 1:
/*  81 */         for (i = 0; i < 2; i++) {
/*  82 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  83 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.15F));
/*  84 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  85 */                 .get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         } 
/*     */         
/*  88 */         if (AbstractDungeon.ascensionLevel >= 18) {
/*  89 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Burn(), 2, false, true));
/*     */           break;
/*     */         } 
/*  92 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 2));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*  97 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/*  98 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, 2), 2));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 3:
/* 103 */         for (i = 0; i < this.skewerCount; i++) {
/* 104 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 105 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.05F));
/* 106 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 107 */                 .get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, true));
/*     */         } 
/*     */         break;
/*     */     } 
/*     */     
/* 112 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 117 */     switch (this.moveCount % 3) {
/*     */       case 0:
/* 119 */         if (!lastMove((byte)1)) {
/* 120 */           setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base, 2, true); break;
/*     */         } 
/* 122 */         setMove((byte)2, Intent.BUFF);
/*     */         break;
/*     */       
/*     */       case 1:
/* 126 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, this.skewerCount, true);
/*     */         break;
/*     */       default:
/* 129 */         if (AbstractDungeon.aiRng.randomBoolean()) {
/* 130 */           setMove((byte)2, Intent.BUFF); break;
/*     */         } 
/* 132 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */         break;
/*     */     } 
/*     */     
/* 136 */     this.moveCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 141 */     AnimationState.TrackEntry e = null;
/* 142 */     switch (key) {
/*     */       case "SLOW_ATTACK":
/* 144 */         this.state.setAnimation(0, "Attack_1", false);
/* 145 */         e = this.state.addAnimation(0, "Idle", true, 0.0F);
/* 146 */         e.setTimeScale(0.5F);
/*     */         break;
/*     */       case "ATTACK":
/* 149 */         this.state.setAnimation(0, "Attack_2", false);
/* 150 */         e = this.state.addAnimation(0, "Idle", true, 0.0F);
/* 151 */         e.setTimeScale(0.7F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 160 */     super.damage(info);
/* 161 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 162 */       this.state.setAnimation(0, "Hit", false);
/* 163 */       AnimationState.TrackEntry e = this.state.addAnimation(0, "Idle", true, 0.0F);
/* 164 */       e.setTimeScale(0.7F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 170 */     super.die();
/* 171 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 172 */       if (!m.isDead && !m.isDying) {
/* 173 */         if (AbstractDungeon.player.hasPower("Surrounded")) {
/* 174 */           AbstractDungeon.player.flipHorizontal = (m.drawX < AbstractDungeon.player.drawX);
/* 175 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, "Surrounded"));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 181 */         if (m.hasPower("BackAttack"))
/* 182 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)m, (AbstractCreature)m, "BackAttack")); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\ending\SpireSpear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */