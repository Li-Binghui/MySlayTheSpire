/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegrowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class Darkling extends AbstractMonster {
/*  32 */   private static final Logger logger = LogManager.getLogger(Darkling.class.getName());
/*     */   public static final String ID = "Darkling";
/*  34 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Darkling");
/*  35 */   public static final String NAME = monsterStrings.NAME;
/*  36 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  37 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final int HP_MIN = 48;
/*     */   
/*     */   public static final int HP_MAX = 56;
/*     */   public static final int A_2_HP_MIN = 50;
/*     */   public static final int A_2_HP_MAX = 59;
/*     */   private static final float HB_X = 0.0F;
/*     */   private static final float HB_Y = -20.0F;
/*     */   private static final float HB_W = 260.0F;
/*     */   private static final float HB_H = 200.0F;
/*     */   private static final int BITE_DMG = 8;
/*     */   private static final int A_2_BITE_DMG = 9;
/*     */   
/*     */   public Darkling(float x, float y) {
/*  52 */     super(NAME, "Darkling", 56, 0.0F, -20.0F, 260.0F, 200.0F, null, x, y + 20.0F);
/*  53 */     loadAnimation("images/monsters/theForest/darkling/skeleton.atlas", "images/monsters/theForest/darkling/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  59 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  60 */     e.setTimeScale(MathUtils.random(0.75F, 1.0F));
/*     */     
/*  62 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  63 */       setHp(50, 59);
/*     */     } else {
/*  65 */       setHp(48, 56);
/*     */     } 
/*     */     
/*  68 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  69 */       this.chompDmg = 9;
/*  70 */       this.nipDmg = AbstractDungeon.monsterHpRng.random(9, 13);
/*     */     } else {
/*  72 */       this.chompDmg = 8;
/*  73 */       this.nipDmg = AbstractDungeon.monsterHpRng.random(7, 11);
/*     */     } 
/*     */     
/*  76 */     this.dialogX = -50.0F * Settings.scale;
/*  77 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.chompDmg));
/*  78 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.nipDmg));
/*     */   }
/*     */   private int chompDmg; private int nipDmg; private static final int BLOCK_AMT = 12; private static final int CHOMP_AMT = 2; private static final byte CHOMP = 1; private static final byte HARDEN = 2; private static final byte NIP = 3; private static final byte COUNT = 4; private static final byte REINCARNATE = 5; private boolean firstMove = true;
/*     */   
/*     */   public void usePreBattleAction() {
/*  83 */     (AbstractDungeon.getCurrRoom()).cannotLose = true;
/*  84 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegrowPower((AbstractCreature)this)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  89 */     switch (this.nextMove) {
/*     */       case 1:
/*  91 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  92 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*  93 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  94 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  96 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         break;
/*     */       case 2:
/*  99 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 12));
/* 100 */         if (AbstractDungeon.ascensionLevel >= 17) {
/* 101 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 2), 2));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 3:
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 107 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 108 */               .get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*     */         break;
/*     */       case 4:
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, DIALOG[0]));
/*     */         break;
/*     */       case 5:
/* 114 */         if (MathUtils.randomBoolean()) {
/* 115 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("DARKLING_REGROW_2", 
/* 116 */                 MathUtils.random(-0.1F, 0.1F)));
/*     */         } else {
/* 118 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("DARKLING_REGROW_1", 
/* 119 */                 MathUtils.random(-0.1F, 0.1F)));
/*     */         } 
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, this.maxHealth / 2));
/* 122 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REVIVE"));
/* 123 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegrowPower((AbstractCreature)this), 1));
/* 124 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 125 */           r.onSpawnMonster(this);
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 130 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 135 */     if (this.halfDead) {
/* 136 */       setMove((byte)5, Intent.BUFF);
/*     */       
/*     */       return;
/*     */     } 
/* 140 */     if (this.firstMove) {
/* 141 */       if (num < 50) {
/* 142 */         if (AbstractDungeon.ascensionLevel >= 17) {
/* 143 */           setMove((byte)2, Intent.DEFEND_BUFF);
/*     */         } else {
/* 145 */           setMove((byte)2, Intent.DEFEND);
/*     */         } 
/*     */       } else {
/* 148 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       } 
/*     */       
/* 151 */       this.firstMove = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 156 */     if (num < 40) {
/* 157 */       if (!lastMove((byte)1) && (AbstractDungeon.getMonsters()).monsters.lastIndexOf(this) % 2 == 0) {
/* 158 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */       } else {
/* 160 */         getMove(AbstractDungeon.aiRng.random(40, 99));
/*     */       } 
/* 162 */     } else if (num < 70) {
/* 163 */       if (!lastMove((byte)2)) {
/* 164 */         if (AbstractDungeon.ascensionLevel >= 17) {
/* 165 */           setMove((byte)2, Intent.DEFEND_BUFF);
/*     */         } else {
/* 167 */           setMove((byte)2, Intent.DEFEND);
/*     */         } 
/*     */       } else {
/* 170 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */       }
/*     */     
/* 173 */     } else if (!lastTwoMoves((byte)3)) {
/* 174 */       setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */     } else {
/* 176 */       getMove(AbstractDungeon.aiRng.random(0, 99));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 183 */     switch (key) {
/*     */       case "ATTACK":
/* 185 */         this.state.setAnimation(0, "Attack", false);
/* 186 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "REVIVE":
/* 189 */         this.halfDead = false;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 198 */     super.damage(info);
/* 199 */     if (this.currentHealth <= 0 && !this.halfDead) {
/* 200 */       this.halfDead = true;
/* 201 */       for (AbstractPower p : this.powers) {
/* 202 */         p.onDeath();
/*     */       }
/* 204 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 205 */         r.onMonsterDeath(this);
/*     */       }
/* 207 */       this.powers.clear();
/*     */       
/* 209 */       logger.info("This monster is now half dead.");
/* 210 */       boolean allDead = true;
/* 211 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 212 */         if (m.id.equals("Darkling") && !m.halfDead) {
/* 213 */           allDead = false;
/*     */         }
/*     */       } 
/*     */       
/* 217 */       logger.info("All dead: " + allDead);
/* 218 */       if (!allDead) {
/* 219 */         if (this.nextMove != 4) {
/* 220 */           setMove((byte)4, Intent.UNKNOWN);
/* 221 */           createIntent();
/* 222 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)4, Intent.UNKNOWN));
/*     */         } 
/*     */       } else {
/* 225 */         (AbstractDungeon.getCurrRoom()).cannotLose = false;
/* 226 */         this.halfDead = false;
/* 227 */         for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 228 */           m.die();
/*     */         }
/*     */       } 
/* 231 */     } else if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 232 */       this.state.setAnimation(0, "Hit", false);
/* 233 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 239 */     if (!(AbstractDungeon.getCurrRoom()).cannotLose)
/* 240 */       super.die(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Darkling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */