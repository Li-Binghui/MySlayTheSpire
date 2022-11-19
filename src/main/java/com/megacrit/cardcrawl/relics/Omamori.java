/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Omamori extends AbstractRelic {
/*    */   public static final String ID = "Omamori";
/*    */   
/*    */   public Omamori() {
/* 10 */     super("Omamori", "omamori.png", RelicTier.COMMON, LandingSound.FLAT);
/* 11 */     this.counter = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 21 */     this.counter = setCounter;
/* 22 */     if (setCounter == 0) {
/* 23 */       usedUp();
/* 24 */     } else if (setCounter == 1) {
/* 25 */       this.description = this.DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */   
/*    */   public void use() {
/* 30 */     flash();
/* 31 */     this.counter--;
/* 32 */     if (this.counter == 0) {
/* 33 */       setCounter(0);
/*    */     } else {
/* 35 */       this.description = this.DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 41 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 46 */     return new Omamori();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Omamori.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */