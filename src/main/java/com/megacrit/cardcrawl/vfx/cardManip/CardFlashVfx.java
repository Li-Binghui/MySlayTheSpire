/*     */ package com.megacrit.cardcrawl.vfx.cardManip;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class CardFlashVfx extends AbstractGameEffect {
/*     */   private AbstractCard card;
/*     */   private TextureAtlas.AtlasRegion img;
/*  17 */   private float yScale = 0.0F;
/*     */   private boolean isSuper = false;
/*     */   
/*     */   public CardFlashVfx(AbstractCard card, boolean isSuper) {
/*  21 */     this(card, new Color(1.0F, 0.8F, 0.2F, 0.0F), isSuper);
/*     */   }
/*     */   
/*     */   public CardFlashVfx(AbstractCard card, Color c, boolean isSuper) {
/*  25 */     this.card = card;
/*  26 */     this.isSuper = isSuper;
/*  27 */     this.duration = 0.5F;
/*     */     
/*  29 */     if (isSuper) {
/*  30 */       this.img = ImageMaster.CARD_FLASH_VFX;
/*     */     } else {
/*  32 */       switch (card.type) {
/*     */         case POWER:
/*  34 */           this.img = ImageMaster.CARD_POWER_BG_SILHOUETTE;
/*     */           break;
/*     */         case ATTACK:
/*  37 */           this.img = ImageMaster.CARD_ATTACK_BG_SILHOUETTE;
/*     */           break;
/*     */         default:
/*  40 */           this.img = ImageMaster.CARD_SKILL_BG_SILHOUETTE;
/*     */           break;
/*     */       } 
/*     */     } 
/*  44 */     this.color = c;
/*     */   }
/*     */   
/*     */   public CardFlashVfx(AbstractCard card) {
/*  48 */     this(card, new Color(1.0F, 0.8F, 0.2F, 0.0F), false);
/*     */   }
/*     */   
/*     */   public CardFlashVfx(AbstractCard card, Color c) {
/*  52 */     this.card = card;
/*  53 */     this.duration = 0.5F;
/*     */     
/*  55 */     switch (card.type) {
/*     */       case POWER:
/*  57 */         this.img = ImageMaster.CARD_POWER_BG_SILHOUETTE;
/*     */         break;
/*     */       case ATTACK:
/*  60 */         this.img = ImageMaster.CARD_ATTACK_BG_SILHOUETTE;
/*     */         break;
/*     */       default:
/*  63 */         this.img = ImageMaster.CARD_SKILL_BG_SILHOUETTE;
/*     */         break;
/*     */     } 
/*  66 */     this.color = c;
/*  67 */     this.isSuper = false;
/*     */   }
/*     */   
/*     */   public void update() {
/*  71 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  72 */     if (this.duration < 0.0F) {
/*  73 */       this.isDone = true;
/*     */     } else {
/*  75 */       this.yScale = Interpolation.bounceIn.apply(1.2F, 1.1F, this.duration * 2.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  81 */     sb.setBlendFunction(770, 1);
/*  82 */     this.color.a = this.duration;
/*  83 */     sb.setColor(this.color);
/*     */     
/*  85 */     if (this.isSuper) {
/*  86 */       sb.draw((TextureRegion)this.img, this.card.current_x + this.img.offsetX - this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - this.img.originalHeight / 2.0F, this.img.originalWidth / 2.0F - this.img.offsetX, this.img.originalHeight / 2.0F - this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.52F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.53F * Settings.scale, this.card.angle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       sb.draw((TextureRegion)this.img, this.card.current_x + this.img.offsetX - this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - this.img.originalHeight / 2.0F, this.img.originalWidth / 2.0F - this.img.offsetX, this.img.originalHeight / 2.0F - this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.55F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.57F * Settings.scale, this.card.angle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       sb.draw((TextureRegion)this.img, this.card.current_x + this.img.offsetX - this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - this.img.originalHeight / 2.0F, this.img.originalWidth / 2.0F - this.img.offsetX, this.img.originalHeight / 2.0F - this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.58F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.6F * Settings.scale, this.card.angle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 122 */       sb.draw((TextureRegion)this.img, this.card.current_x + this.img.offsetX - this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - this.img.originalHeight / 2.0F, this.img.originalWidth / 2.0F - this.img.offsetX, this.img.originalHeight / 2.0F - this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.52F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.52F * Settings.scale, this.card.angle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 134 */       sb.draw((TextureRegion)this.img, this.card.current_x + this.img.offsetX - this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - this.img.originalHeight / 2.0F, this.img.originalWidth / 2.0F - this.img.offsetX, this.img.originalHeight / 2.0F - this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.55F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.55F * Settings.scale, this.card.angle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       sb.draw((TextureRegion)this.img, this.card.current_x + this.img.offsetX - this.img.originalWidth / 2.0F, this.card.current_y + this.img.offsetY - this.img.originalHeight / 2.0F, this.img.originalWidth / 2.0F - this.img.offsetX, this.img.originalHeight / 2.0F - this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.card.drawScale * (this.yScale + 1.0F) * 0.58F * Settings.scale, this.card.drawScale * (this.yScale + 1.0F) * 0.58F * Settings.scale, this.card.angle);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\CardFlashVfx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */