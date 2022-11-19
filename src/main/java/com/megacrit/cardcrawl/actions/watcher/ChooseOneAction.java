/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
/*    */ 
/*    */ public class ChooseOneAction
/*    */   extends AbstractGameAction {
/*    */   private ArrayList<AbstractCard> choices;
/*    */   
/*    */   public ChooseOneAction(ArrayList<AbstractCard> choices) {
/* 14 */     this.duration = Settings.ACTION_DUR_FAST;
/* 15 */     this.choices = choices;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 21 */       AbstractDungeon.cardRewardScreen.chooseOneOpen(this.choices);
/* 22 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 26 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\ChooseOneAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */