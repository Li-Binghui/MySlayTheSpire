/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ConfusionPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Confusion";
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Confusion");
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ConfusionPower(AbstractCreature owner) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Confusion";
/* 18 */     this.owner = owner;
/* 19 */     updateDescription();
/* 20 */     loadRegion("confusion");
/* 21 */     this.type = PowerType.DEBUFF;
/* 22 */     this.priority = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 27 */     CardCrawlGame.sound.play("POWER_CONFUSION", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCardDraw(AbstractCard card) {
/* 32 */     if (card.cost >= 0) {
/* 33 */       int newCost = AbstractDungeon.cardRandomRng.random(3);
/* 34 */       if (card.cost != newCost) {
/* 35 */         card.cost = newCost;
/* 36 */         card.costForTurn = card.cost;
/* 37 */         card.isCostModified = true;
/*    */       } 
/* 39 */       card.freeToPlayOnce = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 45 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ConfusionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */