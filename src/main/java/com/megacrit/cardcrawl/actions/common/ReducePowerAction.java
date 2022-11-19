/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReducePowerAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private String powerID;
/*    */   private AbstractPower powerInstance;
/*    */   
/*    */   public ReducePowerAction(AbstractCreature target, AbstractCreature source, String power, int amount) {
/* 23 */     setValues(target, source, amount);
/* 24 */     if (Settings.FAST_MODE) {
/* 25 */       this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
/*    */     } else {
/* 27 */       this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/*    */     } 
/* 29 */     this.powerID = power;
/* 30 */     this.actionType = ActionType.REDUCE_POWER;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ReducePowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerInstance, int amount) {
/* 38 */     setValues(target, source, amount);
/* 39 */     if (Settings.FAST_MODE) {
/* 40 */       this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
/*    */     } else {
/* 42 */       this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/*    */     } 
/* 44 */     this.powerInstance = powerInstance;
/* 45 */     this.actionType = ActionType.REDUCE_POWER;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 50 */     if (this.duration == this.startDuration) {
/* 51 */       AbstractPower reduceMe = null;
/* 52 */       if (this.powerID != null) {
/* 53 */         reduceMe = this.target.getPower(this.powerID);
/* 54 */       } else if (this.powerInstance != null) {
/* 55 */         reduceMe = this.powerInstance;
/*    */       } 
/*    */       
/* 58 */       if (reduceMe != null) {
/* 59 */         if (this.amount < reduceMe.amount) {
/* 60 */           reduceMe.reducePower(this.amount);
/* 61 */           reduceMe.updateDescription();
/* 62 */           AbstractDungeon.onModifyPower();
/*    */         } else {
/* 64 */           addToTop(new RemoveSpecificPowerAction(this.target, this.source, reduceMe));
/*    */         } 
/*    */       }
/*    */     } 
/*    */     
/* 69 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ReducePowerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */