/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class Necronomicon extends AbstractRelic {
/*    */   public static final String ID = "Necronomicon";
/*    */   private static final int COST_THRESHOLD = 2;
/*    */   private boolean activated = true;
/*    */   
/*    */   public Necronomicon() {
/* 23 */     super("Necronomicon", "necronomicon.png", RelicTier.SPECIAL, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 28 */     return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 33 */     CardCrawlGame.sound.play("NECRONOMICON");
/* 34 */     this.description = this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[2];
/* 35 */     AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Necronomicurse(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */     
/* 37 */     UnlockTracker.markCardAsSeen("Necronomicurse");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 42 */     AbstractCard cardToRemove = null;
/* 43 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 44 */       if (c instanceof Necronomicurse) {
/* 45 */         cardToRemove = c;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 50 */     if (cardToRemove != null) {
/* 51 */       AbstractDungeon.player.masterDeck.group.remove(cardToRemove);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 57 */     if (card.type == AbstractCard.CardType.ATTACK && ((card.costForTurn >= 2 && !card.freeToPlayOnce) || (card.cost == -1 && card.energyOnUse >= 2)) && this.activated) {
/*    */       
/* 59 */       this.activated = false;
/* 60 */       flash();
/* 61 */       AbstractMonster m = null;
/*    */       
/* 63 */       if (action.target != null) {
/* 64 */         m = (AbstractMonster)action.target;
/*    */       }
/*    */       
/* 67 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 68 */       AbstractCard tmp = card.makeSameInstanceOf();
/* 69 */       tmp.current_x = card.current_x;
/* 70 */       tmp.current_y = card.current_y;
/* 71 */       tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
/* 72 */       tmp.target_y = Settings.HEIGHT / 2.0F;
/* 73 */       tmp.applyPowers();
/* 74 */       tmp.purgeOnUse = true;
/* 75 */       AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
/*    */ 
/*    */       
/* 78 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 84 */     this.activated = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkTrigger() {
/* 89 */     return this.activated;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 94 */     return new Necronomicon();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Necronomicon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */