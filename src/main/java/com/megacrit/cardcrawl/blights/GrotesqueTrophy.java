/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.Pride;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class GrotesqueTrophy extends AbstractBlight {
/*    */   public static final String ID = "GrotesqueTrophy";
/* 13 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("GrotesqueTrophy");
/* 14 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public GrotesqueTrophy() {
/* 17 */     super("GrotesqueTrophy", NAME, DESC[0] + '\003' + DESC[1], "trophy.png", false);
/* 18 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 23 */     CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*    */     
/* 25 */     for (int i = 0; i < 3; i++) {
/* 26 */       Pride pride = new Pride();
/* 27 */       UnlockTracker.markCardAsSeen(((AbstractCard)pride).cardID);
/* 28 */       group.addToBottom(pride.makeCopy());
/*    */     } 
/*    */     
/* 31 */     AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, DESC[2]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 36 */     this.counter++;
/* 37 */     flash();
/*    */     
/* 39 */     CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*    */     
/* 41 */     for (int i = 0; i < 3; i++) {
/* 42 */       Pride pride = new Pride();
/* 43 */       UnlockTracker.markCardAsSeen(((AbstractCard)pride).cardID);
/* 44 */       group.addToBottom(pride.makeCopy());
/*    */     } 
/*    */     
/* 47 */     AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, DESC[2]);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\GrotesqueTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */