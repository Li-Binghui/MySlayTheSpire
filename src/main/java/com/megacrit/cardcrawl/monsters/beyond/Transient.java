/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.FadingPower;
/*     */ import com.megacrit.cardcrawl.powers.ShiftingPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ public class Transient extends AbstractMonster {
/*  23 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Transient"); public static final String ID = "Transient";
/*  24 */   public static final String NAME = monsterStrings.NAME;
/*  25 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  26 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP = 999;
/*  29 */   private int count = 0;
/*     */   private static final int DEATH_DMG = 30;
/*     */   private static final int INCREMENT_DMG = 10;
/*     */   private static final int A_2_DEATH_DMG = 40;
/*     */   private int startingDeathDmg;
/*     */   private static final byte ATTACK = 1;
/*     */   
/*     */   public Transient() {
/*  37 */     super(NAME, "Transient", 999, 0.0F, -15.0F, 370.0F, 340.0F, null, 0.0F, 20.0F);
/*  38 */     loadAnimation("images/monsters/theForest/transient/skeleton.atlas", "images/monsters/theForest/transient/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  44 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  46 */     this.gold = 1;
/*  47 */     this.dialogX = -100.0F * Settings.scale;
/*  48 */     this.dialogY -= 20.0F * Settings.scale;
/*     */     
/*  50 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  51 */       this.startingDeathDmg = 40;
/*     */     } else {
/*  53 */       this.startingDeathDmg = 30;
/*     */     } 
/*     */     
/*  56 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg));
/*  57 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 10));
/*  58 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 20));
/*  59 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 30));
/*  60 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 40));
/*  61 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 50));
/*  62 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.startingDeathDmg + 60));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  67 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  68 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new FadingPower((AbstractCreature)this, 6)));
/*     */     } else {
/*  70 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new FadingPower((AbstractCreature)this, 5)));
/*     */     } 
/*  72 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ShiftingPower((AbstractCreature)this)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  77 */     switch (this.nextMove) {
/*     */       case 1:
/*  79 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  81 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  82 */               .get(this.count), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*  83 */         this.count++;
/*  84 */         setMove((byte)1, Intent.ATTACK, this.startingDeathDmg + this.count * 10);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/*  91 */     super.damage(info);
/*  92 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/*  93 */       this.state.setAnimation(0, "Hurt", false);
/*  94 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 100 */     switch (key) {
/*     */       case "ATTACK":
/* 102 */         this.state.setAnimation(0, "Attack", false);
/* 103 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 112 */     super.die();
/* 113 */     UnlockTracker.unlockAchievement("TRANSIENT");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 118 */     setMove((byte)1, Intent.ATTACK, this.startingDeathDmg + this.count * 10);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Transient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */