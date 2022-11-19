/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.PainfulStabsPower;
/*     */ 
/*     */ public class BookOfStabbing extends AbstractMonster {
/*     */   public static final String ID = "BookOfStabbing";
/*  22 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("BookOfStabbing");
/*  23 */   public static final String NAME = monsterStrings.NAME;
/*  24 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  25 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP_MIN = 160;
/*     */   
/*     */   private static final int HP_MAX = 164;
/*     */   
/*     */   private static final int A_2_HP_MIN = 168;
/*     */   private static final int A_2_HP_MAX = 172;
/*     */   private static final int STAB_DAMAGE = 6;
/*     */   private static final int BIG_STAB_DAMAGE = 21;
/*  35 */   private int stabCount = 1; private static final int A_2_STAB_DAMAGE = 7; private static final int A_2_BIG_STAB_DAMAGE = 24; private int stabDmg; private int bigStabDmg; private static final byte STAB = 1; private static final byte BIG_STAB = 2;
/*     */   
/*     */   public BookOfStabbing() {
/*  38 */     super(NAME, "BookOfStabbing", 164, 0.0F, -10.0F, 320.0F, 420.0F, null, 0.0F, 5.0F);
/*  39 */     loadAnimation("images/monsters/theCity/stabBook/skeleton.atlas", "images/monsters/theCity/stabBook/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  43 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  44 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  45 */     this.stateData.setMix("Hit", "Idle", 0.2F);
/*  46 */     e.setTimeScale(0.8F);
/*     */     
/*  48 */     this.type = EnemyType.ELITE;
/*  49 */     this.dialogX = -70.0F * Settings.scale;
/*  50 */     this.dialogY = 50.0F * Settings.scale;
/*     */     
/*  52 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  53 */       setHp(168, 172);
/*     */     } else {
/*  55 */       setHp(160, 164);
/*     */     } 
/*     */     
/*  58 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  59 */       this.stabDmg = 7;
/*  60 */       this.bigStabDmg = 24;
/*     */     } else {
/*  62 */       this.stabDmg = 6;
/*  63 */       this.bigStabDmg = 21;
/*     */     } 
/*     */     
/*  66 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.stabDmg));
/*  67 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.bigStabDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  72 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new PainfulStabsPower((AbstractCreature)this)));
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/*  77 */     switch (this.nextMove) {
/*     */       case 1:
/*  79 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  80 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*     */         
/*  82 */         for (i = 0; i < this.stabCount; i++) {
/*  83 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_BOOK_STAB_" +
/*  84 */                 MathUtils.random(0, 3)));
/*  85 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*     */ 
/*     */                 
/*  88 */                 .get(0), AbstractGameAction.AttackEffect.SLASH_VERTICAL, false, true));
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK_2"));
/*  96 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*  97 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  98 */               .get(1), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 103 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 108 */     switch (stateName) {
/*     */       case "ATTACK":
/* 110 */         this.state.setAnimation(0, "Attack", false);
/* 111 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "ATTACK_2":
/* 114 */         this.state.setAnimation(0, "Attack_2", false);
/* 115 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 122 */     super.damage(info);
/* 123 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 124 */       this.state.setAnimation(0, "Hit", false);
/* 125 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 131 */     if (num < 15) {
/* 132 */       if (lastMove((byte)2)) {
/* 133 */         this.stabCount++;
/* 134 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.stabCount, true);
/*     */       } else {
/* 136 */         setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/* 137 */         if (AbstractDungeon.ascensionLevel >= 18) {
/* 138 */           this.stabCount++;
/*     */         }
/*     */       }
/*     */     
/* 142 */     } else if (lastTwoMoves((byte)1)) {
/* 143 */       setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/* 144 */       if (AbstractDungeon.ascensionLevel >= 18) {
/* 145 */         this.stabCount++;
/*     */       }
/*     */     } else {
/* 148 */       this.stabCount++;
/* 149 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.stabCount, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 156 */     super.die();
/* 157 */     CardCrawlGame.sound.play("STAB_BOOK_DEATH");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\BookOfStabbing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */