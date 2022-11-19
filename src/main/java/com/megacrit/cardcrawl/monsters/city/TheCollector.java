/*     */ package com.megacrit.cardcrawl.monsters.city;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SuicideAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.MonsterStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.FrailPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*     */ import com.megacrit.cardcrawl.powers.WeakPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
/*     */ import com.megacrit.cardcrawl.vfx.GlowyFireEyesEffect;
/*     */ import com.megacrit.cardcrawl.vfx.StaffFireEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class TheCollector extends AbstractMonster {
/*  41 */   private static final Logger logger = LogManager.getLogger(TheCollector.class.getName());
/*     */   public static final String ID = "TheCollector";
/*  43 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TheCollector");
/*  44 */   public static final String NAME = monsterStrings.NAME;
/*  45 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  46 */   public static final String[] DIALOG = monsterStrings.DIALOG;
/*     */   
/*     */   public static final int HP = 282;
/*     */   
/*     */   public static final int A_2_HP = 300;
/*     */   
/*     */   private static final int FIREBALL_DMG = 18;
/*     */   private static final int STR_AMT = 3;
/*     */   private static final int BLOCK_AMT = 15;
/*     */   private static final int A_2_FIREBALL_DMG = 21;
/*  56 */   private int turnsTaken = 0; private static final int A_2_STR_AMT = 4; private static final int A_2_BLOCK_AMT = 18; private int rakeDmg; private int strAmt; private int blockAmt; private int megaDebuffAmt; private static final int MEGA_DEBUFF_AMT = 3;
/*  57 */   private float spawnX = -100.0F;
/*  58 */   private float fireTimer = 0.0F;
/*     */   
/*     */   private static final float FIRE_TIME = 0.07F;
/*     */   private boolean ultUsed = false;
/*     */   private boolean initialSpawn = true;
/*  63 */   private HashMap<Integer, AbstractMonster> enemySlots = new HashMap<>(); private static final byte SPAWN = 1; private static final byte FIREBALL = 2; private static final byte BUFF = 3;
/*     */   private static final byte MEGA_DEBUFF = 4;
/*     */   private static final byte REVIVE = 5;
/*     */   
/*     */   public TheCollector() {
/*  68 */     super(NAME, "TheCollector", 282, 15.0F, -40.0F, 300.0F, 390.0F, null, 60.0F, 135.0F);
/*     */     
/*  70 */     this.dialogX = -90.0F * Settings.scale;
/*  71 */     this.dialogY = 10.0F * Settings.scale;
/*  72 */     this.type = EnemyType.BOSS;
/*     */     
/*  74 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  75 */       setHp(300);
/*  76 */       this.blockAmt = 18;
/*     */     } else {
/*  78 */       setHp(282);
/*  79 */       this.blockAmt = 15;
/*     */     } 
/*     */     
/*  82 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  83 */       this.rakeDmg = 21;
/*  84 */       this.strAmt = 5;
/*  85 */       this.megaDebuffAmt = 5;
/*  86 */     } else if (AbstractDungeon.ascensionLevel >= 4) {
/*  87 */       this.rakeDmg = 21;
/*  88 */       this.strAmt = 4;
/*  89 */       this.megaDebuffAmt = 3;
/*     */     } else {
/*  91 */       this.rakeDmg = 18;
/*  92 */       this.strAmt = 3;
/*  93 */       this.megaDebuffAmt = 3;
/*     */     } 
/*     */     
/*  96 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.rakeDmg));
/*     */ 
/*     */     
/*  99 */     loadAnimation("images/monsters/theCity/collector/skeleton.atlas", "images/monsters/theCity/collector/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/* 103 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/* 104 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 109 */     CardCrawlGame.music.unsilenceBGM();
/* 110 */     AbstractDungeon.scene.fadeOutAmbiance();
/* 111 */     AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_CITY");
/* 112 */     UnlockTracker.markBossAsSeen("COLLECTOR");
/*     */   }
/*     */   
/*     */   public void takeTurn() {
/*     */     int i;
/* 117 */     switch (this.nextMove) {
/*     */       case 1:
/* 119 */         for (i = 1; i < 3; i++) {
/* 120 */           AbstractMonster m = new TorchHead(this.spawnX + -185.0F * i, MathUtils.random(-5.0F, 25.0F));
/* 121 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_COLLECTOR_SUMMON"));
/* 122 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(m, true));
/* 123 */           this.enemySlots.put(Integer.valueOf(i), m);
/*     */         } 
/*     */         
/* 126 */         this.initialSpawn = false;
/*     */         break;
/*     */       case 2:
/* 129 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 130 */               .get(0), AbstractGameAction.AttackEffect.FIRE));
/*     */         break;
/*     */       case 3:
/* 133 */         if (AbstractDungeon.ascensionLevel >= 19) {
/* 134 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt + 5));
/*     */         } else {
/* 136 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt));
/*     */         } 
/* 138 */         for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 139 */           if (!m.isDead && !m.isDying && !m.isEscaping) {
/* 140 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.strAmt), this.strAmt));
/*     */           }
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 4:
/* 146 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0]));
/* 147 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
/* 148 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new CollectorCurseEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 2.0F));
/*     */ 
/*     */ 
/*     */         
/* 152 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, this.megaDebuffAmt, true), this.megaDebuffAmt));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 158 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.megaDebuffAmt, true), this.megaDebuffAmt));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 164 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, this.megaDebuffAmt, true), this.megaDebuffAmt));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 171 */         this.ultUsed = true;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 176 */         for (Map.Entry<Integer, AbstractMonster> m : this.enemySlots.entrySet()) {
/* 177 */           if (((AbstractMonster)m.getValue()).isDying) {
/* 178 */             AbstractMonster newMonster = new TorchHead(this.spawnX + -185.0F * ((Integer)m.getKey()).intValue(), MathUtils.random(-5.0F, 25.0F));
/* 179 */             int key = ((Integer)m.getKey()).intValue();
/* 180 */             this.enemySlots.put(Integer.valueOf(key), newMonster);
/* 181 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(newMonster, true));
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       default:
/* 186 */         logger.info("ERROR: Default Take Turn was called on " + this.name);
/*     */         break;
/*     */     } 
/* 189 */     this.turnsTaken++;
/* 190 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 195 */     if (this.initialSpawn) {
/* 196 */       setMove((byte)1, Intent.UNKNOWN);
/*     */       
/*     */       return;
/*     */     } 
/* 200 */     if (this.turnsTaken >= 3 && !this.ultUsed) {
/* 201 */       setMove((byte)4, Intent.STRONG_DEBUFF);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 206 */     if (num <= 25 && isMinionDead() && !lastMove((byte)5)) {
/* 207 */       setMove((byte)5, Intent.UNKNOWN);
/*     */       
/*     */       return;
/*     */     } 
/* 211 */     if (num <= 70 && !lastTwoMoves((byte)2)) {
/* 212 */       setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */       
/*     */       return;
/*     */     } 
/* 216 */     if (!lastMove((byte)3)) {
/* 217 */       setMove((byte)3, Intent.DEFEND_BUFF);
/*     */     } else {
/* 219 */       setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isMinionDead() {
/* 224 */     for (Map.Entry<Integer, AbstractMonster> m : this.enemySlots.entrySet()) {
/* 225 */       if (((AbstractMonster)m.getValue()).isDying) {
/* 226 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 235 */     super.update();
/* 236 */     if (!this.isDying) {
/* 237 */       this.fireTimer -= Gdx.graphics.getDeltaTime();
/* 238 */       if (this.fireTimer < 0.0F) {
/* 239 */         this.fireTimer = 0.07F;
/* 240 */         AbstractDungeon.effectList.add(new GlowyFireEyesEffect(this.skeleton
/*     */               
/* 242 */               .getX() + this.skeleton.findBone("lefteyefireslot").getX(), this.skeleton
/* 243 */               .getY() + this.skeleton.findBone("lefteyefireslot").getY() + 140.0F * Settings.scale));
/*     */         
/* 245 */         AbstractDungeon.effectList.add(new GlowyFireEyesEffect(this.skeleton
/*     */               
/* 247 */               .getX() + this.skeleton.findBone("righteyefireslot").getX(), this.skeleton
/* 248 */               .getY() + this.skeleton.findBone("righteyefireslot").getY() + 140.0F * Settings.scale));
/*     */         
/* 250 */         AbstractDungeon.effectList.add(new StaffFireEffect(this.skeleton
/*     */               
/* 252 */               .getX() + this.skeleton.findBone("fireslot").getX() - 120.0F * Settings.scale, this.skeleton
/* 253 */               .getY() + this.skeleton.findBone("fireslot").getY() + 390.0F * Settings.scale));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 260 */     useFastShakeAnimation(5.0F);
/* 261 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 262 */     this.deathTimer += 1.5F;
/* 263 */     super.die();
/* 264 */     onBossVictoryLogic();
/*     */     
/* 266 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 267 */       if (!m.isDead && !m.isDying) {
/* 268 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
/* 269 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
/* 270 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new InflameEffect((AbstractCreature)m), 0.2F));
/*     */       } 
/*     */     } 
/* 273 */     UnlockTracker.hardUnlockOverride("COLLECTOR");
/* 274 */     UnlockTracker.unlockAchievement("COLLECTOR");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\city\TheCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */