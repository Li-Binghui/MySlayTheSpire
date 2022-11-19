/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class BirdFacedUrn extends AbstractRelic {
/*    */   public static final String ID = "Bird Faced Urn";
/*    */   private static final int HEAL_AMT = 2;
/*    */   
/*    */   public BirdFacedUrn() {
/* 16 */     super("Bird Faced Urn", "bird_urn.png", RelicTier.RARE, LandingSound.SOLID);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 26 */     if (card.type == AbstractCard.CardType.POWER) {
/* 27 */       flash();
/* 28 */       addToTop((AbstractGameAction)new HealAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 2));
/* 29 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return new BirdFacedUrn();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BirdFacedUrn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */