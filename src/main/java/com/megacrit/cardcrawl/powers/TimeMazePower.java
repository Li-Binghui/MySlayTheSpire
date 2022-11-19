/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*    */ 
/*    */ public class TimeMazePower extends AbstractPower {
/*    */   public static final String POWER_ID = "TimeMazePower";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TimeMazePower");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */   private int maxAmount;
/*    */   
/*    */   public TimeMazePower(AbstractCreature owner, int maxAmount) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "TimeMazePower";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = maxAmount;
/* 23 */     this.maxAmount = maxAmount;
/* 24 */     updateDescription();
/* 25 */     loadRegion("time");
/* 26 */     this.type = PowerType.BUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 31 */     this.description = DESC[0] + this.maxAmount + DESC[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 36 */     flashWithoutSound();
/* 37 */     this.amount--;
/* 38 */     if (this.amount == 0) {
/* 39 */       this.amount = this.maxAmount;
/* 40 */       AbstractDungeon.actionManager.cardQueue.clear();
/* 41 */       for (AbstractCard c : AbstractDungeon.player.limbo.group) {
/* 42 */         AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
/*    */       }
/* 44 */       AbstractDungeon.player.limbo.group.clear();
/* 45 */       AbstractDungeon.player.releaseCard();
/* 46 */       AbstractDungeon.overlayMenu.endTurnButton.disable(true);
/*    */     } 
/* 48 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 53 */     this.amount = 15;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\TimeMazePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */