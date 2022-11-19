/*    */ package com.megacrit.cardcrawl.actions.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.MetallicizePower;
/*    */ 
/*    */ public class DEPRECATEDArmorSelectedAmountAction extends AbstractGameAction {
/*    */   public DEPRECATEDArmorSelectedAmountAction(AbstractCreature target, AbstractCreature source, int multiplier) {
/* 12 */     setValues(target, source, multiplier);
/* 13 */     this.actionType = ActionType.POWER;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (this.duration == 0.5F) {
/* 19 */       this.amount = AbstractDungeon.handCardSelectScreen.numSelected * this.amount;
/* 20 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new MetallicizePower(this.target, this.amount), this.amount));
/*    */     } 
/*    */ 
/*    */     
/* 24 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\deprecated\DEPRECATEDArmorSelectedAmountAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */