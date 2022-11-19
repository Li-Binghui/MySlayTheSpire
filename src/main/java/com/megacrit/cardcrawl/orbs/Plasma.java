/*     */ package com.megacrit.cardcrawl.orbs;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;
/*     */ 
/*     */ public class Plasma extends AbstractOrb {
/*     */   public static final String ORB_ID = "Plasma";
/*  23 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Plasma");
/*  24 */   public static final String[] DESC = orbString.DESCRIPTION;
/*  25 */   private float vfxTimer = 1.0F; private float vfxIntervalMin = 0.1F; private float vfxIntervalMax = 0.4F;
/*     */   
/*     */   private static final float ORB_WAVY_DIST = 0.04F;
/*     */   
/*     */   private static final float PI_4 = 12.566371F;
/*     */   
/*     */   public Plasma() {
/*  32 */     this.ID = "Plasma";
/*  33 */     this.img = ImageMaster.ORB_PLASMA;
/*  34 */     this.name = orbString.NAME;
/*  35 */     this.baseEvokeAmount = 2;
/*  36 */     this.evokeAmount = this.baseEvokeAmount;
/*  37 */     this.basePassiveAmount = 1;
/*  38 */     this.passiveAmount = this.basePassiveAmount;
/*  39 */     updateDescription();
/*  40 */     this.angle = MathUtils.random(360.0F);
/*  41 */     this.channelAnimTimer = 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/*  46 */     applyFocus();
/*  47 */     this.description = DESC[0] + this.evokeAmount + DESC[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvoke() {
/*  52 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new GainEnergyAction(this.evokeAmount));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStartOfTurn() {
/*  58 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), 0.1F));
/*  59 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainEnergyAction(this.passiveAmount));
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerEvokeAnimation() {
/*  64 */     CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
/*  65 */     AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(this.cX, this.cY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/*  70 */     super.updateAnimation();
/*  71 */     this.angle += Gdx.graphics.getDeltaTime() * 45.0F;
/*     */     
/*  73 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/*  74 */     if (this.vfxTimer < 0.0F) {
/*  75 */       AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(this.cX, this.cY));
/*  76 */       this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  82 */     this.c.a /= 2.0F;
/*  83 */     sb.setColor(this.shineColor);
/*  84 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  92 */         MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     sb.setBlendFunction(770, 1);
/* 102 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 111 */         MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     sb.setBlendFunction(770, 771);
/* 120 */     renderText(sb);
/* 121 */     this.hb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderText(SpriteBatch sb) {
/* 126 */     if (this.showEvokeValue) {
/* 127 */       FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, 
/*     */ 
/*     */           
/* 130 */           Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playChannelSFX() {
/* 140 */     CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractOrb makeCopy() {
/* 145 */     return new Plasma();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\orbs\Plasma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */