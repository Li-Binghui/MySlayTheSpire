/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*    */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.ui.FtueTip;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ShuffleAllAction
/*    */   extends AbstractGameAction {
/* 19 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Shuffle Tip");
/* 20 */   public static final String[] MSG = tutorialStrings.TEXT;
/* 21 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*    */   private boolean shuffled = false;
/*    */   private boolean vfxDone = false;
/* 24 */   private int count = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ShuffleAllAction() {
/* 30 */     setValues(null, null, 0);
/* 31 */     this.actionType = ActionType.SHUFFLE;
/*    */     
/* 33 */     if (!((Boolean)TipTracker.tips.get("SHUFFLE_TIP")).booleanValue()) {
/* 34 */       AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.SHUFFLE);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 40 */       TipTracker.neverShowAgain("SHUFFLE_TIP");
/*    */     } 
/* 42 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 43 */       r.onShuffle();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 49 */     if (!this.shuffled) {
/* 50 */       this.shuffled = true;
/* 51 */       AbstractPlayer p = AbstractDungeon.player;
/* 52 */       addToTop((AbstractGameAction)new PutOnDeckAction((AbstractCreature)p, (AbstractCreature)p, 99, true));
/* 53 */       p.discardPile.shuffle(AbstractDungeon.shuffleRng);
/*    */     } 
/*    */ 
/*    */     
/* 57 */     if (!this.vfxDone) {
/* 58 */       Iterator<AbstractCard> c = AbstractDungeon.player.discardPile.group.iterator(); if (c.hasNext()) {
/* 59 */         this.count++;
/* 60 */         AbstractCard e = c.next();
/* 61 */         c.remove();
/* 62 */         if (this.count < 11) {
/* 63 */           (AbstractDungeon.getCurrRoom()).souls.shuffle(e, false);
/*    */         } else {
/* 65 */           (AbstractDungeon.getCurrRoom()).souls.shuffle(e, true);
/*    */         } 
/*    */         return;
/*    */       } 
/* 69 */       this.vfxDone = true;
/*    */     } 
/*    */     
/* 72 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ShuffleAllAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */