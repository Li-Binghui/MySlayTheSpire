/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
/*     */ 
/*     */ public class Snecko extends AbstractMonster {
/*  30 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Snecko"); public static final String ID = "Snecko";
/*  31 */   public static final String NAME = monsterStrings.NAME;
/*  32 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  33 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final byte GLARE = 1; private static final byte BITE = 2; private static final byte TAIL = 3;
/*     */   private static final int BITE_DAMAGE = 15;
/*     */   private static final int TAIL_DAMAGE = 8;
/*     */   private static final int A_2_BITE_DAMAGE = 18;
/*     */   private static final int A_2_TAIL_DAMAGE = 10;
/*     */   private int biteDmg;
/*     */   private int tailDmg;
/*     */   private static final int VULNERABLE_AMT = 2;
/*     */   private static final int HP_MIN = 114;
/*     */   private static final int HP_MAX = 120;
/*     */   private static final int A_2_HP_MIN = 120;
/*     */   private static final int A_2_HP_MAX = 125;
/*     */   private boolean firstTurn = true;
/*     */   
/*     */   public Snecko() {
/*  48 */     this(0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public Snecko(float x, float y) {
/*  52 */     super(NAME, "Snecko", 120, -30.0F, -20.0F, 310.0F, 305.0F, null, x, y);
/*  53 */     loadAnimation("images/monsters/theCity/reptile/skeleton.atlas", "images/monsters/theCity/reptile/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  57 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  58 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  59 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  60 */     e.setTimeScale(0.8F);
/*     */     
/*  62 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  63 */       setHp(120, 125);
/*     */     } else {
/*  65 */       setHp(114, 120);
/*     */     } 
/*     */     
/*  68 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  69 */       this.biteDmg = 18;
/*  70 */       this.tailDmg = 10;
/*     */     } else {
/*  72 */       this.biteDmg = 15;
/*  73 */       this.tailDmg = 8;
/*     */     } 
/*     */     
/*  76 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.biteDmg));
/*  77 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.tailDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  82 */     switch (this.nextMove) {
/*     */       case 1:
/*  84 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  85 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_SNECKO_GLARE"));
/*  86 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new IntimidateEffect(this.hb.cX, this.hb.cY), 0.5F));
/*     */         
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new FastShakeAction((AbstractCreature)AbstractDungeon.player, 1.0F, 1.0F));
/*  89 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new ConfusionPower((AbstractCreature)AbstractDungeon.player)));
/*     */         break;
/*     */       
/*     */       case 2:
/*  93 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK_2"));
/*  94 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX + 
/*     */ 
/*     */                 
/*  98 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, AbstractDungeon.player.hb.cY + 
/*  99 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE
/* 100 */                 .cpy()), 0.3F));
/*     */         
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 103 */               .get(0), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */       case 3:
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 107 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 108 */               .get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 109 */         if (AbstractDungeon.ascensionLevel >= 17) {
/* 110 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 132 */     switch (stateName) {
/*     */       case "ATTACK":
/* 134 */         this.state.setAnimation(0, "Attack", false);
/* 135 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "ATTACK_2":
/* 138 */         this.state.setAnimation(0, "Attack_2", false);
/* 139 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 146 */     super.damage(info);
/* 147 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 148 */       this.state.setAnimation(0, "Hit", false);
/* 149 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 156 */     if (this.firstTurn) {
/* 157 */       this.firstTurn = false;
/* 158 */       setMove(MOVES[0], (byte)1, Intent.STRONG_DEBUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 163 */     if (num < 40) {
/* 164 */       setMove(MOVES[1], (byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 169 */     if (lastTwoMoves((byte)2)) {
/* 170 */       setMove(MOVES[1], (byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */     } else {
/* 172 */       setMove(MOVES[2], (byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 178 */     super.die();
/* 179 */     CardCrawlGame.sound.play("SNECKO_DEATH");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Snecko.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */