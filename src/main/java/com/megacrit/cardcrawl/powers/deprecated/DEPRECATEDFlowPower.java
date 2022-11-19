/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ import com.megacrit.cardcrawl.powers.LoseDexterityPower;
/*    */ import com.megacrit.cardcrawl.powers.LoseStrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class DEPRECATEDFlowPower extends AbstractPower {
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FlowPower"); public static final String POWER_ID = "FlowPower";
/*    */   
/*    */   public DEPRECATEDFlowPower(AbstractCreature owner, int amount) {
/* 20 */     this.name = powerStrings.NAME;
/* 21 */     this.ID = "FlowPower";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = amount;
/* 24 */     updateDescription();
/* 25 */     loadRegion("afterImage");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 36 */     if (card.type == AbstractCard.CardType.SKILL) {
/* 37 */       flash();
/* 38 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, this.amount), this.amount));
/* 39 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseStrengthPower(this.owner, this.amount), this.amount));
/*    */     }
/* 41 */     else if (card.type == AbstractCard.CardType.ATTACK) {
/* 42 */       flash();
/* 43 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new DexterityPower(this.owner, this.amount), this.amount));
/* 44 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseDexterityPower(this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDFlowPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */