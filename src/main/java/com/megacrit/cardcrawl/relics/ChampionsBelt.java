/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class ChampionsBelt extends AbstractRelic {
/*    */   public static final String ID = "Champion Belt";
/*    */   
/*    */   public ChampionsBelt() {
/* 15 */     super("Champion Belt", "championBelt.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */   public static final int EFFECT = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger(AbstractCreature target) {
/* 25 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 26 */     addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower(target, 1, false), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 31 */     return new ChampionsBelt();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\ChampionsBelt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */