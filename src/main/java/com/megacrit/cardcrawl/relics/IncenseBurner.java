/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

/*    */
/*    */ public class IncenseBurner extends AbstractRelic {
/*    */   public static final String ID = "Incense Burner";
/*    */   
/*    */   public IncenseBurner() {
/* 13 */     super("Incense Burner", "incenseBurner.png", RelicTier.RARE, LandingSound.CLINK);
/*    */   }
/*    */   private static final int NUM_TURNS = 6;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 23 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 28 */     if (this.counter == -1) {
/* 29 */       this.counter += 2;
/*    */     } else {
/* 31 */       this.counter++;
/*    */     } 
/*    */     
/* 34 */     if (this.counter == 6) {
/* 35 */       this.counter = 0;
/* 36 */       flash();
/* 37 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 38 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, null, (AbstractPower)new IntangiblePlayerPower((AbstractCreature)AbstractDungeon.player, 1), 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new IncenseBurner();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\IncenseBurner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */