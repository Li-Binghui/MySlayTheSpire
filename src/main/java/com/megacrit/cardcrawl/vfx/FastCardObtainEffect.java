/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Omamori;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FastCardObtainEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private AbstractCard card;
/*    */   
/*    */   public FastCardObtainEffect(AbstractCard card, float x, float y) {
/* 25 */     if (card.color == AbstractCard.CardColor.CURSE && AbstractDungeon.player.hasRelic("Omamori") && 
/* 26 */       (AbstractDungeon.player.getRelic("Omamori")).counter != 0) {
/* 27 */       ((Omamori)AbstractDungeon.player.getRelic("Omamori")).use();
/* 28 */       this.duration = 0.0F;
/* 29 */       this.isDone = true;
/*    */     } 
/*    */     
/* 32 */     CardHelper.obtain(card.cardID, card.rarity, card.color);
/*    */     
/* 34 */     this.card = card;
/* 35 */     this.duration = 0.01F;
/* 36 */     card.current_x = x;
/* 37 */     card.current_y = y;
/* 38 */     CardCrawlGame.sound.play("CARD_SELECT");
/*    */   }
/*    */   
/*    */   public void update() {
/* 42 */     if (this.isDone) {
/*    */       return;
/*    */     }
/*    */     
/* 46 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 47 */     this.card.update();
/*    */     
/* 49 */     if (this.duration < 0.0F) {
/*    */ 
/*    */       
/* 52 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 53 */         r.onObtainCard(this.card);
/*    */       }
/*    */       
/* 56 */       this.isDone = true;
/* 57 */       this.card.shrink();
/* 58 */       (AbstractDungeon.getCurrRoom()).souls.obtain(this.card, true);
/* 59 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 60 */         r.onMasterDeckChange();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 67 */     if (!this.isDone)
/* 68 */       this.card.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FastCardObtainEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */