/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.SetAnimationAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.FlightPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ 
/*     */ public class Byrd extends AbstractMonster {
/*     */   public static final String ID = "Byrd";
/*  27 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Byrd");
/*  28 */   public static final String NAME = monsterStrings.NAME;
/*  29 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  30 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final String THREE_BYRDS = "3_Byrds";
/*     */   
/*     */   public static final String FOUR_BYRDS = "4_Byrds";
/*     */   
/*     */   public static final String IMAGE = "images/monsters/theCity/byrdFlying.png";
/*     */   private static final int HP_MIN = 25;
/*     */   private static final int HP_MAX = 31;
/*     */   private static final int A_2_HP_MIN = 26;
/*     */   private static final int A_2_HP_MAX = 33;
/*     */   private static final float HB_X_F = 0.0F;
/*     */   private static final float HB_X_G = 10.0F;
/*     */   private static final float HB_Y_F = 50.0F;
/*     */   private static final float HB_Y_G = -50.0F;
/*     */   private static final float HB_W = 240.0F;
/*     */   private static final float HB_H = 180.0F;
/*     */   private static final int PECK_DMG = 1;
/*     */   private static final int PECK_COUNT = 5;
/*     */   private static final int SWOOP_DMG = 12;
/*     */   private static final int A_2_PECK_COUNT = 6;
/*     */   
/*     */   public Byrd(float x, float y) {
/*  53 */     super(NAME, "Byrd", 31, 0.0F, 50.0F, 240.0F, 180.0F, null, x, y);
/*     */     
/*  55 */     if (AbstractDungeon.ascensionLevel >= 7) {
/*  56 */       setHp(26, 33);
/*     */     } else {
/*  58 */       setHp(25, 31);
/*     */     } 
/*     */     
/*  61 */     if (AbstractDungeon.ascensionLevel >= 17) {
/*  62 */       this.flightAmt = 4;
/*     */     } else {
/*  64 */       this.flightAmt = 3;
/*     */     } 
/*     */     
/*  67 */     if (AbstractDungeon.ascensionLevel >= 2) {
/*  68 */       this.peckDmg = 1;
/*  69 */       this.peckCount = 6;
/*  70 */       this.swoopDmg = 14;
/*     */     } else {
/*  72 */       this.peckDmg = 1;
/*  73 */       this.peckCount = 5;
/*  74 */       this.swoopDmg = 12;
/*     */     } 
/*     */     
/*  77 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.peckDmg));
/*  78 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.swoopDmg));
/*  79 */     this.damage.add(new DamageInfo((AbstractCreature)this, 3));
/*     */     
/*  81 */     loadAnimation("images/monsters/theCity/byrd/flying.atlas", "images/monsters/theCity/byrd/flying.json", 1.0F);
/*  82 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle_flap", true);
/*  83 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */   private static final int A_2_SWOOP_DMG = 14; private int peckDmg; private int peckCount; private int swoopDmg; private int flightAmt; private static final int HEADBUTT_DMG = 3; private static final int CAW_STR = 1; private static final byte PECK = 1; private static final byte GO_AIRBORNE = 2; private static final byte SWOOP = 3; private static final byte STUNNED = 4; private static final byte HEADBUTT = 5; private static final byte CAW = 6; private boolean firstMove = true; private boolean isFlying = true; public static final String FLY_STATE = "FLYING"; public static final String GROUND_STATE = "GROUNDED";
/*     */   
/*     */   public void usePreBattleAction() {
/*  88 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new FlightPower((AbstractCreature)this, this.flightAmt)));
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/*  93 */     switch (this.nextMove) {
/*     */       case 1:
/*  95 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/*  96 */         for (i = 0; i < this.peckCount; i++) {
/*  97 */           playRandomBirdSFx();
/*  98 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  99 */                 .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 103 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 105 */               .get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 106 */         setMove((byte)2, Intent.UNKNOWN);
/*     */         return;
/*     */       case 2:
/* 109 */         this.isFlying = true;
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "FLYING"));
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new FlightPower((AbstractCreature)this, this.flightAmt)));
/*     */         break;
/*     */       
/*     */       case 6:
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("BYRD_DEATH"));
/* 116 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0], 1.2F, 1.2F));
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, 1), 1));
/*     */         break;
/*     */       
/*     */       case 3:
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 122 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 123 */               .get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*     */         break;
/*     */       case 4:
/* 126 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SetAnimationAction((AbstractCreature)this, "head_lift"));
/* 127 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, TextAboveCreatureAction.TextType.STUNNED));
/*     */         break;
/*     */     } 
/* 130 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private void playRandomBirdSFx() {
/* 134 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_BYRD_ATTACK_" + MathUtils.random(0, 5)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/*     */     AnimationState.TrackEntry e;
/* 140 */     switch (stateName) {
/*     */       case "FLYING":
/* 142 */         loadAnimation("images/monsters/theCity/byrd/flying.atlas", "images/monsters/theCity/byrd/flying.json", 1.0F);
/*     */ 
/*     */ 
/*     */         
/* 146 */         e = this.state.setAnimation(0, "idle_flap", true);
/* 147 */         e.setTime(e.getEndTime() * MathUtils.random());
/* 148 */         updateHitbox(0.0F, 50.0F, 240.0F, 180.0F);
/*     */         break;
/*     */       case "GROUNDED":
/* 151 */         setMove((byte)4, Intent.STUN);
/* 152 */         createIntent();
/* 153 */         this.isFlying = false;
/* 154 */         loadAnimation("images/monsters/theCity/byrd/grounded.atlas", "images/monsters/theCity/byrd/grounded.json", 1.0F);
/*     */ 
/*     */ 
/*     */         
/* 158 */         e = this.state.setAnimation(0, "idle", true);
/* 159 */         e.setTime(e.getEndTime() * MathUtils.random());
/* 160 */         updateHitbox(10.0F, -50.0F, 240.0F, 180.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 172 */     if (this.firstMove) {
/* 173 */       this.firstMove = false;
/* 174 */       if (AbstractDungeon.aiRng.randomBoolean(0.375F)) {
/* 175 */         setMove((byte)6, Intent.BUFF);
/*     */       } else {
/* 177 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.peckCount, true);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 182 */     if (this.isFlying) {
/*     */ 
/*     */       
/* 185 */       if (num < 50) {
/*     */         
/* 187 */         if (lastTwoMoves((byte)1)) {
/* 188 */           if (AbstractDungeon.aiRng.randomBoolean(0.4F)) {
/* 189 */             setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */           } else {
/* 191 */             setMove((byte)6, Intent.BUFF);
/*     */           } 
/*     */         } else {
/* 194 */           setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.peckCount, true);
/*     */         }
/*     */       
/*     */       }
/* 198 */       else if (num < 70) {
/*     */         
/* 200 */         if (lastMove((byte)3)) {
/* 201 */           if (AbstractDungeon.aiRng.randomBoolean(0.375F)) {
/* 202 */             setMove((byte)6, Intent.BUFF);
/*     */           } else {
/* 204 */             setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.peckCount, true);
/*     */           } 
/*     */         } else {
/* 207 */           setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 212 */       else if (lastMove((byte)6)) {
/* 213 */         if (AbstractDungeon.aiRng.randomBoolean(0.2857F)) {
/* 214 */           setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */         } else {
/* 216 */           setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.peckCount, true);
/*     */         } 
/*     */       } else {
/* 219 */         setMove((byte)6, Intent.BUFF);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 224 */       setMove((byte)5, Intent.ATTACK, ((DamageInfo)this.damage.get(2)).base);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 230 */     super.die();
/* 231 */     CardCrawlGame.sound.play("BYRD_DEATH");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\Byrd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */