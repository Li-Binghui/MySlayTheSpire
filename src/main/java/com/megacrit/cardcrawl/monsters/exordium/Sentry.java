/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
/*     */ 
/*     */ public class Sentry extends AbstractMonster {
/*  29 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Sentry"); public static final String ID = "Sentry";
/*  30 */   public static final String NAME = monsterStrings.NAME;
/*  31 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  32 */   public static final String[] DIALOG = monsterStrings.DIALOG; public static final String ENC_NAME = "Sentries"; private static final int HP_MIN = 38; private static final int HP_MAX = 42;
/*     */   private static final int A_2_HP_MIN = 39;
/*     */   private static final int A_2_HP_MAX = 45;
/*     */   private static final byte BOLT = 3;
/*     */   private static final byte BEAM = 4;
/*     */   private int beamDmg;
/*     */   private int dazedAmt;
/*     */   private static final int DAZED_AMT = 2;
/*     */   private static final int A_18_DAZED_AMT = 3;
/*     */   private boolean firstMove = true;
/*     */   
/*     */   public Sentry(float x, float y) {
/*  44 */     super(NAME, "Sentry", 42, 0.0F, -5.0F, 180.0F, 310.0F, null, x, y);
/*  45 */     this.type = EnemyType.ELITE;
/*     */     
/*  47 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  48 */       setHp(39, 45);
/*     */     } else {
/*  50 */       setHp(38, 42);
/*     */     } 
/*     */     
/*  53 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  54 */       this.beamDmg = 10;
/*     */     } else {
/*  56 */       this.beamDmg = 9;
/*     */     } 
/*     */     
/*  59 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  60 */       this.dazedAmt = 3;
/*     */     } else {
/*  62 */       this.dazedAmt = 2;
/*     */     } 
/*     */     
/*  65 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.beamDmg));
/*     */     
/*  67 */     loadAnimation("images/monsters/theBottom/sentry/skeleton.atlas", "images/monsters/theBottom/sentry/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  71 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  72 */     e.setTimeScale(2.0F);
/*  73 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  74 */     this.stateData.setMix("idle", "attack", 0.1F);
/*  75 */     this.stateData.setMix("idle", "spaz1", 0.1F);
/*  76 */     this.stateData.setMix("idle", "hit", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  81 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  86 */     switch (this.nextMove) {
/*     */       case 3:
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("THUNDERCLAP"));
/*  89 */         if (!Settings.FAST_MODE) {
/*  90 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.ROYAL, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.5F));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  95 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new FastShakeAction((AbstractCreature)AbstractDungeon.player, 0.6F, 0.2F));
/*     */         } else {
/*  97 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.ROYAL, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.1F));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 102 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new FastShakeAction((AbstractCreature)AbstractDungeon.player, 0.6F, 0.15F));
/*     */         } 
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Dazed(), this.dazedAmt));
/*     */         break;
/*     */       case 4:
/* 107 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 108 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.SKY)));
/* 110 */         if (Settings.FAST_MODE) {
/* 111 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.1F));
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */           
/* 120 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.3F));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 129 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 130 */               .get(0), AbstractGameAction.AttackEffect.NONE, Settings.FAST_MODE));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 135 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 140 */     super.damage(info);
/* 141 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 142 */       this.state.setAnimation(0, "hit", false);
/* 143 */       this.state.addAnimation(0, "idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 149 */     switch (stateName) {
/*     */       case "ATTACK":
/* 151 */         this.state.setAnimation(0, "attack", false);
/* 152 */         this.state.addAnimation(0, "idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 159 */     if (this.firstMove) {
/* 160 */       if ((AbstractDungeon.getMonsters()).monsters.lastIndexOf(this) % 2 == 0) {
/* 161 */         setMove((byte)3, Intent.DEBUFF);
/*     */       } else {
/* 163 */         setMove((byte)4, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } 
/* 165 */       this.firstMove = false;
/*     */       
/*     */       return;
/*     */     } 
/* 169 */     if (lastMove((byte)4)) {
/* 170 */       setMove((byte)3, Intent.DEBUFF);
/*     */     } else {
/* 172 */       setMove((byte)4, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\Sentry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */