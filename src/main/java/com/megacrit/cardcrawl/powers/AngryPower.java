/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class AngryPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Angry";
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Angry");
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public AngryPower(AbstractCreature owner, int attackAmount) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Angry";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = attackAmount;
/* 21 */     updateDescription();
/* 22 */     this.isPostActionPower = true;
/* 23 */     loadRegion("anger");
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 28 */     if (info.owner != null && damageAmount > 0 && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
/*    */       
/* 30 */       addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/* 31 */       flash();
/*    */     } 
/* 33 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\AngryPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */