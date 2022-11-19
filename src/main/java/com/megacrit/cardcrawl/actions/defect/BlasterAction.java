/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlasterAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 18 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 19 */       int counter = 0;
/* 20 */       for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 21 */         if (!(o instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot)) {
/* 22 */           counter++;
/*    */         }
/*    */       } 
/*    */       
/* 26 */       if (counter != 0) {
/* 27 */         addToBot((AbstractGameAction)new GainEnergyAction(counter));
/*    */       }
/*    */     } 
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\BlasterAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */