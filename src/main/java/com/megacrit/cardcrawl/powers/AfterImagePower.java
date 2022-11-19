/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class AfterImagePower extends AbstractPower {
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("After Image"); public static final String POWER_ID = "After Image";
/*    */   
/*    */   public AfterImagePower(AbstractCreature owner, int amount) {
/* 17 */     this.name = powerStrings.NAME;
/* 18 */     this.ID = "After Image";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = amount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("afterImage");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 32 */     if (Settings.FAST_MODE) {
/* 33 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.amount, true));
/*    */     } else {
/* 35 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.amount));
/*    */     } 
/* 37 */     flash();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\AfterImagePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */