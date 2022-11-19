/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ 
/*    */ public class AncientTeaSet extends AbstractRelic {
/*    */   public static final String ID = "Ancient Tea Set";
/*    */   
/*    */   public AncientTeaSet() {
/* 16 */     super("Ancient Tea Set", "tea_set.png", RelicTier.COMMON, LandingSound.SOLID);
/*    */   }
/*    */   private static final int ENERGY_AMT = 2; private boolean firstTurn = true;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     if (AbstractDungeon.player != null) {
/* 22 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 24 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 29 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 34 */     this.description = setDescription(c);
/* 35 */     this.tips.clear();
/* 36 */     this.tips.add(new PowerTip(this.name, this.description));
/* 37 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 42 */     if (this.firstTurn) {
/* 43 */       if (this.counter == -2) {
/* 44 */         this.pulse = false;
/* 45 */         this.counter = -1;
/* 46 */         flash();
/* 47 */         addToTop((AbstractGameAction)new GainEnergyAction(2));
/* 48 */         addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */       } 
/* 50 */       this.firstTurn = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 56 */     this.firstTurn = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCounter(int counter) {
/* 61 */     super.setCounter(counter);
/* 62 */     if (counter == -2) {
/* 63 */       this.pulse = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRestRoom() {
/* 69 */     flash();
/* 70 */     this.counter = -2;
/* 71 */     this.pulse = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 76 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 81 */     return new AncientTeaSet();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\AncientTeaSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */