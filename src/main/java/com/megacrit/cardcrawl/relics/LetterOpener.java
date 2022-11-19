/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class LetterOpener
/*    */   extends AbstractRelic
/*    */ {
/*    */   public static final String ID = "Letter Opener";
/*    */   private static final int DAMAGE = 5;
/*    */   
/*    */   public LetterOpener() {
/* 19 */     super("Letter Opener", "letterOpener.png", RelicTier.UNCOMMON, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0] + '\005' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 29 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 34 */     if (card.type == AbstractCard.CardType.SKILL) {
/* 35 */       this.counter++;
/*    */       
/* 37 */       if (this.counter % 3 == 0) {
/* 38 */         flash();
/* 39 */         this.counter = 0;
/* 40 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 41 */         addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */               
/* 44 */               DamageInfo.createDamageMatrix(5, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 53 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 58 */     return new LetterOpener();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\LetterOpener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */