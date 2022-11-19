/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.screens.CardRewardScreen;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ChooseOneColorless
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private boolean retrieveCard = false;
/*    */   
/*    */   public ChooseOneColorless() {
/* 18 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 19 */     this.duration = Settings.ACTION_DUR_FAST;
/* 20 */     this.amount = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 26 */       AbstractDungeon.cardRewardScreen.customCombatOpen(generateCardChoices(), CardRewardScreen.TEXT[1], false);
/* 27 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 31 */     if (!this.retrieveCard) {
/* 32 */       if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
/* 33 */         AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
/*    */         
/* 35 */         if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
/* 36 */           disCard.upgrade();
/*    */         }
/*    */         
/* 39 */         disCard.current_x = -1000.0F * Settings.xScale;
/*    */         
/* 41 */         if (this.amount == 1) {
/* 42 */           if (AbstractDungeon.player.hand.size() < 10) {
/* 43 */             AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */           } else {
/*    */             
/* 46 */             AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */           }
/*    */         
/*    */         }
/* 50 */         else if (AbstractDungeon.player.hand.size() + this.amount <= 10) {
/* 51 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         }
/* 57 */         else if (AbstractDungeon.player.hand.size() == 9) {
/* 58 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */         
/*    */         }
/*    */         else {
/*    */ 
/*    */           
/* 64 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */         } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 73 */         AbstractDungeon.cardRewardScreen.discoveryCard = null;
/*    */       } 
/* 75 */       this.retrieveCard = true;
/*    */     } 
/*    */     
/* 78 */     tickDuration();
/*    */   }
/*    */   
/*    */   private ArrayList<AbstractCard> generateCardChoices() {
/* 82 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*    */     
/* 84 */     while (derp.size() != 3) {
/* 85 */       boolean dupe = false;
/* 86 */       AbstractCard tmp = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
/* 87 */       for (AbstractCard c : derp) {
/* 88 */         if (c.cardID.equals(tmp.cardID)) {
/* 89 */           dupe = true;
/*    */           break;
/*    */         } 
/*    */       } 
/* 93 */       if (!dupe) {
/* 94 */         derp.add(tmp.makeCopy());
/*    */       }
/*    */     } 
/*    */     
/* 98 */     return derp;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ChooseOneColorless.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */