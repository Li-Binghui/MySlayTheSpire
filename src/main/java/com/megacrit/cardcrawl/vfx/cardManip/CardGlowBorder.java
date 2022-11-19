/*    */ package com.megacrit.cardcrawl.vfx.cardManip;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class CardGlowBorder
/*    */   extends AbstractGameEffect {
/*    */   private AbstractCard card;
/*    */   
/*    */   public CardGlowBorder(AbstractCard card) {
/* 21 */     this(card, Color.valueOf("30c8dcff"));
/*    */   }
/*    */   private TextureAtlas.AtlasRegion img; private float scale;
/*    */   public CardGlowBorder(AbstractCard card, Color gColor) {
/* 25 */     this.card = card;
/*    */     
/* 27 */     switch (card.type) {
/*    */       case POWER:
/* 29 */         this.img = ImageMaster.CARD_POWER_BG_SILHOUETTE;
/*    */         break;
/*    */       case ATTACK:
/* 32 */         this.img = ImageMaster.CARD_ATTACK_BG_SILHOUETTE;
/*    */         break;
/*    */       default:
/* 35 */         this.img = ImageMaster.CARD_SKILL_BG_SILHOUETTE;
/*    */         break;
/*    */     } 
/*    */     
/* 39 */     this.duration = 1.2F;
/*    */     
/* 41 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 42 */       this.color = gColor.cpy();
/*    */     } else {
/* 44 */       this.color = Color.GREEN.cpy();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void update() {
/* 49 */     this.scale = (1.0F + Interpolation.pow2Out.apply(0.03F, 0.11F, 1.0F - this.duration)) * this.card.drawScale * Settings.scale;
/* 50 */     this.color.a = this.duration / 2.0F;
/* 51 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 53 */     if (this.duration < 0.0F) {
/* 54 */       this.isDone = true;
/* 55 */       this.duration = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 61 */     sb.setColor(this.color);
/* 62 */     sb.draw((TextureRegion)this.img, this.card.current_x + this.img.offsetX - this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - this.img.originalHeight / 2.0F, this.img.originalWidth / 2.0F - this.img.offsetX, this.img.originalHeight / 2.0F - this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.card.angle);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\CardGlowBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */