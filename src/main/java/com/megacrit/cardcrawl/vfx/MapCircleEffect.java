/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*    */ 
/*    */ public class MapCircleEffect extends AbstractGameEffect {
/*    */   public static Texture img;
/*    */   private float x;
/*    */   private float y;
/*    */   public static final int W = 192;
/*    */   
/*    */   public MapCircleEffect(float x, float y, float angle) {
/* 18 */     img = ImageMaster.MAP_CIRCLE_1;
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.scale = Settings.scale;
/* 22 */     this.duration = 1.2F;
/* 23 */     this.startingDuration = 1.2F;
/* 24 */     this.scale = 3.0F * Settings.scale;
/* 25 */     this.rotation = angle;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     if (Settings.FAST_MODE) {
/* 31 */       this.duration -= Gdx.graphics.getDeltaTime();
/*    */     }
/* 33 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 35 */     if (this.duration < 1.0F) {
/* 36 */       img = ImageMaster.MAP_CIRCLE_5;
/* 37 */     } else if (this.duration < 1.05F) {
/* 38 */       img = ImageMaster.MAP_CIRCLE_4;
/* 39 */     } else if (this.duration < 1.1F) {
/* 40 */       img = ImageMaster.MAP_CIRCLE_3;
/* 41 */     } else if (this.duration < 1.15F) {
/* 42 */       img = ImageMaster.MAP_CIRCLE_2;
/*    */     } 
/*    */     
/* 45 */     this.scale = MathHelper.scaleLerpSnap(this.scale, 1.5F * Settings.scale);
/*    */     
/* 47 */     if (this.duration < 0.0F) {
/* 48 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 54 */     sb.setColor(new Color(0.09F, 0.13F, 0.17F, 1.0F));
/* 55 */     sb.draw(img, this.x - 96.0F, this.y - 96.0F, 96.0F, 96.0F, 192.0F, 192.0F, this.scale, this.scale, this.rotation, 0, 0, 192, 192, false, false);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\MapCircleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */