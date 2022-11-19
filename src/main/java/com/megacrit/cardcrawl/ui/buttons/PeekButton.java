/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PeekButton
/*     */ {
/*  22 */   private static final Color HOVER_BLEND_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.4F);
/*  23 */   private static final float SHOW_X = 140.0F * Settings.scale, DRAW_Y = Settings.HEIGHT / 2.0F;
/*  24 */   private static final float HIDE_X = SHOW_X - 400.0F * Settings.scale;
/*  25 */   private float current_x = HIDE_X;
/*  26 */   private float target_x = this.current_x;
/*     */   private boolean isHidden = true;
/*     */   public static boolean isPeeking = false;
/*  29 */   private float particleTimer = 0.0F;
/*     */ 
/*     */   
/*  32 */   public Hitbox hb = new Hitbox(170.0F * Settings.scale, 170.0F * Settings.scale);
/*     */   
/*     */   public PeekButton() {
/*  35 */     this.hb.move(SHOW_X, DRAW_Y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  39 */     if (!this.isHidden) {
/*  40 */       this.hb.update();
/*     */       
/*  42 */       if (InputHelper.justClickedLeft && this.hb.hovered) {
/*  43 */         this.hb.clickStarted = true;
/*  44 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*  46 */       if (this.hb.justHovered) {
/*  47 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*     */       
/*  50 */       if (InputActionSet.peek.isJustPressed() || CInputActionSet.peek.isJustPressed()) {
/*  51 */         CInputActionSet.peek.unpress();
/*  52 */         this.hb.clicked = true;
/*     */       } 
/*     */     } 
/*     */     
/*  56 */     if (isPeeking) {
/*  57 */       this.particleTimer -= Gdx.graphics.getDeltaTime();
/*  58 */       if (this.particleTimer < 0.0F) {
/*  59 */         this.particleTimer = 0.2F;
/*  60 */         AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(this.hb.cX, this.hb.cY, Color.SKY));
/*  61 */         AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(this.hb.cX, this.hb.cY, Color.WHITE));
/*     */       } 
/*     */     } 
/*     */     
/*  65 */     if (this.current_x != this.target_x) {
/*  66 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  67 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  68 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/*  74 */     this.current_x = HIDE_X;
/*  75 */     this.target_x = HIDE_X;
/*  76 */     this.isHidden = true;
/*     */   }
/*     */   
/*     */   public void hide() {
/*  80 */     if (!this.isHidden) {
/*  81 */       this.target_x = HIDE_X;
/*  82 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/*  87 */     if (this.isHidden) {
/*  88 */       isPeeking = false;
/*  89 */       this.target_x = SHOW_X;
/*  90 */       this.isHidden = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  95 */     sb.setColor(Color.WHITE);
/*  96 */     renderButton(sb);
/*     */     
/*  98 */     if (isPeeking) {
/*  99 */       sb.setBlendFunction(770, 1);
/* 100 */       sb.setColor(new Color(0.6F, 1.0F, 1.0F, 1.0F));
/* 101 */       float derp = Interpolation.swingOut.apply(1.0F, 1.1F, 
/*     */ 
/*     */           
/* 104 */           MathUtils.cosDeg((float)(System.currentTimeMillis() / 4L % 360L)) / 12.0F);
/* 105 */       sb.draw(ImageMaster.PEEK_BUTTON, this.current_x - 64.0F, Settings.HEIGHT / 2.0F - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * derp, Settings.scale * derp, 0.0F, 0, 0, 128, 128, false, false);
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
/* 122 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 125 */     if (this.hb.hovered && !this.hb.clickStarted) {
/* 126 */       sb.setBlendFunction(770, 1);
/* 127 */       sb.setColor(HOVER_BLEND_COLOR);
/* 128 */       renderButton(sb);
/* 129 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 132 */     if (this.hb.clicked) {
/* 133 */       this.hb.clicked = false;
/* 134 */       isPeeking = !isPeeking;
/*     */       
/* 136 */       if (isPeeking) {
/* 137 */         AbstractDungeon.overlayMenu.hideBlackScreen();
/* 138 */         AbstractDungeon.dynamicBanner.hide();
/*     */       } else {
/* 140 */         AbstractDungeon.overlayMenu.showBlackScreen(0.75F);
/* 141 */         if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
/* 142 */           AbstractDungeon.dynamicBanner.appear();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     renderControllerUi(sb);
/*     */     
/* 149 */     if (!this.isHidden) {
/* 150 */       this.hb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 155 */     sb.draw(ImageMaster.PEEK_BUTTON, this.current_x - 64.0F, Settings.HEIGHT / 2.0F - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderControllerUi(SpriteBatch sb) {
/* 175 */     if (Settings.isControllerMode && !this.isHidden) {
/* 176 */       sb.setColor(Color.WHITE);
/* 177 */       sb.draw(CInputActionSet.peek
/* 178 */           .getKeyImg(), 20.0F * Settings.scale, this.hb.cY - 56.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\PeekButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */