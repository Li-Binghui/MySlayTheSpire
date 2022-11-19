/*     */ package com.megacrit.cardcrawl.actions.unique;
/*     */ 
/*     */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
/*     */ 
/*     */ public class DiscoveryAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   private boolean retrieveCard = false;
/*     */   private boolean returnColorless = false;
/*  17 */   private AbstractCard.CardType cardType = null;
/*     */   
/*     */   public DiscoveryAction() {
/*  20 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  21 */     this.duration = Settings.ACTION_DUR_FAST;
/*  22 */     this.amount = 1;
/*     */   }
/*     */   
/*     */   public DiscoveryAction(AbstractCard.CardType type, int amount) {
/*  26 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  27 */     this.duration = Settings.ACTION_DUR_FAST;
/*  28 */     this.amount = amount;
/*  29 */     this.cardType = type;
/*     */   }
/*     */   
/*     */   public DiscoveryAction(boolean colorless, int amount) {
/*  33 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  34 */     this.duration = Settings.ACTION_DUR_FAST;
/*  35 */     this.amount = amount;
/*  36 */     this.returnColorless = colorless;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*     */     ArrayList<AbstractCard> generatedCards;
/*  42 */     if (this.returnColorless) {
/*  43 */       generatedCards = generateColorlessCardChoices();
/*     */     } else {
/*  45 */       generatedCards = generateCardChoices(this.cardType);
/*     */     } 
/*     */     
/*  48 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*  49 */       AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], (this.cardType != null));
/*     */ 
/*     */ 
/*     */       
/*  53 */       tickDuration();
/*     */       
/*     */       return;
/*     */     } 
/*  57 */     if (!this.retrieveCard) {
/*  58 */       if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
/*  59 */         AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
/*  60 */         AbstractCard disCard2 = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
/*     */         
/*  62 */         if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
/*  63 */           disCard.upgrade();
/*  64 */           disCard2.upgrade();
/*     */         } 
/*     */         
/*  67 */         disCard.setCostForTurn(0);
/*  68 */         disCard2.setCostForTurn(0);
/*     */         
/*  70 */         disCard.current_x = -1000.0F * Settings.xScale;
/*  71 */         disCard2.current_x = -1000.0F * Settings.xScale + AbstractCard.IMG_HEIGHT_S;
/*     */         
/*  73 */         if (this.amount == 1) {
/*  74 */           if (AbstractDungeon.player.hand.size() < 10) {
/*  75 */             AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */           } else {
/*     */             
/*  78 */             AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */           } 
/*     */           
/*  81 */           disCard2 = null;
/*     */         }
/*  83 */         else if (AbstractDungeon.player.hand.size() + this.amount <= 10) {
/*  84 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  89 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard2, Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*  95 */         else if (AbstractDungeon.player.hand.size() == 9) {
/*  96 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 101 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 107 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 112 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 121 */         AbstractDungeon.cardRewardScreen.discoveryCard = null;
/*     */       } 
/* 123 */       this.retrieveCard = true;
/*     */     } 
/*     */     
/* 126 */     tickDuration();
/*     */   }
/*     */   
/*     */   private ArrayList<AbstractCard> generateColorlessCardChoices() {
/* 130 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*     */     
/* 132 */     while (derp.size() != 3) {
/* 133 */       boolean dupe = false;
/*     */       
/* 135 */       AbstractCard tmp = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
/* 136 */       for (AbstractCard c : derp) {
/* 137 */         if (c.cardID.equals(tmp.cardID)) {
/* 138 */           dupe = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 142 */       if (!dupe) {
/* 143 */         derp.add(tmp.makeCopy());
/*     */       }
/*     */     } 
/*     */     
/* 147 */     return derp;
/*     */   }
/*     */   
/*     */   private ArrayList<AbstractCard> generateCardChoices(AbstractCard.CardType type) {
/* 151 */     ArrayList<AbstractCard> derp = new ArrayList<>();
/*     */     
/* 153 */     while (derp.size() != 3) {
/* 154 */       boolean dupe = false;
/* 155 */       AbstractCard tmp = null;
/* 156 */       if (type == null) {
/* 157 */         tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
/*     */       } else {
/* 159 */         tmp = AbstractDungeon.returnTrulyRandomCardInCombat(type);
/*     */       } 
/* 161 */       for (AbstractCard c : derp) {
/* 162 */         if (c.cardID.equals(tmp.cardID)) {
/* 163 */           dupe = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 167 */       if (!dupe) {
/* 168 */         derp.add(tmp.makeCopy());
/*     */       }
/*     */     } 
/*     */     
/* 172 */     return derp;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DiscoveryAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */