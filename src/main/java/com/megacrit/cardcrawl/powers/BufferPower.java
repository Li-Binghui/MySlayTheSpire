/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class BufferPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Buffer"); public static final String POWER_ID = "Buffer";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public BufferPower(AbstractCreature owner, int bufferAmt) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Buffer";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = bufferAmt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("buffer");
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 26 */     this.fontScale = 8.0F;
/* 27 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 32 */     if (this.amount <= 1) {
/* 33 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 35 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
/* 41 */     if (damageAmount > 0) {
/* 42 */       addToTop((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this.ID, 1));
/*    */     }
/* 44 */     return 0;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BufferPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */