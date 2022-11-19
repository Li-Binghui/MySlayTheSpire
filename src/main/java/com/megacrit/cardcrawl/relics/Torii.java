/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Torii extends AbstractRelic {
/*    */   public static final String ID = "Torii";
/*    */   private static final int THRESHOLD = 5;
/*    */   
/*    */   public Torii() {
/* 14 */     super("Torii", "torii.png", RelicTier.RARE, LandingSound.HEAVY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\005' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 24 */     if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 1 && damageAmount <= 5) {
/*    */       
/* 26 */       flash();
/* 27 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 28 */       return 1;
/*    */     } 
/* 30 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return new Torii();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Torii.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */