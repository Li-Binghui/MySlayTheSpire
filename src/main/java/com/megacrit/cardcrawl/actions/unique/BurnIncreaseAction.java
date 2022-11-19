/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.Burn;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
/*    */ 
/*    */ public class BurnIncreaseAction extends AbstractGameAction {
/*    */   private static final float DURATION = 3.0F;
/*    */   private boolean gotBurned = false;
/*    */   
/*    */   public BurnIncreaseAction() {
/* 14 */     this.duration = 3.0F;
/* 15 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == 3.0F) {
/* 21 */       for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 22 */         if (c instanceof Burn) {
/* 23 */           c.upgrade();
/*    */         }
/*    */       } 
/*    */       
/* 27 */       for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 28 */         if (c instanceof Burn) {
/* 29 */           c.upgrade();
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 34 */     if (this.duration < 1.5F && !this.gotBurned) {
/* 35 */       this.gotBurned = true;
/* 36 */       Burn b = new Burn();
/* 37 */       b.upgrade();
/* 38 */       AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect((AbstractCard)b));
/* 39 */       Burn c = new Burn();
/* 40 */       c.upgrade();
/* 41 */       AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect((AbstractCard)c));
/* 42 */       Burn d = new Burn();
/* 43 */       d.upgrade();
/* 44 */       AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect((AbstractCard)d));
/*    */     } 
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
/* 56 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\BurnIncreaseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */