/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class WingBoots extends AbstractRelic {
/*    */   public static final String ID = "WingedGreaves";
/*    */   
/*    */   public WingBoots() {
/* 10 */     super("WingedGreaves", "winged.png", RelicTier.RARE, LandingSound.FLAT);
/* 11 */     this.counter = 3;
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
/* 22 */     if (this.counter == -2) {
/* 23 */       usedUp();
/* 24 */       this.counter = -2;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 30 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 40);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return new WingBoots();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\WingBoots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */