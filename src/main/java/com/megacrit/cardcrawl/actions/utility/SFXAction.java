/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ 
/*    */ public class SFXAction extends AbstractGameAction {
/*    */   private String key;
/*  8 */   private float pitchVar = 0.0F;
/*    */   private boolean adjust = false;
/*    */   
/*    */   public SFXAction(String key) {
/* 12 */     this(key, 0.0F, false);
/*    */   }
/*    */   
/*    */   public SFXAction(String key, float pitchVar) {
/* 16 */     this(key, pitchVar, false);
/*    */   }
/*    */   
/*    */   public SFXAction(String key, float pitchVar, boolean pitchAdjust) {
/* 20 */     this.key = key;
/* 21 */     this.pitchVar = pitchVar;
/* 22 */     this.adjust = pitchAdjust;
/* 23 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (!this.adjust) {
/* 29 */       CardCrawlGame.sound.play(this.key, this.pitchVar);
/*    */     } else {
/* 31 */       CardCrawlGame.sound.playA(this.key, this.pitchVar);
/*    */     } 
/* 33 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\SFXAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */