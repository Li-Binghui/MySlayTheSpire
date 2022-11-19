/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class EmpowerEffect extends AbstractGameEffect {
/*    */   private static final float SHAKE_DURATION = 0.25F;
/*    */   
/*    */   public EmpowerEffect(float x, float y) {
/* 12 */     CardCrawlGame.sound.play("CARD_POWER_IMPACT", 0.1F);
/* 13 */     for (int i = 0; i < 18; i++) {
/* 14 */       AbstractDungeon.effectList.add(new EmpowerCircleEffect(x, y));
/*    */     }
/*    */     
/* 17 */     CardCrawlGame.screenShake.rumble(0.25F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\EmpowerEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */