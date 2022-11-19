/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class StasisPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Stasis";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Stasis");
/*    */   
/*    */   private AbstractCard card;
/*    */   
/*    */   public StasisPower(AbstractCreature owner, AbstractCard card) {
/* 20 */     this.name = powerStrings.NAME;
/* 21 */     this.ID = "Stasis";
/* 22 */     this.owner = owner;
/* 23 */     this.card = card;
/* 24 */     this.amount = -1;
/* 25 */     updateDescription();
/* 26 */     loadRegion("stasis");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 31 */     this.description = powerStrings.DESCRIPTIONS[0] + FontHelper.colorString(this.card.name, "y") + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 37 */     if (AbstractDungeon.player.hand.size() != 10) {
/* 38 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.card, false, true));
/*    */     } else {
/* 40 */       addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(this.card, true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\StasisPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */