/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.ThornsPower;
/*    */ 
/*    */ public class BronzeScales extends AbstractRelic {
/*    */   public static final String ID = "Bronze Scales";
/*    */   
/*    */   public BronzeScales() {
/* 13 */     super("Bronze Scales", "bronzeScales.png", RelicTier.COMMON, LandingSound.CLINK);
/*    */   }
/*    */   private static final int DAMAGE = 3;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     flash();
/* 24 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new ThornsPower((AbstractCreature)AbstractDungeon.player, 3), 3));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 34 */     return new BronzeScales();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BronzeScales.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */