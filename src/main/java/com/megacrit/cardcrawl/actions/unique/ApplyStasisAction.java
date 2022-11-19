/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StasisPower;
/*    */ 
/*    */ public class ApplyStasisAction extends AbstractGameAction {
/*    */   private AbstractCreature owner;
/* 15 */   private AbstractCard card = null; private float startingDuration;
/*    */   
/*    */   public ApplyStasisAction(AbstractCreature owner) {
/* 18 */     this.owner = owner;
/* 19 */     this.duration = Settings.ACTION_DUR_LONG;
/* 20 */     this.startingDuration = Settings.ACTION_DUR_LONG;
/* 21 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (AbstractDungeon.player.drawPile.isEmpty() && AbstractDungeon.player.discardPile.isEmpty()) {
/* 27 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 31 */     if (this.duration == this.startingDuration) {
/* 32 */       if (AbstractDungeon.player.drawPile.isEmpty()) {
/* 33 */         this.card = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng, AbstractCard.CardRarity.RARE);
/*    */ 
/*    */         
/* 36 */         if (this.card == null) {
/* 37 */           this.card = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng, AbstractCard.CardRarity.UNCOMMON);
/*    */ 
/*    */           
/* 40 */           if (this.card == null) {
/* 41 */             this.card = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng, AbstractCard.CardRarity.COMMON);
/*    */ 
/*    */             
/* 44 */             if (this.card == null) {
/* 45 */               this.card = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
/*    */             }
/*    */           } 
/*    */         } 
/* 49 */         AbstractDungeon.player.discardPile.removeCard(this.card);
/*    */       } else {
/* 51 */         this.card = AbstractDungeon.player.drawPile.getRandomCard(AbstractDungeon.cardRandomRng, AbstractCard.CardRarity.RARE);
/*    */ 
/*    */         
/* 54 */         if (this.card == null) {
/* 55 */           this.card = AbstractDungeon.player.drawPile.getRandomCard(AbstractDungeon.cardRandomRng, AbstractCard.CardRarity.UNCOMMON);
/*    */ 
/*    */           
/* 58 */           if (this.card == null) {
/* 59 */             this.card = AbstractDungeon.player.drawPile.getRandomCard(AbstractDungeon.cardRandomRng, AbstractCard.CardRarity.COMMON);
/*    */ 
/*    */             
/* 62 */             if (this.card == null) {
/* 63 */               this.card = AbstractDungeon.player.drawPile.getRandomCard(AbstractDungeon.cardRandomRng);
/*    */             }
/*    */           } 
/*    */         } 
/* 67 */         AbstractDungeon.player.drawPile.removeCard(this.card);
/*    */       } 
/*    */       
/* 70 */       AbstractDungeon.player.limbo.addToBottom(this.card);
/* 71 */       this.card.setAngle(0.0F);
/* 72 */       this.card.targetDrawScale = 0.75F;
/* 73 */       this.card.target_x = Settings.WIDTH / 2.0F;
/* 74 */       this.card.target_y = Settings.HEIGHT / 2.0F;
/* 75 */       this.card.lighten(false);
/* 76 */       this.card.unfadeOut();
/* 77 */       this.card.unhover();
/* 78 */       this.card.untip();
/* 79 */       this.card.stopGlowing();
/*    */     } 
/*    */     
/* 82 */     tickDuration();
/* 83 */     if (this.isDone && this.card != null) {
/* 84 */       addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StasisPower(this.owner, this.card)));
/* 85 */       addToTop((AbstractGameAction)new ShowCardAction(this.card));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ApplyStasisAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */