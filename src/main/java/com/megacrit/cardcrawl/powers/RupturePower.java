/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class RupturePower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Rupture"); public static final String POWER_ID = "Rupture";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public RupturePower(AbstractCreature owner, int strAmt) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Rupture";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = strAmt;
/* 20 */     updateDescription();
/* 21 */     this.isPostActionPower = true;
/* 22 */     loadRegion("rupture");
/*    */   }
/*    */ 
/*    */   
/*    */   public void wasHPLost(DamageInfo info, int damageAmount) {
/* 27 */     if (damageAmount > 0 && info.owner == this.owner) {
/* 28 */       flash();
/* 29 */       addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 35 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\RupturePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */