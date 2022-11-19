/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Nunchaku extends AbstractRelic {
/*    */   public static final String ID = "Nunchaku";
/*    */   
/*    */   public Nunchaku() {
/* 16 */     super("Nunchaku", "nunchaku.png", RelicTier.COMMON, LandingSound.FLAT);
/* 17 */     this.counter = 0;
/*    */   }
/*    */   private static final int NUM_CARDS = 10;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     if (AbstractDungeon.player != null) {
/* 23 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 25 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 30 */     return this.DESCRIPTIONS[0] + '\n' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 35 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 36 */       this.counter++;
/*    */       
/* 38 */       if (this.counter % 10 == 0) {
/* 39 */         this.counter = 0;
/* 40 */         flash();
/* 41 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 42 */         addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new Nunchaku();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Nunchaku.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */