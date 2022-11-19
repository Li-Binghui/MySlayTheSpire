/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
/*    */ 
/*    */ public class ForeignInfluenceAction
/*    */   extends AbstractGameAction {
/*    */   private boolean retrieveCard = false;
/*    */   private boolean upgraded;
/*    */   
/*    */   public ForeignInfluenceAction(boolean upgraded) {
/* 19 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 20 */     this.duration = Settings.ACTION_DUR_FAST;
/* 21 */     this.upgraded = upgraded;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 27 */       AbstractDungeon.cardRewardScreen.customCombatOpen(generateCardChoices(), CardRewardScreen.TEXT[1], true);
/* 28 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 32 */     if (!this.retrieveCard) {
/* 33 */       if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
/* 34 */         AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
/* 35 */         if (this.upgraded) {
/* 36 */           disCard.setCostForTurn(0);
/*    */         }
/* 38 */         disCard.current_x = -1000.0F * Settings.xScale;
/* 39 */         if (AbstractDungeon.player.hand.size() < 10) {
/* 40 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */         }
/*    */         else {
/*    */           
/* 44 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */         } 
/*    */ 
/*    */         
/* 48 */         AbstractDungeon.cardRewardScreen.discoveryCard = null;
/*    */       } 
/* 50 */       this.retrieveCard = true;
/*    */     } 
/*    */     
/* 53 */     tickDuration();
/*    */   }
/*    */   
/*    */   private ArrayList<AbstractCard> generateCardChoices() {
/* 57 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*    */ 
/*    */     
/* 60 */     while (derp.size() != 3) {
/* 61 */       AbstractCard.CardRarity cardRarity; boolean dupe = false;
/*    */ 
/*    */       
/* 64 */       int roll = AbstractDungeon.cardRandomRng.random(99);
/* 65 */       if (roll < 55) {
/* 66 */         cardRarity = AbstractCard.CardRarity.COMMON;
/* 67 */       } else if (roll < 85) {
/* 68 */         cardRarity = AbstractCard.CardRarity.UNCOMMON;
/*    */       } else {
/* 70 */         cardRarity = AbstractCard.CardRarity.RARE;
/*    */       } 
/*    */       
/* 73 */       AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.ATTACK, cardRarity);
/*    */       
/* 75 */       for (AbstractCard c : derp) {
/* 76 */         if (c.cardID.equals(tmp.cardID)) {
/* 77 */           dupe = true;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 82 */       if (!dupe) {
/* 83 */         derp.add(tmp.makeCopy());
/*    */       }
/*    */     } 
/*    */     
/* 87 */     return derp;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\ForeignInfluenceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */