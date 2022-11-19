/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
/*    */ 
/*    */ public class ThousandCutsPower extends AbstractPower {
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Thousand Cuts"); public static final String POWER_ID = "Thousand Cuts";
/* 18 */   public static final String NAME = powerStrings.NAME;
/* 19 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ThousandCutsPower(AbstractCreature owner, int amount) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "Thousand Cuts";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = amount;
/* 26 */     updateDescription();
/* 27 */     loadRegion("thousandCuts");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterCardPlayed(AbstractCard card) {
/* 32 */     flash();
/* 33 */     addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
/* 34 */     if (Settings.FAST_MODE) {
/* 35 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new CleaveEffect()));
/*    */     } else {
/* 37 */       addToBot((AbstractGameAction)new VFXAction(this.owner, (AbstractGameEffect)new CleaveEffect(), 0.2F));
/*    */     } 
/* 39 */     addToBot((AbstractGameAction)new DamageAllEnemiesAction(this.owner, 
/*    */ 
/*    */           
/* 42 */           DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 50 */     this.fontScale = 8.0F;
/* 51 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 56 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ThousandCutsPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */