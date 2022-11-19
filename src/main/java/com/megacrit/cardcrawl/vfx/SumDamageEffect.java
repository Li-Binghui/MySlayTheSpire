/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ 
/*    */ public class SumDamageEffect extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 2.5F;
/*    */   private float x;
/* 12 */   private static final float OFFSET_Y = 200.0F * Settings.scale; private float y; private float vY;
/*    */   private int amt;
/* 14 */   private float scale = 3.0F * Settings.scale;
/*    */   public AbstractCreature target;
/*    */   
/*    */   public SumDamageEffect(AbstractCreature target, float x, float y, int amt) {
/* 18 */     this.duration = 2.5F;
/* 19 */     this.startingDuration = 2.5F;
/* 20 */     this.x = x;
/* 21 */     this.y = y + OFFSET_Y;
/* 22 */     this.vY = 90.0F * Settings.scale;
/* 23 */     this.target = target;
/* 24 */     this.amt = amt;
/* 25 */     this.color = Settings.GOLD_COLOR.cpy();
/*    */   }
/*    */   
/*    */   public void update() {
/* 29 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 30 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 31 */     if (this.vY > 0.0F) {
/* 32 */       this.vY -= 50.0F * Settings.scale * Gdx.graphics.getDeltaTime();
/*    */     }
/*    */     
/* 35 */     this.scale = Settings.scale * this.duration / 2.5F + 1.3F;
/* 36 */     if (this.duration < 1.0F) {
/* 37 */       this.color.a = this.duration;
/*    */     }
/* 39 */     if (this.duration < 0.0F) {
/* 40 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 46 */     FontHelper.damageNumberFont.getData().setScale(this.scale);
/* 47 */     FontHelper.renderFontCentered(sb, FontHelper.damageNumberFont, Integer.toString(this.amt), this.x, this.y, this.color);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() {}
/*    */ 
/*    */   
/*    */   public void refresh(int amt) {
/* 56 */     this.amt += amt;
/* 57 */     this.duration = 2.5F;
/* 58 */     this.color.a = 1.0F;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\SumDamageEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */