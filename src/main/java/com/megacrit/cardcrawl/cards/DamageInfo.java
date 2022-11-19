/*     */ package com.megacrit.cardcrawl.cards;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ 
/*     */ public class DamageInfo {
/*     */   public AbstractCreature owner;
/*     */   public String name;
/*     */   public DamageType type;
/*     */   public int base;
/*     */   public int output;
/*     */   public boolean isModified = false;
/*     */   
/*     */   public DamageInfo(AbstractCreature damageSource, int base, DamageType type) {
/*  19 */     this.owner = damageSource;
/*  20 */     this.type = type;
/*  21 */     this.base = base;
/*  22 */     this.output = base;
/*     */   }
/*     */   
/*     */   public DamageInfo(AbstractCreature owner, int base) {
/*  26 */     this(owner, base, DamageType.NORMAL);
/*     */   }
/*     */   
/*     */   public void applyPowers(AbstractCreature owner, AbstractCreature target) {
/*  30 */     this.output = this.base;
/*  31 */     this.isModified = false;
/*  32 */     float tmp = this.output;
/*     */     
/*  34 */     if (!owner.isPlayer) {
/*     */ 
/*     */       
/*  37 */       if (Settings.isEndless && AbstractDungeon.player.hasBlight("DeadlyEnemies")) {
/*  38 */         float mod = AbstractDungeon.player.getBlight("DeadlyEnemies").effectFloat();
/*  39 */         tmp *= mod;
/*     */         
/*  41 */         if (this.base != (int)tmp) {
/*  42 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  47 */       for (AbstractPower p : owner.powers) {
/*  48 */         tmp = p.atDamageGive(tmp, this.type);
/*     */         
/*  50 */         if (this.base != (int)tmp) {
/*  51 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  56 */       for (AbstractPower p : target.powers) {
/*  57 */         tmp = p.atDamageReceive(tmp, this.type);
/*  58 */         if (this.base != (int)tmp) {
/*  59 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  64 */       tmp = AbstractDungeon.player.stance.atDamageReceive(tmp, this.type);
/*  65 */       if (this.base != (int)tmp) {
/*  66 */         this.isModified = true;
/*     */       }
/*     */ 
/*     */       
/*  70 */       for (AbstractPower p : owner.powers) {
/*  71 */         tmp = p.atDamageFinalGive(tmp, this.type);
/*  72 */         if (this.base != (int)tmp) {
/*  73 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  78 */       for (AbstractPower p : target.powers) {
/*  79 */         tmp = p.atDamageFinalReceive(tmp, this.type);
/*  80 */         if (this.base != (int)tmp) {
/*  81 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  86 */       this.output = MathUtils.floor(tmp);
/*  87 */       if (this.output < 0) {
/*  88 */         this.output = 0;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  93 */       for (AbstractPower p : owner.powers) {
/*  94 */         tmp = p.atDamageGive(tmp, this.type);
/*  95 */         if (this.base != (int)tmp) {
/*  96 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 101 */       tmp = AbstractDungeon.player.stance.atDamageGive(tmp, this.type);
/* 102 */       if (this.base != (int)tmp) {
/* 103 */         this.isModified = true;
/*     */       }
/*     */ 
/*     */       
/* 107 */       for (AbstractPower p : target.powers) {
/* 108 */         tmp = p.atDamageReceive(tmp, this.type);
/* 109 */         if (this.base != (int)tmp) {
/* 110 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 115 */       for (AbstractPower p : owner.powers) {
/* 116 */         tmp = p.atDamageFinalGive(tmp, this.type);
/* 117 */         if (this.base != (int)tmp) {
/* 118 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 123 */       for (AbstractPower p : target.powers) {
/* 124 */         tmp = p.atDamageFinalReceive(tmp, this.type);
/* 125 */         if (this.base != (int)tmp) {
/* 126 */           this.isModified = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 131 */       this.output = MathUtils.floor(tmp);
/* 132 */       if (this.output < 0) {
/* 133 */         this.output = 0;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyEnemyPowersOnly(AbstractCreature target) {
/* 144 */     this.output = this.base;
/* 145 */     this.isModified = false;
/* 146 */     float tmp = this.output;
/*     */ 
/*     */     
/* 149 */     for (AbstractPower p : target.powers) {
/* 150 */       tmp = p.atDamageReceive(this.output, this.type);
/* 151 */       if (this.base != this.output) {
/* 152 */         this.isModified = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 157 */     for (AbstractPower p : target.powers) {
/* 158 */       tmp = p.atDamageFinalReceive(this.output, this.type);
/* 159 */       if (this.base != this.output) {
/* 160 */         this.isModified = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 165 */     if (tmp < 0.0F) {
/* 166 */       tmp = 0.0F;
/*     */     }
/* 168 */     this.output = MathUtils.floor(tmp);
/*     */   }
/*     */   
/*     */   public static int[] createDamageMatrix(int baseDamage) {
/* 172 */     return createDamageMatrix(baseDamage, false);
/*     */   }
/*     */   
/*     */   public static int[] createDamageMatrix(int baseDamage, boolean isPureDamage) {
/* 176 */     int[] retVal = new int[(AbstractDungeon.getMonsters()).monsters.size()];
/* 177 */     for (int i = 0; i < retVal.length; i++) {
/* 178 */       DamageInfo info = new DamageInfo((AbstractCreature)AbstractDungeon.player, baseDamage);
/* 179 */       if (!isPureDamage) {
/* 180 */         info.applyPowers((AbstractCreature)AbstractDungeon.player, (AbstractDungeon.getMonsters()).monsters.get(i));
/*     */       }
/* 182 */       retVal[i] = info.output;
/*     */     } 
/* 184 */     return retVal;
/*     */   }
/*     */   
/*     */   public static int[] createDamageMatrix(int baseDamage, boolean isPureDamage, boolean isOrbDamage) {
/* 188 */     int[] retVal = new int[(AbstractDungeon.getMonsters()).monsters.size()];
/* 189 */     for (int i = 0; i < retVal.length; i++) {
/* 190 */       DamageInfo info = new DamageInfo((AbstractCreature)AbstractDungeon.player, baseDamage);
/* 191 */       if (isOrbDamage && ((AbstractMonster)(AbstractDungeon.getMonsters()).monsters.get(i)).hasPower("Lockon")) {
/* 192 */         info.output = (int)(info.base * 1.5F);
/*     */       }
/* 194 */       retVal[i] = info.output;
/*     */     } 
/* 196 */     return retVal;
/*     */   }
/*     */   
/*     */   public enum DamageType {
/* 200 */     NORMAL, THORNS, HP_LOSS;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\DamageInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */