/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class MetallicizePower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Metallicize"); public static final String POWER_ID = "Metallicize";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public MetallicizePower(AbstractCreature owner, int armorAmt) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "Metallicize";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = armorAmt;
/* 19 */     updateDescription();
/* 20 */     loadRegion("armor");
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 25 */     CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     if (this.owner.isPlayer) {
/* 31 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 33 */       this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
/* 39 */     flash();
/* 40 */     addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\MetallicizePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */