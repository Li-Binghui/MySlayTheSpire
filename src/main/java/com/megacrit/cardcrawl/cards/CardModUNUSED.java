/*    */ package com.megacrit.cardcrawl.cards;
/*    */ 
/*    */ public class CardModUNUSED {
/*    */   public String key;
/*    */   private EffectType type;
/*    */   public DurationType dur;
/*    */   private int amount;
/*    */   private boolean applied = false;
/*    */   
/*    */   public CardModUNUSED(EffectType type, DurationType dur, int amount, String key) {
/* 11 */     this.type = type;
/* 12 */     this.dur = dur;
/* 13 */     this.amount = amount;
/* 14 */     this.key = key;
/*    */   }
/*    */   
/*    */   public enum EffectType {
/* 18 */     DAMAGE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum DurationType
/*    */   {
/* 25 */     ONE_TURN, COMBAT, ATTACKS_PLAYED, CARDS_PLAYED;
/*    */   }
/*    */   
/*    */   public void apply(AbstractCard card) {
/* 29 */     if (!this.applied) {
/* 30 */       this.applied = true;
/* 31 */       switch (this.type) {
/*    */         case DAMAGE:
/* 33 */           card.damage += this.amount;
/*    */           break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int applyDamageMod(int baseDamage) {
/* 42 */     return baseDamage + this.amount;
/*    */   }
/*    */   
/*    */   public void unapply(AbstractCard card) {
/* 46 */     switch (this.type) {
/*    */       case DAMAGE:
/* 48 */         card.damage -= this.amount;
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\CardModUNUSED.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */