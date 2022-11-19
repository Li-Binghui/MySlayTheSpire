/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class LevelTransitionTextOverlayEffect
/*    */   extends AbstractGameEffect {
/*    */   private String name;
/* 14 */   private float alpha = 0.0F; private String levelNum; private static final float DUR = 5.0F;
/* 15 */   private Color c1 = Settings.GOLD_COLOR.cpy();
/* 16 */   private Color c2 = Settings.BLUE_TEXT_COLOR.cpy();
/*    */   private boolean higher = false;
/*    */   
/*    */   public LevelTransitionTextOverlayEffect(String name, String levelNum, boolean higher) {
/* 20 */     this.name = name;
/* 21 */     this.levelNum = levelNum;
/* 22 */     this.duration = 5.0F;
/* 23 */     this.startingDuration = 5.0F;
/* 24 */     this.color = Settings.GOLD_COLOR.cpy();
/* 25 */     this.color.a = 0.0F;
/* 26 */     this.higher = higher;
/*    */   }
/*    */   
/*    */   public LevelTransitionTextOverlayEffect(String name, String levelNum) {
/* 30 */     this.name = name;
/* 31 */     this.levelNum = levelNum;
/* 32 */     this.duration = 5.0F;
/* 33 */     this.startingDuration = 5.0F;
/* 34 */     this.color = Settings.GOLD_COLOR.cpy();
/* 35 */     this.color.a = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 40 */     if (this.duration > 4.0F) {
/* 41 */       this.alpha = Interpolation.pow5Out.apply(1.0F, 0.0F, (this.duration - 4.0F) / 4.0F);
/*    */     } else {
/* 43 */       this.alpha = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 2.5F);
/*    */     } 
/* 45 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 46 */     if (this.duration < 0.0F) {
/* 47 */       this.isDone = true;
/*    */     }
/*    */     
/* 50 */     this.c1.a = this.alpha;
/* 51 */     this.c2.a = this.alpha;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 56 */     if (this.higher) {
/* 57 */       FontHelper.renderFontCentered(sb, FontHelper.SCP_cardDescFont, this.levelNum, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 290.0F * Settings.scale, this.c2, 1.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 65 */       FontHelper.renderFontCentered(sb, FontHelper.dungeonTitleFont, this.name, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 190.0F * Settings.scale, this.c1);
/*    */ 
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */ 
/*    */       
/* 73 */       FontHelper.renderFontCentered(sb, FontHelper.SCP_cardDescFont, this.levelNum, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 90.0F * Settings.scale, this.c2, 1.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 81 */       FontHelper.renderFontCentered(sb, FontHelper.dungeonTitleFont, this.name, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 10.0F * Settings.scale, this.c1);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\LevelTransitionTextOverlayEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */