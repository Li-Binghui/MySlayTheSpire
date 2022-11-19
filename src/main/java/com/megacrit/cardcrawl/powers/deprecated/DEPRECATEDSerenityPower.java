/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DEPRECATEDSerenityPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Serenity";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Serenity");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DEPRECATEDSerenityPower(AbstractCreature owner, int amt) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Serenity";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amt;
/* 23 */     updateDescription();
/* 24 */     loadRegion("platedarmor");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 34 */     this.description = DESCRIPTIONS[0] + this.amount + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 39 */     if (damageAmount > 0 && ((AbstractPlayer)this.owner).stance.ID.equals("Calm")) {
/* 40 */       flash();
/* 41 */       damageAmount -= this.amount;
/* 42 */       if (damageAmount < this.amount) {
/* 43 */         damageAmount = 0;
/*    */       }
/*    */     } 
/* 46 */     return damageAmount;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDSerenityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */