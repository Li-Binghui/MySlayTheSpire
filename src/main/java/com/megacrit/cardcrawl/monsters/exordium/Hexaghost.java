/*     */ package com.megacrit.cardcrawl.monsters.exordium;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.RollMoveAction;
/*     */ import com.megacrit.cardcrawl.actions.unique.BurnIncreaseAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.GhostIgniteEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class Hexaghost extends AbstractMonster {
/*  40 */   private static final Logger logger = LogManager.getLogger(Hexaghost.class.getName());
/*     */   public static final String ID = "Hexaghost";
/*  42 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Hexaghost");
/*  43 */   public static final String NAME = monsterStrings.NAME;
/*  44 */   public static final String[] MOVES = monsterStrings.MOVES;
/*  45 */   public static final String[] DIALOG = monsterStrings.DIALOG; public static final String IMAGE = "images/monsters/theBottom/boss/ghost/core.png"; private static final int HP = 250;
/*     */   private static final int A_2_HP = 264;
/*     */   private static final int SEAR_DMG = 6;
/*     */   private static final int INFERNO_DMG = 2;
/*     */   private static final int FIRE_TACKLE_DMG = 5;
/*     */   private static final int BURN_COUNT = 1;
/*     */   private static final int STR_AMT = 2;
/*  52 */   private ArrayList<HexaghostOrb> orbs = new ArrayList<>(); private static final int A_4_INFERNO_DMG = 3;
/*     */   private static final int A_4_FIRE_TACKLE_DMG = 6;
/*     */   private static final int A_19_BURN_COUNT = 2;
/*     */   private static final int A_19_STR_AMT = 3;
/*     */   private int searDmg;
/*     */   private int strAmount;
/*     */   private int searBurnCount;
/*  59 */   private int strengthenBlockAmt = 12; private int fireTackleDmg; private int fireTackleCount = 2; private int infernoDmg; private int infernoHits = 6; private static final byte DIVIDER = 1;
/*     */   private static final byte TACKLE = 2;
/*     */   private static final byte INFLAME = 3;
/*  62 */   private static final String STRENGTHEN_NAME = MOVES[0]; private static final byte SEAR = 4; private static final byte ACTIVATE = 5; private static final byte INFERNO = 6; private static final String SEAR_NAME = MOVES[1]; private static final String BURN_NAME = MOVES[2];
/*     */   private static final String ACTIVATE_STATE = "Activate";
/*     */   private static final String ACTIVATE_ORB = "Activate Orb";
/*     */   private static final String DEACTIVATE_ALL_ORBS = "Deactivate";
/*     */   private boolean activated = false;
/*     */   private boolean burnUpgraded = false;
/*  68 */   private int orbActiveCount = 0;
/*     */   private HexaghostBody body;
/*     */   
/*     */   public Hexaghost() {
/*  72 */     super(NAME, "Hexaghost", 250, 20.0F, 0.0F, 450.0F, 450.0F, "images/monsters/theBottom/boss/ghost/core.png");
/*  73 */     this.type = EnemyType.BOSS;
/*  74 */     this.body = new HexaghostBody(this);
/*  75 */     this.disposables.add(this.body);
/*  76 */     createOrbs();
/*     */     
/*  78 */     if (AbstractDungeon.ascensionLevel >= 9) {
/*  79 */       setHp(264);
/*     */     } else {
/*  81 */       setHp(250);
/*     */     } 
/*     */     
/*  84 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  85 */       this.strAmount = 3;
/*  86 */       this.searBurnCount = 2;
/*  87 */       this.fireTackleDmg = 6;
/*  88 */       this.infernoDmg = 3;
/*  89 */     } else if (AbstractDungeon.ascensionLevel >= 4) {
/*  90 */       this.strAmount = 2;
/*  91 */       this.searBurnCount = 1;
/*  92 */       this.fireTackleDmg = 6;
/*  93 */       this.infernoDmg = 3;
/*     */     } else {
/*  95 */       this.strAmount = 2;
/*  96 */       this.searBurnCount = 1;
/*  97 */       this.fireTackleDmg = 5;
/*  98 */       this.infernoDmg = 2;
/*     */     } 
/*     */     
/* 101 */     this.searDmg = 6;
/* 102 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.fireTackleDmg));
/* 103 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.searDmg));
/* 104 */     this.damage.add(new DamageInfo((AbstractCreature)this, -1));
/* 105 */     this.damage.add(new DamageInfo((AbstractCreature)this, this.infernoDmg));
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/* 110 */     UnlockTracker.markBossAsSeen("GHOST");
/* 111 */     CardCrawlGame.music.precacheTempBgm("BOSS_BOTTOM");
/*     */   }
/*     */   
/*     */   private void createOrbs() {
/* 115 */     this.orbs.add(new HexaghostOrb(-90.0F, 380.0F, this.orbs.size()));
/* 116 */     this.orbs.add(new HexaghostOrb(90.0F, 380.0F, this.orbs.size()));
/* 117 */     this.orbs.add(new HexaghostOrb(160.0F, 250.0F, this.orbs.size()));
/* 118 */     this.orbs.add(new HexaghostOrb(90.0F, 120.0F, this.orbs.size()));
/* 119 */     this.orbs.add(new HexaghostOrb(-90.0F, 120.0F, this.orbs.size()));
/* 120 */     this.orbs.add(new HexaghostOrb(-160.0F, 250.0F, this.orbs.size()));
/*     */   } public void takeTurn() {
/*     */     int d, i;
/*     */     Burn c;
/*     */     int j;
/* 125 */     switch (this.nextMove) {
/*     */       
/*     */       case 5:
/* 128 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Activate"));
/* 129 */         d = AbstractDungeon.player.currentHealth / 12 + 1;
/*     */         
/* 131 */         ((DamageInfo)this.damage.get(2)).base = d;
/*     */         
/* 133 */         applyPowers();
/* 134 */         setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(2)).base, 6, true);
/*     */         return;
/*     */ 
/*     */       
/*     */       case 1:
/* 139 */         for (i = 0; i < 6; i++) {
/* 140 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new GhostIgniteEffect(AbstractDungeon.player.hb.cX + 
/*     */ 
/*     */ 
/*     */                   
/* 144 */                   MathUtils.random(-120.0F, 120.0F) * Settings.scale, AbstractDungeon.player.hb.cY + 
/* 145 */                   MathUtils.random(-120.0F, 120.0F) * Settings.scale), 0.05F));
/*     */           
/* 147 */           if (MathUtils.randomBoolean()) {
/* 148 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("GHOST_ORB_IGNITE_1", 0.3F));
/*     */           } else {
/* 150 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("GHOST_ORB_IGNITE_2", 0.3F));
/*     */           } 
/* 152 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 153 */                 .get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
/*     */         } 
/* 155 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Deactivate"));
/* 156 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         return;
/*     */ 
/*     */       
/*     */       case 2:
/* 161 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.CHARTREUSE)));
/* 162 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
/* 163 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 164 */               .get(0), AbstractGameAction.AttackEffect.FIRE));
/* 165 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 166 */               .get(0), AbstractGameAction.AttackEffect.FIRE));
/* 167 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Activate Orb"));
/* 168 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         return;
/*     */ 
/*     */       
/*     */       case 4:
/* 173 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new FireballEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.5F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 181 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 182 */               .get(1), AbstractGameAction.AttackEffect.FIRE));
/* 183 */         c = new Burn();
/* 184 */         if (this.burnUpgraded) {
/* 185 */           c.upgrade();
/*     */         }
/* 187 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)c, this.searBurnCount));
/* 188 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Activate Orb"));
/* 189 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         return;
/*     */ 
/*     */       
/*     */       case 3:
/* 194 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new InflameEffect((AbstractCreature)this), 0.5F));
/* 195 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.strengthenBlockAmt));
/* 196 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmount), this.strAmount));
/*     */         
/* 198 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Activate Orb"));
/* 199 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         return;
/*     */ 
/*     */       
/*     */       case 6:
/* 204 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ScreenOnFireEffect(), 1.0F));
/* 205 */         for (j = 0; j < this.infernoHits; j++) {
/* 206 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 207 */                 .get(3), AbstractGameAction.AttackEffect.FIRE));
/*     */         }
/* 209 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new BurnIncreaseAction());
/* 210 */         if (!this.burnUpgraded) {
/* 211 */           this.burnUpgraded = true;
/*     */         }
/* 213 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "Deactivate"));
/* 214 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */         return;
/*     */     } 
/* 217 */     logger.info("ERROR: Default Take Turn was called on " + this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 224 */     if (!this.activated) {
/* 225 */       this.activated = true;
/* 226 */       setMove((byte)5, Intent.UNKNOWN);
/*     */     } else {
/*     */       
/* 229 */       switch (this.orbActiveCount) {
/*     */         case 0:
/* 231 */           setMove(SEAR_NAME, (byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */           break;
/*     */         case 1:
/* 234 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.fireTackleCount, true);
/*     */           break;
/*     */         case 2:
/* 237 */           setMove(SEAR_NAME, (byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */           break;
/*     */         case 3:
/* 240 */           setMove(STRENGTHEN_NAME, (byte)3, Intent.DEFEND_BUFF);
/*     */           break;
/*     */         case 4:
/* 243 */           setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, this.fireTackleCount, true);
/*     */           break;
/*     */         case 5:
/* 246 */           setMove(SEAR_NAME, (byte)4, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
/*     */           break;
/*     */         case 6:
/* 249 */           setMove(BURN_NAME, (byte)6, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(3)).base, this.infernoHits, true);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String stateName) {
/* 261 */     switch (stateName) {
/*     */       
/*     */       case "Activate":
/* 264 */         if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 265 */           CardCrawlGame.music.unsilenceBGM();
/* 266 */           AbstractDungeon.scene.fadeOutAmbiance();
/* 267 */           CardCrawlGame.music.playPrecachedTempBgm();
/*     */         } 
/*     */         
/* 270 */         for (HexaghostOrb orb : this.orbs) {
/* 271 */           orb.activate(this.drawX + this.animX, this.drawY + this.animY);
/*     */         }
/* 273 */         this.orbActiveCount = 6;
/* 274 */         this.body.targetRotationSpeed = 120.0F;
/*     */         break;
/*     */ 
/*     */       
/*     */       case "Activate Orb":
/* 279 */         for (HexaghostOrb orb : this.orbs) {
/* 280 */           if (!orb.activated) {
/* 281 */             orb.activate(this.drawX + this.animX, this.drawY + this.animY);
/*     */             break;
/*     */           } 
/*     */         } 
/* 285 */         this.orbActiveCount++;
/* 286 */         if (this.orbActiveCount == 6) {
/* 287 */           setMove(BURN_NAME, (byte)6, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(3)).base, this.infernoHits, true);
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case "Deactivate":
/* 294 */         for (HexaghostOrb orb : this.orbs) {
/* 295 */           orb.deactivate();
/*     */         }
/* 297 */         CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);
/* 298 */         CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);
/* 299 */         this.orbActiveCount = 0;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 306 */     useFastShakeAnimation(5.0F);
/* 307 */     CardCrawlGame.screenShake.rumble(4.0F);
/* 308 */     super.die();
/*     */     
/* 310 */     for (HexaghostOrb orb : this.orbs) {
/* 311 */       orb.hide();
/*     */     }
/*     */     
/* 314 */     onBossVictoryLogic();
/* 315 */     UnlockTracker.hardUnlockOverride("GHOST");
/* 316 */     UnlockTracker.unlockAchievement("GHOST_GUARDIAN");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 324 */     super.update();
/* 325 */     this.body.update();
/*     */     
/* 327 */     for (HexaghostOrb orb : this.orbs) {
/* 328 */       orb.update(this.drawX + this.animX, this.drawY + this.animY);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 337 */     this.body.render(sb);
/* 338 */     super.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\exordium\Hexaghost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */