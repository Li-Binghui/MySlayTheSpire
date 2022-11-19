/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RainingGoldEffect extends AbstractGameEffect {
/*    */   private int amount;
/* 10 */   private float staggerTimer = 0.0F; private int min; private int max;
/*    */   private boolean playerCentered;
/*    */   
/*    */   public RainingGoldEffect(int amount) {
/* 14 */     this.amount = amount;
/* 15 */     this.playerCentered = false;
/* 16 */     if (amount < 100) {
/* 17 */       this.min = 1;
/* 18 */       this.max = 7;
/*    */     } else {
/* 20 */       this.min = 3;
/* 21 */       this.max = 18;
/*    */     } 
/*    */   }
/*    */   
/*    */   public RainingGoldEffect(int amount, boolean centerOnPlayer) {
/* 26 */     this(amount);
/* 27 */     this.playerCentered = centerOnPlayer;
/*    */   }
/*    */   
/*    */   public void update() {
/* 31 */     this.staggerTimer -= Gdx.graphics.getDeltaTime();
/* 32 */     if (this.staggerTimer < 0.0F) {
/* 33 */       int goldToSpawn = MathUtils.random(this.min, this.max);
/*    */       
/* 35 */       if (goldToSpawn <= this.amount) {
/* 36 */         this.amount -= goldToSpawn;
/*    */       } else {
/* 38 */         goldToSpawn = this.amount;
/* 39 */         this.isDone = true;
/*    */       } 
/*    */       
/* 42 */       for (int i = 0; i < goldToSpawn; i++) {
/* 43 */         AbstractDungeon.effectsQueue.add(new TouchPickupGold(this.playerCentered));
/*    */       }
/*    */       
/* 46 */       this.staggerTimer = MathUtils.random(0.3F);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RainingGoldEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */