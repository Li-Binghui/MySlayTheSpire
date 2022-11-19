/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DEPRECATEDGroundedPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Grounded";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Grounded");
/* 16 */   public static final String NAME = powerStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DEPRECATEDGroundedPower(AbstractCreature owner) {
/* 20 */     this.name = NAME;
/* 21 */     this.ID = "Grounded";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = -1;
/* 24 */     updateDescription();
/* 25 */     loadRegion("corruption");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 35 */     if (card.type == AbstractCard.CardType.SKILL) {
/* 36 */       flash();
/* 37 */       addToBot((AbstractGameAction)new ChangeStanceAction("Calm"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDGroundedPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */