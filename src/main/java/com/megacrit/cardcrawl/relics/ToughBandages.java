/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ToughBandages extends AbstractRelic {
/*    */   public static final String ID = "Tough Bandages";
/*    */   
/*    */   public ToughBandages() {
/* 13 */     super("Tough Bandages", "tough_bandages.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */   private static final int BLOCK_AMT = 3;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onManualDiscard() {
/* 23 */     flash();
/* 24 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 25 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 3, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new ToughBandages();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\ToughBandages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */