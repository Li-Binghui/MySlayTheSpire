/*    */ package com.megacrit.cardcrawl.monsters;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
/*    */ 
/*    */ public class MonsterInfo
/*    */   implements Comparable<MonsterInfo>
/*    */ {
/* 12 */   private static final Logger logger = LogManager.getLogger(MonsterInfo.class.getName());
/*    */   public String name;
/*    */   public float weight;
/*    */   
/*    */   public MonsterInfo(String name, float weight) {
/* 17 */     this.name = name;
/* 18 */     this.weight = weight;
/*    */   }
/*    */   
/*    */   public static void normalizeWeights(ArrayList<MonsterInfo> list) {
/* 22 */     Collections.sort(list);
/* 23 */     float total = 0.0F;
/* 24 */     for (MonsterInfo i : list) {
/* 25 */       total += i.weight;
/*    */     }
/*    */     
/* 28 */     for (MonsterInfo i : list) {
/* 29 */       i.weight /= total;
/* 30 */       if (Settings.isInfo) {
/* 31 */         logger.info(i.name + ": " + i.weight + "%");
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String roll(ArrayList<MonsterInfo> list, float roll) {
/* 44 */     float currentWeight = 0.0F;
/* 45 */     for (MonsterInfo i : list) {
/* 46 */       currentWeight += i.weight;
/* 47 */       if (roll < currentWeight) {
/* 48 */         return i.name;
/*    */       }
/*    */     } 
/* 51 */     return "ERROR";
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(MonsterInfo other) {
/* 56 */     return Float.compare(this.weight, other.weight);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\MonsterInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */