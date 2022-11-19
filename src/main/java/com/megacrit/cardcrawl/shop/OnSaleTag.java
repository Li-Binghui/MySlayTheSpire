/*    */ package com.megacrit.cardcrawl.shop;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class OnSaleTag
/*    */ {
/*    */   public AbstractCard card;
/* 14 */   private static final float W = 128.0F * Settings.scale;
/* 15 */   public static Texture img = null;
/*    */   
/*    */   public OnSaleTag(AbstractCard c) {
/* 18 */     this.card = c;
/* 19 */     if (img == null) {
/* 20 */       switch (Settings.language) {
/*    */         case DEU:
/* 22 */           img = ImageMaster.loadImage("images/npcs/sale_tag/deu.png");
/*    */           return;
/*    */         case EPO:
/* 25 */           img = ImageMaster.loadImage("images/npcs/sale_tag/epo.png");
/*    */           return;
/*    */         case FIN:
/* 28 */           img = ImageMaster.loadImage("images/npcs/sale_tag/fin.png");
/*    */           return;
/*    */         case FRA:
/* 31 */           img = ImageMaster.loadImage("images/npcs/sale_tag/fra.png");
/*    */           return;
/*    */         case ITA:
/* 34 */           img = ImageMaster.loadImage("images/npcs/sale_tag/ita.png");
/*    */           return;
/*    */         case JPN:
/* 37 */           img = ImageMaster.loadImage("images/npcs/sale_tag/jpn.png");
/*    */           return;
/*    */         case KOR:
/* 40 */           img = ImageMaster.loadImage("images/npcs/sale_tag/kor.png");
/*    */           return;
/*    */         case RUS:
/* 43 */           img = ImageMaster.loadImage("images/npcs/sale_tag/rus.png");
/*    */           return;
/*    */         case THA:
/* 46 */           img = ImageMaster.loadImage("images/npcs/sale_tag/tha.png");
/*    */           return;
/*    */         case UKR:
/* 49 */           img = ImageMaster.loadImage("images/npcs/sale_tag/ukr.png");
/*    */           return;
/*    */         case ZHS:
/* 52 */           img = ImageMaster.loadImage("images/npcs/sale_tag/zhs.png");
/*    */           return;
/*    */       } 
/* 55 */       img = ImageMaster.loadImage("images/npcs/sale_tag/eng.png");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 62 */     sb.setColor(Color.WHITE);
/* 63 */     sb.draw(img, this.card.current_x + 30.0F * Settings.scale + (this.card.drawScale - 0.75F) * 60.0F * Settings.scale, this.card.current_y + 70.0F * Settings.scale + (this.card.drawScale - 0.75F) * 90.0F * Settings.scale, W * this.card.drawScale, W * this.card.drawScale);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 69 */     sb.setBlendFunction(770, 1);
/* 70 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, (MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) + 1.25F) / 3.0F));
/* 71 */     sb.draw(img, this.card.current_x + 30.0F * Settings.scale + (this.card.drawScale - 0.75F) * 60.0F * Settings.scale, this.card.current_y + 70.0F * Settings.scale + (this.card.drawScale - 0.75F) * 90.0F * Settings.scale, W * this.card.drawScale, W * this.card.drawScale);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 77 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\shop\OnSaleTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */