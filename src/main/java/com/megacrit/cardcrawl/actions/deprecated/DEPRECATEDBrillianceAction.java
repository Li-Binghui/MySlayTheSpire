/*    */ package com.megacrit.cardcrawl.actions.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.TransformCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Miracle;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class DEPRECATEDBrillianceAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 15 */     ArrayList<AbstractCard> g = AbstractDungeon.player.hand.group;
/* 16 */     for (int i = 0; i < g.size(); i++) {
/* 17 */       if (!((AbstractCard)g.get(i)).cardID.equals("Miracle")) {
/* 18 */         addToBot((AbstractGameAction)new TransformCardInHandAction(i, (AbstractCard)new Miracle()));
/*    */       }
/*    */     } 
/* 21 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\deprecated\DEPRECATEDBrillianceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */