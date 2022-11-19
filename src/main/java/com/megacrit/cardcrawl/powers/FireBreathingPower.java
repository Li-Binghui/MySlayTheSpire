/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class FireBreathingPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Fire Breathing";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Fire Breathing");
/*    */   
/*    */   public FireBreathingPower(AbstractCreature owner, int newAmount) {
/* 17 */     this.name = powerStrings.NAME;
/* 18 */     this.ID = "Fire Breathing";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = newAmount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("firebreathing");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCardDraw(AbstractCard card) {
/* 32 */     if (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
/* 33 */       flash();
/* 34 */       addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */             
/* 37 */             DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\FireBreathingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */