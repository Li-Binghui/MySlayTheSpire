/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Shiv;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class NinjaScroll extends AbstractRelic {
/*    */   public static final String ID = "Ninja Scroll";
/*    */   
/*    */   public NinjaScroll() {
/* 14 */     super("Ninja Scroll", "ninjaScroll.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */   private static final int AMOUNT = 3;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 24 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 25 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Shiv(), 3, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new NinjaScroll();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\NinjaScroll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */