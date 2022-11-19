/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ThornsPower;
/*     */ 
/*     */ public class Spiker extends AbstractMonster {
/*  19 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Spiker"); public static final String ID = "Spiker";
/*  20 */   public static final String NAME = monsterStrings.NAME;
/*  21 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  22 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final String ENCOUNTER_NAME = "Ancient Shapes";
/*     */   
/*     */   private static final int HP_MIN = 42;
/*     */   private static final int HP_MAX = 56;
/*     */   private static final int A_2_HP_MIN = 44;
/*     */   private static final int A_2_HP_MAX = 60;
/*     */   private static final float HB_X = -8.0F;
/*     */   private static final float HB_Y = -10.0F;
/*     */   private static final float HB_W = 150.0F;
/*     */   private static final float HB_H = 150.0F;
/*     */   private static final int STARTING_THORNS = 3;
/*     */   private static final int A_2_STARTING_THORNS = 4;
/*     */   private int startingThorns;
/*     */   private static final byte ATTACK = 1;
/*     */   private static final int ATTACK_DMG = 7;
/*     */   private static final int A_2_ATTACK_DMG = 9;
/*     */   private int attackDmg;
/*     */   private static final byte BUFF_THORNS = 2;
/*     */   private static final int BUFF_AMT = 2;
/*  43 */   private int thornsCount = 0;
/*     */   
/*     */   public Spiker(float x, float y) {
/*  46 */     super(NAME, "Spiker", 56, -8.0F, -10.0F, 150.0F, 150.0F, null, x, y + 10.0F);
/*     */     
/*  48 */     loadAnimation("images/monsters/theForest/spiker/skeleton.atlas", "images/monsters/theForest/spiker/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  52 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  53 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  55 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  56 */       setHp(44, 60);
/*     */     } else {
/*  58 */       setHp(42, 56);
/*     */     } 
/*     */     
/*  61 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  62 */       this.startingThorns = 4;
/*  63 */       this.attackDmg = 9;
/*     */     } else {
/*  65 */       this.startingThorns = 3;
/*  66 */       this.attackDmg = 7;
/*     */     } 
/*     */     
/*  69 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.attackDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  74 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  75 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ThornsPower((AbstractCreature)this, this.startingThorns + 3)));
/*     */     } else {
/*     */       
/*  78 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ThornsPower((AbstractCreature)this, this.startingThorns)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  85 */     switch (this.nextMove) {
/*     */       case 1:
/*  87 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  89 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*     */         break;
/*     */       case 2:
/*  92 */         this.thornsCount++;
/*  93 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ThornsPower((AbstractCreature)this, 2), 2));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  99 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 104 */     if (this.thornsCount > 5) {
/* 105 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     if (num < 50 && !lastMove((byte)1)) {
/* 110 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 114 */     setMove((byte)2, Intent.BUFF);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Spiker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */