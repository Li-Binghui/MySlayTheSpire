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
/*    */ public class BetaCardArtUnlockedTextEffect extends AbstractGameEffect {
/* 12 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("BetaArtUnlockEffect");
/* 13 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   private static final float TEXT_DURATION = 3.0F;
/* 15 */   private static final float START_Y = Settings.HEIGHT - 410.0F * Settings.scale;
/* 16 */   private static final float TARGET_Y = Settings.HEIGHT - 310.0F * Settings.scale;
/*    */   private float y;
/*    */   private String msg;
/*    */   
/*    */   public BetaCardArtUnlockedTextEffect(int unlockValue) {
/* 21 */     CardCrawlGame.sound.play("UNLOCK_PING");
/* 22 */     this.duration = 3.0F;
/* 23 */     this.startingDuration = 3.0F;
/* 24 */     this.y = START_Y;
/* 25 */     this.color = Settings.GREEN_TEXT_COLOR.cpy();
/*    */     
/* 27 */     switch (unlockValue) {
/*    */       case 0:
/* 29 */         this.msg = TEXT[0];
/*    */         return;
/*    */       case 1:
/* 32 */         this.msg = TEXT[1];
/*    */         return;
/*    */       case 2:
/* 35 */         this.msg = TEXT[2];
/*    */         return;
/*    */       case 3:
/* 38 */         this.msg = TEXT[3];
/*    */         return;
/*    */       case 4:
/* 41 */         this.msg = TEXT[4];
/*    */         return;
/*    */     } 
/* 44 */     this.msg = "ERROR";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 51 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 52 */     if (this.duration < 0.0F) {
/* 53 */       this.isDone = true;
/* 54 */       this.duration = 0.0F;
/*    */     } 
/*    */     
/* 57 */     if (this.duration > 2.5F) {
/* 58 */       this.y = Interpolation.elasticIn.apply(TARGET_Y, START_Y, (this.duration - 2.5F) * 2.0F);
/* 59 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (this.duration - 2.5F) * 2.0F);
/* 60 */     } else if (this.duration < 0.5F) {
/* 61 */       this.color.a = Interpolation.pow2In.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 67 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.msg, Settings.WIDTH / 2.0F, this.y, this.color);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\BetaCardArtUnlockedTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */