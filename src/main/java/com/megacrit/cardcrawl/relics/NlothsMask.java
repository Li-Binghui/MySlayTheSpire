/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class NlothsMask extends AbstractRelic {
/*    */   public static final String ID = "NlothsMask";
/*    */   
/*    */   public NlothsMask() {
/*  9 */     super("NlothsMask", "nloth.png", RelicTier.SPECIAL, LandingSound.FLAT);
/* 10 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onChestOpenAfter(boolean bossChest) {
/* 15 */     if (!bossChest && 
/* 16 */       this.counter > 0) {
/* 17 */       this.counter--;
/*    */       
/* 19 */       flash();
/* 20 */       AbstractDungeon.getCurrRoom().removeOneRelicFromRewards();
/*    */       
/* 22 */       if (this.counter == 0) {
/* 23 */         setCounter(-2);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 31 */     this.counter = setCounter;
/* 32 */     if (setCounter == -2) {
/* 33 */       usedUp();
/* 34 */       this.counter = -2;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 40 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 45 */     return new NlothsMask();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\NlothsMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */