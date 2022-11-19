/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class FeelNoPainPower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Feel No Pain"); public static final String POWER_ID = "Feel No Pain";
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public FeelNoPainPower(AbstractCreature owner, int amount) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Feel No Pain";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = amount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("noPain");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onExhaust(AbstractCard card) {
/* 32 */     flash();
/* 33 */     addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.amount, Settings.FAST_MODE));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\FeelNoPainPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */