/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class LoseHPEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public LoseHPEffect(AbstractCreature target, float x, float y, int number) {
/* 14 */     AbstractDungeon.effectList.add(new DamageNumberEffect(target, x, y, number));
/* 15 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\LoseHPEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */