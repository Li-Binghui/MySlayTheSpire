/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ 
/*     */ public class SlaverBlue extends AbstractMonster {
/*  20 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("SlaverBlue"); public static final String ID = "SlaverBlue";
/*  21 */   public static final String NAME = monsterStrings.NAME;
/*  22 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  23 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   private static final int HP_MIN = 46;
/*     */   
/*     */   private static final int HP_MAX = 50;
/*     */   private static final int A_2_HP_MIN = 48;
/*     */   private static final int A_2_HP_MAX = 52;
/*     */   private static final int STAB_DMG = 12;
/*     */   private static final int A_2_STAB_DMG = 13;
/*     */   private static final int RAKE_DMG = 7;
/*     */   private static final int A_2_RAKE_DMG = 8;
/*  34 */   private int stabDmg = 12; private int rakeDmg = 7; private int weakAmt = 1; private static final byte STAB = 1;
/*     */   private static final byte RAKE = 4;
/*     */   
/*     */   public SlaverBlue(float x, float y) {
/*  38 */     super(NAME, "SlaverBlue", 50, 0.0F, 0.0F, 170.0F, 230.0F, null, x, y);
/*     */     
/*  40 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  41 */       setHp(48, 52);
/*     */     } else {
/*  43 */       setHp(46, 50);
/*     */     } 
/*     */     
/*  46 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  47 */       this.stabDmg = 13;
/*  48 */       this.rakeDmg = 8;
/*     */     } else {
/*  50 */       this.stabDmg = 12;
/*  51 */       this.rakeDmg = 7;
/*     */     } 
/*     */     
/*  54 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.stabDmg));
/*  55 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.rakeDmg));
/*     */     
/*  57 */     loadAnimation("images/monsters/theBottom/blueSlaver/skeleton.atlas", "images/monsters/theBottom/blueSlaver/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  61 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  62 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  67 */     switch (this.nextMove) {
/*     */       case 1:
/*  69 */         playSfx();
/*  70 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  71 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  72 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*     */         break;
/*     */       case 4:
/*  75 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  76 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  77 */               .get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */         
/*  79 */         if (AbstractDungeon.ascensionLevel >= 17) {
/*  80 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, this.weakAmt + 1, true), this.weakAmt + 1));
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */         
/*  87 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, this.weakAmt, true), this.weakAmt));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playSfx() {
/* 102 */     int roll = MathUtils.random(1);
/* 103 */     if (roll == 0) {
/* 104 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLAVERBLUE_1A"));
/*     */     } else {
/* 106 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_SLAVERBLUE_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 111 */     int roll = MathUtils.random(1);
/* 112 */     if (roll == 0) {
/* 113 */       CardCrawlGame.sound.play("VO_SLAVERBLUE_2A");
/*     */     } else {
/* 115 */       CardCrawlGame.sound.play("VO_SLAVERBLUE_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 123 */     if (num >= 40 && !lastTwoMoves((byte)1)) {
/* 124 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 128 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*     */       
/* 130 */       if (!lastMove((byte)4)) {
/* 131 */         setMove(MOVES[0], (byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */         return;
/*     */       } 
/* 134 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 139 */     if (!lastTwoMoves((byte)4)) {
/* 140 */       setMove(MOVES[0], (byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */       return;
/*     */     } 
/* 143 */     setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 151 */     super.die();
/* 152 */     playDeathSfx();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\SlaverBlue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */