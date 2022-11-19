/*    */ package com.megacrit.cardcrawl.screens.stats;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class AchievementItem {
/* 15 */   public Hitbox hb = new Hitbox(160.0F * Settings.scale, 160.0F * Settings.scale); private TextureAtlas.AtlasRegion img; private String title;
/*    */   private String desc;
/*    */   public String key;
/*    */   public boolean isUnlocked;
/* 19 */   private static final Color LOCKED_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.8F);
/*    */   
/*    */   public AchievementItem(String title, String desc, String imgUrl, String key, boolean hidden) {
/* 22 */     this.isUnlocked = UnlockTracker.achievementPref.getBoolean(key, false);
/* 23 */     this.key = key;
/* 24 */     if (this.isUnlocked) {
/* 25 */       CardCrawlGame.publisherIntegration.unlockAchievement(key);
/* 26 */       this.title = title;
/* 27 */       this.desc = desc;
/* 28 */       if (StatsScreen.atlas != null) {
/* 29 */         this.img = StatsScreen.atlas.findRegion("unlocked/" + imgUrl);
/*    */       }
/* 31 */     } else if (hidden) {
/* 32 */       this.title = AchievementGrid.NAMES[26];
/* 33 */       this.desc = AchievementGrid.TEXT[26];
/* 34 */       if (StatsScreen.atlas != null) {
/* 35 */         this.img = StatsScreen.atlas.findRegion("locked/" + imgUrl);
/*    */       }
/*    */     } else {
/* 38 */       this.title = title;
/* 39 */       this.desc = desc;
/* 40 */       if (StatsScreen.atlas != null) {
/* 41 */         this.img = StatsScreen.atlas.findRegion("locked/" + imgUrl);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public AchievementItem(String title, String desc, String imgUrl, String key) {
/* 47 */     this(title, desc, imgUrl, key, false);
/*    */   }
/*    */   
/*    */   public void update() {
/* 51 */     if (this.hb != null) {
/* 52 */       this.hb.update();
/* 53 */       if (this.hb.hovered) {
/* 54 */         TipHelper.renderGenericTip(InputHelper.mX + 100.0F * Settings.scale, InputHelper.mY, this.title, this.desc);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb, float x, float y) {
/* 60 */     if (!this.isUnlocked) {
/* 61 */       sb.setColor(LOCKED_COLOR);
/*    */     } else {
/* 63 */       sb.setColor(Color.WHITE);
/*    */     } 
/*    */     
/* 66 */     if (this.hb.hovered) {
/* 67 */       sb.draw((TextureRegion)this.img, x - this.img.packedWidth / 2.0F, y - this.img.packedHeight / 2.0F, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, Settings.scale * 1.1F, Settings.scale * 1.1F, 0.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/*    */     else {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 79 */       sb.draw((TextureRegion)this.img, x - this.img.packedWidth / 2.0F, y - this.img.packedHeight / 2.0F, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, Settings.scale, Settings.scale, 0.0F);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 91 */     this.hb.move(x, y);
/* 92 */     this.hb.render(sb);
/*    */   }
/*    */   
/*    */   public void reloadImg() {
/* 96 */     this.img = StatsScreen.atlas.findRegion(this.img.name);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\stats\AchievementItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */