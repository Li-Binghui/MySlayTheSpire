/*     */ package com.megacrit.cardcrawl.orbs;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.FrostOrbActivateEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.FrostOrbPassiveEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*     */ 
/*     */ public class Frost extends AbstractOrb {
/*  20 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Frost"); public static final String ORB_ID = "Frost"; private boolean hFlip1;
/*     */   private boolean hFlip2;
/*  22 */   private float vfxTimer = 1.0F, vfxIntervalMin = 0.15F, vfxIntervalMax = 0.8F;
/*     */   
/*     */   public Frost() {
/*  25 */     this.hFlip1 = MathUtils.randomBoolean();
/*  26 */     this.hFlip2 = MathUtils.randomBoolean();
/*     */     
/*  28 */     this.ID = "Frost";
/*  29 */     this.name = orbString.NAME;
/*  30 */     this.baseEvokeAmount = 5;
/*  31 */     this.evokeAmount = this.baseEvokeAmount;
/*  32 */     this.basePassiveAmount = 2;
/*  33 */     this.passiveAmount = this.basePassiveAmount;
/*  34 */     updateDescription();
/*  35 */     this.channelAnimTimer = 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/*  40 */     applyFocus();
/*  41 */     this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEvoke() {
/*  47 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.evokeAmount));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/*  53 */     super.updateAnimation();
/*  54 */     this.angle += Gdx.graphics.getDeltaTime() * 180.0F;
/*     */     
/*  56 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/*  57 */     if (this.vfxTimer < 0.0F) {
/*  58 */       AbstractDungeon.effectList.add(new FrostOrbPassiveEffect(this.cX, this.cY));
/*  59 */       if (MathUtils.randomBoolean()) {
/*  60 */         AbstractDungeon.effectList.add(new FrostOrbPassiveEffect(this.cX, this.cY));
/*     */       }
/*  62 */       this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEndOfTurn() {
/*  68 */     float speedTime = 0.6F / AbstractDungeon.player.orbs.size();
/*  69 */     if (Settings.FAST_MODE) {
/*  70 */       speedTime = 0.0F;
/*     */     }
/*     */     
/*  73 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), speedTime));
/*     */     
/*  75 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.passiveAmount, true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void triggerEvokeAnimation() {
/*  81 */     CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.1F);
/*  82 */     AbstractDungeon.effectsQueue.add(new FrostOrbActivateEffect(this.cX, this.cY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  87 */     sb.setColor(this.c);
/*  88 */     sb.draw(ImageMaster.FROST_ORB_RIGHT, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip1, false);
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
/* 105 */     sb.draw(ImageMaster.FROST_ORB_LEFT, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F - this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip1, false);
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
/*     */ 
/*     */     
/* 124 */     sb.draw(ImageMaster.FROST_ORB_MIDDLE, this.cX - 48.0F - this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 2.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip2, false);
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
/* 141 */     renderText(sb);
/* 142 */     this.hb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playChannelSFX() {
/* 147 */     CardCrawlGame.sound.play("ORB_FROST_CHANNEL", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractOrb makeCopy() {
/* 152 */     return new Frost();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\orbs\Frost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */