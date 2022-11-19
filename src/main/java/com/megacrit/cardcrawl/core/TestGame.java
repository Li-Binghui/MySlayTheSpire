/*    */ package com.megacrit.cardcrawl.core;
/*    */

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
/*    */ 
/*    */ public class TestGame implements ApplicationListener {
/* 11 */   private OrthographicCamera camera = new OrthographicCamera();
/*    */   
/*    */   public static FitViewport viewport;
/*    */   private SpriteBatch sb;
/*    */   private Texture texture;
/*    */   
/*    */   public void create() {
/* 18 */     viewport = new FitViewport(1600.0F, 900.0F, (Camera)this.camera);
/* 19 */     viewport.apply();
/* 20 */     this.sb = new SpriteBatch();
/* 21 */     this.texture = new Texture("images/whiteScreen.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public void render() {
/* 26 */     this.sb.begin();
/* 27 */     this.sb.setColor(Color.RED);
/* 28 */     this.sb.draw(this.texture, 0.0F, 0.0F, 1600.0F, 900.0F);
/* 29 */     this.sb.end();
/*    */   }
/*    */ 
/*    */   
/*    */   public void resize(int width, int height) {
/* 34 */     viewport.update(width, height);
/* 35 */     this.camera.update();
/*    */   }
/*    */   
/*    */   public void pause() {}
/*    */   
/*    */   public void resume() {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\TestGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */