/*    */ package com.megacrit.cardcrawl.actions;
/*    */ 
/*    */ 
/*    */ public class ActionLogEntry
/*    */ {
/*    */   public AbstractGameAction.ActionType type;
/*    */   
/*    */   public ActionLogEntry(AbstractGameAction.ActionType type) {
/*  9 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 15 */     return this.type.toString();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\ActionLogEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */