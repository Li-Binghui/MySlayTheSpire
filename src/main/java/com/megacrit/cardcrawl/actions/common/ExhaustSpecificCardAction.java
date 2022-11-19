/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ExhaustSpecificCardAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractCard targetCard;
/*    */   
/*    */   public ExhaustSpecificCardAction(AbstractCard targetCard, CardGroup group, boolean isFast) {
/* 16 */     this.targetCard = targetCard;
/* 17 */     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.amount);
/* 18 */     this.actionType = ActionType.EXHAUST;
/* 19 */     this.group = group;
/*    */     
/* 21 */     this.startingDuration = Settings.ACTION_DUR_FAST;
/* 22 */     this.duration = this.startingDuration;
/*    */   }
/*    */   private CardGroup group; private float startingDuration;
/*    */   public ExhaustSpecificCardAction(AbstractCard targetCard, CardGroup group) {
/* 26 */     this(targetCard, group, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     if (this.duration == this.startingDuration && 
/* 32 */       this.group.contains(this.targetCard)) {
/* 33 */       this.group.moveToExhaustPile(this.targetCard);
/* 34 */       CardCrawlGame.dungeon.checkForPactAchievement();
/* 35 */       this.targetCard.exhaustOnUseOnce = false;
/* 36 */       this.targetCard.freeToPlayOnce = false;
/*    */     } 
/*    */ 
/*    */     
/* 40 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ExhaustSpecificCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */