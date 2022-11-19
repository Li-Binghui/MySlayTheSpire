/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ public class UndoAction
/*    */   extends AbstractGameAction
/*    */ {
/* 16 */   private AbstractPlayer p = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     if (this.duration == Settings.ACTION_DUR_MED) {
/*    */ 
/*    */       
/* 25 */       if (GameActionManager.turn == 1) {
/* 26 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 30 */       if (this.p.currentHealth < GameActionManager.playerHpLastTurn) {
/*    */         
/* 32 */         this.p.heal(GameActionManager.playerHpLastTurn - this.p.currentHealth, true);
/*    */       }
/* 34 */       else if (this.p.currentHealth > GameActionManager.playerHpLastTurn) {
/*    */         
/* 36 */         addToTop((AbstractGameAction)new DamageAction((AbstractCreature)this.p, new DamageInfo((AbstractCreature)this.p, this.p.currentHealth - GameActionManager.playerHpLastTurn, DamageInfo.DamageType.HP_LOSS), AttackEffect.FIRE));
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\UndoAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */