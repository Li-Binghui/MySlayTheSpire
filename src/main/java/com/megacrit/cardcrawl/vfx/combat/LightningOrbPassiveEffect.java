/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class LightningOrbPassiveEffect
/*    */   extends AbstractGameEffect {
/* 13 */   private Texture img = null;
/* 14 */   private int index = 0;
/*    */   
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public LightningOrbPassiveEffect(float x, float y) {
/* 20 */     this.renderBehind = MathUtils.randomBoolean();
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/* 23 */     this.color = Settings.LIGHT_YELLOW_COLOR.cpy();
/* 24 */     this.img = ImageMaster.LIGHTNING_PASSIVE_VFX.get(this.index);
/* 25 */     this.scale = MathUtils.random(0.6F, 1.0F) * Settings.scale;
/* 26 */     this.rotation = MathUtils.random(360.0F);
/* 27 */     if (this.rotation < 120.0F) {
/* 28 */       this.renderBehind = true;
/*    */     }
/* 30 */     this.flipX = MathUtils.randomBoolean();
/* 31 */     this.flipY = MathUtils.randomBoolean();
/* 32 */     this.intervalDuration = MathUtils.random(0.03F, 0.06F);
/* 33 */     this.duration = this.intervalDuration;
/*    */   }
/*    */   private boolean flipX; private boolean flipY; private float intervalDuration;
/*    */   
/*    */   public void update() {
/* 38 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 39 */     if (this.duration < 0.0F) {
/* 40 */       this.index++;
/* 41 */       if (this.index > ImageMaster.LIGHTNING_PASSIVE_VFX.size() - 1) {
/* 42 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 45 */       this.img = ImageMaster.LIGHTNING_PASSIVE_VFX.get(this.index);
/*    */       
/* 47 */       this.duration = this.intervalDuration;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 53 */     sb.setColor(this.color);
/* 54 */     sb.setBlendFunction(770, 1);
/* 55 */     sb.draw(this.img, this.x - 61.0F, this.y - 61.0F, 61.0F, 61.0F, 122.0F, 122.0F, this.scale, this.scale, this.rotation, 0, 0, 122, 122, this.flipX, this.flipY);
/* 56 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\LightningOrbPassiveEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */