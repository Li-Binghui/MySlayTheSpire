/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class MalaiseAction extends AbstractGameAction {
/*    */   private boolean freeToPlayOnce = false;
/*    */   private boolean upgraded = false;
/* 17 */   private int energyOnUse = -1;
/*    */   
/*    */   private AbstractPlayer p;
/*    */   
/*    */   private AbstractMonster m;
/*    */ 
/*    */   
/*    */   public MalaiseAction(AbstractPlayer p, AbstractMonster m, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
/* 25 */     this.p = p;
/* 26 */     this.m = m;
/* 27 */     this.freeToPlayOnce = freeToPlayOnce;
/* 28 */     this.upgraded = upgraded;
/* 29 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 30 */     this.actionType = ActionType.SPECIAL;
/* 31 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 36 */     int effect = EnergyPanel.totalCount;
/* 37 */     if (this.energyOnUse != -1) {
/* 38 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 41 */     if (this.p.hasRelic("Chemical X")) {
/* 42 */       effect += 2;
/* 43 */       this.p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 46 */     if (this.upgraded) {
/* 47 */       effect++;
/*    */     }
/*    */     
/* 50 */     if (effect > 0) {
/* 51 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.p, (AbstractPower)new StrengthPower((AbstractCreature)this.m, -effect), -effect));
/* 52 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.p, (AbstractPower)new WeakPower((AbstractCreature)this.m, effect, false), effect));
/*    */       
/* 54 */       if (!this.freeToPlayOnce) {
/* 55 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 58 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\MalaiseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */