/*     */ package com.megacrit.cardcrawl.vfx.scene;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class InteractableTorchEffect extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*  19 */   private float particleTimer1 = 0.0F; private Hitbox hb; private boolean activated = true;
/*     */   private static final float PARTICLE_EMIT_INTERVAL = 0.1F;
/*     */   private static TextureAtlas.AtlasRegion img;
/*  22 */   private TorchSize size = TorchSize.M;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractableTorchEffect(float x, float y, TorchSize size) {
/*  31 */     if (img == null) {
/*  32 */       img = ImageMaster.vfxAtlas.findRegion("env/torch");
/*     */     }
/*  34 */     this.size = size;
/*  35 */     this.x = x;
/*  36 */     this.y = y;
/*     */     
/*  38 */     if (Settings.isLetterbox) {
/*  39 */       this.y += Settings.LETTERBOX_OFFSET_Y;
/*     */     }
/*  41 */     this.hb = new Hitbox(50.0F * Settings.scale, 60.0F * Settings.scale);
/*  42 */     this.hb.move(x, this.y);
/*  43 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.4F);
/*     */     
/*  45 */     switch (size) {
/*     */       case S:
/*  47 */         this.scale = Settings.scale * 0.6F;
/*     */         break;
/*     */       case M:
/*  50 */         this.scale = Settings.scale;
/*     */         break;
/*     */       case L:
/*  53 */         this.scale = Settings.scale * 1.4F;
/*     */         break;
/*     */     } 
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
/*     */   public InteractableTorchEffect(float x, float y) {
/*  67 */     this(x, y, TorchSize.M);
/*     */   }
/*     */   
/*     */   public enum TorchSize {
/*  71 */     S, M, L;
/*     */   }
/*     */   
/*     */   public void update() {
/*  75 */     this.hb.update();
/*  76 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  77 */       this.activated = !this.activated;
/*  78 */       if (this.activated) {
/*  79 */         CardCrawlGame.sound.playA("ATTACK_FIRE", 0.4F);
/*     */       } else {
/*  81 */         CardCrawlGame.sound.play("SCENE_TORCH_EXTINGUISH");
/*     */       } 
/*     */     } 
/*     */     
/*  85 */     if (this.activated && !Settings.DISABLE_EFFECTS) {
/*  86 */       this.particleTimer1 -= Gdx.graphics.getDeltaTime();
/*  87 */       if (this.particleTimer1 < 0.0F) {
/*  88 */         this.particleTimer1 = 0.1F;
/*  89 */         switch (this.size) {
/*     */           case S:
/*  91 */             AbstractDungeon.effectsQueue.add(new TorchParticleSEffect(this.x, this.y - 10.0F * Settings.scale));
/*  92 */             AbstractDungeon.effectsQueue.add(new LightFlareSEffect(this.x, this.y - 10.0F * Settings.scale));
/*     */             break;
/*     */           case M:
/*  95 */             AbstractDungeon.effectsQueue.add(new TorchParticleMEffect(this.x, this.y));
/*  96 */             AbstractDungeon.effectsQueue.add(new LightFlareMEffect(this.x, this.y));
/*     */             break;
/*     */           case L:
/*  99 */             AbstractDungeon.effectsQueue.add(new TorchParticleLEffect(this.x, this.y + 14.0F * Settings.scale));
/* 100 */             AbstractDungeon.effectsQueue.add(new LightFlareLEffect(this.x, this.y + 14.0F * Settings.scale));
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 112 */     if (Settings.DISABLE_EFFECTS) {
/*     */       return;
/*     */     }
/*     */     
/* 116 */     sb.setColor(this.color);
/* 117 */     sb.draw((TextureRegion)img, this.x - (img.packedWidth / 2), this.y - (img.packedHeight / 2) - 24.0F * Settings.yScale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 128 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\InteractableTorchEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */