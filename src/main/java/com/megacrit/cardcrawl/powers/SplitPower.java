/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class SplitPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Split";
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Split");
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public SplitPower(AbstractCreature owner) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "Split";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = -1;
/* 19 */     updateDescription();
/* 20 */     loadRegion("split");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 25 */     this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.owner.name, "y") + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\SplitPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */