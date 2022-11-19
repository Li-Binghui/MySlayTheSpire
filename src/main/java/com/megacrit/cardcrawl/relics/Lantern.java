/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ 
/*    */ public class Lantern
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Lantern";
/*    */   
/*    */   public Lantern() {
/* 17 */     super("Lantern", "lantern.png", RelicTier.COMMON, LandingSound.SOLID);
/* 18 */     this.energyBased = true;
/*    */   }
/*    */   private static final int ENERGY_AMT = 1; private boolean firstTurn = true;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     if (AbstractDungeon.player != null) {
/* 24 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 26 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 31 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1] + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 36 */     this.description = setDescription(c);
/* 37 */     this.tips.clear();
/* 38 */     this.tips.add(new PowerTip(this.name, this.description));
/* 39 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 44 */     this.firstTurn = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 49 */     if (this.firstTurn) {
/* 50 */       flash();
/* 51 */       addToTop((AbstractGameAction)new GainEnergyAction(1));
/* 52 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 53 */       this.firstTurn = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 59 */     return new Lantern();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Lantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */