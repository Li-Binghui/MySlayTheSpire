/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;
/*    */ 
/*    */ public class IncreaseMiscAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private int miscIncrease;
/*    */   private UUID uuid;
/*    */   
/*    */   public IncreaseMiscAction(UUID targetUUID, int miscValue, int miscIncrease) {
/* 16 */     this.miscIncrease = miscIncrease;
/*    */     
/* 18 */     this.uuid = targetUUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 24 */       if (!c.uuid.equals(this.uuid))
/*    */         continue; 
/* 26 */       c.misc += this.miscIncrease;
/* 27 */       c.applyPowers();
/* 28 */       c.baseBlock = c.misc;
/* 29 */       c.isBlockModified = false;
/*    */     } 
/* 31 */     for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
/* 32 */       c.misc += this.miscIncrease;
/* 33 */       c.applyPowers();
/* 34 */       c.baseBlock = c.misc;
/*    */     } 
/* 36 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\IncreaseMiscAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */