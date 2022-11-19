/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class GremlinMask extends AbstractRelic {
/*    */   public GremlinMask() {
/* 12 */     super("GremlinMask", "gremlinMask.png", RelicTier.SPECIAL, LandingSound.CLINK);
/*    */   }
/*    */   public static final String ID = "GremlinMask";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 22 */     flash();
/* 23 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 24 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, false), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 34 */     return new GremlinMask();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\GremlinMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */