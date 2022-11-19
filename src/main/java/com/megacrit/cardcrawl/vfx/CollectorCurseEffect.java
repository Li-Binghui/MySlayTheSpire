/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;
/*    */ 
/*    */ public class CollectorCurseEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/* 15 */   private float stakeTimer = 0.0F; private float y; private int count;
/*    */   
/*    */   public CollectorCurseEffect(float x, float y) {
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.count = 13;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     this.stakeTimer -= Gdx.graphics.getDeltaTime();
/* 26 */     if (this.stakeTimer < 0.0F) {
/* 27 */       if (this.count == 13) {
/* 28 */         CardCrawlGame.sound.playA("ATTACK_HEAVY", -0.5F);
/*    */         
/* 30 */         AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.BLACK.cpy(), 0.8F));
/* 31 */         AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.0F, 1.0F, 0.7F)));
/*    */       } 
/*    */       
/* 34 */       AbstractDungeon.effectsQueue.add(new CollectorStakeEffect(this.x + 
/*    */             
/* 36 */             MathUtils.random(-50.0F, 50.0F) * Settings.scale, this.y + 
/* 37 */             MathUtils.random(-60.0F, 60.0F) * Settings.scale));
/* 38 */       this.stakeTimer = 0.04F;
/* 39 */       this.count--;
/* 40 */       if (this.count == 0)
/* 41 */         this.isDone = true; 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\CollectorCurseEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */