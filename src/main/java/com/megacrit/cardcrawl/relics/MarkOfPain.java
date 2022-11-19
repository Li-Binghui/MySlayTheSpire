/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.Wound;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class MarkOfPain extends AbstractRelic {
/*    */   public static final String ID = "Mark of Pain";
/*    */   
/*    */   public MarkOfPain() {
/* 14 */     super("Mark of Pain", "mark_of_pain.png", RelicTier.BOSS, LandingSound.MAGICAL);
/*    */   }
/*    */   private static final int CARD_AMT = 2;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 24 */     flash();
/* 25 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 26 */     addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new Wound(), 2, true, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 31 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 36 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 41 */     return new MarkOfPain();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MarkOfPain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */