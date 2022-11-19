/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class LikeWaterPower extends AbstractPower {
/*    */   public static final String POWER_ID = "LikeWaterPower";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("LikeWaterPower");
/*    */   
/*    */   public LikeWaterPower(AbstractCreature owner, int amt) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "LikeWaterPower";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("like_water");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 25 */     this.fontScale = 8.0F;
/* 26 */     this.amount += stackAmount;
/* 27 */     if (this.amount > 999) {
/* 28 */       this.amount = 999;
/*    */     }
/* 30 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 35 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
/* 40 */     if (isPlayer) {
/* 41 */       AbstractPlayer p = (AbstractPlayer)this.owner;
/* 42 */       if (p.stance.ID.equals("Calm")) {
/* 43 */         flash();
/* 44 */         addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\LikeWaterPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */