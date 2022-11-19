/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.common.*;
/*     */
/*     */
/*     */
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*     */ 
/*     */ public class BanditBear extends AbstractMonster {
/*     */   public static final String ID = "BanditBear";
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("BanditBear");
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final int HP_MIN = 38;
/*     */   
/*     */   public static final int HP_MAX = 42;
/*     */   
/*     */   public static final int A_2_HP_MIN = 40;
/*     */   
/*     */   public static final int A_2_HP_MAX = 44;
/*     */   private static final int MAUL_DMG = 18;
/*     */   private static final int A_2_MAUL_DMG = 20;
/*     */   private static final int LUNGE_DMG = 9;
/*     */   private static final int A_2_LUNGE_DMG = 10;
/*     */   
/*     */   public BanditBear(float x, float y) {
/*  40 */     super(NAME, "BanditBear", 42, -5.0F, -4.0F, 180.0F, 280.0F, null, x, y);
/*     */     
/*  42 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  43 */       setHp(40, 44);
/*     */     } else {
/*  45 */       setHp(38, 42);
/*     */     } 
/*     */     
/*  48 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  49 */       this.maulDmg = 20;
/*  50 */       this.lungeDmg = 10;
/*     */     } else {
/*  52 */       this.maulDmg = 18;
/*  53 */       this.lungeDmg = 9;
/*     */     } 
/*     */     
/*  56 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  57 */       this.con_reduction = -4;
/*     */     } else {
/*  59 */       this.con_reduction = -2;
/*     */     } 
/*     */     
/*  62 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.maulDmg));
/*  63 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.lungeDmg));
/*     */     
/*  65 */     loadAnimation("images/monsters/theCity/bear/skeleton.atlas", "images/monsters/theCity/bear/skeleton.json", 1.0F);
/*  66 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  67 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  68 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  69 */     this.state.setTimeScale(1.0F);
/*     */   }
/*     */   private static final int LUNGE_DEFENSE = 9; private static final int CON_AMT = -2; private static final int A_17_CON_AMT = -4; private int maulDmg; private int lungeDmg; private int con_reduction; private static final byte MAUL = 1; private static final byte BEAR_HUG = 2; private static final byte LUNGE = 3;
/*     */   
/*     */   public void takeTurn() {
/*  74 */     switch (this.nextMove) {
/*     */       case 2:
/*  76 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  77 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new DexterityPower((AbstractCreature)AbstractDungeon.player, this.con_reduction), this.con_reduction));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  83 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage
/*  84 */               .get(1)).base));
/*     */         break;
/*     */       case 1:
/*  87 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "MAUL"));
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/*  89 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  90 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  91 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage
/*  92 */               .get(1)).base));
/*     */         break;
/*     */       case 3:
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  97 */               .get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*  98 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 9));
/*  99 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetMoveAction(this, (byte)1, Intent.ATTACK, ((DamageInfo)this.damage
/* 100 */               .get(0)).base));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 109 */     switch (key) {
/*     */       case "MAUL":
/* 111 */         this.state.setAnimation(0, "Attack", false);
/* 112 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 121 */     super.damage(info);
/* 122 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 123 */       this.state.setAnimation(0, "Hit", false);
/* 124 */       this.state.setTimeScale(1.0F);
/* 125 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 131 */     super.die();
/* 132 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 133 */       if (!m.isDead && !m.isDying) {
/* 134 */         m.deathReact();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 141 */     setMove((byte)2, Intent.STRONG_DEBUFF);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\BanditBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */