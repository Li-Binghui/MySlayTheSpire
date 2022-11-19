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
import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ 
/*    */ public class ArtOfWar extends AbstractRelic {
/*    */   public static final String ID = "Art of War";
/*    */   private boolean gainEnergyNext = false, firstTurn = false;
/*    */   
/*    */   public ArtOfWar() {
/* 18 */     super("Art of War", "artOfWar.png", RelicTier.COMMON, LandingSound.FLAT);
/* 19 */     this.pulse = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     if (AbstractDungeon.player != null) {
/* 25 */       return setDescription(AbstractDungeon.player.chosenClass);
/*    */     }
/* 27 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 32 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 37 */     this.description = setDescription(c);
/* 38 */     this.tips.clear();
/* 39 */     this.tips.add(new PowerTip(this.name, this.description));
/* 40 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 45 */     flash();
/* 46 */     this.firstTurn = true;
/* 47 */     this.gainEnergyNext = true;
/* 48 */     if (!this.pulse) {
/* 49 */       beginPulse();
/* 50 */       this.pulse = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 56 */     beginPulse();
/* 57 */     this.pulse = true;
/* 58 */     if (this.gainEnergyNext && !this.firstTurn) {
/* 59 */       flash();
/* 60 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 61 */       addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */     } 
/* 63 */     this.firstTurn = false;
/* 64 */     this.gainEnergyNext = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 69 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 70 */       this.gainEnergyNext = false;
/* 71 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 77 */     this.pulse = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 82 */     return new ArtOfWar();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\ArtOfWar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */