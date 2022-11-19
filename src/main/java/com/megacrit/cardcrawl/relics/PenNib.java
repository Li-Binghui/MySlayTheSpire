/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PenNibPower;
/*    */ 
/*    */ public class PenNib extends AbstractRelic {
/*    */   public static final String ID = "Pen Nib";
/*    */   
/*    */   public PenNib() {
/* 17 */     super("Pen Nib", "penNib.png", RelicTier.COMMON, LandingSound.CLINK);
/* 18 */     this.counter = 0;
/*    */   }
/*    */   public static final int COUNT = 10;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 28 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 29 */       this.counter++;
/*    */       
/* 31 */       if (this.counter == 10) {
/* 32 */         this.counter = 0;
/* 33 */         flash();
/* 34 */         this.pulse = false;
/* 35 */       } else if (this.counter == 9) {
/* 36 */         beginPulse();
/* 37 */         this.pulse = true;
/* 38 */         AbstractDungeon.player.hand.refreshHandLayout();
/* 39 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 40 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PenNibPower((AbstractCreature)AbstractDungeon.player, 1), 1, true));
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
/*    */   
/*    */   public void atBattleStart() {
/* 53 */     if (this.counter == 9) {
/* 54 */       beginPulse();
/* 55 */       this.pulse = true;
/* 56 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 57 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PenNibPower((AbstractCreature)AbstractDungeon.player, 1), 1, true));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 69 */     return new PenNib();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\PenNib.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */