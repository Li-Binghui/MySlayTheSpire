/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.SumDamageEffect;
/*    */ 
/*    */ public class DamageNumberEffect extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 1.2F;
/*    */   private float x;
/* 18 */   private static final float OFFSET_Y = 150.0F * Settings.scale; private float y; private float vX; private float vY;
/* 19 */   private static final float GRAVITY_Y = -2000.0F * Settings.scale;
/*    */   private int amt;
/* 21 */   private float scale = 1.0F;
/*    */   public AbstractCreature target;
/*    */   
/*    */   public DamageNumberEffect(AbstractCreature target, float x, float y, int amt) {
/* 25 */     this.duration = 1.2F;
/* 26 */     this.startingDuration = 1.2F;
/* 27 */     this.x = x;
/* 28 */     this.y = y + OFFSET_Y;
/* 29 */     this.target = target;
/*    */     
/* 31 */     this.vX = MathUtils.random(100.0F * Settings.scale, 150.0F * Settings.scale);
/* 32 */     if (MathUtils.randomBoolean()) {
/* 33 */       this.vX = -this.vX;
/*    */     }
/* 35 */     this.vY = MathUtils.random(400.0F * Settings.scale, 500.0F * Settings.scale);
/*    */     
/* 37 */     this.amt = amt;
/* 38 */     this.color = Color.RED.cpy();
/*    */     
/* 40 */     if (!Settings.SHOW_DMG_SUM || amt <= 0) {
/*    */       return;
/*    */     }
/*    */     
/* 44 */     boolean isSumDamageAvailable = false;
/*    */     
/* 46 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 47 */       if (e instanceof SumDamageEffect && 
/* 48 */         ((SumDamageEffect)e).target == target) {
/* 49 */         isSumDamageAvailable = true;
/* 50 */         ((SumDamageEffect)e).refresh(amt);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 55 */     if (!isSumDamageAvailable) {
/* 56 */       for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 57 */         if (e instanceof DamageNumberEffect && e != this && 
/* 58 */           ((DamageNumberEffect)e).target == target) {
/* 59 */           AbstractDungeon.effectsQueue.add(new SumDamageEffect(target, x, y, ((DamageNumberEffect)e).amt + amt));
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 68 */     this.x += Gdx.graphics.getDeltaTime() * this.vX;
/* 69 */     this.y += Gdx.graphics.getDeltaTime() * this.vY;
/* 70 */     this.vY += Gdx.graphics.getDeltaTime() * GRAVITY_Y;
/*    */     
/* 72 */     super.update();
/*    */     
/* 74 */     if (this.color.g != 1.0F) {
/* 75 */       this.color.g += Gdx.graphics.getDeltaTime() * 4.0F;
/* 76 */       if (this.color.g > 1.0F) {
/* 77 */         this.color.g = 1.0F;
/*    */       }
/*    */     } 
/* 80 */     if (this.color.b != 1.0F) {
/* 81 */       this.color.b += Gdx.graphics.getDeltaTime() * 4.0F;
/* 82 */       if (this.color.b > 1.0F) {
/* 83 */         this.color.b = 1.0F;
/*    */       }
/*    */     } 
/* 86 */     this.scale = Interpolation.pow4Out.apply(6.0F, 1.2F, 1.0F - this.duration / 1.2F) * Settings.scale;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 93 */     FontHelper.damageNumberFont.getData().setScale(this.scale);
/* 94 */     FontHelper.renderFontCentered(sb, FontHelper.damageNumberFont, Integer.toString(this.amt), this.x, this.y, this.color);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DamageNumberEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */