/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ 
/*    */ public class HappyFlower
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Happy Flower";
/*    */   
/*    */   public HappyFlower() {
/* 16 */     super("Happy Flower", "sunflower.png", RelicTier.COMMON, LandingSound.SOLID);
/*    */   }
/*    */   private static final int NUM_TURNS = 3; private static final int ENERGY_AMT = 1;
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
/* 29 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
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
/*    */   public void onEquip() {
/* 42 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 47 */     if (this.counter == -1) {
/* 48 */       this.counter += 2;
/*    */     } else {
/* 50 */       this.counter++;
/*    */     } 
/*    */     
/* 53 */     if (this.counter == 3) {
/* 54 */       this.counter = 0;
/* 55 */       flash();
/* 56 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 57 */       addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 63 */     return new HappyFlower();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\HappyFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */