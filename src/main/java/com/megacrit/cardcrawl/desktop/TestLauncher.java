/*    */ package com.megacrit.cardcrawl.desktop;
/*    */ 
/*    */ import com.badlogic.gdx.ApplicationListener;
/*    */ import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
/*    */ import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
/*    */ import com.megacrit.cardcrawl.core.TestGame;
/*    */ 
/*    */ public class TestLauncher {
/*    */   public static void main(String[] arg) {
/* 10 */     LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
/* 11 */     config.resizable = false;
/* 12 */     config.title = "1600x900 Fullscreen Test";
/* 13 */     config.width = 1600;
/* 14 */     config.height = 900;
/* 15 */     config.fullscreen = true;
/* 16 */     config.foregroundFPS = 60;
/* 17 */     config.backgroundFPS = 24;
/* 18 */     new LwjglApplication((ApplicationListener)new TestGame(), config);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\desktop\TestLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */