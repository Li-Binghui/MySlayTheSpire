/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class ReactivePower extends AbstractPower {
/*    */   public static final String POWER_ID = "Compulsive";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Compulsive");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ReactivePower(AbstractCreature owner) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Compulsive";
/* 20 */     this.owner = owner;
/* 21 */     updateDescription();
/* 22 */     loadRegion("reactive");
/* 23 */     this.priority = 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 28 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 33 */     if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0 && damageAmount < this.owner.currentHealth) {
/*    */       
/* 35 */       flash();
/* 36 */       addToBot((AbstractGameAction)new RollMoveAction((AbstractMonster)this.owner));
/*    */     } 
/* 38 */     return damageAmount;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ReactivePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */