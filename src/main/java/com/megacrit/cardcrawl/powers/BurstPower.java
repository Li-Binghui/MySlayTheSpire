/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
/*    */ public class BurstPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Burst";
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Burst");
/* 18 */   public static final String NAME = powerStrings.NAME;
/* 19 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public BurstPower(AbstractCreature owner, int amount) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "Burst";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = amount;
/* 26 */     updateDescription();
/* 27 */     loadRegion("burst");
/*    */   }
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
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 41 */     if (!card.purgeOnUse && card.type == AbstractCard.CardType.SKILL && this.amount > 0) {
/* 42 */       flash();
/* 43 */       AbstractMonster m = null;
/*    */       
/* 45 */       if (action.target != null) {
/* 46 */         m = (AbstractMonster)action.target;
/*    */       }
/*    */       
/* 49 */       AbstractCard tmp = card.makeSameInstanceOf();
/* 50 */       AbstractDungeon.player.limbo.addToBottom(tmp);
/* 51 */       tmp.current_x = card.current_x;
/* 52 */       tmp.current_y = card.current_y;
/* 53 */       tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
/* 54 */       tmp.target_y = Settings.HEIGHT / 2.0F;
/* 55 */       if (m != null) {
/* 56 */         tmp.calculateCardDamage(m);
/*    */       }
/*    */       
/* 59 */       tmp.purgeOnUse = true;
/* 60 */       AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
/*    */ 
/*    */ 
/*    */       
/* 64 */       this.amount--;
/* 65 */       if (this.amount == 0) {
/* 66 */         addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Burst"));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 73 */     if (isPlayer)
/* 74 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Burst")); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BurstPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */