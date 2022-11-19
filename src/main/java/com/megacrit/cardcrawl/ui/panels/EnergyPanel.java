/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;
/*     */ 
/*     */ public class EnergyPanel
/*     */   extends AbstractPanel
/*     */ {
/*  24 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Energy Panel Tip");
/*     */   
/*  26 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  27 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*     */   
/*     */   private static final int RAW_W = 256;
/*  30 */   private static final Color ENERGY_TEXT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);
/*     */   
/*  32 */   public static float fontScale = 1.0F;
/*     */   public static final float FONT_POP_SCALE = 2.0F;
/*  34 */   public static int totalCount = 0;
/*  35 */   private Hitbox tipHitbox = new Hitbox(0.0F, 0.0F, 120.0F * Settings.scale, 120.0F * Settings.scale);
/*     */   
/*     */   private Texture gainEnergyImg;
/*     */   
/*  39 */   private float energyVfxAngle = 0.0F; private float energyVfxScale = Settings.scale;
/*  40 */   private Color energyVfxColor = Color.WHITE.cpy();
/*  41 */   public static float energyVfxTimer = 0.0F;
/*     */   public static final float ENERGY_VFX_TIME = 2.0F;
/*     */   private static final float VFX_ROTATE_SPEED = -30.0F;
/*     */   
/*     */   public EnergyPanel() {
/*  46 */     super(198.0F * Settings.xScale, 190.0F * Settings.yScale, -480.0F * Settings.scale, 200.0F * Settings.yScale, 12.0F * Settings.scale, -12.0F * Settings.scale, null, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     this.gainEnergyImg = AbstractDungeon.player.getEnergyImage();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setEnergy(int energy) {
/*  61 */     totalCount = energy;
/*     */     
/*  63 */     AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
/*  64 */     energyVfxTimer = 2.0F;
/*  65 */     fontScale = 2.0F;
/*     */   }
/*     */   
/*     */   public static void addEnergy(int e) {
/*  69 */     totalCount += e;
/*     */ 
/*     */     
/*  72 */     if (totalCount >= 9) {
/*  73 */       UnlockTracker.unlockAchievement("ADRENALINE");
/*     */     }
/*     */     
/*  76 */     if (totalCount > 999) {
/*  77 */       totalCount = 999;
/*     */     }
/*     */     
/*  80 */     AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
/*  81 */     fontScale = 2.0F;
/*  82 */     energyVfxTimer = 2.0F;
/*     */   }
/*     */   
/*     */   public static void useEnergy(int e) {
/*  86 */     totalCount -= e;
/*     */     
/*  88 */     if (totalCount < 0) {
/*  89 */       totalCount = 0;
/*     */     }
/*     */     
/*  92 */     if (e != 0) {
/*  93 */       fontScale = 2.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void update() {
/*  98 */     AbstractDungeon.player.updateOrb(totalCount);
/*  99 */     updateVfx();
/*     */     
/* 101 */     if (fontScale != 1.0F) {
/* 102 */       fontScale = MathHelper.scaleLerpSnap(fontScale, 1.0F);
/*     */     }
/*     */     
/* 105 */     this.tipHitbox.update();
/* 106 */     if (this.tipHitbox.hovered && !AbstractDungeon.isScreenUp) {
/* 107 */       AbstractDungeon.overlayMenu.hoveredTip = true;
/*     */     }
/*     */     
/* 110 */     if (Settings.isDebug) {
/* 111 */       if (InputHelper.scrolledDown) {
/* 112 */         addEnergy(1);
/* 113 */       } else if (InputHelper.scrolledUp && totalCount > 0) {
/* 114 */         useEnergy(1);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateVfx() {
/* 120 */     if (energyVfxTimer != 0.0F) {
/* 121 */       this.energyVfxColor.a = Interpolation.exp10In.apply(0.5F, 0.0F, 1.0F - energyVfxTimer / 2.0F);
/* 122 */       this.energyVfxAngle += Gdx.graphics.getDeltaTime() * -30.0F;
/* 123 */       this.energyVfxScale = Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - energyVfxTimer / 2.0F);
/*     */ 
/*     */ 
/*     */       
/* 127 */       energyVfxTimer -= Gdx.graphics.getDeltaTime();
/* 128 */       if (energyVfxTimer < 0.0F) {
/* 129 */         energyVfxTimer = 0.0F;
/* 130 */         this.energyVfxColor.a = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 137 */     this.tipHitbox.move(this.current_x, this.current_y);
/*     */ 
/*     */     
/* 140 */     renderOrb(sb);
/* 141 */     renderVfx(sb);
/*     */ 
/*     */ 
/*     */     
/* 145 */     String energyMsg = totalCount + "/" + AbstractDungeon.player.energy.energy;
/*     */     
/* 147 */     AbstractDungeon.player.getEnergyNumFont().getData().setScale(fontScale);
/* 148 */     FontHelper.renderFontCentered(sb, AbstractDungeon.player
/*     */         
/* 150 */         .getEnergyNumFont(), energyMsg, this.current_x, this.current_y, ENERGY_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     this.tipHitbox.render(sb);
/* 157 */     if (this.tipHitbox.hovered && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp)
/*     */     {
/* 159 */       TipHelper.renderGenericTip(50.0F * Settings.scale, 380.0F * Settings.scale, LABEL[0], MSG[0]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderOrb(SpriteBatch sb) {
/* 164 */     if (totalCount == 0) {
/* 165 */       AbstractDungeon.player.renderOrb(sb, false, this.current_x, this.current_y);
/*     */     } else {
/* 167 */       AbstractDungeon.player.renderOrb(sb, true, this.current_x, this.current_y);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderVfx(SpriteBatch sb) {
/* 177 */     if (energyVfxTimer != 0.0F) {
/* 178 */       sb.setBlendFunction(770, 1);
/* 179 */       sb.setColor(this.energyVfxColor);
/* 180 */       sb.draw(this.gainEnergyImg, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, -this.energyVfxAngle + 50.0F, 0, 0, 256, 256, true, false);
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
/* 197 */       sb.draw(this.gainEnergyImg, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, this.energyVfxAngle, 0, 0, 256, 256, false, false);
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
/* 214 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getCurrentEnergy() {
/* 219 */     if (AbstractDungeon.player == null) {
/* 220 */       return 0;
/*     */     }
/*     */     
/* 223 */     return totalCount;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\EnergyPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */