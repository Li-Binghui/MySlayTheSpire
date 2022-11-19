/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class NirvanaPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Nirvana"); public static final String POWER_ID = "Nirvana";
/*    */   
/*    */   public NirvanaPower(AbstractCreature owner, int amt) {
/* 14 */     this.name = powerStrings.NAME;
/* 15 */     this.ID = "Nirvana";
/* 16 */     this.owner = owner;
/* 17 */     this.amount = amt;
/* 18 */     updateDescription();
/* 19 */     loadRegion("nirvana");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 25 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onScry() {
/* 30 */     flash();
/* 31 */     addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\NirvanaPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */