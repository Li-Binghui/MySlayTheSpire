/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
/*     */ 
/*     */ public class WrithingMass extends AbstractMonster {
/*     */   public static final String ID = "WrithingMass";
/*  30 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("WrithingMass");
/*  31 */   public static final String NAME = monsterStrings.NAME;
/*     */   
/*     */   private static final int HP = 160;
/*     */   
/*     */   private static final int A_2_HP = 175;
/*     */   private boolean firstMove = true;
/*     */   private boolean usedMegaDebuff = false;
/*     */   private static final int HIT_COUNT = 3;
/*     */   
/*     */   public WrithingMass() {
/*  41 */     super(NAME, "WrithingMass", 160, 5.0F, -26.0F, 450.0F, 310.0F, null, 0.0F, 15.0F);
/*     */     
/*  43 */     loadAnimation("images/monsters/theForest/spaghetti/skeleton.atlas", "images/monsters/theForest/spaghetti/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  47 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  48 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  49 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*     */     
/*  51 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  52 */       setHp(175);
/*     */     } else {
/*  54 */       setHp(160);
/*     */     } 
/*     */     
/*  57 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  58 */       this.damage.add(new DamageInfo((AbstractCreature)this, 38));
/*  59 */       this.damage.add(new DamageInfo((AbstractCreature)this, 9));
/*  60 */       this.damage.add(new DamageInfo((AbstractCreature)this, 16));
/*  61 */       this.damage.add(new DamageInfo((AbstractCreature)this, 12));
/*  62 */       this.normalDebuffAmt = 2;
/*     */     } else {
/*  64 */       this.damage.add(new DamageInfo((AbstractCreature)this, 32));
/*  65 */       this.damage.add(new DamageInfo((AbstractCreature)this, 7));
/*  66 */       this.damage.add(new DamageInfo((AbstractCreature)this, 15));
/*  67 */       this.damage.add(new DamageInfo((AbstractCreature)this, 10));
/*  68 */       this.normalDebuffAmt = 2;
/*     */     } 
/*     */   }
/*     */   private int normalDebuffAmt; private static final byte BIG_HIT = 0; private static final byte MULTI_HIT = 1; private static final byte ATTACK_BLOCK = 2; private static final byte ATTACK_DEBUFF = 3; private static final byte MEGA_DEBUFF = 4;
/*     */   
/*     */   public void usePreBattleAction() {
/*  74 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ReactivePower((AbstractCreature)this)));
/*  75 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new MalleablePower((AbstractCreature)this)));
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/*  80 */     switch (this.nextMove) {
/*     */       case 0:
/*  82 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  83 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  84 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  85 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*     */         break;
/*     */       case 1:
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  89 */         for (i = 0; i < 3; i++) {
/*  90 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  91 */                 .get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*     */         }
/*     */         break;
/*     */       case 2:
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  97 */               .get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  98 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, ((DamageInfo)this.damage.get(2)).base));
/*     */         break;
/*     */       case 3:
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 102 */               .get(3), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, this.normalDebuffAmt, true), this.normalDebuffAmt));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.normalDebuffAmt, true), this.normalDebuffAmt));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/*     */         break;
/*     */       case 4:
/* 118 */         this.usedMegaDebuff = true;
/* 119 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new FastShakeAction((AbstractCreature)this, 0.5F, 0.2F));
/* 120 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AddCardToDeckAction(
/* 121 */               CardLibrary.getCard("Parasite").makeCopy()));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 127 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 132 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 133 */       this.state.setAnimation(0, "Hit", false);
/* 134 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */     
/* 137 */     super.damage(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 142 */     switch (key) {
/*     */       case "ATTACK":
/* 144 */         this.state.setAnimation(0, "Attack", false);
/* 145 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 154 */     if (this.firstMove) {
/* 155 */       this.firstMove = false;
/* 156 */       if (num < 33) {
/* 157 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, 3, true);
/* 158 */       } else if (num < 66) {
/* 159 */         setMove((byte)2, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(2)).base);
/*     */       } else {
/* 161 */         setMove((byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(3)).base);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 166 */     if (num < 10) {
/* 167 */       if (!lastMove((byte)0)) {
/* 168 */         setMove((byte)0, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 170 */         getMove(AbstractDungeon.aiRng.random(10, 99));
/*     */       } 
/* 172 */     } else if (num < 20) {
/* 173 */       if (!this.usedMegaDebuff && !lastMove((byte)4)) {
/* 174 */         setMove((byte)4, Intent.STRONG_DEBUFF);
/*     */       }
/* 176 */       else if (AbstractDungeon.aiRng.randomBoolean(0.1F)) {
/* 177 */         setMove((byte)0, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 179 */         getMove(AbstractDungeon.aiRng.random(20, 99));
/*     */       }
/*     */     
/* 182 */     } else if (num < 40) {
/* 183 */       if (!lastMove((byte)3)) {
/* 184 */         setMove((byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(3)).base);
/*     */       }
/* 186 */       else if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 187 */         getMove(AbstractDungeon.aiRng.random(19));
/*     */       } else {
/* 189 */         getMove(AbstractDungeon.aiRng.random(40, 99));
/*     */       }
/*     */     
/* 192 */     } else if (num < 70) {
/* 193 */       if (!lastMove((byte)1)) {
/* 194 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, 3, true);
/*     */       }
/* 196 */       else if (AbstractDungeon.aiRng.randomBoolean(0.3F)) {
/* 197 */         setMove((byte)2, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(2)).base);
/*     */       } else {
/* 199 */         getMove(AbstractDungeon.aiRng.random(39));
/*     */       }
/*     */     
/*     */     }
/* 203 */     else if (!lastMove((byte)2)) {
/* 204 */       setMove((byte)2, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(2)).base);
/*     */     } else {
/* 206 */       getMove(AbstractDungeon.aiRng.random(69));
/*     */     } 
/*     */     
/* 209 */     createIntent();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\WrithingMass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */