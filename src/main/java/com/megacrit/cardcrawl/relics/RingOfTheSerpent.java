/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RingOfTheSerpent
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Ring of the Serpent";
/*    */   private static final int NUM_CARDS = 1;
/*    */   
/*    */   public RingOfTheSerpent() {
/* 11 */     super("Ring of the Serpent", "serpent_ring.png", RelicTier.BOSS, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 21 */     AbstractDungeon.player.masterHandSize++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 26 */     AbstractDungeon.player.masterHandSize--;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 31 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 36 */     return AbstractDungeon.player.hasRelic("Ring of the Snake");
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 41 */     return new RingOfTheSerpent();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\RingOfTheSerpent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */