/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Expunger;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class ConjureBladeAction extends AbstractGameAction {
/*    */   public int[] multiDamage;
/*    */   private boolean freeToPlayOnce;
/*    */   private AbstractPlayer p;
/*    */   private int energyOnUse;
/*    */   
/*    */   public ConjureBladeAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse) {
/* 18 */     this.p = p;
/* 19 */     this.freeToPlayOnce = freeToPlayOnce;
/* 20 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 21 */     this.actionType = ActionType.SPECIAL;
/* 22 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     int effect = EnergyPanel.totalCount;
/* 28 */     if (this.energyOnUse != -1) {
/* 29 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 32 */     if (this.p.hasRelic("Chemical X")) {
/* 33 */       effect += 2;
/* 34 */       this.p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 37 */     Expunger c = new Expunger();
/* 38 */     c.setX(effect);
/*    */     
/* 40 */     addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)c, 1, true, true));
/*    */     
/* 42 */     if (!this.freeToPlayOnce) {
/* 43 */       this.p.energy.use(EnergyPanel.totalCount);
/*    */     }
/* 45 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\ConjureBladeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */