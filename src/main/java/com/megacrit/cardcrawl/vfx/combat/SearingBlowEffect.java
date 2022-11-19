/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SearingBlowEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private int timesUpgraded;
/*    */   
/*    */   public SearingBlowEffect(float x, float y, int timesUpgraded) {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.timesUpgraded = timesUpgraded;
/* 19 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, true);
/*    */   }
/*    */   
/*    */   public void update() {
/* 23 */     CardCrawlGame.sound.playA("ATTACK_FIRE", 0.3F);
/* 24 */     CardCrawlGame.sound.playA("ATTACK_HEAVY", -0.3F);
/*    */     
/* 26 */     float dst = 180.0F + this.timesUpgraded * 3.0F;
/*    */     
/* 28 */     AbstractDungeon.effectsQueue.add(new RedFireballEffect(this.x - dst * Settings.scale, this.y, this.x + dst * Settings.scale, this.y - 50.0F * Settings.scale, this.timesUpgraded));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SearingBlowEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */