/*    */ package com.megacrit.cardcrawl.cards;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class DescriptionLine {
/*    */   public String text;
/*    */   public float width;
/*    */   private String[] cachedTokenizedText;
/*    */   private String[] cachedTokenizedTextCN;
/* 10 */   private static final float offsetter = 10.0F * Settings.scale;
/*    */   
/*    */   public DescriptionLine(String text, float width) {
/* 13 */     this.text = text.trim();
/* 14 */     this.width = width -= offsetter;
/*    */   }
/*    */   
/*    */   public String[] getCachedTokenizedText() {
/* 18 */     if (this.cachedTokenizedText == null) {
/* 19 */       this.cachedTokenizedText = tokenize(this.text);
/*    */     }
/* 21 */     return this.cachedTokenizedText;
/*    */   }
/*    */   
/*    */   public String[] getCachedTokenizedTextCN() {
/* 25 */     if (this.cachedTokenizedTextCN == null) {
/* 26 */       this.cachedTokenizedTextCN = tokenizeCN(this.text);
/*    */     }
/* 28 */     return this.cachedTokenizedTextCN;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String[] tokenize(String desc) {
/* 33 */     String[] tokenized = desc.split("\\s+");
/* 34 */     for (int i = 0; i < tokenized.length; i++) {
/* 35 */       tokenized[i] = tokenized[i] + ' ';
/*    */     }
/* 37 */     return tokenized;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static String[] tokenizeCN(String desc) {
/* 43 */     String[] tokenized = desc.split("\\s+");
/* 44 */     for (int i = 0; i < tokenized.length; i++) {
/* 45 */       tokenized[i] = tokenized[i].replace("!", "");
/*    */     }
/* 47 */     return tokenized;
/*    */   }
/*    */   
/*    */   public String getText() {
/* 51 */     return this.text;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\DescriptionLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */