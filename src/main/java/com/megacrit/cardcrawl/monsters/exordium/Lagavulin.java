/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class Lagavulin extends AbstractMonster {
/*  31 */   private static final Logger logger = LogManager.getLogger(Lagavulin.class.getName());
/*     */   public static final String ID = "Lagavulin";
/*  33 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Lagavulin");
/*  34 */   public static final String NAME = monsterStrings.NAME;
/*  35 */   public static final String[] MOVES = monsterStrings.MOVES; private static final int HP_MIN = 109; private static final int HP_MAX = 111; private static final int A_2_HP_MIN = 112; private static final int A_2_HP_MAX = 115; private static final byte DEBUFF = 1;
/*  36 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final byte STRONG_ATK = 3;
/*     */   private static final byte OPEN = 4;
/*     */   private static final byte IDLE = 5;
/*     */   private static final byte OPEN_NATURAL = 6;
/*  42 */   private static final String DEBUFF_NAME = MOVES[0]; private static final int STRONG_ATK_DMG = 18; private static final int DEBUFF_AMT = -1; private static final int A_18_DEBUFF_AMT = -2; private static final int A_2_STRONG_ATK_DMG = 20;
/*     */   private int attackDmg;
/*     */   private int debuff;
/*     */   private static final int ARMOR_AMT = 8;
/*     */   private boolean isOut = false;
/*     */   private boolean asleep;
/*     */   private boolean isOutTriggered = false;
/*  49 */   private int idleCount = 0, debuffTurnCount = 0;
/*     */   
/*     */   public Lagavulin(boolean setAsleep) {
/*  52 */     super(NAME, "Lagavulin", 111, 0.0F, -25.0F, 320.0F, 220.0F, null, 0.0F, 20.0F);
/*  53 */     this.type = EnemyType.ELITE;
/*  54 */     this.dialogX = -100.0F * Settings.scale;
/*     */     
/*  56 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  57 */       setHp(112, 115);
/*     */     } else {
/*  59 */       setHp(109, 111);
/*     */     } 
/*     */     
/*  62 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  63 */       this.attackDmg = 20;
/*     */     } else {
/*  65 */       this.attackDmg = 18;
/*     */     } 
/*     */     
/*  68 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  69 */       this.debuff = -2;
/*     */     } else {
/*  71 */       this.debuff = -1;
/*     */     } 
/*     */     
/*  74 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.attackDmg));
/*  75 */     this.asleep = setAsleep;
/*     */     
/*  77 */     loadAnimation("images/monsters/theBottom/lagavulin/skeleton.atlas", "images/monsters/theBottom/lagavulin/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  81 */     AnimationState.TrackEntry e = null;
/*  82 */     if (!this.asleep) {
/*  83 */       this.isOut = true;
/*  84 */       this.isOutTriggered = true;
/*  85 */       e = this.state.setAnimation(0, "Idle_2", true);
/*  86 */       updateHitbox(0.0F, -25.0F, 320.0F, 370.0F);
/*     */     } else {
/*  88 */       e = this.state.setAnimation(0, "Idle_1", true);
/*     */     } 
/*  90 */     this.stateData.setMix("Attack", "Idle_2", 0.25F);
/*  91 */     this.stateData.setMix("Hit", "Idle_2", 0.25F);
/*  92 */     this.stateData.setMix("Idle_1", "Idle_2", 0.5F);
/*     */     
/*  94 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  99 */     if (this.asleep) {
/* 100 */       CardCrawlGame.music.precacheTempBgm("ELITE");
/* 101 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 8));
/* 102 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new MetallicizePower((AbstractCreature)this, 8), 8));
/*     */     } else {
/*     */       
/* 105 */       CardCrawlGame.music.unsilenceBGM();
/* 106 */       AbstractDungeon.scene.fadeOutAmbiance();
/* 107 */       CardCrawlGame.music.playTempBgmInstantly("ELITE");
/* 108 */       setMove(DEBUFF_NAME, (byte)1, Intent.STRONG_DEBUFF);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 114 */     switch (this.nextMove) {
/*     */ 
/*     */       
/*     */       case 1:
/* 118 */         this.debuffTurnCount = 0;
/* 119 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "DEBUFF"));
/* 120 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new DexterityPower((AbstractCreature)AbstractDungeon.player, this.debuff), this.debuff));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, this.debuff), this.debuff));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 133 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 3:
/* 136 */         this.debuffTurnCount++;
/* 137 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 138 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 139 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 140 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 141 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 5:
/* 144 */         this.idleCount++;
/* 145 */         if (this.idleCount >= 3) {
/* 146 */           logger.info("idle happened");
/* 147 */           this.isOutTriggered = true;
/* 148 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "OPEN"));
/* 149 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ATTACK, ((DamageInfo)this.damage
/* 150 */                 .get(0)).base));
/*     */         } else {
/* 152 */           setMove((byte)5, Intent.SLEEP);
/*     */         } 
/* 154 */         switch (this.idleCount) {
/*     */           case 1:
/* 156 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[1], 0.5F, 2.0F));
/* 157 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */             break;
/*     */           case 2:
/* 160 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2], 0.5F, 2.0F));
/* 161 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */             break;
/*     */         } 
/*     */         
/*     */         break;
/*     */       
/*     */       case 4:
/* 168 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, TextAboveCreatureAction.TextType.STUNNED));
/* 169 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */       case 6:
/* 172 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "OPEN"));
/* 173 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/* 174 */         createIntent();
/* 175 */         this.isOutTriggered = true;
/* 176 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 188 */     if (stateName.equals("ATTACK")) {
/* 189 */       this.state.setAnimation(0, "Attack", false);
/* 190 */       this.state.addAnimation(0, "Idle_2", true, 0.0F);
/* 191 */     } else if (stateName.equals("DEBUFF")) {
/* 192 */       this.state.setAnimation(0, "Debuff", false);
/* 193 */       this.state.addAnimation(0, "Idle_2", true, 0.0F);
/* 194 */     } else if (stateName.equals("OPEN") && !this.isDying) {
/* 195 */       this.isOut = true;
/* 196 */       updateHitbox(0.0F, -25.0F, 320.0F, 360.0F);
/* 197 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[3], 0.5F, 2.0F));
/* 198 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ReducePowerAction((AbstractCreature)this, (AbstractCreature)this, "Metallicize", 8));
/*     */       
/* 200 */       CardCrawlGame.music.unsilenceBGM();
/* 201 */       AbstractDungeon.scene.fadeOutAmbiance();
/* 202 */       CardCrawlGame.music.playPrecachedTempBgm();
/* 203 */       this.state.setAnimation(0, "Coming_out", false);
/* 204 */       this.state.addAnimation(0, "Idle_2", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 210 */     int previousHealth = this.currentHealth;
/*     */     
/* 212 */     super.damage(info);
/*     */     
/* 214 */     if (this.currentHealth != previousHealth && !this.isOutTriggered) {
/* 215 */       setMove((byte)4, Intent.STUN);
/* 216 */       createIntent();
/* 217 */       this.isOutTriggered = true;
/* 218 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "OPEN"));
/* 219 */     } else if (this.isOutTriggered && info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/*     */       
/* 221 */       this.state.setAnimation(0, "Hit", false);
/* 222 */       this.state.addAnimation(0, "Idle_2", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 228 */     if (this.isOut) {
/* 229 */       if (this.debuffTurnCount < 2) {
/* 230 */         if (lastTwoMoves((byte)3)) {
/* 231 */           setMove(DEBUFF_NAME, (byte)1, Intent.STRONG_DEBUFF);
/*     */         } else {
/* 233 */           setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */         } 
/*     */       } else {
/* 236 */         setMove(DEBUFF_NAME, (byte)1, Intent.STRONG_DEBUFF);
/*     */       } 
/*     */     } else {
/* 239 */       setMove((byte)5, Intent.SLEEP);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 245 */     super.die();
/* 246 */     AbstractDungeon.scene.fadeInAmbiance();
/* 247 */     CardCrawlGame.music.fadeOutTempBGM();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\Lagavulin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */