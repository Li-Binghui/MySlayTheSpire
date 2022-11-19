/*    */ package com.megacrit.cardcrawl.vfx.shader;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.graphics.glutils.FrameBuffer;
/*    */ import com.badlogic.gdx.graphics.glutils.ShaderProgram;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.badlogic.gdx.math.Vector3;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ShaderEffect
/*    */ {
/*    */   private ArrayList<TextureRegion> heatRegions;
/*    */   private ArrayList<Vector2> heatCoords;
/*    */   private ArrayList<Vector2> heatDimensions;
/*    */   private Vector3 coords;
/* 19 */   private float time = 0.0F;
/*    */   private ShaderProgram shader;
/*    */   
/*    */   public ShaderEffect(FrameBuffer frameBuffer) {
/* 23 */     this.coords = new Vector3(0.0F, 0.0F, 0.0F);
/* 24 */     this.heatRegions = new ArrayList<>();
/* 25 */     this.heatCoords = new ArrayList<>();
/* 26 */     this.heatDimensions = new ArrayList<>();
/*    */     
/* 28 */     this
/*    */       
/* 30 */       .shader = new ShaderProgram(Gdx.files.internal("shaders/water/vertex.vs").readString(), Gdx.files.internal("shaders/water/fragment.fs").readString());
/*    */     
/* 32 */     this.heatRegions.add(new TextureRegion((Texture)frameBuffer.getColorBufferTexture()));
/* 33 */     this.heatCoords.add(new Vector2(0.0F, 0.0F));
/* 34 */     this.heatDimensions.add(new Vector2(32.0F, 32.0F));
/*    */   }
/*    */   
/*    */   public void update() {
/* 38 */     float dt = Gdx.graphics.getDeltaTime();
/* 39 */     this.time += dt;
/* 40 */     float angle = this.time * 6.2831855F;
/* 41 */     if (angle > 6.2831855F) {
/* 42 */       angle -= 6.2831855F;
/*    */     }
/* 44 */     Gdx.gl20.glBlendFunc(770, 771);
/* 45 */     Gdx.gl20.glEnable(3042);
/* 46 */     this.shader.begin();
/* 47 */     this.shader.setUniformf("timedelta", -angle);
/* 48 */     this.shader.end();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, FrameBuffer frameBuffer) {
/* 53 */     sb.begin();
/* 54 */     for (int i = 0; i < this.heatRegions.size(); i++) {
/* 55 */       TextureRegion region = this.heatRegions.get(i);
/* 56 */       this.coords.set(((Vector2)this.heatCoords.get(i)).x, ((Vector2)this.heatCoords.get(i)).y, 0.0F);
/*    */       
/* 58 */       region.setTexture((Texture)frameBuffer.getColorBufferTexture());
/*    */       
/* 60 */       region.setRegion(this.coords.x, this.coords.y, ((Vector2)this.heatDimensions.get(i)).x * 1.0F, ((Vector2)this.heatDimensions.get(i)).y * 1.0F);
/*    */       
/* 62 */       sb.draw(region, this.coords.x, this.coords.y, ((Vector2)this.heatDimensions.get(i)).x * 1.0F, ((Vector2)this.heatDimensions.get(i)).y * 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\shader\ShaderEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */