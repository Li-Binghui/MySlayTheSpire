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
/*    */ public class Test1 extends AbstractRelic {
/*    */   public static final String ID = "Test 1";
/*    */   
/*    */   public Test1() {
/* 15 */     super("Test 1", "test1.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */   private static final int ENERGY_AMT = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     if (AbstractDungeon.player != null) {
/* 21 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 23 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 28 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 33 */     this.description = setDescription(c);
/* 34 */     this.tips.clear();
/* 35 */     this.tips.add(new PowerTip(this.name, this.description));
/* 36 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUsePotion() {
/* 41 */     flash();
/* 42 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 43 */     addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 48 */     return new Test1();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Test1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */