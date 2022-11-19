/*    */ package com.megacrit.cardcrawl.unlock;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class AbstractUnlock
/*    */   implements Comparable<AbstractUnlock> {
/*    */   public String title;
/* 11 */   public AbstractPlayer player = null; public String key; public UnlockType type;
/* 12 */   public AbstractCard card = null;
/* 13 */   public AbstractRelic relic = null;
/*    */   
/*    */   public enum UnlockType {
/* 16 */     CARD, RELIC, LOADOUT, CHARACTER, MISC;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ 
/*    */   
/*    */   public int compareTo(AbstractUnlock u) {
/* 24 */     switch (this.type) {
/*    */       case CARD:
/* 26 */         return this.card.cardID.compareTo(u.card.cardID);
/*    */       case RELIC:
/* 28 */         return this.relic.relicId.compareTo(u.relic.relicId);
/*    */     } 
/* 30 */     return this.title.compareTo(u.title);
/*    */   }
/*    */   
/*    */   public void onUnlockScreenOpen() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\unlock\AbstractUnlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */