/*     */ package com.megacrit.cardcrawl.orbs;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DarkOrbEvokeAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*     */ 
/*     */ public class Dark
/*     */   extends AbstractOrb
/*     */ {
/*     */   public static final String ORB_ID = "Dark";
/*  27 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Dark");
/*  28 */   public static final String[] DESC = orbString.DESCRIPTION;
/*     */   private static final float ORB_BORDER_SCALE = 1.2F;
/*  30 */   private float vfxTimer = 0.5F;
/*     */   private static final float VFX_INTERVAL_TIME = 0.25F;
/*     */   
/*     */   public Dark() {
/*  34 */     this.ID = "Dark";
/*  35 */     this.img = ImageMaster.ORB_DARK;
/*  36 */     this.name = orbString.NAME;
/*  37 */     this.baseEvokeAmount = 6;
/*  38 */     this.evokeAmount = this.baseEvokeAmount;
/*  39 */     this.basePassiveAmount = 6;
/*  40 */     this.passiveAmount = this.basePassiveAmount;
/*  41 */     updateDescription();
/*  42 */     this.channelAnimTimer = 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/*  47 */     applyFocus();
/*  48 */     this.description = DESC[0] + this.passiveAmount + DESC[1] + this.evokeAmount + DESC[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvoke() {
/*  53 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new DarkOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEndOfTurn() {
/*  61 */     float speedTime = 0.6F / AbstractDungeon.player.orbs.size();
/*  62 */     if (Settings.FAST_MODE) {
/*  63 */       speedTime = 0.0F;
/*     */     }
/*     */     
/*  66 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.DARK), speedTime));
/*     */     
/*  68 */     this.evokeAmount += this.passiveAmount;
/*  69 */     updateDescription();
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerEvokeAnimation() {
/*  74 */     CardCrawlGame.sound.play("ORB_DARK_EVOKE", 0.1F);
/*  75 */     AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(this.cX, this.cY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyFocus() {
/*  80 */     AbstractPower power = AbstractDungeon.player.getPower("Focus");
/*  81 */     if (power != null) {
/*  82 */       this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
/*     */     } else {
/*  84 */       this.passiveAmount = this.basePassiveAmount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/*  90 */     super.updateAnimation();
/*  91 */     this.angle += Gdx.graphics.getDeltaTime() * 120.0F;
/*     */     
/*  93 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/*  94 */     if (this.vfxTimer < 0.0F) {
/*  95 */       AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(this.cX, this.cY));
/*  96 */       this.vfxTimer = 0.25F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 102 */     sb.setColor(this.c);
/* 103 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
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
/* 120 */     this.c.a /= 3.0F;
/* 121 */     sb.setColor(this.shineColor);
/* 122 */     sb.setBlendFunction(770, 1);
/* 123 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale * 1.2F, this.angle / 1.2F, 0, 0, 96, 96, false, false);
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
/* 140 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.5F, this.scale * 1.5F, this.angle / 1.4F, 0, 0, 96, 96, false, false);
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
/* 157 */     sb.setBlendFunction(770, 771);
/* 158 */     renderText(sb);
/* 159 */     this.hb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderText(SpriteBatch sb) {
/* 164 */     FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, 
/*     */ 
/*     */         
/* 167 */         Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, 
/*     */ 
/*     */         
/* 175 */         Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playChannelSFX() {
/* 184 */     CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractOrb makeCopy() {
/* 189 */     return new Dark();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\orbs\Dark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */