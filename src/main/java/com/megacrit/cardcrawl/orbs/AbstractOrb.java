/*     */ package com.megacrit.cardcrawl.orbs;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.vfx.BobEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractOrb
/*     */ {
/*     */   public String name;
/*     */   public String description;
/*     */   public String ID;
/*  28 */   protected ArrayList<PowerTip> tips = new ArrayList<>();
/*  29 */   public int evokeAmount = 0; public int passiveAmount = 0;
/*  30 */   protected int baseEvokeAmount = 0; protected int basePassiveAmount = 0;
/*     */ 
/*     */   
/*  33 */   public float cX = 0.0F; public float cY = 0.0F; public float tX;
/*  34 */   protected Color c = Settings.CREAM_COLOR.cpy(); public float tY;
/*  35 */   protected Color shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   protected static final int W = 96;
/*  37 */   public Hitbox hb = new Hitbox(96.0F * Settings.scale, 96.0F * Settings.scale);
/*  38 */   protected Texture img = null;
/*  39 */   protected BobEffect bobEffect = new BobEffect(3.0F * Settings.scale, 3.0F);
/*  40 */   protected static final float NUM_X_OFFSET = 20.0F * Settings.scale;
/*  41 */   protected static final float NUM_Y_OFFSET = -12.0F * Settings.scale; protected float angle; protected float scale;
/*  42 */   protected float fontScale = 0.7F;
/*     */   protected boolean showEvokeValue = false;
/*     */   protected static final float CHANNEL_IN_TIME = 0.5F;
/*  45 */   protected float channelAnimTimer = 0.5F;
/*     */   
/*     */   public abstract void updateDescription();
/*     */   
/*     */   public abstract void onEvoke();
/*     */   
/*     */   public static AbstractOrb getRandomOrb(boolean useCardRng) {
/*  52 */     ArrayList<AbstractOrb> orbs = new ArrayList<>();
/*  53 */     orbs.add(new Dark());
/*  54 */     orbs.add(new Frost());
/*  55 */     orbs.add(new Lightning());
/*  56 */     orbs.add(new Plasma());
/*     */     
/*  58 */     if (useCardRng) {
/*  59 */       return orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1));
/*     */     }
/*  61 */     return orbs.get(MathUtils.random(orbs.size() - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStartOfTurn() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEndOfTurn() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyFocus() {
/*  75 */     AbstractPower power = AbstractDungeon.player.getPower("Focus");
/*  76 */     if (power != null && !this.ID.equals("Plasma")) {
/*  77 */       this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
/*  78 */       this.evokeAmount = Math.max(0, this.baseEvokeAmount + power.amount);
/*     */     } else {
/*  80 */       this.passiveAmount = this.basePassiveAmount;
/*  81 */       this.evokeAmount = this.baseEvokeAmount;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int applyLockOn(AbstractCreature target, int dmg) {
/*  86 */     int retVal = dmg;
/*  87 */     if (target.hasPower("Lockon")) {
/*  88 */       retVal = (int)(retVal * 1.5F);
/*     */     }
/*  90 */     return retVal;
/*     */   }
/*     */   
/*     */   public abstract AbstractOrb makeCopy();
/*     */   
/*     */   public void update() {
/*  96 */     this.hb.update();
/*  97 */     if (this.hb.hovered) {
/*  98 */       TipHelper.renderGenericTip(this.tX + 96.0F * Settings.scale, this.tY + 64.0F * Settings.scale, this.name, this.description);
/*     */     }
/* 100 */     this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7F);
/*     */   }
/*     */   
/*     */   public void updateAnimation() {
/* 104 */     this.bobEffect.update();
/* 105 */     this.cX = MathHelper.orbLerpSnap(this.cX, AbstractDungeon.player.animX + this.tX);
/* 106 */     this.cY = MathHelper.orbLerpSnap(this.cY, AbstractDungeon.player.animY + this.tY);
/*     */     
/* 108 */     if (this.channelAnimTimer != 0.0F) {
/* 109 */       this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
/* 110 */       if (this.channelAnimTimer < 0.0F) {
/* 111 */         this.channelAnimTimer = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 115 */     this.c.a = Interpolation.pow2In.apply(1.0F, 0.01F, this.channelAnimTimer / 0.5F);
/* 116 */     this.scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, this.channelAnimTimer / 0.5F);
/*     */   }
/*     */   
/*     */   public void setSlot(int slotNum, int maxOrbs) {
/* 120 */     float dist = 160.0F * Settings.scale + maxOrbs * 10.0F * Settings.scale;
/* 121 */     float angle = 100.0F + maxOrbs * 12.0F;
/* 122 */     float offsetAngle = angle / 2.0F;
/* 123 */     angle *= slotNum / (maxOrbs - 1.0F);
/* 124 */     angle += 90.0F - offsetAngle;
/* 125 */     this.tX = dist * MathUtils.cosDeg(angle) + AbstractDungeon.player.drawX;
/* 126 */     this.tY = dist * MathUtils.sinDeg(angle) + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0F;
/*     */     
/* 128 */     if (maxOrbs == 1) {
/* 129 */       this.tX = AbstractDungeon.player.drawX;
/* 130 */       this.tY = 160.0F * Settings.scale + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0F;
/*     */     } 
/*     */     
/* 133 */     this.hb.move(this.tX, this.tY);
/*     */   }
/*     */   
/*     */   public abstract void render(SpriteBatch paramSpriteBatch);
/*     */   
/*     */   protected void renderText(SpriteBatch sb) {
/* 139 */     if (!(this instanceof EmptyOrbSlot)) {
/* 140 */       if (this.showEvokeValue) {
/* 141 */         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, 
/*     */ 
/*     */             
/* 144 */             Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 150 */         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, 
/*     */ 
/*     */             
/* 153 */             Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, this.c, this.fontScale);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void triggerEvokeAnimation() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void showEvokeValue() {
/* 166 */     this.showEvokeValue = true;
/* 167 */     this.fontScale = 1.5F;
/*     */   }
/*     */   
/*     */   public void hideEvokeValues() {
/* 171 */     this.showEvokeValue = false;
/*     */   }
/*     */   
/*     */   public abstract void playChannelSFX();
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\orbs\AbstractOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */