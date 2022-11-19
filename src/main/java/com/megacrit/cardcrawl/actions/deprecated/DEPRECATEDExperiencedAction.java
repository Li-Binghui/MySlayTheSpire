/*    */ package com.megacrit.cardcrawl.actions.deprecated;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DEPRECATEDExperiencedAction extends AbstractGameAction {
/*    */   private int blockPerCard;
/*    */   
/*    */   public DEPRECATEDExperiencedAction(int blockPerCard, AbstractCard card) {
/* 13 */     this.blockPerCard = blockPerCard;
/* 14 */     this.card = card;
/*    */   }
/*    */   private AbstractCard card;
/*    */   
/*    */   public void update() {
/* 19 */     int upgradeCount = 0;
/* 20 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 21 */       if (c.upgraded && c != this.card) {
/* 22 */         upgradeCount++;
/*    */       }
/*    */     } 
/* 25 */     addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, upgradeCount * this.blockPerCard));
/*    */     
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\deprecated\DEPRECATEDExperiencedAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */