/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.GameDataStringBuilder;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ 
/*    */ public class AbstractDailyMod
/*    */ {
/*    */   public String name;
/*    */   public String description;
/*    */   public String modID;
/*    */   
/*    */   public AbstractDailyMod(String setId, String name, String description, String imgUrl, boolean positive) {
/* 16 */     this(setId, name, description, imgUrl, positive, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public Texture img;
/*    */   public boolean positive;
/*    */   public AbstractPlayer.PlayerClass classToExclude;
/*    */   private static final String IMG_DIR = "images/ui/run_mods/";
/*    */   
/*    */   public AbstractDailyMod(String setId, String name, String description, String imgUrl, boolean positive, AbstractPlayer.PlayerClass exclusion) {
/* 26 */     this.modID = setId;
/* 27 */     this.name = name;
/* 28 */     this.description = description;
/* 29 */     this.positive = positive;
/* 30 */     this.img = ImageMaster.loadImage("images/ui/run_mods/" + imgUrl);
/* 31 */     this.classToExclude = exclusion;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void effect() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public static String gameDataUploadHeader() {
/* 41 */     GameDataStringBuilder sb = new GameDataStringBuilder();
/* 42 */     sb.addFieldData("name");
/* 43 */     sb.addFieldData("text");
/* 44 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public String gameDataUploadData() {
/* 48 */     GameDataStringBuilder sb = new GameDataStringBuilder();
/* 49 */     sb.addFieldData(this.name);
/* 50 */     sb.addFieldData(this.description);
/* 51 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\AbstractDailyMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */