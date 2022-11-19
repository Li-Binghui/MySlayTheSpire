/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class HelloPower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Hello"); public static final String POWER_ID = "Hello";
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public HelloPower(AbstractCreature owner, int cardAmt) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Hello";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = cardAmt;
/* 21 */     updateDescription();
/* 22 */     loadRegion("hello");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 27 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 28 */       flash();
/* 29 */       for (int i = 0; i < this.amount; i++) {
/* 30 */         addToBot((AbstractGameAction)new MakeTempCardInHandAction(
/*    */               
/* 32 */               AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON, AbstractDungeon.cardRandomRng)
/* 33 */               .makeCopy(), 1, false));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 42 */     this.fontScale = 8.0F;
/* 43 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 48 */     if (this.amount > 1) {
/* 49 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 51 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\HelloPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */