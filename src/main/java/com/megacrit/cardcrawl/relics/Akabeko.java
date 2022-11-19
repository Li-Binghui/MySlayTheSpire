/*    */ package com.megacrit.cardcrawl.relics;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
/*    */ 
/*    */ public class Akabeko extends AbstractRelic {
/*    */   public static final String ID = "Akabeko";
/*    */   
/*    */   public Akabeko() {
/* 13 */     super("Akabeko", "akabeko.png", RelicTier.COMMON, LandingSound.CLINK);
/*    */   }
/*    */   private static final int VIGOR = 8;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\b' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     flash();
/* 24 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VigorPower((AbstractCreature)AbstractDungeon.player, 8), 8));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return new Akabeko();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Akabeko.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */