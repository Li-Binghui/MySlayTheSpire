/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.FtueTip;

import java.util.Iterator;
/*    */ 
/*    */ public class EmptyDeckShuffleAction
/*    */   extends AbstractGameAction
/*    */ {
/* 17 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Shuffle Tip");
/* 18 */   public static final String[] MSG = tutorialStrings.TEXT;
/* 19 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*    */   private boolean shuffled = false;
/*    */   private boolean vfxDone = false;
/* 22 */   private int count = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EmptyDeckShuffleAction() {
/* 28 */     setValues(null, null, 0);
/* 29 */     this.actionType = ActionType.SHUFFLE;
/*    */     
/* 31 */     if (!((Boolean)TipTracker.tips.get("SHUFFLE_TIP")).booleanValue()) {
/* 32 */       AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.SHUFFLE);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 38 */       TipTracker.neverShowAgain("SHUFFLE_TIP");
/*    */     } 
/* 40 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 41 */       r.onShuffle();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 47 */     if (!this.shuffled) {
/* 48 */       this.shuffled = true;
/* 49 */       AbstractDungeon.player.discardPile.shuffle(AbstractDungeon.shuffleRng);
/*    */     } 
/*    */     
/* 52 */     if (!this.vfxDone) {
/* 53 */       Iterator<AbstractCard> c = AbstractDungeon.player.discardPile.group.iterator(); if (c.hasNext()) {
/* 54 */         this.count++;
/* 55 */         AbstractCard e = c.next();
/* 56 */         c.remove();
/* 57 */         if (this.count < 11) {
/* 58 */           (AbstractDungeon.getCurrRoom()).souls.shuffle(e, false);
/*    */         } else {
/* 60 */           (AbstractDungeon.getCurrRoom()).souls.shuffle(e, true);
/*    */         } 
/*    */         return;
/*    */       } 
/* 64 */       this.vfxDone = true;
/*    */     } 
/*    */     
/* 67 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\EmptyDeckShuffleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */