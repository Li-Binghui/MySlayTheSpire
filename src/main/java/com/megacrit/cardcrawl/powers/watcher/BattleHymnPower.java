/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class BattleHymnPower extends AbstractPower {
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BattleHymn"); public static final String POWER_ID = "BattleHymn";
/*    */   
/*    */   public BattleHymnPower(AbstractCreature owner, int amt) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "BattleHymn";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("hymn");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 26 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 27 */       flash();
/* 28 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Smite(), this.amount, false));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 34 */     this.fontScale = 8.0F;
/* 35 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 40 */     if (this.amount > 1) {
/* 41 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */     } else {
/* 43 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\BattleHymnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */