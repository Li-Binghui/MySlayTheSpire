/*    */ package com.megacrit.cardcrawl.relics.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.LoseStrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class DEPRECATEDYin extends AbstractRelic {
/*    */   public DEPRECATEDYin() {
/* 18 */     super("Yin", "yin.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "Yin";
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 28 */     if (card.type == AbstractCard.CardType.SKILL) {
/* 29 */       flash();
/* 30 */       AbstractPlayer p = AbstractDungeon.player;
/* 31 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)p, this));
/* 32 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, 1), 1));
/* 33 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new LoseStrengthPower((AbstractCreature)p, 1), 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 39 */     return new DEPRECATEDYin();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\deprecated\DEPRECATEDYin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */