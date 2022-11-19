/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class MercuryHourglass
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Mercury Hourglass";
/*    */   private static final int DMG = 3;
/*    */   
/*    */   public MercuryHourglass() {
/* 16 */     super("Mercury Hourglass", "hourglass.png", RelicTier.UNCOMMON, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 26 */     flash();
/* 27 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 28 */     addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */           
/* 31 */           DamageInfo.createDamageMatrix(3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new MercuryHourglass();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\MercuryHourglass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */