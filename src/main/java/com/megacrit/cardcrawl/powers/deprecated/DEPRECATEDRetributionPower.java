/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.watcher.VigorPower;
/*    */ 
/*    */ public class DEPRECATEDRetributionPower extends AbstractPower {
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Retribution"); public static final String POWER_ID = "Retribution";
/*    */   
/*    */   public DEPRECATEDRetributionPower(AbstractCreature owner, int vigorAmt) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "Retribution";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = vigorAmt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("anger");
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 26 */     if (damageAmount > 0) {
/* 27 */       flash();
/* 28 */       addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new VigorPower(this.owner, this.amount), this.amount));
/*    */     } 
/* 30 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 35 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDRetributionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */