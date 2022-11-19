/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class HexPower extends AbstractPower {
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Hex"); public static final String POWER_ID = "Hex";
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public HexPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Hex";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
/* 23 */     loadRegion("hex");
/* 24 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 29 */     if (card.type != AbstractCard.CardType.ATTACK) {
/* 30 */       flash();
/* 31 */       addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Dazed(), this.amount, true, true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\HexPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */