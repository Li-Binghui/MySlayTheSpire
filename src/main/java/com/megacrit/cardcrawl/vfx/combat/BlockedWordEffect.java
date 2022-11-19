/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class BlockedWordEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 2.3F;
/* 14 */   private static final float OFFSET_Y = 150.0F * Settings.scale; private float x; private float y;
/*    */   private String msg;
/* 16 */   private float scale = 1.0F;
/*    */   public AbstractCreature target;
/*    */   
/*    */   public BlockedWordEffect(AbstractCreature target, float x, float y, String msg) {
/* 20 */     this.duration = 2.3F;
/* 21 */     this.startingDuration = 2.3F;
/* 22 */     this.x = x;
/* 23 */     this.y = y + OFFSET_Y;
/* 24 */     this.target = target;
/* 25 */     this.msg = msg;
/* 26 */     this.color = Color.WHITE.cpy();
/*    */   }
/*    */   
/*    */   public void update() {
/* 30 */     this.y += Gdx.graphics.getDeltaTime() * this.duration * 100.0F * Settings.scale;
/*    */     
/* 32 */     super.update();
/* 33 */     this.scale = Settings.scale * this.duration / 2.3F + 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 38 */     FontHelper.damageNumberFont.getData().setScale(this.scale);
/* 39 */     FontHelper.renderFontCentered(sb, FontHelper.damageNumberFont, this.msg, this.x, this.y, this.color);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BlockedWordEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */