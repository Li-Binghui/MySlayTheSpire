/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class FlameAnimationEffect extends AbstractGameEffect {
/* 13 */   public Texture img = null;
/*    */   private static final int W = 256;
/*    */   private static final float DUR = 0.5F;
/*    */   private static boolean alternator = true;
/*    */   private boolean flipped = false;
/*    */   private Hitbox nodeHb;
/*    */   private float offsetX;
/*    */   private float offsetY;
/*    */   
/*    */   public FlameAnimationEffect(Hitbox hb) {
/* 23 */     this.nodeHb = hb;
/*    */     
/* 25 */     this.startingDuration = 0.5F;
/* 26 */     this.duration = 0.5F;
/*    */     
/* 28 */     this.scale = MathUtils.random(0.9F, 1.3F) * Settings.scale;
/* 29 */     this.rotation = MathUtils.random(-30.0F, 30.0F);
/* 30 */     this.offsetX = MathUtils.random(0.0F, 8.0F) * Settings.scale;
/* 31 */     this.offsetY = MathUtils.random(-3.0F, 14.0F) * Settings.scale;
/* 32 */     alternator = !alternator;
/* 33 */     this.flipped = alternator;
/*    */     
/* 35 */     if (!alternator) {
/* 36 */       this.offsetX = -this.offsetX;
/*    */     }
/*    */     
/* 39 */     this.color = new Color(0.34F, 0.34F, 0.34F, 1.0F);
/* 40 */     this.color = this.color.cpy();
/*    */     
/* 42 */     this.img = ImageMaster.FLAME_ANIM_1;
/*    */   }
/*    */   
/*    */   public void update() {
/* 46 */     this.color.a = this.duration / 0.5F;
/*    */     
/* 48 */     if (this.duration < 0.1F) {
/* 49 */       this.img = null;
/* 50 */     } else if (this.duration < 0.0F) {
/* 51 */       this.img = ImageMaster.FLAME_ANIM_6;
/* 52 */     } else if (this.duration < 0.1F) {
/* 53 */       this.img = ImageMaster.FLAME_ANIM_5;
/* 54 */     } else if (this.duration < 0.2F) {
/* 55 */       this.img = ImageMaster.FLAME_ANIM_4;
/* 56 */     } else if (this.duration < 0.3F) {
/* 57 */       this.img = ImageMaster.FLAME_ANIM_3;
/* 58 */     } else if (this.duration < 0.4F) {
/* 59 */       this.img = ImageMaster.FLAME_ANIM_2;
/*    */     } 
/*    */     
/* 62 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 63 */     if (this.duration < 0.0F) {
/* 64 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb, float s) {
/* 69 */     sb.setColor(this.color);
/* 70 */     if (this.img != null)
/* 71 */       sb.draw(this.img, this.nodeHb.cX - 128.0F + this.offsetX, this.nodeHb.cY - 128.0F + this.offsetY, 128.0F, 128.0F, 256.0F, 256.0F, s * this.scale, s * this.scale, this.rotation, 0, 0, 256, 256, this.flipped, false); 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FlameAnimationEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */