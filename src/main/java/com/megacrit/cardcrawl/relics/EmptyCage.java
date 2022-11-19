/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.ArrayList;
import java.util.Iterator;
/*    */ 
/*    */ public class EmptyCage
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Empty Cage";
/*    */   private boolean cardsSelected = true;
/*    */   
/*    */   public EmptyCage() {
/* 18 */     super("Empty Cage", "cage.png", RelicTier.BOSS, LandingSound.SOLID);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 28 */     this.cardsSelected = false;
/* 29 */     if (AbstractDungeon.isScreenUp) {
/* 30 */       AbstractDungeon.dynamicBanner.hide();
/* 31 */       AbstractDungeon.overlayMenu.cancelButton.hide();
/* 32 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*    */     } 
/* 34 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.INCOMPLETE;
/*    */     
/* 36 */     CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 37 */     for (AbstractCard card : (AbstractDungeon.player.masterDeck.getPurgeableCards()).group) {
/* 38 */       tmp.addToTop(card);
/*    */     }
/*    */     
/* 41 */     if (tmp.group.isEmpty()) {
/* 42 */       this.cardsSelected = true; return;
/*    */     } 
/* 44 */     if (tmp.group.size() <= 2) {
/* 45 */       deleteCards(tmp.group);
/*    */     } else {
/* 47 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/* 48 */           .getPurgeableCards(), 2, this.DESCRIPTIONS[1], false, false, false, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 60 */     super.update();
/* 61 */     if (!this.cardsSelected && 
/* 62 */       AbstractDungeon.gridSelectScreen.selectedCards.size() == 2) {
/* 63 */       deleteCards(AbstractDungeon.gridSelectScreen.selectedCards);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void deleteCards(ArrayList<AbstractCard> group) {
/* 69 */     this.cardsSelected = true;
/* 70 */     float displayCount = 0.0F;
/* 71 */     for (Iterator<AbstractCard> i = group.iterator(); i.hasNext(); ) {
/* 72 */       AbstractCard card = i.next();
/* 73 */       card.untip();
/* 74 */       card.unhover();
/* 75 */       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, Settings.WIDTH / 3.0F + displayCount, Settings.HEIGHT / 2.0F));
/*    */       
/* 77 */       displayCount += Settings.WIDTH / 6.0F;
/* 78 */       AbstractDungeon.player.masterDeck.removeCard(card);
/*    */     } 
/*    */     
/* 81 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 82 */     AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 87 */     return new EmptyCage();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\EmptyCage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */