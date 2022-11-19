/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
/*    */ 
/*    */ public class LightningOrbActivateEffect
/*    */   extends AbstractGameEffect {
/* 14 */   private static ArrayList<TextureAtlas.AtlasRegion> regions = null;
/* 15 */   private TextureAtlas.AtlasRegion img = null;
/* 16 */   private int index = 0;
/*    */   private float x;
/*    */   
/*    */   public LightningOrbActivateEffect(float x, float y) {
/* 20 */     this.renderBehind = false;
/* 21 */     this.color = Settings.LIGHT_YELLOW_COLOR.cpy();
/*    */     
/* 23 */     if (regions == null) {
/* 24 */       regions = new ArrayList<>();
/* 25 */       regions.add(ImageMaster.vfxAtlas.findRegion("combat/defect/l_orb1"));
/* 26 */       regions.add(ImageMaster.vfxAtlas.findRegion("combat/defect/l_orb2"));
/* 27 */       regions.add(ImageMaster.vfxAtlas.findRegion("combat/defect/l_orb3"));
/* 28 */       regions.add(ImageMaster.vfxAtlas.findRegion("combat/defect/l_orb4"));
/* 29 */       regions.add(ImageMaster.vfxAtlas.findRegion("combat/defect/l_orb5"));
/* 30 */       regions.add(ImageMaster.vfxAtlas.findRegion("combat/defect/l_orb6"));
/*    */     } 
/*    */     
/* 33 */     this.img = regions.get(this.index);
/* 34 */     this.x = x - this.img.packedWidth / 2.0F;
/* 35 */     this.y = y - this.img.packedHeight / 2.0F;
/* 36 */     this.scale = 2.0F * Settings.scale;
/* 37 */     this.duration = 0.03F;
/*    */   }
/*    */   private float y;
/*    */   
/*    */   public void update() {
/* 42 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 43 */     if (this.duration < 0.0F) {
/* 44 */       this.index++;
/* 45 */       if (this.index > regions.size() - 1) {
/* 46 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 49 */       this.img = regions.get(this.index);
/*    */       
/* 51 */       this.duration = 0.03F;
/*    */     } 
/* 53 */     this.color.a -= Gdx.graphics.getDeltaTime() * 2.0F;
/* 54 */     if (this.color.a < 0.0F) {
/* 55 */       this.color.a = 0.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 61 */     sb.setColor(this.color);
/* 62 */     sb.setBlendFunction(770, 1);
/* 63 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 74 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\LightningOrbActivateEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */