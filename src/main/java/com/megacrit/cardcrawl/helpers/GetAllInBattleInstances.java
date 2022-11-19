/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashSet;
import java.util.UUID;
/*    */ 
/*    */ public class GetAllInBattleInstances
/*    */ {
/*    */   public static HashSet<AbstractCard> get(UUID uuid) {
/* 11 */     HashSet<AbstractCard> cards = new HashSet<>();
/* 12 */     if (AbstractDungeon.player.cardInUse.uuid.equals(uuid))
/* 13 */       cards.add(AbstractDungeon.player.cardInUse); 
/* 14 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 15 */       if (!c.uuid.equals(uuid))
/*    */         continue; 
/* 17 */       cards.add(c);
/*    */     } 
/* 19 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 20 */       if (!c.uuid.equals(uuid))
/*    */         continue; 
/* 22 */       cards.add(c);
/*    */     } 
/* 24 */     for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
/* 25 */       if (!c.uuid.equals(uuid))
/*    */         continue; 
/* 27 */       cards.add(c);
/*    */     } 
/* 29 */     for (AbstractCard c : AbstractDungeon.player.limbo.group) {
/* 30 */       if (!c.uuid.equals(uuid))
/*    */         continue; 
/* 32 */       cards.add(c);
/*    */     } 
/* 34 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 35 */       if (!c.uuid.equals(uuid))
/*    */         continue; 
/* 37 */       cards.add(c);
/*    */     } 
/* 39 */     return cards;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\GetAllInBattleInstances.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */