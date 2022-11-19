/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class PowerIconShowEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float DUR = 2.2F;
/*    */   private float x;
/* 17 */   private static final float STARTING_OFFSET_Y = 130.0F * Settings.scale; private float y; private float offsetY; private Texture img;
/* 18 */   private static final float TARGET_OFFSET_Y = 170.0F * Settings.scale;
/*    */   private static final float LERP_RATE = 5.0F;
/*    */   private static final int W = 32;
/* 21 */   private Color shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*    */   
/*    */   public PowerIconShowEffect(AbstractPower power) {
/* 24 */     if (!power.owner.isDeadOrEscaped()) {
/* 25 */       this.x = power.owner.hb.cX;
/* 26 */       this.y = power.owner.hb.cY + power.owner.hb.height / 2.0F;
/*    */     } 
/* 28 */     this.img = power.img;
/* 29 */     this.duration = 2.2F;
/* 30 */     this.startingDuration = 2.2F;
/* 31 */     this.offsetY = STARTING_OFFSET_Y;
/* 32 */     this.color = Color.WHITE.cpy();
/* 33 */     this.renderBehind = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 38 */     super.update();
/* 39 */     this.offsetY = MathUtils.lerp(this.offsetY, TARGET_OFFSET_Y, Gdx.graphics.getDeltaTime() * 5.0F);
/* 40 */     this.y += Gdx.graphics.getDeltaTime() * 12.0F * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 45 */     if (this.img != null) {
/* 46 */       this.color.a /= 2.0F;
/* 47 */       sb.setColor(this.shineColor);
/* 48 */       sb.setBlendFunction(770, 1);
/* 49 */       sb.draw(this.img, this.x - 16.0F, this.y - 16.0F + this.offsetY, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale * 2.5F, Settings.scale * 2.5F, 0.0F, 0, 0, 32, 32, false, false);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 66 */       sb.setBlendFunction(770, 771);
/* 67 */       sb.setColor(this.color);
/* 68 */       sb.draw(this.img, this.x - 16.0F, this.y - 16.0F + this.offsetY, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 32, 32, false, false);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\PowerIconShowEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */