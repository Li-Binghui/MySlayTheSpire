/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class SmilingMask
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Smiling Mask";
/*    */   public static final int COST = 50;
/*    */   
/*    */   public SmilingMask() {
/* 13 */     super("Smiling Mask", "merchantMask.png", RelicTier.COMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '2' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom(AbstractRoom room) {
/* 23 */     if (room instanceof com.megacrit.cardcrawl.rooms.ShopRoom) {
/* 24 */       flash();
/* 25 */       this.pulse = true;
/*    */     } else {
/* 27 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 33 */     return ((Settings.isEndless || AbstractDungeon.floorNum <= 48) && 
/* 34 */       !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 39 */     return new SmilingMask();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\SmilingMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */