/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PandorasBox
/*    */   extends AbstractRelic
/*    */ {
/*    */   public static final String ID = "Pandora's Box";
/* 19 */   private int count = 0;
/*    */   private boolean calledTransform = true;
/*    */   
/*    */   public PandorasBox() {
/* 23 */     super("Pandora's Box", "pandoras_box.png", RelicTier.BOSS, LandingSound.MAGICAL);
/* 24 */     removeStrikeTip();
/*    */   }
/*    */   
/*    */   private void removeStrikeTip() {
/* 28 */     ArrayList<String> strikes = new ArrayList<>();
/*    */     
/* 30 */     for (String s : GameDictionary.STRIKE.NAMES) {
/* 31 */       strikes.add(s.toLowerCase());
/*    */     }
/*    */     
/* 34 */     for (Iterator<PowerTip> t = this.tips.iterator(); t.hasNext(); ) {
/* 35 */       PowerTip derp = t.next();
/* 36 */       String s = derp.header.toLowerCase();
/* 37 */       if (strikes.contains(s)) {
/* 38 */         t.remove();
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 46 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 51 */     this.calledTransform = false;
/*    */     
/* 53 */     for (Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator(); i.hasNext(); ) {
/* 54 */       AbstractCard e = i.next();
/* 55 */       if (e.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || e.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
/* 56 */         i.remove();
/* 57 */         this.count++;
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     if (this.count > 0) {
/* 62 */       CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 63 */       for (int j = 0; j < this.count; j++) {
/* 64 */         AbstractCard card = AbstractDungeon.returnTrulyRandomCard().makeCopy();
/* 65 */         UnlockTracker.markCardAsSeen(card.cardID);
/* 66 */         card.isSeen = true;
/* 67 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 68 */           r.onPreviewObtainCard(card);
/*    */         }
/* 70 */         group.addToBottom(card);
/*    */       } 
/* 72 */       AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 78 */     super.update();
/* 79 */     if (!this.calledTransform && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.GRID) {
/* 80 */       this.calledTransform = true;
/* 81 */       (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 87 */     return new PandorasBox();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\PandorasBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */