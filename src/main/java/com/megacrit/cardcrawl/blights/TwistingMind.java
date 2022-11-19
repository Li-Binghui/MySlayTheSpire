/*    */ package com.megacrit.cardcrawl.blights;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.cards.status.Burn;
/*    */ import com.megacrit.cardcrawl.cards.status.Dazed;
/*    */ import com.megacrit.cardcrawl.cards.status.Slimed;
/*    */ import com.megacrit.cardcrawl.cards.status.VoidCard;
/*    */ import com.megacrit.cardcrawl.cards.status.Wound;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class TwistingMind extends AbstractBlight {
/* 17 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("TwistingMind"); public static final String ID = "TwistingMind";
/* 18 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public TwistingMind() {
/* 21 */     super("TwistingMind", NAME, DESC[0] + '\001' + DESC[1], "twist.png", false);
/* 22 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 27 */     this.counter++;
/* 28 */     updateDescription();
/* 29 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 34 */     this.description = DESC[0] + this.counter + DESC[1];
/* 35 */     this.tips.clear();
/* 36 */     this.tips.add(new PowerTip(this.name, this.description));
/* 37 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 42 */     flash();
/* 43 */     CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*    */     
/* 45 */     group.addToBottom((AbstractCard)new Slimed());
/* 46 */     group.addToBottom((AbstractCard)new VoidCard());
/* 47 */     group.addToBottom((AbstractCard)new Burn());
/* 48 */     group.addToBottom((AbstractCard)new Dazed());
/* 49 */     group.addToBottom((AbstractCard)new Wound());
/* 50 */     group.shuffle();
/*    */     
/* 52 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDrawPileAction(group
/* 53 */           .getBottomCard(), this.counter, false, true));
/*    */     
/* 55 */     group.clear();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\TwistingMind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */