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
/*    */ import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
/*    */ 
/*    */ public class ShowCardAndAddToDiscardEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 1.5F;
/*    */   private AbstractCard card;
/* 17 */   private static final float PADDING = 30.0F * Settings.scale;
/*    */   
/*    */   public ShowCardAndAddToDiscardEffect(AbstractCard srcCard, float x, float y) {
/* 20 */     this.card = srcCard.makeStatEquivalentCopy();
/* 21 */     this.duration = 1.5F;
/* 22 */     this.card.target_x = x;
/* 23 */     this.card.target_y = y;
/* 24 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/* 25 */     this.card.drawScale = 0.75F;
/* 26 */     this.card.targetDrawScale = 0.75F;
/* 27 */     CardCrawlGame.sound.play("CARD_OBTAIN");
/* 28 */     if (this.card.type != AbstractCard.CardType.CURSE && this.card.type != AbstractCard.CardType.STATUS && AbstractDungeon.player
/* 29 */       .hasPower("MasterRealityPower")) {
/* 30 */       this.card.upgrade();
/*    */     }
/* 32 */     AbstractDungeon.player.discardPile.addToTop(srcCard);
/*    */   }
/*    */   
/*    */   public ShowCardAndAddToDiscardEffect(AbstractCard card) {
/* 36 */     this.card = card;
/* 37 */     this.duration = 1.5F;
/* 38 */     identifySpawnLocation(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/* 39 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(card.target_x, card.target_y));
/* 40 */     card.drawScale = 0.01F;
/* 41 */     card.targetDrawScale = 1.0F;
/* 42 */     if (card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS && AbstractDungeon.player
/* 43 */       .hasPower("MasterRealityPower")) {
/* 44 */       card.upgrade();
/*    */     }
/* 46 */     AbstractDungeon.player.discardPile.addToTop(card);
/*    */   }
/*    */   
/*    */   private void identifySpawnLocation(float x, float y) {
/* 50 */     int effectCount = 0;
/* 51 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 52 */       if (e instanceof ShowCardAndAddToDiscardEffect) {
/* 53 */         effectCount++;
/*    */       }
/*    */     } 
/*    */     
/* 57 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*    */     
/* 59 */     switch (effectCount) {
/*    */       case 0:
/* 61 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*    */         return;
/*    */       case 1:
/* 64 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 2:
/* 67 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*    */         return;
/*    */       case 3:
/* 70 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */       case 4:
/* 73 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*    */         return;
/*    */     } 
/* 76 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 77 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 84 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 85 */     this.card.update();
/*    */     
/* 87 */     if (this.duration < 0.0F) {
/* 88 */       this.isDone = true;
/* 89 */       this.card.shrink();
/* 90 */       (AbstractDungeon.getCurrRoom()).souls.discard(this.card, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 96 */     if (!this.isDone)
/* 97 */       this.card.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\ShowCardAndAddToDiscardEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */