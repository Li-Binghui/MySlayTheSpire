/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardQueueItem;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class MummifiedHand
/*    */   extends AbstractRelic {
/* 16 */   private static final Logger logger = LogManager.getLogger(MummifiedHand.class.getName());
/*    */   public static final String ID = "Mummified Hand";
/*    */   
/*    */   public MummifiedHand() {
/* 20 */     super("Mummified Hand", "mummifiedHand.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 25 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 30 */     if (card.type == AbstractCard.CardType.POWER) {
/* 31 */       flash();
/* 32 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */       
/* 34 */       ArrayList<AbstractCard> groupCopy = new ArrayList<>();
/* 35 */       for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
/* 36 */         if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce) {
/* 37 */           groupCopy.add(abstractCard); continue;
/*    */         } 
/* 39 */         logger.info("COST IS 0: " + abstractCard.name);
/*    */       } 
/*    */ 
/*    */       
/* 43 */       for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
/* 44 */         if (i.card != null) {
/* 45 */           logger.info("INVALID: " + i.card.name);
/* 46 */           groupCopy.remove(i.card);
/*    */         } 
/*    */       } 
/*    */       
/* 50 */       AbstractCard c = null;
/* 51 */       if (!groupCopy.isEmpty()) {
/* 52 */         logger.info("VALID CARDS: ");
/* 53 */         for (AbstractCard cc : groupCopy) {
/* 54 */           logger.info(cc.name);
/*    */         }
/*    */         
/* 57 */         c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
/*    */       } else {
/* 59 */         logger.info("NO VALID CARDS");
/*    */       } 
/*    */       
/* 62 */       if (c != null) {
/* 63 */         logger.info("Mummified hand: " + c.name);
/* 64 */         c.setCostForTurn(0);
/*    */       } else {
/* 66 */         logger.info("ERROR: MUMMIFIED HAND NOT WORKING");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 73 */     return new MummifiedHand();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MummifiedHand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */