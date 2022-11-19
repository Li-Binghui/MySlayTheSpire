/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Miracle;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class HolyWater extends AbstractRelic {
/*    */   public HolyWater() {
/* 12 */     super("HolyWater", "holy_water.png", RelicTier.BOSS, LandingSound.MAGICAL);
/*    */   }
/*    */   public static final String ID = "HolyWater";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 22 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 23 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Miracle(), 3, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 28 */     return AbstractDungeon.player.hasRelic("PureWater");
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new HolyWater();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\HolyWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */