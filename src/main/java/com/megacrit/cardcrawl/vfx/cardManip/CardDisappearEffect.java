/*    */ package com.megacrit.cardcrawl.vfx.cardManip;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardDisappearEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private AbstractCard card;
/* 18 */   private static final float PADDING = 30.0F * Settings.scale;
/*    */   
/*    */   public CardDisappearEffect(AbstractCard card, float x, float y) {
/* 21 */     this.card = card;
/* 22 */     this.startingDuration = 2.0F;
/* 23 */     this.duration = this.startingDuration;
/* 24 */     identifySpawnLocation(x, y);
/* 25 */     card.drawScale = 0.01F;
/* 26 */     card.targetDrawScale = 1.0F;
/* 27 */     CardCrawlGame.sound.play("CARD_BURN");
/*    */   }
/*    */   
/*    */   private void identifySpawnLocation(float x, float y) {
/* 31 */     int effectCount = 0;
/* 32 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 33 */       if (e instanceof CardDisappearEffect) {
/* 34 */         effectCount++;
/*    */       }
/*    */     } 
/* 37 */     for (AbstractGameEffect e : AbstractDungeon.topLevelEffects) {
/* 38 */       if (e instanceof CardDisappearEffect) {
/* 39 */         effectCount++;
/*    */       }
/*    */     } 
/*    */     
/* 43 */     this.card.current_x = x;
/* 44 */     this.card.current_y = y;
/* 45 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*    */     
/* 47 */     switch (effectCount) {
/*    */       case 0:
/* 49 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*    */         return;
/*    */       case 1:
/* 52 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 2:
/* 55 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 3:
/* 58 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */       case 4:
/* 61 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */     } 
/* 64 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 65 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 71 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 72 */     if (this.duration < 0.5F && 
/* 73 */       !this.card.fadingOut) {
/* 74 */       this.card.fadingOut = true;
/*    */     }
/*    */ 
/*    */     
/* 78 */     this.card.update();
/*    */     
/* 80 */     if (this.duration < 0.0F) {
/* 81 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 87 */     if (!this.isDone)
/* 88 */       this.card.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\CardDisappearEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */