/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Matryoshka extends AbstractRelic {
/*    */   public Matryoshka() {
/* 11 */     super("Matryoshka", "matryoshka.png", RelicTier.UNCOMMON, LandingSound.SOLID);
/* 12 */     this.counter = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "Matryoshka";
/*    */   
/*    */   public void onChestOpen(boolean bossChest) {
/* 22 */     if (!bossChest && 
/* 23 */       this.counter > 0) {
/* 24 */       this.counter--;
/*    */       
/* 26 */       flash();
/* 27 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */       
/* 29 */       if (AbstractDungeon.relicRng.randomBoolean(0.75F)) {
/* 30 */         AbstractDungeon.getCurrRoom().addRelicToRewards(RelicTier.COMMON);
/*    */       } else {
/* 32 */         AbstractDungeon.getCurrRoom().addRelicToRewards(RelicTier.UNCOMMON);
/*    */       } 
/* 34 */       if (this.counter == 0) {
/* 35 */         setCounter(-2);
/* 36 */         this.description = this.DESCRIPTIONS[2];
/*    */       } else {
/* 38 */         this.description = this.DESCRIPTIONS[1];
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 46 */     this.counter = setCounter;
/* 47 */     if (setCounter == -2) {
/* 48 */       usedUp();
/* 49 */       this.counter = -2;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 55 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 40);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 60 */     return new Matryoshka();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Matryoshka.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */