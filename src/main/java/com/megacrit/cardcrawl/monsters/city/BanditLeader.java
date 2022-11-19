/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SetMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ 
/*     */ public class BanditLeader extends AbstractMonster {
/*     */   public static final String ID = "BanditLeader";
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("BanditLeader");
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final int HP_MIN = 35;
/*     */   
/*     */   public static final int HP_MAX = 39;
/*     */   
/*     */   public static final int A_2_HP_MIN = 37;
/*     */   public static final int A_2_HP_MAX = 41;
/*     */   private static final int SLASH_DAMAGE = 15;
/*     */   private static final int AGONIZE_DAMAGE = 10;
/*     */   private static final int A_2_SLASH_DAMAGE = 17;
/*     */   private static final int A_2_AGONIZE_DAMAGE = 12;
/*     */   
/*     */   public BanditLeader(float x, float y) {
/*  39 */     super(NAME, "BanditLeader", 39, -10.0F, -7.0F, 180.0F, 285.0F, null, x, y);
/*  40 */     this.dialogX = 0.0F * Settings.scale;
/*  41 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  43 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  44 */       this.weakAmount = 3;
/*     */     } else {
/*  46 */       this.weakAmount = 2;
/*     */     } 
/*     */     
/*  49 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  50 */       setHp(37, 41);
/*     */     } else {
/*  52 */       setHp(35, 39);
/*     */     } 
/*     */     
/*  55 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  56 */       this.slashDmg = 17;
/*  57 */       this.agonizeDmg = 12;
/*     */     } else {
/*  59 */       this.slashDmg = 15;
/*  60 */       this.agonizeDmg = 10;
/*     */     } 
/*     */     
/*  63 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.slashDmg));
/*  64 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.agonizeDmg));
/*     */     
/*  66 */     loadAnimation("images/monsters/theCity/romeo/skeleton.atlas", "images/monsters/theCity/romeo/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  70 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  71 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  72 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  73 */     this.state.setTimeScale(0.8F);
/*     */   }
/*     */   private static final int WEAK_AMT = 2; private static final int A_17_WEAK = 3; private int slashDmg; private int agonizeDmg; private int weakAmount; private static final byte CROSS_SLASH = 1; private static final byte MOCK = 2; private static final byte AGONIZING_SLASH = 3;
/*     */   
/*     */   public void deathReact() {
/*  78 */     if (!isDeadOrEscaped()) {
/*  79 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2]));
/*     */     }
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     boolean bearLives;
/*  85 */     switch (this.nextMove) {
/*     */       case 2:
/*  87 */         bearLives = true;
/*  88 */         for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/*  89 */           if (m instanceof BanditBear && m.isDying) {
/*  90 */             bearLives = false;
/*     */             break;
/*     */           } 
/*     */         } 
/*  94 */         if (bearLives) {
/*  95 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0]));
/*     */         } else {
/*  97 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[1]));
/*     */         } 
/*  99 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage
/* 100 */               .get(1)).base));
/*     */         break;
/*     */       case 3:
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "STAB"));
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 105 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 106 */               .get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 107 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, this.weakAmount, true), this.weakAmount));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/* 114 */               .get(0)).base));
/*     */         break;
/*     */       case 1:
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "STAB"));
/* 118 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/* 119 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 120 */               .get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */         
/* 122 */         if (AbstractDungeon.ascensionLevel >= 17 && !lastTwoMoves((byte)1)) {
/* 123 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/* 124 */                 .get(0)).base)); break;
/*     */         } 
/* 126 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage
/* 127 */               .get(1)).base));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 137 */     switch (key) {
/*     */       case "STAB":
/* 139 */         this.state.setAnimation(0, "Attack", false);
/* 140 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 149 */     super.damage(info);
/* 150 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 151 */       this.state.setAnimation(0, "Hit", false);
/* 152 */       this.state.setTimeScale(0.8F);
/* 153 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 159 */     setMove((byte)2, Intent.UNKNOWN);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\BanditLeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */