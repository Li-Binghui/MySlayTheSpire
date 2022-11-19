/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class RetainCardPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Retain Cards";
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Retain Cards");
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public RetainCardPower(AbstractCreature owner, int numCards) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Retain Cards";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = numCards;
/* 21 */     updateDescription();
/* 22 */     loadRegion("retain");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     if (this.amount == 1) {
/* 28 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 30 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 36 */     if (isPlayer && !AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") && 
/* 37 */       !AbstractDungeon.player.hasPower("Equilibrium"))
/* 38 */       addToBot((AbstractGameAction)new RetainCardsAction(this.owner, this.amount)); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\RetainCardPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */