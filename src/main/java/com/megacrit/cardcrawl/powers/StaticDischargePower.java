/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.ChannelAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.orbs.Lightning;
/*    */ 
/*    */ public class StaticDischargePower extends AbstractPower {
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("StaticDischarge"); public static final String POWER_ID = "StaticDischarge";
/*    */   
/*    */   public StaticDischargePower(AbstractCreature owner, int lightningAmount) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "StaticDischarge";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = lightningAmount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("static_discharge");
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 26 */     if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
/*    */       
/* 28 */       flash();
/* 29 */       for (int i = 0; i < this.amount; i++) {
/* 30 */         addToTop((AbstractGameAction)new ChannelAction((AbstractOrb)new Lightning()));
/*    */       }
/*    */     } 
/* 33 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\StaticDischargePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */