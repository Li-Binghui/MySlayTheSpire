/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ForcefieldPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Nullify Attack";
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Nullify Attack");
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ForcefieldPower(AbstractCreature owner) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "Nullify Attack";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = -1;
/* 19 */     updateDescription();
/* 20 */     loadRegion("forcefield");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 25 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
/* 30 */     if (damage > 0.0F && type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) {
/* 31 */       return 0.0F;
/*    */     }
/* 33 */     return damage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ForcefieldPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */