/*    */ package com.megacrit.cardcrawl.vfx.cardManip;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShowCardBrieflyEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float EFFECT_DUR = 2.5F;
/*    */   private AbstractCard card;
/* 18 */   private static final float PADDING = 30.0F * Settings.scale;
/*    */   
/*    */   public ShowCardBrieflyEffect(AbstractCard card) {
/* 21 */     this.card = card;
/* 22 */     this.duration = 2.5F;
/* 23 */     this.startingDuration = 2.5F;
/* 24 */     identifySpawnLocation(Settings.WIDTH - 96.0F * Settings.scale, Settings.HEIGHT - 32.0F * Settings.scale);
/* 25 */     card.drawScale = 0.01F;
/* 26 */     card.targetDrawScale = 1.0F;
/*    */   }
/*    */   
/*    */   public ShowCardBrieflyEffect(AbstractCard card, float x, float y) {
/* 30 */     this.card = card;
/* 31 */     this.duration = 2.5F;
/* 32 */     this.startingDuration = 2.5F;
/* 33 */     this.card.drawScale = 0.01F;
/* 34 */     this.card.targetDrawScale = 1.0F;
/* 35 */     this.card.current_x = Settings.WIDTH / 2.0F;
/* 36 */     this.card.current_y = Settings.HEIGHT / 2.0F;
/* 37 */     this.card.target_x = x;
/* 38 */     this.card.target_y = y;
/*    */   }
/*    */   
/*    */   private void identifySpawnLocation(float x, float y) {
/* 42 */     int effectCount = 0;
/* 43 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 44 */       if (e instanceof ShowCardBrieflyEffect) {
/* 45 */         effectCount++;
/*    */       }
/*    */     } 
/*    */     
/* 49 */     this.card.current_x = Settings.WIDTH / 2.0F;
/* 50 */     this.card.current_y = Settings.HEIGHT / 2.0F;
/* 51 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*    */     
/* 53 */     switch (effectCount) {
/*    */       case 0:
/* 55 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*    */         return;
/*    */       case 1:
/* 58 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 2:
/* 61 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 3:
/* 64 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */       case 4:
/* 67 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */     } 
/* 70 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 71 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 78 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 79 */     if (this.duration < 0.6F) {
/* 80 */       this.card.fadingOut = true;
/*    */     }
/* 82 */     this.card.update();
/*    */     
/* 84 */     if (this.duration < 0.0F) {
/* 85 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 91 */     if (!this.isDone)
/* 92 */       this.card.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\ShowCardBrieflyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */