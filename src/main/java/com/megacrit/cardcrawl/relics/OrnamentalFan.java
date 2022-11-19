/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class OrnamentalFan extends AbstractRelic {
/*    */   public static final String ID = "Ornamental Fan";
/*    */   
/*    */   public OrnamentalFan() {
/* 15 */     super("Ornamental Fan", "ornamentalFan.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */   private static final int BLOCK = 4;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\004' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 25 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 30 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 31 */       this.counter++;
/*    */       
/* 33 */       if (this.counter % 3 == 0) {
/* 34 */         flash();
/* 35 */         this.counter = 0;
/* 36 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 37 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 4));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 44 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new OrnamentalFan();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\OrnamentalFan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */