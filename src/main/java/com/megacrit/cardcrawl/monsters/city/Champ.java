/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import java.util.ArrayList;
/*     */ 
/*     */ public class Champ
/*     */   extends AbstractMonster {
/*     */   public static final String ID = "Champ";
/*  42 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Champ");
/*  43 */   public static final String NAME = monsterStrings.NAME;
/*  44 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  45 */   public static final String[] DIALOG = monsterStrings.DIALOG; public static final int HP = 420; public static final int A_9_HP = 440; private static final byte HEAVY_SLASH = 1;
/*     */   private static final byte DEFENSIVE_STANCE = 2;
/*     */   private static final byte EXECUTE = 3;
/*     */   private static final byte FACE_SLAP = 4;
/*     */   private static final byte GLOAT = 5;
/*     */   private static final byte TAUNT = 6;
/*     */   private static final byte ANGER = 7;
/*  52 */   private static final String STANCE_NAME = MOVES[0]; private static final String EXECUTE_NAME = MOVES[1]; private static final String SLAP_NAME = MOVES[2];
/*     */   
/*     */   public static final int SLASH_DMG = 16;
/*     */   
/*     */   public static final int EXECUTE_DMG = 10;
/*     */   
/*     */   public static final int SLAP_DMG = 12;
/*     */   public static final int A_2_SLASH_DMG = 18;
/*     */   public static final int A_2_SLAP_DMG = 14;
/*     */   private int slashDmg;
/*     */   private int executeDmg;
/*     */   private int slapDmg;
/*     */   private int blockAmt;
/*     */   private static final int DEBUFF_AMT = 2;
/*     */   private static final int EXEC_COUNT = 2;
/*  67 */   private int numTurns = 0; private static final int FORGE_AMT = 5; private static final int BLOCK_AMT = 15; private static final int A_9_FORGE_AMT = 6; private static final int A_9_BLOCK_AMT = 18; private static final int A_19_FORGE_AMT = 7; private static final int A_19_BLOCK_AMT = 20; private static final int STR_AMT = 2; private static final int A_4_STR_AMT = 3; private static final int A_19_STR_AMT = 4; private int strAmt; private int forgeAmt;
/*  68 */   private int forgeTimes = 0; private int forgeThreshold = 2;
/*     */   private boolean thresholdReached = false, firstTurn = true;
/*     */   
/*     */   public Champ() {
/*  72 */     super(NAME, "Champ", 420, 0.0F, -15.0F, 400.0F, 410.0F, null, -90.0F, 0.0F);
/*  73 */     this.type = EnemyType.BOSS;
/*  74 */     this.dialogX = -100.0F * Settings.scale;
/*  75 */     this.dialogY = 10.0F * Settings.scale;
/*     */     
/*  77 */     loadAnimation("images/monsters/theCity/champ/skeleton.atlas", "images/monsters/theCity/champ/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  81 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  82 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  83 */     e.setTimeScale(0.8F);
/*     */     
/*  85 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  86 */       setHp(440);
/*     */     } else {
/*  88 */       setHp(420);
/*     */     } 
/*     */     
/*  91 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  92 */       this.slashDmg = 18;
/*  93 */       this.executeDmg = 10;
/*  94 */       this.slapDmg = 14;
/*  95 */       this.strAmt = 4;
/*  96 */       this.forgeAmt = 7;
/*  97 */       this.blockAmt = 20;
/*  98 */     } else if (AbstractDungeon.ascensionLevel >= 9) {
/*  99 */       this.slashDmg = 18;
/* 100 */       this.executeDmg = 10;
/* 101 */       this.slapDmg = 14;
/* 102 */       this.strAmt = 3;
/* 103 */       this.forgeAmt = 6;
/* 104 */       this.blockAmt = 18;
/* 105 */     } else if (AbstractDungeon.ascensionLevel >= 4) {
/* 106 */       this.slashDmg = 18;
/* 107 */       this.executeDmg = 10;
/* 108 */       this.slapDmg = 14;
/* 109 */       this.strAmt = 3;
/* 110 */       this.forgeAmt = 5;
/* 111 */       this.blockAmt = 15;
/*     */     } else {
/* 113 */       this.slashDmg = 16;
/* 114 */       this.executeDmg = 10;
/* 115 */       this.slapDmg = 12;
/* 116 */       this.strAmt = 2;
/* 117 */       this.forgeAmt = 5;
/* 118 */       this.blockAmt = 15;
/*     */     } 
/*     */     
/* 121 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.slashDmg));
/* 122 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.executeDmg));
/* 123 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.slapDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 128 */     CardCrawlGame.music.unsilenceBGM();
/* 129 */     AbstractDungeon.scene.fadeOutAmbiance();
/* 130 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_CITY");
/* 131 */     UnlockTracker.markBossAsSeen("CHAMP");
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 136 */     float vfxSpeed = 0.1F;
/*     */     
/* 138 */     if (Settings.FAST_MODE) {
/* 139 */       vfxSpeed = 0.0F;
/*     */     }
/*     */     
/* 142 */     if (this.firstTurn) {
/* 143 */       this.firstTurn = false;
/* 144 */       if (AbstractDungeon.player.hasRelic("Champion Belt")) {
/* 145 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[8], 0.5F, 2.0F));
/*     */       }
/*     */     } 
/*     */     
/* 149 */     switch (this.nextMove) {
/*     */       case 7:
/* 151 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_CHAMP_CHARGE"));
/* 152 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, getLimitBreak(), 2.0F, 3.0F));
/* 153 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 154 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 155 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.25F));
/* 156 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)this));
/* 157 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this, (AbstractCreature)this, "Shackled"));
/*     */         
/* 159 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt * 3), this.strAmt * 3));
/*     */         break;
/*     */       
/*     */       case 1:
/* 163 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 164 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/* 165 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new GoldenSlashEffect(AbstractDungeon.player.hb.cX - 60.0F * Settings.scale, AbstractDungeon.player.hb.cY, false), vfxSpeed));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 172 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 173 */               .get(0), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */       case 2:
/* 176 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt));
/* 177 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new MetallicizePower((AbstractCreature)this, this.forgeAmt), this.forgeAmt));
/*     */         break;
/*     */       
/*     */       case 3:
/* 181 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateJumpAction((AbstractCreature)this));
/* 182 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*     */         
/* 184 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new GoldenSlashEffect(AbstractDungeon.player.hb.cX - 60.0F * Settings.scale, AbstractDungeon.player.hb.cY, true), vfxSpeed));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 192 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 193 */               .get(1), AbstractGameAction.AttackEffect.NONE));
/*     */         
/* 195 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new GoldenSlashEffect(AbstractDungeon.player.hb.cX + 60.0F * Settings.scale, AbstractDungeon.player.hb.cY, true), vfxSpeed));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 202 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 203 */               .get(1), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */       case 4:
/* 206 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_CHAMP_SLAP"));
/* 207 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 208 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 209 */               .get(2), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/* 210 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 216 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 224 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt), this.strAmt));
/*     */         break;
/*     */       
/*     */       case 6:
/* 228 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CHAMP_2A"));
/* 229 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, getTaunt()));
/* 230 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 236 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 249 */     switch (key) {
/*     */       case "ATTACK":
/* 251 */         this.state.setAnimation(0, "Attack", false);
/* 252 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 261 */     super.damage(info);
/* 262 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 263 */       this.state.setAnimation(0, "Hit", false);
/* 264 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getTaunt() {
/* 269 */     ArrayList<String> derp = new ArrayList<>();
/* 270 */     derp.add(DIALOG[0]);
/* 271 */     derp.add(DIALOG[1]);
/* 272 */     derp.add(DIALOG[2]);
/* 273 */     derp.add(DIALOG[3]);
/* 274 */     return derp.get(MathUtils.random(derp.size() - 1));
/*     */   }
/*     */   
/*     */   private String getLimitBreak() {
/* 278 */     ArrayList<String> derp = new ArrayList<>();
/* 279 */     derp.add(DIALOG[4]);
/* 280 */     derp.add(DIALOG[5]);
/* 281 */     return derp.get(MathUtils.random(derp.size() - 1));
/*     */   }
/*     */   
/*     */   private String getDeathQuote() {
/* 285 */     ArrayList<String> derp = new ArrayList<>();
/* 286 */     derp.add(DIALOG[6]);
/* 287 */     derp.add(DIALOG[7]);
/* 288 */     return derp.get(MathUtils.random(derp.size() - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 294 */     this.numTurns++;
/*     */     
/* 296 */     if (this.currentHealth < this.maxHealth / 2 && !this.thresholdReached) {
/* 297 */       this.thresholdReached = true;
/* 298 */       setMove((byte)7, Intent.BUFF);
/*     */       
/*     */       return;
/*     */     } 
/* 302 */     if (!lastMove((byte)3) && !lastMoveBefore((byte)3) && this.thresholdReached) {
/* 303 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new TalkAction((AbstractCreature)this, getDeathQuote(), 2.0F, 2.0F));
/* 304 */       setMove(EXECUTE_NAME, (byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, 2, true);
/*     */       
/*     */       return;
/*     */     } 
/* 308 */     if (this.numTurns == 4 && !this.thresholdReached) {
/* 309 */       setMove((byte)6, Intent.DEBUFF);
/* 310 */       this.numTurns = 0;
/*     */       
/*     */       return;
/*     */     } 
/* 314 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*     */       
/* 316 */       if (!lastMove((byte)2) && this.forgeTimes < this.forgeThreshold && num <= 30) {
/* 317 */         this.forgeTimes++;
/* 318 */         setMove(STANCE_NAME, (byte)2, Intent.DEFEND_BUFF);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/* 323 */     } else if (!lastMove((byte)2) && this.forgeTimes < this.forgeThreshold && num <= 15) {
/* 324 */       this.forgeTimes++;
/* 325 */       setMove(STANCE_NAME, (byte)2, Intent.DEFEND_BUFF);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 331 */     if (!lastMove((byte)5) && !lastMove((byte)2) && num <= 30) {
/* 332 */       setMove((byte)5, Intent.BUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 337 */     if (!lastMove((byte)4) && num <= 55) {
/* 338 */       setMove(SLAP_NAME, (byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(2)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 342 */     if (!lastMove((byte)1)) {
/* 343 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } else {
/* 345 */       setMove(SLAP_NAME, (byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(2)).base);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 352 */     useFastShakeAnimation(5.0F);
/* 353 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 354 */     super.die();
/* 355 */     if (MathUtils.randomBoolean()) {
/* 356 */       CardCrawlGame.sound.play("VO_CHAMP_3A");
/*     */     } else {
/* 358 */       CardCrawlGame.sound.play("VO_CHAMP_3B");
/*     */     } 
/* 360 */     AbstractDungeon.scene.fadeInAmbiance();
/* 361 */     onBossVictoryLogic();
/* 362 */     UnlockTracker.hardUnlockOverride("CHAMP");
/* 363 */     UnlockTracker.unlockAchievement("CHAMP");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Champ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */