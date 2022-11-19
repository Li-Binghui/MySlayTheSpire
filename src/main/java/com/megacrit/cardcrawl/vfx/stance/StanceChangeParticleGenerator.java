/*    */ package com.megacrit.cardcrawl.vfx.stance;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ public class StanceChangeParticleGenerator
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private float x;
/*    */   private float y;
/*    */   private String stanceId;
/*    */   
/*    */   public StanceChangeParticleGenerator(float x, float y, String stanceId) {
/* 17 */     this.x = x;
/* 18 */     this.y = y;
/* 19 */     this.stanceId = stanceId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (!this.stanceId.equals("Calm"))
/* 25 */       if (this.stanceId.equals("Divinity")) {
/* 26 */         for (int i = 0; i < 20; i++) {
/* 27 */           AbstractDungeon.effectsQueue.add(new DivinityStanceChangeParticle(Color.PINK, this.x, this.y));
/*    */         }
/* 29 */       } else if (this.stanceId.equals("Wrath")) {
/* 30 */         for (int i = 0; i < 10; i++) {
/* 31 */           AbstractDungeon.effectsQueue.add(new WrathStanceChangeParticle(this.x));
/*    */         }
/* 33 */       } else if (this.stanceId.equals("Neutral")) {
/* 34 */         for (int i = 0; i < 20; i++) {
/* 35 */           AbstractDungeon.effectsQueue.add(new DivinityStanceChangeParticle(Color.WHITE, this.x, this.y));
/*    */         }
/*    */       } else {
/* 38 */         for (int i = 0; i < 20; i++) {
/* 39 */           AbstractDungeon.effectsQueue.add(new DivinityStanceChangeParticle(Color.WHITE, this.x, this.y));
/*    */         }
/*    */       }  
/* 42 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\StanceChangeParticleGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */