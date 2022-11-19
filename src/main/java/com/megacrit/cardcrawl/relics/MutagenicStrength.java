/*    */ package com.megacrit.cardcrawl.relics;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/*    */
/*    */ public class MutagenicStrength extends AbstractRelic {
/*    */   public static final String ID = "MutagenicStrength";
/*    */   
/*    */   public MutagenicStrength() {
/* 14 */     super("MutagenicStrength", "mutagen.png", RelicTier.SPECIAL, LandingSound.CLINK);
/*    */   }
/*    */   private static final int STR_AMT = 3;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1] + '\003' + this.DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 24 */     flash();
/* 25 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, 3), 3));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new LoseStrengthPower((AbstractCreature)AbstractDungeon.player, 3), 3));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 42 */     return new MutagenicStrength();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MutagenicStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */