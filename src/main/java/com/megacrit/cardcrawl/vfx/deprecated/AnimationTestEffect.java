/*    */ package com.megacrit.cardcrawl.vfx.deprecated;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.esotericsoftware.spine.AnimationStateData;
/*    */ import com.esotericsoftware.spine.Skeleton;
/*    */ import com.esotericsoftware.spine.SkeletonData;
/*    */ import com.esotericsoftware.spine.SkeletonJson;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class AnimationTestEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public float duration;
/*    */   public float startingDuration;
/*    */   protected Color color;
/* 22 */   protected float scale = 1.0F; protected float rotation = 0.0F;
/*    */   
/*    */   public boolean renderBehind = false;
/*    */   
/*    */   TextureAtlas atlas;
/*    */   Skeleton skeleton;
/*    */   AnimationState state;
/*    */   
/*    */   public AnimationTestEffect() {
/* 31 */     this.duration = 3.0F;
/* 32 */     this.atlas = new TextureAtlas(Gdx.files.internal("animations/skeleton.atlas"));
/* 33 */     SkeletonJson json = new SkeletonJson(this.atlas);
/* 34 */     json.setScale(Settings.scale / 2.0F);
/* 35 */     SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("animations/skeleton.json"));
/* 36 */     this.skeleton = new Skeleton(skeletonData);
/* 37 */     this.skeleton.setPosition(1250.0F, 20.0F);
/* 38 */     AnimationStateData stateData = new AnimationStateData(skeletonData);
/* 39 */     this.state = new AnimationState(stateData);
/* 40 */     this.state.setAnimation(0, "animation", true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 48 */     this.skeleton.setPosition(InputHelper.mX, InputHelper.mY);
/* 49 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 50 */     if (this.duration < 0.0F) {
/* 51 */       this.atlas.dispose();
/* 52 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() {
/* 69 */     this.atlas.dispose();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\deprecated\AnimationTestEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */