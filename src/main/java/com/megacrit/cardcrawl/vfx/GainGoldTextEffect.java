/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class GainGoldTextEffect extends AbstractGameEffect {
/* 14 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("GainGoldTextEffect");
/* 15 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/* 17 */   private static int totalGold = 0;
/* 18 */   private int gold = 0;
/*    */   
/*    */   private boolean reachedCenter = false;
/*    */   
/*    */   private float x;
/* 23 */   private float waitTimer = 1.0F; private float y; private float destinationY; private static final float WAIT_TIME = 1.0F;
/* 24 */   private float fadeTimer = 1.0F;
/* 25 */   private static final float FADE_Y_SPEED = 100.0F * Settings.scale;
/*    */   private static final float TEXT_DURATION = 3.0F;
/*    */   
/*    */   public GainGoldTextEffect(int startingAmount) {
/* 29 */     this.x = AbstractDungeon.player.hb.cX;
/* 30 */     this.y = AbstractDungeon.player.hb.cY;
/* 31 */     this.destinationY = this.y + 150.0F * Settings.scale;
/* 32 */     this.duration = 3.0F;
/* 33 */     this.startingDuration = 3.0F;
/* 34 */     this.reachedCenter = false;
/* 35 */     this.gold = startingAmount;
/* 36 */     totalGold = startingAmount;
/* 37 */     this.color = Color.GOLD.cpy();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 43 */     if (this.waitTimer > 0.0F) {
/* 44 */       this.gold = totalGold;
/* 45 */       if (!this.reachedCenter && this.y != this.destinationY) {
/* 46 */         this.y = MathUtils.lerp(this.y, this.destinationY, Gdx.graphics.getDeltaTime() * 9.0F);
/* 47 */         if (Math.abs(this.y - this.destinationY) < Settings.UI_SNAP_THRESHOLD) {
/* 48 */           this.y = this.destinationY;
/* 49 */           this.reachedCenter = true;
/*    */         } 
/*    */       } else {
/* 52 */         this.waitTimer -= Gdx.graphics.getDeltaTime();
/* 53 */         if (this.waitTimer < 0.0F) {
/* 54 */           this.gold = totalGold;
/*    */         } else {
/* 56 */           this.gold = totalGold;
/*    */         } 
/*    */       } 
/*    */     } else {
/* 60 */       this.y += Gdx.graphics.getDeltaTime() * FADE_Y_SPEED;
/* 61 */       this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 62 */       this.color.a = this.fadeTimer;
/* 63 */       if (this.fadeTimer < 0.0F) {
/* 64 */         this.isDone = true;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean ping(int amount) {
/* 70 */     if (this.waitTimer > 0.0F) {
/* 71 */       this.waitTimer = 1.0F;
/* 72 */       totalGold += amount;
/* 73 */       return true;
/*    */     } 
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 81 */     if (!this.isDone)
/* 82 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, "+ " + 
/*    */ 
/*    */           
/* 85 */           Integer.toString(this.gold) + TEXT[0], this.x, this.y, this.color); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GainGoldTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */