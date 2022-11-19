/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class FlashIntentEffect extends AbstractGameEffect {
/*    */   private static final float DURATION = 1.0F;
/*    */   private static final float FLASH_INTERVAL = 0.17F;
/* 13 */   private float intervalTimer = 0.0F;
/*    */   private Texture img;
/*    */   private AbstractMonster m;
/*    */   
/*    */   public FlashIntentEffect(Texture img, AbstractMonster m) {
/* 18 */     this.duration = 1.0F;
/* 19 */     this.img = img;
/* 20 */     this.m = m;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     this.intervalTimer -= Gdx.graphics.getDeltaTime();
/* 26 */     if (this.intervalTimer < 0.0F && 
/* 27 */       !this.m.isDying) {
/* 28 */       this.intervalTimer = 0.17F;
/* 29 */       AbstractDungeon.effectsQueue.add(new FlashIntentParticle(this.img, this.m));
/*    */     } 
/*    */ 
/*    */     
/* 33 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 35 */     if (this.duration < 0.0F)
/* 36 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlashIntentEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */