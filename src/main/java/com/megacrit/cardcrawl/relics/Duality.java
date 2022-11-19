/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
/*    */ 
/*    */ public class Duality extends AbstractRelic {
/*    */   public Duality() {
/* 17 */     super("Yang", "duality.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "Yang";
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 27 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 28 */       flash();
/* 29 */       AbstractPlayer p = AbstractDungeon.player;
/* 30 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)p, this));
/* 31 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new DexterityPower((AbstractCreature)p, 1), 1));
/* 32 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new LoseDexterityPower((AbstractCreature)p, 1), 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new Duality();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Duality.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */