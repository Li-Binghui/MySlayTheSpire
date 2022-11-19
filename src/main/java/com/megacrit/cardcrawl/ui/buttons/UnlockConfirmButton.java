/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.unlock.AbstractUnlock;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ 
/*     */ public class UnlockConfirmButton
/*     */ {
/*  23 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Unlock Confirm Button");
/*  24 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final int W = 512;
/*     */   private static final int H = 256;
/*  28 */   private static final float TAKE_Y = Settings.HEIGHT / 2.0F - 410.0F * Settings.scale;
/*  29 */   private static final float X = Settings.WIDTH / 2.0F;
/*  30 */   private Color hoverColor = Color.WHITE.cpy();
/*  31 */   private Color textColor = Color.WHITE.cpy();
/*  32 */   private Color btnColor = Color.WHITE.cpy();
/*  33 */   private float target_a = 0.0F;
/*     */   private boolean done = false;
/*  35 */   private float animTimer = 0.0F;
/*     */   private static final float ANIM_TIME = 0.4F;
/*  37 */   private float scale = 0.8F;
/*     */   
/*     */   private static final float HOVER_BRIGHTNESS = 0.33F;
/*     */   
/*     */   private static final float SCALE_START = 0.6F;
/*  42 */   private String buttonText = "NOT_SET";
/*     */ 
/*     */   
/*  45 */   private static final float HITBOX_W = 260.0F * Settings.scale; private static final float HITBOX_H = 80.0F * Settings.scale;
/*  46 */   public Hitbox hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_H);
/*     */   
/*     */   public UnlockConfirmButton() {
/*  49 */     this.buttonText = TEXT[0];
/*  50 */     this.hb.move(Settings.WIDTH / 2.0F, TAKE_Y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  54 */     animateIn();
/*     */     
/*  56 */     if (!this.done && this.animTimer < 0.2F) {
/*  57 */       this.hb.update();
/*     */     }
/*     */     
/*  60 */     if (this.hb.hovered && !this.done) {
/*  61 */       this.hoverColor.a = 0.33F;
/*     */     } else {
/*  63 */       this.hoverColor.a = MathHelper.fadeLerpSnap(this.hoverColor.a, 0.0F);
/*     */     } 
/*     */     
/*  66 */     if (this.hb.justHovered) {
/*  67 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/*  70 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  71 */       this.hb.clickStarted = true;
/*  72 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*     */     } 
/*     */     
/*  75 */     if (this.hb.clicked || CInputActionSet.select.isJustPressed()) {
/*  76 */       CInputActionSet.select.unpress();
/*  77 */       this.hb.clicked = false;
/*  78 */       this.hb.hovered = false;
/*     */ 
/*     */       
/*  81 */       if (AbstractDungeon.unlockScreen.unlock != null) {
/*  82 */         UnlockTracker.hardUnlock(AbstractDungeon.unlockScreen.unlock.key);
/*  83 */         CardCrawlGame.sound.stop("UNLOCK_SCREEN", AbstractDungeon.unlockScreen.id);
/*     */       
/*     */       }
/*  86 */       else if (AbstractDungeon.unlocks != null) {
/*  87 */         for (AbstractUnlock u : AbstractDungeon.unlocks) {
/*  88 */           UnlockTracker.hardUnlock(u.key);
/*     */         }
/*     */       } 
/*     */       
/*  92 */       InputHelper.justClickedLeft = false;
/*  93 */       hide();
/*     */       
/*  95 */       if (!AbstractDungeon.is_victory) {
/*  96 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
/*     */       } else {
/*  98 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.VICTORY;
/*     */       } 
/* 100 */       AbstractDungeon.closeCurrentScreen();
/*     */     } 
/*     */     
/* 103 */     this.textColor.a = MathHelper.fadeLerpSnap(this.textColor.a, this.target_a);
/* 104 */     this.btnColor.a = this.textColor.a;
/*     */   }
/*     */   
/*     */   private void animateIn() {
/* 108 */     if (this.animTimer != 0.0F) {
/* 109 */       this.animTimer -= Gdx.graphics.getDeltaTime();
/* 110 */       if (this.animTimer < 0.0F) {
/* 111 */         this.animTimer = 0.0F;
/*     */       }
/* 113 */       this.scale = Interpolation.elasticIn.apply(1.0F, 0.6F, this.animTimer / 0.4F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hide() {
/* 118 */     this.textColor = Color.LIGHT_GRAY.cpy();
/* 119 */     this.done = true;
/*     */   }
/*     */   
/*     */   public void show() {
/* 123 */     this.textColor = Color.WHITE.cpy();
/* 124 */     this.animTimer = 0.4F;
/* 125 */     this.hoverColor.a = 0.0F;
/* 126 */     this.textColor.a = 0.0F;
/* 127 */     this.target_a = 1.0F;
/* 128 */     this.scale = 0.6F;
/* 129 */     this.done = false;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 133 */     sb.setColor(this.btnColor);
/* 134 */     renderButton(sb);
/*     */     
/* 136 */     if (!this.hb.clickStarted && !this.done) {
/* 137 */       sb.setBlendFunction(770, 1);
/* 138 */       sb.setColor(this.hoverColor);
/* 139 */       renderButton(sb);
/* 140 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 143 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, Settings.WIDTH / 2.0F, TAKE_Y, this.textColor, this.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 154 */     if (this.hb.clickStarted) {
/* 155 */       sb.setColor(Color.LIGHT_GRAY);
/*     */     }
/* 157 */     if (!this.done) {
/* 158 */       sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, X - 256.0F, TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, this.scale * Settings.scale, this.scale * Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 176 */       sb.draw(ImageMaster.REWARD_SCREEN_TAKE_USED_BUTTON, X - 256.0F, TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, this.scale * Settings.scale, this.scale * Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 195 */     if (Settings.isControllerMode) {
/* 196 */       sb.draw(CInputActionSet.select
/* 197 */           .getKeyImg(), X - 32.0F - 130.0F * Settings.scale, TAKE_Y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\UnlockConfirmButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */