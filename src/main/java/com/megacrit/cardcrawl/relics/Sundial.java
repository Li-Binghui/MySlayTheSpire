/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Sundial
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Sundial";
/*    */   
/*    */   public Sundial() {
/* 15 */     super("Sundial", "sundial.png", RelicTier.UNCOMMON, LandingSound.SOLID);
/*    */   }
/*    */   private static final int NUM_TURNS = 3; private static final int ENERGY_AMT = 2;
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
/* 28 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 33 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onShuffle() {
/* 38 */     this.counter++;
/*    */     
/* 40 */     if (this.counter == 3) {
/* 41 */       this.counter = 0;
/* 42 */       flash();
/* 43 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 44 */       addToBot((AbstractGameAction)new GainEnergyAction(2));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 50 */     return new Sundial();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Sundial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */