/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SuicideAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.LaserBeamEffect;
/*     */ 
/*     */ public class BronzeAutomaton extends AbstractMonster {
/*     */   public static final String ID = "BronzeAutomaton";
/*  33 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("BronzeAutomaton");
/*  34 */   public static final String NAME = monsterStrings.NAME;
/*  35 */   private static final String[] MOVES = monsterStrings.MOVES; private static final int HP = 300; private static final int A_2_HP = 320;
/*     */   private static final byte FLAIL = 1;
/*     */   private static final byte HYPER_BEAM = 2;
/*     */   private static final byte STUNNED = 3;
/*     */   private static final byte SPAWN_ORBS = 4;
/*     */   private static final byte BOOST = 5;
/*  41 */   private static final String BEAM_NAME = MOVES[0];
/*     */   
/*     */   private static final int FLAIL_DMG = 7;
/*     */   
/*     */   private static final int BEAM_DMG = 45;
/*     */   
/*     */   private static final int A_2_FLAIL_DMG = 8;
/*     */   private static final int A_2_BEAM_DMG = 50;
/*     */   private int flailDmg;
/*     */   private int beamDmg;
/*  51 */   private int numTurns = 0; private static final int BLOCK_AMT = 9; private static final int STR_AMT = 3; private static final int A_2_BLOCK_AMT = 12; private static final int A_2_STR_AMT = 4; private int strAmt; private int blockAmt;
/*     */   private boolean firstTurn = true;
/*     */   
/*     */   public BronzeAutomaton() {
/*  55 */     super(NAME, "BronzeAutomaton", 300, 0.0F, -30.0F, 270.0F, 400.0F, null, -50.0F, 20.0F);
/*  56 */     loadAnimation("images/monsters/theCity/automaton/skeleton.atlas", "images/monsters/theCity/automaton/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  60 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*  61 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */     
/*  63 */     this.type = EnemyType.BOSS;
/*  64 */     this.dialogX = -100.0F * Settings.scale;
/*  65 */     this.dialogY = 10.0F * Settings.scale;
/*     */     
/*  67 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  68 */       setHp(320);
/*  69 */       this.blockAmt = 12;
/*     */     } else {
/*  71 */       setHp(300);
/*  72 */       this.blockAmt = 9;
/*     */     } 
/*     */     
/*  75 */     if (AbstractDungeon.ascensionLevel >= 4) {
/*  76 */       this.flailDmg = 8;
/*  77 */       this.beamDmg = 50;
/*  78 */       this.strAmt = 4;
/*     */     } else {
/*  80 */       this.flailDmg = 7;
/*  81 */       this.beamDmg = 45;
/*  82 */       this.strAmt = 3;
/*     */     } 
/*     */     
/*  85 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.flailDmg));
/*  86 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.beamDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  91 */     CardCrawlGame.music.unsilenceBGM();
/*  92 */     AbstractDungeon.scene.fadeOutAmbiance();
/*  93 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_CITY");
/*  94 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3)));
/*  95 */     UnlockTracker.markBossAsSeen("AUTOMATON");
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 100 */     switch (this.nextMove) {
/*     */       case 4:
/* 102 */         if (MathUtils.randomBoolean()) {
/* 103 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("AUTOMATON_ORB_SPAWN", 
/* 104 */                 MathUtils.random(-0.1F, 0.1F)));
/*     */         } else {
/* 106 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_AUTOMATON_SUMMON", 
/* 107 */                 MathUtils.random(-0.1F, 0.1F)));
/*     */         } 
/* 109 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new BronzeOrb(-300.0F, 200.0F, 0), true));
/* 110 */         if (MathUtils.randomBoolean()) {
/* 111 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("AUTOMATON_ORB_SPAWN", 
/* 112 */                 MathUtils.random(-0.1F, 0.1F)));
/*     */         } else {
/* 114 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_AUTOMATON_SUMMON", 
/* 115 */                 MathUtils.random(-0.1F, 0.1F)));
/*     */         } 
/* 117 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(new BronzeOrb(200.0F, 130.0F, 1), true));
/*     */         break;
/*     */       case 1:
/* 120 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 121 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 122 */               .get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 123 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 124 */               .get(0), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*     */         break;
/*     */       case 5:
/* 127 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt));
/* 128 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt), this.strAmt));
/*     */         break;
/*     */       
/*     */       case 2:
/* 132 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new LaserBeamEffect(this.hb.cX, this.hb.cY + 60.0F * Settings.scale), 1.5F));
/*     */         
/* 134 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 135 */               .get(1), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */       case 3:
/* 138 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TextAboveCreatureAction((AbstractCreature)this, TextAboveCreatureAction.TextType.STUNNED));
/*     */         break;
/*     */     } 
/* 141 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 146 */     if (this.firstTurn) {
/* 147 */       setMove((byte)4, Intent.UNKNOWN);
/* 148 */       this.firstTurn = false;
/*     */       
/*     */       return;
/*     */     } 
/* 152 */     if (this.numTurns == 4) {
/* 153 */       setMove(BEAM_NAME, (byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/* 154 */       this.numTurns = 0;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 159 */     if (lastMove((byte)2)) {
/* 160 */       if (AbstractDungeon.ascensionLevel >= 19) {
/* 161 */         setMove((byte)5, Intent.DEFEND_BUFF);
/*     */         return;
/*     */       } 
/* 164 */       setMove((byte)3, Intent.STUN);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 169 */     if (lastMove((byte)3) || lastMove((byte)5) || lastMove((byte)4)) {
/* 170 */       setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */     } else {
/* 172 */       setMove((byte)5, Intent.DEFEND_BUFF);
/*     */     } 
/*     */     
/* 175 */     this.numTurns++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 180 */     useFastShakeAnimation(5.0F);
/* 181 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 182 */     super.die();
/* 183 */     onBossVictoryLogic();
/*     */     
/* 185 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 186 */       if (!m.isDead && !m.isDying) {
/* 187 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
/* 188 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
/* 189 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new InflameEffect((AbstractCreature)m), 0.2F));
/*     */       } 
/*     */     } 
/* 192 */     UnlockTracker.hardUnlockOverride("AUTOMATON");
/* 193 */     UnlockTracker.unlockAchievement("AUTOMATON");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\BronzeAutomaton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */