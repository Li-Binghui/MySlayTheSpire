/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class MarkPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "PathToVictoryPower";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PathToVictoryPower");
/*    */   
/*    */   public MarkPower(AbstractCreature owner, int amt) {
/* 17 */     this.name = powerStrings.NAME;
/* 18 */     this.ID = "PathToVictoryPower";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = amt;
/* 21 */     updateDescription();
/* 22 */     loadRegion("pressure_points");
/* 23 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 28 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerMarks(AbstractCard card) {
/* 33 */     if (card.cardID.equals("PathToVictory"))
/* 34 */       addToBot((AbstractGameAction)new LoseHPAction(this.owner, null, this.amount, AbstractGameAction.AttackEffect.FIRE)); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\MarkPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */