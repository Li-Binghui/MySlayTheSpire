/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class NecronomicurseEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 1.5F;
/* 14 */   private static final float PADDING = 30.0F * Settings.scale; private static final float FAST_DUR = 0.5F; private AbstractCard card;
/* 15 */   private float waitTimer = 2.0F;
/*    */   
/*    */   public NecronomicurseEffect(AbstractCard card, float x, float y) {
/* 18 */     this.card = card;
/* 19 */     if (Settings.FAST_MODE) {
/* 20 */       this.duration = 0.5F;
/*    */     } else {
/* 22 */       this.duration = 1.5F;
/*    */     } 
/* 24 */     identifySpawnLocation(x, y);
/* 25 */     card.drawScale = 0.01F;
/* 26 */     card.targetDrawScale = 1.0F;
/* 27 */     CardCrawlGame.sound.play("CARD_SELECT");
/* 28 */     AbstractDungeon.player.masterDeck.addToTop(card);
/*    */   }
/*    */   
/*    */   private void identifySpawnLocation(float x, float y) {
/* 32 */     int effectCount = 0;
/* 33 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 34 */       if (e instanceof NecronomicurseEffect) {
/* 35 */         effectCount++;
/*    */       }
/*    */     } 
/*    */     
/* 39 */     this.card.current_x = x;
/* 40 */     this.card.current_y = y;
/* 41 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*    */     
/* 43 */     switch (effectCount) {
/*    */       case 0:
/* 45 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*    */         return;
/*    */       case 1:
/* 48 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 2:
/* 51 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 3:
/* 54 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */       case 4:
/* 57 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */     } 
/* 60 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 61 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 68 */     if (this.waitTimer > 0.0F) {
/* 69 */       this.waitTimer -= Gdx.graphics.getDeltaTime();
/*    */       
/*    */       return;
/*    */     } 
/* 73 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 74 */     this.card.current_x += MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 75 */     this.card.current_y += MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 76 */     this.card.update();
/*    */     
/* 78 */     if (this.duration < 0.0F) {
/* 79 */       this.isDone = true;
/* 80 */       this.card.shrink();
/*    */       
/* 82 */       (AbstractDungeon.getCurrRoom()).souls.obtain(this.card, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 88 */     if (!this.isDone && this.waitTimer < 0.0F)
/* 89 */       this.card.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\NecronomicurseEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */