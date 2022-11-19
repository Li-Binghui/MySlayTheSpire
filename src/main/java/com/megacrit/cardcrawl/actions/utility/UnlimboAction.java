/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*    */ 
/*    */ public class UnlimboAction extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   private boolean exhaust;
/*    */   
/*    */   public UnlimboAction(AbstractCard card, boolean exhaust) {
/* 14 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 15 */     this.card = card;
/* 16 */     this.exhaust = exhaust;
/*    */   }
/*    */   
/*    */   public UnlimboAction(AbstractCard card) {
/* 20 */     this(card, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.duration == Settings.ACTION_DUR_XFAST) {
/* 26 */       if (!this.exhaust);
/*    */ 
/*    */       
/* 29 */       AbstractDungeon.player.limbo.removeCard(this.card);
/* 30 */       if (this.exhaust) {
/* 31 */         AbstractDungeon.effectList.add(new ExhaustCardEffect(this.card));
/*    */       }
/* 33 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\UnlimboAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */