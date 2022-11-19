/*     */ package com.megacrit.cardcrawl.monsters.beyond;
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
/*     */ 
/*     */ public class Reptomancer extends AbstractMonster {
/*     */   public static final String ID = "Reptomancer";
/*  29 */   private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Reptomancer");
/*  30 */   public static final String NAME = monsterStrings.NAME;
/*     */   
/*     */   private static final int HP_MIN = 180;
/*     */   private static final int HP_MAX = 190;
/*     */   private static final int A_2_HP_MIN = 190;
/*     */   private static final int A_2_HP_MAX = 200;
/*     */   private static final int BITE_DMG = 30;
/*     */   private static final int A_2_BITE_DMG = 34;
/*  38 */   public static final float[] POSX = new float[] { 210.0F, -220.0F, 180.0F, -250.0F }; private static final int SNAKE_STRIKE_DMG = 13; private static final int A_2_SNAKE_STRIKE_DMG = 16; private static final int DAGGERS_PER_SPAWN = 1; private static final int ASC_2_DAGGERS_PER_SPAWN = 2; private static final byte SNAKE_STRIKE = 1; private static final byte SPAWN_DAGGER = 2; private static final byte BIG_BITE = 3;
/*  39 */   public static final float[] POSY = new float[] { 75.0F, 115.0F, 345.0F, 335.0F };
/*     */   private int daggersPerSpawn;
/*  41 */   private AbstractMonster[] daggers = new AbstractMonster[4];
/*     */   private boolean firstMove = true;
/*     */   
/*     */   public Reptomancer() {
/*  45 */     super(NAME, "Reptomancer", AbstractDungeon.monsterHpRng.random(180, 190), 0.0F, -30.0F, 220.0F, 320.0F, null, -20.0F, 10.0F);
/*  46 */     this.type = EnemyType.ELITE;
/*     */     
/*  48 */     loadAnimation("images/monsters/theForest/mage/skeleton.atlas", "images/monsters/theForest/mage/skeleton.json", 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (AbstractDungeon.ascensionLevel >= 18) {
/*  54 */       this.daggersPerSpawn = 2;
/*     */     } else {
/*  56 */       this.daggersPerSpawn = 1;
/*     */     } 
/*     */     
/*  59 */     if (AbstractDungeon.ascensionLevel >= 8) {
/*  60 */       setHp(190, 200);
/*     */     } else {
/*  62 */       setHp(180, 190);
/*     */     } 
/*     */     
/*  65 */     if (AbstractDungeon.ascensionLevel >= 3) {
/*  66 */       this.damage.add(new DamageInfo((AbstractCreature)this, 16));
/*  67 */       this.damage.add(new DamageInfo((AbstractCreature)this, 34));
/*     */     } else {
/*  69 */       this.damage.add(new DamageInfo((AbstractCreature)this, 13));
/*  70 */       this.damage.add(new DamageInfo((AbstractCreature)this, 30));
/*     */     } 
/*     */     
/*  73 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  74 */     this.stateData.setMix("Idle", "Sumon", 0.1F);
/*  75 */     this.stateData.setMix("Sumon", "Idle", 0.1F);
/*  76 */     this.stateData.setMix("Hurt", "Idle", 0.1F);
/*  77 */     this.stateData.setMix("Idle", "Hurt", 0.1F);
/*  78 */     this.stateData.setMix("Attack", "Idle", 0.1F);
/*  79 */     e.setTime(e.getEndTime() * MathUtils.random());
/*     */   }
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  84 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/*  85 */       if (!m.id.equals(this.id)) {
/*  86 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new MinionPower((AbstractCreature)this)));
/*     */       }
/*  88 */       if (m instanceof SnakeDagger) {
/*     */         
/*  90 */         if ((AbstractDungeon.getMonsters()).monsters.indexOf(m) > (AbstractDungeon.getMonsters()).monsters.indexOf(this)) {
/*     */           
/*  92 */           this.daggers[0] = m; continue;
/*     */         } 
/*  94 */         this.daggers[1] = m;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/*     */     int daggersSpawned, i;
/* 102 */     switch (this.nextMove) {
/*     */       case 1:
/* 104 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "ATTACK"));
/* 105 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.3F));
/* 106 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX + 
/*     */ 
/*     */                 
/* 109 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, AbstractDungeon.player.hb.cY + 
/* 110 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.ORANGE
/* 111 */                 .cpy()), 0.1F));
/*     */         
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 114 */               .get(0), AbstractGameAction.AttackEffect.NONE));
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX + 
/*     */ 
/*     */                 
/* 118 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, AbstractDungeon.player.hb.cY + 
/* 119 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.ORANGE
/* 120 */                 .cpy()), 0.1F));
/*     */         
/* 122 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 123 */               .get(0), AbstractGameAction.AttackEffect.NONE));
/* 124 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 132 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStateAction(this, "SUMMON"));
/* 133 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.5F));
/*     */         
/* 135 */         daggersSpawned = 0;
/* 136 */         for (i = 0; daggersSpawned < this.daggersPerSpawn && i < this.daggers.length; i++) {
/* 137 */           if (this.daggers[i] == null || this.daggers[i].isDeadOrEscaped()) {
/* 138 */             SnakeDagger daggerToSpawn = new SnakeDagger(POSX[i], POSY[i]);
/* 139 */             this.daggers[i] = daggerToSpawn;
/* 140 */             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(daggerToSpawn, true));
/* 141 */             daggersSpawned++;
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 147 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateFastAttackAction((AbstractCreature)this));
/* 148 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX + 
/*     */ 
/*     */                 
/* 151 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, AbstractDungeon.player.hb.cY + 
/* 152 */                 MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE
/* 153 */                 .cpy()), 0.1F));
/*     */         
/* 155 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
/* 156 */               .get(1), AbstractGameAction.AttackEffect.NONE));
/*     */         break;
/*     */     } 
/* 159 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
/*     */   }
/*     */   
/*     */   private boolean canSpawn() {
/* 163 */     int aliveCount = 0;
/* 164 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 165 */       if (m != this && !m.isDying) {
/* 166 */         aliveCount++;
/*     */       }
/*     */     } 
/* 169 */     if (aliveCount > 3) {
/* 170 */       return false;
/*     */     }
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 177 */     super.damage(info);
/* 178 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
/* 179 */       this.state.setAnimation(0, "Hurt", false);
/* 180 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 186 */     super.die();
/* 187 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 188 */       if (!m.isDead && !m.isDying) {
/* 189 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
/* 190 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 197 */     if (this.firstMove) {
/* 198 */       this.firstMove = false;
/* 199 */       setMove((byte)2, Intent.UNKNOWN);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 204 */     if (num < 33) {
/* 205 */       if (!lastMove((byte)1)) {
/* 206 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */       } else {
/* 208 */         getMove(AbstractDungeon.aiRng.random(33, 99));
/*     */       }
/*     */     
/*     */     }
/* 212 */     else if (num < 66) {
/* 213 */       if (!lastTwoMoves((byte)2)) {
/* 214 */         if (canSpawn()) {
/* 215 */           setMove((byte)2, Intent.UNKNOWN);
/*     */         } else {
/* 217 */           setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */         } 
/*     */       } else {
/* 220 */         setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base, 2, true);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 225 */     else if (!lastMove((byte)3)) {
/* 226 */       setMove((byte)3, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
/*     */     } else {
/* 228 */       getMove(AbstractDungeon.aiRng.random(65));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeState(String key) {
/* 235 */     switch (key) {
/*     */       case "ATTACK":
/* 237 */         this.state.setAnimation(0, "Attack", false);
/* 238 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */       case "SUMMON":
/* 241 */         this.state.setAnimation(0, "Sumon", false);
/* 242 */         this.state.addAnimation(0, "Idle", true, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\beyond\Reptomancer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */