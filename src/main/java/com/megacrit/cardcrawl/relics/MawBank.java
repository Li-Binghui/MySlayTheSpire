/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class MawBank
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "MawBank";
/*    */   private static final int GOLD_AMT = 12;
/*    */   
/*    */   public MawBank() {
/* 13 */     super("MawBank", "bank.png", RelicTier.COMMON, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\f' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom(AbstractRoom room) {
/* 23 */     if (!this.usedUp) {
/* 24 */       flash();
/* 25 */       AbstractDungeon.player.gainGold(12);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpendGold() {
/* 31 */     if (!this.usedUp) {
/* 32 */       flash();
/* 33 */       setCounter(-2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 39 */     this.counter = setCounter;
/* 40 */     if (setCounter == -2) {
/* 41 */       usedUp();
/* 42 */       this.counter = -2;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 48 */     return ((Settings.isEndless || AbstractDungeon.floorNum <= 48) && 
/* 49 */       !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 54 */     return new MawBank();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MawBank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */