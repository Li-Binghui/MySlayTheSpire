/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
/*    */ 
/*    */ public class FadeWipeParticle extends AbstractGameEffect {
/*    */   private static final float DUR = 1.0F;
/*    */   private float y;
/*    */   private float lerpTimer;
/*    */   
/*    */   public FadeWipeParticle() {
/* 20 */     this.img = ImageMaster.SCENE_TRANSITION_FADER;
/* 21 */     this.flatImg = ImageMaster.WHITE_SQUARE_IMG;
/* 22 */     this.color = AbstractDungeon.fadeColor.cpy();
/* 23 */     this.color.a = 0.0F;
/* 24 */     this.duration = 1.0F;
/* 25 */     this.startingDuration = 1.0F;
/* 26 */     this.y = (Settings.HEIGHT + this.img.packedHeight);
/* 27 */     this.delayTimer = 0.1F;
/*    */   }
/*    */   private float delayTimer; private TextureAtlas.AtlasRegion img; private Texture flatImg;
/*    */   public void update() {
/* 31 */     if (this.delayTimer > 0.0F) {
/* 32 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/*    */       
/*    */       return;
/*    */     } 
/* 36 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 37 */     if (this.duration < 0.0F) {
/* 38 */       this.duration = 0.0F;
/* 39 */       this.lerpTimer += Gdx.graphics.getDeltaTime();
/* 40 */       if (this.lerpTimer > 0.5F) {
/* 41 */         this.color.a = MathHelper.slowColorLerpSnap(this.color.a, 0.0F);
/* 42 */         if (this.color.a == 0.0F) {
/* 43 */           this.isDone = true;
/*    */         }
/*    */       } 
/*    */     } else {
/* 47 */       this.color.a = Interpolation.pow5In.apply(1.0F, 0.0F, this.duration / 1.0F);
/* 48 */       this.y = Interpolation.pow3In.apply(0.0F - this.img.packedHeight, (Settings.HEIGHT + this.img.packedHeight), this.duration / 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 54 */     sb.setColor(this.color);
/* 55 */     sb.draw((TextureRegion)this.img, 0.0F, this.y, Settings.WIDTH, this.img.packedHeight);
/* 56 */     sb.draw(this.flatImg, 0.0F, this.y + this.img.packedHeight - 1.0F * Settings.scale, Settings.WIDTH, Settings.HEIGHT);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FadeWipeParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */