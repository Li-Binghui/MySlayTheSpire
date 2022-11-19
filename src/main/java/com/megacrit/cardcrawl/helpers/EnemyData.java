/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */ public class EnemyData {
/*    */   public String name;
/*    */   public int level;
/*    */   public MonsterType type;
/*    */   
/*    */   public enum MonsterType {
/*  9 */     WEAK, STRONG, ELITE, BOSS, EVENT;
/*    */   }
/*    */   
/*    */   public EnemyData(String key, int level, MonsterType type) {
/* 13 */     this.name = key;
/* 14 */     this.level = level;
/* 15 */     this.type = type;
/*    */   }
/*    */   
/*    */   public static String gameDataUploadHeader() {
/* 19 */     GameDataStringBuilder builder = new GameDataStringBuilder();
/* 20 */     builder.addFieldData("name");
/* 21 */     builder.addFieldData("level");
/* 22 */     builder.addFieldData("type");
/* 23 */     return builder.toString();
/*    */   }
/*    */   
/*    */   public String gameDataUploadData() {
/* 27 */     GameDataStringBuilder builder = new GameDataStringBuilder();
/* 28 */     builder.addFieldData(this.name);
/* 29 */     builder.addFieldData(this.level);
/* 30 */     builder.addFieldData(this.type.name());
/* 31 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\EnemyData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */