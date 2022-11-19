/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ 
/*    */ public class RushdownPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Adaptation";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Adaptation");
/*    */   
/*    */   public RushdownPower(AbstractCreature owner, int amount) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "Adaptation";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("rushdown");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 26 */     if (this.amount > 1) {
/* 27 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
/*    */     } else {
/* 29 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
/* 35 */     if (!oldStance.ID.equals(newStance.ID) && newStance.ID.equals("Wrath")) {
/* 36 */       flash();
/* 37 */       addToBot((AbstractGameAction)new DrawCardAction(this.owner, this.amount));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\RushdownPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */