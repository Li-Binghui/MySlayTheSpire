/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
/*     */ 
/*     */ public class FlashPotionEffect
/*     */   extends AbstractGameEffect {
/*     */   private static final int W = 64;
/*     */   private static final float DURATION = 1.0F;
/*  16 */   private static final float END_SCALE = 8.0F * Settings.scale;
/*     */   
/*     */   private Texture containerImg;
/*     */   private Texture liquidImg;
/*     */   
/*     */   public FlashPotionEffect(AbstractPotion p) {
/*  22 */     this.duration = 1.0F;
/*  23 */     this.liquidColor = p.liquidColor.cpy();
/*  24 */     if (p.hybridColor != null) {
/*  25 */       this.renderHybrid = true;
/*  26 */       this.hybridColor = p.hybridColor.cpy();
/*     */     } 
/*  28 */     if (p.spotsColor != null) {
/*  29 */       this.renderSpots = true;
/*  30 */       this.spotsColor = p.spotsColor.cpy();
/*     */     } 
/*  32 */     this.color = Color.WHITE.cpy();
/*     */     
/*  34 */     switch (p.size) {
/*     */       case T:
/*  36 */         this.containerImg = ImageMaster.POTION_T_CONTAINER;
/*  37 */         this.liquidImg = ImageMaster.POTION_T_LIQUID;
/*  38 */         this.hybridImg = ImageMaster.POTION_T_HYBRID;
/*  39 */         this.spotsImg = ImageMaster.POTION_T_SPOTS;
/*     */         break;
/*     */       case S:
/*  42 */         this.containerImg = ImageMaster.POTION_S_CONTAINER;
/*  43 */         this.liquidImg = ImageMaster.POTION_S_LIQUID;
/*  44 */         this.hybridImg = ImageMaster.POTION_S_HYBRID;
/*  45 */         this.spotsImg = ImageMaster.POTION_S_SPOTS;
/*     */         break;
/*     */       case M:
/*  48 */         this.containerImg = ImageMaster.POTION_M_CONTAINER;
/*  49 */         this.liquidImg = ImageMaster.POTION_M_LIQUID;
/*  50 */         this.hybridImg = ImageMaster.POTION_M_HYBRID;
/*  51 */         this.spotsImg = ImageMaster.POTION_M_SPOTS;
/*     */         break;
/*     */       case SPHERE:
/*  54 */         this.containerImg = ImageMaster.POTION_SPHERE_CONTAINER;
/*  55 */         this.liquidImg = ImageMaster.POTION_SPHERE_LIQUID;
/*  56 */         this.hybridImg = ImageMaster.POTION_SPHERE_HYBRID;
/*  57 */         this.spotsImg = ImageMaster.POTION_SPHERE_SPOTS;
/*     */         break;
/*     */       case H:
/*  60 */         this.containerImg = ImageMaster.POTION_H_CONTAINER;
/*  61 */         this.liquidImg = ImageMaster.POTION_H_LIQUID;
/*  62 */         this.hybridImg = ImageMaster.POTION_H_HYBRID;
/*  63 */         this.spotsImg = ImageMaster.POTION_H_SPOTS;
/*     */         break;
/*     */       case BOTTLE:
/*  66 */         this.containerImg = ImageMaster.POTION_BOTTLE_CONTAINER;
/*  67 */         this.liquidImg = ImageMaster.POTION_BOTTLE_LIQUID;
/*  68 */         this.hybridImg = ImageMaster.POTION_BOTTLE_HYBRID;
/*  69 */         this.spotsImg = ImageMaster.POTION_BOTTLE_SPOTS;
/*     */         break;
/*     */       case HEART:
/*  72 */         this.containerImg = ImageMaster.POTION_HEART_CONTAINER;
/*  73 */         this.liquidImg = ImageMaster.POTION_HEART_LIQUID;
/*  74 */         this.hybridImg = ImageMaster.POTION_HEART_HYBRID;
/*  75 */         this.spotsImg = ImageMaster.POTION_HEART_SPOTS;
/*     */         break;
/*     */       case SNECKO:
/*  78 */         this.containerImg = ImageMaster.POTION_SNECKO_CONTAINER;
/*  79 */         this.liquidImg = ImageMaster.POTION_SNECKO_LIQUID;
/*  80 */         this.hybridImg = ImageMaster.POTION_SNECKO_HYBRID;
/*  81 */         this.spotsImg = ImageMaster.POTION_SNECKO_SPOTS;
/*     */         break;
/*     */       case FAIRY:
/*  84 */         this.containerImg = ImageMaster.POTION_FAIRY_CONTAINER;
/*  85 */         this.liquidImg = ImageMaster.POTION_FAIRY_LIQUID;
/*  86 */         this.hybridImg = ImageMaster.POTION_FAIRY_HYBRID;
/*  87 */         this.spotsImg = ImageMaster.POTION_FAIRY_SPOTS;
/*     */         break;
/*     */       case GHOST:
/*  90 */         this.containerImg = ImageMaster.POTION_GHOST_CONTAINER;
/*  91 */         this.liquidImg = ImageMaster.POTION_GHOST_LIQUID;
/*  92 */         this.hybridImg = ImageMaster.POTION_GHOST_HYBRID;
/*  93 */         this.spotsImg = ImageMaster.POTION_GHOST_SPOTS;
/*     */         break;
/*     */       case JAR:
/*  96 */         this.containerImg = ImageMaster.POTION_JAR_CONTAINER;
/*  97 */         this.liquidImg = ImageMaster.POTION_JAR_LIQUID;
/*  98 */         this.hybridImg = ImageMaster.POTION_JAR_HYBRID;
/*  99 */         this.spotsImg = ImageMaster.POTION_JAR_SPOTS;
/*     */         break;
/*     */       case BOLT:
/* 102 */         this.containerImg = ImageMaster.POTION_BOLT_CONTAINER;
/* 103 */         this.liquidImg = ImageMaster.POTION_BOLT_LIQUID;
/* 104 */         this.hybridImg = ImageMaster.POTION_BOLT_HYBRID;
/* 105 */         this.spotsImg = ImageMaster.POTION_BOLT_SPOTS;
/*     */         break;
/*     */       case CARD:
/* 108 */         this.containerImg = ImageMaster.POTION_CARD_CONTAINER;
/* 109 */         this.liquidImg = ImageMaster.POTION_CARD_LIQUID;
/* 110 */         this.hybridImg = ImageMaster.POTION_CARD_HYBRID;
/* 111 */         this.spotsImg = ImageMaster.POTION_CARD_SPOTS;
/*     */         break;
/*     */       case MOON:
/* 114 */         this.containerImg = ImageMaster.POTION_MOON_CONTAINER;
/* 115 */         this.liquidImg = ImageMaster.POTION_MOON_LIQUID;
/* 116 */         this.hybridImg = ImageMaster.POTION_MOON_HYBRID;
/*     */         break;
/*     */       case SPIKY:
/* 119 */         this.containerImg = ImageMaster.POTION_SPIKY_CONTAINER;
/* 120 */         this.liquidImg = ImageMaster.POTION_SPIKY_LIQUID;
/* 121 */         this.hybridImg = ImageMaster.POTION_SPIKY_HYBRID;
/*     */         break;
/*     */       case EYE:
/* 124 */         this.containerImg = ImageMaster.POTION_EYE_CONTAINER;
/* 125 */         this.liquidImg = ImageMaster.POTION_EYE_LIQUID;
/* 126 */         this.hybridImg = ImageMaster.POTION_EYE_HYBRID;
/*     */         break;
/*     */       case ANVIL:
/* 129 */         this.containerImg = ImageMaster.POTION_ANVIL_CONTAINER;
/* 130 */         this.liquidImg = ImageMaster.POTION_ANVIL_LIQUID;
/* 131 */         this.hybridImg = ImageMaster.POTION_ANVIL_HYBRID;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   private Texture hybridImg; private Texture spotsImg;
/*     */   private Color liquidColor;
/*     */   private Color color;
/*     */   
/*     */   public void update() {
/* 140 */     this.scale = Interpolation.exp10In.apply(Settings.scale, END_SCALE * 2.0F, this.duration / 1.0F);
/*     */     
/* 142 */     if (this.duration > 0.3F) {
/* 143 */       this.liquidColor.a = Interpolation.pow2.apply(0.4F, 0.05F, this.duration / 1.0F);
/*     */     } else {
/* 145 */       this.liquidColor.a = this.duration * 2.0F;
/*     */     } 
/*     */     
/* 148 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */ 
/*     */     
/* 151 */     this.color.a = this.liquidColor.a;
/* 152 */     if (this.renderHybrid) {
/* 153 */       this.hybridColor.a = this.liquidColor.a;
/*     */     }
/* 155 */     if (this.renderSpots) {
/* 156 */       this.spotsColor.a = this.liquidColor.a;
/*     */     }
/*     */     
/* 159 */     if (this.duration < 0.0F)
/* 160 */       this.isDone = true; 
/*     */   }
/*     */   
/*     */   private Color hybridColor;
/*     */   
/*     */   public void render(SpriteBatch sb, float x, float y) {
/* 166 */     sb.setColor(this.color);
/* 167 */     sb.draw(this.containerImg, x - 32.0F, y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/* 168 */     sb.setColor(this.liquidColor);
/* 169 */     sb.draw(this.liquidImg, x - 32.0F, y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/* 170 */     sb.setBlendFunction(770, 1);
/* 171 */     sb.setColor(this.color);
/* 172 */     sb.draw(this.containerImg, x - 32.0F, y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/* 173 */     sb.setColor(this.liquidColor);
/* 174 */     sb.draw(this.liquidImg, x - 32.0F, y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     
/* 176 */     if (this.renderHybrid) {
/* 177 */       sb.setColor(this.hybridColor);
/* 178 */       sb.draw(this.hybridImg, x - 32.0F, y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (this.renderSpots) {
/* 197 */       sb.setColor(this.spotsColor);
/* 198 */       sb.draw(this.spotsImg, x - 32.0F, y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */     
/* 201 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   private Color spotsColor;
/*     */   private boolean renderHybrid = false, renderSpots = false;
/*     */   
/*     */   public void dispose() {}
/*     */   
/*     */   public void render(SpriteBatch sb) {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FlashPotionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */