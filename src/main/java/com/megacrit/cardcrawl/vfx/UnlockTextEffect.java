/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class UnlockTextEffect extends AbstractGameEffect {
/* 12 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("UnlockTextEffect");
/* 13 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private static final float TEXT_DURATION = 3.0F;
/* 16 */   private static final float X = 1350.0F * Settings.scale;
/* 17 */   private static final float START_Y = Settings.HEIGHT / 2.0F - 440.0F * Settings.scale;
/* 18 */   private static final float TARGET_Y = Settings.HEIGHT / 2.0F - 270.0F * Settings.scale;
/*    */   private float y;
/* 20 */   private static final String LABEL = TEXT[0];
/*    */   
/*    */   public UnlockTextEffect() {
/* 23 */     CardCrawlGame.sound.play("UNLOCK_PING");
/* 24 */     this.duration = 3.0F;
/* 25 */     this.startingDuration = 3.0F;
/* 26 */     this.y = START_Y;
/* 27 */     this.color = Settings.BLUE_TEXT_COLOR.cpy();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 32 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 33 */     if (this.duration < 0.0F) {
/* 34 */       this.isDone = true;
/* 35 */       this.duration = 0.0F;
/*    */     } 
/*    */     
/* 38 */     if (this.duration > 2.5F) {
/* 39 */       this.y = Interpolation.elasticIn.apply(TARGET_Y, START_Y, (this.duration - 2.5F) * 2.0F);
/* 40 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (this.duration - 2.5F) * 2.0F);
/* 41 */     } else if (this.duration < 0.5F) {
/* 42 */       this.color.a = Interpolation.pow2In.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 48 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, LABEL, X, this.y, this.color);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\UnlockTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */