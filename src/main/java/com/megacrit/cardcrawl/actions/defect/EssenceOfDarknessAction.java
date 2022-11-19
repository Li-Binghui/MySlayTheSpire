/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.orbs.Dark;
/*    */ 
/*    */ public class EssenceOfDarknessAction extends AbstractGameAction {
/*    */   public EssenceOfDarknessAction(int potency) {
/* 11 */     this.duration = Settings.ACTION_DUR_FAST;
/* 12 */     this.amount = potency;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 18 */       for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
/* 19 */         for (int j = 0; j < this.amount; j++) {
/* 20 */           AbstractDungeon.player.channelOrb((AbstractOrb)new Dark());
/*    */         }
/*    */       } 
/*    */       
/* 24 */       if (Settings.FAST_MODE) {
/* 25 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\EssenceOfDarknessAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */