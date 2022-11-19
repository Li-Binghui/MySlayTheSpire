/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DEPRECATEDMasterRealityPower extends AbstractPower {
/*    */   public static final String POWER_ID = "MasterRealityPower";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MasterRealityPower");
/*    */   
/*    */   public DEPRECATEDMasterRealityPower(AbstractCreature owner, int amt) {
/* 17 */     this.name = powerStrings.NAME;
/* 18 */     this.ID = "MasterRealityPower";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = amt;
/* 21 */     updateDescription();
/* 22 */     loadRegion("master_smite");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterCardPlayed(AbstractCard card) {
/* 27 */     if (card.retain || card.selfRetain) {
/* 28 */       flash();
/* 29 */       addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(null, this.amount), AbstractGameAction.AttackEffect.FIRE));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 43 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDMasterRealityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */