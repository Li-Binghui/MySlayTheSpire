/*    */ package com.megacrit.cardcrawl.trials;
/*    */ 
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OneHpTrial
/*    */   extends AbstractTrial
/*    */ {
/*    */   public AbstractPlayer setupPlayer(AbstractPlayer player) {
/* 15 */     player.currentHealth = 1;
/* 16 */     player.maxHealth = 1;
/* 17 */     return player;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\trials\OneHpTrial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */