/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class DEPRECATEDDisciplinePower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DisciplinePower"); public static final String POWER_ID = "DisciplinePower";
/*    */   
/*    */   public DEPRECATEDDisciplinePower(AbstractCreature owner) {
/* 15 */     this.name = powerStrings.NAME;
/* 16 */     this.ID = "DisciplinePower";
/* 17 */     this.owner = owner;
/* 18 */     updateDescription();
/* 19 */     loadRegion("no_stance");
/* 20 */     this.type = PowerType.BUFF;
/* 21 */     this.amount = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 26 */     if (EnergyPanel.totalCount > 0) {
/* 27 */       this.amount = EnergyPanel.totalCount;
/* 28 */       this.fontScale = 8.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 34 */     if (this.amount != -1) {
/* 35 */       addToTop((AbstractGameAction)new DrawCardAction(this.amount));
/* 36 */       this.amount = -1;
/* 37 */       this.fontScale = 8.0F;
/* 38 */       flash();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 44 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDDisciplinePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */