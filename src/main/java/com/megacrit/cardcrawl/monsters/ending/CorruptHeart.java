/*     */ package com.megacrit.cardcrawl.monsters.ending;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.status.*;
/*     */
/*     */
/*     */
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.HeartAnimListener;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*     */ import com.megacrit.cardcrawl.powers.BeatOfDeathPower;
/*     */ import com.megacrit.cardcrawl.powers.FrailPower;
/*     */ import com.megacrit.cardcrawl.powers.InvinciblePower;
/*     */ import com.megacrit.cardcrawl.powers.PainfulStabsPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
/*     */ 
/*     */ public class CorruptHeart extends AbstractMonster {
/*  39 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("CorruptHeart"); public static final String ID = "CorruptHeart";
/*  40 */   public static final String NAME = monsterStrings.NAME;
/*  41 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  42 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*  43 */   private HeartAnimListener animListener = new HeartAnimListener();
/*     */   private static final byte BLOOD_SHOTS = 1;
/*     */   private static final byte ECHO_ATTACK = 2;
/*     */   private static final byte DEBILITATE = 3;
/*     */   private static final byte GAIN_ONE_STRENGTH = 4;
/*     */   public static final int DEBUFF_AMT = -1;
/*     */   private int bloodHitCount;
/*     */   private boolean isFirstMove = true;
/*  51 */   private int moveCount = 0, buffCount = 0;
/*     */   
/*     */   public CorruptHeart() {
/*  54 */     super(NAME, "CorruptHeart", 750, 30.0F, -30.0F, 476.0F, 410.0F, null, -50.0F, 30.0F);
/*     */     
/*  56 */     loadAnimation("images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", 1.0F);
/*  57 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  58 */     e.setTimeScale(1.5F);
/*  59 */     this.state.addListener((AnimationState.AnimationStateListener)this.animListener);
/*  60 */     this.type = EnemyType.BOSS;
/*     */     
/*  62 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  63 */       setHp(800);
/*     */     } else {
/*  65 */       setHp(750);
/*     */     } 
/*     */     
/*  68 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  69 */       this.damage.add(new DamageInfo((AbstractCreature)this, 45));
/*  70 */       this.damage.add(new DamageInfo((AbstractCreature)this, 2));
/*  71 */       this.bloodHitCount = 15;
/*     */     } else {
/*  73 */       this.damage.add(new DamageInfo((AbstractCreature)this, 40));
/*  74 */       this.damage.add(new DamageInfo((AbstractCreature)this, 2));
/*  75 */       this.bloodHitCount = 12;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  81 */     CardCrawlGame.music.unsilenceBGM();
/*  82 */     AbstractDungeon.scene.fadeOutAmbiance();
/*  83 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_ENDING");
/*  84 */     int invincibleAmt = 300;
/*  85 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  86 */       invincibleAmt -= 100;
/*     */     }
/*  88 */     int beatAmount = 1;
/*  89 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  90 */       beatAmount++;
/*     */     }
/*  92 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new InvinciblePower((AbstractCreature)this, invincibleAmt), invincibleAmt));
/*     */     
/*  94 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new BeatOfDeathPower((AbstractCreature)this, beatAmount), beatAmount));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*     */     int additionalAmount, i;
/* 100 */     switch (this.nextMove) {
/*     */       case 3:
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartMegaDebuffEffect()));
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Dazed(), 1, true, false, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 130 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Slimed(), 1, true, false, false, Settings.WIDTH * 0.35F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 139 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Wound(), 1, true, false, false, Settings.WIDTH * 0.5F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 148 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Burn(), 1, true, false, false, Settings.WIDTH * 0.65F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 157 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new VoidCard(), 1, true, false, false, Settings.WIDTH * 0.8F, Settings.HEIGHT / 2.0F));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 168 */         additionalAmount = 0;
/* 169 */         if (hasPower("Strength") && (getPower("Strength")).amount < 0) {
/* 170 */           additionalAmount = -(getPower("Strength")).amount;
/*     */         }
/*     */         
/* 173 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(new Color(0.8F, 0.5F, 1.0F, 1.0F))));
/*     */         
/* 175 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartBuffEffect(this.hb.cX, this.hb.cY)));
/*     */         
/* 177 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, additionalAmount + 2), additionalAmount + 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 184 */         switch (this.buffCount) {
/*     */           case 0:
/* 186 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2), 2));
/*     */             break;
/*     */           
/*     */           case 1:
/* 190 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new BeatOfDeathPower((AbstractCreature)this, 1), 1));
/*     */             break;
/*     */           
/*     */           case 2:
/* 194 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new PainfulStabsPower((AbstractCreature)this)));
/*     */             break;
/*     */           
/*     */           case 3:
/* 198 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 10), 10));
/*     */             break;
/*     */           
/*     */           default:
/* 202 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 50), 50));
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 207 */         this.buffCount++;
/*     */         break;
/*     */       case 1:
/* 210 */         if (Settings.FAST_MODE) {
/* 211 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BloodShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.bloodHitCount), 0.25F));
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 221 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BloodShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.bloodHitCount), 0.6F));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 232 */         for (i = 0; i < this.bloodHitCount; i++) {
/* 233 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 234 */                 .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
/*     */         }
/*     */         break;
/*     */       case 2:
/* 238 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViceCrushEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.5F));
/*     */ 
/*     */ 
/*     */         
/* 242 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 243 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*     */         break;
/*     */     } 
/* 246 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 251 */     if (this.isFirstMove) {
/* 252 */       setMove((byte)3, Intent.STRONG_DEBUFF);
/* 253 */       this.isFirstMove = false;
/*     */       
/*     */       return;
/*     */     } 
/* 257 */     switch (this.moveCount % 3) {
/*     */       case 0:
/* 259 */         if (AbstractDungeon.aiRng.randomBoolean()) {
/* 260 */           setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, this.bloodHitCount, true); break;
/*     */         } 
/* 262 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */         break;
/*     */       
/*     */       case 1:
/* 266 */         if (!lastMove((byte)2)) {
/* 267 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base); break;
/*     */         } 
/* 269 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, this.bloodHitCount, true);
/*     */         break;
/*     */       
/*     */       default:
/* 273 */         setMove((byte)4, Intent.BUFF);
/*     */         break;
/*     */     } 
/*     */     
/* 277 */     this.moveCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 282 */     if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
/* 283 */       super.die();
/* 284 */       this.state.removeListener((AnimationState.AnimationStateListener)this.animListener);
/* 285 */       onBossVictoryLogic();
/* 286 */       onFinalBossVictoryLogic();
/* 287 */       CardCrawlGame.stopClock = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\ending\CorruptHeart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */