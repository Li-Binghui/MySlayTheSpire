/*    */ package com.megacrit.cardcrawl.vfx.stance;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StanceAuraEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public static boolean switcher = true;
/* 23 */   private TextureAtlas.AtlasRegion img = ImageMaster.EXHAUST_L;
/*    */   
/*    */   private float vY;
/*    */   
/*    */   public StanceAuraEffect(String stanceId) {
/* 28 */     if (stanceId.equals("Wrath")) {
/* 29 */       this
/*    */ 
/*    */         
/* 32 */         .color = new Color(MathUtils.random(0.6F, 0.7F), MathUtils.random(0.0F, 0.1F), MathUtils.random(0.1F, 0.2F), 0.0F);
/*    */     }
/* 34 */     else if (stanceId.equals("Calm")) {
/* 35 */       this.color = new Color(MathUtils.random(0.5F, 0.55F), MathUtils.random(0.6F, 0.7F), 1.0F, 0.0F);
/*    */     } else {
/* 37 */       this
/*    */ 
/*    */         
/* 40 */         .color = new Color(MathUtils.random(0.6F, 0.7F), MathUtils.random(0.0F, 0.1F), MathUtils.random(0.6F, 0.7F), 0.0F);
/*    */     } 
/*    */ 
/*    */     
/* 44 */     this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 16.0F, AbstractDungeon.player.hb.width / 16.0F);
/*    */ 
/*    */ 
/*    */     
/* 48 */     this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 16.0F, AbstractDungeon.player.hb.height / 12.0F);
/*    */ 
/*    */ 
/*    */     
/* 52 */     this.x -= this.img.packedWidth / 2.0F;
/* 53 */     this.y -= this.img.packedHeight / 2.0F;
/*    */     
/* 55 */     switcher = !switcher;
/*    */     
/* 57 */     this.renderBehind = true;
/* 58 */     this.rotation = MathUtils.random(360.0F);
/* 59 */     if (switcher) {
/* 60 */       this.renderBehind = true;
/* 61 */       this.vY = MathUtils.random(0.0F, 40.0F);
/*    */     } else {
/* 63 */       this.renderBehind = false;
/* 64 */       this.vY = MathUtils.random(0.0F, -40.0F);
/*    */     } 
/*    */   }
/*    */   private float y; private float x;
/*    */   
/*    */   public void update() {
/* 70 */     if (this.duration > 1.0F) {
/* 71 */       this.color.a = Interpolation.fade.apply(0.3F, 0.0F, this.duration - 1.0F);
/*    */     } else {
/* 73 */       this.color.a = Interpolation.fade.apply(0.0F, 0.3F, this.duration);
/*    */     } 
/* 75 */     this.rotation += Gdx.graphics.getDeltaTime() * this.vY;
/* 76 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 77 */     if (this.duration < 0.0F) {
/* 78 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 84 */     sb.setColor(this.color);
/* 85 */     sb.setBlendFunction(770, 1);
/* 86 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 97 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\StanceAuraEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */