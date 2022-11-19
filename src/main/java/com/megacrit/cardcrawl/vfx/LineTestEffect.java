/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LineTestEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 14 */   private static final float SPACING = 30.0F * Settings.scale; private float x2; private float y2;
/* 15 */   private ArrayList<MapDot> dots = new ArrayList<>();
/*    */   
/*    */   public LineTestEffect() {
/* 18 */     this.x = InputHelper.mX;
/* 19 */     this.y = InputHelper.mY;
/* 20 */     this.x2 = Settings.WIDTH / 2.0F;
/* 21 */     this.y2 = Settings.HEIGHT / 2.0F;
/*    */     
/* 23 */     Vector2 vec2 = (new Vector2(this.x2, this.y2)).sub(new Vector2(this.x, this.y));
/* 24 */     float length = vec2.len();
/* 25 */     float START = SPACING * MathUtils.random();
/*    */     float i;
/* 27 */     for (i = START; i < length; i += SPACING) {
/* 28 */       vec2.clamp(length - i, length - i);
/* 29 */       this.dots.add(new MapDot(this.x + vec2.x, this.y + vec2.y, (new Vector2(this.x - this.x2, this.y - this.y2)).nor().angle() + 90.0F, true));
/*    */     } 
/*    */     
/* 32 */     this.duration = 3.0F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 36 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 37 */     if (this.duration < 0.0F) {
/* 38 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 44 */     for (MapDot d : this.dots)
/* 45 */       d.render(sb); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\LineTestEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */