/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.CurlUpPower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.WebEffect;
/*     */ 
/*     */ public class LouseDefensive extends AbstractMonster {
/*  26 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("FuzzyLouseDefensive"); public static final String ID = "FuzzyLouseDefensive";
/*  27 */   public static final String NAME = monsterStrings.NAME;
/*  28 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  29 */   public static final String[] DIALOG = monsterStrings.DIALOG; private static final int HP_MIN = 11; private static final int HP_MAX = 17;
/*     */   private static final int A_2_HP_MIN = 12;
/*     */   private static final int A_2_HP_MAX = 18;
/*     */   private static final byte BITE = 3;
/*     */   private static final byte WEAKEN = 4;
/*     */   private boolean isOpen = true;
/*     */   private static final String CLOSED_STATE = "CLOSED";
/*     */   private static final String OPEN_STATE = "OPEN";
/*     */   private static final String REAR_IDLE = "REAR_IDLE";
/*     */   private final int biteDamage;
/*     */   private static final int WEAK_AMT = 2;
/*     */   
/*     */   public LouseDefensive(float x, float y) {
/*  42 */     super(NAME, "FuzzyLouseDefensive", 17, 0.0F, -5.0F, 180.0F, 140.0F, null, x, y);
/*     */     
/*  44 */     loadAnimation("images/monsters/theBottom/louseGreen/skeleton.atlas", "images/monsters/theBottom/louseGreen/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  48 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  49 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  51 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  52 */       setHp(12, 18);
/*     */     } else {
/*  54 */       setHp(11, 17);
/*     */     } 
/*     */     
/*  57 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  58 */       this.biteDamage = AbstractDungeon.monsterHpRng.random(6, 8);
/*     */     } else {
/*  60 */       this.biteDamage = AbstractDungeon.monsterHpRng.random(5, 7);
/*     */     } 
/*     */     
/*  63 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.biteDamage));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  69 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  70 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CurlUpPower((AbstractCreature)this, AbstractDungeon.monsterHpRng
/*  71 */               .random(9, 12))));
/*  72 */     } else if (AbstractDungeon.ascensionLevel >= 7) {
/*  73 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CurlUpPower((AbstractCreature)this, AbstractDungeon.monsterHpRng
/*  74 */               .random(4, 8))));
/*     */     } else {
/*  76 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new CurlUpPower((AbstractCreature)this, AbstractDungeon.monsterHpRng
/*  77 */               .random(3, 7))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*  83 */     switch (this.nextMove) {
/*     */       case 3:
/*  85 */         if (!this.isOpen) {
/*  86 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "OPEN"));
/*  87 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*     */         } 
/*  89 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  91 */               .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*     */         break;
/*     */       case 4:
/*  94 */         if (!this.isOpen) {
/*  95 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REAR"));
/*  96 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(1.2F));
/*  97 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_FAST_3", 
/*  98 */                 MathUtils.random(0.88F, 0.92F), true));
/*  99 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new WebEffect((AbstractCreature)AbstractDungeon.player, this.hb.cX - 70.0F * Settings.scale, this.hb.cY + 10.0F * Settings.scale)));
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 106 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "REAR_IDLE"));
/* 107 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.9F));
/* 108 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_FAST_3", 
/* 109 */                 MathUtils.random(0.88F, 0.92F), true));
/* 110 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new WebEffect((AbstractCreature)AbstractDungeon.player, this.hb.cX - 70.0F * Settings.scale, this.hb.cY + 10.0F * Settings.scale)));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 2, true), 2));
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
/* 132 */     if (stateName.equals("CLOSED")) {
/* 133 */       this.state.setAnimation(0, "transitiontoclosed", false);
/* 134 */       this.state.addAnimation(0, "idle closed", true, 0.0F);
/* 135 */       this.isOpen = false;
/* 136 */     } else if (stateName.equals("OPEN")) {
/* 137 */       this.state.setAnimation(0, "transitiontoopened", false);
/* 138 */       this.state.addAnimation(0, "idle", true, 0.0F);
/* 139 */       this.isOpen = true;
/* 140 */     } else if (stateName.equals("REAR_IDLE")) {
/* 141 */       this.state.setAnimation(0, "rear", false);
/* 142 */       this.state.addAnimation(0, "idle", true, 0.0F);
/* 143 */       this.isOpen = true;
/*     */     } else {
/* 145 */       this.state.setAnimation(0, "transitiontoopened", false);
/* 146 */       this.state.addAnimation(0, "rear", false, 0.0F);
/* 147 */       this.state.addAnimation(0, "idle", true, 0.0F);
/* 148 */       this.isOpen = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 154 */     if (AbstractDungeon.ascensionLevel >= 17) {
/* 155 */       if (num < 25) {
/* 156 */         if (lastMove((byte)4)) {
/* 157 */           setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */         } else {
/* 159 */           setMove(MOVES[0], (byte)4, Intent.DEBUFF);
/*     */         }
/*     */       
/* 162 */       } else if (lastTwoMoves((byte)3)) {
/* 163 */         setMove(MOVES[0], (byte)4, Intent.DEBUFF);
/*     */       } else {
/* 165 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       }
/*     */     
/*     */     }
/* 169 */     else if (num < 25) {
/* 170 */       if (lastTwoMoves((byte)4)) {
/* 171 */         setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       } else {
/* 173 */         setMove(MOVES[0], (byte)4, Intent.DEBUFF);
/*     */       }
/*     */     
/* 176 */     } else if (lastTwoMoves((byte)3)) {
/* 177 */       setMove(MOVES[0], (byte)4, Intent.DEBUFF);
/*     */     } else {
/* 179 */       setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\LouseDefensive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */