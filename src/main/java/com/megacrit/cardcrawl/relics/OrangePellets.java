/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class OrangePellets extends AbstractRelic {
/*    */   public static final String ID = "OrangePellets";
/*    */   private static boolean SKILL = false, POWER = false, ATTACK = false;
/*    */   
/*    */   public OrangePellets() {
/* 16 */     super("OrangePellets", "pellets.png", RelicTier.SHOP, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 26 */     SKILL = false;
/* 27 */     POWER = false;
/* 28 */     ATTACK = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 33 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 34 */       ATTACK = true;
/* 35 */     } else if (card.type == AbstractCard.CardType.SKILL) {
/* 36 */       SKILL = true;
/* 37 */     } else if (card.type == AbstractCard.CardType.POWER) {
/* 38 */       POWER = true;
/*    */     } 
/*    */     
/* 41 */     if (ATTACK && SKILL && POWER) {
/* 42 */       flash();
/* 43 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */       
/* 45 */       addToBot((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)AbstractDungeon.player));
/* 46 */       SKILL = false;
/* 47 */       POWER = false;
/* 48 */       ATTACK = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 54 */     return new OrangePellets();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\OrangePellets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */