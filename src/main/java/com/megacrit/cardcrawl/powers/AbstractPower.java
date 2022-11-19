/*     */ package com.megacrit.cardcrawl.powers;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import com.megacrit.cardcrawl.vfx.combat.SilentGainPowerEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPower
/*     */   implements Comparable<AbstractPower>
/*     */ {
/*  38 */   private static final Logger logger = LogManager.getLogger(AbstractPower.class.getName());
/*     */   public static TextureAtlas atlas;
/*     */   public TextureAtlas.AtlasRegion region48;
/*     */   public TextureAtlas.AtlasRegion region128;
/*     */   private static final int RAW_W = 32;
/*     */   protected static final float POWER_STACK_FONT_SCALE = 8.0F;
/*     */   private static final float FONT_LERP = 10.0F;
/*     */   private static final float FONT_SNAP_THRESHOLD = 0.05F;
/*  46 */   protected float fontScale = 1.0F;
/*  47 */   private Color color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  48 */   private Color redColor = new Color(1.0F, 0.0F, 0.0F, 1.0F);
/*  49 */   private Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F); public AbstractCreature owner; public String name; public String description;
/*  50 */   private ArrayList<AbstractGameEffect> effect = new ArrayList<>();
/*     */   
/*     */   public String ID;
/*     */   
/*     */   public Texture img;
/*  55 */   public int amount = -1; public int priority = 5;
/*  56 */   public PowerType type = PowerType.BUFF;
/*     */   
/*     */   protected boolean isTurnBased = false;
/*     */   public boolean isPostActionPower = false;
/*     */   public boolean canGoNegative = false;
/*     */   public static String[] DESCRIPTIONS;
/*     */   
/*     */   public enum PowerType
/*     */   {
/*  65 */     BUFF, DEBUFF;
/*     */   }
/*     */   
/*     */   public static void initialize() {
/*  69 */     atlas = new TextureAtlas(Gdx.files.internal("powers/powers.atlas"));
/*     */   }
/*     */   
/*     */   protected void loadRegion(String fileName) {
/*  73 */     this.region48 = atlas.findRegion("48/" + fileName);
/*  74 */     this.region128 = atlas.findRegion("128/" + fileName);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  79 */     return "[" + this.name + "]: " + this.description;
/*     */   }
/*     */   
/*     */   public void playApplyPowerSfx() {
/*  83 */     if (this.type == PowerType.BUFF) {
/*  84 */       int roll = MathUtils.random(0, 2);
/*  85 */       if (roll == 0) {
/*  86 */         CardCrawlGame.sound.play("BUFF_1");
/*  87 */       } else if (roll == 1) {
/*  88 */         CardCrawlGame.sound.play("BUFF_2");
/*     */       } else {
/*  90 */         CardCrawlGame.sound.play("BUFF_3");
/*     */       } 
/*     */     } else {
/*     */       
/*  94 */       int roll = MathUtils.random(0, 2);
/*  95 */       if (roll == 0) {
/*  96 */         CardCrawlGame.sound.play("DEBUFF_1");
/*  97 */       } else if (roll == 1) {
/*  98 */         CardCrawlGame.sound.play("DEBUFF_2");
/*     */       } else {
/* 100 */         CardCrawlGame.sound.play("DEBUFF_3");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateParticles() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(int slot) {
/* 113 */     updateFlash();
/* 114 */     updateFontScale();
/* 115 */     updateColor();
/*     */   }
/*     */   
/*     */   protected void addToBot(AbstractGameAction action) {
/* 119 */     AbstractDungeon.actionManager.addToBottom(action);
/*     */   }
/*     */   
/*     */   protected void addToTop(AbstractGameAction action) {
/* 123 */     AbstractDungeon.actionManager.addToTop(action);
/*     */   }
/*     */   
/*     */   private void updateFlash() {
/* 127 */     for (Iterator<AbstractGameEffect> i = this.effect.iterator(); i.hasNext(); ) {
/* 128 */       AbstractGameEffect e = i.next();
/* 129 */       e.update();
/* 130 */       if (e.isDone) {
/* 131 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateColor() {
/* 137 */     if (this.color.a != 1.0F) {
/* 138 */       this.color.a = MathHelper.fadeLerpSnap(this.color.a, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateFontScale() {
/* 143 */     if (this.fontScale != 1.0F) {
/* 144 */       this.fontScale = MathUtils.lerp(this.fontScale, 1.0F, Gdx.graphics.getDeltaTime() * 10.0F);
/* 145 */       if (this.fontScale - 1.0F < 0.05F) {
/* 146 */         this.fontScale = 1.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {}
/*     */   
/*     */   public void stackPower(int stackAmount) {
/* 155 */     if (this.amount == -1) {
/* 156 */       logger.info(this.name + " does not stack");
/*     */       return;
/*     */     } 
/* 159 */     this.fontScale = 8.0F;
/* 160 */     this.amount += stackAmount;
/*     */   }
/*     */   
/*     */   public void reducePower(int reduceAmount) {
/* 164 */     if (this.amount - reduceAmount <= 0) {
/* 165 */       this.fontScale = 8.0F;
/* 166 */       this.amount = 0;
/*     */     } else {
/*     */       
/* 169 */       this.fontScale = 8.0F;
/* 170 */       this.amount -= reduceAmount;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getHoverMessage() {
/* 175 */     return this.name + ":\n" + this.description;
/*     */   }
/*     */   
/*     */   public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
/* 179 */     if (this.img != null) {
/* 180 */       sb.setColor(c);
/* 181 */       sb.draw(this.img, x - 12.0F, y - 12.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 32, 32, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 199 */       sb.setColor(c);
/* 200 */       if (Settings.isMobile) {
/* 201 */         sb.draw((TextureRegion)this.region48, x - this.region48.packedWidth / 2.0F, y - this.region48.packedHeight / 2.0F, this.region48.packedWidth / 2.0F, this.region48.packedHeight / 2.0F, this.region48.packedWidth, this.region48.packedHeight, Settings.scale * 1.17F, Settings.scale * 1.17F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 213 */         sb.draw((TextureRegion)this.region48, x - this.region48.packedWidth / 2.0F, y - this.region48.packedHeight / 2.0F, this.region48.packedWidth / 2.0F, this.region48.packedHeight / 2.0F, this.region48.packedWidth, this.region48.packedHeight, Settings.scale, Settings.scale, 0.0F);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     for (AbstractGameEffect e : this.effect) {
/* 228 */       e.render(sb, x, y);
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
/* 233 */     if (this.amount > 0) {
/* 234 */       if (!this.isTurnBased) {
/* 235 */         this.greenColor.a = c.a;
/* 236 */         c = this.greenColor;
/*     */       } 
/* 238 */       FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, 
/*     */ 
/*     */           
/* 241 */           Integer.toString(this.amount), x, y, this.fontScale, c);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 246 */     else if (this.amount < 0 && this.canGoNegative) {
/* 247 */       this.redColor.a = c.a;
/* 248 */       c = this.redColor;
/*     */       
/* 250 */       FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, 
/*     */ 
/*     */           
/* 253 */           Integer.toString(this.amount), x, y, this.fontScale, c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 262 */     return damage;
/*     */   }
/*     */   
/*     */   public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
/* 266 */     return damage;
/*     */   }
/*     */   
/*     */   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
/* 270 */     return damage;
/*     */   }
/*     */   
/*     */   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
/* 274 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
/* 279 */     return atDamageGive(damage, type);
/*     */   }
/*     */   
/*     */   public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
/* 283 */     return atDamageFinalGive(damage, type);
/*     */   }
/*     */   
/*     */   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
/* 287 */     return atDamageFinalReceive(damage, type);
/*     */   }
/*     */   
/*     */   public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
/* 291 */     return atDamageReceive(damage, damageType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void atStartOfTurn() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void duringTurn() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void atStartOfTurnPostDraw() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void atEndOfTurn(boolean isPlayer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void atEndOfRound() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onScry() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDamageAllEnemies(int[] damage) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int onHeal(int healAmount) {
/* 332 */     return healAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 342 */     return damageAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
/* 363 */     return damageAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
/* 375 */     return damageAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInflictDamage(DamageInfo info, int damageAmount, AbstractCreature target) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEvokeOrb(AbstractOrb orb) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCardDraw(AbstractCard card) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayCard(AbstractCard card, AbstractMonster m) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUseCard(AbstractCard card, UseCardAction action) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void wasHPLost(DamageInfo info, int damageAmount) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSpecificTrigger() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void triggerMarks(AbstractCard card) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onChannel(AbstractOrb orb) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void atEnergyGain() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onExhaust(AbstractCard card) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float modifyBlock(float blockAmount) {
/* 469 */     return blockAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public float modifyBlock(float blockAmount, AbstractCard card) {
/* 474 */     return modifyBlock(blockAmount);
/*     */   }
/*     */   
/*     */   public float modifyBlockLast(float blockAmount) {
/* 478 */     return blockAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGainedBlock(float blockAmount) {}
/*     */   
/*     */   public int onPlayerGainedBlock(float blockAmount) {
/* 485 */     return MathUtils.floor(blockAmount);
/*     */   }
/*     */   
/*     */   public int onPlayerGainedBlock(int blockAmount) {
/* 489 */     return blockAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGainCharge(int chargeAmount) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRemove() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnergyRecharge() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDrawOrDiscard() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onAfterCardPlayed(AbstractCard usedCard) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInitialApplication() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(AbstractPower other) {
/* 524 */     return this.priority - other.priority;
/*     */   }
/*     */   
/*     */   public void flash() {
/* 528 */     this.effect.add(new GainPowerEffect(this));
/* 529 */     AbstractDungeon.effectList.add(new FlashPowerEffect(this));
/*     */   }
/*     */   
/*     */   public void flashWithoutSound() {
/* 533 */     this.effect.add(new SilentGainPowerEffect(this));
/* 534 */     AbstractDungeon.effectList.add(new FlashPowerEffect(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {}
/*     */   
/*     */   public HashMap<String, Serializable> getLocStrings() {
/* 541 */     HashMap<String, Serializable> powerData = new HashMap<>();
/* 542 */     powerData.put("name", this.name);
/* 543 */     powerData.put("description", DESCRIPTIONS);
/* 544 */     return powerData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int onLoseHp(int damageAmount) {
/* 554 */     return damageAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onVictory() {}
/*     */   
/*     */   public boolean canPlayCard(AbstractCard card) {
/* 561 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\AbstractPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */