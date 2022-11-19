/*    */ package com.megacrit.cardcrawl.characters;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.HeartAnimListener;
/*    */ 
/*    */ public class AnimatedNpc
/*    */ {
/* 19 */   private TextureAtlas atlas = null;
/*    */   public Skeleton skeleton;
/*    */   private AnimationState state;
/*    */   private AnimationStateData stateData;
/*    */   
/*    */   public AnimatedNpc(float x, float y, String atlasUrl, String skeletonUrl, String trackName) {
/* 25 */     loadAnimation(atlasUrl, skeletonUrl, 1.0F);
/* 26 */     this.skeleton.setPosition(x, y);
/* 27 */     this.state.setAnimation(0, trackName, true);
/* 28 */     this.state.setTimeScale(1.0F);
/*    */   }
/*    */   
/*    */   private void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
/* 32 */     this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
/* 33 */     SkeletonJson json = new SkeletonJson(this.atlas);
/* 34 */     json.setScale(Settings.renderScale / scale);
/* 35 */     SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
/* 36 */     this.skeleton = new Skeleton(skeletonData);
/* 37 */     this.skeleton.setColor(Color.WHITE);
/* 38 */     this.stateData = new AnimationStateData(skeletonData);
/* 39 */     this.state = new AnimationState(this.stateData);
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 43 */     this.state.update(Gdx.graphics.getDeltaTime());
/* 44 */     this.state.apply(this.skeleton);
/* 45 */     this.skeleton.updateWorldTransform();
/* 46 */     this.skeleton.setFlip(false, false);
/* 47 */     this.skeleton.setColor(Color.WHITE);
/* 48 */     sb.end();
/* 49 */     CardCrawlGame.psb.begin();
/* 50 */     AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
/* 51 */     CardCrawlGame.psb.end();
/* 52 */     sb.begin();
/* 53 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb, Color color) {
/* 57 */     this.state.update(Gdx.graphics.getDeltaTime());
/* 58 */     this.state.apply(this.skeleton);
/* 59 */     this.skeleton.updateWorldTransform();
/* 60 */     this.skeleton.setFlip(false, false);
/* 61 */     this.skeleton.setColor(color);
/* 62 */     sb.end();
/* 63 */     CardCrawlGame.psb.begin();
/* 64 */     AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
/* 65 */     CardCrawlGame.psb.end();
/* 66 */     sb.begin();
/* 67 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 71 */     this.atlas.dispose();
/*    */   }
/*    */   
/*    */   public void setTimeScale(float setScale) {
/* 75 */     this.state.setTimeScale(setScale);
/*    */   }
/*    */   
/*    */   public void addListener(HeartAnimListener listener) {
/* 79 */     this.state.addListener((AnimationState.AnimationStateListener)listener);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\characters\AnimatedNpc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */