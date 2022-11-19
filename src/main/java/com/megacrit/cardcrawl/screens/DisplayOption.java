/*    */ package com.megacrit.cardcrawl.screens;
/*    */ 
/*    */ public class DisplayOption implements Comparable<DisplayOption> {
/*    */   public int width;
/*  5 */   public String aspectRatio = " TAB TAB"; public int height;
/*    */   
/*    */   public DisplayOption(int width, int height) {
/*  8 */     this.width = width;
/*  9 */     this.height = height;
/*    */   }
/*    */   
/*    */   public DisplayOption(int width, int height, boolean showAspectRatio) {

/* 13 */     this.width = width;
/* 14 */     this.height = height;
/* 16 */     if (showAspectRatio) {
/* 17 */       appendAspectRatio();
/*    */     }
/*    */   }
/*    */   
/*    */   private void appendAspectRatio() {
/* 22 */     float ratio = this.width / this.height;
/*    */     
/* 24 */     if (ratio > 1.25F && ratio < 1.26F) {
/* 25 */       this.aspectRatio = " (5:4)";
/* 26 */     } else if (ratio > 1.32F && ratio < 1.34F) {
/* 27 */       this.aspectRatio = " (4:3)";
/* 28 */     } else if (ratio > 1.76F && ratio < 1.78F) {
/* 29 */       this.aspectRatio = " (16:9)";
/* 30 */     } else if (ratio > 1.59F && ratio < 1.61F) {
/* 31 */       this.aspectRatio = " (16:10)";
/* 32 */     } else if (ratio > 2.32F && ratio < 2.34F) {
/* 33 */       this.aspectRatio = " (21:9)";
/*    */     } else {
/* 35 */       this.aspectRatio = " (" + String.format("#.##", new Object[] { Float.valueOf(ratio) }) + ")";
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(DisplayOption other) {
/* 41 */     if (this.width == other.width) {
/* 42 */       if (this.height == other.height)
/* 43 */         return 0; 
/* 44 */       if (this.height < other.height) {
/* 45 */         return -1;
/*    */       }
/* 47 */       return 1;
/*    */     } 
/* 49 */     if (this.width < other.width) {
/* 50 */       return -1;
/*    */     }
/* 52 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object other) {
/* 58 */     return (((DisplayOption)other).width == this.width && ((DisplayOption)other).height == this.height);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return "(" + this.width + "," + this.height + ")";
/*    */   }
/*    */   
/*    */   public String uiString() {
/* 67 */     return this.width + " x " + this.height + this.aspectRatio;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DisplayOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */