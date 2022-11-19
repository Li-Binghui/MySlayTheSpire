/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class Shuriken
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Shuriken";
/*    */   
/*    */   public Shuriken() {
/* 18 */     super("Shuriken", "shuriken.png", RelicTier.UNCOMMON, LandingSound.CLINK);
/*    */   }
/*    */   private static final int STR_AMT = 1; private static final int NUM_CARDS = 3;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1] + '\001' + this.DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 28 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 33 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 34 */       this.counter++;
/*    */       
/* 36 */       if (this.counter % 3 == 0) {
/* 37 */         this.counter = 0;
/* 38 */         flash();
/* 39 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 40 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, 1), 1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 52 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 57 */     return new Shuriken();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Shuriken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */