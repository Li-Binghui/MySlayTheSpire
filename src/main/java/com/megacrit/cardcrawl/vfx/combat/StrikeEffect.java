/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class StrikeEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public StrikeEffect(AbstractCreature target, float x, float y, int number) {
/* 14 */     AbstractDungeon.effectsQueue.add(new DamageNumberEffect(target, x, y, number));
/*    */     int i;
/* 16 */     for (i = 0; i < 18; i++) {
/* 17 */       AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(x, y));
/*    */     }
/* 19 */     for (i = 0; i < 5; i++) {
/* 20 */       AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(x, y));
/*    */     }
/*    */     
/* 23 */     if (number < 5) {
/* 24 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
/* 25 */     } else if (number < 20) {
/* 26 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*    */     } else {
/* 28 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
/*    */     } 
/*    */   }
/*    */   
/*    */   public StrikeEffect(AbstractCreature target, float x, float y, String msg) {
/* 33 */     AbstractDungeon.effectsQueue.add(new BlockedWordEffect(target, x, y, msg));
/*    */     
/* 35 */     for (int i = 0; i < 18; i++) {
/* 36 */       AbstractDungeon.effectsQueue.add(new BlockImpactLineEffect(x, y));
/*    */     }
/*    */     
/* 39 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\StrikeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */