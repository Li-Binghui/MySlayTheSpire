/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class AscensionLevelUpTextEffect extends AbstractGameEffect {
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AscensionTextEffect");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private static final float TEXT_DURATION = 3.0F;
/* 17 */   private static final float START_Y = Settings.HEIGHT / 2.0F + 130.0F * Settings.scale;
/* 18 */   private static final float TARGET_Y = Settings.HEIGHT / 2.0F + 230.0F * Settings.scale;
/*    */   private float y;
/*    */   private int level;
/*    */   
/*    */   public AscensionLevelUpTextEffect() {
/* 23 */     CardCrawlGame.sound.play("UNLOCK_PING");
/* 24 */     this.duration = 3.0F;
/* 25 */     this.startingDuration = 3.0F;
/* 26 */     this.y = START_Y;
/* 27 */     this.color = Settings.GREEN_TEXT_COLOR.cpy();
/* 28 */     this.level = AbstractDungeon.ascensionLevel + 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 33 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 34 */     if (this.duration < 0.0F) {
/* 35 */       this.isDone = true;
/* 36 */       this.duration = 0.0F;
/*    */     } 
/*    */     
/* 39 */     if (this.duration > 2.5F) {
/* 40 */       this.y = Interpolation.elasticIn.apply(TARGET_Y, START_Y, (this.duration - 2.5F) * 2.0F);
/* 41 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (this.duration - 2.5F) * 2.0F);
/* 42 */     } else if (this.duration < 0.5F) {
/* 43 */       this.color.a = Interpolation.pow2In.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 49 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[0] + this.level + TEXT[1], Settings.WIDTH / 2.0F, this.y, this.color);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\AscensionLevelUpTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */