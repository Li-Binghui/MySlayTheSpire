/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DuplicationPower extends AbstractPower {
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DuplicationPower"); public static final String POWER_ID = "DuplicationPower";
/*    */   
/*    */   public DuplicationPower(AbstractCreature owner, int amount) {
/* 20 */     this.name = powerStrings.NAME;
/* 21 */     this.ID = "DuplicationPower";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = amount;
/* 24 */     updateDescription();
/* 25 */     loadRegion("doubleTap");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     if (this.amount == 1) {
/* 31 */       this.description = powerStrings.DESCRIPTIONS[0];
/*    */     } else {
/* 33 */       this.description = powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 39 */     if (!card.purgeOnUse && this.amount > 0) {
/* 40 */       flash();
/* 41 */       AbstractMonster m = null;
/*    */       
/* 43 */       if (action.target != null) {
/* 44 */         m = (AbstractMonster)action.target;
/*    */       }
/*    */       
/* 47 */       AbstractCard tmp = card.makeSameInstanceOf();
/* 48 */       AbstractDungeon.player.limbo.addToBottom(tmp);
/* 49 */       tmp.current_x = card.current_x;
/* 50 */       tmp.current_y = card.current_y;
/* 51 */       tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
/* 52 */       tmp.target_y = Settings.HEIGHT / 2.0F;
/*    */       
/* 54 */       if (m != null) {
/* 55 */         tmp.calculateCardDamage(m);
/*    */       }
/*    */       
/* 58 */       tmp.purgeOnUse = true;
/* 59 */       AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
/*    */ 
/*    */       
/* 62 */       this.amount--;
/* 63 */       if (this.amount == 0) {
/* 64 */         addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "DuplicationPower"));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 71 */     if (this.amount == 0) {
/* 72 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "DuplicationPower"));
/*    */     } else {
/* 74 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "DuplicationPower", 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DuplicationPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */