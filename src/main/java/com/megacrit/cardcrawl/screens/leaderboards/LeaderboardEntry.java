/*    */ package com.megacrit.cardcrawl.screens.leaderboards;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.screens.stats.CharStat;
/*    */ 
/*    */ public class LeaderboardEntry {
/*    */   public int rank;
/*    */   public int score;
/*    */   public String name;
/*    */   public boolean isTime = false;
/* 14 */   private Color color = Settings.CREAM_COLOR.cpy();
/* 15 */   private static final float START_Y = 800.0F * Settings.scale;
/* 16 */   private static final float LINE_SPACING = -32.0F * Settings.scale;
/*    */   
/*    */   public LeaderboardEntry(int rank, String name, int score, boolean isTime, boolean isYou) {
/* 19 */     this.rank = rank;
/* 20 */     if (name.length() > 18) {
/* 21 */       this.name = name.substring(0, 18) + "...";
/*    */     } else {
/* 23 */       this.name = name;
/*    */     } 
/* 25 */     this.score = score;
/* 26 */     this.isTime = isTime;
/* 27 */     if (isYou) {
/* 28 */       this.color = Settings.GREEN_TEXT_COLOR.cpy();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, int index) {
/* 34 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, 
/*    */ 
/*    */         
/* 37 */         Integer.toString(this.rank), LeaderboardScreen.RANK_X, index * LINE_SPACING + START_Y, this.color);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.leaderboardFont, this.name, LeaderboardScreen.NAME_X, index * LINE_SPACING + START_Y, this.color);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     if (this.isTime) {
/* 53 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, 
/*    */ 
/*    */           
/* 56 */           CharStat.formatHMSM(this.score), LeaderboardScreen.SCORE_X, index * LINE_SPACING + START_Y, this.color);
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 61 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, 
/*    */ 
/*    */           
/* 64 */           Integer.toString(this.score), LeaderboardScreen.SCORE_X, index * LINE_SPACING + START_Y, this.color);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\leaderboards\LeaderboardEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */