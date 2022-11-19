/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DEPRECATEDFlickedPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "FlickPower";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FlickPower");
/*    */   private static final int FLICK_DMG = 50;
/*    */   
/*    */   public DEPRECATEDFlickedPower(AbstractCreature owner, int amt) {
/* 19 */     this.name = powerStrings.NAME;
/* 20 */     this.ID = "FlickPower";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amt;
/* 23 */     updateDescription();
/* 24 */     loadRegion("talk_to_hand");
/* 25 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 29 */     this.amount += stackAmount;
/* 30 */     if (this.amount >= 3) {
/* 31 */       addToBot((AbstractGameAction)new DamageAction(this.owner, new DamageInfo(null, 50, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 37 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, null, "FlickPower"));
/*    */     } else {
/* 39 */       this.fontScale = 8.0F;
/* 40 */       updateDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 48 */     if (this.amount == 1) {
/* 49 */       this.description = powerStrings.DESCRIPTIONS[0] + powerStrings.DESCRIPTIONS[1] + '2' + powerStrings.DESCRIPTIONS[3];
/*    */     }
/*    */     else {
/*    */       
/* 53 */       this.description = powerStrings.DESCRIPTIONS[0] + powerStrings.DESCRIPTIONS[2] + '2' + powerStrings.DESCRIPTIONS[3];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDFlickedPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */