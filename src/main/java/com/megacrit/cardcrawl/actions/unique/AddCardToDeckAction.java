/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class AddCardToDeckAction extends AbstractGameAction {
/*    */   AbstractCard cardToObtain;
/*    */   
/*    */   public AddCardToDeckAction(AbstractCard card) {
/* 13 */     this.cardToObtain = card;
/* 14 */     this.duration = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(this.cardToObtain, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */     
/* 21 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\AddCardToDeckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */