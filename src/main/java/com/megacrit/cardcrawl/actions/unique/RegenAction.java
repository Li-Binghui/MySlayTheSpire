/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class RegenAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public RegenAction(AbstractCreature target, int amount) {
/* 15 */     this.target = target;
/* 16 */     this.amount = amount;
/* 17 */     this.actionType = ActionType.DAMAGE;
/* 18 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if ((AbstractDungeon.getCurrRoom()).phase != AbstractRoom.RoomPhase.COMBAT) {
/* 24 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 28 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 29 */       if (this.target.currentHealth > 0) {
/* 30 */         this.target.tint.color = Color.CHARTREUSE.cpy();
/* 31 */         this.target.tint.changeColor(Color.WHITE.cpy());
/* 32 */         this.target.heal(this.amount, true);
/*    */       } 
/*    */       
/* 35 */       if (this.target.isPlayer) {
/* 36 */         AbstractPower p = this.target.getPower("Regeneration");
/* 37 */         if (p != null) {
/* 38 */           p.amount--;
/* 39 */           if (p.amount == 0) {
/* 40 */             this.target.powers.remove(p);
/*    */           } else {
/* 42 */             p.updateDescription();
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 48 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RegenAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */