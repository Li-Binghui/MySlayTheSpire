/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class ThornsPower
/*    */   extends AbstractPower
/*    */ {
/* 15 */   private static final Logger logger = LogManager.getLogger(ThornsPower.class.getName());
/*    */   public static final String POWER_ID = "Thorns";
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Thorns");
/* 18 */   public static final String NAME = powerStrings.NAME;
/* 19 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ThornsPower(AbstractCreature owner, int thornsDamage) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "Thorns";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = thornsDamage;
/* 26 */     updateDescription();
/* 27 */     loadRegion("thorns");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 31 */     if (this.amount == -1) {
/* 32 */       logger.info(this.name + " does not stack");
/*    */       return;
/*    */     } 
/* 35 */     this.fontScale = 8.0F;
/* 36 */     this.amount += stackAmount;
/* 37 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 42 */     if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
/*    */       
/* 44 */       flash();
/* 45 */       addToTop((AbstractGameAction)new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 57 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ThornsPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */