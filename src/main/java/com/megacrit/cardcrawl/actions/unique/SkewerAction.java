/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkewerAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private boolean freeToPlayOnce;
/*    */   private int damage;
/*    */   private AbstractPlayer p;
/*    */   private AbstractMonster m;
/*    */   private DamageInfo.DamageType damageTypeForTurn;
/*    */   private int energyOnUse;
/*    */   
/*    */   public SkewerAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
/* 28 */     this.p = p;
/* 29 */     this.m = m;
/* 30 */     this.damage = damage;
/* 31 */     this.freeToPlayOnce = freeToPlayOnce;
/* 32 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 33 */     this.actionType = ActionType.SPECIAL;
/* 34 */     this.damageTypeForTurn = damageTypeForTurn;
/* 35 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 40 */     int effect = EnergyPanel.totalCount;
/* 41 */     if (this.energyOnUse != -1) {
/* 42 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 45 */     if (this.p.hasRelic("Chemical X")) {
/* 46 */       effect += 2;
/* 47 */       this.p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 50 */     if (effect > 0) {
/* 51 */       for (int i = 0; i < effect; i++) {
/* 52 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)this.m, new DamageInfo((AbstractCreature)this.p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_LIGHT));
/*    */       }
/*    */       
/* 55 */       if (!this.freeToPlayOnce) {
/* 56 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 59 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\SkewerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */