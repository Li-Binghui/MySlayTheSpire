/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.PowerExpireTextEffect;
/*    */ 
/*    */ public class RemoveSpecificPowerAction extends AbstractGameAction {
/*    */   private String powerToRemove;
/*    */   private AbstractPower powerInstance;
/*    */   private static final float DURATION = 0.1F;
/*    */   
/*    */   public RemoveSpecificPowerAction(AbstractCreature target, AbstractCreature source, String powerToRemove) {
/* 16 */     setValues(target, source, this.amount);
/* 17 */     this.actionType = ActionType.DEBUFF;
/* 18 */     this.duration = 0.1F;
/* 19 */     this.powerToRemove = powerToRemove;
/*    */   }
/*    */   
/*    */   public RemoveSpecificPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerInstance) {
/* 23 */     setValues(target, source, this.amount);
/* 24 */     this.actionType = ActionType.DEBUFF;
/* 25 */     this.duration = 0.1F;
/* 26 */     this.powerInstance = powerInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     if (this.duration == 0.1F) {
/* 32 */       if (this.target.isDeadOrEscaped()) {
/* 33 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 37 */       AbstractPower removeMe = null;
/* 38 */       if (this.powerToRemove != null) {
/* 39 */         removeMe = this.target.getPower(this.powerToRemove);
/* 40 */       } else if (this.powerInstance != null && 
/* 41 */         this.target.powers.contains(this.powerInstance)) {
/* 42 */         removeMe = this.powerInstance;
/*    */       } 
/*    */ 
/*    */       
/* 46 */       if (removeMe != null) {
/* 47 */         AbstractDungeon.effectList.add(new PowerExpireTextEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, removeMe.name, removeMe.region128));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 53 */         removeMe.onRemove();
/* 54 */         this.target.powers.remove(removeMe);
/* 55 */         AbstractDungeon.onModifyPower();
/*    */         
/* 57 */         for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 58 */           o.updateDescription();
/*    */         }
/*    */       } else {
/* 61 */         this.duration = 0.0F;
/*    */       } 
/*    */     } 
/*    */     
/* 65 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\RemoveSpecificPowerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */