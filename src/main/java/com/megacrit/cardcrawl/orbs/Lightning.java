/*     */ package com.megacrit.cardcrawl.orbs;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbPassiveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*     */ 
/*     */ 
/*     */ public class Lightning
/*     */   extends AbstractOrb
/*     */ {
/*     */   public static final String ORB_ID = "Lightning";
/*  32 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Lightning");
/*  33 */   private float vfxTimer = 1.0F;
/*     */   
/*     */   private static final float PI_DIV_16 = 0.19634955F;
/*     */   
/*     */   private static final float ORB_WAVY_DIST = 0.05F;
/*     */   private static final float PI_4 = 12.566371F;
/*     */   private static final float ORB_BORDER_SCALE = 1.2F;
/*     */   
/*     */   public Lightning() {
/*  42 */     this.ID = "Lightning";
/*  43 */     this.img = ImageMaster.ORB_LIGHTNING;
/*  44 */     this.name = orbString.NAME;
/*  45 */     this.baseEvokeAmount = 8;
/*  46 */     this.evokeAmount = this.baseEvokeAmount;
/*  47 */     this.basePassiveAmount = 3;
/*  48 */     this.passiveAmount = this.basePassiveAmount;
/*  49 */     updateDescription();
/*  50 */     this.angle = MathUtils.random(360.0F);
/*  51 */     this.channelAnimTimer = 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/*  56 */     applyFocus();
/*  57 */     this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEvoke() {
/*  63 */     if (AbstractDungeon.player.hasPower("Electro")) {
/*  64 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new LightningOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS), true));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  69 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new LightningOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS), false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEndOfTurn() {
/*  78 */     if (AbstractDungeon.player.hasPower("Electro")) {
/*  79 */       float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/*  80 */       if (Settings.FAST_MODE) {
/*  81 */         speedTime = 0.0F;
/*     */       }
/*  83 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
/*     */ 
/*     */       
/*  86 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new LightningOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS), true));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  91 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new LightningOrbPassiveAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS), this, false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void triggerPassiveEffect(DamageInfo info, boolean hitAll) {
/* 101 */     if (!hitAll) {
/* 102 */       AbstractMonster abstractMonster = AbstractDungeon.getRandomMonster();
/*     */       
/* 104 */       if (abstractMonster != null) {
/* 105 */         float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/* 106 */         if (Settings.FAST_MODE) {
/* 107 */           speedTime = 0.0F;
/*     */         }
/*     */         
/* 110 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, info, AbstractGameAction.AttackEffect.NONE, true));
/* 111 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
/*     */         
/* 113 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(((AbstractCreature)abstractMonster).drawX, ((AbstractCreature)abstractMonster).drawY), speedTime));
/*     */         
/* 115 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/*     */       } 
/*     */     } else {
/* 118 */       float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
/* 119 */       if (Settings.FAST_MODE) {
/* 120 */         speedTime = 0.0F;
/*     */       }
/*     */       
/* 123 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
/*     */       
/* 125 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, 
/*     */ 
/*     */             
/* 128 */             DamageInfo.createDamageMatrix(info.base, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
/*     */ 
/*     */ 
/*     */       
/* 132 */       for (AbstractMonster m3 : (AbstractDungeon.getMonsters()).monsters) {
/* 133 */         if (!m3.isDeadOrEscaped() && !m3.halfDead) {
/* 134 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(m3.drawX, m3.drawY), speedTime));
/*     */         }
/*     */       } 
/*     */       
/* 138 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerEvokeAnimation() {
/* 144 */     CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE", 0.1F);
/* 145 */     AbstractDungeon.effectsQueue.add(new LightningOrbActivateEffect(this.cX, this.cY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/* 150 */     super.updateAnimation();
/* 151 */     this.angle += Gdx.graphics.getDeltaTime() * 180.0F;
/*     */     
/* 153 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 154 */     if (this.vfxTimer < 0.0F) {
/* 155 */       AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
/* 156 */       if (MathUtils.randomBoolean()) {
/* 157 */         AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
/*     */       }
/* 159 */       this.vfxTimer = MathUtils.random(0.15F, 0.8F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 165 */     this.c.a /= 2.0F;
/* 166 */     sb.setColor(this.shineColor);
/* 167 */     sb.setBlendFunction(770, 1);
/* 168 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 176 */         MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, this.scale * 1.2F, this.angle, 0, 0, 96, 96, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 194 */         MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, -this.angle, 0, 0, 96, 96, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     sb.setBlendFunction(770, 771);
/* 203 */     sb.setColor(this.c);
/* 204 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 96, 96, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     renderText(sb);
/* 222 */     this.hb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playChannelSFX() {
/* 227 */     CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractOrb makeCopy() {
/* 232 */     return new Lightning();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\orbs\Lightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */