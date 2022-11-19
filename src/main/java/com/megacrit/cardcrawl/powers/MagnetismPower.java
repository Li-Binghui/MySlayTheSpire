/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class MagnetismPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Magnetism"); public static final String POWER_ID = "Magnetism";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String SINGULAR_DESCRIPTION = powerStrings.DESCRIPTIONS[0];
/* 14 */   public static final String PLURAL_DESCRIPTION = powerStrings.DESCRIPTIONS[1];
/*    */   
/*    */   public MagnetismPower(AbstractCreature owner, int cardAmount) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Magnetism";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = cardAmount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("magnet");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 27 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 28 */       flash();
/* 29 */       for (int i = 0; i < this.amount; i++) {
/* 30 */         addToBot((AbstractGameAction)new MakeTempCardInHandAction(
/*    */               
/* 32 */               AbstractDungeon.returnTrulyRandomColorlessCardInCombat().makeCopy(), 1, false));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 41 */     this.fontScale = 8.0F;
/* 42 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 47 */     if (this.amount > 1) {
/* 48 */       this.description = String.format(PLURAL_DESCRIPTION, new Object[] { Integer.valueOf(this.amount) });
/*    */     } else {
/* 50 */       this.description = String.format(SINGULAR_DESCRIPTION, new Object[] { Integer.valueOf(this.amount) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\MagnetismPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */