/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.Bone;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.cards.status.Burn;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.IntangiblePower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.NemesisFireParticle;
/*     */ import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
/*     */ 
/*     */ public class Nemesis extends AbstractMonster {
/*  30 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Nemesis"); public static final String ID = "Nemesis";
/*  31 */   public static final String NAME = monsterStrings.NAME;
/*     */   private static final int HP = 185;
/*     */   private static final int A_2_HP = 200;
/*     */   private static final int SCYTHE_COOLDOWN_TURNS = 2;
/*     */   private static final float HB_X = 5.0F;
/*     */   private static final float HB_Y = -10.0F;
/*     */   private static final int SCYTHE_DMG = 45;
/*     */   private static final int FIRE_DMG = 6;
/*     */   private static final int FIRE_TIMES = 3;
/*     */   private static final int A_2_FIRE_DMG = 7;
/*     */   private static final int BURN_AMT = 3;
/*     */   private int fireDmg;
/*  43 */   private int scytheCooldown = 0; private static final byte TRI_ATTACK = 2;
/*     */   private static final byte SCYTHE = 3;
/*     */   private static final byte TRI_BURN = 4;
/*  46 */   private float fireTimer = 0.0F; private static final float FIRE_TIME = 0.05F; private Bone eye1;
/*     */   private Bone eye2;
/*     */   private Bone eye3;
/*     */   private boolean firstMove = true;
/*     */   
/*     */   public Nemesis() {
/*  52 */     super(NAME, "Nemesis", 185, 5.0F, -10.0F, 350.0F, 440.0F, null, 0.0F, 0.0F);
/*  53 */     this.type = EnemyType.ELITE;
/*     */     
/*  55 */     loadAnimation("images/monsters/theForest/nemesis/skeleton.atlas", "images/monsters/theForest/nemesis/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  59 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  60 */     e.setTime(e.getEndTime() * MathUtils.random());
/*  61 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  62 */     e.setTimeScale(0.8F);
/*  63 */     this.eye1 = this.skeleton.findBone("eye0");
/*  64 */     this.eye2 = this.skeleton.findBone("eye1");
/*  65 */     this.eye3 = this.skeleton.findBone("eye2");
/*     */     
/*  67 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  68 */       setHp(200);
/*     */     } else {
/*  70 */       setHp(185);
/*     */     } 
/*     */     
/*  73 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  74 */       this.fireDmg = 7;
/*     */     } else {
/*  76 */       this.fireDmg = 6;
/*     */     } 
/*     */     
/*  79 */     this.damage.add(new DamageInfo((AbstractCreature)this, 45));
/*  80 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.fireDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/*  86 */     switch (this.nextMove) {
/*     */       case 3:
/*  88 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/*  89 */         playSfx();
/*  90 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.4F));
/*  91 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  92 */               .get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*     */         break;
/*     */       case 2:
/*  95 */         for (i = 0; i < 3; i++) {
/*  96 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/*  97 */                 .get(1), AbstractGameAction.AttackEffect.FIRE));
/*     */         }
/*     */         break;
/*     */       case 4:
/* 101 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_NEMESIS_1C"));
/* 102 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 107 */         if (AbstractDungeon.ascensionLevel >= 18) {
/* 108 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 5));
/*     */           break;
/*     */         } 
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 3));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (!hasPower("Intangible")) {
/* 118 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new IntangiblePower((AbstractCreature)this, 1)));
/*     */     }
/*     */     
/* 121 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 126 */     if (info.output > 0 && hasPower("Intangible")) {
/* 127 */       info.output = 1;
/*     */     }
/*     */     
/* 130 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 131 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 132 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 133 */       e.setTimeScale(0.8F);
/*     */     } 
/* 135 */     super.damage(info);
/*     */   }
/*     */   
/*     */   public void changeState(String key) {
/*     */     AnimationState.TrackEntry e;
/* 140 */     switch (key) {
/*     */       case "ATTACK":
/* 142 */         e = this.state.setAnimation(0, "Attack", false);
/* 143 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/* 144 */         e.setTimeScale(0.8F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 153 */     this.scytheCooldown--;
/* 154 */     if (this.firstMove) {
/* 155 */       this.firstMove = false;
/*     */       
/* 157 */       if (num < 50) {
/* 158 */         setMove((byte)2, Intent.ATTACK, this.fireDmg, 3, true);
/*     */       } else {
/* 160 */         setMove((byte)4, Intent.DEBUFF);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 166 */     if (num < 30) {
/* 167 */       if (!lastMove((byte)3) && this.scytheCooldown <= 0) {
/* 168 */         setMove((byte)3, Intent.ATTACK, 45);
/* 169 */         this.scytheCooldown = 2;
/*     */       }
/* 171 */       else if (AbstractDungeon.aiRng.randomBoolean()) {
/* 172 */         if (!lastTwoMoves((byte)2)) {
/* 173 */           setMove((byte)2, Intent.ATTACK, this.fireDmg, 3, true);
/*     */         } else {
/* 175 */           setMove((byte)4, Intent.DEBUFF);
/*     */         }
/*     */       
/* 178 */       } else if (!lastMove((byte)4)) {
/* 179 */         setMove((byte)4, Intent.DEBUFF);
/*     */       } else {
/* 181 */         setMove((byte)2, Intent.ATTACK, this.fireDmg, 3, true);
/*     */       }
/*     */     
/*     */     }
/* 185 */     else if (num < 65) {
/* 186 */       if (!lastTwoMoves((byte)2)) {
/* 187 */         setMove((byte)2, Intent.ATTACK, this.fireDmg, 3, true);
/*     */       }
/* 189 */       else if (AbstractDungeon.aiRng.randomBoolean()) {
/* 190 */         if (this.scytheCooldown > 0) {
/* 191 */           setMove((byte)4, Intent.DEBUFF);
/*     */         } else {
/* 193 */           setMove((byte)3, Intent.ATTACK, 45);
/* 194 */           this.scytheCooldown = 2;
/*     */         } 
/*     */       } else {
/* 197 */         setMove((byte)4, Intent.DEBUFF);
/*     */       }
/*     */     
/*     */     }
/* 201 */     else if (!lastMove((byte)4)) {
/* 202 */       setMove((byte)4, Intent.DEBUFF);
/*     */     }
/* 204 */     else if (AbstractDungeon.aiRng.randomBoolean() && this.scytheCooldown <= 0) {
/* 205 */       setMove((byte)3, Intent.ATTACK, 45);
/* 206 */       this.scytheCooldown = 2;
/*     */     } else {
/* 208 */       setMove((byte)2, Intent.ATTACK, this.fireDmg, 3, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 215 */     int roll = MathUtils.random(1);
/* 216 */     if (roll == 0) {
/* 217 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_NEMESIS_1A"));
/*     */     } else {
/* 219 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_NEMESIS_1B"));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playDeathSfx() {
/* 224 */     int roll = MathUtils.random(1);
/* 225 */     if (roll == 0) {
/* 226 */       CardCrawlGame.sound.play("VO_NEMESIS_2A");
/*     */     } else {
/* 228 */       CardCrawlGame.sound.play("VO_NEMESIS_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 234 */     playDeathSfx();
/* 235 */     super.die();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 240 */     super.update();
/* 241 */     if (!this.isDying) {
/* 242 */       this.fireTimer -= Gdx.graphics.getDeltaTime();
/* 243 */       if (this.fireTimer < 0.0F) {
/* 244 */         this.fireTimer = 0.05F;
/* 245 */         AbstractDungeon.effectList.add(new NemesisFireParticle(this.skeleton
/* 246 */               .getX() + this.eye1.getWorldX(), this.skeleton.getY() + this.eye1.getWorldY()));
/* 247 */         AbstractDungeon.effectList.add(new NemesisFireParticle(this.skeleton
/* 248 */               .getX() + this.eye2.getWorldX(), this.skeleton.getY() + this.eye2.getWorldY()));
/* 249 */         AbstractDungeon.effectList.add(new NemesisFireParticle(this.skeleton
/* 250 */               .getX() + this.eye3.getWorldX(), this.skeleton.getY() + this.eye3.getWorldY()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Nemesis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */