/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class CharonsAshes
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Charon's Ashes";
/*    */   public static final int DMG = 3;
/*    */   
/*    */   public CharonsAshes() {
/* 18 */     super("Charon's Ashes", "ashes.png", RelicTier.RARE, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onExhaust(AbstractCard card) {
/* 28 */     flash();
/*    */     
/* 30 */     addToTop((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */           
/* 33 */           DamageInfo.createDamageMatrix(3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
/*    */ 
/*    */ 
/*    */     
/* 37 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 38 */       if (!mo.isDead) {
/* 39 */         addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)mo, this));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 47 */     return new CharonsAshes();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\CharonsAshes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */