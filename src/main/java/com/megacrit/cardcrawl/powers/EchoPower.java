/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardQueueItem;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EchoPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Echo Form";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Echo Form");
/* 16 */   public static final String NAME = powerStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/* 18 */   private int cardsDoubledThisTurn = 0;
/*    */   
/*    */   public EchoPower(AbstractCreature owner, int amount) {
/* 21 */     this.name = NAME;
/* 22 */     this.ID = "Echo Form";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = amount;
/* 25 */     updateDescription();
/* 26 */     loadRegion("echo");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 32 */     if (this.amount == 1) {
/* 33 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 35 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 41 */     this.cardsDoubledThisTurn = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 46 */     if (!card.purgeOnUse && this.amount > 0 && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - this.cardsDoubledThisTurn <= this.amount) {
/*    */       
/* 48 */       this.cardsDoubledThisTurn++;
/* 49 */       flash();
/* 50 */       AbstractMonster m = null;
/*    */       
/* 52 */       if (action.target != null) {
/* 53 */         m = (AbstractMonster)action.target;
/*    */       }
/*    */       
/* 56 */       AbstractCard tmp = card.makeSameInstanceOf();
/* 57 */       AbstractDungeon.player.limbo.addToBottom(tmp);
/* 58 */       tmp.current_x = card.current_x;
/* 59 */       tmp.current_y = card.current_y;
/* 60 */       tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
/* 61 */       tmp.target_y = Settings.HEIGHT / 2.0F;
/*    */       
/* 63 */       if (m != null) {
/* 64 */         tmp.calculateCardDamage(m);
/*    */       }
/*    */       
/* 67 */       tmp.purgeOnUse = true;
/* 68 */       AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\EchoPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */